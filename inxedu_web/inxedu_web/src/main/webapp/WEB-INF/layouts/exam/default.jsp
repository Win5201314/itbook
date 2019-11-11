<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
        try    http://www.inxedu.com
-->
<!DOCTYPE HTML>
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<%@ include file="/base_exam.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><sitemesh:title />-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}"/> 
	<meta name="keywords" content="${websitemap.web.keywords}"/>
	<meta name="description" content="${websitemap.web.description}"/>
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js?v=${v}"></script><![endif]-->
	<!--[if lt IE 7]>
	<script src="${ctximg}/static/common/ie_png.js?v=${v}"></script>
	<script type="text/javascript">try {ie_png.fix('.png');}catch(e){}</script>
	<![endif]-->

	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="/static/inxweb/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/inxweb/css/theme.css">
	<link rel="stylesheet" type="text/css" href="/static/exam/css/examtheme.css">
	<link rel="stylesheet" type="text/css" href="/static/inxweb/css/global.css">
	<link rel="stylesheet" type="text/css" href="/static/inxweb/css/web.css">
	<link rel="stylesheet" type="text/css" href="/static/inxweb/css/mw_320_768.css" media="screen and (min-width: 320px) and (max-width: 768px)">
	<link rel="stylesheet" href="/static/exam/css/exam-style.css?v=${v}" />
	<link href="/static/exam/css/mw_320_768.css" rel="stylesheet" type="text/css" media="screen and (min-width: 320px) and (max-width: 768px)">
	<!--[if lt IE 9]><script src="${ctx}/static/common/html5.js"></script><![endif]-->
	<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/header/header.js"></script>
	<script type="text/javascript" src="${ctx }/static/inxweb/js/common.js"></script>
	<script src="${ctximg}/static/common/webutils.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/exam/js/examJs.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/exam/js/header.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<%--<script src="${ctximg}/static/exam/js/footer.js?v=${v}" type="text/javascript" charset="utf-8"></script>--%>
	<script type="text/javascript">
		var baselocation = "${ctx}";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=CommonConstants.contextPath%>";//单独的上传按钮使用的路径
		var uploadServerUrl="<%=uploadServerUrl%>";
		var uid="${uid}";
	</script>
	<sitemesh:head />
</head>
<body >
	<%--变量--%>
	<%--是否购买QQ第三方登录服务--%>
	<input type="hidden" id="qqLoginIsOpen" value="<%=CacheConstans.QQ_LOGIN_IS_OPEN%>"/>
		<%--是否购买SINA第三方登录服务--%>
	<input type="hidden" id="sinaLoginIsOpen" value="<%=CacheConstans.SINA_LOGIN_IS_OPEN%>"/>
		<%--是否购买WEIXIN第三方登录服务--%>
	<input type="hidden" id="weixinLoginIsOpen" value="<%=CacheConstans.WEIXIN_LOGIN_IS_OPEN%>"/>
		<%--用户 cookie key--%>
	<input type="hidden" id="cookieKey" value="<%=CacheConstans.WEB_USER_LOGIN_PREFIX%>"/>
		<%--Cookie域--%>
	<input type="hidden" id="mydomain" value="<%=CommonConstants.MYDOMAIN%>"/>
		<%--变量--%>
	<jsp:include page="/WEB-INF/layouts/limit_login/limit_login.jsp" />

	<div class="e-wrap in-wrap">
		<div class="h-mobile-mask"></div>
		<div class="head-mobile">
			<div class="head-mobile-box">
				<section class="clearfix">
					<div class="u-face-pic">
						<img src="${ctx }/static/inxweb/img/avatar-boy.gif" alt="" class="userImgPhoto">
					</div>
					<h4 class="hLh30 txtOf">
				<span class="fsize16 c-ccc userNameClass">
					<span class="vam ml5" style="cursor:pointer;" onclick="lrFun()">登录</span>
				</span>
					</h4>
					<div class="hLh30">
						<a href="${ctx }/shopcart?command=queryShopCart" title="购物车" class="c-999 u-c-shopcar">购物车</a>
					</div>
				</section>
				<nav class="mw-nav">
					<ul class="clearfix">
						<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
							<li><a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
						</c:forEach>
					</ul>
				</nav>
				<section class="u-m-dd">
					<ul class="">
						<li>
							<a href="${ctx}/uc/index" title="我的课程">
								我的课程
							</a>
						</li>
						<li>
							<a href="${ctx}/uc/courseNote" title="我的笔记">
								我的笔记
							</a>
						</li>
						<li>
							<a href="${ctx}/uc/myFavorites" title="课程收藏">
								课程收藏
							</a>
						</li>
						<c:if test="${serviceSwitch.coupon=='ON'}">
							<li>
								<a href="${ctx}/uc/myCouPon" title="优惠劵">
									优惠劵
								</a>
							</li>
						</c:if>
						<c:if test="${serviceSwitch.member=='ON'}">
							<li>
								<a href="${ctx}/uc/associator" title="我的会员">
									<tt class="vam">我的会员</tt>
								</a>
							</li>
						</c:if>
						<li>
							<a href="${ctx}/uc/order/myOrderList/ALL" title="订单中心">
								订单中心
							</a>
						</li>
						<c:if test="${serviceSwitch.account=='ON'}">
							<li>
								<a href="${ctx}/uc/myAccount" title="我的账户">
									我的账户
								</a>
							</li>
						</c:if>
						<c:if test="${serviceSwitch.invite=='ON'}">
							<li>
								<a href="${ctx}/uc/myInvite" title="我的邀请">
									邀请好友
								</a>
							</li>
						</c:if>
						<li>
							<a href="${ctx}/uc/initUpdateUser/0" title="个人资料">
								个人资料
							</a>
						</li>
						<li>
							<a href="${ctx}/uc/initUpdateUser/1" title="密码设置">
								密码设置
							</a>
						</li>
						<li>
							<a href="/uc/letter" title="消息中心">
								消息中心
							</a>
						</li>
					</ul>
					<div  id="mobileExitDiv" class="mt15 undis tac">
						<a href="javascript:void(0)" title="退出" onclick="exit();" class="u-loginout">退出登录</a>
					</div>
				</section>
			</div>
		</div>
		<div class="main-warp">
			<div class="main-infor">
				<!-- e-header -->
				<jsp:include page="/WEB-INF/layouts/web/header.jsp" />
				<!-- /e-header -->
				<c:if test="${keywordmap.keyword.verifyExam=='ON' }">

				</c:if>
				<sitemesh:body />
			</div>
		</div>
	</div>
	<div class="mo-footer">
		<!-- e-footer begin -->
		<jsp:include page="/WEB-INF/layouts/web/footer.jsp" />
		<!-- e-footer end -->
	</div>
	<!-- 统计代码 -->
	${websitemap.web.censusCodeString}

	<script src="/static/exam/js/iscroll.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$(function(){
			footFunc();
			loadsubject();//加载考试专业
		});
		function footFunc(){
			var myScroll;
			var wH = $(window).height();
			function loaded() {
				myScroll = new iScroll('wrapper');
			}
			var _tBtn=$("#gn-ul"),
					_tBox=$(".e-nav-bx-warp"),
					_closeBtn=$(".e-nav-wrap").find(".dtClose1");
			$(".e-nav-box").css("height",wH-51+"px");
			_tBtn.on("click","li",function() {
				$("html,body").animate({"scrollTop" : 0}, 100);
				var _this = $(this);
				_index = _this.index();
				_tBox.find(".scroller").children("div").eq(_index).show().siblings().hide();
				if(!_tBox.hasClass("hauto")) {
					_tBox.show().addClass("hauto");
					$("html,body").css("overflow-y","hidden");
					if($(".e-nav-wrap .scroller").height()<$(".e-nav-box").height()){
						$(".e-nav-wrap .scroller").css("height",$(".e-nav-box").height()+1+"px");
					}
					loaded();
					myScroll.scrollTo(0, 0);
				} else {
					_tBox.removeClass("hauto").hide();
					$("html,body").css("overflow-y","visible");
					$(".e-nav-wrap .scroller").css("height","auto");
					myScroll.destroy();
				}
			});
			_closeBtn.click(function(){
				_tBox.removeClass("hauto").hide();
				$("html,body").css("overflow-y","visible");
				$(".e-nav-wrap .scroller").css("height","auto");
				myScroll.destroy();
			});
			/* 		_tBtn.each(function(){
			 var _this=$(this).children("a");
			 _this.click(function(){
			 var _index=_this.parent().index();
			 _tBox.children().children("section").eq(_index).show().siblings().hide();
			 if(!_tBox.hasClass("hauto")){
			 _tBox.addClass("hauto animated0d8s bounceInUp");
			 _tBox.css("height",_tHeight);
			 $("html,body").css("overflow-y","hidden");
			 }else{
			 _tBox.removeClass("hauto animated0d8s bounceInUp");
			 _tBox.css("height","0");
			 $("html,body").css("overflow-y","visible");
			 }
			 });
			 _closeBtn.click(function(){
			 _tBox.removeClass("hauto animated0d8s bounceInUp");
			 _tBox.css("height","0");
			 $("html,body").css("overflow-y","visible");
			 })
			 }) */

		}

		/**
		 * 加载专业
		 * @param change
		 */
		function loadsubject(){
			$.ajax({
				type:"post",
				dataType:"text",
				url:baselocation+"/subj/ajax/getSubject",
				data:{},
				async:false,
				success:function(result){
					$(".examAllSubjectAppendHtml").append(result);
				},error:function(error){
				}
			});
		}
	</script>
</body>
</html>
