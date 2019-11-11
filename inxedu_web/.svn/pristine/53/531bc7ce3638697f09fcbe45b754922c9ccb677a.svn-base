<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>发送短信消息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
	<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
	<script type="text/javascript" src="${ctx}/static/common/jquery-form.js"></script>
	<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
	<script type="text/javascript">
		layui.use(['form', 'laydate'], function(){
			var form = layui.form();
			var laydate = layui.laydate;
			form.on('select(filter)', function(data){
				changeType();
			});

		});
		function sendmessage(){
			var sendLinkss = $("#pepole").val();
			if(sendLinkss.trim()=='')
                layer.msg("短信接收人不能为空", {icon: 5, shift: 6});
				return false;
			}
			var valL = sendLinkss.split(",").length - 1;
			var sendInfo=$("#messageInfo").val();
			if(sendInfo.trim()==''){
                layer.msg("短信内容不能为空！", {icon: 5, shift: 6});
				return false;
			}
			if(sendInfo.length>70){
                layer.msg("短信内容过长，请按要求输入！", {icon: 5, shift: 6});
				return false;
			}
			var sendType=$("#type").val();
			var sendTime=$("#sendDate").val();
			if(sendType==2){
				var nowTime=new Date().getTime();
				var sendDate=Date.parse(sendTime.replace(/-/gi,"/"));
				if(sendTime==""||sendTime==null){
                    layer.msg("发送时间不能为空", {icon: 5, shift: 6});
					return false;
				}else if(sendDate<nowTime){
                    layer.msg("定时发送时间必须大于当前系统时间", {icon: 5, shift: 6});
					return false;
				}
			}
			if(confirm('确定发送?')==false){
				return false;
			}
			$.ajax({
				url : "${ctx}/admin/mobile/sendMsg",
				data : {
					"linksman" : sendLinkss,
					"content" : sendInfo,
					"sendType" : sendType,
					"sendTime" : sendTime
				},  // 参数
				type : "post",
				async : false,
				dataType : "json",  //返回json数据 
				success:function (result){
					if(result.message=='发送成功'){
						if(sendType==1){
							msgshow(result.message+"","true","1000");
						}else if(sendType==2){
							window.location.href = "/admin/mobile/sendMsglist";
						}
					}else{
						msgshow(result.message+"","false","1000");
					}

				}
			});
		}

		function changeType(){
			if($("#type").val()==1){
				$("#sendTr").hide();
			}else{
				$("#sendTr").show();
			}
		}

		//选择用户手机号
		function showNewwin(){
			window.open('${ctx}/admin/user/select_userlist/1','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
		}
		//显示 去重
		function addnewUserId(newUserPhoneArr){
			var phoneIds=[];
			if($("#pepole").val().trim()!=""){
				phoneIds=$("#pepole").val().split(",");
			}
			phoneIds = phoneIds.concat(newUserPhoneArr);
			phoneIds.sort();
			phoneIds = uniqueArray(phoneIds);
			$("#pepole").val(phoneIds);
		}
		function uniqueArray(a){
			temp = [];
			for(var i = 0; i < a.length; i ++){
				if(!contains(temp, a[i])){
					temp.length+=1;
					temp[temp.length-1] = a[i];
				}
			}
			return temp;
		}
		function contains(a, e){
			for(j=0;j<a.length;j++)if(a[j]==e)return true;
			return false;
		}

		function importExcel(){
			var myFile = $("#myFile").val();
			if(myFile.length <= 0){
                layer.msg("请选择导入内容", {icon: 5, shift: 6});
				return false;
			}
			$("#importP").submit();
		}

		//form 以ajax提交
		$(function() {
			$("#importP").submit(function(){
				$(this).ajaxSubmit({
					type:"post",  //提交方式
					dataType:"json", //数据类型
					url:"${ctx}/admin/email/importMsgExcel/1", //请求url
					success:function(result){ //提交成功的回调函数
						if(result.success==true){
                            layer.msg("导入成功", {icon: 1, shift: 6});
							$("#pepole").val(result.entity);
						}else{
                            layer.msg("result.message", {icon: 5, shift: 6});
						}
					}
				});
				return false; //不刷新页面
			});
		});
	</script>

</head>
<body >
	<fieldset class="layui-elem-field">
		<legend>
			<span>短信管理</span>
			&gt;
			<span>发送</span>
		</legend>
		<form  class="layui-form" action="/admin/email/importMsgExcel/1" method="post" id="importP" enctype="multipart/form-data">
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">注意事项</label>
				<div class="layui-input-block">
					<div class="layui-inline">
						<div class="layui-form-mid layui-word-aux">
							批量导入&nbsp;&nbsp;&nbsp;&nbsp;<br />
							1、必须是excel格式,详情请参照模版sheet1<br/>
							2、格式不能有误<br/>
							3、记录要挨着输入，不能有空行<br />
							4、导入excel批量导入<a href="/static/common/admin/masterplate/mobile.xls" class="c-red">示例模版</a>下载
						</div>
					</div>
					<div class="layui-inline">
						<div class="layui-form-mid layui-word-aux">
							规则<br/>
							1、手机号格式:132******** 或者132********,138********<br/>
							2、短信内容,不能超过70汉字或者英文字母 <br/>
							3、发送流程：添加手机号-&gt;设置短信内容&gt;提交发送<br/>
							4、添加手机号时，查询后可以选择添加所选学员及添加所有学员，请慎重选择。<br/>
							5、群发短信最多不能超过1000条
							6、定时短信会有几分钟的延迟，如有延迟请耐心等待。
						</div>
					</div>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label layui-form-label-w">联系人</label>
				<div class="layui-input-block layui-textarea-block">
					<textarea  id="pepole" name="numerStr" placeholder="请输入联系人" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">选择学员</label>
				<input onclick="showNewwin()" class="layui-btn layui-btn-small" type="button" value="选择学员"/>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">批量导入</label>
				<input id="myFile" type="file" title="Excel导入" name="myFile" style="width: 150px;"/>
				<input onclick="importExcel()" class="layui-btn layui-btn-small" type="button" value="提交Excel"/>
			</div>
			<div class="layui-form-item  layui-form-text">
				<label class="layui-form-label layui-form-label-w">发送内容</label>
				<div class="layui-input-block layui-textarea-block">
					<textarea name="message" class="layui-textarea" id="messageInfo"></textarea>
				</div>
			</div>
			<div class="layui-form-item ">
				<label class="layui-form-label layui-form-label-w">发送方式</label>
				<div class="layui-input-block layui-select-inline">
					<select id="type" name="type" onchange="changeType()" lay-filter="filter" autocomplete="off">
						<option value="1" selected="selected">正常</option>
						<option value="2">定时</option>
					</select>
				</div>
			</div>
			<div id="sendTr" style="display:none" class="layui-form-item ">
				<label class="layui-form-label layui-form-label-w">发送时间</label>
				<div class="layui-input-block">
					<input type="date" readonly="readonly" name="sendDate" id="sendDate"  onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input layui-input-6">
				</div>
			</div>

			<div class="layui-form-item ">
				<div class="layui-input-block">
					<input onclick="sendmessage()" class="layui-btn layui-btn-danger" type="button" value="发 送" />
					<input onclick="history.go(-1)" class="layui-btn layui-btn-primary" type="button" value="返 回" />
				</div>
			</div>
		</form>
	</fieldset>
</body>
</html>