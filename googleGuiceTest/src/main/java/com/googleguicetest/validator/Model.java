package com.googleguicetest.validator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Model {

    @NotNull(message = "年龄不能不填写")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄不能大于150")
    private Integer age;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
