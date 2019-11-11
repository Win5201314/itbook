<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>讲师列表</title>
<script type="text/javascript" src="${ctx}/static/admin/teacher/select_teacher_list.js"></script>
	<script type="text/javascript">
		var form;
		layui.use(['form', 'laydate'], function () {
			form = layui.form();
			var laydate = layui.laydate;
			form.on('select(filter)', function (data) {
			});

			form.on('checkbox(selectAll)', function (data) {
				$("input[name='teacherId']").each(function(){
					$(this).attr('checked',data.elem.checked);
				});
				form.render('checkbox'); //刷新选择框渲染
			});
			//各种基于事件的操作，下面会有进一步介绍
		});
	</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>课程管理</span>
		>
		<span>添加课程</span>
		>
		<span>选择老师</span>
	</legend>
	<div class="layui-field-box">
					<form action="${ctx}/admin/teacher/selectlist/${type}" method="post" id="searchForm" class="layui-form">
						<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
						<div class="layui-form-item">
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input placeholder="讲师名" type="text" name="queryTeacher.name" value="${queryTeacher.name}" class="layui-input"/>
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">状态</label>
								<div class="layui-input-inline">
									<select name="queryTeacher.isStar" lay-filter="select">
										<option <c:if test="${queryTeacher.isStar==0}"> selected="selected"</c:if> value="0">请选择</option>
										<option <c:if test="${queryTeacher.isStar==1}"> selected="selected"</c:if> value="1">高级讲师</option>
										<option <c:if test="${queryTeacher.isStar==2}"> selected="selected"</c:if> value="2">首席讲师</option>
									</select>
								</div>
							</div>
							<div class="layui-inline">
								<input onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger"  type="button" value="查找" />
								<input onclick="$('#searchForm input:text').val('');$('#searchForm select').val(0);" class="layui-btn layui-btn-small layui-btn-danger"   type="button" value="清空" />
							</div>
						</div>

						<table class="layui-table" lay-even="" lay-skin="row">
							<thead>
							<tr>
								<td align="center">
									<c:if test="${type=='checkbox'}">
										<input type="checkbox" id="selectAll" lay-filter="selectAll"/>
									</c:if>
								</td>
								<td align="center">名称</td>
								<td align="center">头衔</td>
								<td align="center">资历</td>
								<td align="center">简介</td>
							</tr>
							</thead>

							<tbody>
							<c:forEach items="${teacherList}" var="tc" varStatus="index">
								<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
									<td align="center">
										<c:if test="${type=='checkbox'}">
											<input type="checkbox" name="teacherId" title='${tc.name}' value="${tc.id}" />
										</c:if>
										<c:if test="${type=='radio'}">
											<input type="radio" name="teacherId" title='${tc.name}' value="${tc.id}" />
										</c:if>
									</td>
									<td align="center">${tc.name}</td>
									<td align="center">
										<c:if test="${tc.isStar==1 }">高级讲师 </c:if>
										<c:if test="${tc.isStar==2 }">首席讲师 </c:if>
									</td>
									<td align="center">${tc.education }</td>
									<td align="center">${fn:substring(tc.career,0,30)}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					</form>
				</div>
				<div style="text-align: center;">
					<a title="确认" onclick="confirmSelect()" class="layui-btn layui-btn-small" href="javascript:void(0)">确认
					</a>
					<a title="关闭" onclick="closeWin()" class="layui-btn layui-btn-small" href="javascript:void(0)">关闭
					</a>
				</div>
			</div>
	</fieldset>
</body>
</html>