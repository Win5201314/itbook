<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>评论列表</title>

    <script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script>
        function delcomment(commentId){
            $.ajax({
                url : baselocation + '/admin/comment/del/'+commentId,
                type : 'post',
                async : true,
                dataType : 'text',
                success : function(result) {
                    location.reload();
                }
            });
        }
        $(function(){
            /**加载时间控件*/
            $("#beginCreateTime,#endCreateTime").datetimepicker({
                regional:"zh-CN",
                changeMonth: true,
                dateFormat:"yy-mm-dd",
                timeFormat: "HH:mm:ss"
            });
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>评论列表</span>
        &gt;
        <a title="返回上一页" onclick="history.go(-1)" class="fsize14 c-master f-fM" href="javascript:void(0)">
            返回上一页
        </a>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/comment/query/${comment.otherId}/${comment.type}" method="post" id="searchForm">
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
        </form>
        <form action="${ctx}/admin/article/publishOrDelete" id="publishOrDeleteForm" method="post">
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <thead>
                <tr>
                    <td align="center">昵称</td>
                    <td align="center">邮箱</td>
                    <td align="center">手机</td>
                    <td align="center">评论内容</td>
                    <td align="center" width="232">操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${commentList}" var="com" varStatus="index">
                    <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                        <td align="center">${com.userName}</td>
                        <td align="center">${com.email}</td>
                        <td align="center">${com.mobile}</td>
                        <td align="center"><c:out value="${fn:substring(com.content,0,20)}"></c:out></td>
                        <td align="center">
                                <%--<button onclick="window.location.href='${ctx}/admin/commentreply/query?pCommentId=${com.commentId}'" class="layui-btn layui-btn-small" type="button">回复列表</button>--%>
                            <button onclick="delcomment('${com.commentId}')" class="layui-btn layui-btn-small" type="button">删除</button>
                                <%--<button onclick="window.location.href='${ctx}/admin/commentreply/info/${com.commentId}'" class="layui-btn layui-btn-small" type="button">修改</button>--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
    </div>
</fieldset>
</body>
</html>