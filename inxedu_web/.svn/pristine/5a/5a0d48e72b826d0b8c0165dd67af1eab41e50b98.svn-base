<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><c:set var="courseSellType" value="课程"></c:set>
	<c:if test="${sellType=='LIVE'}">
		<c:set var="courseSellType" value="直播"></c:set>
	</c:if>${courseSellType}节点</title>
<link type="text/css" rel="stylesheet" href="${ctx}/static/common/ztree/css/zTreeStyle.css" />
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/kpoint/kpoint.js"></script>
<c:if test="${sellType=='LIVE' and serviceSwitch.live=='ON'}">
	<script type="text/javascript" src="${ctx}/static/live/live_gensee.js"></script>
</c:if>
<script type="text/javascript" src="${ctx}/static/admin/teacher/select_teacher_list.js"></script>
<script type="text/javascript" src="${ctx}/static/common/uploadify/ccswfobject.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css" />
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
	<%--百度编译器--%>
	<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" src="${ctx}/static/common/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<%--时间控件--%>
	<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
	<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script src="${ctx}/kindeditor/kindeditor.js"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js"></script>

<style type="text/css">
#swfDiv embed {
	position: absolute;
	z-index: 1;
}
#swfDiv{*position:absolute; z-index:2;}
</style>
<script type="text/javascript">
	layui.use(['form', 'laydate'], function () {
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function (data) {
			toBaijiayunType();
		});
		form.on('select(roomType)', function (data) {
			baijiayunRoomType();
		});

		form.on('submit(*)', function(data){
			//执行提交方法
			addSubmit()

		});
		form.on('submit(update)', function(data){
			//执行提交方法
			updateRoomInfo()
		});

		//各种基于事件的操作，下面会有进一步介绍
	});
	/*百家云房间id 防止用户修改视频地址找打不到 房间id*/
	var courseId = ${courseId};
		var ztree=[];
		ztree=${kpointList};
		//课程类型：COURSE(课程) LIVE(直播)
		var sellType="${sellType}";
		$(function(){
			showKpointZtree(ztree);
			//音频上传控件
			uploadAudio('audioupload','videourl','audioQueue');
			//文档上传控件
			uploadFileLoad('fileupload','videourl','fileQueue');
			//文本编辑框
			initKindEditor_addblog('content', '80%', 350,'courseContxt','true');
		});

		/**
		 * 音频上传控件加载
		 * @param controlId
		 * @param ids
		 * @param errId
		 */
		function uploadAudio(controlId,ids,errId){
			$("#"+controlId).uploadify({
				'uploader' : '/static/common/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
				'script'  :'<%=uploadfileUrl%>',
				'scriptData':{"param":"courseKpoint/audio"},
				'queueID' : 'audioQueue', //文件队列ID
				'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
				'auto' : true, //选定文件后是否自动上传
				'multi' :false, //是否允许同时上传多文件
				'hideButton' : false,//上传按钮的隐藏
				'buttonText' : 'Browse',//默认按钮的名字
				'buttonImg' : '/static/common/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
				'width' : 105,
				'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
				'sizeLimit' : 51200000,//控制上传文件的大小
				'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
				'fileDesc' : '支持格式:mp3',//出现在上传对话框中的文件类型描述
				'fileExt' : '*.mp3',//支持的格式，启用本项时需同时声明fileDesc
				'folder' : '/upload',//您想将文件保存到的路径
				'cancelImg' : '/static/common/uploadify/cancel.png',
				onSelect : function(event, queueID,fileObj) {
					fileuploadIndex = 1;
					$("#"+errId).html("");
					if (fileObj.size > 51200000) {
                        layer.msg("文件太大最大限制51200kb", {icon: 5, shift: 6});
						return false;
					}
				},
				onComplete : function(event,queueID, fileObj, response,data) {
					$("#"+ids).val(response);
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#"+errId).html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
				}
			});
		}

		/**
		 * 文档上传控件加载
		 * @param controlId
		 * @param ids
		 * @param errId
		 */
		function uploadFileLoad(controlId,ids,errId){
			$("#"+controlId).uploadify({
				'uploader' : '${ctximg}/static/common/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
				'script' :'<%=pdfUploadAndImg%>',
				'scriptData':{"param":"courseKpoint/pdf","width":"128","height":"78"},
				'queueID' : 'fileQueue', //文件队列ID
				'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
				'auto' : true, //选定文件后是否自动上传
				'multi' :false, //是否允许同时上传多文件
				'hideButton' : false,//上传按钮的隐藏
				'buttonText' : 'Browse',//默认按钮的名字
				'buttonImg' : '/static/common/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
				'width' : 105,
				'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
				'sizeLimit' : 51200000,//控制上传文件的大小
				'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
				'fileDesc' : '支持格式:pdf',
				'fileExt'  : '*.pdf;',
				'cancelImg' : '/static/common/uploadify/cancel.png',
				onSelect : function(event, queueID,fileObj) {
					fileuploadIndex = 1;
					$("#"+errId).html("");
					if (fileObj.size > 51200000) {
                        layer.msg("文件太大最大限制51200kb", {icon: 5, shift: 6});
						return false;
					}
				},
				onComplete : function(event,queueID, fileObj, response,data) {
					//updateFunctionImageDelete("videourl","swf");
					//返回地址不能为空
					if(response==null||response==""){
						$("#messagePDF").html("上传失败请刷新重试");
						return;
					}
					var jsonobj = JSON.parse(response);
					//pdf地址
					response = jsonobj.pdfUrl;

					//逗号隔开的地址转出数组
					var urlList = jsonobj.pngUrlStrs.split(",");
					//逗号隔开的缩列图地址转出数组
					var urlListTB = jsonobj.pngUrlStrsTB.split(",");

					var pdfPngUrlStr = "";//原图地址
					var pdfPngUrlTBStr = "";//缩列图地址
					for(var i=0;i<urlList.length;i++){
						if(i==0){
							pdfPngUrlStr=urlList[i];
							pdfPngUrlTBStr = urlListTB[i];
						}else{
							pdfPngUrlStr+=","+urlList[i];
							pdfPngUrlTBStr +="," + urlListTB[i];
						}
					}

					$("#atlas").val(pdfPngUrlStr);
					$("#atlasThumbnail").val(pdfPngUrlTBStr);
					$("#"+ids).val(response);
					$("#pageCount").val(urlList.length);
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#"+errId).html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
				}
			});
		}

		//图片集上传控件
		KindEditor.ready(function(K) {
			var editor = K.editor({
				allowFileManager : true,
				uploadJson : uploadShrinkDiagram+'&param='+"courseKpoint/atlas"+'&fileType=jpg,gif,png,jpeg&pressText=inxedu&width=128&height=78',// 图片上传路径
			});
			K('#J_selectImage').click(function() {
				editor.loadPlugin('multiimage', function() {
					editor.plugin.multiImageDialog({
						clickFn : function(urlList) {
							var div = K('#J_imageView');
							//div.html('');
							K.each(urlList, function(i, data) {
								div.append('<div style="float: left;margin-top: 15px;margin-right: 10px;"><img src="' + data.smallurl + '" alt="" width="120" height="78">' +
										'<input type="hidden" class="atlasImage" name="courseKpoint.atlas" value="' + data.url + '">' +
										'<input type="hidden" class="" name="courseKpoint.atlasThumbnail" value="' + data.smallurl + '">' +
										'<a href="javascript:void(0)" class="remove-pic-item" onclick="delimg(this,\'' + data.url + '\')">X</a></div>');
							});
							editor.hideDialog();
							$("#pageCount").val($(".atlasImage").length);
						}
					});
				});
			});
		});
/*显示创建百家云房间弹窗*/
function createBaijiayunLive() {
	layer.open({
		title:'创建百家云直播',
		type: 1,
		shade: false,
		area: '500px',
		maxmin: false,
		content: $('#createBaijiayunLive')
	});
}
/*添加直播间*/
function addSubmit() {
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/admin/liveRoom/add",
		data:$("#liveRoom").serialize(),
		async:false,
		success:function(result){
			if (result.success==true){
				layer.closeAll();
				$("#videourl").val(result.message);
				$("#liveBeginTime").val($("input[name='startTime']").val());
				$("#liveEndTime").val($("input[name='endTime']").val());
				$(".baijiayunLive").show();
			}else {
                layer.msg(result.message, {icon: 5, shift: 6});
			}
		}
	})
}
/*更新直播间信息*/
function updateRoomInfo() {
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/admin/liveRoom/updateLiveRoom",
		data:$("#updateLiveRoom").serialize(),
		async:false,
		success:function(result){
			if (result.success==true){
				layer.closeAll();
				$("#liveBeginTime").val($("input[name='liveRoom.startTime']").val());
				$("#liveEndTime").val($("input[name='liveRoom.endTime']").val())
			}else {
                layer.msg(result.message, {icon: 5, shift: 6});
			}
		}
	})
}
/*打开更新直播间信息弹窗*/
function openUpdateBaijiayunInfo() {
	$.ajax({
		url:"${ctx}/admin/liveRoom/selectRoomInfo",
		data:{"id":$("#videourl").val()},
		type: "post",
		dataType:"json",
		success:function(data){
			if (data.success==true){
				$("input[name='liveRoom.maxUsers']").val(data.entity.max_users);
				$("input[name='liveRoom.roomTitle']").val(data.entity.title);
				$("input[name='liveRoom.startTime']").val(data.entity.start_time);
				$("input[name='liveRoom.endTime']").val(data.entity.end_time);
				$("input[name='liveRoom.roomId']").val(data.entity.room_id);
				layer.open({
					title:'更新百家云直播',
					type: 1,
					shade: false,
					area: '500px',
					maxmin: false,
					content: $('#updateBaijiayunLive')
				});
			}else {
			    msgshow(data.message,"false","1000")
            }

		}
	});
}
/*改变百家云打开的方式 网页或客户端*/
function toBaijiayunType() {
	var info = $("#videourl").val();
	var arry = info.split("|");
	if (arry.length>1){
		$("#videourl").val(arry[0]+"|"+$("#roomType").val())
	}else {
		/*把进入房间类型拼到地址*/
		$("#videourl").val($("#videourl").val()+"|"+$("#roomType").val());
	}

}
	/*老师进入直播间*/
	function toLiveRoom(role,type) {
		$.ajax({
			url:"${ctx}/admin/liveRoom/toLiveRoom",
			data:{"id":$("#videourl").val(),"role":role,"type":type},
			type: "post",
			dataType:"json",
			success:function(data){
			    if(type=="WEB"){
                    window.open(data.message+"");
                }else {
                    window.location.href = data.message+"";
                }
			}
		});
	}
	/**
	 * 选择视频
	 **/
	function selectBaijiayunRoom(){
		window.open('${ctx}/admin/liveRoom/selectBaijiayunRoom','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	}/**
	 * 选择频道
	 **/
	function selectChan(){
		window.open('${ctx}/admin/video/showchanlist','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=1000,height=600');
	}
	function addBaijiayunRoom(roomId) {
		$.ajax({
			url:"${ctx}/admin/liveRoom/selectRoomFromList",
			data:{"roomId":roomId},
			type: "post",
			dataType:"json",
			success:function(data){
				$("#videourl").val(data.entity.room_id);
				$("#liveBeginTime").val(data.entity.start_time);
				$("#liveEndTime").val(data.entity.end_time);
				$(".baijiayunLive").show();
			}
		});
	}
	function addChanNum(id) {
        $("#videourl").val("rtmp://127.0.0.1:1936/live/"+id);
	}
	/*百家云房间类型*/
	function baijiayunRoomType() {
		if ($("#selectRoomType").val()=="1"){
			$("input[name='maxUsers']").val(1);
			$("input[name='maxUsers']").attr("readonly","true");
            $("#oneToOne").show();
			layui.form().render()
		}else {
			$("input[name='maxUsers']").removeAttr("readonly");
            $("#oneToOne").hide();
            layui.form().render()

		}
	}
	</script>
</head>
<body>
	<div class="">
		<table class="layui-table">
			<thead>
				<tr>
					<th colspan="2" align="left">
							<span>${courseSellType}节点管理</span>
						<font color="red">*视频节点只支持二级</font>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="40%">
						<div class="numb-box">
						<fieldset class="layui-elem-field" class="kpotionBox" style="height: 600px;">
							<legend  style="width: 200px;">
								<a href="${ctx}/admin/cou/list">
								<span>${courseSellType}节点管理</span>
								</a>
								&gt;
								<span>节点列表</span>
							</legend>
							<div class="">
								<div class="commonWrap">
									<div id="kpointList" class="ztree"></div>

                                    <div class="mt20">
									<a title="创建视频节点" onclick="addaKpoint(${courseId});" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
										<span></span>
										创建视频节点
									</a>
									<a title="取消选中" onclick="ztreeCancelSelectedNode();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
										<span></span>
										取消选中
									</a>
                                    </div>

								</div>
							</div>
						</fieldset></div>

					</td>
					<td width="60%">
						<div class="numb-box">
						<fieldset class="layui-elem-field" class="kpotionBox"  style="overflow: auto;margin-left: 50px;height: 600px;">
							<legend>
								<span>编辑节点</span>
							</legend>
							<div id="updateWin" style="display: none;" class="mt20">
								<div class="commonWrap">
									<form id="updateForm">
										<input type="hidden" name="courseKpoint.kpointId" />
										<input type="hidden" id="courseId" />
										<input type="hidden" name="courseKpoint.atlas" value="" id="atlas" />
										<input type="hidden" name="courseKpoint.atlasThumbnail" value="" id="atlasThumbnail" />
										<table style="line-height: 35px;" class="layui-table">
											<tr>
												<td>节点名称:</td>
												<td style="text-align: left;">
													<input name="courseKpoint.name" class="layui-input layui-input-6"type="text" />
												</td>
											</tr>
											<tr>
												<td>节点类型:</td>
												<td style="text-align: left;">
													<select id="courseKpointKpointType"class="layui-input layui-input-6" name="courseKpoint.kpointType" onchange="kpointTypeChange()">
														<option value="0">目录</option>
														<option value="1">章节</option>
													</select>
												</td>
											</tr>
											<tr style="display:none" class="tr_kpoint">
												<td>课件类型:</td>
												<td style="text-align: left;">
													<select id="fileType" name="courseKpoint.fileType" class="layui-input layui-input-6" onchange="chooseFileType()">
														<option value="VIDEO">视频</option>
														<option value="AUDIO">音频</option>
														<option value="PDF">文档</option>
														<option value="TXT">文本</option>
														<option value="ATLAS">图片集</option>
														<c:if test="${serviceSwitch.exam=='ON'}">
															<option value="EXERCISES">课后作业</option>
														</c:if>
														<c:if test="${sellType=='LIVE' and serviceSwitch.live=='ON'}">
															<option value="LIVE">直播</option>
														</c:if>
														<%--<option value="BaoLiWeiShi">保利威视</option>--%>
													</select>
												</td>
											</tr>
											<tr style="display:none" class="tr_kpoint tr_fileType_control videoType ">
												<td>视频类型:</td>
												<td style="text-align: left;">
													<select id="courseKpointVideoType" class="layui-input layui-input-6" name="courseKpoint.videoType" onchange="videoTypeChange()">
														<%--<option value="">--请选择--</option>--%>
														<%--<option value="INXEDUVIDEO">因酷云</option>--%>
														<option value="INXEDUCLOUD">因酷云</option>
														<%--<option value="BaoLiWeiShi">保利威势</option>--%>
														<%--<option value="baofeng">暴风</option>--%>
														<option value="baijiayun">百家云</option>
														<option value="IFRAME">其他</option>
														<option value="CC">CC视频</option>
													</select>
												</td>
											</tr>
											<tr class="uploadCCVideo tr_kpoint tr_fileType_control" style="display:none">
												<td>上传CC视频:</td>
												<td style="text-align: left;">
													<div id="swfDiv" style="*position:absolute; z-index:2;float:left;z-index: 1000;cursor: pointer; margin-top:5px;"></div><input type="button" value="上传" id="btn_width" style="width: 80px; height: 25px;"/>
													<input type="hidden" id="upload_title" minlength="0">
													<input type="hidden"  id="upload_tag" minlength="0">
													<input type="hidden"  id="upload_desp" minlength="0">
													<script type="text/javascript">
														// 加载上传flash ------------- start
														var swfobj=new SWFObject('http://union.bokecc.com/flash/api/uploader.swf', 'uploadswf', '80', '25', '8');
														swfobj.addVariable( "progress_interval" , 1);	//	上传进度通知间隔时长（单位：s）
														swfobj.addVariable( "notify_url" , "");	//	上传视频后回调接口
														swfobj.addParam('allowFullscreen','true');
														swfobj.addParam('allowScriptAccess','always');
														swfobj.addParam('wmode','transparent');
														swfobj.write('swfDiv');
														// 加载上传flash ------------- end
													</script>
												</td>
											</tr>
											<tr class="selYunInxeduVideo tr_kpoint tr_fileType_control" style="display:none">
												<td>选择视频:</td>
												<td style="text-align: left;">
													<input type="button" value="选择视频" onclick='yunInxeduOpenWindow("/video/list",IfInxeduVideoWindowClosed)' class="layui-btn layui-btn-small ">
												</td>
											</tr>
											<tr class="tr_kpoint tr_fileType_control uploadCCVideo" style="display:none">
												<td>上传进度:</td>
												<td style="text-align: left;">
													<span id="up">无</span>
												</td>
											</tr>
											<tr style="display:none" class="tr_kpoint tr_fileType_control urlInput">
												<td id="videoUrlTitle">视频地址:</td>
												<td style="text-align: left;">
													<input type="text"class="layui-input layui-input-6" onfocus=this.blur() name="courseKpoint.videoUrl" id="videourl" value=""/>
													<button onclick="selectChan()" class="layui-btn layui-btn-small" type="button">选择频道</button>
												</td>
											</tr>
											<tr style="display:none" class="tr_kpoint genseeLiveTr">
												<td>讲师加入地址:</td>
												<td style="text-align: left;">
													<input type="text" id="teacherJoinUrl" value="" class="layui-input layui-input-6"/>(只显示)
												</td>
											</tr>
											<tr id="audioUpLoader" class="tr_kpoint tr_fileType_control fileupload" style="display: none">
												<td>上传音频:</td>
												<td>
													<input  type="button" value="上传" id="audioupload" />
													<font color="red vam ml10">请上传MP3文件,音频课件将使用服务器自身带宽</font>
													<div id="audioQueue" class="mt10"></div>
												</td>
											</tr>
											<tr id="fileUpLoader" class="tr_kpoint tr_fileType_control fileupload" style="display: none">
												<td>请上传课件:</td>
												<td>
													<input type="button" id="fileupload" value="上传"/>
													<font color="red vam ml10">pdf课件将使用服务器自身带宽,文件越大上传越慢,请耐心等待</font>
													<div id="fileQueue" class="mt10"></div>
												</td>
											</tr>
											<tr class="tr_kpoint tr_fileType_control videoTr txtContent" style="display: none;">
												<td>文本内容:</td>
												<td><textarea id="content" name="courseKpoint.content" rows="" cols=""></textarea></td>
											</tr>
											<tr class="tr_kpoint tr_fileType_control videoTr imgAtlas" style="display: none;">
												<td>课程图片:</td>
												<td>
													<input type="button" id="J_selectImage" class="layui-btn layui-btn-small" value="批量上传" /><input style="margin-left:10px" type="button" class="layui-btn layui-btn-small" onclick="$('#J_imageView').html('')" value="清空" /><font color="red vam ml10">按住Ctrl或Shift可多选图片,每张图片不得大于100kb</font><font color="red">(图片显示顺序以上传排列顺序为准)</font>

													<div id="J_imageView"></div>
												</td>
											</tr>
											<tr id="pageCountTr" class="tr_kpoint tr_fileType_control videoTr" style="display: none">
												<td>页数/张数:</td>
												<td><input id="pageCount" type="text" name="courseKpoint.pageCount" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
											</tr>

											<%--直播--%>
											<tr class="tr_kpoint liveTr" style="display: none">
												<td>直播开始时间:</td>
												<td>
													<input type="text" class="layui-input layui-input-6" value="" name="courseKpoint.liveBeginTime" id="liveBeginTime" onclick="layui.laydate({elem: this, istime: true,format: 'YYYY-MM-DD hh:mm:ss'})"/>
												</td>
											</tr>
											<tr class="tr_kpoint liveTr" style="display: none">
												<td>直播结束时间:</td>
												<td>
													<input type="text" class="layui-input layui-input-6" value="" name="courseKpoint.liveEndTime" id="liveEndTime" onclick="layui.laydate({elem: this, istime: true,format: 'YYYY-MM-DD hh:mm:ss'})"/>
												</td>
											</tr>
											<tr class="tr_kpoint liveTr" style="display: none">
												<td>直播供应商:</td>
												<td>
													<select id="supplier" name="courseKpoint.supplier" onchange="supplierChange()" class="layui-input layui-input-6">
														<option value="gensee">展示互动</option>
														<option value="inxedu_cloud">因酷云</option>
														<option value="baijiayun">百家云</option>
														<option value="">其他</option>
													</select>
												</td>
											</tr>
											<tr class="tr_kpoint genseeLiveTr" style="display:none">
												<td>创建直播课堂:</td>
												<td style="text-align: left;">
													<input type="hidden" name="courseKpoint.teacherId" value="0" />
													<input type="button" value="创建" id="addateGenseeBtn" onclick="createGensee()" class="layui-btn layui-btn-small layui-btn-danger">
													<input type="button" value="修改" id="updateGenseeBtn" onclick="" class="layui-btn layui-btn-small layui-btn-danger" style="display: none;">
													<input type="button" value="删除" id="delGenseeBtn" onclick="" class="layui-btn layui-btn-small layui-btn-danger" style="display: none;">
												</td>
											</tr>
											<tr class="tr_kpoint baijiayunLiveTr" style="display:none">
												<td>创建直播课堂:</td>
												<td style="text-align: left;">
													<input type="button" value="创建" onclick="createBaijiayunLive()" class=" layui-btn layui-btn-small layui-btn-danger">
													<input type="button" value="选择已有" onclick="selectBaijiayunRoom()" class=" layui-btn layui-btn-small layui-btn-danger">
													<input type="button" value="修改"  style="display: none" onclick="openUpdateBaijiayunInfo()" class="baijiayunLive layui-btn layui-btn-small layui-btn-danger" >

												</td>
											</tr>
											<tr class="baijiayunLive"style="display:none">
												<td>讲师进入直播</td>
												<td class="td-actions" >
													<input type="button" value="网页版"  onclick="toLiveRoom(1,'WEB')" class="layui-btn layui-btn-small layui-btn-danger" >
													<input type="button" value="客户端"  onclick="toLiveRoom(1,'APP')" class="layui-btn layui-btn-small layui-btn-danger" >
												</td>
											</tr>
											<tr class="baijiayunLive"style="display:none">
												<td>房间类型</td>
												<td class="td-actions">
													<select class="layui-input layui-input-6"  name="courseKpoint.openType"lay-filter="filter" id="openType">
														<option value="WEB">网页版</option>
														<option value="APP">客户端</option>
													</select>
												</td>
											</tr>
											<tr class="tr_kpoint yunInxeduTr" style="display:none">
												<td>选择直播:</td>
												<td style="text-align: left;">
													<input type="button" value="选择直播" id="" onclick='yunInxeduOpenWindow("/liveRoom/list",IfInxeduWindowClosed)' class="layui-btn layui-btn-small layui-btn-danger">
												</td>
											</tr>
											<%--end 直播--%>

											<tr style="display: none">
												<td>排序:</td>
												<td>
													<input type="text" value="0" name="courseKpoint.sort" />
												</td>
											</tr>
											<tr  id="trPlayTimes" style="display: none" >
												<td>播放次数:</td>
												<td>
													<input type="text" value="0" name="courseKpoint.playCount"  readonly=""readonly/>
												</td>
											</tr>
											<tr class="tr_kpoint tr_fileType_control" id="timeLongTr">
												<td>播放时间:</td>
												<td>
													<input type="text" value="00:00" name="courseKpoint.playTime" class="layui-input layui-input-6"/>
												</td>
											</tr>
											<tr class="tr_kpoint tr_is_kpoint">
												<td>是否免费:</td>
												<td style="text-align: left;" id="isfree">
													<input type="radio" name="courseKpoint.free" value="1" />
													是
													<input type="radio" name="courseKpoint.free" value="2" checked />
													否

													<font color="red vam ml10">文档、文本格式、图片集、音频等格式暂不支持试听</font>
												</td>
											</tr>
											<%--<tr class="tr_kpoint" id="teacherTr" >
												<td>视频讲师:</td>
												<td style="text-align: left;">
													<input type="hidden" name="courseKpoint.teacherId" value="0" />
													<p id="teacher" style="margin: 0 0 0em;"></p>
													<input type="button" value="选择老师" onclick="selectTeacher('radio')" class="layui-btn layui-btn-small layui-btn-danger">
												</td>
											</tr>--%>
											<tr class="tr_fileType_control" id="ExercisesTr">
												<td>课后作业:</td>
												<td style="text-align: left;">
													<input type="hidden" value="" id="examQuestionIds"/><%--所有试题id--%>
													<input type="hidden" value="" id="examPaperId" /><%--试卷id--%>
													<select id="examType" class="layui-input layui-input-6">
														<option value="0">试卷</option>
														<option value="1">试题</option>
													</select>
													<button onclick="selectExam()" type="button" class="layui-btn layui-btn-small">选择</button>
													<p id="exercises" style="margin: 0 0 0em;"></p>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<button class="layui-btn layui-btn-small layui-btn-danger" style="float: left;" onclick="updateKpoint()" type="button">确定</button>
													<button class="layui-btn layui-btn-small layui-btn-danger" style="float: left;" type="button">取消</button>
												</td>
											</tr>
										</table>
									</form>
								</div>
							</div>
						</fieldset></div>
					</td>
				</tr>
			<tr>
				<td align="center" colspan="2">
					<%--<a title="确定" class="layui-btn layui-btn-danger" href="${ctx}/admin/cou/list">
						<span></span>
						确定
					</a>--%>
				<a title="返回" onclick="history.go(-1)" class="layui-btn layui-btn-primary" href="javascript:void(0)">
					<span></span>
					返回
				</a>
				</td>
			</tr>
			</tbody>
		</table>

	</div>

	<!-- 修改视频节点信息窗口，结束 -->
	<div id="createBaijiayunLive" style="display: none;">
		<div class="numb-box">
			<form class="layui-form" id="liveRoom" method="post" >
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">房间标题</label>
					<div class="layui-input-inline">
						<input  name="roomTitle" class="layui-input" lay-verify="required" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">房间最大人数</label>
					<div class="layui-input-inline">
						<input  name="maxUsers" class="layui-input" lay-verify="required" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">开始时间</label>
					<div class="layui-input-inline">
						<input  name="startTime" class="layui-input"onclick="layui.laydate({elem: this, istime: true,format: 'YYYY-MM-DD hh:mm:ss'})" lay-verify="required" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">结束时间</label>
					<div class="layui-input-inline">
						<input  name="endTime" class="layui-input"onclick="layui.laydate({elem: this, istime: true,format: 'YYYY-MM-DD hh:mm:ss'})" lay-verify="required" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">类型</label>
					<div class="layui-input-inline">
						<select name="type" id="selectRoomType" lay-filter="roomType" onchange="baijiayunRoomType()"  class="layui-input">
							<option value="2">班课</option>
							<option value="1">一对一课 </option>
						</select>
					</div>

				</div>
				<div class="layui-form-item"id="oneToOne" style="display: none">
					<label class="layui-form-label layui-form-label-w"></label>
					<div class="layui-input-inline c-999">
						一对一课学生麦克风会自动打开
					</div>

				</div>

				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-danger" lay-submit lay-filter="*" type="button">确定</button>
						<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
					</div>
				</div>
			</form>

		</div>
	</div>
	<!-- 更改房间信息 -->
	<div id="updateBaijiayunLive" style="display: none;">
		<div class="numb-box">
			<form class="layui-form" id="updateLiveRoom" method="post" >
				<input  name="liveRoom.roomId" type="hidden" >
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">房间标题</label>
					<div class="layui-input-inline">
						<input  name="liveRoom.roomTitle" class="layui-input" lay-verify="required" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">房间最大人数</label>
					<div class="layui-input-inline">
						<input  name="liveRoom.maxUsers" class="layui-input" lay-verify="required" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">开始时间</label>
					<div class="layui-input-inline">
						<input  name="liveRoom.startTime" class="layui-input"onclick="layui.laydate({elem: this, istime: true,format: 'YYYY-MM-DD hh:mm:ss'})" lay-verify="required" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">结束时间</label>
					<div class="layui-input-inline">
						<input  name="liveRoom.endTime" class="layui-input"onclick="layui.laydate({elem: this, istime: true,format: 'YYYY-MM-DD hh:mm:ss'})" lay-verify="required" >
					</div>
				</div>
				<%--<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">类型</label>
					<div class="layui-input-inline">
						<select name="type" class="layui-input">
							<option value="2">班课</option>
							<option value="1">一对一课 </option>
						</select>
					</div>
				</div>--%>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-danger" lay-submit lay-filter="update" type="button">确定</button>
						<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
					</div>
				</div>
			</form>

		</div>
	</div>
</body>
</html>