<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>修改讲师</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css"/>
    <script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/common/ztree/css/zTreeStyle.css"/>
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
        <span>修改讲师</span>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/teacher/update" method="post" id="saveTeacherForm" class="layui-form">
            <input type="hidden" name="teacher.id" value="${teacher.id}"/>
            <input type="hidden" name="teacher.picPath" id="imagesUrl" value="${teacher.picPath}"/>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师名称</label>
                <div class="layui-input-block">
                    <input name="teacher.name" value="${teacher.name}" class="layui-input layui-input-6" type="text" lay-verify="required"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师资历</label>
                <div class="layui-input-block layui-textarea-block">
                     <textarea name="teacher.education" class="layui-textarea" lay-verify="required" >${teacher.education}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师专业</label>
                <div class="layui-input-block">
                    <input type="hidden" name="teacher.subjectId" value="${teacher.subjectId}"/>
                    <input readonly="readonly" value="${subject.subjectName}" id="subjectId" class="layui-input layui-input-6" onclick="showSubjectList()"/>
                    <div id="ztreedemo" class="ztree" style="z-index:999999;display: none; position: absolute; top: 30px; left: 0; background: #f8f8f8; width: 150px;border: 3px solid #ececec;"></div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师等级</label>
                <div class="layui-input-block layui-select-inline">
                    <select name="teacher.isStar" lay-filter="select">
                        <option <c:if test="${teacher.isStar==1}">selected="selected"</c:if> value="1">高级讲师</option>
                        <option <c:if test="${teacher.isStar==2}">selected="selected"</c:if> value="2">首席讲师</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师排序</label>
                <div class="layui-input-block">
                    <input name="teacher.sort" value="${teacher.sort}" lay-verify="number" class="layui-input layui-input-6"/>
                    <p class="fsize12 c-red f-fM hLh20">以降序排列</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师简介</label>
                <div class="layui-input-block layui-textarea-block">
                    <textarea id="career" name="teacher.career" lay-verify="required" class="layui-textarea">${teacher.career}</textarea>
                    <p class="fsize12 c-red f-fM hLh20">以降序排列</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">讲师头像</label>
                <div class="layui-input-block">
                    <c:choose>
                        <c:when test="${teacher.picPath!=null && teacher.picPath!=''}">
                            <img src="${staticServer}${teacher.picPath}" alt="" id="subjcetpic" width="200px"
                                 height="200px"/>
                        </c:when>
                        <c:otherwise>
                            <img src="${ctx }/static/common/admin/images/default_head.jpg" alt="" id="subjcetpic"
                                 width="200px" height="200px"/>
                        </c:otherwise>
                    </c:choose>
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
