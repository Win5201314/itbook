<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Basic Page Needs
        ================================================== -->
	<title>搜索</title>
	<!-- 让360双核浏览器用webkit内核渲染页面
	================================================== -->
	<meta name="renderer" content="webkit">
	<!-- CSS
	================================================== -->
	<link href="/static/inxweb/h5search/css/reset.css" rel="stylesheet" type="text/css">
	<link href="/static/inxweb/h5search/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/static/common/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/webutils.js"></script>
	<script type="text/javascript" src="/static/inxweb/h5search/js/flexible.debug.js"></script>
	<!-- 页面内容加载未完成之前显示loading.gif图标
	================================================== -->
	<script src="/static/inxweb/h5search/js/pageload.js" type="text/javascript" charset="utf-8"></script>
	<!-- 加载iscroll统一各设备滚动条兼容性
	================================================== -->
	<script src="/static/inxweb/h5search/js/iscroll.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var myScroll;
		function loaded() {myScroll = new iScroll('wrapper');}
		document.addEventListener('touchmove', function(e) { e.preventDefault(); }, false);
		document.addEventListener('DOMContentLoaded', function() { setTimeout(loaded, 200); }, false);

		$(function(){
			//获取搜索历史
			var keyWords=unescape(getCookie("${searchType}"));
			if(isNotEmpty(keyWords)&&keyWords!="null"){
				var keyWordsArr=keyWords.split("|");
				var cookieKeyWordHtml="";
				for(var i=0;i<keyWordsArr.length;i++){
					if(isNotEmpty(keyWordsArr[i].trim())&&keyWordsArr[i]!="null"){
						cookieKeyWordHtml+=
								'<li>'+
								'	<div>'+
								'		<em class="icon24 list_search_ioc vam" onclick="clicksearch(this,\''+keyWordsArr[i]+'\')">&nbsp;</em>'+
								'			<font class="fsize16 c-999 vam">'+keyWordsArr[i]+'</font>'+
								'	</div>'+
								'	<a href="javascript:void(0)" onclick="delCookie(this,\''+keyWordsArr[i]+'\')" class="i_cm_close">'+
								'		<em class="icon24 close_ico">&nbsp;</em>'+
								'	</a>'+
								'</li>';
					}
				}
				if(isNotEmpty(cookieKeyWordHtml)){
					$("ul.seach_group_ul").html(cookieKeyWordHtml);
					$("div.undata-wrap").hide();
					$("ul.seach_group_ul").show();
				}
			}

		});

		/**
		 * 点击某个 关键词 搜索
		 */
		function clicksearch(obj,keyword){
			$("#headerformH5Search").find("input").val(keyword);
			$("#headerformH5Search").submit();
		}

		/**
		 * 提交设置cookie
		 */
		function searchKeyWord(obj){
			var keyWord=$(obj).find("input").val();
			var cookiekeyWords=unescape(getCookie("${searchType}"));
			cookiekeyWords=cookiekeyWords.replace(keyWord+"|","");
			setCookie("${searchType}", keyWord+"|"+cookiekeyWords);
		}

		/**
		 * 删除单个
		 * @param obj
		 * @param keyWords
         */
		function delCookie(obj,keyWords){
			var cookiekeyWords=unescape(getCookie("${searchType}"));
			cookiekeyWords=cookiekeyWords.replace(keyWords+"|","");
			setCookie("${searchType}", cookiekeyWords);
			$(obj).parent().remove();
		}

		/**
		 * 清空所有
		 */
		function cleakCookieKeyword(){
			setCookie("${searchType}", "");
			$("ul.seach_group_ul").hide();
			$("div.undata-wrap").show();
		}
	</script>
</head>
<body>
<div id="i_wrap" class="i_unMenu">
	<header class="i_header">
		<section class="i_h_bx">
			<div class="i_h_gb_s_box i_news_seach_box">
				<form action="${ctx}${searchType}" method="post" id="headerformH5Search" onsubmit="searchKeyWord(this)">
					<label class="i_h_gb_search">
						<em class="i_h_gb_s_ico" onclick="$('#headerformH5Search').submit();">&nbsp;</em>
							<input class="i_h_gb_s_input" type="text" name="<c:if test="${searchType=='/front/showcoulist'}">queryCourse.courseName</c:if>
								   <c:if test="${searchType=='/front/showLivelist'}">queryCourse.courseName</c:if>
								   <c:if test="${searchType=='/front/articlelist'}">queryArticle.queryKey</c:if>
								   <c:if test="${searchType=='/front/teacherlist'}">queryTeacher.name</c:if>"
								   placeholder="请输入<c:if test="${searchType=='/front/showcoulist'}">课程</c:if>
								   <c:if test="${searchType=='/front/showLivelist'}">直播</c:if>
								   <c:if test="${searchType=='/front/articlelist'}">资讯</c:if>
								   <c:if test="${searchType=='/front/teacherlist'}">讲师</c:if>名称" />
					</label>
				</form>
			</div>
			<aside class="news_seach_navBtn">
				<a href="javascript:void(0)" title="" onclick="window.history.back(-1);">
					<em class="i_cm_goBack_btn"></em>
					<span class="fsize18 c-fff vam">&nbsp;</span>
				</a>
			</aside>
		</section>
	</header>
	<section id="SL-container" class="i_main">
		<div class="i_mainEle">
			<article id="wrapper">
				<div class="scroller">
					<div>
						<div class="i_cm_box i_cm_box_top">
							<h6 class="i_cm_small_title"><span class="fsize16 c-333">搜索历史</span><a href="javascript:void(0)" onclick="cleakCookieKeyword()" class="fsize16 c-master fr mr10">清空</a></h6>
							<!-- 无数据 提示 开始 -->
							<div class="undata-wrap">
								<span class="undata-ico">&nbsp;</span>
								<p class="hLh30"><span class="fsize16 c-master">暂无数据~~~</span></p>
							</div>
							<!-- 无数据 提示 结束 -->
							<div>
								<ul class="seach_group_ul" style="display: none;">

								</ul>
							</div>
						</div>
						<%--<div class="i_cm_box">
							<h6 class="i_cm_small_title"><span class="fsize16 c-333">热门搜索</span></h6>
							<!-- 无数据 提示 开始 -->
							<div class="undata-wrap">
								<span class="undata-ico">&nbsp;</span>
								<p class="hLh30"><span class="fsize16 c-master">暂无数据~~~</span></p>
							</div>
							<!-- 无数据 提示 结束 -->
							<div>
								<ul class="seach_group_ul">
									<li>
										<div>
											<span class="g_nub_list vam">1</span>
											<font class="fsize16 c-999 vam">历史</font>
										</div>
									</li>
									<li>
										<div>
											<span class="g_nub_list vam">2</span>
											<font class="fsize16 c-999 vam">历史</font>
										</div>
									</li>
									<li>
										<div>
											<span class="g_nub_list vam">3</span>
											<font class="fsize16 c-999 vam">历史</font>
										</div>
									</li>
									<li>
										<div>
											<span class="g_nub_list vam">4</span>
											<font class="fsize16 c-999 vam">历史</font>
										</div>
									</li>
									<li>
										<div>
											<span class="g_nub_list vam">5</span>
											<font class="fsize16 c-999 vam">历史</font>
										</div>
									</li>
								</ul>
							</div>
						</div>--%>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript" src="/static/inxweb/h5search/js/zepto.min.js"></script>
<script src="/static/inxweb/h5search/js/scrollLoad.js" type="text/javascript"></script>
</body>
</html>