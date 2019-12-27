package com.zsl.jysc.common.error;

/**
 * 大型分布式系统中都会定义一堆错误码和对应的错误信息，分段
 *
 */
public enum EmBusinessError implements CommonError {
    //通用的错误类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    //20001开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    ;

    private int errorCode;
    private String errorMsg;

    private EmBusinessError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        return this.setErrorMsg(errorMsg);
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
