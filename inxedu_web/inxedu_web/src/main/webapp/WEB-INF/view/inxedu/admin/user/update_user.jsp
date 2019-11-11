<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>修改学员</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css"/>
    <link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"></link>
    <link rel="stylesheet" href="/static/common/layui/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>

    <script type="text/javascript">
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function(data){
            });
        });

        function updateuser() {
            var emailVal = $("#u-email").val();
            var mobileVal = $("#u-mobile").val();
            var userName = $("#userName").val();
            if (isEmpty(emailVal) && isEmpty(mobileVal) && isEmpty(userName)) {
                layer.msg("邮箱，手机，账号不能都为空！", {icon: 5, shift: 6});
                return;
            }

            var reg = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则

            if (!isEmpty(emailVal)) {
                if (reg.test(emailVal) == false) {//格式不正确
                    layer.msg("请输入正确的邮箱！", {icon: 5, shift: 6});
                    return;
                }
            }

            var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则

            if (!isEmpty(mobileVal)) {
                if (reg.test(mobileVal) == false) {//格式不正确
                    layer.msg("请输入正确的手机！", {icon: 5, shift: 6});
                    return;
                }
            }
            /**账户正则表达式 限10个字符，支持中英文、数字、减号或下划线 */
            var reg = /^[\u4e00-\u9fa5_a-zA-Z0-9-]{1,10}/;
            if (!isEmpty(userName)) {
                if (reg.test(userName) == false) {//格式不正确
                    layer.msg("请输入正确的账号！", {icon: 5, shift: 6});
                    return;
                }
            }

            if(!$("#updateUserForm").isValid()){
                return;
            }
            var date = $("#updateUserForm").serialize();
            $.ajax({
                url: baselocation + '/admin/user/updateuser',
                data: date,
                type: 'post',
                async: false,
                dataType: 'json',
                success: function (result) {
                    if (result.success) {
                        layer.msg("修改成功", {icon: 1, shift: 6});
                        window.location.href = "/admin/user/getuserList";
                    } else {
                        layer.msg(result.message, {icon: 5, shift: 6});
                    }
                }
            });
        }

    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <a href="${ctx}/admin/user/getuserList">
            <span>学员列表</span>
        </a>
        &gt;
        <span>修改学员</span>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/teacher/add" method="post" id="updateUserForm" class="layui-form"
              data-validator-option="{stopOnError:false, timely:false}">
            <input type="hidden" name="user.userId" value="${user.userId}"/>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">邮箱</label>
                <div class="layui-input-block">
                    <input id="u-email" name="user.email" value="${user.email}" type="text" class="layui-input layui-input-6"/>
                    <p class="fsize12 c-red f-fM hLh20">邮箱可以为空，但必须唯一</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">手机</label>
                <div class="layui-input-block">
                    <input id="u-mobile" name="user.mobile" value="${user.mobile}" class="layui-input layui-input-6"/>
                    <p class="fsize12 c-red f-fM hLh20">手机可以为空，但必须唯一</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">账号</label>
                <div class="layui-input-block">
                    <input id="userName" name="user.userName" value="${user.userName}" class="layui-input layui-input-6"/>
                    <p class="fsize12 c-red f-fM hLh20">账号邮箱，手机不能都为空，账号唯一，限10个字符，支持中英文、数字、减号或下划线</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">昵称</label>
                <div class="layui-input-block">
                    <input name="user.showName" value="${user.displayName}"  class="layui-input layui-input-6"/>
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label layui-form-label-w">性别</label>
                <div class="layui-input-block">
                    <select name="user.sex" lay-filter="select">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                    <script>
                        $("#sex").val('${user.sex}');
                    </script>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">年龄</label>
                <div class="layui-input-block">
                    <input name="user.age" value="${user.age}" data-rule="integer[+0]" class="layui-input layui-input-6" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">注册时间</label>
                <div class="layui-input-block">
                    <div class="xy-warp fsize14 c-999">
                    <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">状态</label>
                <div class="layui-input-block">
                    <div class="xy-warp fsize14 c-999">
                        <c:if test="${user.isavalible==1}">正常</c:if>
                        <c:if test="${user.isavalible==2}">冻结</c:if>
                    </div>
                </div>
            </div>
            <c:if test="${user.loginAccount!=null}">
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">学员卡号</label>
                    <div class="layui-input-block">
                        <div class="xy-warp fsize14 c-999">
                            ${user.loginAccount}
                       </div>
                    </div>
                </div>
            </c:if>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">密码</label>
                <div class="layui-input-block">
                    <input name="user.password" value="" type="password" class="layui-input layui-input-6"/>
                    <p class="fsize12 c-red f-fM hLh20">如果为空则不修改，输入密码则修改</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">确定密码</label>
                <div class="layui-input-block">
                    <input name="passwords" value="" type="password" class="layui-input layui-input-6"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <a class="layui-btn layui-btn-danger" title="提 交" href="javascript:updateuser()">提 交</a>
                    <a class="layui-btn layui-btn-primary" title="返 回" href="javascript:history.go(-1);">返 回</a>
                </div>
            </div>
        </form>
    </div>
</fieldset>
</body>
</html>
