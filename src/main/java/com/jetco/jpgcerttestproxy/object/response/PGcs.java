package com.jetco.jpgcerttestproxy.object.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PGcs {

	private String messageType;
	private String messageVersion;
	private String htmlCreq;
	
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
	public String getHtmlCreq() {
		return htmlCreq;
	}
	public void setHtmlCreq(String htmlCreq) {
		this.htmlCreq = htmlCreq;
	}
	
	
	
}
