package com.jetco.jpgcerttestproxy.object.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PPrs {
	
	private String messageType;
	private String messageVersion;
	private String p_messageVersion;
	private String p_completed;
	private String messageExtension;
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageVersion() {
		return messageVersion;
	}
	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}
	public String getP_messageVersion() {
		return p_messageVersion;
	}
	public void setP_messageVersion(String p_messageVersion) {
		this.p_messageVersion = p_messageVersion;
	}
	public String getP_completed() {
		return p_completed;
	}
	public void setP_completed(String p_completed) {
		this.p_completed = p_completed;
	}
	public String getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(String messageExtension) {
		this.messageExtension = messageExtension;
	}
	
}
