package com.lichkin.application;

import org.junit.Test;

import com.lichkin.springframework.generator.LKApiGenerator;
import com.lichkin.springframework.generator.LKApiGenerator.Type;

public class LKApiGeneratorRunner {

	String projectDir = Thread.currentThread().getContextClassLoader().getResource(".").getPath().replace("/target/test-classes/", "");

	String apiType = "WEB";

	String userType = "ADMIN";

	int index = 0;

	int errorCode = 20000;


	@Test
	public void generateInsert() {
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCompEntity", index, errorCode, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCategoryEntity", index, errorCode, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDictionaryEntity", index, errorCode, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysMenuEntity", index, errorCode, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysRoleEntity", index, errorCode, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysAdminLoginEntity", index, errorCode, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDeptEntity", index, errorCode, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysEmployeeEntity", index, errorCode, Type.Insert, "新增数据接口");
	}


	@Test
	public void generateUpdate() {
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCompEntity", index, errorCode, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCategoryEntity", index, errorCode, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDictionaryEntity", index, errorCode, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysMenuEntity", index, errorCode, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysRoleEntity", index, errorCode, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysAdminLoginEntity", index, errorCode, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDeptEntity", index, errorCode, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysEmployeeEntity", index, errorCode, Type.Update, "编辑数据接口");
	}


	@Test
	public void generatePage() {
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCompEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysRoleEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysAdminLoginEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysAdminLoginLogEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysAdminOperLogEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysEmployeeEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysEmployeeOperLogEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysUserLoginEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysUserOperLogEntity", index, errorCode, Type.GetPage, "获取分页数据接口");
	}


	@Test
	public void generateList() {
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCategoryEntity", index, errorCode, Type.GetList, "获取列表数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDictionaryEntity", index, errorCode, Type.GetList, "获取列表数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysEmployeeEntity", index, errorCode, Type.GetList, "获取列表数据接口");
	}


	@Test
	public void generateOne() {
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCompEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCategoryEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDictionaryEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysMenuEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysRoleEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysAdminLoginEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDeptEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysEmployeeEntity", index, errorCode, Type.GetOne, "获取单个数据接口");
	}


	@Test
	public void generateUS() {
		LKApiGenerator.generate(userType, apiType, projectDir, "SysCompEntity", index, errorCode, Type.UpdateUsingStatus, "修改状态接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDictionaryEntity", index, errorCode, Type.UpdateUsingStatus, "修改状态接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysRoleEntity", index, errorCode, Type.UpdateUsingStatus, "修改状态接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysAdminLoginEntity", index, errorCode, Type.UpdateUsingStatus, "修改状态接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysDeptEntity", index, errorCode, Type.UpdateUsingStatus, "修改状态接口");
		LKApiGenerator.generate(userType, apiType, projectDir, "SysEmployeeEntity", index, errorCode, Type.UpdateUsingStatus, "修改状态接口");
	}

}
