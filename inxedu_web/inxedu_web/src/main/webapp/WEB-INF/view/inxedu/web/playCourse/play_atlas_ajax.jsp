<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<style type="text/css">
	.pic-box{width: 100%;height: 96%;overflow-y: auto;}
	.lig-box-cx{margin: 20px 0 0;}
	.lig-box-cx li{float: left;width: 14%;margin:0px 0px 30px 33px ;background: #fff;}
	.big-lig-box{border: 2px solid transparent;}
	.lig-pic-box{padding:2px;background: #fafafa;position: relative;margin-bottom: 5px;}
	.lig-pic-box img{display: block;cursor: pointer;cursor: url(${ctx}/static/inxweb/img/big.cur), auto;width: 100%;}
	.lig-box-cx li:hover .big-lig-box{cursor:pointer;border-color: #ff1e1e;}
	.p-h-video-tip{display: none;}
</style>
<div class="pic-box">
	<ul class="clearfix lig-box-cx">
		<c:forEach items="${courseKpoint.kpointAtlasesList}" var="atlas" varStatus="statu">
		<li>
			<div class="big-lig-box">
				<div class="lig-pic-box">
					<a href="${staticServer}${atlas.url}" title="图片${statu.index+1}" rel="lightbox[plants]" class="lightbox-enabled">
						<img src="${staticServer}${atlas.urlThumbnail}" alt="">
					</a>
				</div>
				<div class="hLh30 tac fsize14 c-333 f-fM mb5">图片${statu.index+1}</div>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/lightbox/jquery.lightbox.css">
<script type="text/javascript" src="${ctx}/static/inxweb/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/lightbox/jquery.lightbox.min.js"></script>
<script>
	//视频类型
	videotype="${courseKpoint.videoType}";
</script>