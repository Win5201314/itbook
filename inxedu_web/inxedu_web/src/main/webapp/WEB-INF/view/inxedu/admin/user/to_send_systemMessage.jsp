<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>发送系统消息</title>
<script type="text/javascript" src="${ctx }/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript">
KindEditor.ready(function(K) {
	window.EditorObject = K.create('textarea[id="message"]', {
			resizeType  : 1,
	       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
	       allowPreviewEmoticons : false,
	       allowUpload : true,//允许上传 
	       syncType : 'auto',
	       urlType : 'domain',//absolute
	       newlineTag :'br',//回车换行br|p
	       uploadJson : '<%=keuploadSimpleUrl%>&param=question',//图片上传路径
	       allowFileManager : false,
	       afterBlur:function(){EditorObject.sync();}, 
	       items : ['emoticons']
	});
});
	function sendmessage(){
		var content = $("#message").val();
		if(content==null||content.trim()==""){
            layer.msg("请填写消息内容在发送", {icon: 5, shift: 6});
			return false;
		}
		 $.ajax({
             url:"${ctx}/admin/user/letter/sendJoinGroup",
             type:"post",
             data:{"content":content},
             dataType:"json",
             success:function(result){
             	if(result.message=='success'){
             		KindEditor.html('#message', '');
                    layer.msg("发送成功", {icon: 1, shift: 6});
             	}
             }
         });
	}
</script>

</head>
<body  >
	<fieldset class=layui-elem-field">
	<legend>
		<span>系统管理</span>
		&gt;
		<span>发送系统消息</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/cou/addCourse" method="post" id="saveCourseForm" class="layui-form">
			<input type="hidden" name="course.logo" />
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;发送系统消息</label>
				<div class="layui-input-block">
					<textarea id="message" data-rule="required;" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button onclick="sendmessage()" class="layui-btn layui-btn-small layui-btn-danger" type="button">发送</button>
					<button onclick="history.go(-1);" class="layui-btn layui-btn-small layui-btn-danger" type="button">返回</button>
				</div>
			</div>

		</form>
	</div>
</fieldset>
</body>
</html>