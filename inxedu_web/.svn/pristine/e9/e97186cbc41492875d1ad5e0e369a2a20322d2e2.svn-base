<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的收藏</title>
<script type="text/javascript">
	/**
	 * 全选或反选 
	 */
	function selectAll(em) {
		$("input[name='favouriteId']").prop('checked', $(em).prop('checked'));
	}

	/**
	 * 批量删除收藏
	 */
	function batchDelFav() {
		var arr = $("input[name='favouriteId']:checked");
		if (arr == null || arr.length == 0) {
			dialog("删除提示", "请选择要删除的收藏课程！", 1);
			return;
		}
		var favouriteIdStr = "";
		$('input[name="favouriteId"]:checked').each(function() {
			favouriteIdStr = favouriteIdStr + $(this).val() + ",";
		});
		favouriteIdStr = favouriteIdStr.substring(0, favouriteIdStr.length - 1);
		dialog("删除提示", "确认要删除选择的收藏课程？", 2, "${ctx}/uc/deleteFaveorite/"
				+ favouriteIdStr);
	}
	function delConfirm(courseId) {
		dialog("删除提示", "确认要删除收藏的此课程？", 2, "javascript:void(0)' onclick='deleteFaveorite("+courseId+")");
	}
	function deleteFaveorite(courseId) {
		$("#tisbutt,.dClose,#qujiao").click();
		$.ajax({
			url:'/webapp/deleteFaveorite',
			type:'post',
			data:{"courseId":courseId},
			dataType: 'json',
			success: function (result) {
				if (result.success==true){
					msgshow("删除成功","true","3000");
					window.location.reload();
				}else{
					msgshow(result.message,"false","3000");
				}
			}
		})
	}
</script>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<form action="${ctx}/uc/myFavorites" method="post" id="searchForm">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" style="width: 60px"/>
			</form>
			<section>
				<div class="">
					<section class="u-r-tit-all">
						<h2 class="unFw">
							<span class="f-fH current">我的收藏</span>
						</h2>
					</section>
					<section class="u-r-all-box">
						<div class="u-fav-list">
							<c:if test="${empty favoriteList}">
								<section class="no-data-wrap" <c:if test="${fn:length(courseNoteList)>0}">style="display: none" </c:if>>
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">还没有收藏任何课程！~<a href="${ctx}/front/showcoulist" class="c-master">马上去学习</a></span>
								</section>
							</c:if>
							<c:if test="${not empty favoriteList}">
								<ul>
									<c:forEach items="${favoriteList}" var="favorite">
										<li>
											<dl>
												<dd class="u-order-list">
													<section class="col-6 disIb vam u-o-left">
														<div class="mr30 u-o-l-infor">
															<div class="c-cou-item">
																<div class="my-sc-box">
																	<div>
																		<a class="u-ol-pic" href="${ctx}/front/couinfo/${favorite.courseId}" title="${favorite.courseName}">
																			<img src="${staticServer}${favorite.logo}" alt="">
																		</a>
																		<h6 class="hLh30 txtOf name">
																			<a class="fsize16 c-333" href="${ctx}/front/couinfo/${favorite.courseId}" title=""> ${favorite.courseName}</a>
																		</h6>
																		<section class="hLh20 txtOf mt10">
																			<c:if test="${fn:length(favorite.teacherList)!=0}">
																			<span class="fsize14 c-999 vam">主讲：</span>
																			<c:forEach items="${favorite.teacherList}" var="teacher">
																				<span class="fsize14 c-999 vam">${teacher.name}</span>
																			</c:forEach>
																			</c:if>
																		</section>
																		<section class="hLh20 txtOf mt10 u-o-l-jg">
																			<span class="c-999 fsize13">
																				<fmt:formatDate value="${favorite.addTime}" pattern="yyyy-MM-dd HH:mm" />
																			</span>
																		</section>
																	</div>
																</div>
															</div>
														</div>
													</section>
													<section class="disIb vam u-o-maddile col-tw tac mb10">
														<div class="disIb vam tac u-o-price">
														<span class="c-master f-fM fsize16">
															￥<font>${favorite.currentPrice}</font>
														</span>
														</div>
													</section>
													<section class="col-tw disIb vam u-o-right">
														<div class="tac u-o-r-btn-infor">
															<p class="c-999 f-fA mb10"><a href="javascript:void(0)" onclick="delConfirm('${favorite.courseId}')" class="cou-kscp" style="margin-right: 0">取消收藏</a></p>
														</div>
													</section>
													<div class="clear"></div>
												</dd>
											</dl>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
						<!-- 公共分页 开始 -->
						<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
						<!-- 公共分页 结束 -->
					</section>
				</div>
			</section>
		</div>
	</article>
</body>
</html>