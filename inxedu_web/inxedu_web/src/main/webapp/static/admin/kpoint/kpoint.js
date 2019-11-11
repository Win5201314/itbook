var ztreeObject;
var setting = {
	edit:{
		enable: true,
		renameTitle:'修改视频节点',
		removeTitle:'删除视频节点'
	},
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'kpointId',
			pIdKey:'parentId',
			rootPid:''
		},
		key:{
			name:'name',
			title:'name'
		}
	},
	callback: {
		//修改视频节点
		beforeEditName:initUpdateKpoint,
		//删除节点
		beforeRemove:deleteKpoint,
		//修改视频父节点
		beforeDrop:updateKpointParentId,
		//拖动后方法
		onDrop: udpSort,
        /*节点展开后判断高度*/
        onExpand: unpdatHeight
	}
};
$(function(){
	$('.ui-dialog-titlebar-close,.closeBut').click(function(){
		closeData();
	});

});
/*判断节点外层高度*/
function unpdatHeight() {
    if ($("#kpointList").height()>$(".kpointBox").first().height() && $("#kpointList").height()>600){
        $(".kpotionBox").height($("#kpointList").height()+100);
    }
}
/**
 * 初始化视频节点树
 * @param ztree 视频节点数据
 */
function showKpointZtree(ztree){
	//ztree = eval('('+ztree+')');
	ztreeObject = $.fn.zTree.init($("#kpointList"), setting, ztree);
	//ztreeObject.expandAll(true);
	ztreeObject.expandNode(ztreeObject.getNodeByTId('1'),true,false);//展开第一个节点
    setTimeout(function () {
        if ($("#kpointList").height()+100>$(".kpointBox").first().height() && $("#kpointList").height()>600){
            $(".kpotionBox").height($("#kpointList").height()+100)
        }
    },150)
}
/**
 * 取消树的选中状态
 */
function ztreeCancelSelectedNode(){
	ztreeObject = $.fn.zTree.getZTreeObj("kpointList");
	ztreeObject.cancelSelectedNode();
}


/**
 * 创建视频节点
 * @param courseId 课程ID
 */
function addaKpoint(courseId){
	var parentId =0;
	ztreeObject = $.fn.zTree.getZTreeObj("kpointList");
	var seleNodes = ztreeObject.getSelectedNodes();
	if(seleNodes!=null && seleNodes.length>0){
		parentId =seleNodes[0].kpointId;
	}
	if(seleNodes!=null && seleNodes.length>0){
		var seleLevel=seleNodes[0].level;
		if(seleLevel!=0){
            layer.msg("创建视频节点只支持二级,请重新选择一级节点再添加!", {icon: 5, shift: 6});
			return;
		}
	}
	if(getKpointType(parentId)==false){//判断父级节点类型
		return;
	}
	var kpointType=0;
	if(parentId!=0){
		kpointType=1;
	}
	//课件类型（课程默认是VIDEO）
	var fileType="VIDEO";
	if(sellType=="LIVE"){
		//如果是直播(类型为LIVE)
		fileType="LIVE";
	}

	$.ajax({
		url:baselocation+'/admin/kpoint/addkpoint',
		type:'post',
		dataType:'json',
		data:{
			'courseKpoint.name':'新创建视频',
			'courseKpoint.parentId':parentId,
			'courseKpoint.courseId':courseId,
			'courseKpoint.kpointType':kpointType,
			'courseKpoint.fileType':fileType,
			'courseKpoint.videoType':"INXEDUVIDEO"
		},
		success:function(result){
			if(result.success==false){
				msgshow(result.message,"false");
			}else{
				var nodes =[result.entity];
				if(parentId>0){
					ztreeObject.addNodes(seleNodes[0],nodes);
				}else{
					ztreeObject.addNodes(null,nodes);
				}
				/*节点排序*/
				udpSort();
				if ($("#kpointList").height()+100>$(".kpotionBox").first().height()){
					$(".kpotionBox").height($("#kpointList").height()+100);
				}
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}

/**
 * 修改视频
 * @param treeNode
 */
function initUpdateKpoint(treeId, treeNode){
	closeData();
	var treeNodeLeve=treeNode.level;
	var childrenNodes = treeNode.children;

	$.ajax({
		url:baselocation+'/admin/kpoint/getkpoint/'+treeNode.kpointId,
		type:'post',
		dataType:'json',
		success:function(result){
			var obj = result.entity;
			$("input[name='courseKpoint.kpointId']").val(obj.kpointId);
			$("#openType").val(obj.openType);
			$("#courseId").val(obj.courseId);
			$("input[name='courseKpoint.name']").val(obj.name);
			if(treeNodeLeve==0){//一级节点
				//$("input[name='courseKpoint.videoUrl']").parent().parent().hide();//不输入视频地址
			}else{
				$("input[name='courseKpoint.videoUrl']").parent().parent().show();
				$("#courseKpointVideoType").parent().parent().show();
				$("select[name='courseKpoint.kpointType']").val(1);//节点类型默认 为视频
				$("select[name='courseKpoint.kpointType']").parent().parent().hide();//节点类型 隐藏
			}
			if (childrenNodes&&childrenNodes.length>0) {//如果 当前节点有子节点
				$("select[name='courseKpoint.kpointType']").val(0);//节点类型 为目录
				$("input[name='courseKpoint.videoUrl']").parent().parent().hide();//不输入视频地址
				$("#courseKpointVideoType").parent().parent().hide();
				$("select[name='courseKpoint.kpointType']").parent().parent().hide();//节点类型 隐藏
		    }else{
		    	$("#courseKpointKpointType").parent().parent().show();//显示
				/*if(sellType=="LIVE"){//如果是直播 一级目录 只能是文件夹
					$("#courseKpointKpointType").parent().parent().hide();//隐藏
				}*/
		    }
			if(treeNodeLeve!=0){
				$("#courseKpointKpointType").parent().parent().hide();//隐藏
			}

			//给标签赋值
			$("#courseKpointKpointType").val(obj.kpointType);
			$("#courseKpointVideoType").val(obj.videoType);
			$("#courseKpointVideoType").change();
			$("input[name='courseKpoint.videoUrl']").val(obj.videoUrl);
			$("#fileType").val(obj.fileType);
			//清空文本内容
			//EditorObject.html("");
			//设置HTML内容
			//EditorObject.html(obj.content);
			$("input[name='courseKpoint.sort']").val(obj.sort);
			$("#pageCount").val(obj.pageCount);
			$("input[name='courseKpoint.playCount']").val(obj.playCount);
			$("input[name='courseKpoint.teacherId']").val(obj.teacherId);
			$("input[name='courseKpoint.playTime']").val(obj.playTime);
			$("#J_imageView").html("");//清空图片集
			$("#atlas").val("");
			$("#atlasThumbnail").val("");
			if(obj.fileType=="ATLAS"){
				var content='';
				for(i=0;i<obj.kpointAtlasesList.length;i++){
					/*content+='<span><a href="<%=staticImageServer%>'+obj.kpointAtlasesList[i].url+'" ' +
						'target="_blank"><input type="hidden" name="courseKpoint.atlas" class="aatlas" value="'+obj.kpointAtlasesList[i].url+'"/>' +
						'<input type="hidden" name="courseKpoint.atlasThumbnail" value="'+obj.kpointAtlasesList[i].urlThumbnail+'"/><img class="img" src="<%=staticImageServer%>'+obj.kpointAtlasesList[i].url+'" width="100px;" height="100px"/></a>' +
						'<a href="javascript:void(0)" onclick="delimg(this,\''+obj.kpointAtlasesList[i].url+'\')">删除</a></span>';*/
					content+='<div style="float: left"><img src="' + obj.kpointAtlasesList[i].urlThumbnail + '" alt="" width="120" height="78">' +
						'<input type="hidden" class="atlasImage" name="courseKpoint.atlas" value="' + obj.kpointAtlasesList[i].url + '">' +
						'<input type="hidden" class="" name="courseKpoint.atlasThumbnail" value="' + obj.kpointAtlasesList[i].urlThumbnail + '">' +
						'<span class="remove-pic-item" onclick="delimg(this,\'' + obj.kpointAtlasesList[i].url + ' \')">X</span></div>';
				}
				$("#J_imageView").html(content);
			}
			if(obj.fileType=="PDF"){
				var pdfatlas="";
				var pdfThumbnail="";
				for(i=0;i<obj.kpointAtlasesList.length;i++){
					pdfatlas+=obj.kpointAtlasesList[i].url+",";
					pdfThumbnail+=obj.kpointAtlasesList[i].urlThumbnail+",";
				}
				$("#atlas").val(pdfatlas);
				$("#atlasThumbnail").val(pdfThumbnail);
			}

			//课后作业 清空
			$("#exercises").text("");
			$("#examQuestionIds").val("");
			$("#examPaperId").val("");
			if(obj.fileType=="EXERCISES"){
				$("#exercises").text(obj.content);
				//清空
				$("#examQuestionIds").val("");
				$("#examPaperId").val(obj.videoUrl);
				//清空文本内容
				//EditorObject.html("");
				//设置HTML内容 试卷名
				//EditorObject.html(obj.content);
				//$("#content").val(obj.content);
				UE.getEditor('content').setContent(obj.content);
				$("#examType").val(0);
			}

			$("#liveBeginTime").val("");
			$("#liveEndTime").val("");
			$("#supplier").val("");
			if(obj.fileType=="LIVE" && result.message!=null &&result.message!=""){
				$("#liveBeginTime").val(result.message.split("|")[0]);
				$("#liveEndTime").val(result.message.split("|")[1]);
				$("#supplier").val(obj.supplier);
			}
			if(obj.teacherName!=null&&obj.teacherName!=""){
				$("#teacher").text(obj.teacherName);
			}else {
				$("#teacher").text("");
			}

			$("input[name='courseKpoint.isFree']").attr('checked',false);
			if(obj.free==1){
				$($("input[name='courseKpoint.free']")[0]).attr('checked',true);
			}else if(obj.free==2){
				$($("input[name='courseKpoint.free']")[1]).attr('checked',true);
			}
			//节点类型改变方法
			kpointTypeChange();
			$("#updateWin").show();
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
	return false;
}

/**
 * 执行修改
 */
function updateKpoint(){
	var params = '';
	$("#updateForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	var name = $("input[name='courseKpoint.name']").val();
	if(name==null || $.trim(name)==''){
        layer.msg("视频节点名不能为空", {icon: 5, shift: 6});
		return false;
	}
	var reg=/^\d+$/;
	var ue = UE.getEditor('content');
	if($("#courseKpointKpointType").val()==1){//章节专属验证
		var fileType=$("#fileType").val();
		if(fileType=='TXT') {
			if (isEmpty(ue.getContent())) {
                layer.msg("文本内容必填！", {icon: 5, shift: 6});
				return;
			}
			var content=encodeURIComponent(ue.getContent());
			params+="courseKpoint.content="+content+"&";
		}else if(fileType=="ATLAS"&&isEmpty($("#J_imageView").html())){
            layer.msg("图片集至少上传一张图片", {icon: 5, shift: 6});
			return;
		}else if(fileType=="VIDEO"&&isEmpty($("#videourl").val())){
            layer.msg("请填写视频地址", {icon: 5, shift: 6});
			return;
		}else if(fileType=="AUDIO"&&isEmpty($("#videourl").val())){
            layer.msg("请上传音频", {icon: 5, shift: 6});
			return;
		}else if(fileType=="PDF"&&isEmpty($("#videourl").val())){
            layer.msg("请上传PDF文件", {icon: 5, shift: 6});
			return;
		}else if(fileType=="LIVE"){
			if(isEmpty($("#videourl").val())){
                layer.msg("请输入直播地址", {icon: 5, shift: 6});
				return;
			}
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
			/*var teacherId = $("input[name='courseKpoint.teacherId']").val();
			if(teacherId<=0){
			 	layer.msg("请选择老师", {icon: 5, shift: 6});
				return false;
			}*/
			params+="courseKpoint.supplier="+$("#supplier").val()+"&";
		}else if(fileType=="EXERCISES"){
			var content=encodeURIComponent(ue.getContent());
			if($("#examType").val()==0&&$("#examPaperId").val().trim()==""){
				//选择试卷
                layer.msg("请选择试卷", {icon: 5, shift: 6});
				return;
			}else if($("#examType").val()==1&&isEmpty($("#examQuestionIds").val().trim())){
				//选择试题
                layer.msg("请选择试题", {icon: 5, shift: 6});
				return;
			}
			//所有试题id
			var examQuestionIds=$("#examQuestionIds").val().trim();
			examQuestionIds=examQuestionIds.substr(0,examQuestionIds.length-1);
			params+="courseKpoint.content="+content+"&examType="+$("#examType").val()+"&examPaperId="+$("#examPaperId").val()+"&examQuestionIds="+examQuestionIds+"&";
		}

		var playCount =$('input[name="courseKpoint.playCount"]').val();
		if(!reg.test(playCount)){
            layer.msg("播放数必须是正整数！", {icon: 5, shift: 6});
			return false;
		}
	}

	/*var sort = $("input[name='courseKpoint.sort']").val();
	if(!reg.test(sort)){
	 	layer.msg("排序必须是正整数！", {icon: 5, shift: 6});
		return false;
	}*/

	var openType = $("#openType").val();
	params+="courseKpoint.openType="+openType+"&";
	var kpointType=$("#courseKpointKpointType").val();
	params+="courseKpoint.kpointType="+kpointType+"&";
	var videoType=$("#courseKpointVideoType").val();
	params+="courseKpoint.videoType="+videoType+"&";
	var fileType=$("#fileType").val();
	params+="courseKpoint.fileType="+fileType+"&";

	$.ajax({
		url:baselocation+'/admin/kpoint/updateKpoint',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success==false){
                layer.msg(result.message, {icon: 5, shift: 6});
			}else{
				var obj = result.entity;
				ztreeObject = $.fn.zTree.getZTreeObj("kpointList");
				var node = ztreeObject.getNodeByParam('kpointId',obj.kpointId,null);
				node.name=obj.name;
				node.videoUrl=obj.videoUrl;
				node.sort = obj.sort;
				node.playCount = obj.playCount;
				node.free = obj.free;
				node.teacherId = obj.teacherId;
				ztreeObject.updateNode(node);
				closeData();
                layer.msg("修改成功", {icon: 1, shift: 6});
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}

var ids='';
/**
 * 递归节点的所有的子级节点的ID
 * @param node
 */
function getChildren(node){
	ids+=node.kpointId+',';
	var nodes = node.children;
	if(nodes!=null && nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			getChildren(nodes[i]);
		}
	}
}

/**
 * 删除视频树节点
 * @param treeId 树ID
 * @param treeNode 视频节点对象
 */
function deleteKpoint(treeId, treeNode){
	var is = false;
	if(!confirm("确认要删除["+treeNode.name+"]及该节点的子级节点？")){
		return is;
	}
	getChildren(treeNode);
	$.ajax({
		url:baselocation+'/admin/kpoint/deletekpoint/'+ids,
		type:'post',
		dataType:'json',
		async:false,
		success:function(result){
			if(result.success==true){
				is=true;
				try
				{
					delKpointAndDelGensee(ids);
				}
				catch (e)
				{}
			}else{
                layer.msg(result.message, {icon: 1, shift: 6});
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作", {icon: 5, shift: 6});
		}
	});
	return is;
}

/**
 * 拖拽修改视频节点的父节点
 * @param treeId 目标节点 targetNode 所在 zTree 的 treeId
 * @param treeNodes 被拖拽的节点 JSON 数据集合
 * @param targetNode treeNodes 被拖拽放开的目标节点 JSON 数据对象。
 * @param moveType 指定移动到目标节点的相对位置
 */
function updateKpointParentId(treeId, treeNodes, targetNode, moveType){
	var parentId;
	var is = true;
	if (moveType=="inner"){
		parentId = targetNode.kpointId;
		if(targetNode.parentId!=null){
			msgshow("课程节点只支持2级","false");
			is = false;
			return is;
		}
	}else {
		parentId = targetNode.parentId;
		if (targetNode.parentId==null){
			parentId=0;
		}
	}
	var treeObj = $.fn.zTree.getZTreeObj("kpointList");

	var kpointId = treeNodes[0].kpointId;

	$.ajax({
		url:baselocation+'/admin/kpoint/updateparentid/'+parentId+'/'+kpointId,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==false){
				msgshow(result.message,"false");
				is = false;
				return is;
			}else{
				is = true;
			}

		}
	});
	return is;
}
/*节点拖动后的事件
* 更改节点排序
*/
function udpSort(){
	var nodesInfo = getNodeInfo();
	$.ajax({
		url:baselocation+'/admin/kpoint/updSort/',
		type:'post',
		dataType:'json',
		data:{"nodes":nodesInfo},
		success:function(result){

		}
	});
}

/**
 * 选择添加老师回调
 * @param arr 老师数组
 */
function addTeahcerList(arr){
	var teacher = arr[0];
	$("input[name='courseKpoint.teacherId']").val(teacher.id);
	$("#teacher").text(teacher.name);
}
/*获取拖动后的节点的id集合*/
function getNodeInfo() {
	var nodeInfo = "";
	/*获取树节点的所有信息*/
	var treeObj = $.fn.zTree.getZTreeObj("kpointList");
	var nodes = treeObj.getNodes();
	/*循环获取节点id*/
	for(var i=0;i<nodes.length;i++){
		nodeInfo += (nodes[i].kpointId)+',';
		if (nodes[i].children!=null){
			for(var k=0;k<nodes[i].children.length;k++){
				nodeInfo += (nodes[i].children[k].kpointId)+',';
			}
		}
	}
	return nodeInfo;
}
/**
 * 取消
 */
function closeData(){
	$("#updateWin").hide();
	$("#updateForm input:text").val('');
	$("input[name='courseKpoint.kpointId']").val(0);
	$("input[name='courseKpoint.teacherId']").val(0);
	$("input[name='courseKpoint.isFree']").attr('checked',false);
	$("#teacher").text('');
}

/**
 * 视频节点类型 下拉改变
 */
function kpointTypeChange(){
	//先隐藏所有
	$(".tr_kpoint").hide();

	var kpointType=$("#courseKpointKpointType").val();
	//再显示要 展示的
	if(kpointType==1){
		//$("input[name='courseKpoint.videoUrl']").parent().parent().show();
		$("#fileType").parent().parent().show();
		//显示 章节 的属性
		$(".tr_is_kpoint").show();
	}


	/*var kpointType=$("#courseKpointKpointType").val();
	if(kpointType==0){
		$("input[name='courseKpoint.videoUrl']").parent().parent().hide();
		$("#fileType").parent().parent().hide();
		$("#courseKpointVideoType").parent().parent().hide();
		$("input[name='courseKpoint.videoUrl']").val("");
	}else{
		$("input[name='courseKpoint.videoUrl']").parent().parent().show();
		$("#fileType").parent().parent().show();
		$("#courseKpointVideoType").parent().parent().show();
	}*/
	//调用 它的下一级展示
	chooseFileType();//课件类型改变
}

/**
 *判断节点类型
 */
function getKpointType(parentId){
	var isTrue=true;
	$.ajax({
		url:baselocation+'/admin/kpoint/getkpoint/'+parentId,
		type:'post',
		dataType:'json',
		async:false,
		success:function(result){
			var obj = result.entity;
			if(obj!=null && obj!="" && obj.kpointType==1){
				isTrue=false;
                layer.msg("创建视频节点只能在目录节点类型下添加!", {icon: 5, shift: 6});
			}
		},
		error:function(error){
			isTrue=false;
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
	return isTrue;
}

/*
 * 视频类型 下拉改变
 */
function videoTypeChange(){
	$(".uploadCCVideo").hide();
	$(".selYunInxeduVideo").hide();
	var viddeoType=$("#courseKpointVideoType").val();
	if(viddeoType=="CC"){
		//$("input[name='courseKpoint.videoUrl']").attr("readonly","readonly");
		$(".uploadCCVideo").show();
		$("#up").html("无");
	}
	else if(viddeoType=="INXEDUCLOUD"){
		$(".selYunInxeduVideo").show();
	}
	else{
		//$("input[name='courseKpoint.videoUrl']").removeAttr("readonly");
		$(".uploadCCVideo").hide();
	}
}

//-------------------
//文件选择成功后，Flash 会调用
//调用者：flash
//功能：选中上传文件，获取文件名函数
//时间：2010-12-22
//说明：用户可以加入相应逻辑
//-------------------
function on_spark_selected_file(filename) {
	$("#upload_title").val(filename);
	$("#upload_tag").val(filename);
	$("#upload_desp").val(filename);

	$.ajax({
		url:baselocation+'/admin/ajax/kpoint/ccVideoTHQSData',
		data:{"filename":filename},
		type:'post',
		dataType:'json',
		async:false,
		success:function(result){
			if(result.success==true){
				$("#uploadswf")[0].start_upload(result.message);
			}
		},
		error:function(error){
			isTrue=false;
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}

//	-------------------
//调用者：flash
//功能：验证上传是否正常进行函数
//时间：2010-12-22
//说明：用户可以加入相应逻辑
//-------------------
function on_spark_upload_validated(status, videoid) {
	if (status == "OK") {
		// layer.msg("上传正常,videoid:" + videoid, {icon: 1, shift: 6});
		$("input[name='courseKpoint.videoUrl']").val(videoid);//视频id
	} else if (status == "NETWORK_ERROR") {
        layer.msg("网络错误", {icon: 5, shift: 6});
	} else {
        layer.msg("api错误码：" + status, {icon: 5, shift: 6});
	}
}

//-------------------
//调用者：flash
//功能：通知上传进度函数
//时间：2010-12-22
//说明：用户可以加入相应逻辑
//-------------------
function on_spark_upload_progress(progress) {
	var uploadProgress = document.getElementById("up");
	if (progress == -1) {
		uploadProgress.innerHTML = "上传出错：" + progress;
        //layer.msg("上传出错：" + progress, {icon: 5, shift: 6});
	} else if (progress == 100) {
		uploadProgress.innerHTML = "进度：100% 上传完成";
        //layer.msg("进度：100% 上传完成", {icon: 5, shift: 6});
	} else {
		uploadProgress.innerHTML = "进度：" + progress + "%";
        //layer.msg("进度：" + progress + "%", {icon: 1, shift: 6});
	}
}


/**
 * 选择课件格式 控制页面效果的切换
 */
function chooseFileType(){
	////先隐藏所有
	$(".tr_fileType_control").hide();
	var kpointType=$("#courseKpointKpointType").val();//节点类型
	//课件类型
	var fileType= $.trim($("#fileType").val());

	if(kpointType==1){//章节类型 处理显示
		//输入地址显示
		$("#videourl").parent().parent().show();
		$(".urlInput").show();
		$("#trPlayTimes").hide();//播放次数
		$("#videourl").removeAttr("readonly");

		$("input[name='courseKpoint.free']:eq(0)").removeAttr("disabled");
		if(fileType=='VIDEO'){//选择视频格式
			//$("#timeLongTr").show();
			$(".videoType").show();
			//$('#videotype').val('CC');
			$(".fileupload").hide();
			var videoType=$("#videotype").val();
			if(videoType=="CC"){
				$("#videoUrlTitle").html("CC播放地址:");
			}else{
				$("#videoUrlTitle").html("视频地址:");
			}
			//视频类型显示
			$("#courseKpointVideoType").parent().parent().show();
			videoTypeChange();//视频类型改变
			$("#isfree").parent().show();//显示免费 收费
		}else if(fileType=='AUDIO'){//选择音频格式
			//$("#timeLongTr").show();
			$(".videoType").hide();
			$("#videoUrlTitle").html("音频地址:");
			$("#isfree").parent().hide();//隐藏免费 收费
			//默认不试听
			$("input[name='courseKpoint.free']:eq(0)").attr("disabled","disabled");
			$("input[name='courseKpoint.free']:eq(1)").prop("checked","checked");
			$(".fileupload").show();
			$("#timeLongTr").show();
			$("#fileUpLoader").hide();
		}else if(fileType=='TXT'){//选择文本格式
			$(".videoType").hide();
			$(".urlInput").hide();
			$(".fileupload").hide();
			$(".txtContent").show();
			$("#isfree").parent().hide();//隐藏免费 收费
			//默认不试听
			$("input[name='courseKpoint.free']:eq(0)").attr("disabled","disabled");
			$("input[name='courseKpoint.free']:eq(1)").prop("checked","checked");
			//$("#pageCountTr").show();
			//$("#timeLongTr").hide();
		}else if(fileType=='ATLAS'){//选择图片集格式
			$(".videoType").hide();
			$(".urlInput").hide();
			$(".fileupload").hide();
			$(".imgAtlas").show();
			$("#isfree").parent().hide();//隐藏免费 收费
			//默认不试听
			$("input[name='courseKpoint.free']:eq(0)").attr("disabled","disabled");
			$("input[name='courseKpoint.free']:eq(1)").prop("checked","checked");
			//$("#pageCountTr").show();
			//$("#timeLongTr").hide();
			$("#imgAtlas").html('');
		}else if(fileType=="PDF"){//选择文档格式
			$(".videoType").hide();
			$("#videoUrlTitle").html("文档地址:");
			$("#isfree").parent().hide();//隐藏免费 收费
			//默认不试听
			$("input[name='courseKpoint.free']:eq(0)").attr("disabled","disabled");
			$("input[name='courseKpoint.free']:eq(1)").prop("checked","checked");
			$(".fileupload").show();
			$("#audioUpLoader").hide();
			//$("#pageCountTr").show();
			//$("#timeLongTr").hide();
			$("#videourl").attr("readonly",true);
		}else if(fileType=="LIVE"){//选择直播格式
			$(".videoType").hide();
			$("#fileType").parent().parent().hide();
			$("#videoUrlTitle").html("直播地址:");
			$("#isfree").parent().hide();//隐藏免费 收费
			//默认不试听
			$("input[name='courseKpoint.free']:eq(0)").attr("disabled","disabled");
			$("input[name='courseKpoint.free']:eq(1)").prop("checked","checked");
			$(".liveTr").show();
			$("#teacherTr").show();
			//直播供应商下拉改变
			supplierChange();
		}else if(fileType=="EXERCISES"){//选择课后作业
			$(".videoType").hide();
			$(".urlInput").hide();
			$(".fileupload").hide();
			$("#isfree").parent().hide();//隐藏免费 收费
			$("#trPlayTimes").hide();
			//默认不试听
			$("input[name='courseKpoint.free']:eq(0)").attr("disabled","disabled");
			$("input[name='courseKpoint.free']:eq(1)").prop("checked","checked");
			$("#ExercisesTr").show();
		}
	}
}


/**
 * 删除图片集图片
 * @param obj
 */
function delimg(obj,imageUrl){
	$(obj).parent().remove();
	$("#pageCount").val($(".atlasImage").length);
	deleteFile(imageUrl);
}

/*
 * 直播供应商 下拉改变
 */
function supplierChange(){
	var supplier=$("#supplier").val();
	if(supplier=="gensee"){
		$(".genseeLiveTr").show();
	}else{
		$(".genseeLiveTr").hide();
	}
}

/**
 * 创建直播课堂
 * @param type 页面显示的单选还是多选 checkbox多选（默认），radio单选
 */
function createGensee(){
	window.open(baselocation+'/admin/liveGensee/toadd?liveId='+$("input[name='courseKpoint.kpointId']").val() ,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=600');
}

var timer;
var winyunInxeduOpen;


/**
 * 关闭窗口后 从cookie中获取数据
 * @constructor
 */
function IfInxeduVideoWindowClosed() {
	if (winyunInxeduOpen.closed == true) {
		window.clearInterval(timer);
		$("#videourl").val(unescape(getCookie("yun_inxedu_videoUrl")));
		setCookie("yun_inxedu_videoUrl","");
		var exp = new Date();
		exp.setTime(exp.getTime() + 60 * 1000);
		document.cookie = "is_yun_inxedu_open_window" + "=false;expires="+ exp.toGMTString() + ";path=/;domain=.inxedu.com";//因酷云判断 cookie的值 ，来是否显示 选择按钮
	}
}

/**
 * window open 指定url ，定时关闭
 */
function yunInxeduOpenWindow(openUrl,callBack) {
	winyunInxeduOpen=window.open(baselocation+'/admin/yun/inxedu?url='+openUrl,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=600');
	timer=window.setInterval(callBack,500);

	var exp = new Date();
	exp.setTime(exp.getTime() + 3600 * 1000);
	document.cookie = "is_yun_inxedu_open_window" + "=true;expires="+ exp.toGMTString() + ";path=/;domain=.inxedu.com";//因酷云判断 cookie的值 ，来是否显示 选择按钮
}