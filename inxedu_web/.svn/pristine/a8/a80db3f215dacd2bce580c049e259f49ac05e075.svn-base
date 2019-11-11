<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>选择学员列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	  $( "#startDate,#endDate" ).datetimepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
});
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function clean(){
	$("#userId,#useremail,#mobile,#showName,#startDate,#endDate,#courseName").val("");
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
	var qstChecked = $(".radio:radio");
	if (qstChecked.length == 0) {
        layer.msg("请选择房间", {icon: 5, shift: 6});
		return;
	}
	opener.addBaijiayunRoom($('input:radio:checked').val());
	quxiao();
}
// 把选中用户一条记录放到数组中
function toParentsValue(obj) {
	myArrayMoveStock.push(obj);
}
function allselect(cb) {
	$("input[class='chekbox']").prop('checked', cb.checked);
}
function deleteRoom(roomId) {

	$.ajax({
		url:"${ctx}/admin/liveRoom/deleteRoom",
		data:{"roomId":roomId},
		type: "post",
		dataType:"json",
		success:function(data){
			if (data.success==true){
				window.location.reload();
			}else {
                layer.msg("", {icon: 5, shift: 6});
			}
		}
	});
}
</script>
</head>
<body  >
			<!-- /tab1 begin-->
<div class="">
	<div class="commonWrap">
		<form action="${ctx}/admin/liveRoom/selectBaijiayunRoom" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table class="layui-table">
						<thead>
							<tr>
								<th><span>房间ID</span></th>
								<th><span>标题</span></th>
								<th><span>最大人数</span></th>
								<th><span>开始时间</span></th>
								<th><span>结束时间</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<jsp:useBean id="start_time" class="java.util.Date"/>
						<jsp:useBean id="end_time" class="java.util.Date"/>
						<c:forEach  items="${roomList}" var="room" varStatus="index">
							<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
								<td>
									<input type="radio" name="room_id" class="radio"  value="${room.room_id }"/>
									${room.room_id }
								</td>
								<td>${room.title }</td>
								<td>${room.max_users }</td>
								<c:set target="${start_time}" property="time" value="${room.start_time*1000}"/>
								<c:set target="${end_time}" property="time" value="${room.end_time*1000}"/>
								<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${start_time}" type="both"/></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${end_time}" type="both"/></td>
								<td>
									<input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="deleteRoom(${room.room_id })" value="删除">
								</td>
							</tr>
							</c:forEach>
							<tr class="">
								<td colspan="9">
									<input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="selectQstList()" value="确定">
									<input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="quxiao();" value="取消">
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
</body>
</html>
