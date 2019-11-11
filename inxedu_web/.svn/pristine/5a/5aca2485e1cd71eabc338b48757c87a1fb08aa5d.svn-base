<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<script type="text/javascript" src="${ctx}/static/admin/system/sys-user.js"></script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		用户列表
	</legend>
	<div class="numb-box-t">
		<div class="layui-field-box">
			<form action="${ctx}/admin/sysuser/userlist" method="post" id="searchForm" class="layui-form">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">${courseSellType}标题</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="登录名/姓名/邮箱" type="text" name="querySysUser.keyWord" value="${querySysUser.keyWord}" />
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="searchUser();">查找</button>
						<a title="创建用户" onclick="showWin()" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">创建用户
						</a>
						<%--<a title="清空" onclick="$('#searchForm input:text').val('');" class="layui-btn layui-btn-small" href="javascript:void(0)">清空
                    </a>--%>
					</div>
				</div>
			</form>
			<table class="layui-table" lay-even="" lay-skin="row">
				<thead>
				<tr>
					<td align="center">登录名</td>
					<td align="center">姓名</td>
					<td align="center">邮箱</td>
					<td align="center">电话号</td>
					<td align="center">创建时间</td>
					<td align="center">最后登录时间</td>
					<td align="center">最后登录IP</td>
					<td align="center">状态</td>
					<td align="center" class="last-cz">操作</td>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${userList}" var="sysuser" varStatus="index">
					<tr>
						<td align="center">${sysuser.loginName}</td>
						<td align="center">
							<c:if test="${sysuser.userName!=null && sysuser.userName!=''}">${sysuser.userName}</c:if>
							<c:if test="${sysuser.userName==null || sysuser.userName==''}">--</c:if>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${sysuser.email!=null && sysuser.email!=''}">
									${sysuser.email}
								</c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${sysuser.tel!=null && sysuser.tel!=''}">
									${sysuser.tel}
								</c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<fmt:formatDate value="${sysuser.createTime}" pattern="yyyy/MM/dd" />
						</td>
						<td align="center">
							<c:if test="${sysuser.lastLoginTime!=null}">
								<fmt:formatDate value="${sysuser.lastLoginTime}" pattern="yyyy/MM/dd HH:mm" />
							</c:if>
							<c:if test="${sysuser.lastLoginTime==null}">--</c:if>
						</td>
						<td align="center">
							<c:if test="${sysuser.lastLoginIp!=null && sysuser.lastLoginIp!=''}">
								${sysuser.lastLoginIp}
							</c:if>
							<c:if test="${sysuser.lastLoginIp==null || sysuser.lastLoginIp==''}">
								--
							</c:if>
						</td>
						<td align="center">
							<c:if test="${sysuser.status==0}">正常</c:if>
							<c:if test="${sysuser.status==1}">冻结</c:if>
						</td>
						<td align="center">
							<button onclick="updatePwd(${sysuser.userId})" class="layui-btn layui-btn-small" type="button">修改密码</button>
							<button onclick="delUser(${sysuser.userId})" class="layui-btn layui-btn-small" type="button">删除用户</button>
							<button onclick="window.location.href='${ctx}/admin/sysuser/looklog/${sysuser.userId}'" class="layui-btn layui-btn-small" type="button">登陆日志</button>
							<span><%--用于冻结 启用 显示按钮--%>
								<c:if test="${sysuser.status==0}">
									<button onclick="disableOrstart(${sysuser.userId},2,this)" class="layui-btn layui-btn-small" type="button">冻结</button>
								</c:if>
								<c:if test="${sysuser.status==1}">
									<button onclick="disableOrstart(${sysuser.userId},1,this)" class="layui-btn layui-btn-small" type="button">启用</button>
								</c:if>
							</span>
							<button onclick="initUser(${sysuser.userId})" class="layui-btn layui-btn-small" type="button">修改</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</div>
	</div>
</fieldset>
	<!-- 添加用户窗口 ,开始-->
	<div id="createWin" style="display: none">
		<div class="numb-box">
			<form id="sysUserForm" class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">所属角色</label>
					<div class="layui-input-inline">
						<select name="sysUser.roleId" lay-filter="select">
							<option value="0">请选择</option>
							<c:forEach items="${sysRoleList}" var="role">
								<option value="${role.roleId}">${role.roleName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">登录名</label>
					<div class="layui-input-inline">
						<input  class="layui-input" name="sysUser.loginName" id="loginName" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">密码</label>
					<div class="layui-input-inline">
						<input  class="layui-input" name="sysUser.loginPwd" id="loginPwd" type="password">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">确认密码</label>
					<div class="layui-input-inline">
						<input  class="layui-input" id="confirmPwd" type="password">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">姓名</label>
					<div class="layui-input-inline">
						<input  class="layui-input" name="sysUser.userName" id="userName" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">邮箱</label>
					<div class="layui-input-inline">
						<input  class="layui-input" name="sysUser.email" id="email" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">电话</label>
					<div class="layui-input-inline">
						<input  class="layui-input" name="sysUser.tel" id="tel" type="text">
					</div>
				</div>

			</form>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-danger" onclick="createSysUser()">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
				</div>
			</div>
		</div>
		<!-- 添加用户窗口 ,结束-->
	</div>
	<!-- 修改用户窗口 ,开始-->
	<div id="updateWin" style="display: none; ">
		<div class="numb-box">
			<form id="updateSysUserForm" class="layui-form" action="">
				<input type="hidden" name="sysUser.userId" value="0" />
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">所属角色</label>
					<div class="layui-input-inline">
						<select name="sysUser.roleId" lay-filter="select">
							<option value="0">请选择</option>
							<c:forEach items="${sysRoleList}" var="role">
								<option value="${role.roleId}">${role.roleName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label  layui-form-label-w">登录名</label>
					<div class="layui-input-inline" >
						<input  class="layui-input layui-disabled" type="text" id="userLongName">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">姓名</label>
					<div class="layui-input-inline" >
						<input  class="layui-input" type="text" name="sysUser.userName">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">邮箱</label>
					<div class="layui-input-inline" >
						<input  class="layui-input" type="text" name="sysUser.email">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">电话</label>
					<div class="layui-input-inline" >
						<input  class="layui-input" type="text" name="sysUser.tel">
					</div>
				</div>

			</form>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-danger" onclick="updateSysUser()">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改用户窗口 ,结束-->

	<!-- 修改密码窗口 ,开始-->
	<div id="updatePwdWin" style="display: none;">
		<div class="numb-box">
			<form class="layui-form" action="">
				<input type="hidden" id="sysUserId" value="0" />
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">密码</label>
					<div class="layui-input-inline">
						<input  class="layui-input" type="password">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">确认密码</label>
					<div class="layui-input-inline">
						<input class="layui-input" type="password">
					</div>
				</div>
			</form>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-danger" onclick="submitUpdatePwd()">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改密码窗口 ,结束-->
</body>
</html>