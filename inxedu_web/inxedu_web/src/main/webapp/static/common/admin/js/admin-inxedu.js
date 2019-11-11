layui.use(['form', 'laydate'], function () {
	var form = layui.form();
	var laydate = layui.laydate;
	form.on('select(select)', function (data) {
		checkImgWH(data.elem)
	});
	//各种基于事件的操作，下面会有进一步介绍
});
/**
 * 获取弹出框
 * @param title 标题
 * @param context 内容
 * @param index 下标
 * @param callback 回调函数
 * @param height 内容区域高度
 */
function dialog(title,context,index,callback,height) {
	$(".ui-dialog").remove();
	$.ajax({
		url:baselocation+'/common/dialog',
		type:'post',
		dataType:'text',
		data:{'title':title,'context':context,'index':index,'height':height},
		success:function(result){
			$('body').append(result);
			$("#cancelBut,.ui-icon-closethick").click(function(){
				$(".ui-dialog").remove();
			});
			$("#affirmBut").click(function(){
				$(".ui-dialog").remove();
				if(callback){
					callback();
				}
			});
		}
	});
}
/**
 * 弹出框 （弱提示）
 * @param info 消息内容
 * @param success true 成功 false 失败
 * @param timeNum 显示时长毫秒
 */
function msgshow(info,success,timeNum){
	var successType= 1;
	if(success=='false'){
		successType=2;
	}
	layer.msg(info, {
		icon: successType,
		time: timeNum //2秒关闭（如果不配置，默认是3秒）
	});
}
$(function(){
	layH();
});
function layH(){
	var boH =$(".layui-body").height();
	$(".layui-tab").css("min-height",boH);
}
$(window).resize(function(){layH();});