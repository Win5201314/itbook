<%@ page import="com.baidu.ueditor.ActionEnter" %>
<%@ page import="com.inxedu.os.image.util.UploadPropertyUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");
	response.setHeader("Content-Type", "text/html");
	response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	response.addHeader("Access-Control-Allow-Origin", "*");
	response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With,Content-Type");
	UploadPropertyUtil propertyUtil = UploadPropertyUtil.getInstance("project");
	String rootPath= propertyUtil.getProperty("file.root");

	System.out.println("++++++rootPath:"+rootPath);
	String diskPath = application.getRealPath( "/" );
	System.out.println("++++++diskPath:"+diskPath);
	String result= new ActionEnter(request, rootPath).exec();
	out.write(result);

%>