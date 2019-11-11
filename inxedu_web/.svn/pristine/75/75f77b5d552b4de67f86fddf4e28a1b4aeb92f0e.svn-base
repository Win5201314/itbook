package com.inxedu.os.edu.controller.course;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.*;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.member.CourseMember;
import com.inxedu.os.edu.entity.member.CourseMemberDTO;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.service.course.*;
import com.inxedu.os.edu.service.member.CourseMemberService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
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
 * Course管理接口
 * @author www.inxedu.com
 */
@Controller
public class CourseController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
	@Autowired
	private CoursePackageService coursePackageService;
	@Autowired
	private CourseFavoritesService courseFavoritesService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired(required=false)
	private MemberTypeService memberTypeService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
	@Autowired(required=false)
	private CourseMemberService courseMemberService;
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }
    @InitBinder("courseFavorites")
    public void initBinderCourseFavorites(WebDataBinder binder) {
    	binder.setFieldDefaultPrefix("courseFavorites.");
    }

    /**
     * 课程列表展示,搜索课程
     */
    @RequestMapping("/front/showcoulist")
    public ModelAndView showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        ModelAndView model = new ModelAndView();
        try {
        	model.setViewName(getViewPath("/web/course/courses-list"));
            // 页面传来的数据放到page中
        	page.setPageSize(12);
            //只查询上架的
            queryCourse.setIsavaliable(1);
			//查询 课程+套餐
			if (StringUtils.isEmpty(queryCourse.getSellType_cou_pag())){
				/*如果没有传sellType_cou_pag就取课程和套餐*/
				queryCourse.setSellType_cou_pag("true");
			}
			if(StringUtils.isEmpty(queryCourse.getOrder())){
				queryCourse.setOrder("BYGONE");//默认排序
			}
            // 搜索课程列表
            List<CourseDto> courseList = courseService.queryWebCourseListPage(queryCourse, page);
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
                if (subject.getParentId() != 0) {//条件为二级专业,获得它的同级专业
                    sonSubjectList = subjectService.getSubjectListByOne(Long.valueOf(subject.getParentId()));

                    model.addObject("subjectParentId", subject.getParentId());//父级id
					subject.setSubjectId(subject.getParentId());
					subject = subjectService.getSubjectBySubject(subject);
					model.addObject("subjectParentName", subject.getSubjectName());//父级 专业名
                } else {//条件为一级专业，获得它的子级专业
                    sonSubjectList = subjectService.getSubjectListByOne(Long.valueOf(subject.getSubjectId()));
					model.addObject("subjectParentName", subject.getSubjectName());//当前查询专业名
                }
                model.addObject("sonSubjectList", sonSubjectList);
            }
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if ("ON".equals(serviceSwitch.get("member"))&&ObjectUtils.isNotNull(memberTypeService)){
				   /*会员类型集合*/
				List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
				model.addObject("memberTypeList",memberTypeList);
			}
            model.addObject("page",page);

            model.addObject("queryCourse", queryCourse);
            model.addObject("subjectList", subjectList);

			  /*用于移动端展示的专业列表 查询的所有专业*/
			List<Subject> mobileSubjecs = subjectService.getSubjectList(new QuerySubject());
           	/*用于移动端 查询选择的subject的父级*/
			/*用于移动端*/
			model.addObject("mobileSubjecs",mobileSubjecs);
			/*用于移动端*/
			model.addObject("parrentSubject",subject);
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("showCourseList()--error", e);
        }
        return model;
    }

    /**
     * 课程详情
     * 不管是课程套餐还是课程目录时都放到list(coursePackageList)中
     */
    @RequestMapping("/front/couinfo/{id}")
    public ModelAndView couinfo(HttpServletRequest request, @PathVariable("id") int courseId) {
        ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName(getViewPath("/web/course/course-infor"));
            // 查询课程详情
            Course course = courseService.queryCourseById(courseId);
			//如果课程为空或者为删除状态则不能查看课程
			if(ObjectUtils.isNull(course)||course.getIsavaliable()==3){
				model.setViewName("redirect:/");
				return model;
			}
			//课程章节
			List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
			//如果是直播
			if(course.getSellType().equals("LIVE")){
				//购买服务开关
				Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
				if(!serviceSwitch.get("live").toString().equals("ON")){
					request.setAttribute("msg","您暂未购买【直播】功能，无法查看直播详情！");
					//访问受限
					model.setViewName(getViewPath("/web/pay/visit-limit"));
					return model;
				}
				model.setViewName("redirect:/front/liveinfo/"+courseId);
				return model;
			}
			//如果是套餐
			if(course.getSellType().equals("PACKAGE")){
				model.setViewName(getViewPath("/web/course/course-package-info"));

				List<Long> ids = new ArrayList<Long>();
				ids.add(Long.valueOf(courseId));
				List<CourseDto> courseDtoList=courseService.getCourseListPackage(ids);
				model.addObject("courseDtoList", courseDtoList);

				//购买此套餐的人
				/*QueryTrxorderDetail queryTrxorderDetail=new QueryTrxorderDetail();
				queryTrxorderDetail.setCourseId(Long.valueOf(courseId));
				queryTrxorderDetail.setLimitNum(8);
				List<QueryTrxorderDetail> queryTrxorderDetailList= trxorderDetailService.getTrxorderDetailCourseList(queryTrxorderDetail);
				model.addObject("queryTrxorderDetailList", queryTrxorderDetailList);*/

				//获得第一个课程id
				model.addObject("firstCourseId", ObjectUtils.isNotNull(courseDtoList)?courseDtoList.get(0).getCourseId():0);
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
            	/*for(CourseDto tempCoursedto : courseList){
            		teacherList=teacherService.queryCourseTeacerList(tempCoursedto.getCourseId());
            		tempCoursedto.setTeacherListMap(teacherList);
            	}*/
            	model.addObject("courseList", courseList);
            	int userId = SingletonLoginUtils.getLoginUserId(request);
            	if(userId>0){
            		isok = orderService.checkUserCursePay(userId, courseId);
            		
            		//查询是否已经收藏
        			boolean isFavorites = courseFavoritesService.checkFavorites(userId, courseId);
        			model.addObject("isFavorites", isFavorites);
            	}

				/*查询课程所属套餐集合*/
				List<CoursePackage> coursePackages = coursePackageService.quePackageByCouId(Long.valueOf(courseId));
				List<List<CourseDto>> courseDtoLists = new ArrayList<>();
				List<Course> packageCourses = new ArrayList<>();
				for (CoursePackage coursePackage:coursePackages){
					Course course1 = new Course();
					List<Long> ids = new ArrayList<Long>();
					ids.add(Long.valueOf(coursePackage.getMainCourseId()));
					/*根据套餐id获取套餐下的课程*/
					List<CourseDto> courseDtoList=courseService.getCourseListPackage(ids);
					/*获取套餐对应的课程信息*/
					course1 = courseService.queryCourseById(Integer.parseInt(coursePackage.getMainCourseId().toString()));
					/*套餐下的课程集合*/
					course1.setCourseList(courseDtoList);

					packageCourses.add(course1);
				}
				model.addObject("packageCourses",packageCourses);

				//查询目录
				if(kpointList!=null && kpointList.size()>0){
					int freeVideoId=0;
						for(CourseKpoint temp:kpointList){
							if(temp.getKpointType()==1&& (temp.getFileType().equals("VIDEO")/*||temp.getFileType().equals("AUDIO")*/)){
								//已购买 获取第一个
								if(isok){
									freeVideoId=temp.getKpointId();
									model.addObject("freeVideoId",freeVideoId);
									break;
								}
								//获取一个可以试听的视频id
								else if (temp.getFree()==1) {
									freeVideoId=temp.getKpointId();
									model.addObject("freeVideoId",freeVideoId);
									break;
								}
							}
						}
				}else{
					model.addObject("kpointListIsNull","true");
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
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("couinfo()----error", e);
        }
        return model;
    }
    
    /**
     * 查询课程目录
     */
    @RequestMapping("/front/ajax/courseKpointList/{courseId}/{type}")
    public ModelAndView courseKpointList(HttpServletRequest request,@ModelAttribute("courseFavorites") CourseFavorites courseFavorites,@PathVariable("courseId") int courseId,@PathVariable("type") int type){
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/web/course/course-kpoint-list"));
			// 查询课程详情
			Course course = courseService.queryCourseById(courseId);
			//判断该课程是否可以观看
			boolean isok=false;
			if(course!=null){
				int userId = SingletonLoginUtils.getLoginUserId(request);
				if(userId>0){
					isok = orderService.checkUserCursePay(userId, courseId);
				}
				//查询目录
				List<CourseKpoint> parentKpointList = new ArrayList<CourseKpoint>();
				List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
				if(kpointList!=null && kpointList.size()>0){
					for(CourseKpoint temp:kpointList){
						if (temp.getParentId()==0) {
							parentKpointList.add(temp);
						}
						//如果没有观看权限则清空视频URL避免被盗看
						if (isok ==false) {
							//设置收费的视频URL为空
							if(temp.getFree()==2){
								temp.setVideoUrl(null);
							}
						}
						/*根据课程id和用户id查询学习记录*/
						CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
						courseStudyhistory.setCourseId(new Long(courseId));
						courseStudyhistory.setUserId(new Long(userId));
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
					model.addObject("parentKpointList", kpointList);
					model.addObject("parentKpointSize", parentKpointList.size());

				}
			}

			/*查询课程最近被观看到章节*/
			CourseStudyhistory lastTimeStudyhistory = courseService.getCourseStudyhistory(request,new Long(courseId));
			model.addObject("lastTimeStudyhistory",lastTimeStudyhistory);
			model.addObject("userId",SingletonLoginUtils.getLoginUserId(request));
			model.addObject("course", course);
			model.addObject("courseId", courseId);
			model.addObject("isok", isok);
			model.addObject("playFromType", type);
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("courseKpointList()----error", e);
		}
		return model;
    }

	/**
	 * 添加课程收藏
	 */
	@RequestMapping("/front/createfavorites/{courseId}")
	@ResponseBody
	public Map<String,Object> createFavorites(HttpServletRequest request,@ModelAttribute("courseFavorites") CourseFavorites courseFavorites,@PathVariable("courseId") int courseId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int userId = SingletonLoginUtils.getLoginUserId(request);
			if(userId<=0){
				json = this.setJson(false, "请登录！", null);
				return json;
			}
			if(courseId<=0){
				json = this.setJson(false, "请选择要收藏的课程！", null);
				return json;
			}
			boolean is = courseFavoritesService.checkFavorites(userId, courseId);
			if(is){
				json = this.setJson(false, "该课程你已经收藏过了！", null);
				return json;
			}
			courseFavorites.setUserId(userId);
			courseFavorites.setAddTime(new Date());
			courseFavoritesService.createCourseFavorites(courseFavorites);
			json = this.setJson(true, "收藏成功", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("createFavorites()--error",e);
		}
		return json;
	}

	/**
	 * 购买课程验证
	 */
	@RequestMapping("/course/check/{id}")
	@ResponseBody
	public Map<String, Object> checkCourse(@PathVariable int id) {
		Map<String, Object> json = null;
		try {
			Course course = courseService.queryCourseById(id);
			if (course.getIsavaliable() != 1) {
				json = this.setJson(false, "课程已下架", null);
			} else if (course.getCurrentPrice().doubleValue() <= 0) {
				json = this.setJson(false, "开通课程金额不能为0", null);
			} else {
				json = this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("CourseController.checkCourse", e);
			json = this.setJson(false, "error", null);
		}
		return json;
	}
	/**
	 * 删除课程（更改订单流水状态)
	 */
	@RequestMapping("/front/ajax/delTrxOrder")
	@ResponseBody
	public Map<String,Object> deleteTrxorderDetailById(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Long courseId = Long.parseLong(request.getParameter("courseId"));
			QueryTrxorderDetail queryTrxorderDetail = new QueryTrxorderDetail();
			/*根据用户id、课程号、购买状态查找订单流水*/
			queryTrxorderDetail.setUserId(new Long(SingletonLoginUtils.getLoginUserId(request)));
			queryTrxorderDetail.setCourseId(courseId);
			queryTrxorderDetail.setAuthStatus("SUCCESS");
			List<QueryTrxorderDetail> queryTrxorderDetailList = trxorderDetailService.getTrxorderDetailCourseList(queryTrxorderDetail);
			/*将查到的流水状态改为删除状态*/
			if (queryTrxorderDetailList.size()>0){
				TrxorderDetail trxorderDetail = new TrxorderDetail();
				trxorderDetail = trxorderDetailService.getTrxorderDetailById(queryTrxorderDetailList.get(0).getId());
				trxorderDetail.setAuthStatus("DELETE");
				trxorderDetailService.updateTrxorderDetail(trxorderDetail);
			}
			json = this.setJson(true,"",null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("deleteTrxorderDetailById()--error",e);
		}
		return json;
	}
}