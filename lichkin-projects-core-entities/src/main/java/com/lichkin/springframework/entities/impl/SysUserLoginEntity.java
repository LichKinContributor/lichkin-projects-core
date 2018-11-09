package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.DefaultByteValue;
import com.lichkin.framework.defines.annotations.DefaultStringValue;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.framework.defines.entities.I_User;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.springframework.entities.suppers.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(

		afterSaveMain = false, IUSubTables = {}

		, insertCheckType = InsertCheckType.UNCHECK

		, insertFields = {}

		, updateCheckType = UpdateCheckType.UNCHECK

		, updateFields = {}

		, pageQueryConditions = {

				"String startDate 开始日期 #entityR", //
				"String endDate 结束日期 #entityR", //

		}

)
public class SysUserLoginEntity extends BaseEntity implements I_User, I_Login {

	/** serialVersionUID */
	private static final long serialVersionUID = 10020L;

	/** 姓名 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 64, nullable = false)
	private String userName;

	/** 性别（枚举） */
	@Enumerated(EnumType.STRING)
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 7, nullable = false)
	private LKGenderEnum gender;

	/** 登录名 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 64, nullable = false)
	private String loginName;

	/** 手机号码 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 11, nullable = false)
	private String cellphone;

	/** 邮箱 */
	@DefaultStringValue
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 128, nullable = false)
	private String email;

	/** 身份证号（自定义加密） */
	@DefaultStringValue
	@FieldGenerator(resultColumn = true)
	@Column(length = 64, nullable = false)
	private String userCard;

	/** 密码（两次MD5） */
	@DefaultStringValue
	@FieldGenerator(resultColumn = true)
	@Column(length = 32, nullable = false)
	private String pwd;

	/** 验证码 */
	@DefaultStringValue
	@Column(length = 6, nullable = false)
	private String securityCode;

	/** 密码错误次数 */
	@DefaultByteValue
	@Column(nullable = false)
	private Byte errorTimes;

	/** 账号等级 */
	@DefaultByteValue(1)
	@FieldGenerator(resultColumn = true)
	@Column(nullable = false)
	private Byte level;

	/** 登录令牌 */
	@DefaultStringValue(DefaultStringValue.TOKEN)
	@FieldGenerator(resultColumn = true)
	@Column(length = 64, nullable = false)
	private String token;

	/** 锁定时间（yyyyMMddHHmmssSSS） */
	@DefaultStringValue(DefaultStringValue.GMT_START)
	@Column(length = 17, nullable = false)
	private String lockTime;

	/** 照片（Base64） */
	@Lob
	@Column
	private String photo;

}
