package com.inxedu.os.edu.controller.account;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.constants.enums.account.AccountBizType;
import com.inxedu.os.edu.constants.enums.account.AccountHistoryType;
import com.inxedu.os.edu.entity.account.QueryUserAccounthistory;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccounthistory;
import com.inxedu.os.edu.entity.card.CardCode;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.account.UserAccounthistoryService;
import com.inxedu.os.edu.service.card.CardCodeService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lucky on 2017/1/12.
 */
@Controller
public class UserAccountController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryUserAccounthistory")
    public void initBinderQueryUserAccounthistory(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUserAccounthistory.");
    }

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccounthistoryService userAccounthistoryService;
    @Autowired(required=false)
    private CardCodeService cardCodeSerivce;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    /**
     * 我的账户
     */
    @RequestMapping("/uc/myAccount")
    public ModelAndView myAccount(HttpServletRequest request, @ModelAttribute("queryUserAccounthistory")QueryUserAccounthistory queryUserAccounthistory, @ModelAttribute("page")PageEntity page){
        ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/uc-account"));
        try{
            Long userId=Long.valueOf(SingletonLoginUtils.getLoginUserId(request));
            UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
            model.addObject("userAccount", userAccount);

            queryUserAccounthistory.setUserId(userId);
            List<UserAccounthistory> accList = userAccounthistoryService.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
            model.addObject("accList", accList);
        }catch (Exception e) {
            model.setViewName(this.setExceptionRequest(request, e));
            logger.error("myAccount()---error",e);
        }
        return model;
    }

    /**
     * 账户详情
     */
    @RequestMapping("/accountInfo")
    public ModelAndView accountInfo(HttpServletRequest request){
        ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/uc-accountInfo"));
        try{

        }catch (Exception e) {
            model.setViewName(this.setExceptionRequest(request, e));
            logger.error("myInvite()---error",e);
        }
        return model;
    }

    /**
     * 充值卡激活
     */
    @RequestMapping("/uc/ajax/recharge")
    @ResponseBody
    public Map<String,Object> recharge(HttpServletRequest request){
        Map<String,Object> json = new HashedMap();
        try {
            /*充值卡卡号*/
            String couponCode = request.getParameter("couponCode");
            /*充值卡密码*/
            String couponPwd = request.getParameter("couponPwd");
            int userId = SingletonLoginUtils.getLoginUserId(request);
            CardCode cardCode = new CardCode();
            cardCode.setCardCode(couponCode);
            cardCode.setCardCodePassword(couponPwd);
            String msg = cardCodeSerivce.activationCard(cardCode, Long.valueOf(SingletonLoginUtils.getLoginUserId(request)), 2);
            json = this.setJson(true,"",msg);
        }catch (Exception e) {
            logger.error("UserAccountController.recharge()", e);
            json=setAjaxException(json);
        }
        return json;

    }
    /**
     * 提现
     */
    @RequestMapping("/uc/ajax/drawMoney")
    @ResponseBody
    public Map<String,Object> drawMoney(HttpServletRequest request){
        Map<String,Object> json = new HashedMap();
        try {
            //提现开关
            Map<String,Object> withdrawals = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.withdrawals.toString()).get(WebSiteProfileType.withdrawals.toString());
            if (!(withdrawals.get("withdrawalsSwitch")+"").equals("ON")){
                json = this.setJson(false,"暂不支持提现",null);
                return json;
            }

            /*提取金额*/
            String money = request.getParameter("money");
            /*银行卡号*/
            String card = request.getParameter("card");
            /*转账银行*/
            String bank = request.getParameter("bank");
            Long userId=Long.valueOf(SingletonLoginUtils.getLoginUserId(request));
            UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
            //分销配置
            Map<String,Object> invite = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.invite.toString()).get(WebSiteProfileType.invite.toString());
            //可提现金额
            BigDecimal usable=userAccount.getCashAmount();
            //判断 分销金额是否允许提现
            if("ON".equals(invite.get("withdrawCash"))){
                usable=usable.add(userAccount.getBackAmount());
            }
            BigDecimal drawMoney = new BigDecimal(money);
            if (usable.compareTo(drawMoney)==-1){
                json = this.setJson(false,"可提现金额不足",null);
                return json;
            }
            /*冻结提现金额*/
            userAccount.setForzenAmount(userAccount.getForzenAmount().add(drawMoney));
            /*余额减掉要提取的金额*/
            userAccount.setBalance(userAccount.getBalance().subtract(drawMoney));
            /*如果要提现金额 小于等于 可提现金额*/
            if(drawMoney.compareTo(userAccount.getCashAmount())<=0){
                /*现金余额减掉要提取的金额*/
                userAccount.setCashAmount(userAccount.getCashAmount().subtract(drawMoney));
            }else if(drawMoney.compareTo(userAccount.getCashAmount())==1&&"ON".equals(invite.get("withdrawCash"))){
                userAccount.setCashAmount(new BigDecimal(0));
                userAccount.setBackAmount(userAccount.getBackAmount().subtract(drawMoney.subtract(userAccount.getCashAmount())));
            }
            /*更新账户信息*/
            userAccountService.updateUserAccount(userAccount);

            /*创建申请提现记录*/
            UserAccounthistory userAccounthistory = new UserAccounthistory();
            userAccounthistory.setUserId(new Long(SingletonLoginUtils.getLoginUserId(request)));
            userAccounthistory.setActHistoryType(AccountHistoryType.DRAWMONEY.toString());
            userAccounthistory.setCreateTime(new Date());
            userAccounthistory.setBalance(userAccount.getBalance());
            userAccounthistory.setBackAmount(userAccount.getBackAmount());
            userAccounthistory.setStatus("NOTVIEWED");
            userAccounthistory.setCashAmount(userAccount.getCashAmount());

            userAccounthistory.setTrxAmount(drawMoney);
            userAccounthistory.setDescription("提现金额"+drawMoney);
            userAccounthistory.setBizType(AccountBizType.DRAWMONEY.toString());
            userAccounthistory.setVersion(userAccount.getVersion());
            /*转账银行*/
            userAccounthistory.setBank(bank);
            /*银行卡*/
            userAccounthistory.setCard(card);
            userAccounthistory.setVmAmount(userAccount.getVmAmount());
            userAccounthistoryService.addUserAccounthistory(userAccounthistory);
            json = this.setJson(true,"",null);
        }catch (Exception e) {
            logger.error("UserAccountController.drawMoney()", e);
            json=setAjaxException(json);
        }
        return json;
    }
    /**
     * 会员充值
     */
    @RequestMapping("/account/recharge")
    public String accountRecharge(HttpServletRequest request) {
        try {
            request.setAttribute("recharge",request.getParameter("recharge"));
        }catch (Exception e){
            logger.error("ShopcartController.payMember()--error",e);
            setExceptionRequest(request,e);
        }
        return getViewPath("/web/account/account-buy");
    }
}
