<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改个人密码</title>
<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"/>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>
<script type="text/javascript">

	function submitform(){
		$.ajax({
			url:baselocation+'/admin/sysuser/user/updatepwd',
			type:'post',
			data:$("#submitform").serialize(),
			async:false,
			dataType:'json',
			success:function(result){
				if(!result.success){
					var entity = result.entity;
					if(entity=="oldPwdisnull"){
                        layer.msg("请输入旧密码", {icon: 5, shift: 6});
						return;
					}
					if(entity=="newPwdisnull"){
                        layer.msg("请输入新密码", {icon: 5, shift: 6});
						return;
					}
					if(entity=="confirmPwdisnull"){
                        layer.msg("请输入确认密码", {icon: 5, shift: 6});
						return;
					}
					if(entity=="newPwderror"){
                        layer.msg("请输入6到16位的新密码", {icon: 5, shift: 6});
						return;
					}
					if(entity=="newPwdNotEqualsconfirmPwd"){
                        layer.msg("确认密码和密码不相等", {icon: 5, shift: 6});
						return;
					}
					if(entity=="oldPwdIsError"){
                        layer.msg("旧密码不正确", {icon: 5, shift: 6});
						return;
					}
					if(entity=="newPwdEquestOldPwd"){
                        layer.msg("旧密码和新密码相等", {icon: 5, shift: 6});
						return;
					}

				}
                layer.msg("成功", {icon: 1, shift: 6});
			}
		});
	}
</script>
</head>
<body>

<div class="numb-box"><fieldset class="layui-elem-field">
	<legend>
		<span>个人管理</span>
		&gt;
		<span>修改密码</span>
	</legend>
	<div class="mt20">
		<form action="${ctx}/admin" method="post" id="submitform">
			<p>
				<label for="sf"><span>
							<tt class="c_666 ml20 fsize12">
								（<font color="red">*</font>&nbsp;为必填项）
							</tt>
						</span></label>
				<span class="field_desc"></span>
			</p>
			<p>
				<label for="sf"><font color="red">*</font>&nbsp;原密码:</label>
				<input type="password" name="oldPwd" id="oldPwd" class="{required:true} lf" data-rule="required;"/>
				<span class="field_desc"></span>
			</p>
			<p>
				<label for="sf"><font color="red">*</font>&nbsp;新密码：</label>
				<input type="password" name="newPwd" id="newPwd" class="{required:true,number:true,min:0,max:1000} lf" data-rule="required;"/>
				<span class="field_desc"></span>
			</p>
			<p>
				<label for="sf"><font color="red">*</font>&nbsp;确认密码：</label>
				<input type="password" name="confirmPwd" id="confirmPwd" class="{required:true} lf" data-rule="required;"/>
				<span class="field_desc"></span>
			</p>
			<p>
				<input type="button" value="提 交" class="layui-btn layui-btn-small" onclick="submitform()" />
				<%--<input type="button" value="返 回" class="layui-btn layui-btn-small" onclick="javascript:history.go(-1);" />--%>
			</p>
		</form>
	</div>
	<!-- /tab4 end -->
</fieldset></div>
</body>
</html>
