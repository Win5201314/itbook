package com.zsl.xiangqing.mapper;

import com.zsl.xiangqing.entity.Users;

import java.util.List;

public interface SearchMapper {

    List<Users> selectUsersByHeight(Float height);
}
