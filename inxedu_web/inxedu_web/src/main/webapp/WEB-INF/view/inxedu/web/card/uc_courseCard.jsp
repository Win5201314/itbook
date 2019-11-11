<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>我的课程卡</title>
	<script type="text/javascript">
		$(function(){
			cCardFun();
		});
		function cCardFun(){
			$(".c-caed-body>tr>td>em").each(function() {
				var _this = $(this),
						_cont = _this.siblings(".c-csrd-m-wrap");
				_this.click(function() {
					if(_cont.is(":hidden")){
						_cont.show();
						_btn.addClass("cou-arrow-up");
					}else{
						_cont.hide();
						_btn.removeClass("cou-arrow-up");
					}
				});

			})
		}

		function checkData(){
			var message="";
			if($("#cardCourseCode").val()==''){
				message+="卡号不能为空！\n";
			}
			if($("#cardCoursePassword").val()==''){
				message+="密码不能为空！\n";
			}
			return message;
		}

		function courseCardActive(){
			var msg=checkData();
			if(msg!=''){
				$('.dialog-shadow').remove();
				dialog('激活提示',msg,1);
			}else{
				$.ajax({
					url : baselocation + "/course/activationcard/1",
					data : {
						"cardCode.type":1,
						"cardCode.cardCode" : $.trim($("#cardCourseCode").val()),
						"cardCode.cardCodePassword" : $.trim($("#cardCoursePassword").val())
					},
					type : "post",
					dataType : "json",
					cache : false,
					async : false,
					success : function(result) {
						var msg="";
						if(result.entity=='passwordError'){
							msg="卡号或密码错误，请确认，谢谢！";
						}else if(result.entity=='dontActivate'){
							msg="该卡未被激活，请联系客服进行处理！谢谢";
						}else if(result.entity=='alreadyUse'){
							msg="该卡已被使用，不能再进行激活，请确认！谢谢";
						}else if(result.entity=='overDue'){
							msg="该卡已过期，不能进行激活，请确认！谢谢";
						}else if(result.entity=='close'){
							msg="该卡已作废，不能进行激活，请确认！谢谢";
						}else if (result.entity=='typeError'){
							msg="卡号或密码错误，请确认，谢谢！";
						}else if(result.entity=='dateError'){
							msg="该卡不在有效期内，请确认！谢谢";
						}else{
							msg="";
							$('.dialog-shadow').remove();
							dialog('激活提示',"您的课程卡已激活成功!",2,'/uc/index');
						}
						if(msg!=""){
							$('.dialog-shadow').remove();
							dialog('激活提示',msg,1);
						}
					},
					error : function(error) {
						$('.dialog-shadow').remove();
						dialog('激活提示',"您的课程卡激活发生异常，请及时联系客服人员进行处理，谢谢！",1);
					}
				});
			}
		}


	</script>
</head>
<body>
<article class="col-80 fl">
	<div class="u-r-cont">
		<section>
			<div>
				<section class="c-infor-tabTitle c-tab-title">
					<a href="javascript: void(0)" title="Wo的订单" class="c-tab-title">Wo的课程卡</a>
					<a href="javascript:void(0)" title="已完成" class="current">课程卡管理</a>
				</section>
			</div>
			<section >
				<div class=" p-cou-card-pad">
					<section class="mt40 pb20 line2">
						<h4 class="hLh30">
							<span class="c-333 fsize14 fbold">有新课程卡？</span>
							<a class="ml20 jihu-btn" title="马上激活" id="activateCardCourse" href="javascript:void(0)" onclick="dialog('课程卡激活','',6);">马上激活</a>
						</h4>
					</section>
					<section class="mt15">
						<h5 class="c-333 fsize14 fbold">课程卡有什么用途？</h5>
						<p class="c-666 mt5">使用课程卡激活后可以去学习与课程卡金额相应的课程。</p>
					</section>
					<section class="mt30 mb30">
						<h5 class="c-333 fsize14 line2 pb10 fbold">我的课程卡列表</h5>
						<c:if test="${userCardCodeList==null}">
							<!-- /无数据提示 开始-->
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">你还没有课程卡记录...</span>
							</section>
							<!-- /无数据提示 结束-->
						</c:if>
						<c:if test="${not empty userCardCodeList}">
							<!-- pc端开始 -->
							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="u-c-card tab-integral mt10 p-card-table">
								<thead>
								<tr>
									<th>编号</th>
									<th>卡号</th>
									<th width="28%">关联课程</th>
									<th>使用时间</th>
									<th>卡状态</th>
								</tr>
								</thead>
								<tbody class="tac">
								<c:forEach var="userCard" items="${userCardCodeList}">
									<tr>
										<td>${userCard.id}</td>
										<td>${userCard.cardCode}（课程卡）</td>
										<td>${userCard.courseName}</td>
										<td><fmt:formatDate value="${userCard.useTime}" pattern="yyyy/MM/dd  HH:mm" /></td>
										<td>已使用</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<!-- pc端结束 -->
							<!-- 移动端开始 -->
							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="u-c-card tab-integral mt10 m-card-table">
								<thead>
								<tr>
									<th>编号</th>
									<th style="width: 60%;">卡号</th>
									<th>卡状态</th>
								</tr>
								</thead>
								<tbody class="tac c-caed-body">
								<c:forEach var="userCard" items="${userCardCodeList}">
									<tr>
										<td class="pr">${userCard.id}<em class="cou-arrow-down c-arrow"></em>
											<div class="c-csrd-m-wrap undis">
												<div class="c-card-more">
													<ul>
														<li class="clearfix"><div class="c-c-more-cleft">关联课程：</div><div class="c-c-more-cright">${userCard.courseName}</div></li>
														<li class="clearfix"><div class="c-c-more-cleft">使用时间：</div><div class="c-c-more-cright"><fmt:formatDate value="${userCard.useTime}" pattern="yyyy/MM/dd  HH:mm" /></div></li>
													</ul>
													<div class="yk-DT-arrow"><em>◆</em><span>◆</span></div>
												</div>
											</div>
										</td>
										<td style="width: 60%;">${userCard.cardCode}（课程卡）</td>
										<td>已使用</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<!--移动端结束  -->
						</c:if>
					</section>
				</div>
			</section>
		</section>
		<!-- /我的课程 -->
	</div>
</article>
<!-- /右侧内容区 结束 -->
</body>
</html>