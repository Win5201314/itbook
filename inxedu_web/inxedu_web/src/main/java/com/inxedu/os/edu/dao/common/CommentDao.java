package com.inxedu.os.edu.dao.common;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.common.Comment;

import java.util.List;
import java.util.Map;
/**
 * 评论dao层接口
 * @author www.inxedu.com
 */
public interface CommentDao {
	/**
	 * 分页查询评论
	 */
	List<Comment> getCommentByPage(Comment comment, PageEntity page);
	/**
	 * 添加评论
	 * @param Comment    评论实体
	 */
	void addComment(Comment comment);
	/**
	 * 更新评论
	 */
	void updateComment(Comment comment);
	/**
	 * 查询评论互动
	 */
	List<Comment> queryCommentInteraction(Comment comment);
	/**
	 * 更新评论点赞数,回复数等
	 */
	void updateCommentNum(Map<String, String> map);
	/**
	 * 查询评论
	 */
	Comment queryComment(Comment comment);
	/**
	 * 删除评论
	 */
	void delComment(int commentId);
	/**
	 * 查询评论 list
	 */
	List<Comment> queryCommentList(Comment comment);
	/**
	 * 查询最近10条讨论
	 */
	List<Comment> queryCommentTalk(Comment comment);
	/**
	 * 根据课程分页查询评论
	 */
	List<Comment> queryCourseComment(Comment comment, PageEntity page);
}
