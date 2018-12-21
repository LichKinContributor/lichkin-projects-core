package com.lichkin.application.apis.api000;

import java.util.List;

import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.entities.impl.SysRoleEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SO {

	private SysCompEntity comp;

	private SysAdminLoginEntity adminLogin;

	private List<SysRoleEntity> listRole;

	private List<SysMenuEntity> listMenu;

}