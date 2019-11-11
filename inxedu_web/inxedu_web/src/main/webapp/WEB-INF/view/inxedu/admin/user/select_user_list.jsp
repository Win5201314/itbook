<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>选择学员列表</title>
<script type="text/javascript">
	var form;
	layui.use(['form', 'laydate'], function () {
		form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function (data) {
		});

		form.on('checkbox(selectAll)', function (data) {
			$("input[class='questionIds']").attr('checked', data.elem.checked);//是否被选中，true或者false
			form.render('checkbox'); //刷新选择框渲染
		});
		//各种基于事件的操作，下面会有进一步介绍
	});
function submitSearch(){
	$("#searchForm").prop("action","${ctx}/admin/user/select_userlist/${type}");
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
function clean(){
	$("#userId,#email,#mobile,#courseName").val("");
}

//关闭窗口
function quxiao() {
	window.close();
}
//存放数据的数组
var myArrayMoveStock = [];
//将小页面被选中的入库明细信息带到大页面
function selectQstList() {
	if (initArray()) {
		//调用父页面的方法
		window.close();
	}
}
function initArray() {
	var qstChecked = $(".questionIds:checked");
	if (qstChecked.length == 0) {
        layer.msg("请选择用户", {icon: 5, shift: 6});
		return;
	}
	qstChecked.each(function() {
		toParentsValue($(this).val());
	});
	opener.addnewUserId(myArrayMoveStock);
	quxiao();
}
// 把选中用户一条记录放到数组中
function toParentsValue(obj) {
	myArrayMoveStock.push(obj);
}

</script>
</head>
<body  >
<fieldset class="layui-elem-field">
	<legend>
		学员列表
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/user/select_userlist/${type}" method="post" id="searchForm" class="layui-form">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" name="user.userName" value="${user.userName}" id="userName"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" name="user.email" value="${user.email}" id="email"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">手机</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" name="user.mobile" value="${user.mobile}" id="mobile"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-inline">
						<select name="user.sex" lay-filter="select">
							<option <c:if test="${user.sex == 0 }">selected="selected"</c:if> value="0">请选择</option>
							<option <c:if test="${user.sex == 1 }">selected="selected"</c:if> value="1">男</option>
							<option <c:if test="${user.sex == 2 }">selected="selected"</c:if> value="2">女</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn layui-btn-small" onclick="submitSearch()">查找</button>
				</div>
			</div>

			<table class="layui-table" lay-even="" lay-skin="row">
				<thead>
					<tr>
						<td align="center"><input type="checkbox" id="selectAll"  lay-filter="selectAll" title="全选"/></td>
						<td align="center">姓名</td>
						<td align="center">邮箱</td>
						<td align="center">手机</td>
						<td align="center">性别</td>
						<td align="center">注册时间</td>
						<td align="center">状态</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="u" varStatus="index">
						<tr>
							<td align="center">
								<c:if test="${type==1 }"><!--1 短信  -->
									<c:if test="${not empty u.mobile && u.mobile.length()==11 }">
										<input type="checkbox" class="questionIds" id="${u.userId }" value="${u.mobile }"/>
									</c:if>
								</c:if>
								<c:if test="${type==2}"><!--2邮箱  -->
									<c:if test="${not empty u.email}">
										<input type="checkbox" class="questionIds" id="${u.userId }" value="${u.email }"/>
									</c:if>
								</c:if>
								<%--${u.userId }--%>
							</td>
							<td align="center">${u.userName}</td>
							<td align="center">${u.email}</td>
							<td align="center">${u.mobile}</td>
							<td align="center">
								<c:if test="${u.sex==1}">男</c:if>
								<c:if test="${u.sex==2}">女</c:if>
							</td>
							<td align="center">
								<fmt:formatDate value="${u.createTime}" pattern="yyyy/MM/dd HH:mm" />
							</td>
							<td align="center">
								<c:if test="${u.isavalible==1}">正常</c:if>
								<c:if test="${u.isavalible==2}">冻结</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
	<div style="text-align: center;">
		<a title="确定" onclick="selectQstList()" class="layui-btn layui-btn-small" href="javascript:void(0)">确定
		</a>
		<a title="取消" onclick="quxiao()" class="layui-btn layui-btn-small" href="javascript:void(0)">取消
		</a>
	</div>
	</fieldset>
</body>
</html>
