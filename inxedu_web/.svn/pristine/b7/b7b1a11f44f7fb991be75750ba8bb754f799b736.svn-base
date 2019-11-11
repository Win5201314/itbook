<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>课程列表</title>
    <script type="text/javascript">
        var form;
        layui.use(['form', 'laydate'], function () {
            form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });

            form.on('checkbox(selectAll)', function (data) {
                $("input[name='courseId']").attr('checked', data.elem.checked);//是否被选中，true或者false
                form.render('checkbox'); //刷新选择框渲染
            });
            //各种基于事件的操作，下面会有进一步介绍
        });

        $(function () {
            $("input[name='courseId']").click(function () {
                var allarr = $("input[name='courseId']");
                var selectarr = $("input[name='courseId']:checked");
                if (selectarr != null && allarr != null && allarr.length == selectarr.length) {
                    $("#selectAll").attr('checked', true);
                } else {
                    $("#selectAll").attr('checked', false);
                }
            });
        });
        /**
         * 关闭窗口
         */
        function closeWin() {
            window.close();
        }
        //确认选择
        function confirmSelect() {
            if (initArray()) {
                //调用父页面的方法
                window.close();
            }
        }
        function initArray() {
            var qstChecked = $("input[name='courseId']:checked");
            if (qstChecked.length == 0) {
                layer.msg("请选择课程", {icon: 5, shift: 6});
                return;
            }
            qstChecked.each(function () {
                toParentsValue($(this).val());
            });
            opener.addnewArray(myArrayMoveStock);
            closeWin();
        }
        //存放数据的数组
        var myArrayMoveStock = [];
        // 把选中试题一条记录放到数组中
        function toParentsValue(obj) {
            myArrayMoveStock.push(obj);
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        套餐课程列表
    </legend>
    <div class="layui-field-box">
            <form action="${ctx}/admin/cou/showCourseList" method="post" id="searchForm" class="layui-form">
                <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
                <input type="hidden" name="type" value="${type}"/>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程标题</label>
                        <div class="layui-input-inline">
                            <input type="text" name="queryCourse.courseName" value="${queryCourse.courseName}" placeholder="课程标题" class="layui-input"/>
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
                        <a title="查找课程" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查找课程</a>
                    </div>
                </div>
                <table class="layui-table" lay-even="" lay-skin="row">
                    <thead>
                    <tr>
                        <td align="center">
                            <input type="checkbox" id="selectAll" lay-filter="selectAll" title="全选"/>
                        </td>
                        <td align="center">课程名</td>
                        <td align="center">专业</td>
                        <td align="center">创建时间</td>
                        <td align="center">原价</td>
                        <td align="center">优惠价</td>
                        <td align="center">课时</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courseList}" var="course" varStatus="index">
                        <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                            <td align="center">
                                <input type="checkbox" value="${course.courseId}" name="courseId"/>
                            </td>
                            <td align="center">${course.courseName}</td>
                            <td align="center">${course.subjectName}</td>
                            <td align="center">
                                <fmt:formatDate value="${course.addTime}" pattern="dd/MM/yyyy HH:mm:ss"/>
                            </td>
                            <td align="center">${course.sourcePrice}</td>
                            <td align="center">${course.currentPrice}</td>
                            <td align="center">${course.lessionNum}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
            <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
        </div>
        <div style="text-align: center;">
            <a title="确认" onclick="confirmSelect()" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
                确认
            </a>
            <a title="关闭" onclick="closeWin()" class="layui-btn layui-btn-small layui-btn-primary" href="javascript:void(0)">
                关闭
            </a>
        </div>
</fieldset>
</body>
</html>