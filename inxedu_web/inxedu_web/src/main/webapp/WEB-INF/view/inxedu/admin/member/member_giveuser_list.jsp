<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>赠送会员列表</title>
    <script type="text/javascript">
        $(function () {
            $("#memberType").val(${queryMemberSale.type}+"")
        });

        //赠送会员
        function toFreeMember(memberSaleId) {
            if (confirm("确定赠送会员吗?")) {
                $.ajax({
                    url: "${ctx}/admin/user/freeMember",
                    data: {
                        "userId": "${userId}",
                        "memberSaleId": memberSaleId
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.success == true) {
                            layer.msg("赠送成功！", {icon: 1, shift: 6});
                        } else {
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    },
                    error: function (error) {
                        layer.msg("服务器繁忙，请稍后再试", {icon: 5, shift: 6});
                    }
                })
            }
        }
    </script>
</head>
<body>
<div class="numb-box">
    <fieldset class="layui-elem-field">
        <legend>
            <span>用户管理</span>
            >
            <span>赠送会员</span>
        </legend>
        <div class="layui-field-box">
            <form action="${ctx}/admin/user/memberList/${userId}" method="post" id="searchForm" class="layui-form">
                <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">会员名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" name="queryMemberSale.name" value="${queryMemberSale.name}" placeholder="会员名称"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">会员类型：</label>
                        <div class="layui-input-inline">
                            <select  id="memberType" name="queryMemberSale.type">
                                <option value="0">查看所有</option>
                                <c:forEach items="${memberTypes}" var="memberType">
                                    <option value="${memberType.id}">${memberType.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <a title="查找会员" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查找会员</a>
                    </div>
                </div>
            </form>
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <thead>
                <tr>
                    <td align="center" width="150px">会员</td>
                    <td align="center">类型</td>
                    <td align="center">价钱</td>
                    <td align="center">有效结束时间</td>
                    <td align="center">操作</td>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${memberSaleDTOs}" var="membersale" varStatus="index">
                    <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                        <td align="center">${membersale.name}</td>
                        <td align="center">
                                ${memberTypes[membersale.type-1].title}
                        </td>
                        <td align="center">${membersale.price}</td>
                        <td align="center">购买后${membersale.days}个月</td>

                        <td align="center">
                            <button onclick="toFreeMember(${membersale.id})" class="layui-btn layui-btn-small" type="button">赠送</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
        </div>
    </fieldset>
</div>
</body>
</html>