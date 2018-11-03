package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.beans.impl.LKPageBean;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
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


	public List<SysDeptEntity> findExist(String id, String compId, String busCompId, String parentCode, String deptName) {
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysDeptR.id, id);
		}

		sql.eq(SysDeptR.compId, compId);
		sql.eq(SysDeptR.parentCode, parentCode);
		sql.eq(SysDeptR.deptName, deptName);

		return dao.getList(sql, SysDeptEntity.class);
	}


	public String analysisDeptCode(String compId, String parentCode) {
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);
		sql.eq(SysDeptR.compId, compId);
		sql.eq(SysDeptR.parentCode, parentCode);
		sql.setPage(new LKPageBean(1));
		sql.addOrders(new Order(SysDeptR.deptCode, false));
		Page<SysDeptEntity> page = dao.getPage(sql, SysDeptEntity.class);
		if (CollectionUtils.isNotEmpty(page.getContent())) {
			return LKCodeUtils.nextCode(page.getContent().get(0).getDeptCode());
		}
		if (LKCodeUtils.currentLevel(parentCode) == 8) {
			throw new LKRuntimeException(ErrorCodes.SysDept_LEVEL_OUT);
		}
		return LKCodeUtils.createCode(parentCode);
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
