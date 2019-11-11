<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改资讯信息表</title>
<script type="text/javascript">
function formSubmit(){
	$("#updForm").submit();
}
</script>
</head>
<body>

	<fieldset class="layui-elem-field">
		<legend>
			<span>资讯信息表管理</span>
			&gt;
			<span>修改资讯信息表</span>
		</legend>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/qcloudSmsTemplate/update" method="post" id="updForm">
					<p>
						<span>
								资讯信息表基本属性
								<tt class="c_666 ml20 fsize12">
									（<font color="red">*</font>&nbsp;为必填项）
								</tt>
							</span>
						<span class="field_desc"></span>
					</p>
					<p>
						<label for="sf"><font color="red">*</font>类型template(模板) sign（前面）</label>
						<input type="text" name="qcloudSmsTemplate.type" class="{required:true} lf" id="type" value="${qcloudSmsTemplate.type }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label for="sf"><font color="red">*</font>内容</label>
						<input type="text" name="qcloudSmsTemplate.text" class="{required:true} lf" id="text" value="${qcloudSmsTemplate.text }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label for="sf"><font color="red">*</font>相关id</label>
						<input type="text" name="qcloudSmsTemplate.otherId" class="{required:true} lf" id="otherId" value="${qcloudSmsTemplate.otherId }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label for="sf"><font color="red">*</font>创建时间</label>
						<input type="text" name="qcloudSmsTemplate.createTime" class="{required:true} lf" id="createTime" value="${qcloudSmsTemplate.createTime }" />
						<span class="field_desc"></span>
					</p>
					<p>
						<input type="button" value="修 改" class="layui-btn layui-btn-small" onclick="formSubmit()" />
					</p>
				</form>
			</div>
		</div>
	</fieldset>
</body>
</html>
