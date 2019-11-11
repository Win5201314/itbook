<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>推荐课程管理列表</title>
    <script type="text/javascript" src="${ctx}/static/admin/website/detail.js"></script>
    <script>
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function(data){
            });
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
       推荐课程管理列表
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/detail/list" method="post" id="searchForm" class="layui-form" >
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
            <%--<input placeholder="课程名" type="text" name="dto.courseName" value="${dto.courseName}" />--%>
            <div class="layui-form-item">
                <label class="layui-form-label">请选择类型</label>
                <div class="layui-input-inline">
                    <select name="dto.id" lay-filter="select">
                        <option value="0">请选择类型</option>
                        <c:forEach items="${websiteCourseList}" var="list">
                            <c:choose>
                                <c:when test="${dto.id==list.id}">
                                    <option selected value="${list.id}">${list.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${list.id}">${list.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-inline">
                    <a title="查找" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查找
                    </a>
                    <a title="添加推荐课程" onclick="selectCousre()" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">添加推荐课程
                    </a>
                </div>
            </div>
        </form>
        <table cellspacing="0" cellpadding="0" border="0" class="layui-table" width="100%">
            <thead>
            <tr>
                <%--<td align="center">ID</td>--%>
                <td align="center">课程名称</td>
                <td align="center">类型</td>
                <td align="center">推荐分类</td>
                <td align="center">课程状态</td>
                <td align="center" width="70">排序值</td>
                <td align="center">操作</td>
            </tr>
            </thead>

            <tbody>
            <c:choose>
                <c:when test="${dtoList!=null && dtoList.size()>0}">
                    <c:forEach items="${dtoList}" var="dto" varStatus="index">
                        <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                                <%--<td align="center">${dto.id}</td>--%>
                            <td align="center">${dto.courseName}</td>
                            <td align="center">
                                <c:if test="${dto.sellType=='COURSE'}">课程</c:if>
                                <c:if test="${dto.sellType=='PACKAGE'}">套餐</c:if>
                                <c:if test="${dto.sellType=='LIVE'}">直播</c:if>
                            </td>
                            <td align="center">${dto.recommendName}</td>
                            <td align="center">
                                <c:if test="${dto.isavaliable==1}">上架</c:if>
                                <c:if test="${dto.isavaliable==2}">下架</c:if>
                            </td>
                            <td align="center">${dto.orderNum}</td>

                            <td align="center">
                                    <button onclick="updateSort(${dto.id},this)" class="layui-btn layui-btn-small" type="button">修改排序</button>
                                <button onclick="deleteDetail(${dto.id})" class="layui-btn layui-btn-small" type="button">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr class="odd">
                        <td align="center" colspan="6">
                            <div class="tips">
                                <span>还没有相关推荐课程！</span>
                            </div>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</fieldset>
</body>
</html>