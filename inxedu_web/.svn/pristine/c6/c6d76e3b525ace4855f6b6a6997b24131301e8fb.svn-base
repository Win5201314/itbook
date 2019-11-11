package com.inxedu.os.cloud.service.impl;

import com.inxedu.os.cloud.dao.sign.SignDao;
import com.inxedu.os.cloud.entity.sign.Sign;
import com.inxedu.os.cloud.service.sign.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SignService")
public class SignServiceImpl  implements SignService {
    @Autowired
    private SignDao signDao;

    @Override
    public Long addSign(Sign sign){
        return signDao.addSign(sign);
    }

    @Override
    public List<Sign> querySignById(String courseId){
        return signDao.querySignById(courseId);
    }
}
