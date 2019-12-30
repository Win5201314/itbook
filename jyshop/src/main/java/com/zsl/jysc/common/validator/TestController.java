package com.zsl.jysc.common.validator;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class TestController {

    @Autowired
    private ValidatorImpl validator;

    /**
     * 企业应用一般包含三类对象
     * 数据库面对的对象dataObject <- Model（领域模型） -> ViewObject（给前端看的对象）
     * BeanUtils.copyProperties();
     */

    @GetMapping("/testValidator")
    public Object test(String phone, Integer age) {
        Model model = new Model();
        model.setAge(age);
        model.setPhone(phone);
        ValidationResult validationResult = validator.validate(model);
        if (validationResult.isHasErrors()) {
            //领域模型校验报错，参数异常
            return validationResult.getErrorMsg();
        }
        //BeanUtils.copyProperties();
        return "----";
    }
}
