var ztreeObject;
var setting = {
	check: {
		enable: true,
		chkboxType : {"Y" : "s", "N" : "s"}
	},
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		fontCss: function(treeId, treeNode){
			return treeNode.functionType==2?{'color':'red'}:{};
		}
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'functionId',
			pIdKey:'parentId',
			rootPid:''
		},
		key:{
			name:'functionName',
			title:'functionName'
		}
	}
};

/**
 * 改变选择角色，勾选对应权限
 * @param roleId 角色ID
 */
function onchangeRole(roleId){
	ztreeObject = $.fn.zTree.getZTreeObj("ztreedemo");
	ztreeObject.checkAllNodes(false);
	if(roleId>0 && roleId!=''){
		$.ajax({
			url:baselocation+'/admin/sysrole/changesrole/'+roleId,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==true){
					var list = result.entity;
					if(list!=null && list.length>0){
						$.each(list,function(index,val){  
							var treeNode = ztreeObject.getNodeByParam("functionId", val, null);
							ztreeObject.checkNode(treeNode, true, false, false)
					  	});
					}
				}
			}
		});
	}
}

/**
 * 删除角色
 */
function delRole(){
	var $role=$("#roleId");
	var roleId = $role.val();
	if(roleId<=0 || roleId==''){
        layer.msg("请选择要删除的角色", {icon: 5, shift: 6});
		return false;
	}
	if(confirm('确认要删除角色'+$role.find('option:selected').text()+'？')){
		$.ajax({
			url:baselocation+'/admin/sysrole/deleterole/'+roleId,
			type:'post',
			dataType:'json',
			success:function(result){
				$("#roleId>option:selected").remove();
				$("#roleId").val(0);
				ztreeObject = $.fn.zTree.getZTreeObj("ztreedemo");
				ztreeObject.checkAllNodes(false);
				layer.msg(result.message);
				window.setTimeout('window.location.reload()',1000);
			}
		});
	}
}

/**
 * 保存角色的权限
 */
function saveRoleFunction(){
	var roleId = $("#roleId").val();
	if(roleId<=0 || roleId==''){
        layer.msg("请选择角色", {icon: 5, shift: 6});
		return false;
	}
	ztreeObject = $.fn.zTree.getZTreeObj("ztreedemo");
	var nodes = ztreeObject.getCheckedNodes(true);
	var functionIds='';
	if(nodes!=null && nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			functionIds+=nodes[i].functionId+',';
		}
	}
	$.ajax({
		url:baselocation+'/admin/sysrole/saveroelfunction/'+roleId,
		type:'post',
		dataType:'json',
		data:{'functionIds':functionIds},
		success:function(result){
            layer.msg(result.message+",刷新即可重新加载菜单权限", {icon: 1, shift: 6});
		}
	});
}

/**初始化权限树*/
function initFunction(treedata){
	treedata = eval('('+treedata+')');
	ztreeObject = $.fn.zTree.init($("#ztreedemo"), setting, treedata);
}

/**
 * 修改或创建角色 1创建 2修改
 */
function saveRole(type){
	var roleName = $("#roleName").val();
	if(roleName==null || $.trim(roleName)==''){
        layer.msg("请输入角色名！", {icon: 5, shift: 6});
		return false;
	}
	var roleId = $("#roleId").val();
	if(type==1){
		roleId=0;
	}
	$.ajax({
		url:baselocation+'/admin/sysrole/editrole/'+type,
		type:'post',
		dataType:'json',
		data:{'sysRole.roleName':roleName,'sysRole.roleId':roleId},
		success:function(result){
			if(result.success==true){
				var obj = result.entity;
				if(type==1){
					$("#roleName").val('');
					$("#roleId").append('<option value="'+obj.roleId+'">'+obj.roleName+'</option>');
				}else if(type==2){
					$("#roleId>option:selected").text(obj.roleName);
				}
				layer.closeAll(); //疯狂模式，关闭所有层
				layer.msg(result.message);
				window.setTimeout('window.location.reload()',1000);
			}else
				layer.msg(result.message);
		},
		error:function(error){
			layer.msg("系统繁忙，请稍后再操作！");
		}
	});
}
/**
 * 显示编辑窗口
 */
function editRole(type) {
	if(type==2){
		if($("#roleId").val()<=0){
            layer.msg("请选择要修改的角色！", {icon: 5, shift: 6});
			return false;
		}
		var roleName = $("#roleId>option:selected").text();
		$("#roleName").val(roleName);
	}
    $("#save-btn").attr('onclick','saveRole('+type+')');

	layer.open({
		title:'修改角色',
		type: 1,
		shade: false,
		area: '500px',
		maxmin: false,
		content: $("#addRole")
	});
}