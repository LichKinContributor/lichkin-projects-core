package com.lichkin.application.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.lichkin.framework.defines.LKConfigStatics;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.CategoryAuthTypeEnum;
import com.lichkin.framework.utils.LKListUtils;

public class LKSysCatagoryCache {

	private static List<Category> listInner = new ArrayList<>();

	private static Map<String, Map<String, Category>> mapInner = new HashMap<>();


	public static Category get(String locale, String categoryCode) {
		return mapInner.get(locale).get(categoryCode);
	}


	public static void initList(List<Category> list) {
		listInner.clear();
		mapInner.clear();
		listInner = list;
		Locale[] locales = LKConfigStatics.IMPLEMENTED_LOCALE_ARR;
		for (Locale locale : locales) {
			if (!locale.getLanguage().equals("en")) {
				mapInner.put(locale.toString(), new HashMap<>());
			}
		}
		mapInner.put("en", new HashMap<>());
		for (Category category : list) {
			mapInner.get(category.getLocale()).put(category.getCategoryCode(), category);
		}
	}


	public static List<Category> getList(boolean showFull, String locale, String compId, String categoryCode, String categoryName, CategoryAuthTypeEnum authType) {
		return (LKFrameworkStatics.LichKin.equals(compId) ? LKListUtils.deepCopy(listInner) : listInner).stream()

				.filter(category -> {
					return (LKFrameworkStatics.LichKin.equals(compId) && (locale == null)) ? true : category.getLocale().equals(locale);
				})

				.filter(category -> {
					return StringUtils.isEmpty(categoryCode) || (category.getCategoryCode().indexOf(categoryCode) >= 0);
				})

				.filter(category -> {
					return StringUtils.isEmpty(categoryName) || (category.getCategoryName().indexOf(categoryName) >= 0);
				})

				.filter(category -> {
					String authTypeDictCode = category.getAuthTypeDictCode();
					if (LKFrameworkStatics.LichKin.equals(compId)) {
						category.setRoot(true);
						switch (authTypeDictCode) {
							case "COMP":
								return showFull ? (authType == null) || authType.toString().equals(authTypeDictCode) : false;
						}
					} else {
						switch (authTypeDictCode) {
							case "ENUM":
							case "ROOT":
							case "R_2_C":
								return false;
						}
					}

					if (authType != null) {
						return authType.toString().equals(authTypeDictCode);
					}
					return true;
				})

				.collect(Collectors.toList());
	}


	public static List<Category> getList(String locale) {
		return listInner.stream()

				.filter(category -> {
					return category.getLocale().equals(locale);
				})

				.filter(category -> {
					String authTypeDictCode = category.getAuthTypeDictCode();
					switch (authTypeDictCode) {
						case "ENUM":
						case "ROOT":
						case "R_2_C":
							return true;
						default:
							return false;
					}
				})

				.collect(Collectors.toList());
	}

}
