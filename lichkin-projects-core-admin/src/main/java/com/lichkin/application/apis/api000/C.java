package com.lichkin.application.apis.api000;import static com.lichkin.springframework.web.LKRequestStatics.REQUEST_ID;import static com.lichkin.springframework.web.LKRequestStatics.REQUEST_IP;import static com.lichkin.springframework.web.LKRequestStatics.REQUEST_TIME;import java.util.ArrayList;import java.util.List;import org.apache.commons.collections.CollectionUtils;import org.joda.time.DateTime;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;import com.lichkin.framework.defines.LKFrameworkStatics;import com.lichkin.framework.defines.entities.I_Menu;import com.lichkin.framework.defines.entities.I_Role;import com.lichkin.framework.defines.exceptions.LKException;import com.lichkin.framework.utils.LKDateTimeUtils;import com.lichkin.framework.web.annotations.LKApiType;import com.lichkin.framework.web.enums.ApiType;import com.lichkin.springframework.controllers.ApiKeyValues;import com.lichkin.springframework.controllers.LKApiVYController;import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;import com.lichkin.springframework.entities.impl.SysMenuEntity;import com.lichkin.springframework.entities.impl.SysRoleEntity;import com.lichkin.springframework.services.LKApiService;import com.lichkin.springframework.web.LKSession;@RestController("AccountLoginController")@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/Web/AccountLogin")@LKApiType(apiType = ApiType.OPEN)public class C extends LKApiVYController<I, SO> {	@Autowired	private S service;	@Override	protected LKApiService<I, SO> getService(I cin, ApiKeyValues<I> params) {		return service;	}	@Override	protected void afterInvokeService(I cin, ApiKeyValues<I> params, SO sout) throws LKException {		SysAdminLoginEntity login = sout.getAdminLogin();		LKSession.setLogin(session, login);		LKSession.setComp(session, sout.getComp());		LKSession.setUser(session, login);		List<SysRoleEntity> listRole = sout.getListRole();		if (CollectionUtils.isNotEmpty(listRole)) {			List<I_Role> roles = new ArrayList<>(listRole.size());			roles.addAll(listRole);			LKSession.setRoles(session, roles);		}		List<SysMenuEntity> listMenu = sout.getListMenu();		if (CollectionUtils.isNotEmpty(listMenu)) {			List<I_Menu> menus = new ArrayList<>(listMenu.size());			menus.addAll(listMenu);			LKSession.setMenus(session, menus);		}		if (!login.getCompId().equals(LKFrameworkStatics.LichKin)) {			service.saveLog(					login.getId(),					login.getCompId(),					(String) request.getAttribute(REQUEST_ID),					LKDateTimeUtils.toString((DateTime) request.getAttribute(REQUEST_TIME)),					(String) request.getAttribute(REQUEST_IP)			);		}	}	@Override	protected boolean saveLog(I cin, ApiKeyValues<I> params) {		return false;	}}