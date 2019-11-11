var subjectList;
$(function(){
	initKindEditor_addblog('content', '100%', 350,'courseContxt','true');
	initSimpleImageUpload('imageFile','course',callback,"","true","640","357");
	/*$("#endTime,#liveBeginTime,#liveEndTime").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: "HH:mm:ss"
    });*/
	//有效期类型change事件
	losetypeShow(); 
	$("#losetype").change();
});

/**
 * 图片上传回调
 * @param imgUrl 图片路径
 */
function callback(imgUrl){
	$("input[name='course.logo']").val(imgUrl);
	$("#showImage").attr('src',staticServer+imgUrl);
}

/**
 * 选择讲师后的回调 
 * @param tcArr 讲师数组
 */
function addTeahcerList(tcArr){
	var pList = $("#teacherList p");
	var ids = $("input[name='teacherIdArr']").val();
	var tcHtml='';
	var tcIds='';
	for(var i=0;i<tcArr.length;i++){
		if(pList!=null && pList.length>0){
			for(var j=0;j<pList.length;j++){
				var id = $(pList[j]).attr('id');
				if(id==tcArr[i].id){
					$(pList[j]).remove();
					ids= ids.replace(id+',','');
				}
			}
		}
		tcIds+=tcArr[i].id+',';
		tcHtml+='<p class="t-List-p" id="'+tcArr[i].id+'"><span class="vam c-666">'+tcArr[i].name+'</span><a href="javascript:void(0)" onclick="deleteTc('+tcArr[i].id+')" class="ml20 layui-btn layui-btn-mini layui-btn-danger">删除</a></p>';
	}
	$("input[name='teacherIdArr']").val(tcIds+ids);
	$("#teacherList").append(tcHtml);
	$("#teacherList").show()

}

/**
 * 删除老师
 * @param tcId 老师ID
 */
function deleteTc(tcId){
	$("#teacherList #"+tcId).remove();
	var ids = $("input[name='teacherIdArr']").val();
	ids= ids.replace(tcId+',','');
	$("input[name='teacherIdArr']").val(ids);
	if ($("#teacherList").html()==""){
		$("#teacherList").hide()
	}
}


/**
 * 保存课程
 */
function saveCourse(){
	var title = $("input[name='course.courseName']").val();
	if(title==null||$.trim(title)==""){
        layer.msg("请填写名称", {icon: 5, shift: 6});
		return false;
	}
	if($("#sellType").val()=="LIVE"){
		var beginTime = $("#liveBeginTime").val();
		var endTime = $("#liveEndTime").val();
		if(beginTime==null || $.trim(beginTime)==''||endTime==null || $.trim(endTime)==''){
            layer.msg("请选择直播时间", {icon: 5, shift: 6});
			return false;
		}
		beginTime = beginTime.replace(/-/g,'/');
		endTime = endTime.replace(/-/g,'/');
		if(beginTime >= endTime){
            layer.msg("直播结束时间要大于直播开始时间", {icon: 5, shift: 6});
			return false;
		}
	}
	var subjectId = $("input[name='course.subjectId']").val();
	if(subjectId<=0){
        layer.msg("请选择专业", {icon: 5, shift: 6});
		return false;
	}

	var regs=/(^\d+[\.][0-9]{1,2}$)|(^\d+$)/;
	var sourcePrice = $("input[name='course.sourcePrice']").val();
	var currentPrice = $("input[name='course.currentPrice']").val();
	if(!regs.test(sourcePrice) || !regs.test(currentPrice)){
        layer.msg(sourcePrice, {icon: 5, shift: 6});
        layer.msg("价格必须是整数或最多保留两位小数！", {icon: 5, shift: 6});
		return false;
	}

	if ($("#losetype").val() == 0) {//有效期类型 到期时间
		var beginTime = new Date();
		var endTime = $("#endTime").val();
		if(endTime==null || $.trim(endTime)==''){
            layer.msg("请选择有效期结束时间", {icon: 5, shift: 6});
			return false;
		}
		/*beginTime = beginTime.replace(/-/g,'/');*/
		endTime = endTime.replace(/-/g,'/');
		if(beginTime > new Date(endTime)){
            layer.msg("有效期结束时间要大于当前时间", {icon: 5, shift: 6});
			return false;
		}
	}
	else if ($("#losetype").val() == 1) {//按天数
		var loseTime=$("#loseTime").val();
		if($.trim(loseTime)==""){
            layer.msg("请输入有效期天数", {icon: 5, shift: 6});
			return false;
		}
	}

	var teacherIdArr = $("input[name='teacherIdArr']").val();
	if(isEmpty(teacherIdArr)){
        layer.msg("请添加教师", {icon: 5, shift: 6});
		return false;
	}
	var abstract = $("textarea[name='course.title']").val();
	if (abstract==null||$.trim(abstract)==""){
        layer.msg("请填写简介内容", {icon: 5, shift: 6});
		return false;

	}
	$("#saveCourseForm").submit();
}

/**
 * 有效期类型change事件
 */
function losetypeShow() {
	$("#losetype").change(function() {
		$(".loseTimeShow").hide();
		$(".endTimeShow").hide();
		if ($(this).val() == 1) {
			$(".loseTimeShow").show();
			$("#endTime").val('');
		}
		if ($(this).val() == 0) {
			$(".endTimeShow").show();
			$("#loseTime").val('');
		}
	});
}

/**
 * 直播 有效期结束时间 为直播结束时间
 * @param obj
 */
function changeEndTime(obj){
	//$("#endTime").val($(obj).val());
}