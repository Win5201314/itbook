<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>我的资料</title>
<script type="text/javascript" src="${ctx}/static/common/jcrop/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="${ctx}/static/inxweb/user/user.js"></script>
<script type="text/javascript">

	var oldEmail="${user.email}";
	var oldMobile="${user.mobile}";
	var oldUserName="${user.userName}";
$(function(){
	showTab('${index}');//选项控制显示
});
</script>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div class="">
					<section class="u-r-tit-all">
						<h2 class="unFw">
							<span class="f-fH current">
								<c:if test="${index==0}">我的信息</c:if>
								<c:if test="${index==1}">密码设置</c:if>
							</span>
						</h2>
					</section>
					<div class="u-r-all-box" id="p_tCont">
						<!--修改头像，开始-->
						<div class="u-account-box">
							<div class="clearfix">
								<section class="fl u-acc-l">
									<%--<section class="ukindeditor of">
                                        <section class="clearfix">
                                            <!--个人头像上传控件-->
                                            <section>
                                                <input id="fileupload" type="button" width="133" value="选择头像" height="45" class="pa" />
                                            </section>
                                            <!--个人头像上传控件-->
                                            <!--个人头像裁切控件-->
                                            <div class="fl jc-demo-box pr mt40">
                                                <c:choose>
                                                    <c:when test="${user.picImg!=null && user.picImg!=''}">
                                                        <img src="${staticServer}${user.picImg}" width="100%" height="100%" alt="头像加载中..." class="dis pictureWrap" id="picture" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="${ctx}/static/inxweb/img/avatar-boy.gif" width="100%" height="100%" alt="头像加载中..." class="dis pictureWrap"
                                                             id="picture" />
                                                    </c:otherwise>
                                                </c:choose>
                                                <div id="preview-pane" class="preview-pane1">
                                                    <div class="preview-container">
                                                        <c:choose>
                                                            <c:when test="${user.picImg!=null && user.picImg!=''}">
                                                                <img src="${staticServer}${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <p class="c-999">大尺寸个人头像，大小<br />180x180像素</p>
                                                </div>
                                                <div id="preview-pane" class="preview-pane2">
                                                    <div class="preview-container" style="width: 80px; height: 80px; margin: 0 auto;">
                                                        <c:choose>
                                                            <c:when test="${user.picImg!=null && user.picImg!=''}">
                                                                <img src="${staticServer}${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <p class="c-999">中尺寸个人头像，80x80像素</p>
                                                </div>
                                                <div id="preview-pane" class="preview-pane3">
                                                    <div class="preview-container" style="width: 50px; height: 50px;">
                                                        <c:choose>
                                                            <c:when test="${user.picImg!=null && user.picImg!=''}">
                                                                <img src="${staticServer}${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <p class="c-999">小尺寸个人头像，50x50像素</p>
                                                </div>
                                            </div>
                                            <!--个人头像裁切控件-->
                                            <div class="fl ml30 uploadWrap mt40">
                                                <p class="c-green">您上传的图片将会自动生成三种尺寸的清晰头像，请注意小尺寸的头像是否清晰。</p>
                                            </div>
                                            <section class="clear"></section>
                                            <div class="uploadBc of">
                                                <div class="tac">
                                                    <span id="save_message">

                                                    </span>
                                                </div>
                                                &lt;%&ndash;<div class="ml50 mt20 pl50">
                                                    <a href="javascript:void(0)" title="" class="comm-btn c-btn-7" onclick="updateImg(${user.userId})">保 存</a>
                                                </div>&ndash;%&gt;
                                            </div>
                                        </section>
                                    </section>--%>
									<div class="tac ur-titpic">
										<span class="u-r-t-picin">
											<img  src="${ctx}${not empty user.picImg?user.picImg:"/static/inxweb/img/avatar-boy.gif"}" >
										</span>
									</div>
									<div class="tac ur-titbtn">
										<a class="" href="javascript:void(0)" onclick="dialog('修改头像','',7)">修改头像</a>
									</div>
								</section>
								<div class="fl u-acc-bottom">
									<!-- /修改个人头像 -->
									<input type="button" class="commBtn bgGreen w80 ml50" id="deleImage" style="display: none">
									<!--修改头像，结束-->
									<form method="post" name="photoForm">
										<input id="photoPath" type="hidden" value="${user.picImg}" />
										<input id="oldPhotoPath" type="hidden" value="${user.picImg}" />
										<input type="hidden" value="1" id="picture_width" />
										<input type="hidden" value="1" id="picture_height" />
										<input type="hidden" value="82" id="txt_top" />
										<input type="hidden" value="73" id="txt_left" />
										<input type="hidden" value="120" id="txt_DropWidth" />
										<input type="hidden" value="120" id="txt_DropHeight" />
										<input type="hidden" id="txt_Zoom" />
									</form>
									<!--修改头像，结束-->
									<form method="post" id="updateForm">
										<input type="hidden" name="user.userId" value="${user.userId}" />
										<input type="hidden" name="user.sex" value="${user.sex}" />
										<ol class="u-account-li tac">
											<li>
												<label class="u-a-title">
													<span class="fsize16 c-999">账 户</span>
												</label>
												<input id="userName" type="text" class="u-a-inpt" name="user.userName" <c:if test="${not empty user.userName}"> readonly="readonly" </c:if> value="${user.userName}" placeholder="">
											</li>
											<li>
												<label class="u-a-title">
													<span class="fsize16 c-999">昵 称</span>
												</label>
												<input id="changeShowName" type="text" class="u-a-inpt" name="user.showName" value="${user.displayName}" placeholder="">
												<!-- <span class="u-a-error"><em class="u-a-cw icon16">&nbsp;</em>请输入正确的昵称</span> -->
											</li>
											<li style="display: none">
												<label class="u-a-title">
													<span class="fsize16 c-999">性 别</span>
												</label>
												<div class="disIb vam">
													<div class="xb-box clearfix tac">
														<a class="mr30" href="javascript:void(0)" onclick="selectSex(1,this)">
															<em class="boy-ioc"> </em>
															<span>男</span>
														</a>
														<a href="javascript:void(0)" onclick="selectSex(2,this)">
															<em class="gil-ioc"> </em>
															<span>女</span>
														</a>
														<script>
															if("${user.sex}"==2){
																$(".gil-ioc").parent().addClass("current");
															}else{
																$(".boy-ioc").parent().addClass("current");
															}
														</script>
													</div>
												</div>

												<%--<input type="radio" name="user.sex" <c:if test="${user.sex==1}">checked="checked"</c:if> value="1"/><span class="vam fsize14 c-666">男</span>
                                                <input type="radio" name="user.sex" <c:if test="${user.sex==2}">checked="checked"</c:if> value="2"/><span class="vam fsize14 c-666">女</span>--%>
												<!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
											</li>
											<li>
												<label class="u-a-title">
													<span class="fsize16 c-999">手机号</span>
												</label>
												<input type="text" class="u-a-inpt" name="user.mobile" id="u-mobile" value="${user.mobile }" placeholder="" <c:if test="${not empty user.mobile}"> readonly="readonly" </c:if> onkeyup="$(this).next().html('');" maxlength="11">
												<c:if test="${empty user.mobile}">
													<span class="u-a-error">请输入手机补全信息</span>
												</c:if>
												<!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
											</li>
											<li>
												<label class="u-a-title">
													<span class="fsize16 c-999">邮 箱</span>
												</label>
												<input type="text" class="u-a-inpt" name="user.email" id="u-email" value="${user.email}" placeholder="" <c:if test="${not empty user.email}">readonly="readonly" </c:if> onkeyup="$(this).next().html('');">
												<c:if test="${empty user.email}">
													<span class="u-a-error">请输入邮箱补全信息</span>
												</c:if>
												<!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
											</li>


											<%--	<li>
                                                    <label class="u-a-title">
                                                        <span class="fsize16 c-999">姓 名</span>
                                                    </label>
                                                    <input type="text" class="u-a-inpt" name="user.userName" value="${user.userName }" placeholder="" >
                                                    <!-- <span class="u-a-error"><em class="u-a-cw icon16">&nbsp;</em>请输入正确的账 号</span> -->
                                                </li>--%>

											<%--<li>
                                                <label class="u-a-title">
                                                    <span class="fsize16 c-999">年 龄</span>
                                                </label>
                                                <select name="user.age">
                                                    <option value="0">0岁</option>
                                                    <c:forEach var="age" begin="1" end="110">
                                                        <option <c:if test="${user.age==age}">selected</c:if> value="${age}">${age}岁</option>
                                                    </c:forEach>
                                                </select>
                                                <!-- <span class="u-a-error"><em class="u-a-cw icon16">&nbsp;</em></span> -->
                                            </li>--%>
										</ol>
									</form>
									<div class="mt10 hLh30 tac fsize14 c-333 f-fM">注：完善信息方便找回密码，账户更安全。</div>
									<section class="u-o-right mt50 tac">
										<a class="cou-kscp big-all-btn" href="javascript:void(0)" onclick="updateUserInfo()">更 新 资 料</a>
									</section>
								</div>
							</div>
						</div>
						<%--
						<div class="u-account-box">

						</div>--%>

						<!--修改密码，开始-->
						<div class="u-account-box undis">
							<form method="post" name="pwdForm" id="pwdForm">
								<input type="hidden" name="user.userId" value="${user.userId}" />
								<ol class="u-account-li tac">
									<li>
										<label class="u-a-title">
											<span class="fsize16 c-999">旧密码</span>
										</label>
										<input type="password" class="u-a-inpt" name="nowPassword" value="" placeholder="" maxlength="16">
										<span class="u-a-error"></span>
									</li>
									<li>
										<label class="u-a-title">
											<span class="fsize16 c-999">新密码</span>
										</label>
										<input type="password" class="u-a-inpt" name="newPassword" placeholder="" maxlength="16">
										<span class="u-a-error"></span>
									</li>
									<li>
										<label class="u-a-title">
											<span class="fsize16 c-999">新密码确认</span>
										</label>
										<input type="password" class="u-a-inpt" name="confirmPwd" value="" placeholder="" maxlength="16">
										<span class="u-a-error"></span>
									</li>
								</ol>
							</form>
							<section class="u-o-right mt50 tac">
								<a href="javascript:void(0)" title="修 改 密 码" class="cou-kscp big-all-btn mr20" onclick="updatePwd();">修 改 密 码</a>
								<%--<a href="javascript:void(0)" title="重 置 密 码" class="cou-kscp big-all-btn" onclick="$('#pwdForm').find('input').val('');">重 置 密 码</a>--%>
							</section>
						</div>
						<!--修改密码，结束-->
					</div>
				</div>
			</section>
			<!-- /我的资料 -->
		</div>
	</article>
	<!-- /右侧内容区 结束 -->
</body>
</html>