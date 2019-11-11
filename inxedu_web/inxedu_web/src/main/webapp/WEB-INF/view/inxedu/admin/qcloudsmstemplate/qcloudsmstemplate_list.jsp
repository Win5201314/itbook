<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>腾讯云短信管理</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        /**
         * 删除
         */
        function delQcloudSmsTemplate(id, em) {
            if (!confirm('确实要删除吗?')) {
                return;
            }
            $.ajax({
                url: '/admin/ajax/qcloudSmsTemplateDel/' + id,
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result.success == false) {
                        layer.msg(result.message, {icon: 1, shift: 6});
                    } else {
                        location.reload();
                    }
                },
                error: function (error) {
                    layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
                }
            });
        }
        /*查询模板内容*/
        function selectContent(id) {
            $.ajax({
                url: '/admin/ajax/selectContent/' + id,
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    confirm(result.entity.text)
                },
                error: function (error) {
                    layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
                }
            })
        }
        function submitForm() {
            var type = $("#type").val();
            if (type == "sign") {
                $("#searchForm").attr('action', '${ctx}/admin/qcloudSmsTemplate/list?type=sign');
                $("#searchForm").submit()
            } else {
                $("#searchForm").attr('action', '${ctx}/admin/qcloudSmsTemplate/list?type=template');
                $("#searchForm").submit()
            }
        }
        function clearInfo() {
            $("#searchForm input:text").val("")
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        腾讯云短信管理
    </legend>
    <div class="layui-field-box">


        <form class="layui-form" action="${ctx}/admin/qcloudSmsTemplate/list" method="post" id="searchForm">
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
            <input style="display: none" id="type" type="button" name="qcloudSmsTemplate.type"
                   value="${qcloudSmsTemplate.type }" placeholder="类型template(模板) sign（前面）ID"/>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">主键ID</label>
                    <div class="layui-input-inline">
                        <input type="number" name="qcloudSmsTemplate.id" lay-verify="number" autocomplete="off"
                               value="${qcloudSmsTemplate.id }" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="qcloudSmsTemplate.createTime" id="createTime" lay-verify="date"
                               autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})"
                               value="<fmt:formatDate value="${qcloudSmsTemplate.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-inline">
                        <input type="text" name="qcloudSmsTemplate.text" value="${qcloudSmsTemplate.text }"
                               class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <a title="查找短信信息表" onclick="submitForm()" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查找模板信息表</a>
                </div>
            </div>


            <%--<a title="清空" onclick="clearInfo()"
                class="layui-btn layui-btn-small" href="javascript:void(0)">清空
            </a>--%>
        </form>
        <table class="layui-table">
            <thead>
            <tr>
                <td align="center">主键</td>
                <td align="center">类型</td>
                <td align="center">内容</td>
                <td align="center">相关id</td>
                <td align="center">内容审批</td>
                <td align="center">创建时间</td>
                <td align="center">操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${qcloudSmsTemplateList}" var="qcloudSmsTemplate" varStatus="index">
                <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                    <td align="center">${qcloudSmsTemplate.id }</td>
                    <td align="center">
                        <c:if test="${qcloudSmsTemplate.type=='sign'}">签名</c:if>
                        <c:if test="${qcloudSmsTemplate.type=='template'}">短信</c:if>
                            <%--<c:if test="${qcloudSmsTemplate.type=='template1'}">营销短信</c:if>--%>
                    </td>
                    <td align="center">
                        <c:out value="${fn:substring(qcloudSmsTemplate.text, 0, 6)}......"/>
                    </td>
                    <td align="center">${qcloudSmsTemplate.otherId }</td>
                    <td align="center">
                        <c:if test="${qcloudSmsTemplate.status==0}">已通过</c:if>
                        <c:if test="${qcloudSmsTemplate.status==1}">待审核</c:if>
                        <c:if test="${qcloudSmsTemplate.status==2}">已拒绝</c:if>
                    </td>

                    <td align="center"><fmt:formatDate value="${qcloudSmsTemplate.createTime }"
                                                       pattern="yyyy/MM/dd HH:mm"/></td>
                    <td align="center">
                        <button onclick="selectContent(${qcloudSmsTemplate.id})" class="layui-btn layui-btn-small" type="button">查看内容</button>
                        <button onclick="delQcloudSmsTemplate(${qcloudSmsTemplate.id},this)" class="layui-btn layui-btn-small" type="button">删除</button>
                            <%--<button onclick="window.location.href='${ctx}/admin/qcloudSmsTemplate/toUpdate/${qcloudSmsTemplate.id}'" class="layui-btn layui-btn-small" type="button">修改</button>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</body>
</html>