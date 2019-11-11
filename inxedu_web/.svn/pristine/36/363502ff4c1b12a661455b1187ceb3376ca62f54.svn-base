<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改敏感词</title>
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

	<fieldset class="layui-elem-field">
		<legend>
			<span>敏感词管理</span>
			&gt;
			<span>修改敏感词</span>
		</legend>
		<!-- /tab4 begin -->
		<div class="numb-box">
			<div class="layui-field-box">
				<div class="commonWrap">
					<form action="${ctx}/admin/sensitiveWords/update" method="post" id="updForm">
						<input type="hidden" name="sensitiveWords.id" value="${sensitiveWords.id }" />
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">敏感词</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="sensitiveWords.sensitiveWord" value="${sensitiveWords.sensitiveWord }" id="sensitiveWord" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block mt20">
								<button class="layui-btn layui-btn-danger" onclick="formSubmit()">确定</button>
								<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</fieldset>
</body>
</html>
