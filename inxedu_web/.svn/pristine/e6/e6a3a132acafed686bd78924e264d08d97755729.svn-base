<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>生成统计</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        function doSubmit() {
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var operate = $("#operate").val();
            var url = '';
            if (operate == 0) {
                layer.msg("请选择操作", {icon: 5, shift: 6});
                return;
            } else if (operate == 1) {
                url = '/admin/statistics/create/batch';
            } else if (operate == 2) {
                url = '/admin/statistics/del/batch';
            }
            if (startTime == null || startTime == '') {
                layer.msg("请输入开始日期", {icon: 5, shift: 6});
                return;
            }
            if (endTime == null || endTime == '') {
                layer.msg("请输入结束日期", {icon: 5, shift: 6});
                return;
            }
            var begin = new Date($("#startTime").val().replace(/-/g, "/"));
            var end = new Date($("#endTime").val().replace(/-/g, "/"));
            if (begin > end) {
                layer.msg("结束日期必须大于开始日期", {icon: 5, shift: 6});
                return;
            }
            var date = new Date();
            date.setDate(date.getDate() - 1);
            if (date < end) {
                layer.msg("结束日期不能大于等于当前时间", {icon: 5, shift: 6});
                return;
            }
            $.ajax({
                url: '${ctx}' + url,
                type: "post",
                data: {"startTime": startTime, "endTime": endTime},
                dataType: "json",
                success: function (result) {
                    if (result.message == 'true') {
                        layer.msg("操作成功", {icon: 1, shift: 6});
                    } else if (result.message == 'exists') {
                        layer.msg("时间段内已有数据，请删除后再操作", {icon: 5, shift: 6});
                    }
                }
            })
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>统计管理</span>
        &gt;
        <span>生成统计</span>
    </legend>
    <form class="layui-form" method="post" id="statisticsForm">
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-label-w">范围</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text"  placeholder="开始日期" id="startTime" class="layui-input"
                       onclick="layui.laydate({elem: this, festival: true})">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text"  placeholder="结束日期" id="endTime" class="layui-input"
                       onclick="layui.laydate({elem: this, festival: true})">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-label-w">操作</label>
            <div class="layui-input-inline">
                <select id="operate">
                    <option value="0">--请选择--</option>
                    <option value="1">生成统计</option>
                    <option value="2">删除统计</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <a class="layui-btn layui-btn-small layui-btn-danger" title="执行" href="javascript:doSubmit()">执行</a>
            </div>
        </div>
    </form>
</fieldset>
</body>
</html>