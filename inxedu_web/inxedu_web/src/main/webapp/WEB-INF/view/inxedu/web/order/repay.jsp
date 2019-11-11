<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程支付</title>
	<script type="text/javascript" src="${ctx}/static/inxweb/shopcart/shopcart.js"></script>
	<script>
		$(function() {
			placeholderFun()//placeholder的兼容方法；

			var couponCode='${couponCode}';
			$("#queryCoupon").val('${couponCode}');
			var requestId='${trxorder.orderNo}';
			if(couponCode!=null&&couponCode!=''){
				couponBtnClick();//显示优惠券编码div
				$("#couponCode").val(couponCode);
				addcode(requestId);//验证优惠券
			}
			$("li>img").click(function (){
				var ra = $(this).prev(':radio');
				if(ra!=null){
					$(':radio').attr('checked',false);
					$(ra).attr('checked',true);
				}
			});

			//是否显示弹窗
			if("${showPayDialog}"=="true"){
			    //显示的支付方式
			    var payTypeShow="${payType}";
			    if(isNotEmpty("${paybank}")&&"${paybank}"!="undefined"){//选择了某个银行
                    payTypeShow=$("input[name='defaultbank'][value='${paybank}']").attr("bankName");
                }
				dialog('提示',payTypeShow,3,"${trxorder.orderNo}");
			}

            //默认支付方式处理
            if($("#ulPayType").children().size()<=0 ){
                dialog('提示','该网站暂不支持购买',1);
                $(".order-btn").remove();
                $("#ulPayType").parent().parent().hide();
            }else{
                $("#ulPayType").find("input").eq("0").click();
            }
        });


    </script>
</head>
<body>
<div id="aCoursesList" class="bg-fa of">
	<div class="container">
		<section class="path-wrap txtOf hLh30">
			<a class="c-999 fsize14" title="" href="${ctx}">首页</a>
			\ <span class="c-333 fsize14">商品支付</span>
		</section>
		<article class="mt30 repay-big-box">
			<%--<div class="order-step-bg-2 order-step"></div>--%>
			<div class="mt30">
				<header class=""><span class="fsize24 c-333">商品订单</span></header>
				<dl id="shopingcart" class="c-order-list of">
					<dt>
						<ul class="c-o-head clearfix of">
							<li class="c-head-li-16 tac"><span>商品</span></li>
							<li class="c-head-li-36 tac"><span>标题</span></li>
							<li class="c-head-li-3 tac"><span>讲师</span></li>
							<li class="c-head-li-2 tac"><span>价格</span></li>
							<%--<li class="c-head-li-4"><span>操作</span></li>--%>
						</ul>
					</dt>
					<c:forEach items="${orderList }" var="sc">
						<dd>
							<ul class="c-o-tbody clearfix of">
								<li class="c-head-li-16"><div class="c-o-t-img">
									<c:choose>
										<c:when test="${not empty sc.courseLogo}">
											<img src="${staticServer}${sc.courseLogo}" class="img-responsive">
										</c:when>
										<c:otherwise>
											<img src="/static/inxweb/img/coures.gif" class="img-responsive">
										</c:otherwise>
									</c:choose>
								</div></li>
								<li class="c-head-li-36">
									<div class="c-head-title">
										<h6 class="unFw c-666"> ${sc.courseName}</h6>
										<div class="mt10 u-order-desc">
											<p class="c-999 txtOf"> ${sc.courseTitle}</p>
										</div>
									</div>
								</li>
								<li class="c-head-li-3 tac">
									<div class="c-t-wz">
										<span class="c-666 vam disIb">
											<c:forEach items="${sc.teacherList }" var="teacher">
												${teacher.name}&nbsp;
											</c:forEach>
										</span>
									</div>
								</li>
								<li class="c-head-li-2 tac">
									<div class="c-t-wz">
										<span class="c-master fsize14 disIb vam">￥${sc.currentPrice }</span>
									</div>
								</li>
								<%--<li>
									<div class="c-t-wz"><a class="c-666" href="javascript:deleteid(3,13,1)">取消</a></div>
								</li>--%>
							</ul>
						</dd>
					</c:forEach>
				<div class="buyCom_price c-666 fr tar mt10 pr10">
					<span>商品数量：<span id="buyCount" class="fsize14 c-orange mr5">${orderList.size()}</span>&nbsp;&nbsp;</span>
					商品金额：<span id="div_totalMoney" class="fsize14 c-orange mr5">￥${bankAmount }</span>
				</div>
			</dl>

			<div  style="display: none;">
				<!-- alipay参数 -->
				<form action="${ctx }/order/bank" name="orderForm" id="orderForm" method="post" target="_blank">
					<input id="orderId" name="orderId" type="hidden" value="${trxorder.orderId}"/>
					<input id="defaultbank" name="bankId" type="hidden" value="${paybank}"/>
					<input id="payType" name="payType" type="hidden" value="${empty originalPayType?'ALIPAY':originalPayType}" />
				</form>
			</div>
			<!-- 优惠券编码 -->
			<div  style="display: none;">
				<input id="couponCodeHidden" type="hidden" value=""/>
			</div>

			<div class="mt30">
				<header class=""><span class="fsize24 c-333">支付方式</span></header>
				<div class="c-pay-method">
					<c:if test="${serviceSwitch.account=='ON'}">
						<div class="of">
							<header class="c-p-title">账户余额</header>
							<div class="clearfix">
								<div class="c-p-left">
									<div class="c-couse-card">
										<p>账户余额：<span class="c-master fsize24 f-fG">￥${userAccount.balance}</span></p>
										<p class="mt20">优先使用余额支付，余额不足，使用其它方式支付剩余金额!</p>
									</div>
								</div>
								<%--<div class="c-p-right">
									<img src="/static/inxweb/img/coucard.png">
								</div>--%>
							</div>
						</div>
					</c:if>
					<c:if test="${yibaoIsOpen=='ON'}">
						<div  class="of">
							<header class="c-p-title">网上银行</header>
							<div class="buyB_payPlat">
								<ul class="clearfix">

                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="CEB-NET-B2C" bankName="光大银行"> <img src="/static/inxweb/img/buy/bank_ZGGDYH.png" alt="光大银行"></label></li>
                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="ICBC-NET-B2C" bankName="中国工商银行"><img src="/static/inxweb/img/buy/bank_ZGGSYH.png" alt="中国工商银行"></label></li>
                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="CCB-NET-B2C" bankName="中国建设银行"><img src="/static/inxweb/img/buy/bank_ZGJSYH.png" alt="中国建设银行"></label></li>
                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="ABC-NET-B2C" bankName="中国农业银行"><img src="/static/inxweb/img/buy/bank_ZGNYYH.png" alt="中国农业银行"></label></li>
                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="CMBCHINA-NET-B2C" bankName="招商银行"><img src="/static/inxweb/img/buy/bank_ZSYH.png" alt="招商银行"></label></li>
                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="BOC-NET-B2C" bankName="中国银行"><img src="/static/inxweb/img/buy/bank_ZGYH.png" alt="中国银行"></label></li>
                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="BOCO-NET-B2C" bankName="中国交通银行"><img src="/static/inxweb/img/buy/bank_JTYH.png" alt="中国交通银行"></label></li>
                                    <li><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="POST-NET-B2C" bankName="中国邮政储蓄银行"><img src="/static/inxweb/img/buy/bank_ZGYZCXYH.png" alt="中国邮政储蓄银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="CIB-NET-B2C" bankName="兴业银行"><img src="/static/inxweb/img/buy/bank_XYYH.png" alt="兴业银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="CMBC-NET-B2C" bankName="中国民生银行"><img src="/static/inxweb/img/buy/bank_ZGMSYH.png" alt="中国民生银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="ECITIC-NET-B2C" bankName="中信银行"><img src="/static/inxweb/img/buy/bank_ZXYH.png" alt="中信银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="PINGANBANK-NET-B2C" bankName="平安银行"><img src="/static/inxweb/img/buy/bank_PAYH.png" alt="平安银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="SDB-NET-B2C" bankName="深圳发展银行"><img src="/static/inxweb/img/buy/bank_SZFZYH.png" alt="深圳发展银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="SHB-NET-B2C" bankName="上海银行"><img src="/static/inxweb/img/buy/bank_SHYH.png" alt="上海银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="GDB-NET-B2C" bankName="广发银行"><img src="/static/inxweb/img/buy/bank_GFYH.png" alt="广发银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="SPDB-NET-B2C" bankName="上海浦东发展银行"><img src="/static/inxweb/img/buy/bank_PFYH.png" alt="上海浦东发展银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="HXB-NET-B2C" bankName="华夏银行"><img src="/static/inxweb/img/buy/bank_HXYH.png" alt="华夏银行"></label></li>
                                    <li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="BCCB-NET-B2C" bankName="北京银行"><img src="/static/inxweb/img/buy/wal_bank03_beiJIng.jpg" alt="北京银行"></label></li>
                                        <%--<li class="buyB_payPlatNone"><label><input type="radio" onclick="checkbank('YEEPAY')" name="defaultbank" value="BJRCB-NET"><img src="/static/inxweb/img/buy/bank_BJNSYH.png" alt="北京农商银行"></label></li>--%>
								</ul>
							</div>
						</div>
					</c:if>
					<div  class="of">
						<header class="c-p-title">第三方支付</header>
						<div class="buyB_payPlat">
							<ul class="clearfix" id="ulPayType">
								<c:if test="${zhifubaoIsOpen=='ON' }">
									<li> <label> <input type="radio" onclick="checkbank('ALIPAY')" checked="checked" name="alipay" value=""> <img alt="支付宝" src="/static/inxweb/img/buy/buyB_pay_kuaiqian3.jpg"> </label> </li>
								</c:if>
								<c:if test="${weixinIsOpen=='ON' }">
									<li><label> <input type="radio" onclick="checkbank('WEIXIN')" name="weixin" value=""> <img alt="微信" src="/static/inxweb/img/buy/buyB_pay_wx.jpg"> </label> </li>
								</c:if>
								<%--<c:if test="${keywordmap.keyword.verifykq=='ON' }">
									<li><label><input type="radio" value="00" name="kqBank" onclick="checkbank('KUAIQIAN')"   style="margin-top:5px;" />
										<img src="/static/inxweb/img/buy/buyB_pay_kuaiqian1.jpg" alt="快钱"  /></label></li>
								</c:if>--%>
								<c:if test="${yibaoIsOpen=='ON'}">
									<li>
										<label>
											<input type="radio" value="" name="defaultbank"  onclick="checkbank('YEEPAY')"/>
											<img src="/static/inxweb/img/buy/buyB_pay_yibao.jpg" alt="易宝"  />
										</label>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="mt30">
				<header class=""><span class="fsize24 c-333">结算信息</span></header>
				<div class="c-pay-method c-p-m">
					<div class="mobile-top">
						<c:if test="${couponIsOpen=='ON'}">
							<div class="fl ml20">
								<p class="fsize14 c-666">使用代金券可以抵消部分金额哦</p>
								<div class="mt20 coupon-box clearfix">
									<%--<label class='c-666' style="display: inline-block;width: 115px;text-align: right;">使用我的优惠券：</label>--%>
									<select id="queryCoupon" class="buyText01 fl" name="queryCouponCode.status" onchange="chooseCoupon(this)" style="width: 204px;">
										<option value="">--选择我已有的优惠券--</option>
										<c:forEach items="${couponCodeList}" var="coupon">
											<option value="${coupon.couponCode}">
												<c:if test="${coupon.type==1}">
													<fmt:formatNumber value="${coupon.amount}" pattern="0.0"/>折
												</c:if>
												<c:if test="${coupon.type==2}">
													￥${coupon.amount}
												</c:if>
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="mt20 coupon-box clearfix">
									<input class="buyText01 fl" type="text" id="couponCode" onkeyup="inputcode()" name="" placeholder="请输入优惠券编码">
									<a class="buyCoupon_add2 fl" href="javascript:addcode('${trxorder.orderNo}')" id="tjcode">添加</a>
									<a href="javascript:initPrice()" class="buyCoupon_add2 fl"  style="display:none" id="initcode">取消</a>
								</div>
							</div>
						</c:if>
						<div class="fr tar ddzj">
							<p class="fsize14 c-333"><span class=" hLh30">订单总价：<tt class="c-master f-fG fsize16" id="oldmoney">￥${trxorder.orderAmount}</tt></span><span class="ml5 mr5 hLh30">-</span><span class=" hLh30">优惠金额：<tt class="c-master f-fG fsize16" id="yhmoney">￥0.00</tt></span><span class="ml4 mr5 hLh30">=</span><span class="c-master f-fG fsize16 hLh30" id="paySumPrice">￥${trxorder.sumMoney}</span></p>
							<p class="fsize14 c-333 hLh30 mt10"><span>优惠类型：<tt class="c-master" id="yhTypeId">无</tt></span></p>
							<p class="fsize24 c-333 mt20 hLh30"><span>订单支付金额：<tt class="c-master fsize34 f-fG" id="payprice">￥${trxorder.sumMoney}</tt></span></p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="tar mt40 ord-btn-tj">
						<a href="javascript:disOrderSuccess()" class="order-btn">提交订单</a>
					</div>
				</div>
			</div>

			<!--提交成功 start-->
			<article class="mt30" id="order_success"   style="display: none" >
				<div class="order-table pb20"  >
					<section class="mt20 mr20 mb20 ml20">
						<div class="orderSuccess pr ml20"  >
							<ol>
								<li><h2 class="hLh30 line3 pb10"><strong class="c-333 fsize20"><tt>订单号:</tt><font class="ml5 mr5 c-orange" id="orderId_success">${trxorder.orderNo}</font>下单成功，订单总额<font class="ml5 c-orange" id="amount_success"></font></strong></h2></li>
								<li><h2 class="hLh30 line3 pb10"><strong class="c-333 fsize20"><tt>帐户余额:</tt><font class="ml5 mr5 c-orange" id="balance_s" >￥0.00</font><font class="ml5 c-orange" id="bankAmount_s">，需充值￥0.00</font></strong></h2></li>
								<li class="mt10">
									<span class="vam"><a class="order-submit" title="" id="payNow" href="javascript:void(0)" onclick="goToBank()">立即支付</a></span>
								</li>
								<li class="tac"></li>
								<li class="mt20"><span class="c-333 fsize14">您现在可以：<a id="repaylink" class="c-333 mr10" title="查看订单信息" href="/front/repay/"><u>返回修改支付方式</u></a> | <a class="c-4e ml10 mr10" title="进入学习中心" href="${ctx }/uc/home"><u>进入学习中心</u></a> | <a class="c-4e ml10" title="返回首页" href="${ctx }/"><u>返回首页</u></a></span></li>
							</ol>
							<span class="succIcon pa"></span>
						</div>
					</section>
				</div>
			</article>
			<!--提交成功 end-->
				</div>
		</article>
	</div>
</div>
<script>
	if(isNotEmpty('${originalPayType}')){
		payType = '${originalPayType}';
		//选中支付方式
		checkbank(payType);
        $("input[name='"+payType.toLowerCase()+"']").prop("checked",true);
        if(payType=='YEEPAY'){
            $("input[name='defaultbank'][value='${paybank}']").attr("checked",true);
        }
	}
</script>
</body>
</html>