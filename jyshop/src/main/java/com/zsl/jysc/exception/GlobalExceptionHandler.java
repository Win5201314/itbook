package com.zsl.jysc.exception;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.common.error.BusinessException;
import com.zsl.jysc.common.error.CommonResult;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * 只能捕捉controller层的异常，其他层的报错记得往上层抛出即可
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public CommonResult handleBusinessException(BusinessException e) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("errorCode", e.getErrorCode());
        responseData.put("errorMsg", e.getErrorMsg());
        return CommonResult.createByError(responseData);
    }

    @ResponseBody
    @ExceptionHandler(value = VerifyTokenException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse<String> handleVerifyTokenException(VerifyTokenException e) {
        return ServerResponse.createByErrorMessage(e.getMessage());
    }

    //拦截未授权页面
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(UnauthorizedException.class)
    public ServerResponse<String> handleException(UnauthorizedException e) {
        logger.debug(e.getMessage());
        return ServerResponse.createByErrorMessage("未授权页面");
    }

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse<String> errorHandler(Exception ex) {
        return ServerResponse.createByErrorMessage(ex.getMessage());
    }

    /**
     * 处理登录认证报的错
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {AuthenticationException.class, UnknownAccountException.class,
            IncorrectCredentialsException.class, ExcessiveAttemptsException.class,
            LockedAccountException.class, LockedAccountException.class})
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse<String> authenticationExceptionHandler(Exception ex) {
        return ServerResponse.createByErrorMessage(ex.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse<String> handleException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return ServerResponse.createByErrorMessage("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse<String> notFount(RuntimeException e) {
        logger.error("运行时异常:", e);
        return ServerResponse.createByErrorMessage("运行时异常:" + e.getMessage());
    }

    /**
     * shiro 登录进去之后的session过期异常
     * 再次进行登录
     */
    @ExceptionHandler(ExpiredSessionException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse<String> ExpiredSessionException(ExpiredSessionException e) {
        logger.error("session过期异常", e);
        return ServerResponse.createByErrorMessage("会话过期了，请重新登录");
    }
}
