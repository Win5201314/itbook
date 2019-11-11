<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改图片</title>
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
			saveImage();
		});
		//各种基于事件的操作，下面会有进一步介绍
	});
$(function(){
	$("#imageColor").bigColorpicker("imageColor","L",10);
});
/*选择不同类型图片提示上传不同尺寸图片*/
function htmlImgSize(type) {
	if (type==1||type==16||type==17){
		$(".htmlImgSize").html("(请上传 1920*460(长X宽)像素 的图片)")
	}else if (type==11){
		$(".htmlImgSize").html("(请上传 140*140(长X宽)像素 的图片)")
	}else if (type==18||type==20){
		$(".htmlImgSize").html("(请上传 1920*400(长X宽)像素 的图片)")
	}else if (type==19){
		$(".htmlImgSize").html("(请上传 1920*456(长X宽)像素 的图片)")
	}
}
</script>

</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<a href="${ctx}/admin/website/imagesPage">
			<span>广告图</span>
		</a>
		&gt;
		<span>修改</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/website/updateImages" method="post" id="saveImagesForm" class="layui-form">
			<input type="hidden" name="websiteImages.imageId" value="${websiteImages.imageId}">
			<input type="hidden" name="websiteImages.imagesUrl" value="${websiteImages.imagesUrl}">
			<input type="hidden" name="websiteImages.previewUrl" value="${websiteImages.previewUrl}">
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;图片标题</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.title" value="${websiteImages.title}" lay-verify="required" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">图片描述</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.describe" value="${websiteImages.describe }" type="text">
					<p class="fsize12 c-red f-fM hLh20">(仅后台描述前台没有展示效果除个别分类,如首页课程互动则会用到)</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;图片类型</label>
				<div class="layui-input-inline layui-select-inline">
					<div class="layui-unselect layui-form-select">
						<select id="imgType" name="websiteImages.typeId" lay-filter="select">
							<option value="0">请选类型</option>
							<c:forEach items="${typeList}" var="type">
								<option onclick="htmlImgSize('${type.typeId}')" value="${type.typeId}" <c:if test="${websiteImages.typeId==type.typeId}">selected</c:if>>${type.typeName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">跳转链接</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.linkAddress" value="${websiteImages.linkAddress}" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;排序</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" name="websiteImages.seriesNumber" value="${websiteImages.seriesNumber}" lay-verify="number" type="number">
					<p class="fsize12 c-red f-fM hLh20">（降序）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">图片</label>
				<div class="layui-input-block">
					<c:choose>
						<c:when test="${websiteImages.imagesUrl==null || websiteImages.imagesUrl==''}">
							<img id="imagesUrl" width="400px" height="210px" src="${ctx }/static/admin/assets/logo.png">
						</c:when>
						<c:otherwise>
							<img id="imagesUrl" width="400px" height="210px" src="${staticServer}${websiteImages.imagesUrl}">
						</c:otherwise>
					</c:choose>
					<input type="button" value="上传" id="imageFile" />
					<font class="htmlImgSize c-red">(请上传 1920*460(长X宽)像素 的图片)</font>
				</div>
			</div>
			<div class="layui-form-item"  style="display: none;">
				<label class="layui-form-label layui-form-label-w">略缩图片</label>
				<div class="layui-input-block">
					<c:choose>
						<c:when test="${websiteImages.previewUrl==null || websiteImages.previewUrl==''}">
							<img id="previewUrl" width="200px" height="120px" src="${ctx }/static/admin/assets/logo.png">
						</c:when>
						<c:otherwise>
							<img id="previewUrl" width="200px" height="120px" src="${staticServer}${websiteImages.previewUrl}">
						</c:otherwise>
					</c:choose>
					<input type="button" value="上传" id="previewFile" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">背景色</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" id="imageColor" name="websiteImages.color"  value="${websiteImages.color}" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn"  lay-submit lay-filter="*">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回</button>
				</div>
			</div>
		</form>
	</div>
</fieldset>
</body>
</html>