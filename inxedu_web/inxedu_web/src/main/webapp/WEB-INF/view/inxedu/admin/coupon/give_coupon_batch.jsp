<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>批量发送优惠券</title>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-form.js"></script>
<script type="text/javascript">
	var couponCodeSize='${courseCodeList.size()}';//优惠券可用数

	function wordCount(obj,tipId){
		$(obj).keyup(function(){
			$("#"+tipId).text($.trim($(this).val()).length);
		});
	}
	/**
	 * 选择学员
	 **/
	function showNewwin(){
		window.open('${ctx}/admin/user/selectCouponUserList','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	}
	function addnewUserId(newTcIdArr){
		var tcIds = $("#tcIds").val().split(",");
		tcIds = tcIds.concat(newTcIdArr);
		tcIds = tcIds.unique();
		$("#tcIds").val(tcIds);
		queryuser();
	}

	function queryuser(){
		var ids = $("#tcIds").val();
		tcIds=ids.split(",");
		var count=tcIds.length;
		if(isEmpty(ids)){
			count=0;
		}
		$("#unuseCount").html(count);
		if(ids!=null&&ids!=""){
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${ctx}/admin/user/queryByIds",
				data : {
					"ids" : ids
				},
				async : false,
				success : function(result) {
					if (result.success == true) {
						var str = "";
						var list = result.entity;
						for(var i=0;i<list.length;i++){
							var tc = list[i];
							var nickname="匿名";
							if(isNotEmpty(tc.displayName)){
								nickname=tc.displayName;
							}
							str+='<p style="width:100%;margin: 0 0 0em">id:'+tc.userId+'&nbsp;&nbsp;昵称:'+nickname+'&nbsp;&nbsp;<a onclick="delUser('+tc.userId+')" class="layui-btn layui-btn-small" href="javascript:void(0)"> 删除</a></p>';
						}
						$("#tchtml").html(str);
					}
				}
			});
		}else{
			$("#tchtml").html("");
		}
	}
	/**
	 * 删除学员
	 **/
	function delUser(id){
		var tcIds = $("#tcIds").val();
		tcIds=","+tcIds+",";
		var pattern =","+id+",";
		tcIds = tcIds.replace(new RegExp(pattern), ",");
		tcIds = tcIds.split(",").unique();
		$("#tcIds").val(tcIds);
		queryuser();
	}

	function clearTeacher(){
		$("#tcIds").val("");
		$("#tchtml").html("");
		$("#unuseCount").html(0);
	}

	/**
	 * 赠送优惠券
	 * @param id
     */
	function giveCouponCode(id){
		if(!${isOk}){
            layer.msg("优惠券已过期！", {icon: 5, shift: 6});
			return;
		}
		if(couponCodeSize<=0){
            layer.msg("优惠券不足", {icon: 5, shift: 6});
			return ;
		}
		var tcIds = $("#tcIds").val();
		if(isEmpty(tcIds)){
            layer.msg("请选择学员！", {icon: 5, shift: 6});
			return;
		}
		var arrayIds=tcIds.split(",");
		var count=arrayIds.length;
		if(couponCodeSize<count){
            layer.msg("优惠券数量不足，学员必须小于等于优惠券数量！", {icon: 5, shift: 6});
			return;
		}
		$.ajax({
			url:"/admin/coupon/ajax/giveCouponCode",
			data:{
				"couponId":id,
				"userIds":tcIds
			},
			type:"post",
			dataType:"json",
			cache : false,
			async:false,
			success:function(result){
				if(result.success){
                    layer.msg(result.message, {icon: 1, shift: 6});
					window.location.href="";
				}else{
                    layer.msg(result.message, {icon: 5, shift: 6});
				}
			}
		});
	}
</script>

</head>
<body  >

<div class="numb-box"><fieldset class="layui-elem-field">
	<legend>
		<span>优惠券管理</span>
		&gt;
		<span>批量发送优惠券</span>
	</legend>
	<div class="">
			<input id="tcIds" type="hidden" name="userIds" value="" />
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01" style="line-height: 35px;">
					<tbody>
					<tr>
						<td align="center" width="10%">优惠券名称：</td>
						<td>
							${coupon.title}
						</td>
					</tr>
					<tr>
						<td align="center">优惠券数量：</td>
						<td>
							${courseCodeList.size()+givedCouponCodeList.size()}张
						</td>
					</tr>
					<tr>
						<td align="center">已赠送：</td>
						<td>
							${givedCouponCodeList.size()}张
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;已赠送学员</td>
						<td>
							<div id="" style="overflow-y: auto;height:auto">
								<c:forEach items="${givedCouponCodeList}" var="couponCode">
									<p style="width:100%;margin: 0 0 0em">用户ID:${couponCode.userId}&nbsp;&nbsp;昵称:${couponCode.showName}&nbsp;&nbsp;优惠券编码:${couponCode.couponCode}&nbsp;&nbsp;
										状态：<c:if test="${couponCode.status==1 }">未使用</c:if>
										<c:if test="${couponCode.status==2 }">已使用</c:if>
										<c:if test="${couponCode.status==3 }">过期</c:if>
										<c:if test="${couponCode.status==4 }">作废</c:if>
										<c:if test="${couponCode.status==6 }">冻结</c:if>
									</p>
								</c:forEach>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center">剩余：</td>
						<td>
							${courseCodeList.size()}张
						</td>
					</tr>
					<tr>
						<td align="center">已选择学员数：</td>
						<td>
							<em id="unuseCount" style="color: #ff0000;">0</em>个
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;选择学员</td>
						<td>
							<input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="showNewwin()" value="添加学员">
							<%--<input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="clearTeacher()" value="清空">--%>
							<div id="tchtml" style="overflow-y: auto;height:auto;"></div>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input onclick="giveCouponCode(${coupon.id})" class="layui-btn layui-btn-small" type="button" value="提 交" />
							<input onclick="history.go(-1);" class="layui-btn layui-btn-small" type="button" value="返回" />
						</td>
					</tr>
					</tbody>
				</table>
			</div>
	</div><!-- /commonWrap -->
<!-- /tab2 end-->
</fieldset></div>
</body>
</html>