package com.jetco.jpgcerttestproxy.object.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PPrq {

	private String messageType;
	private String p_messageVersion;
	private String messageVersion;
	private String threeDSRequestorID;
	private String threeDSServerTransID;
	private String threeDSRequestorURL;
	private String messageExtension;
	private String p_isSerialNumPresent;
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getP_messageVersion() {
		return p_messageVersion;
	}
	public void setP_messageVersion(String p_messageVersion) {
		this.p_messageVersion = p_messageVersion;
	}
	public String getMessageVersion() {
		return messageVersion;
	}
	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}
	public String getThreeDSRequestorID() {
		return threeDSRequestorID;
	}
	public void setThreeDSRequestorID(String threeDSRequestorID) {
		this.threeDSRequestorID = threeDSRequestorID;
	}
	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}
	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}
	public String getThreeDSRequestorURL() {
		return threeDSRequestorURL;
	}
	public void setThreeDSRequestorURL(String threeDSRequestorURL) {
		this.threeDSRequestorURL = threeDSRequestorURL;
	}
	public String getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(String messageExtension) {
		this.messageExtension = messageExtension;
	}
	public String getP_isSerialNumPresent() {
		return p_isSerialNumPresent;
	}
	public void setP_isSerialNumPresent(String p_isSerialNumPresent) {
		this.p_isSerialNumPresent = p_isSerialNumPresent;
	}
	
	@Override
	public String toString() {
		return "PPrq [messageType=" + messageType + ", p_messageVersion=" + p_messageVersion + ", messageVersion="
				+ messageVersion + ", threeDSRequestorID=" + threeDSRequestorID + ", threeDSServerTransID="
				+ threeDSServerTransID + ", threeDSRequestorURL=" + threeDSRequestorURL + ", messageExtension="
				+ messageExtension + ", p_isSerialNumPresent=" + p_isSerialNumPresent + "]";
	}
	
	
}
