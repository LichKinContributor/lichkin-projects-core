package com.lichkin.application.apis.api003;import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;import com.lichkin.application.services.impl.SysMenuService;import com.lichkin.framework.beans.impl.LKRequestBean;import com.lichkin.framework.defines.LKFrameworkStatics;import com.lichkin.framework.defines.beans.impl.LKTreeBean;import com.lichkin.framework.defines.entities.I_Menu;import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;import com.lichkin.framework.defines.exceptions.LKException;import com.lichkin.framework.utils.LKTreeUtils;import com.lichkin.framework.web.annotations.LKApiType;import com.lichkin.framework.web.enums.ApiType;import com.lichkin.springframework.controllers.LKApiY0Controller;import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;import com.lichkin.springframework.web.LKSession;@RestController("GetAssignableMenusController")@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_ADMIN + "/AssignableMenus")@LKApiType(apiType = ApiType.COMPANY_BUSINESS)public class C extends LKApiY0Controller<LKRequestBean, List<LKTreeBean>> {	@Autowired	private SysMenuService service;	@Override	protected List<LKTreeBean> doInvoke(LKRequestBean cin) throws LKException {		SysAdminLoginEntity entity = (SysAdminLoginEntity) LKSession.getLogin(session);		if (LKSession.getCompId(session).equals(LKFrameworkStatics.LichKin) && entity.getSuperAdmin().equals(LKYesNoEnum.YES)) {			return LKTreeUtils.toTree(service.findAllAssignableAsSuperAdmin(), "id", "menuName", "menuCode", "parentCode");		}		List<I_Menu> menus = LKSession.getMenus(session);		for (int i = menus.size() - 1; i >= 0; i--) {			if (menus.get(i).getAssignable().equals(LKYesNoEnum.NO)) {				menus.remove(i);			}		}		return LKTreeUtils.toTree(menus, "id", "menuName", "menuCode", "parentCode");	}}