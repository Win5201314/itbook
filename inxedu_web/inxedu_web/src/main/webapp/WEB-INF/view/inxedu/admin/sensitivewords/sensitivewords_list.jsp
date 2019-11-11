<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>敏感词列表</title>

<!-- 时间控件 -->
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>


<script type="text/javascript">
$(function(){
	
});

/**
 * 删除
 */
function delSensitiveWords(id,em){
	if(!confirm('确实要删除吗?')){
		return;
	}
	$.ajax({
		url:'/admin/ajax/sensitiveWordsDel/'+id,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==false){
                layer.msg(result.message, {icon: 5, shift: 6});
			}else{
				location.reload();
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}
/**
 * 显示添加窗口
 */
function showWin(){
	//通过这种方式弹出的层，每当它被选择，就会置顶。
	layer.open({
		title:'添加敏感词',
		type: 1,
		shade: false,
		area: '500px',
		maxmin: false,
		content: $('#createWin')
	});
}
function formSubmit(){
	$("#addForm").submit();
}
</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		敏感词
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/sensitiveWords/list" method="post" id="searchForm" class="layui-form">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<div class="layui-form-item">
				<%--<div class="layui-inline">
					<label class="layui-form-label">id</label>
					<div class="layui-input-inline">
						<input type="number" min="0" class="layui-input" name="sensitiveWords.id" value="${sensitiveWords.id }" placeholder="id"/>
					</div>
				</div>--%>
				<div class="layui-inline">
					<label class="layui-form-label">敏感词</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" name="sensitiveWords.sensitiveWord" value="${sensitiveWords.sensitiveWord }" placeholder="敏感词"/>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="$('#searchForm').submit();">查找敏感词</button>
					<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="showWin()" type="button">添加敏感词</button>
				</div>

			</div>
		</form>
		<table class="layui-table" lay-even="" lay-skin="row">
			<thead>
				<tr>
					<td align="center">id</td>
					<td align="center">敏感词</td>
					<td align="center">操作</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${sensitiveWordsList}" var="sensitiveWords"  varStatus="index">
					<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
						<td align="center">${sensitiveWords.id }</td>
						<td align="center">${sensitiveWords.sensitiveWord }</td>
						<td align="center">
							<button onclick="delSensitiveWords(${sensitiveWords.id},this)" class="layui-btn layui-btn-small" type="button">删除</button>
							<button onclick="window.location.href='${ctx}/admin/sensitiveWords/toUpdate/${sensitiveWords.id}'" class="layui-btn layui-btn-small" type="button">修改</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
	<div class="numb-box"  style="display: none" id="createWin">
		<div class="layui-field-box">
			<form action="${ctx}/admin/sensitiveWords/add" method="post" id="addForm" class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">敏感词</label>
					<div class="layui-input-block">
						<input class="layui-input layui-input-6" required="required"  placeholder="请输入敏感词" name="sensitiveWords.sensitiveWord" id="sensitiveWord" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block mt20">
						<button class="layui-btn layui-btn-danger" onclick="formSubmit()">确定</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</fieldset>
</body>
</html>