<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>推荐课程列表</title>
    <script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
            form.on('checkbox(selectAll)', function (data) {
                $("input[name='courseId']").attr('checked', data.elem.checked);//是否被选中，true或者false
                form.render('checkbox'); //刷新选择框渲染
            });
            //各种基于事件的操作，下面会有进一步介绍
        });
        var subjectList = eval('(' + '${subjectList}' + ')');
        $(function () {
            $("#selectAll").click(function () {
                $("input[name='courseId']").attr('checked', $(this).attr('checked'));
            });
            $("input[name='courseId']").click(function () {
                var allarr = $("input[name='courseId']");
                var selectarr = $("input[name='courseId']:checked");
                if (selectarr != null && allarr != null && allarr.length == selectarr.length) {
                    $("#selectAll").attr('checked', true);
                } else {
                    $("#selectAll").attr('checked', false);
                }
            });

            var param = {
                data: subjectList,	//处理的数据（必选）数据格式：[{object Object},{object Object}]
                showId: 'levelId',//显示的数据标签ID（必选）
                idKey: 'subjectId',//数据的ID（必选）
                pidKey: 'parentId',//数据的父ID（必选）
                nameKey: 'subjectName',//数据显示的名（必选）
                returnElement: 'subjectId',//返回选中的值（必选 ）
                //-----------------------------------------------------
                initVal:${queryCourse.subjectId},
                defName: '请选择专业',//默认显示的选项名（可选，如果不设置默认显示“请选择”）
                defValue: '0'//默认的选项值（可选，如果不设置默认是“0”）
            };
            //ML._init(param);
        });

        /**
         * 关闭窗口
         */
        function closeWin() {
            window.close();
        }

        //确认选择
        function confirmSelect() {
            var courseArr = $("input[name='courseId']:checked");
            if (courseArr == null || courseArr.length == 0) {
                layer.msg("请选择课程！", {icon: 5, shift: 6});
                return false;
            }
            var recommendId = $("select[name='recommend']").val();
            if (recommendId <= 0) {
                layer.msg("请选择推荐类型！", {icon: 5, shift: 6});
                return false;
            }
            var courseIds = ',';
            for (var i = 0; i < courseArr.length; i++) {
                courseIds += courseArr[i].value + ",";
            }
            $.ajax({
                url: baselocation + '/admin/detail/addrecommendCourse',
                type: 'post',
                dataType: 'json',
                data: {'courseIds': courseIds, "recommendId": recommendId},
                success: function (result) {
                    if (result.success == false) {
                        layer.msg(result.message, {icon: 5, shift: 6});
                    } else {
                        window.opener.refurbishPage(result.entity);
                        closeWin();
                    }
                },
                error: function (eror) {
                    layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
                }
            });
        }
        $(function () {
            $("select").change(function () {
                changeCourse()
            });
            $("#courseType").unbind("change")
        });
        function changeCourse() {
            $("#searchForm").submit();
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>推荐课程管理</span>
        >
        <span>创建课程</span>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/cou/showrecommendList" method="post" id="searchForm" class="layui-form">
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
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
                        <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}" />
                        <input id="subjectNameBtn" type="text" class="layui-input" value=""  onclick="showSubjectMenu()"/>
                        <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;z-index:100;">
                            <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
                        </div>
                    </div>
                    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
                    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
                    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
                    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
                    <script type="text/javascript" src="/static/admin/subject/subject_util.js"></script>
                    <script>
                        //全部专业的json数据
                        var subject_treedata=${subjectList};
                    </script>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="queryCourse.isavaliable" onchange="changeCourse()" class="xz-new-sele" lay-filter="select">
                            <option value="0">请选择状态</option>
                            <option
                                    <c:if test="${queryCourse.isavaliable==1 }">selected</c:if> value="1">上架
                            </option>
                            <option
                                    <c:if test="${queryCourse.isavaliable==2 }">selected</c:if> value="2">下架
                            </option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="queryCourse.sellType" class="xz-new-sele" lay-filter="select">
                            <option value="">请选择类型</option>
                            <option
                                    <c:if test="${queryCourse.sellType=='COURSE' }">selected</c:if> value="COURSE">课程
                            </option>
                            <option
                                    <c:if test="${queryCourse.sellType=='LIVE' }">selected</c:if> value="LIVE">直播
                            </option>
                            <option
                                    <c:if test="${queryCourse.sellType=='PACKAGE' }">selected</c:if> value="PACKAGE">套餐
                            </option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">课程推荐类型</label>
                    <div class="layui-input-inline">
                        <select name="recommend" id="courseType" class="xz-new-sele" lay-filter="select">
                            <option value="0">请选择类型</option>
                            <c:forEach items="${webstieList}" var="list">
                                <option value="${list.id }">${list.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="layui-form-mid layui-word-aux">提示：请从列表勾选要创建的课程</div>
                </div>
                <div class="layui-input-block">
                    <a title="确认创建" onclick="confirmSelect()" class="layui-btn layui-btn-small"
                       href="javascript:void(0)">
                        确认创建
                    </a>
                    <a title="搜索" onclick="$('#searchForm').submit()" class="layui-btn layui-btn-small"
                       href="javascript:void(0)">
                        搜索
                    </a>
                    <a title="关闭" onclick="closeWin()" class="layui-btn layui-btn-small"
                       href="javascript:void(0)">关闭</a>
                </div>
            </div>

        <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
            <thead>
            <tr>
                <td align="center">

                    <input type="checkbox" lay-filter="selectAll" id="selectAll"/>
                </td>
                <td align="center">课程名</td>
                <td align="center">专业</td>
                <td align="center">状态</td>
                <td align="center">类型</td>
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
                        <c:if test="${course.isavaliable==1}">上架</c:if>
                        <c:if test="${course.isavaliable==2}">下架</c:if>
                    </td>
                    <td align="center">
                        <c:if test="${course.sellType=='COURSE'}">课程</c:if>
                        <c:if test="${course.sellType=='PACKAGE'}">套餐</c:if>
                        <c:if test="${course.sellType=='LIVE'}">直播</c:if>
                    </td>
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
</fieldset>

</body>
</html>