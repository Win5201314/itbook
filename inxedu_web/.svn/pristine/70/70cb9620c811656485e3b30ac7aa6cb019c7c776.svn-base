<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>学员卡创建</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function(data){
            });
        });
        function addCourse() {
            window.open('${cxt}/admin/cou/couponCourseList?page.currentPage=1',
                    +'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802');
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
                        + "<a class='c-master ml30' href='javascript:void(0)' onclick='delcourse(\""
                        + arr[0] + "\",\"" + arr[1] + "\")'>删除</a></p>";
                courseIds += arr[0] + ",";
                courseNamestr = arr[1] + ",";
            }
            $("#coursestr").prepend(str);
            $("#courseIdHidden").val(courseIds);
            $("#coursestr").show();

        }
        function delcourse(courseId, title) {
            if (confirm("确定要删除【" + title + "】")) {
                $("#coursespan" + courseId).remove();
                var ids = $("#courseIdHidden").val();
                ids = ids.replace(courseId + ",", "");
                $("#courseIdHidden").val(ids);
                if ($("#coursestr").html() == "") {
                    $("#coursestr").hide()
                }
            }
        }
        function clearcourse() {
            $("#courseIdHidden").val("");
            $("#coursestr>p").remove();
        }
        function addSubmit() {
            var courseNames = "";
            $("p[name='courseName']").each(function () {
                courseNames += $(this).attr('title') + ",";
            });
            $("#cardCourseName").val(courseNames);
            //验证名称
            if ($("#cardTitle").val() == null || $("#cardTitle").val() == '') {
                layer.msg("请输入学员卡名称", {icon: 5, shift: 6});
                return;
            }
            var cardMoney = $("#cardMoney").val();
            if (cardMoney == null || $.trim(cardMoney) == '') {
                layer.msg("请输入学员卡金额", {icon: 5, shift: 6});
                return;
            } else {
                var reg = /^\d+(\.\d{1,2})*$/;
                if (!reg.test(cardMoney)) {
                    layer.msg("请输入正确的学员卡金额", {icon: 5, shift: 6});
                    return;
                }
            }
            //验证生成数量
            if ($("#totalNum").val() == null || $("#totalNum").val() == '') {
                layer.msg("请输入虚拟卡生成数量", {icon: 5, shift: 6});
                return;
            }
            if (isNaN($("#totalNum").val())) {
                layer.msg("生成虚拟卡只能为数字", {icon: 5, shift: 6});
                return;
            }
            if (parseInt($("#totalNum").val()) > 2000) {
                layer.msg("生成虚拟卡数量不能大于2000", {icon: 5, shift: 6});
                return;
            }
            if (parseInt($("#totalNum").val()) <= 0) {
                layer.msg("生成虚拟卡数量不能小于0", {icon: 5, shift: 6});
                return;
            }

            var loginAccountPrefix = $("#loginAccountPrefix").val();
            if (loginAccountPrefix == null || $.trim(loginAccountPrefix) == '') {
                layer.msg("请输入学员卡登录账号前缀", {icon: 5, shift: 6});
                return;
            } else {
                var reg = /^[a-zA-Z]+$/;
                if (!reg.test(loginAccountPrefix)) {
                    layer.msg("学员卡登录账号前缀必须是英文字母", {icon: 5, shift: 6});
                    return;
                }
            }
            $(".dialog").show();
            $.ajax({
                url: "${ctx}/admin/card/createUsercard",
                data: {
                    "courseIds": $("#courseIdHidden").val(),
                    "card.type": 3,
                    "card.courseName": $("#cardCourseName").val(),
                    "card.name": $("#cardTitle").val(),
                    "card.money": $("#cardMoney").val(),
                    "card.num": $("#totalNum").val(),
                    "card.loginAccountPrefix": $("#loginAccountPrefix").val(),
                    "card.remark": $("#info").val()
                },
                post: "post",
                async: false,
                success: function (result) {
                    $(".dialog").hide();
                    if (result.success == true) {
                        layer.msg(result.message, {icon: 1, shift: 6});
                        window.location.href = "${ctx}/admin/card/mainlist?queryMainCard.type=3";
                    } else {
                        layer.msg(result.message, {icon: 5, shift: 6});
                    }

                },
                error: function (error) {
                    $(".dialog").hide();
                    layer.msg(error.responseText, {icon: 5, shift: 6});
                }
            })
        }
    </script>
    <style>
        .dialog {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0.68);
            border-radius: 8px;
            height: 160px;
            left: 50%;
            margin: -40px 0 0 -40px;
            overflow: hidden;
            position: absolute;
            text-align: center;
            top: 50%;
            width: 160px;
        }
    </style>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend><em class="icon14 i_01"></em>&nbsp;<span>线下卡管理</span> &gt; <span>新建学员卡</span></legend>
    <div class="layui-field-box">
    <form class="layui-form" action="${ctx}/admin/card/createUsercard" method="post" id="addCardForm">
        <input type="hidden" name="courseIds" id="courseIdHidden"/>
        <input type="hidden" name="card.type" id="cardType" value="3"/>
        <input type="hidden" name="card.courseName" id="cardCourseName"/>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">学员卡名称</label>
            <div class="layui-input-block">
                <input class="layui-input layui-input-6" name="card.name" id="cardTitle" type="text"/>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">学员卡金额</label>
            <div class="layui-input-block">
                <input class="layui-input layui-input-6" id="cardMoney" name="card.money" type="text"/>
                <p class="hLh20 fsize12 c-red f-fM">如：1000或10.0或12.00(金额仅显示用无实际功能)</p>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">赠送课程</label>
            <div class="layui-input-block">
                <div id="coursestr" class="teacherList" style="display: none"></div>
                <a href="javascript:void(0)" onclick="addCourse()" class="layui-btn layui-btn-small">添加课程</a>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">学员卡数量</label>
            <div class="layui-input-block">
                <input class="layui-input layui-input-6" type="number" name="card.num" id="totalNum" min="0" max="2000" />
                <p class="hLh20 fsize12 c-red f-fM">必须是数字，数字不得大于2000</p>
            </div>

        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">学员卡登录账号前缀</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input layui-input-6" name="card.loginAccountPrefix" id="loginAccountPrefix" maxlength="6"/>
                 <p class="hLh20 fsize12 c-red f-fM">*请输入生成登录账号的前缀，生成的学员卡将由此规则生成登录账号，必须是英文字母</p>
            </div>
        </div>
        <div class="layui-form-item  layui-form-text">
            <label class="layui-form-label layui-form-label-w">备注</label>
            <div class="layui-input-block layui-textarea-block">
                <textarea class="layui-textarea" name="card.remark" id="info"></textarea>
            </div>
        </div>
        <div class="layui-form-item ">
            <div class="layui-input-block">
                <input type="button" value="提交" class="layui-btn " onclick="addSubmit()"/>
                <input type="button" value="返回" class="layui-btn" onclick="history.go(-1);"/>
            </div>
        </div>
    </form>
        </div>
</fieldset>
</body>
</html>
