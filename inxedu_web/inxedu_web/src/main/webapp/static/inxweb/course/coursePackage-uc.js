/**
 * 获得直播章节目录
 * @param num
 */
function getCoursePackageList(courseId,obj) {
	if($(obj).parent().find(".lh-menu-wrap").html()==null){
		$.ajax({
			url : baselocation + "/uc/ajax/coursePackageList/"+courseId,
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
		$(obj).siblings("#coursePackageList").hide();
		$(obj).addClass("sch-open");
		$(obj).text("课程表展开∨");
	}else{
		$(obj).siblings("#coursePackageList").show();
		$(obj).removeClass("sch-open");
		$(obj).text("课程表收起∧");
	}
}