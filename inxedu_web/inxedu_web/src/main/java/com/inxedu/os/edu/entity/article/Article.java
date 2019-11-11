package com.inxedu.os.edu.entity.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 文章资讯
 * @author www.inxedu.com
 */
@Data
public class Article implements Serializable{
	private static final long serialVersionUID = -760228884438140694L;
	/**文章ID*/
	private int articleId;
	/**文章标题*/
	private String title;
	/**文章摘要*/
	private String summary;//
	/**文章图片URL*/
	private String imageUrl;
	/**创建时间*/
	private Date createTime;
	/**发布时间 */
	private Date publishTime;
	/**文章点击量*/
	private int clickNum;
	/**文章点赞量*/
	private int praiseCount;
	/** 排序值 */
	private int sort;
	/**文章评论数*/
	private int commentNum;

}
