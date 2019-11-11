<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加资讯</title>
<script type="text/javascript" src="${ctx}/static/admin/article/article.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
	<%--百度编译器--%>
	<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
    <link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"></link>
    <script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>

    <script type="text/javascript">
        $(function () {
            initKindEditor_addblog('content', "100%", 300, 'articleContent', 'true');
            initSimpleImageUpload('imageFile', 'article', callback, '', 'true', '500', '332');
        });

    </script>

</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <a href="${ctx}/admin/article/showlist">
            <span>资讯管理</span>
        </a>
        &gt;
        <span>添加资讯</span>
    </legend>
    <div class="layui-field-box">
    <form action="${ctx}/admin/article/addarticle"  class="layui-form" method="post" id="articleForm" data-validator-option="{stopOnError:false, timely:false}">
        <input type="hidden" name="article.imageUrl">
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w"><font color="red">*</font>标题</label>
            <div class="layui-input-block">
                <input class="layui-input layui-input-6" name="article.title" data-rule="标题:required;length(0~190);" type="text" />
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">摘要</label>
            <div class="layui-input-block layui-textarea-block">
                 <textarea name="article.summary" class="layui-textarea" data-rule="摘要:required;length(0~490);" id="summary"></textarea>
            </div>
        </div>
        <div class="layui-form-item " style="display: none">
            <label class="layui-form-label layui-form-label-w">点击数</label>
            <div class="layui-input-block">
                <input name="article.clickNum" id="clickNum" type="text" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
            </div>
        </div>
        <div class="layui-form-item " style="display: none">
            <label class="layui-form-label layui-form-label-w">发布时间</label>
            <div class="layui-input-block">
                <input name="article.publishTime" id="publishTime" readonly="readonly" type="text" />
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">封面图片</label>
            <div class="layui-input-block">
                <img id="showImage" width="180" height="100" src="${ctx }/static/admin/assets/logo.png">
                <input type="button" value="上传" id="imageFile"/>
                <font class="fsize12 c-red f-fM vam">请上传宽高为： 500*332 的图片</font>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label layui-form-label-w">内容</label>
            <div class="layui-input-block" style="width: 55%">
                <textarea name="articleContent.content" class="" id="content"></textarea>
            </div>
        </div>
        <div class="layui-form-item " style="display: none">
            <label class="layui-form-label layui-form-label-w">排序值</label>
            <div class="layui-input-block">
                <input name="article.sort" id="sort" type="text" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
            </div>
        </div>
        <div class="layui-form-item " >
            <div class="layui-input-block">
                <input onclick="saveArticle()" class="layui-btn layui-btn-danger" type="button" value="保存">
            </div>
        </div>
    </form>
        </div>
</fieldset>
</body>
</html>