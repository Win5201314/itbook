<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>讲师列表</title>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
layui.use(['form', 'laydate'], function(){
	var form = layui.form();
	var laydate = layui.laydate;
	form.on('select(filter)', function(data){
	});
	//各种基于事件的操作，下面会有进一步介绍
});
/**
 * 删除老师
 * @param tcId 老师ID
 */
function deleteTeacher(tcId){
	if(confirm('确认要删除该讲师？')){
		document.location='/admin/teacher/delete/'+tcId;
	}
}
$(function(){
	/**加载时间控件*/
	$("#beginCreateTime,#endCreateTime").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: ""
    });
});
</script>
</head>
<body>
	<fieldset class="layui-elem-field">
		<legend>
			讲师列表
		</legend>
		<div class="layui-field-box">
			<form action="${ctx}/admin/teacher/list" method="post" id="searchForm" class="layui-form">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">讲师名称</label>
						<div class="layui-input-inline">
							<input placeholder="讲师名" type="text" class="layui-input" name="queryTeacher.name" value="${queryTeacher.name}" />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">类别</label>
						<div class="layui-input-inline">
							<select name="queryTeacher.isStar" lay-filter="select">
								<option <c:if test="${queryTeacher.isStar==0}"> selected="selected"</c:if> value="0">请选择</option>
								<option <c:if test="${queryTeacher.isStar==1}"> selected="selected"</c:if> value="1">高级讲师</option>
								<option <c:if test="${queryTeacher.isStar==2}"> selected="selected"</c:if> value="2">首席讲师</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">创建时间</label>
						<div class="layui-input-inline">
							<input class="layui-input" name="queryTeacher.beginCreateTime" placeholder="开始时间" value="<fmt:formatDate value="${queryTeacher.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss "/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
						</div>
						<div class="layui-input-inline">
							<input class="layui-input" name="queryTeacher.endCreateTime" placeholder="结束时间" value="<fmt:formatDate value="${queryTeacher.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss "/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
						</div>
					</div>
					<div class="layui-inline">
						<a title="查找用户" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查找</a>
					</div>
				</div>

			</form>
			<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
				<thead>
				<tr>
					<%--<td align="center">ID</td>--%>
					<td width="10%" align="center">名称</td>
					<td align="center">头衔</td>
					<td align="center" width="25%">资历</td>
					<td align="center" width="30%">简介</td>
					<td align="center">添加时间</td>
					<td align="center">排序</td>
					<td align="center" width="200">操作</td>
				</tr>
				</thead>

				<tbody>
				<c:forEach items="${teacherList}" var="tc" varStatus="index">
					<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
							<%--<td align="center">${tc.id}</td>--%>
						<td align="center">${tc.name}</td>
						<td align="center">
							<c:if test="${tc.isStar==1 }">高级讲师 </c:if>
							<c:if test="${tc.isStar==2 }">首席讲师 </c:if>
						</td>
						<td align="center">${tc.education }</td>
						<td align="center">${fn:substring(tc.career,0,30)}</td>
						<td align="center">
							<fmt:formatDate type="both" value="${tc.createTime}" pattern="yyyy/MM/dd HH:mm" />
						</td>
						<td align="center">${tc.sort}</td>
						<td align="center">
							<button onclick="deleteTeacher(${tc.id})" class="layui-btn layui-btn-small" type="button">删除</button>
							<button onclick="window.location.href='${ctx}/admin/teacher/toUpdate/${tc.id}'" class="layui-btn layui-btn-small" type="button">修改</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</div>
	</fieldset>
</body>
</html>