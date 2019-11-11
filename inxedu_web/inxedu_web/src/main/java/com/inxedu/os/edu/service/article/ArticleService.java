package com.inxedu.os.edu.service.article;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.article.Article;
import com.inxedu.os.edu.entity.article.ArticleContent;
import com.inxedu.os.edu.entity.article.QueryArticle;

import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
public interface ArticleService {
	/**
	 * 创建文章
	 * @param article 文章实体
	 * @return 返回文章ID
	 */
	int createArticle(Article article);
	
	/**
	 * 添加文章内容
	 * @param content 文章内容实体
	 */
	void addArticleContent(ArticleContent content);
	
	/**
	 * 修改文章信息
	 * @param article 文章实体
	 */
	void updateArticle(Article article);
	
	/**
	 * 修改文章内容 
	 * @param content
	 */
	void updateArticleContent(ArticleContent content);
	
	/**
	 * 删除文章
	 * @param articleIds 文章ID数组
	 */
	void deleteArticleByIds(String[] articleIds);
	
	/**
	 * 通过文章ID查询文章信息
	 * @param articleId 文章ID
	 * @return Article文章实体信息
	 */
	Article queryArticleById(int articleId);
	
	/**
	 * 通过文章ID查询文章内容
	 * @param articleId 文章内容
	 * @return String类型文章内容
	 */
	String queryArticleContentByArticleId(int articleId);
	
	/**
	 * 分页查询文章列表
	 */
	List<Article> queryArticlePage(QueryArticle query, PageEntity page);
	
	/**
	 * 修改累加文章点击量
	 * @param map 文章ID
	 */
	void updateArticleNum(Map<String, String> map);
	
	/**
	 * 公共多条件查询文章资讯列表,用于前台
	 */
	List<Article> queryArticleList(QueryArticle queryArticle);
	
	/**
	 * 获取所有文章总记录数
	 * @return 总记录数
	 */
	int queryAllArticleCount();
}
