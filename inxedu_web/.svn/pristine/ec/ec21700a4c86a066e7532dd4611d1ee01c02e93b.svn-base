<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>查看优惠券编码</title>
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
</script>
</head>
<body >
<div class="numb-box"><fieldset class="layui-elem-field">
	<legend>
		<span>优惠券编码管理</span>
		&gt;
		<span>查看优惠券编码</span>
	</legend>
		<!-- /tab4 begin -->
		<div class="">
		<div class="commonWrap">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01" style="line-height: 35px;">
				<tbody>
					<tr>
						<td align="center" width="10%"><font color="red">*</font>&nbsp;优惠券编码 </td>
						<td>
							${couponCode.couponCode}
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券名称 </td>
						<td>
							${couponDTO.title}
						</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>类型</td>
						<td width="80%">
							<c:if test="${couponDTO.type==1 }">折扣券</c:if>
							<c:if test="${couponDTO.type==2 }">定额券</c:if>
							<c:if test="${couponDTO.type==3 }">会员定额券</c:if>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;次数限制</td>
						<td>
							<c:if test="${couponDTO.useType==1 }">无限</c:if>
							<c:if test="${couponDTO.useType==2 }">正常</c:if>&nbsp;&nbsp;<font style="color:red">'无限'优惠券在有效期内可以被任意多人使用多次，'正常'优惠券使用一次后不能继续使用</font>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;状态</td>
						<td>
							<c:if test="${couponCode.status==1 }">未使用</c:if>
							<c:if test="${couponCode.status==2 }">已使用</c:if>
							<c:if test="${couponCode.status==3 }">过期</c:if>
							<c:if test="${couponCode.status==4 }">作废</c:if>
						</td>
					</tr>
					<c:if test="${couponCode.status==2 }">
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单编号</td>
							<td>
								${couponCode.requestId }
							</td>
						</tr>
					</c:if>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用限额</td>
						<td>
							${couponDTO.limitAmount}元以上&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red">只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入“300”</font>
						</td>
					</tr>
					<tr id="preferentialTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠折扣</td>
						<td>
							${couponDTO.amount}折&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red">该优惠券折扣额，如2折优惠券，则选择“2.0”</font>
						</td>
					</tr>
					<tr id="reduceTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠金额</td>
						<td>
							${couponDTO.amount}
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;适用范围</td>
						<td>
							<c:choose>
								<c:when test="${couponDTO.subjectName!=null&&couponDTO.subjectName!=''}">
									单选项目
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
						<td align="center" colspan="2"><input onclick="history.go(-1);" class="layui-btn layui-btn-small" type="button" value="返回" /></td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		<!-- /tab4 end -->
	</div>
</fieldset></div>
</body>
</html>
