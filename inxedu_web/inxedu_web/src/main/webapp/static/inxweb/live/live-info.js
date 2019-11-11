//评论课程id
var otherId = $("#otherId").val();
//评论类型,类型2为课程 3为直播课
var type = 3;
//课程有效期到期时间
var loseTimeTime = $("#loseTimeTime").val();
//有效期类型，0：到期时间，1：按天数
var loseType=$("#loseType").val();

$(function() {
	/*给导航加选中*/
	$("a[href$='/front/liveIndex']").parent().addClass("current");
	shareShow1(); //课程分享
	//ctbodyFun();
	cTabFun($("#c-i-tabTitle>a")); //滚动定位
	//学过此课程的用户
	getCourseLearnedUser(otherId);
	queryComment();//评论
	var render = "canvas";
	try {
		//默认字符串转二维码的方法，仅支持html5
		document.createElement('canvas').getContext('2d');
	} catch (e) {
		//如果不支持html5报错后通过table生成二维码
		render = "table";
	}
	//conDowFun();
	//生成二维码
	$('#output').qrcode({
		text: baselocation+"/front/couinfo/"+otherId,
		height: 120,
		width: 120,
		render: render,
		src: '/favicon.ico'//这里配置Logo的地址即可。
	});
});
function shareShow1() {
	$(".kcShare").hover(function() {
		$(this).stop().animate({"width" : "180px"}, 200);
		$(this).siblings("span").css({"width" : "60px"});
		$(this).children("#bdshare").stop().animate({"right" : "0"}, 200);
	}, function() {
		$(this).stop().animate({"width" : "48px"}, 200);
		$(this).children("#bdshare").stop().animate({"right" : "-160px"}, 200);
	});
}
//课程详情收起展开
var ctbodyFun = function() {
	var ctb = $(".course-txt-body"),
		ctBtn = $(".ctb-btn>a");
	if (ctb.height() < 88) {
		ctBtn.parent().hide();
		return false;
	} else {
		ctb.css({"height" : "88px"});
		ctBtn.parent().show();
		ctBtn.toggle(function() {
			ctBtn.text("收起更多∧");
			ctb.stop().animate({"height" : "100%"}, 500);
		}, function() {
			ctBtn.text("查看更多∨");
			ctb.css({"height" : "88px"});
		});
	}
};

/**
 * 最近的一个直播播放
 */
function nowLivePlay(courseId){
	if(!isLogin()){
		lrFun();
		return;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url:baselocation+"/front/ajax/nowLivePlay",
		data:{"courseId":courseId},
		cache : true,
		async : false,
		success : function(result) {
			if(result.success){
                // var heigth=$(window).height();
				// window.open(result.entity+"&width=100%&height="+heigth);
                $("#live").load(result.entity);
			}else{
				dialog('提示',result.message,1);
			}
		}
	});
}

//直播倒计时
/*
function conDowFun() {
	var second = ${not empty liveTime?liveTime:0}; // 剩余秒数

	ss = second%1000;
	second = (second -ss)/1000;
	//写一个方法，将秒数专为天数
	if (second > 0) {

		var toDays = function () {
			var s = second % 60; // 秒
			if (s < 10) {
				s = "0" + s;
			}
			var mi = (second - s) / 60 % 60; // 分钟
			var h = ((second - s) / 60 - mi ) / 60 % 24; // 小时
			if (h < 10) {
				h = "0" + h;
			}
			var d = (((second - s) / 60 - mi ) / 60 - h ) / 24; // 天
			return "直播倒计时：<tt>" + d + "</tt>天<tt>" + h + "</tt>时<tt>" + mi + "</tt>分<tt>" + s + "</tt>秒";
		};
		//然后写一个定时器
		window.setInterval(function () {
			if (second > 0) {
				second--;
			}
			document.getElementById("showTimes").innerHTML = toDays();
		}, 1000);
	}
}*/
