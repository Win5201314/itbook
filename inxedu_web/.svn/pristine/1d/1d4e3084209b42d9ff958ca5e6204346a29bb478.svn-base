<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>批量发送系统消息</title>
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
		var userEmails=$("#userEmails").val();
		if(userEmails==null || userEmails.trim()==""){
            layer.msg("请输入用户邮箱", {icon: 5, shift: 6});
			return false;
		}
		var content = $("#message").val();
		if(content==null||content.trim()==""){
            layer.msg("请填写消息内容在发送", {icon: 5, shift: 6});
			return false;
		}
		 $.ajax({
             url:"${ctx}/admin/user/letter/sendJoinGroupBatch",
             type:"post",
             data:{
            	 "content":content,
            	 "userEmails":userEmails
            	 },
             dataType:"json",
             success:function(result){
             	if(result.message=='success'){
             		KindEditor.html('#message', '');
                    layer.msg("发送成功", {icon: 1, shift: 6});
             	}else{
                    layer.msg(result.message, {icon: 5, shift: 6});
             	}
             }
         });
	}
</script>

</head>
<body  >
<div class="numb-box"><fieldset class="layui-elem-field">
	<legend>
		<span>系统消息</span>
		&gt;
		<span>批量发送系统消息</span>
	</legend>
	<div class="">
		<form action="${ctx}/admin/cou/addCourse" method="post" id="saveCourseForm">
			<input type="hidden" name="course.logo" />
				<p>
					<label for="sf"><span>
							<tt class="c_666 ml20 fsize12">
								（<font color="red">*</font>&nbsp;为必填项）
							</tt>
						</span></label>
					<span class="field_desc"></span>
				</p>
				<p>
					<label for="sf"><font color="red">*</font>&nbsp;批量选择用户:</label>
					<textarea name="" id="userEmails" name="userEmails"  style="width: 641px; height: 76px;" class="{required:true} sf"></textarea>
					<span class="">请输入要发送系统消息的用户邮箱,每个邮箱用逗号隔开,可从用户列表导出用户后,复制用户邮箱.</span>
				</p>
				<p>
					<label for="sf"><font color="red">*</font>&nbsp;消息内容:</label>
					<textarea name="" id="message"></textarea>
					<span class="field_desc"></span>
				</p>
				<p>
					<input type="button" value="发 送" class="layui-btn layui-btn-small" onclick="sendmessage()" />
					<input type="button" value="返 回" class="layui-btn layui-btn-small" onclick="history.go(-1);" />
				</p>
		</form>
	</div>
</fieldset></div>
</body>
</html>