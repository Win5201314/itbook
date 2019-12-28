package com.googleguicetest.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ValidatorImpl validator;

    @GetMapping("/test")
    public Object test(String phone, Integer age) {
        Model model = new Model();
        model.setAge(age);
        model.setPhone(phone);
        ValidationResult validationResult = validator.validate(model);
        if (validationResult.isHasErrors()) {
            //领域模型校验报错，参数异常
            return validationResult.getErrorMsg();
        }
        return "----";
    }
}
