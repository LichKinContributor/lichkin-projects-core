package com.lichkin.application.apis.ROOT.GetDeptTree;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.beans.impl.LKTreeBean;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKTreeUtils;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiYYController;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiService;

@RestController(Statics.CONTROLLER_NAME)
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + Statics.SUB_URL)
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiYYController<I, List<LKTreeBean>, List<SysDeptEntity>> {

	@Autowired
	private S service;


	@Override
	protected LKApiService<I, List<SysDeptEntity>> getService(I cin, ApiKeyValues<I> params) {
		return service;
	}


	@Override
	protected List<LKTreeBean> afterInvokeService(I cin, ApiKeyValues<I> params, List<SysDeptEntity> sout) throws LKException {
		return LKTreeUtils.toTree(sout, "id", "deptName", "deptCode", "parentCode");
	}

}
