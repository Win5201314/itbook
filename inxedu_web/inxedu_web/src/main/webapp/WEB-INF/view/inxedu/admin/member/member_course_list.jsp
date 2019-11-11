<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会员课程列表</title>
    <script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>
    <script type="text/javascript">
        layui.use(['form'], function () {
            var form = layui.form();
        });
        //删除课程
        function delCourse(id) {
            if (confirm("是否删除?")) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx}/admin/deleteCourse/ajax/" + id + "/${memberTypeId}",
                    cache: true,
                    success: function (result) {
                        if (result.success == true) {
                            window.location.reload();
                        }
                    }
                });
            }
        }
        //选择课程
        function showNewwin() {
            window.open('${ctx}/admin/cou/showCourseList?type=member', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
        }
        //选择课程
        function addnewArray(array) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx}/admin/membertype/addMemberCourse",
                data: {"memberTypeId": '${memberTypeId}', "ids": array.toString()},
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
    <div class="numb-box">
        <fieldset class="layui-elem-field">
            <legend>
                <span>会员管理</span>
                &gt;
                <span>会员课程列表</span>
            </legend>
            <div class="layui-field-box">
                <form class="layui-form" action="${ctx}/admin/membertype/memberCourse/${memberTypeId}" method="post"
                      id="searchForm">
                    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
                    <input type="hidden" name="queryCourse.courseId" value="${queryCourse.courseId}"/>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">专业</label>
                            <div class="layui-input-inline">
                                <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
                                <input id="subjectNameBtn" type="text" class="layui-input" value="" onclick="showSubjectMenu()"/>
                                <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;z-index:100;">
                                    <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
                                </div>
                            </div>
                            <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css"/>
                            <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css"/>
                            <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
                            <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
                            <script type="text/javascript" src="/static/admin/subject/subject_util.js"></script>
                            <script>
                                //全部专业的json数据
                                var subject_treedata =${subjectList};
                            </script>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">课程名称</label>
                            <div class="layui-input-inline">
                                <input class="layui-input" type="text" name="queryCourse.courseName"
                                       value="${queryCourse.courseName}" id="name"/>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="$('#searchForm').submit();">查找</button>
                            <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="showNewwin();">添加课程</button>
                            <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="history.go(-1)">返回</button>
                        </div>
                    </div>
                </form>
                <table class="layui-table">
                    <thead>
                    <tr>
                        <td align="center">ID</td>
                        <td align="center" width="150px">课程名称</td>
                        <td align="center">价格</td>
                        <td align="center">操作</td>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${courseDtoList}" var="course" varStatus="index">
                        <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                            <td align="center">${course.courseId}</td>
                            <td align="center">${course.courseName}</td>
                            <td align="center">${course.currentPrice}</td>
                            <td align="center">
                                <button onclick="delCourse(${course.courseId})" class="layui-btn layui-btn-small" type="button">删除
                                </button>
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