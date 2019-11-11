<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>修改密码</title>
<script type="text/javascript" src="${ctx}/static/inxweb/front/forget_password.js"></script>
<script>
	var token = '${token}';
</script>
</head>
<body>
	<div class="bg-fa of">
		<section class="container">
			<div class="u-body i-b-p-box" style="min-height: 500px;">
				<div class="i-box i-box-password" class="i-box">
					<div>
						<section class="i-b-pas-title">
							<p>设置密码</p>
						</section>
						<%--<section class="c-infor-tabTitle c-tab-title current">
							&lt;%&ndash;<a class="c-title-name" style="cursor: default;" href="javascript: void(0)">忘记密码</a>&ndash;%&gt;
							<a id="emailRecovery" class="current c-title-name" title="邮箱找回密码" href="javascript: void(0)">邮箱找回</a>
							<a id="mobileRecovery" class="c-title-name" title="手机找回密码" href="javascript: void(0)">手机找回</a>
						</section>--%>
					</div>
					<form id="recoveryForm">
						<div class="email-box">
							<div class="mt10 mb10  ts-box">
								<p class="e-l-jy">
									<span class="fsize12 c-red" id="errorMsg"></span>
								</p>
							</div>
							<ol class="e-login-options">
								<li>
									<em class="zc-ico zc-dl-ioc">&nbsp;</em>
									<input type="password" id="oldpwd"  placeholder="请输入新密码" />
									<p class="lr-tip-wrap"></p>
								</li>
								<li>
									<em class="zc-ico zc-dl-ioc">&nbsp;</em>
									<input type="password" id="newpwd"  placeholder="请再次输入密码" />
									<p class="lr-tip-wrap"></p>
								</li>
							</ol>
							<section class=" tac">
								<a href="javascript: void(0)" onclick="changePwd()" title="提 交" class="e-login-btn">确 定</a>
							</section>
						</div>
					</form>

				</div>
			</div>
		</section>
	</div>
</body>
</html>