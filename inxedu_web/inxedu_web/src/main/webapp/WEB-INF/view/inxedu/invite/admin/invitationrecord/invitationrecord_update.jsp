<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改edu_invitation_record</title>
	<%--验证框架--%>
	<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"/>
	<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>
<script type="text/javascript">
function formSubmit(){
	$("#updForm").submit();
}
</script>
</head>
<body>

	<fieldset>
		<legend>
			<span>edu_invitation_record管理</span>
			&gt;
			<span>修改edu_invitation_record</span>
		</legend>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/invitationRecord/update" method="post" id="updForm">
							<input type="hidden" name="invitationRecord.id" value="${invitationRecord.id }" />
					<p>
						<span>
								edu_invitation_record基本属性
								<tt class="c_666 ml20 fsize12">
									（<font color="red">*</font>&nbsp;为必填项）
								</tt>
							</span>
						<span class="field_desc"></span>
					</p>
					<p>
						<label ><font color="red">*</font>邀请人的用户id</label>
						<input type="text" name="invitationRecord.userId" class="lf" data-rule="required;" id="userId" value="${invitationRecord.userId }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label ><font color="red">*</font>被邀请人用户id</label>
						<input type="text" name="invitationRecord.invitationUserId" class="lf" data-rule="required;" id="invitationUserId" value="${invitationRecord.invitationUserId }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label ><font color="red">*</font>返现金额</label>
						<input type="text" name="invitationRecord.cashback" class="lf" data-rule="required;" id="cashback" value="${invitationRecord.cashback }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label ><font color="red">*</font>添加时间</label>
						<input type="text" name="invitationRecord.addTime" class="lf" data-rule="required;" id="addTime" value="${invitationRecord.addTime }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<input type="button" value="修 改" class="button" onclick="formSubmit()" />
						<input type="button" value="返 回" class="button" onclick="history.go(-1);" />
					</p>
				</form>
			</div>
		</div>
	</fieldset>
</body>
</html>
