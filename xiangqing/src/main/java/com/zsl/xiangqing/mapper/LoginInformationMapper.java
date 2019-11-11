package com.zsl.xiangqing.mapper;

import com.zsl.xiangqing.entity.LoginInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginInformationMapper {

    void insertLoginInformation(LoginInformation loginInformation);
}
