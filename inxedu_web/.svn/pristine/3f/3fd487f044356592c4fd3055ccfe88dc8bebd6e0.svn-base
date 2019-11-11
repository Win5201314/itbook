<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>会员类型</title>

    <script type="text/javascript">
        function updateStatus(id, status) {
            layui.use(['form'], function () {
                var form = layui.form();
            });
            $.ajax({
                url: "${cxt}/admin/membertype/status.json",
                type: "post",
                data: {"memberType.id": id, "memberType.status": status},
                dataType: "json",
                success: function (result) {
                    if (result.message == 'true') {
                        if (status == 0) {
                            layer.msg("启用成功", {icon: 1, shift: 6});
                        } else if (status == 1) {
                            layer.msg("停用成功", {icon: 1, shift: 6});
                        }
                        window.location.reload();
                    }
                }
            });

        }
        function del(id) {
            if(!confirm("确定删除吗？")){
                return;
            }
            $.ajax({
                url: "${cxt}/admin/membertype/delete/"+id,
                type: "post",
                data: {},
                dataType: "json",
                success: function (result) {
                    if (result.success) {
                        layer.msg("删除成功", {icon: 1, shift: 6});
                        window.location.reload();
                    }
                }
            });

        }


    </script>
</head>
<body>

<fieldset class="layui-elem-field">
    <legend>
        <span>会员类型管理</span>
        &gt;
        <span>会员类型列表</span>
    </legend>

    <div class="layui-field-box">
        <table class="layui-table">
            <thead>
            <tr>
                <th width="25%"><span>ID</span></th>
                <th width="25%"><span>名称</span></th>
                <th width="25%"><span>状态</span></th>
                <th width="25%"><span>操作</span></th>
            </tr>
            </thead>
            <tbody id="tabS_02" align="center">
            <c:if test="${memberTypeList.size()>0}">
                <c:forEach items="${memberTypeList}" var="memberType">
                    <tr>
                        <td>${memberType.id }</td>
                        <td>${memberType.title }</td>
                        <td>
                            <c:if test="${memberType.status==0}">正常</c:if>
                            <c:if test="${memberType.status==1}">停用</c:if>
                        </td>
                        <td>
                            <button onclick="window.location.href=${cxt}'/admin/membertype/doupdate/${memberType.id}'"
                                    class="layui-btn layui-btn-small" type="button">修改
                            </button>
                            <button onclick="del(${memberType.id})"
                                    class="layui-btn layui-btn-small" type="button">删除
                            </button>
                            <c:if test="${memberType.status==0}">
                                <button onclick="updateStatus(${memberType.id},1)" class="layui-btn layui-btn-small"
                                        type="button">停用
                                </button>
                            </c:if>
                            <c:if test="${memberType.status==1}">
                                <button onclick="updateStatus(${memberType.id},0)" class="layui-btn layui-btn-small"
                                        type="button">启用
                                </button>
                            </c:if>
                            <button onclick="window.open(${cxt}'/admin/membertype/memberCourse/${memberType.id}')"
                                    class="layui-btn layui-btn-small" type="button">会员课程
                            </button>

                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${memberTypeList.size()==0||memberTypeList==null}">
                <tr>
                    <td align="center" colspan="16">
                        <div class="tips">
                            <span>还没有会员类型！</span>
                        </div>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <p class="hLh20 fsize12 c-666 f-fM">
           提示：每种会员只能看自己的课程 ，黄金会员只能看课程中设置为黄金会员的课程<br/>
        </p>
        <div class="layui-form-item tac mt10">
            <input onclick="window.location.href=${cxt}'/admin/membertype/doadd'" value="新建会员类型" class="layui-btn layui-btn-danger" type="button">
        </div>
    </div>
</fieldset>
</body>
</html>
