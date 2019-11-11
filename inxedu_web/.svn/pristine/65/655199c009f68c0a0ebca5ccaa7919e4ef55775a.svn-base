<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>数据清理添加</title>
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
		<span>新建</span>
	</legend>
	<div class="numb-box">
		<div class="layui-field-box">
			<form action="${ctx}/admin/dataclean/add" method="post" id="saveForm" class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;名称</label>
					<div class="layui-input-block">
						<input name="name" type="text" lay-verify="required" autocomplete="off" class="layui-input layui-input-6"  />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;描述</label>
					<div class="layui-input-block">
						<input name="desc" type="text"  lay-verify="required" autocomplete="off" class="layui-input layui-input-6"/>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;执行sql</label>
					<div class="layui-input-block layui-textarea-block">
						<textarea placeholder="请输入内容" name="sql" lay-verify="required" autocomplete="off" class="layui-textarea"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-danger" lay-submit lay-filter="*">提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</fieldset>
</body>
</html>
