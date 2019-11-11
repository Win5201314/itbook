<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>直播播放</title>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<script type="text/javascript" src="${ctx}/static/common/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/webutils.js"></script>
	<style>
	html,body{height: 100%}
	body {margin: 0!important;}
	</style>
</head>
<body>
<%--用户 cookie key--%>
<input type="hidden" id="cookieKey" value="<%=CacheConstans.WEB_USER_LOGIN_PREFIX%>"/>
<%--Cookie域--%>
<input type="hidden" id="mydomain" value="<%=CommonConstants.MYDOMAIN%>"/>
<jsp:include page="/WEB-INF/layouts/limit_login/limit_login.jsp" />
<c:if test="${success}">
	<%--<iframe id="playervideoiframe" src="${url}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>--%>

    <div  id="mydiv" style="background-color:#000000;width:812px; height:440px;">

    </div>
    <script type="text/javascript" src="${ctx}/static/inxweb/live/swfs/swfobject.js" ></script>
    <script type="text/javascript" >
        function initPlayer(rtmpUrl){
            var p = {
                //视频地址
                src : rtmpUrl,
                //是否自动隐藏控制栏
                controlBarAutoHide : "false",
                //控制栏位置
                controlBarPosition: "bottom",
                //流类型
                streamType : "live",
                //是否自动播放
                autoPlay : "true",
                //不显示详细信息
                verbose : "false",
                //是否显示缓冲中字样
                bufferingOverlay : "true",
                //播放前显示的自定义画面
                poster : "images/logo.gif",
                //播放结束的自定义画面
                //endOfVideoOverlay : ctx + "/images/homepage/logo.png",
                //是否自动切换清晰度（客户端），无作用
                autoSwitchQuality : "true",
                //超时时间
                rtmpNetConnectionFactoryTimeout : 20,
                //js事件桥接
                javascriptCallbackFunction: "onJavaScriptBridgeCreated"
            };
            var ppi = "${ctx}/static/inxweb/live/swfs/playerProductInstall.swf";
            var params = {
                quality : "high",
                scale :	"noborder",
                bgcolor : "#000000",
                allowscriptaccess : "sameDomain",
                allowfullscreen	: "true",
                wmode:"Opaque"
            };
            var attributes = {
                id : "player",
                name : "player",
                align : "middle"
            };
            $("#mydiv").html("");
            swfobject.embedSWF("${ctx}/static/inxweb/live/swfs/StrobeMediaPlayback.swf", "mydiv", "812", "440", "10.3.0", ppi, p, params, attributes);
            swfobject.createCSS("#mydiv", "display:block;text-align:left;");
        }

        initPlayer("${url}");
    </script>
</c:if>
<c:if test="${!success}">
	${url}
</c:if>
</body>
</html>