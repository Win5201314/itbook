package com.inxedu.os.edu.controller.article;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.article.Article;
import com.inxedu.os.edu.entity.article.QueryArticle;
import com.inxedu.os.edu.service.article.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台文章资讯
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/front")

public class ArticleController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;
	// 绑定属性 封装参数
	@InitBinder("queryArticle")
	public void initQueryArticle(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryArticle.");
	}
	/**
	 * 分页查询文章列表
	 */
	@RequestMapping("/articlelist")
	public ModelAndView showArticleList(HttpServletRequest request, @ModelAttribute("queryArticle") QueryArticle queryArticle, @ModelAttribute("page") PageEntity page) {
		ModelAndView model = new ModelAndView();
		try {
			page.setPageSize(10);
			List<Article> articleList = articleService.queryArticlePage(queryArticle, page);
			model.addObject("articleList", articleList);
			model.addObject("queryArticle", queryArticle);
			model.addObject("page", page);
			model.setViewName(getViewPath("/web/article/article-list"));

			// 查询文章 按评论数多的 排序
			QueryArticle query = new QueryArticle();
			query.setCount(5);
			query.setOrderby(2);
			List<Article> articleListRecommend= articleService.queryArticleList(query);
			request.setAttribute("articleListRecommend", articleListRecommend);
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showArticleList()--error", e);
		}
		return model;
	}
	/**
	 * 修改文章点击数量
	 */
	@RequestMapping("/updateArticleClickNum/{articleId}")
	@ResponseBody
	public Map<String, Object> updateArticleClickNum(@PathVariable("articleId") int articleId) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", "+1");
			map.put("type", "clickNum");
			map.put("articleId", articleId + "");
			articleService.updateArticleNum(map);
			Article article = articleService.queryArticleById(articleId);
			json = this.setJson(true, null, article);
		} catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateArticleClickNum()--error", e);
		}
		return json;
	}
	/**
	 * 查询好文推荐（文章按浏览书排行）
	 */
	@RequestMapping("/ajax/recommend")
	public String queryArticleRecommend(HttpServletRequest request) {
		try {
			// 查询排行文章
			List<Article> articleList=(List<Article>)CacheUtil.get(CacheConstans.ARTICLE_GOOD_RECOMMEND);
			if (articleList==null||articleList.size()==0) {
				QueryArticle query = new QueryArticle();
				query.setCount(10);
				query.setOrderby(1);
				articleList = articleService.queryArticleList(query);
				CacheUtil.set(CacheConstans.ARTICLE_GOOD_RECOMMEND, articleList,CacheConstans.RECOMMEND_COURSE_TIME);
			}
			request.setAttribute("articleList", articleList);
		} catch (Exception e) {
			logger.error("queryArticleRecommend()--error", e);
		}
		return getViewPath("/web/article/article-recommend");
	}
	/**
	 * 文章详情
	 */
	@RequestMapping("/articleinfo/{id}")
	public String articleInfo(HttpServletRequest request, @PathVariable("id") int id) {
		try {
			// 查询文章详情
			Article article = articleService.queryArticleById(id);
			String content = articleService.queryArticleContentByArticleId(id);
			request.setAttribute("content", content);
			request.setAttribute("article", article);
		} catch (Exception e) {
			logger.error("articleInfo()--error", e);
			return this.setExceptionRequest(request, e);
		}
		return getViewPath("/web/article/article-info");
	}

}
