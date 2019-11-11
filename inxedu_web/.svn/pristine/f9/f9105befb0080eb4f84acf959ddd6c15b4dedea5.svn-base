/**
 * 获得课程章节目录
 * @param num
 */
function getCourseKpointList(courseId,type) {
	/*$(obj).parent().parent().parent().children().removeClass("current");
	$(obj).parent().parent().addClass("current");
	/!*courseId对应课程id*!/
	var courseId = $(obj).parent().find(".courseId").val();
	/!*更改删除课程方法传入的课程id*!/
	$("#delTrxoder").attr("onClick","delSelfCourse("+courseId+")");
	/!*studyPercent学习进度*!/
	var studyPercent = $(obj).parent().find(".studyPercent").val();
	if (studyPercent<100){
		$("#studyPercentImg").add("bg-green")
	}
	if (studyPercent>=100){
		$("#studyPercentImg").add("bg-orange")
	}
	$("#studyPercent").html(studyPercent+"%");
	/!*endTime到期时间*!/
	var endTime = $(obj).parent().find(".endTime").val();

	/!*根据到期时间显示状态*!/
	if (endTime!=""){
		var date = endTime.split("-");
		if (new Date(date[0],parseInt(date[1])-1,date[2],date[3],date[4],date[5])>new Date().getTime()){
			$("#status").removeClass("c-red");
			$("#status").addClass("c-green");

			$("#status").html("正常")
		}else {
			$("#status").removeClass("c-green");
			$("#status").addClass("c-red");
			$("#status").html("已过期")
		}
	}else {
		$("#status").removeClass("c-red");
		$("#status").addClass("c-green");
		$("#status").html("正常")
	}*/

	$.ajax({
		url : baselocation + "/front/ajax/courseKpointList/"+courseId+"/"+type,
		data:{},
		type : 'post',
		dataType : 'text',
		success : function(result) {
			$("#courseKpointMenu").html(result);
			treeMenu(); //课程树
		}
	});
}
/*删除个人中心课程*/
function delSelfCourse(courseId) {
	$.ajax({
		url:baselocation+"/front/ajax/delTrxOrder",
		data:{"courseId":courseId},
		type:"post",
		dataType:"json",
		success:function (result) {
			if (result.success==true){
				window.location.reload();
			}
		}
	})
}
/*检查课程卡卡号密码是否为空*/
function checkData(){
	var message="";
	if($("#cardCourseCode").val()==''){
		message+="卡号不能为空！\n";
	}
	if($("#cardCoursePassword").val()==''){
		message+="密码不能为空！\n";
	}
	return message;
}
/*激活课程卡*/
function courseCardActive(){
	var msg=checkData();
	if(msg!=''){
		msgshow(msg,"false");
	}else{
		$.ajax({
			url : baselocation + "/course/activationcard/1",
			data : {
				"cardCode.type":1,
				"cardCode.cardCode" : $.trim($("#cardCourseCode").val()),
				"cardCode.cardCodePassword" : $.trim($("#cardCoursePassword").val())
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				var msg="";
				if(result.entity=='passwordError'){
					msg="卡号或密码错误，请确认，谢谢！";
				}else if(result.entity=='dontActivate'){
					msg="该卡未被激活，请联系客服进行处理！谢谢";
				}else if(result.entity=='alreadyUse'){
					msg="该卡已被使用，不能再进行激活，请确认！谢谢";
				}else if(result.entity=='overDue'){
					msg="该卡已过期，不能进行激活，请确认！谢谢";
				}else if(result.entity=='close'){
					msg="该卡已作废，不能进行激活，请确认！谢谢";
				}else if (result.entity=='typeError'){
					msg="卡号或密码错误，请确认，谢谢！";
				}else if(result.entity=='dateError'){
					msg="该卡不在有效期内，请确认！谢谢";
				}else{
					msg="";
					$('.dialog-shadow').remove();
					dialog('激活提示',"您的课程卡已激活成功!",2,'/uc/index');
				}
				if(msg!=""){
					//$('.dialog-shadow').remove();
					//dialog('激活提示',msg,1);
					msgshow(msg,"false");
				}
			},
			error : function(error) {
				//$('.dialog-shadow').remove();
				//dialog('激活提示',"您的课程卡激活发生异常，请及时联系客服人员进行处理，谢谢！",1);
				msgshow("您的课程卡激活发生异常，请及时联系客服人员进行处理，谢谢！","false");
			}
		});
	}
}