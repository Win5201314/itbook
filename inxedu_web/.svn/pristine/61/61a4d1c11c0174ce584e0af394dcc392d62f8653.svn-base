<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>我的订单</title>
	<script>
		$(function () {
            $("#a-${queryOrder.states}").addClass("current");
            $("#mobileSelect${queryOrder.states}").addClass("m-current");
        });

		/**
		 * 重新支付
		 * @param id
		 */
		function gotoRePay(id){
			$.ajax({
				url:"${ctx}/uc/order/front/repaycheck/"+id,
				type:"post",
				dataType:"json",
				async : false,
				success:function(result){
					if(result.message=='true'){
						//window.location.href='${ctx}/front/repay/'+id;
						window.open('${ctx}/front/repay/'+id);
					}else{
						dialog('错误提示',result.message,17,'javascript:window.location.reload()');
					}
				}
			})
		}

		//取消课程
		function cancleCourse(orderId){
			window.location.href=baselocation + "/uc/order/cancleoder/"+orderId;
		}
		function delTrxorderDetailDTO(detailId) {
			$.ajax({
				url:"${ctx}/front/trxorderDetail/delTrxorder",
				data:{"detailId":detailId},
				type:"post",
				dataType:"json",
				success:function(result){
					window.location.reload()
				}
			})
		}
	</script>
</head>
<body>
<article class="col-80 fl">
	<div class="u-r-cont">
		<section>
			<form action="${ctx}/uc/order/myOrderList/${state}" method="post" id="searchForm">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" style="width: 60px"/>
			</form>
			<div class="">
				<section class="u-r-tit-all clearfix">
					<h2 class="unFw m-xl-ashow">
						<a href="${ctx}/uc/order/myOrderList/ALL" id="a-" class="f-fH">全部订单</a>
						<a href="${ctx}/uc/order/myOrderList/SUCCESS"  id="a-SUCCESS" class="f-fH">已完成</a>
						<a href="${ctx}/uc/order/myOrderList/INIT"  id="a-INIT" class="f-fH">未完成</a>
						<a href="${ctx}/uc/order/myOrderList/CANCEL"  id="a-CANCEL" class="f-fH">已取消</a>
						<a href="${ctx}/uc/order/myOrderList/REFUND"  id="a-REFUND" class="f-fH">已退款</a>
					</h2>
					<section class="u-m-tit-abox fl">
						<h2 class="unFw u-m-n-box">
							<a class="f-fH mTitle current more" href="javascript:void(0)" title="">全部订单</a>
							<em class="icon16 u-i-tbsj-ico"> </em>
						</h2>
						<div class="u-m-tit-a-warp">
							<div class="u-m-t-ab-in">
								<ul>
									<li  id="mobileSelect">
										<a href="${ctx}/uc/order/myOrderList/ALL" class="">全部订单</a>
										<em class="u-i-m-ab-xz">&nbsp;</em>
									</li>
									<li  id="mobileSelectSUCCESS">
										<a href="${ctx}/uc/order/myOrderList/SUCCESS" class="">已完成</a>
										<em class="u-i-m-ab-xz">&nbsp;</em>
									</li>
									<li id="mobileSelectINIT">
										<a href="${ctx}/uc/order/myOrderList/INIT" class="">未完成</a>
										<em class="u-i-m-ab-xz">&nbsp;</em>
									</li>
									<li id="mobileSelectCANCEL">
										<a href="${ctx}/uc/order/myOrderList/CANCEL" class="">已取消</a>
										<em class="u-i-m-ab-xz">&nbsp;</em>
									</li>
									<li id="mobileSelectREFUND">
										<a href="${ctx}/uc/order/myOrderList/REFUND" class="">已退款</a>
										<em class="u-i-m-ab-xz">&nbsp;</em>
									</li>
								</ul>
							</div>
						</div>
					</section>
				</section>
				<section class="orde-tab-new u-r-all-box">
					<div class="o-tab-n-body">
						<c:if test="${empty orderList }">
							<!-- /无数据提示 开始-->
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em>
								<span class="c-666 fsize14 ml10 vam">您还没有购买过课程！</span>
							</section>
							<!-- /无数据提示 结束-->
						</c:if>
						<div>
							<div class="u-order-wrap">
								<c:forEach items="${orderList}" var="order" varStatus="index">
									<dl>
										<dt class="clearfix">
											<span class="c-999 fsize14 fl"><tt class="f-fM">订单号：</tt><tt class="f-fM c-666">${order.orderNo}</tt></span>
												<%--<span class="c-999 ml20"><tt class="f-fM">优惠：</tt><tt class="f-fM c-666">￥${order.couponAmount}</tt></span>--%>
											<span class="fr u-o-dt-time">
												<tt class="f-fM"><fmt:formatDate type="both" value="${order.createTime }" pattern="yyyy-MM-dd HH:mm"/></tt>
											</span>
										</dt>
										<%--未支付订单样式--%>
										<c:if test="${order.states=='INIT'}">
										<dd class="u-order-list">
											<section class="w50pre disIb vam u-o-left">
												<div class="mr30 u-o-l-infor">
													<c:forEach items="${order.trxorderDetailList}" var="detail" varStatus="index">
														<div class="c-cou-item">
															<div class="">
																<div>
																	<a <c:if test="${order.orderType=='COURSE'}">href="${ctx }/front/couinfo/${detail.courseId }"</c:if> title="" class="u-ol-pic">
																		<c:choose>
																			<c:when test="${order.orderType=='ACCOUNT' }">
																				<img src="${ctx }/static/inxweb/img/account-order.jpg" alt="">
																			</c:when>
																			<c:when test="${not empty detail.courseImgUrl }">
																				<img src="${staticServer}${detail.courseImgUrl}" alt="">
																			</c:when>
																			<c:otherwise>
																				<img src="${ctx }/static/inxweb/img/coures.gif" alt="">
																			</c:otherwise>
																		</c:choose>
																	</a>
																	<h6 class="hLh30 txtOf name">
																		<a <c:if test="${order.orderType=='COURSE'}">href="${ctx }/front/couinfo/${detail.courseId }"</c:if> title="" class="fsize14 c-666">
																			<c:if test="${detail.sellType=='COURSE'}">[课程订单]&nbsp;</c:if>
																			<c:if test="${detail.sellType=='LIVE'}">[直播订单]&nbsp;</c:if>
																			<c:if test="${detail.sellType=='PACKAGE'}">[套餐订单]&nbsp;</c:if>
																			<c:if test="${order.orderType=='MEMBER'}">[会员订单]&nbsp;</c:if>
																			<c:if test="${order.orderType=='ACCOUNT'}">[充值订单]&nbsp;</c:if>
																				${detail.courseName }
																		</a>
																	</h6>
																	<section class="hLh30 txtOf mt10 u-o-l-jg">
																		<span class="c-master fsize16">￥<font>${detail.currentPirce }</font></span>
																	</section>
																</div>
															</div>
														</div>
													</c:forEach>
												</div>
											</section>
											<section class="disIb vam u-o-maddile col-2 tac mb20">
												<div class="disIb vam tac u-o-price">
													<span class="c-999 f-fM fsize14"><fmt:formatDate type="both" value="${order.createTime }" pattern="yyyy-MM-dd HH:mm"/></span>
												</div>
											</section>
											<section class="disIb vam col-2 tac mb20 col-2-new">
												<div class="disIb vam tac u-o-m-zt">

														<c:if test="${order.states=='SUCCESS'}">

															<p class="hLh20"><span class="c-green fsize14">购买成功</span></p>

														</c:if>
												<c:if test="${order.states=='INIT'}">
													<span class="c-red fsize14">
													未支付
														</span>
												</c:if>
												<c:if test="${order.states=='CANCEL'}">
													<span class="c-999 fsize14">
													已取消订单
														</span>
												</c:if>
												<c:if test="${order.states=='REFUND'}">
														<span class="c-blue fsize14">
													已退款
														</span>
												</c:if>


												</div>
											</section>
											<section class="col-2 disIb vam u-o-right">
												<c:if test="${order.states=='INIT'}">
													<div class="tac u-o-r-btn-infor">
													<p class="c-999 f-fA mb10"><a href="javascript:gotoRePay(${order.orderId})" class="cou-kscp">继续支付</a></p>
													<p class="mb20"><a href="javascript:cancleCourse(${order.orderId})" class="cou-kscp gmkc">取消订单</a></p>
													</div>
												</c:if>
												<c:if test="${order.states=='SUCCESS'}">
												<c:forEach items="${order.trxorderDetailList}" var="detail" varStatus="index">
													<div class="tac u-o-r-btn-infor">
														<%--<p class="c-999 f-fA mb10"><a href="javascript:void(0)" onclick="delTrxorderDetailDTO('${detail.trxorderDetailId}')" class="cou-kscp">删除课程</a></p>--%>
														<p class="mb20"><a href="${ctx}/uc/courseCommen/${detail.courseId}" class="cou-kscp">评价课程</a></p>
													</div>
												</c:forEach>
												</c:if>
											</section>
											<div class="clear"></div>
										</dd>
										</c:if>
											<%--不是未支付订单样式--%>
										<c:if test="${order.states!='INIT'}">
											<c:forEach items="${order.trxorderDetailList}" var="detail" varStatus="index">

											<dd class="u-order-list">
												<section class="w50pre disIb vam u-o-left">
													<div class="mr30 u-o-l-infor">
															<div class="c-cou-item">
																<div class="">
																	<div>
																		<a <c:if test="${order.orderType=='COURSE'}">href="${ctx }/front/couinfo/${detail.courseId }"</c:if> title="" class="u-ol-pic">
																			<c:choose>
																				<c:when test="${order.orderType=='ACCOUNT' }">
																					<img src="${ctx }/static/inxweb/img/account-order.jpg" alt="">
																				</c:when>
																				<c:when test="${not empty detail.courseImgUrl }">
																					<img src="${staticServer}${detail.courseImgUrl}" alt="">
																				</c:when>
																				<c:otherwise>
																					<img src="${ctx }/static/inxweb/img/coures.gif" alt="">
																				</c:otherwise>
																			</c:choose>
																		</a>
																		<h6 class="hLh30 txtOf name">
																			<a <c:if test="${order.orderType=='COURSE'}">href="${ctx }/front/couinfo/${detail.courseId }"</c:if> title="" class="fsize14 c-666">
																				<c:if test="${detail.sellType=='COURSE'}">[课程订单]&nbsp;</c:if>
																				<c:if test="${detail.sellType=='LIVE'}">[直播订单]&nbsp;</c:if>
																				<c:if test="${detail.sellType=='PACKAGE'}">[套餐订单]&nbsp;</c:if>
																				<c:if test="${order.orderType=='MEMBER'}">[会员订单]&nbsp;</c:if>
																				<c:if test="${order.orderType=='ACCOUNT'}">[充值订单]&nbsp;</c:if>
																					${detail.courseName }
																			</a>
																		</h6>
																		<section class="hLh30 txtOf mt10 u-o-l-jg">
																			<span class="c-master fsize16">￥<font>${detail.currentPirce }</font></span>
																		</section>
																	</div>
																</div>
															</div>

													</div>
												</section>
												<section class="disIb vam u-o-maddile col-2 tac mb20">
													<div class="disIb vam tac u-o-price">
														<span class="c-999 f-fM fsize14"><fmt:formatDate type="both" value="${order.createTime }" pattern="yyyy-MM-dd HH:mm"/></span>
													</div>
												</section>
												<section class="disIb vam col-2 tac mb20 col-2-new">
													<div class="disIb vam tac u-o-m-zt">

														<c:if test="${order.states=='SUCCESS'}">

															<p class="hLh20"><span class="c-green fsize14">购买成功</span></p>

														</c:if>
														<c:if test="${order.states=='INIT'}">
														<span class="c-red fsize14">
														未完成
														</span>
														</c:if>
														<c:if test="${order.states=='CANCEL'}">
														<span class="c-999 fsize14">
														已取消订单
														</span>
														</c:if>
														<c:if test="${order.states=='REFUND'}">
														<span class="c-blue fsize14">
															已退款
														</span>
														</c:if>
													</div>
												</section>
												<section class="col-2 disIb vam u-o-right">
													<c:if test="${order.states=='SUCCESS'}">
														<div class="tac u-o-r-btn-infor">
															<%--<p class="c-999 f-fA mb10"><a href="javascript:void(0)" onclick="delTrxorderDetailDTO('${detail.trxorderDetailId}')" class="cou-kscp">删除课程</a></p>--%>
															<c:if test="${order.orderType=='COURSE'}">
																<p class="mb20"><a href="${ctx}/uc/courseCommen/${detail.courseId}" class="cou-kscp">评价课程</a></p>
															</c:if>
														</div>
													</c:if>
												</section>
												<div class="clear"></div>
											</dd>
											</c:forEach>
										</c:if>
									</dl>
								</c:forEach>
							</div>
						</div>
						<!-- 公共分页 开始 -->
						<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
						<!-- 公共分页 结束 -->
					</div>
				</section>
			</div>
		</section>
	</div>
</article>
</body>
</html>