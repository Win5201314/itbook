<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作日志记录</title>
<script>
	var form;
	layui.use(['form', 'laydate'], function(){
		form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function(data){
		});
		//各种基于事件的操作，下面会有进一步介绍
	});
	function query(){
		$("#searchForm").submit();
	}
</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		操作日志
	</legend>
	<div class="layui-field-box">
			<form action="${ctx}/admin/syslog/page" method="post" id="searchForm" class="layui-form">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">操作人</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="loginName" name="sysLog.loginName" value="${sysLog.loginName}" placeholder="操作人"/>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">类型</label>
						<div class="layui-input-inline">
							<select name="sysLog.type" id="type" lay-filter="select">
								<option value="">-- 全部 --</option>
								<option value="">-- 全部 --</option>
								<option value="add">添加</option>
								<option value="update">更新</option>
								<option value="del">删除</option>
							</select>
							<script>
								$("#type").val("${sysLog.type}");
							</script>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">开始时间</label>
						<div class="layui-input-inline">
							<input id="beginTime" name="sysLog.beginTime" value="<fmt:formatDate value="${sysLog.beginTime}" pattern="yyyy-MM-dd hh:mm:ss" />" placeholder="开始时间" autocomplete="off" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">结束时间</label>
						<div class="layui-input-inline">
							<input id="endTime" name="sysLog.endTime" value="<fmt:formatDate value="${sysLog.endTime}" pattern="yyyy-MM-dd hh:mm:ss" />" placeholder="结束时间" autocomplete="off" class="layui-input" type="text" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layui-btn-small" lay-submit="" onclick="query();" type="button">查询</button>
						<%--<input class="layui-btn layui-btn-small" type="button" onclick="$('#searchForm input:text,#searchForm select').val('');" value="清空">--%>
					</div>
				</div>
			</form>
			<table class="layui-table" lay-even="" lay-skin="row">
				<thead>
				<tr>
					<td align="center">操作人</td>
					<td align="center">类型</td>
					<td align="center">操作描述</td>
					<td align="center">操作时间</td>
					<td align="center">操作</td>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${sysLogList}" var="syslog" varStatus="index">
					<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>

						<td align="center">${syslog.loginName}</td>

						<td align="center">
							<c:if test="${syslog.type=='add'}">添加</c:if>
							<c:if test="${syslog.type=='update'}">更新</c:if>
							<c:if test="${syslog.type=='del'}">删除</c:if>
							<c:if test="${syslog.type=='moveNavDown'||syslog.type=='moveNavUp'}">排序</c:if>
						</td>
						<td align="center">${syslog.operation}</td>
						<td align="center">
							<fmt:formatDate value="${syslog.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td align="center">
							<button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='/admin/syslog/query?id=${syslog.id}'">查看</button>
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