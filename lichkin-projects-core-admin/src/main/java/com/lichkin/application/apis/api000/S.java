package com.lichkin.application.apis.api000;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.beans.SysAdminLoginRoleR;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.db.beans.SysRoleMenuR;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.db.beans.eq_;
import com.lichkin.framework.db.beans.in;
import com.lichkin.framework.db.beans.isNull;
import com.lichkin.framework.defines.LKConfigStatics;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.beans.impl.LichKin;
import com.lichkin.framework.defines.beans.impl.LichKinUserLogin;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.framework.utils.security.otp.LKOTPEncrypter;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysAdminLoginLogEntity;
import com.lichkin.springframework.entities.impl.SysAdminLoginRoleEntity;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.entities.impl.SysRoleMenuEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.web.beans.LKRequestInfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class S extends LKApiServiceImpl<I, SO> implements LKApiService<I, SO> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		Admin_AccountLogin_account_inexistent(20000),

		Admin_AccountLogin_comp_inexistent(20000),

		Admin_AccountLogin_account_locked_byAdmin(20000),

		Admin_AccountLogin_account_locked(20000),

		Admin_AccountLogin_pwd_incorrect(20000),

		;

		private final Integer code;

	}


	@Value("${com.lichkin.api.admin.login.lockTimeoutMinutes:1440}")
	private int lockTimeoutMinutes;

	@Value("${com.lichkin.api.admin.login.maxErrorTimes:5}")
	private int maxErrorTimes;


	@Transactional
	@Override
	public SO handle(I sin, ApiKeyValues<I> params) throws LKException {
		if (LKFrameworkStatics.LichKin.equals(sin.getLoginName())) {
			if (!LKConfigStatics.WEB_ADMIN_DEBUG && !LKMD5Encrypter.encrypt(LKOTPEncrypter.encrypt()).equals(sin.getPwd())) {
				throw new LKRuntimeException(ErrorCodes.Admin_AccountLogin_pwd_incorrect).withParam("#chances", 0);
			}
			return superAdmin(sin);
		}

		SysAdminLoginEntity adminLogin = findAdminLoginByLoginName(sin.getLoginName());
		if (adminLogin == null) {
			throw new LKRuntimeException(ErrorCodes.Admin_AccountLogin_account_inexistent);
		}

		String compId = adminLogin.getCompId();
		SysCompEntity comp = compId.equals(LKFrameworkStatics.LichKin) ? superComp() : dao.findOneById(SysCompEntity.class, adminLogin.getCompId());
		if (comp == null) {
			throw new LKRuntimeException(ErrorCodes.Admin_AccountLogin_comp_inexistent);
		}

		if (LKUsingStatusEnum.LOCKED.equals(adminLogin.getUsingStatus())) {
			// 后台操作人员锁定账号，必须解锁才能登录
			if (StringUtils.isBlank(adminLogin.getLockTime())) {
				throw new LKRuntimeException(ErrorCodes.Admin_AccountLogin_account_locked_byAdmin);
			}
			int remainingTime = (int) (lockTimeoutMinutes - ((DateTime.now().getMillis() - LKDateTimeUtils.toDateTime(adminLogin.getLockTime()).getMillis()) / 60000));
			if (remainingTime > 0) {
				throw new LKRuntimeException(ErrorCodes.Admin_AccountLogin_account_locked).withParam("#remainingTime", remainingTime);
			}
			adminLogin.setErrorTimes((byte) 0);
			adminLogin.setUsingStatus(LKUsingStatusEnum.USING);
		}

		if (!adminLogin.getPwd().equals(LKMD5Encrypter.encrypt(sin.getPwd()))) {
			adminLogin.setErrorTimes((byte) (adminLogin.getErrorTimes() + 1));
			if (adminLogin.getErrorTimes() >= maxErrorTimes) {
				adminLogin.setUsingStatus(LKUsingStatusEnum.LOCKED);
				adminLogin.setLockTime(LKDateTimeUtils.now());
				dao.mergeOne(adminLogin);
				throw new LKException(ErrorCodes.Admin_AccountLogin_account_locked).withParam("#remainingTime", lockTimeoutMinutes);
			} else {
				dao.mergeOne(adminLogin);
				throw new LKException(ErrorCodes.Admin_AccountLogin_pwd_incorrect).withParam("#chances", maxErrorTimes - adminLogin.getErrorTimes());
			}
		} else {
			dao.mergeOne(adminLogin);
		}

		List<SysRoleEntity> listRole = findListRoleByLoginId(adminLogin.getId());

		List<SysMenuEntity> listAllMenu = findListMenuByListRole(sin.getLoginName(), listRole);

		List<SysMenuEntity> compListMenu = findCompanyOwnedMenus(compId);
		List<String> menuIds = new ArrayList<>();
		for (SysMenuEntity menuEntity : compListMenu) {
			menuIds.add(menuEntity.getId());
		}
		// 过滤掉员工有公司没有的菜单
		List<SysMenuEntity> listMenu = listAllMenu.stream().filter(item -> menuIds.contains(item.getId())).collect(Collectors.toList());

		SO out = new SO();
		out.setComp(comp);
		out.setAdminLogin(adminLogin);
		out.setListRole(listRole);
		out.setListMenu(listMenu);
		return out;
	}


	private SysAdminLoginEntity findAdminLoginByLoginName(String loginName) {
		if (StringUtils.isBlank(loginName)) {
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysAdminLoginEntity.class);
		sql.neq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysAdminLoginR.email, loginName);

		return dao.getOne(sql, SysAdminLoginEntity.class);
	}


	private List<SysRoleEntity> findListRoleByLoginId(String loginId) {
		QuerySQL sql = new QuerySQL(SysRoleEntity.class);
		sql.selectTable(SysRoleEntity.class);
		sql.innerJoin(SysAdminLoginRoleEntity.class, new Condition(null, new eq_(SysAdminLoginRoleR.roleId, SysRoleR.id)));

		sql.eq(SysRoleR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysAdminLoginRoleR.loginId, loginId);

		return dao.getList(sql, SysRoleEntity.class);
	}


	private List<SysMenuEntity> findListMenuByListRole(String loginName, List<SysRoleEntity> listRole) {
		if (CollectionUtils.isEmpty(listRole)) {
			return null;
		}

		final StringBuilder roleIds = new StringBuilder();
		for (int i = listRole.size() - 1; i >= 0; i--) {
			roleIds.append(listRole.get(i).getId());
			if (i != 0) {
				roleIds.append(LKFrameworkStatics.SPLITOR);
			}
		}

		// 查询菜单信息
		QuerySQL sql = new QuerySQL(SysMenuEntity.class, true);
		sql.selectTable(SysMenuEntity.class);
		sql.innerJoin(SysRoleMenuEntity.class, new Condition(null, new eq_(SysRoleMenuR.menuId, SysMenuR.id)), new Condition(true, new in(SysRoleMenuR.roleId, roleIds.toString())));

		if (!loginName.equals("lichkin@lichkin.com")) {
			sql.eq(SysMenuR.onLine, Boolean.TRUE);
		}

		sql.addOrders(new Order(SysMenuR.parentCode), new Order(SysMenuR.menuCode));

		return dao.getList(sql, SysMenuEntity.class);
	}


	private SO superAdmin(I sin) {
		SO out = new SO();
		out.setComp(superComp());
		out.setAdminLogin(LKBeanUtils.newInstance(LichKinUserLogin.getInstance(), SysAdminLoginEntity.class));
		out.setListRole(dao.getList(new QuerySQL(false, SysRoleEntity.class), SysRoleEntity.class));
		QuerySQL sql = new QuerySQL(false, SysMenuEntity.class);
		sql.where(new Condition(new Condition(new eq(SysMenuR.rootOnly, Boolean.TRUE)), new Condition(false, new isNull(SysMenuR.rootOnly))));
		out.setListMenu(dao.getList(sql, SysMenuEntity.class));
		return out;
	}


	private SysCompEntity superComp() {
		return LKBeanUtils.newInstance(LichKin.getInstance(), SysCompEntity.class);
	}


	@Transactional
	public void saveLog(String loginId, String compId, LKRequestInfo requestInfo) {
		SysAdminLoginLogEntity log = new SysAdminLoginLogEntity();
		log.setLoginId(loginId);
		log.setCompId(compId);
		log.setRequestId(requestInfo.getRequestId());
		log.setRequestTime(LKDateTimeUtils.toString(requestInfo.getRequestTime()));
		log.setRequestIp(requestInfo.getRequestIp());
		dao.persistOne(log);
	}


	private List<SysMenuEntity> findCompanyOwnedMenus(String compId) {
		QuerySQL sql = new QuerySQL(SysMenuEntity.class);
		sql.innerJoin(SysRoleMenuEntity.class, new Condition(SysMenuR.id, SysRoleMenuR.menuId));
		sql.innerJoin(SysRoleEntity.class, new Condition(SysRoleMenuR.roleId, SysRoleR.id));
		sql.innerJoin(SysAdminLoginRoleEntity.class, new Condition(SysRoleR.id, SysAdminLoginRoleR.roleId));
		sql.innerJoin(SysAdminLoginEntity.class, new Condition(SysAdminLoginRoleR.loginId, SysAdminLoginR.id));
		sql.innerJoin(SysCompEntity.class, new Condition(SysAdminLoginR.compId, SysCompR.id));
		sql.eq(SysCompR.id, compId);
		sql.eq(SysAdminLoginR.superAdmin, true);
		return dao.getList(sql, SysMenuEntity.class);
	}

}
