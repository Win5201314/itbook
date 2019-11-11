<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<jsp:include page="/WEB-INF/layouts/limit_login/limit_login.jsp" />
<%--用户 cookie key--%>
<input type="hidden" id="cookieKey" value="<%=CacheConstans.WEB_USER_LOGIN_PREFIX%>"/>
<%--Cookie域--%>
<input type="hidden" id="mydomain" value="<%=CommonConstants.MYDOMAIN%>"/>
<script>
	//视频类型
	videotype="${videotype}";
</script>
<c:choose>
	<c:when test="${videotype=='IFRAME'}"> <%-- SWF/56视频/IFRAME--%>
		<!-- 错误类型的 先用iframe承接 -->
		<iframe id="playervideoiframe" src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>

	</c:when>
	<c:when test="${videotype=='INXEDUVIDEO'}"> <%-- 因酷云视频视频 --%>
		<div id="videoareaname" style="width: 100%;height: 100%"></div>
		<script>
			var vodparam = "${videourl}";
			cloudsdk.initplay("videoareaname",{"src":vodparam,"id":"cloudsdk","isautosize":"0"});
			var html =  $("video").parent().html();
			$("video").remove();
			if(html!=null&&html!=''){
				$("#videoareaname").html(html);
			}
		</script>
	</c:when>
	<c:when test="${videotype=='INXEDUCLOUD'}"> <%-- 因酷云视频视频 --%>
		<div id="play" style="width: 100%;height: 100%"></div>
		<script type="text/javascript">
			/*objectPlayer=new aodianPlayer({
				container:'play',//播放器容器ID，必要参数
				//rtmpUrl: 'rtmp://25424.lssplay.aodianyun.com/lucky/stream',//控制台开通的APP rtmp地址，必要参数
				hlsUrl: '${videourl}',//控制台开通的APP hls地址，必要参数
				/!* 以下为可选参数*!/
				width: '100%',//播放器宽度，可用数字、百分比等
				height: '100%',//播放器高度，可用数字、百分比等
				autostart: true,//是否自动播放，默认为false
				bufferlength: '1',//视频缓冲时间，默认为3秒。hls不支持！手机端不支持
				maxbufferlength: '2',//最大视频缓冲时间，默认为2秒。hls不支持！手机端不支持
				stretching: '1',//设置全屏模式,1代表按比例撑满至全屏,2代表铺满全屏,3代表视频原始大小,默认值为1。hls初始设置不支持，手机端不支持
				controlbardisplay: 'enable',//是否显示控制栏，值为：disable、enable默认为disable。
				//adveDeAddr: '',//封面图片链接
				//adveWidth: 320,//封面图宽度
				//adveHeight: 240,//封面图高度
				//adveReAddr: '',//封面图点击链接
				//isclickplay: false,//是否单击播放，默认为false
				isfullscreen: true,//是否双击全屏，默认为true
				onReady:function() {
					objectPlayer.addPlayerCallback('play.stop', function () {
						//alert("play.stop");
					});
					if(!checkIsMobile() && isNotEmpty("${lastPlayTime}")){
						/!*hls点播输入某个时间，从该时间点播放 pc端使用 仅支持pc端播放hls*!/
						objectPlayer.setcurrentTime("${lastPlayTime}",true);
						//objectPlayer.startPlay();
					}
				}
			});*/

			var configArr="${configArr}".split("|");
			var showinfo=isNotEmpty( isNotEmpty("${user.mobile}")?"${user.mobile}":"${user.email}" )?"${user.email}":"${user.showName}";
            objectPlayer=new mpsPlayer({
                container:'play',//播放器容器ID，必要参数
                uin: configArr[0],//用户ID
                appId: configArr[1],//播放实例ID
                //rtmpUrl: "",  //rtmp地址 直播可以同设置rtmp和hls地址  优先使用rtmp地址
                hlsUrl: "${videourl}",  //hls地址 点播视频只需设置hls地址
                width: '100%',//播放器宽度，可用数字、百分比等
                height: '100%',//播放器高度，可用数字、百分比等
                autostart: true,//是否自动播放，默认为false
                stretching: 1,  //设置全屏模式,1代表按比例撑满至全屏,2代表铺满全屏,3代表视频原始大小,默认值为1。hls初始设置不支持，手机端不支持
                mobilefullscreen: false,  //移动端是否全屏，默认为true
                controlbardisplay: 'enable',//是否显示控制栏，值为：disable、enable默认为disable。
                isclickplay: true,//是否单击播放，默认为false
                isfullscreen: true,//是否双击全屏，默认为true
                onReady:function() {
                    /*objectPlayer.addPlayerCallback('play.stop', function () {
                        //alert("play.stop");
                    });*/
                    if(!checkIsMobile() && isNotEmpty("${lastPlayTime}")){
                        /*hls点播输入某个时间，从该时间点播放 pc端使用 仅支持pc端播放hls*/
                        objectPlayer.setcurrentTime("${lastPlayTime}",true);
                        //objectPlayer.startPlay();
                    }
                }
            });

            //随机 获取定时时间，并且每次都不一样
            var timeArray = [10000, 20000, 30000];
            function randRange() {
                var arrayIndex = Math.floor(Math.random()*timeArray.length);
                var newTime = timeArray[arrayIndex];
                timeArray.splice(arrayIndex,1);
                if(timeArray.length==0){
                    timeArray = [10000, 20000, 30000];
                }
                return newTime;
            }
            function toggleSomething() {
                var _word = showinfo;
                var _color = getColorByRandom();
                var _speed = 10;
                var _font = 20;
                if(_word){
                    objectPlayer.sendbarrage(_word,_font,_color,_speed,'微软雅黑');
                }

                //clearInterval(timer);
                timer = setTimeout(toggleSomething, randRange());
            }
            var timer;
            if(!checkIsMobile()){
                timer = setTimeout(toggleSomething, 10000);
			}
			/* rtmpUrl与hlsUrl同时存在时播放器优先加载rtmp*/
			/* 以下为Aodian Player支持的事件 */
			/* objectPlayer.startPlay();//播放 */
			/* objectPlayer.pausePlay();//暂停 */
			/* objectPlayer.stopPlay();//停止 hls不支持*/
			/* objectPlayer.closeConnect();//断开连接 */
			/* objectPlayer.setMute(true);//静音或恢复音量，参数为true|false */
			/* objectPlayer.setVolume(volume);//设置音量，参数为0-100数字 */
			/* objectPlayer.setFullScreenMode(1);//设置全屏模式,1代表按比例撑满至全屏,2代表铺满全屏,3代表视频原始大小,默认值为1。手机不支持 */

            //随机取一种颜色，并且每次颜色都不一样
            var colorList = ["0xff0000","0x808000","0x008080","0x808080","0xC0C0C0","0xFFFFFF","0xFF00FF","0x0000FF","0xFFFF00","0x800080"];
            function getColorByRandom(){
                var colorIndex = Math.floor(Math.random()*colorList.length);
                var color = colorList[colorIndex];
                colorList.splice(colorIndex,1);
                if(colorList.length==0){
                    colorList = ["0xff0000","0x808000","0x008080","0x808080","0xC0C0C0","0xFFFFFF","0xFF00FF","0x0000FF","0xFFFF00","0x800080"];
                }
                return color;
            }
		</script>
	</c:when>
	<c:when test="${videotype=='CC'}"> <%-- cc视频 --%>
        <script src='http://p.bokecc.com/player?vid=${videourl}&siteid=${ccwebsitemap.inxeduVideo.ccappID}&autoStart=true&width=100%&height=100%&playerid=51A2AD3118ACAD37&playertype=1' type='text/javascript'></script>
	</c:when>
	<c:when test="${videotype=='LETV'}"> <%-- 乐视云--%>
		<script>
			$(function(){
				var width = $("#playervideoiframe").width();
				var height=$("#playervideoiframe").height();
				$("#playervideoiframe").attr("src","${videourl}&width=100%&height="+height);
			});
		</script>
		<iframe id="playervideoiframe" src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>

	</c:when>
	<c:when test="${videotype=='BaoLiWeiShi'}"> <%-- 保利威视--%>
		<div id='plv_div' style="width:100%;height:100%"></div>
		<script>
			var width = $("#plv_div").width();
			var height=$("#plv_div").height();
			var player = polyvObject('#plv_div').videoPlayer({
				'width':''+width+'',
				'height':''+height+'',
				'vid' : '${videourl}'
			});
		</script>
	</c:when>
	<c:when test="${videotype=='baijiayun'}"> <%-- 百家云--%>
		<div id='baijiayun_div' style="width:100%;height:100%"></div>

		<script>
				var player = new bjcPlayer('#baijiayun_div', {
					token: '${videourl}',
					onended: function () {
						 console.log('onended');
					},
					onplay: function () {

						console.log('onplay');
					},
					onpause: function () {
						 console.log('onpause');
					},
					onplaybegin:function(){
						$('#baijiayun_div').unbind("click");
					},
					onplayfinish: function () {
						$("#baijiayun_div").click(function () {
							player.play('${courseKpoint.videoUrl}');
						});
					},
					onpartfinish: function () {
						console.log('onpartfinish event');
					},
					onerror: function (e) {
						console.log('onerror');
						console.log(e);
					}
				});
				player.play('${courseKpoint.videoUrl}');
		</script>
	</c:when>

	<c:when test="${videotype=='tengxunyun'}"> <%-- 腾讯云--%>
		<div id='tengxun_div' style="width:100%;height:100%">
			<iframe src="" id="video_iframe" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
		</div>
		<script>
			var url = "http://play.video.qcloud.com/iplayer.html?$appid=1253254776&$fileid=${videourl}&$autoplay=0&$sw=1280&$sh=720";
			url = url.substring(0,url.indexOf("$sw="));
			var width = $("#tengxun_div").width();
			var height=$("#tengxun_div").height();
			url = url+"$sw="+width+"&$sh="+height;
			$("#video_iframe").attr("src",url);
		</script>
	</c:when>
	<c:otherwise>
		<!-- 错误类型的 先用iframe承接 -->
		<iframe id="playervideoiframe" src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>

	</c:otherwise>
</c:choose>
