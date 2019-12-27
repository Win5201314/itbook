package com.zsl.jysc.common.error;

public interface CommonError {

    int getErrorCode();

    CommonError setErrorMsg(String errorMsg);

    String getErrorMsg();

}
