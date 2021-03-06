package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKCodeService;
import com.lichkin.springframework.services.LKDBService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class SysDeptBusService extends LKDBService {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDept_LEVEL_OUT(20000),

		;

		private final Integer code;

	}


	public List<SysDeptEntity> findExist(ApiKeyValues<?> params, String parentCode, String deptName) {
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);

		addConditionId(sql, SysDeptR.id, params.getId());
//		addConditionLocale(sql, SysDeptR.locale, params.getLocale());
		addConditionCompId(true, sql, SysDeptR.compId, params.getCompId(), params.getBusCompId());
//		addConditionUsingStatus(true, params.getCompId(), sql, SysDeptR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		sql.eq(SysDeptR.parentCode, parentCode);
		sql.eq(SysDeptR.deptName, deptName);

		return dao.getList(sql, SysDeptEntity.class);
	}


	@Autowired
	private LKCodeService codeService;


	public String analysisDeptCode(String compId, String parentCode) {
		return codeService.analysisCode(SysDeptEntity.class, compId, parentCode, "deptCode", SysDeptR.compId, SysDeptR.parentCode, SysDeptR.deptCode, ErrorCodes.SysDept_LEVEL_OUT);
	}


	public String analysisFullName(String compId, String deptCode, String deptName) {
		// 存储部门全名
		List<String> parentsCode = LKCodeUtils.parentsCode(deptCode, true);
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);
		sql.eq(SysDeptR.compId, compId);
		sql.in(SysDeptR.deptCode, parentsCode);
		sql.addOrders(new Order(SysDeptR.deptCode));
		List<SysDeptEntity> parentDeptList = dao.getList(sql, SysDeptEntity.class);
		StringBuffer sb = new StringBuffer();
		for (SysDeptEntity dept : parentDeptList) {
			sb.append(dept.getDeptName() + " -> ");
		}

		// 超长处理
		String fullName = null;
		int sbLength = sb.length();
		int deptNameLength = deptName.length();
		if ((sbLength + deptNameLength) > 256) {
			fullName = sb.substring(0, 256 - deptNameLength - "...".length()) + "..." + deptName;
		} else {
			fullName = sb.append(deptName).toString();
		}
		return fullName;
	}


	public String analysisFullName(String id, String deptCode, String oldDeptName, String newDeptName, String fullName) {
		if (!newDeptName.equals(oldDeptName)) {
			int position = LKCodeUtils.currentLevel(deptCode) - 1;

			QuerySQL sql = new QuerySQL(SysDeptEntity.class);
			sql.like(SysDeptR.deptCode, LikeType.RIGHT, LKCodeUtils.realCode(deptCode));
			List<SysDeptEntity> deptList = dao.getList(sql, SysDeptEntity.class);
			for (SysDeptEntity dept : deptList) {
				String newFullName = newFullName(dept.getFullName(), position, newDeptName);
				dept.setFullName(newFullName);
				if (dept.getId().equals(id)) {
					fullName = newFullName;
				}
			}
			dao.mergeList(deptList);
		}
		return fullName;
	}


	private String newFullName(String oldFullName, int position, String newDeptName) {
		StringBuilder sb = new StringBuilder();
		String[] deptNames = oldFullName.split(" -> ");
		for (int i = 0; i < deptNames.length; i++) {
			if (i == position) {
				sb.append(newDeptName);
			} else {
				sb.append(deptNames[i]);
			}
			if (i != (deptNames.length - 1)) {
				sb.append(" -> ");
			}
		}

		// 超长处理
		String newFullName = sb.toString();
		if (newFullName.length() > 256) {
			String lastDeptName = newFullName.substring(newFullName.lastIndexOf(" -> ") + " -> ".length());
			newFullName = newFullName.substring(0, 256 - lastDeptName.length() - "...".length()) + "..." + lastDeptName;
		}
		return newFullName;
	}

}
