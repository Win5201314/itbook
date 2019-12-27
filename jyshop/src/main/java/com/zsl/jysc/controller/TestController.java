package com.zsl.jysc.controller;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.common.error.BusinessException;
import com.zsl.jysc.common.error.CommonError;
import com.zsl.jysc.common.error.EmBusinessError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 前后端分离 Session and Cookies
 */
@RestController
public class TestController {

    /**拦截器 或者 过滤器那种解决办法 不如 注解@CrossOrigin简便
     * 用户在登录的时候，服务器会返回一个SessionId的数据给前端，放在Cookie里面
     * 服务器会给当前用户数据存放在Session里面，并且绑定SessionId和Session数据，存放在Redis里面
     * 1.Redis + Session 存储问题
     * 2.CORS跨域问题 参考：https://www.jianshu.com/p/f4a9266ff484
     * //@CrossOrigin(origins = "http://www.a.com:8888", allowCredentials = "true")
     *浏览器同源策略 域名 端口 协议
     */
    @CrossOrigin
    @PostMapping(value = "/test")
    public ServerResponse<String> test(HttpServletRequest request, HttpServletResponse response) {

        /*
        // 在session中存放用户信息  (token 结合Session 模式最好，我认为)
        Users user = userService.selectUserByLoginName(username);
        subject.getSession().setAttribute("user", user);
        //登录成功则返回sessionId作为token给前端存储，前端请求时将该token放入请求头，以Authorization为key，以此来鉴权
        String token = subject.getSession().getId().toString();
        Cookie cookie1 = new Cookie("token", token);
        cookie1.setHttpOnly(true);
        response.addCookie(cookie1);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //就是上面的token
        String authorization = request.getHeader("Authorization");
        //解析token，从Redis中获取对应的Session数据，就可以找到个人信息了
        User userInfo = userService.getUserInfo(authorization);
        */
        //cookie.setPath("/");
        //我们设置Cookie的path为根目录"/"，以便在该域的所有路径下都能看到这个Cookie。
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            String name = cookie.getName();
            if (name.equals("sessionId")) {
                //从Redis中取出对应的Session数据

            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("key", "value");

        Cookie cookie = new Cookie("key", "value");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        Cookie cookie1 = new Cookie("sessionId", "后台生成的sessionId");
        cookie1.setHttpOnly(true);
        response.addCookie(cookie1);
        return null;
    }

    @PostMapping(value = "/test2")
    public ServerResponse<String> test(String email) throws BusinessException {
        if ("".equals(email))
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "邮箱参数格式异常");
        return null;
    }
}
