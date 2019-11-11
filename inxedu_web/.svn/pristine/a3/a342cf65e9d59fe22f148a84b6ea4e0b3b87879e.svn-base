package com.inxedu.os.edu.controller.invitationrecord;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecord;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecordDto;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.invitationrecord.InvitationRecordService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 * @description edu_invitation_record 前台InvitationRecordController
 */
@Controller
public class InvitationRecordController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(InvitationRecordController.class);

	@Autowired
	private InvitationRecordService invitationRecordService;
	@Autowired
	private WebsiteProfileService websiteProfileService;

	
	// 绑定属性 封装参数
	@InitBinder("invitationRecord")
	public void initInvitationRecord(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("invitationRecord.");
	}

    /**
     * 我的邀请
     */
    @RequestMapping("/uc/myInvite")
    public ModelAndView myInvite(HttpServletRequest request,@ModelAttribute("invitationRecord")InvitationRecord invitationRecord,@ModelAttribute("page")PageEntity page){
        ModelAndView model = new ModelAndView(getViewPath("/invite/invitationrecord/uc-invite"));
        try{
            User user= SingletonLoginUtils.getLoginUser(request);
            model.addObject("user", user);
            invitationRecord.setUserId(Long.valueOf(user.getUserId()));
            List<InvitationRecordDto> invitationRecordList=invitationRecordService.queryInvitationRecordListPage(invitationRecord, page);
            model.addObject("invitationRecordList", invitationRecordList);

            //分销配置
            Map<String,Object> invite = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.invite.toString()).get(WebSiteProfileType.invite.toString());
            request.setAttribute("invite",invite);
        }catch (Exception e) {
            model.setViewName(this.setExceptionRequest(request, e));
            logger.error("InvitationRecordController.myInvite()---error",e);
        }
        return model;
    }






	
	/** 
	 * 到edu_invitation_record添加页面
	 */
    @RequestMapping("/invitationRecord/toadd")
    public ModelAndView toAddInvitationRecord(HttpServletRequest request,ModelAndView model) {
    	model.setViewName(getViewPath("/web/invitationRecord/invitationrecord-add"));
    	try{
    		
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("InvitationRecordController.toAddInvitationRecord()---error",e);
		}
        return model;
    }
	
    /**
     * 添加edu_invitation_record
     */
    @RequestMapping("/invitationRecord/add")
    public String addInvitationRecord(HttpServletRequest request, @ModelAttribute("invitationRecord") InvitationRecord invitationRecord) {
        try {
            // 添加edu_invitation_record
            if (ObjectUtils.isNotNull(invitationRecord)) {
                invitationRecordService.addInvitationRecord(invitationRecord);
            }
        } catch (Exception e) {
            logger.error("InvitationRecordController.addInvitationRecord", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/front/invitationRecord/list";
    }
    
    /**
     * 刪除edu_invitation_record
     */
    @RequestMapping("/invitationRecord/delete/{id}")
    public String deleteInvitationRecord(HttpServletRequest request, @PathVariable("id") Long id) {
    	try {
    		// 刪除edu_invitation_record
            invitationRecordService.delInvitationRecordById(id);
        } catch (Exception e) {
            logger.error("InvitationRecordController.deleteInvitationRecord()---error", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/front/invitationRecord/list";
    }
    
    /**
     * 根据id获得edu_invitation_record详情
     */
    @RequestMapping("/invitationRecord/toUpdate/{id}")
    public ModelAndView toUpdateInvitationRecord(HttpServletRequest request,ModelAndView model, @PathVariable("id") Long id) {
    	model.setViewName(getViewPath("/web/invitationRecord/invitationrecord-info"));
    	try {
            // 查詢edu_invitation_record
            InvitationRecord invitationRecord = invitationRecordService.getInvitationRecordById(id);
            // 把返回的数据放到model中
            model.addObject("invitationRecord", invitationRecord);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("InvitationRecordController.toUpdateInvitationRecord()--error", e);
        }
        return model;
    }
    /**
     * 更新edu_invitation_record
     */
    @RequestMapping("/invitationRecord/update")
    public ModelAndView updateInvitationRecord(HttpServletRequest request,ModelAndView model, @ModelAttribute("invitationRecord") InvitationRecord invitationRecord) {
    	model.setViewName("redirect:/front/invitationRecord/list");
    	try {
            invitationRecordService.updateInvitationRecord(invitationRecord);
        } catch (Exception e) {
            logger.error("InvitationRecordController.updateInvitationRecord()--error", e);
            model.setViewName(setExceptionRequest(request, e));
        }
        return model;
    }
	
    /**
     * 查询edu_invitation_record分页列表
     */
    @RequestMapping("/invitationRecord/list")
    public ModelAndView invitationRecordListPage(HttpServletRequest request,ModelAndView model,@ModelAttribute("invitationRecord") InvitationRecord invitationRecord, @ModelAttribute("page") PageEntity page) {
    	model.setViewName(getViewPath("/web/invitationrecord/invitationrecord-list"));
    	try {
            //按条件查询edu_invitation_record分页
            List<InvitationRecordDto> invitationRecordList =  invitationRecordService.queryInvitationRecordListPage(invitationRecord, page);
            //edu_invitation_record数据
            model.addObject("invitationRecordList",invitationRecordList);
            //分数数据
            model.addObject("page",page);
            //InvitationRecord查询条件
            model.addObject("invitationRecord",invitationRecord);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("InvitationRecordController.invitationRecordListPage()--error", e);
        }
        return model;
    }
}



