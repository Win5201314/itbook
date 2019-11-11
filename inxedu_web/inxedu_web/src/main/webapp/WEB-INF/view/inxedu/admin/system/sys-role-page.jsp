<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>角色列表</title>
<link type="text/css" rel="stylesheet" href="${ctx}/static/common/ztree/css/zTreeStyle.css" />
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/system/sys-role.js"></script>
<script type="text/javascript">
	layui.use(['form', 'laydate'], function(){
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(roleSelect)', function(data){
			onchangeRole(data.value);//事件所监听
		});
		//各种基于事件的操作，下面会有进一步介绍
	});

	var treedata='${functionList}';
	$(function(){
		initFunction(treedata);
	});
</script>
</head>
<body>
	<fieldset class="layui-elem-field">
		<legend>
			<span>系统管理</span>
			&gt;
			<span>角色管理</span>
		</legend>
		<div class="numb-box">
			<div class="layui-field-box">
				<form action="" method="post" id="updateRoleFunctionForm" class="layui-form" onsubmit="return updateRole();">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label layui-form-label-w">所属角色</label>
							<div class="layui-input-inline">
								<select id="roleId" onchange="onchangeRole(this.value);" lay-filter="roleSelect">
									<option value="0">请选择角色</option>
									<c:forEach items="${roleList}" var="roleList">
										<option value="${roleList.roleId}">${roleList.roleName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<div id="ztreedemo" class="ztree"></div>
						</div>
					</div>
					<div class="layui-form-item site-demo-button">
						<input type="button" value="修改角色名称" onclick="editRole(2)" class="layui-btn layui-btn-small layui-btn-danger" />
						<input type="button" value="添加角色" onclick="editRole(1)" class="layui-btn layui-btn-small layui-btn-danger" />
						<input type="button" value="保存" onclick="saveRoleFunction()" class="layui-btn layui-btn-small layui-btn-danger" />
						<input type="button" value="删除选中角色" onclick="delRole();" class="layui-btn layui-btn-small layui-btn-danger" />
						<font color="red">(*红字为功能权限，其他为菜单权限)</font>
					</div>
				</form>
			</div>
		</div>
	</fieldset>

	<div id="addRole" style="display: none; ">
		<div class="numb-box">
			<form class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">新角色名称</label>
					<div class="layui-input-inline">
						<input  class="layui-input" id="roleName" type="text">
					</div>
				</div>
			</form>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" id="save-btn" onclick="saveRole()">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加角色 -->
</body>
</html>
