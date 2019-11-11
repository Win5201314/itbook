<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员列表</title>
<script type="text/javascript" src="${ctx}/static/admin/user/user.js"></script>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script>
	layui.use(['form', 'laydate'], function(){
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function(data){
		});
		//各种基于事件的操作，下面会有进一步介绍
	});
</script>
</head>
<body>
	<fieldset class="layui-elem-field">
		<legend>
			学员列表
		</legend>
		<div class="layui-field-box">
			<form action="${ctx}/admin/user/getuserList" method="post" id="searchForm" class="layui-form">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">账号/邮箱</label>
						<div class="layui-input-inline">
							<input placeholder="账号/邮箱/手机" type="text" name="queryUser.keyWord" value="${queryUser.keyWord}" class="layui-input"/>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">状态</label>
						<div class="layui-input-inline">
							<select name="queryUser.isavalible" lay-filter="select">
								<option value="0">全部</option>
								<option <c:if test="${queryUser.isavalible==1}"> selected="selected" </c:if> value="1">正常</option>
								<option <c:if test="${queryUser.isavalible==2}"> selected="selected" </c:if> value="2">冻结</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">注册来源</label>
						<div class="layui-input-inline">
							<select name="queryUser.registerFrom">
								<option value="">请选择</option>
								<option value="">请选择</option>
								<option <c:if test="${queryUser.registerFrom=='register'}"> selected="selected" </c:if> value="register">网站</option>
								<option <c:if test="${queryUser.registerFrom=='admin'}"> selected="selected" </c:if> value="admin">后台批量开通</option>
								<option <c:if test="${queryUser.registerFrom=='userCard'}"> selected="selected" </c:if> value="userCard">学员卡</option>
								<option <c:if test="${queryUser.registerFrom=='OpenAppRegisterFrom'}"> selected="selected" </c:if> value="OpenAppRegisterFrom">第三方登录</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="查询" onclick="goPage(1)">
						<input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="导出Excel" onclick="userExcel()">
					</div>
				</div>

				<table  class="layui-table" lay-even="" lay-skin="row">
					<thead>
					<tr>
						<td align="center">账号</td>
						<td align="center">邮箱</td>
						<td align="center">手机号</td>
						<td align="center">注册来源</td>
						<td align="center">状态</td>
						<td align="center">登录方式</td>
						<td align="center" width="40%">操作</td>
					</tr>
					</thead>

					<tbody>
					<c:forEach items="${userList}" var="user" varStatus="index">
						<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
							<td align="center">${user.userName}</td>
							<td align="center">${user.email}</td>
							<td align="center">${user.mobile}</td>
							<td align="center">
								<c:if test="${user.registerFrom=='register'}">网站</c:if>
								<c:if test="${user.registerFrom=='admin'}">后台批量开通</c:if>
								<c:if test="${user.registerFrom=='userCard'}">学员卡</c:if>
								<c:if test="${user.registerFrom=='OpenAppRegisterFrom'}">第三方登录</c:if>
							</td>
							<td align="center" class="status">
								<c:if test="${user.isavalible==1}">正常</c:if>
								<c:if test="${user.isavalible==2}">冻结</c:if>
							</td>
							<td align="center">
								<c:if test="${empty user.userProfileList}">网校</c:if>
								<c:forEach items="${user.userProfileList}" var="userProfile" varStatus="index">
									<c:if test="${userProfile.profiletype=='QQ'}">QQ</c:if>
									<c:if test="${userProfile.profiletype=='SINA'}">微博</c:if>
									<c:if test="${userProfile.profiletype=='WEIXIN'}">微信PC</c:if>
									<c:if test="${userProfile.profiletype=='WEIXIN_PUBLIC'}">微信里登录</c:if>
									${!(index.last) ? ',' : ''}

								</c:forEach>
							</td>
							<td align="center">

									<%--<c:if test="${serviceSwitch.exam=='ON'}">
                                        <button onclick="window.location.href='/admin/paper/toPaperRecord?queryPaperRecord.email=${user.email}'" class="layui-btn layui-btn-small" type="button">考试记录</button>
                                    </c:if>--%>
								<button onclick="window.location.href='${ctx}/admin/order/showorderlist?queryOrder.userId=${user.userId}'" class="layui-btn layui-btn-small" type="button">查看订单</button>
								<button onclick="selectCousre(${user.userId})" class="layui-btn layui-btn-small" type="button">赠送课程</button>
								<c:if test="${serviceSwitch.member=='ON'}">
									<button onclick="selectMember(${user.userId})" class="layui-btn layui-btn-small" type="button">赠送会员</button>
								</c:if>
								<button onclick="window.location.href='${ctx}/admin/user/lookuserlog/${user.userId}'" class="layui-btn layui-btn-small" type="button">查看日志</button>
								<button onclick="window.location.href='/admin/user/toupdateuser?userId=${user.userId}'" class="layui-btn layui-btn-small" type="button">修改</button>
								<button onclick="window.location.href='/admin/account/detailed_list?queryUserAccounthistory.userId=${user.userId}&queryUserAccounthistory.actHistoryType=BACKMONEY'" class="layui-btn layui-btn-small" type="button">返现记录</button>
								<button onclick="window.location.href='/admin/invitationRecord/list?invitationRecord.userId=${user.userId}'" class="layui-btn layui-btn-small" type="button">邀请记录</button>
								<span id="frozenOrThaw${user.userId}">
									<c:if test="${user.isavalible!=2}">
										<button onclick="frozenOrThaw(${user.userId},2,this)" class="layui-btn layui-btn-small" type="button">冻结</button>
									</c:if>
									<c:if test="${user.isavalible==2}">
										<button onclick="frozenOrThaw(${user.userId},1,this)" class="layui-btn layui-btn-small" type="button">解冻</button>
									</c:if>
								</span>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</form>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</div>
	</fieldset>
</body>
</html>