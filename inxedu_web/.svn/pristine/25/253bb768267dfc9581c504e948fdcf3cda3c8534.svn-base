<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8">
<title><sitemesh:title></sitemesh:title></title>
<%--<link type="text/css" href="${ctx}/static/admin/css/layout.css" rel="stylesheet" />--%>
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="${ctx}/static/common/jquery-1.7.2.min.js"></script>


<script src="/static/common/layui/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/common/admin/js/admin-inxedu.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/webutils.js"></script>
<script type="text/javascript">
	var baselocation = "${ctx}";
	var keuploadSimpleUrl='<%=keuploadSimpleUrl%>';
	var uploadServerUrl='<%=uploadServer%>';
	var uploadSimpleUrl="<%=uploadSimpleUrl%>";
	var pdfUploadAndImg='<%=pdfUploadAndImg%>';
	var uploadShrinkDiagram='<%=uploadShrinkDiagram%>';//图片上传带缩列图
	var staticServer='<%=CommonConstants.staticServer%>';//静态服务器 域名
	$(function () {
		/*延时重新渲染layui 防止有元素未渲染*/
		setTimeout(function () {
			if(layui.form!=null){
				layui.form().render();
			}

		},100)
	});
    </script>
<sitemesh:head></sitemesh:head>
<link rel="stylesheet" href="/static/common/layui/layui/css/layui.css"  media="all">
<link rel="stylesheet" href="/static/common/layui/layui/css/global.css" media="all">
</head>
<body>
	<sitemesh:body></sitemesh:body>
</body>
</html>
