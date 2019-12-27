package com.zsl.jysc.common.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 通用返回类型
 */
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候,如果是null的对象,key也会消失
public class CommonResult<T> {

    /**
     * 返回状态 成功 = success, 失败 = fail(包括很多失败类型，放在data里面)
     */
    private String status;

    /**
     * 返回具体内容
     */
    private T data;

    public CommonResult(String status) {
        this.status = status;
    }

    public CommonResult(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public static CommonResult createBySuccess() {
        return new CommonResult("success");
    }

    public static <T> CommonResult<T> createBySuccess(T data) {
        return new CommonResult("success", data);
    }


    public static <T> CommonResult<T> createByError(T data) {
        return new CommonResult("fail", data);
    }

}
