<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>我的优惠券</title>
<script type="text/javascript">
		$(function() {
		 	 yhqPos();//优惠券图片的垂直居中  
			
			
		});
		  function yhqPos(){
			$(".coupon-list li .u-coup-box").each(function(){
				var _this=$(this);
				var tHeight=_this.children(".coupon-left").height();
				var tWidth=_this.children(".coupon-left").width();
				_this.height(tHeight).width(tWidth);
			})
			
		}
		/*优惠券兑换*/
		function couponActive() {
			/*获取输入的优惠券编码*/
			var couponCode = $("#couponCode").val();
			$.ajax({
				url : baselocation+"/coupon/couponActive",
				type : 'post',
				data : {"couponCode":couponCode},
				dataType : 'json',
				success : function (result) {
					if (result.success==true){
						window.location.reload();
					}else {
						msgshow(result.message,"false","2000")
					}
				}
			})
		}
</script>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div class="">
					<section class="u-r-tit-all clearfix">
						<h2 class="unFw fl">
							<span class="f-fH current">我的优惠劵</span>
						</h2>
						<section class="u-o-right fr tar mr20 mt13">
							<a class="cou-kscp" href="javascript:void (0)" onclick="dialog('优惠券兑换','',8);" title="优惠券兑换">优惠券兑换</a>
						</section>
					</section>
					<section class="u-r-all-box">
						<c:if test="${empty couponList }">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em>
							<span class="c-666 fsize14 ml10 vam">很遗憾！你还没有优惠券哦</span>
						</section>
						<!-- /无数据提示 结束-->
						</c:if>
						<c:if test="${not empty couponList}">
						<div class="coupon-box-wrap">
							<div class="coupon-wrap-list of">
								<ul class="coupon-list clearfix">

										<%--<li>
											<div class="clearfix u-coup-box ">
												<div class=" coupon-left">
													<div class="cou-l-box">
														<div>
															<span class="cou-left-num">编码：201K61118XX160357X73</span>
														</div>
														<div class="c-mon-wrap tar">
															<span class="fsize30 f-fM c-fff vam">￥</span>
															<span class=" fsize46 f-fM c-fff vam">1000</span>
															<span class="fsize24 f-fM c-fff vam">优惠劵</span>
														</div>
														<div class="cou-l-box-bottom clearfix">
															<span class="c-666 f-fM dis fl">有效期： 2016-11-24 ~ 2016-11-30</span>
															<span class="c-666 f-fM dis fr fsize14">未使用</span>
														</div>
													</div>
												</div>
											</div>
										</li>

									<li>
										<div class="clearfix u-coup-box ">
											<div class=" coupon-left">
												<div class="cou-l-box">
													<div>
														<span class="cou-left-num">编码：201K61118XX160357X73</span>
													</div>
													<div class="c-mon-wrap tar">
														<span class="fsize30 f-fM c-fff vam">￥</span>
														<span class=" fsize46 f-fM c-fff vam">1000</span>
														<span class="fsize24 f-fM c-fff vam">优惠劵</span>
													</div>
													<div class="cou-l-box-bottom clearfix">
														<span class="c-666 f-fM dis fl">有效期： 2016-11-24 ~ 2016-11-30</span>
														<span class="c-666 f-fM dis fr fsize14">未使用</span>
													</div>
												</div>
											</div>
										</div>
									</li>
									<li>
										<div class="clearfix u-coup-box u-coup-box-jjgq">
											<div class=" coupon-left">
												<div class="cou-l-box">
													<div>
														<span class="cou-left-num">编码：201K61118XX160357X73</span>
													</div>
													<div class="c-mon-wrap tar">
														<span class="fsize30 f-fM c-fff vam">￥</span>
														<span class=" fsize46 f-fM c-fff vam">1000</span>
														<span class="fsize24 f-fM c-fff vam">优惠劵</span>
													</div>
													<div class="cou-l-box-bottom clearfix">
														<span class="c-666 f-fM dis fl">有效期： 2016-11-24 ~ 2016-11-30</span>
														<span class="c-666 f-fM dis fr fsize14">未使用</span>
													</div>
													<div class="u-coup-jjgq-ico">
														<img src="${ctx}/static/inxweb/img/yhj-jjgq.png" class="dis" width="100%" height="100%">
													</div>
												</div>
											</div>
										</div>
									</li>--%>
									<c:forEach items="${couponList}" var="coupon">
									<li>
										<div class="u-coup-box <c:if test="${coupon.status==3||coupon.status==4||coupon.status==6}">u-coup-box-ygq</c:if> ">
											<div class="coupon-left">
												<div class="cou-l-box">
													<div>
														<span class="cou-left-num">编码：${coupon.couponCode}</span>
													</div>
													<div class="c-mon-wrap tar">
														<span class="fsize30 f-fM c-fff vam">￥</span>
														<span class=" fsize46 f-fM c-fff vam"><fmt:formatNumber value="${coupon.amount}" pattern="0.0"/></span>
														<span class="fsize24 f-fM c-fff vam">优惠劵</span>
													</div>
													<div class="cou-l-box-bottom clearfix">
														<span class="c-999 f-fM dis fl fsize14">
															有效期：
															<fmt:formatDate value="${coupon.startTime}" type="both" pattern="yyyy-MM-dd"/> ~
															<fmt:formatDate value="${coupon.endTime}" type="both" pattern="yyyy-MM-dd"/>
														</span>
														<c:if test="${coupon.status!=5}">
														<span class="dis fr">
															<c:if test="${coupon.status==1}"><tt class="fsize14 f-fM c-master">未使用</tt></c:if>
															<c:if test="${coupon.status==2}"><tt class="fsize14 f-fM c-999">已使用</tt></c:if>
															<c:if test="${coupon.status==3}"><tt class="fsize14 f-fM c-999">已过期</tt></c:if>
															<c:if test="${coupon.status==4}"><tt class="fsize14 f-fM c-999">已作废</tt></c:if>
															<c:if test="${coupon.status==6}"><tt class="fsize14 f-fM c-999">已冻结</tt></c:if>
															</span>
														</c:if>
														<c:if test="${coupon.status==5}"><span class="c-red f-fM dis fr fsize14">即将过期</span></c:if>
													</div>
													<c:if test="${coupon.status==2}">
														<div class="u-coup-ygq-ico">
															<img src="${ctx}/static/inxweb/img/coupon-ysy.png" width="100%" height="100%" class="dis">
														</div>
													</c:if>
													<c:if test="${coupon.status==3}">
														<div class="u-coup-ygq-ico">
															<img src="${ctx}/static/inxweb/img/coupon-expired.png" width="100%" height="100%" class="dis">
														</div>
													</c:if>
													<c:if test="${coupon.status==4}">
														<div class="u-coup-ygq-ico">
															<img src="${ctx}/static/inxweb/img/coupon-yzf.png" width="100%" height="100%" class="dis">
														</div>
													</c:if>
													<c:if test="${coupon.status==5}">
														<div class="u-coup-jjgq-ico">
															<img src="${ctx}/static/inxweb/img/yhj-jjgq.png" class="dis" width="100%" height="100%">
														</div>
													</c:if>
													<c:if test="${coupon.status==6}">
														<div class="u-coup-ygq-ico">
															<img src="${ctx}/static/inxweb/img/coupon-djz.png" width="100%" height="100%" class="dis">
														</div>
													</c:if>
												</div>
											</div>
											<div class="u-conbg-box">
												<c:if test="${coupon.status==3||coupon.status==4||coupon.status==6}">
													<img src="${ctx}/static/inxweb/img/new-yhj-bg-gq.png">
												</c:if>
												<c:if test="${coupon.status!=3&&coupon.status!=4&&coupon.status!=6}">
													<img src="${ctx}/static/inxweb/img/new-yhj-bg.png">
												</c:if>
											</div>
										</div>
									</li>
									</c:forEach>
									<%--<c:forEach items="${couponList }" var="coupon">
										<li <c:if test="${coupon.status==2 or coupon.status==3 or coupon.status==4 or coupon.status==6}">class="expired-state"</c:if> >
											<div class="clearfix u-coup-box <c:if test='${coupon.type==1}'>zk-coupon</c:if> ">
												<div class="fl coupon-left">
													<div class="cou-l-box">
														<div><span class="cou-left-num">编码：${coupon.couponCode}</span></div>
														<div class="c-mon-wrap" >
															<span class="cou-money"><font>
																<c:if test="${coupon.type==1}">折</c:if><c:if test="${coupon.type==2}">￥</c:if>
															</font>
																<tt class="f-fG"><fmt:formatNumber value="${coupon.amount}" pattern="0.0"/></tt>
															</span>
														</div>
														<p class="c-666 f-fM mt10">有效期：
															<fmt:formatDate value="${coupon.startTime}" type="both" pattern="yyyy-MM-dd"/> ~
															<fmt:formatDate value="${coupon.endTime}" type="both" pattern="yyyy-MM-dd"/>
														</p>
													</div>
												</div>
												<div class="fl coupon-right">
													<c:if test="${coupon.type==1}"><img src="/static/inxweb/img/coupon-zk.png"></c:if><c:if test="${coupon.type==2}"><img src="/static/inxweb/img/coupon.png"></c:if>
												</div>
											</div>
											<c:if test="${coupon.status==3}">
												<div class="exp-chapter"><img src="/static/inxweb/img/coupon-expired.png"></div>
											</c:if>
											<c:if test="${coupon.status==2}">
												<div class="exp-chapter"><img src="/static/inxweb/img/cou-used.png"></div>
											</c:if>
											<c:if test="${coupon.status==4}">
												<div class="exp-chapter"><img src="/static/inxweb/img/coupon-zf.png"></div>
											</c:if>
										</li>
									</c:forEach>--%>
								</ul>
							</div>
							<!-- 公共分页 开始 -->
							<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
							<!-- 公共分页 结束 -->
							<form action="${ctx}/uc/myCouPon?queryCouponCode.status=${status}" method="post" id="searchForm">
								<input type="hidden" name="page.currentPage" value="1" id="pageCurrentPage" />
							</form>
						</div>
						</c:if>
					</section>
				</div>
			</section>
		</div>
	</article>
	<%--<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div>
					<section class="c-infor-tabTitle c-tab-title">
						<a href="javascript: void(0)" title="Wo的优惠券" class="c-tab-title">Wo的优惠券</a>
						<a href="${ctx}/uc/myCouPon" title="全部" <c:if test="${status=='0'}">class="current"</c:if> >全部</a>
						<a href="${ctx}/uc/myCouPon?queryCouponCode.status=1" title="未使用" <c:if test="${status=='1'}">class="current"</c:if> >未使用</a>
						<a href="${ctx}/uc/myCouPon?queryCouponCode.status=2" title="已使用" <c:if test="${status=='2'}">class="current"</c:if> >已使用</a>
						<a href="${ctx}/uc/myCouPon?queryCouponCode.status=3" title="已过期" <c:if test="${status=='3'}">class="current"</c:if> >已过期</a>
						<a href="${ctx}/uc/myCouPon?queryCouponCode.status=4" title="已作废" <c:if test="${status=='4'}">class="current"</c:if> >已作废</a>
					</section>
				</div>
				<div>
					<c:if test="${empty couponList }">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em>
							<span class="c-666 fsize14 ml10 vam">很遗憾！你还没有优惠券哦</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty couponList }">
						<div class="coupon-box-wrap">
							<div class="mt20 of ml30">
								<ul class="coupon-list">
									<c:forEach items="${couponList }" var="coupon">
									   <li <c:if test="${coupon.status==2 or coupon.status==3 or coupon.status==4 or coupon.status==6}">class="expired-state"</c:if> >
											<div class="clearfix u-coup-box <c:if test='${coupon.type==1}'>zk-coupon</c:if> ">
												<div class="fl coupon-left">
													<div class="cou-l-box">
														<div><span class="cou-left-num">编码：${coupon.couponCode}</span></div>
														<div class="c-mon-wrap" >
															<span class="cou-money"><font>
																<c:if test="${coupon.type==1}">折</c:if><c:if test="${coupon.type==2}">￥</c:if>
															</font>
																<tt class="f-fG"><fmt:formatNumber value="${coupon.amount}" pattern="0.0"/></tt>
															</span>
														</div>
														<p class="c-666 f-fM mt10">有效期：
															<fmt:formatDate value="${coupon.startTime}" type="both" pattern="yyyy-MM-dd"/> ~
																<fmt:formatDate value="${coupon.endTime}" type="both" pattern="yyyy-MM-dd"/>
														</p>
													</div>
												</div>
												<div class="fl coupon-right">
													<c:if test="${coupon.type==1}"><img src="/static/inxweb/img/coupon-zk.png"></c:if><c:if test="${coupon.type==2}"><img src="/static/inxweb/img/coupon.png"></c:if>
												</div>
											</div>
										   <c:if test="${coupon.status==3}">
											   <div class="exp-chapter"><img src="/static/inxweb/img/coupon-expired.png"></div>
										   </c:if>
										   <c:if test="${coupon.status==2}">
											   <div class="exp-chapter"><img src="/static/inxweb/img/cou-used.png"></div>
										   </c:if>
										   <c:if test="${coupon.status==4}">
											   <div class="exp-chapter"><img src="/static/inxweb/img/coupon-zf.png"></div>
										   </c:if>
										</li>
									</c:forEach>
								</ul>
							</div>
							<!-- 公共分页 开始 -->
							<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
							<!-- 公共分页 结束 -->
							<form action="${ctx}/uc/myCouPon?queryCouponCode.status=${status}" method="post" id="searchForm">
								<input type="hidden" name="page.currentPage" value="1" id="pageCurrentPage" />
							</form>
						</div>
					</c:if>
				</div>
			</section>
			<!-- /我的课程 -->
		</div>
	</article>--%>
	<!-- /右侧内容区 结束 -->
</body>

</html>