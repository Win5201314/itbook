$(function(){
	/**加载时间控件*/
	$("#beginCreateTime,#endCreateTime").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: "HH:mm:ss"
    });

});

/**
 * 冻结或解冻用户
 * @param userId 用户ID
 * @param type 1解冻 2冻结
 * @param em
 */
function frozenOrThaw(userId,type,em){
	$.ajax({
		url:baselocation+'/admin/user/updateuserstate',
		type:'post',
		dataType:'json',
		data:{'user.userId':userId,'user.isavalible':type},
		success:function(result){
			if(result.success==true){
				var td = $(em).parents().parents().parents().find('td').eq(4);
				if(type==1){
					$(td).html('正常');
					$('#frozenOrThaw'+userId).html('<button onclick="frozenOrThaw('+userId+',2,this)" class="layui-btn layui-btn-small" type="button">冻结</button>');
				}else if(type==2){
					$(td).html('冻结');
					$('#frozenOrThaw'+userId).html('<button onclick="frozenOrThaw('+userId+',1,this)" class="layui-btn layui-btn-small" type="button">解冻</button>');

				}
			}else{
                layer.msg(result.message, {icon: 5, shift: 6});
			}
		},
		error:function(error){
			
		}
	});
}


/**
 * 用户列表导出
 */
function userExcel(){
	$("#searchForm").prop("action","/admin/user/export");
	$("#searchForm").submit();
	$("#searchForm").prop("action","/admin/user/getuserList");
}

/**
 * 赠送课程页面
 */
function selectCousre(userId){
		/*window.open(baselocation+'/admin/user/courseList/'+userId ,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=600');*/
	window.open(baselocation+'/admin/user/courseList/'+userId ,'newwindow');
}
function selectMember(userId) {
	window.open(baselocation+'/admin/user/memberList/'+userId ,'newwindow');
}