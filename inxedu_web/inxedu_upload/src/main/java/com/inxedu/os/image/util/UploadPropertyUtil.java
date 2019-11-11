package com.inxedu.os.image.util;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class UploadPropertyUtil {
	private static Map<String, UploadPropertyUtil> instance = Collections.synchronizedMap(new HashMap<String, UploadPropertyUtil>());
	protected String sourceUrl;
	protected ResourceBundle resourceBundle;
	private static Map<String, String> convert = Collections.synchronizedMap(new HashMap<String, String>());

	protected UploadPropertyUtil(String sourceUrl) {
		this.sourceUrl = sourceUrl;
		load();
	}

	public static UploadPropertyUtil getInstance(String sourceUrl) {
		synchronized (UploadPropertyUtil.class) {
			UploadPropertyUtil manager = instance.get(sourceUrl);
			if (manager == null) {
				manager = new UploadPropertyUtil(sourceUrl);
				instance.put(sourceUrl, manager);
			}
			return manager;
		}
	}

	private synchronized void load() {
		try {
			this.resourceBundle = ResourceBundle.getBundle(this.sourceUrl);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("sourceUrl = " + this.sourceUrl+ "file load error!", e);
		}
	}

	public String getProperty(String key) {
		try {
			return new String(this.resourceBundle.getString(key).getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return this.resourceBundle.getString(key);
	}

	public Map<String, String> readyConvert() {
		Enumeration<String> enu = this.resourceBundle.getKeys();
		while (enu.hasMoreElements()) {
			String key = enu.nextElement();
			String value = this.resourceBundle.getString(key);
			convert.put(value, key);
		}
		return convert;
	}

	public Map<String, String> readyConvert(ResourceBundle resourcebundle) {
		Enumeration<String> enu = resourcebundle.getKeys();
		while (enu.hasMoreElements()) {
			String key = enu.nextElement();
			String value = resourcebundle.getString(key);
			convert.put(value, key);
		}
		return convert;
	}

}