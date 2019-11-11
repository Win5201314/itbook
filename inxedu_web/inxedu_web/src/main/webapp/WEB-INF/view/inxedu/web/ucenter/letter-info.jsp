<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>课程评论</title>
<script>
</script>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div class="u-r-all-box">
					<section class="u-r-tit-all">
						<h2 class="clearfix c-999 f-fA fsize14">
							<a href="/uc/letter" class="fsize16 c-333 f-fM unFw name">消息中心</a>
							&gt;
							<span href="javascript:void(0)" class="fsize16 c-master f-fM unFw name">消息详情</span>
						</h2>
					</section>
					<section>
						<div class="clearfix cou-r-pl-all">
							<div class="u-r-new-infor">
								<h5 class="hLh30 fsize18 c-333 f-fM tac txtOf">
									<c:if test="${msgReceive.type==1}">系统消息</c:if>
									<c:if test="${msgReceive.type==5}">课程过期</c:if>
									<c:if test="${msgReceive.type==6}">优惠券过期</c:if>
								</h5>
								<p class="u-r-n-i-txt c-999">${msgReceive.content}</p>
								<p class="hLh30 fsize14 c-666 tar"><fmt:formatDate type="both" value="${msgReceive.addTime }" pattern="yyyy-MM-dd" /></p>
							</div>
						</div>
					</section>
				</div>
			</section>
		</div>
	</article>
	<script>
		$(function () {
			/*给导航加选中*/
			$("a[href$='/uc/letter']").parent().addClass("current");
		})
	</script>
</body>
</html>