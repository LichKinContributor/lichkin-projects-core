package com.lichkin.application.apis.api10004.S.n03;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.services.LKApiBusService;
import com.lichkin.springframework.services.LKApiVoidService;

@Service("SysMenuS03Service")
public class S extends LKApiBusService<I, Void, SysMenuEntity> implements LKApiVoidService<I> {

	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		boolean up = sin.isUp();
		String menuCode = sin.getMenuCode();

		String targetRealCode = null;
		List<SysMenuEntity> targetSubList = null;
		if (up) {// 向上
			String prevCode = LKCodeUtils.prevCode(menuCode);
			if (prevCode == null) {// 第一个菜单
				return;
			}
			targetRealCode = LKCodeUtils.realCode(prevCode);
			targetSubList = getSubList(targetRealCode);
		} else {// 向下
			targetRealCode = LKCodeUtils.realCode(LKCodeUtils.nextCode(menuCode));
			targetSubList = getSubList(targetRealCode);
			if (CollectionUtils.isEmpty(targetSubList)) {// 最后一个菜单
				return;
			}
		}

		String sourceRealCode = LKCodeUtils.realCode(menuCode);
		List<SysMenuEntity> sourceSubList = getSubList(sourceRealCode);

		changeSubList(sourceSubList, sourceRealCode, targetRealCode);
		changeSubList(targetSubList, targetRealCode, sourceRealCode);

		dao.mergeList(sourceSubList);
		dao.mergeList(targetSubList);
	}


	private List<SysMenuEntity> getSubList(String menuCode) {
		QuerySQL sql = new QuerySQL(SysMenuEntity.class);
		sql.like(SysMenuR.menuCode, LikeType.RIGHT, menuCode);
		return dao.getList(sql, SysMenuEntity.class);
	}


	private void changeSubList(List<SysMenuEntity> subList, String menuRealCode, String newMenuRealCode) {
		for (SysMenuEntity menu : subList) {
			menu.setParentCode(menu.getParentCode().replace(menuRealCode, newMenuRealCode));
			menu.setMenuCode(menu.getMenuCode().replace(menuRealCode, newMenuRealCode));
		}
	}

}
