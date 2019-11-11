<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty courseList}">
	<!-- /无数据提示 开始-->
	<section class="no-data-wrap">
		<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
	</section>
	<!-- /无数据提示 结束-->
</c:if>
<c:if test="${not empty courseList}">
	<ul class="clearfix">
		<c:forEach items="${courseList}" var="course">
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