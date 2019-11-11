package com.inxedu.os.edu.controller.webfront;

import com.alibaba.fastjson.JSONObject;
import com.asual.lesscss.LessEngine;
import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.article.Article;
import com.inxedu.os.edu.entity.article.QueryArticle;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.help.HelpMenu;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.entity.website.WebsiteImages;
import com.inxedu.os.edu.entity.website.WebsiteProfile;
import com.inxedu.os.edu.service.article.ArticleService;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseTeacherService;
import com.inxedu.os.edu.service.generateindex.GenerateIndexService;
import com.inxedu.os.edu.service.help.HelpMenuService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.website.WebsiteImagesService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台 controller
 * @author www.inxedu.com
 */
@Controller
public class WebFrontController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(WebFrontController.class);

	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private HelpMenuService helpMenuService;
    @Autowired
    private ArticleService articleService;
	@Autowired
	private GenerateIndexService generateIndexService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private CourseTeacherService courseTeacherService;
	@Autowired
	private SubjectService subjectService;
    @Autowired
    private WebsiteImagesService websiteImagesService;
    @Autowired
    private TeacherService teacherService;
	/**
	 * 首页获取网站首页数据
	 */
	@RequestMapping("/index")
	public String getIndexpage(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			//缓存邀请码
			WebUtils.setCookie(response,"invitationCode",request.getParameter("invitationCode"),1);

			//获取后台系统设置的主题色,并根据设置取banner
			websiteProfileService.setWebsiteThemeColor(request);

			// 获得所有推荐课程
			request.setAttribute("mapCourseList", courseService.queryRecommenCourseList());

			/*取最新课程和全部课程*/
			model.addAttribute("newCourseList", bnaCourse("NEW"));
			model.addAttribute("allCourseList", bnaCourse("ALL"));

			//购买服务开关
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if("ON".equals(serviceSwitch.get("indexLive"))){
				//近期直播章节
				CourseKpoint courseKpoint=new CourseKpoint();
				courseKpoint.setFileType("LIVE");//直播
				courseKpoint.setKpointType(1);//节点类型 0文件目录 1视频
				courseKpoint.setQueryOrder("near");//近期
				courseKpoint.setQueryLimitNum(8);//限制条数
				courseKpoint.setIsavaliable(1);
				//courseKpoint.setLiveBeginTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));//包括今天之后的
				/*结束时间大于今天的  正在播放 和未开始的*/
				courseKpoint.setLiveEndTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));
				/*去重查询直播最近开始的直播章节*/
				List<CourseKpointDto> courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
				/*如果没有要开始的直播章节就查询最近结束的4个*/
				if (ObjectUtils.isNull(courseKpointDtoList)){
					courseKpoint.setLiveBeginTime(null);
					courseKpoint.setQueryOrder("overNear");//近期结束章节
					courseKpoint.setQueryLimitNum(4);//限制条数
					courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
				}
				model.addAttribute("courseDtos",courseKpointDtoList);
			}



			List<Teacher> teacherList=(List<Teacher>) CacheUtil.get(CacheConstans.INDEX_TEACHER_RECOMMEND);
			if(ObjectUtils.isNull(teacherList)){
				// 查询课程购买多的几位讲师
				/*QueryCourse queryCourse=new QueryCourse();
				//限制条数
				queryCourse.setCount(4);
				//排序 购买数多的
				queryCourse.setOrder("PAGEBUYCOUNT");
				teacherList=courseTeacherService.getTeachersByCourse(queryCourse);*/
				//按照 讲师排序值 查询讲师
				QueryTeacher queryTeacher=new QueryTeacher();
				queryTeacher.setCount(4);
				teacherList =teacherService.queryTeacherList(queryTeacher);
				CacheUtil.set(CacheConstans.INDEX_TEACHER_RECOMMEND, teacherList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("teacherList", teacherList);

			//最新资讯
            QueryArticle queryArticle = new QueryArticle();
            queryArticle.setOrderby(0);
			queryArticle.setCount(4);
            model.addAttribute("articleList",articleService.queryArticleList(queryArticle));
		} catch (Exception e) {
			logger.error("WebFrontController.getIndexpage", e);
			return setExceptionRequest(request, e);
		}
		return getViewPath("/web/front/index");
	}
	/*首页查询最新课程或全部课程*/
	public List<CourseDto> bnaCourse(String order){
		QueryCourse queryCourse = new QueryCourse();
		queryCourse.setOrder(order);
		//只查询上架的
		queryCourse.setIsavaliable(1);
		//只查询课程的
		queryCourse.setSellType("COURSE");
		//查询条数
		queryCourse.setQueryLimit(8);
		// 获得精品课程、最新课程、全部课程
		List<CourseDto> courseDtoBNAList = courseService.queryCourse(queryCourse);
		return courseDtoBNAList;
	}
	/**
	 * 首页2
	 */
	@RequestMapping("/index2")
	public String getIndexTwopage(HttpServletRequest request, Model model) {
		try {
			//获取后台系统设置的主题色,并根据设置取banner
			websiteProfileService.setWebsiteThemeColor(request);
			// 获得所有推荐课程
			request.setAttribute("mapCourseList", courseService.queryRecommenCourseList());

			//购买服务开关
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if("ON".equals(serviceSwitch.get("indexLive"))){
				//近期直播章节
				CourseKpoint courseKpoint=new CourseKpoint();
				courseKpoint.setFileType("LIVE");//直播
				courseKpoint.setKpointType(1);//节点类型 0文件目录 1视频
				courseKpoint.setQueryOrder("near");//近期
				courseKpoint.setQueryLimitNum(4);//限制条数
				courseKpoint.setIsavaliable(1);
				//courseKpoint.setLiveBeginTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));//包括今天之后的
				/*结束时间大于今天的  正在播放 和未开始的*/
				courseKpoint.setLiveEndTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));
				/*去重查询直播最近开始的直播章节*/
				List<CourseKpointDto> courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
				/*如果没有要开始的直播章节就查询最近结束的4个*/
				if (ObjectUtils.isNull(courseKpointDtoList)){
					courseKpoint.setLiveBeginTime(null);
					courseKpoint.setQueryOrder("overNear");//近期结束章节
					courseKpoint.setQueryLimitNum(4);//限制条数
					courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
				}
				model.addAttribute("courseDtos",courseKpointDtoList);
			}


			List<Teacher> teacherList=(List<Teacher>) CacheUtil.get(CacheConstans.INDEX2_TEACHER_RECOMMEND);
			if(ObjectUtils.isNull(teacherList)){
				// 查询课程购买多的几位讲师
				/*QueryCourse queryCourse=new QueryCourse();
				//限制条数
				queryCourse.setCount(3);
				//排序 购买数多的
				queryCourse.setOrder("PAGEBUYCOUNT");
				teacherList=courseTeacherService.getTeachersByCourse(queryCourse);*/
				//按照 讲师排序值 查询讲师
				QueryTeacher queryTeacher=new QueryTeacher();
				queryTeacher.setCount(4);
				teacherList =teacherService.queryTeacherList(queryTeacher);
				CacheUtil.set(CacheConstans.INDEX2_TEACHER_RECOMMEND, teacherList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("teacherList", teacherList);

			//专业课程
			List<Subject> subjectList=(List<Subject>) CacheUtil.get(CacheConstans.INDEX2_SUBJECT_COURSE);
			if(ObjectUtils.isNull(subjectList)){
				QueryCourse queryCourse=new QueryCourse();
				queryCourse.setOrder("BUYCOUNT");//购买数
				queryCourse.setIsavaliable(1);
				queryCourse.setSellType("COURSE");

				// 查询所有1级专业
				QuerySubject querySubject = new QuerySubject();
				querySubject.setParentId(0);
				subjectList = subjectService.getSubjectList(querySubject);
				for(Subject subject:subjectList){
					// 查询专业 下的课程
					queryCourse.setCount(6);
					queryCourse.setSubjectId(subject.getSubjectId());
					subject.setCourseDtoList(courseService.queryCourseList(queryCourse));
					// 查询专业 下的课程（最新排序）
					queryCourse.setCount(11);
					queryCourse.setOrder("SEQUENCE");//购买数
					subject.setCourseDtoList2(courseService.queryCourseList(queryCourse));
				}
				CacheUtil.set(CacheConstans.INDEX2_SUBJECT_COURSE, subjectList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("indexSubjectCourseList", subjectList);



			//左侧热门资讯
			List<Article> articleList=(List<Article>) CacheUtil.get(CacheConstans.INDEX2_ARTICLE_LEFT);
			if(ObjectUtils.isNull(articleList)){
				QueryArticle queryArticle = new QueryArticle();
				queryArticle.setOrderby(1);
				queryArticle.setCount(4);
				articleList=articleService.queryArticleList(queryArticle);
				CacheUtil.set(CacheConstans.INDEX2_ARTICLE_LEFT, articleList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("leftarticleList", articleList);
			//右侧最新资讯
			articleList=(List<Article>) CacheUtil.get(CacheConstans.INDEX2_ARTICLE_RIGHT);
			if(ObjectUtils.isNull(articleList)){
				QueryArticle queryArticle = new QueryArticle();
				queryArticle.setOrderby(1);
				queryArticle.setCount(10);
				articleList=articleService.queryArticleList(queryArticle);
				CacheUtil.set(CacheConstans.INDEX2_ARTICLE_RIGHT, articleList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("rightarticleList", articleList);
		} catch (Exception e) {
			logger.error("WebFrontController.getIndexTwopage", e);
			return setExceptionRequest(request, e);
		}
		return getViewPath("/web/front/index2");
	}
	/**
	 * 首页3
	 */
	@RequestMapping("/index3")
	public String getIndexTreepage(HttpServletRequest request, Model model) {
		try {
			//获取后台系统设置的主题色,并根据设置取banner
			websiteProfileService.setWebsiteThemeColor(request);
			// 获得所有推荐课程
			request.setAttribute("mapCourseList", courseService.queryRecommenCourseList());

			//购买服务开关
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if("ON".equals(serviceSwitch.get("indexLive"))){
				//近期直播章节
				CourseKpoint courseKpoint=new CourseKpoint();
				courseKpoint.setFileType("LIVE");//直播
				courseKpoint.setKpointType(1);//节点类型 0文件目录 1视频
				courseKpoint.setQueryOrder("near");//近期
				courseKpoint.setQueryLimitNum(4);//限制条数
				courseKpoint.setIsavaliable(1);
				//courseKpoint.setLiveBeginTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));//包括今天之后的
				/*结束时间大于今天的  正在播放 和未开始的*/
				courseKpoint.setLiveEndTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));
				/*去重查询直播最近开始的直播章节*/
				List<CourseKpointDto> courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
				/*如果没有要开始的直播章节就查询最近结束的4个*/
				if (ObjectUtils.isNull(courseKpointDtoList)){
					courseKpoint.setLiveBeginTime(null);
					courseKpoint.setQueryOrder("overNear");//近期结束章节
					courseKpoint.setQueryLimitNum(4);//限制条数
					courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
				}
				model.addAttribute("courseDtos",courseKpointDtoList);
			}


			List<Teacher> teacherList=(List<Teacher>) CacheUtil.get(CacheConstans.INDEX3_TEACHER_RECOMMEND);
			if(ObjectUtils.isNull(teacherList)){
				/*QueryCourse queryCourse=new QueryCourse();
				//限制条数
				queryCourse.setCount(4);
				//排序 购买数多的
				// 查询课程购买多的几位讲师
				queryCourse.setOrder("PAGEBUYCOUNT");
				teacherList=courseTeacherService.getTeachersByCourse(queryCourse);*/

				//按照 讲师排序值 查询讲师
				QueryTeacher queryTeacher=new QueryTeacher();
				queryTeacher.setCount(4);
				teacherList =teacherService.queryTeacherList(queryTeacher);
				CacheUtil.set(CacheConstans.INDEX3_TEACHER_RECOMMEND, teacherList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("teacherList", teacherList);

			//专业课程
			List<Subject> subjectList=(List<Subject>) CacheUtil.get(CacheConstans.INDEX3_SUBJECT_COURSE);
			if(ObjectUtils.isNull(subjectList)){
				QueryCourse queryCourse=new QueryCourse();
				queryCourse.setOrder("BUYCOUNT");//购买数
				queryCourse.setIsavaliable(1);
				queryCourse.setSellType("COURSE");

				// 查询所有1级专业
				QuerySubject querySubject = new QuerySubject();
				querySubject.setParentId(0);
				subjectList = subjectService.getSubjectList(querySubject);
				for(Subject subject:subjectList){
					// 查询专业 下所有 的课程（最新排序）
					queryCourse.setCount(6);
					queryCourse.setOrder("SEQUENCE");//购买数
					queryCourse.setSubjectId(subject.getSubjectId());
					subject.setCourseDtoList(courseService.queryCourseList(queryCourse));

					/*查询所有 子专业*/
					List<Subject> childSubject = subjectService.getSubjectListByOne(new Long(subject.getSubjectId()));
					subject.setSubjectList(childSubject);
					// 查询子专业 下所有 的课程（最新排序）
					for (int i=0;i<childSubject.size();i++){
						queryCourse.setSubjectId(childSubject.get(i).getSubjectId());
						childSubject.get(i).setCourseDtoList(courseService.queryCourseList(queryCourse));
					}
				}
				CacheUtil.set(CacheConstans.INDEX3_SUBJECT_COURSE, subjectList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("indexSubjectCourseList", subjectList);

            /*课程广告图*/
            Map<String,List<WebsiteImages>> images = websiteImagesService.queryImagesByType();
            model.addAttribute("images",images.get("type_23"));
			//左侧热门资讯
			List<Article> articleList=(List<Article>) CacheUtil.get(CacheConstans.INDEX3_ARTICLE_LEFT);
			if(ObjectUtils.isNull(articleList)){
				QueryArticle queryArticle = new QueryArticle();
				queryArticle.setOrderby(1);
				queryArticle.setCount(2);
				articleList=articleService.queryArticleList(queryArticle);
				CacheUtil.set(CacheConstans.INDEX3_ARTICLE_LEFT, articleList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("leftarticleList", articleList);
			//右侧最新资讯
			articleList=(List<Article>) CacheUtil.get(CacheConstans.INDEX3_ARTICLE_RIGHT);
			if(ObjectUtils.isNull(articleList)){
				QueryArticle queryArticle = new QueryArticle();
				queryArticle.setOrderby(1);
				queryArticle.setCount(10);
				articleList=articleService.queryArticleList(queryArticle);
				CacheUtil.set(CacheConstans.INDEX3_ARTICLE_RIGHT, articleList,CacheConstans.RECOMMEND_COURSE_TIME);//设置缓存
			}
			model.addAttribute("rightarticleList", articleList);
		} catch (Exception e) {
			logger.error("WebFrontController.getIndexTwopage", e);
			return setExceptionRequest(request, e);
		}
		return getViewPath("/web/front/index3");
	}
	/** 
	 * 修改主题色
	 * 
	 */
    @RequestMapping("/theme/ajax/update")
    @ResponseBody
    public Object updateTheme(HttpServletRequest request,@RequestParam String color) {
    	Map<String,Object> json = new HashMap<String,Object>();
		try {
			changeColor(request,color);
			json = this.setJson(true, color, "");
		}catch (Exception e){
			logger.error("WebFrontController.updateTheme", e);
		}
		return json;
    }
    
    public void changeColor(HttpServletRequest request,String colorfalg) throws Exception{
    	String color="#ea562e";
    	if (colorfalg.equals("blue")) {
    		color="#009ed9";
		}else if (colorfalg.equals("green")) {
    		color="#68cb9b";
		}


		Map<String, String> map = new HashMap<String, String>();
		map.put("inxedu_index_theme_color", colorfalg);
		// 将map转化json串
		JSONObject jsonObject = FastJsonUtil.obj2JsonObject(map);
		if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
			WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
			websiteProfile.setType(WebSiteProfileType.ThemeColor.toString());
			websiteProfile.setDesciption(jsonObject.toString());
			websiteProfileService.updateWebsiteProfile(websiteProfile);
		}

    	//获得项目根目录
    	String strDirPath = request.getSession().getServletContext().getRealPath("/");
    	//读取字符串
    	StringBuffer sb = new StringBuffer();
    	//当前读取行数
    	int rowcount = 1 ;
    	//要修改的行数
    	int updaterowcount = 2 ;
    	FileReader fr;
    	try {
			String path = strDirPath+"/static/inxweb/css/less/theme.less";
			fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				if(rowcount==updaterowcount&&StringUtils.isNotEmpty(color)){
					sb.append("@mColor:"+color+";");
				}else{
					sb.append(line);
				}
				line = br.readLine();
				rowcount++;
			}
			br.close();
			fr.close();
			LessEngine engine = new LessEngine();
			FileWriter fw = new FileWriter(strDirPath+"/static/inxweb/css/theme.css");
		    fw.write(engine.compile(sb.toString()));
			fw.flush();
			fw.close();

			//购买服务开关
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());

			if("ON".equals(serviceSwitch.get("exam").toString())) {
				sb = new StringBuffer();
				//当前读取行数
				rowcount = 1 ;
				//要修改的行数
				updaterowcount = 2 ;
				path = strDirPath+"/static/exam/css/less/examtheme.less";
				fr = new FileReader(path);
				br = new BufferedReader(fr);
				line = br.readLine();
				while (line != null) {
					if(rowcount==updaterowcount&&StringUtils.isNotEmpty(color)){
						sb.append("@mColor:"+color+";");
					}else{
						sb.append(line);
					}
					line = br.readLine();
					rowcount++;
				}
				br.close();
				fr.close();
				engine = new LessEngine();
				fw = new FileWriter(strDirPath+"/static/exam/css/examtheme.css");
				fw.write(engine.compile(sb.toString()));
				fw.flush();
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }

	/**
	 * 帮助中心
	 */
	@RequestMapping("/front/helpCenter")
	public String getHelpCenter(HttpServletRequest request, Model model) {
		try {
			// 右侧显示内容的二级菜单id
			String id = request.getParameter("id");
			// 帮助中心菜单集合，不含内容
			List<List<HelpMenu>> helpMenus = helpMenuService.getHelpMenuAll();
			model.addAttribute("helpMenus", helpMenus);
			// 右侧显示内容
			HelpMenu helpMenuContent = null;
			if (id != null && !id.equals("")) {
				helpMenuContent = helpMenuService.getHelpMenuById(Long.parseLong(id));
			} else if (helpMenus.size() > 0 && helpMenus.get(0).get(1) != null) {
				helpMenuContent = helpMenuService.getHelpMenuById(helpMenus.get(0).get(1).getId());
			}
			model.addAttribute("helpMenuContent", helpMenuContent);//显示的

		} catch (Exception e) {
			logger.error("WebFrontController.getHelpCenter", e);
			return setExceptionRequest(request, e);
		}
		return getViewPath("/web/front/helpCenter");
	}
    

	/**
	 * 根据用户key 获得登录用户（用于小组登录）
	 *
	 */
	@RequestMapping("/generateIndex")
	@ResponseBody
	public Object generateIndex(HttpServletRequest request) {
		Map<String,Object> json = new HashMap<String,Object>();
		String path = request.getParameter("path");
		if(StringUtils.isEmpty(path)){
			path="index";
		}
		//删除缓存
		if("index".equals(path)){
			CacheUtil.remove(CacheConstans.INDEX_TEACHER_RECOMMEND);
		}
		else if("index2".equals(path)){
			CacheUtil.remove(CacheConstans.INDEX2_TEACHER_RECOMMEND);
			CacheUtil.remove(CacheConstans.INDEX2_SUBJECT_COURSE);
			CacheUtil.remove(CacheConstans.INDEX2_ARTICLE_LEFT);
			CacheUtil.remove(CacheConstans.INDEX2_ARTICLE_RIGHT);
		}
		else if("index3".equals(path)){
			CacheUtil.remove(CacheConstans.INDEX3_TEACHER_RECOMMEND);
			CacheUtil.remove(CacheConstans.INDEX3_SUBJECT_COURSE);
			CacheUtil.remove(CacheConstans.INDEX3_ARTICLE_LEFT);
			CacheUtil.remove(CacheConstans.INDEX3_ARTICLE_RIGHT);
		}
		json = this.setJson(true, "success", generateIndexService.getGenerateIndex(path));
		return json;
	}

	/**
	 * 个人中心 异常 ，重定向到前台，不走/uc布局
	 */
	@RequestMapping("/front/error")
	public String error(HttpServletRequest request, Model model) {
		return "/common/error";
	}

	/**
	 * h5 搜索
	 */
	@RequestMapping("/h5/ajax/h5Search")
	public String goH5Search(HttpServletRequest request) {
		try {
			String searchType = request.getParameter("searchType");
			request.setAttribute("searchType",searchType);
		} catch (Exception e) {
			logger.error("WebFrontController.goH5Search", e);
			return setExceptionRequest(request, e);
		}
		return getViewPath("/web/front/h5search");
	}
}
