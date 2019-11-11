package com.inxedu.os.edu.controller.member;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.member.CourseMember;
import com.inxedu.os.edu.entity.member.CourseMemberDTO;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.member.CourseMemberService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.subject.SubjectService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * MemberType管理接口 User: qinggang.liu Date: 2014-09-26
 */
@Controller
@RequestMapping("/admin")
public class AdminMemberTypeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminMemberTypeController.class);
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private CourseMemberService courseMemberService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private MemberSaleService memberSaleService;

    private static final String getMemberTypes = getViewPath("/admin/member/member_type_list");
    private static final String updateMemberType = getViewPath("/admin/member/member_type_update");
    private static final String addMemberType = getViewPath("/admin/member/member_type_add");
    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("memberType")
    public void initBinderMemberType(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberType.");
    }
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }
    /**
     * 会员类型列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/membertype/list")
    public ModelAndView getMemberTypes(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getMemberTypes);
        try {
            List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
            modelAndView.addObject("memberTypeList", memberTypeList);
        } catch (Exception e) {
            logger.error("AdminMemberController.getMemberTypes--会员类型列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 跳转更新会员类型
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/membertype/doupdate/{id}")
    public ModelAndView doUpdateMemberType(HttpServletRequest request, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(updateMemberType);
        try {
            MemberType memberType = memberTypeService.getMemberTypeById(id);
            modelAndView.addObject("memberType", memberType);
        } catch (Exception e) {
            logger.error("AdminMemberController.doUpdateMemberType--跳转更新会员类型出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 更新会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/update")
    public String updateMemberType(HttpServletRequest request, MemberType memberType) {
        try {
            memberTypeService.updateMemberType(memberType);
        } catch (Exception e) {
            logger.error("AdminMemberController.updateMemberType--更新会员类型出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/membertype/list";
    }
    /**
     * 跳转添加会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/doadd")
    public String doaddMemberType(HttpServletRequest request) {
        return addMemberType;
    }
    /**
     * 添加会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/add")
    public String addMemberType(HttpServletRequest request, MemberType memberType) {
        try {
            memberTypeService.addMemberType(memberType);
        } catch (Exception e) {
            logger.error("AdminMemberController.addMemberType--添加会员类型出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/membertype/list";
    }

    /**
     * 停用启用会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/status")
    @ResponseBody
    public Map<String, Object> updateMemberTypeStatus(HttpServletRequest request, MemberType memberType) {
        Map<String, Object> json = null;
        try {
            memberTypeService.updateMemberTypeStatus(memberType);
            json = this.setJson(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminMemberController.addMemberType--更改会员类型状态出错", e);
            json = this.setJson(false, "error", null);
        }
        return json;
    }
    /**
     * 添加会员课程
     *
     * @return
     */
    @RequestMapping("/membertype/addMemberCourse")
    @ResponseBody
    public Map<String ,Object>  addMemberCourse(HttpServletRequest request) {
        Map<String,Object> json = new HashedMap();
        try {
            /*课程id*/
            String courseIds = request.getParameter("ids");
            String ids[] = courseIds.split(",");
            /*会员类型id*/
            String memberTypeId = request.getParameter("memberTypeId");
            List<CourseMember> courseMemberList = new ArrayList<>();
            for(int i=0;i<ids.length;i++){

               /* 去重*/
                CourseMemberDTO courseMemberDTO = new CourseMemberDTO();
                courseMemberDTO.setCourseId(Long.parseLong(ids[i]));
                courseMemberDTO.setMemberTypeId(Long.parseLong(memberTypeId));
                if (ObjectUtils.isNull(courseMemberService.queryCourseMemberList(courseMemberDTO))){
                     /*课程id和对应的会员类型id*/
                    CourseMember courseMember = new CourseMember();
                    courseMember.setCourseId(Long.parseLong(ids[i]));
                    courseMember.setMemberTypeId(Long.parseLong(memberTypeId));
                    courseMemberList.add(courseMember);
                }
            }
            if (ObjectUtils.isNotNull(courseMemberList)){
                courseMemberService.addCourseMember(courseMemberList);
            }
            json = this.setJson(true,"",null);
        } catch (Exception e) {
            logger.error("AdminMemberTypeController.addMemberCourse", e);
            json = setJson(false, "false", null);
        }
        return json;
    }
    /*查询会员课程*/
    @RequestMapping("/membertype/memberCourse/{memberTypeId}")
    public String  memberCourse(HttpServletRequest request,@PathVariable Long memberTypeId,@ModelAttribute("page") PageEntity page,
                                @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            page.setPageSize(10);
            queryCourse.setMemberTypeId(memberTypeId);
            List<CourseDto> courseDtoList = courseService.queryMemberCourseListPage(queryCourse,page);
            //查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", FastJsonUtil.obj2JsonString(subjectList));
            request.setAttribute("courseDtoList",courseDtoList);
            request.setAttribute("memberTypeId",memberTypeId);
            request.setAttribute("page",page);
            request.setAttribute("queryCourse",queryCourse);
        } catch (Exception e) {
            logger.error("AdminMemberController.addMemberType--添加会员类型出错", e);
            return setExceptionRequest(request, e);
        }
        return getViewPath("/admin/member/member_course_list");
    }
    /*查询会员课程*/
    @RequestMapping("/deleteCourse/ajax/{courseId}/{memberTypeId}")
    @ResponseBody
    public Map<String,Object>  deleteCourse(HttpServletRequest request,@PathVariable("courseId")Long courseId,@PathVariable("memberTypeId")Long memberTypeId) {
        Map<String,Object> json = new HashedMap();
        try {
            CourseMemberDTO courseMemberDTO = new CourseMemberDTO();
            courseMemberDTO.setCourseId(courseId);
            courseMemberDTO.setMemberTypeId(memberTypeId);
            courseMemberService.delCourseMember(courseMemberDTO);
            json = this.setJson(true,"",null);
        } catch (Exception e) {
            logger.error("AdminMemberTypeController.deleteCourse", e);
            json = setJson(false, "false", null);
        }
        return json;
    }

    /**
     * 删除会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/delete/{id}")
    @ResponseBody
    public Map<String, Object> delMemberType(HttpServletRequest request, @PathVariable("id")Long id) {
        Map<String, Object> json = null;
        try {
            memberTypeService.delMemberType(id);
            //删除会员商品
            memberSaleService.delMemberSaleByType(id);
            json = this.setJson(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminMemberController.delMemberType--删除会员类型出错", e);
            json = this.setJson(false, "error", null);
        }
        return json;
    }
}