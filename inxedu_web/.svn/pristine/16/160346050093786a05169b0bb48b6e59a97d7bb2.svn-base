<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		type='${type}';
		$("#"+type).attr("href","javascript:void(0)");
		if(type=='logo'){
			initSimpleImageUpload('fileuploadButton','websiteLogo',callback);
		}
	});
	function callback(imgUrl){
		$('#subjcetpic').attr("src",'${staticServer}'+imgUrl);
		$("#subjcetpic").show();
		$('#imagesUrl').val(imgUrl);
	}
	function submit(){
		if(type!=''){
			if(type=='logo'){
				if($("#imagesUrl").val()==''){
                    layer.msg("请上传logo", {icon: 5, shift: 6});
					return ;
				}
				$("#searchForm").attr("action","${ctx}/admin/websiteProfile/update");
			}else if(type=='ico'){
				var icoFile=$("#icoFile").val();
				if(icoFile!=null&&icoFile!=''){
					var fileNames=icoFile.split('.');
			  		var fileName=fileNames[0];
			  		var fileNameSuffix=fileNames[1];
			  		if(!(fileNameSuffix == "ico" || fileNameSuffix == "ICO")) {
                        layer.msg("请选择ico格式的图片!", {icon: 5, shift: 6});
			  			return;
			  		}else if(fileName.indexOf('favicon')==-1)
			  		{
                        layer.msg("文件必须命名为favicon", {icon: 5, shift: 6});
			  			return;
			  		}else{
			  			$("#icoForm").attr("action","${ctx}/admin/websiteProfile/uploadIco");
			  			$("#icoForm").submit();
			  			return;
			  		}
				}else{
                    layer.msg("请上传ioc文件", {icon: 5, shift: 6});
					return;
				}
			}else{
				$("#searchForm").attr("action","${ctx}/admin/websiteProfile/find/"+type+"");
			}
			$("#searchForm").submit();
		}
	}

</script>
	<script type="text/javascript">
		$(function(){
			initSimpleImageUpload('fileupload','online',callback);
		});
		function callback(imgUrl){
			$("#onlinepic").attr("src","${staticServer}"+imgUrl);
			$("#imageUrl").val(imgUrl);
		}
		function doSubmit(){
			if($("#link").val()==null||$("#link").val()==''){
                layer.msg("请填写咨询链接", {icon: 5, shift: 6});
				return;
			}
			if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
                layer.msg("请上传二维码", {icon: 5, shift: 6});
				return ;
			}
			$("#addOnlineForm").submit();
		}
	</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		网站管理
	</legend>
	<div class="numb-box">
		<div class="layui-field-box">
			<div class="commonWrap">
				<div class="capHead">
					<div class="clearfix">
						<div class="optionList">
							<%--<button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/websiteProfile/find/message'"
                                    id="message">消息配置</button>
                            <button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/websiteProfile/find/emailConfigure'"
                                    id="emailConfigure">邮箱配置</button>
                            <button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/websiteProfile/find/sms'"
                                    id="sms">短信配置</button>--%>
							<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${ctx}/admin/websiteProfile/find/web'"
							>基本信息</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${ctx}/admin/websiteProfile/find/serviceSwitch'"
									id="thirdlogin">功能开关</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${ctx}/admin/websiteProfile/find/registerController'"
									id="registerController">注册开关</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${ctx}/admin/websiteProfile/find/inxeduCloud'"
							>因酷云</button>
								<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${ctx}/admin/websiteProfile/find/baijiayun'"
								>百家云</button>
								<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${ctx}/admin/websiteProfile/find/genseeLive'"
										id="genseeLive">展示互动直播配置</button>
							<%--<button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/websiteProfile/find/cc'"
                                id="web">CC视频配置</button>--%>
							<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="window.location.href='${ctx}/admin/websiteProfile/find/WebSwitch'"
                                id="WebSwitch">限制登陆</button>
							<%--&nbsp;&nbsp;&nbsp;--%>
							<%--<button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/websiteProfile/find/logo'"
                                id="logo">logo</button>
                            &nbsp;&nbsp;&nbsp;
                            <button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/websiteProfile/find/ico'"
                                id="ico">ico</button>
                            &nbsp;&nbsp;&nbsp;--%>
							<%--<button type="button" class="layui-btn layui-btn-small"
                                onclick="window.location.href='${ctx}/admin/websiteProfile/find/censusCode'" id="censusCode">统计代码</button>--%>

							<%--<button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/websiteProfile/online'"
                                    >在线咨询</button>--%>
							<%--<button type="button" class="layui-btn layui-btn-small" onclick="window.location.href='${ctx}/admin/website/navigates'"
                                    >导航管理</button>--%>
						</div>
					</div>
				</div>
				<table class="layui-table mt20" lay-even="" lay-skin="row">
					<thead>
						<tr>
						<td width="30%" align="center">
							<span>名称</span>
						</td>
						<td width="70%" align="center">
							<span>描述</span>
						</td>
					</tr>
					</thead>
					<c:if test="${type=='web' }">
						<tbody id="tabS_web" align="center">
						<tr class="odd">
							<td>网站title(网站头部)</td>
							<td>${webSiteMap.web.title}</td>
						</tr>
						<tr>
							<td>网校名称(网站头部)</td>
							<td>${webSiteMap.web.company}</td>
						</tr>
						<tr class="odd">
							<td>网站作者</td>
							<td>${webSiteMap.web.author}</td>
						</tr>
						<tr>
							<td>关键词</td>
							<td>${webSiteMap.web.keywords}</td>
						</tr>
						<tr class="odd">
							<td>描述</td>
							<td>${webSiteMap.web.description}</td>
						</tr>
						<tr>
							<td>联系邮箱(网站底部)</td>
							<td>${webSiteMap.web.email}</td>
						</tr>
						<tr class="odd">
							<td>联系电话(网站底部)</td>
							<td>${webSiteMap.web.phone}</td>
						</tr>
						<tr>
							<td>工作时间(网站底部)</td>
							<td>${webSiteMap.web.workTime}</td>
						</tr>
						<tr>
							<td>备注(网站底部)</td>
							<td>${webSiteMap.web.remarks}</td>
						</tr>
						<tr class="odd">
							<td>版权以及备案号(网站底部)</td>
							<td>${webSiteMap.web.copyright}</td>
						</tr>
						<tr>
							<td>设置新的ico文件</td>
							<td>
								<img class="icoimg" alt="" src="${ctx}/favicon.ico?v=<%=Math.random()*100%>">
							</td>
						</tr>
						<tr class="odd">
							<td>设置新logo</td>
							<td>
								<img alt="" src="${staticServer}${webSiteMap.web.logo}" id="subjcetpic" width="144px" height="90px" />
							</td>
						</tr>
						<tr>
							<td>统计代码</td>
							<td>
								<textarea rows="6" cols="60" disabled="disabled">${webSiteMap.web.censusCodeString}</textarea>
							</td>
						</tr>
						<tr class="odd">
							<td > 咨询链接</td>
							<td>
									${webSiteMap.web.onlineUrl}

							</td>
						</tr>
						<tr>
							<td>二维码</td>
							<td>
								<img src="${staticServer}${webSiteMap.web.onlineImageUrl}" alt="" width="100px" height="100px" id="onlinepic" />
							</td>
						</tr>
						</tbody>
					</c:if>
					<c:if test="${type=='alipay'}">
						<tbody id="tabS_alipay" align="center">
						<tr class="odd">
							<td>合作者身份（PID）</td>
							<td>${webSiteMap.alipay.alipaypartnerID}</td>
						</tr>
						<tr>
							<td>安全校验码（key）</td>
							<td>${webSiteMap.alipay.alipaykey}</td>
						</tr>
						<tr class="odd">
							<td>商家邮箱</td>
							<td>${webSiteMap.alipay.sellerEmail}</td>
						</tr>
						<tr>
							<td>支付宝<!-- 选择(企业或个人，个人支付宝需定制) --></td>
							<c:if test="${webSiteMap.alipay.status==0}">
								<td>企业支付宝</td>
							</c:if>
							<c:if test="${webSiteMap.alipay.status==1}">
								<td>个人支付宝</td>
							</c:if>
						</tr>
						</tbody>
					</c:if>
					<c:if test="${type=='sms'}">
						<tbody id="tabS_alipay" align="center">
						<tr class="odd">
							<td>短信提供商</td>
							<td>
								<c:if test="${webSiteMap.sms.smstype=='tengXunSms'}">腾讯云短信</c:if>
								<c:if test="${webSiteMap.sms.smstype=='other'}">其他</c:if>
							</td>
						</tr>
						<tr class="">
							<td>腾讯云sdkappid</td>
							<td>${webSiteMap.sms.sdkappid}</td>
						</tr>
						<tr class="odd">
							<td>腾讯云strAppkey</td>
							<td>${webSiteMap.sms.strAppkey}</td>
						</tr>
						</tbody>
					</c:if>
					<form action="?" id="searchForm" method="post">
						<input type="hidden" name="flag" id="flag" value="flag" />
						<input type="hidden" name="type" id="type" value="${type}" />
						<c:if test="${type=='logo'}">
							<tbody id="tabS_logo" align="center">
							<tr class="odd">
								<td>图片地址</td>
								<td>
									<input type="text" name="url" id="imagesUrl" value="${webSiteMap.logo.url}" style="width: 450px;" />
									<font color="red">*LOGO链接,可直接修改</font>
								</td>
							</tr>
							</tbody>
						</c:if>
					</form>
					<c:if test="${type=='censusCode' }">
						<tbody id="tabS_censusCode" align="center">

						</tbody>
					</c:if>
					<form action="?" method="post" enctype="multipart/form-data" id="icoForm">
						<c:if test="${type=='ico' }">
							<tbody id="tabS_censusCode" align="center">
							<tr  class="odd">
								<td>ico文件</td>
								<td>
									<img alt="" src="${ctx}/favicon.ico?v=<%=Math.random()*100%>">
								</td>
							</tr>

							</tbody>
						</c:if>
						<c:if test="${type=='cc'}">
							<tbody id="tabS_alipay" align="center">
                                                        <tr>
                                                            <th>CC视频配置</th>
                                                        </tr>
                                                        <tr class="odd">
                                                            <td>CC ID</td>
                                                            <td>${webSiteMap.cc.ccappID}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>CC KEY</td>
                                                            <td>${webSiteMap.cc.ccappKEY}</td>
                                                        </tr>
                                                        </tbody>
						</c:if>
						<c:if test="${type=='inxeduCloud'}">
							<%--<tbody align="center">
                            <tr>
                                <th>云视频配置</th>
                            </tr>
                            <tr class="odd">
                                <td>因酷云UserId</td>
                                <td>${webSiteMap.inxeduVideo.UserId}</td>
                            </tr>
                            <tr>
                                <td>因酷云SecretKey</td>
                                <td>${webSiteMap.inxeduVideo.SecretKey}</td>
                            </tr>
                            <tr class="odd">
                                <td>因酷云AccessKey</td>
                                <td>${webSiteMap.inxeduVideo.AccessKey}</td>
                            </tr>
                            </tbody>--%>
							<tbody align="center">
							<tr class="odd">
								<td>因酷云UserId</td>
								<td>${webSiteMap.inxeduCloud.UserId}</td>
							</tr>
							<tr>
								<td>因酷云AccessKey</td>
								<td>${webSiteMap.inxeduCloud.AccessKey}</td>
							</tr>
							<tr class="odd">
								<td>因酷云SecretKey</td>
								<td>${webSiteMap.inxeduCloud.SecretKey}</td>
							</tr>
							</tbody>
						</c:if>
						<c:if test="${type=='genseeLive'}">
							<tbody align="center">
							<tr class="odd">
								<td>直播站点域名</td>
								<td>${webSiteMap.genseeLive.siteDomain}</td>
							</tr>
							<tr>
								<td>账号</td>
								<td>${webSiteMap.genseeLive.loginName}</td>
							</tr>
							<tr class="odd">
								<td>密码</td>
								<td>${webSiteMap.genseeLive.password}</td>
							</tr>
							</tbody>
						</c:if>
						<c:if test="${type=='baijiayun'}">
							<tbody align="center">
							<tr class="odd">
								<td>百家云Partner_ID</td>
								<td>${webSiteMap.baijiayun.PartnerId}</td>
							</tr>
							<tr>
								<td>百家云Partner_Key</td>
								<td>${webSiteMap.baijiayun.PartnerKey}</td>
							</tr>
							<tr class="odd">
								<td>百家云Secret_Key</td>
								<td>${webSiteMap.baijiayun.SecretKey}</td>
							</tr>
							</tbody>
						</c:if>
						<c:if test="${type=='genseeLive'}">

						</c:if>
						<c:if test="${type=='WebSwitch'}">
							<tbody id="tabS_alipay" align="center">
							<tr class="odd">
								<td>限制登录</td>
								<td>${webSiteMap.WebSwitch.limitLogin}</td>
							</tr>
							<tr>
								<td>imPath</td>
								<td>${webSiteMap.WebSwitch.imPath}</td>
							</tr>
							</tbody>
						</c:if>
						<c:if test="${type=='registerController'}">
							<tbody align="center">
							<tr style="display: none" class="odd">
								<td>邮箱注册开关</td>
								<td>${webSiteMap.registerController.emailRegister}</td>
							</tr>
							<tr style="display: none">
								<td>手机注册开关</td>
								<td>${webSiteMap.registerController.phoneRegister}</td>
							</tr>
							<tr style="display: none" class="odd">
								<td>邮箱注册验证开关</td>
								<td>${webSiteMap.registerController.emailProving}</td>
							</tr>
							<tr>
								<td>手机注册验证开关</td>
								<td>${webSiteMap.registerController.phoneProving}</td>
							</tr>
							<tr class="odd">
								<td>邮箱密码找回开关</td>
								<td>${webSiteMap.registerController.emailRecovery}</td>
							</tr>
							<tr>
								<td>手机密码找回开关</td>
								<td>${webSiteMap.registerController.mobileRecovery}</td>
							</tr>
							<tr class="odd">
								<td>ip注册次数限制</td>
								<td>${webSiteMap.registerController.ipLimit}</td>
							</tr>
							<tr>
								<td>邮箱注册次数限制</td>
								<td>${webSiteMap.registerController.emailLimit}</td>
							</tr>
							<tr class="odd">
								<td>手机注册次数限制</td>
								<td>${webSiteMap.registerController.mobileLimit}</td>
							</tr>
							</tbody>
						</c:if>
						<c:if test="${type=='serviceSwitch'}">
							<tbody id="tabS_alipay" align="center">
							<tr class="odd">
								<td>腾讯QQ登录</td>
								<td>${webSiteMap.serviceSwitch.verifyQQ}</td>
							</tr>
							<tr>
								<td>新浪微博登录</td>
								<td>${webSiteMap.serviceSwitch.verifySINA}</td>
							</tr>
							<tr class="odd">
								<td>微信登录</td>
								<td>${webSiteMap.serviceSwitch.verifyWeiXin}</td>
							</tr>
							<tr>
								<td>第三方登录绑定开关</td>
								<td>${webSiteMap.serviceSwitch.thirdLoginBinding}</td>
							</tr>
							<tr class="odd">
								<td>优惠券</td>
								<td>${webSiteMap.serviceSwitch.coupon}</td>
							</tr>
							<tr>
								<td>支付宝支付</td>
								<td>${webSiteMap.serviceSwitch.alipay}</td>
							</tr>
							<tr class="odd">
								<td>易宝支付</td>
								<td>${webSiteMap.serviceSwitch.yibaopay}</td>
							</tr>
							<tr>
								<td>微信支付</td>
								<td>${webSiteMap.serviceSwitch.weixinpay}</td>
							</tr>
							<tr class="odd">
								<td>促销卡</td>
								<td>${webSiteMap.serviceSwitch.cardServer}</td>
							</tr>
							<tr>
								<td>直播</td>
								<td>${webSiteMap.serviceSwitch.live}</td>
							</tr>
							<tr>
								<td>首页直播</td>
								<td>${webSiteMap.serviceSwitch.indexLive}</td>
							</tr>
							<tr>
								<td>套餐</td>
								<td>${webSiteMap.serviceSwitch.PackageSwitch}</td>
							</tr>
							<tr>
								<td>分销</td>
								<td>${webSiteMap.serviceSwitch.invite}</td>
							</tr>
							<tr>
								<td>会员</td>
								<td>${webSiteMap.serviceSwitch.member}</td>
							</tr>
							<tr>
								<td>账户</td>
								<td>${webSiteMap.serviceSwitch.account}</td>
							</tr>
							<tr>
								<td>充值卡</td>
								<td>${webSiteMap.serviceSwitch.recharge}</td>
							</tr>
							</tbody>
						</c:if>
						<c:if test="${type=='emailConfigure'}">
							<tbody id="tabS_emailConfigure" align="center">
							<tr class="odd">
								<td>邮件传输协议（SMTP）</td>
								<td>${webSiteMap.emailConfigure.SMTP}</td>
							</tr>
							<tr>
								<td>邮件账号</td>
								<td>${webSiteMap.emailConfigure.username}</td>
							</tr>
							<tr class="odd">
								<td>邮件密码</td>
								<td>${webSiteMap.emailConfigure.password}</td>
							</tr>
							</tbody>
						</c:if>
						<c:if test="${type=='message'}">
							<tbody id="tabS_emailConfigure" align="center">
							<tr class="odd">
								<td>注册发送信息</td>
								<td>
									内容：${webSiteMap.message.register.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.register.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="">
								<td>第三方第一次登陆发送信息</td>
								<td>
									内容：${webSiteMap.message.outsideRegister.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.outsideRegister.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="odd">
								<td>后台开通订单</td>
								<td>
									内容：${webSiteMap.message.auditingOrder.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.auditingOrder.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="">
								<td>后台取消订单</td>
								<td>
									内容：${webSiteMap.message.cancleOrder.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.cancleOrder.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="odd">
								<td>后台恢复订单</td>
								<td>
									内容：${webSiteMap.message.recoveryOrder.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.recoveryOrder.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="">
								<td>后台订单退款</td>
								<td>
									内容：${webSiteMap.message.refundOrder.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.refundOrder.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="odd">
								<td>后台订单详情延期</td>
								<td>
									内容：${webSiteMap.message.delayOrder.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.delayOrder.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="">
								<td>后台订单详情课程关闭</td>
								<td>
									内容：${webSiteMap.message.closeCourseOrder.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.closeCourseOrder.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="odd">
								<td>后台赠送优惠券</td>
								<td>
									内容：${webSiteMap.message.giveCouponCode.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.giveCouponCode.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="">
								<td>后台问答采纳最佳答案</td>
								<td>
									内容：${webSiteMap.message.adminQuestionAccept.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.adminQuestionAccept.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="odd">
								<td>前台问答采纳最佳答案</td>
								<td>
									内容：${webSiteMap.message.frontQuestionAccept.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.frontQuestionAccept.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="">
								<td>订单支付成功</td>
								<td>
									内容：${webSiteMap.message.paySuccess.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.paySuccess.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="odd">
								<td>后台赠送课程</td>
								<td>
									内容：${webSiteMap.message.giveCourse.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.giveCourse.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="">
								<td>注册短信模板</td>
								<td>
									内容：${webSiteMap.message.registerMsg.modelContent}<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.registerMsg.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="odd">
								<td>密码找回短信模板</td>
								<td>
									内容：${webSiteMap.message.recoverySms.modelContent}
									<br/>
									通知方式：
									<c:forEach items="${webSiteMap.message.recoverySms.sendType}" var="sendType">
										<c:if test="${sendType=='letter'}">
											站内信&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='email'}">
											邮件&nbsp;&nbsp;
										</c:if>
										<c:if test="${sendType=='mobile'}">
											手机短信&nbsp;&nbsp;
										</c:if>
									</c:forEach>
								</td>
							</tr>
							</tbody>
						</c:if>
					</form>
				</table>
				<div class="layui-form-item">
					<div class="mt20 mb10 tac">
						<input class="layui-btn layui-btn-small layui-btn-danger" type="button" value="修改" onclick="submit(-1)">
					</div>
				</div>

			</div>
		</div>
	</div>
</fieldset>
</body>
</html>