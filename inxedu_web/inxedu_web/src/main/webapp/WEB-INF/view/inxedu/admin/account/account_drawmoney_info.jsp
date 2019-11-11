<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>提现详情</title>
<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>

</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>学员账户管理</span> &gt; <span>学员帐户历史记录</span>
	</legend>
	<div class="layui-field-box">
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">姓名</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.userName}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">邮箱</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.email}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">手机</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.mobile}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">提现金额</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.trxAmount}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">转账银行</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.bank}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">银行卡号</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.card}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">状态</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					<c:if test="${userAccounthistory.status=='NOTVIEWED'}">
						未审批
					</c:if>
					<c:if test="${userAccounthistory.status=='SUCCESS'}">
						通过
					</c:if>
					<c:if test="${userAccounthistory.status=='FAIL'}">
						失败
					</c:if>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">申请时间</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					<fmt:formatDate value="${userAccounthistory.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both" />
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">操作时间</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					<fmt:formatDate value="${userAccounthistory.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both" />
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">操作人</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.adminUserName}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label layui-form-label-w">备注</label>
			<div class="layui-input-block">
				<div class="xy-warp fsize14 c-999 f-fM">
					${userAccounthistory.description}
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input onclick="history.go(-1);" class="layui-btn layui-btn-primary" type="button" value="返回" />
			</div>
		</div>
	</div>
	</fieldset>
</body>
</html>
