package com.inxedu.os.common.session;

import com.inxedu.os.common.cache.CacheUtil;

import java.util.HashMap;
import java.util.Map;

public class SessionService {

	private static SessionService instance = null;
	public static synchronized SessionService getInstance() {
		if (instance == null) {
			instance = new SessionService();
		}
		return instance;
	}
	private SessionService() {

	}
	public Map getSession(String id) {
		Map session = (Map) CacheUtil.get(id);
		if (session == null) {
			session = new HashMap();
			CacheUtil.set(id, session);
		}
		return session;
	}
	public void saveSession(String id, Map session) {
		CacheUtil.set(id, session);
	}
	public void saveSession(String id, Map session,int s) {
		CacheUtil.set(id, session,s);
	}
	public void removeSession(String id) {
		CacheUtil.remove(id);
	}

}
