<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>新建会员商品</title>

    <script type="text/javascript">
        layui.use(['form'], function () {
            var form = layui.form();
        });
        function addSubmit() {
            if ($("#name").val() == null || $("#name").val().trim() == '') {
                layer.msg("请填写名称", {icon: 5, shift: 6});
                return;
            }
            if ($("#type").val() == null || $("#type").val() == 0) {
                layer.msg("请选择会员类型", {icon: 5, shift: 6});
                return;
            }
            var price = $("#price").val();
            if (price == null || $.trim(price) == '') {
                layer.msg("请填写会员价格", {icon: 5, shift: 6});
                return;
            } else {
                var reg = /^(([0-9]+)|([0-9]+(\.[0-9]{1,2})))$/;
                if (!reg.test(price)) {
                    layer.msg("请输入正确的价格,小数点后面只能输入一位或两位。", {icon: 5, shift: 6});
                    return;
                }
            }
            var days = $("#days").val();
            if (days == null || $.trim(days) == '') {
                layer.msg("请填写开通天数", {icon: 5, shift: 6});
                return;
            } else {
                if (!isNumber(days)) {
                    layer.msg("开通天数必须是正整数", {icon: 5, shift: 6});
                    return;
                }
            }

            var sort = $("input[name='memberSale.sort']").val();
            var reg = /^[0-9]+$/;
            if (!reg.test(sort)) {
                layer.msg("排序数必须是正整数", {icon: 5, shift: 6});
                return;
            }
            $("#addForm").submit();
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>会员商品管理</span>
        &gt;
        <span>新建会员价格</span>
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="${ctx}/admin/membersale/add.json" method="post" id="addForm">
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="memberSale.name" id="name" lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input layui-input-6">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">类型</label>
                <div class="layui-input-block layui-select-inline">
                    <select name="memberSale.type" id="type">
                        <option value="0">--请选择--</option>
                        <c:forEach items="${memberTypes }" var="memberType">
                            <option value="${memberType.id }">${memberType.title }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">价格</label>
                <div class="layui-input-block">
                    <input type="number" name="memberSale.price" id="price" lay-verify="required" placeholder="请输入价格" autocomplete="off" class="layui-input layui-input-6">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">开通月数</label>
                <div class="layui-input-block">
                    <input type="number" name="memberSale.days" id="days" min="1" value="1" lay-verify="required" placeholder="请输入月数" autocomplete="off" class="layui-input layui-input-6">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">排序</label>
                <div class="layui-input-block">
                    <input type="number" name="memberSale.sort" value="0" lay-verify="required" placeholder="请输入月数" autocomplete="off" class="layui-input layui-input-6">
                    <p class="hLh20 fsize12 c-red f-fM">倒序</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">描述</label>
                <div class="layui-input-block layui-textarea-block">
                    <textarea placeholder="请输入内容" name="memberSale.description" id="description" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block mt10">
                    <a class="layui-btn  layui-btn-danger" title="提 交" href="javascript:addSubmit()">提
                        交</a>
                    <a class="layui-btn layui-btn-primary" title="返 回" href="javascript:history.go(-1);">返
                        回</a>
                </div>
            </div>
        </form>
    </div>
</fieldset>
</body>
</html>
