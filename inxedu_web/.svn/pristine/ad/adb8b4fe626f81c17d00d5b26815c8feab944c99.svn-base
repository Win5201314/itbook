

$(function(){
	createChatChannle(courseId,currentKpointId);
});
var roomKey = "uc_play_kpoint_";
var socketio = null;
var sendStatus = false;
/**
 * 创建聊天通道
 *
 */
function createChatChannle(courseId,kpointId){
	if(typeof IM =='undefined'){
		return;
	}
	var user = getLoginUser();
	if(user==null){
		return;
	}
	roomKey='uc_play_kpoint_'+courseId+'_'+kpointId;
	if(socketio==null){
		socketio = IM.nameSpace(imPath,'/web-group-chat');
	}else{
		socketio.emit('SET-GROUP-CHAT-KEY',roomKey);
	}
	if(socketio){
		socketio.on('connect',function(){
			socketio.emit('SET-GROUP-CHAT-KEY',roomKey);
			//如果连接成功，则设置状态为可发送消息
			sendStatus=true;

		});
		socketio.on(roomKey,function(data){
			var textHtml='';

				textHtml='<li>';
				textHtml+='<aside class="noter-pic"><img width="50" height="50" src="'+data.avatar+'" alt=""/></aside>';
				textHtml+='<div class="of"><span class="fl">';
				if(data.userId!=user.id){
					textHtml+='<font class="fsize12 c-blue">'+data.userName+'</font><font class="fsize12 c-999">：</font></span></div>';
				}else{
					textHtml+='<font class="fsize12 c-blue">我</font><font class="fsize12 c-999">：</font></span></div>';
				}
				textHtml+='<div class="noter-txt mt5">';
				textHtml+='<p>'+data.content+'</p></div>';
				textHtml+='</li>';

			$(".talkhtml").append(textHtml);
			if($('.talkhtml li').length>50){//当条数过长则清理
				$('.talkhtml li').slice(0, 1).remove();
			}

			if($(".commentHtml .talkhtml")[0]!=null){
				//手机端新发表的讨论定位到最下面
				$(".commentHtml .talkhtml")[0].scrollTop = $(".commentHtml .talkhtml")[0].scrollHeight;
			}
			if($(".talkAllhtml .talkhtml")[0]!=null){
				//pc端新发表的讨论定位到最下面
				$(".talkAllhtml .talkhtml")[0].scrollTop = $(".talkAllhtml .talkhtml")[0].scrollHeight;
			}



		});
		socketio.on('disconnect',function(){
			//如果连接断开，则设置状态为不能发送消息
			sendStatus=false;
		});
	}
}

/**
 * 发送聊天内容
 */
function sendtalk(){

	var content = $("#talkContent").val();
	if(content==null || $.trim(content)==''){
		dialog('提示','请输入内容！',1);
		return ;
	}
	if(sendStatus==false){
		dialog('提示','聊天房间连接失败！',1);
		return ;
	}
	var formTime ="2016-07-25 00:00:00";

	var user = getLoginUser();
	var userName = user.userName;
	if(userName==null || $.trim(userName)==''){
		userName=user.email;
	}
	//如果头像为空则为默认头像
	var avatar = baselocation+'/static/inxweb/img/avatar-boy.gif';
	//头像不为空则用用户头像
	if(user.picImg!=null&&user.picImg!=""){
		avatar = baselocation+user.picImg;
	}
	//$(".picImg").attr("src",avatar);
	var msg={
		roomKey:roomKey,//房间Key
		userId:user.id,//用户ID
		avatar:avatar,//用户头像
		userName:userName,//用户名
		content:content,//消息内容
		createTime:formTime//发送时间
	};
	socketio.emit('GROUP-CHAT-MESSAGE',msg);
	$("#talkContent").val("");
	$.ajax({
		url : baselocation + '/web/comment/ajax/addcomment',
		type : 'post',
		data : {
			"pCommentId" : "0",
			"content" : content,
			"type" : 5,
			"otherId" : currentKpointId
		},
		async : true,
		dataType : 'text',
		success : function(result) {
		}
	});

}

//查询最近10条讨论
function queryCommentTalk() {

	ajaxPage("/web/comment/ajax/querytalk", "&otherId=" + currentKpointId , 1, commentTalkCallBack);
}
function commentTalkCallBack(result) {
	$(".talkAllhtml").html(result);
	//讨论动态高度赋值
	$("#p-h-r-cont .p-center-discuss,#p-h-r-cont .talkhtml").height($("#p-h-r-cont").height()-110);

	//pc端新发表的讨论定位到最下面
	$(".talkAllhtml .talkhtml")[0].scrollTop = $(".talkAllhtml .talkhtml")[0].scrollHeight;
}
//查询最近10条讨论
function queryCommentTalkMobile(obj) {
	$(obj).removeClass("current").siblings().removeClass("current");
	$(obj).addClass("current");
	$(".commentHtml").next(".courseKpointHtml").hide();
	$(".commentHtml").show();
	ajaxPage("/web/comment/ajax/querytalk", "&otherId=" + currentKpointId , 1, commentTalkCallBackMobile);
}
function commentTalkCallBackMobile(result) {
	$(".commentHtml").html(result);
	//手机端新发表的讨论定位到最下面
	$(".commentHtml .talkhtml")[0].scrollTop = $(".commentHtml .talkhtml")[0].scrollHeight;
}

