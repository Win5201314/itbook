package com.inxedu.os.edu.controller.teacher;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
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
import java.util.Date;
import java.util.List;

/**
 * 教师管理
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminTeacherController extends BaseController {
    //log日志
    private static final Logger logger = LoggerFactory.getLogger(AdminTeacherController.class);

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("teacher")
    public void initBinderTeacher(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("teacher.");
    }
    @InitBinder("queryTeacher")
    public void initBinderQueryTeacher(WebDataBinder binder) {
    	binder.setFieldDefaultPrefix("queryTeacher.");
    }
    
    //教师 service
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;

    /** 到教师添加页面 */
    @RequestMapping("/teacher/toadd")
    public ModelAndView toAddTeacher(HttpServletRequest request) {
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName(getViewPath("/admin/teacher/add_teacher"));//讲师添加页面
            //查询一级专业
    		QuerySubject query = new QuerySubject();
            query.setParentId(0);
    		List<Subject> subjectList = subjectService.getSubjectList(query);
    		model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("toAddTeacher()---error",e);
		}
        return model;
    }
    
    /** 添加教师 */
    @RequestMapping("/teacher/add")
    @SystemLog(type="add",operation="添加教师")
    public String addTeacher(HttpServletRequest request, @ModelAttribute("teacher") Teacher teacher) {
        try {
            // 添加讲师
            if (ObjectUtils.isNotNull(teacher)) {
                //状态:0正常1删除
                teacher.setStatus(0);
                //添加时间
                teacher.setCreateTime(new Date());
                teacher.setUpdateTime(new Date());
                teacherService.addTeacher(teacher);
                /*清除前台首页教师缓存*/
                CacheUtil.remove(CacheConstans.INDEX_TEACHER_RECOMMEND);
            }
        } catch (Exception e) {
            logger.error("AdminTeacherController.addTeacher", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/teacher/list";
    }
    /**
     * 到教师列表页面分页
     */
    @RequestMapping("/teacher/list")
    public ModelAndView teacherList(HttpServletRequest request,@ModelAttribute("queryTeacher") QueryTeacher queryTeacher, @ModelAttribute("page") PageEntity page) {
    	ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName(getViewPath("/admin/teacher/teacher_list"));//讲师列表页面
            //按条件查询教师分页
            List<Teacher> teacherList =  teacherService.queryTeacherListPage(queryTeacher, page);
            //教师数据
            model.addObject("teacherList",teacherList);
            //分数数据
            model.addObject("page",page);
            //讲师查询条件
            model.addObject("queryTeacher",queryTeacher);
            request.getSession().setAttribute("teacher_list", WebUtils.getServletRequestUriParms(request));
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("teacherList()--error", e);
        }
        return model;
    }
    /**
     * 根据老师id获得详情
     */
    @RequestMapping("/teacher/toUpdate/{id}")
    public ModelAndView toUpdateTeacher(HttpServletRequest request, @PathVariable("id") int id) {
    	ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName(getViewPath("/admin/teacher/teacher_update"));//讲师修改页面
            // 查詢老師
            Teacher teacher = teacherService.getTeacherById(id);
            // 把返回的数据放到model中
            model.addObject("teacher", teacher);
            QuerySubject query = new QuerySubject();
            query.setParentId(0);
            List<Subject> subjectList = subjectService.getSubjectList(query);
            if(teacher!=null && teacher.getSubjectId()>0){
            	for(Subject s:subjectList){
            		if(s.getSubjectId()==teacher.getSubjectId()){
                        //查询讲师的专业
            			model.addObject("subject", s);
            			break;
            		}
            	}
            }
            model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
        } catch (Exception e) {
        	model.setViewName(setExceptionRequest(request, e));
            logger.error("toUpdateTeacher()--error", e);
        }
        return model;
    }
    /**
     * 更新讲师
     */
    @RequestMapping("/teacher/update")
    @SystemLog(type="update",operation="修改教师")
    public ModelAndView updateTeacher(HttpServletRequest request, @ModelAttribute("teacher") Teacher teacher) {
    	ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName("redirect:/admin/teacher/list");
    		
            if (ObjectUtils.isNotNull(teacher)) {
                teacher.setUpdateTime(new Date());
                teacherService.updateTeacher(teacher);
                 /*清除前台首页教师缓存*/
                CacheUtil.remove(CacheConstans.INDEX_TEACHER_RECOMMEND);
            }
            Object uri = request.getSession().getAttribute("teacher_list");
            if(uri!=null){
            	model.setViewName("redirect:"+uri.toString());
            }
        } catch (Exception e) {
            logger.error("updateTeacher()--error", e);
            model.setViewName(setExceptionRequest(request, e));
        }
        return model;
    }
    /**
     * 刪除讲师
     */
    @RequestMapping("/teacher/delete/{id}")
    @SystemLog(type="del",operation="刪除教师")
    public ModelAndView deleteTeacher(HttpServletRequest request, @PathVariable("id") Integer id) {
    	ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName("redirect:/admin/teacher/list");
            if (ObjectUtils.isNotNull(id)) {
                teacherService.deleteTeacherById(id);// 刪除讲师
                 /*清除前台首页教师缓存*/
                CacheUtil.remove(CacheConstans.INDEX_TEACHER_RECOMMEND);
            }
            Object uri = request.getSession().getAttribute("teacher_list");
            if(uri!=null){
            	model.setViewName("redirect:"+uri.toString());
            }
        } catch (Exception e) {
            logger.error("deleteTeacher()---error", e);
            model.setViewName(setExceptionRequest(request, e));
        }
        return model;
    }

    /**
     * 查询讲师列表
     */
    @RequestMapping("/teacher/selectlist/{type}")
    public ModelAndView queryselectTeacherList(HttpServletRequest request,@ModelAttribute("page") PageEntity page, @ModelAttribute("queryTeacher") QueryTeacher queryTeacher,@PathVariable("type") String type) {      
    	ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName(getViewPath("/admin/teacher/select_teacher_list"));// 选择讲师列表页面
            // 查詢讲师
            List<Teacher> teacherList = teacherService.queryTeacherListPage(queryTeacher, page);
            // 把返回的数据放到model中
            model.addObject("teacherList", teacherList);
            model.addObject("page", page);
            model.addObject("queryTeacher", queryTeacher);
            model.addObject("type", type);
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("AdminTeacherController.queryTeacherList", e);
        }
        return model;
    }
}