<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>支付成功</title>
<script>
	var theme_color = '${theme_color}';
</script>
</head> 
<body>
	<div id="aCoursesList" class="bg-fa of">
		<div class="container">
			<section class="path-wrap txtOf hLh30"> 
				<a class="c-999 fsize14" title="" href="${ctx}">首页</a>
				 \<a class="c-999 fsize14" title="" href="${ctx}">购物车</a>
				 \<span class="c-333 fsize14">支付成功</span> 
			</section>
			<div class="mt30">
			    <div class="order-step-bg-3 order-step"></div>
				<header class=""><span class="fsize24 c-333">支付成功</span></header>
				<div class="c-pay-method c-p-m c-pay-s-box">
					<div class="tac pr">
						<em class="icon80"></em><span class="c-333 f-fH pay-font">支付成功！</span>
						<div class="c-order-num"><p class="c-666 fsize16 f-fM">订单编号：<span class="c-333 fsize18">${trxorder.orderNo}</span></p></div>
					</div>
					<div class="mt30 pl20">
						<header class="c-p-title">您的支付信息</header>
						<div class="mt30 clearfix of">
							<div class="fl">
								<ul class="order-list-item ml20">
									<li class="o-l-fir"><em class="icon14 c-o-icon mt5"></em><span class="fsize14 c-999 f-fM ml5 vam">付款金额：<tt class="c-master f-fG fsize30">￥${trxorder.sumMoney}</tt></span></li>
									<li><em class="icon14 c-o-icon mt5"></em><span class="fsize14 c-999 f-fM  ml5 vam">支付方式：
										<tt class="c-333 f-fM">
											<c:choose>
												<c:when test="${trxorder.payType=='ALIPAY'}">支付宝</c:when>
												<c:when test="${trxorder.payType=='WEIXIN'}">微信</c:when>
												<c:when test="${trxorder.payType=='YEEPAY'}">易宝</c:when>
												<c:when test="${trxorder.payType=='CARD'}">课程卡</c:when>
												<c:when test="${trxorder.payType=='ACCOUNT'}">账户余额</c:when>
												<c:when test="${trxorder.payType=='USERCARD'}">学员卡</c:when>
											</c:choose>
										</tt></span></li>
									<li><em class="icon14 c-o-icon mt5"></em><span class="fsize14 c-999 f-fM  ml5 vam">支付日期：<tt class="c-333 f-fM"><fmt:formatDate value="${trxorder.payTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate> </tt></span></li>
                                   	<%--如果支付的是课程--%>
									<c:if test="${trxorder.orderType=='COURSE'}">
										<li><em class="icon14 c-o-icon mt5"></em><span class="fsize14 c-999 f-fM  ml5 vam c-buy-cou">所购课程：<tt class="c-333 f-fM">
											<c:forEach items="${courseList}" var="course">
												《${course.courseName}》
											</c:forEach>
										</tt></span></li>
									</c:if>
									<%--如果是开通会员--%>
									<c:if test="${trxorder.orderType=='MEMBER'}">
										<li><em class="icon14 c-o-icon mt5"></em><span class="fsize14 c-999 f-fM  ml5 vam c-buy-cou">会员类型：<tt class="c-333 f-fM">
											${memberSale.name}
										</tt></span></li>
								   </c:if>
								</ul>
							</div>
							<div class="fr mr30 pay-nav-return clearfix">
								<div class="mt10 w50"><a href="${ctx}" class="ret-btn"><em class="icon20"></em><tt>返回首页</tt></a></div>
								<%--如果支付的是课程--%>
								<c:if test="${trxorder.orderType=='COURSE'}">
									<div class="mt30 w50"><a href="javascript:tostudy()" class="ret-btn lea-cou-btn"><em class="icon20"></em><tt>学习课程</tt></a></div>
								</c:if>
								<c:if test="${trxorder.orderType=='MEMBER'}">
									<div class="mt30 w50"><a href="javascript:tostudy()" class="ret-btn lea-cou-btn"><em class="icon20"></em><tt>个人中心</tt></a></div>
								</c:if>
								<script>
									//如果是直播则调到我的直播 如果是课程则调到我的课程
									function tostudy(){
										var coursetype ='${courseList[0].sellType}';
										if(coursetype=='LIVE'){
											window.location.href="/uc/live";
										}else{
											window.location.href="/uc/index";
										}
									}
								</script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function () {
			if("${msg}"!=null && "${msg}"!=''){
				dialog('提示信息',decodeURI("${msg}"),0);
			}
		})
	</script>
	
</body>
</html>