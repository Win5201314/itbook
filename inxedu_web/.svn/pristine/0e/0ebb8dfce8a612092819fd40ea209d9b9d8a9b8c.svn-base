<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>查看优惠券</title>
<script type="text/javascript">
	var type='${couponDTO.type}';
	$(function(){
		changeType(type);//初始化优惠券类型
	});
	function changeType(val){
		if(val==1){
			$("#reduceTr").css("display","none");
			$("#preferentialTr").css("display","");
		}else{
			$("#preferentialTr").css("display","none");
			$("#reduceTr").css("display","");
		}
	}
	function daochuBianMa(id){
   		var codeSize='${couponDTO.couponCodes.size()}';
	   	if(codeSize>0){
	   		window.location.href="${ctx}/admin/coupon/exportcode?queryCouponCode.couponId="+id;
	   		return true;
	   	}else{
            layer.msg("此优惠券无优惠编码，不能进行导出！", {icon: 5, shift: 6});
	   		return false;
	   	}
   	}
	function wasteBianMa(id){
   		var codeSize='${couponDTO.couponCodes.size()}';
	   	if(codeSize>0){
	   		$.ajax({
	   			url:"${ctx}/admin/coupon/wastecoupon/"+id,
	   			type:"post",
	   			dataType:"json",
	   			success:function(result){
	   				if(result.message=='true'){
                        layer.msg("作废成功", {icon: 1, shift: 6});
	   				}
	   			}
	   		});
	   		
	   	}else{
            layer.msg("此优惠券无优惠编码，不能进行作废！", {icon: 5, shift: 6});
	   	}
   	}
</script>
</head>
<body >
<div class="numb-box"><fieldset class="layui-elem-field">
	<legend>
		<span>优惠券管理</span>
		&gt;
		<span>查看优惠券</span>
	</legend>
		<!-- /tab4 begin -->
		<div class="">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01" style="line-height: 35px;">
				<tbody>
					<tr>
						<td align="center" width="10%"><font color="red">*</font>&nbsp;优惠券名称 </td>
						<td>${couponDTO.title}</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券类型</td>
						<td>
							<c:if test="${couponDTO.type==1 }">折扣券</c:if>
							<c:if test="${couponDTO.type==2 }">定额券</c:if>
							<c:if test="${couponDTO.type==3 }">会员定额券</c:if>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;次数限制</td>
						<td>
							<c:if test="${couponDTO.useType==1 }">无限</c:if>
							<c:if test="${couponDTO.useType==2 }">正常</c:if>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用限额</td>
						<td>${couponDTO.limitAmount}元以上
						</td>
					</tr>
					<tr id="preferentialTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠折扣</td>
						<td>${couponDTO.amount}折</td>
					</tr>
					<tr id="reduceTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠金额</td>
						<td>${couponDTO.amount}元</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;适用范围</td>
						<td>
							<c:choose>
								<c:when test="${couponDTO.subjectName!=null&&couponDTO.subjectName!=''}">
									单选专业
								</c:when>
								<c:when test="${couponDTO.courses!=null&&couponDTO.courses.size()>0}">
									多选课程
								</c:when>
								<c:otherwise>
									所有课程
								</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
					<c:if test="${couponDTO.subjectName!=null&&couponDTO.subjectName!=''}">
					<tr id="limitSubject">
						<td align="center">&nbsp;</td>
						<td>
							${couponDTO.subjectName}
						</td>
					</tr>
					</c:if>
					<c:if test="${couponDTO.courses!=null&&couponDTO.courses.size()>0}">
					<tr id="limitCourse">
						<td align="center">&nbsp;</td>
						<td>
							<c:forEach items="${couponDTO.courses}" var="course">
								${course.courseName}<br/>
							</c:forEach>
						</td>
					</tr>
					</c:if>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用起始日期</td>
						<td>
							<fmt:formatDate value="${couponDTO.startTime}" type="both" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用截止日期</td>
						<td>
							<fmt:formatDate value="${couponDTO.endTime}" type="both" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td align="center">&nbsp;发放类型</td>
						<td>
							<c:choose>
								<c:when test="${couponDTO.giveType==2}">分享注册赠送</c:when>
								<c:when test="${couponDTO.giveType==3}">购买课程赠送</c:when>
								<c:otherwise>普通发放</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;生成优惠编码数量</td>
						<td>
							<c:choose>
								<c:when test="${couponDTO.giveType==1}">${couponDTO.totalNum}/${couponDTO.userNum}</c:when>
								<c:otherwise>----</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="center">备注</td>
						<td>
							${couponDTO.info}
						</td>
					</tr>
					<c:if test="${couponDTO.couponCodes!=null&&couponDTO.couponCodes.size()>0}">
						<tr>
							<td align="center" id="intro">优惠券编码</td>
							<td>
								<textarea id="couponCode" style="font-size: 9;height: 86px;" readonly="readonly" rows="8" cols="35"></textarea>
								<input type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="daochuBianMa(${couponDTO.id})" value="导出优惠码">
								<input type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="wasteBianMa(${couponDTO.id})" value="作废优惠码">
								<input type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${cxt}/admin/coupon/giveCouponBatch/${couponDTO.id}'" value="批量赠送">
								<script type="text/javascript">
									 var textareaHtml="";
									 <c:forEach items="${couponDTO.couponCodes}" var="codeString" varStatus="status">
			                             textareaHtml=textareaHtml+'${status.count}、'+'${codeString}'+'\n';
			                         </c:forEach>
		                            $("#couponCode").html(textareaHtml);
								</script>
							</td>
						</tr>
					</c:if>
					<tr>
						<td align="center" colspan="2"><input type="button" class="layui-btn layui-btn-small" value="返回" name="" onclick="history.go(-1)"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- /tab4 end -->
</fieldset></div>
</body>
</html>
