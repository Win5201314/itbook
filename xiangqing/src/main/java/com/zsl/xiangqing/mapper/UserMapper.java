package com.zsl.xiangqing.mapper;

import com.zsl.xiangqing.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

@Mapper
public interface UserMapper {

    Users selectUserByLoginName(String username);

    //@Cacheable(value = "db0", key = "#username")
    Users selectUserByPhoneNumber(@Param(value = "username") String username);

    Users selectUserByEmail(String username);

    Users selectUserByOpenid(String openid);

    int updateUserInfo(Users user);

    int checkOpenid(String openid);

    int checkPhoneNumber(@Param(value = "username") String username);

    int addNewUsers(@Param(value = "users") Users users);
}
