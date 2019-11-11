package com.inxedu.os.common.util.QCloud;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * 腾讯云短信工具类
 */
public class QCloudUtil {

	/**
	 * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
	 */
	public static String Encrypt(String strSrc, String encName) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			if (encName == null || encName.equals("")) {
				encName = "SHA-256";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * unix时间戳
     */
	public static long unixtime() {
		return System.currentTimeMillis()/1000;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL object = new URL(url);
			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("POST");
			con.setRequestProperty("Cache-Control",  "no-cache");
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "utf-8");
			wr.write(param.toString());
			wr.flush();
			// 显示 POST 请求返回的内容
			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					sb.append(line);
				}
				br.close();
				result = "" + sb.toString();
			} else {
				System.out.println(con.getResponseMessage());
			}
			result=sb.toString();
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		return result;
	}



}