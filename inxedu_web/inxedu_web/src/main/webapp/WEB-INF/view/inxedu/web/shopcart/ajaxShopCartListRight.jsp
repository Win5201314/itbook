<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<span class="pa white-bg">&nbsp;</span>
<c:set var="totalMoney" value="0"></c:set>
<div class=" s-car-box">
	<div class="s-car-box-top clearfix">
		<div class="fl s-c-icon">
			<em class="icon20 vam"></em>
			<span class="vam fsize16 ml5 c-666">购物车</span>
		</div>
		<div class="fr mt5">
			<a class="s-car-close" href="javascript:ccrFun();"></a>
		</div>
	</div>
	<div class="s-car-box-middle">
		<!--无内容开始  -->
		<c:if test="${empty shopcartList}">
			<section class="no-data-wrap"><em class="icon30 no-data-ico">&nbsp;</em><span class="c-666 fsize14 ml10 vam"><font class="c-999 fsize12 vam">购物车暂无课程，建议你去<a target="_blank" href="${ctx}/front/showcoulist" title="选课" class="c-master ml5">选课</a></font></span></section>
		</c:if>
		<!--无内容结束  -->
		<c:if test="${!empty shopcartList}">
			<ul class="c-b-m-list">
				<c:forEach items="${shopcartList }" var="sc">
					<c:set var="totalMoney" value="${totalMoney+sc.course.currentPrice }"></c:set>

					<li>
						<div class="addScar-elem">
							<p class="addScar-list shopcartlist32">
								<a class="dis fl mr10" href="${ctx}/front/couinfo/${sc.course.courseId}">
									<c:choose>
										<c:when test="${not empty sc.course.logo}">
											<img src="${staticServer}${sc.course.logo}" width="50" height="38">
										</c:when>
										<c:otherwise>
											<img src="/static/inxweb/img/coures.gif" width="50" height="38">
										</c:otherwise>
									</c:choose>
								</a>
										<span class="a-s-l-c-name">
											<a class="c-4e fsize12 f-fM" href="${ctx}/front/couinfo/${sc.course.courseId}">${sc.course.courseName}</a>
										</span>
										<span class="a-s-l-c-attr">
											<tt class="c-red dis">￥${sc.course.currentPrice}</tt>
											<a href="javascript:deleteCartId(${sc.id},${sc.goodsid },${sc.type})"><u class="c-blue f-fM fsize12">删除</u></a>
										</span>
							</p>
						</div>
					</li>
				</c:forEach>

			</ul>
		</c:if>
	</div>
	<div class="s-car-box-bot">
		<div class="s-c-b c-4e">
			<div class="hLh20"><strong class="c-master">${shopcartList.size()}</strong>件商品</div>
			<div class="hLh20">共计：<strong class="c-master">￥${totalMoney}</strong></div>
			<a class="js-btn" href="${ctx}/shopcart?command=queryShopCart" target="_blank">立即结算</a>
		</div>
	</div>
</div>
<script>
	var vBtn=$("#v-nav-first"),
			naxBox=$(".r-fixed-wrap");
	function ccrFun() {
		naxBox.stop().animate({"right" : "-279px"}, 400);
		vBtn.removeClass("vBtnCurr");
		$("html").removeClass("onScroll");
	}
</script>