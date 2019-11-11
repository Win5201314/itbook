/** start 展视互动 */
/*
 * 直播供应商 下拉改变
 */
function supplierChange(){
	$(".genseeLiveTr").hide();
	$(".yunInxeduTr").hide();
	$(".baijiayunLiveTr").hide();
	$(".baijiayunLive").hide();
	$("#videoUrlTitle").val("直播地址");
	var supplier=$("#supplier").val();
	if(supplier=="gensee"){
		$(".genseeLiveTr").show();
		//查找展示互动 的直播
		$.ajax({
			url:baselocation+'/admin/ajax/getLiveGenseeByKpointid/'+$("input[name='courseKpoint.kpointId']").val(),
			data:{},
			type:'post',
			dataType:'json',
			async:false,
			success:function(result){
				if(result.success==true){
					var entity=result.entity;
					$("#videourl").val(entity.studentJoinUrl);
					$("#teacherJoinUrl").val(entity.teacherJoinUrl);

					$("#updateGenseeBtn").attr("onclick","updateGensee("+entity.id+")");
					$("#delGenseeBtn").attr("onclick","deleteGensee("+entity.id+")");
					$("#addateGenseeBtn").hide();
					$("#updateGenseeBtn").show();
					$("#delGenseeBtn").show();
				}else{
					$("#updateGenseeBtn").attr("onclick","");
					$("#delGenseeBtn").attr("onclick","");
					$("#addateGenseeBtn").show();
					$("#updateGenseeBtn").hide();
					$("#delGenseeBtn").hide();
				}
			},
			error:function(error){
				isTrue=false;
				alert("系统繁忙，请稍后再操作！");
			}
		});
	}
	else if(supplier=="inxedu_cloud"){
		$(".yunInxeduTr").show();
	}
	else if(supplier=="baijiayun"){
		$(".baijiayunLiveTr").show();
		$("#videoUrlTitle").val("直播id");
		$.ajax({
			url:baselocation+'/admin/ajax/getLiveBaijiayunByKpointid/'+$("input[name='courseKpoint.kpointId']").val(),
			data:{},
			type:'post',
			dataType:'json',
			async:false,
			success:function(result){
				if (result.success==true){
					$(".baijiayunLive").show();
				}
			},
			error:function(error){
				alert("系统繁忙，请稍后再操作！");
			}
		});
	}
	else{
		$(".genseeLiveTr").hide();
	}
}
/**
 * 创建直播课堂
 */
function createGensee(){
	window.open(baselocation+'/admin/liveGensee/toadd?liveId='+$("input[name='courseKpoint.kpointId']").val()
		+'&kpointName='+$("input[name='courseKpoint.name']").val()
		+'&beginTime='+$("#liveBeginTime").val()
		+'&liveEndTime='+$("#liveEndTime").val()
		,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=600');
}
/**
 * 修改直播课堂
 */
function updateGensee(id){
	window.open(baselocation+'/admin/liveGensee/toUpdate/'+id ,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=600');
}

/**
 * 根据章节 删除直播课堂
 */
function delKpointAndDelGensee(ids){
	$.ajax({
		url:'/admin/ajax/liveGenseeDelByKpointid/'+ids,
		type:'post',
		dataType:'json',
		success:function(result){

		},
		error:function(error){
			alert("系统繁忙，请稍后再操作！");
		}
	});
}

/**
 * 删除直播课堂
 */
function deleteGensee(id){
	if(!confirm('确实要删除吗?')){
		return;
	}
	$.ajax({
		url:'/admin/ajax/liveGenseeDel/'+id,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==false){
				alert(result.message);
			}else{
				alert("删除成功");
				$("#videourl").val("");
				$("#teacherJoinUrl").val("");

				$("#updateGenseeBtn").attr("onclick","");
				$("#delGenseeBtn").attr("onclick","");

				$("#addateGenseeBtn").show();
				$("#updateGenseeBtn").hide();
				$("#delGenseeBtn").hide();
			}
		},
		error:function(error){
			alert("系统繁忙，请稍后再操作！");
		}
	});
}

/**
 * 创建直播课堂 后的回调
 * @param data
 */
function addGenseeData(data){
	$("#videourl").val(data.studentJoinUrl);
	$("#teacherJoinUrl").val(data.teacherJoinUrl);

	$("#updateGenseeBtn").attr("onclick","updateGensee("+data.genseeId+")");
	$("#delGenseeBtn").attr("onclick","deleteGensee("+data.genseeId+")");
	$("#addateGenseeBtn").hide();
	$("#updateGenseeBtn").show();
	$("#delGenseeBtn").show();
}

/** end 展视互动 */



/** start 因酷云直播 */


var timer;
var winyunInxeduOpen;

/**
 * 关闭窗口后 从cookie中获取数据
 * @constructor
 */
function IfInxeduWindowClosed() {
	if (winyunInxeduOpen.closed == true) {
		window.clearInterval(timer);
		$("#videourl").val(unescape(getCookie("yun_inxedu_roomid")));
		setCookie("yun_inxedu_roomid","");
		var exp = new Date();
		exp.setTime(exp.getTime() + 60 * 1000);
		document.cookie = "is_yun_inxedu_open_window" + "=false;expires="+ exp.toGMTString() + ";path=/;domain=.inxedu.com";//因酷云判断 cookie的值 ，来是否显示 选择按钮
	}
}
/** end 因酷云直播 */