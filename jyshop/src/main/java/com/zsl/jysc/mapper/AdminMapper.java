package com.zsl.jysc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    int isExitsAdmin(@Param(value = "username") String username, @Param(value = "password") String password);
}
