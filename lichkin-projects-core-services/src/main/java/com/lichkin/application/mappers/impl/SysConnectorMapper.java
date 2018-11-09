package com.lichkin.application.mappers.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysConnectorMapper {

	List<String> selectAllIds();

}
