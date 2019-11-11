<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>课程卡创建</title>

    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        $(function () {
            var addType = "${addType}";
            if (addType == "course") {
                $("#courseTr").css("display", "");
                $("#cardType").val(1);
            } else {
                $("#courseTr").css("display", "none");
                $("#cardType").val(2);
            }
        });

        function addCourse() {
            window
                    .open(
                            '${cxt}/admin/cou/couponCourseList?page.currentPage=1',
                            +'newwindow',
                            'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802');
        }
        function getCourseList(CourseList) {
            //p对象的name获取已存在的课程集合，去除提交过来的重复对象
            $("p[name='courseName']").each(function (i, val) {
                for (var j = 0; j < CourseList.length; j++) {
                    var id = CourseList[j];
                    if (val.id == 'coursespan' + id[0]) {
                        CourseList.splice(j, 1);
                    }
                }
            });
            $("#coursestr").show();
            //插入到表格中
            coursePAdd(CourseList);
        }

        function coursePAdd(myArray) {
            var str = "";
            var courseIds = $("#courseIdHidden").val();
            for (var i = 0; i < myArray.length; i++) {
                var arr = myArray[i];
                str += "<p style='width:100%;margin: 0 0 0em' id='coursespan"
                        + arr[0]
                        + "' name='courseName' value='"
                        + arr[0]
                        + "' title='"
                        + arr[1]
                        + "'>"
                        + arr[1]
                        + "&nbsp;&nbsp;<a class='c-master ml30' href='javascript:void(0)' onclick='delcourse(\""
                        + arr[0] + "\",\"" + arr[1] + "\")'>删除</a></p>";
                courseIds += arr[0] + ",";
                courseNamestr = arr[1] + ",";
            }
            $("#coursestr").prepend(str);
            $("#courseIdHidden").val(courseIds);
        }
        function delcourse(courseId, title) {
            if (confirm("确定要删除【" + title + "】")) {
                $("#coursespan" + courseId).remove();
                var ids = $("#courseIdHidden").val();
                ids = ids.replace(courseId + ",", "");
                $("#courseIdHidden").val(ids);
            }
            if ($("#coursestr").html() == "") {
                $("#coursestr").hide()
            }
        }
        function clearcourse() {
            $("#courseIdHidden").val("");
            $("#coursestr>p").remove();
        }
        function addSubmit() {


            //验证优惠券名称
            if ($("#cardTitle").val() == null || $("#cardTitle").val() == '') {
                layer.msg("请输入充值卡名称", {icon: 5, shift: 6});
                return;
            }

            var type = $("#cardType").val();

            var cardMoney = $("#cardMoney").val();
            if (cardMoney == null || $.trim(cardMoney) == '') {
                layer.msg("请输入充值卡额度", {icon: 5, shift: 6});
                return;
            } else {
                var reg = /^[0-9]+$/;
                if (!reg.test(cardMoney)) {
                    layer.msg("充值卡额度必须是正整数", {icon: 5, shift: 6});
                    return false;
                }
            }
            //使用开始至截至日期
            if ($("#startTime").val() == null || $("#startTime").val() == '') {
                layer.msg("请选择使用起始日期", {icon: 5, shift: 6});
                return;
            }

            if ($("#endTime").val() == null || $("#endTime").val() == '') {
                layer.msg("请选择使用截止日期", {icon: 5, shift: 6});
                return;
            }
            var begin = new Date($("#startTime").val().replace(/-/g, "/"));
            var end = new Date($("#endTime").val().replace(/-/g, "/"));
            if (begin > end) {
                layer.msg("截止日期必须大于起始日期", {icon: 5, shift: 6});
                return;
            }

            //验证生成数量
            if ($("#totalNum").val() == null || $("#totalNum").val() == '') {
                layer.msg("请输入充值卡生成数量", {icon: 5, shift: 6});
                return;
            }
            if (isNaN($("#totalNum").val())) {
                layer.msg("生成充值卡只能为数字", {icon: 5, shift: 6});
                return;
            }
            if (parseInt($("#totalNum").val()) > 1000) {
                layer.msg("生成充值卡数量不能大于1000", {icon: 5, shift: 6});
                return;
            }
            if (parseInt($("#totalNum").val()) <= 0) {
                layer.msg("生成充值卡数量必须大于0", {icon: 5, shift: 6});
                return;
            }
            $("#addCardForm").submit();
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>充值卡管理</span>
        &gt;
        <span>新建充值卡</span>
    </legend>
    <div class="layui-field-box">
        <div class="numb-box-t">
            <form class="layui-form" action="${ctx}/admin/card/createcard" method="post" id="addCardForm">
                <input type="hidden" name="card.type" value="2"/>
                <div class="layui-form-item ">
                    <label class="layui-form-label layui-form-label-w">充值卡名称</label>
                    <div class="layui-input-block">
                        <input class="layui-input layui-input-6" name="card.name" id="cardTitle" type="text"/>
                        <div class="layui-form-mid layui-word-aux"></div>
                    </div>
                </div>
                <div class="layui-form-item ">
                    <label class="layui-form-label layui-form-label-w">充值卡额度</label>
                    <div class="layui-input-block">
                        <input class="layui-input layui-input-6" id="cardMoney" name="card.money" min="0" max="1000" type="number"/>
                        <div class="layui-form-mid layui-word-aux"></div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">范围</label>
                   <div class="layui-input-block">
                       <div class="layui-input-inline">
                           <input type="text" name="card.beginTime" placeholder="开始时间" id="startTime" class="layui-input" onclick="layui.laydate({elem: this, festival: true})">
                       </div>
                       <div class="layui-form-mid">-</div>
                       <div class="layui-input-inline">
                           <input type="text" name="card.endTime" placeholder="结束时间" id="endTime" class="layui-input" onclick="layui.laydate({elem: this, festival: true})">
                       </div>
                   </div>
                </div>
                <div class="layui-form-item ">
                    <label class="layui-form-label layui-form-label-w">充值卡数量</label>
                    <div class="layui-input-block">
                        <input class="layui-input layui-input-6" id="totalNum" name="card.num" min="0" type="number"/>
                        <p class="hLh20 fsize12 c-red f-fM">必须是数字</p>
                    </div>
                </div>
                <div class="layui-form-item " style="display: none">
                    <label class="layui-form-label layui-form-label-w">备注</label>
                    <div class="layui-input-inline">
                        <textarea rows="3" cols="80" name="card.remark" id="info"></textarea>
                        <div class="layui-form-mid layui-word-aux">充值卡描述</div>
                    </div>
                </div>
                <div class="layui-form-item " >
                    <div class="layui-input-block">
                        <input type="button" value="提交" class="layui-btn" onclick="addSubmit()"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</fieldset>
</body>
</html>
