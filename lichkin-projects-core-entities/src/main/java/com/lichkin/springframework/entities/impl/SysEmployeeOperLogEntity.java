package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.entities.I_CompId;
import com.lichkin.springframework.entities.suppers.OperLogEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工操作日志表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysEmployeeOperLogEntity extends OperLogEntity implements I_CompId {

	/** serialVersionUID */
	private static final long serialVersionUID = 10016L;

	/** 公司ID（SysCompEntity.id） */
	@Column(length = 64, nullable = false)
	private String compId;

}
