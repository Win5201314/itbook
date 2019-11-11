package com.inxedu.os.cloud.service.sign;

import com.inxedu.os.cloud.entity.sign.Sign;

import java.util.List;

public interface SignService {

    /**
     * 添加签到信息
     */
    Long addSign(Sign sign);

    /**
     * 查询课程/直播的签到信息
     */
    List<Sign> querySignById(String courseId);

}
