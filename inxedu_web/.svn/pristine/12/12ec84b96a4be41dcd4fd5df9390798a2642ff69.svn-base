<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的过期直播</title>
	<script type="text/javascript" src="${ctx}/static/inxweb/live/live-uc.js"></script>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div>
					<section class="c-infor-tabTitle c-tab-title">
						<a href="javascript: void(0)" title="Wo的直播" class="c-tab-title">Wo的直播</a>
						<a href="${ctx }/uc/live" title="收费">收费</a>
						<a href="${ctx }/uc/freeLiveList" title="免费">免费</a>
						<a href="${ctx }/uc/overduelive" title="已过期" class="current">已过期</a>
					</section>
				</div>
				<div class="mt40">
					<c:if test="${courseList==null || courseList.size()<=0 }">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">还没有过期的直播！</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty courseList }">
						<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
						<c:forEach items="${courseList}" var="course">
							<c:set var="beginDate">
								<fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd" type="date"/>
							</c:set>
							<c:set var="endDate">
								<fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd" type="date"/>
							</c:set>
							<div class="live-cou-box live-cou-list mt20" id="live-cou-list">
								<ul>
									<li <c:if test="${course.liveBeginTime.time<nowDate and course.liveEndTime.time>nowDate}">class="current"</c:if> >
										<div class="live-cb-wrap">
											<div class="clearfix live-cb-box">
												<aside class="live-pic">
													<c:choose>
														<c:when test="${not empty course.logo}">
															<img src="${staticServer}${course.logo}" class="img-responsive" alt="${course.courseName}" />
														</c:when>
														<c:otherwise>
															<img src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}" />
														</c:otherwise>
													</c:choose>
												</aside>
												<h3 class="hLh30 txtOf"> <a href="${ctx}/front/couinfo/${course.courseId }" title="" class="live-course-title">${course.courseName}</a> </h3>
												<div class="mt15"><span class="c-666 fsize14">购买数：${course.pageBuycount }人</span><span class="c-master fsize14 ml30">￥${course.currentPrice }</span></div>
												<section class="i-q-txt mt15"> <p> <span class="c-999 f-fA">${course.courseName}</span> </p> </section>
											</div>
											<div class="live-cou-time clearfix">
												<div class="fl">
													<%--<span class="fsize28">12-01</span>
													<span class="ml20 fsize16"> 00:00</span>
													<span class="ml20 fsize16">~</span>
													<span class="ml20 fsize28">03-18</span>
													<span class="ml20 fsize16"> 00:00</span>--%>
													<c:if test="${beginDate == endDate}">
														<span class="fsize28"><fmt:formatDate value="${course.liveBeginTime}" pattern="MM月dd日" type="date"/></span>
													<span class="ml20 fsize16">
														<fmt:formatDate value="${course.liveBeginTime}" pattern="HH:mm" type="date"/>&nbsp;~&nbsp;<fmt:formatDate value="${course.liveEndTime}" pattern="HH:mm" type="date"/>
													</span>
													</c:if>
													<c:if test="${beginDate != endDate}">
														<span class="fsize28"><fmt:formatDate value="${course.liveBeginTime}" pattern="MM月dd日" type="date"/></span>
														<%--<span class="ml20 fsize16">
															<fmt:formatDate value="${course.liveBeginTime}" pattern="HH:mm" type="date"/>
														</span>--%>
														<span class="ml20 fsize16">~</span>
														<span class="ml20 fsize28"><fmt:formatDate value="${course.liveEndTime}" pattern="MM月dd日" type="date"/></span>
														<%--<span class="ml20 fsize16">
															<fmt:formatDate value="${course.liveEndTime}" pattern="HH:mm" type="date"/>
														</span>--%>
													</c:if>
												</div>
												<div class="fr">
													<a <%--href="javascript:void(0)" onclick="nowLivePlay(${course.courseId})"--%> href="${ctx}/front/couinfo/${course.courseId }">
														<c:if test="${nowDate-course.liveEndTime.time < 0}">]加入直播</c:if>
														<c:if test="${nowDate-course.liveEndTime.time > 0}">已结束</c:if>
													</a>
												</div>
											</div>
										</div>



										<div class="mt20 u-live-sch">
											<a href="javascript:void(0);" onclick="getLiveKpointList(${course.courseId},this)" class="sch-btn sch-open">课程表展开∨</a>

										</div>
									</li>
								</ul>
							</div>
						</c:forEach>
						<!-- 公共分页 开始 -->
						<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
						<!-- 公共分页 结束 -->
						<form method="post" id="searchForm" action="${ctx}/uc/live">
							<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
						</form>
					</c:if>
				</div>
			</section>
			<!-- /我的课程 -->
		</div>
	</article>
</body>
</html>