package com.itbook.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class KeyGenerator implements org.springframework.cache.interceptor.KeyGenerator {

    /**
     * 上面的自定义的key规则是类
     * 本类名+方法名+参数名(中间没有逗号区分)
     */
    @Override
    public Object generate(Object o, Method method, Object... params) {
        //规定  本类名+方法名+参数名 为key
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append("-");
        sb.append(method.getName());
        sb.append("-");
        for (Object param : params) {
            sb.append(param.toString());
        }
        return sb.toString();
    }
}
