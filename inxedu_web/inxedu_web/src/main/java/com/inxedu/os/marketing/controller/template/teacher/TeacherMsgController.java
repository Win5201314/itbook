package com.inxedu.os.marketing.controller.template.teacher;

import com.inxedu.os.app.controller.article.AppArticleController;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.teacher.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/23.
 */
@Controller
@RequestMapping("/webapp")
public class TeacherMsgController extends BaseController {
    private static Logger logger=Logger.getLogger(TeacherMsgController.class);

    @Autowired
    private TeacherService teacherService;

   /* 返回首页的师资数据*/
    @RequestMapping("/teacherList")
    @ResponseBody
    public Map<String, Object> getTeacherList(HttpServletRequest request, @ModelAttribute("page") PageEntity page){
        Map<String, Object> json=new HashMap<String, Object>();
        try {
            page.setCurrentPage(1);
            page.setPageSize(4);
            QueryTeacher queryTeacher = new QueryTeacher();
            List<Teacher> teachers = new ArrayList<Teacher>();
            teachers = teacherService.queryTeacherListPage(queryTeacher,page);
            json = this.setJson(true,"",teachers);
        }catch (Exception e){
            json = this.setJson(false, "异常", null);
            logger.error("getTeacherList()--error",e);
        }
        return json;
    }
    /* 返回名师页的师资团队数据*/
    @RequestMapping("/teacherTeamList")
    @ResponseBody
    public Map<String, Object> teacherTeamList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,int num){
        Map<String, Object> json=new HashMap<String, Object>();
        try {
            page.setCurrentPage(1);
            page.setPageSize(num);
            QueryTeacher queryTeacher = new QueryTeacher();
            List<Teacher> teachers = new ArrayList<Teacher>();
            teachers = teacherService.queryTeacherListPage(queryTeacher,page);
            json = this.setJson(true,"",teachers);
        }catch (Exception e){
            json = this.setJson(false, "异常", null);
            logger.error("teacherTeamList()--error",e);
        }
        return json;
    }
}
