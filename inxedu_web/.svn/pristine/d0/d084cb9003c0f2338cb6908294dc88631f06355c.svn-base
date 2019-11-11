package com.inxedu.os.app.controller.comment;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.common.Comment;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.comment.CommentService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webapp")
public class AppComment extends BaseController{
	private static Logger logger=Logger.getLogger(AppComment.class);

	@Autowired
	private CommentService commentService;
	@Autowired
	private OrderService orderService;
	/**
	 * 查询评论
	 */
	@RequestMapping("/ajax/queryCommon")
	@ResponseBody
	public  Map<String, Object> queryComment(HttpServletRequest request, @ModelAttribute("page") PageEntity page, Comment comment) {
		Map<String, Object> json=new HashMap<String, Object>();
		try {
			Map<String,Object> result = new HashedMap();
			//查询评论一级
			comment.setPCommentId(0);
			comment.setType(1);
			comment.setOtherId(comment.getCourseId());
			List<Comment> commentList = commentService.getCommentByPage(comment, page);// 查询评论
			result.put("commentList", commentList);// 评论list
			result.put("page", page);
			//result.put("comment", comment);
			json=this.setJson(true, "成功", result);
		} catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("couinfo()--error",e);
		}
		return json;
	}
	/**
	 * 添加评论
	 */
	@RequestMapping("/ajax/addcomment")
	@ResponseBody
	public Map<String, Object> addComment(HttpServletRequest request, Comment comment) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			// 如果用户未登录则不能评论
			int userId = SingletonLoginUtils.getLoginUserId(request);
			if (userId == 0) {
				json = this.setJson(false, "isnotlogin", "用户id为空");
				return json;
			}
			// 登陆用户id
			comment.setUserId(userId);
			if (comment.getType() != 1) {
				if (!orderService.checkUserCursePay(userId, comment.getOtherId())) {
					json = this.setJson(false, "您还没有购买该课程,请购买后评论!", "");
					return json;
				}
			}
			//根据用户ID 和评论 缓存,防止用户刷评论,在缓存时间内只能评论三次
			//除了聊天类型
			if (comment.getType() != 5) {
				StringBuilder commentKey = new StringBuilder("commentNum_" + comment.getUserId() + "_" + comment.getOtherId() + "_" + comment.getType());
				Object commenNumCache = CacheUtil.get(commentKey.toString());
				if (commenNumCache != null) {
					int commNum = (int) commenNumCache;
					if (commNum >= 3) {
						return this.setJson(false, "操作频繁,请稍后再试!", "");
					} else {
						CacheUtil.set(commentKey.toString(), ++commNum, CacheConstans.USER_COMMENT_TIME);
					}
				} else {
					CacheUtil.set(commentKey.toString(), 1, CacheConstans.USER_COMMENT_TIME);
				}
			}
			// 添加评论
			commentService.addComment(comment);
			json = this.setJson(true, "添加成功", null);

		} catch (Exception e) {
			json = this.setJson(false, "false", "添加异常");
			logger.error("addComment()--error", e);
		}
		return json;
	}
}
