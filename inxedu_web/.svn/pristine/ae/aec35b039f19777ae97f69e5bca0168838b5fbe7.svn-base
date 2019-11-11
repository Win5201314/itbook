<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>功能权限管理</title>
<link type="text/css" rel="stylesheet" href="${ctx}/static/common/ztree/css/zTreeStyle.css" />
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/system/sys-function.js"></script>

<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript">
	layui.use(['form', 'laydate'], function(){
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function(data){
		});
		//各种基于事件的操作，下面会有进一步介绍
	});
		var ztree='${jsonFunction}';
		$(function(){
			showFunctionZtree(ztree);
			$(".ui-dialog-titlebar-close,.closeBut").click(function(){
				closeData();
			});
			initSimpleImageUpload('imageFile','function',callback,false);

		});
		/**
		 * 图片上传回调
		 */
		function callback(imgUrl){
			$("input[name='sysFunction.imageUrl']").val(imgUrl);
			$("#showImage").attr('src',baselocation+imgUrl);
		}
</script>
</head>
<body>
	<fieldset class="layui-elem-field site-demo-button">
		<legend>
			<span>系统管理</span>
			&gt;
			<span>权限树</span>
		</legend>
		<div class="numb-box">
			<div id="ztreedemo" class="ztree"></div>
			<div>
				<input type="button" value="增加权限" onclick="addFunction();" class="layui-btn layui-btn-small layui-btn-danger" />
				<input type="button" value="删除选中权限" onclick="delFunctions();" class="layui-btn layui-btn-small layui-btn-danger" />
				<input type="button" onclick="checkNodeFalse();" value="清空选中" class="layui-btn layui-btn-small layui-btn-danger" />
				<font color="red">(*红字为功能权限，其他为菜单权限，拖拽可修改权限节点父级)</font>
			</div>
		</div>
	</fieldset>
	<!-- 修改权限信息窗口，开始 -->
	<div id="updateWin" style="display: none; ">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		</fieldset>
		<form class="layui-form" id="updateForm">
			<input type="hidden" id="functionId" name="sysFunction.functionId" />
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">权限名</label>
				<div class="layui-input-inline">
					<input  class="layui-input" id="functionName" name="sysFunction.functionName" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">权限URL</label>
				<div class="layui-input-inline">
					<input  class="layui-input" id="functionUrl" name="sysFunction.functionUrl" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">所属角色</label>
				<div class="layui-input-inline">
					<select id="functionType" name="sysFunction.functionType" lay-filter="select">
						<option value="1">菜单类型</option>
						<option value="2">功能类型</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">路径类型</label>
				<div class="layui-input-inline">
					<select id="urlType" name="sysFunction.urlType" lay-filter="select">
						<option value="1">网校</option>
						<option value="2">社区</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">排序</label>
				<div class="layui-input-inline">
					<input  class="layui-input" id="sort" type="text" value="0" name="sysFunction.sort">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">图标</label>
				<div class="layui-input-inline">
					<input id="imageUrl" type="hidden" value="" name="sysFunction.imageUrl" />
					<img id="showImage" width="20" height="20" src="${ctx}/static/admin/assets/logo.png" />
					<input type="button" value="上传" id="imageFile" />
					<font color="red">(请上传 20*20(长X宽)像素 的图片)</font>
				</div>
			</div>
		</form>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" onclick="updateFunction()">确定</button>
				<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
			</div>
		</div>
	</div>
	<!-- 修改权限信息窗口，结束 -->
</body>
</html>