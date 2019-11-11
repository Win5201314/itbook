<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- /global header begin-->
<header id="header-2">
	<section class="hear-2-top">
		<div class="container">
			<h1 class="logo-2">
				<a class="headerlogo" href="${ctx }/" title="${websitemap.web.company}">
					<%--<img src="${staticServer}${websitemap.web.logo}" width="100%" alt="${websitemap.web.company}">--%>
					<img src="${ctx }/static/inxweb/img/logo.png" width="100%" alt="${websitemap.web.company}">
				</a>
				<a class="headerfanhui" style="display: none" href="javascript:history.go(-1)" title="${websitemap.web.company}">
					<img src="${ctx }/static/inxweb/img/mo-back.png">
				</a>
			</h1>
			<div class="head-3-right">
				<section class="search-2">
					<div class="s-2-infor">
						<form action="${ctx}/front/showcoulist" method="post" id="headerformSearch">
							<input type="text" value="" id="headerSearchName" name="queryCourse.courseName" placeholder="请输入要查找的内容">
							<a href="javascript:$('#headerformSearch').submit()" type="submit" title="搜索" class="seach-2-btn">&nbsp;</a>
						</form>
					</div>
				</section>
				<section class="h2-r-login">
					<ul class="clearfix h2-r-l-list">
						<li class="undis" id="no-login">
							<a href="javascript:lrFun()" title="登录">
								<em class="icon18 l2gio-icon mr5">&nbsp;</em>登录
							</a>
						</li>
						<li class="undis loginedLiShow">
							<a href="${ctx}/uc/index" title="" class="h2-name">
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
							<a href="${ctx }/front/helpCenter" title="帮助" class="vam">
								<em class="icon18 l2-help mr5">&nbsp;</em>帮助
							</a>
						</li>

						<li class="undis loginedLiShow">
							<a href="javascript:void(0)" title="退出" onclick="exit();" class="vam">
								退出
							</a>
						</li>
					</ul>
				</section>
			</div>
			<div class="clear"></div>
			<aside class="mw-nav-btn">
				<div class="mw-nav-icon"></div>
			</aside>
			<aside class="mw-nav-seach">
				<a href="${ctx}/h5/ajax/h5Search" id="headerH5SearchPage" class="h2-mw-seach-ico">&nbsp;</a>
			</aside>
		</div>
	</section>
	<section class="hear-2-bottom">
		<div class="container">
			<div class="h2-nav clearfix">
				<div>
					<aside class="fr">
					<span class="h2-tel-warp">
						<em class="h2-tel-ico">&nbsp;</em>
						<tt class="vam fsize16 c-fff f-fM ml5">${websitemap.web.phone}</tt>
					</span>
					</aside>
					<ul class="h2-n-list clearfix fl">
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
				<div class="clear"></div>
			</div>
		</div>
	</section>
</header>
<!-- /global header end-->

<script type="text/javascript" src="${ctx}/static/inxweb/front/index2.js"></script>
