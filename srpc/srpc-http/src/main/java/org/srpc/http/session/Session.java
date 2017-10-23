package org.srpc.http.session;

import java.io.Serializable;


public class Session implements Serializable{
	private static final long serialVersionUID = -5245831439798532767L;
	private static final String TAG = Session.class.getCanonicalName();
	private String sessionId;
	private Long expireTime;

	public String getSessionId() {
		
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
}
