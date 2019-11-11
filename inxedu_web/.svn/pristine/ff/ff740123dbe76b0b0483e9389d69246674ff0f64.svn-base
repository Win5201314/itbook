<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" http-equiv="Content-Type" />
<title>${websitemap.web.company}-${websitemap.web.title}</title>
<meta name="author" content="${websitemap.web.author}" />
<meta name="keywords" content="${websitemap.web.keywords}" />
<meta name="description" content="${websitemap.web.description}" />
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
<link type="text/css" href="${ctx}/static/common/jerichotab/css/jquery.jerichotab.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/static/common/jerichotab/js/jquery.jerichotab.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/easyTooltip.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/hoverIntent.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/superfish.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/custom.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/main/main.js"></script>

<script type="text/javascript">
	var tabTitleHeight = 33; // 页签的高度
	 /*sMenu*/
	 $(function() {
		 queryheader();
		 slideScroll();
		 navFun();
		 $(window).resize(function() {navFun();});
		 $.fn.initJerichoTab({
			 renderTo: '#right', uniqueId: 'jerichotab',
			 contentCss: { 'height': $('#right').height() - tabTitleHeight },
			 tabs: [], loadOnce: false, tabWidth: 110, titleHeight: tabTitleHeight
		 });
		 //先隐藏，第一次显示iframe
		 $("#jerichotab").hide();
	 });

	/**
	 * mainFrame 加载完成后 设置mainFrame 高度
	 */
	window.onload = function () {
		setIframeHeight(document.getElementById('mainFrame'));
	};

</script>
</head>
<body>
	<div class="tHeader headerimg">
		<div>
			<div id="header">
				<div id="top">
					<div class="logo">
						<h1>
							<a href="http://demo1.inxedu.com/" target="_blank" title="因酷在线教育软件 - 在线教育整体解决方案提供商" class="tooltip">
								<img src="${ctx}/static/admin/assets/logo.png"  height="56" alt="因酷在线教育软件 - 在线教育整体解决方案提供商" />
							</a>
						</h1>
					</div>
					<div class=" nav-bar headerhtml" id="menuHeader">
						<div class="nav-wrap">
							<div class="navList">
								<ul>
								</ul>
							</div>
						</div>
					</div>
					<div class="meta">
						<p>欢迎来到${websitemap.web.company}后台管理系统!</p>
						<ul>
							<li>
								<a href="${ctx}/admin/outlogin" title="退出系统" class="tooltip">退出系统
								</a>
							</li>
							<li>
								<a href="javascript:void(0)" onclick="adminChangePwd()" title="修改密码" class="tooltip">修改密码</a>
							</li>
							<li>
								<a href="javascript:void(0)" title="${sysuser.loginName}" class="tooltip">${sysuser.loginName}</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="container">
		<div id="bgwrap" class="clearfix">
			<div id="content">
				<div id="main">
					<div id="right" width="100%">
						<iframe id="mainFrame" name="mainFrame" src="${ctx}/admin/main/index" style="overflow:visible;visibility: hidden" scrolling="yes" frameborder="no" width="100%"></iframe>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div id="ui-sMenu" class="ui-accordion ui-widget ui-helper-reset">
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<p class="mid c-666">${websitemap.web.copyright}</p>
		<p class="mid c-666">
			Powered By <a target="_blank" href="http://www.inxedu.com/" style="color: #999;">因酷教育软件</a>
		</p>
	</div>
</body>
</html>


