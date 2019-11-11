<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<script src="${ctx}/static/common/johndyer/build/jquery.js"></script>
<script src="${ctx}/static/common/johndyer/build/mediaelement-and-player.min.js"></script>
<link rel="stylesheet" href="/static/common/johndyer/build/mediaelementplayer.css" />
<audio id="player2" AutoPlay="True"  src="${staticServer}${courseKpoint.videoUrl}" type="audio/mp3" controls="controls">
</audio>
	<div class="yinpin pt20 pb50">
		<%--<img src="<%=contextPath%>/static/inxweb/img/yinpin-tu.png" class="img">--%>
	</div>
<script>
	$('#player2').mediaelementplayer();
</script>