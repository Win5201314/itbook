<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<script type="text/javascript" src="${ctx}/static/inxweb/front/footer.js"></script>
<footer id="footer">
	<section class="container">
		<div class="b-foot">
			<section class="fl col-80">
				<section class="mr20">
					<div class="find-box">
						<h4 class="hLh30 txtOf">
							<span class="fsize18 f-fM c-fff">友情链接</span>
						</h4>
						<ul class="flink-list clearfix">
							<c:forEach items="${navigatemap.FRIENDLINK}" var="friendLinkNavigate">
								<li><a href="<%--${friendLinkNavigate.url}--%>${ctx}" title="${friendLinkNavigate.name}" <c:if test="${friendLinkNavigate.newPage==0}">target="_blank"</c:if>>${friendLinkNavigate.name}</a></li>
							</c:forEach>
						</ul>
						<div class="clear"></div>
					</div>
					<section class="b-f-link mt20">
						<c:forEach items="${navigatemap.TAB}" var="indexNavigate" varStatus="index">
							<a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a>|
						</c:forEach>
						<span>服务热线：${websitemap.web.phone}</span>
						<span>Email：${websitemap.web.email}</span>
						<span class="ml30">
							${websitemap.web.remarks}
						</span>
					</section>
					<section class="b-f-link mt10">
						<span>${websitemap.web.copyright}</span>
						<span class="c-666">Powered by<a title="${websitemap.web.company}" target="_blank" href="${ctx}" class="c-666">${websitemap.web.company}</a></span>
						<!-- 统计代码 -->
						${websitemap.web.censusCodeString}
					</section>
				</section>
			</section>
			<aside class="fl col-20 tar ewm-box">
				<span class="disIb tac">
					<img src="${staticServer}${websiteImages.type_11[0].imagesUrl}" alt="" class="dis" width="140" height="140">
					<p class="hLh30 fsize14 c-999 f-fM mt10 tac">关注微信</p>
				</span>
				<%--<span class="disIb ml30">
					<img src="${ctx}/static/inxweb/img/wbrew.png" alt="" class="dis" width="140" height="140">
					<p class="hLh30 fsize14 c-999 f-fM mt10">关注微博</p>
				</span>--%>
			</aside>
			<div class="clear"></div>

		</div>
	</section>
</footer>
<div class="r-fixed-wrap">
	<div class="pr r-fix-box">
		<ul class="r-fixed-ul">
			<li id="goTopBtn" class="undis">
				<a href="javascript: void(0)" title="返回顶部" class="bg-orange">
					<em class="r-f-icon-3">&nbsp;</em><span>返回顶部</span>
				</a>
			</li>
			<li class="foot-zixun" id="shopCart">
				<a href="javascript:shopCartHtml()" title="查看详情" class="bg-orange pr" id="v-nav-first">
					<em class="r-f-icon-4">&nbsp;</em><!-- <span>查看详情</span> -->
					<tt class="shop-car-num" id="shopCartNum">0</tt>
				</a>
			</li>
			<li class="foot-zixun">
				<a href="${websitemap.web.onlineUrl }" target="_blank" title="在线咨询" class="bg-orange">
					<em class="r-f-icon-1">&nbsp;</em><span>在线咨询</span>
				</a>
			</li>
			<li class="foot-zixun">
				<a href="JavaScript:void(0)" title="扫描关注" class="bg-orange">
					<em class="r-f-icon-2">&nbsp;</em><span>扫描关注</span>
					<div class="smgz-pic">
						<img src="${staticServer}${websitemap.web.onlineImageUrl }" width="150" height="150" class="dis"/>
						<p class=" fsize14 f-fM c-666 tac">扫一扫，关注我们</p>
					</div>
				</a>
			</li>
		</ul>
		<div class="shopcar-box fr" id="shopcarthtml">

		</div>
	</div>
</div>
<!--手机端回到顶部 开始-->
<div class="m-go-top-box">
	<ul class="r-fixed-ul">
		<li id="goTopBtn2" class="">
			<a href="javascript: void(0)" title="返回顶部" class="bg-orange">
				<em class="r-f-icon-3">&nbsp;</em><span>返回顶部</span>
			</a>
		</li>
	</ul>
</div>
<!--手机端回到顶部 开始-->
<section class="mo-foot">
	<article class="mo-f-warp">
		<ul class="clearfix mo-f-w-list" id="gn-ul">
			<c:forEach items="${navigatemap.FOOTER}" var="indexNavigate">
				<li style="width: ${100/(fn:length(navigatemap.FOOTER))}%">
					<a href="${indexNavigate.url}">
							${indexNavigate.image}
						<p>${indexNavigate.name}</p>
					</a>
				</li>
			</c:forEach>
		</ul>
	</article>
</section>
<script>
	setTimeout(function () {
		try {
			headerIndexSubject();//第二三版首页 专业 显示 处理
		}catch (err){}

	},100)
</script>
