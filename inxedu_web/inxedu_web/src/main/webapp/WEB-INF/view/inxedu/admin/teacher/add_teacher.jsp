<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>添加讲师</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/common/ztree/css/zTreeStyle.css"/>
    <script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/admin/teacher/teacher.js"></script>

    <script type="text/javascript">
        subjectList = '${subjectList}';
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });

            //监听提交
            form.on('submit(teacherFormSubmit)', function (data) {
                teacherFormSubmit();
            });

        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <a href="${ctx}/admin/teacher/list">
            <span>讲师管理</span>
        </a>
        &gt;
        <span>添加讲师</span>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/teacher/add" method="post" id="saveTeacherForm" class="layui-form">
            <input type="hidden" name="teacher.picPath" id="imagesUrl"/>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>讲师名称</label>
                <div class="layui-input-block">
                    <input name="teacher.name" type="text" class="layui-input layui-input-6" lay-verify="required"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>讲师资历</label>
                <div class="layui-input-block layui-textarea-block">
                    <textarea name="teacher.education" lay-verify="required" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>讲师专业</label>
                <div class="layui-input-block">
                    <input type="hidden" name="teacher.subjectId" value="0"/>
                    <input readonly="readonly" id="subjectId" onclick="showSubjectList()" class="layui-input layui-input-6"/>
                    <ul id="ztreedemo" class="ztree" style="z-index:99999;display: none; position: absolute; top: 30px; left: 0;  width:59%;border: 3px solid #ececec;"></ul>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师等级</label>
                <div class="layui-input-block layui-select-inline">
                    <select name="teacher.isStar" lay-filter="select">
                        <option value="1">高级讲师</option>
                        <option value="2">首席讲师</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师排序</label>
                <div class="layui-input-block">
                    <input name="teacher.sort" value="0" lay-verify="number"  class="layui-input layui-input-6"/>
                    <p class="fsize12 c-red f-fM hLh20">以降序排列</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>讲师简介</label>
                <div class="layui-input-block layui-textarea-block">
                    <textarea class="layui-textarea" id="career" lay-verify="required" name="teacher.career"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师头像</label>
                <div class="layui-input-block">
                    <img src="${ctx }/static/common/admin/images/default_head.jpg" alt="" id="subjcetpic" width="200px" height="200px"/>
                    <input type="button" value="上传" id="fileuploadButton"/>
                    <font class="htmlImgSize c-red">(请上传 288*288(长X宽)像素 的图片)</font>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <a class="layui-btn layui-btn-danger" title="提 交" href="javascript:void(0)" lay-submit="" lay-filter="teacherFormSubmit">提 交</a>
                    <a class="layui-btn layui-btn-primary" title="返 回" href="javascript:history.go(-1);">返 回</a>
                </div>
            </div>
        </form>
    </div>
</fieldset>
</body>
</html>
