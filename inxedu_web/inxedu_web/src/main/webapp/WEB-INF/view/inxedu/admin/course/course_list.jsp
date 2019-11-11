<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><c:set var="courseSellType" value="课程"></c:set>
        <c:if test="${queryCourse.sellType=='LIVE'}">
            <c:set var="courseSellType" value="直播"></c:set>
        </c:if>
        <c:if test="${queryCourse.sellType=='PACKAGE'}">
            <c:set var="courseSellType" value="套餐"></c:set>
        </c:if>${courseSellType}列表</title>

    <script type="text/javascript">

        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;

            form.on('select(filter)', function (data) {
            });
        });
        var subjectList = eval('(' + '${subjectList}' + ')');
        $(function () {
            $("#sellType").val("${queryCourse.sellType}");

        });

        /**
         * 删除课程
         */
        function avaliable(courseId, type, em) {
            if (!confirm('确实要删除吗?')) {
                return;
            }
            $.ajax({
                url: baselocation + '/admin/cou/avaliable/' + courseId + '/' + type,
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
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        课程列表
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/cou/list" method="post" id="searchForm" class="layui-form">
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">${courseSellType}标题</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="queryCourse.courseName" value="${queryCourse.courseName}" placeholder="${courseSellType}标题"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="queryCourse.sellType" id="sellType" lay-filter="select">
                            <option value="">请选择类型</option>
                            <option value="">请选择</option>
                            <option value="COURSE">课程</option>
                            <c:if test="${serviceSwitch.live=='ON'}">
                                <option value="LIVE">直播</option>
                            </c:if>
                            <c:if test="${serviceSwitch.PackageSwitch=='ON'}">
                                <option value="PACKAGE">套餐</option>
                            </c:if>
                        </select>
                        <script>
                            $("#sellType").val("${queryCourse.sellType}");
                        </script>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="queryCourse.isavaliable" lay-filter="select">
                            <option value="0">请选择</option>
                            <option
                                    <c:if test="${queryCourse.isavaliable==1}">selected</c:if> value="1">上架
                            </option>
                            <option
                                    <c:if test="${queryCourse.isavaliable==2 }">selected</c:if> value="2">下架
                            </option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">专业</label>
                    <div class="layui-input-inline">
                        <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
                        <input id="subjectNameBtn" type="text" class="layui-input" value="" onclick="showSubjectMenu()"/>
                        <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;z-index:100;">
                            <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
                        </div>
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
                <div class="layui-inline">
                    <%--<label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" name="queryCourse.beginCreateTime" id="beginCreateTime" placeholder="开始创建时间" value="<fmt:formatDate value="${queryCourse.beginCreateTime}" pattern="yyyy-MM-dd "/>" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" value="<fmt:formatDate value="${queryCourse.endCreateTime}" pattern="yyyy-MM-dd "/>" name="queryCourse.endCreateTime" id="endCreateTime" placeholder="结束创建时间" autocomplete="off" class="layui-input">
                    </div>--%>
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="queryCourse.beginCreateTime" placeholder="开始时间" value="<fmt:formatDate value="${queryCourse.beginCreateTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="queryCourse.endCreateTime" placeholder="结束时间" value="<fmt:formatDate value="${queryCourse.endCreateTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="$('#searchForm').submit();">查找</button>
                    <a title="添加课程" class="layui-btn layui-btn-small layui-btn-danger" href="${ctx}/admin/cou/toAddCourse">
                        <c:if test="${queryCourse.sellType=='PACKAGE'}">添加套餐课程</c:if>
                        <c:if test="${queryCourse.sellType!='PACKAGE'}">添加课程</c:if>
                    </a>
                    <a title="批量赠送" class="layui-btn layui-btn-small layui-btn-danger" href="${ctx}/admin/cou/toGiveCourses">批量赠送</a>
                </div>
            </div>
            <%--创建时间:
            <input placeholder="开始创建时间" name="queryCourse.beginCreateTime"
                   value="<fmt:formatDate value="${queryCourse.beginCreateTime}" pattern="yyyy-MM-dd "/>" id="beginCreateTime" type="text"
                   readonly="readonly" style="width: 128px;"/>-
            <input placeholder="结束创建时间" id="endCreateTime" name="queryCourse.endCreateTime"
                   value="<fmt:formatDate value="${queryCourse.endCreateTime}" pattern="yyyy-MM-dd "/>" type="text" readonly="readonly" style="width: 128px;"/>--%>
        </form>
        <table class="layui-table" lay-even="" lay-skin="row">
            <thead>
            <tr>
                <th align="center" style="display: none">ID</th>
                <th align="center">${courseSellType}名</th>
                <th align="center">状态</th>
                <th align="center">专业</th>
                <th align="center">现价</th>
                <th align="center">销售量</th>
                <th align="center">浏览量</th>
                <th align="center">创建时间</th>
                <th align="center">有效结束时间</th>
                <th align="center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${courseList}" var="course" varStatus="index">
                <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                    <td align="center" style="display: none">${course.courseId}</td>
                    <td align="center">${course.courseName}</td>
                    <td align="center">
                        <c:if test="${course.isavaliable==1}">上架</c:if>
                        <c:if test="${course.isavaliable==2}">下架</c:if>
                    </td>
                    <td align="center">${course.subjectName}</td>
                    <td align="center">${course.currentPrice}</td>
                    <td align="center">${course.pageBuycount}</td>
                    <td align="center">${course.pageViewcount}</td>
                    <td align="center">
                        <fmt:formatDate value="${course.addTime}" pattern="yyyy/MM/dd HH:mm"/>
                    </td>
                    <td align="center">
                        <c:if test="${not empty course.endTime}">
                            <fmt:formatDate value="${course.endTime}" pattern="yyyy/MM/dd HH:mm"/>
                        </c:if>
                        <c:if test="${empty course.endTime}">
                            购买后${course.loseTime}天
                        </c:if>
                    </td>
                    <td align="center">
                        <button onclick="avaliable(${course.courseId},3,this)" class="layui-btn layui-btn-small" type="button">删除</button>
                        <button onclick="window.location.href='${ctx}/admin/cou/initUpdate/${course.courseId}'" class="layui-btn layui-btn-small" type="button">修改</button>
                        <button onclick="window.location.href='${ctx}/admin/comment/query/${course.courseId}/2'" class="layui-btn layui-btn-small" type="button">课程评论</button>

                        <c:if test="${course.sellType=='PACKAGE'}">
                            <button onclick="window.location.href='${ctx}/admin/cou/packages/${course.courseId}'" class="layui-btn layui-btn-small" type="button">课程管理</button>
                        </c:if>
                        <c:if test="${course.sellType=='COURSE' or course.sellType=='LIVE'}">
                            <button onclick="window.location.href='${ctx}/admin/kpoint/list/${course.courseId}'" class="layui-btn layui-btn-small" type="button">章节管理</button>
                        </c:if>
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