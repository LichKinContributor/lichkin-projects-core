package com.lichkin.application.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.springframework.entities.impl.SysCategoryEntity;

public class LKSysCatagoryCache {

	static List<SysCategoryEntity> list = new ArrayList<>();

	static Map<String, SysCategoryEntity> map = new HashMap<>();


	public static SysCategoryEntity get(String configKey) {
		return map.get(configKey);
	}


	public static List<SysCategoryEntity> getList(String locale, String compId) {
		List<SysCategoryEntity> result = new ArrayList<>();
		if (LKFrameworkStatics.LichKin.equals(compId)) {
			for (SysCategoryEntity category : list) {
				if (category.getLocale().equals(locale) && ((category.getRootOnly() == null) || LKYesNoEnum.YES.equals(category.getRootOnly()))) {
					result.add(category);
				}
			}
		} else {
			for (SysCategoryEntity category : list) {
				if (category.getLocale().equals(locale) && ((category.getRootOnly() == null) || LKYesNoEnum.NO.equals(category.getRootOnly())) && LKYesNoEnum.NO.equals(category.getAuth())) {
					result.add(category);
				}
			}
		}
		return result;
	}

}
