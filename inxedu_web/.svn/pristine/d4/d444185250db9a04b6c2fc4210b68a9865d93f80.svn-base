<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>赠送课程列表</title>
    <script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>

    <script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function(data){
            });
        });

        var subjectList = eval('(' + '${subjectList}' + ')');
        $(function () {
            var param = {
                data: subjectList,	//处理的数据（必选）数据格式：[{object Object},{object Object}]
                showId: 'levelId',//显示的数据标签ID（必选）
                idKey: 'subjectId',//数据的ID（必选）
                pidKey: 'parentId',//数据的父ID（必选）
                nameKey: 'subjectName',//数据显示的名（必选）
                returnElement: 'subjectId',//返回选中的值（必选 ）
                //-----------------------------------------------------
                initVal:${queryCourse.subjectId},
                defName: '请选择',//默认显示的选项名（可选，如果不设置默认显示“请选择”）
                defValue: '0'//默认的选项值（可选，如果不设置默认是“0”）
            };
            //ML._init(param);
            /* 时间控件 */
            $("#beginCreateTime,#endCreateTime").datetimepicker({
                regional: "zh-CN",
                changeMonth: true,
                dateFormat: "yy-mm-dd",
                timeFormat: "HH:mm:ss"
            });
        });

        //赠送课程
        function toFreeCourse(courseId) {
            var userId = $("#userId").val();
            if (confirm("确定赠送课程吗?")) {
                $.ajax({
                    url: "${ctx}/admin/user/freeCourse",
                    data: {
                        "userId": "${userId}",
                        "courseId": courseId
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.success == true) {
                            layer.msg("赠送成功！", {icon: 1, shift: 6});
                        } else {
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    },
                    error: function (error) {
                        layer.msg("服务器繁忙，请稍后再试", {icon: 5, shift: 6});
                    }
                })
            }
        }
    </script>
</head>
<body>
    <div class="numb-box">
        <fieldset class="layui-elem-field">
            <legend>
                <span>用户管理</span>
                >
                <span>赠送课程</span>
            </legend>
            <div class="layui-field-box">
                <form action="${ctx}/admin/user/courseList/${userId}" method="post" id="searchForm" class="layui-form">
                    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">${courseSellType}标题</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" name="queryCourse.courseName" value="${queryCourse.courseName}" placeholder="课程标题"/>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">类型</label>
                            <div class="layui-input-inline">
                                <select name="queryCourse.sellType" lay-filter="select">
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
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-inline">
                                <select name="queryCourse.isavaliable" lay-filter="select">
                                    <option value="0">状态</option>
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
                            <label class="layui-form-label">专业</label>
                            <div class="layui-input-inline">
                                <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}" />
                                <input id="subjectNameBtn" type="text" class="layui-input" value=""  onclick="showSubjectMenu()"/>
                                <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;z-index:100;">
                                    <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
                                </div>
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
                        <div class="layui-inline">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-inline">
                                <input class="layui-input" name="queryCourse.beginCreateTime" placeholder="开始时间" value="<fmt:formatDate value="${queryCourse.beginCreateTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                            </div>
                            <div class="layui-input-inline">
                                <input class="layui-input" name="queryCourse.endCreateTime" placeholder="结束时间" value="<fmt:formatDate value="${queryCourse.endCreateTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <a title="查找课程" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger"
                               href="javascript:void(0)">查找课程</a>
                        </div>

                    </div>

                </form>
                <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                    <thead>
                    <tr>
                        <td align="center" width="150px">课程名</td>
                        <td align="center">状态</td>
                        <td align="center">类型</td>
                        <td align="center">专业</td>
                        <td align="center">原价</td>
                        <td align="center">优惠价</td>
                        <td align="center">课时</td>
                        <%--<td align="center">销售量</td>
                        <td align="center">浏览量</td>--%>
                        <td align="center">创建时间</td>
                        <td align="center">有效结束时间</td>
                        <td align="center">操作</td>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${courseList}" var="course" varStatus="index">
                        <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                            <td align="center">${course.courseName}</td>
                            <td align="center">
                                <c:if test="${course.isavaliable==1}">上架</c:if>
                                <c:if test="${course.isavaliable==2}">下架</c:if>
                            </td>
                            <td align="center">
                                <c:if test="${course.sellType=='COURSE'}">课程</c:if>
                                <c:if test="${course.sellType=='PACKAGE'}">套餐</c:if>
                                <c:if test="${course.sellType=='LIVE'}">直播</c:if>
                            </td>
                            <td align="center">${course.subjectName}</td>
                            <td align="center">${course.sourcePrice}</td>
                            <td align="center">${course.currentPrice}</td>
                            <td align="center">${course.lessionNum}</td>
                                <%--<td align="center">${course.pageBuycount}</td>
                                <td align="center">${course.pageViewcount}</td>--%>
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
                                <button onclick="toFreeCourse(${course.courseId})" class="layui-btn layui-btn-small"
                                        type="button">
                                    赠送
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