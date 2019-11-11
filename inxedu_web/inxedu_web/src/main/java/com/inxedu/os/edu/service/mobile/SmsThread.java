package com.inxedu.os.edu.service.mobile;


import java.util.concurrent.Callable;

/**
 * 
 * @ClassName  com.inxedu.os.edu.service.user.SmsThread
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月22日 下午5:32:52
 */
public class SmsThread implements Callable{
    private final SmsUtil smsUtil;

    public SmsThread(SmsUtil smsUtil) {
        this.smsUtil = smsUtil;
    }

    @Override
    public Object call() throws Exception {
        String returnMes = smsUtil.sendmsg();
        return returnMes;
    }
}
