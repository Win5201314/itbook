<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单课程管理列表</title>
<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/orderdetail/trxorderDetail.js"></script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		订单课程列表
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/orderDetial/list" method="post" id="searchForm"  class="layui-form">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" id="keyWord" name="queryTrxorderDetail.keyWord" value="${queryTrxorderDetail.keyWord}" placeholder="订单号/邮箱/手机号" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">课程名</label>
					<div class="layui-input-inline">
						<input type="text" placeholder="课程名" id="courseName" name="queryTrxorderDetail.courseName" value="${queryTrxorderDetail.courseName}"  class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">状态</label>
					<div class="layui-input-inline">
						<select name="queryTrxorderDetail.trxStatus" id="trxStatus" lay-filter="select">
							<option value="">全部</option>
							<option value="">全部</option>
							<option value="SUCCESS"  ${queryTrxorderDetail.trxStatus=='SUCCESS'?'selected':''}>已支付</option>
							<option value="INIT" ${queryTrxorderDetail.trxStatus=='INIT'?'selected':''}>未支付</option>
							<option value="CANCEL" ${queryTrxorderDetail.trxStatus=='CANCEL'?'selected':''}>已取消</option>
							<option value="REFUND" ${queryTrxorderDetail.trxStatus=='REFUND'?'selected':''}>退款</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">

					<label class="layui-form-label">下单时间</label>
					<div class="layui-input-inline">
						<input placeholder="开始下单时间" name="queryTrxorderDetail.startCreateTime"
							   value="<fmt:formatDate value="${queryTrxorderDetail.startCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beginCreateTime" type="text"
							   readonly="readonly"  class="layui-input" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})"/>
					</div>
					<div class="layui-input-inline">
						<input placeholder="结束下单时间" id="endCreateTime" name="queryTrxorderDetail.endCreateTime"
							   value="<fmt:formatDate value="${queryTrxorderDetail.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" readonly="readonly" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">

					<label class="layui-form-label">支付时间</label>
					<div class="layui-input-inline">
						<input placeholder="开始支付时间" id="beginPayTime" name="queryTrxorderDetail.startPayTime"
							   value="<fmt:formatDate value="${queryTrxorderDetail.startPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text"
							   readonly="readonly"  class="layui-input" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})"/>
					</div>
					<div class="layui-input-inline">
						<input placeholder="结束支付时间" id="endPayTime" name="queryTrxorderDetail.endPayTime"
							   value="<fmt:formatDate value="${queryTrxorderDetail.endPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" readonly="readonly" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<button title="查询订单" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
						查询订单
					</button>
					<button title="清空" onclick="$('#searchForm input:text').val('');$('#keyWord').val('');$('#courseName').val();$('#payType').val('');$('#trxStatus').val('');"
							class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
						清空
					</button>

					<button type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="trxorderExcel()">导出Excel</button>
				</div>
			</div>
		</form>
		<table class="layui-table" lay-even="" lay-skin="row">
			<thead>
				<tr>
					<td align="center">订单号</td>
					<td align="center">学员邮箱</td>
					<td align="center">学员手机</td>
					<td align="center">课程名</td>
					<td align="center">金额</td>
					<td align="center">创建时间</td>
					<td align="center">状态</td>
					<td align="center">支付时间</td>
					<td align="center">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${trxorderDetailList}" var="trxorderDetail" varStatus="index">
					<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
						<td align="center">${trxorderDetail.orderNo}</td>
						<td align="center">${trxorderDetail.email}</td>
						<td align="center">${trxorderDetail.mobile}</td>
						<td align="center"> <a href="${ctx}/admin/cou/initUpdate/${trxorderDetail.courseId}">${trxorderDetail.courseName}</a></td>
						<td align="center">${trxorderDetail.currentPrice}</td>
						<td align="center">
							<fmt:formatDate value="${trxorderDetail.createTime}" pattern="yyyy/MM/dd" />

						</td>
						<td align="center">
							<c:if test="${trxorderDetail.authStatus=='INIT'}">
								<font color="#e33b00">未支付</font>
							</c:if>
							<c:if test="${trxorderDetail.authStatus=='SUCCESS'}">
								<font color="#00e33b">已支付</font>
							</c:if>
							<c:if test="${trxorderDetail.authStatus=='REFUND'}">
								退款
							</c:if>
							<c:if test="${trxorderDetail.authStatus=='CANCEL'}">已取消</c:if>
							<c:if test="${trxorderDetail.authStatus=='CLOSED'}">
								<font color="orange">已关闭</font>
							</c:if>
						</td>
                        <td align="center">
							<c:choose>
								<c:when test="${trxorderDetail.payTime!=null}">

									<fmt:formatDate value="${trxorderDetail.payTime}" pattern="yyyy/MM/dd" />
                    <%--<fmt:formatDate value="${trxorderDetail.createTimeFormat}" pattern="yyyy/MM/dd"  />--%>
								</c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
                        </td>
                        <td>
                            <button onclick="window.location.href='${ctx}/admin/orderDetial/trxorderDetailInfo/${trxorderDetail.id}'" class="layui-btn layui-btn-small" type="button">详情</button>
                        </td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
</fieldset>
</body>
</html>