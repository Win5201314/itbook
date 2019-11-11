package com.inxedu.os.common.sysLog;


import java.lang.annotation.*;

/**
 * 类的方法描述注解
 * @author LuoYu
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String type() default "";

    String operation() default "";
}
