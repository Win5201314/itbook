<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加敏感词</title>
	<%--验证框架--%>
	<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"/>
	<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>
<script type="text/javascript">
function formSubmit(){
	$("#addForm").submit();
}
</script>
</head>
<body>

	<fieldset class="layui-elem-field">
		<legend>
			<span>敏感词管理</span>
			&gt;
			<span>添加敏感词</span>
		</legend>
		<!-- /tab4 begin -->
		<div class="layui-field-box">
				<form action="${ctx}/admin/sensitiveWords/add" method="post" id="addForm" class="layui-form">
					<div class="layui-form-item">
						<label class="layui-form-label layui-form-label-w">敏感词</label>
						<div class="layui-input-inline">
							<input class="layui-input" name="sensitiveWords.sensitiveWord" id="sensitiveWord" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" onclick="formSubmit()">确定</button>
							<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</fieldset>
</body>
</html>
