<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>频道列表</title>
    <script type="text/javascript">
        /**
         * 创建频道
         */
        function createchan() {
            if (!confirm('确实要创建吗?')) {
                return;
            }
            $.ajax({
                url: baselocation + '/admin/video/createchan',
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

        /**
         * 删除频道
         */
        function deletechan(streamId) {
            if (!confirm('确实要删除吗?')) {
                return;
            }
            $.ajax({
                url: baselocation + '/admin/video/deletechan/' + streamId,
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

        /**
         * 选择频道
         */
        function selectchan(id) {
            opener.addChanNum(id);
            window.close();
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        频道列表
    </legend>
    <div class="layui-field-box">
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="createchan()">创建频道</button>
                </div>
        <table class="layui-table" lay-even="" lay-skin="row">
            <thead>
            <tr>
                <th align="center" style="display: none">ID</th>
                <th align="center">频道名称</th>
                <th align="center">状态</th>
                <th align="center">频道号</th>
                <th align="center">码率</th>
                <th align="center">分辨率</th>
                <th align="center">频道名称</th>
                <th align="center">频道类型</th>
                <th align="center">媒体类型</th>
                <th align="center">源位置</th>
                <th align="center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${streamList}" var="stream" varStatus="index">
                <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                    <td align="center" style="display: none">${stream.id}</td>
                    <td align="center">${stream.name}</td>
                    <td align="center">${stream.statcode}</td>
                    <td align="center">${stream.id}</td>
                    <td align="center">${stream.mediatype}</td>
                    <td align="center">${stream.mediatype}</td>
                    <td align="center">${stream.mediatype}</td>
                    <td align="center">${stream.mediatype}</td>
                    <td align="center">${stream.mediatype}</td>
                    <td align="center">${stream.mediatype}</td>
                    <td align="center">
                        <button onclick="deletechan(${stream.id})" class="layui-btn layui-btn-small" type="button">删除</button>
                        <button onclick="selectchan(${stream.id})" class="layui-btn layui-btn-small" type="button">选择</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</fieldset>
</body>
</html>