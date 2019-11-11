<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送邮件消息</title>
    <%--百度编译器--%>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" src="${ctx}/static/common/jquery-form.js"></script>
<script type="text/javascript">
    layui.use(['form', 'laydate'], function(){
        var form = layui.form();
        var laydate = layui.laydate;
        form.on('select(filter)', function(data){
            if (data.value == 2) {
                $("#sendtime").show();
            } else {
                $("#sendtime").hide();
            }
        });
    });
        $(function () {
            initKindEditor_addblog('emalMessage', '100%', 200,'email','true');
        });

        function sendmessage() {
            var sendLinkss = $("#pepole").val();
            if (sendLinkss.trim() == '') {
                layer.msg("邮件接收人不能为空！", {icon: 5, shift: 6});
                return false;
            }
            var valL = sendLinkss.split(",").length - 1;
            var sendTitle = $("#title").val();
            if (sendTitle.trim() == '') {
                layer.msg("邮件标题不能为空！", {icon: 5, shift: 6});
                return false;
            }
           var sendInfo = UE.getEditor('emalMessage').getContent();
            if (sendInfo.trim() == '') {
                layer.msg("邮件内容不能为空！", {icon: 5, shift: 6});
                return false;
            }

            if (confirm('确定发送?') == false) {
                return false;
            }
            var startTime = $("#startTime").val();
            var type = $("#type").val();
            $.ajax({
                url: "${ctx}/admin/email/sendEmailMsg",
                data: {
                    "linksman": sendLinkss,
                    "content": sendInfo,
                    "startTime": startTime,
                    "type": type,
                    "title": sendTitle
                },  // 参数
                type: "post",
                async: false,
                dataType: "json",  //返回json数据
                success: function (result) {
                    if (result.message == '发送成功') {
                    	if(type==1){
                    		window.location.href = "/admin/email/progressbar?type=1";
                    	}else if(type==2){
                    		window.location.href = "/admin/email/sendEmaillist";
                    	}
                    } else {
                        layer.msg(result.message, {icon: 5, shift: 6});
                    }
                }
            });
        }
        //选择用户邮箱号
        function showNewwin() {
            window.open('${ctx}/admin/user/select_userlist/2', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
        }
        //显示 去重
        function addnewUserId(newUserPhoneArr) {
            var phoneIds = [];
            if ($("#pepole").val().trim() != "") {
                phoneIds = $("#pepole").val().split(",");
            }
            phoneIds = phoneIds.concat(newUserPhoneArr);
            phoneIds.sort();
            phoneIds = uniqueArray(phoneIds);
            $("#pepole").val(phoneIds);
        }
        function uniqueArray(a) {
            temp = [];
            for (var i = 0; i < a.length; i++) {
                if (!contains(temp, a[i])) {
                    temp.length += 1;
                    temp[temp.length - 1] = a[i];
                }
            }
            return temp;
        }
        function contains(a, e) {
            for (var j = 0; j < a.length; j++)
                if (a[j] == e)return true;
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
	                url:"${ctx}/admin/email/importMsgExcel/2", //请求url  
	                success:function(result){ //提交成功的回调函数  
	                	if(result.success==true){
                            layer.msg("导入成功", {icon: 1, shift: 6});
	                		$("#pepole").val(result.entity);
	                	}else{
                            layer.msg(result.message, {icon: 5, shift: 6});
	                	}
	                }  
	            });  
	            return false; //不刷新页面  
	        });  
	    });  
</script>

</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>邮件管理</span>
        &gt;
        <span>发送邮件</span>
    </legend>
    <form  class="layui-form" action="/admin/email/importMsgExcel/2" method="post" id="importP" enctype="multipart/form-data">
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-label-w">注意事项</label>
            <div class="layui-input-block">
                <div class="layui-inline">
                    <div class="layui-form-mid layui-word-aux">
                        批量导入&nbsp;&nbsp;&nbsp;&nbsp;<br />
                        1、必须是excel格式,详情请参照模版sheet1<br/>
                        2、格式不能有误<br/>
                        3、记录要挨着输入，不能有空行<br/>
                        4、导入excel批量导入<a href="/static/common/admin/masterplate/email.xls" class="c-red">示例模版</a>下载
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-form-mid layui-word-aux">
                        规则<br/>
                        1、邮箱格式:********@qq.com  <br/>
                        或者********@126.com,********@163.com,********@sina.com...等<br/>
                        2、发送流程：添加邮箱号-&gt;设置邮件内容&gt;提交发送<br/>
                        3、添加邮箱号时，查询后可以选择添加所选学员及添加所有学员，请慎重选择。<br/>
                        4、群发邮件最多不能超过1000条<br/>
                        5、定时邮件会有几分钟的延迟。
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label layui-form-label-w">联系人</label>
            <div class="layui-input-block layui-textarea-block">
                <textarea name="numerStr" class="layui-textarea"  id="pepole"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-label-w">选择</label>
            <input onclick="showNewwin()" class="layui-btn layui-btn-small" type="button" value="添加学员" />
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-label-w">批量导入</label>
            <input id="myFile" type="file" title="Excel导入" name="myFile" style="width: 150px;"/>
            <input onclick="importExcel()" class="layui-btn layui-btn-small" type="button" value="提交Excel"/>
        </div>
        <div class="layui-form-item  layui-form-text">
            <label class="layui-form-label layui-form-label-w">邮箱标题</label>
            <div class="layui-input-block">
                <input  class="layui-input layui-input-6" name="" type="text" id="title" />
            </div>
        </div>
        <div class="layui-form-item  layui-form-text">
            <label class="layui-form-label layui-form-label-w">正文内容</label>
            <div class="layui-input-block layui-textarea-block">
                <textarea name=""  id="emalMessage"></textarea>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">邮箱类型</label>
            <div class="layui-input-block layui-select-inline">
                <select name="type" id="type" lay-filter="filter">
                    <option value="1">正常</option>
                    <option value="2">定时</option>
                </select>
            </div>
        </div>
        <div id="sendtime" style="display:none" class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">发送时间</label>
            <div class="layui-input-block">
                <input type="date" readonly="readonly" name="sendDate" id="startTime"  onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input layui-input-6">
            </div>
        </div>
        <div class="layui-form-item ">
            <div class="layui-input-block">
                <input onclick="sendmessage()" class="layui-btn layui-btn-danger" type="button" value="发 送" />
                <input onclick="history.go(-1);" class="layui-btn layui-btn-primary" type="button" value="返回" />
            </div>
        </div>
    </form>
</fieldset>
</body>
</html>