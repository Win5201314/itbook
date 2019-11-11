<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
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
					<c:forEach var="image" items="${websiteImagesList}" varStatus="status">
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
		<!-- /为你推荐 开始 -->
		<div class="of bg-fff">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="javascript:void(0)" title="换一换" class="c-master" onclick="huanyihuan(4)">换一换</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">为你推荐</span>
					</h2>
				</header>
				<article class="comm-course-list">
					<c:if test="${empty mapCourseList.recommen_2}">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty mapCourseList.recommen_2}">
						<%--显示几层--%>
					    <c:set var="layerNum" value="${mapCourseList.recommen_2.size()%4==0 ? mapCourseList.recommen_2.size()/4 : mapCourseList.recommen_2.size()/4+1 }"></c:set>
						<c:forEach begin="0" end="${layerNum-1}" varStatus="layerOutIndex">
							<ul class="of <c:if test="${layerOutIndex.index!=0}">undis</c:if> " id="weinituijian${layerOutIndex.index}">
								<c:forEach items="${mapCourseList.recommen_2}" var="course" varStatus="index">
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
		<!-- /网校课程 开始 -->
		<div class="of bg-f8">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/showcoulist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">网校课程</span>
					</h2>
					<div class="fl c-tit-right">
						<a href="javascript:void(0)" title="精品课程" onclick="bna('goodCourse',this)" class="current boutiqueCourse">精品课程</a>
						<a href="javascript:void(0)" title="最新课程" onclick="bna('newCourseList',this)">最新课程</a>
						<a href="javascript:void(0)" title="全部课程" onclick="bna('allCourseList',this)">全部课程</a>
					</div>
				</header>
				<div>
					<article class="comm-course-list">
						<ul id="goodCourse" class="current">
							<c:if test="${not empty mapCourseList.recommen_32}">
								<c:forEach items="${mapCourseList.recommen_32}" var="course" varStatus="index">
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
								</c:forEach>
							</c:if>
						</ul>
						<ul id="newCourseList" style="display: none">
							<c:if test="${not empty newCourseList }">
								<c:forEach var="course_bna" items="${newCourseList }" varStatus="index">
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/${course_bna.courseId}">
													<c:if test="${not empty course_bna.logo&& course_bna.logo!=''}">
														<img xSrc="${staticServer}${course_bna.logo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course_bna.courseName }">
													</c:if>
													<c:if test="${empty course_bna.logo||course_bna.logo=='' }">
														<img xSrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course_bna.courseName }">
													</c:if>
												</a>
													<%-- <div class="cc-mask">
                                                        <a href="${ctx}/front/couinfo/${course_bna.courseId}" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                                                    </div> --%>
											</section>
											<div class="coure-bg-g">
											<h3 class="hLh30 txtOf mt10">
												<a href="${ctx}/front/couinfo/${course_bna.courseId}" title="${course_bna.courseName }" class="course-title fsize18 c-333">${course_bna.courseName }</a>
											</h3>
											<section class="mt10 hLh20 of">
												<c:if test="${course_bna.currentPrice=='0.00' }">
													<span class="fr"><tt class="c-green fsize12 f-fA">免费</tt></span>
												</c:if>
												<c:if test="${course_bna.currentPrice!='0.00' }">
													<span class="fr"><tt class="c-master fsize14 f-fM">￥${course_bna.currentPrice }</tt></span>
												</c:if>
												<span class="fl jgAttr c-ccc f-fA"> <tt class="c-999 f-fA">
														${course_bna.pageBuycount}人购买
													</tt> | <tt class="c-999 f-fA span-2">
														${course_bna.pageViewcount}浏览
													</tt>
												</span>
											</section>
											</div>
										</div>
									</li>
								</c:forEach>
							</c:if>
						</ul>
						<ul id="allCourseList" style="display: none">
							<c:if test="${not empty allCourseList }">
								<c:forEach var="course_bna" items="${allCourseList }" varStatus="index">
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/${course_bna.courseId}">
													<c:if test="${not empty course_bna.logo&& course_bna.logo!=''}">
														<img xSrc="${staticServer}${course_bna.logo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course_bna.courseName }">
													</c:if>
													<c:if test="${empty course_bna.logo||course_bna.logo=='' }">
														<img xSrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course_bna.courseName }">
													</c:if>
												</a>
													<%-- <div class="cc-mask">
                                                        <a href="${ctx}/front/couinfo/${course_bna.courseId}" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                                                    </div> --%>
											</section>
											<div class="coure-bg-g">
											<h3 class="hLh30 txtOf mt10">
												<a href="${ctx}/front/couinfo/${course_bna.courseId}" title="${course_bna.courseName }" class="course-title fsize18 c-333">${course_bna.courseName }</a>
											</h3>
											<section class="mt10 hLh20 of">
												<c:if test="${course_bna.currentPrice=='0.00' }">
													<span class="fr"><tt class="c-green fsize12 f-fA">免费</tt></span>
												</c:if>
												<c:if test="${course_bna.currentPrice!='0.00' }">
													<span class="fr"><tt class="c-master fsize14 f-fM">￥${course_bna.currentPrice }</tt></span>
												</c:if>
												<span class="fl jgAttr c-ccc f-fA"> <tt class="c-999 f-fA">
														${course_bna.pageBuycount}人购买
													</tt> | <tt class="c-999 f-fA span-2">
														${course_bna.pageViewcount}浏览
													</tt>
												</span>
											</section
											</div>
										</div>
									</li>
								</c:forEach>
							</c:if>
						</ul>
						<div class="clear"></div>
					</article>
				</div>
			</section>
		</div>
		<!-- /网校课程 结束 -->
			<!--直播课程开始  -->

		<c:if test="${serviceSwitch.indexLive=='ON'}">
			<div class="of bg-fff">
				<section class="container">
					<header class="comm-title">
						<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/showLivelist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
						<h2 class="fl tac tit-header">
							<span class="c-333">直播课程</span>
						</h2>
					</header>
					<div>
						<div class="live-box-in" id="live-box-in">
							<article class="comm-course-list i-live-cou-list of" id="i-live-cou-list">
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
										<c:forEach items="${courseDtos}" var="courseDto" varStatus="index">
											<c:set var="beginDate">
												<fmt:formatDate value="${courseDto.liveBeginTime}" pattern="yyyy-MM-dd" type="date"/>
											</c:set>
											<c:set var="endDate">
												<fmt:formatDate value="${courseDto.liveEndTime}" pattern="yyyy-MM-dd" type="date"/>
											</c:set>
											<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
											<li<%-- <c:if test="${nowDate-course.liveEndTime.time < 0}">class="current"</c:if>--%> >
												<div class="cc-l-wrap in-live-ing">
													<div class="cc-l-wrap-top">
														<section class="course-img">
															<c:if test="${not empty courseDto.courseLogo }">
																<a title="${courseDto.courseName }" href="${ctx}/front/couinfo/${courseDto.courseId}">
																	<img xsrc="${staticServer}${courseDto.courseLogo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${courseDto.courseName }">
																</a>
															</c:if>
															<c:if test="${empty courseDto.courseLogo }">
																<a title="${courseDto.courseName }" href="${ctx}/front/couinfo/${courseDto.courseId}">
																	<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${courseDto.courseName }">
																</a>
															</c:if>
														</section>
														<section class="pl10 pr10">
															<h3 class="hLh30 txtOf mt5 in-live-name">
																<a class="course-title fsize16 c-333" title="${courseDto.courseName }" href="${ctx}/front/couinfo/${courseDto.courseId}">${courseDto.courseName }</a>
																<a href="${ctx}/front/couinfo/${courseDto.courseId}" class="live-zt live-zt-ing">
																	<c:if test="${courseDto.liveBeginTime.time<nowDate and courseDto.liveEndTime.time>nowDate }"><img src="${ctx}/static/inxweb/img/live-ing.gif"></c:if>
																	<c:if test="${courseDto.liveBeginTime.time>nowDate and courseDto.liveEndTime.time>nowDate }"><img src="${ctx}/static/inxweb/img/live-wks.png"></c:if>
																	<c:if test="${courseDto.liveEndTime.time <nowDate}"><img src="${ctx}/static/inxweb/img/live-over.png"></c:if>
																</a>
															</h3>
															<section class="mt10 hLh20 of">
																<span class="fr"><tt class=" fsize14 f-fG  c-master">￥${courseDto.currentPrice}</tt></span>
														<span class="fl jgAttr c-ccc f-fA">
															<tt class="c-999 f-fA">学员： ${courseDto.courseBuycount}人</tt>
														</span>
															</section>
														</section>
													</div>
													<div class="cc-l-wrap-bottom">
														<div class="in-live-line pr">
															<span class="pa">&nbsp;</span>
														</div>
														<section class="i-live-sta">
															<div class="tac">
																<em class="vam icon16 i-live-time"></em>
																<c:if test="${beginDate != endDate}">
																	<span class="fsize16 c-666 f-fM vam">
																		<fmt:formatDate value="${courseDto.liveBeginTime}" pattern="MM-dd HH:mm" type="date"/>
																	</span>
																	<span class="fsize16 c-999 f-fM vam">
																			<%--<c:if test="${courseDto.nearestLiveBeginTime==null&&course.nearestLiveEndTime==null}">已结束</c:if>--%>
																		<%--<c:if test="${courseDto.nearestLiveBeginTime!=null&&course.nearestLiveEndTime!=null}">--%>---<%--</c:if>--%>
																	</span>
																	<span class="fsize16 c-666 f-fM vam">
																		<fmt:formatDate value="${courseDto.liveEndTime}" pattern="MM-dd HH:mm" type="date"/>
																	</span>
																</c:if>
																<c:if test="${beginDate == endDate}">
																	<span class="fsize16 c-666 f-fM vam"><fmt:formatDate value="${courseDto.liveBeginTime}" pattern="MM-dd" type="date"/>&nbsp;</span>
																	<span class="fsize16 c-666 f-fM vam">
                                                                        <fmt:formatDate value="${courseDto.liveBeginTime}" pattern="HH:mm" type="date"/>--<fmt:formatDate value="${courseDto.liveEndTime}" pattern="HH:mm" type="date"/>
                                                                    </span>
																</c:if>
															</div>

														</section>
														<section class="clearfix  pl10 pr10">
															<div class="fl">
																<span class="c-666 fsize12 f-fM vam">主讲：</span>
																<span class="c-666 fsize12 f-fM vam">
																<c:forEach items="${courseDto.teacherList}" var="teacherList" varStatus="index">
																	<c:if test="${index.index==0}">
																		${teacherList.name}
																	</c:if>
																	<c:if test="${index.index!=0 && index.index<2}">
																		/${teacherList.name}
																	</c:if>
																</c:forEach>
																<c:if test="${courseDto.teacherList.size()>2}">
																	...
																</c:if>
																</span>
															</div>
															<div class="fr">
																<span class="c-666 fsize12 f-fM vam"><fmt:formatDate value="${courseDto.courseLiveBeginTime}" pattern="MM-dd"/>---<fmt:formatDate value="${courseDto.courseLiveEndTime}" pattern="MM-dd"/></span>
															</div>
														</section>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</article>
							<c:if test="${fn:length(courseDtos)>3}">
							<a class="lv-prev lv-contrl" href="javascript: void(0)">prev</a>
							<a class="lv-next lv-contrl" href="javascript: void(0)">next</a>
							</c:if>
						</div>
						<div class="live-box-in-mobile">
							<article class="comm-course-list i-live-cou-list of">
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
										<c:forEach items="${courseDtos}" var="courseDto" varStatus="index">
											<c:set var="beginDate">
												<fmt:formatDate value="${courseDto.liveBeginTime}" pattern="yyyy-MM-dd" type="date"/>
											</c:set>
											<c:set var="endDate">
												<fmt:formatDate value="${courseDto.liveEndTime}" pattern="yyyy-MM-dd" type="date"/>
											</c:set>
											<li >
												<div class="cc-l-wrap">
													<div class="cc-l-wrap-top">
														<section class="course-img">
															<c:if test="${not empty courseDto.courseLogo }">
																<img xsrc="${staticServer}${courseDto.courseLogo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${courseDto.courseName }">
															</c:if>
															<c:if test="${empty courseDto.courseLogo }">
																<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${courseDto.courseName }">
															</c:if>

														</section>
														<section class="pl10 pr10">
															<h3 class="hLh30 txtOf mt5 in-live-name">
																<a class="course-title fsize16 c-333" title="${courseDto.courseName }" href="${ctx}/front/couinfo/${courseDto.courseId}">${courseDto.courseName }</a>
																<a href="javascript:void(0)" class="live-zt live-zt-ing">
																</a>
															</h3>
															<section class="mt10 hLh20 of">
																<span class="fr"><tt class=" fsize14 f-fG  c-master">￥${courseDto.currentPrice}</tt></span>
														<span class="fl jgAttr c-ccc f-fA">
															<tt class="c-999 f-fA">学员： ${courseDto.courseBuycount}人</tt>
														</span>
															</section>
														</section>
													</div>
													<div class="cc-l-wrap-bottom">
														<div class="in-live-line pr">
															<span class="pa">&nbsp;</span>
														</div>
														<section class="i-live-sta">
															<div class="tac">
																<em class="vam icon16 i-live-time"></em>
																<c:if test="${beginDate != endDate}">
																	<span class="fsize16 c-666 f-fM vam">
																		<fmt:formatDate value="${courseDto.liveBeginTime}" pattern="MM-dd HH:mm" type="date"/>
																	</span>
																	<span class="fsize16 c-999 f-fM vam">
																			<%--<c:if test="${courseDto.nearestLiveBeginTime==null&&course.nearestLiveEndTime==null}">已结束</c:if>--%>
																		<%--<c:if test="${courseDto.nearestLiveBeginTime!=null&&course.nearestLiveEndTime!=null}">--%>---<%--</c:if>--%>
																	</span>
																	<span class="fsize16 c-666 f-fM vam">
																		<fmt:formatDate value="${courseDto.liveEndTime}" pattern="MM-dd HH:mm" type="date"/>
																	</span>
																</c:if>
																<c:if test="${beginDate == endDate}">
																	<span class="fsize16 c-666 f-fM vam"><fmt:formatDate value="${courseDto.liveBeginTime}" pattern="MM-dd" type="date"/>&nbsp;</span>
																	<span class="fsize16 c-666 f-fM vam">
                                                                        <fmt:formatDate value="${courseDto.liveBeginTime}" pattern="HH:mm" type="date"/>--<fmt:formatDate value="${courseDto.liveEndTime}" pattern="HH:mm" type="date"/>
                                                                    </span>
																</c:if>
															</div>
														</section>
														<section class="clearfix  pl10 pr10">
															<div class="fl">
																<span class="c-666 fsize12 f-fM vam">主讲：</span>
																<span class="c-666 fsize12 f-fM vam"><c:forEach items="${courseDto.teacherList}" var="teacherList">${teacherList.name}</c:forEach></span>
															</div>
                                                            <div class="fr">
                                                                <span class="c-666 fsize12 f-fM vam"><fmt:formatDate value="${courseDto.liveBeginTime}" pattern="MM-dd"/>---<fmt:formatDate value="${courseDto.liveEndTime}" pattern="MM-dd"/></span>
                                                            </div>
														</section>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</article>
							<c:if test="${fn:length(courseDtos)>3}">
							<a class="lv-prev lv-contrl" href="javascript: void(0)">&nbsp;</a>
							<a class="lv-next lv-contrl" href="javascript: void(0)">&nbsp;</a>
							</c:if>
						</div>
					</div>
				</section>
			</div>
		</c:if>
			<!--直播课程结束  -->
		<!-- /网校名师 开始 -->
		<div class="of bg-f8">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/teacherlist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">网校名师</span>
					</h2>
				</header>
				<div>
					<article class="i-teacher-list">
						<ul class="of">
							<c:forEach items="${teacherList}" var="teacher" varStatus="index">
								<li>
									<section class="i-teach-wrap">
										<div class="i-teach-pic">
											<a href="${ctx}/front/teacher/${teacher.id}" title="${teacher.name}">
												<c:if test="${not empty teacher.picPath&&teacher.picPath!=''}">
													<img alt="${teacher.name}" src="${ctx}/static/inxweb/img/avatar-boy.gif" xsrc="${staticServer}${teacher.picPath}">
												</c:if>
												<c:if test="${empty teacher.picPath||teacher.picPath==''}">
													<img alt="${teacher.name}" src="${ctx}/static/inxweb/img/avatar-boy.gif" xsrc="${ctx}/static/inxweb/img/avatar-boy.gif">
												</c:if>
											</a>
										</div>
										<div class="mt10 hLh30 txtOf tac">
											<a href="${ctx}/front/teacher/${teacher.id}" title="${teacher.name}" class="fsize18 c-666">${teacher.name}</a>
										</div>
										<div class="hLh30 txtOf tac">
											<span class="fsize14 c-999">${teacher.education }</span>
										</div>
										<div class="mt15 i-q-txt">
											<p class="c-999 f-fA">${teacher.career }</p>
										</div>
									</section>
								</li>
							</c:forEach>
						</ul>
						<div class="clear"></div>
					</article>
				</div>
			</section>
				<%--<div class="in-teach-slider">
					<section id="currentTeacher" class="in-t-s-top">
                        <c:forEach items="${teacherList}" var="teacher" varStatus="index">
                            <section <c:if test="${index.index==0}">class="in-teach-warp"</c:if><c:if test="${index.index!=0}">class="in-teach-warp undis"</c:if>>
                                <article class="in-teach-w-in">
                                    <a href="/front/teacher/${teacher.id}" target="_blank" class="in-teach-pic">
                                        <img alt="${teacher.name}" src="${ctx}${teacher.picPath}" xsrc="${ctx}${teacher.picPath}">
                                    </a>
                                    <h1 class="name">${teacher.name}</h1>
                                    <p class="job">
                                        <c:if test="${teacher.isStar==1}">高级讲师</c:if>
                                        <c:if test="${teacher.isStar==2}">首席讲师</c:if>
                                    </p>
                                    <div class="n-txt">
                                        <p>${teacher.career}</p>
                                    </div>
                                </article>
                            </section>
                        </c:forEach>
					</section>
					<section class="in-t-s-bottom mt30">

						&lt;%&ndash;<div id="teacher-box-index" class="teacher-box-index">
							<div id="teacher-list-index" class="teacher-list-index">
								<ul class="clearfix">
                                    <c:forEach items="${teacherList}" var="teacher" varStatus="index">
                                        <li <c:if test="${index.index==0}">class="current"</c:if> value="${index.index}">
                                            <a href="javascript:void(0)">
                                                <img alt="${teacher.name}" src="https://wx.inxedu.com/images/upload/teacher/20150915/1442297885942.jpg" xsrc="${ctx}${teacher.picPath}">
                                            </a>
                                        </li>
                                    </c:forEach>

								</ul>
							</div>
							<a class="prev" href="javascript: void(0)">&nbsp;</a>
							<a class="next" href="javascript: void(0)">&nbsp;</a>
						</div>&ndash;%&gt;
					</section>
				</div>--%>
		</div>
		<!-- /网校名师 结束 -->
		<div class="bg-fff of">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/articlelist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">资讯列表</span>
					</h2>
				</header>
				<section class="in-new-box clerfix">
					<div class="clearfix">
						<article class="w50pre fl">
							<div class="mr30 i-article-list in-new-box-list">
								<ul>
									<c:forEach items="${articleList}" var="article" varStatus="index">
										<c:if test="${index.index<2}">
											<li>
												<aside class="i-article-pic">
													<a target="_blank" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">
														<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif"  alt="${article.title}">
													</a>
												</aside>
												<h3 class="hLh30 txtOf">
													<a target="_blank" class="i-art-title" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">${article.title}</a>
												</h3>
												<section class="i-q-txt mt10 i-q-txt2">
													<p>
														<span class="c-999 f-fA">${article.summary}</span>
													</p>
												</section>
												<section class="hLh30 txtOf mt5 chearfix">
													<span class="fl mr50">
														<em class="icon16 in-new-eyeico">&nbsp;</em>
														<tt class="fsize12 c-999 f-fM vam">${article.clickNum}</tt>
													</span>
													<span class="fl">
														<em class="icon16 in-new-timeico">&nbsp;</em>
														<tt class="fsize12 c-999 f-fM vam"><fmt:formatDate value="${article.publishTime }" pattern="yyyy-MM-dd HH:mm" /></tt>
													</span>
												</section>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</article>
						<article class="w50pre fl">
							<div class="ml30 i-article-list in-new-box-list">
								<ul>
									<c:forEach items="${articleList}" var="article" varStatus="index">
										<c:if test="${index.index>1}">
											<li>
												<aside class="i-article-pic">
													<a target="_blank" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">
														<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif"  alt="${article.title}">
													</a>
												</aside>
												<h3 class="hLh30 txtOf">
													<a target="_blank" class="i-art-title" href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}">${article.title}</a>
												</h3>
												<section class="i-q-txt mt5 i-q-txt2">
													<p>
														<span class="c-999 f-fA">${article.summary}</span>
													</p>
												</section>
												<section class="hLh30 txtOf mt10 chearfix">
													<span class="fl mr50">
														<em class="icon16 in-new-eyeico">&nbsp;</em>
														<tt class="fsize12 c-999 f-fM vam">${article.clickNum}</tt>
													</span>
													<span class="fl">
														<em class="icon16 in-new-timeico">&nbsp;</em>
														<tt class="fsize12 c-999 f-fM vam"><fmt:formatDate value="${article.publishTime }" pattern="yyyy-MM-dd HH:mm" /></tt>
													</span>
												</section>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</article>
						<div class="clear"></div>
					</div>
				</section>
			</section>
		</div>
		<%--<div class="bg-fff of">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt15 mr10 hyh"><a href="${ctx}/front/articlelist" title="更多" class="c-master fsize14" >更多 &gt;</a></span>
					<h2 class="fl tac tit-header">
						<span class="c-333">最新资讯</span>
					</h2>
				</header>
				<section class="in-new-box clerfix">
					<div class="clearfix">
						<c:if test="${empty articleList}">
							<!-- /无数据提示 开始-->
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
							</section>
							<!-- /无数据提示 结束-->
						</c:if>
						<c:if test="${not empty articleList}">
							<c:set var="article" value="${articleList[0]}"/>
							<article class="w50pre fl">
								<div class="mr50 in-new-left-box">
									<div class="i-n-l-infor">
										<a href="${ctx}/front/articleinfo/${article.articleId}" target="_blank" class="i-n-pic">
											<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif" alt="${article.title}">
										</a>
										<h3 class="i-n-l-mane">
											<a href="${ctx}/front/articleinfo/${article.articleId}" target="_blank">${article.title}</a>
										</h3>
									</div>
								</div>
							</article>
							<c:if test="${articleList.size()<2}">
								<!-- /无数据提示 开始-->
								<section class="no-data-wrap">
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有更多数据，小编正在努力整理中...</span>
								</section>
								<!-- /无数据提示 结束-->
							</c:if>
							<c:if test="${articleList.size()>=2}">
								<article class="w50pre fl">

									<div class="i-article-list in-new-box-list">
										<ul>
											<c:set var="article" value="${articleList[1]}"/>
											<li>
												<aside class="i-article-pic">
													<a href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}" target="_blank">
														<img xsrc="${staticServer}${article.imageUrl }" src="/static/inxweb/img/coures.gif" alt="${article.title}">
													</a>
												</aside>
												<h3 class="hLh30 txtOf">
													<a class="i-art-title" href="${ctx}/front/articleinfo/${article.articleId}" target="_blank" title="${article.title}">${article.title}</a>
												</h3>
												<section class="i-q-txt mt10">
													<p>
														<span class="c-999 f-fA">${article.summary }</span>
													</p>
												</section>
												<section class="hLh30 txtOf mt10 chearfix">
													<span class="fl mr50">
														<em class="icon16 in-new-eyeico">&nbsp;</em>
														<tt class="fsize12 c-999 f-fM vam">${article.clickNum}</tt>
													</span>
													<span class="fl">
														<em class="icon16 in-new-timeico">&nbsp;</em>
														<tt class="fsize12 c-999 f-fM vam"><fmt:formatDate value="${article.publishTime }" pattern="yyyy-MM-dd HH:mm" /></tt>
													</span>
												</section>
											</li>
										</ul>
									</div>
									<div class="in-new-right-list">
										<ul class="in-n-r-l-ul">
											<c:forEach var="article" items="${articleList }" varStatus="index">
												<c:if test="${index.index>1}">
													<li class="clearfix">
														<a href="${ctx }/front/articleinfo/${article.articleId}" class="fl fsize16 c-666 f-fM">
															<em class="icon14 i-n-t-l-ico mr5">&nbsp;</em>
																${article.title }
														</a>
														<span class="time fr fsize14 c-666 f-fM">
															<fmt:formatDate value="${article.publishTime }" pattern="yyyy-MM-dd" />
														</span>
													</li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
								</c:if>
							</article>
						</c:if>
						<div class="clear"></div>
					</div>
				</section>
			</section>
		</div>--%>
	</div>
	<input type="hidden" id="theme_color" value="${theme_color}"/>
	<script type="text/javascript" src="${ctx}/static/inxweb/js/swiper-2.1.0.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index_theme_color.js"></script><!-- 换肤 -->
</body>
</html>