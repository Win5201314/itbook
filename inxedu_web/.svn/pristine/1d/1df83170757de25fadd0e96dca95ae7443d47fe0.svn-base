package com.inxedu.os.edu.controller.dialogController;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 前台 controller
 * @author www.inxedu.com
 */
@Controller
public class FrontDialogController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(FrontDialogController.class);
    @Autowired
    private UserService userService;
    @Autowired(required=false)
    private MemberTypeService memberTypeService;
    @Autowired(required=false)
    private MemberSaleService memberSaleService;
    @Autowired(required=false)
    private UserAccountService userAccountService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @RequestMapping("/dialog/ajax/showPage")
    public ModelAndView dialog(HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName(getViewPath("/web/front/dialog"));
            String dTitle = request.getParameter("dTitle");
            String msg = request.getParameter("msg");
            int index = Integer.parseInt(request.getParameter("index"));
            String url = request.getParameter("url");
            /*如果index=7弹出修改头像页面*/
            if (index==7){
                /*取得当前登陆人的信息返回到页面上*/
                int userId = SingletonLoginUtils.getLoginUserId(request);
                User user = userService.queryUserById(userId);
                model.addObject("user", user);
            }
            /*如果index=9 开通会员*/
            if(index==9){
                /*查询会员类型*/
                List<MemberType> memberTypes = memberTypeService.getMemberTypes();
                model.addObject("memberTypes", memberTypes);
            }
            /*如果index=10 提现*/
            if (index==10){
                Long userId=Long.valueOf(SingletonLoginUtils.getLoginUserId(request));
                UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
                //分销配置
                Map<String,Object> invite = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.invite.toString()).get(WebSiteProfileType.invite.toString());
                model.addObject("withdrawCash", invite.get("withdrawCash"));
                model.addObject("userAccount", userAccount);
            }
            model.addObject("dTitle",dTitle);
            model.addObject("msg",msg);
            model.addObject("index",index);
            model.addObject("url",url);
        }catch (Exception e){
            model.setViewName(this.setExceptionRequest(request, e));
            logger.error("dialog()--error",e);
        }
        return model;
    }
    /*查询会员的售价*/
    @RequestMapping("/ajax/getMemberChildType")
    @ResponseBody
    public Map<String,Object> getMemberChildType(HttpServletRequest request){
        Map<String,Object> json = null;
        try {
            String typeId = request.getParameter("typeId");
            /*查询会员的售价*/
            List<MemberSale> memberSales = memberSaleService.getMemberSaleListByType(Long.parseLong(typeId));
            json = this.setJson(true,"",memberSales);
        }catch (Exception e){
            logger.error("FrontDialogController.getMemberChildType--加载会员子类型出错", e);
            json = this.setJson(false, "系统繁忙,请稍后再试！", null);
        }
        return json;
    }
}
