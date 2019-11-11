package com.inxedu.os.common.sysLog;

import com.google.gson.GsonBuilder;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.system.SysLog;
import com.inxedu.os.edu.service.system.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzl16 on 2016/7/26.
 */
//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class SystemLogAspect {
    //注入Service用于把日志保存数据库
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    @Resource
    private SysLogService sysLogService;
    @Pointcut("@annotation(com.inxedu.os.common.sysLog.SystemLog)")
    public void controllerAspect() {
    }
    //配置controller环绕通知,使用在方法aspect()上注册的切入点
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable  {
        //调用核心逻辑
        Object retVal = joinPoint.proceed();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取操作内容
        String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
        SysLog sysLog = new SysLog();
        sysLog.setAdminUserId(SingletonLoginUtils.getLoginSysUserId(request));//相关操作id
        sysLog.setType(querySystemLogType(joinPoint));//操作类型
        sysLog.setContent(opContent);//相关对象的json数据
        sysLog.setOperation(querySystemLogOperation(joinPoint));//操作描述
        sysLogService.createSysLog(sysLog);
        logger.info("记录后台操作日志："+methodName+"   描述："+sysLog.getOperation());
        return retVal;
    }
    /**
     * 获取注解中对方法的描述信息  Type属性
     */
    public  static String querySystemLogType(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String type = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    type = method.getAnnotation(SystemLog.class).type();
                    break;
                }
            }
        }
        return type;
    }
    /**
     * 获取注解中对方法的描述信息   operation属性
     */
    public  static String querySystemLogOperation(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String operation = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    operation = method.getAnnotation(SystemLog.class).operation();
                    break;
                }
            }
        }
        return operation;
    }
    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值，
     * 将参数值拼接为操作内容
     */
    public String adminOptionContent(Object[] args, String mName) throws Exception{
        if (args == null) {
            return null;
        }

        Map map = new HashMap<>();
        map.put("方法名",mName);
        // 遍历参数对象放入map中转成json字符串（从第二个参数开始转：第一个不是我们想要的参数）
        for (int i=0;i< args.length;i++) {
            Object info = args[i];
            //获取对象类型
            String className = info.getClass().getName();
            //去除该类型对象，（转json报错）
            if("com.inxedu.os.common.session.HttpServletRequestWrapper".equals(className)
                    ||"com.opensymphony.sitemesh.webapp.ContentBufferingResponse".equals(className)
                    ||"net.bull.javamelody.JspWrapper$HttpRequestWrapper3".equals(className)
                    ||"org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest".equals(className)
                    ||"org.springframework.web.multipart.commons.CommonsMultipartFile".equals(className)
                    ){
                continue;
            }
            System.out.println(className);
            className = className.substring(className.lastIndexOf(".") + 1);
            map.put(className,info);
        }
        return FastJsonUtil.obj2JsonString(map);
    }
}
