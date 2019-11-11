<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>第三方登录绑定</title>
	<style type="text/css">
		.lr-Switch ul li a{ border: 1px solid #0a59c9;  font-size: 1rem;color: #0a59c9;}
		.lr-Switch ul li .current {background-color:  #0a59c9;color: #fff;}
	</style>
</head>
<body>
<div class="m-ptb54">
	<div>
		<input type="hidden" id="name" value="${userProfile.name}"/>
		<input type="hidden" id="avatar" value="${userProfile.avatar}"/>
		<input type="hidden" id="userProfileId" value="${userProfile.userProfileId}"/>
		<div class="third-party">
			<h2 class="title">绑定${websitemap.web.company}账号</h2>
			<div class="Basic mt10">
				<div class="fl">
					<c:choose>
						<c:when test="${not empty userProfile.avatar }">
							<img src="${userProfile.avatar}">
						</c:when>
						<c:otherwise>
							<img src="/static/inxweb/img/avatar-boy.gif">
						</c:otherwise>
					</c:choose>

				</div>
				<p style="font-weight: bold;" class="fsize16 f-fM c-333 mt10">${userProfile.name}</p>
				<p class="fsize14 f-fM mt10">
					<tt class="c-999">您正在使用</tt>
					<tt class="c-master">
						<c:if test="${userProfile.profiletype=='QQ'}">QQ账号</c:if>
						<c:if test="${userProfile.profiletype=='SINA'}">新浪账号</c:if>
						<c:if test="${userProfile.profiletype=='WEIXIN' or userProfile.profiletype=='WEIXIN_PUBLIC'}">微信</c:if>
					</tt>
					<tt class="c-999">登录${websitemap.web.company}</tt>
				</p>
				<div class="clear"></div>
			</div>
			<div class="Basic-list mt40 menu" id="bunding">
				<ul class="third-p-form">
					<li>
						<div>
							<p class="mb20 pt20 fsize14 f-fM c-666 fl">绑定已有账号？</p>
							<a class="mb20 pt20 fsize14 f-fM c-red fl"  href="javascript:void(0)" onclick="$('#bunding').hide();$('#regBunding').show();">绑定新账号</a>
							<div class="clear"></div>
						</div>
						<p class="mb5 fsize14 f-fM c-333">Email/手机号</p>
						<span><input type="text"  class="t-p-inp" maxlength="30" name="" id="userEmail" value="" onkeyup='clearError($(this).attr("id"))'></span>
						<p class="mt5 fsize12 c-red f-fM" id="userEmailError"></p>
					</li>
					<li>
						<p class="mb5 fsize14 f-fM c-333">密码</p>
						<input type="password" class="t-p-inp" maxlength="10" name="" id="password" value="" onkeyup='clearError($(this).attr("id"))'>
						<p class="mt5 fsize12 c-red f-fM" id="passwordError"></p>
					</li>
					<li class="tac last-list">
						<a class="Determine-btn" onclick="bundling()" href="javascript:void(0)">确认绑定</a>
					</li>
				</ul>
			</div>
			<div class="menu undis" id="regBunding">
				<div>
					<div class="Basic-list mt40 ">
						<ul class="third-p-form">
							<li>
								<div>
									<a class="mb20 pt20 fsize14 f-fM c-red fl"  href="javascript:void(0)" onclick="$('#regBunding').hide();$('#bunding').show();">绑定已有账号</a>
									<p class="mb20 pt20 fsize14 f-fM c-666 fl">？绑定新账号</p>
									<div class="clear"></div>
								</div>
								<div id="bundlingEmail">
									<p class="mb5 fsize14 f-fM c-333">邮箱</p>
									<input type="text" class="t-p-inp" maxlength="30" id="regEmail" onkeyup='clearError($(this).attr("id"))'>
									<p class="mt5 fsize12 c-red f-fM" id="regEmailError"></p>
								</div>
							</li>
							<li id="bundlingMobile">
								<p class="mb5 fsize14 f-fM c-333">手机</p>
								<input type="text" class="t-p-inp" maxlength="11" id="regMobile" onkeyup='clearError($(this).attr("id"))'>
								<p class="mt5 fsize12 c-red f-fM" id="regMobileError"></p>
							</li>

							<li id="pp-randomcode-reg-mobile" style="display: none">
								<p class="mb5 fsize14 f-fM c-333">手机验证码</p>
								<input type="text" class="t-p-inp fl" style="width: 100px;" maxlength="4" id="regMobilerandomcode" onkeyup='clearError($(this).attr("id"))'>
								<a href="javascript:void(0)" onclick="sendPhonecodeRegister()" title="" class="vam ml10 disIb mobile-yz-btn">点击获取验证码</a>
								<p class="mt5 fsize12 c-red f-fM" id="regMobilerandomcodeError"></p>
							</li>
							<li>
								<p class="mb5 fsize14 f-fM c-333">密码</p>
								<input type="password" class="t-p-inp" maxlength="18" id="regPwd" onkeyup='clearError($(this).attr("id"))'>
								<p class="mt5 fsize12 c-red f-fM" id="regPwdError"></p>
							</li>
							<li>
								<p class="mb5 fsize14 f-fM c-333">确认密码</p>
								<input type="password" class="t-p-inp" id="confirmPassword"  onkeyup='clearError($(this).attr("id"))'>
								<p class="mt5 fsize12 c-red f-fM" id="confirmPasswordError"></p>
							</li>
							<li id="randomcodeLi">
								<p class="mb5 fsize14 f-fM c-333">验证码</p>
								<input id="randomcode" class="t-p-inp fl" type="text" maxlength="4" onkeyup='clearError($(this).attr("id"))' value="" name="" style="width: 100px;">
								<a class="vam ml10 disIb fl" title="" href="javascript:void(0)">
									<img width="86" height="40" src="/ran/ajax/random" alt="验证码，点击图片更换" onclick="this.src='/ran/ajax/random?random='+Math.random()">
								</a>
								<span class="c-999 fl ml10">
								看不清
								<br>
								<a class="js-verify-refresh c-green" onclick="$(this).parent().prev().find('img').click()" href="javascript:void(0)">换一张</a>
								</span>
								<p class="mt5 fsize12 c-red f-fM" id="randomcodeError" style="clear: both"></p>
								<p class="clear"></p>
							</li>
							<li class="tac last-list">
								<a class="Determine-btn" onclick="bundingRegister()" href="javascript:void(0)">确认绑定</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- body main -->
	</div>
</div>
<script>
	//判断邮箱、手机注册开关是否打开，控制邮箱、手机绑定是否显示
	var emailRegister= "${registerController.emailRegister}";
	var phoneRegister="${registerController.phoneRegister}";
	var phoneProving="${registerController.phoneProving}";
	if (emailRegister=="OFF"){
		$("#bundlingEmail").css("display","none");
	}
	if (phoneRegister=="OFF"){
		$("#bundlingMobile").css("display","none");
	}
	if (emailRegister=="ON"){
		$("#bundlingEmail").css("display","block");
	}
	if (phoneRegister=="ON"){
		$("#bundlingMobile").css("display","block");
	}
	if (phoneProving=="ON"){
		$("#pp-randomcode-reg-mobile").css("display","block");
		$("#randomcodeLi").insertBefore($("#pp-randomcode-reg-mobile"));
	}

</script>
<script src="${ctximg}/static/inxweb/user/bunding_profile.js" type="text/javascript"></script>
</body>
</html>