<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>在线咨询</title>
<link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript">
$(function(){
	initSimpleImageUpload('fileupload','online',callback);
});
function callback(imgUrl){
	$("#onlinepic").attr("src","${staticServer}"+imgUrl);
	$("#imageUrl").val(imgUrl);	
}
function doSubmit(){
	if($("#link").val()==null||$("#link").val()==''){
        layer.msg("请填写咨询链接", {icon: 5, shift: 6});
		return;
	}
	if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
        layer.msg("请上传二维码", {icon: 5, shift: 6});
		return ;
	 }
	$("#addOnlineForm").submit();
}
</script>
</head>
<body>

	<div class="numb-box"><fieldset class="layui-elem-field">
		<legend>
			<span>在线咨询管理</span>
			&gt;
			<span>在线咨询</span>
		</legend>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/websiteProfile/online/update" method="post" id="addOnlineForm">
					<input type="hidden" name="onlineImageUrl" id="imageUrl" value="${websiteonlinemap.online.onlineImageUrl}" />
					<p>
						<span>
								在线咨询基本属性
							</span></label>
						<label for="sf"><span>
								<tt class="c_666 ml20 fsize12">
									（<font color="red">*</font>&nbsp;为必填项）
								</tt>
							</span>
						<span class="field_desc"></span>
					</p>
					<p>
						<label for="sf"><font color="red">*</font>咨询链接</label>
						<input type="text" name="onlineUrl" class="{required:true} mf" id="link" value="${websiteonlinemap.online.onlineUrl}" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label for="sf"><font color="red">*</font>&nbsp;二维码</label>
						<img src="${staticServer}${websiteonlinemap.online.onlineImageUrl}" alt="" width="100px" height="100px" id="onlinepic" />
						<span id="fileQueue" style="margin-top: 30px; border: 0px"></span>
						<span style="border: 0px; padding-top: 2px; padding-left: 2px;">
							<input type="button" value="上传" id="fileupload" />
							<br />&nbsp;<font color="red">*图片链接，支持JPG、PNG格式，尺寸（100*100像素）小于512kb</font>
						</span>
					</p>
					<p>
						<input type="button" value="修改" class="layui-btn layui-btn-small" onclick="doSubmit()" />
						<%--<input type="button" value="返回" class="layui-btn layui-btn-small" onclick="history.go(-1);" />--%>
					</p>
				</form>
			</div>
		</div>
	</fieldset></div>
</body>
</html>
