<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- /global header begin-->
<header id="header">
	<section class="container">
		<h1 id="logo">
			<a class="headerlogo"  href="${ctx }/" title="${websitemap.web.company}">
				<img src="${staticServer}${websitemap.web.logo}" width="100%" alt="${websitemap.web.company}">
			</a>
			<a class="headerfanhui" style="display: none" href="javascript:history.go(-1)" title="${websitemap.web.company}">
				<img src="${ctx }/static/inxweb/img/mo-back.png">
			</a>
		</h1>
		<div class="h-r-nsl">
			<ul class="nav">
				<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
					<li><a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
				</c:forEach>
			</ul>
			<!-- / nav -->
			<ul class="h-r-login clearfix">
				<li class="undis h-login-li" id="no-login">
					<a href="javascript:lrFun()" title="登录">
						<em class="icon18 login-icon">&nbsp;</em><span class="vam ml5">登录</span>
					</a>
				</li>
				<li class="tit-new-list undis h-login-li" id="is-login-one">
					<a href="${ctx}/uc/letter" title="消息" id="headerMsgCountId">
						<em class="news-icon">&nbsp;</em>
					</a>
					<q class="red-point" id="letterPoint" style="display: none">&nbsp;</q>
						<div class="tit-n-warp">
							<ol id="letter">
							
							</ol>
							<div class="new-tit-box tac mt10 mb20" style="display:none">
								<a href="${ctx}/uc/letter" title="查看更多" class="fsize16 c-master f-fM disIb">查看更多</a>
							</div>
						</div>
				</li>
				<li class="h-r-user undis h-login-li loginedLiShow">
					<a href="${ctx}/uc/index" title="">
						<img src="" width="30" height="30" class="vam picImg" alt="">
						<span class="vam disIb c-master" id="userName"></span>
					</a>
				</li>
				<li class="h-r-user undis h-login-li loginedLiShow">
					<a href="${ctx }/front/helpCenter" title="帮助" class="ml10 vam">
						<span class="vam disIb c-fff">帮助</span>
					</a>
				</li>
				<li class="h-r-user undis h-login-li loginedLiShow">
					<a href="javascript:void(0)" title="退出" onclick="exit();" class="ml10 vam">
						<span class="vam disIb c-fff">退出</span>
					</a>
				</li>
				<!-- /未登录显示第1 li；登录后显示第2，3 li -->
			</ul>
			<aside class="h-r-search">
				<div class="pr h-r-s-box-cx">
					<form action="${ctx}/front/showcoulist" method="post" id="headerformSearch">
						<label class="h-r-s-box"><input type="text" value="" id="headerSearchName" name="queryCourse.courseName" placeholder="搜索...">
						<button class="s-btn" type="submit"> <em class="icon18">&nbsp;</em> </button></label>
					</form>
				</div>
			</aside>
		</div>
		<aside class="mw-nav-btn">
			<div class="mw-nav-icon"></div>
		</aside>
		<div class="clear mobile-clear"></div>
	</section>
</header>
<!-- /global header end-->

