<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>短信列表</title>

    <script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        $(function () {
            var type = "${userMobileMsg.type}";
            if (type != null && type != "") {
                $("#type").val('${userMobileMsg.type}');
            }
            var status = "${userMobileMsg.status}";
            if (status != null && status != "") {
                $("#status").val('${userMobileMsg.status}');
            }
        });
        function submitSearch() {
            $("#pageCurrentPage").val(1);
            $("#searchForm").submit();
        }
        function clean() {
            $("#nickname,#useremail,#mobile,#startDate,#endDate").val("");
            $("#type,#status").val(0);
        }

        function delMsg(id) {
            if (confirm("确定要删除吗？")) {
                $.ajax({
                    url: "${ctx}/admin/mobile/delMsgById/" + id,
                    data: {},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        layer.msg(result.message, {icon: 1, shift: 6});
                        if (result.success == true) {
                            window.location.href = "/admin/user/sendMsglist";
                        }
                    }
                })
            }
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        短信列表
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="${ctx}/admin/mobile/sendMsglist" method="post" id="searchForm">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>


            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" type="text" name="userMobileMsg.mobile" value="${userMobileMsg.mobile}" id="mobile"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="userMobileMsg.type" id="type">
                            <option value="0" selected="selected">请选择</option>
                            <option value="1">普通</option>
                            <option value="2">定时</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="userMobileMsg.status" id="status">
                            <option value="0" selected="selected">请选择</option>
                            <option value="1">已发送</option>
                            <option value="2">未发送</option>
                            <option value="3">发送失败</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" name="userMobileMsg.startDate" placeholder="开始时间" id="startDate" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" value="${userMobileMsg.startDate}">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" name="userMobileMsg.endDate" placeholder="结束时间" id="endDate" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" value="${userMobileMsg.endDate}">
                    </div>
                </div>
                <div class="layui-inline">
                    <a title="查询" onclick="submitSearch()" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查询</a>
                    <a title="新增短信" class="layui-btn layui-btn-small layui-btn-danger" href="${ctx}/admin/mobile/toMsg">新增短信</a>
                </div>
            </div>

            <%--<a title="清空" class="layui-btn layui-btn-small " onclick="clean()" href="javascript:void(0)">清空
            </a>--%>

            </a>
        </form>
        <table class="layui-table">
            <thead>
            <tr>
                <td align="center" width="6%">短信类型</td>
                <td align="center" width="12%">短信内容</td>
                <td align="center" width="13%">手机号</td>
                <td align="center" width="12%">创建时间</td>
                <td align="center" width="12%">发送时间</td>
                <td align="center" width="8%">状态</td>
                <td align="center" width="8%">操作人</td>
                <td align="center" width="9%">操作</td>
            </tr>
            </thead>
            <tbody>
            <c:if test="${list.size()>0}">
                <c:forEach items="${list}" var="msg" varStatus="index">
                    <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                        <td align="center">${msg.type==1?'普通':'定时'}</td>
                        <td align="center">${fn:substring(msg.content,0,50) }</td>
                        <td align="center">${fn:substring(msg.mobile,0,55) }<c:if test="${msg.mobile.length()>55 }">......</c:if></td>
                        <td align="center"><fmt:formatDate value="${msg.createTime }" type="both"/></td>
                        <td><fmt:formatDate value="${msg.sendTime }" type="both"/></td>
                        <td align="center">
                            <c:if test="${msg.status==1 }">已发送</c:if>
                            <c:if test="${msg.status==2 }">未发送</c:if>
                            <c:if test="${msg.status==3 }">发送失败</c:if>
                        </td>
                        <td align="center">${msg.loginName}</td>
                        <td class="c_666 czBtn" align="center">
                            <button onclick="window.location.href='${ctx}/admin/mobile/sendMsgInfo/${msg.id }'" class="layui-btn layui-btn-small" type="button">查看</button>
                            <c:if test="${msg.status==2&&msg.type==2 }">
                                <button onclick="delMsg(${msg.id })" class="layui-btn layui-btn-small" type="button">删除</button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${list.size()==0||list==null}">
                <tr>
                    <td align="center" colspan="16">
                        <div class="tips">
                            <span>还没有短信信息！</span>
                        </div>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</fieldset>
</body>
</html>
