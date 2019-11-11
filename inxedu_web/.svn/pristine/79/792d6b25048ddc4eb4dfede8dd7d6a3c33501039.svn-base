var payType='ALIPAY';//选择的支付方式
/** 购物车单个课程删除 */
function deleteid(id,goodsid,type) {
	$.ajax({
		url : baselocation + "/shopcart/ajax/deleteShopitem",
		data : {
			"id":id,
			'goodsid' : goodsid,
			"type":type
		},
		type : "post",
		dataType : "json",
		async : false,
		cache : false,
		success : function(result) {
			queryShopCartinfo();
			//重新查询购物车右侧列表
			shopCartHtml();
			//显示购物车数量
			showshopnums();
		}
	});
}

//优惠券
function couponBtnClick(object){
	if (!$(object).hasClass("c-on")) {
		$(object).addClass("c-on");
		$(object).children("tt").css({"color" : "#FF4000"});
		$(".buyCouponWrap").css({"visibility" : "visible"});
	} else {
		$(object).removeClass("c-on");
		$(object).children("tt").css({"color" : "#666"});
		$(".buyCouponWrap").css({"visibility" : "hidden"});
	}
}

//初始化获得购物车信息
function queryShopCartinfo() {
	$.ajax({
		url : baselocation + "/shopcart/ajax/queryShopCartinfo?type=1",
		data : {
		},
		type : "post",
		dataType : "text",
		async : false,
		cache : false,
		success : function(result) {
			if(result==''&&result==null){
				dialog('友情提示',"购物车内没有课程,请先选购课程",1);
			}else{
				$("#shopingcart").html(result);
			}
			initPrice();
		}
	});
    if(!isLogin()){
		lrFun();
        /*$("#userEmail").mailAutoComplete({
            boxClass: "out_box", //外部box样式
            listClass: "list_box", //默认的列表样式
            focusClass: "focus_box", //列表选样式中
            markCalss: "mark_box", //高亮样式
            autoClass: false,//不使用默认的样式
            textHint: true //提示文字自动隐藏
        });*/
    }
}

//显示页面课程价格,使用优惠卷时，只改变这3个div的显示即可
function initPrice(){
	//显示初始化 支付金额
	$("#oldmoney").html($("#div_totalMoney").html());
	$("#paySumPrice").html($("#div_totalMoney").html());
	$("#payprice").html($("#div_totalMoney").html());

	//将优惠券的信息清除
	$("#initcode").hide();
	$("#tjcode").show();
	$("#yhmoney").html("￥0.00");
	$("#yhTypeId").html("无");
	$("#couponCode").val("");
	$("#couponCodeHidden").val("");


	//$("#couponBtn").removeClass("c-on");
	//$("#couponBtn").children("tt").css({"color" : "#666"});
	//$(".buyCouponWrap").css({"visibility" : "hidden"});
	$("#queryCoupon").val('');
}
//格式化价格
function fmtprice(price){
	if(typeof(price) == 'undefined' || price == null || price ==""|| isNaN(price) ||price == Infinity){
	        return "￥0.00";
	}else{
		if (parseFloat(price).toFixed(2)>0){
			return "￥"+parseFloat(price).toFixed(2);
		}else {
			return "￥0.00"
		}
	}
}

//判断订单,提交订单 otherId相关id 暂时只用于会员销售类型id orderType商品类型
function order(otherId,orderType) {
    if(!isLogin()){
		lrFun();
        /*$("#userEmail").mailAutoComplete({
            boxClass: "out_box", //外部box样式
            listClass: "list_box", //默认的列表样式
            focusClass: "focus_box", //列表选样式中
            markCalss: "mark_box", //高亮样式
            autoClass: false,//不使用默认的样式
            textHint: true //提示文字自动隐藏
        });*/
        return ;
    }
    var buyCount=Number($("#buyCount").html());
	if (orderType=='MEMBER'&&otherId==0){
		msgshow("请选择要购买的会员！");
		return;
	}
    if((orderType=='COURSE')&&buyCount==0){
        dialog('提示信息',"购物车内没有课程，请先选购课程！",1);
        return;
    }
	if (orderType=='ACCOUNT'&&isNaN(otherId)){
		msgshow("请输入正确的充值金额！");
		return;
	}
	/*不走购物车的商品相关id*/
    var orderOtherId;
    if(otherId!=null&&otherId!=""){
        orderOtherId = otherId;
    }

    var couponCode=$("#couponCodeHidden").val();//优惠券编码
    //订单提交，服务端要做验证，下单时重新查询数据库
    $.ajax({
        url: "/order?command=buy&type=1",
        data:{"payType":payType,"couponcode":couponCode,"otherId":orderOtherId,"orderType":orderType},
        type:"post",
        dataType: "json",
        async : false,
        success: function(result) {
            if(result.success){
                if(isNotNull(result.entity.order)){
                    //金额页面只作为显示用，以服务端提交时重新取数据库为准
                    var orderId = result.entity.order.orderId;
                    $("#orderId").val(orderId);
                    $("#repaylink").attr("href","/front/repay/"+orderId);
                    //显示提交成功的DIV
                    $("#orderId_success").html(result.entity.order.orderNo);
                    $("#amount_success").html(fmtprice(result.entity.order.sumMoney));
                    $("#balance_s").html("￥"+result.entity.balance);
                    if(isNotEmpty(result.entity.bankAmount)){
                        $("#bankAmount_s").html("，还需充值￥"+result.entity.bankAmount);
                    }else{
                        $("#bankAmount_s").html("");
                    }
                    //$("#order_init").hide();
                    //$("#order_success").show();
                    //window.scrollTo(0,0);
					//清空购物车
					if(otherId==null||otherId==""){
						clearShopCart("1");
					}
					//到支付页面
					setTimeout("goToBank();",1000);
					if(!checkIsMobile()){
						//到重新支付页面
						setTimeout("window.location.href='/front/repay/"+orderId+"?showPayDialog=true&payType="+payType+"&orderType="+result.entity.order.orderType+"&paybank="+$("input[name='defaultbank']:checked").val()+"';",1200);
					}

					//goToBank();
                }else if(result.entity.msg=='param'){
                    dialog('友情提示',"参数错误",1);
                }else if(result.entity.msg=='amount'){
                    dialog('友情提示',"购物车金额错误",1);
                }else if(result.entity.msg=='courselosedate'){
                	dialog('友情提示',"购物车中有已经过期的课程，已经删除，请重新确认",17,'javascript:window.location.reload();');
                }
            }else{
				dialog('下单提示',result.message,1);
            }

        },
        error : function() {
			dialog('下单提示',"下单异常，请稍后再试",1);
        }
    });
}
/**
 * 选择支付宝支付
 * @param type 用于支付接口传递参数, 此处只用到0
 */
function checkbank(type){
	/*if(type=='KUAIQIAN'){
		payType='KUAIQIAN';
		$("input[name='kqBank']").attr("checked",true);
		$("input[name='alipay']").attr("checked",false);
		$("input[name='yeepay']").attr("checked",false);
		$("input[name='weixin']").attr("checked",false);
		$("input[name='defaultbank']").attr("checked",false);
		$("#payType").val('KUAIQIAN');
	}else */
	if(type=='ALIPAY'){
		payType='ALIPAY';
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='yeepay']").attr("checked",false);
		$("input[name='weixin']").attr("checked",false);
		$("input[name='defaultbank']").attr("checked",false);
		$("#payType").val('ALIPAY');
	}
	/*else if(type=='ALIPAY_BANK'){
		payType='ALIPAY';
		$("input[name='alipay']").attr("checked",false);
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='yeepay']").attr("checked",false);
		$("input[name='weixin']").attr("checked",false);
		$("#payType").val('ALIPAY');
	}*/
	else if(type=='YEEPAY'){
		payType="YEEPAY";
		$("#payType").val('YEEPAY');
		$("input[name='weixin']").attr("checked",false);
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='alipay']").attr("checked",false);
		$("input[name='ALIPAY_BANK']").attr("checked",false);
	}else if(type=='WEIXIN'){
		payType='WEIXIN';
		$("input[name='yeepay']").attr("checked",false);
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='alipay']").attr("checked",false);
		$("input[name='defaultbank']").attr("checked",false);
		$("#payType").val('WEIXIN');
	}
}
/**
 * 跳转到银行
 */
function goToBank(){
	if(payType=='YEEPAY'){
		//先清空
        $("#defaultbank").val("");
		var defaultbank=$("input[name='defaultbank']:checked").val();
		if(typeof(defaultbank)!="undefined"&&defaultbank!=''){
			$("#defaultbank").val(defaultbank);
		}
		document.orderForm.submit();
	}else if(payType=='KUAIQIAN'){
		document.orderForm.submit();
	}else if(payType=='ALIPAY'){
		var defaultbank=$("input[name='alipaybank']:checked").val();
		if(typeof(defaultbank)!="undefined"&&defaultbank!=''){
			$("#defaultbank").val(defaultbank);
		}
		document.orderForm.submit();
	}else if(payType=='WEIXIN'){
		document.orderForm.submit();
	}else{
		dialog('友情提示',"请返回选择支付方式",1);
	}
}

/**
 * 下单成功后，清空购物车操作
 * @param type
 */
function clearShopCart(type){
	$.ajax({
		url: "/shopcart/clearShopitem/"+type,
		data:{ },
		type:"post",
		dataType: "json",
		async:true,
		success: function(result) {
		},
		error : function() {
		}
	});
}
/**
 * 重新支付验证
 */
function disOrderSuccess(){
	if(!isLogin()){
		lrFun();
		/*$("#userEmail").mailAutoComplete({
		 boxClass: "out_box", //外部box样式
		 listClass: "list_box", //默认的列表样式
		 focusClass: "focus_box", //列表选样式中
		 markCalss: "mark_box", //高亮样式
		 autoClass: false,//不使用默认的样式
		 textHint: true //提示文字自动隐藏
		 });*/
		return ;
	}
	$.ajax({//更新订单信息
		url:"/front/repayupdate",
		data:{"payType":payType,"requestId":$("#orderId_success").html(),"couponcode":$("#couponCodeHidden").val()},
		type:"post",
		dataType:"json",
		async:false,
		success:function(result){
			if(result.message=="true"){
				if(result.entity!=null){//更新订单价格
					$("#balance_s").html(fmtprice(result.entity.balance));
					$("#amount_success").html(fmtprice(result.entity.amount));
					var bankAmount=parseFloat(result.entity.balance)-parseFloat(result.entity.amount);
					if(isNotEmpty(result.entity.bankAmount)){
						$("#bankAmount_s").html("，还需充值￥"+result.entity.bankAmount);
					}else{
						$("#bankAmount_s").html("");
					}
				}
				goToBank();
				var payTypeStr = "支付宝";
				if(payType=='YEEPAY'){
					payTypeStr = "易宝";
                    if(isNotEmpty($("input[name='defaultbank']:checked").val())) {//选择了银行
                        payTypeStr=$("input[name='defaultbank'][value='"+$("input[name='defaultbank']:checked").val()+"']").attr("bankName");
                    }

				}else if(payType=='WEIXIN'){
					payTypeStr = "微信";
				}
				dialog('提示',payTypeStr,3,$("#orderId_success").html());
				//$("#order_init").hide();
				//$("#order_success").show();
				//window.scrollTo(0,0);
			}else if(result.message=="frozen"){
				dialog('友情提示',"此优惠券已在其他订单中使用！",1);
			}else{
				dialog('友情提示',"订单异常，请稍后再试！",1);
			}
		}
	})
   
}
//输入优惠券
function inputcode() {
	if($("#couponCode").val()!=""){
		$('#tjcode').show();
		$("#initcode").hide();

		//手动输入优惠券时  将优惠券下拉框重置
		if($("#couponCode").val()!=$("#queryCoupon").val()){
			$("#queryCoupon").val('');

			$("#yhmoney").html("￥0.00");
			$("#yhTypeId").html("无");
			$("#couponCodeHidden").val("");
			$("#oldmoney").html($("#div_totalMoney").html());
			$("#paySumPrice").html($("#div_totalMoney").html());
			$("#payprice").html($("#div_totalMoney").html());
		}
	}
	else{
		$('#tjcode').hide();
		$("#initcode").show();
		initPrice();
	}
}
	
//验证优惠券
function addcode(requestId){
	var str=$("#couponCode").val().trim();
	if(str==""||str==null){
	dialog('友情提示',"请输入优惠券编号！",1);
	return;
	}
	 $.ajax({
	   type: "POST",
	   url: "/coupon/check",
	   data: {"couponCode":str,"requestId":requestId},
	   dataType : "json",
	   success: function(result){
		   $("#couponCodeHidden").val($("#couponCode").val());
		   var obj=result.entity;
		   if(result.message!='true'||obj==null){
			   initPrice();
			   dialog('友情提示',result.message,1);
			   return false;
		   }else{
			   $("#tjcode").hide();
			   $("#initcode").show();

				   var couponPrice=parseFloat(obj.couponCodeDTO.amount).toFixed(2);
				   var totalPrice=$("#div_totalMoney").html().replace("￥","");
				   var price=subtraction(totalPrice,couponPrice);
				   $("#yhmoney").html(fmtprice(couponPrice));//优惠金额
				   $("#yhTypeId").html("立减（"+fmtprice(couponPrice)+"元）");
				   $("#paySumPrice").html(fmtprice(price));
				   $("#payprice").html(fmtprice(price));

		   }
	   },
	   error : function (error) {
		   alert(error.responseText);
	   }
	});
}
/**
 * 选择我的优惠券
 */
function chooseCoupon(obj){
	var code=$(obj).val();
	if(code==null&&code==''){
		initPrice();
	}else{
		$("#initcode").hide();
		$("#tjcode").show();
		$("#yhmoney").html("￥0.00");
		$("#yhTypeId").html("无");
		$("#couponCode").val("");
		$("#couponCodeHidden").val("");
		$("#oldmoney").html($("#div_totalMoney").html());
		$("#paySumPrice").html($("#div_totalMoney").html());
		$("#payprice").html($("#div_totalMoney").html());
	}
	$("#couponCode").val(code);
	$("#couponCode").click();
}
/*根据会员类型加载子类*/
function memberType(memberTypeId,obj) {
	/*更改选中状态*/
	if (obj!=null){
		$(obj).parent().find("li").removeClass("current");
		$(obj).addClass("current")
	}
	$.ajax({
		url:baselocation+'/ajax/getMemberChildType',
		type:'post',
		dataType:'json',
		data:{'typeId':memberTypeId},
		success:function(result){
			if (result.success==true){
				var obj = [];
				/*获取会员售价集合*/
				obj = result.entity;
				var html="";
				for(var i=0;i<obj.length;i++){
					html +='<li>'+
						'<label>'+
						'<input id=memberSale'+obj[i].id+' onclick="selectMemberSale('+obj[i].id+','+obj[i].price+')"  name="memberSaleType" value="" type="radio">'+
						'<span class="f-fM">'+
						'<tt class="vam f-fM c-666 fsize14">'+obj[i].days+'个月</tt>'+
						'<tt class="vam f-fM c-666 fsize14">，共计</tt>'+
						'<tt class="vam f-fM c-master fsize16">'+obj[i].price+'</tt>'+
						'<tt class="vam f-fM c-666 fsize14">元</tt>'+
						'</span>'+
						'</label>'+
						'</li>'
				}
				$("#memberSale").html("");
				/*把售价放到select里*/
				$("#memberSale").append(html);
			}
		}
	})
}
/*选择会员商品*/
function selectMemberSale(memberSaleId,price) {
	$("#order").attr("href","javascript:order("+memberSaleId+",'MEMBER')");
	$(".price").html("￥"+price)
}