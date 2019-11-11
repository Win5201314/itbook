<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<%@page isErrorPage="true"%>
<html>
<title>系统异常</title>
<head>
	<script type="text/javascript">
		function showOrCloseErrorInfo() {
			var div = document.getElementById("error_div");
			if(div.style.display == "none") {
				div.style.display = "block";
			} else {
				div.style.display = "none";
			}
		}
	</script>
</head>
<body>
<!-- 主体 开始-->
<div class="body-error">
	<div class="e-4-wrap e-4-error">
		<div class="fl-2">
			<div class="e-4-txt-wrap">
				<div class="mt50 pt30 error-txt">
					<h4 class="c-master f-fH error-txt-n">请尝试以下操作：</h4>
					<p class="e-4-t-1 c-666 error-txt-n">1、尝试按F5进行页面刷新</p>
					<p class="e-4-t-1 c-666 error-txt-n">2、重新键入URL地址进入访问</p>
					<p class="e-4-t-1 c-666 mt30 error-txt-btn">
						<a class="mr30" href="${ctx }" title="首页">
							<img src="${ctx}/static/inxweb/img/home-error.png">
						</a>
						<a class="" href="javascript:window.history.go(-1)" title="返回">
							<img src="${ctx}/static/inxweb/img/back-error.png">
						</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- /error-demo -->
</div>
<div id="error_div" style="display: none" align="left">
	<%
		//show error message
		if(exception!=null){
			StackTraceElement[] messages = exception.getStackTrace();
			if (messages!=null) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(exception.toString()).append("<br/>");
				for (int i = 0; i < messages.length; i++) {
					buffer.append(messages[i].toString()).append("<br/>");
				}
				out.println(buffer.toString());
			}
		}
		else{
			out.println(request.getAttribute("myexception").toString());
		}
	%>
</div>
</body>
</html>