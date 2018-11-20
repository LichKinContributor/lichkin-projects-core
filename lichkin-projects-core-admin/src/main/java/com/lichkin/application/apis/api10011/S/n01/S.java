package com.lichkin.application.apis.api10011.S.n01;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusService;
import com.lichkin.springframework.services.LKApiVoidService;

@Service("SysDeptS01Service")
public class S extends LKApiBusService<I, Void, SysDeptEntity> implements LKApiVoidService<I> {

	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		boolean up = sin.isUp();
		String deptCode = sin.getDeptCode();

		String targetRealCode = null;
		List<SysDeptEntity> targetSubList = null;
		if (up) {// 向上
			String prevCode = LKCodeUtils.prevCode(deptCode);
			if (prevCode == null) {// 第一个菜单
				return;
			}
			targetRealCode = LKCodeUtils.realCode(prevCode);
			targetSubList = getSubList(targetRealCode);
		} else {// 向下
			targetRealCode = LKCodeUtils.realCode(LKCodeUtils.nextCode(deptCode));
			targetSubList = getSubList(targetRealCode);
			if (CollectionUtils.isEmpty(targetSubList)) {// 最后一个菜单
				return;
			}
		}

		String sourceRealCode = LKCodeUtils.realCode(deptCode);
		List<SysDeptEntity> sourceSubList = getSubList(sourceRealCode);

		changeSubList(sourceSubList, sourceRealCode, targetRealCode);
		changeSubList(targetSubList, targetRealCode, sourceRealCode);

		dao.mergeList(sourceSubList);
		dao.mergeList(targetSubList);
	}


	private List<SysDeptEntity> getSubList(String deptCode) {
		QuerySQL sql = new QuerySQL(SysDeptEntity.class);
		sql.like(SysDeptR.deptCode, LikeType.RIGHT, deptCode);
		return dao.getList(sql, SysDeptEntity.class);
	}


	private void changeSubList(List<SysDeptEntity> subList, String deptRealCode, String newDeptRealCode) {
		for (SysDeptEntity dept : subList) {
			dept.setParentCode(dept.getParentCode().replace(deptRealCode, newDeptRealCode));
			dept.setDeptCode(dept.getDeptCode().replace(deptRealCode, newDeptRealCode));
		}
	}

}