<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- /global header begin-->
<header id="header-3">
	<section class="hear-3-top of">
		<div class="container">
			<div class="head-3-left">
				<tt class="f-fM fsize12 c-666 vam">欢迎来到</tt>
				<tt class="f-fM fsize12 c-master vam">${websitemap.web.company}!</tt>
			</div>
			<div class="head-3-right fr">
				<section class="h3-r-login">
					<ul class="clearfix h3-r-l-list">
						<li class="undis" id="no-login">
							<a href="javascript:lrFun()" title="登录">
								<em class="icon18 l2gio-icon mr5">&nbsp;</em>登录
							</a>
						</li>

						<li class="undis loginedLiShow">
							<a href="${ctx}/uc/index" title="" class="h3-name">
								<img src="" width="30" height="30" class="vam picImg" alt="">
								<span class="vam disIb c-master" id="userName"></span>
							</a>
						</li>
						<li class="pr undis loginedLiShow" id="is-login-one">
							<a href="${ctx}/uc/letter" title="消息" id="headerMsgCountId">
								<em class="news-2-icon">&nbsp;</em>
							</a>
							<q class="red-point" style="display: none">&nbsp;</q>
						</li>
						<li>
							<a href="${ctx }/front/helpCenter" title="帮助" class="">
								<em class="icon18 l2-help mr5">&nbsp;</em>帮助
							</a>
						</li>

						<li class="undis loginedLiShow">
							<a href="javascript:void(0)" title="退出" onclick="exit();" class="">
								退出
							</a>
						</li>
					</ul>
				</section>
			</div>
			<div class="clear"></div>
		</div>
	</section>
	<section class="hear-3-bottom">
		<div class="container">
			<h1 class="logo-3">
				<a class="headerlogo" href="${ctx }/" title="${websitemap.web.company}">
					<%--<img src="${staticServer}${websitemap.web.logo}" width="100%" alt="${websitemap.web.company}">--%>
					<img src="${ctx }/static/inxweb/img/logo.png" width="100%" alt="${websitemap.web.company}">
				</a>
				<a class="headerfanhui" style="display: none" href="javascript:history.go(-1)" title="${websitemap.web.company}">
					<img src="${ctx }/static/inxweb/img/mo-back.png">
				</a>
			</h1>
			<div class="h3-nav clearfix">
				<div>
					<aside class="fr">
						<section class="search-3">
							<div class="s-3-infor">
								<form action="${ctx}/front/showcoulist" method="post" id="headerformSearch">
									<input type="text" value="" id="headerSearchName" name="queryCourse.courseName" placeholder="请输入要查找的内容">
									<a href="javascript:$('#headerformSearch').submit()" type="submit" title="搜索" class="seach-3-btn">
										<img src="${ctx}/static/inxweb/img/d3-shear-ico.png">
									</a>
								</form>
							</div>
						</section>
					</aside>
					<ul class="h3-n-list clearfix fl">
						<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
							<li><a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="n-item-menu">
					<section class="n-item-left">
						<div class="n-i-m-top">
							<a href="javascript:void(0)">
								<em class="icon20 n-i-kcall">&nbsp;</em>
								<tt class="vam f-fM">全部课程</tt>
							</a>
						</div>
						<div class="n-i-m-bottom">
							<c:forEach items="${headerSubjectList}" var="parentSubject" varStatus="index">
								<c:if test="${index.index<10}">
									<section class="i-j-item i-j-it-all" style="visibility: hidden">
										<div class="i-j-item-bx">
											<div class="i-j-it-txt">
												<span class="i-j-item-first-span fsize16 c-fff">${parentSubject.subjectName}</span>
												<c:forEach items="${parentSubject.subjectList}" var="sonSubject" varStatus="index">
													<a class="i-j-item-first-a fsize12 c-fff" href="${ctx}/front/showcoulist?queryCourse.subjectId=${sonSubject.subjectId}">${sonSubject.subjectName}</a>
												</c:forEach>
											</div>
										</div>
									</section>
								</c:if>
							</c:forEach>
							<section class="n-i-m-more i-j-it-all">
								<div class="i-j-item-bx">
									<div class="DT-arrow">
										<em>◆</em>
										<span>◆</span>
									</div>
									<a class="f-fM" href="${ctx}/front/showcoulist">更多课程</a>
								</div>
							</section>
						</div>
					</section>
					<section class="n-item-right n-item-right-op">
						<c:forEach items="${headerSubjectList}" var="parentSubject" varStatus="index">
							<c:if test="${index.index<10}">
								<div class="j-cateright-wrap">
									<div class="v-i-tab-a clearfix">
										<dl>
											<dt class="fl">
												<span class="c-master fsize16 f-fM">${parentSubject.subjectName}</span>
												<span class="f-fM fsize14 v-i-tab-line">|</span>
											</dt>
											<dd>
												<c:forEach items="${parentSubject.subjectList}" var="sonSubject" varStatus="index">
													<a href="${ctx}/front/showcoulist?queryCourse.subjectId=${sonSubject.subjectId}" class="c-666 fsize14 f-fM">${sonSubject.subjectName}
														<c:if test="${!index.last}"><tt class="f-fM fsize14 v-i-tab-line">/</tt></c:if>
													</a>
												</c:forEach>
											</dd>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</section>
				</div>
				<div class="d3-ba-r-login">
					<div class="d3-b-r-top">
						<div class="d3-b-r-t-in">
							<div class="tit clearfix">
								<span class="fl">
									<em class="icon20 d3-b-r-zx-ico">&nbsp;</em>
									<tt class="vam fsize18 f-fM c-fff">资讯</tt>
								</span>
								<span class="fr">
									<a href="/front/articlelist" title="更多" class="f-fA fsize12 c-fff">更多&gt;&gt;</a>
								</span>
								<div class="clear"></div>
							</div>
							<ul class="list mt5">
								<c:forEach items="${rightarticleList}" var="article" varStatus="index">
									<li>
										<a href="${ctx}/front/articleinfo/${article.articleId}" title="${article.title}" class="dis txtOf fsize14 c-fff f-fM">${article.title}</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="d3-b-r-bottom">
						<section class="d3-b-r-b-in">
							<div class="undis loginedLiShow">
								<img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="pic vam picImg">
								<span class="name fszie14 f-fM c-666 vam">欢迎来到因酷教育软件有限公司</span>
								<%--<span class="name fszie14 f-fM c-master vam">某某某，下午好</span>--%>
							</div>
							<div  class="tac undis d3-b-r-btn mt20 no-login">
								<a href="javascript:lrFun()" class="bg mr30 fsize14 f-fM" title="登录">登录</a>
								<a href="javascript:lrFun(2)" class="ku fszie14 f-fM" title="注册">注册</a>
							</div>
							<div  class="tac undis d3-b-r-btn mt20 loginedLiShow">
								<a href="javascript:exit()" class="ku fszie14 f-fM" title="退出">退出</a>
							</div>
						</section>
					</div>
				</div>
				<aside class="mw-nav-btn">
					<div class="mw-nav-icon"></div>
				</aside>
				<aside class="mw-nav-seach">
					<a href="${ctx}/h5/ajax/h5Search" id="headerH5SearchPage" class="h2-mw-seach-ico">&nbsp;</a>
				</aside>
				<div class="clear"></div>
			</div>
		</div>
	</section>
</header>
<!-- /global header end-->

