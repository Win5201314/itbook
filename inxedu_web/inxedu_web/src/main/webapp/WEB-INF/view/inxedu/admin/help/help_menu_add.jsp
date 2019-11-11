<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>
    <%--<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js?v=${v}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css?v=${v}" />--%>
    <%--百度编译器--%>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function(data){
            });
            form.on('radio(radio-menuChoose)', function (data) {
                showMenu(data.value);
            });
            form.on('submit(*)', function(data){
                addSubmit();
            });
        });
        var editor;
        $(function () {
            $("#menuName").keyup(function () {
                $("#menuNameCount").text($.trim($(this).val()).length);
            });
            //初始化 Kindeditor
            initKindEditor_addblog('content', '80%', 350, 'helpcontent', 'true');
        });

        function showMenu(flag) {
            if (flag == 2) {
                $(".menuTwo").show();
            } else {
                $("#menuOne").val(0);
                //KindEditor.html('#content', '');//清空文本编辑器内容
                UE.getEditor('content').execCommand('cleardoc');//百度编译器清空文本编辑器内容
                $(".menuTwo").hide();
            }
        }
        function addSubmit() {
            var flag = $('input[name="menuChoose"]:eq(1)').prop("checked");
            if (flag) {
                if ($("#menuOne").val() == 0) {
                    layer.msg("请选择一级菜单", {icon: 5, shift: 6});
                    return;
                }
                if (!UE.getEditor('content').hasContents()) {// hasContents()  检查有内容返回true，否则返回false
                    layer.msg("请填写帮助内容", {icon: 5, shift: 6});
                    return;
                }
            }
            $("#addForm").submit();
        }
    </script>
</head>
<body>
<div class="numb-box">
    <fieldset class="layui-elem-field">
        <legend>
            <span>帮助菜单</span>
            &gt;
            <span>添加</span>
        </legend>
        <div class="numb-box">
            <p class="hLh20 fsize14 c-red f-fM"><font color="red">*</font>&nbsp;为必填项</p>
            <form class="layui-form" action="${ctx}/admin/helpMenu/add" method="post" id="addForm">
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">级别选择</label>
                    <div class="layui-input-block">
                        <div class="layui-form-mid">
                            <input value="1" lay-filter="radio-menuChoose" type="radio" title="一级菜单" name="menuChoose" checked="checked"/>
                            <input value="2"  lay-filter="radio-menuChoose" type="radio" title="二级菜单" name="menuChoose"/>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item menuTwo" style="display: none">
                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;选择一级菜单</label>
                    <div class="layui-input-block">
                        <div class="layui-unselect layui-form-select">
                            <select id="menuOne" class="layui-input" name="helpMenu.parentId">
                                <option value="0">--请选择--</option>
                                <c:forEach items="${helpMenus}" var="helpMenuOne">
                                    <option value="${helpMenuOne.id}">${helpMenuOne.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;菜单名称</label>
                    <div class="layui-input-block">
                        <input id="menuName" class="layui-input layui-input-6" lay-verify="required" type="text" name="helpMenu.name" maxlength="9"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">帮助内容</label>
                    <div class="layui-input-block">
                        <textarea name="helpMenu.content" id="content" data-rule="required;"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;排序</label>
                    <div class="layui-input-block">
                        <input id="sort" type="number" class="layui-input layui-input-6" lay-verify="number" name="helpMenu.sort" value="0"/>
                        <p class="fsize12 c-red f-fM hLh20">倒序</p>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">外链</label>
                    <div class="layui-input-block">
                        <input id="linkBuilding" class="layui-input layui-input-6" type="text" name="helpMenu.linkBuilding" value=""/>
                        <p class="fsize12 c-red f-fM hLh20">(选填)外链为空则显示帮助内容，不为空则跳转到外链!</p>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block nt20">
                        <button class="layui-btn  layui-btn-danger" lay-submit lay-filter="*">提 交</button>
                        <button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返 回</button>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
</div>

</body>
</html>