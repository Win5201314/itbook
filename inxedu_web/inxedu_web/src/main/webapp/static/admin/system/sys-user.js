var form;
$(function(){
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
	layui.use(['form', 'laydate'], function(){
	 form = layui.form();
	 var laydate = layui.laydate;
	 form.on('select(filter)', function(data){
	 });
	 //各种基于事件的操作，下面会有进一步介绍
	 });
});

/** 
 * 显示添加窗口 
 */
function showWin(){
	//通过这种方式弹出的层，每当它被选择，就会置顶。
	layer.open({
		title:'创建用户',
		type: 1,
		shade: false,
		area: '500px',
		maxmin: false,
		content: $('#createWin')
	});
}

/**
 * 点击查找用户
 */
function searchUser(){
	$("#searchForm").submit();
}

/**
 * 禁用或启用后台用户
 * @param userId 用户ID
 * @param type 1启用 2禁用
 * @param em 
 */
function disableOrstart(userId,type,em){
	closedData();
	$.ajax({
		url:baselocation+'/admin/sysuser/disableOrstart/'+userId+'/'+type,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==true){
				var td = $(em).parents('td').parent('tr').children('td')[7];
				if(type==1){
					$(td).text('正常');
					$(em).parent('span').html('<button onclick="disableOrstart('+userId+',2,this)" class="layui-btn layui-btn-small" type="button">冻结</button>');
				}else if(type==2){
					$(td).text('冻结');
					$(em).parent('span').html('<button onclick="disableOrstart('+userId+',1,this)" class="layui-btn layui-btn-small" type="button">启用</button>');
				}
			}else{
                layer.msg(result.message, {icon: 5, shift: 6});
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作", {icon: 5, shift: 6});
		}
	});
}

/**
 * 显示修改密码窗口
 */
function updatePwd(userId){
	//layer.closeAll(); //疯狂模式，关闭所有层
	$("#updatePwdWin #sysUserId").val(userId);
	//通过这种方式弹出的层，每当它被选择，就会置顶。
	layer.open({
		title:'修改密码',
		type: 1,
		shade: false,
		area: '500px',
		maxmin: false,
		content: $('#updatePwdWin')
	});
}

/**
 * 提交修改密码
 */
function submitUpdatePwd(){
	var passArr = $("#updatePwdWin input:password");
	if(passArr[0].value==null ||$.trim(passArr[0].value)==''){
        layer.msg("请输入密码！", {icon: 5, shift: 6});
		return false;
	}else if (passArr[1].value==null || $.trim(passArr[1].value)==''){
        layer.msg("请输入确认密码！", {icon: 5, shift: 6});
		return false;
	}
	if(passArr[0].value!=passArr[1].value){
        layer.msg("两次密码不一致！", {icon: 5, shift: 6});
		return false;
	}
	var userId = $("#updatePwdWin #sysUserId").val();
	$.ajax({
		url:baselocation+'/admin/sysuser/updatepwd/'+userId,
		type:'post',
		dataType:'json',
		data:{'newPwd':passArr[0].value},
		success:function(result){
			if(result.success==true){
				layer.closeAll(); //疯狂模式，关闭所有层
			}
			layer.msg(result.message);
		},
		error:function(error){
            layer.msg("两次密码不一致！", {icon: 5, shift: 6});
		}
	});
}

/**
 * 执行修改用户信息
 */
function updateSysUser(){
	var params = '';
	$("#updateSysUserForm input,#updateSysUserForm select").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/sysuser/updateuser',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success==true){
				layer.closeAll(); //疯狂模式，关闭所有层
			}
			layer.msg(result.message);
			window.location.reload();
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}

/**
 * 初始化修改用户
 * @param userId
 */
function initUser(userId){
	$.ajax({
		url:baselocation+'/admin/sysuser/initupdateuser/'+userId,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==true){
				var user = result.entity;
				$("#updateSysUserForm input[name='sysUser.userId']").val(user.userId);
				$("#userLongName").val(user.loginName);
				$("#updateSysUserForm input[name='sysUser.userName']").val(user.userName);
				$("#updateSysUserForm input[name='sysUser.email']").val(user.email);
				$("#updateSysUserForm input[name='sysUser.tel']").val(user.tel);
				$("#updateSysUserForm select[name='sysUser.roleId']").val(user.roleId);

				form.render('select'); //刷新select选择框渲染
				//通过这种方式弹出的层，每当它被选择，就会置顶。
				layer.open({
					title:'修改用户',
					type: 1,
					shade: false,
					area: '500px',
					maxmin: false,
					content: $("#updateWin")
				});
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}

/**
 * 创建用户
 */
function createSysUser(){
	if($("#confirmPwd").val()!=$("#loginPwd").val()){
        layer.msg("两次密码不一致", {icon: 5, shift: 6});
		return false;
	}
	var params = '';
	$("#sysUserForm input,#sysUserForm select").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/sysuser/createuser',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success==true){
				layer.closeAll(); //疯狂模式，关闭所有层
				layer.msg(result.message);
				window.location.reload();
			}else{
                layer.msg(result.message, {icon: 5, shift: 6});
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}

function closedData(){
	$("#createWin input:text,#updateWin input:text").val('');
	$("#createWin input:text").val('');
	$("input[name='sysUser.loginName']").val('');
	$("input:password").val('');
	$("select").val(0);
	$("#updateWin,#createWin,#updatePwdWin").hide();
}

/**
 * 删除用户
 * @param userId
 */
function delUser(userId){
	closedData();
	if(confirm("确定删除吗？")){
		$.ajax({
			url:baselocation+'/admin/sysuser/disableOrstart/'+userId+'/3',
			data:{},
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==true){
					layer.msg(result.message);
					window.setTimeout('window.location.reload()',1000);
				}
			},
			error:function(error){
                layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
			}
		});
	}
}