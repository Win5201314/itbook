package com.zsl.boss.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseMapper {

    int updateHeadimgurl(@Param("id") int id, @Param("headimgurl") String headimgurl, @Param("type") int type);
}
