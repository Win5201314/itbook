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
					<c:forEach var="image" items="${websiteImages.type_25}" varStatus="status">
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
	<div id="aCoursesList" class="of bg-fa index-bg">
		<!-- /为你推荐 开始 -->
		<div class="of bg-fff">
			<section class="container">
				<header class="comm-title comm-title-demo2">
					<span class="fr mt20 mr10 hyh"><a href="javascript:void(0)" title="换一换" class="c-master fsize14" onclick="huanyihuan(4)">换一换<em class="d2-hyh-ico icon16 ml5">&nbsp;</em></a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">精品课程</span>
					</h2>
				</header>
				<article class="comm-course-list">
					<c:if test="${empty mapCourseList.recommen_32}">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty mapCourseList.recommen_32}">
						<%--显示几层--%>
						<c:set var="layerNum" value="${mapCourseList.recommen_32.size()%4==0 ? mapCourseList.recommen_32.size()/4 : mapCourseList.recommen_32.size()/4+1 }"></c:set>
						<c:forEach begin="0" end="${layerNum-1}" varStatus="layerOutIndex">
							<ul class="of <c:if test="${layerOutIndex.index!=0}">undis</c:if> " id="weinituijian${layerOutIndex.index}">
								<c:forEach items="${mapCourseList.recommen_32}" var="course" varStatus="index">
									<c:if test="${( (index.index+1)>=(layerOutIndex.index*4+1) ) and ( (index.index+1)<=(layerOutIndex.index+1)*4 ) }">
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
														<%-- <div class="cc-mask">
															<a href="${ctx}/front/couinfo/${course.courseId}" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
														</div> --%>
												</section>
												<div class="coure-bg-g">
													<h3 class="hLh30 txtOf">
														<a href="${ctx}/front/couinfo/${course.courseId}" title="${course.courseName }" class="course-title fsize16 c-333">${course.courseName }</a>
													</h3>
													<section class="mt10 hLh20 of">
														<c:if test="${course.currentPrice=='0.00' }">
															<span class="fr"><tt class="c-green fsize12 f-fA">免费</tt></span>
														</c:if>
														<c:if test="${course.currentPrice!='0.00' }">
															<span class="fr"><tt class="c-master fsize14 f-fM">￥${course.currentPrice }</tt></span>
														</c:if>
														<span class="fl jgAttr c-ccc f-fA">
															<tt class="c-999 f-fA mr10">${course.pageBuycount }人购买</tt>
															<tt class="c-999 f-fA span-2">${course.pageViewcount }浏览</tt>
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
					<div class="clear"></div>
				</article>
			</section>
		</div>
		<!-- /为你推荐 结束 -->
		<!-- /直播课程 开始 -->
		<c:if test="${serviceSwitch.indexLive=='ON'}">
			<div class="of">
				<section class="container">
					<header class="comm-title comm-title-demo2">
						<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/showLivelist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
						<h2 class="fl tac tit-header">
							<span class="c-333">直播课程</span>
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
															<span class="c-666 fsize14 f-fM vam">主讲：</span>
															<span class="c-666 fsize14 f-fM vam">
																<c:forEach items="${course.teacherList}" var="teacherList" varStatus="index">
																	<c:if test="${index.index==0}">${teacherList.name}</c:if>
																	<c:if test="${index.index==1}">/${teacherList.name}</c:if>
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
		</c:if>
		<!-- /直播课程 结束 -->
		<!--/分类课程开始  -->
		<c:forEach items="${indexSubjectCourseList}" var="subject" varStatus="index">
			<c:if test="${index.index<4}">
				<div class="of <c:if test="${index.index%2==0}">bg-fff</c:if> <c:if test="${index.index%2!=0}">bg-f8</c:if>  ">
					<section class="container">
						<div>
							<section class="clearfix">
								<div class="fr d2-couse-phb col-25">
									<article class="ml50">
										<header class="comm-title comm-title-demo2">
											<h2 class="fl tac tit-header">
												<span class="c-333">排行榜</span>
											</h2>
										</header>
										<div class="d2-c-phb-body">
											<c:if test="${empty subject.courseDtoList2}">
												<!-- /无数据提示 开始-->
												<section class="no-data-wrap">
													<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
												</section>
												<!-- /无数据提示 结束-->
											</c:if>
											<c:if test="${not empty subject.courseDtoList2}">
												<ul class="d2-phb-list">
													<c:forEach items="${subject.courseDtoList2}" var="course" varStatus="index">
														<li>
															<a href="${ctx}/front/couinfo/${course.courseId}" title="${course.courseName }" class="name f-fM">
																	${course.courseName }
															</a>
															<%--<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="pic mt5">
																<img class="img-responsive" xsrc="http://127.0.0.1/images/upload/course/20160630/1467281952649.jpg" src="http://127.0.0.1/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
															</a>--%>
															<span class="phb-l-nub <c:if test="${index.index<3}">p-l-n-fist</c:if> ">${index.index+1}</span>
														</li>
													</c:forEach>
												</ul>
											</c:if>
										</div>
									</article>
								</div>
								<div class="fl d2-couse-list col-75">
									<header class="comm-title comm-title-demo2">
										<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/showcoulist?queryCourse.subjectId=${subject.subjectId}" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
										<h2 class="fl tac tit-header">
											<span class="c-333">${subject.subjectName}</span>
										</h2>
									</header>
									<article class="comm-course-list d2-coures-fl-list">
										<c:if test="${empty subject.courseDtoList}">
											<!-- /无数据提示 开始-->
											<section class="no-data-wrap">
												<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
											</section>
											<!-- /无数据提示 结束-->
										</c:if>
										<c:if test="${not empty subject.courseDtoList}">
											<ul class="of">
											<c:forEach items="${subject.courseDtoList}" var="course" varStatus="index">
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
																<tt class="c-999 f-fA span-2">${course.pageViewcount }浏览</tt>
															</span>
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
					</section>
				</div>
			</c:if>
		</c:forEach>
		<%--<div class="of bg-f8">
			<section class="container">
				<header class="comm-title comm-title-demo2">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/showLivelist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">金融专业</span>
					</h2>
					<div class="fl d2-title-fl">
						<a href="" class="current">运营管理</a>
						<a href="">领导艺术</a>
						<a href="">市场营销</a>
						<a href="">礼仪沟通</a>
					</div>
				</header>
				<section class="live-moblie-fl">
					<a href="" class="current">运营管理</a>
					<a href="">领导艺术</a>
					<a href="">市场营销</a>
					<a href="">礼仪沟通</a>
				</section>
				<div>
					<section class="clearfix">
						<div class="fr d2-couse-phb col-3">
							<article class="ml50">
								<div>
									<section class="d2-c-phb-tit d2-c-phb-tit-2 clearfix">
										<a href="javascript:void(0)" class="current fl">免费排行</a>
										<a href="javascript:void(0)" class="fr">付费排行</a>
									</section>
								</div>
								<div class="d2-c-phb-body d2-c-phb-body-2">
									<ul class="d2-phb-list">
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="pic mt5">
												<img class="img-responsive" xsrc="http://127.0.0.1/images/upload/course/20160630/1467281952649.jpg" src="http://127.0.0.1/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
											</a>
											<span class="phb-l-nub p-l-n-fist">1</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<span class="phb-l-nub p-l-n-fist">2</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<span class="phb-l-nub p-l-n-fist">3</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<span class="phb-l-nub">4</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<span class="phb-l-nub">5</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<span class="phb-l-nub">6</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<span class="phb-l-nub">7</span>
										</li>
									</ul>
									<ul class="d2-phb-list">
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												2课程名字名字名字名字
											</a>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="pic mt5">
												<img class="img-responsive" xsrc="http://127.0.0.1/images/upload/course/20160630/1467281952649.jpg" src="http://127.0.0.1/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
											</a>
											<span class="phb-l-nub p-l-n-fist">1</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												2课程名字名字名字名字
											</a>
											<span class="phb-l-nub p-l-n-fist">2</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												2课程名字名字名字名字
											</a>
											<span class="phb-l-nub p-l-n-fist">3</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												2课程名字名字名字名字
											</a>
											<span class="phb-l-nub">4</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												2课程名字名字名字名字
											</a>
											<span class="phb-l-nub">5</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												课程名字名字名字名字
											</a>
											<span class="phb-l-nub">6</span>
										</li>
										<li>
											<a href="http://127.0.0.1/front/couinfo/64" title="建筑工程管理与实务" class="name f-fM">
												2课程名字名字名字名字
											</a>
											<span class="phb-l-nub">7</span>
										</li>
									</ul>
								</div>
							</article>
						</div>
						<div class="fl d2-couse-list col-7">
							<article class="comm-course-list d2-coures-fl-list">
								<ul id="" class="of">
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/64">
													<img class="img-responsive" xsrc="${ctx}/images/upload/course/20160630/1467281952649.jpg" src="${ctx}/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
												</a>
											</section>
											<div class="bg-f8 coure-bg-g">
												<h3 class="hLh30 txtOf">
													<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/64" title="建筑工程管理与实务">建筑工程管理与实务</a>
												</h3>
												<section class="mt10 hLh20 of">
													<span class="fr">
														<tt class="c-master fsize14 f-fM">￥0.01</tt>
													</span>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">791人购买</tt>
														<tt class="c-999 f-fA">1471浏览</tt>
													</span>
												</section>
											</div>
										</div>
									</li>
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/64">
													<img class="img-responsive" xsrc="${ctx}/images/upload/course/20160630/1467281952649.jpg" src="${ctx}/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
												</a>
											</section>
											<div class="bg-f8 coure-bg-g">
												<h3 class="hLh30 txtOf">
													<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/64" title="建筑工程管理与实务">建筑工程管理与实务</a>
												</h3>
												<section class="mt10 hLh20 of">
													<span class="fr">
														<tt class="c-master fsize14 f-fM">￥0.01</tt>
													</span>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">791人购买</tt>
														<tt class="c-999 f-fA">1471浏览</tt>
													</span>
												</section>
											</div>
										</div>
									</li>
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/64">
													<img class="img-responsive" xsrc="${ctx}/images/upload/course/20160630/1467281952649.jpg" src="${ctx}/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
												</a>
											</section>
											<div class="bg-f8 coure-bg-g">
												<h3 class="hLh30 txtOf">
													<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/64" title="建筑工程管理与实务">建筑工程管理与实务</a>
												</h3>
												<section class="mt10 hLh20 of">
													<span class="fr">
														<tt class="c-master fsize14 f-fM">￥0.01</tt>
													</span>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">791人购买</tt>
														<tt class="c-999 f-fA">1471浏览</tt>
													</span>
												</section>
											</div>
										</div>
									</li>
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/64">
													<img class="img-responsive" xsrc="${ctx}/images/upload/course/20160630/1467281952649.jpg" src="${ctx}/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
												</a>
											</section>
											<div class="bg-f8 coure-bg-g">
												<h3 class="hLh30 txtOf">
													<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/64" title="建筑工程管理与实务">建筑工程管理与实务</a>
												</h3>
												<section class="mt10 hLh20 of">
													<span class="fr">
														<tt class="c-master fsize14 f-fM">￥0.01</tt>
													</span>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">791人购买</tt>
														<tt class="c-999 f-fA">1471浏览</tt>
													</span>
												</section>
											</div>
										</div>
									</li>
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/64">
													<img class="img-responsive" xsrc="${ctx}/images/upload/course/20160630/1467281952649.jpg" src="${ctx}/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
												</a>
											</section>
											<div class="bg-f8 coure-bg-g">
												<h3 class="hLh30 txtOf">
													<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/64" title="建筑工程管理与实务">建筑工程管理与实务</a>
												</h3>
												<section class="mt10 hLh20 of">
													<span class="fr">
														<tt class="c-master fsize14 f-fM">￥0.01</tt>
													</span>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">791人购买</tt>
														<tt class="c-999 f-fA">1471浏览</tt>
													</span>
												</section>
											</div>
										</div>
									</li>
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/64">
													<img class="img-responsive" xsrc="${ctx}/images/upload/course/20160630/1467281952649.jpg" src="${ctx}/images/upload/course/20160630/1467281952649.jpg" alt="建筑工程管理与实务">
												</a>
											</section>
											<div class="bg-f8 coure-bg-g">
												<h3 class="hLh30 txtOf">
													<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/64" title="建筑工程管理与实务">建筑工程管理与实务</a>
												</h3>
												<section class="mt10 hLh20 of">
													<span class="fr">
														<tt class="c-master fsize14 f-fM">￥0.01</tt>
													</span>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">791人购买</tt>
														<tt class="c-999 f-fA">1471浏览</tt>
													</span>
												</section>
											</div>
										</div>
									</li>
								</ul>
							</article>
						</div>
					</section>
				</div>
			</section>
		</div>--%>
		<!--分类课程结束  -->
		<!-- /网校名师 开始 -->
		<div class="of bg-fff">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/teacherlist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">名家讲师</span>
					</h2>
				</header>
				<div class="d2-in-teach">
					<ul class="clearfix d2-i-t-list">
						<c:forEach items="${teacherList}" var="teacher" varStatus="index">
							<li>
								<a href="${ctx}/front/teacher/${teacher.id}" class="da-i-t-warp" title="查看详情">
									<div class="d2-i-t-top">
										<div class="DT-arrow">
											<em>◆</em>
											<span>◆</span>
										</div>
										<span class="d2-t-nr-ico">&nbsp;</span>
										<div class="d2-i-t-txt">
											<p class="f-fM">${teacher.career}</p>
										</div>
									</div>
									<div class="d2-i-t-bottom">
										<span class="pic">
											<img alt="${teacher.name}" src="${ctx}/static/inxweb/img/avatar-boy.gif" xsrc="${staticServer}${teacher.picPath}">
										</span>
										<h5 class="name f-fM c-333 hLh30 txtOf tar fsize18">${teacher.name}</h5>
										<p class="job f-fM c-999 hLh30 txtOf tar fsize14 mt10">${teacher.education}</p>
										<div class="d2-i-t-txt-bottom">
											<p class="f-fM">${teacher.career}</p>
										</div>
									</div>
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</section>
		</div>
		<!-- /网校名师 结束 -->
		<div class="bg-f8 of">
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
								<div class="d2-i-n-l-box of">
									<ul class="clearfix">
										<c:forEach items="${leftarticleList}" var="article" varStatus="index">
											<li>
												<div class="inwarp">
													<a class="pic-a-n" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">
														<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif"  alt="${article.title}">
													</a>
													<a class="name-a-n" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">
														<span class="txtOf">${article.title}</span>
													</a>
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
										<ul class="d2-i-nr-b-list">
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
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index2.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index_theme_color.js"></script><!-- 换肤 -->
</body>
</html>