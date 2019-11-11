<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>我的账户</title>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div class="">
					<section class="u-r-tit-all">
						<h2 class="unFw">
							<span class="f-fH current">
								我的账户
							</span>
						</h2>
					</section>
					<div class="u-r-all-box" id="p_tCont">
						<section class="member-all-box">
							<div class="my-oder-in of">
								<div class="myacc-l">
									<em class="m-acc-ico icon20 mr5">&nbsp;</em>
									<tt class="f-fM c-333 fsize16 vam">账户金额：共计</tt>
									<tt class="f-fM c-master fsize24 vam">￥${userAccount.balance}</tt>
									<c:if test="${serviceSwitch.recharge=='ON'}">
										<tt class="f-fM c-999 fsize14 vam">，其中￥${userAccount.vmAmount}来自充值卡</tt>
									</c:if>
									<c:if test="${serviceSwitch.invite=='ON'}">
										<tt class="f-fM c-999 fsize14 vam">，￥${userAccount.backAmount}来自邀请返现。</tt>
									</c:if>
								</div>
								<div class="myacc-r">
									<div class="myacc-r-cz">
										<em class="m-acc-cz-ico icon20 mr5">&nbsp;</em>
										<tt class="f-fM c-333 fsize16 vam">账户充值：</tt>
										<input type="text" id="payCash" class="u-a-inpt u-a-inpt-zhcz" />
										<tt class="f-fM c-333 fsize16 vam mr20">元</tt>
										<a href="javascript:void(0)" onclick="doPayCash()" title="账户充值" class="f-fM fsize14 btn-bg mr50">账户充值</a>
									</div>
									<div class="myacc-r-cz myacc-r-cz-btn">
										<c:if test="${withdrawals.withdrawalsSwitch=='ON'}">
											<a href="javascript:void (0)" onclick="dialog('余额提现','',10)" title="余额提现" class="f-fM fsize14 btn-ku mr30">余额提现</a>
										</c:if>
										<c:if test="${serviceSwitch.recharge=='ON'}">
											<a href="javascript:void (0)" onclick="dialog('充值卡激活','',11)" title="充值卡激活" class="f-fM fsize14 btn-ku mr30">充值卡激活</a>
										</c:if>
									</div>
								</div>
							</div>
							<div class="member-yt mt30">
								<div class="m-acc-title">
									<span class="f-fM">
										账户余额用途
									</span>
								</div>
								<div class="mem-body mem-b-txt">
									<p class="c-999 f-fM">
										1.账户余额可在站内购买商品。
									</p>
									<c:if test="${withdrawals.withdrawalsSwitch=='ON'}">
										<p class=" c-999 f-fM">
											2.账户余额可作为现金，提现到账户本人绑定的银行卡。
										</p>
									</c:if>
								</div>
							</div>
							<div class="member-yt mt30">
								<div class="m-acc-title">
									<span class="f-fM">
										账户充值说明：
									</span>
								</div>
								<div class="mem-body mem-b-txt">
									<p class=" c-999 f-fM ">
										1.现金充值：点击<tt class="f-fM fsize14 c-master">账户充值</tt>。支持银行卡、支付宝、微信、易支付等充值方式。
									</p>
									<c:if test="${serviceSwitch.recharge=='ON'}">
										<p class="c-999 f-fM">
											2.充值卡充值：点击<tt class="f-fM fsize14 c-master">充值卡激活</tt>。充值卡仅限已登录账号使用，请在卡片有效期内激活，注意卡片信息保密。
										</p>
									</c:if>
									<p class="fsize14 c-999 f-fM ">
										<tt class="f-fM  c-master">提示：</tt>充值额度不可提现。账户中金额与人民币比例为1:1，最小支持5元充值。
									</p>
								</div>
							</div>
							<c:if test="${withdrawals.withdrawalsSwitch=='ON'}">
								<div class="member-yt mt30">
									<div class="m-acc-title">
										<span class="f-fM">
											账户提现说明：
										</span>
									</div>
									<div class="mem-body mem-b-txt">
										<p class=" c-999 f-fM">
											点击<tt class="f-fM fsize14 c-master">余额提现</tt>，账户内金额可全部提现到登录账号所绑定的银行卡内。提现过程中，提现的金额会处于冻结状态，提现审核通过，将自动转入银行卡内，请注意查收。
										</p>
									</div>
								</div>
							</c:if>
							<div class="member-jl mt30">
								<div class="m-acc-title">
									<aside class="fr m-acc-sou mr30">

										<form action="${ctx}/uc/myAccount"name="searchForm" id="searchForm" method="post">
											<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
											<tt class="f-fM fsize14 c-666">类型查找：</tt>
											<select name="queryUserAccounthistory.actHistoryType" onchange="$('#searchForm').submit()" id="actHistoryType">
												<option  value="" >全部</option>
												<option value="SALES" >站内消费</option>
												<option value="CASHLOAD"  >账户充值</option>
												<c:if test="${serviceSwitch.recharge=='ON'}">
													<option value="VMLOAD"  >充值卡充值</option>
												</c:if>
												<c:if test="${withdrawals.withdrawalsSwitch=='ON'}">
												<option value="DRAWMONEY"  >余额提现</option>
												</c:if>
												<c:if test="${serviceSwitch.invite=='ON'}">
													<option value="BACKMONEY"  >返现</option>
												</c:if>
												<option value="ADMINLOAD">后台充值</option>
											</select>
										</form>
									</aside>
									<span class="f-fM">
										账户使用记录
									</span>
									<div class="clear"></div>
								</div>
								<div class="mem-body">
									<c:if test="${empty accList}">
										<!-- /无数据提示 开始-->
										<section class="no-data-wrap">
											<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
										</section>
										<!-- /无数据提示 结束-->
									</c:if>
									<c:if test="${not empty accList}">
										<table class="member-jl-tab" width="100%" cellspacing="0" cellpadding="0" border="0">
											<thead>
												<tr>
													<th align="center">金额</th>
													<th align="center">类型</th>
													<th align="center">日期</th>
													<th align="center" class="mbile-none">余额</th>
													<th align="center" class="mbile-none">状态</th>
													<th align="center">备注</th>
													<%--<th align="center">操作</th>--%>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${accList}" var="accountHistory">
													<tr>
														<td align="center">
															<c:if test="${accountHistory.actHistoryType!='SALES'}">
																<span class="u-ass-zc f-fM">${accountHistory.trxAmount}</span>
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='SALES'}">
																<span class="u-ass-gq f-fM">${accountHistory.trxAmount}</span>
															</c:if>
														</td>
														<td align="center">
															<c:if test="${accountHistory.actHistoryType=='SALES'}">
																消费
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='REFUND'}">
																退款
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='CASHLOAD'}">
																现金充值
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='VMLOAD'}">
																充值卡充值
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='DRAWMONEY'}">
																提现
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='BACKMONEY'}">
																返现
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='ADMINLOAD'}">
																后台充值
															</c:if>
														</td>
														<td align="center"><fmt:formatDate value="${accountHistory.createTime}" pattern="yyyy/MM/dd" /></td>
														<td align="center" class="mbile-none">${accountHistory.balance}</td>
														<td align="center" class="mbile-none">
															<c:if test="${accountHistory.actHistoryType=='DRAWMONEY'&&accountHistory.status=='NOTVIEWED'}">
																<span class="u-ass-cw f-fM">审批中</span>
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='DRAWMONEY'&&accountHistory.status=='SUCCESS'}">
																<span class="u-ass-zc f-fM">已提现</span>
															</c:if>
															<c:if test="${accountHistory.actHistoryType=='DRAWMONEY'&&accountHistory.status=='FAIL'}">
																<span class="u-ass-gq f-fM">提现失败</span>
															</c:if>
															<c:if test="${accountHistory.actHistoryType!='DRAWMONEY'}">
																<span class="u-ass-zc f-fM">成功</span>
															</c:if>
														</td>
														<td align="center">
																${accountHistory.description}
														</td>
														<%--<td align="center">
															<a href="${ctx}/uc/accountInfo" title="查看详情" class="btn f-fM">查看详情</a>
														</td>--%>
													</tr>

												</c:forEach>
												<%--<tr>
													<td align="center">
														<span class="u-ass-gq f-fM">-10</span>
													</td>
													<td align="center">站内消费</td>
													<td align="center">2017-01-02</td>
													<td align="center">200.00</td>
													<td align="center">
														<span class="u-ass-zc f-fM">成功</span>
													</td>
													<td align="center">
														<a href="${ctx}/uc/accountInfo" title="查看详情" class="btn f-fM">查看详情</a>
													</td>
												</tr>
												<tr>
													<td align="center">
														<span class="u-ass-zc f-fM">+10</span>
													</td>
													<td align="center">账户充值</td>
													<td align="center">2017-01-02</td>
													<td align="center">200.00</td>
													<td align="center">
														<span class="u-ass-gq f-fM">失败</span>
													</td>
													<td align="center">
														<a href="${ctx}/uc/accountInfo" title="查看详情" class="btn f-fM">查看详情</a>
													</td>
												</tr>
												<tr>
													<td align="center">
														<span class="u-ass-gq f-fM">-10</span>
													</td>
													<td align="center">余额提现</td>
													<td align="center">2017-01-02</td>
													<td align="center">200.00</td>
													<td align="center">
														<span class="u-ass-dj f-fM">审核中</span>
													</td>
													<td align="center">
														<a href="${ctx}/uc/accountInfo" title="查看详情" class="btn f-fM">查看详情</a>
													</td>
												</tr>--%>
											</tbody>
										</table>
										<!-- 公共分页 开始 -->
										<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
										<!-- 公共分页 结束 -->
									</c:if>
								</div>
							</div>
						</section>
					</div>
				</div>
			</section>
		</div>
	</article>
<script>
	$(function(){
		$("#actHistoryType").val("${queryUserAccounthistory.actHistoryType}");
	});
	function doPayCash(){
		var payCash=$("#payCash").val();
		if(payCash==null||payCash==''){
			dialog('充值提示','请输入充值金额',1);
			return;
		}
		var reg = /^[0-9]+(.[0-9]{1,2})?$/;

		if(isNaN(payCash) ||!reg.test(payCash)){
			dialog('充值提示','请输入正确的充值金额',1);
			return;
		}
		if(payCash<5){
			dialog('充值提示','最小支持5元充值',1);
			return;
		}
		window.location.href="/account/recharge?recharge="+payCash;
	}
</script>
</body>
</html>