package com.inxedu.os.edu.controller.qcloudsmstemplate;



import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.QCloud.QCloudSMSSignUtil;
import com.inxedu.os.common.util.QCloud.QCloudSMSTemplateUtil;
import com.inxedu.os.common.util.StringUtils;

import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.qcloudsmstemplate.QcloudSmsTemplate;
import com.inxedu.os.edu.service.qcloudsmstemplate.QcloudSmsTemplateService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
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
 * @description  后台QcloudSmsTemplateController
 */
@Controller
@RequestMapping("/admin")
public class AdminQcloudSmsTemplateController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminQcloudSmsTemplateController.class);
	
	@Autowired
	private QcloudSmsTemplateService qcloudSmsTemplateService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
	// 绑定属性 封装参数
	@InitBinder("qcloudSmsTemplate")
	public void initQcloudSmsTemplate(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("qcloudSmsTemplate.");
	}
	/** 
	 * 到签名添加页面
	 */
    @RequestMapping("/qcloudSmsSign/toadd")
    public ModelAndView toAddQcloudSmsSign(HttpServletRequest request,ModelAndView model) {
    	model.setViewName(getViewPath("/admin/qcloudsmstemplate/qcloudsmsSign_add"));
        return model;
    }
    /**
     * 到模板添加页面
     */
    @RequestMapping("/qcloudSmsTemlate/toadd")
    public ModelAndView toAddQcloudSmsTemplate(ModelAndView model) {
        model.setViewName(getViewPath("/admin/qcloudsmstemplate/qcloudsmsTemplate_add"));
        return model;
    }
    /**
     * ajax添加模板
     */
    @RequestMapping("/qcloudSmsTemlate/ajax/add")
    @ResponseBody
    public Map<String,Object> ajaxAddQcloudSmsTemplate(HttpServletRequest request){
        Map<String, Object> json = new HashMap<String, Object>(4);
        Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString());
        Map<String,Object> smsMap=(Map<String,Object>)websitemap.get("sms");
        QCloudSMSTemplateUtil qCloudSMSTemplateUtil = new QCloudSMSTemplateUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
        try {
            String text = request.getParameter("text");
            int smsType = Integer.parseInt(request.getParameter("smsType").toString());
            String result = qCloudSMSTemplateUtil.addTemplate(text,smsType);
            Map<String,Object> resultMap = FastJsonUtil.json2Map(result);
            json = setJson(true,null,resultMap);
        } catch (Exception e) {
            logger.error("AdminQcloudSmsTemplateController.ajaxAddQcloudSmsSign()---error", e);
            json=this.setAjaxException(json);
        }
        return json;
    }
    /**
     * 添加Template
     */
    @RequestMapping("/qcloudSmsTemplate/add")
    @SystemLog(type = "add",operation = "添加Template")
    public String addQcloudSmsTemplate(HttpServletRequest request, @ModelAttribute("qcloudSmsTemplate") QcloudSmsTemplate qcloudSmsTemplate,String type) {
        try {
            // 添加
            if (ObjectUtils.isNotNull(qcloudSmsTemplate)) {
                qcloudSmsTemplateService.addQcloudSmsTemplate(qcloudSmsTemplate);
            }
        } catch (Exception e) {
            logger.error("AdminQcloudSmsTemplateController.addQcloudSmsSign", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/qcloudSmsTemplate/list?type="+type;
    }
    /**
     * ajax添加签名
     */
    @RequestMapping("/qcloudSmsSign/ajax/add")
    @SystemLog(type = "add",operation = "ajax添加签名")
    @ResponseBody
    public Map<String,Object> ajaxAddQcloudSmsSign(HttpServletRequest request){
        Map<String, Object> json = new HashMap<String, Object>(4);
        Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString());
        Map<String,Object> smsMap=(Map<String,Object>)websitemap.get("sms");
        QCloudSMSSignUtil qCloudSMSSignUtil = new QCloudSMSSignUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
        try {
            String text = request.getParameter("text");
            String result = qCloudSMSSignUtil.addSign(text);
            Map<String,Object> resultMap=FastJsonUtil.json2Map(result);
            json = setJson(true,null,resultMap);
        } catch (Exception e) {
            logger.error("AdminQcloudSmsTemplateController.ajaxAddQcloudSmsSign()---error", e);
            json=this.setAjaxException(json);
        }
        return json;
    }
    /**
     * 刪除
     */
    @RequestMapping("/qcloudSmsTemplate/delete/{id}")
    @SystemLog(type = "del",operation = "刪除")
    public String deleteQcloudSmsTemplate(HttpServletRequest request, @PathVariable("id") int id) {
    	try {
    		// 刪除
            qcloudSmsTemplateService.delQcloudSmsTemplateById(id);
        } catch (Exception e) {
            logger.error("AdminQcloudSmsTemplateController.deleteQcloudSmsTemplate()---error", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/qcloudSmsTemplate/list";
    }
    
    /**
     * ajax刪除
     */
    @RequestMapping("/ajax/qcloudSmsTemplateDel/{id}")
    @SystemLog(type = "del",operation = "刪除")
    @ResponseBody
    public Map<String,Object> ajaxDelQcloudSmsTemplate(HttpServletRequest request, @PathVariable("id") int id) {
    	Map<String, Object> json = new HashMap<String, Object>(4);
    	try {
            Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString());
            Map<String,Object> smsMap=(Map<String,Object>)websitemap.get("sms");
            QCloudSMSSignUtil qCloudSMSSignUtil = new QCloudSMSSignUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
            QCloudSMSTemplateUtil qCloudSMSTemplateUtil = new QCloudSMSTemplateUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
    		// 刪除
            QcloudSmsTemplate qcloudSmsTemplate = qcloudSmsTemplateService.getQcloudSmsTemplateById(id);
            if (qcloudSmsTemplate.getType().equals("sign")){
                qCloudSMSSignUtil.delSign(Integer.parseInt(qcloudSmsTemplate.getOtherId()));
            }else {
                int i[] = {Integer.parseInt(qcloudSmsTemplate.getOtherId())};
                qCloudSMSTemplateUtil.delTemplate(i);
            }
            qcloudSmsTemplateService.delQcloudSmsTemplateById(id);
            json = setJson(true, null, null);
        } catch (Exception e) {
            logger.error("AdminQcloudSmsTemplateController.ajaxDelQcloudSmsTemplate()---error", e);
            json=this.setAjaxException(json);
        }
    	return json;
    }
    //查看内容
    @RequestMapping("/ajax/selectContent/{id}")
    @ResponseBody
    public Map<String,Object> ajaxSelectContent(HttpServletRequest request, @PathVariable("id") int id) {
        Map<String, Object> json = new HashMap<String, Object>(4);
        try {
            QcloudSmsTemplate qcloudSmsTemplate = qcloudSmsTemplateService.getQcloudSmsTemplateById(id);
            json = this.setJson(true,"",qcloudSmsTemplate);
        } catch (Exception e) {
            logger.error("AdminQcloudSmsTemplateController.ajaxSelectContent()---error", e);
            json=this.setAjaxException(json);
        }
        return json;

    }

    /**
     * 根据id修改
     */
    @RequestMapping("/qcloudSmsTemplate/toUpdate/{id}")
    @SystemLog(type = "update",operation = "修改")
    public ModelAndView toUpdateQcloudSmsTemplate(HttpServletRequest request,ModelAndView model, @PathVariable("id") int id) {
    	model.setViewName(getViewPath("/admin/qcloudsmstemplate/qcloudsmstemplate_update"));
    	try {
            // 查詢
            QcloudSmsTemplate qcloudSmsTemplate = qcloudSmsTemplateService.getQcloudSmsTemplateById(id);
            // 把返回的数据放到model中
            model.addObject("qcloudSmsTemplate", qcloudSmsTemplate);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminQcloudSmsTemplateController.toUpdateQcloudSmsTemplate()--error", e);
        }
        return model;
    }
    /**
     * 更新
     */
    @RequestMapping("/qcloudSmsTemplate/update")
    @SystemLog(type = "update",operation = "更新")
    public ModelAndView updateQcloudSmsTemplate(HttpServletRequest request,ModelAndView model, @ModelAttribute("qcloudSmsTemplate") QcloudSmsTemplate qcloudSmsTemplate) {
    	model.setViewName("redirect:/admin/qcloudSmsTemplate/list");
    	try {
            qcloudSmsTemplateService.updateQcloudSmsTemplate(qcloudSmsTemplate);
        } catch (Exception e) {
            logger.error("AdminQcloudSmsTemplateController.updateQcloudSmsTemplate()--error", e);
            model.setViewName(setExceptionRequest(request, e));
        }
        return model;
    }
    /**
     * 查询分页列表
     */
    @RequestMapping("/qcloudSmsTemplate/list")
    public ModelAndView qcloudSmsTemplateListPage(HttpServletRequest request,ModelAndView model,@ModelAttribute("qcloudSmsTemplate") QcloudSmsTemplate qcloudSmsTemplate, @ModelAttribute("page") PageEntity page,String type) {
    	model.setViewName(getViewPath("/admin/qcloudsmstemplate/qcloudsmstemplate_list"));
    	try {
    	    qcloudSmsTemplate.setType(type);
            Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString());
            Map<String,Object> smsMap=(Map<String,Object>)websitemap.get("sms");
            QCloudSMSSignUtil qCloudSMSSignUtil = new QCloudSMSSignUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
            QCloudSMSTemplateUtil qCloudSMSTemplateUtil = new QCloudSMSTemplateUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
            //按条件查询分页
            List<QcloudSmsTemplate> qcloudSmsTemplateList =  qcloudSmsTemplateService.queryQcloudSmsTemplateListPage(qcloudSmsTemplate, page);
            /*查询审查状态*/
            if (StringUtils.isNotEmpty(smsMap.get("sdkappid").toString())&&StringUtils.isNotEmpty(smsMap.get("strAppkey").toString())) {
                qcloudSmsTemplateService.checkStatus(qcloudSmsTemplateList);
            }
            //数据
            model.addObject("qcloudSmsTemplateList",qcloudSmsTemplateList);
            //分数数据
            model.addObject("page",page);
            //查询条件
            model.addObject("qcloudSmsTemplate",qcloudSmsTemplate);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminQcloudSmsTemplateController.qcloudSmsTemplateListPage()--error", e);
        }
        return model;
    }
}



