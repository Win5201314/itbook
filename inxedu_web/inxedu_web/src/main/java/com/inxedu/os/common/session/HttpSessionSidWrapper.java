package com.inxedu.os.common.session;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

public class HttpSessionSidWrapper extends HttpSessionWrapper {

	private String sid = "";

	private Map map = null;

	public HttpSessionSidWrapper(String sid, HttpSession session) {
		super(session);
		this.sid = sid;
		this.map = SessionService.getInstance().getSession(sid);
	}

	public Object getAttribute(String arg0) {
		return this.map.get(arg0);
	}

	public String getId() {
		return sid;
	}
	public Enumeration getAttributeNames() {

		return (new Enumerator(this.map.keySet(), true));
	}

	public void invalidate() {
		this.map.clear();
		SessionService.getInstance().removeSession(this.sid);
	}

	public void removeAttribute(String arg0) {
		this.map.remove(arg0);
		SessionService.getInstance().saveSession(this.sid, this.map);
	}

	@SuppressWarnings("unchecked")
	public void setAttribute(String arg0, Object arg1) {
		this.map.put(arg0, arg1);
		SessionService.getInstance().saveSession(this.sid, this.map);
	}
	public void setMaxInactiveInterval(int arg0) {
		SessionService.getInstance().saveSession(this.sid, this.map,arg0);
	}
}
