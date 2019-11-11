package com.inxedu.os.edu.controller.account;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.account.UserOptType;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccountDTO;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.account.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserAccount管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
@RequestMapping("/admin")
public class AdminUserAccountController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserAccountController.class);

    @Autowired
    private UserAccountService userAccountService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinderUserAccounthistory(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 获取用户账户信息
     */
    @RequestMapping("/account/list")
    public ModelAndView accountList(HttpServletRequest request,
                                    @ModelAttribute("page") PageEntity page,
                                    @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/account/account_list"));
        try {
            // 设置分页 ，默认每页10
            List<UserAccountDTO> userAccountList = userAccountService.getUserAccountListByCondition(page, user);
            modelAndView.addObject("userAccountList", userAccountList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            setExceptionRequest(request, e);
        }
        return modelAndView;
    }

    /**
     * 账户详情
     */
    @RequestMapping("/account/info/{userId}/{flag}")
    public String getUserAccountByUserId(HttpServletRequest request, Model model,
                                         @PathVariable("userId") Long userId,
                                         @PathVariable("flag") String flag) {
        try {
            // 获得用户账户详情
            UserAccountDTO userAccountDTO = userAccountService.getuserAccountInfo(userId);
            model.addAttribute("userAccountDTO", userAccountDTO);
            model.addAttribute("flag", flag);
        } catch (Exception e) {
            logger.error("getUserAccountByUserId", e);
            return setExceptionRequest(request, e);
        }
        return getViewPath("/admin/account/account_recharge");
    }






    /**
     * 更新账户状态
     *
     * @param request
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping("/account/update/{userId}")
    @ResponseBody
    public Map<String, Object> updateAccountStatus(HttpServletRequest request,
                                                   @PathVariable("userId") Long userId,
                                                   @RequestParam("status") String status) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(status)) {
                userAccountService.updateUserAccountStatus(userId, status);
                json = this.setJson(true, "修改成功", null);

                // 查询账户
                UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
                // 记录系统用户操作
                Map<String, Object> descMap = new HashMap<String, Object>();
                descMap.put("optuser", "操作id_" + SingletonLoginUtils.getLoginUserId(request));
                descMap.put("optType", status.indexOf(UserOptType.FROZEN.toString()) != -1 ? "冻结" : "解冻");
                descMap.put("accountId", "账户id_" + userAccount != null ? userAccount.getId() : 0);
                descMap.put("userId", "用户id_" + userId);
                //userService.addUserOptRecord(userId, UserOptType.GIVECOURSE.toString(), SingletonLoginUtils.getSysUserId(request), this.getSysLoginLoginName(request), userAccount != null ? userAccount.getId() : 0L, gson.toJson(descMap));
            } else {
                json = this.setJson(false, "请求数据错误", null);
            }
        } catch (Exception e) {
            logger.error("updateAccountStatus", e);
            json = this.setJson(false, "系统错误", null);
        }
        return json;
    }

    /**
     * 后台账户充值,扣费
     *
     * @param request
     * @return
     */
    @RequestMapping("/account/recharge")
    @ResponseBody
    public Map<String, Object> gainUserRechargeAmount(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String userId = request.getParameter("userId");// 获得用户id
            String flag = request.getParameter("flag");// 获得用户id
            String balance = request.getParameter("balance");// 获得用户id
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(flag) || StringUtils.isEmpty(balance)) {
                json = this.setJson(true, "请求数据错误", null);
                return json;
            }
            // 后台账户充值,扣费
            SysUser sysUser = SingletonLoginUtils.getLoginSysUser(request);
            boolean result = userAccountService.gainUserRechargeAmount(sysUser, Long.valueOf(userId), new BigDecimal(balance), flag);
            if (result) {
                json = this.setJson(true, "操作成功", null);
            } else {
                json = this.setJson(false, "操作失败", null);
            }
        } catch (Exception e) {
            logger.error("gainUserRechargeAmount--recharge is failure", e);
            json = this.setJson(false, "recharge is failure", null);
        }
        return json;
    }
}