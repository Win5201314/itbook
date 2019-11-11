<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html> <!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>第三方登录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/inxweb/three-login/three-login.css">
    <script type="text/javascript" src="${ctx}/static/inxweb/three-login/three-login.js"></script>
</head>
<body>
<article class="col-75 fl u-m-c-wrap">
    <div class="u-r-cont">
        <section>
            <div>
                <section class="c-infor-tabTitle c-tab-title">
                    <a href="javascript: void(0)" title="Wo的第三方登录"  class="c-title-name c-tab-title">Wo的第三方登录</a>
                </section>
            </div>
            <section >
                <div class="Binding"><p class="fsize14 f-fM c-orange hLh30 tac">绑定第三方账号，可以直接登录。</p>
                    <ul class="Binding-list clearfix">
                        <c:if test="${'ON'==QQ_LOGIN_IS_OPEN}">
                        <li class="Binding-qq">
                            <div><span class="img">&nbsp;</span>
                                <p class="fsize14 f-fM c-333 tac mt20">
                                <c:choose>
                                    <c:when test="${!empty userProfileQQ.id}">
                                        <c:choose>
                                            <c:when test="${not empty userProfileQQ.name}">${userProfileQQ.name}</c:when>
                                            <c:otherwise>&nbsp;</c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>未绑定账号</c:otherwise>
                                </c:choose>
                                </p>
                                <c:choose>
                                    <c:when test="${!empty userProfileQQ.id}"><a href="javascript:void(0)" onclick="removeBinding(${userProfileQQ.id})" class="ljbd-btn mt20">解除绑定</a></c:when>
                                    <c:otherwise><a href="javascript:thirdPartyLogin('QQ')" class="ljbd-btn mt20">立即绑定</a></c:otherwise>
                                </c:choose>
                            </div>
                        </li>
                        </c:if>
                        <c:if test="${'ON'==SINA_LOGIN_IS_OPEN}">
                        <li class="Binding-xl">
                            <div><span class="img">&nbsp;</span>
                                <p class="fsize14 f-fM c-333 tac mt20">
                                    <c:choose>
                                        <c:when test="${!empty userProfileSina.id}">
                                            <c:choose>
                                                <c:when test="${not empty userProfileSina.name}">${userProfileSina.name}</c:when>
                                                <c:otherwise>&nbsp;</c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>未绑定账号</c:otherwise>
                                    </c:choose>
                                </p>
                                <c:choose>
                                    <c:when test="${!empty userProfileSina.id}"><a href="javascript:void(0)" onclick="removeBinding(${userProfileSina.id})" class="ljbd-btn mt20">解除绑定</a></c:when>
                                    <c:otherwise><a href="javascript:thirdPartyLogin('SINA')" class="ljbd-btn mt20">立即绑定</a></c:otherwise>
                                </c:choose>
                            </div>
                        </li>
                        </c:if>
                        <c:if test="${'ON'==WEIXIN_LOGIN_IS_OPEN}">
                            <li class="Binding-wx">
                                <div><span class="img">&nbsp;</span>
                                    <p class="fsize14 f-fM c-333 tac mt20">
                                        <c:choose>
                                            <c:when test="${!empty userProfileWeiXin.id}">
                                                <c:choose>
                                                    <c:when test="${not empty userProfileWeiXin.name}">${userProfileWeiXin.name}</c:when>
                                                    <c:otherwise>&nbsp;</c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>未绑定账号</c:otherwise>
                                        </c:choose>
                                    </p>
                                    <c:choose>
                                        <c:when test="${!empty userProfileWeiXin.id}"><a href="javascript:void(0)" onclick="removeBinding(${userProfileWeiXin.id})" class="ljbd-btn mt20">解除绑定</a></c:when>
                                        <c:otherwise><a href="javascript:thirdPartyLogin('WEIXIN')" class="ljbd-btn mt20">立即绑定</a></c:otherwise>
                                    </c:choose>
                                </div>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </section>
        </section>
        <!-- /我的资料 -->
    </div>
</article>
<!-- /右侧内容区 结束 -->
</body>
</html>