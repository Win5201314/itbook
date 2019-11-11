<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录日志</title>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		用户登录日志
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/user/lookuserlog/${userId}" method="post" id="searchForm" class="layui-form">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
		</form>
		<a href="javascript:void(0)" class="layui-btn layui-btn-small layui-btn-danger" onclick="history.go(-1)" title="返回">
			返 回
		</a>
		<table  class="layui-table" lay-even="" lay-skin="row">
			<thead>
				<tr>
					<td align="center">登录时间</td>
					<td align="center">登录IP</td>
					<td align="center">操作系统</td>
					<td align="center">浏览器</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${logList}" var="log" varStatus="index">
					<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
						<td align="center">
							<fmt:formatDate value="${log.loginTime}" pattern="dd/MM/yyyy HH:mm:ss" />
						</td>
						<td align="center">${log.ip}</td>
						<td align="center">${log.osName}</td>
						<td align="center">${log.userAgent}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
</fieldset>
</body>
</html>