package com.inxedu.os.edu.controller.comment;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.common.Comment;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.comment.CommentService;
import com.inxedu.os.edu.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台评论模块
 *
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/web/comment")
public class CommentController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;
    @Autowired
    private OrderService orderService;

    /**
     * 查询评论
     */
    @RequestMapping("/ajax/query")
    public String queryComment(HttpServletRequest request, @ModelAttribute("page") PageEntity page, Comment comment) {
        try {
            page.setPageSize(6);
            //查询评论一级
            comment.setPCommentId(0);
            List<Comment> commentList = commentService.getCommentByPage(comment, page);// 查询评论
            request.setAttribute("commentList", commentList);// 评论list
            request.setAttribute("page", page);
            request.setAttribute("comment", comment);
            User user = SingletonLoginUtils.getLoginUser(request);
            request.setAttribute("user", user);
        } catch (Exception e) {
            logger.error("queryComment()--error", e);
        }
        // 评论列表
        return getViewPath("/web/comment/comment");
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
            json = this.setJson(true, "true", "发送成功");

        } catch (Exception e) {
            json = this.setJson(false, "false", "发送异常");
            logger.error("addComment()--error", e);
        }
        return json;
    }

    /**
     * 查询文章评论的回复
     */
    @RequestMapping("/ajax/commentreply")
    public String queryCommentReply(HttpServletRequest request, @ModelAttribute("page") PageEntity page, Comment comment) {
        try {
            comment.setLimitNumber(9);
            List<Comment> commentList = commentService.queryCommentList(comment);
            request.setAttribute("commentList", commentList);// 回复
            User user = SingletonLoginUtils.getLoginUser(request);
            request.setAttribute("user", user);
        } catch (Exception e) {
            logger.error("queryCommentReply()--error", e);
        }
        // 评论回复
        return getViewPath("/web/comment/comment_reply");
    }

    /**
     * 查询评论的回复 分页
     */
    @RequestMapping("/ajax/commentreplypage")
    public String queryCommentReplyPage(HttpServletRequest request, Comment comment) {
        try {
            List<Comment> commentList = commentService.queryCommentList(comment);
            request.setAttribute("commentList", commentList);// 回复

            comment.setCommentId(comment.getPCommentId());
            comment = commentService.queryComment(comment);
            request.setAttribute("parentComment", comment);
        } catch (Exception e) {
            logger.error("queryCommentReplyPage()--error", e);
        }
        //评论回复分页
        return getViewPath("/web/comment/comment_reply_page");
    }

    /**
     * 查询讨论最近10条
     */
    @RequestMapping("/ajax/querytalk")
    public String queryCommentTalk(HttpServletRequest request, @ModelAttribute("page") PageEntity page, Comment comment) {
        try {
            //查询评论一级
            comment.setPCommentId(0);
            comment.setType(5);//讨论
            List<Comment> commentList = commentService.getCommentByPage(comment, page);// 查询评论
            //查询最后8条
            page.setCurrentPage(page.getTotalPageSize());
            comment.setOrder("commentIdASC");
            comment.setBeginNum(page.getTotalResultSize() - 8);
            comment.setLimitNumber(6);
            commentList = commentService.queryCommentTalk(comment);// 查询评论
            request.setAttribute("commentList", commentList);// 评论list
            request.setAttribute("page", page);
            request.setAttribute("comment", comment);
            User user = SingletonLoginUtils.getLoginUser(request);
            request.setAttribute("user", user);
        } catch (Exception e) {
            logger.error("queryComment()--error", e);
        }
        // 评论列表
        return getViewPath("/web/comment/comment_talk");
    }
}
