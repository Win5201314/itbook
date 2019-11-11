<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
    <%--<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js?v=${v}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css?v=${v}" />--%>
    <%--百度编译器--%>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript">
	layui.use(['form', 'laydate'], function(){
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function(data){
		});
		form.on('submit(*)', function(data){
			updateSubmit();
		});
	});

    var editor;
$(function(){
	$("#menuName").keyup(function(){
		$("#menuNameCount").text($.trim($(this).val()).length);
	});
	//初始化 Kindeditor
    initKindEditor_addblog('content', '80%', 350,'helpcontent','true');
});
				

function updateSubmit(){
	var parentId='${helpMenu.parentId}';
	if(parentId>0){
		if($("#menuOne").val()==0){
            layer.msg("请选择一级菜单", {icon: 5, shift: 6});
			return;
		}
        if(!UE.getEditor('content').hasContents()){// hasContents()  检查有内容返回true，否则返回false
            layer.msg("请填写帮助内容", {icon: 5, shift: 6});
            return;
        }
	}
	$("#updateForm").submit();
}
</script>
</head>
<body>
<div class="numb-box"><fieldset class="layui-elem-field">
	<legend>
		<a href="${ctx}/admin/helpMenu/list">
			<span>帮助菜单</span>
		</a>
		&gt;
		<span>修改</span>
	</legend>
	<div class="numb-box">
		<p class="hLh20 fsize14 c-red f-fM"><font color="red">*</font>&nbsp;为必填项</p>
			<form action="${ctx}/admin/helpMenu/update" method="post" id="updateForm"  class="layui-form">
				<input type="hidden" value="${helpMenu.id}" name="helpMenu.id">
				<div class="layui-form-item" style="display: none">
					<label class="layui-form-label layui-form-label-w">选择一级菜单</label>
					<div class="layui-input-inline">
						<select id="menuOne" class="layui-input" name="helpMenu.parentId">
							<option value="0">--请选择--</option>
							<c:forEach items="${helpMenus}" var="helpMenuOne">
								<option value="${helpMenuOne.id}" <c:if test="${helpMenuOne.id==helpMenu.parentId}">selected="selected"</c:if>>${helpMenuOne.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;菜单名称</label>
					<div class="layui-input-block">
						<input id="menuName" type="text" name="helpMenu.name" maxlength="9" value="${helpMenu.name}" class="layui-input layui-input-6 fl" lay-verify="required"/>
						<%--<div class="layui-form-mid fl fsize14 c-666 f-fM ml20 hLh30">
							<em id="menuNameCount" class="fsize14 c-red f-fM">0</em>/9
						</div>--%>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">帮助内容</label>
					<div class="layui-input-block">
						<textarea name="helpMenu.content" id="content" data-rule="required;">${helpMenu.content}</textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;排序：</label>
					<div class="layui-input-block">
						<input id="sort" type="number" name="helpMenu.sort" value="${helpMenu.sort}" lay-verify="number" class="layui-input layui-input-6"/>
						<p class="fsize12 c-red f-fM hLh20">倒序</p>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">外链：</label>
					<div class="layui-input-block">
						<input id="linkBuilding" type="text" class="layui-input layui-input-6" name="helpMenu.linkBuilding" value="${helpMenu.linkBuilding }" />
						<p class="fsize12 c-red f-fM hLh20">(选填)外链为空则显示帮助内容，不为空则跳转到外链!</p>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block mt20">
						<button class="layui-btn layui-btn-danger" lay-submit lay-filter="*">提 交</button>
						<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返 回</button>
					</div>

				</div>

			</form>
		</div>
	</fieldset>
</div>
</body>
</html>