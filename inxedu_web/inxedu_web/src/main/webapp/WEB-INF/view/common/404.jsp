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
		<title>404错误页面</title>
		<meta name="author" content="${websitemap.web.author}" />
		<meta name="keywords" content="${websitemap.web.keywords}" />
		<meta name="description" content="${websitemap.web.description}" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
		<meta content="telephone=no" name="format-detection" />
		<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
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
	</head>
	<body>
		<div class="in-wrap" >
			<!-- 公共头引入 -->
			<jsp:include page="/WEB-INF/layouts/web/header.jsp" />
			<!-- 公共头引入 -->
			<%--<div class="body-404">
				<section class="container">
					<div class="e-4-wrap">
						<div class="fl-2">
							<div class="e-4-txt-wrap">
								<h2 class="c-master f-fH">找不到该页面！</h2>
								<div>
									<p class="e-4-t-1 c-666">无法访本页的原因是：</p>
									<p class="e-4-t-1 c-666">你使用的URL可能拼写错误或者它只是临时脱机</p>
									<p class="e-4-t-1 c-666">所访问的页面不存在或被管理员已删除</p>
								</div>
								<div class="mt50">
									<h4 class="c-333 f-fH">请尝试以下操作：</h4>
									<p class="e-4-t-1 c-666">1、尝试按F5进行页面刷新</p>
									<p class="e-4-t-1 c-666">2、重新键入URL地址进入访问</p>
									<p class="e-4-t-1 c-666">3、或返回 <a class="f-fH c-master" href="${ctx }" title="">网站首页</a></p>
								</div>
							</div>
						</div>
					</div>
					<!-- /400-demo -->
				</section>
			</div>--%>
			<div class="body-404">
				<section class="container">
					<div class="e-4-wrap">
						<div class="fl-2">
							<div class="e-4-txt-wrap">
								<div>
									<h2 class="c-master f-fH">OH，有错误发生！！</h2>
								</div>
								<div class="mt50">
									<p class="e-4-t-1 c-666">很抱歉，系统暂时无法处理您的访问请求！</p>
									<p class="e-4-t-1 c-666">返回 <a class="c-master mr30"href="${ctx }" title="">网站首页</a><a class="c-master" href="javascript:window.location.reload();" title="">H5刷新当前页</a></p>
								</div>
							</div>
						</div>
					</div>
					<!-- /400-demo -->
				</section>
			</div>
			<!-- 公共底引入 -->
			<jsp:include page="/WEB-INF/layouts/web/footer.jsp" />
			<!-- 公共底引入 -->
		</div>
	</body>
</html>