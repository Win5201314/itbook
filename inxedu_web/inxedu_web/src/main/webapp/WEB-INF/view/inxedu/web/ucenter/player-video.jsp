﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>播放大厅-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}" />
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}" />
	<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/theme.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/global.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/css/web.css">
	<link href="${ctx}/static/inxweb/css/mw_320_768.css" rel="stylesheet" type="text/css" media="screen and (min-width: 320px) and (max-width: 768px)">
	<!--[if lt IE 9]><script src="${ctx}/static/inxweb/js/html5.js"></script><![endif]-->
	<%--<script src="http://vod.baofengcloud.com/html/script/bfcloud.js?v=2"></script>--%>
	<script src='http://player.polyv.net/script/polyvplayer.min.js'></script>
	<c:if test="${judgeIsMoblie==true}">
		<link rel="stylesheet" type="text/css" href="http://www.baijiacloud.com/m/asset/playback/player.css">
		<script type="text/javascript" src="http://www.baijiacloud.com/m/asset/playback/player.js"></script>
	</c:if>
	<c:if test="${judgeIsMoblie!=true}">
		<link rel="stylesheet" type="text/css" href="http://www.baijiacloud.com/web/asset/playback/player.css">
		<script type="text/javascript" src="http://www.baijiacloud.com/web/asset/playback/player.js"></script>
	</c:if>
</head>
<body>
	<section class="new-pl-vi-box pr">
		<article class="n-p-v-top">
			<h4 class="fsize16 c-fff f-fM unFw disIb vam">
				<a href="" class="m-n-plback-ico">&nbsp;</a>
				${course.courseName}
			</h4>
		</article>
		<article class="n-p-v-bottom clearfix">
			<div class="pr n-p-vb-in">
				<div class="n-p-v-b-lt">
					<div  id="mydiv" style="background-color:#000000;width:1400px; height:800px;">

					</div>
					<script type="text/javascript" src="${ctx}/static/inxweb/live/swfs/swfobject.js" ></script>
					<script type="text/javascript" >
                        function initPlayer(rtmpUrl){
                            var flashvars  = {
                                //视频地址
                                src : rtmpUrl,
                                //是否自动隐藏控制栏
                                controlBarAutoHide : "false",
                                //控制栏位置
                                controlBarPosition: "bottom",
                                //流类型
                                // streamType : "live",
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
                            // $("#mydiv").html("");
                            swfobject.embedSWF("${ctx}/static/inxweb/live/swfs/StrobeMediaPlayback.swf", "mydiv", "1400", "800", "10.3.0", ppi, flashvars , params, attributes);
                            swfobject.createCSS("#mydiv", "display:block;text-align:left;");
                        }
                        initPlayer("${parentKpointList[0].videoUrl}");
					</script>
					<%--<section class="playBox" id="videoPlay"></section>--%>
				</div>
				<article class="video-open-btn" id="o-c-btn">
					<a href="javascript:void(0);" title="展开" class="">&nbsp;</a>
				</article>
				<div class="n-p-v-b-rt">
					<section class="n-p-v-b-rt-in">
						<section class="p-h-r-title">
							<ol id="p-h-r-title">
								<li>
									<a class="course_mulu" href="javascript: void(0)" onclick="toChapterOrNote('note')" title="课程目录">课程目录</a>
								</li>
								<li class="current">
									<a href="javascript: void(0)" title="课程笔记" onclick="toChapterOrNote('chapter')">课程笔记</a>
								</li>
							</ol>
							<div class="clear"></div>
						</section>
						<div id="chapter" class="p-h-r-list">
							<menu id="lh-menu" class="lh-menu">
								<ul >
									<c:forEach items="${parentKpointList }" var="parentKpoint" varStatus="index">
										<c:if test="${parentKpoint.kpointType==0 }"><!-- 文件目录 -->
											<li  class="lh-menu-stair">
												<a href="javascript: void(0)"  title="${parentKpoint.name }" class="l-m-stitle"
												   <c:if test="${index.first==true}">class="current-1 l-m-stitle"</c:if>>
													<span class="fr"><em class="icon16 m-tree-icon">&nbsp;</em></span>
														${parentKpoint.name }
												</a>
												<ol class="lh-menu-ol"
													<c:if test="${index.first==true}">style="display: block;"</c:if>
													<c:if test="${index.first==false}">style="display: none;"</c:if>
												>
													<c:forEach items="${parentKpoint.kpointList}" var="sonKpoint">
														<li class="lh-menu-second">
															<div class="l-m-stitle clearfix">
																<c:choose>
																	<%--如果章节id等于最近学习的章节id--%>
																	<c:when test="${not empty lastTimeStudyhistory && sonKpoint.kpointId==lastTimeStudyhistory.kpointId }">
																		<span class="fr mr20">
																			<em class="icon14 u-c-jxzico">&nbsp;</em>
																				<tt class="vam fsize12 f-fM c-master TxT">近期学习</tt>
																		</span>
																	</c:when>
																	<c:otherwise>
																		<c:if test="${sonKpoint.kpointId!=lastTimeStudyhistory.kpointId && sonKpoint.isStudy=='true'}">
																			<span class="fr mr20">
																				<em class="icon14 u-c-overico">&nbsp;</em>
																					<tt class="vam fsize12 f-fM c-green TxT">已学习</tt>
																			</span>
																		</c:if>
																		<c:if test="${sonKpoint.kpointId!=lastTimeStudyhistory.kpointId && sonKpoint.isStudy=='false'}">
																			<span class="fr mr20">
																					<em class="icon14 u-c-wksioc">&nbsp;</em>
																					<tt class="vam fsize12 f-fM c-666 TxT">未学习</tt>
																				</span>
																		</c:if>
																	</c:otherwise>
																</c:choose>
														<c:if test="${sonKpoint.fileType=='AUDIO' }">
															<em class="lh-p-icon-yp icon20">&nbsp;</em>
														</c:if>
														<c:if test="${sonKpoint.fileType=='VIDEO' }">
															<em class="lh-p-icon-sp icon20">&nbsp;</em>
														</c:if>
														<c:if test="${sonKpoint.fileType=='TXT' }">
															<em class="lh-p-icon-wd icon20">&nbsp;</em>
														</c:if>
														<c:if test="${sonKpoint.fileType=='PDF' }">
															<em class="lh-p-icon-wd icon20">&nbsp;</em>
														</c:if>
														<c:if test="${sonKpoint.fileType=='ATLAS' }">
															<em class="lh-p-icon-tp icon20">&nbsp;</em>
														</c:if>
														<c:if test="${sonKpoint.fileType=='EXERCISES' }">
															<em class="lh-p-icon-wd icon20">&nbsp;</em>
														</c:if>
													</span>
																<a  id="playKpointId${sonKpoint.kpointId }" href="javascript:void(0)" onclick="getPlayerHtml(${sonKpoint.kpointId },this,'${sonKpoint.name }')" title="${sonKpoint.name }"  class="cou-tit-txt">
																		${sonKpoint.name }
																</a>

															</div>
														</li>
													</c:forEach>
												</ol>
											</li>
											<c:set var="folderIndex" value="${folderIndex+1 }"/>
										</c:if>
										<c:if test="${parentKpoint.kpointType==1 && parentKpoint.parentId==0 }"><!-- 视频 而且没有父级-->
											<li class="lh-menu-stair">
												<ul class="lh-menu-ol no-parent-node">
													<li class="lh-menu-second">
														<div class="l-m-stitle clearfix">
															<c:choose>
																<%--如果章节id等于最近学习的章节id--%>
																<c:when test="${not empty lastTimeStudyhistory && parentKpoint.kpointId==lastTimeStudyhistory.kpointId }">
																		<span class="fr mr20">
																			<em class="icon14 u-c-jxzico">&nbsp;</em>
																				<tt class="vam fsize12 f-fM c-master TxT">近期学习</tt>
																		</span>
																</c:when>
																<c:otherwise>
																	<c:if test="${parentKpoint.kpointId!=lastTimeStudyhistory.kpointId && parentKpoint.isStudy=='true'}">
																			<span class="fr mr20">
																				<em class="icon14 u-c-overico">&nbsp;</em>
																					<tt class="vam fsize12 f-fM c-green TxT">已学习</tt>
																			</span>
																	</c:if>
																	<c:if test="${parentKpoint.kpointId!=lastTimeStudyhistory.kpointId && parentKpoint.isStudy=='false'}">
																			<span class="fr mr20">
																					<em class="icon14 u-c-wksioc">&nbsp;</em>
																					<tt class="vam fsize12 f-fM c-666 TxT">未学习</tt>
																				</span>
																	</c:if>
																</c:otherwise>
															</c:choose>
												<span class="">
													<c:if test="${parentKpoint.fileType=='AUDIO' }">
														<em class="lh-p-icon-yp icon20">&nbsp;</em>
													</c:if>
													<c:if test="${parentKpoint.fileType=='VIDEO' }">
														<em class="lh-p-icon-yp icon20">&nbsp;</em>
													</c:if>
													<c:if test="${parentKpoint.fileType=='TXT' }">
														<em class="lh-p-icon-wd icon20">&nbsp;</em>
													</c:if>
													<c:if test="${sonKpoint.fileType=='PDF' }">
														<em class="lh-p-icon-wd icon20">&nbsp;</em>
													</c:if>
													<c:if test="${parentKpoint.fileType=='ATLAS' }">
														<em class="lh-p-icon-tp icon20">&nbsp;</em>
													</c:if>
													<c:if test="${parentKpoint.fileType=='EXERCISES' }">
														<em class="lh-p-icon-hk icon20 ">&nbsp;</em>
													</c:if>
												</span>
															<a id="playKpointId${parentKpoint.kpointId }" href="javascript:void(0)" title="${parentKpoint.name }" onclick="getPlayerHtml(${parentKpoint.kpointId },this,'${parentKpoint.name }')" class="cou-tit-txt">
																	${parentKpoint.name }
															</a>
														</div>
													</li>
												</ul>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</menu>
						</div>
						<div id="note" class="undis">
							<div class="video-bj-warp" id="notesContext">
								<!-- 课程笔记 位置 -->
								<section class="v-bj-box" >


								</section>
								<section class="mt20 clearfix">
								<span class="fr">
									<tt class="mr5 c-orange" style="display: none;" id="notContextId"></tt>
									<a href="javascript: void(0)" onclick="addNotest()" title="保存" class="lh-reply-btn">保存</a>
								</span>
								</section>
							</div>
						</div>
					</section>
				</div>
				<div class="clear"></div>
			</div>
		</article>
	</section>
	<script type="text/javascript" src="${ctx}/static/common/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/webutils.js"></script>
	<script type="text/javascript" src="${ctx }/static/inxweb/js/common.js" ></script>
	<script src="/static/inxweb/comment/comment.js" type="text/javascript"></script><!-- 评论js -->
	<script type="text/javascript" src="${ctx }/kindeditor/kindeditor-all.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/play/playVideo.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/course-kpoint-list.js"></script>
	<script type="text/javascript" src="http://cdn.aodianyun.com/mps/v1/hlsplayer.js"></script>
	<script src="${ctx}/static/common/jquery.nicescroll.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(".p-h-r-list .lh-menu").niceScroll({
			cursorcolor:"#fafafa",
			cursoropacitymax:1,
			touchbehavior:false,
			cursorwidth:"3px",
			cursorborder:"0",
			cursorborderradius:"3x"
		});
	</script>

	<%--聊天--%>
	<jsp:include page="/WEB-INF/layouts/limit_login/limit_login.jsp" />
	<script src='${ctx}/static/inxweb/chat/chat.js'></script>
	<script>
		playFromType = 2;
		//评论课程id
	var otherId = '0';
	var courseId = '${course.courseId}';
	//评论类型,类型2为课程 4章节
	var type = 4;
	var currentKpointId="0";//当前播放视频id(没有视频节点默认为零)
	var playKpointId="${playKpointId}";//要播放的 视频节点

	var isok="${isok}";//是否可以播放
	var message="${message}";//提示信息
	var studyPercent="${course.studyPercent}";//学习进度百分比
	//手机端 图片集、pdf 返回标识
	var goBack="${goBack}";
	//视频类型全局变量
	var videotype;
	//因酷云播放器全局变量
	var objectPlayer;

	getCourseKpointList("${course.courseId}",2);
	/**
	 * 获得课程章节目录
	 */
	function getCourseKpointList(courseId,type) {
		$.ajax({
			url : baselocation + "/front/ajax/courseKpointList/"+courseId+"/"+type,
			data:{},
			type : 'post',
			dataType : 'text',
			success : function(result) {
				if(checkIsMobile()){// 移动端环境下效果
					$(".courseKpointHtml").prev().hide();
					$(".courseKpointHtml").show();
					$(".courseKpointHtml").html(result);
					$(".mobileCommentHide").hide();//精彩评论 和课程讨论隐藏
				}else{
					$("#courseKpointMenu").html(result);
					//queryComment();// 查询课程评论
					$("#tabTitleKpoint.c-infor-tabTitle.c-tab-title").find("a").eq(2).addClass("current").siblings().removeClass("current");
				}
				treeMenu(); //课程树
			}
		});
	}
	//点击查询章节
	function queryCourseKpointList(obj){
		$(obj).removeClass("current").siblings().removeClass("current");
		$(obj).addClass("current");
		if(isNotEmpty($(".courseKpointHtml").html())){
			$(".courseKpointHtml").prev().hide();
			$(".courseKpointHtml").show();
		}else{
			getCourseKpointList("${course.courseId}",2);
		}
	}
	/*课程目录或笔记切换*/
	function toChapterOrNote(str) {
		if (str=="chapter"){
			$("#chapter").addClass("undis");
			$("#note").removeClass("undis");
			/*查询笔记*/
			$.ajax({
				url : baselocation + "/courseNote/ajax/querynote",
				type : "post",
				dataType : "text",
				data : {
					'kpointId' : playKpointId,
					"courseId" : courseId
				},
				success : function(result) {

					$("#notesContext").html(result);
				}
			});
		}
		if (str=="note"){
			$("#note").addClass("undis");
			$("#chapter").removeClass("undis");
			$("#notContextId").html("")
		}

	}
	/*如果是从课程笔记进入播放大厅 点击笔记按钮*/
	$(function () {
		var courseNote = ${isCourseNote}+"";
		if(courseNote=="true"){
			$("#p-h-r-title li").last().click();
			toChapterOrNote('chapter')
		}
	})
	</script>





</body>
</html>
