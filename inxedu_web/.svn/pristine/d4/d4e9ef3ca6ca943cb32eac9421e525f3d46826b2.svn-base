package com.inxedu.os.marketing.controller.template.article;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.article.Article;
import com.inxedu.os.edu.entity.article.QueryArticle;
import com.inxedu.os.edu.service.article.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/23.
 */
@Controller
@RequestMapping("/webapp")
public class ArticleMsgController extends BaseController {
    private static Logger logger=Logger.getLogger(ArticleMsgController.class);

    @Autowired
    private ArticleService articleService;

   /* 返回首页的文章信息*/
    @RequestMapping("/article")
    @ResponseBody
    public Map<String, Object> getArticleList(HttpServletRequest request,int size){
        Map<String, Object> json=new HashMap<String, Object>();
        try {
            PageEntity page = new PageEntity();
            QueryArticle queryArticle = new QueryArticle();
            page.setPageSize(size);
            List<Article> articleList = articleService.queryArticlePage(queryArticle, page);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            json = this.setJson(true,"",articleList);
        }catch (Exception e){
            json = this.setJson(false, "异常", null);
            logger.error("getArticleList()--error",e);
        }
        return json;
    }
    /* 返回首页的课程信息*/
    @RequestMapping("/getArticle")
    @ResponseBody
    public Map<String, Object> getArticle(HttpServletRequest request,int articleId){
        Map<String, Object> json=new HashMap<String, Object>();
        try {
            PageEntity page = new PageEntity();
            Article article = new Article();
            article = articleService.queryArticleById(articleId);
            json = this.setJson(true,"",article);
        }catch (Exception e){
            json = this.setJson(false, "异常", null);
            logger.error("getTeacherList()--error",e);
        }
        return json;
    }
    /**
     * 查询好文推荐（文章按浏览书排行）
     */
    @RequestMapping("/recommend")
    @ResponseBody
    public Map<String, Object> queryArticleRecommend(HttpServletRequest request,int num) {
        Map<String, Object> json=new HashMap<String, Object>();
        try {
            List<Article> articleList= new ArrayList<>();
            QueryArticle query = new QueryArticle();
            query.setCount(num);
            query.setOrderby(1);
            articleList = articleService.queryArticleList(query);
            json = this.setJson(true,"",articleList);
        } catch (Exception e) {
            json = this.setJson(false, "异常", null);
            logger.error("queryArticleRecommend()--error",e);
        }
        return json;
    }
    /**
     * 查询好文推荐（文章按浏览书排行）
     */
    @RequestMapping("/toArticle")
    @ResponseBody
    public Map<String, Object> toArticle(HttpServletRequest request,int articleId) {
        Map<String, Object> json=new HashMap<String, Object>();
        try {
            Article article = new Article();
            article = articleService.queryArticleById(articleId);

            json = this.setJson(true,articleService.queryArticleContentByArticleId(articleId),article);
        } catch (Exception e) {
            json = this.setJson(false, "异常", null);
            logger.error("queryArticleRecommend()--error",e);
        }
        return json;
    }
}
