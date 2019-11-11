<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html> 
<html>
<head>
<title>讲师详情</title>
</head>
<body>
	<div id="aCoursesList" class="bg-f8 of">
		<!-- /课程列表 开始 -->
		<section class="container">
			<header class="comm-title">
				<h2 class="fl tac">
					<span class="c-333">讲师介绍</span>
				</h2>
			</header>
			<div class="t-infor-wrap bg-fff">
				<%--<div class="fl t-infor-menu">
					<section id="t-infor-menu" class="c-tab-title">
						<p>
							<a class="current" name="c-desc" title="讲师详情" href="javascript: void(0)">讲师详情</a>
						</p>
						<p>
							<a title="主讲课程" name="c-course" href="javascript: void(0)">主讲课程</a>
						</p>
					</section>
				</div>--%>
				<section class="t-infor-box c-desc-content">
					<div class="">
						<section class="t-infor-pic">
							<c:choose>
								<c:when test="${not empty teacher.picPath }">
									<img src="${ctx }/static/inxweb/img/avatar-boy.gif" xsrc="${staticServer}${teacher.picPath}" alt="">
								</c:when>
								<c:otherwise>
									<img xSrc="${ctx }/static/inxweb/img/avatar-boy.gif" src="${ctx }/static/inxweb/img/avatar-boy.gif" class="img-responsive" alt="">
								</c:otherwise>
							</c:choose>
						</section>
						<h3 class="hLh30">
							<span class="fsize24 c-333">${teacher.name } <c:if test="${teacher.isStar ==1}">&nbsp;高级讲师</c:if> <c:if test="${teacher.isStar ==2}">&nbsp;首席讲师</c:if></span>
						</h3>
						<section class="mt10">
							<span class="t-tag-bg">${teacher.education }</span>
						</section>
						<section class="t-infor-txt">
							<p class="mt20">${teacher.career }</p>
						</section>
						<div class="clear"></div>
					</div>
				</section>
				<div class="clear"></div>
			</div>
			<section class="mt30">
				<div>
					<header class="comm-title all-teacher-title c-course-content">
						<h2 class="fl tac">
							<span class="c-333">主讲课程</span>
						</h2>
						<section class="c-tab-title">
							<a href="javascript: void(0)">&nbsp;</a>
						</section>
					</header>
					<section class="bg-fff artil-list-box">
						<!-- /无数据提示 开始-->
						<c:if test="${empty courseList }">
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
							</section>
						</c:if>
						<!-- /无数据提示 结束-->
						<article class="comm-course-list">
							<ul class="of">
								<c:if test="${not empty courseList }">
									<c:forEach var="course" items="${courseList }">
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
														<%--<div class="cc-mask">
                                                            <a href="${ctx}/front/couinfo/${course.courseId}" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                                                        </div>--%>
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
							<div class="clear"></div>
						</article>
						<!-- 公共分页 开始 -->
						<div>
							<form action="${ctx }/front/teacher/${teacher.id}" method="post" id="searchForm">
								<input type="hidden" name="page.currentPage" id="pageCurrentPage" value="1">
							</form>
							<jsp:include page="/WEB-INF/view/common/front_page.jsp"></jsp:include>
						</div>
						<!-- 公共分页 结束 -->
					</section>
				</div>
			</section>
		</section>
		<!-- /课程列表 结束 -->
	</div>
	<script type="text/javascript" src="${ctx}/static/inxweb/teacher/teacher-info.js"></script>
	<script>
		$(function () {
			/*给导航加选中*/
			$("a[href$='/front/teacherlist']").parent().addClass("current");
		})
	</script>
</body>
</html>