package com.inxedu.os.edu.controller.invitationrecord;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecord;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecordDto;
import com.inxedu.os.edu.service.invitationrecord.InvitationRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 * @description edu_invitation_record 后台InvitationRecordController
 */
@Controller
@RequestMapping("/admin")
public class AdminInvitationRecordController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(AdminInvitationRecordController.class);
	
	@Autowired
	private InvitationRecordService invitationRecordService;
	
	
	// 绑定属性 封装参数
	@InitBinder("invitationRecord")
	public void initInvitationRecord(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("invitationRecord.");
	}
	
	/** 
	 * 到edu_invitation_record添加页面
	 */
    @RequestMapping("/invitationRecord/toadd")
    public ModelAndView toAddInvitationRecord(HttpServletRequest request,ModelAndView model) {
    	model.setViewName(getViewPath("/admin/invitationRecord/invitationrecord_add"));
    	try{
    		
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("AdminInvitationRecordController.toAddInvitationRecord()---error",e);
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
            logger.error("AdminInvitationRecordController.addInvitationRecord", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/invitationRecord/list";
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
            logger.error("AdminInvitationRecordController.deleteInvitationRecord()---error", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/invitationRecord/list";
    }
    
    /**
     * ajax刪除edu_invitation_record
     */
    @RequestMapping("/ajax/invitationRecordDel/{id}")
    @ResponseBody
    public Map<String,Object> ajaxDelInvitationRecord(HttpServletRequest request, @PathVariable("id") Long id) {
    	Map<String, Object> json = new HashMap<String, Object>(4);
    	try {
    		// 刪除edu_invitation_record
            invitationRecordService.delInvitationRecordById(id);
            json = setJson(true, null, null);
        } catch (Exception e) {
            logger.error("AdminInvitationRecordController.ajaxDelInvitationRecord()---error", e);
            json=this.setAjaxException(json);
        }
    	return json;
    }

    
    
    /**
     * 根据id修改
     */
    @RequestMapping("/invitationRecord/toUpdate/{id}")
    public ModelAndView toUpdateInvitationRecord(HttpServletRequest request,ModelAndView model, @PathVariable("id") Long id) {
    	model.setViewName(getViewPath("/admin/invitationRecord/invitationrecord_update"));
    	try {
            // 查詢edu_invitation_record
            InvitationRecord invitationRecord = invitationRecordService.getInvitationRecordById(id);
            // 把返回的数据放到model中
            model.addObject("invitationRecord", invitationRecord);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminInvitationRecordController.toUpdateInvitationRecord()--error", e);
        }
        return model;
    }
    /**
     * 更新edu_invitation_record
     */
    @RequestMapping("/invitationRecord/update")
    public ModelAndView updateInvitationRecord(HttpServletRequest request,ModelAndView model, @ModelAttribute("invitationRecord") InvitationRecord invitationRecord) {
    	model.setViewName("redirect:/admin/invitationRecord/list");
    	try {
            invitationRecordService.updateInvitationRecord(invitationRecord);
        } catch (Exception e) {
            logger.error("AdminInvitationRecordController.updateInvitationRecord()--error", e);
            model.setViewName(setExceptionRequest(request, e));
        }
        return model;
    }
	
    /**
     * 查询edu_invitation_record分页列表
     */
    @RequestMapping("/invitationRecord/list")
    public ModelAndView invitationRecordListPage(HttpServletRequest request,ModelAndView model,@ModelAttribute("invitationRecord") InvitationRecord invitationRecord, @ModelAttribute("page") PageEntity page) {
    	model.setViewName(getViewPath("/invite/admin/invitationrecord/invitationrecord_list"));
    	try {
            //按条件查询edu_invitation_record分页
            List<InvitationRecordDto> invitationRecordList =  invitationRecordService.queryInvitationRecordListPage(invitationRecord, page);
            //edu_invitation_record数据
            model.addObject("invitationRecordList",invitationRecordList);
            //分数数据
            model.addObject("page",page);
            //edu_invitation_record查询条件
            model.addObject("invitationRecord",invitationRecord);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminInvitationRecordController.invitationRecordListPage()--error", e);
        }
        return model;
    }
}



