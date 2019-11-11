<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN">
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no,minimal-ui">
<title><sitemesh:title></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
<meta name="author" content="${websitemap.web.author}" />
<meta name="keywords" content="${websitemap.web.keywords}" />
<meta name="description" content="${websitemap.web.description}" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta content="telephone=no" name="format-detection" />
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/reset.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/global.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/web.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/theme.css">
<link href="${ctx}/static/inxweb/css/mw_320_768.css" rel="stylesheet" type="text/css" media="screen and (min-width: 320px) and (max-width: 768px)">
<!--[if lt IE 9]><script src="${ctx}/static/common/html5.js"></script><![endif]-->
<script type="text/javascript" src="${ctx}/static/common/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/webutils.js"></script>
<script type="text/javascript" src="${ctx}/static/inxweb/header/header.js"></script>
<script type="text/javascript" src="${ctx }/static/inxweb/js/common.js"></script>
	<script type="text/javascript">
		var baselocation = "${ctx}";
		var keuploadSimpleUrl='<%=keuploadSimpleUrl%>';
		var uploadServerUrl='<%=uploadServer%>';
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";
		var pdfUploadAndImg='<%=pdfUploadAndImg%>';
		var uploadShrinkDiagram='<%=uploadShrinkDiagram%>';//图片上传带缩列图
		var staticServer='<%=CommonConstants.staticServer%>';//静态服务器 域名
	</script>
<sitemesh:head></sitemesh:head>
</head>
<body class="W-body">
	<%--变量--%>
		<%--是否购买QQ第三方登录服务--%>
	<input type="hidden" id="qqLoginIsOpen" value="<%=CacheConstans.QQ_LOGIN_IS_OPEN%>"/>
		<%--是否购买SINA第三方登录服务--%>
	<input type="hidden" id="sinaLoginIsOpen" value="<%=CacheConstans.SINA_LOGIN_IS_OPEN%>"/>
		<%--是否购买WEIXIN第三方登录服务--%>
	<input type="hidden" id="weixinLoginIsOpen" value="<%=CacheConstans.WEIXIN_LOGIN_IS_OPEN%>"/>
		<%--用户 cookie key--%>
	<input type="hidden" id="cookieKey" value="<%=CacheConstans.WEB_USER_LOGIN_PREFIX%>"/>
		<%--Cookie域--%>
	<input type="hidden" id="emailRegister" value="${registerController.emailRegister}"/>
	<input type="hidden" id="phoneRegister" value="${registerController.phoneRegister}"/>
	<input type="hidden" id="emailProving" value="${registerController.emailProving}"/>
	<input type="hidden" id="phoneProving" value="${registerController.phoneProving}"/>
	<input type="hidden" id="emailRecovery" value="${registerController.emailRecovery}"/>
	<input type="hidden" id="mobileRecovery" value="${registerController.mobileRecovery}"/>
	<%--变量--%>
	<div class="in-wrap" >
		<div class="h-mobile-mask"></div>
		<div class="head-mobile">
			<div class="head-mobile-box">
				<section class="clearfix">
					<div class="u-face-pic">
						<img src="${ctx }/static/inxweb/img/avatar-boy.gif" alt="" class="userImgPhoto">
					</div>
					<h4 class="hLh30 txtOf">
				<span class="fsize16 c-ccc userNameClass">
					<span class="vam ml5" style="cursor:pointer;" onclick="lrFun()">登录</span>
				</span>
					</h4>
					<div class="hLh30">
						<a href="${ctx }/shopcart?command=queryShopCart" title="购物车" class="c-999 u-c-shopcar">购物车</a>
					</div>
				</section>
				<nav class="mw-nav">
					<ul class="clearfix">
						<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
							<li><a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
						</c:forEach>
					</ul>
				</nav>
				<section class="u-m-dd">
					<ul class="">
						<li>
							<a href="${ctx}/uc/index" title="我的课程">
								我的课程
							</a>
						</li>
						<li>
							<a href="${ctx}/uc/courseNote" title="我的笔记">
								我的笔记
							</a>
						</li>
						<li>
							<a href="${ctx}/uc/myFavorites" title="课程收藏">
								课程收藏
							</a>
						</li>
						<c:if test="${serviceSwitch.coupon=='ON'}">
							<li>
								<a href="${ctx}/uc/myCouPon" title="优惠劵">
									优惠劵
								</a>
							</li>
						</c:if>
						<c:if test="${serviceSwitch.member=='ON'}">
							<li>
								<a href="${ctx}/uc/associator" title="我的会员">
									我的会员
								</a>
							</li>
						</c:if>
						<li>
							<a href="${ctx}/uc/order/myOrderList/ALL" title="订单中心">
								订单中心
							</a>
						</li>
						<c:if test="${serviceSwitch.account=='ON'}">
							<li>
								<a href="${ctx}/uc/myAccount" title="我的账户">
									我的账户
								</a>
							</li>
						</c:if>
						<c:if test="${serviceSwitch.invite=='ON'}">
							<li>
								<a href="${ctx}/uc/myInvite" title="我的邀请">
									邀请好友
								</a>
							</li>
						</c:if>
						<li>
							<a href="${ctx}/uc/initUpdateUser/0" title="个人资料">
								个人资料
							</a>
						</li>
						<li>
							<a href="${ctx}/uc/initUpdateUser/1" title="密码设置">
								密码设置
							</a>
						</li>
						<li>
							<a href="/uc/letter" title="消息中心">
								消息中心
							</a>
						</li>
					</ul>
					<div  id="mobileExitDiv" class="mt15 undis tac">
						<a href="javascript:void(0)" title="退出" onclick="exit();" class="u-loginout">退出登录</a>
					</div>
				</section>
			</div>
		</div>
		<div class="main-warp">
			<div class="main-infor">
				<!-- 公共头引入 -->
				<jsp:include page="/WEB-INF/layouts/web/header.jsp" />
				<!-- 公共头引入 -->
				<sitemesh:body></sitemesh:body>
			</div>
		</div>
	</div>
	<div class="mo-footer">
		<!-- 公共底引入 -->
		<jsp:include page="/WEB-INF/layouts/web/footer.jsp" />
		<!-- 公共底引入 -->
	</div>
	<jsp:include page="/WEB-INF/layouts/limit_login/limit_login.jsp" />
	</body>
</html>
