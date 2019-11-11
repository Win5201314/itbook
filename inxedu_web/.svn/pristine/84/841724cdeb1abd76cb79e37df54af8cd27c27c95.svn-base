package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.dao.course.CourseDao;
import com.inxedu.os.edu.dao.course.CoursePackageDao;
import com.inxedu.os.edu.dao.website.WebsiteCourseDetailDao;
import com.inxedu.os.edu.entity.course.*;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.member.CourseMember;
import com.inxedu.os.edu.entity.member.CourseMemberDTO;
import com.inxedu.os.edu.entity.member.MemberRecordDTO;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.member.CourseMemberService;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.user.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Course管理接口
 * @author www.inxedu.com
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private CoursePackageDao coursePackageDao;
	@Autowired
	private WebsiteCourseDetailDao websiteCourseDetailDao;
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired(required=false)
	private CourseMemberService courseMemberService;
	@Autowired(required=false)
	private MemberRecordService memberRecordService;

	public int addCourse(Course course) {
		return courseDao.addCourse(course);
	}

	public List<CourseDto> queryCourseListPage(QueryCourse query, PageEntity page) {
		return courseDao.queryCourseListPage(query, page);
	}

	public Course queryCourseById(int courseId) {
		return courseDao.queryCourseById(courseId);
	}

	public void updateCourse(Course course) {
		courseDao.updateCourse(course);
	}

	public void updateAvaliableCourse(int courseId, int type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseId", courseId);
		map.put("type", type);
		courseDao.updateAvaliableCourse(map);
		//如果是删除课程则把该课程的推荐记录和套餐记录删除
		if(type==3){
			//删除该课程的相关套餐
			CoursePackage coursePackage = new CoursePackage();
			coursePackage.setCourseId(Long.valueOf(courseId));
			coursePackageDao.delCoursePackageByCourseId(coursePackage);
			//删除该课程的相关课程推荐
			websiteCourseDetailDao.deleteDetailByCourseId(courseId);
		}

	}

	public Map<String, List<CourseDto>> queryRecommenCourseList() {
		@SuppressWarnings("unchecked")
		Map<String, List<CourseDto>> recMap = (Map<String, List<CourseDto>>) CacheUtil.get(CacheConstans.RECOMMEND_COURSE);
		if (recMap == null) {
			List<CourseDto> courseList = courseDao.queryRecommenCourseList();
			if (courseList != null && courseList.size() > 0) {
				recMap = new HashMap<String, List<CourseDto>>();
				List<CourseDto> _list = new ArrayList<CourseDto>();
				int recommendId = courseList.get(0).getRecommendId();
				for (CourseDto _cd : courseList) {
					if (recommendId == _cd.getRecommendId()) {
						_list.add(_cd);
					} else {
						//给整理好的list排序
						sortCourseDto(_list);
						recMap.put("recommen_" + recommendId, _list);
						_list = new ArrayList<CourseDto>();
						_list.add(_cd);
					}
					recommendId = _cd.getRecommendId();
				}
				//给整理好的list排序
				sortCourseDto(_list);
				recMap.put("recommen_" + recommendId, _list);
				CacheUtil.set(CacheConstans.RECOMMEND_COURSE, recMap, CacheConstans.RECOMMEND_COURSE_TIME);
			}
		}
		return recMap;
	}
	//list进行排序  正序
	public void sortCourseDto(List<CourseDto> _list){
		if(ObjectUtils.isNotNull(_list)){
			Collections.sort(_list, new Comparator<CourseDto>() {
				public int compare(CourseDto arg0, CourseDto arg1) {
					if(arg0.getOrderNum()>arg1.getOrderNum()){
						return -1;
					}
					if(arg0.getOrderNum()<arg1.getOrderNum()){
						return 1;
					}
					return 0;
				}
			});
		}
	}
	public List<CourseDto> queryCourseList(QueryCourse query) {
		return courseDao.queryCourseList(query);
	}

	public List<CourseDto> queryWebCourseListPage(QueryCourse queryCourse, PageEntity page) {
		return courseDao.queryWebCourseListPage(queryCourse, page);
	}

	public List<CourseDto> queryInterfixCourseLis(int subjectId, int count, int courseId,String sellType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("subjectId", subjectId);
		map.put("courseId", courseId);
		map.put("sellType", sellType);
		return courseDao.queryInterfixCourseList(map);
	}

	public List<CourseDto> queryMyCourseList(QueryCourse queryCourse, PageEntity page) {
		return courseDao.queryMyCourseList(queryCourse,page);
	}
	public int queryAllCourseCount() {
		return courseDao.queryAllCourseCount();
	}
	public List<CourseDto> queryRecommenCourseListByRecommendId(Long recommendId) {
		return courseDao.queryRecommenCourseListByRecommendId(recommendId);
	}
	public List<CourseDto> queryCourse(QueryCourse queryCourse) {
		return courseDao.queryCourse(queryCourse);
	}

	/**
	 * 获得项目专业限制的所有课程
	 */
	public List<Course> getCouponSubjectCourse(Long subjectId, String courseIds) {
		return courseDao.getCouponSubjectCourse(subjectId, courseIds);
	}

	/**
	 * 更新课程的数据数量（浏览数，购买数）
	 * @param type pageViewcount浏览数 pageBuycount购买数
	 */
	public void updateCourseCount(String type,int courseId){
		this.courseDao.updateCourseCount(type,courseId);
	}

	/**
	 * 后台根据条件获取套餐下课程详细信息
	 */
	public List<CourseDto> getPackageCourseListByCondition(Course course) {
		return courseDao.getPackageCourseListByCondition(course);
	}

	/**
	 * 获取课程套餐的详细课程列表
	 * @param ids 查询条件
	 */
	public List<CourseDto> getCourseListPackage(List<Long> ids){
		return courseDao.getCourseListPackage(ids);
	}
	/**
	 * 把传入的课程设置学习记录等信息
	 */
	public void getCoursePutStudyhistory(List<CourseDto> courseList,int userId){
		if(ObjectUtils.isNotNull(courseList)){
			for(Course course:courseList){
				CourseStudyhistory courseStudyhistory=new CourseStudyhistory();
				courseStudyhistory.setUserId(Long.valueOf(userId));
				courseStudyhistory.setCourseId(Long.valueOf(String.valueOf(course.getCourseId())));
				//我的课程学习记录
				List<CourseStudyhistory>  couStudyhistorysLearned=courseStudyhistoryService.getCourseStudyhistoryList(courseStudyhistory);
				int courseHistorySize=0;
				if (couStudyhistorysLearned!=null&&couStudyhistorysLearned.size()>0) {
					courseHistorySize=couStudyhistorysLearned.size();
				}
				//二级视频节点的 总数
				int sonKpointCount=courseKpointService.getSecondLevelKpointCount(Long.valueOf(course.getCourseId()));
				NumberFormat numberFormat = NumberFormat.getInstance();
				//我的学习进度百分比
				// 设置精确到小数点后0位
				numberFormat.setMaximumFractionDigits(0);
				String studyPercent = numberFormat.format((float) courseHistorySize / (float) sonKpointCount * 100);
				if(sonKpointCount==0){
					studyPercent="0";
				}
				course.setStudyPercent(studyPercent);
			}
		}
	}
	/**
	 * excel批量赠送课程
	 */
	public String updateGiveCoursesExcelExcel(HttpServletRequest request, MultipartFile myFile, Integer mark) throws Exception{
		String msg="";
		HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
		HSSFSheet sheet = wookbook.getSheet("Sheet1");

		int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
		if(rows==0){
			throw new BaseException("请填写数据");
		}
		for (int i = 1; i <= rows+1; i++) {
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			// 行不为空
			if (row != null) {
				// **读取cell**
				String email = getCellValue(row.getCell((short) 0));//邮箱
				String courseIds = getCellValue(row.getCell((short) 1));//赠送给用户学员的课程Id

				//邮箱
				if(StringUtils.isEmpty(email)){
					if(mark==1){
						msg+="第" + i + "行邮箱为空<br/>";
						continue;
					}else{
						throw new BaseException("第" + i + "行邮箱为空<br/>");
					}
				}
				User user =userService.queryUserByEmailOrMobile(email);
				if (ObjectUtils.isNull(user)){
					if(mark==1){
						msg+="第" + i + "行邮箱不存在<br/>";
						continue;
					}else{
						throw new BaseException("第" + i + "行邮箱不存在<br/>");
					}
				}

				if(StringUtils.isNotEmpty(courseIds)){
					int userId = user.getUserId();//用户学员Id
					String[] courseIdArr = courseIds.replace(",", " ").trim().split(" ");//赠送课程Id数组
					for(int j=0;j<courseIdArr.length;j++){
						int courseId = Integer.parseInt(courseIdArr[j]);
						// 判断课程是否已过期
						boolean overdue = false;
						Course course = this.queryCourseById(courseId);
						if (ObjectUtils.isNull(course)) {
							if(mark==1){
								msg+="第"+i+"行第"+(j+1)+"个的赠送课程不存在<br/>";
								continue;
							}else{
								throw new BaseException("第"+i+"行第"+(j+1)+"个的赠送课程不存在<br/>");
							}
						}
						//如果课程价格为0可以观看
						if(course.getCurrentPrice().compareTo(new BigDecimal(0))==0){
							if(mark==1){
								msg+="第"+i+"行第"+(j+1)+"个的赠送课程价格不能为零<br/>";
								continue;
							}else{
								throw new BaseException("第"+i+"行第"+(j+1)+"个的赠送课程价格不能为零<br/>");
							}
						}
						//判断该课程 用户是否可以购买
						boolean isok=orderService.checkUserCursePay(userId, courseId);
						if(isok){
							if(mark==1){
								msg+="第"+i+"行第"+(j+1)+"个用户已经购买了此课程<br/>";
								continue;
							}else{
								throw new BaseException("第"+i+"行第"+(j+1)+"个用户已经购买了此课程<br/>");
							}
						}
						//判断是否到期
						if (course.getLoseType() == 0) {
							Date date = new Date();// 今天
							String nowStr = DateUtils.formatDate(date, "yyyy-MM-dd");
							String authStr = DateUtils.formatDate(course.getEndTime(), "yyyy-MM-dd");
							if (Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1 > 0) {
								overdue = true;
							}
						} else {
							overdue = true;
						}
						if (overdue) {
							//赠送课程
							Map<String, Object> result=orderService.addOrderMsg(userId,course.getCourseId());
							if(!result.get("msg").equals("success")){
								if(mark==1){
									msg+="第"+i+"行第"+(j+1)+"个赠送课程失败<br/>";
									continue;
								}else{
									throw new BaseException("第"+i+"行第"+(j+1)+"个赠送课程失败<br/>");
								}
							}else{
								//发送站内信
								String conent = "【<a class=\"course-title c-333\" target=\"_blank\" href=\""+ CommonConstants.contextPath+"/front/couinfo/"+courseId+"\">"+course.getCourseName()+"</a>】";
								msgReceiveService.sendMessage( userId, "赠送课程提示", MsgType.giveCourse.toString(), true, conent);
							}
						} else {
							if(mark==1){
								msg+="第"+i+"行第"+j+1+"个赠送课程已过期<br/>";
								continue;
							}else{
								throw new BaseException("第"+i+"行第"+j+1+"个赠送课程已过期<br/>");
							}
						}
					}
				}else{
					if(mark==1){
						msg+="第"+i+"行赠送课程ID为空<br/>";
						continue;
					}else{
						throw new BaseException("第"+i+"行赠送课程ID为空<br/>");
					}
				}
			}
		}
		return msg;
	}
	/*个人中心查询所有课程*/
	public List<CourseDto> querySelfCourse(QueryCourse queryCourse,PageEntity page){
		//查询我的课程
		List<CourseDto> courseList = this.getMyCourseList(queryCourse);

		String type = queryCourse.getSellType();
        /*查询我的套餐*/
		queryCourse.setSellType("PACKAGE");
		List<CourseDto> packageCourse = this.getMyCourseList(queryCourse) ;
		queryCourse.setSellType(type);
            /*获取套餐的id集合*/
		List<Long> packageCoursesId = new ArrayList<>();
		if (packageCourse!=null&&packageCourse.size()>0){
			for (int i=0;i<packageCourse.size();i++){
				Course course = packageCourse.get(i);
				packageCoursesId.add(new Long(course.getCourseId()));
			}
			 /*获取套餐子课*/
			if (packageCoursesId!=null&&packageCoursesId.size()>0){
				List<CourseDto> courses = this.getCourseListPackage(packageCoursesId);
				/* 去重 */
				for (int k=courses.size()-1;k>=0;k--){
					for (int i=courseList.size()-1;i>=0;i--){
						int courseId = courseList.get(i).getCourseId();
						int pacageCourseId = courses.get(k).getCourseId();
						/*删除courseList中的课程（courseList和courses都有的课程） 或者 删除courseList的PACKAGE套餐课程*/
						if (courseId==pacageCourseId||courseList.get(i).getSellType().equals("PACKAGE")){
							courseList.remove(i);
						}
					}
               		/* 如果有课程类型 把不是要查的课程去除掉*/
					if (StringUtils.isNotEmpty(type)&&!courses.get(k).getSellType().equals(type)){
						courses.remove(k);
					}
				}
				/* 把套餐子课放到courseList*/
				courseList.addAll(courses);
			}
		}

		//总记录数
		page.setTotalResultSize(courseList.size());
		//计算共有几页
		page.setTotalPageSize(page.getTotalResultSize()%page.getPageSize()==0?page.getTotalResultSize()/page.getPageSize():page.getTotalResultSize()/page.getPageSize()+1);
		return courseList;
	}
	/**
	 * 获得Hsscell内容
	 */
	public String getCellValue(HSSFCell cell) {
		String value = "";
		if (cell != null) {
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA:
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					DecimalFormat df = new DecimalFormat("0");
					value = df.format(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue().trim();
					break;
				default:
					value = "";
					break;
			}
		}
		return value.trim();
	}
	/*查询课程最近被观看到章节*/
	public CourseStudyhistory getCourseStudyhistory(HttpServletRequest request,Long courseId){
		CourseStudyhistory courseStudyhistory=new CourseStudyhistory();
		courseStudyhistory.setCourseId(new Long(courseId));
		courseStudyhistory.setUserId(new Long(SingletonLoginUtils.getLoginUserId(request)));
		List<CourseStudyhistory>  couStudyhistorysLearned=courseStudyhistoryService.getCourseStudyhistoryList(courseStudyhistory);
		CourseStudyhistory lastTimeStudyhistory = new CourseStudyhistory();
		if (couStudyhistorysLearned.size()>0){
			lastTimeStudyhistory = couStudyhistorysLearned.get(0);
		}
		return lastTimeStudyhistory;
	}

	@Override
	public List<CourseDto> getMyCourseList(QueryCourse queryCourse) {
		return courseDao.getMyCourseList(queryCourse);
	}

	@Override
	public void setNearTime(List<CourseDto> courseDtos) {
		for (CourseDto courseDto:courseDtos){
				/*取直播章节开始结束时间*/
			List<CourseKpoint> courseKpointList = courseKpointService.queryCourseKpointByCourseId(courseDto.getCourseId());
			CourseKpoint courseKpoint = new CourseKpoint();
			List<Date> nearestBeginTime = new ArrayList();
			List<Date> nearestEndTime  = new ArrayList();
				/*把结束时间大于现在时间的直播时间放到开始和结束时间的list中*/
			for (int i=0;i<courseKpointList.size();i++){
				courseKpoint = courseKpointList.get(i);
				if (courseKpoint.getLiveEndTime()!=null&&new Date().before(courseKpoint.getLiveEndTime())){
					nearestBeginTime.add(courseKpoint.getLiveBeginTime());
					nearestEndTime.add(courseKpoint.getLiveEndTime());
				}
			}
				/*比较获取最近时间*/
			Date beginDate = null;
			Date endDate = null;
			for (int k=0;k<nearestBeginTime.size();k++){
				beginDate = nearestBeginTime.get(k);
				endDate = nearestEndTime.get(k);
				if (k+1<nearestBeginTime.size()){
					if (beginDate.after(nearestBeginTime.get(k+1))){
						beginDate = nearestBeginTime.get(k+1);
						endDate = nearestEndTime.get(k+1);
					}
				}
			}
			courseDto.setNearestLiveBeginTime(beginDate);
			courseDto.setNearestLiveEndTime(endDate);
		}
	}

	@Override
	public CourseDto getMyCourseByCourseId(QueryCourse queryCourse) {
		return courseDao.getMyCourseByCourseId(queryCourse);
	}

	@Override
	public List<CourseDto> queryMemberCourseListPage(QueryCourse queryCourse, PageEntity page) {
		return courseDao.queryMemberCourseListPage(queryCourse,page);
	}

	@Override
	public boolean hasMember(Long courseId, Long userId) {
		if(ObjectUtils.isNull(memberRecordService)){
			return false;
		}
		/*查寻用户当前所有类型会员的开通情况*/
		List<MemberRecordDTO> memberRecordDTOList = memberRecordService.userNowMemberInfo(userId);
		CourseMemberDTO courseMemberDTO = new CourseMemberDTO();
		courseMemberDTO.setCourseId(courseId);
		/*查询课程对应的会员类型*/
		List<CourseMember> courseMemberList = courseMemberService.queryCourseMemberList(courseMemberDTO);
		/*如果用户开通了会员并且课程是会员课程*/
		if(ObjectUtils.isNotNull(memberRecordDTOList)&&ObjectUtils.isNotNull(courseMemberList)){

			/*判断用户是否开通了课程对应的会员类型*/
			for(int i=0;i<courseMemberList.size();i++){
				/*如果用户开通的会员包括课程对应的某个会员类型*/
				for (int k=0;k<memberRecordDTOList.size();k++){
					if (memberRecordDTOList.get(k).getMemberType()==courseMemberList.get(i).getMemberTypeId()){
						return true;
					}
				}
			}
		}
		return false;
	}
}