<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>优惠券编码列表</title>
	<script type="text/javascript">
		function cancel(){
			$("#type").val(-1);
			$("#useType").val(-1);
			$("#status").val(-1);
			$("#couponId").val("");
			$("#requestId").val("");
			$("#couponCode").val("");
			$("#isGive").val("1");
		}

		/**
		 * 赠送优惠券
		 * @param id
         */
		function giveCoupon(id){
			if(confirm("确定赠送优惠券？")){
				$.ajax({
					url:"${ctx}/admin/ajax/giveUserCouponCode",
					data:{
						"couponCode.userId":'${queryCouponCode.userId}',
						"couponCode.id":id
					},
					type:"post",
					dataType:"json",
					cache : false,
					async:false,
					success:function(result){
						if(result.success){
                            layer.msg(result.message, {icon: 1, shift: 6});
							window.location.reload();
						}else{
                            layer.msg(result.message, {icon: 5, shift: 6});
						}
					}
				});
			}
		}
	</script>
</head>
<body  >

<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>优惠券编码管理</span> &gt; <span>优惠券编码列表</span> </h4>
	</div>
		<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
				<form action="${ctx}/admin/usercouponcode/page" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<input type="hidden" name="queryCouponCode.userId" value="${queryCouponCode.userId}"/>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>优惠券ID：</font></span>
									<input type="text"  class="layui-input" name="queryCouponCode.couponId" id="couponId" value="${queryCouponCode.couponId}"/>
								</li>
								<li>
									<span class="ddTitle"><font>优惠券：</font></span>
									<select  name="queryCouponCode.isGive" id="isGive">
										<option value="1" <c:if test="${queryCouponCode.isGive==1 }">selected="selected"</c:if>>未赠送</option>
										<option value="2" <c:if test="${queryCouponCode.isGive==2 }">selected="selected"</c:if>>用户已有</option>
									</select>
								</li>
								<li>
									<span class="ddTitle"><font>优惠券编码：</font></span>
									<input type="text"  class="layui-input" name="queryCouponCode.couponCode" id="couponCode" value="${queryCouponCode.couponCode}"/>
								</li>
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>次数限制：</font></span>
									<select  name="queryCouponCode.useType" id="useType">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCouponCode.useType==1 }">selected="selected"</c:if>>无限</option>
										<option value="2" <c:if test="${queryCouponCode.useType==2 }">selected="selected"</c:if>>正常</option>
									</select>
								</li>
								<li>
									<span class="ddTitle"><font>优惠类型：</font></span>
									<select  name="queryCouponCode.type" id="type">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCouponCode.type==1 }">selected="selected"</c:if>>折扣券</option>
										<option value="2" <c:if test="${queryCouponCode.type==2 }">selected="selected"</c:if>>定额券</option>
										<option value="3" <c:if test="${queryCouponCode.type==3 }">selected="selected"</c:if>>会员定额券</option>
									</select>
								</li>
								<li>
									<span class="ddTitle"><font>编码状态：</font></span>
									<select  name="queryCouponCode.status" id="status">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCouponCode.status==1 }">selected="selected"</c:if>>未使用</option>
										<option value="2" <c:if test="${queryCouponCode.status==2 }">selected="selected"</c:if>>已使用</option>
										<option value="3" <c:if test="${queryCouponCode.status==3 }">selected="selected"</c:if>>过期</option>
										<option value="4" <c:if test="${queryCouponCode.status==4 }">selected="selected"</c:if>>作废</option>
									</select>
								</li>
							</ul>
						</div>
						<div class="w50pre fl" style="text-align: center;">
							<ul class="ddBar">
								<li>
									<input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="goPage(1)" value="查询">
									<%--<input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="cancel()" value="清空">--%>
								</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fr c_666"><span>优惠券编码列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
					</div>
				</caption>
					<thead>
						<tr>
							<th><span>ID</span></th>
                            <th><span>名称</span></th>
                            <th><span>优惠券编码</span></th>
                            <th><span>类型</span></th>
                            <th><span>限额</span></th>
                            <th><span>次数限制</span></th>
                            <th><span>有效期</span></th>
                            <th><span>状态</span></th>
                            <th><span>创建时间</span></th>
                            <th><span>创建人</span></th>
                            <th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
					<c:if test="${couponCodeDTOList.size()>0}">
					<c:forEach  items="${couponCodeDTOList}" var="couponCodeDTO" >
						<tr>
							<td>${couponCodeDTO.id}</td>
							<td>${couponCodeDTO.title}</td>
							<td>${couponCodeDTO.couponCode}</td>
							<td>
								<c:if test="${couponCodeDTO.type==1 }">折扣券</c:if>
								<c:if test="${couponCodeDTO.type==2 }">定额券</c:if>
								<c:if test="${couponCodeDTO.type==3 }">会员定额券</c:if>
							</td>
							<td>${couponCodeDTO.limitAmount }</td>
							<td>
								<c:if test="${couponCodeDTO.useType==1 }">无限</c:if>
								<c:if test="${couponCodeDTO.useType==2 }">正常</c:if>
							</td>
							<td>
								<fmt:formatDate value="${couponCodeDTO.startTime}" type="both"  pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${couponCodeDTO.endTime}" type="both"  pattern="yyyy-MM-dd"/>
							</td>
							<td>
								<c:if test="${couponCodeDTO.status==1 }">未使用</c:if>
								<c:if test="${couponCodeDTO.status==2 }">已使用</c:if>
								<c:if test="${couponCodeDTO.status==3 }">过期</c:if>
								<c:if test="${couponCodeDTO.status==4 }">作废</c:if>
							</td>
							<td>
								<fmt:formatDate value="${couponCodeDTO.createTime}" type="both"  pattern="yyyy-MM-dd"/>
							</td>
							<td>
								${couponCodeDTO.optuserName }
							</td>
							<td  class="c_666 czBtn" align="center">
								<c:choose>
									<c:when test="${couponCodeDTO.userId>0}">已赠送</c:when>
									<c:otherwise><a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="giveCoupon(${couponCodeDTO.id})">赠送优惠券</a></c:otherwise>
								</c:choose>
							</td>
							
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${couponCodeDTOList.size()==0||couponCodeDTOList==null}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有优惠券编码！</span>
								</div>
							</td>
						</tr>
						</c:if>
					</tbody>
				</table>
				</form>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
<!-- 内容 结束 -->

</body>
</html>
