package com.inxedu.os.edu.controller.comment;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.edu.entity.common.Comment;
import com.inxedu.os.edu.service.article.ArticleService;
import com.inxedu.os.edu.service.comment.CommentService;
import com.inxedu.os.edu.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台评论模块
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminCommentController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AdminCommentController.class);

	@Autowired
	private CommentService commentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ArticleService articleService;
	@InitBinder("comment")
	public void initBinderCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("comment.");
	}

	/**
	 * 查询评论
	 */
	@RequestMapping("/comment/query/{otherId}/{type}")
	public String queryComment(HttpServletRequest request, @ModelAttribute("page") PageEntity page,Comment comment,@PathVariable("otherId") int otherId,@PathVariable("type") Integer type) {
		try {
			//查询评论一级
			comment.setPCommentId(0);
			comment.setOtherId(otherId);
			comment.setType(type);
			List<Comment> commentList = commentService.getCommentByPage(comment, page);// 查询评论
			request.setAttribute("commentList", commentList);// 评论list
			request.setAttribute("comment", comment);
			request.setAttribute("page", page);
		} catch (Exception e) {
			logger.error("queryComment()--error", e);
		}
		return getViewPath("/admin/comment/comment-list");// 评论列表
	}
	
	/**
	 * 查询评论回复
	 */
	@RequestMapping("/commentreply/query")
	public String queryCommentReply(HttpServletRequest request, @ModelAttribute("page") PageEntity page,Comment comment) {
		try {
			page.setPageSize(10);
			List<Comment> commentList = commentService.getCommentByPage(comment, page);// 查询评论
			request.setAttribute("commentList", commentList);// 评论list
			request.setAttribute("comment", comment);// 评论list
			request.setAttribute("page", page);
		} catch (Exception e) {
			logger.error("queryComment()--error", e);
		}
		// 回复列表
		return getViewPath("/admin/comment/comment-reply-list");
	}
	/**
	 * 删除评论,回复
	 */
	@RequestMapping("/comment/del/{commentId}")
	@ResponseBody
	@SystemLog(type="del",operation="删除评论")
	public Map<String, Object>  delComment(@ModelAttribute("page") PageEntity page,@PathVariable("commentId") int commentId) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			commentService.delComment(commentId);
			json = this.setJson(true, "", "");
		} catch (Exception e) {
			this.setAjaxException(json);
			logger.error("queryComment()--error", e);
			setJson(false, "", "");
		}
		return json;
	}
	
	/**
	 * 查询评论详情
	 */
	@RequestMapping("/commentreply/info/{commentId}")
	public String queryCommentInfo(HttpServletRequest request,@PathVariable("commentId") int commentId) {
		try {
			//查询评论
			Comment comment = new Comment();
			comment.setCommentId(commentId);
			comment = commentService.queryComment(comment);
			request.setAttribute("comment", comment);// 评论
		} catch (Exception e) {
			logger.error("queryCommentInfo()--error", e);
		}
		// 回复详情
		return getViewPath("/admin/comment/comment_info");
	}
	/**
	 * 更新评论
	 */
	@RequestMapping("/commentreply/update")
	@SystemLog(type="update",operation="更新评论")
	public String updateCommentInfo(HttpServletRequest request,Comment comment) {
		try {
			//更新评论
			commentService.updateComment(comment);
			request.setAttribute("comment", comment);// 评论
		} catch (Exception e) {
			logger.error("updateCommentInfo()--error", e);
		}
		return "redirect:/admin/comment/query";
	}
}
