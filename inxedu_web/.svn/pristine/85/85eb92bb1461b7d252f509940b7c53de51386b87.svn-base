<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>课程列表</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
            form.on('checkbox(all)', function(data){
                if ($("#selectAll").attr("checked")){
                    $("input[name='ids']:checkbox").prop('checked', true);
                }else {
                    $("input[name='ids']:checkbox").prop('checked',false);
                }
                form.render();
            });
        });
        //存放数据的数组
        var myArrayMoveStock = [];
        //将小页面被选中的入库明细信息带到大页面
        function selectList() {
            var courseChecked = document.getElementsByName("ids");
            // 定义是否有产品被选中
            var notSelect = true;
            var overdue = false;
            var courseNames = "";
            // 把被选中的入库明细传入数组
            myArrayMoveStock = [];
            for (var i = 0; i < courseChecked.length; i++) {
                if (courseChecked[i].checked == true) {
                    var records = courseChecked[i].value;
                    var instockmsg = [];
                    instockmsg = records.split("#");//以#分开获得数组
                    var courseId = instockmsg[0];
                    var courseName = instockmsg[1];
                    $.ajax({
                        url: "${ctx}/admin/cou/courseOverdue",
                        data: {"courseId": courseId},
                        type: "post",
                        dataType: "json",
                        async: false,
                        success: function (result) {
                            if (result.message == 'overdue') {
                                courseChecked[i].checked = false;
                                overdue = true;
                                courseNames += courseName + ",";
                            }
                        }
                    });

                    if (courseChecked[i].checked == true) {
                        toParentsValue(courseId, courseName);
                        notSelect = false;
                    }

                }
            }

            if (overdue) {
                courseNames = courseNames.substring(0, courseNames.length - 1);
                layer.msg("您选择的课程'" + courseNames + "'已过期，请重新选择", {icon: 5, shift: 6});
                return;
            }
            //没有入库明细被选择
            if (notSelect) {
                layer.msg("请选择课程", {icon: 5, shift: 6});
                return;
            }

            //调用父页面的方法
            window.opener.getCourseList(myArrayMoveStock);
            window.close();
        }
        // 把选中产品的一条记录放到数组中
        function toParentsValue(courseId, courseName) {
            myArrayMoveStock.push([courseId, courseName]);
        }
    </script>
</head>
<body>
<form class="layui-form" action="${ctx}/admin/cou/couponCourseList" name="searchForm" id="searchForm" method="post">
    <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
    <table class="layui-table">
        <thead>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">课程id</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" onkeyup="value=value.replace(/[^\d]/g,'')"
                           name="queryCourse.courseId" value="${course.courseId}" id="courseId"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-inline">
                    <select name="queryCourse.isavaliable" id="isavaliable">
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
                <label class="layui-form-label">课程名称</label>
                <div class="layui-inline">
                <input type="text" class="layui-input" name="queryCourse.courseName" value="${course.courseName}"
                       id="courseName"/>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <select name="queryCourse.sellType" id="sellType">
                        <option value="">类型</option>
                        <option value="COURSE">课程</option>
                        <option value="LIVE">直播</option>
                        <option value="PACKAGE">套餐</option>
                    </select>
                    <script>
                        $("#sellType").val('${course.sellType}');
                    </script>
                </div>
            </div>
            <div class="layui-inline">
                <input type="button" name="" value="查询" class="layui-btn layui-btn-small layui-btn-danger"
                       onclick="goPage(1)"/>
            </div>
        </div>
        <tr>
            <th><span><input type="checkbox" lay-filter="all" id="selectAll" onclick="allCheck(this)" title="全选"/></span></th>
            <th><span>ID</span></th>
            <th><span>课程名称</span></th>
            <th><span>状态</span></th>
            <th><span>类型</span></th>
            <th><span>价格</span></th>
            <th><span>添加时间</span></th>
        </tr>
        </thead>
        <tbody id="tabS_02" align="center">

        <c:if test="${courseList.size()>0}">
            <c:forEach items="${courseList}" var="cou" varStatus="index">
                <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                    <td><input type="checkbox" name="ids" value="${cou.courseId}#${cou.courseName}"/></td>
                    <td>${cou.courseId}</td>
                    <td>${cou.courseName }</td>
                    <td>
                        <c:if test="${cou.isavaliable==1}">上架</c:if>
                        <c:if test="${cou.isavaliable==2}">下架</c:if>
                    </td>
                    <td>
                        <c:if test="${cou.sellType=='COURSE'}">课程</c:if>
                        <c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
                        <c:if test="${cou.sellType=='LIVE'}">直播</c:if>
                    </td>
                    <td>${cou.currentPrice}</td>
                    <td>
                        <fmt:formatDate type="both" value="${cou.addTime }"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${courseList.size()==0||courseList==null}">
            <tr>
                <td align="center" colspan="16">
                    <div class="tips">
                        <span>还没有课程数据！</span>
                    </div>
                </td>
            </tr>
        </c:if>
        <tr>
            <td align="center" colspan="7">
                <input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="selectList()"
                       value="确定">
                <input class="layui-btn layui-btn-small layui-btn-danger" type="button"
                       onclick="window.close();" value="取消">
            </td>
        </tr>
        </tbody>
    </table>
    <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
</form>
</body>
</html>
