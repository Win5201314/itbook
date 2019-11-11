<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邀请记录列表</title>
<script type="text/javascript">
	layui.use(['form', 'laydate'], function () {
		var form = layui.form();
		var laydate = layui.laydate;

	});

/**
 * 删除
 */
function delInvitationRecord(id,em){
	if(!confirm('确实要删除吗?')){
		return;
	}
	$.ajax({
		url:'/admin/ajax/invitationRecordDel/'+id,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==false){
                layer.msg(result.message, {icon: 5, shift: 6});
			}else{
				location.reload();
			}
		},
		error:function(error){
            layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
		}
	});
}
</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>邀请记录列表</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/invitationRecord/list" method="post" id="searchForm" class="layui-form">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">id</label>
					<div class="layui-input-inline">
						<input type="text" name="invitationRecord.id" value="${invitationRecord.id }" placeholder="id" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">邀请人的用户id</label>
					<div class="layui-input-inline">
						<input type="text" name="invitationRecord.invitationUserId" value="${invitationRecord.invitationUserId }" placeholder="邀请人的用户id" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">被邀请人用户id</label>
					<div class="layui-input-inline">
						<input type="text" name="invitationRecord.cashback" value="${invitationRecord.cashback }" placeholder="被邀请人用户id" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">添加时间</label>
					<div class="layui-input-inline">
						<input class="layui-input" name="invitationRecord.addTime" placeholder="开始时间" value="<fmt:formatDate value="${invitationRecord.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="layui.laydate({elem: this, festival: true})">
					</div>

			</div>

			<a title="查找" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找
			</a>
			<a title="清空" onclick="$('#searchForm input:text').val('');$('#searchForm select').val(0);$('select').change();"
				class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
			</div>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="layui-table" width="100%">
			<thead>
				<tr>
					<td align="center">id</td>
					<td align="center">邀请人的用户id</td>
					<%--<td align="center">被邀请人</td>--%>
					<td align="center">返现金额</td>
					<td align="center">添加时间</td>
					<%--<td align="center">操作</td>--%>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${invitationRecordList}" var="invitationRecord"  varStatus="index">
					<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
						<td align="center">${invitationRecord.id }</td>
						<%--<td align="center">${invitationRecord.userId }</td>--%>
						<td align="center">${invitationRecord.displayName }</td>
						<td align="center">${invitationRecord.cashback }</td>
						<td align="center"><fmt:formatDate value="${invitationRecord.addTime }" pattern="yyyy/MM/dd HH:mm" /></td>
						<%--<td align="center">
							<div class="c-more-box">
								<a href="javascript:void(0)" class="check-more">更多<em class="ui-state-default ui-corner-all"> </em></a>
								<ul class="c-more-list">
									<li><a href="javascript:void(0)" onclick="delInvitationRecord(${invitationRecord.id},this)" >删除</a></li>
								</ul>
							</div>
							<button onclick="window.location.href='${ctx}/admin/invitationRecord/toUpdate/${invitationRecord.id}'" class="ui-state-default ui-corner-all" type="button">修改</button>
						</td>--%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
</body>
</html>