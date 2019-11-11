package com.inxedu.os.edu.constants.enums;

public class Constants {
	// 小程序唯一标识 (在微信小程序管理后台获取)
	public static final String APPID ="wx806cfc528700da81";
	// 小程序的 app secret (在微信小程序管理后台获取)
	public static final String APP_SECRECT ="fc0c8846455b8b5c6610b08f03acc6e8";
	//授权
	public static final String AUTHORIZATION_CODE ="authorization_code";
	//返回码    0 成功
	public static final String SUCCESS_CODE ="0";   
	//auth.code2Session 接口地址,根据code获取 用户唯一标识 OpenID 和 会话 session_key
	public static final String  GET_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
}
