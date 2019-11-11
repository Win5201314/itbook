
/**
 * 发送找回密码邮件
 */
function sendPwdRecoveery(){
	if(checkInputVal()==true){
		var emailRecovery=$("#emailRecovery").val();
		var mobileRecovery=$("#mobileRecovery").val();
		var inputVal=$("#emailOrMobile").val();
		//邮箱 并且 开启邮箱找回密码 ，显示 输入验证码
		if(emailRecovery=='ON'&&checkEmail(inputVal)==true){
			if(isEmpty($("#randomCode").val())){
				$("#errorMsg").text("请输入验证码");
				return ;
			}
		}
		//手机号 并且 开启手机找回密码 ，显示 输入验证码
		if(mobileRecovery=='ON'&&checkMobile(inputVal)==true){
			if(isEmpty($("#u-mobile-prove").val())){
				$("#errorMsg").text("请输入手机验证码");
				return ;
			}
		}
		var params='';
		$("#recoveryForm input:text").each(function(){
			params+=$(this).serialize()+"&";
		});
		$.ajax({
			url:baselocation+'/front/ajax/findPassAjax',
			type:'post',
			dataType:'json',
			data:params,
			success:function(result){
				if(result.success==true){
					//刷新验证码
					$("#randomCode").next(".yz-pic").click();
					if(result.message=="uuid"){
						window.location.href = baselocation + '/front/updatepwd?token=' + result.entity;
					}else {
						$("#errorMsg").text(result.message);
					}
				}else{
					$("#errorMsg").text(result.message);
				}
			}
		});
	}
}
/**
 * 找回密码
 */
function changePwd() {
	var newpwd = $("#newpwd").val();
	var oldpwd = $("#oldpwd").val();
	if (newpwd != oldpwd){
		dialog('更改密码提示',"新密码和确认密码不一致",1);

	}else if(newpwd == oldpwd){
		$.ajax({
			url:baselocation+'/front/changePwd',
			type:'post',
			dataType:'json',
			data:{'newpwd':newpwd,"oldpwd":oldpwd,"token":token},
			success:function(result){
				if(result.success){
					/*dialog("提示信息",result.message,0);
					$(".order-submit").click(function(){*/
						//改完密码后跳转到首页
						//给首页传个参数，通过传递的参数来判断是否执行弹出帐号登陆窗口
						window.location.href=baselocation+"?msg=ExternalLogin";
					/*});*/
				}else{
					msgshow(result.message,"false","3000");
				}
			}
		});
	}

}
/**
 *获取手机找回密码验证码
 */
function getProve(obj) {

	var proveCode = $("#randomCode").val();
	var mobile = $("#emailOrMobile").val();
	var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
	if (reg.test(mobile) == false) {//格式不正确
		var adc =reg.test(mobile);
		$("#errorMsg").text("请输入正确的手机号");
		return;
	}
	$("#errorMsg").text("");
	$.ajax({
		url:baselocation+'/front/ajax/getProve',
		type:'post',
		dataType:'json',
		data:{"proveCode":proveCode,"mobile":mobile},
		success:function(result){
			if(result.success){
				//刷新验证码
				$("#randomCode").next(".yz-pic").click();
				$(obj).removeAttr("onclick");
				// $("#u-mobile-prove").css("display","block");
				$("#errorMsg").text(result.message);
				var timeTicket;
				var timeNum = 60;
				/*当点击获取验证码后设置60秒计时不可点击*/
				timeTicket = setInterval(function () {
					if (timeNum>1){
						timeNum--;
						/*设置按钮不可点击*/
						$(".yz-numb").addClass("mobile-yz-btn-no");
						$(".yz-numb").text(timeNum+"秒后可点击");
					}else if (timeNum==1){
						$(".yz-numb").text("点击获取验证码");
						$(".yz-numb").removeClass("mobile-yz-btn-no");
						timeNum = 60;
						$(obj).attr("onclick","getProve(this)");
						clearInterval(timeTicket);
					}
				},1000);
			}else{
				$("#errorMsg").text(result.message);
			}
		}
	});

}

/**
 * 检查输入内容
 * @param obj
 */
function checkInputVal() {
	$("#errorMsg").text("");
	var emailRecovery=$("#emailRecovery").val();
	var mobileRecovery=$("#mobileRecovery").val();
	var inputVal=$("#emailOrMobile").val();
	if(emailRecovery=='ON'&&mobileRecovery=='ON'){
		if(checkEmail(inputVal)!=true&&checkMobile(inputVal)!=true){
			$("#errorMsg").text("请输入正确的邮箱 / 手机号！");
			return false;
		}
	}
	else if(emailRecovery=='ON'){
		if(checkEmail(inputVal)!=true){
			$("#errorMsg").text("请输入正确的邮箱！");
			return false;
		}
	}
	else if(mobileRecovery=='ON'){
		if(checkMobile(inputVal)!=true){
			$("#errorMsg").text("请输入正确的手机号！");
			return false;
		}
	}

	//手机号 并且 开启手机找回密码 ，显示 输入验证码
	if(mobileRecovery=='ON'&&checkMobile(inputVal)==true){
		$("#mobileRandomCode").show();
	}else{
		$("#mobileRandomCode").hide();
	}
	return true;

}

/**
 * 检查输入的邮箱
 * @param inputVal
 * @returns {boolean}
 */
function checkEmail(inputVal) {
	var reg = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
	if (reg.test(inputVal) == false) {//格式不正确
		//$("#errorMsg").text("请输入正确的邮箱！");
		return false;
	}
	return true;
}

/**
 * 检查输入的手机
 * @param inputVal
 * @returns {boolean}
 */
function checkMobile(inputVal) {
	var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
	if (reg.test(inputVal) == false) {//格式不正确
		//$("#errorMsg").text("请输入正确的手机号！");
		return false;
	}
	return true;
}