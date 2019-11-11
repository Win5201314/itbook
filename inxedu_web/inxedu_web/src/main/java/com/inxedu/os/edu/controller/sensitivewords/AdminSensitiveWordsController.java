package com.inxedu.os.edu.controller.sensitivewords;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.entity.sensitivewords.SensitiveWords;
import com.inxedu.os.edu.service.sensitivewords.SensitiveWordsService;
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
 * @description 敏感词 后台SensitiveWordsController
 */
@Controller
@RequestMapping("/admin")
public class AdminSensitiveWordsController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(AdminSensitiveWordsController.class);
	
	@Autowired
	private SensitiveWordsService sensitiveWordsService;
	
	
	// 绑定属性 封装参数
	@InitBinder("sensitiveWords")
	public void initSensitiveWords(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sensitiveWords.");
	}
	
	/** 
	 * 到敏感词添加页面
	 */
    @RequestMapping("/sensitiveWords/toadd")
    public ModelAndView toAddSensitiveWords(HttpServletRequest request,ModelAndView model) {
    	model.setViewName(getViewPath("/admin/sensitivewords/sensitivewords_add"));
    	try{
    		
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("AdminSensitiveWordsController.toAddSensitiveWords()---error",e);
		}
        return model;
    }
	
    /**
     * 添加敏感词
     */
    @RequestMapping("/sensitiveWords/add")
    public String addSensitiveWords(HttpServletRequest request, @ModelAttribute("sensitiveWords") SensitiveWords sensitiveWords) {
        try {
            // 添加敏感词
            if (ObjectUtils.isNotNull(sensitiveWords)) {
                sensitiveWordsService.addSensitiveWords(sensitiveWords);
            }
        } catch (Exception e) {
            logger.error("AdminSensitiveWordsController.addSensitiveWords", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/sensitiveWords/list";
    }

    /**
     * ajax刪除敏感词
     */
    @RequestMapping("/ajax/sensitiveWordsDel/{id}")
    @ResponseBody
    public Map<String,Object> ajaxDelSensitiveWords(HttpServletRequest request, @PathVariable("id") Long id) {
    	Map<String, Object> json = new HashMap<String, Object>(4);
    	try {
    		// 刪除敏感词
            sensitiveWordsService.delSensitiveWordsById(id);
            json = setJson(true, null, null);
        } catch (Exception e) {
            logger.error("AdminSensitiveWordsController.ajaxDelSensitiveWords()---error", e);
            json=this.setAjaxException(json);
        }
    	return json;
    }

    
    
    /**
     * 根据id修改
     */
    @RequestMapping("/sensitiveWords/toUpdate/{id}")
    public ModelAndView toUpdateSensitiveWords(HttpServletRequest request,ModelAndView model, @PathVariable("id") Long id) {
    	model.setViewName(getViewPath("/admin/sensitivewords/sensitivewords_update"));
    	try {
            // 查詢敏感词
            SensitiveWords sensitiveWords = sensitiveWordsService.getSensitiveWordsById(id);
            // 把返回的数据放到model中
            model.addObject("sensitiveWords", sensitiveWords);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminSensitiveWordsController.toUpdateSensitiveWords()--error", e);
        }
        return model;
    }
    /**
     * 更新敏感词
     */
    @RequestMapping("/sensitiveWords/update")
    public ModelAndView updateSensitiveWords(HttpServletRequest request,ModelAndView model, @ModelAttribute("sensitiveWords") SensitiveWords sensitiveWords) {
    	model.setViewName("redirect:/admin/sensitiveWords/list");
    	try {
            sensitiveWordsService.updateSensitiveWords(sensitiveWords);
        } catch (Exception e) {
            logger.error("AdminSensitiveWordsController.updateSensitiveWords()--error", e);
            model.setViewName(setExceptionRequest(request, e));
        }
        return model;
    }
	
    /**
     * 查询敏感词分页列表
     */
    @RequestMapping("/sensitiveWords/list")
    public ModelAndView sensitiveWordsListPage(HttpServletRequest request,ModelAndView model,@ModelAttribute("sensitiveWords") SensitiveWords sensitiveWords, @ModelAttribute("page") PageEntity page) {
    	model.setViewName(getViewPath("/admin/sensitivewords/sensitivewords_list"));
    	try {
            //按条件查询敏感词分页
            List<SensitiveWords> sensitiveWordsList =  sensitiveWordsService.querySensitiveWordsListPage(sensitiveWords, page);
            //敏感词数据
            model.addObject("sensitiveWordsList",sensitiveWordsList);
            //分数数据
            model.addObject("page",page);
            //敏感词查询条件
            model.addObject("sensitiveWords",sensitiveWords);
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminSensitiveWordsController.sensitiveWordsListPage()--error", e);
        }
        return model;
    }
}



