package com.itbook.mapper;

import com.itbook.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where loginName = #{loginName} and password = #{password}")
    User findWithLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);

    @Insert("insert into user (loginName, password) values (#{loginName}, #{password})")
    void insertNewUser(@Param("loginName") String loginName, @Param("password") String password);

}
