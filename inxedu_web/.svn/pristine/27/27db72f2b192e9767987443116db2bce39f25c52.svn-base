<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<link href="${ctx}/static/common/AudioPlayer/mobile/audio.css" rel="stylesheet" type="text/css">
<div class="ado-box">
	<section class="ado-cover">
		<div class="ado-cover-pic">
			<aside class="ado-cover-wrap">
				<span id="buffer"></span>
				<span class="coures-zhao"></span>
				<c:choose>
					<c:when test="${not empty course.logo}">
						<img class="ado-c-img" src="${staticServer}${course.logo}" alt="">
					</c:when>
					<c:otherwise>
						<img class="ado-c-img" src="/static/common/AudioPlayer/mobile/sprite.gif" alt="">
					</c:otherwise>
				</c:choose>

			</aside>
		</div>
		<div class="ado-cover-name">
			<h6><span id="aName"></span></h6>
		</div>
	</section><!-- /封面图转动区域 -->
	<section class="ado-audio-wrap">
		<audio id="audio" src=""></audio>
		<a href="javascript: void(0)" title="播放" id="btn-playorpause" class="btn-playorpause">播放</a>
		<div id="container" class="stick-wrap">
			<div id="stick" class="stick-bar"></div>
		</div>
		<aside class="ado-time-wrap">
			<section class="ado-time-detail">
				<span class="currentTime" id="adoCurrentTime">00:00</span>/<span class="allTime" id="adoAllTime">

            </span>
			</section>
		</aside>
	</section><!-- /H5音频播放器 -->
</div>
<script>
	var musicName="${courseKpoint.name}";
	var musicUrl="${staticServer}${courseKpoint.videoUrl}";
	var musicTime="${courseKpoint.playTime }";

	$(function() {
		$("#adoAllTime").text(musicTime);

		//音频播放 单独重新设置  播放器高度动态赋值
		$("#p-h-box").css("height", "312px");
		$("#p-h-r-cont").css("height", "207px");
		$(".p-h-video").css("height", "240px");
	})
</script>
<script src="${ctx}/static/common/AudioPlayer/mobile/audio.js" type="text/javascript" charset="utf-8"></script>