package com.inxedu.os.edu.controller.live;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import com.inxedu.os.edu.entity.member.CourseMember;
import com.inxedu.os.edu.entity.member.CourseMemberDTO;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.course.*;
import com.inxedu.os.edu.service.livecourse.LiveCourseService;
import com.inxedu.os.edu.service.member.CourseMemberService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 直播管理接口
 * @author www.inxedu.com
 */
@Controller
public class LiveController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LiveController.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CourseFavoritesService courseFavoritesService;
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;
	@Autowired
	private LiveCourseService liveCourseService;
	@Autowired(required=false)
	private MemberTypeService memberTypeService;
	@Autowired
	private CourseTeacherService courseTeacherService;
	@Autowired(required=false)
	private CourseMemberService courseMemberService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

	/**
	 * 直播首页
	 */
	@RequestMapping("/front/liveIndex")
	public ModelAndView liveIndex(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(getViewPath("/web/live/liveIndex"));
		try {
			//近期直播
			/*CourseKpoint courseKpoint=new CourseKpoint();
			courseKpoint.setFileType("LIVE");//直播
			courseKpoint.setKpointType(1);//节点类型 0文件目录 1视频
			courseKpoint.setQueryOrder("near");//近期
			courseKpoint.setQueryLimitNum(3);//限制条数
			courseKpoint.setIsavaliable(1);
			courseKpoint.setLiveBeginTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));//包括今天之后的
			List<CourseKpointDto> courseKpointDtoList=courseKpointService.queryKpointList(courseKpoint);
			model.addObject("courseKpointDtoList", courseKpointDtoList);*/
			//近期直播章节
			CourseKpoint courseKpoint=new CourseKpoint();
			courseKpoint.setFileType("LIVE");//直播
			courseKpoint.setKpointType(1);//节点类型 0文件目录 1视频
			courseKpoint.setQueryOrder("near");//近期
			courseKpoint.setQueryLimitNum(4);//限制条数
			courseKpoint.setIsavaliable(1);
			courseKpoint.setLiveEndTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));//包括今天之后的
			model.addObject("courseDtos", courseKpointService.queryCourseNearestKpointList(courseKpoint));

			// 获得推荐直播课程
			List<CourseDto> liveRecommend = courseService.queryRecommenCourseList().get("recommen_35");
			if(ObjectUtils.isNotNull(liveRecommend)){
				//查询直播章节
				Map<String, Object> queryMap=new HashMap<>();
				queryMap.put("queryTime",new Date());
				for(CourseDto courseDto:liveRecommend){
					queryMap.put("coruseId",courseDto.getCourseId());
					courseDto.setCourseKpointList(liveCourseService.queryKpointByCourse(queryMap));
				}
			}
			request.setAttribute("liveRecommend", liveRecommend);

			QueryCourse queryCourse;
			//最新直播
			List<CourseDto> newcourseList = (List<CourseDto>) CacheUtil.get(CacheConstans.LIVE_INDEX_RECOMMEND+"new");
			if(ObjectUtils.isNull(newcourseList)){
				queryCourse=new QueryCourse();
				//只查询上架的
				queryCourse.setIsavaliable(1);
				//查询直播课程
				queryCourse.setSellType("LIVE");
				//限制条数
				queryCourse.setCount(8);
				//排序 最新
				queryCourse.setOrder("SEQUENCE");
				// 搜索列表
				newcourseList = courseService.queryCourseList(queryCourse);
				CacheUtil.set(CacheConstans.LIVE_INDEX_RECOMMEND+"new",newcourseList,CacheConstans.RECOMMEND_COURSE_TIME);
			}
			model.addObject("newcourseList", newcourseList);

			// 查询所有1级专业
			QuerySubject querySubject = new QuerySubject();
			querySubject.setParentId(0);
			model.addObject("subjectList", subjectService.getSubjectList(querySubject));

			//直播免费排行
			newcourseList = (List<CourseDto>) CacheUtil.get(CacheConstans.LIVE_INDEX_RECOMMEND+"free");
			if(ObjectUtils.isNull(newcourseList)){
				queryCourse=new QueryCourse();
				//只查询上架的
				queryCourse.setIsavaliable(1);
				//查询直播课程
				queryCourse.setSellType("LIVE");
				//限制条数
				queryCourse.setCount(6);
				//排序 浏览数多的
				queryCourse.setOrder("FOLLOW");
				//免费
				queryCourse.setIsFree("true");
				// 搜索列表
				newcourseList = courseService.queryCourseList(queryCourse);
				//查询直播章节
				Map<String, Object> queryMap=new HashMap<>();
				queryMap.put("queryTime",new Date());
				for(CourseDto courseDto:newcourseList){
					queryMap.put("coruseId",courseDto.getCourseId());
					courseDto.setCourseKpointList(liveCourseService.queryKpointByCourse(queryMap));
				}
				CacheUtil.set(CacheConstans.LIVE_INDEX_RECOMMEND+"free",newcourseList,CacheConstans.RECOMMEND_COURSE_TIME);
			}
			model.addObject("freecourseList", newcourseList);

			//直播免费排行
			newcourseList = (List<CourseDto>) CacheUtil.get(CacheConstans.LIVE_INDEX_RECOMMEND+"charge");
			if(ObjectUtils.isNull(newcourseList)){
				queryCourse=new QueryCourse();
				//只查询上架的
				queryCourse.setIsavaliable(1);
				//查询直播课程
				queryCourse.setSellType("LIVE");
				//限制条数
				queryCourse.setCount(6);
				//排序 浏览数多的
				queryCourse.setOrder("FOLLOW");
				//收费
				queryCourse.setIsFree("false");
				// 搜索列表
				newcourseList = courseService.queryCourseList(queryCourse);
				//查询直播章节
				Map<String, Object> queryMap=new HashMap<>();
				queryMap.put("queryTime",new Date());
				for(CourseDto courseDto:newcourseList){
					queryMap.put("coruseId",courseDto.getCourseId());
					courseDto.setCourseKpointList(liveCourseService.queryKpointByCourse(queryMap));
				}
				CacheUtil.set(CacheConstans.LIVE_INDEX_RECOMMEND+"charge",newcourseList,CacheConstans.RECOMMEND_COURSE_TIME);
			}
			model.addObject("chargecourseList", newcourseList);

			// 查询课程购买多的几位讲师
			List<Teacher> teacherList=(List<Teacher>) CacheUtil.get(CacheConstans.INDEX_TEACHER_RECOMMEND+"_live");
			if(ObjectUtils.isNull(teacherList)){
				queryCourse=new QueryCourse();
				//查询直播课程
				queryCourse.setSellType("LIVE");
				//限制条数
				queryCourse.setCount(4);
				//排序 购买数多的
				queryCourse.setOrder("PAGEBUYCOUNT");
				teacherList=courseTeacherService.getTeachersByCourse(queryCourse);
				CacheUtil.set(CacheConstans.INDEX_TEACHER_RECOMMEND+"_live", teacherList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addObject("teacherList",teacherList);
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("liveIndex()--error", e);
		}
		return model;
	}

	/**
	 * 课程直播列表展示,搜索课程
	 */
	@RequestMapping("/front/showLivelist")
	public ModelAndView showLiveCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/web/live/liveList"));
			// 页面传来的数据放到page中
			page.setPageSize(12);
			//只查询上架的
			queryCourse.setIsavaliable(1);
			//查询 课程
			queryCourse.setSellType("LIVE");
			if(StringUtils.isEmpty(queryCourse.getOrder())){
				queryCourse.setOrder("BYGONE");//默认排序
			}
			queryCourse.setQueryTime(new Date());
			// 搜索课程列表
			List<Course> courseList = liveCourseService.queryWebLiveCourseListPage(queryCourse, page);
			model.addObject("courseList", courseList);

			// 查询所有1级专业
			QuerySubject querySubject = new QuerySubject();
			querySubject.setParentId(0);
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);

			Subject subject = new Subject();
			subject.setSubjectId(queryCourse.getSubjectId());
			subject = subjectService.getSubjectBySubject(subject);
			/*获取专业筛选条件的信息*/
			model.addObject("currentSubject",subject);

			//根据条件专业查询 所有的子专业
			if (ObjectUtils.isNotNull(queryCourse.getSubjectId())) {
				//查询子专业
				List<Subject> sonSubjectList = null;
				if (subject.getParentId() != 0) {//条件为二级专业
					sonSubjectList = subjectService.getSubjectListByOne(Long.valueOf(subject.getParentId()));

					model.addObject("subjectParentId", subject.getParentId());//父级id
					subject.setSubjectId(subject.getParentId());
					subject = subjectService.getSubjectBySubject(subject);
					model.addObject("subjectParentName", subject.getSubjectName());//父级 专业名
				} else {//条件为一级专业
					sonSubjectList = subjectService.getSubjectListByOne(Long.valueOf(subject.getSubjectId()));
					model.addObject("subjectParentName", subject.getSubjectName());//当前查询专业名
				}
				model.addObject("sonSubjectList", sonSubjectList);
			}

			// 全部教师
			QueryTeacher query = new QueryTeacher();
			List<Teacher> teacherList =teacherService.queryTeacherList(query);

			// 获得所有推荐课程
			Map<String, List<CourseDto>> mapCourseList = courseService.queryRecommenCourseList();
			request.setAttribute("mapCourseList", mapCourseList);
			model.addObject("page",page);
			model.addObject("queryCourse", queryCourse);
			model.addObject("teacherList", teacherList);
			model.addObject("subjectList", subjectList);


			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if ("ON".equals(serviceSwitch.get("member"))&&ObjectUtils.isNotNull(memberTypeService)){
				/*会员类型集合*/
				List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
				model.addObject("memberTypeList",memberTypeList);
			}
			/*用于移动端展示的专业列表 查询的所有专业*/
			List<Subject> mobileSubjecs = subjectService.getSubjectList(new QuerySubject());
           	/*用于移动端 查询选择的subject的父级*/
				/*用于移动端*/
			model.addObject("mobileSubjecs",mobileSubjecs);
			/*用于移动端*/
			model.addObject("parrentSubject",subject);
			model.addObject("currentTeacher",teacherService.getTeacherById(queryCourse.getTeacherId()));

		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showCourseList()--error", e);
		}
		return model;
	}

	/**
	 * 直播详情
	 */
	@RequestMapping("/front/liveinfo/{id}")
	public ModelAndView couinfo(HttpServletRequest request, @PathVariable("id") int courseId) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/web/live/live-infor"));
			// 查询详情
			Course course = courseService.queryCourseById(courseId);
			//课程章节
			List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
			//查询目录
			List<CourseKpoint> parentKpointList = new ArrayList<CourseKpoint>();
			if(kpointList!=null && kpointList.size()>0){
				/*最近开始直播的章节*/
				CourseKpoint courseKpoint = new CourseKpoint();
				for (CourseKpoint temp : kpointList){
					if (temp.getLiveBeginTime()!=null&&!temp.getLiveEndTime().before(new Date())){
						courseKpoint = temp;
					}
				}
				for(CourseKpoint temp:kpointList){
					if (temp.getParentId()==0) {
						parentKpointList.add(temp);
						//清空地址
						temp.setVideoUrl(null);
					}
					/*把直播时间到现在时间转换成毫秒*/
					if(courseKpoint.getLiveEndTime()!=null&&courseKpoint.getLiveEndTime().after(new Date())){
						Long begin = courseKpoint.getLiveBeginTime().getTime();
						Long nowTime = System.currentTimeMillis();
						Long liveTime = begin - nowTime;
						model.addObject("liveTime",liveTime);
						model.addObject("courseKpoint",courseKpoint);
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
					}
				}
				model.addObject("parentKpointList", parentKpointList);
			}

			//判断该课程是否可以观看
			boolean isok=false;
			if(course!=null){
				model.addObject("course", course);
				//更新课程的浏览量
				courseService.updateCourseCount("pageViewcount",courseId);

				//查询课程老师
				List<Map<String,Object>> teacherList = teacherService.queryCourseTeacerList(courseId);
				model.addObject("teacherList", teacherList);
				//相关课程
				List<CourseDto> courseList = courseService.queryInterfixCourseLis(course.getSubjectId(), 5,course.getCourseId(),course.getSellType());
				for(CourseDto tempCoursedto : courseList){
					teacherList=teacherService.queryCourseTeacerList(tempCoursedto.getCourseId());
					tempCoursedto.setTeacherListMap(teacherList);
				}
				model.addObject("courseList", courseList);
				int userId = SingletonLoginUtils.getLoginUserId(request);
				if(userId>0){
					isok = orderService.checkUserCursePay(userId, courseId);

					//查询是否已经收藏
					boolean isFavorites = courseFavoritesService.checkFavorites(userId, courseId);
					model.addObject("isFavorites", isFavorites);
				}
			}
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if ("ON".equals(serviceSwitch.get("member"))&&ObjectUtils.isNotNull(memberTypeService)){
				/*查询课程对应的会员类型*/
				CourseMemberDTO courseMemberDTO = new CourseMemberDTO();
				courseMemberDTO.setCourseId(new Long(courseId));
				List<CourseMember> courseMemberList = courseMemberService.queryCourseMemberList(courseMemberDTO);
				/*所有会员类型*/
				List<MemberType> memberTypes = memberTypeService.getMemberTypes();
				model.addObject("courseMemberList", courseMemberList);
				model.addObject("memberTypes", memberTypes);
			}
			model.addObject("isok", isok);

			//如果是直播查询 未开始的（开始了默认学过）节点数量
			CourseKpoint courseKpoint = new CourseKpoint();
			courseKpoint.setCourseId(course.getCourseId());
			courseKpoint.setLiveBeginTime(new Date());
			List<CourseKpointDto> courseKpointList = courseKpointService.queryCourseNearestKpointList(courseKpoint);
			int notStart=courseKpointList.size();
			int videoKpointSize=courseKpointService.getSecondLevelKpointCount(Long.valueOf(course.getCourseId()));
			/*已开始开始的直播节点数量*/
			model.addObject("started",videoKpointSize-notStart);
			//课程视频节点的 总数
			model.addObject("videoKpointSize",videoKpointSize);

			//从缓存中获取
			//学习此课程的人 (最新8个)
			List<CourseStudyhistory> couStudyhistorysLearned=(List<CourseStudyhistory>)CacheUtil.get("COURSE_LEARNED_USER_"+courseId);
			if(couStudyhistorysLearned==null||couStudyhistorysLearned.size()==0){
				couStudyhistorysLearned=courseStudyhistoryService.getCourseStudyhistoryListByCouId(Long.valueOf(String.valueOf(courseId)));
				CacheUtil.set("COURSE_LEARNED_USER_"+courseId, couStudyhistorysLearned,300);//缓存5分钟
			}
			model.addObject("couStudyhistorysLearned",couStudyhistorysLearned);
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("couinfo()----error", e);
		}
		return model;
	}

	/**
	 * 查询直播目录
	 */
	@RequestMapping("/front/ajax/liveKpointList/{courseId}")
	public ModelAndView liveKpointList(HttpServletRequest request,@PathVariable("courseId") int courseId){
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/web/live/live-kpoint-list"));
			// 查询课程详情
			Course course = courseService.queryCourseById(courseId);
			//判断该课程是否可以观看
			boolean isok=false;
			if(course!=null){
				int userId = SingletonLoginUtils.getLoginUserId(request);
				if(userId>0){
					isok = orderService.checkUserCursePay(userId, courseId);
				}
				/*if(isok){*/
					//查询目录
					List<CourseKpoint> parentKpointList = new ArrayList<CourseKpoint>();
					List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
					if(kpointList!=null && kpointList.size()>0) {


						/*最近开始直播的章节*/
						CourseKpoint courseKpoint = new CourseKpoint();
						for (CourseKpoint temp : kpointList){
							if (temp.getLiveBeginTime()!=null&&!temp.getLiveEndTime().before(new Date())){
								courseKpoint = temp;
							}
						}
						for (CourseKpoint temp : kpointList) {
							if (temp.getParentId() == 0) {
								parentKpointList.add(temp);
							}
							/*如果章节的直播结束大于现在&&小于最近开始直播时间*/
							if (temp.getLiveBeginTime()!=null&&courseKpoint.getLiveBeginTime()!=null&&temp.getLiveEndTime().after(new Date())&&temp.getLiveBeginTime().before(courseKpoint.getLiveBeginTime())){
								/*更新最近直播章节*/
								courseKpoint = temp;
							}
							/*把直播时间到现在时间转换成毫秒*/
							if(courseKpoint.getLiveEndTime()!=null&&courseKpoint.getLiveEndTime().after(new Date())){
								Long begin = courseKpoint.getLiveBeginTime().getTime();
								Long nowTime = System.currentTimeMillis();
								Long liveTime = begin - nowTime;
								model.addObject("liveTime",liveTime);
								model.addObject("courseKpoint",courseKpoint);
							}
						}

						for (CourseKpoint tempParent : parentKpointList) {
							for (CourseKpoint temp : kpointList) {
								if (temp.getParentId() == tempParent.getKpointId()) {
									tempParent.getKpointList().add(temp);
								}
							}
						}
						/*直播播放进度*/
						int childKpointSize =  0;
						/*已结束的直播数*/
						int overkpoint =0;
						/*章节节点 （非视频节点）*/
						int parentKpointsize=0;
						/*循环查看视频节点数*/
						if (ObjectUtils.isNotNull(parentKpointList)){
							for (int i=0;i<parentKpointList.size();i++){
								if (parentKpointList.get(i).getKpointType()==0){
									childKpointSize += parentKpointList.get(i).getKpointList().size();
									/*父级节点数+1*/
									parentKpointsize++;
								    /*循环查看子节点播放数*/
									for (int k=0;k<parentKpointList.get(i).getKpointList().size();k++){
										if (parentKpointList.get(i).getKpointList().get(k).getLiveBeginTime().before(new Date())){
											overkpoint++;
										}
									}
								}else {
									/*如果为视频节点 子节点+1*/
									childKpointSize++;
									/*如果直播时间大于当前时间 播放节点数量+1*/
									if(parentKpointList.get(i).getLiveBeginTime().before(new Date())){
										overkpoint++;
									}
								}

							}
						}
						model.addObject("parentKpointList", parentKpointList);
						model.addObject("parentKpointsize", parentKpointsize);
						model.addObject("childKpointSize", childKpointSize);
						model.addObject("overkpoint", overkpoint);

					}
				/*}*/
			}
			model.addObject("isok", isok);
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("courseKpointList()----error", e);
		}
		return model;
	}

	/**
	 * 直播首页 按专业推荐
	 * @param request
	 * @return
     */
	@RequestMapping("/live/ajax/liveRecommended")
	public ModelAndView liveRecommended(HttpServletRequest request,ModelAndView model,@RequestParam("subjectId")int subjectId) {
		model.setViewName(getViewPath("/web/live/live-index-recommend"));
		try {
			List<CourseDto> courseList = (List<CourseDto>) CacheUtil.get(CacheConstans.LIVE_INDEX_RECOMMEND+subjectId);
			if(ObjectUtils.isNull(courseList)){
				QueryCourse queryCourse=new QueryCourse();
				queryCourse.setSubjectId(subjectId);
				//只查询上架的
				queryCourse.setIsavaliable(1);
				//查询直播课程
				queryCourse.setSellType("LIVE");
				//限制条数
				queryCourse.setCount(8);
				//排序
				queryCourse.setOrder("SEQUENCE");
				// 搜索列表
				courseList = courseService.queryCourseList(queryCourse);
				CacheUtil.set(CacheConstans.LIVE_INDEX_RECOMMEND+subjectId,courseList,CacheConstans.RECOMMEND_COURSE_TIME);
			}
			model.addObject("courseList", courseList);
			model.addObject("subjectId", subjectId);
			request.setAttribute("queryTrxorderDetailList", courseList);
		} catch (Exception e) {
			logger.error("liveRecommended()", e);
			model.setViewName(this.setExceptionRequest(request, e));
		}
		return model;
	}

	/**
	 * 个人中心  我的直播
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("/uc/live")
	public ModelAndView ucLive(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			page.setPageSize(3);
			model.setViewName(getViewPath("/web/live/uc_live"));
			//获取登录用户ID
			int userId = SingletonLoginUtils.getLoginUserId(request);
			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(userId);
			queryCourse.setSellType("LIVE");
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
	 * 个人中心  我的直播 过期
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("/uc/overduelive")
	public ModelAndView ucOverdueLive(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			page.setPageSize(3);
			model.setViewName(getViewPath("/web/live/uc_live_overdue"));
			//获取登录用户ID
			int userId = SingletonLoginUtils.getLoginUserId(request);
			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(userId);
			queryCourse.setSellType("LIVE");
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
	 * 免费直播列表
	 */
	@RequestMapping("/uc/freeLiveList")
	public ModelAndView showFreeCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/web/live/uc_freeLive"));
			// 页面传来的数据放到page中
			page.setPageSize(3);
			QueryCourse queryCourse=new QueryCourse();
			queryCourse.setSellType("LIVE");//直播类型
			//只查询上架的
			queryCourse.setIsavaliable(1);
			queryCourse.setIsFree("true");//查询免费的课程
			// 搜索课程列表
			List<CourseDto> courseList = courseService.queryWebCourseListPage(queryCourse, page);
			model.addObject("courseList", courseList);
			model.addObject("page",page);
			model.addObject("sellType",queryCourse.getSellType());
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showFreeCourseList()--error", e);
		}
		return model;
	}
}