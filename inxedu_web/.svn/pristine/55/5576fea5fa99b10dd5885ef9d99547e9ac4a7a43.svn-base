<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>资讯列表</title>
</head>
<body>
	<div id="aCoursesList" class="bg-fa of">
		<section class="container">
			<section class="mt30 article-new-title clearfix">
				<c:set var="article" value="${articleListRecommend[0]}"/>
				<article class="a-n-l-box fl w50pre">
					<div class="mr5">
						<div class="big-pic pic-box">
							<div class="nr-infor">
								<a href="${ctx}/front/articleinfo/${article.articleId}">
									<section class="n-i-sebox">
										<h5 class="hLh30 fisze14 c-fff f-fM txtOf ml10 mr10 unFw">内容摘要：</h5>
										<div class="nr-i-zy">
											<p class="c-fff fsize14">
												${article.summary }.....
											</p>
										</div>
										<p class="nr-i-zy-btn tac">
											查看详情
										</p>
									</section>
								</a>
							</div>
							<a href="${ctx}/front/articleinfo/${article.articleId}" class="pic-a-n">
								<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif" alt="${article.title}">
							</a>
							<a href="${ctx}/front/articleinfo/${article.articleId}" class="name-a-n">
								<span class="txtOf">${article.title}</span>
							</a>
						</div>
					</div>
				</article>
				<article class="a-n-r-box fl w50pre">
					<div class="ml10">
						<div class="small-pic clearfix mb10">
							<c:forEach var="article" items="${articleListRecommend }" varStatus="index">
								<c:if test="${index.index>=1 and index.index<=2}">
									<div class="w50pre fl">
										<div class="mr5 pic-box">
											<div class="nr-infor">
												<a href="${ctx}/front/articleinfo/${article.articleId}">
													<section class="n-i-sebox">
														<h5 class="hLh30 fisze14 c-fff f-fM txtOf ml10 mr10 unFw">内容摘要：</h5>
														<div class="nr-i-zy">
															<p class="c-fff fsize14">
																${article.summary }.....
															</p>
														</div>
														<p class="nr-i-zy-btn tac">
															查看详情
														</p>
													</section>
												</a>
											</div>
											<a href="${ctx}/front/articleinfo/${article.articleId}" class="pic-a-n">
												<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif" alt="${article.title}">
											</a>
											<a href="${ctx}/front/articleinfo/${article.articleId}" class="name-a-n">
												<span class="txtOf">${article.title}</span>
											</a>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<%--<div class="w50pre fl">
								<div class="ml5 pic-box">
									<div class="nr-infor">
										<a href="">
											<section class="n-i-sebox">
												<h5 class="hLh30 fisze14 c-fff f-fM txtOf ml10 mr10 unFw">内容摘要：</h5>
												<div class="nr-i-zy">
													<p class="c-fff fsize14">
														${article.summary }.....
													</p>
												</div>
												<p class="nr-i-zy-btn tac">
													查看详情
												</p>
											</section>
										</a>
									</div>
									<a href="" class="pic-a-n">
										<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif">
									</a>
									<a href="" class="name-a-n">
										<span class="txtOf">${article.title}</span>
									</a>
								</div>
							</div>--%>
						</div>
						<div class="small-pic clearfix">
							<c:forEach var="article" items="${articleListRecommend }" varStatus="index">
								<c:if test="${index.index>=3 and index.index<=4}">
									<div class="w50pre fl">
										<div class="mr5 pic-box">
											<div class="nr-infor">
												<a href="${ctx}/front/articleinfo/${article.articleId}">
													<section class="n-i-sebox">
														<h5 class="hLh30 fisze14 c-fff f-fM txtOf ml10 mr10 unFw">内容摘要：</h5>
														<div class="nr-i-zy">
															<p class="c-fff fsize14">
																	${article.summary }.....
															</p>
														</div>
														<p class="nr-i-zy-btn tac">
															查看详情
														</p>
													</section>
												</a>
											</div>
											<a href="${ctx}/front/articleinfo/${article.articleId}" class="pic-a-n">
												<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif" alt="${article.title}">
											</a>
											<a href="${ctx}/front/articleinfo/${article.articleId}" class="name-a-n">
												<span class="txtOf">${article.title}</span>
											</a>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<%--<div class="w50pre fl">
								<div class="mr5 pic-box">
									<div class="nr-infor">
										<a href="">
											<section class="n-i-sebox">
												<h5 class="hLh30 fisze14 c-fff f-fM txtOf ml10 mr10 unFw">内容摘要：</h5>
												<div class="nr-i-zy">
													<p class="c-fff fsize14">
														上个周日的北京天气闷热，预谋着一场阵雨。即便是休息日的早晨，中关村依旧车水马龙，在中关村创业公社·亭基地虚拟现实加速器里，科技爱好者们坐在椅上地上楼梯上，聆听几位VR行业大咖的关于虚拟现实未来的辩论。  身着蓝色衬衣，领口别着蓝色墨镜的北大心理学系教授、博导魏坤琳，面带微笑认真听着台上Worldviz创始人Andrew Beall的发言，不时陷入思索。  因在《最强大脑》上的犀利点.....
													</p>
												</div>
												<p class="nr-i-zy-btn tac">
													查看详情
												</p>
											</section>
										</a>
									</div>
									<a href="" class="pic-a-n">
										<img src="${ctx}/images/upload/article/20150928/1443424120422.jpg">
									</a>
									<a href="" class="name-a-n">
										<span class="txtOf">资讯名字名字在</span>
									</a>
								</div>
							</div>
							<div class="w50pre fl">
								<div class="ml5 pic-box">
									<div class="nr-infor">
										<a href="">
											<section class="n-i-sebox">
												<h5 class="hLh30 fisze14 c-fff f-fM txtOf ml10 mr10 unFw">内容摘要：</h5>
												<div class="nr-i-zy">
													<p class="c-fff fsize14">
														上个周日的北京天气闷热，预谋着一场阵雨。即便是休息日的早晨，中关村依旧车水马龙，在中关村创业公社·亭基地虚拟现实加速器里，科技爱好者们坐在椅上地上楼梯上，聆听几位VR行业大咖的关于虚拟现实未来的辩论。  身着蓝色衬衣，领口别着蓝色墨镜的北大心理学系教授、博导魏坤琳，面带微笑认真听着台上Worldviz创始人Andrew Beall的发言，不时陷入思索。  因在《最强大脑》上的犀利点.....
													</p>
												</div>
												<p class="nr-i-zy-btn tac">
													查看详情
												</p>
											</section>
										</a>
									</div>
									<a href="" class="pic-a-n">
										<img src="${ctx}/images/upload/article/20150928/1443424120422.jpg">
									</a>
									<a href="" class="name-a-n">
										<span class="txtOf">资讯名字名字在</span>
									</a>
								</div>
							</div>--%>
						</div>
					</div>
				</article>
			</section>
			<section class="i-article mt30">
				<div class="fl col-7">
					<section class="bg-fff artil-list-box">
						<header class="comm-title all-article-title new-a-tit-r">
							<h2 class="fl tac">
								<span class="c-333">资讯列表</span>
							</h2>
							<%--<section class="c-tab-title">
                                <a href="javascript: void(0)">&nbsp;</a>
                            </section>--%>
						</header>
						<!-- /无数据提示 开始-->
						<c:if test="${empty articleList }">
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
							</section>
						</c:if>
						<!-- /无数据提示 结束-->
						<article class="i-article-list">
							<!-- /资讯列表 开始-->
							<ul>
								<c:if test="${not empty articleList }">
									<c:forEach var="article" items="${articleList }">
										<li>
											<aside class="i-article-pic">
												<a href="${ctx }/front/articleinfo/${article.articleId}" title="${article.title }" target="_blank">
													<img xSrc="${staticServer}${article.imageUrl }" src="${ctx }/static/inxweb/img/coures.gif" alt="${article.title }">
												</a>
											</aside> <!-- ${ctx }/web/article/showInfor/${article.articleId } -->
											<h3 class="hLh30 txtOf unFw">
												<a href="${ctx }/front/articleinfo/${article.articleId}" target="_blank" title="${article.title }" class="i-art-title">${article.title }</a>
											</h3>
											<section class="i-q-txt mt10">
												<p>
													<span class="c-999 f-fA">${article.summary }</span>
												</p>
											</section>
											<section class="hLh20 mt10 pr10 a-list-extrainfo clearfix">
												<span class="fr">
													<em class="icon18 i-n-xqtime">&nbsp;</em>
													<tt class="c-999 f-fM">
														<!-- 1小时前发布 -->
														<fmt:formatDate value="${article.publishTime }" pattern="yyyy-MM-dd HH:mm" />
													</tt>
												</span>
												<span class="fl">
													<a class="noter-dy mr30 c-999 f-fM" title="回答" href="${ctx }/front/articleinfo/${article.articleId}">
														<em class="icon18">&nbsp;</em>
														<tt class="vam fsize12 f-fM">( ${article.commentNum } )</tt>
													</a>
													<a href="javascript: void(0);" title="赞一下" class="noter-zan f-fM c-999" onclick="addPraise('${article.articleId}',3)">
														<em class="icon18">&nbsp;</em>
														<tt class="addPraise${article.articleId}_3 praiseCount vam fsize12 f-fM">( ${article.praiseCount } )</tt>
													</a>
												</span>
											</section>
										</li>
									</c:forEach>
								</c:if>
							</ul>
							<!-- /资讯列表 结束-->
						</article>
						<!-- 公共分页 开始 -->
						<form action="${ctx }/front/articlelist" method="post" id="searchForm">
							<input type="hidden" name="page.currentPage" id="pageCurrentPage" value="1">
							<input type="hidden" name="queryArticle.queryKey" id="articleQueryKey" value="${queryArticle.queryKey}" />
						</form>
						<div>
							<jsp:include page="/WEB-INF/view/common/front_page.jsp"></jsp:include>
							<div class="clear"></div>
						</div>
						<!-- 公共分页 结束 -->
					</section>
				</div>
				<!-- 好文推荐 -->
				<aside class="fl col-3 articleRecommend"></aside>
				<div class="clear"></div>
			</section>
		</section>
		<!-- /资讯列表 结束 -->
	</div>
	<script src="${ctx }/static/inxweb/acticle/acticle-list.js" type="text/javascript"></script>
</body>
</html>