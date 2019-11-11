<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>邮箱列表</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        function submitSearch() {
            $("#pageCurrentPage").val(1);
            $("#searchForm").submit();
        }
        function clean() {
            $("#email,#status,#startDate,#endDate").val("");
            $("#type,#status").val(0);
        }
        $(function () {
            $("#type").val('${userEmailMsg.type}');
            $("#status").val('${userEmailMsg.status}');
        })
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>邮箱列表</span>
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="${ctx}/admin/email/sendEmaillist" method="post" id="searchForm">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
            <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userEmailMsg.email" class="layui-input" value="${userEmailMsg.email}" id="email"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="userEmailMsg.type" id="type">
                            <option value="0">请选择</option>
                            <option value="1">普通</option>
                            <option value="2">定时</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">是否发送</label>
                    <div class="layui-input-inline">
                        <select name="userEmailMsg.status" id="status">
                            <option value="0">请选择</option>
                            <option value="1">已发送</option>
                            <option value="2">未发送</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" name="userEmailMsg.startDate" lay-verify="date" placeholder="开始时间" value="${userEmailMsg.startDate}" id="startDate" class="layui-input" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" name="userEmailMsg.endDate" lay-verify="date" placeholder="结束时间" value="${userEmailMsg.endDate}" id="endDate" class="layui-input" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                </div>
                <div class="layui-inline">
                    <a title="查询" onclick="submitSearch()" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查询</a>
                    <a title="新增邮件" class="layui-btn layui-btn-small layui-btn-danger" href="${ctx}/admin/email/toEmailMsg">新增邮件</a>
                </div>
            </div>
            <%--<a title="清空" class="layui-btn layui-btn-small" onclick="clean()" href="javascript:void(0)">清空
            </a>--%>
        </form>
        <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
            <thead>
            <tr>
                <td align="center" width="150px">ID</td>
                <td align="center">邮件类型</td>
                <td align="center">是否发送</td>
                <td align="center">邮件标题</td>
                <td align="center">邮箱</td>
                <td align="center">创建时间</td>
                <td align="center">发送时间</td>
                <td align="center">操作人</td>
                <td align="center">操作</td>
            </tr>
            </thead>

            <tbody>
            <c:if test="${list.size()>0}">
                <c:forEach items="${list}" var="msg" varStatus="index">
                    <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                            <%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
                        <td align="center">${msg.id }</td>
                        <td align="center">${msg.type==1?'普通':'定时' }</td>
                        <td align="center">${msg.status==1?'已发送':'未发送' }</td>
                        <td align="center">${msg.title }</td>
                        <td align="center">${fn:substring(msg.email, 0, 50)}</td>
                        <td align="center"><fmt:formatDate value="${msg.createTime }" type="both"/></td>
                        <td align="center"><fmt:formatDate value="${msg.sendTime }" type="both"/></td>
                        <td align="center">${msg.loginName}</td>
                        <td align="center" class="c_666 czBtn" align="center">
                            <button onclick="window.location.href='${ctx}/admin/email/sendEmailMsgInfo/${msg.id }'"
                                    class="layui-btn layui-btn-small" type="button">查看
                            </button>
                            <c:if test="${msg.type==2&&msg.status==2}">
                                <button onclick="window.location.href='${ctx}/admin/email/sendEmail/del?id=${msg.id }'"
                                        class="layui-btn layui-btn-small" type="button">删除
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${list.size()==0||list==null}">
                <tr>
                    <td align="center" colspan="16">
                        <div class="tips">
                            <span>还没有邮件信息！</span>
                        </div>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</body>
</html>
