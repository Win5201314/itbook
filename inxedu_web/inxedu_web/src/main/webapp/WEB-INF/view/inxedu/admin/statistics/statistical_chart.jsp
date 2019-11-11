<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>统计图</title>
    <script type="text/javascript" src="${ctx}/static/admin/js/highStock/highstock.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(mouth)', function (data) {
                statisticalByMouth($("#mouth"));
                form.render();
            });
        /*    form.on('select(type)', function (data) {
                statisticalChart();
            });*/
        });
        var rotationVal = 0;
        $(function () {
            $("#type option[value='${type}']").attr("selected", true);
            var curDate = new Date();
            startDate = new Date(curDate.getTime() - 24 * 60 * 60 * 1000 * 7);
            $("#startDate").val(startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());
            $("#endDate").val(curDate.getFullYear() + "-" + (curDate.getMonth() + 1) + "-" + curDate.getDate());
            if (isLeapYear(new Date().getFullYear())) {
                $("#February").val('02-01,02-29')
            }
            var mouth = new Date().getMonth() + 1;
            var day = new Date().getDate();
            $("#nowday").val(mouth + "-01" + "," + mouth + "-" + day);
            statisticalChart();
        });

        //查询时间条件
        function time(day) {
            var startDate = "";
            var endDate = "";
            var tian = "";
            if (day != null && $.trim(day) != '' && day > 0) {
                //当前时间
                var curDate = new Date();
                //开始时间
                startDate = new Date(curDate.getTime() - 24 * 60 * 60 * 1000 * day);
                //结束时间
                endDate = curDate;
                //格式化时间
                startDate = startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate();
                endDate = curDate.getFullYear() + "-" + (curDate.getMonth() + 1) + "-" + curDate.getDate();
            } else {
                startDate = $("#startDate").val();
                endDate = $("#endDate").val();
            }
            var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();
            var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();
            var tian = Math.abs((startTime - endTime)) / (1000 * 60 * 60 * 24);
            return {"startDate": startDate, "endDate": endDate, "tian": tian}
        }

        function statisticalChart(day) {
            if (time(day).tian >= 14) {
                rotationVal = 90;
            } else {
                rotationVal = 0;
            }
            console.info($("#type").val());
            var url = "/admin/ajax/statistics/" + $("#type").val();
            var startDate = time(day).startDate;
            var endDate = time(day).endDate;

            if (startDate == null || startDate == '') {
                layer.msg("请输入开始日期", {icon: 5, shift: 6});
                return;
            }
            if (endDate == null || endDate == '') {
                layer.msg("请输入结束日期", {icon: 5, shift: 6});
                return;
            }
            var begin = new Date(startDate.replace(/-/g, "/"));
            var end = new Date(endDate.replace(/-/g, "/"));
            if (begin > end) {
                layer.msg("结束日期必须大于开始日期", {icon: 5, shift: 6});
                return;
            }

            $.ajax({
                url: baselocation + url,
                type: "post",
                data: {
                    "startDate": startDate,
                    "endDate": endDate,
                    "type": $("#type").val()
                },
                dataType: "text",
                async: false,
                success: function (result) {
                    $("#chart").html(result);
                }
            })
        }
        function isLeapYear(iYear) {//是否是闰年
            if (iYear % 4 == 0 && iYear % 100 != 0) {
                return true;
            } else {
                if (iYear % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        function statisticalByMouth(obj) {

            var time = [];
            time = $(obj).val().split(",");
            time[0] = new Date().getFullYear() + "-" + time[0];
            time[1] = new Date().getFullYear() + "-" + time[1];
            $("#startDate").val(time[0]);
            $("#endDate").val(time[1]);
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>统计</span>
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="${ctx }/admin/statistics/login" method="post">
            <div class="layui-form-item">
                <div class="">
                    <div class="layui-input-inline">
                        <div style="display: none">
                            <select id="type" lay-filter="type">
                                <option value="login">学员登录数统计</option>
                                <option value="registered">学员注册数统计</option>
                                <option value="order">学员订单数统计</option>
                                <option value="income">营收额统计</option>
                                <option value="videoViewingNum">视频观看数统计</option>
                                <option value="dailyUserNumber">学员数统计</option>
                                <option value="dailyCourseNumber">课程数统计</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">月份</label>
                        <div class="layui-input-inline">
                            <select lay-filter="mouth" id="mouth" onchange="statisticalByMouth(this)">
                                <option id="nowday" value="01-01,12-31">月份</option>
                                <option value="01-01,01-31">一月</option>
                                <option id="February" value="02-01,02-28">二月</option>
                                <option value="03-01,03-31">三月</option>
                                <option value="04-01,04-30">四月</option>
                                <option value="05-01,05-31">五月</option>
                                <option value="06-01,06-30">六月</option>
                                <option value="07-01,07-31">七月</option>
                                <option value="08-01,08-31">八月</option>
                                <option value="09-01,09-30">九月</option>
                                <option value="10-01,10-31">十月</option>
                                <option value="11-01,11-30">十一月</option>
                                <option value="12-01,12-31">十二月</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                       <label class="layui-form-label">时间</label>
                       <div class="layui-input-inline">
                           <input type="text" id="startDate" class="layui-input" name="startDate" onclick="layui.laydate({elem: this, festival: true})" value="${startDate }"/>
                       </div>
                       <div class="layui-form-mid">-</div>
                       <div class="layui-input-inline">
                           <div class="layui-input-inline">
                               <input type="text" id="endDate" class="layui-input" name="endDate" onclick="layui.laydate({elem: this, festival: true})" value="${endDate }"/>
                           </div>
                       </div>
                   </div>
                    <div class="layui-inline">
                        <input id="query" type="button" onclick="statisticalChart()" class="layui-btn layui-btn-small layui-btn-danger" value="查询"/>
                        <input type="button" onclick="window.location.href='/admin/jumpGenerationStatisticsPage'" class="layui-btn layui-btn-small layui-btn-danger" value="生成统计"/>
                    </div>
                </div>
            </div>


            <%--<input id="day7" type="button" onclick="statisticalChart(7)" class="layui-btn layui-btn-small" value="近7天" />
            <input id="day15" type="button" onclick="statisticalChart(15)" class="layui-btn layui-btn-small" value="近15天" />
            <input id="day30" type="button" onclick="statisticalChart(30)" class="layui-btn layui-btn-small" value="近30天" />--%>


            <div class="commonWrap">
                <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                    <tbody>
                    <tr align="center">
                        <td id="chart">


                        </td>
                    </tr>
                    </tbody>
                </table>
            </div><!-- /commonWrap -->
        </form>
    </div>
</fieldset>
</body>
</html>