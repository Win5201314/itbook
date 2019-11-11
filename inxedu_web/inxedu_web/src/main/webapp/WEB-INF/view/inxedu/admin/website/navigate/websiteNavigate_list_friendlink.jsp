<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>友情链接</title>
    <script type="text/javascript">
        var form;
        layui.use(['form', 'laydate'], function () {
            form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
            //各种基于事件的操作，下面会有进一步介绍
        });
        function delNavigate(id) {
            if (confirm("真的要删除吗？") == true) {
                $.ajax({
                    url: "${cxt}/admin/website/delNavigate/" + id,
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        if (result.message == "true") {
                            msgshow("删除成功");
                            window.location.reload();
                        }
                    }
                });
            }
        }
        //冻结或解冻导航
        function freezeNavigate(id, status) {
            $.ajax({
                url: "${cxt}/admin/website/freezeNavigate",
                type: "post",
                data: {"websiteNavigate.id": id, "websiteNavigate.status": status},
                dataType: "json",
                success: function (result) {
                    if (result.message == "true") {
                        if (status == 0) {
                            msgshow("解冻成功");
                        } else {
                            msgshow("冻结成功");
                        }
                        window.location.reload();
                    }
                }
            });
        }

        /*点击修改后将标签换成input修改内容*/
        function update(navId, obj) {
            $("input[name='websiteNavigate.name']").val($(obj).parent().parent().find(".navigateName").text());
            $("input[name='websiteNavigate.id']").val(navId);
            $("input[name='websiteNavigate.orderNum']").val($(obj).parent().parent().find(".navigateOrderNum")[0].value);
            $("input[name='websiteNavigate.url']").val($(obj).parent().parent().find(".navigateUrl")[0].innerText);
            layer.closeAll(); //疯狂模式，关闭所有层
            layer.open({
                title:'修改配置',
                type: 1,
                shade: false,
                area: '550px',
                maxmin: false,
                content: $("#updateNaveInfo")
            });

        }
        /*新建链接*/
        function addNavigates() {

            $("input[name='websiteNavigate.name']").val("");
            $("input[name='websiteNavigate.id']").val(0);
            $("input[name='websiteNavigate.url']").val("");
            $("input[name='websiteNavigate.orderNum']").val(0);
            layer.open({
                title: '新建链接',
                type: 1,
                shade: false,
                area: '550px',
                maxmin: false,
                content: $("#updateNaveInfo")
            });
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend> 友情链接 </legend>
    <!-- /tab1 begin-->
    <div class="numb-box">
        <div class="commonWrap">
            <form class="layui-form" action="${ctx}/admin/website/navigates" name="searchForm" id="searchForm"
                  method="post">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <td align="center">
                            <span>名称</span>
                        </td>
                        <td align="center">
                            <span>跳转地址</span>
                        </td>
                        <td align="center">
                            <span>操作</span>
                        </td>
                    </tr>
                    </thead>
                    <tbody id="tabS_02" align="center">
                    <c:if test="${websiteNavigates.size()>0}">
                        <c:forEach items="${websiteNavigates}" var="navigate" varStatus="index">
                            <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                                <td class="navigateName">${navigate.name }</td>
                                <td class="navigateUrl">${navigate.url }</td>
                                <input class="navigateOrderNum" type="hidden" value="${navigate.orderNum }">
                                <td class="c_666 czBtn" align="center">
                                    <button type="button" class="layui-btn layui-btn-small   save"
                                            onclick="update(${navigate.id},this)">修改
                                    </button>
                                    <button type="button" class="layui-btn layui-btn-small   delete"
                                            onclick="delNavigate(${navigate.id})">删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${websiteNavigates.size()==0||websiteNavigates==null}">
                        <tr>
                            <td align="center" colspan="16">
                                <div class="tips">
                                    <span>还没有链接！</span>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
                <div class="layui-form-item">
                    <div class="tac mt20">
                        <button onclick="addNavigates()" class="layui-btn layui-btn-small layui-btn-danger" type="button">新建链接</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div id="updateNaveInfo" style="display: none">
        <div class="numb-box">
            <form class="layui-form" action="${ctx}/admin/website/updateNav">
                <input type="hidden" name="websiteNavigate.type" value="FRIENDLINK"/>
                <input type="hidden" name="websiteNavigate.newPage" value="0"/>
                <input type="hidden" name="websiteNavigate.id" value="0"/>
                <input type="hidden" name="websiteNavigate.orderNum" value="0"/>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">名称</label>
                    <div class="layui-input-block">
                        <input type="text" required="required"  name="websiteNavigate.name" class="layui-input layui-input-6"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">跳转地址</label>
                    <div class="layui-input-block">
                        <input type="text" required="required"  name="websiteNavigate.url"  class="layui-input layui-input-6"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block mt20">
                        <button type="submit" class="layui-btn layui-btn-small layui-btn-danger">保 存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</fieldset>
</body>
</html>