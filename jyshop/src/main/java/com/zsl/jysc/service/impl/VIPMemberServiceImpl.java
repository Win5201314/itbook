package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.VIPMember;
import com.zsl.jysc.mapper.VIPMemberMapper;
import com.zsl.jysc.service.IVIPMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VIPMemberServiceImpl implements IVIPMemberService {

    @Autowired
    private VIPMemberMapper vipMemberMapper;

    @Override
    public boolean addNewVIPMember(VIPMember member) {
        return vipMemberMapper.addNewVIPMember(member);
    }
}
