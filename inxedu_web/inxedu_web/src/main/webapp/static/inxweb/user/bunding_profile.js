function clearError(name){
	$("#"+name).removeClass("error");
	$("#"+name+"Error").html("");
}


//绑定已有账户
function bundling(){
	var userEmail=$("#userEmail").val();
	var password=$("#password").val();
	if(isEmpty(userEmail)){
		$("#userEmailError").html('请填写注册时使用的邮箱号码或手机号码！');
		return;
	}
	if(isEmpty(password)){
		$("#passwordError").html('请输入密码！');
		return;
	}
	$.ajax({
		url:baselocation+"/front/ajax/bundingOld",
		data:{
			"account":userEmail,
			"password":password,
			"userProfileId": $("#userProfileId").val()
		},
		type:"post",
		dataType:"json",
		cache : false,
		async:false,
		success:function(result){
			if(result.success){
				dialog('绑定提示','绑定成功!',0);
				$("#dcWrap div.d-tips-1").find("a").attr("onclick","window.location.href='/uc/index';");
			}else{
				dialog('提示',result.message,1);
			}
		}
	});
}

/**
 * 绑定注册新用户
 */
function bundingRegister() {
	var emailVal=$("#regEmail").val();
	if (emailRegister=="ON"){
		if(emailVal==""){//验证邮箱是否为空
			$("#regEmailError").html('请输入邮箱！');
			return;
		}
		var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
		if(reg.test(emailVal)==false){//格式不正确
			$("#regEmailError").html('请输入正确的邮箱！');
			return;
		}
	}

	var mobileVal=$("#regMobile").val();
	if (phoneRegister=="ON"){
		if(mobileVal==""){//验证手机是否为空
			$("#regMobileError").html('请输入用户手机号！');
			return;
		}
		var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
		if(reg.test(mobileVal)==false){//格式不正确
			$("#regMobileError").html('请输入正确的手机！');
			return;
		}
	}

	var regMobilerandomcode=$("#regMobilerandomcode").val();
	if (phoneProving=="ON"){
		if(regMobilerandomcode==""){//验证手机是否为空
			$("#regMobilerandomcodeError").html('请输入手机验证码！');
			return;
		}
	}

	if($("#regPwd").val().trim()==""){//验证密码是否为空
		$("#regPwdError").html('请输入密码！');
		return;
	}
	if($("#regPwd").val().length<6){//验证密码长度
		$("#regPwdError").html('密码长度不能小于六位！');
		return;
	}
	if(($("#regPwd").val()).indexOf(" ")!=-1){
		$("#regPwdError").html('密码不能包含空格！');
		return false;
	}
	if($("#confirmPassword").val().trim()==""){//验证确认密码是否为空
		$("#confirmPasswordError").html('请输入确认密码！');
		return;
	}
	if($("#confirmPassword").val().trim()!=$("#regPwd").val()){//验证密码和确认密码是否相同
		$("#confirmPasswordError").html('密码和确认密码不一样！');
		return;
	}

	if (phoneProving!="ON"){
		if($("#randomcode").val().trim()==""){//验证码是否为空
			$("#randomcodeError").html('请输入验证码！');
			return;
		}
	}


	$.ajax({
		url : baselocation + "/front/ajax/bundingNew",
		data : {"email":$("#regEmail").val(),"password":$("#regPwd").val(),
			"confirmPwd":$("#confirmPassword").val(),"showName":$("#name").val(),"picImg":$("#avatar").val(),
			"mobile":$("#regMobile").val(),"userProfileId": $("#userProfileId").val(),"registerCode":$("#randomcode").val()
			,"mobileCode":regMobilerandomcode
		},
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			if(result.success==true) {
				dialog('绑定提示','绑定成功!',0);
				$("#dcWrap div.d-tips-1").find("a").attr("onclick","window.location.href='/uc/index';");
			}else {
				dialog('绑定提示',result.message,1);
			}
		},
		error : function(error) {
			dialog('绑定提示',"系统繁忙，请稍后再操作",1);
		}
	});
}


/**
 * 发送手机验证码
 */
function sendPhonecodeRegister() {
	var mobileVal=$("#regMobile").val();
	if(mobileVal==""){//验证手机是否为空
		$("#regMobileError").html('请输入用户手机号！');
		return;
	}
	var reg=/^(13[0-9]|15[0123456789]|18[0123456789]|14[0123456789]|17[0123456789])[0-9]{8}$/; //验证手机正则
	if(reg.test(mobileVal)==false){//格式不正确
		$("#regMobileError").html('请输入正确的手机号！');
		return;
	}

	var randomcodeReg=$("#randomcode").val();
	if(isEmpty(randomcodeReg)){
		$("#randomcodeError").html('请输入验证码！');
		return;
	}

	$.ajax({
		url:baselocation+'/createuser/ajax/sendPhoneRegister',
		type:'post',
		dataType:'json',
		data:{
			"mobileVal":mobileVal,
			"randomcodeReg":randomcodeReg
		},
		success:function(result){
			if(result.success==true){
				$("#regMobileError").html(result.message);
				var timeTicket;
				var timeNum = 60;
				//$("#phoneClick").css("visibility","hidden");
				// $("#recoverPhoneClick").css("visibility","visible");
				clearInterval(timeTicket);
				/*当点击获取验证码后设置60秒计时不可点击*/
				timeTicket = setInterval(function () {
					if (timeNum>1){
						timeNum--;
						/*设置按钮不可点击*/
						$(".mobile-yz-btn").addClass("mobile-yz-btn-no").attr("onclick","");
						$(".mobile-yz-btn").text(timeNum+"秒后可点击");
					}else if (timeNum==1){
						$(".mobile-yz-btn").text("点击获取验证码");
						$(".mobile-yz-btn").removeClass("mobile-yz-btn-no").attr("onclick","sendPhonecodeRegister()");
						timeNum = 60;

						clearInterval(timeTicket);

						//刷新验证码
						$(".js-verify-refresh.c-green").click();
					}
				},1000);
			}else{
				$("#regMobileError").html(result.message);
				//刷新验证码
				$(".js-verify-refresh.c-green").click();
			}

		}
	})
}