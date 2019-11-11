<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>网站配置管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/kindeditor/themes/default/default.css" />
	<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
	<script type="text/javascript">
		var form;
		layui.use(['form', 'laydate'], function () {
			form = layui.form();
			var laydate = layui.laydate;
			form.on('select(filter)', function (data) {
			});
			form.on('select(recovery)', function (data) {
				controllLimit(data.value)
			});
			form.on('checkbox(selectType)', function (data) {
				selectType(data.elem);
				form.render();
			});
		});
		$(document).ready(function() {
			type='${type}';
			$("#"+type).attr("href","javascript:void(0)");
			if(type=='web'){
				initSimpleImageUpload('fileuploadButton','websiteLogo',logoCallback,'','true','160','60');
			}
		});
		function logoCallback(imgUrl){
			$('#subjcetpic').attr("src",'${staticServer}'+imgUrl);
			$("#subjcetpic").show();
			$('#logoImageUrl').val(imgUrl);
		}
		function submit(){
			if(type!=''){
				if(type=='web'){
					if($("#imagesUrl").val()==''){
                        layer.msg("请上传logo", {icon: 5, shift: 6});
						return ;
					}
					$("#searchForm").attr("action","${ctx}/admin/websiteProfile/update");
				}else if(type=='web'){
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
			initSimpleImageUpload('fileupload','online',callback,'','true','200','200');
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

			}
			//$("#addOnlineForm").submit();
		}
		function changeType(type) {
			$("#type").val(type);
			$("#addprofileForm").submit();
		}
	</script>
	<style type="text/css">
		.ke-button-common {
			border-radius: 4px;
			height: 26px;
		}
		.ke-button {
			background: #ff5722 none repeat scroll 0 0;
			color: #fff;
			font-size: 14px;
			line-height: 18px;
		}
	</style>
</head>
<body>
	<form action="${ctx }/admin/websiteProfile/update" method="post" id="addprofileForm" enctype="multipart/form-data" class="layui-form">
		<input type="hidden" name="type" id="type" value="${type}" />
		<div class="numb-box">
			<c:if test="${type=='web' }">
				<fieldset class="layui-elem-field">
					<legend>
						<span>系统管理</span>
						&gt;
						<span>基本配置</span>
					</legend>
					<div class="layui-field-box">
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>网站title(网站头部)</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="title" value="${webSiteMap.web.title}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>网校名称(网站头部)</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="company" value="${webSiteMap.web.company}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>网站作者</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="author" value="${webSiteMap.web.author}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>关键词</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="keywords" value="${webSiteMap.web.keywords}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>描述</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="description" value="${webSiteMap.web.description}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>联系邮箱</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="email" value="${webSiteMap.web.email}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>24小时客服服务热线</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="phone" value="${webSiteMap.web.phone}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>工作时间</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6"  name="workTime" value="${webSiteMap.web.workTime}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>备注</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6"  name="remarks" value="<c:out value="${webSiteMap.web.remarks}"></c:out>" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>版权以及备案号(网站底部)</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="copyright" value="${webSiteMap.web.copyright}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>ico文件</label>
							<div class="layui-input-block">
								<img class="icoimg" alt="" src="${ctx}/favicon.ico?v=<%=Math.random()*100%>">
								<input type="file" value="${webSiteMap.web.ico}" name="icoFile" id="icoFile"/>
								<font color="red">（请美工制作图片的大小标为32*32的ico图片,否则图片会失真）</font>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>设置新logo</label>
							<div class="layui-input-block">
								<img alt="" src="${staticServer}${webSiteMap.web.logo}" id="subjcetpic" class="lo-picimg"/>
								<input type="button" id="fileuploadButton" value="上传" />
								<input type="hidden" name="logo" id="logoImageUrl" value="${webSiteMap.web.logo}" />
								<font color="red">*LOGO链接，支持JPG、PNG格式,前台显示会根据(160*60)样式同比缩放</font>
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>统计代码</label>
							<div class="layui-input-block layui-textarea-block">
								<textarea placeholder="请输入内容" class="layui-textarea" name="censusCodeString">${webSiteMap.web.censusCodeString}</textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>咨询链接</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" name="onlineUrl" value="${webSiteMap.web.onlineUrl}" type="text">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w"><font color="red">*</font>二维码</label>
							<div class="layui-input-block">
								<img src="${staticServer}${webSiteMap.web.onlineImageUrl}" alt="" id="onlinepic" class="ewm-ht-pic"/>
								<input type="hidden" name="onlineImageUrl" id="imageUrl" value="${webSiteMap.web.onlineImageUrl}" />
								<span id="fileQueue" style="margin-top: 30px; border: 0px"></span>
								<span style="border: 0px; padding-top: 2px; padding-left: 2px;">
									<input type="button" value="上传" id="fileupload" /><font color="red">*图片链接，支持JPG、PNG格式，尺寸（200*200像素）小于512kb</font>
								</span>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-danger" type="submit">确定</button>
								<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回</button>
							</div>
						</div>
					</div>
				</fieldset>
			</c:if>
			<c:if test="${type=='inxeduCloud'}">
						<%--<fieldset class="new-field-2 fl"  style="display: none;">
							<legend>
								<span>云视频配置</span>
							</legend>
							<div class="">
								<p>
									<label ><font color="red">*</font>&nbsp;因酷云UserId</label>
									<input type="text" name="UserId" value="${webSiteMap.inxeduVideo.UserId}" style="width: 60%" class="sf" />
									<span class="field_desc"></span>
								</p>
								<p>
									<label ><font color="red">*</font>&nbsp;因酷云SecretKey</label>
									<input type="text" name="SecretKey" value="${webSiteMap.inxeduVideo.SecretKey}" style="width: 60%" class="sf" />
									<span class="field_desc"></span>
								</p>
								<p>
									<label ><font color="red">*</font>&nbsp;因酷云AccessKey</label>
									<input type="text" name="AccessKey" value="${webSiteMap.inxeduVideo.AccessKey}" style="width: 60%" class="sf" />
									<span class="field_desc"></span>
								</p>
							</div>
							<div class="tac">
								<button onclick="changeType('inxeduVideo')" class="layui-btn layui-btn-small" style="float: none;" type="button">保存</button>
							</div>
						</fieldset>--%>
					<fieldset class="layui-elem-field">
						<legend>
							<span>视频云配置</span>
						</legend>
						<div class="layui-field-box">
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w">因酷云UserId</label>
								<div class="layui-input-block">
									<input class="layui-input layui-input-6" name="cloudUserId" value="${webSiteMap.inxeduCloud.UserId}" type="text">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w">因酷云AccessKey</label>
								<div class="layui-input-block">
									<input class="layui-input layui-input-6" name="AccessKey" value="${webSiteMap.inxeduCloud.AccessKey}" type="text">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w">因酷云SecretKey</label>
								<div class="layui-input-block">
									<input class="layui-input layui-input-6" name="SecretKey" value="${webSiteMap.inxeduCloud.SecretKey}" type="text">
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button class="layui-btn  layui-btn-danger" type="button" onclick="changeType('inxeduCloud')">保存</button>
									<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">
										返回
									</button>
								</div>
							</div>
							</div>
						</fieldset>
						<div class="clear"></div>
					</div>
					<div class="clearfix mt20">
						<%--<fieldset class="new-field-2 fl">
							<legend>
								<span>CC视频配置</span>
							</legend>
							<div>
								<p>
									<label ><font color="red">*</font>&nbsp;CC ID</label>
									<input type="text" name="ccappID" value="${webSiteMap.cc.ccappID}" style="width: 60%" class="sf" />
									<span class="field_desc"></span>
								</p>
								<p>
									<label ><font color="red">*</font>&nbsp;CC KEY</label>
									<input type="text" name="ccappKEY" value="${webSiteMap.cc.ccappKEY}" style="width: 60%" class="sf" />
									<span class="field_desc"></span>
								</p>
							</div>
							<div class="tac">
								<button onclick="changeType('cc')" class="layui-btn layui-btn-small" style="float: none;" type="button">保存</button>
							</div>
						</fieldset>--%>
						<%--
						<div class="clear"></div>--%>
					</div>
				</c:if>
		<c:if test="${type=='baijiayun'}">
			<fieldset class="layui-elem-field">
				<legend>
					<span>视频云配置</span>
				</legend>
				<div class="layui-field-box">
					<div class="layui-form-item">
						<label class="layui-form-label layui-form-label-w">百家云Partner_ID</label>
						<div class="layui-input-block">
							<input class="layui-input layui-input-6" name="PartnerId" value="${webSiteMap.baijiayun.PartnerId}" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label layui-form-label-w">百家云Partner_Key</label>
						<div class="layui-input-block">
							<input class="layui-input layui-input-6" name="PartnerKey" value="${webSiteMap.baijiayun.PartnerKey}" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label layui-form-label-w">百家云Secret_Key</label>
						<div class="layui-input-block">
							<input class="layui-input layui-input-6" name="SecretKey" value="${webSiteMap.baijiayun.SecretKey}" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn  layui-btn-danger" type="button" onclick="changeType('baijiayun')">保存</button>
							<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">
								返回
							</button>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="clear"></div>
			</div>
			<div class="clearfix mt20">
			</div>
		</c:if>

		<c:if test="${type=='genseeLive'}">
			<fieldset class="layui-elem-field">
				<legend>
					<span>展视互动配置</span>
				</legend>
				<div class="layui-field-box">
					<div class="layui-form-item">
						<label class="layui-form-label layui-form-label-w">直播站点域名</label>
						<div class="layui-input-block">
							<input class="layui-input layui-input-6" name="siteDomain" value="${webSiteMap.genseeLive.siteDomain}" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label layui-form-label-w">账号</label>
						<div class="layui-input-block">
							<input class="layui-input layui-input-6" name="loginName" value="${webSiteMap.genseeLive.loginName}" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label layui-form-label-w">密码</label>
						<div class="layui-input-block">
							<input class="layui-input layui-input-6" name="password" value="${webSiteMap.genseeLive.password}" type="text">
							<%--(密码可加密：加密方式MD5【32位小写】或者SHA-1【40位SHA1小写】)--%>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn  layui-btn-danger" type="button" onclick="changeType('genseeLive')">保存</button>
							<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">
								返回
							</button>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="clear"></div>
			</div>
			<div class="clearfix mt20">
			</div>
		</c:if>
			<c:if test="${type=='sms'}">
				<div class="clearfix">
					<fieldset class="new-field-2 fl">
						<legend>
							<span>短信配置</span>
						</legend>
						<div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w ">短信提供商</label>
								<div class="layui-input-inline">
									<select id="smstype" name="smstype">
										<option value="tengXunSms">腾讯云短信</option>
										<option value="other">其他</option>
									</select>
									<script>
										$("#smstype").val('${webSiteMap.sms.smstype}');
									</script>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w ">腾讯云sdkappid</label>
								<div class="layui-input-block">
									<input type="text" name="sdkappid" value="${webSiteMap.sms.sdkappid}" class="layui-input layui-input-6" />

								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w ">腾讯云strAppkey</label>
								<div class="layui-input-block">
									<input type="text" name="strAppkey" value="${webSiteMap.sms.strAppkey}" class="layui-input layui-input-6" />

								</div>
							</div>

						</div>
						<div class="layui-form-item">

							<div class="layui-input-block">
								<button onclick="changeType('sms')" class="layui-btn layui-btn-small layui-btn-danger" type="button">保存</button>
							</div>
						</div>

					</fieldset>
					<fieldset class="new-field-2 fr">
						<legend>
							<span>邮箱配置</span>
						</legend>
						<div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w ">邮件传输协议(SMTP)</label>
								<div class="layui-input-block">
									<input type="text" name="SMTP" value="${webSiteMap.emailConfigure.SMTP}"  class="layui-input layui-input-6" />

								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w ">邮件账号</label>
								<div class="layui-input-block">
									<input type="text" name="username" value="${webSiteMap.emailConfigure.username}" class="layui-input layui-input-6" />

								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label layui-form-label-w ">邮件密码</label>
								<div class="layui-input-block">
									<input type="text" name="password" value="${webSiteMap.emailConfigure.password}" class="layui-input layui-input-6" />

								</div>
							</div>

						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button onclick="changeType('emailConfigure')" class="layui-btn layui-btn-small layui-btn-danger"  type="button">保存</button>

							</div>
						</div>
					</fieldset>
					<div class="clear"></div>
				</div>
				<div class="mt20">
					<fieldset class="new-field-3">
						<legend>
							<span>消息配置</span>
						</legend>
						<table class="layui-table" lay-skin="line">
							<tbody>
							<tr class="odd">
								<td width="30%">消息内容（<font color="red vam ml10">动态参数用"{1}"代替，</font>）</td>
								<td width="30%"></td>
								<td width="40%"></td>
							</tr>
							<tr>
								<td width="30%" align="center"><font color="red">*</font>&nbsp;注册成功发送信息：<br/>(<font color="red vam ml10">注册成功，第三方登录注册，第三方登录绑定后提醒，{1}对应的是网站名称</font>)</td>
								<td id="register" width="30%">
									<input type="checkbox" name="register.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="register.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="register.sendType"  class="msg"  onclick="selectType(this)"  lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="registerSign" name="register.sign">
											<option >请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px"id="registerTemplate" name="register.template">
										<option >请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script>
										<c:forEach items="${webSiteMap.message.register.sendType}" var="sendType">
										$("#register input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#registerSign").val("${webSiteMap.message.register.sign}");
										$("#registerTemplate").val("${webSiteMap.message.register.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="register.modelContent">${webSiteMap.message.register.modelContent}</textarea></td>

							</tr>
							<tr class="odd" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;第三方第一次登陆发送信息：<br/>(<font color="red vam ml10">登陆成功提醒，第三方登录用户没有邮箱和手机号码，只能发送站内信，{1}对应的是网站名称</font>)</td>
								<td id="outsideRegister" width="30%">
									<input type="checkbox" name="outsideRegister.sendType" class="msg"  value="letter"/>站内信
										<%--<input type="checkbox" name="register.sendType" class="msg"  value="email"/>邮件
                                        <input type="checkbox" name="register.sendType" class="msg"  value="mobile"/>手机短信--%>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.outsideRegister.sendType}" var="sendType">
										$("#outsideRegister input[value='${sendType}']").attr('checked',true);
										</c:forEach>
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="outsideRegister.modelContent">${webSiteMap.message.outsideRegister.modelContent}</textarea></td>
							</tr>
							<tr class="" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台开通订单：<br/>(<font color="red vam ml10">开通订单通知学员，{1}对应订单号</font>)</td>
								<td id="auditingOrder" width="30%;">
									<input type="checkbox" name="auditingOrder.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="auditingOrder.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="auditingOrder.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType"value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="auditingOrderSign" name="auditingOrder.sign">
											<option >请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px"id="auditingOrderTemplate" name="auditingOrder.template">
										<option >请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.auditingOrder.sendType}" var="sendType">
										$("#auditingOrder input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#auditingOrderSign").val("${webSiteMap.message.auditingOrder.sign}");
										$("#auditingOrderTemplate").val("${webSiteMap.message.auditingOrder.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="auditingOrder.modelContent">${webSiteMap.message.auditingOrder.modelContent}</textarea></td>
							</tr>
							<tr class="odd" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台取消订单：<br/>(<font color="red vam ml10">后台取消订单通知学员，{1}对应订单号</font>)</td>
								<td width="30%" id="cancleOrder">
									<input type="checkbox" name="cancleOrder.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="cancleOrder.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="cancleOrder.sendType" class="msg" onclick="selectType(this)" lay-filter="selectType"  value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="cancleOrderSign" name="cancleOrder.sign">
											<option >请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="cancleOrderTemplate" name="cancleOrder.template">
										<option >请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.cancleOrder.sendType}" var="sendType">
										$("#cancleOrder input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#cancleOrderSign").val("${webSiteMap.message.cancleOrder.sign}");
										$("#cancleOrderTemplate").val("${webSiteMap.message.cancleOrder.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="cancleOrder.modelContent">${webSiteMap.message.cancleOrder.modelContent}</textarea></td>
							</tr>
							<tr class="" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台恢复订单：<br/>(<font color="red vam ml10">后台恢复订单通知学员，{1}对应订单号</font>)</td>
								<td width="30%" id="recoveryOrder">
									<input type="checkbox" name="recoveryOrder.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="recoveryOrder.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="recoveryOrder.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="recoveryOrderSign" name="recoveryOrder.sign">
											<option>请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="recoveryOrderTemplate" name="recoveryOrder.template">
										<option>请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.recoveryOrder.sendType}" var="sendType">
										$("#recoveryOrder input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#recoveryOrderSign").val("${webSiteMap.message.recoveryOrder.sign}");
										$("#recoveryOrderTemplate").val("${webSiteMap.message.recoveryOrder.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="recoveryOrder.modelContent">${webSiteMap.message.recoveryOrder.modelContent}</textarea></td>
							</tr>
							<tr class="">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;课程过期：<br/>(<font color="red vam ml10">即将过期的课程提醒，{1}对应课程名</font>)</td>
								<td id="timeOverMsg" width="30%">
									<input type="checkbox" name="timeOverMsg.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="timeOverMsg.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="timeOverMsg.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="timeOverMsgSign" name="timeOverMsg.sign">
											<option>请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="timeOverMsgTemplate" name="timeOverMsg.template">
										<option>请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.timeOverMsg.sendType}" var="sendType">
										$("#timeOverMsg input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#timeOverMsgSign").val("${webSiteMap.message.timeOverMsg.sign}");
										$("#timeOverMsgTemplate").val("${webSiteMap.message.timeOverMsg.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="timeOverMsg.modelContent">${webSiteMap.message.timeOverMsg.modelContent}</textarea></td>
							</tr>
							<tr class="">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;优惠券过期：<br/>(<font color="red vam ml10">即将过期的优惠券提醒，{1}对应优惠券</font>)</td>
								<td id="timeOverCouponCodeMsg" width="30%">
									<input type="checkbox" name="timeOverCouponCodeMsg.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="timeOverCouponCodeMsg.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="timeOverCouponCodeMsg.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="timeOverCouponCodeMsgSign" name="timeOverCouponCodeMsg.sign">
											<option>请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="timeOverCouponCodeMsgTemplate" name="timeOverCouponCodeMsg.template">
										<option>请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.timeOverCouponCodeMsg.sendType}" var="sendType">
										$("#timeOverCouponCodeMsg input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#timeOverCouponCodeMsgSign").val("${webSiteMap.message.timeOverCouponCodeMsg.sign}");
										$("#timeOverCouponCodeMsgTemplate").val("${webSiteMap.message.timeOverCouponCodeMsg.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="timeOverCouponCodeMsg.modelContent">${webSiteMap.message.timeOverCouponCodeMsg.modelContent}</textarea></td>
							</tr>
							<tr class="odd">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台订单退款：<br/>(<font color="red vam ml10">后台订单退款操作通知学员,{1}对应订单号</font>)</td>
								<td id="refundOrder" width="30%">
									<input type="checkbox" name="refundOrder.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="refundOrder.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="refundOrder.sendType" class="msg" onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="refundOrderSign" name="refundOrder.sign">
											<option>请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="refundOrderTemplate" name="refundOrder.template">
										<option>请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.refundOrder.sendType}" var="sendType">
										$("#refundOrder input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#refundOrderSign").val("${webSiteMap.message.refundOrder.sign}");
										$("#refundOrderTemplate").val("${webSiteMap.message.refundOrder.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="refundOrder.modelContent">${webSiteMap.message.refundOrder.modelContent}</textarea></td>

							</tr>
							<tr class="" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台订单详情延期：<br/>(<font color="red vam ml10">后台订单详情延期通知学员，{1}对应订单链接</font>)</td>
								<td width="30%" id="delayOrder">
									<input type="checkbox" name="delayOrder.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="delayOrder.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="delayOrder.sendType" class="msg" onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="delayOrderSign"name="delayOrder.sign">
											<option>请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="delayOrderTemplate" name="delayOrder.template">
										<option>请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.delayOrder.sendType}" var="sendType">
										$("#delayOrder input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#delayOrderSign").val("${webSiteMap.message.delayOrder.sign}");
										$("#delayOrderTemplate").val("${webSiteMap.message.delayOrder.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="delayOrder.modelContent">${webSiteMap.message.delayOrder.modelContent}</textarea></td>
							</tr>
							<tr class="odd" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台订单详情课程关闭：<br/>(<font color="red vam ml10">后台订单详情课程关闭通知学员，{1}对应课程连接</font>)</td>
								<td id="closeCourseOrder" width="30%">
									<input type="checkbox" name="closeCourseOrder.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="closeCourseOrder.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="closeCourseOrder.sendType" class="msg" onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="closeCourseOrderSign" name="closeCourseOrder.sign">
											<option>请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="closeCourseOrderTemplate" name="closeCourseOrder.template">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.closeCourseOrder.sendType}" var="sendType">
										$("#closeCourseOrder input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#closeCourseOrderSign").val("${webSiteMap.message.closeCourseOrder.sign}");
										$("#closeCourseOrderTemplate").val("${webSiteMap.message.closeCourseOrder.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="closeCourseOrder.modelContent">${webSiteMap.message.closeCourseOrder.modelContent}</textarea></td>
							</tr>
							<tr class="">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台赠送优惠券：<br/>(<font color="red vam ml10">后台赠送优惠券通知学员，{1}对应优惠卷编码</font>)</td>
								<td id="giveCouponCode" width="30%">
									<input type="checkbox" name="giveCouponCode.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="giveCouponCode.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="giveCouponCode.sendType" class="msg" onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="giveCouponCodeSign" name="giveCouponCode.sign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select id="giveCouponCodeTemplate" style="margin-left: 4px" name="giveCouponCode.template">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.giveCouponCode.sendType}" var="sendType">
										$("#giveCouponCode input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#giveCouponCodeSign").val("${webSiteMap.message.giveCouponCode.sign}");
										$("#giveCouponCodeTemplate").val("${webSiteMap.message.giveCouponCode.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="giveCouponCode.modelContent">${webSiteMap.message.giveCouponCode.modelContent}</textarea></td>

							</tr>
							<tr class="odd" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台问答采纳最佳答案：<br/>(<font color="red vam ml10">后台问答采纳最佳答案通知学员，{1}对应问答链接</font>)</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="adminQuestionAccept.modelContent">${webSiteMap.message.adminQuestionAccept.modelContent}</textarea></td>
								<td id="adminQuestionAccept" width="30%">
									<input type="checkbox" name="adminQuestionAccept.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="adminQuestionAccept.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="adminQuestionAccept.sendType" class="msg" onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select id="adminQuestionAcceptSign" name="adminQuestionAccept.sign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select id="adminQuestionAcceptTemplate" style="margin-left: 4px" name="adminQuestionAccept.template">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.adminQuestionAccept.sendType}" var="sendType">
										$("#adminQuestionAccept input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#adminQuestionAcceptSign").val("${webSiteMap.message.adminQuestionAccept.sign}");
										$("#adminQuestionAcceptTemplate").val("${webSiteMap.message.adminQuestionAccept.template}");
									</script>
								</td>
							</tr>
							<tr class="" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;前台问答采纳最佳答案：<br/>(<font color="red vam ml10">前台问答采纳最佳答案通知学员，{1}对应问答链接</font>)</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="frontQuestionAccept.modelContent">${webSiteMap.message.frontQuestionAccept.modelContent}</textarea></td>
								<td id="frontQuestionAccept" width="30%">
									<input type="checkbox" name="frontQuestionAccept.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="frontQuestionAccept.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="frontQuestionAccept.sendType" class="msg" onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select name="frontQuestionAccept.sign" id="frontQuestionAcceptSign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" id="frontQuestionAcceptTemplate" name="frontQuestionAccept.template">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.frontQuestionAccept.sendType}" var="sendType">
										$("#frontQuestionAccept input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#frontQuestionAcceptSign").val(${webSiteMap.message.frontQuestionAccept.sign});
										$("#frontQuestionAcceptTemplate").val(${webSiteMap.message.frontQuestionAccept.template});

									</script>
								</td>
							</tr>
							<tr class="odd">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;订单支付成功：<br/>(<font color="red vam ml10">购买课程支付成功通知学员，{1}对应交易请求号</font>)</td>
								<td id="paySuccess" width="30%">
									<input type="checkbox" name="paySuccess.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="paySuccess.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="paySuccess.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select name="paySuccess.sign" id="paySuccessSign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" name="paySuccess.template"id="paySuccessTemplate">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.paySuccess.sendType}" var="sendType">
										$("#paySuccess input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#paySuccessSign").val("${webSiteMap.message.paySuccess.sign}");
										$("#paySuccessTemplate").val("${webSiteMap.message.paySuccess.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="paySuccess.modelContent">${webSiteMap.message.paySuccess.modelContent}</textarea></td>

							</tr>
							<tr class="odd">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;提现处理：<br/>(<font color="red vam ml10">提现请求被处理后通知学员，{1}对应申请提现日期</font>)</td>
								<td id="drawMoney" width="30%">
									<input type="checkbox" name="drawMoney.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="drawMoney.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="drawMoney.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select name="drawMoney.sign" id="drawMoneySign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" name="drawMoney.template"id="drawMoneyTemplate">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.drawMoney.sendType}" var="sendType">
										$("#drawMoney input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#drawMoneySign").val("${webSiteMap.message.drawMoney.sign}");
										$("#drawMoneyTemplate").val("${webSiteMap.message.drawMoney.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="drawMoney.modelContent">${webSiteMap.message.drawMoney.modelContent}</textarea></td>

							</tr>
							<tr class="" style="display: none">
								<td width="30%" align="center"><font color="red">*</font>&nbsp;后台赠送课程：<br/>(<font color="red vam ml10">后台赠送课程通知学员，{1}对应的是课程链接</font>)</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="giveCourse.modelContent">${webSiteMap.message.giveCourse.modelContent}</textarea></td>
								<td id="giveCourse" width="30%">
									<input type="checkbox" name="giveCourse.sendType" class="msg"  value="letter"/>站内信
									<input type="checkbox" name="giveCourse.sendType" class="msg"  value="email"/>邮件
									<input type="checkbox" name="giveCourse.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select name="giveCourse.sign" id="giveCourseSign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" name="giveCourse.template" id="giveCourseTemplate">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.giveCourse.sendType}" var="sendType">
											$("#giveCourse input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#giveCourseSign").val("${webSiteMap.message.giveCourse.sign}");
										$("#giveCourseTemplate").val("${webSiteMap.message.giveCourse.template}")
									</script>
								</td>
							</tr>
							<tr class="odd" >
								<td width="30%" align="center"><font color="red">*</font>&nbsp;注册短信模板：<br/>(<font color="red vam ml10">手机注册发送验证码时的模板，{1}对应短信验证码</font>)</td>
								<td id="registerMsg" width="30%">
										<%--	<input type="checkbox" name="registerMsg.sendType" class="msg"  value="letter"/>站内信
                                            <input type="checkbox" name="registerMsg.sendType" class="msg"  value="email"/>邮件--%>
									<input type="checkbox" name="registerMsg.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select name="registerMsg.sign" id="registerMsgSign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" name="registerMsg.template" id="registerMsgTemplate">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.registerMsg.sendType}" var="sendType">
										$("#registerMsg input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#registerMsgSign").val("${webSiteMap.message.registerMsg.sign}");
										$("#registerMsgTemplate").val("${webSiteMap.message.registerMsg.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="registerMsg.modelContent">${webSiteMap.message.registerMsg.modelContent}</textarea></td>
							</tr>
							<tr class="odd" >
								<td width="30%" align="center"><font color="red">*</font>&nbsp;手机找回密码的短信模板：<br/>(<font color="red vam ml10">手机找回密码发送验证码时的模板，{1}对应短信验证码</font>)</td>
								<td id="recoverySms" width="30%">
										<%--	<input type="checkbox" name="recoverySms.sendType" class="msg"  value="letter"/>站内信
                                            <input type="checkbox" name="recoverySms.sendType" class="msg"  value="email"/>邮件--%>
									<input type="checkbox" name="recoverySms.sendType" class="msg"  onclick="selectType(this)" lay-filter="selectType" value="mobile"/>手机短信
									<div style="display: none">
										签名:
										<select name="recoverySms.sign" id="recoverySmsSign">
											<option value="">请选择签名</option>
											<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
												<c:if test="${qcloudSmsTemplates.type=='sign'&&qcloudSmsTemplates.status==0}">
													<option value="${qcloudSmsTemplates.text}">${qcloudSmsTemplates.text}</option>
												</c:if>
											</c:forEach>
										</select></br>
										模板:<select style="margin-left: 4px" name="recoverySms.template" id="recoverySmsTemplate">
										<option value="">请选择模板</option>
										<c:forEach items="${qcloudSmsTemplates}" var="qcloudSmsTemplates">
											<c:if test="${qcloudSmsTemplates.type=='template'&&qcloudSmsTemplates.status==0}">
												<option value="${qcloudSmsTemplates.otherId}">${qcloudSmsTemplates.text}</option>
											</c:if>
										</c:forEach>
									</select>
									</div>
									<script type="text/javascript">
										<c:forEach items="${webSiteMap.message.recoverySms.sendType}" var="sendType">
										$("#recoverySms input[value='${sendType}']").attr('checked',true);
										</c:forEach>
										$("#recoverySmsSign").val("${webSiteMap.message.recoverySms.sign}");
										$("#recoverySmsTemplate").val("${webSiteMap.message.recoverySms.template}")
									</script>
								</td>
								<td align="" width="40%"><textarea class="layui-textarea" name="recoverySms.modelContent">${webSiteMap.message.recoverySms.modelContent}</textarea></td>
							</tr>
							</tbody>
						</table>
						<div class="layui-form-item">
							<div class="mt20 mb10 tac">
								<button onclick="changeType('message')" class="layui-btn layui-btn-small layui-btn-danger" type="button">保存</button>
							</div>
						</div>
							<%--<p>
                                <label ><font color="red">*</font>&nbsp;注册发送信息</label>
                                <textarea rows="3" cols="60" name="censusCodeString">${webSiteMap.message.register.modelContent}</textarea>
                                <span class="field_desc"></span>
                            </p>--%>
					</fieldset>
				</div>
			</c:if>
			<c:if test="${type=='registerController'}">
				<fieldset class="layui-elem-field">
					<legend>
						<a href="javascript:void(0)">
							<span>系统管理</span>
						</a>
						>
						<span>注册配置</span>
					</legend>
					<div class="layui-field-box">
						<div class="layui-form-item" style="display: none">
							<label class="layui-form-label layui-form-label-w ">邮箱注册开关</label>
							<div class="layui-input-inline">
								<select name="emailRegister" id="emailRegister" onchange="checkField(this.value)">
									<option value="ON">ON</option>
									<option value="OFF">OFF</option>
								</select>
								<script type="">
									$("#emailRegister").val("${webSiteMap.registerController.emailRegister}");
								</script>
							</div>
						</div>
						<div class="layui-form-item" style="display: none">
							<label class="layui-form-label layui-form-label-w ">手机注册开关</label>
							<div class="layui-input-inline">
								<select name="phoneRegister" id="phoneRegister" onchange="checkField(this.value)">
									<option value="ON">ON</option>
									<option value="OFF">OFF</option>
								</select>
								<script type="">
									$("#phoneRegister").val("${webSiteMap.registerController.phoneRegister}");
								</script>
							</div>
						</div>
						<div class="layui-form-item" style="display: none">
							<label class="layui-form-label layui-form-label-w ">邮箱注册验证开关</label>
							<div class="layui-input-inline">
								<select name="emailProving" id="emailProvingController" onchange="controllProving(this.value)">
									<option value="ON">ON</option>
									<option value="OFF">OFF</option>
								</select>
								<script type="">
									$("#emailProvingController").val("${webSiteMap.registerController.emailProving}");
								</script>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w ">手机注册验证开关</label>
							<div class="layui-input-inline">
								<select name="phoneProving" id="phoneProvingController" onchange="controllProving(this.value)">
									<option value="ON">ON</option>
									<option value="OFF">OFF</option>
								</select>
								<script type="">
									$("#phoneProvingController").val("${webSiteMap.registerController.phoneProving}");
								</script>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w ">邮箱密码找回开关</label>
							<div class="layui-input-inline">
								<select name="emailRecovery" id="emailRecoveryController" lay-filter="recovery" onchange="controllLimit(this.value)">
									<option value="ON">ON</option>
									<option value="OFF">OFF</option>
								</select>
								<script type="">
									$("#emailRecoveryController").val("${webSiteMap.registerController.emailRecovery}");
								</script>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w ">手机密码找回开关</label>
							<div class="layui-input-inline">
								<select name="mobileRecovery" id="mobileRecoveryController"lay-filter="recovery" onchange="controllLimit(this.value)">
									<option value="ON">ON</option>
									<option value="OFF">OFF</option>
								</select>
								<script type="">
									$("#mobileRecoveryController").val("${webSiteMap.registerController.mobileRecovery}");
								</script>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">ip注册次数限制</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="ipLimit" id="ipLimitController" onchange="controllLimit(this.value)" value="${webSiteMap.registerController.ipLimit}" type="text">
							</div>
							<div class="layui-form-mid layui-word-aux">(当天内同一IP限制次数)</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">邮箱注册次数限制</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="emailLimit" id="emailLimitController" value="${webSiteMap.registerController.emailLimit}" type="text">
							</div>
							<div class="layui-form-mid layui-word-aux">(当天内同一邮箱限制次数)</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">手机注册次数限制</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="mobileLimit" id="mobileLimitController" value="${webSiteMap.registerController.mobileLimit}" type="text">
							</div>
							<div class="layui-form-mid layui-word-aux">(当天内同一手机限制次数)</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn  layui-btn-danger"  type="submit">保存</button>
								<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回
								</button>
							</div>
						</div>
					</div>
				</fieldset>
			</c:if>
				<script>
					//如果邮箱注册关闭就隐藏邮箱验证按钮并设为OFF
					if ($("#emailRegister").val()=="OFF"){
						$("#emailProving").css("display","none");
						$("#emailProvingController").val("OFF");
					}
					//如果手机注册关闭就隐藏手机验证按钮并设为OFF
					if($("#phoneRegister").val()=="OFF"){
						$("#phoneProving").css("display","none");
						$("#phoneProvingController").val("OFF");
					}
                    //检查邮箱和手机开关（不允许都是关闭状态）
					function checkField(val){
						var e = $("#emailRegister").val();
						var p = $("#phoneRegister").val();
						//不允许是都关闭的状态
						//如果邮箱注册关闭就隐藏邮箱验证按钮并设为OFF
						if (e=="OFF"){
							$("#emailProving").css("display","none");
							$("#emailProvingController").val("OFF");
						}
						//如果手机注册关闭就隐藏手机验证按钮并设为OFF
						if(p=="OFF"){
							$("#phoneProving").css("display","none");
							$("#phoneProvingController").val("OFF");
						}
						//如果手机注册打开就显示手机验证按钮
						if (p=="ON"){
							$("#phoneProving").css("display","block");
						}
						//如果邮箱注册打开就显示邮箱验证按钮
						if(e=="ON"){
							$("#emailProving").css("display","block");
						}
						//邮箱注册和手机注册不能同时关闭
						if (e =='OFF'&& p=='OFF'){
                            layer.msg("手机注册和邮箱注册不能同时关闭", {icon: 5, shift: 6});
							//当两种注册方式被同时关闭时打开邮箱注册,同时把邮箱验证设为可见
							$("#emailRegister").val("ON");
							$("#emailProving").css("display","block");
						}
					}
					//邮箱验证和手机验证只能开一个
					function controllProving(val) {
						if ($("#emailProvingController").val()=="ON" && $("#phoneProvingController").val()=="ON"){
                            layer.msg("手机验证和邮箱验证不能同时打开", {icon: 5, shift: 6});
							$("#phoneProvingController").val("OFF");
						}
					}
					function controllLimit(val) {
						if ($("#ipLimitController").val()==""){
                            layer.msg("ip注册限制次数不能为空", {icon: 5, shift: 6});
							$("#ipLimitController").val("20");
						}
						if ($("#emailLimitController").val()==""){
                            layer.msg("邮箱注册限制次数不能为空", {icon: 5, shift: 6});
							$("#emailLimitController").val("20");
						}
						if ($("#mobileLimitController").val()==""){
                            layer.msg("手机注册限制次数不能为空", {icon: 5, shift: 6});
							$("#mobileLimitController").val("20");
						}
						//邮箱密码找回和手机密码找回不能同时关闭
						if ($("#mobileRecoveryController").val() =='OFF'&& $("#emailRecoveryController").val()=='OFF'){
                            layer.msg("手机密码找回和邮箱密码找回不能同时关闭", {icon: 5, shift: 6});
							$("#emailRecoveryController").val("ON");
							form.render('select');
						}
					}
				</script>
			<c:if test="${type=='WebSwitch'}">
				<fieldset class="layui-elem-field">
					<legend>
						<span>限制登录</span>
					</legend>
					<div class="layui-field-box">

						<label class="layui-form-label layui-form-label-w"><font color="red">*</font>限制开关</label>
						<div class="layui-input-block layui-select-inline">
							<select name="limitLogin" id="limitLogin">
								<option value="ON">ON</option>
								<option value="OFF">OFF</option>
							</select>
							<script type="">
                                $("#limitLogin").val("${webSiteMap.WebSwitch.limitLogin}");
							</script>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">imPath</label>
							<div class="layui-input-block">
								<input class="layui-input layui-input-6" type="text" name="imPath" value="${webSiteMap.WebSwitch.imPath}"/>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn  layui-btn-danger" type="button" onclick="changeType('WebSwitch')">保存</button>
								<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">
									返回
								</button>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="clear"></div>
				</div>
				<div class="clearfix mt20">
				</div>











			</c:if>
			<c:if test="${type=='serviceSwitch'}">
				<fieldset class="layui-elem-field">
					<legend>
						<span>系统管理</span>
						&gt;
						<span>基本配置</span>
					</legend>
					<div class="layui-field-box">
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>腾讯QQ登录</label>
								<div class="layui-input-block layui-select-inline">
									<select name="verifyQQ" id="verifyQQ">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<script type="">
										$("#verifyQQ").val("${webSiteMap.serviceSwitch.verifyQQ}");
									</script>
									<button onclick='showMenu(this,"qqLogin")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w">app_ID</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="qq_app_ID" name="app_ID" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w">app_KEY</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="qq_app_KEY" name="app_KEY" type="text">
													</div>
												</div>
											<%--	<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w">redirect_URI</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="qq_redirect_URI" name="qq_redirect_URI" type="text">
													</div>
												</div>--%>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w">scope</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="qq_scope" name="scope" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w">authorizeURL</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="qq_authorizeURL" name="authorizeURL" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w">accessTokenURL</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="qq_accessTokenURL" name="accessTokenURL" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w">getUserInfoURL</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="getUserInfoURL" name="getUserInfoURL" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w">getOpenIDURL</label>
													<div class="layui-input-block">
														<input  class="layui-input layui-input-6" id="getOpenIDURL" name="getOpenIDURL" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn layui-btn-danger" onclick='updateWebsiteAjax("qqLogin")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>新浪微博登录</label>
								<div class="layui-input-block layui-select-inline">
									<select name="verifySINA" id="verifySINA">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<button onclick='showMenu(this,"sinaLogin")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;App Key</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="client_ID" name="client_ID" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;App Secret</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="client_SERCRET" name="client_SERCRET" type="text">
													</div>
												</div>
												<%--<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;redirect_URI</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="sina_redirect_URI" name="sina_redirect_URI" type="text">
													</div>
												</div>--%>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;baseURL</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="baseURL" name="baseURL" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;accessTokenURL</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="accessTokenURL" name="accessTokenURL" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;authorizeURL</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="authorizeURL" name="authorizeURL" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;rmURL</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="rmURL" name="rmURL" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn layui-btn-danger" onclick='updateWebsiteAjax("sinaLogin")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>微信登录</label>
								<div class="layui-input-block layui-select-inline">
									<select name="verifyWeiXin" id="verifyWeiXin">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<button onclick='showMenu(this,"weixinLogin")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;appid</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="appid" name="appid" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;secret</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="secret" name="secret" type="text">
													</div>
												</div>
											<%--	<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;redirect_uri</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="weixin_redirect_uri" name="weixin_redirect_uri" type="text">
														<p class="fsize12 c-red f-fM hLh20">（微信APP扫描浏览，授权登录）</p>
													</div>
												</div>--%>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>强制授权</label>
													<div class="layui-input-block layui-select-inline" style="width: 48.5%">
															<select name="forceWeChatLogin" id="forceWeChatLogin">
																<option value="ON">ON</option>
																<option value="OFF">OFF</option>
															</select>
															<p class="fsize12 c-red f-fM hLh20">（微信APP扫描浏览，是否强制授权登录）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;wxAppID</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"  id="weixinLogin_appId" name="weixinLogin_appId" type="text">
														<p class="fsize12 c-red f-fM hLh20">（微信公众平台appid）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;wxAppSecret</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"  id="weixinLogin_wxAppSecret" name="weixinLogin_wxAppSecret" type="text">
														<p class="fsize12 c-red f-fM hLh20">（微信公众平台AppSecret）</p>
													</div>
												</div>
												<div class="layui-form-item"  style="display: none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;response_type</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"  id="response_type" name="response_type" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;scope</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="scope" name="scope" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;href</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="href" name="href" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn  layui-btn-danger" onclick='updateWebsiteAjax("weixinLogin")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>第三方登录绑定开关</label>
								<div class="layui-input-block layui-select-inline">
									<select name="thirdLoginBinding" id="thirdLoginBinding">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#thirdLoginBinding").val("${webSiteMap.serviceSwitch.thirdLoginBinding}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>优惠券</label>
								<div class="layui-input-block layui-select-inline">
									<select name="coupon" id="coupon">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#coupon").val("${webSiteMap.serviceSwitch.coupon}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>支付宝支付</label>
								<div class="layui-input-block layui-select-inline">
									<select name="alipay" id="alipay">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#alipay").val("${webSiteMap.serviceSwitch.alipay}");
									</script>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<button onclick='showMenu(this,"alipay")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;合作者身份（PID）</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"  id="alipaypartnerID" name="alipaypartnerID"  type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;安全校验码（key）</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="alipaykey" name="alipaykey" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;商家邮箱</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="sellerEmail" name="sellerEmail" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display:none;">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;商家邮箱</label>
													<div class="layui-input-block">
														<label ><font color="red">*</font>&nbsp;支付宝<!-- 选择(企业或个人，个人支付宝需定制) --></label>
														<input type="radio"  id="status" name="status" value="0" <c:if test="${webSiteMap.alipay.status==0}">checked='checked'</c:if> />
														企业
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn  layui-btn-danger" onclick='updateWebsiteAjax("alipay")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>易宝支付</label>
								<div class="layui-input-block layui-select-inline">
									<select name="yibaopay" id="yibaopay">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#yibaopay").val("${webSiteMap.serviceSwitch.yibaopay}");
									</script>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<button onclick='showMenu(this,"yee")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<div class="layui-form-item">
														<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;p1_MerId</label>
														<div class="layui-input-block">
															<input class="layui-input layui-input-6" id="p1_MerId" name="p1_MerId" type="text">
														</div>
													</div>
													<div class="layui-form-item">
														<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;keyValue</label>
														<div class="layui-input-block">
															<input class="layui-input layui-input-6" id="keyValue" name="keyValue" type="text">
														</div>
													</div>
													<div class="layui-input-block">
														<button class="layui-btn layui-btn-danger" onclick='updateWebsiteAjax("yee")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>微信支付</label>
								<div class="layui-input-block layui-select-inline">
									<select name="weixinpay" id="weixinpay">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#weixinpay").val("${webSiteMap.serviceSwitch.weixinpay}");
									</script>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<button onclick='showMenu(this,"weixin")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;wxAppID</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="wxAppID" name="wxAppID" type="text">
														<p class="fsize12 c-red f-fM hLh20">（微信公众平台appid）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;wxAppSecret</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="wxAppSecret" name="wxAppSecret" type="text">
														<p class="fsize12 c-red f-fM hLh20">（微信公众平台AppSecret）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;wxMchId</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="wxMchId" name="wxMchId" type="text">
														<p class="fsize12 c-red f-fM hLh20">（微信公众平台所对应的商户号）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;wxPayKey</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"id="wxPayKey" name="wxPayKey" type="text">
														<p class="fsize12 c-red f-fM hLh20">（微信公众平台所对应的商户号中的API密钥）</p>
													</div>
												</div>
												<div class="layui-form-item" style="display: none">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;wxToken</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="wxToken" name="wxToken" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;mobilePayKey</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" id="mobilePayKey" name="mobilePayKey" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;encodingAESKey</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"  id="encodingAESKey" name="encodingAESKey" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;mobileAppId</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"  id="mobileAppId" name="mobileAppId" type="text">
													</div>
												</div>
												<div class="layui-form-item" style="display: none">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;mobileMchId</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6"  id="mobileMchId" name="mobileMchId" type="text">
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn layui-btn-danger" onclick='updateWebsiteAjax("weixin")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>促销卡</label>
								<div class="layui-input-block layui-select-inline">
									<select name="cardServer" id="cardServer">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#cardServer").val("${webSiteMap.serviceSwitch.cardServer}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>直播</label>
								<div class="layui-input-block layui-select-inline">
									<select name="live" id="live">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#live").val("${webSiteMap.serviceSwitch.live}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>首页直播</label>
								<div class="layui-input-block layui-select-inline">
									<select name="indexLive" id="indexLive">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#indexLive").val("${webSiteMap.serviceSwitch.indexLive}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>套餐</label>
								<div class="layui-input-block layui-select-inline">
									<select name="PackageSwitch" id="PackageSwitch">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#PackageSwitch").val("${webSiteMap.serviceSwitch.PackageSwitch}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>分销</label>
								<div class="layui-input-block layui-select-inline">
									<select  name="invite" id="invite">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#invite").val("${webSiteMap.serviceSwitch.invite}");
									</script>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<button onclick='showMenu(this,"invite")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;分销返现金额</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" name="cashback" id="cashback" type="text">
														<p class="fsize12 c-red f-fM hLh20">（邀请成功后返现给用户）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;课程消费返现比例</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" name="coursePercent" id="coursePercent" type="text">
														<p class="fsize12 c-red f-fM hLh20">（邀请用户消费返现给用户金额的比例）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;套餐消费返现比例</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" name="packagePercent" id="packagePercent" type="text">
														<p class="fsize12 c-red f-fM hLh20">（邀请用户消费返现给用户金额的比例）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;直播消费返现比例</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" name="livePercent" id="livePercent" type="text">
														<p class="fsize12 c-red f-fM hLh20">（邀请用户消费返现给用户金额的比例）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;会员消费返现比例</label>
													<div class="layui-input-block">
														<input class="layui-input layui-input-6" name="memberPercent" id="memberPercent" type="text">
														<p class="fsize12 c-red f-fM hLh20">（邀请用户消费返现给用户金额的比例）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;分销金额是否提现</label>
													<div class="layui-input-block layui-select-inline" style="width: 48.5%">
														<select name="withdrawCash" id="withdrawCash">
															<option value="ON">ON</option>
															<option value="OFF">OFF</option>
														</select>
														<p class="fsize12 c-red f-fM hLh20">（分销返现金额是否允许提现到银行卡）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn layui-btn-danger"  onclick='updateWebsiteAjax("invite")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>会员</label>
								<div class="layui-input-block layui-select-inline">
									<select name="member" id="member">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#member").val("${webSiteMap.serviceSwitch.member}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>账户</label>
								<div class="layui-input-block layui-select-inline">
									<select name="account" id="account">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#account").val("${webSiteMap.serviceSwitch.account}");
									</script>
								</div>
							</div>
							<div class="fl">
								<div class="layui-input-line">
									<button onclick='showMenu(this,"withdrawals")' class="layui-btn layui-btn-small layui-btn-danger" type="button">修改配置</button>
									<div style="display:none;">
										<div class="numb-box">
											<form class="layui-form" action="">
												<div class="layui-form-item">
													<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;是否提现</label>
													<div class="layui-input-block layui-select-inline" style="width: 48.5%">
														<select name="withdrawalsSwitch" id="withdrawalsSwitch">
															<option value="ON">ON</option>
															<option value="OFF">OFF</option>
														</select>
														<p class="fsize12 c-red f-fM hLh20">（是否允许账户余额提现到银行卡）</p>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn layui-btn-danger"  onclick='updateWebsiteAjax("withdrawals")' type="button">确定</button>
														<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="fl">
								<label class="layui-form-label layui-form-label-w"><font color="red">*</font>充值卡</label>
								<div class="layui-input-block layui-select-inline">
									<select name="recharge" id="recharge">
										<option value="ON">ON</option>
										<option value="OFF">OFF</option>
									</select>
									<script type="">
										$("#recharge").val("${webSiteMap.serviceSwitch.recharge}");
									</script>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn  layui-btn-danger" type="submit">确定</button>
								<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回
								</button>
							</div>
						</div>
					</div>
				</fieldset>
			</c:if>

		</div>
			<!-- /tab4 end -->
	</form>
	<script>
		function showMenu(obj,type){
			layer.closeAll(); //疯狂模式，关闭所有层

			 $.ajax({
				 url:baselocation+'/admin/ajax/getWebsiteProfile/'+type,
				 type:'post',
				 dataType:'json',
				 success:function(result){
					 if(result.success==true){
						 var resultmap=result.entity;
						 if(type=="qqLogin"){
							$("#qq_app_ID").val(resultmap.app_ID);
							$("#qq_app_KEY").val(resultmap.app_KEY);
							$("#qq_redirect_URI").val(resultmap.redirect_URI);
							$("#qq_scope").val(resultmap.scope);
							$("#qq_authorizeURL").val(resultmap.authorizeURL);
							$("#qq_accessTokenURL").val(resultmap.accessTokenURL);
							$("#getUserInfoURL").val(resultmap.getUserInfoURL);
							$("#getOpenIDURL").val(resultmap.getOpenIDURL);
						 }
						 if(type=="sinaLogin"){
							$("#client_ID").val(resultmap.client_ID);
							$("#client_SERCRET").val(resultmap.client_SERCRET);
							$("#sina_redirect_URI").val(resultmap.redirect_URI);
							$("#baseURL").val(resultmap.baseURL);
							$("#accessTokenURL").val(resultmap.accessTokenURL);
							$("#authorizeURL").val(resultmap.authorizeURL);
							$("#rmURL").val(resultmap.rmURL);
						 }
						 if(type=="weixinLogin"){
							$("#appid").val(resultmap.appid);
							$("#weixin_redirect_uri").val(resultmap.redirect_uri);
							$("#response_type").val(resultmap.response_type);
							$("#scope").val(resultmap.scope);
							$("#secret").val(resultmap.secret);
							$("#href").val(resultmap.href);
							 $("#forceWeChatLogin").val(resultmap.forceWeChatLogin);
							 $("#weixinLogin_appId").val(resultmap.weixinLogin_appId);
							 $("#weixinLogin_wxAppSecret").val(resultmap.weixinLogin_wxAppSecret);
						 }
						 if(type=="alipay"){
							$("#alipaypartnerID").val(resultmap.alipaypartnerID);
							$("#alipaykey").val(resultmap.alipaykey);
							$("#sellerEmail").val(resultmap.sellerEmail);
							$("#status").prop("checked","true");
						 }
						 if(type=="yee"){
							$("#p1_MerId").val(resultmap.p1_MerId);
							$("#keyValue").val(resultmap.keyValue);
						 }
						 if(type=="weixin"){
							$("#wxMchId").val(resultmap.wxMchId);
							$("#wxAppID").val(resultmap.wxAppID);
							$("#wxToken").val(resultmap.wxToken);
							$("#mobilePayKey").val(resultmap.mobilePayKey);
							$("#wxPayKey").val(resultmap.wxPayKey);
							$("#encodingAESKey").val(resultmap.encodingAESKey);
							$("#wxAppSecret").val(resultmap.wxAppSecret);
							$("#mobileAppId").val(resultmap.mobileAppId);
							$("#mobileMchId").val(resultmap.mobileMchId);
						 }
						 if(type=="invite"){
							$("#cashback").val(resultmap.cashback);
							$("#percent").val(resultmap.percent);
							$("#coursePercent").val(resultmap.coursePercent);
							$("#packagePercent").val(resultmap.packagePercent);
							$("#livePercent").val(resultmap.livePercent);
							$("#memberPercent").val(resultmap.memberPercent);
							$("#withdrawCash").val(resultmap.withdrawCash);
						 }
						 if(type=="withdrawals"){
							$("#withdrawalsSwitch").val(resultmap.withdrawalsSwitch);
						 }
						 form.render('select'); //刷新select选择框渲染
						 //通过这种方式弹出的层，每当它被选择，就会置顶。
						 layer.open({
							 title:'修改配置',
							 type: 1,
							 shade: false,
							 area: '700px',
							 maxmin: false,
							 content: $(obj).next()
						 });

					 }
				 },
				 error:function(error){
                     layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
				 }
			 });
		}

		function updateWebsiteAjax(type)
		{
			var data="";
			if(type=="qqLogin"){
				data+="app_ID="+$("#qq_app_ID").val()
				+"&app_KEY="+$("#qq_app_KEY").val()
				+"&redirect_URI="+$("#qq_redirect_URI").val()
				+"&scope="+$("#qq_scope").val()
				+"&authorizeURL="+$("#qq_authorizeURL").val()
				+"&accessTokenURL="+$("#qq_accessTokenURL").val()
				+"&getUserInfoURL="+$("#getUserInfoURL").val()
				+"&getOpenIDURL="+$("#getOpenIDURL").val();
			}
			if(type=="invite"){

				if (!isNumber($("input[name='cashback']").val())||$("input[name='cashback']").val()<0){
					msgshow("分销返现金额必须为正整数","false","1000");
					return;
				}
				if (!isNumber($("input[name='coursePercent']").val())||$("input[name='cashback']").val()<0){
					msgshow("课程消费返现比例必须为正整数","false","1000");
					return;
				}
				if (!isNumber($("input[name='packagePercent']").val())||$("input[name='cashback']").val()<0){
					msgshow("套餐消费返现比例必须为正整数","false","1000");
					return;
				}
				if (!isNumber($("input[name='livePercent']").val())||$("input[name='cashback']").val()<0){
					msgshow("直播消费返现比例必须为正整数","false","1000");
					return;
				}
				if (!isNumber($("input[name='memberPercent']").val())||$("input[name='cashback']").val()<0){
					msgshow("会员消费返现比例必须为正整数","false","1000");
					return;
				}
			}

			if(type=="sinaLogin"){
				data+="client_ID="+$("#client_ID").val()
				+"&client_SERCRET="+$("#client_SERCRET").val()
				+"&redirect_URI="+$("#sina_redirect_URI").val()
				+"&baseURL="+$("#baseURL").val()
				+"&accessTokenURL="+$("#accessTokenURL").val()
				+"&authorizeURL="+$("#authorizeURL").val()
				+"&rmURL="+$("#rmURL").val();
			}
			if(type=="weixinLogin"){
				data+="appid="+$("#appid").val()
				+"&redirect_uri="+$("#weixin_redirect_uri").val()
				+"&response_type="+$("#response_type").val()
				+"&scope="+$("#scope").val()
				+"&secret="+$("#secret").val()
				+"&href="+$("#href").val()
				+"&forceWeChatLogin="+$("#forceWeChatLogin").val()
				+"&weixinLogin_appId="+$("#weixinLogin_appId").val()
				+"&weixinLogin_wxAppSecret="+$("#weixinLogin_wxAppSecret").val();
			}
			if(type=="alipay"){
				data+="sellerEmail="+$("#sellerEmail").val()
				+"&alipaykey="+$("#alipaykey").val()
				+"&alipaypartnerID="+$("#alipaypartnerID").val()
				+"&status="+$("#status").val();
			}
			if(type=="yee"){
				data+="p1_MerId="+$("#p1_MerId").val()
				+"&keyValue="+$("#keyValue").val()
				+"&response_type="+$("#response_type").val();
			}
			if(type=="weixin"){
				data+="wxMchId="+$("#wxMchId").val()
				+"&wxAppID="+$("#wxAppID").val()
				+"&wxToken="+$("#wxToken").val()
				+"&mobilePayKey="+$("#mobilePayKey").val()
				+"&wxPayKey="+$("#wxPayKey").val()
				+"&encodingAESKey="+$("#encodingAESKey").val()
				+"&wxAppSecret="+$("#wxAppSecret").val()
				+"&mobileAppId="+$("#mobileAppId").val()
				+"&mobileMchId="+$("#mobileMchId").val();
			}
			if(type=="invite"){
				data+="cashback="+$("#cashback").val()
				+"&coursePercent="+$("#coursePercent").val()
				+"&packagePercent="+$("#packagePercent").val()
				+"&livePercent="+$("#livePercent").val()
				+"&memberPercent="+$("#memberPercent").val()
				+"&withdrawCash="+$("#withdrawCash").val();
			}
			if(type=="withdrawals"){
				data+="withdrawalsSwitch="+$("#withdrawalsSwitch").val();
			}
			$.ajax({
				url:baselocation+'/admin/ajax/updateWebsiteProfile/'+type+"?"+data,
				type:'post',
				dataType:'json',
				success:function(result){
					if(result.success==true){
                        layer.msg("修改成功", {icon: 1, shift: 6});
						layer.closeAll(); //疯狂模式，关闭所有层
					}else{
                        layer.msg("系统异常", {icon: 5, shift: 6});
					}
				},
				error:function(error){
                    layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
				}
			});
		}

		function selectType(obj) {
			if ($(obj).attr('checked')=='checked') {
				$(obj).next().next().show();
			}
			if ($(obj).attr('checked')!='checked') {
				$(obj).next().next().hide();
			}
		}

	</script>
</body>
</html>
