package com.inxedu.os.edu.controller.qcloudsmstemplate;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.entity.qcloudsmstemplate.QcloudSmsTemplate;
import com.inxedu.os.edu.service.qcloudsmstemplate.QcloudSmsTemplateService;
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

/**
 * @author www.inxedu.com
 * @description  前台QcloudSmsTemplateController
 */
@Controller
public class QcloudSmsTemplateController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QcloudSmsTemplateController.class);
	@Autowired
	private QcloudSmsTemplateService qcloudSmsTemplateService;
	// 绑定属性 封装参数
	@InitBinder("qcloudSmsTemplate")
	public void initQcloudSmsTemplate(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("qcloudSmsTemplate.");
	}
	/**
	 * 到添加页面
	 */
    @RequestMapping("/qcloudSmsTemplate/toadd")
    public ModelAndView toAddQcloudSmsTemplate(ModelAndView model) {
    	model.setViewName(getViewPath("/web/qcloudSmsTemplate/qcloudsmstemplate-add"));
        return model;
    }
    /**
     * 添加
     */
    @RequestMapping("/qcloudSmsTemplate/add")
    public String addQcloudSmsTemplate(HttpServletRequest request, @ModelAttribute("qcloudSmsTemplate") QcloudSmsTemplate qcloudSmsTemplate) {
        try {
            // 添加
            if (ObjectUtils.isNotNull(qcloudSmsTemplate)) {
                qcloudSmsTemplateService.addQcloudSmsTemplate(qcloudSmsTemplate);
            }
        } catch (Exception e) {
            logger.error("QcloudSmsTemplateController.addQcloudSmsTemplate", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/front/qcloudSmsTemplate/list";
    }
    /**
     * 刪除
     */
    @RequestMapping("/qcloudSmsTemplate/delete/{id}")
    public String deleteQcloudSmsTemplate(HttpServletRequest request, @PathVariable("id") int id) {
    	try {
    		// 刪除
            qcloudSmsTemplateService.delQcloudSmsTemplateById(id);
        } catch (Exception e) {
            logger.error("QcloudSmsTemplateController.deleteQcloudSmsTemplate()---error", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/front/qcloudSmsTemplate/list";
    }
    
    /**
     * 根据id获得详情
     */
    @RequestMapping("/qcloudSmsTemplate/toUpdate/{id}")
    public ModelAndView toUpdateQcloudSmsTemplate(HttpServletRequest request,ModelAndView model, @PathVariable("id") int id) {
    	model.setViewName(getViewPath("/web/qcloudSmsTemplate/qcloudsmstemplate-info"));
    	try {
            // 查詢
            QcloudSmsTemplate qcloudSmsTemplate = qcloudSmsTemplateService.getQcloudSmsTemplateById(id);
            // 把返回的数据放到model中
            model.addObject("qcloudSmsTemplate", qcloudSmsTemplate);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("QcloudSmsTemplateController.toUpdateQcloudSmsTemplate()--error", e);
        }
        return model;
    }
    /**
     * 更新
     */
    @RequestMapping("/qcloudSmsTemplate/update")
    public ModelAndView updateQcloudSmsTemplate(HttpServletRequest request,ModelAndView model, @ModelAttribute("qcloudSmsTemplate") QcloudSmsTemplate qcloudSmsTemplate) {
    	model.setViewName("redirect:/front/qcloudSmsTemplate/list");
    	try {
            qcloudSmsTemplateService.updateQcloudSmsTemplate(qcloudSmsTemplate);
        } catch (Exception e) {
            logger.error("QcloudSmsTemplateController.updateQcloudSmsTemplate()--error", e);
            model.setViewName(setExceptionRequest(request, e));
        }
        return model;
    }
	
    /**
     * 查询分页列表
     */
    @RequestMapping("/qcloudSmsTemplate/list")
    public ModelAndView qcloudSmsTemplateListPage(HttpServletRequest request,ModelAndView model,@ModelAttribute("qcloudSmsTemplate") QcloudSmsTemplate qcloudSmsTemplate, @ModelAttribute("page") PageEntity page) {
    	model.setViewName(getViewPath("/web/qcloudsmstemplate/qcloudsmstemplate-list"));
    	try {
            //按条件查询分页
            List<QcloudSmsTemplate> qcloudSmsTemplateList =  qcloudSmsTemplateService.queryQcloudSmsTemplateListPage(qcloudSmsTemplate, page);
            //数据
            model.addObject("qcloudSmsTemplateList",qcloudSmsTemplateList);
            //分数数据
            model.addObject("page",page);
            //QcloudSmsTemplate查询条件
            model.addObject("qcloudSmsTemplate",qcloudSmsTemplate);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("QcloudSmsTemplateController.qcloudSmsTemplateListPage()--error", e);
        }
        return model;
    }
}



