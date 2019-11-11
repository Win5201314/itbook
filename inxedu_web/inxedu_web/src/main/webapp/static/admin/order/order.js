
/**
 * 审核订单
 * @param orderId 订单号
 * @param em 点击元素
 */
function auditing(orderNo,em){
	if(confirm('订单开通后将变成支付成功状态，确认要开通？')){
		$.ajax({
			url:baselocation+'/admin/order/auditing/'+orderNo,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==false){
                    layer.msg(result.message, {icon: 5, shift: 6});
				}else{
					var order = result.entity.order;
					var state = $(em).parent("samp").parent("td").parent("tr").children("td")[4];
					$(state).html('<font color="#00e33b">已支付</font>');
					/*var payTime = $(em).parent("samp").parent("td").parent("tr").children("td")[6];
					$(payTime).text(result.entity.payTime);*/
					var loginName = $(em).parent("samp").parent("td").parent("tr").children("td")[5];
					$(loginName).text(result.entity.sysUser);
					var td = $(em).parent("samp").parent("td");
					$(td).append('<button onclick="refund('+orderNo+',this)" class="layui-btn layui-btn-small" type="button">退款</button>');
					//$(td).text('--');
					$(td).find("samp").hide();
				}
			},
			error:function(error){
                layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
			}
		});
	}
}

/**
 * 取消或恢复订单
 * @param state 订单状态 
 * @param orderId 订单ID
 */
function cancelOrRegain(state,orderId,orderNo,em){
	var message='确认要取消订单？';
	if(state=='INIT'){
		message='确认要恢复订单？';
	}
	if(confirm(message)){
		$.ajax({
			url:baselocation+'/admin/order/cancelOrRegain',
			type:'post',
			dataType:'json',
			data:{'order.states':state,'order.orderId':orderId},
			success:function(result){
				if(result.success==false){
                    layer.msg(result.message, {icon: 5, shift: 6});
				}else{
					var td = $(em).parent("samp").parent("td").parent("tr").children("td")[5];
					if(state=='INIT'){
						$(td).html('<font color="#e33b00">未支付</font>');
						$("#auditing"+orderId).html('<button onclick="auditing(\''+orderNo+'\',this)" class="ui-state-default ui-corner-all" type="button">开通</button>');
						$("#initcancel"+orderId).html('<button onclick="cancelOrRegain(\'CANCEL\','+orderId+',\''+orderNo+'\',this)" class="ui-state-default ui-corner-all" type="button">取消</button>');
					}else if(state=='CANCEL'){
						$(td).html('已取消');
						$("#auditing"+orderId).html('');
						$("#initcancel"+orderId).html('<button onclick="cancelOrRegain(\'INIT\','+orderId+',\''+orderNo+'\',this)" class="ui-state-default ui-corner-all" type="button">恢复</button>');
					}
				}
			},
			error:function(error){
                layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
			}
		});
	}
}

/**
 * 退款
 * @param id
 * @param em
 * @returns {boolean}
 */
function refund(id, em) {
	if(confirm("您确定要退款？退款后学员不能观看此视频！！")){
		var refundMoney=prompt("请输入退款金额：","0");
		if(refundMoney){
			//点击的是“确定”
			if(isNaN(refundMoney)){
                layer.msg("退款金额为数字类型", {icon: 5, shift: 6});
				return;
			}
			var description=prompt("请输入退款备注：");
			if(description||description === ""){
				$.ajax({
					url : "/admin/order/refund",
					data : {
						"order.orderId":id,
						"order.refundAmount":refundMoney,
						"order.description":description
					},
					dataType : "json",
					type : "post",
					success : function(result) {
						if (result.success == true) {
							var td = $(em).parent("td").parent("tr").children("td")[6];
							$(td).html('<font color="#e33b00">退款</font>');
                            layer.msg("退款成功", {icon: 1, shift: 6});
							$(em).remove();
						}else {
                            layer.msg(result.message, {icon: 5, shift: 6});
						}
					},
					error : function(error) {
                        layer.msg("error", {icon: 5, shift: 6});
					}
				});
			}

		}else if(refundMoney === ""){
			//用户没有输入内容点击的“确定”
            layer.msg("退款金额为空", {icon: 5, shift: 6});
		}else{
			//点击的是“取消”
		}
	}
}


/**
 * 订单列表导出
 */
function orderExcel(){
	$("#searchForm").prop("action","/admin/order/orderExport");
	$("#searchForm").submit();
	$("#searchForm").prop("action","/admin/order/showorderlist");
}