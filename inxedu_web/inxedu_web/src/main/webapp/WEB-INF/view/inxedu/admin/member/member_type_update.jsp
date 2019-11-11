<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>会员类型修改</title>
    <script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>

    <script type="text/javascript">
        $(function () {
            initSimpleImageUpload('imageFile', 'memberType', callback, '', 'true', '350', '230');
        });
        function updateSubmit() {
            if (isEmpty($("#title").val().trim())) {
                layer.msg("请填写名称", {icon: 5, shift: 6});
                return;
            }
            $("#updateForm").submit();
        }
        /**
         * 图片上传回调函数
         * @param imgUrl 上传成功的图片路径
         */
        function callback(imgUrl) {
            $("input[name='memberType.imageUrl']").val(imgUrl);
            $("#showImage").attr("src", staticServer + imgUrl)
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>会员类型管理</span>
        &gt;
        <span>会员类型修改</span>
    </legend>
    <div class="layui-field-box">
        <div class="numb-box">
            <form class="layui-form" action="${ctx}/admin/membertype/update.json" method="post" id="updateForm">
                <input type="hidden" name="memberType.id" value="${ memberType.id}"/>
                <input type="hidden" name="memberType.imageUrl" value="${memberType.imageUrl}"/>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="memberType.title" id="title" lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input layui-input-6" value="${memberType.title }">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">封面图片</label>
                    <div class="layui-input-block">
                        <img id="showImage" width="180" height="100" src="${ctx }${memberType.imageUrl }">
                        <input type="button" value="上传" id="imageFile"/>
                        <font class="fsize12 c-red f-fM ml10">(请上传宽高为： 500*332 的图片)</font>
                    </div>
                </div>
                <div class="layui-form-item mt10">
                    <div class="layui-input-block">
                        <a class="layui-btn layui-btn-danger" title="提 交" href="javascript:updateSubmit()">提
                            交</a>
                        <a class="layui-btn layui-btn-primary" title="返 回" href="javascript:history.go(-1);">返
                            回</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</fieldset>


</body>
</html>
