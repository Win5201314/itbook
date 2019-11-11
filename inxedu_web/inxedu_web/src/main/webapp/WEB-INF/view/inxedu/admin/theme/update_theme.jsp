<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>修改前台主题色</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/common/bigcolorpicker/jquery.bigcolorpicker.css"/>
    <script type="text/javascript" src="${ctx }/static/common/bigcolorpicker/jquery.bigcolorpicker.js"></script>
    <script>
        $(function () {
            $("#imageColor").bigColorpicker("imageColor", "L", 10);
        });
        function formSubmit() {
            $("#form").submit();
        }


        /**
         *修改主题色
         */
        function changeThemeColor(color){
            $(".dialog-box-boy-in").find("div").removeClass("current");
            $("#themeColor"+color).addClass("current");
            $.ajax({
                url :baselocation +  "/theme/ajax/update",
                data : {
                    "color":color
                },
                type : 'post',
                dataType : 'json',
                async:false,
                success : function(result) {
                    if(result.success==true){
                        layer.msg("换肤成功请去前台刷新查看效果", {icon: 1, shift: 6});
                    }
                }
            });
        }

    </script>
    <style>
        .dialog-box-boy-in ul li {
            float: left;
            width: 33.33%;
        }
    </style>
</head>
<body>
<div class="numb-box"><fieldset class="layui-elem-field">
    <legend>
        <span>网站信息</span>
        &gt;
        <span>模板切换</span>
    </legend>

    <div class="">
        <form action="${ctx}/admin/theme/update" method="post" id="form">
            <input type="hidden" name="teacher.picPath" id="imagesUrl"/>
            <p>
                    <div class="container">
                        <article class="dialog-box-boy-in">
                            <ul class="clearfix">
                                <li>
                                    <div class="box-boy-in-i " id="themeColororange">
                                        <a href="javascript:changeThemeColor('orange')" class="pr">
                                            <img width="300" src="/static/inxweb/img/pic/one-master.jpg" alt="" class="pic">
                                            <%--<em class="xz" id="triangle-bottomright">&nbsp; </em>--%>
                                        <div style="display:none;" class="loging"><img alt="" src="/static/inxweb/img/loding.gif"></div>
                                    </a></div>
                                    <div class="box-boy-in-i ">
                                        <button onclick="changeThemeColor('orange')" class="layui-btn layui-btn-small" style="float: none" type="button">网络课堂--模板一</button>
                                    </div>

                                </li>
                                <li>
                                    <div class="box-boy-in-i" id="themeColorgreen"><a href="javascript:changeThemeColor('green')" class="pr"> <img width="300" src="/static/inxweb/img/pic/two-green.jpg" alt="" class="pic">
                                        <%--<em class="xz" id="triangle-bottomright">
                                        &nbsp; </em>--%>
                                        <div style="display:none;" class="loging"><img alt="" src="/static/inxweb/img/loding.gif"></div>
                                    </a></div>
                                    <div class="box-boy-in-i ">
                                        <button onclick="changeThemeColor('green')" class="layui-btn layui-btn-small" style="float: none" type="button">网络课堂--模板二</button>
                                    </div>
                                </li>
                                <li>
                                    <div class="box-boy-in-i" id="themeColorblue"><a href="javascript:changeThemeColor('blue')" class="pr"> <img width="300" src="/static/inxweb/img/pic/three-blue.jpg" alt="" class="pic">
                                        <%--<em class="xz" id="triangle-bottomright">
                                        &nbsp; </em>--%>
                                        <div style="display:none;" class="loging"><img alt="" src="/static/inxweb/img/loding.gif"></div>
                                    </a></div>
                                    <div class="box-boy-in-i ">
                                        <button onclick="changeThemeColor('blue')" class="layui-btn layui-btn-small" style="float: none" type="button">网络课堂--模板三</button>
                                    </div>
                                </li>
                            </ul>
                        </article>
                    </div>
            </p>


                <p>
                    <label for="sf"><font color="red">*</font>&nbsp;前台主题色:</label>
                    <input type="text" name="color" id="imageColor" class="{required:true} sf" data-rule="required;"/>
                    <span class="field_desc"></span>
                </p>
                <p>
                    <input type="button" value="提 交" class="layui-btn layui-btn-small" onclick="formSubmit()" />
                    <%--<input type="button" value="返 回" class="layui-btn layui-btn-small" onclick="javascript:history.go(-1);" />--%>
                </p>

               <%-- <tr style="margin-top: 30px">

                    <td>
                        <font color="red">*</font>前台主题色:
                    </td>
                    <td style="text-align: left;">
                        <input type="text" id="imageColor" name="color" value=""/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <a class="layui-btn layui-btn-small" title="提 交" href="javascript:void(0)" onclick="formSubmit()">提 交</a>
                        <a class="layui-btn layui-btn-small" title="返 回" href="javascript:history.go(-1);">返 回</a>
                    </td>
                </tr>--%>
        </form>
    </div>
</fieldset></div>
</body>
</html>
