<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
	<style type="text/css">
		.n-i-m-bottom{display: block}
	</style>
</head>
<body>
<!-- 模板切换 -->
<jsp:include page="/WEB-INF/view/inxedu/web/front/index_change.jsp" />
	<div class="i-slide banner-slide">
		<section>
			<!-- 如果需要导航按钮 -->
			<a class="arrow-left s-arrow" href="javascript:void(0)"></a>
			<a class="arrow-right s-arrow" href="javascript:void(0)"></a>
			<!-- 图片位置 -->
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach var="image" items="${websiteImages.type_26}" varStatus="status">
						<div class="swiper-slide">
							<a target="_blank" href="<c:if test='${image.linkAddress!=null&&image.linkAddress!=""}'>${image.linkAddress}</c:if>">
								<img class="imgload" src="${staticServer}${image.imagesUrl}" alt="${image.title}">
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- 如果需要分页器 -->
			<div class="pagination"></div>
		</section>
	</div>
	<!-- /index slide -->
	<div id="aCoursesList" class="of index-bg">
		<!-- /直播课程 开始 -->
		<c:if test="${serviceSwitch.indexLive=='ON'}">
		<div class="of bg-f8">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt20 mr10 hyh"><a href="javascript:void(0)" title="查看更多" class="c-master fsize14" onclick="window.location.href='${ctx}/front/showLivelist'">更多&gt;&gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">直播推荐</span>
					</h2>
				</header>
				<div>
					<article class="comm-course-list of demo2-i-live-list demo3-i-live-list">
						<c:if test="${empty courseDtos}">
							<!-- /无数据提示 开始-->
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
							</section>
							<!-- /无数据提示 结束-->
						</c:if>
						<c:if test="${not empty courseDtos}">
							<ul class="of">
								<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
								<c:forEach items="${courseDtos}" var="course" varStatus="index">
									<c:set var="beginDate">
										<fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd" type="date"/>
									</c:set>
									<c:set var="endDate">
										<fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd" type="date"/>
									</c:set>
									<li<%-- <c:if test="${nowDate-course.liveEndTime.time < 0}">class="current"</c:if>--%> >
										<div class="cc-l-wrap in-live-ing">
											<div class="d3-liv-in-zt">
												<c:if test="${course.liveBeginTime.time<nowDate and course.liveEndTime.time>nowDate }">
													<span class="f-fM fsize14 c-fff ing">直播中</span>
												</c:if>
												<c:if test="${course.liveBeginTime.time>nowDate and course.liveEndTime.time>nowDate }">
													<span class="f-fM fsize14 c-fff wks">未开始</span>
												</c:if>
												<c:if test="${course.liveEndTime.time <nowDate}">
													<span class="f-fM fsize14 c-fff yjs">已结束</span>
												</c:if>
											</div>
											<div class="cc-l-wrap-top">
												<section class="course-img">
													<a href="${ctx}/front/couinfo/${course.courseId}">
														<c:if test="${not empty course.courseLogo }">
															<img xsrc="${staticServer}${course.courseLogo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
														</c:if>
														<c:if test="${empty course.courseLogo }">
															<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
														</c:if>
													</a>
													<div class="d3-l-i-time">
														<span class="time-in fsize12 f-fM c-fff">
															直播时间：
															<fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd HH:mm"/>
														</span>
													</div>
													<c:if test="${course.liveBeginTime.time<nowDate and course.liveEndTime.time>nowDate }">
														<div class="in-2-mask">
															<a href="${ctx}/front/couinfo/${course.courseId}" class="f-fM zbz">直播中</a>
														</div>
													</c:if>
													<c:if test="${course.liveBeginTime.time>nowDate and course.liveEndTime.time>nowDate }">
														<div class="in-2-mask">
															<a href="${ctx}/front/couinfo/${course.courseId}" class="f-fM wks">未开始</a>
														</div>
													</c:if>
													<c:if test="${course.liveEndTime.time <nowDate}">
														<div class="in-2-mask">
															<a href="${ctx}/front/couinfo/${course.courseId}" class="f-fM yjs">已结束</a>
														</div>
													</c:if>
												</section>
												<section class="d3-lv-in-b">
													<h3 class="hLh30 txtOf mt5">
														<a class="course-title fsize16 c-333" title="${course.courseName }" href="${ctx}/front/couinfo/${course.courseId}">${course.courseName }</a>
													</h3>
													<section class="mt10 hLh20 of">
														<span class="fr"><tt class=" fsize14 f-fG  c-master">￥${course.currentPrice}</tt></span>
														<span class="fl jgAttr c-ccc f-fA">
															<tt class="c-999 f-fA">${course.courseBuycount}人购买</tt>
															<tt class="c-999 f-fA line">|</tt>
															<tt class="c-999 f-fA span-2">${course.courseBuycount}人浏览</tt>
														</span>
													</section>
												</section>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</article>
				</div>
			</section>
		</div>
		</c:if>
		<!-- /直播课程 结束 -->
		<!--/分类课程开始  -->
		<c:forEach items="${indexSubjectCourseList}" var="subject" varStatus="index">
			<c:if test="${index.index<3}">
				<%--循环次数--%>
				<c:set value="${index.index}" var="i"/>
				<div class="of <c:if test="${index.index%2==0}">bg-fff</c:if> <c:if test="${index.index%2!=0}">bg-f8</c:if>  ">
					<section class="container">
						<header class="comm-title">
							<div class="live-small-tit">
								<span class="fr mt15 mr10 hyh">
									<a class="c-master" href="${ctx}/front/showcoulist?queryCourse.subjectId=${subject.subjectId}" title="更多" onclick="">更多&gt;&gt;</a>
								</span>
								<h2 class="fl tac tit-header">
									<span class="c-master">${subject.subjectName}</span>
								</h2>
								<ul class="clearfix">
									<li class="current">
										<a href="javascript:void(0)" onclick="selectSubject(${subject.subjectId},this)">全部</a>
									</li>
									<c:forEach items="${subject.subjectList}" var="childSubject" varStatus="index">
										<li>
											<a href="javascript:void(0)"onclick="selectSubject(${childSubject.subjectId},this)">${childSubject.subjectName}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</header>
							<section class="live-moblie-fl">
								<c:forEach items="${subject.subjectList}" var="childSubject">
									<a <c:if test="${index.index==0}">class="current"</c:if> onclick="selectSubject(${childSubject.subjectId},this)" href="javascript:void(0)">${childSubject.subjectName}</a>
								</c:forEach>
                            </section>
						<article class="live-no-title">
							<div class="comm-course-list of">
								<div class="com-live-no-box">
									<div class="c-l-n-left">
										<ul>
											<li class="liv-fist-box">
												<div class="d3-left-pic">
													<%--i为循环次数--%>
													<img src="${staticServer}${images[i].imagesUrl}">
												</div>
											</li>
										</ul>
									</div>
									<div class="c-l-n-right d3-in-right">
										<c:if test="${empty subject.subjectList}">
											<!-- /无数据提示 开始-->
											<section class="no-data-wrap">
												<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
											</section>
											<!-- /无数据提示 结束-->
										</c:if>
										<c:if test="${not empty subject.subjectList}">
											<%--专业下所有的课程--%>
											<ul class="clearfix subject${subject.subjectId}">
													<%--子课程--%>
												<c:forEach items="${subject.courseDtoList}" var="course" varStatus="index">
														<li class="">
															<div class="cc-l-wrap">
																<section class="course-img">
																	<a href="${ctx}/front/couinfo/${course.courseId}">
																		<c:if test="${not empty course.logo }">
																			<img src="${staticServer}${course.logo }" class="img-responsive" alt="${course.courseName }">
																		</c:if>
																		<c:if test="${empty course.logo }">
																			<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
																		</c:if>
																	</a>
																</section>
																<div class="bg-f8 coure-bg-g">
																	<h3 class="hLh30 txtOf">
																		<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/${course.courseId}" title="${course.courseName }">${course.courseName }</a>
																	</h3>
																	<section class="mt10 hLh20 of">
																<span class="fr">
																	<c:if test="${course.currentPrice=='0.00' }">
																		<tt class="c-green fsize12 f-fA">免费</tt>
																	</c:if>
																	<c:if test="${course.currentPrice!='0.00' }">
																		<tt class="c-master fsize14 f-fM">￥${course.currentPrice }</tt>
																	</c:if>
																</span>
																<span class="fl jgAttr c-ccc f-fA">
																	<tt class="c-999 f-fA mr10">${course.pageBuycount }人购买</tt>
																	<tt class="c-999 f-fA">${course.pageViewcount }浏览</tt>
																</span>
																	</section>
																</div>
															</div>
														</li>
												</c:forEach>
											</ul>
												<%--子专业 下的所有课程--%>
												<c:forEach items="${subject.subjectList}" var="childSubject" varStatus="index">
													<ul style="display:none" class="clearfix subject${childSubject.subjectId}">
													<%--子课程--%>
													<c:forEach items="${childSubject.courseDtoList}" var="course" varStatus="index">
                                                        <c:if test="${index.index<6}">
														<li class="">
															<div class="cc-l-wrap">
																<section class="course-img">
																	<a href="${ctx}/front/couinfo/${course.courseId}">
																		<c:if test="${not empty course.logo }">
																			<img src="${staticServer}${course.logo }" class="img-responsive" alt="${course.courseName }">
																		</c:if>
																		<c:if test="${empty course.logo }">
																			<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
																		</c:if>
																	</a>
																</section>
																<div class="bg-f8 coure-bg-g">
																	<h3 class="hLh30 txtOf">
																		<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/${course.courseId}" title="${course.courseName }">${course.courseName }</a>
																	</h3>
																	<section class="mt10 hLh20 of">
																<span class="fr">
																	<c:if test="${course.currentPrice=='0.00' }">
																		<tt class="c-green fsize12 f-fA">免费</tt>
																	</c:if>
																	<c:if test="${course.currentPrice!='0.00' }">
																		<tt class="c-master fsize14 f-fM">￥${course.currentPrice }</tt>
																	</c:if>
																</span>
																<span class="fl jgAttr c-ccc f-fA">
																	<tt class="c-999 f-fA mr10">${course.pageBuycount }人购买</tt>
																	<tt class="c-999 f-fA">${course.pageViewcount }浏览</tt>
																</span>
																	</section>
																</div>
															</div>
														</li>
                                                        </c:if>
													</c:forEach>
													</ul>
												</c:forEach>

										</c:if>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
						</article>
					</section>
				</div>
			</c:if>
		</c:forEach>
		<!--分类课程结束  -->
		<!-- /网校名师 开始 -->
		<div class="of bg-f8">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/teacherlist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">名家讲师</span>
					</h2>
				</header>
				<article class="live-no-title">
					<div class="demo-teach-warp">
						<ul class="clearfix demo-teach-list">
							<c:forEach items="${teacherList}" var="teacher" varStatus="index">
								<li>
									<div class="demo-t-l-warp d3-t-l-warp">
										<a class="pic" href="${ctx}/front/teacher/${teacher.id}" title="${teacher.name}" target="_self">
											<c:if test="${not empty teacher.picPath&&teacher.picPath!=''}">
												<img alt="${teacher.name}" src="${ctx}/static/inxweb/img/avatar-boy.gif" xsrc="${staticServer}${teacher.picPath}">
											</c:if>
											<c:if test="${empty teacher.picPath||teacher.picPath==''}">
												<img alt="${teacher.name}" src="${ctx}/static/inxweb/img/avatar-boy.gif" xsrc="${ctx}/static/inxweb/img/avatar-boy.gif">
											</c:if>
										</a>
										<h4 class="hLh30 tac txtOf unFw">
											<a class="name fsize18 c-333 f-fM" href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
										</h4>
										<input value="1" type="hidden">
										<h5 class="hLh30 fsize14 c-999 f-fM tac txtOf unFw">${teacher.education }</h5>
										<div class="mt15 i-q-txt">
											<input value="2" type="hidden">
											<p class="c-999 f-fA">${teacher.career }</p>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</article>
			</section>
		</div>
		<!-- /网校名师 结束 -->
		<div class="bg-fff of">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/articlelist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">新闻资讯</span>
					</h2>
				</header>
				<section class="in-new-box clerfix">
					<div class="clearfix d2-in-news-warp mt30">
						<c:if test="${empty leftarticleList}">
							<!-- /无数据提示 开始-->
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
							</section>
							<!-- /无数据提示 结束-->
						</c:if>
						<c:if test="${not empty leftarticleList}">
							<article class="d2-i-ns-left">
								<div class="d2-i-n-l-box of d3-i-n-l-box">
									<ul class="clearfix">
										<c:forEach items="${leftarticleList}" var="article" varStatus="index">
											<li>
												<div class="inwarp">
													<a class="pic-a-n d3-pic-a-n" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">
														<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif"  alt="${article.title}">
													</a>
													<a class="d3-name-news-in" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">
														<span class="txtOf f-fM dis c-333 fsize16 mt5">${article.title}</span>
													</a>
													<div class="d3-nr-news-in mt5">
														<p class="c-999 f-fM">${article.summary }</p>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</article>
						</c:if>
						<article class="d2-i-ns-right">
							<c:if test="${empty rightarticleList}">
								<!-- /无数据提示 开始-->
								<section class="no-data-wrap">
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
								</section>
								<!-- /无数据提示 结束-->
							</c:if>
							<c:if test="${not empty rightarticleList}">
								<div class="d2-i-n-r-box">
									<section class="d2-i-nr-body">
										<ul class="d2-i-nr-b-list d3-i-nr-b-list">
											<c:forEach items="${rightarticleList}" var="article" varStatus="index">
												<li class="li-1">
													<div class="li-1-in">
														<em class="d2-b-l-ico">&nbsp;</em>
														<a href="${ctx}/front/articleinfo/${article.articleId}" title="" class="name f-fM c-666">${article.title}</a>
														<span class="time f-fM c-666"><fmt:formatDate value="${article.publishTime }" pattern="yyyy-MM-dd" /></span>
													</div>
												</li>
											</c:forEach>
										</ul>
									</section>
								</div>
							</c:if>
						</article>
					</div>
				</section>
			</section>
		</div>
	</div>
	<input type="hidden" id="theme_color" value="${theme_color}"/>
	<script type="text/javascript" src="${ctx}/static/inxweb/js/swiper-2.1.0.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index3.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index_theme_color.js"></script><!-- 换肤 -->
</body>
</html>