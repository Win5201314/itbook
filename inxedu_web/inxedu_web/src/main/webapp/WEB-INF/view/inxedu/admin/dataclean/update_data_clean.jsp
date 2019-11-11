<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>数据清理更新</title>
<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
<%--验证框架--%>
<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"/>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>
<script type="text/javascript">
	var form;
	layui.use(['form', 'laydate'], function(){
		form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function(data){
		});
		form.on('submit(*)', function(data){
			$("#saveForm").submit();
		});
	});
</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>数据管理</span>
		&gt;
		<span>修改</span>
	</legend>
	<div class="numb-box">
		<form action="${ctx}/admin/dataclean/update" method="post" id="saveForm" data-validator-option="{stopOnError:false, timely:false}" class="layui-form">
			<input type="hidden" name="id" value="${dataClean.id}" />
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;名称</label>
				<div class="layui-input-block">
					<input name="name" type="text" value="${dataClean.name}" lay-verify="required" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;描述</label>
				<div class="layui-input-block">
					<input name="desc" type="text" value="${dataClean.desc}" lay-verify="required" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;执行sql</label>
				<div class="layui-input-block layui-textarea-block">
					<textarea placeholder="请输入内容" name="sql" lay-verify="required" class="layui-textarea">${dataClean.sql}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-danger"  lay-submit lay-filter="*">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回</button>
				</div>
			</div>
		</form>
	</div>
</fieldset>
</body>
</html>
