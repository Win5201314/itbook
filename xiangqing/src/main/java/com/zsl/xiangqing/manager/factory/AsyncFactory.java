package com.zsl.xiangqing.manager.factory;

import com.zsl.xiangqing.constant.Constants;
import com.zsl.xiangqing.entity.LoginInformation;
import com.zsl.xiangqing.service.LoginInformationService;
import com.zsl.xiangqing.shiro.ShiroUtils;
import com.zsl.xiangqing.util.AddressUtils;
import com.zsl.xiangqing.util.ServletUtils;
import com.zsl.xiangqing.util.spring.SpringUtils;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
public class AsyncFactory {

    /**
     * 同步session到数据库
     * 
     * @param session 在线用户会话
     * @return 任务task
     */
    /*public static TimerTask syncSessionToDb(final OnlineSession session) {
        return new TimerTask() {
            @Override
            public void run() {
                SysUserOnline online = new SysUserOnline();
                online.setSessionId(String.valueOf(session.getId()));
                online.setDeptName(session.getDeptName());
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus());
                SpringUtils.getBean(ISysUserOnlineService.class).saveOnline(online);

            }
        };
    }*/

    /**
     * 记录登陆信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLoginInformation(final String username, final String status, final String message, final Object... args) {
        //服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        //获取请求ip
        final String ip = ShiroUtils.getIp();
        return new TimerTask() {
            @Override
            public void run() {
                //获取ip对应的真实地址
                String address = AddressUtils.getRealAddressByIP(ip);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                LoginInformation logininfor = new LoginInformation();
                logininfor.setLoginName(username);
                logininfor.setIpAddress(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(Constants.FAIL);
                }
                //时间由数据库语句自动添加，不用在这里添加
                // 插入数据
                SpringUtils.getBean(LoginInformationService.class).insertLoginInformation(logininfor);
            }
        };
    }
}
