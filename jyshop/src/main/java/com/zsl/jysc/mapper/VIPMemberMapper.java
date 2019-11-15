package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.VIPMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VIPMemberMapper {

    boolean addNewVIPMember(VIPMember member);
}
