<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>添加腾讯云签名</title>
    <script type="text/javascript">
        layui.use(['form'], function () {
            var form = layui.form();
        });
        function formSubmit() {
            var text = $("#text").val();
            if (!text) {
                layer.msg("请输入标签内容", {icon: 5, shift: 6});
            } else {
                $.ajax({
                    url: baselocation + '/admin/qcloudSmsSign/ajax/add',
                    type: 'post',
                    dataType: 'json',
                    data: {"text": text},
                    success: function (result) {
                        if (result.entity.result == 0) {
                            $("#otherId").val(result.entity.data.id);
                            $("#addForm").submit();
                        } else {
                            layer.msg("添加失败，请按照模板格式添加标签", {icon: 5, shift: 6});
                        }
                    }
                });
            }
        }
    </script>
</head>
<body>

<fieldset class="layui-elem-field">
    <legend>
        <span>腾讯云签名管理</span>
        &gt;
        <span>添加腾讯云签名</span>
    </legend>
    <div class="layui-field-box">
       <div class="numb-box">
           <form class="layui-form" action="${ctx}/admin/qcloudSmsTemplate/add?type=sign" method="post" id="addForm">
               <input type="hidden" name="qcloudSmsTemplate.type" value="sign"/>
               <div class="layui-form-item">
                   <label class="layui-form-label layui-form-label-w">标签内容</label>
                   <div class="layui-input-block">
                       <input type="text" id="text" name="qcloudSmsTemplate.text" lay-verify="title" autocomplete="off" autofocus="autofocus" placeholder="请输入标签内容" class="layui-input layui-input-6">
                       <p class="hLh20 fsize12 c-red f-fM">短信签名内容不包含【】，例如：因酷网校</p>
                   </div>
               </div>
               <div class="layui-form-item" style="display: none">
                   <label class="layui-form-label layui-form-label-w">相关id</label>
                   <div class="layui-input-block">
                       <input type="text" name="qcloudSmsTemplate.otherId" id="otherId"/>
                   </div>
               </div>
               <div class="layui-form-item">
                   <div class="layui-input-block">
                       <input type="button" value="保 存" class="layui-btn layui-btn-danger" onclick="formSubmit()"/>
                   </div>
               </div>
           </form>
       </div>
    </div>
</fieldset>
</body>
</html>
