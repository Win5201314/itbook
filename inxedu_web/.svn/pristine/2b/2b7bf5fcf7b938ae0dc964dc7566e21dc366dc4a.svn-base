package com.inxedu.os.edu.controller.account;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.entity.account.QueryUserAccounthistory;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccounthistory;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.account.UserAccounthistoryService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * UserAccounthistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
@RequestMapping("/admin")
public class AdminUserAccounthistoryController extends BaseController {

    @Autowired
    private UserAccounthistoryService userAccounthistoryService;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private UserAccountService userAccountService;
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryUserAccounthistory")
    public void initBinderUserAccounthistory(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUserAccounthistory.");
    }

    /**
     * 获取用户历史信息
     *
     * @param request
     * @param queryUserAccounthistory
     * @param page
     * @return
     */
    @RequestMapping("/account/detailed_list")
    public ModelAndView getAccountHistory(HttpServletRequest request,
                                          @ModelAttribute("queryUserAccounthistory") QueryUserAccounthistory queryUserAccounthistory,
                                          @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/account/account_history_list"));
        try {
            List<UserAccounthistory> userHisoty = userAccounthistoryService.getUserAccountHistroyListByCondition(queryUserAccounthistory, page);
            modelAndView.addObject("userHisoty", userHisoty);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            setExceptionRequest(request, e);
        }
        return modelAndView;
    }
    /**
     * 获取用户历史信息
     *
     * @param request
     * @param queryUserAccounthistory
     * @param page
     * @return
     */
    @RequestMapping("/account/drawMoney_list")
    public ModelAndView getAccountWithdraw(HttpServletRequest request,
                                          @ModelAttribute("queryUserAccounthistory") QueryUserAccounthistory queryUserAccounthistory,
                                          @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/account/account_drawmoney_list"));
        try {
            queryUserAccounthistory.setActHistoryType("DRAWMONEY");
            List<UserAccounthistory> userHisoty = userAccounthistoryService.getUserAccountHistroyListByCondition(queryUserAccounthistory, page);
            modelAndView.addObject("userHisoty", userHisoty);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            setExceptionRequest(request, e);
        }
        return modelAndView;
    }
    /**
     * 提现审批
     */
    @RequestMapping("/account/examineDrawMoney")
    @ResponseBody
    public Map<String,Object> examineDrawMoney(HttpServletRequest request) {
        Map<String,Object> json = new HashedMap();
        try {
            /*提现记录id*/
            String accountHistoryId = request.getParameter("accountHistoryId");
            /*审批状态*/
            String status = request.getParameter("status");
            /*审批失败描述*/
            String description = request.getParameter("description");
            /*取出该条提现记录*/
            UserAccounthistory userAccounthistory = userAccounthistoryService.getUserAccounthistoryById(Long.parseLong(accountHistoryId));
            /*更新状态*/
            userAccounthistory.setStatus(status);
            /*更新描述*/
            userAccounthistory.setDescription(description);
            /*操作时间*/
            userAccounthistory.setUpdateTime(new Date());
            /*创建后台操作人*/
            userAccounthistory.setCreateUser(SingletonLoginUtils.getLoginSysUserId(request)+"");


            /*用户冻结的金额减掉提现金额*/
            UserAccount userAccount = userAccountService.getUserAccountByUserId(userAccounthistory.getUserId());
            userAccount.setForzenAmount(userAccount.getForzenAmount().subtract(userAccounthistory.getTrxAmount()));
            /*如果提现失败*/
            if("FAIL".equals(status)){
                /*账户现金余额和总余额加上提现金额*/
                userAccount.setCashAmount(userAccount.getCashAmount().add(userAccounthistory.getTrxAmount()));
                userAccount.setBalance(userAccount.getBalance().add(userAccounthistory.getTrxAmount()));
            }
           /* 账户最后更新时间*/
            userAccount.setLastUpdateTime(new Date());
            userAccountService.updateUserAccount(userAccount);
            userAccounthistoryService.updateUserAccountHistroy(userAccounthistory);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            /*发送消息*/
            msgReceiveService.sendMessage(userAccount.getUserId().intValue(), "提现处理", MsgType.drawMoney.toString(), true, simpleDateFormat.format(new Date()));
            json = this.setJson(true,"",null);
        } catch (Exception e) {
            setExceptionRequest(request, e);
        }
        return json;
    }

    /**
     * 获取用户历史信息
     */
    @RequestMapping("/account/drawMoneyInfo/{id}")
    public ModelAndView getAccountHistory(HttpServletRequest request, @PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/account/account_drawmoney_info"));
        try {
            UserAccounthistory userAccounthistory = userAccounthistoryService.getUserAccounthistoryById(id);
            modelAndView.addObject("userAccounthistory",userAccounthistory);
        } catch (Exception e) {
            setExceptionRequest(request, e);
        }
        return modelAndView;
    }
}