var imPath=$("#imPath").val();

$(function(){
	limitLogin();
});
function callbackFun(){
	$.ajax({
		url:baselocation+'/uc/exit',
		type:'post',
		dataType:'json',
		async:true,
		success:function(result){
			window.location.href = baselocation+"/?msg=LimitLogin";
		},
		error:function(){
            window.location.href = baselocation+"/?msg=LimitLogin";
		}
	});

}
/**
 * 限制登录
 */
function limitLogin(){
	//判断IM对象是否定义
	if(!window.IM){
		return;
	}
	var socket = IM.nameSpace(imPath,"/web-login");
	socket.on('connect',function(){
		var user = getLoginUser();
		if(user!=null && user.userId>0 && typeof IM !='undefined'){
			var sid = getCookie($("#cookieKey").val());
			var roomKey = "inxedu_user_limit_"+user.userId;
			socket.emit('LIMIT-LOGIN',{'sid':sid,'userId':user.userId,'roomKey':roomKey});
			socket.on(roomKey,function(userData){
				if(sid!=userData.sid && userData.userId==user.userId){
					callbackFun();
				}
			});
		}
	});
}
