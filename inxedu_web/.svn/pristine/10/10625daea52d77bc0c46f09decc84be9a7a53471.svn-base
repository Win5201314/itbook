<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学习记录</title>

    <script type="text/javascript">

        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;

            form.on('select(filter)', function (data) {
            });
        });


    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        学习记录
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/studyHistory/list" method="post" id="searchForm" class="layui-form">
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
            <div class="layui-form-item">

                <div class="layui-inline">
                    <label class="layui-form-label">用户:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="courseStudyhistory.keyword" value="${courseStudyhistory.keyword}" placeholder="账号/邮箱/手机/昵称"/>
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">课程名:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="courseStudyhistory.courseName" value="${courseStudyhistory.courseName}" placeholder="课程名"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">章节名:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="courseStudyhistory.kpointName" value="${courseStudyhistory.kpointName}" placeholder="章节名"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">学习时间:</label>
                    <div class="layui-form-mid">
                        <input placeholder="开始时间" class="layui-input" name="courseStudyhistory.beginCreateTime"
                               value="<fmt:formatDate value="${courseStudyhistory.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text"
                               readonly="readonly" style="width: 128px;" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})"/>
                    </div>
                    <div class="layui-form-mid">
                        <input placeholder="结束时间" class="layui-input" name="courseStudyhistory.endCreateTime"
                               value="<fmt:formatDate value="${courseStudyhistory.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  type="text"
                               readonly="readonly" style="width: 128px;" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="$('#searchForm').submit();">查找</button>
                    </div>
                </div>
            </div>
        </form>
        <table class="layui-table" lay-even="" lay-skin="row">
            <thead>
            <tr>
                <th align="center" style="display: none">ID</th>
                <th align="center">邮箱</th>
                <th align="center">手机</th>
                <th align="center">账号</th>
                <th align="center">昵称</th>
                <th align="center">课程名</th>
                <th align="center">章节</th>
                <th align="center">时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${courseStudyhistories}" var="studyHistory" varStatus="index">
                <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                    <td align="center" style="display: none">${studyHistory.id}</td>
                    <td align="center">${studyHistory.userEmail}</td>
                    <td align="center">${studyHistory.userMobile}</td>
                    <td align="center">${studyHistory.userName}</td>
                    <td align="center">${studyHistory.userShowName}</td>
                    <td align="center">${studyHistory.courseName}</td>
                    <td align="center">${studyHistory.kpointName}</td>


                    <td align="center">
                        <fmt:formatDate value="${studyHistory.updateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
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