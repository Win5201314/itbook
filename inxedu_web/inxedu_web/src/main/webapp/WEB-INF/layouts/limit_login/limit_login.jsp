<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<%--imPath--%>
<input type="hidden" id="imPath" value="${WebSwitch.imPath}"/>
<c:if test="${WebSwitch.limitLogin=='ON'}"><%--限制登陆js--%>
	<script type="text/javascript" src="${WebSwitch.imPath}/javascripts/json2.js" async="async"></script>
	<script type="text/javascript" src="${WebSwitch.imPath}/socket.io/socket.io.js" async="async"></script>
	<script type="text/javascript" src="${WebSwitch.imPath}/javascripts/268xue-im-sys.js" async="async" defer></script>
	<script type="text/javascript" src="${ctx}/static/common/limit_login/limitLogin.js" async="async" defer></script>
</c:if>
