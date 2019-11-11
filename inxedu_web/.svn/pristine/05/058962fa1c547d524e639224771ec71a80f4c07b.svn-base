<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>找回密码</title>
<script type="text/javascript" src="${ctx}/static/inxweb/front/forget_password.js"></script>
</head>
<body>
	<div class="bg-fa of">
		<section class="container">
			<div class="u-body i-b-p-box" style="min-height: 500px;">
				<div class="i-box i-box-password" class="i-box">
					<div>
						<section class="i-b-pas-title">
							<p>找回密码</p>
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
								<input type="hidden" id="emailRecovery" value="${registerController.emailRecovery }"/>
								<input type="hidden" id="mobileRecovery" value="${registerController.mobileRecovery }"/>
								<li>
									<em class="lo-ico zc-dl-ioc">&nbsp;</em>
									<input type="text" name="emailOrMobile" id="emailOrMobile" onkeyup='$("#errorMsg").text("");' onblur="checkInputVal()" placeholder="<c:choose>
											<c:when test="${registerController.emailRecovery=='ON' && registerController.mobileRecovery=='ON'}" >请输入邮箱 / 手机号</c:when>
											<c:when test="${registerController.emailRecovery=='ON'}" >请输入邮箱</c:when>
											<c:otherwise>请输入手机号</c:otherwise>
										</c:choose>
										" />
									<p class="lr-tip-wrap"></p>
								</li>
								<li class="clearfix">
									<div class="clearfix">
										<input type="text" class="fl pass_input yazm-input" placeholder="请输入验证码" id="randomCode" onkeyup='$("#errorMsg").text("");' name="pageCode" value=""> <img class="ml10 yz-pic" width="145" height="42" onclick="this.src='${ctx}/ran/ajax/random?random='+Math.random();" alt="验证码，点击图片更换" src="${ctx}/ran/ajax/random" maxlength="4">
									</div>
									<p class="lr-tip-wrap"></p>
								</li>
								<li class="pr undis" id="mobileRandomCode">
									<div class="clearfix">
										<input type="text" class="pass_input fl pass_input yazm-input" name="mobileCode" onkeyup='$("#errorMsg").text("");' id="u-mobile-prove" placeholder="请输入手机验证码	" />
										<a href="javascript:void (0)" class="ml10 yz-numb" onclick="getProve(this)" alt="点击获取验证码"  maxlength="6">点击获取验证码</a>
									</div>
									<p class="lr-tip-wrap"></p>
								</li>
							</ol>
							<section class=" tac">
								<a href="javascript: void(0)" onclick="sendPwdRecoveery()" title="提 交" class="e-login-btn">提 交</a>
							</section>
						</div>
					</form>
					<%--<form id="mobileRecoveryForm" style="display: none">
						<div class="email-box">
							<div class="mt10 mb10">
								<p class="e-l-jy">
									<span class="fsize12 c-red" id="mobileErrorMsg"></span>
								</p>
							</div>
							<ol class="e-login-options">
								<li>
									<em class="lo-ico zc-dl-ioc">&nbsp;</em>
									<input type="text" name="email" id="u-mobile" placeholder="请输入邮箱 / 手机号	" />
									<p class="lr-tip-wrap"></p></li>
								<li class="clearfix" id="getProve"><input type="text" id="u-m-prove" class="fl pass_input yazm-input"  placeholder="请输入图片验证码" name="pageCode" value=""> <img class="ml10 yz-pic" width="145" height="42" onclick="this.src='${ctx}/ran/ajax/random?random='+Math.random();" alt="验证码，点击图片更换" src="${ctx}/ran/ajax/random"  maxlength="4">
									<p id="falseError"  class="lr-tip-wrap c-red"></p>
								</li>
								<li class="pr">
									<div class="clearfix">
									<input type="text" class="pass_input fl pass_input yazm-input" name="mobile" id="u-mobile-prove" placeholder="请输入手机验证码	" />
									<a href="javascript:void (0)" class="ml10 yz-numb" onclick="getProve()" alt="点击获取验证码"  maxlength="6">点击获取验证码</a>
									</div>
										<p class="lr-tip-wrap"></p>
								</li>

							</ol>
							<section class=" tac">
								<a href="javascript: void(0)" onclick="recoveryMobilePwd()" title="提 交" class="e-login-btn">提 交</a>
							</section>
						</div>
					</form>--%>
				</div>
			</div>
		</section>
	</div>
</body>
</html>