<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>我的会员</title>
	<script type="text/javascript">
		/*到提交订单页面*/
		function toPayMember(memberSaleId,memberTypeId) {
			/*if (memberSaleId!=0&&memberSaleId!=null){*/
				window.location.href = baselocation+"/payMember/"+memberSaleId+"?memberTypeId="+memberTypeId;
			/*}else {
				msgshow("管理员还没有设置该类会员价格","true","1500");
			}*/
		}

	</script>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div class="">
					<section class="u-r-tit-all">
						<h2 class="unFw">
							<span class="f-fH current">
								我的会员
							</span>
						</h2>
					</section>
					<div class="u-r-all-box" id="p_tCont">
						<section class="member-all-box">
							<div class="member-zt">
								<article class="mem-zt-box">
									<ul class="clearfix">
										<c:forEach items="${memberTypeList}" var="memberType" varStatus="index">
											<c:if test="${not empty memberType.endTime || memberType.status==0}">
												<li>
													<div class="u-m-card

													<c:if test="${not empty memberType.endTime}"> u-m-card-yes</c:if>
													<c:if test="${empty memberType.endTime}"> u-m-card-no</c:if>
														"
													>
														<section class="ml10 mr10">
															<p class="f-fM u-m-c-txt hLh30 tac mt10">
																<em class="icon30 vip-ico">&nbsp;</em>
																<tt class="vam f-fM c-999 fsize16">${memberType.title}</tt>
															</p>
															<p class="hLh20 of mt15">
																<%--有时间为一个月的会员商品  并且 没有开通该类会员--%>


																<c:if test="${not empty memberSaleList[index.index].price && empty memberType.endTime}">
																	<tt class="fsize12 c-999 vam f-fM">价格：</tt>
																	<tt class="fsize14 c-master f-fM">${memberSaleList[index.index].price}/月</tt>
																</c:if>
																<c:if test="${empty memberSaleList[index.index].price && empty memberType.endTime}">
																	<tt class="fsize12 c-999 vam f-fM">价格：</tt>
																	<tt class="c-999 fsize12">暂无</tt>
																</c:if>
																<c:set var="after7day" value="<%=new Date(System.currentTimeMillis()+604800000)%>"></c:set>

																<%--开通了该类会员--%>
																<c:if test="${not empty memberType.endTime}">
																	<c:if test="${memberType.endTime>after7day}">
																		<tt class="fsize12 c-999 vam f-fM">状&nbsp;&nbsp;&nbsp;态：</tt>
																		<tt class="fsize12 c-green vam f-fM">已开通</tt>
																	</c:if>
																	<c:if test="${memberType.endTime<after7day}">
																		<tt class="fsize12 c-999 vam f-fM">状&nbsp;&nbsp;&nbsp;态：</tt>
																		<tt class="fsize12 c-master vam f-fM undis">即将到期</tt>
																	</c:if>
																</c:if>
															</p>
															<p class="vip-tq-txt of hLh20 mt5 vip-tq-txt-mo txtOf">
																<c:if test="${empty memberType.endTime}">
																	<tt class="fsize12 c-999 vam f-fM">权限：</tt>
																	<tt class="fsize12 c-999 vam f-fM">网站内所属该会员权限课程都可免费观看</tt>
																</c:if>
																<c:if test="${not empty memberType.endTime}">
																	<tt class="fsize12 c-999 vam f-fM">有效期：</tt>
																	<tt class="fsize12 c-999 vam f-fM">
																		<fmt:formatDate value="${memberType.endTime}" pattern="yyyy-MM-dd"/>
																	</tt>
																</c:if>
															</p>
															<div class="tac u-m-c-b-warp"

															>
																<c:if test="${memberType.status==0}">
																	<a href="javascript:void (0)" onclick="toPayMember(${not empty memberSaleList[index.index].id?memberSaleList[index.index].id:0},${memberType.id})" title="开通会员" class="u-m-c-btn f-fM tac">
																		<c:if test="${not empty memberType.endTime }">
																			会员续费
																		</c:if>
																		<c:if test="${empty memberType.endTime }">
																			马上开通
																		</c:if>
																	</a>
																</c:if>
																<c:if test="${memberType.status==1}">
																	<a href="javascript:void (0)"title="开通会员" class="u-m-c-btn f-fM tac">
																		该类会员已停用
																	</a>
																</c:if>

															</div>
														</section>
													</div>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</article>
							</div>
							<%--<div class="member-yt mt50">--%>
								<%--<div class="m-acc-title">--%>
									<%--<span class="f-fM">--%>
										<%--会员特权--%>
									<%--</span>--%>
								<%--</div>--%>
								<%--<div class="mem-body mem-b-txt">--%>
									<%--<p class="c-999 f-fM">--%>
										<%--1.开通会员后可免费观看会员权限下的全部课程--%>
									<%--</p>--%>
								<%--</div>--%>
							<%--</div>--%>
							<div class="member-jl mt50">
								<div class="m-acc-title">
									<span class="f-fM">
										开通记录
									</span>
								</div>
								<div class="mem-body">
									<table class="member-jl-tab" width="100%" cellspacing="0" cellpadding="0" border="0">
										<thead>
											<tr>
												<th align="center">会员名称</th>
												<th align="center">开通日期</th>
												<th align="center">结束日期</th>
												<th align="center" class="mbile-none">状态</th>
												<th align="center">价&nbsp;&nbsp;&nbsp;&nbsp;格</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${memberRecordDTOs}" var="memberRecord">
												<tr>
													<td align="center">${memberRecord.memberTitle}${memberRecord.month}个月</td>
													<td align="center">
														<fmt:formatDate value="${memberRecord.beginDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
													</td>
													<td align="center">
														<fmt:formatDate value="${memberRecord.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
													</td>
													<td align="center" class="mbile-none">
														<c:if test="${memberRecord.status=='SUCCESS'}">
															<tt class="u-ass-zc f-fM">正常</tt>
														</c:if>
														<c:if test="${memberRecord.status=='CLOSED'}">
															<tt class="u-ass-gq f-fM">关闭</tt>
														</c:if>
													</td>
													<td align="center">${memberRecord.price}元</td>
												</tr>
											</c:forEach>
											<form action="${ctx}/uc/associator"name="searchForm" id="searchForm" method="post">
												<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
											</form>
										</tbody>
									</table>
									<!-- 公共分页 开始 -->
									<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
									<!-- 公共分页 结束 -->
								</div>
							</div>
						</section>
					</div>
				</div>
			</section>
		</div>
	</article>
</body>
</html>