<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建图片</title>
<link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/website/images.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/bigcolorpicker/jquery.bigcolorpicker.css" />
<script type="text/javascript" src="${ctx}/static/common/bigcolorpicker/jquery.bigcolorpicker.js"></script>
<script>
	layui.use(['form', 'laydate'], function () {
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(select)', function (data) {
			checkImgWH(data.elem)
		});
		form.on('submit(*)', function(data){
			//执行提交方法
			saveImage()
		});
		//各种基于事件的操作，下面会有进一步介绍
	});

	$(function() {
		$("#imageColor").bigColorpicker("imageColor", "L", 10);
	});
</script>
	<style type="text/css">
		.ke-button-common {border-radius: 4px;height: 26px;}
		.ke-button {background: #d44920;color: #fff;font-size: 14px;line-height: 18px;}
	</style>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>广告图</span>
		&gt;
		<span>添加</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/website/addImages" method="post" id="saveImagesForm" class="layui-form">
			<input type="hidden" name="websiteImages.imagesUrl" />
			<input type="hidden" name="websiteImages.previewUrl" />
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;图片标题</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.title" lay-verify="required" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">图片描述</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.describe" type="text">
					<p class="fsize12 c-red f-fM hLh20">(仅后台描述前台没有展示效果除个别分类,如首页课程互动则会用到)</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;图片类型</label>
				<div class="layui-input-block layui-select-inline">
					<select id="imgType" name="websiteImages.typeId" lay-filter="select" lay-verify="required">
						<%--<option value="0">请选类型</option>--%>
						<c:forEach items="${typeList}" var="type">
							<option onclick="htmlImgSize('${type.typeId}')" value="${type.typeId}">${type.typeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">跳转链接</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.linkAddress" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;排序</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.seriesNumber" lay-verify="number" type="number">
					<p class="fsize12 c-red f-fM hLh20">（降序）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">图片</label>
				<div class="layui-input-block">
					<img id="imagesUrl" width="400px" height="210px" src="${ctx }/static/admin/assets/logo.png">
					<input type="button" value="上传" id="imageFile" /><font class="htmlImgSize c-red">(请上传 1920*460(长X宽)像素 的图片)</font>
				</div>
			</div>
			<div class="layui-form-item"  style="display: none;">
				<label class="layui-form-label layui-form-label-w">略缩图片</label>
				<div class="layui-input-block">
					<img id="previewUrl" width="200px" height="120px" src="${ctx }/static/admin/assets/logo.png">
					<input type="button" value="上传" id="previewFile" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">背景色</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" id="imageColor" name="websiteImages.color" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-danger" lay-submit lay-filter="*">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回</button>
				</div>
			</div>
		</form>
	</div>
</fieldset>
</body>
</html>