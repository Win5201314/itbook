<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>直播首页</title>
	<script type="text/javascript" src="${ctx}/static/inxweb/js/swiper-2.1.0.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/live/live-index.js"></script>
	<style type="text/css">
		.n-i-m-bottom{display: block}
	</style>
</head>
<body>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<div class="i-slide banner-slide">
	<section>
		<!-- 如果需要导航按钮 -->
		<a class="arrow-left s-arrow" href="javascript:void(0)"></a>
		<a class="arrow-right s-arrow" href="javascript:void(0)"></a>
		<!-- 图片位置 -->
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach var="image" items="${websiteImages.type_18}" varStatus="status">
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
<div id="aCoursesList" class="of">
    <!-- live course 开始 -->
	<!-- /直播课程 开始 -->
	<div class="of bg-fff">
		<section class="container">
			<header class="comm-title">
					<span class="fr mt15 mr10 hyh">
						<a class="c-master" href="${ctx}/front/showLivelist" title="更多" onclick="">更多</a>
					</span>
				<h2 class="fl tac tit-header">
					<span class="c-333">近期直播</span>
				</h2>
			</header>
			<div>
				<article class="comm-course-list of demo2-i-live-list">
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
											<section class="">
												<h3 class="hLh30 txtOf mt5">
													<a class="course-title fsize16 c-333" title="${course.courseName }" href="${ctx}/front/couinfo/${course.courseId}">${course.courseName }</a>
												</h3>
												<section class="mt10 hLh20 of">
													<span class="fr"><tt class=" fsize14 f-fG  c-master">￥${course.currentPrice}</tt></span>
														<span class="fl jgAttr c-ccc f-fA">
															<tt class="c-999 f-fA">学员： ${course.courseBuycount}人</tt>
														</span>
												</section>
											</section>
										</div>
										<div class="cc-l-wrap-bottom">
											<div class="in-live-line pr">
												<c:if test="${course.liveBeginTime.time<nowDate and course.liveEndTime.time>nowDate }">
													<a href="${ctx}/front/couinfo/${course.courseId}" class="pa d2-live-ing d2-live-pic">
														<img src="${ctx}/static/inxweb/img/d2-live-ing.gif">
													</a>
												</c:if>
												<c:if test="${course.liveBeginTime.time>nowDate and course.liveEndTime.time>nowDate }">
													<a href="${ctx}/front/couinfo/${course.courseId}" class="pa d2-live-wks d2-live-pic">
														<img src="${ctx}/static/inxweb/img/d2-live-wks.png">
													</a>
												</c:if>
												<c:if test="${course.liveEndTime.time <nowDate}">
													<a href="${ctx}/front/couinfo/${course.courseId}" class="pa d2-live-yjs d2-live-pic">
														<img src="${ctx}/static/inxweb/img/d2-live-yjs.png">
													</a>
												</c:if>
											</div>
											<section class="clearfix  pl10 pr10 mt20">
												<div class="fl">
													<span class="c-666 fsize14 f-fM vam">主讲:</span>
													<span class="c-666 fsize14 f-fM vam">
														<c:forEach items="${course.teacherList}" var="teacherList" varStatus="index">
															<c:if test="${index.index==0}">
																${teacherList.name}
															</c:if>
															<c:if test="${index.index!=0 && index.index<2}">
																/${teacherList.name}
															</c:if>
														</c:forEach>
																<c:if test="${course.teacherList.size()>2}">
																	...
																</c:if>
													</span>
												</div>
												<div class="fr">
													<span class="c-666 fsize14 f-fM vam"><fmt:formatDate value="${course.liveBeginTime}" pattern="MM-dd"/>---<fmt:formatDate value="${course.liveEndTime}" pattern="MM-dd"/></span>
												</div>
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
	<!-- live course 手机端结束 -->
	<div class="bg-f8 of mt30">
		<section class="container">
			<header class="comm-title">
				<span class="fr mt15 mr10 hyh">
					<a class="c-master" href="${ctx}/front/showLivelist" title="更多" onclick="">更多</a>
				</span>
				<h2 class="fl tac tit-header">
					<span class="c-333">直播推荐</span>
				</h2>
			</header>
			<c:if test="${empty liveRecommend}">
				<!-- /无数据提示 开始-->
				<section class="no-data-wrap">
					<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
				</section>
				<!-- /无数据提示 结束-->
			</c:if>
			<c:if test="${not empty liveRecommend}">
				<article>
					<div class="comm-course-list of in-n-lv-tj">
						<ul>
							<c:forEach items="${liveRecommend}" var="course" varStatus="index">
								<li>
									<div class="cc-l-wrap">
										<section class="course-img">
											<a href="${ctx}/front/couinfo/${course.courseId}">
												<c:if test="${not empty course.logo }">
													<img xsrc="${staticServer}${course.logo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
												</c:if>
												<c:if test="${empty course.logo }">
													<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
												</c:if>
											</a>
										</section>
										<div class="coure-bg-g">
											<h3 class="hLh30 txtOf mt5 in-live-name">
												<a class="course-title fsize16 c-333" title="${course.courseName }" href="${ctx}/front/couinfo/${course.courseId}">${course.courseName }</a>
												<a class="live-zt live-zt-ing" href="${ctx}/front/couinfo/${course.courseId}">
													<c:if test="${course.courseKpointList[0].liveBeginTime.time<nowDate and course.courseKpointList[0].liveEndTime.time>nowDate }"><img src="${ctx}/static/inxweb/img/live-ing.gif"></c:if>
													<c:if test="${course.courseKpointList[0].liveBeginTime.time>nowDate and course.courseKpointList[0].liveEndTime.time>nowDate }"><img src="${ctx}/static/inxweb/img/live-wks.png"></c:if>
													<c:if test="${course.courseKpointList[0].liveEndTime.time <nowDate}"><img src="${ctx}/static/inxweb/img/live-over.png"></c:if>
												</a>
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
												<tt class="c-999 f-fA">${pageViewcount.pageViewcount}浏览</tt>
											</span>
											</section>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</article>
			</c:if>
		</section>
	</div>
	<div class="bg-fff of">
		<section class="container">
			<header class="comm-title">
				<div class="live-small-tit">
					<span class="fr mt15 mr10 hyh">
						<a class="c-master" href="${ctx}/front/showLivelist" title="更多" onclick="">更多</a>
					</span>
					<h2 class="fl tac tit-header">
						<span class="c-333">最新直播</span>
					</h2>
					<%--<ul class="clearfix">
						<li class="current">
							<a href="">分类一</a>
						</li>
						<li class="">
							<a href="">分类三</a>
						</li>
						<li class="">
							<a href="">分类二</a>
						</li>
					</ul>--%>
				</div>
			</header>
			<%--<section class="live-moblie-fl">
				<a href="" class="current">分类一</a>
				<a href="">分类三</a>
				<a href="">分类二</a>
			</section>--%>
			<article class="live-no-title">
				<div class="comm-course-list of">
					<div class="com-live-no-box">
						<div class="c-l-n-left">
							<ul>
								<li class="liv-fist-box">
									<div class="cc-l-wrap" style="background:url('${websiteImages.type_22[0].imagesUrl}') ">
									</div>
								</li>
							</ul>
						</div>
						<div class="c-l-n-right">
							<c:if test="${empty newcourseList}">
								<!-- /无数据提示 开始-->
								<section class="no-data-wrap">
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
								</section>
								<!-- /无数据提示 结束-->
							</c:if>
							<c:if test="${not empty newcourseList}">
								<ul class="clearfix">
									<c:forEach items="${newcourseList}" var="course" varStatus="index">
										<li>
											<div class="cc-l-wrap">
												<section class="course-img">
													<a href="${ctx}/front/couinfo/${course.courseId}">
														<c:if test="${not empty course.logo }">
															<img xsrc="${staticServer}${course.logo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
														</c:if>
														<c:if test="${empty course.logo }">
															<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
														</c:if>
													</a>
												</section>
												<div class="coure-bg-g">
													<h3 class="hLh30 txtOf mt5">
														<a class="course-title fsize16 c-333" title="${course.courseName }" href="${ctx}/front/couinfo/${course.courseId}">${course.courseName }</a>
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
															<tt class="c-999 f-fA">${course.pageViewcount}浏览</tt>
														</span>
													</section>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</article>
		</section>
	</div>
	<div class="bg-f8 of mt30">
		<section class="container">
			<header class="comm-title">
				<div class="live-small-tit">
					<span class="fr mt15 mr10 hyh">
						<a class="c-master" href="${ctx}/front/showLivelist" title="更多" onclick="">更多</a>
					</span>
					<h2 class="fl tac tit-header">
						<span class="c-333">专业课程</span>
					</h2>
					<ul class="clearfix">
						<li class="current" id="subjectId0" onclick="liveRecommended(0,this)">
							<a href="javascript:void(0)">全部</a>
						</li>
						<c:forEach items="${subjectList}" var="subject" varStatus="index">
							<c:if test="${index.index<4}">
								<li onclick="liveRecommended(${subject.subjectId},this)">
									<a href="javascript:void(0)">${subject.subjectName}</a>
								</li>
							</c:if>
						</c:forEach>
						<%--<li class="current">
							<a href="">分类一</a>
						</li>
						<li class="">
							<a href="">分类三</a>
						</li>
						<li class="">
							<a href="">分类二</a>
						</li>--%>
					</ul>
				</div>
			</header>
			<section class="live-moblie-fl">
				<a id="subjectId0mobile" href="javascript:void(0)" onclick="liveRecommended(0,this)">全部</a>
				<c:forEach items="${subjectList}" var="subject" varStatus="index">
					<c:if test="${index.index<4}">
						<a href="javascript:void(0)" onclick="liveRecommended(${subject.subjectId},this)">${subject.subjectName}</a>
					</c:if>
				</c:forEach>
			</section>
			<article class="live-no-title">
				<div class="comm-course-list of">
					<div class="com-live-ph-box">
						<div class="c-l-n-right" id="liveRecommend">

						</div>
						<div class="c-l-n-phb">
							<div class="c-l-phb-tit clearfix">
								<a href="javascript:void(0)" class="current">免费排行</a>
								<a href="javascript:void(0)">收费排行</a>
							</div>
							<div class="cl-phb">
								<div class="c-l-phb-box">
									<ul>
										<c:forEach items="${freecourseList}" var="course" varStatus="index">
											<li>
												<span class="phb-in <c:if test="${index.index<3}">phb-top</c:if> ">
													${index.index+1}
												</span>
												<a href="${ctx}/front/couinfo/${course.courseId}" class="hLh30 txtOf fsize16 c-666 f-fM ">${course.courseName}</a>
												<div class="hLh20 of">
													<span class="fsize12 f-fM c-999">
														<c:if test="${not empty course.courseKpointList[0].liveBeginTime}">
															<fmt:formatDate value="${course.courseKpointList[0].liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/> 开播
														</c:if>
														<c:if test="${empty course.courseKpointList[0].liveBeginTime}">
															没有直播
														</c:if>
													</span>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
								<div class="c-l-phb-box undis">
									<ul>
										<c:forEach items="${chargecourseList}" var="course" varStatus="index">
											<li>
												<span class="phb-in <c:if test="${index.index<3}">phb-top</c:if>">
														${index.index+1}
												</span>
												<a href="${ctx}/front/couinfo/${course.courseId}" class="hLh30 txtOf fsize16 c-666 f-fM ">${course.courseName}</a>
												<div class="hLh20 of">
													<span class="fsize12 f-fM c-999">
														<c:if test="${not empty course.courseKpointList[0].liveBeginTime}">
															<fmt:formatDate value="${course.courseKpointList[0].liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/> 开播
														</c:if>
														<c:if test="${empty course.courseKpointList[0].liveBeginTime}">
															没有直播
														</c:if>
													</span>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</article>
		</section>
	</div>
	<div class="bg-fff of">
		<section class="container">
			<header class="comm-title">
					<span class="fr mt15 mr10 hyh">
						<a class="c-master" href="${ctx}/front/teacherlist" title="更多" onclick="">更多</a>
					</span>
				<h2 class="fl tac tit-header">
					<span class="c-333">名师直播</span>
				</h2>
			</header>
			<article class="live-no-title">
				<div class="demo-teach-warp">
					<ul class="clearfix demo-teach-list">
						<c:forEach items="${teacherList}" var="teacher" varStatus="index">
							<li>
								<div class="demo-t-l-warp">
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
	<%--<section class="container">
		<div class="live-cou-nav">
			<ul class="clearfix">
				<li id="subjectId0"><a title="全部" href="javascript:void(0)" onclick="liveRecommended(0)">全部</a></li>
				<c:forEach items="${subjectList}" var="subject">
					<li id="subjectId${subject.subjectId}"><a title="${subject.subjectName}" href="javascript:void(0)" onclick="liveRecommended(${subject.subjectId})">${subject.subjectName}</a></li>
				</c:forEach>
			</ul>
			<div class="live-title"><span><em class="icon12"></em></span><tt class="c-fff fsize14 f-fM">推荐直播</tt></div>
			<div class="live-more"><a class="fsize14 c-redfz f-fM" href="${ctx}/front/showLivelist">更多</a></div>
		</div>
		<div class="live-cou-box" id="liveRecommend">

		</div>
	</section>--%>
</div>
</body>
</html>