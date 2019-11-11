/**
 * 获得直播章节目录
 * @param num
 */
function getLiveKpointList(courseId,obj) {
	if($(obj).parent().find("table.classIsEmpty").html()==null){
		$.ajax({
			url : baselocation + "/front/ajax/liveKpointList/"+courseId,
			data:{},
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$(obj).parent().append(result);
				showTable(obj);
			}
		});
	}else{
		showTable(obj);
	}
}

function showTable(obj){
	if(!$(obj).hasClass("sch-open")){
		$(obj).siblings(".table_sche").hide();
		$(obj).addClass("sch-open");
		$(obj).text("课程表展开∨");
	}else{
		$(obj).siblings(".table_sche").show();
		$(obj).removeClass("sch-open");
		$(obj).text("课程表收起∧");
	}
}

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
		async : true,
		success : function(result) {
			if(result.success){
				window.open(result.entity);
			}else{
				dialog('提示',result.message,1);
			}
		}
	});
}