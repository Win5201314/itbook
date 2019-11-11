package com.inxedu.os.edu.controller.subject;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.sys.SysLogType;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.system.SysLogService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专业 Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminSubjectController extends BaseController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminSubjectController.class);
	@Autowired
	private SubjectService subjectService;


	// 返回路径
	private String toSubjectList = getViewPath("/admin/subject/subject_list");// 专业列表

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("subject")
	public void initBinderSubject(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("subject.");
	}

	/**
	 * 删除专业
	 */
	@RequestMapping("/subj/deleteSubject/{subjectId}")
	@ResponseBody
	@SystemLog(type="del",operation="删除课程专业")
	public Map<String, Object> deleteSubject(@PathVariable("subjectId") int subjectId) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			subjectService.deleteSubject(subjectId);
			json = this.setJson(true, null, null);
		} catch (Exception e) {
			this.setAjaxException(json);
			logger.error("deleteSubject()--error", e);
		}
		return json;
	}

	/**
	 * 修改专业
	 */
	@RequestMapping("/subj/updatesubjectName")
	@ResponseBody
	@SystemLog(type="update",operation="修改课程专业")
	public Map<String, Object> updateSubjectName(@ModelAttribute("subject") Subject subject) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			subjectService.updateSubject(subject);

			json = this.setJson(true, null, null);
		} catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateSubjectName()--error", e);
		}
		return json;
	}
	/**
	 * 修改排序
	 */
	@RequestMapping("/subj/updatesort")
	@ResponseBody
	@SystemLog(type="update",operation="修改课程专业排序")
	public Map<String, Object> updateSort(HttpServletRequest request,@ModelAttribute("subject") Subject subject) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			subjectService.updateSubjectSort(subject);
			json = this.setJson(true, null, null);
		} catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateSort()--error", e);
		}
		return json;
	}

	/**
	 * 修改专业父ID
	 */
	@RequestMapping("/subj/updateparentid/{parentId}/{subjectId}")
	@ResponseBody
	public Map<String, Object> updateParentId(@PathVariable("parentId") int parentId, @PathVariable("subjectId") int subjectId) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			subjectService.updateSubjectParentId(subjectId, parentId);
			json = this.setJson(true, null, null);
		} catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateParentId()---error", e);
		}
		return json;
	}

	/**
	 * 创建专业
	 */
	@RequestMapping("/subj/createSubject")
	@ResponseBody
	@SystemLog(type="add",operation="创建课程专业")
	public Map<String, Object> createSubject(HttpServletRequest request,@ModelAttribute("subject") Subject subject) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			subject.setCreateTime(new Date());
			subjectService.createSubject(subject);

			json = this.setJson(true, null, subject);
		} catch (Exception e) {
			this.setAjaxException(json);
			logger.error("createSubject()---error", e);
		}
		return json;
	}

	/**
	 * 到专业列表页面
	 */
	@RequestMapping("/subj/toSubjectList")
	public ModelAndView toSubjectList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(toSubjectList);
			QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList).toString());
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("toSubjectList()---error", e);
		}
		return model;
	}

}
