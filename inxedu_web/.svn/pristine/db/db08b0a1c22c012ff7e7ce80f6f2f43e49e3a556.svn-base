<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>套餐课程列表</title>
    <script type="text/javascript">

        // 修改排序
        function updateOrder(id) {
            $(".layui-input-1").mouseleave(function(){
                var orderNum = $("#orderNum_" + id).val();
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx}/admin/cou/updateCoursePackageOrderNum",
                    data: {"courseId": id, "orderNum": orderNum},
                    success: function (result) {
                        if (result.success) {

                            window.location.reload();
                        }
                    }
                })
            });
        }
        //清空
        function submitclear() {
            $("#name").val("");
            $("#subjectNameBtn").val("");
        }

        function submitSearch() {
            $("#searchForm").submit();
        }
        //删除课程
        function delCourse(id) {
            if (confirm("是否删除?")) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx}/admin/cou/delCoursePacage/" + id + "/${course.courseId}",
                    cache: true,
                    success: function (result) {
                        if (result.success) {
                            location.reload();
                        }
                    }
                });
            }
        }
        //选择课程
        function showNewwin() {
            window.open('${ctx}/admin/cou/showCourseList', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
        }
        //选择课程
        function addnewArray(array) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx}/admin/cou/addCoursePackage",
                data: {"courseId": '${course.courseId}', "ids": array.toString()},
                cache: true,
                success: function (result) {
                    if (result.success) {
                        location.reload();
                    }
                }
            });
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        套餐课程列表
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/cou/packages/${course.courseId}" method="post" id="searchForm">
            <input type="hidden" name="course.courseId" value="${course.courseId}"/>
            <input type="hidden" id="subjectId" name="course.subjectId" value="${course.subjectId }"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">课程名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="course.courseName" class="layui-input" value="${course.courseName}" id="name"/>
                    </div>

                    <a title="查找课程" onclick="submitSearch()" class="layui-btn layui-btn-small" href="javascript:void(0)">
                        查找
                    </a>
                    <%--<a title="清空" onclick="$('#searchForm input:text').val('');$('#searchForm select').val(0);$('select').change();"
                        class="layui-btn layui-btn-small" href="javascript:void(0)">
                        清空
                    </a>--%>
                    <a title="添加课程" class="layui-btn layui-btn-small" href="javascript:showNewwin()">
                        添加课程
                    </a>
                    <a title="返回" class="layui-btn layui-btn-small" href="javascript:history.go(-1)">
                        返回
                    </a>
                </div>
            </div>


            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <thead>
                <tr>
                    <td align="center">ID</td>
                    <td align="center" width="150px">课程名称</td>
                    <td align="center">价格</td>
                    <td align="center">排序值</td>
                    <td align="center">添加时间</td>
                    <td align="center">操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courseDtoList}" var="course" varStatus="index">
                    <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                        <td align="center">${course.courseId}</td>
                        <td align="center">${course.courseName}</td>
                        <td align="center">${course.currentPrice}</td>
                        <td align="center" ><input type="text"  class="layui-input layui-input-1" name="course.orderNum" value="${course.orderNum}" id="orderNum_${course.courseId}" onclick="updateOrder(${course.courseId})" autocomplete="off"/></td>
                        <td align="center"><fmt:formatDate type="both" value="${course.addTime}"/></td>
                        <td align="center">
                            <button onclick="delCourse(${course.courseId})" class="layui-btn layui-btn-small" type="button">删除</button>
                            <%--<button onclick="updateOrder(${course.courseId})" class="layui-btn layui-btn-small" type="button">修改</button>--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</fieldset>
</body>
</html>