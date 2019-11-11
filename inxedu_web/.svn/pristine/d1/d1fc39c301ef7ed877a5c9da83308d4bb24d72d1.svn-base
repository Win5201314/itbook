package com.inxedu.os.edu.controller.course;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.CoursePackage;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.member.MemberSaleDTO;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.member.QueryMemberSale;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.website.WebsiteCourse;
import com.inxedu.os.edu.service.course.CoursePackageService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseTeacherService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.website.WebsiteCourseService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台课程管理
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);
	@Autowired
	private CourseService courseService;
	@Autowired
	private SubjectService subjectService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private WebsiteCourseService websiteCourseService;
	@Autowired
	private CoursePackageService coursePackageService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired(required=false)
	private MemberSaleService memberSaleService;
	@Autowired(required=false)
	private MemberTypeService memberTypeService;
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryCourse")
	public void initBinderQueryCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCourse.");
	}
	@InitBinder("course")
	public void initBinderCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("course.");
	}
	@InitBinder("queryMemberSale")
	public void initBinderQueryMemberSale(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryMemberSale.");
	}
	/**
	 * 课程列表展示
	 */
	@RequestMapping("/cou/list")
	public ModelAndView showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
		ModelAndView model = new ModelAndView();
		try {
			page.setPageSize(10);
			model.setViewName(getViewPath("/admin/course/course_list"));//课程列表

			//购买服务开关
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if(!"ON".equals(serviceSwitch.get("live"))){
				queryCourse.setSellTypeLive("true");
			}
			if(!"ON".equals(serviceSwitch.get("PackageSwitch"))){
				queryCourse.setSellTypePackage("true");
			}
			//查询课程
			List<CourseDto> courseList = courseService.queryCourseListPage(queryCourse, page);
			model.addObject("page", page);
			model.addObject("courseList", courseList);
			model.addObject("queryCourse", queryCourse);
			//查询专业
            QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
			request.getSession().setAttribute("courseListUri", WebUtils.getServletRequestUriParms(request));
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("CourseController.showCourseList", e);
		}
		return model;
	}
    /**
     * 到添加课程页面
     */
    @RequestMapping("/cou/toAddCourse")
    public ModelAndView toAddCourse(HttpServletRequest request) {
    	ModelAndView model = new ModelAndView();
        try {
        	model.setViewName(getViewPath("/admin/course/add_course"));//添加课程
            //查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
            model.addObject("sellType", request.getParameter("sellType"));
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("CourseController.toAddCourse", e);
        }
        return model;
    }
    /**
     * 添加课程
     * @param course 课程对象
     */
    @RequestMapping("/cou/addCourse")
	@SystemLog(type="add",operation="添加课程")
    public ModelAndView addCourse(HttpServletRequest request,@ModelAttribute("course") Course course){
    	ModelAndView model = new ModelAndView();
    	try{
    		course.setAddTime(new Date());
    		course.setUpdateTime(new Date());
			//WebUtils.zoomImage(course.getLogo(),640,357);
    		courseService.addCourse(course);
			if (course.getSellType().equals("PACKAGE")){
				model.setViewName("redirect:/admin/cou/packages/"+course.getCourseId());
			}else{
				model.setViewName("redirect:/admin/kpoint/list/"+course.getCourseId());
			}
            //添加课程与讲师的关联数据
			courseTeacherService.addCourseTeacher(request, course);
    	}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
		}
    	return model;
    }
    /**
     * 初始化修改页面
     * @param courseId 课程ID
     * @return ModelAndView
     */
    @RequestMapping("/cou/initUpdate/{courseId}")
    public ModelAndView initUpdatePage(HttpServletRequest request,@PathVariable("courseId") int courseId){
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName(getViewPath("/admin/course/update_course"));//更新课程
    		Course course = courseService.queryCourseById(courseId);
    		model.addObject("course", course);
    		 //查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
            
            //查询课程所属老师
            List<Map<String,Object>> teacherList = teacherService.queryCourseTeacerList(course.getCourseId());
            model.addObject("teacherList", FastJsonUtil.obj2JsonString(teacherList));
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("initUpdatePage()---error",e);
		}
    	return model;
    }
	/**
     * 修改课程
     * @param course 课程数据
     * @return ModelAndView
     */
    @RequestMapping("/cou/updateCourse")
	@SystemLog(type="update",operation="修改课程")
    public ModelAndView updateCourse(HttpServletRequest request,@ModelAttribute("course") Course course){
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName("redirect:/admin/cou/list?queryCourse.sellType="+course.getSellType());
    		course.setUpdateTime(new Date());
    		courseService.updateCourse(course);
    		Object uri = request.getSession().getAttribute("courseListUri");
    		if(uri!=null){
    			model.setViewName("redirect:"+uri.toString());
    		}
    		//修改课程与讲师的关联数
			courseTeacherService.addCourseTeacher(request, course);
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("updateCourse()--error",e);
		}
    	return model;
    }
    /**
     * 删除课程
     * @param courseId 课程ID
     * @param type 1正常 2删除
     */
    @RequestMapping("/cou/avaliable/{courseId}/{type}")
    @ResponseBody
	@SystemLog(type="del",operation="删除课程")
	public Map<String,Object> avaliable(@PathVariable("courseId") int courseId,@PathVariable("type") int type){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		courseService.updateAvaliableCourse(courseId,type);
    		json = this.setJson(true, null, null);
    	}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("avaliable()--error",e);
		}
    	return json;
    }
    /**
     * 推荐选择课程列表
     */
    @RequestMapping("/cou/showrecommendList")
    public ModelAndView showRecommendCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse){
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName(getViewPath("/admin/course/course_recommend_list"));//课程列表(推荐课程)
    		//查询课程
			List<CourseDto> courseList = courseService.queryCourseListPage(queryCourse, page);
			model.addObject("page", page);
			model.addObject("courseList", courseList);
			model.addObject("queryCourse", queryCourse);
			//查询专业
            QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
			
			//查询推荐分类
			List<WebsiteCourse> webstieList = websiteCourseService.queryWebsiteCourseList();
			model.addObject("webstieList", webstieList);
    	}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showRecommendCourseList()--error",e);
		}
    	return model;
    }
	/**
	 * 赠送用户课程列表
	 * **/
	@RequestMapping("/user/courseList/{userId}")
	public ModelAndView toCourseForClass(HttpServletRequest request,@ModelAttribute("queryCourse") QueryCourse queryCourse,@PathVariable("userId")Long userId ,@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getViewPath("/admin/course/course_giveuser_list"));//赠送课程页面
		QuerySubject querySubject = new QuerySubject();
		try {
			// 搜索课程列表
			List<CourseDto> courseList = courseService.queryCourseListPage(queryCourse, page);
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			modelAndView.addObject("courseList", courseList);
			modelAndView.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
			modelAndView.addObject("page", page);
			modelAndView.addObject("userId", userId);
		} catch (Exception e) {
			modelAndView.setViewName(this.setExceptionRequest(request, e));
			logger.error("toCourseForClass()--error",e);
		}
		return modelAndView;
	}
	/**
	 * 赠送用户课程列表
	 * **/
	@RequestMapping("/user/memberList/{userId}")
	public ModelAndView toMemberList(HttpServletRequest request,@ModelAttribute("queryMemberSale")QueryMemberSale queryMemberSale,@PathVariable("userId")Long userId ,@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getViewPath("/admin/member/member_giveuser_list"));//赠送会员页面
		try {
			/*查询所有会员类型*/
			List<MemberType> memberTypes = memberTypeService.getMemberTypes();
			modelAndView.addObject("memberTypes",memberTypes);
			page.setPageSize(10);
			/*查询会员商品*/
			List<MemberSaleDTO> memberSaleDTOs = memberSaleService.getMemberSalePage(queryMemberSale,page);
			modelAndView.addObject("memberSaleDTOs",memberSaleDTOs);
			modelAndView.addObject("userId",userId);
		} catch (Exception e) {
			modelAndView.setViewName(this.setExceptionRequest(request, e));
			logger.error("toCourseForClass()--error",e);
		}
		return modelAndView;
	}
	/**
	 * 套餐课程包列表
	 */
	@RequestMapping("/cou/packages/{courseId}")
	public String classCourseList(HttpServletRequest request,@PathVariable("courseId") int courseId, Course course) {
		try {
			course.setCourseId(courseId);
			List<CourseDto> courseDtoList = courseService.getPackageCourseListByCondition(course);
			// 获得一级项目
			QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			request.setAttribute("subjectList", FastJsonUtil.obj2JsonString(subjectList));
			request.setAttribute("courseDtoList", courseDtoList);
			request.setAttribute("course", course);
		} catch (Exception e) {
			logger.error("CourseController.packCourseList", e);
			return setExceptionRequest(request, e);
		}
		return getViewPath("/admin/course/course_package_list");//列表
	}
	/**
	 * 选择课程列表
	 */
	@RequestMapping("/cou/showCourseList")
	public ModelAndView showClassCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/course/course_package_select_list"));
			String type = request.getParameter("type");
			model.addObject("type", type);
			queryCourse.setCourseId(null);
			//查询课程
			queryCourse.setIsavaliable(1);
			/*如果是添加会员课程*/
			if ("member".equals(type)){
				queryCourse.setSellTypePackage("true");
			}
			if (!"member".equals(type)){
				queryCourse.setSellType("COURSE");//类型为课程
			}
			List<CourseDto> courseList = courseService.queryCourseListPage(queryCourse, page);
			model.addObject("page", page);
			model.addObject("courseList", courseList);
			model.addObject("queryCourse", queryCourse);
			//查询专业
			QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			model.addObject("subjectList", FastJsonUtil.obj2JsonString(subjectList));
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showClassCourseList()--error",e);
		}
		return model;
	}
	/**
	 * 套餐添加课程
	 */
	@RequestMapping("/cou/addCoursePackage")
	@ResponseBody
	@SystemLog(type="add",operation="套餐添加课程")
	public Map<String, Object> addCoursePackage(@ModelAttribute("ids") String ids,@ModelAttribute("courseId") Long courseId) {
		Map<String, Object> json = new HashMap<String, Object>(4);
		try {
			coursePackageService.addCoursePackageBatch(ids, courseId);
			json = setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("AdminCourseController.addCoursePackage", e);
			json = setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 修改套餐课程排序值
	 */
	@RequestMapping("/cou/updateCoursePackageOrderNum")
	@ResponseBody
	@SystemLog(type="update",operation="修改套餐课程排序值")
	public Map<String, Object> updateCoursePackageOrderNum(@RequestParam("courseId")long courseId, @RequestParam("orderNum")long orderNum){
		Map<String, Object> json = new HashMap<String, Object>(4);
		try {
			CoursePackage classCourse = new CoursePackage();
			classCourse.setCourseId(courseId);
			classCourse.setOrderNum(orderNum);
			coursePackageService.updateCoursePackageOrderNum(classCourse);
			json = setJson(true, "修改成功", null);
		} catch (Exception e) {
			logger.error("AdminCourseController.updateCoursePackageOrderNum", e);
			json = setJson(false, "系统异常", null);
		}
		return json;
	}
	/**
	 * 删除套餐添加的课程
	 */
	@RequestMapping("/cou/delCoursePacage/{courseId}/{mainCourseId}")
	@ResponseBody
	@SystemLog(type="del",operation="删除套餐添加的课程")
	public Map<String, Object> delCoursePacage(@PathVariable("courseId") Long courseId,@PathVariable("mainCourseId") Long mainCourseId) {
		Map<String, Object> json = new HashMap<String, Object>(4);
		try {
			CoursePackage classCourse = new CoursePackage();
			classCourse.setCourseId(courseId);
			classCourse.setMainCourseId(mainCourseId);
			coursePackageService.delCoursePackage(classCourse);
			json = setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("AdminCourseController.delCoursePacage", e);
			json = setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 批量赠送课程
 	 */
	@RequestMapping("/cou/toGiveCourses")
	public String toGiveCourses() {
		return getViewPath("/admin/course/giveCourses");// 批量赠送课程界面
	}
	/**
	 * 批量赠送课程
	 */
	@RequestMapping("/cou/giveCoursesExcel")
	public ModelAndView importProcess(HttpServletRequest request, @RequestParam("myFile") MultipartFile myfile, @RequestParam("mark") Integer mark) {
		ModelAndView modelandView = new ModelAndView();
		try {
			logger.info("myFile:" + myfile.getName());
			String msg = courseService.updateGiveCoursesExcelExcel(request,myfile,mark);
			request.setAttribute("msg", msg);
			if(msg==null||msg.equals("")){
				modelandView.setViewName("/common/success");
			}else{
				modelandView.setViewName("/common/msg_error");
			}
		} catch (BaseException e) {
			logger.error("AdminCourseController.importProcess", e);
			request.setAttribute("msg", e.getMessage());
			return new ModelAndView("/common/msg_error");
		} catch (Exception e) {
			logger.error("AdminCourseController.importProcess", e);
			request.setAttribute("msg", e.getMessage());
			return new ModelAndView("/common/msg_error");
		}
		return modelandView;
	}
}