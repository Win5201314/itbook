package com.inxedu.os.edu.controller.course;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CourseStudyhistory Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseStudyhistoryController extends BaseController{
 	@Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
    private Logger logger = LoggerFactory.getLogger(AdminCourseStudyhistoryController.class);
	@InitBinder("courseStudyhistory")
	public void initBinderQueryCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("courseStudyhistory.");
	}

	@RequestMapping("/studyHistory/list")
	public ModelAndView studyHistoryList(HttpServletRequest request, @ModelAttribute("page")PageEntity page, @ModelAttribute("courseStudyhistory")CourseStudyhistory courseStudyhistory){
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/admin/course/studyHistory_list"));
			page.setPageSize(10);
			List<CourseStudyhistory> courseStudyhistories = courseStudyhistoryService.queryCourseStudyHistoryListPage(courseStudyhistory,page);
			model.addObject("courseStudyhistories",courseStudyhistories);
			model.addObject("courseStudyhistory",courseStudyhistory);

		}catch (Exception e){
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("AdminCourseStudyhistoryController.studyHistoryList", e);
		}
		return model;
	}
   
}