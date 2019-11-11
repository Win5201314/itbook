<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>开通学员</title>

    <%--验证框架--%>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function(data){
            });
            $(".piliangkaitongShow").click(function(){
                $("fieldset").eq(0).show().next().hide();
                form.render(); //更新全部
            });
             $(".dangekaitongShow").click(function(){
                    $("fieldset").eq(1).show().prev().hide();
                     form.render(); //更新全部
            });

        });
        function importExcel() {

            var myFile = $("#myFile").val();
            if (myFile.length <= 0) {
                layer.msg("请选择导入内容", {icon: 5, shift: 6});
                return false;
            }
            $("#importP").submit();
        }
        /**
         * 单个开通
         * @returns {boolean}
         */
        function singleCreateUser() {
            var params = $("#addForm").serialize();
            $.ajax({
                url: baselocation + '/admin/user/singleCreateUser',
                type: 'post',
                dataType: 'json',
                data: params,
                success: function (result) {
                    if (result.success) {
                        layer.msg("开通成功", {icon: 1, shift: 6});
                    } else {
                        layer.msg(result.message, {icon: 5, shift: 6});
                    }
                }
            });

        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field" style="display: none">
    <legend>
        <span>学员管理</span>
        &gt;
        <span>批量开通</span>
    </legend>
    <div class="layui-field-box">
        <div class="commonWrap">
            <form action="/admin/user/importExcel" method="post" id="importP" enctype="multipart/form-data" class="layui-from">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="layui-table">
                    <thead>
                    <tr>
                        <td align="center">名称</td>
                        <td align="center">流程</td>
                        <td align="center">操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td align="center">批量开通流程</td>
                        <td align="center">模板下载-----填写对应项-----上传模板-----提交</td>
                        <td align="center">
                            <a href="${ctx }/static/common/import_student/import_student.xls" class="layui-btn layui-btn-small">点击下载模版</a>
                        </td>
                    </tr>
                    <tr style="display: none">
                        <td align="center">导入中出错选择项</td>
                        <td align="center">
                            <select name="mark">
                                <option value="2">全部放弃</option>
                                <option value="1">跳过</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">上传</td>
                        <td align="center">
                            <span class="ml10">
                                <input id="myFile" type="file" value="" name="myFile" style="border: 0"/>
                            </span>
                        </td>
                        <td align="center">
                            <input type="button" value="提 交" class="layui-btn layui-btn-small" onclick="importExcel()"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="layui-form-item tac mt20">
                    <button type="button" class="layui-btn dangekaitongShow layui-btn-danger" id="message-list">单个开通</button>
                    <button type="button" class="layui-btn  piliangkaitongShow layui-btn-danger" id="genseeLive">批量开通</button>
                </div>
            </form>

        </div>
    </div>
</fieldset>
<fieldset  class="layui-elem-field">
    <legend>
        <span>学员管理</span>
        &gt;
        <span>单个开通</span>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}" method="post" id="addForm" class="layui-from">
            <div class="layui-form-item">
                <div class="">
                    <label class="layui-form-label layui-form-label-w">账户</label>
                    <div class="layui-input-block">
                        <input type="text" name="userName" class="layui-input layui-input-6" />
                        <p class="fsize12 c-red f-fM hLh20">唯一，限10个字符，支持中英文、数字、减号或下划线</p>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="">
                    <label class="layui-form-label layui-form-label-w">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" name="email" class="layui-input layui-input-6"/>
                        <p class="fsize12 c-red f-fM hLh20">选填</p>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="">
                    <label class="layui-form-label layui-form-label-w">手机号</label>
                    <div class="layui-input-block">
                        <input type="text" name="mobile" class="layui-input layui-input-6"/>
                        <p class="fsize12 c-red f-fM hLh20">选填</p>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="">
                    <label class="layui-form-label layui-form-label-w">密码</label>
                    <div class="layui-input-block">
                        <input type="text" name="password" class="layui-input layui-input-6"/>
                        <p class="fsize12 c-red f-fM hLh20">选填，默认123456，长度6-16，由英文和数字或者下划线</p>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-danger" onclick="singleCreateUser()">单个开通</button>
                    <button type="button" class="layui-btn layui-btn-danger" onclick='$("fieldset").eq(0).show().next().hide()'>批量开通</button>
                </div>
            </div>
        </form>
    </div>
</fieldset>
</body>
</html>