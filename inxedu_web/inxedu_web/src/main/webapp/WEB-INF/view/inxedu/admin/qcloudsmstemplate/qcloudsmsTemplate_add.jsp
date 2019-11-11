<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>添加腾讯云签名</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        function formSubmit() {
            var text = $("#text").val();
            var title = $("#title").val();
            var smsType = $("#smsType").val();
            smsType = 0;
            if (title == "") {
                layer.msg("请输入模板名称", {icon: 5, shift: 6});
            } else if (text == "") {
                layer.msg("请输入模板内容", {icon: 5, shift: 6});
            } else if (text.length > 450) {
                layer.msg("内容不得超过450个字符", {icon: 5, shift: 6});
            } else {
                $.ajax({
                    url: baselocation + '/admin/qcloudSmsTemlate/ajax/add',
                    type: 'post',
                    dataType: 'json',
                    data: {"text": text, "smsType": smsType},
                    success: function (result) {
                        if (result.entity.result == 0) {
                            $("#otherId").val(result.entity.data.id);
                            $("#addForm").submit();
                        } else {
                            layer.msg("添加失败，请按照模板格式添加内容", {icon: 5, shift: 6});
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
        <span>腾讯云模板管理</span>
        &gt;
        <span>添加腾讯云模板</span>
    </legend>
    <div class="layui-field-box">
        <div class="numb-box">
            <form class="layui-form" action="${ctx}/admin/qcloudSmsTemplate/add?type=template" method="post" id="addForm">
                <input type="hidden" name="qcloudSmsTemplate.type" value="template"/>
                <div class="layui-form-item" style="display: none">
                    <label class="layui-form-label layui-form-label-w">相关id</label>
                    <div class="layui-input-inline">
                        <input type="text" name="qcloudSmsTemplate.otherId" class="{required:true} lf" id="otherId" value=""/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">注意事项</label>
                    <div class="layui-input-block">
                        <div class="xy-warp fsize12 c-666 f-fM" style="border: 0">
                           <font class="fsize14 c-333">腾讯云模板基本属性</font><br/>
                            1.长度不要超过450字（汉字，字符，数字等可见符号，都按1个字计算）。单条短信长度为70字，超过70字后，按67字/条分隔成多条，以长短信的方式发送给用户，即用户收到的是一条长短信，但是按多条计费<br>
                            2.营销类短信，会在短信内容后面增加“回T退订”<br/>
                            3.短信模板内容不能含有【】符号<br/>
                            4.不能发送房产、移民、贷款、政治、色情、暴力等违法类短信<br/>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">模板名称</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input layui-input-6" name="qcloudSmsTemplate.name" id="title" value=""/>
                    </div>
                </div>
                <div class="layui-form-item" style="display: none">
                    <label class="layui-form-label layui-form-label-w">短信类型</label>
                    <div class="layui-input-block">
                        <select id="smsType">
                            <option value="1">普通短信</option>
                            <option value="0">营销短信</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item  layui-form-text">
                    <label class="layui-form-label layui-form-label-w">内容</label>
                    <div class="layui-input-block layui-textarea-block">
                        <textarea placeholder="请输入内容" class="layui-textarea" id="text" name="qcloudSmsTemplate.text"></textarea>
                    </div>
                    <div class="layui-input-block xy-warp fsize12 c-666 f-fM" style="border: 0">
                        <font class="fsize14 c-333">内容</font><br/>
                        1.模版示例如下，其中{数字}为可自定义的内容，须从1开始连续编号，如{1}、{2}等<br>
                        2.{1}为您的登录验证码，请于{2}分钟内填写。如非本人操作，请忽略本短信。<br/>
                        3.最多可输入450个字<br/>
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
