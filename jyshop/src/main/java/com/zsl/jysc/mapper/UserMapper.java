package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int isExistOpenid(@Param(value = "openid") String openid);
    boolean addNewUsers(Users users);
    int updateUsernameAndAvatar(@Param(value = "openid") String openid, @Param(value = "username") String username, @Param(value = "avatar") String avatar);
    long selectIdByOpenid(@Param(value = "openid") String openid);
}
