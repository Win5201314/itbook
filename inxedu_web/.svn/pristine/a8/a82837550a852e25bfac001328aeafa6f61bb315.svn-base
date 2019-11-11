<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的消息</title>
<script type="text/javascript">
	function delConfirm(id){
		dialog("删除提示", "确认要删除此条站内信吗？", 2, "javascript:void(0)' onclick='delULetter("+id+")");
	}
function delULetter(id){//删除站内信
	$("#tisbutt,.dClose,#qujiao").click();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/uc/ajax/delLetterInbox",
		data:{"msgReceive.id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
				msgshow("删除成功","true","3000");
				$("#del"+id).remove();//前端根据ID移除信息
				window.location.reload()
			}else{
				msgshow(result.message,"false","3000");
			}
		}
	});
}
</script>
</head>
<body>
<article class="col-80 fl">
	<div class="u-r-cont">
		<section>
			<div class="">
				<section class="u-r-tit-all">
					<h2 class="unFw">
						<span class="f-fH current">消息中心</span>
					</h2>
				</section>
				<div class="u-r-all-box">
					<c:if test="${empty queryLetterList }">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em>
							<span class="c-666 fsize14 ml10 vam">您还没有收到消息哦！</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty queryLetterList }">
						<div class="u-sys-news">
							<ul class="u-sys-list">
								<c:forEach items="${queryLetterList}" var="qltl">
									<li id="del${qltl.id }">
										<div>
											<%--<h4 class="u-s-l-tit hLh30">
												&lt;%&ndash;<a href="${ctx}/uc/letterInfo/${qltl.id}" class="u-s-l-t-a of fsize16 c-333 f-fM">
													<c:if test="${qltl.type==1}"><p class="hLh30"><b class="fsize14 f-fA c-red">系统消息</b> </p></c:if>
													<c:if test="${qltl.type==5}"><p class="hLh30"><b class="fsize14 f-fA c-red">课程过期</b> </p></c:if>
													<c:if test="${qltl.type==6}"><p class="hLh30"><b class="fsize14 f-fA c-red">优惠券过期</b> </p></c:if>
												</a>&ndash;%&gt;

											</h4>--%>
											<section class="u-s-l-txt">
												<%--<a href="${ctx}/uc/letterInfo/${qltl.id}">--%>
												<p class="fsize14 c-999">${qltl.content}</p>
												<%--</a>--%>
												<em class="wd-ico">
													<c:if test="${qltl.status==0}">
														<img src="${ctx}/static/inxweb/img/wd-bg-u.png">
													</c:if>
												</em>
												<a href="javascript:void (0)" onclick="delConfirm(${qltl.id})" class="u-s-l-sc-ico" title="删除">
													&nbsp;
												</a>
											</section>
											<section class="clearfix hLh30">
												<%--<span class="fl">
													<a href="${ctx}/uc/letterInfo/${qltl.id}" class="c-blue fsize14 f-fM">查看更多</a>
												</span>--%>
												<span class="fl">
													<tt class="fsize14 c-999 f-fM"><fmt:formatDate type="both" value="${qltl.addTime }" pattern="yyyy-MM-dd" /></tt>
												</span>
											</section>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<!-- 公共分页 开始 -->
					<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
					<!-- 公共分页 结束 -->
					<form action="${ctx}/uc/letter"name="searchForm" id="searchForm" method="post">
						<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
					</form>
				</div>
			</div>
		</section>
		<!-- /Wo的消息 -->
	</div>
</article>
<!-- /右侧内容区 结束 -->
</body>
</html>

