package com.zsl.jysc.aspect;

import com.zsl.jysc.exception.VerifyTokenException;
import com.zsl.jysc.service.IUserService;
import com.zsl.jysc.util.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class TokenAspect {

    @Autowired
    private IUserService userService;

    @Pointcut("@annotation(com.zsl.jysc.common.annotation.VerifyToken)")
    public void VerifyToken() {}

    @Around("VerifyToken()")
    public void check(ProceedingJoinPoint joinPoint) throws VerifyTokenException {
        //获取请求里面包含的token
        String token = methodBefore(joinPoint);
        if (token == null) throw new VerifyTokenException("令牌验证失败");
        Map<String, Object> map = JWTUtil.verifyToken(token);
        if (map == null) throw new VerifyTokenException("令牌验证失败");
        Long userId = (Long) map.get("userId");
        String openid = (String) map.get("openid");
        if (!userService.isExistOpenidAndUserId(openid, userId)) throw new VerifyTokenException("令牌验证失败");
    }

    /**
     * 获取包含注解的方法里面token参数
     */
    private String methodBefore(JoinPoint joinPoint) {
        try {
            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] objs = joinPoint.getArgs();//参数值
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
            for (int i = 0; i < argNames.length; i++) {
                if ("token".equals(argNames[i])) {
                    return objs[i].toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
