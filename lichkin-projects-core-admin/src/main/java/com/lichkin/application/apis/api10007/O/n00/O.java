package com.lichkin.application.apis.api10007.O.n00;

import com.lichkin.framework.defines.annotations.IgnoreLog;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String userName;

	private LKGenderEnum gender;

	private String email;

	@IgnoreLog
	private String photo;

	/** 角色IDs */
	private String roleIds;

	/** 角色名称s */
	private String roleNames;

}
