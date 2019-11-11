package com.inxedu.os.edu.controller.user;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.*;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import com.inxedu.os.edu.entity.letter.MsgReceive;
import com.inxedu.os.edu.entity.letter.QueryMsgReceive;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.course.*;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.sensitivewords.SensitiveWordsService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.sensitiveword.SensitivewordFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 个人中心  Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/uc")
public class UserController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
    @Autowired
    private CoursePackageService coursePackageService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CourseFavoritesService courseFavoritesService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SensitiveWordsService sensitiveWordsService;


	@InitBinder({"user"})
	public void initBinderUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("user.");
	}
	
	@InitBinder("msgReceive")
	public void initBinderMsgReceive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("msgReceive.");
	}
	
	/**
	 * 删除收藏
	 */
	@RequestMapping("/deleteFaveorite/{courseIds}")
	@ResponseBody
	public Object deleteFavorite(HttpServletRequest request,@PathVariable("courseIds") String courseIds){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			CourseFavorites courseFavorites=new CourseFavorites();
			courseFavorites.setCourseIdStr(courseIds);
			courseFavorites.setUserId(SingletonLoginUtils.getLoginUserId(request));
			courseFavoritesService.deleteCourseFavorites(courseFavorites);
			map = this.setJson(true,"删除成功",null);
		}catch (Exception e) {
			map = setAjaxException(map);
			logger.error("deleteFavorite()---error",e);
		}
		return map;
	}
	
	/**
	 * 我的收藏列表
	 */
	@RequestMapping("/myFavorites")
	public ModelAndView myFavorites(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/favourite_course_list"));
		try{
			page.setPageSize(5);
			List<FavouriteCourseDTO> favoriteList = courseFavoritesService.queryFavoritesPage(SingletonLoginUtils.getLoginUserId(request), page);
			model.addObject("favoriteList", favoriteList);
			model.addObject("page", page);
			request.getSession().setAttribute("favoritesListUri", WebUtils.getServletRequestUriParms(request));
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("myFavorites()---error",e);
		}
		return model;
	}
	

	/**
	 * 进入播放页面
	 */
	@RequestMapping("/play/{courseId}/{kpointId}")
	public ModelAndView playVideo(HttpServletRequest request,@PathVariable("courseId") int courseId,@PathVariable("kpointId")int kpointId){
		ModelAndView model = new ModelAndView();
		model.addObject("playKpointId", kpointId);

		try{
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.WebSwitch.toString());
			request.setAttribute("discussSwitch",map);
			model.setViewName(getViewPath("/web/ucenter/player-video"));
			//获取课程
			Course course = courseService.queryCourseById(courseId);
			if(course!=null){
				model.addObject("course", course);
				int userId = SingletonLoginUtils.getLoginUserId(request);
				//查询是否已经收藏
				boolean isFavorites = courseFavoritesService.checkFavorites(userId, courseId);
				model.addObject("isFavorites", isFavorites);
	    		
				boolean isok= orderService.checkUserCursePay(userId, course.getCourseId());
				if(isok){
	            	//相关课程
	            	/*List<CourseDto> courseList = courseService.queryInterfixCourseLis(course.getSubjectId(), 5,course.getCourseId(),course.getSellType());
	            	for(CourseDto tempCoursedto : courseList){
	            		List<Map<String,Object>> teacherList=teacherService.queryCourseTeacerList(tempCoursedto.getCourseId());
	            		tempCoursedto.setTeacherListMap(teacherList);
	            	}
	            	model.addObject("courseList", courseList);*/
	            	
	            	
	            	CourseStudyhistory courseStudyhistory=new CourseStudyhistory();
	            	courseStudyhistory.setUserId(Long.valueOf(userId));
	            	courseStudyhistory.setCourseId(Long.valueOf(String.valueOf(courseId)));
	            	//我的课程学习记录
	            	List<CourseStudyhistory>  couStudyhistorysLearned=courseStudyhistoryService.getCourseStudyhistoryList(courseStudyhistory);
	            	int courseHistorySize=0;
	            	if (couStudyhistorysLearned!=null&&couStudyhistorysLearned.size()>0) {
	            		courseHistorySize=couStudyhistorysLearned.size();
					}
	            	//二级视频节点的 总数
					int sonKpointCount=courseKpointService.getSecondLevelKpointCount(Long.valueOf(courseId));
	            	NumberFormat numberFormat = NumberFormat.getInstance();  
	            	//我的学习进度百分比
	                // 设置精确到小数点后0位  
	                numberFormat.setMaximumFractionDigits(0);  
	                String studyPercent = numberFormat.format((float) courseHistorySize / (float) sonKpointCount * 100);  
	                if(sonKpointCount==0){
						studyPercent="0";
					}
	                course.setStudyPercent(studyPercent);
				}else{
					model.addObject("message", "您未购买【"+course.getCourseName()+"】课程，播放失败！");
				}
				model.addObject("isok", isok);
				//手机端 图片集、pdf 返回标识
				model.addObject("goBack", request.getParameter("goBack"));
			}

			//查询目录
			List<CourseKpoint> parentKpointList = new ArrayList<CourseKpoint>();
			List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
			if(kpointList!=null && kpointList.size()>0){
				for(CourseKpoint temp:kpointList){
					if (temp.getParentId()==0) {
						parentKpointList.add(temp);
					}
				}
				int freeVideoId=0;
				for(CourseKpoint tempParent:parentKpointList){
					for(CourseKpoint temp:kpointList){
						if (temp.getParentId()==tempParent.getKpointId()) {
							tempParent.getKpointList().add(temp);
						}
						//获取一个可以试听的视频id
						if (freeVideoId==0&&temp.getFree()==1&&temp.getKpointType()==1) {
							freeVideoId=temp.getKpointId();
							model.addObject("freeVideoId",freeVideoId);
						}
						/*根据课程id和用户id查询学习记录*/
						CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
						courseStudyhistory.setCourseId(new Long(courseId));
						courseStudyhistory.setUserId(new Long(SingletonLoginUtils.getLoginUserId(request)));
						List<CourseStudyhistory> courseStudyhistories = courseStudyhistoryService.getCourseStudyhistoryList(courseStudyhistory);
						/*给节点set是否学习*/
						temp.setIsStudy("false");
						/*如果有该章节的学习记录则为已学习*/
						for (CourseStudyhistory courseStudyhistory1:courseStudyhistories){
							if (courseStudyhistory1.getKpointId()== temp.getKpointId()){
								temp.setIsStudy("true");
							}
						}
					}
				}
				model.addObject("parentKpointList", parentKpointList);
			}
			model.addObject("judgeIsMoblie",SingletonLoginUtils.JudgeIsMoblie(request));
			/*查询课程最近被观看到章节*/
			CourseStudyhistory lastTimeStudyhistory = courseService.getCourseStudyhistory(request,new Long(courseId));
			model.addObject("lastTimeStudyhistory",lastTimeStudyhistory);
			/*是否是从课程笔记进入播放大厅*/
            String isCourseNote  = request.getParameter("isCourseNote");
			model.addObject("isCourseNote",isCourseNote);
			model.addObject("playFromType", 2);
			model.addObject("userId",SingletonLoginUtils.getLoginUserId(request));
			/*查询课程笔记*/
			/*CourseNote courseNote = new CourseNote();
			courseNote = courseNoteService.getCourseNoteByKpointIdAndUserId(new Long(kpointId),new Long(SingletonLoginUtils.getLoginUserId(request)));
			model.addObject("courseNote", courseNote);*/

		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("playVideo()--error",e);
		}
		return model;
	}
	
	/**
	 * 进入个人中心
	 */
	@RequestMapping("/index")
	public ModelAndView ucIndex(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/uc_index"));
		try{
			String type = request.getParameter("sellType");
			String ifOver = request.getParameter("ifOver");
			model.addObject("type",type);
			model.addObject("ifOver",ifOver);
			page.setPageSize(7);
			//获取登录用户ID
			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(SingletonLoginUtils.getLoginUserId(request));

			if (StringUtils.isNotEmpty(type)){
				queryCourse.setSellType(type);
			}
			queryCourse.setIsoverdue("false");//默认查询没有过期的
			if ("true".equals(ifOver)){
				queryCourse.setIsoverdue("true");
			}
            List<CourseDto> courseList;
            if (!"PACKAGE".equals(type)&&StringUtils.isEmpty(ifOver)){
            	/*如果不是套餐和过期课程查询我的课程 */
                courseList = courseService.querySelfCourse(queryCourse,page);
                courseList = WebUtils.pageList(courseList,page);
            }else {
                //如果是套餐  或 过期课程
                courseList = courseService.queryMyCourseList(queryCourse,page);
				/*若果是套餐课set子课list*/
				if ("PACKAGE".equals(type)&&(courseList!=null&&courseList.size()>0)){
					for (Course course:courseList){
						List<Long> cId = new ArrayList<>();
						cId.add(new Long(course.getCourseId()));
						course.setCourseList(courseService.getCourseListPackage(cId));
					}
				}
            }
            /*课程学习进度*/
			setStudyHistory(request,courseList);

			/*查询课程最近的一次 学习记录*/
			if (courseList!=null &&courseList.size()>0){
                for (Course course:courseList){
					course.setCourseStudyhistory(courseService.getCourseStudyhistory(request,new Long(course.getCourseId())));
                }
            }
            /*查询用户的最近一条学习记录*/
            CourseStudyhistory courseStudyhistory = courseStudyhistoryService.getRecentStudyhistoryByUserId(new Long(SingletonLoginUtils.getLoginUserId(request)));
			if (ObjectUtils.isNotNull(courseStudyhistory)){
				/*查询最近学习的课程信息*/
				Course recentStudyCourse = courseService.queryCourseById(courseStudyhistory.getCourseId().intValue());
				recentStudyCourse.setCourseStudyhistory(courseStudyhistory);
				/*set学习进度*/
				setStudyHistoryByCourse(request,recentStudyCourse);
				model.addObject("recentStudyCourse",recentStudyCourse);

				CourseKpoint courseKpoint=courseKpointService.queryCourseKpointById(Integer.parseInt(courseStudyhistory.getKpointId() + ""));
				model.addObject("courseKpoint",courseKpoint);
			}
			model.addObject("queryCourse",queryCourse);
			model.addObject("courseList", courseList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("ucIndex()--error",e);
		}
		return model;
	}
	/*设置学习进度*/
	public void setStudyHistory(HttpServletRequest request,List<CourseDto> courseList){
		if(courseList!=null&&courseList.size()>0){
			//根据 已学课程章节数  和 课程下所有的视频章节 计算比例
			for(Course course:courseList){
				setStudyHistoryByCourse(request,course);
			}
		}
	}
	/*给某个课程set学习进度*/
	public void setStudyHistoryByCourse(HttpServletRequest request,Course course){
		//课程视频节点的 总数
		int sonKpointCount = courseKpointService.getSecondLevelKpointCount(Long.valueOf(course.getCourseId()));
		NumberFormat numberFormat = NumberFormat.getInstance();
		if (!"LIVE".equals(course.getSellType())) {
			CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
			courseStudyhistory.setUserId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));
			courseStudyhistory.setCourseId(Long.valueOf(String.valueOf(course.getCourseId())));
			//我的课程学习记录
			List<CourseStudyhistory> couStudyhistorysLearned = courseStudyhistoryService.getCourseStudyhistoryList(courseStudyhistory);
			int courseHistorySize = 0;
			if (couStudyhistorysLearned != null && couStudyhistorysLearned.size() > 0) {
				courseHistorySize = couStudyhistorysLearned.size();
			}
			//我的学习进度百分比
			//设置精确到小数点后0位
			numberFormat.setMaximumFractionDigits(0);
			String studyPercent = numberFormat.format((float) courseHistorySize / (float) sonKpointCount * 100);
			if (sonKpointCount == 0) {
				studyPercent = "0";
			}
			course.setStudyPercent(studyPercent);
		}
				/*如果是直播查询 未开始的（开始了默认学过）节点数量*/
		if ("LIVE".equals(course.getSellType())){
			CourseKpoint courseKpoint = new CourseKpoint();
			courseKpoint.setCourseId(course.getCourseId());
			courseKpoint.setLiveBeginTime(new Date());
			List<CourseKpointDto> courseKpointList = courseKpointService.queryCourseNearestKpointList(courseKpoint);
					/*未开始的直播节点数量*/
			int overSize = courseKpointList.size();
						/*直播set直播进度*/
			course.setStudyPercent(numberFormat.format((float)(sonKpointCount-overSize)/(float)sonKpointCount *100));
			if (sonKpointCount == 0) {
				course.setStudyPercent("0");
			}
		}

	}

	/**
	 * 进入个人中心课程详情
	 */
	@RequestMapping("/courseInfo/{courseId}")
	public ModelAndView courseInfo(HttpServletRequest request,@PathVariable("courseId") int courseId){
		ModelAndView model = new ModelAndView();
		try{

			/*课程详情页面*/
			model.setViewName(getViewPath("/web/ucenter/uc_course"));
			Course course =courseService.queryCourseById(courseId);
			//获取登录用户ID
			int userId = SingletonLoginUtils.getLoginUserId(request);
			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(userId);
			queryCourse.setSellType(course.getSellType());
			//queryCourse.setIsoverdue("false");
			//查询我的课程
			List<CourseDto> courseList = courseService.getMyCourseList(queryCourse);
			//往查出的课程list放入学习记录信息
			courseService.getCoursePutStudyhistory(courseList,userId);

			for (Course course1:courseList){
				if (course1.getCourseId()==courseId){
				/*给课程set学习记录*/
					course = course1;
				}
			}
			if (StringUtils.isEmpty(course.getStudyPercent())){
				course.setStudyPercent("0");
			}


			/*如果是直播进入直播详情页面*/
			if ("LIVE".equals(course.getSellType())){

				model.setViewName(getViewPath("/web/ucenter/uc_live"));
			}
			if("COURSE".equals(course.getSellType())&&course.getEndTime()!=null){
				if (course.getEndTime().before(new Date())){
					model.setViewName("redirect:/front/couinfo/"+courseId);
					return model;
				}
				/*把结束时间和当前时间比较获得结束天数*/
				Long endTime = course.getEndTime().getTime();
				Long nowTime = System.currentTimeMillis();
				Long  day = (endTime - nowTime)/1000/60/60/24;
				course.setLoseTime(day.intValue()+1+"");
			}else if ("COURSE".equals(course.getSellType())&&course.getEndTime()==null&&course.getCurrentPrice().compareTo(new BigDecimal(0))!=0){
                /*如果课程没有到期时间为套餐下的课程*/
                CoursePackage coursePackage = new CoursePackage();
               /* 根据课程id取所有套餐*/
                coursePackage.setCourseId(new Long(course.getCourseId()));
                List<CoursePackage> coursePackageList =  coursePackageService.queryCoursePackageList(coursePackage);
				if (getEndDate(coursePackageList.get(0).getMainCourseId(),request).before(new Date())){
					model.setViewName("redirect:/front/couinfo/"+courseId);
					return model;
				}
                Date maxEndTime = null;
                /*如果套餐集合不为空*/
                if (ObjectUtils.isNotNull(coursePackageList)){
                    /*初始最大到期时间为第一个套餐的到期时间*/
                    maxEndTime  = getEndDate(coursePackageList.get(0).getMainCourseId(),request);
                   /* 循环遍历套餐*/
                    for (CoursePackage coursePackage1:coursePackageList){
                        /*如果套餐的到期时间大于maxEndTime*/
                        Date endDate = getEndDate(coursePackage1.getMainCourseId(),request);
                        if (ObjectUtils.isNotNull(endDate)&&endDate.after(maxEndTime)){
                            /*更新到期时间*/
                            maxEndTime = getEndDate(coursePackage1.getMainCourseId(),request);
                        }
                    }
                }
                /*把结束时间和当前时间比较获得结束天数*/
                Long endTime = maxEndTime.getTime();
                Long nowTime = System.currentTimeMillis();
                Long  day = (endTime - nowTime)/1000/60/60/24;
                course.setLoseTime(day.intValue()+1+"");

            }else if (course.getCurrentPrice().compareTo(new BigDecimal(0))==0){
            	if(course.getLoseType()==1){
					course.setLoseTime(course.getLoseTime());
				}else {
					DateFormat formatExamTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long endTime = formatExamTime.parse(course.getLoseTime()).getTime();
					Long nowTime = System.currentTimeMillis();
					Long  day = (endTime - nowTime)/1000/60/60/24;
					course.setLoseTime(course.getLoseTime());
				}
			}
			model.addObject("courseList", courseList);
			//查询课程老师
			List<Map<String,Object>> teacherList = teacherService.queryCourseTeacerList(courseId);
			model.addObject("teacherList", teacherList);
			model.addObject("course",course);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("ucIndex()--error",e);
		}
		return model;
	}
	/*获取课程的到期时间*/
	public Date getEndDate(long courseId,HttpServletRequest request){
        QueryCourse packageCourse = new QueryCourse();
        packageCourse.setCourseId(courseId);
        packageCourse.setUserId(SingletonLoginUtils.getLoginUserId(request));
        CourseDto courseDto = courseService.getMyCourseByCourseId(packageCourse);
        Date endTime = null;
		if (ObjectUtils.isNotNull(courseDto)){
			endTime = courseDto.getEndTime();
		}
        return endTime;
    }
	/**
     * 免费课程列表
     */
    @RequestMapping("/freeCourseList")
    public ModelAndView showFreeCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView model = new ModelAndView();
        try {
        	model.setViewName(getViewPath("/web/ucenter/uc_freecourse"));
            // 页面传来的数据放到page中
        	page.setPageSize(9);
			QueryCourse queryCourse=new QueryCourse();
			queryCourse.setSellType("COURSE");//直播类型
            //只查询上架的
            queryCourse.setIsavaliable(1);
            queryCourse.setIsFree("true");//查询免费的课程
			queryCourse.setIsoverdue("false");//查询是否过期
            // 搜索课程列表
            List<CourseDto> courseList = courseService.queryWebCourseListPage(queryCourse, page);
			int userId = SingletonLoginUtils.getLoginUserId(request);
			//往查出的课程list放入学习记录信息
			courseService.getCoursePutStudyhistory(courseList,userId);
            model.addObject("courseList", courseList);
            model.addObject("page",page);
            model.addObject("sellType",queryCourse.getSellType());
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("showFreeCourseList()--error", e);
        }
        return model;
    }

	/**
	 * 我的过期课程
	 */
	@RequestMapping("/overdueCourse")
	public ModelAndView overdueCourse(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			page.setPageSize(9);
			model.setViewName(getViewPath("/web/ucenter/uc_overdueCourse"));
			//获取登录用户ID
			int userId = SingletonLoginUtils.getLoginUserId(request);
			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(userId);
			queryCourse.setSellType("COURSE");
			queryCourse.setIsoverdue("true");
			//查询我的课程
			List<CourseDto> courseList = courseService.queryMyCourseList(queryCourse,page);
			//往查出的课程list放入学习记录信息
			courseService.getCoursePutStudyhistory(courseList,userId);
			model.addObject("courseList", courseList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("ucIndex()--error",e);
		}
		return model;
	}

	/**
	 * 个人中心我的套餐
	 */
	@RequestMapping("/coursePackage")
	public ModelAndView ucCoursePackage(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			page.setPageSize(4);
			model.setViewName(getViewPath("/web/ucenter/uc_coursePackage"));
			//获取登录用户ID
			int userId = SingletonLoginUtils.getLoginUserId(request);

			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(userId);
			queryCourse.setSellType("PACKAGE");
			queryCourse.setIsoverdue("false");
			//查询我的
			List<CourseDto> courseList = courseService.queryMyCourseList(queryCourse,page);
			model.addObject("courseList", courseList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("ucIndex()--error",e);
		}
		return model;
	}

	/**
	 * 个人中心我的套餐过期
	 */
	@RequestMapping("/coursePackageOverdue")
	public ModelAndView ucCoursePackageOverdue(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			page.setPageSize(4);
			model.setViewName(getViewPath("/web/ucenter/uc_coursePackageOverdue"));
			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(SingletonLoginUtils.getLoginUserId(request));
			queryCourse.setSellType("PACKAGE");
			queryCourse.setIsoverdue("true");
			//查询我的
			List<CourseDto> courseList = courseService.queryMyCourseList(queryCourse,page);
			model.addObject("courseList", courseList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("ucIndex()--error",e);
		}
		return model;
	}

	/**
	 * 查询套餐课程目录
	 */
	@RequestMapping("/ajax/coursePackageList/{courseId}")
	public ModelAndView coursePackageList(HttpServletRequest request,@PathVariable("courseId") int courseId){
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/web/ucenter/course-package-list"));
			List<Long> ids = new ArrayList<Long>();
			ids.add(Long.valueOf(courseId));
			List<CourseDto> courseDtoList=courseService.getCourseListPackage(ids);
			/*查询学习进度*/
			setStudyHistory(request,courseDtoList);
			/*给课程set学习记录*/
			if (courseDtoList!=null &&courseDtoList.size()>0){
				for (Course course:courseDtoList){
					course.setCourseStudyhistory(courseService.getCourseStudyhistory(request,new Long(course.getCourseId())));
				}
			}
			model.addObject("courseDtoList", courseDtoList);
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("courseKpointList()----error", e);
		}
		return model;
	}
	/**
	 * 修改用户头像
	 */
	@RequestMapping("/updateImg")
	@ResponseBody
	public Map<String,Object> updatePicImg(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(user.getUserId()>0){
				userService.updateImg(user);
				//缓存用户
				userService.setLoginInfo(request,user.getUserId(),"false");
				json = this.setJson(true, null, null);
			}else{
				json = this.setJson(true, "头像修改失败", null);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updatePicImg()",e);
		}
		return json;
	}
	/**
	 * 修改用户密码
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Map<String,Object> updatePwd(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			user = userService.queryUserById(user.getUserId());
			if(user!=null){
				//原密码
				String nowPassword = request.getParameter("nowPassword")==null?"":request.getParameter("nowPassword");
				//新密码
				String newPassword = request.getParameter("newPassword")==null?"":request.getParameter("newPassword");
				//确认密码
				String confirmPwd = request.getParameter("confirmPwd")==null?"":request.getParameter("confirmPwd");
				if(nowPassword.equals("")|| nowPassword.trim().length()==0){
					json = this.setJson(false, "请输入旧密码！", 1);
					return json;
				}
				if(!user.getPassword().equals(MD5.getMD5(nowPassword))){
					json = this.setJson(false, "旧密码不正确！", 1);
					return json;
				}
				if(newPassword.equals("") || newPassword.trim().length()==0){
					json = this.setJson(false, "请输入新密码！", 2);
					return json;
				}
				if(!WebUtils.isPasswordAvailable(newPassword)){
					json = this.setJson(false, "密码只能是数字字母组合且大于等于6位小于等于16位", 2);
					return json;
				}
				if(!newPassword.equals(confirmPwd)){
					json = this.setJson(false, "新密码和确认密码不一致！", 2);
					return json;
				}
				user.setPassword(MD5.getMD5(newPassword));
				userService.updateUserPwd(user);
				json = this.setJson(true, "修改成功", null);
				return json;
			}
			json = this.setJson(false, "修改失败", null);
		}catch (Exception e) {
			json=this.setAjaxException(json);
			logger.error("updatePwd()--error",e);
		}
		return json;
	}
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public Map<String,Object> updateUserInfo(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			//验证邮箱和手机号格式
			if(user.getEmail()==null || user.getEmail().trim().length()==0 || !WebUtils.checkEmail(user.getEmail(), 50)){
				json = setJson(false, "请输入正确的邮箱号", null);
				return json;
			}
			if(user.getMobile()==null || user.getMobile().trim().length()==0 || !WebUtils.checkMobile(user.getMobile())){
				json = setJson(false, "请输入正确的手机号", null);
				return json;
			}
			User updUser = userService.queryUserById(user.getUserId());
			if(StringUtils.isEmpty(updUser.getUserName()) && !user.getUserName().equals(updUser.getUserName())){
				if(userService.checkUsername(user.getUserName().trim())){
					json = this.setJson(false, "该账户已被使用", null);
					return json;
				}else if(!WebUtils.checkUsername(user.getUserName())){
					json = setJson(false, "请输入正确的账户", null);
					return json;
				}else if(new SensitivewordFilter(sensitiveWordsService).CheckSensitiveWord(user.getUserName(),0,1)>0){
					json = setJson(false, "账户含有敏感词，请重新输入", null);
					return json;
				}
				else{
					updUser.setUserName(WebUtils.replaceTagSCRIPT(user.getUserName()));
				}
			}
			if(StringUtils.isEmpty(updUser.getEmail()) && !user.getEmail().equals(updUser.getEmail())){
				if(userService.checkEmail(user.getEmail().trim())){
					json = this.setJson(false, "该邮箱已被使用", null);
					return json;
				}else{
					updUser.setEmail(user.getEmail());
				}
			}
			if(StringUtils.isEmpty(updUser.getMobile()) && !user.getMobile().equals(updUser.getMobile())){
				if(userService.checkMobile(user.getMobile().trim())){
					json = this.setJson(false, "该手机号已被使用", null);
					return json;
				}else{
					updUser.setMobile(user.getMobile());
				}
			}
			if(new SensitivewordFilter(sensitiveWordsService).CheckSensitiveWord(user.getShowName(),0,1)>0){
				json = setJson(false, "昵称含有敏感词，请重新输入", null);
				return json;
			}

			updUser.setUserName(WebUtils.replaceTagSCRIPT(user.getUserName()));//姓名
			updUser.setShowName(WebUtils.replaceTagSCRIPT(user.getShowName()));//昵称
			updUser.setSex(user.getSex());//性别
			updUser.setAge(user.getAge());//年龄
			updUser.setPicImg(user.getPicImg());//头像
			userService.updateUser(updUser);//修改基本信息
			json = this.setJson(true, "修改成功", updUser);
			//缓存用户
			userService.setLoginInfo(request,updUser.getUserId(),"false");
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateUserInfo()---error",e);
		}
		return json;
	}
	
	/**
	 * 初始化修改用户信息页面
	 */
	@RequestMapping("/initUpdateUser/{index}")
	public ModelAndView initUpdateUser(HttpServletRequest request,@PathVariable("index") int index){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/user-info"));
		try{
			User user = userService.queryUserById(SingletonLoginUtils.getLoginUserId(request));
			model.addObject("user", user);
			model.addObject("index",index);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("initUpdateUser()---error",e);
		}
		return model;
	}
	/**
	 * 到课程评论页面
	 */
	@RequestMapping("/courseCommen/{courseId}")
	public ModelAndView courseCommen(HttpServletRequest request,@PathVariable("courseId") int courseId){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/course-comment"));
		try{
			Course course = courseService.queryCourseById(courseId);
			model.addObject("course",course);
			model.addObject("otherId",courseId);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("courseCommen()---error",e);
		}
		return model;
	}
	/**
	 * 到消息详情页面
	 */
	@RequestMapping("/letterInfo/{msgReceiveId}")
	public ModelAndView letterInfo(HttpServletRequest request,@PathVariable("msgReceiveId") Long msgReceiveId){
		ModelAndView model = new ModelAndView();
		try{
			/*通过id搜索消息*/
			QueryMsgReceive queryMsgReceive = msgReceiveService.queryMsgReceiveById(msgReceiveId);
			model.addObject("msgReceive",queryMsgReceive);
			/*更新消息为已读*/
			Long userId = new Long(SingletonLoginUtils.getLoginUserId(request));
			msgReceiveService.updateStatusReadMsgReceiveById(1,msgReceiveId,userId);
			model.setViewName(getViewPath("/web/ucenter/letter-info"));
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("letterInfo()---error",e);
		}
		return model;
	}
	/**
	 * 退出登录
	 */
	@RequestMapping("/exit")
	@ResponseBody
	public Map<String,Object> outLogin(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String prefix = WebUtils.getCookie(request, CacheConstans.WEB_USER_LOGIN_PREFIX);
			if(prefix!=null){
				CacheUtil.remove(prefix);
			}
			json = this.setJson(true, null, null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("outLogin()--error",e);
			
		}
		return json;
	}

	/**
	 * 查询站内信收件箱（无社区）
	 */
	@RequestMapping(value = "/letter")
	public ModelAndView queryUserLetter(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(getViewPath("/web/ucenter/uc_letter_inbox"));// 用户消息
		try {

			page.setPageSize(12);// 分页页数为12
			MsgReceive msgReceive = new MsgReceive();
			msgReceive.setReceivingCusId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));// set用户id
			// 更新所有收件箱为已读
			msgReceiveService.updateAllReadMsgReceiveInbox(msgReceive);
			List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive, page);// 查询站内信收件箱
			//修改用户消息数后  重新加入缓存
			userService.setLoginInfo(request,msgReceive.getReceivingCusId().intValue(),"false");

			modelAndView.addObject("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
			modelAndView.addObject("page", page);// 分页参数放入modelAndView中
		} catch (Exception e) {
			logger.error("UserController.queryUserLetter()", e);
			setExceptionRequest(request, e);
			modelAndView.setViewName(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	
	/**
     * 删除站内信收件箱
     */
    @RequestMapping(value = "/ajax/delLetterInbox")
    @ResponseBody
    public Map<String, Object> delLetterInbox(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            msgReceive.setReceivingCusId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));// set 用户id
            Long num = msgReceiveService.delMsgReceiveInbox(msgReceive);// 删除收件箱
            if (num.intValue() == 1) {
                map.put("message", "success");// 成功
            } else {
                map.put("message", "false");// 失败
            }
        } catch (Exception e) {
            logger.error("UserController.delLetterInbox()", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
	/**
	 * 更改系统消息为已读
	 */
	@RequestMapping(value = "/ajax/changeStatus")
	@ResponseBody
	public Map<String, Object> changeStatus(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			msgReceive.setReceivingCusId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));// set 用户id
			msgReceiveService.updateReadMsgReceiveById(msgReceive);
			map = this.setJson(true, "", "");
		} catch (Exception e) {
			logger.error("UserController.changeStatus()", e);
			map=setAjaxException(map);
		}
		return map;
	}
}