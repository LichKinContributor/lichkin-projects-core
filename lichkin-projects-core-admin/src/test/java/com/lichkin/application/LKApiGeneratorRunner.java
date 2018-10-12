package com.lichkin.application;

import org.junit.Before;
import org.junit.Test;

import com.lichkin.springframework.generator.LKApiGenerator;
import com.lichkin.springframework.generator.LKApiGenerator.Type;

public class LKApiGeneratorRunner {

	String entity;

	int index;

	String projectDir = Thread.currentThread().getContextClassLoader().getResource(".").getPath().replace("/target/test-classes/", "");


	@Before
	public void Before() {
		entity = "SysComp";
		index = 1;
	}


	@Test
	public void generateAll() {
		LKApiGenerator.generate(projectDir, entity);
	}


	@Test
	public void generateOne() {
		LKApiGenerator.generate(projectDir, entity, index, Type.GetPage, "获取分页数据接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.GetList, "获取列表数据接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.GetOne, "获取单个数据接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.GetDroplist, "获取下拉列表数据接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.Insert, "新增数据接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.Update, "编辑数据接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.UpdateUsingStatus, "修改状态接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.Delete, "删除数据接口");
		LKApiGenerator.generate(projectDir, entity, index, Type.Special, "特殊业务接口");
	}

}
