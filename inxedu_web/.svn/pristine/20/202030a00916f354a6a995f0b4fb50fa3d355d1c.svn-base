<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="com.inxedu.os.common.constants.CommonConstants"%>
<%@ page import="com.inxedu.os.common.constants.CacheConstans" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%!
	/**网校地址*/
	static String contextPath=CommonConstants.contextPath;
	/**上传服务用服务器地址，访问时用contextPath，数据库中不存储域名*/
	static String uploadServer=CommonConstants.uploadServer;
	//内容编辑器上传图片路径
	static String keuploadSimpleUrl = uploadServer+"/image/keupload?";
	//图片上传路径 
	static String uploadSimpleUrl = uploadServer+"/image/gok4?";
	//pdf上传路径 和生成图片
	static String pdfUploadAndImg = uploadServer+"/image/pdfUploadAndImg?";
	//上传文件路径
	static String uploadfileUrl = uploadServer+"/image/uploadfile?";
	//上传图片路径带缩列图
	static String uploadShrinkDiagram = uploadServer+"/image/shrinkDiagram?";
	%>
<c:set var="ctx" value="<%=CommonConstants.contextPath%>"></c:set>
<c:set var="staticServer" value="<%=CommonConstants.staticServer%>"></c:set>
<%--是否购买优惠券--%>
<c:set var="couponIsOpen" value="<%=CacheConstans.COUPON_IS_OPEN%>"></c:set>
<%--是否购买 易宝 服务--%>
<c:set var="yibaoIsOpen" value="<%=CacheConstans.YIBAO_IS_OPEN%>"></c:set>
<%--是否购买 微信支付 服务--%>
<c:set var="weixinIsOpen" value="<%=CacheConstans.WEIXIN_IS_OPEN%>"></c:set>
<%--是否购买 支付宝支付 服务--%>
<c:set var="zhifubaoIsOpen" value="<%=CacheConstans.ZHIFUBAO_IS_OPEN%>"></c:set>
<%--imPath--%>
<c:set var="imPath" value="${WebSwitch.imPath}"></c:set>
<%--考试地址--%>
<c:set var="groupPath" value="<%=CommonConstants.groupPath%>"></c:set>
