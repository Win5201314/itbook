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
	var qstChecked = $(".chekbox:checked");
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
function allselect(cb) {
	$("input[class='chekbox']").prop('checked', cb.checked);
}
</script>
</head>
<body  >
			<!-- /tab1 begin-->
<div class="">
	<div class="commonWrap">
		<form action="${ctx}/admin/user/selectCouponUserList" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table class="layui-table">
			
						<%--<caption>
							<div class="capHead">
								<div class="w50pre fl">
									<ul class="ddBar">
										<li>
										    <span class="ddTitle"><font>学员ID：</font></span>
											<input type="text"  class="layui-input" name="user.userId" value="${user.userId}" id="userId" />
										</li>
										&lt;%&ndash;<li>
											<span class="ddTitle"><font>注册开始日期：</font></span>
											<input type="text" name="user.startDate" value="${user.startDate}"  id="startDate" class="AS-inp"/>
										</li>&ndash;%&gt;
                                        <li>
                                            <span class="ddTitle"><font>手机号：</font></span>
                                            <input type="text" name="user.mobile" value="${user.mobile}" id="mobile" />
                                        </li>
									</ul>
									
								</div>
                                <div class="w50pre fl">
                                    <ul class="ddBar">
                                        <li>
                                            <span class="ddTitle"><font>邮箱：</font></span>
                                            <input type="text" name="user.email" value="${user.email}" id="useremail" />
                                        </li>
                                        &lt;%&ndash;<li>
                                            <span class="ddTitle"><font>注册结束日期：</font></span>
                                            <input type="text" name="user.endDate" value="${user.endDate}"  id="endDate" class="AS-inp"/>
                                        </li>&ndash;%&gt;
                                        <li>
                                        	<span class="ddTitle"><font>下单课程名称：</font></span>
                                            <input type="text" name="user.courseName" value="${user.courseName}" id="courseName" />
                                        	<input type="button"  class="layui-btn layui-btn-small layui-btn-danger" value="查询" name="" onclick="submitSearch()"/>
                                            <input type="button"  class="layui-btn layui-btn-small layui-btn-danger" value="清空" name="" onclick="clean()"/>
                                            <!-- <input type="button"  class="layui-btn layui-btn-small layui-btn-danger" value="导出Excel" name="" onclick="userExcel()"/> -->
                                        </li>
                                    </ul>

                                </div>
								<div class="clearfix"></div>
							</div>
						</caption>--%>
						<thead>
							学员ID：
							<input type="text"  class="layui-input" name="user.userId" value="${user.userId}" id="userId" />
							昵称：
							<input type="text"  class="layui-input" name="user.showName" value="${user.showName}" id="showName" />
							手机号：
							<input type="text"  class="layui-input" name="user.mobile" value="${user.mobile}" id="mobile" />
							邮箱：
							<input type="text"  class="layui-input" name="user.email" value="${user.email}" id="useremail" />
							下单课程名称：
							<input type="text"  class="layui-input" name="user.courseName" value="${user.courseName}" id="courseName" />
							<input type="button"  class="layui-btn layui-btn-small" value="查询" name="" onclick="submitSearch()"/>
							<%--<input type="button"  class="layui-btn layui-btn-small" value="清空" name="" onclick="clean()"/>--%>
							<tr>
								<th width="13%"><span><input type="checkbox" onclick="allselect(this)"/>ID</span></th>
								<th width="8%"><span>昵称</span></th>
								<th><span>邮箱</span></th>
								<th><span>手机</span></th>
								<th><span>注册时间</span></th>
								<th><span>状态</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="user" varStatus="index">
							<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
								<td>
									<input type="checkbox" class="chekbox" id="${user.userId }" value="${user.userId}"/>
									${user.userId }
								</td>
								<td>${user.showName }</td>
								<td>${user.email }</td>
								<td>${user.mobile }</td>
								<td><fmt:formatDate value="${user.createTime }" type="both"/></td>
								<td id="isavalible${user.userId}">
								<c:if test="${user.isavalible==0 }">正常</c:if>
								<c:if test="${user.isavalible==1 }">禁用</c:if>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${list.size()==0||list==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有学员信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
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
