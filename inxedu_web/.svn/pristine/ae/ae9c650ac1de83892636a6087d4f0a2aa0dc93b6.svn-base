<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据清理列表</title>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	layui.use(['form', 'laydate'], function () {
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function (data) {
		});
		form.on('checkbox(selectAll)', function(data){
			/**
			 * 全选或反选
			 */
			$("input[name='dataCleanId']").prop('checked',data.elem.checked);
			form.render('checkbox'); //刷新选择框渲染
		});
		//各种基于事件的操作，下面会有进一步介绍
	});

	/**
	 * 执行
	 */
	function thisRun(id){
		if(confirm("确定要执行此语句吗？数据清除后，不可恢复！！！")){
			$.ajax({
				url:"/admin/dataclean/dosql",
				data:{
					"id":id
				},
				dataType:"json",
				type:"post",
				async:true,
				success:function(result){
					if(result.success==true){
                        layer.msg("清除数据成功", {icon: 1, shift: 6});
					}else{
                        layer.msg(result.message, {icon: 5, shift: 6});
					}
				}
			})
		}
	}
	/**
	 * 删除
	 */
	function thisDelete(id){
		if(confirm("确定要删除吗？")){
			$.ajax({
				url:"/admin/dataclean/del",
				data:{
					"id":id
				},
				dataType:"json",
				type:"post",
				async:true,
				success:function(result){
					if(result.success==true){
                        layer.msg("删除成功", {icon: 1, shift: 6});
						window.location.reload();
					}else{
                        layer.msg(result.message, {icon: 5, shift: 6});
					}
				}
			})
		}
	}

	/**
	 * 批量执行
	 */
	function multiRun(){
		var arr = $("input[name='dataCleanId']:checked");
		if (arr == null || arr.length == 0) {
            layer.msg("请选择要执行的语句", {icon: 5, shift: 6});
			return;
		}
		var dataCleanIdStr = "";
		$('input[name="dataCleanId"]:checked').each(function() {
			dataCleanIdStr = dataCleanIdStr + $(this).val() + ",";
		});
		dataCleanIdStr = dataCleanIdStr.substring(0, dataCleanIdStr.length - 1);
		if(confirm("确定要批量执行多条语句吗？数据清除后，不可恢复！！！")){
			$.ajax({
				url:"/admin/dataclean/multiDosql",
				data:{
					"ids":dataCleanIdStr
				},
				dataType:"json",
				type:"post",
				async:true,
				success:function(result){
					if(result.success==true){
                        layer.msg("清除数据成功", {icon: 1, shift: 6});
					}else{
                        layer.msg(result.message, {icon: 5, shift: 6});
					}
				}
			})
		}
	}
</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>数据清理</span>
	</legend>
	<!-- /tab4 begin -->
	<div class="layui-field-box">
		<div class="">
			<form action="${ctx}/admin/dataclean/list" method="post" id="searchForm" class="layui-form">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">名称</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="名称" type="text" name="name" value="${dataClean.name}"/>
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="$('#searchForm').submit();" type="button">查找</button>
						<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="window.location.href='${ctx}/admin/dataclean/toadd'" type="button">添加</button>
						<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="multiRun()" type="button">批量清理</button>
					</div>
				</div>
			</form>
			<form action="${ctx}/admin/article/delete" id="deleteForm" method="post" class="layui-form">
				<table class="layui-table" lay-even="" lay-skin="row">
					<thead>
					<tr>
						<td align="center">
							<input name="allck" type="checkbox" onclick="selectAll(this)" lay-filter="selectAll"/>
							ID
						</td>
						<td align="center">名称</td>
						<td align="center">描述</td>
						<td align="center" width="300">操作</td>
					</tr>
					</thead>

					<tbody>
					<c:forEach items="${dataCleanList}" var="dc" varStatus="index">
						<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
							<td align="center">
								<input type="checkbox" name="dataCleanId" value="${dc.id}" />${dc.id}
							</td>
							<td align="center">${dc.name}</td>
							<td align="center">${dc.desc}</td>
							<td align="center">
								<button onclick="thisDelete(${dc.id})" class="layui-btn layui-btn-small" type="button">删除</button>
								<button onclick="window.location.href='${ctx}/admin/dataclean/toupdate?id=${dc.id}'" class="layui-btn layui-btn-small" type="button">修改</button>
								<button onclick="thisRun(${dc.id})" class="layui-btn layui-btn-small" type="button">清理</button>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</form>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</div>
	</div>
</fieldset>
</body>
</html>