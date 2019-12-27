package com.zsl.jysc.common.error;

/**
 * 包装器业务异常类实现  全局都可以用这个业务异常类
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    //直接接收EmBusinessError的参数用于构造业务异常
    public BusinessException(CommonError commonError) {
        //这个一定要加，exception里面也要初始化
        super();
        this.commonError = commonError;
    }

    //接收自定义errorMsg的方式构造业务异常
    public BusinessException(CommonError commonError, String errorMsg) {
        //这个一定要加，exception里面也要初始化
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errorMsg);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }
}
