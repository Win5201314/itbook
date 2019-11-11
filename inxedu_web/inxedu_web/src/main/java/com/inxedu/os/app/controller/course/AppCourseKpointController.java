package com.inxedu.os.app.controller.course;

import com.inxedu.os.common.controller.BaseController;

import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import com.inxedu.os.edu.service.order.OrderService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webapp")
public class AppCourseKpointController extends BaseController{
	private static Logger logger=Logger.getLogger(AppCourseKpointController.class);
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;

	/**
	 * 课程章节
	 * courseId
	 */
	@RequestMapping("/front/kpoint")
	@ResponseBody
	public Map<String, Object> kpoint(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			Map <String ,Object> result = new HashedMap();
			String courseId=request.getParameter("courseId");
			// 查询课程详情
			Course course = courseService.queryCourseById(Integer.parseInt(courseId));
			//判断该课程是否可以观看
			boolean isok=false;
			if(course!=null){
				int userId = SingletonLoginUtils.getLoginUserId(request);
				if(userId>0){
					isok = orderService.checkUserCursePay(userId, Integer.parseInt(courseId));
				}
				//查询目录
				List<CourseKpoint> parentKpointList = new ArrayList<CourseKpoint>();
				List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(Integer.parseInt(courseId));
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
								result.put("freeVideoId",freeVideoId);
							}
						}
					}
					result.put("kpointList", kpointList);
					result.put("parentKpointSize", parentKpointList.size());

				}
			}
			json=this.setJson(true, "成功", result);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("couinfo()--error",e);
		}
		return json;
	}
}
