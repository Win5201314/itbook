package com.inxedu.os.cloud.dao.impl.sign;

import com.inxedu.os.cloud.dao.sign.SignDao;
import com.inxedu.os.cloud.entity.sign.Sign;
import com.inxedu.os.common.dao.GenericDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SignDao")
public class SignDaoImpl extends GenericDaoImpl implements SignDao {
    /**
     * 添加签到信息
     */
    @Override
    public Long addSign(Sign sign){
        return this.insert("SignMapper.createSign", sign);
    }

    /**
     * 查询课程/直播的签到信息
     */
    @Override
    public List<Sign> querySignById(String courseId){
        return this.selectList("SignMapper.querySignById", courseId);
    }
}
