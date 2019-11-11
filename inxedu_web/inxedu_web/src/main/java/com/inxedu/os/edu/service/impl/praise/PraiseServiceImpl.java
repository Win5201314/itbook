package com.inxedu.os.edu.service.impl.praise;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.edu.dao.praise.PraiseDao;
import com.inxedu.os.edu.entity.praise.Praise;
import com.inxedu.os.edu.service.article.ArticleService;
import com.inxedu.os.edu.service.comment.CommentService;
import com.inxedu.os.edu.service.praise.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 点赞服务接口实现
 *@author www.inxedu.com
 */
@Service("praiseService")
public class PraiseServiceImpl implements PraiseService {

	@Autowired
	private PraiseDao praiseDao;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	
	@Override
	public Long addPraise(Praise praise) {
		//根据点赞目标 type 修改相应的 点赞总数
		int type=praise.getType();
		//添加点赞记录
		praise.setAddTime(new Date());
		Map<String,String> map = new HashMap<String,String>();
		map.put("num","+1");
		map.put("type", "praiseCount");
		//点赞类型为3的是文章点赞
		if(type==3){
			map.put("articleId", praise.getTargetId()+"");
			articleService.updateArticleNum(map);
		}
		//点赞类型为4的是评论点赞
		if(type==4){
			map.put("commentId", praise.getTargetId()+"");
			commentService.updateCommentNum(map);
		}


		//清除文章排行缓存
		CacheUtil.remove(CacheConstans.ARTICLE_GOOD_RECOMMEND);
		return praiseDao.addPraise(praise);
	}

	@Override
	public int queryPraiseCount(Praise praise) {
		return praiseDao.queryPraiseCount(praise);
	}

}
