package com.zsl.boss.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 参考博文 注解 https://segmentfault.com/a/1190000017163807?utm_source=tag-newest
 */
@ApiModel(description = "包装反应体")
public class JsonResult {

    @ApiModelProperty(name = "status", value = "状态 成功 = ok , 失败 = fail")
    private String status = null;

    @ApiModelProperty(name = "result", value = "包装返回值")
    private Object result = null;

    public JsonResult() {
    }

    public JsonResult(String status, Object result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
