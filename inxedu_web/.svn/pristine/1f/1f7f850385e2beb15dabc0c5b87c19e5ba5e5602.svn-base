<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>批量赠送</title>

    <script type="text/javascript">
        function importExcel() {

            var myFile = $("#myFile").val();
            if (myFile.length <= 0) {
                layer.msg("请选择要导入excel文件", {icon: 5, shift: 6});
                return false;
            }
            $("#importP").submit();
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>课程管理</span>
        &gt;
        <span>批量赠送</span>
    </legend>
    <form action="/admin/cou/giveCoursesExcel" method="post" id="importP" enctype="multipart/form-data">
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="layui-table">
            <thead>
            <tr>
                <td align="center" width="20%">名称</td>
                <td align="center" width="60%">内容</td>
                <td align="center" width="20%">操作</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td align="center"><font color="red">*</font>&nbsp;信息描述</td>
                <td>excel模版说明：<br>
                    第一列：用户的电子邮箱,必须是网站存在的！<br>
                    第二列：赠送的课程ID，必须存在，多个课程逗号隔开（赠送课程价格必须大于零）<br>
                </td>
                <td align="center" rowspan="3">
                    <a href="${ctx}/static/common/admin/give_course.xls" class="layui-btn layui-btn-small">点击下载模版</a>
                </td>
            </tr>
            <tr>
                <td align="center">导入出错模式</td>
                <td>
                    <select name="mark">
                        <option value="1">跳过</option>
                        <option value="2">全部放弃</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center">上传</td>
                <td>
                    <span class="ml10">
                        <input id="myFile" type="file" value="" name="myFile" style="border: 0"/>
                    </span>
                </td>
            </tr>
            </tbody>
        </table>
        <div align="center">
            <input type="button" value="提 交" class="layui-btn layui-btn-small" onclick="importExcel()"/>
            <input type="button" value="返 回" class="layui-btn layui-btn-small" onclick="history.go(-1);"/>
        </div>
    </form>
</fieldset>
</body>
</html>