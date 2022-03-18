package com.jetco.jpgcerttestproxy.object.request;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class PReq {
	
	static final Logger log = LoggerFactory.getLogger(PReq.class);

	protected String threeDSRequestorID;
	protected String threeDSRequestorURL;
	protected String p_isSerialNumPresent;
	protected String threeDSServerRefNumber;
	protected String threeDSServerOperatorID;
	protected String threeDSServerTransID;
	protected String messageType;
	protected String messageVersion;
	protected String serialNum;
	
	public abstract Map<String, String> validateMsg();
	
	public String getThreeDSServerRefNumber() {
		return threeDSServerRefNumber;
	}
	public void setThreeDSServerRefNumber(String threeDSServerRefNumber) {
		this.threeDSServerRefNumber = threeDSServerRefNumber;
	}
	public String getThreeDSServerOperatorID() {
		return threeDSServerOperatorID;
	}
	public void setThreeDSServerOperatorID(String threeDSServerOperatorID) {
		this.threeDSServerOperatorID = threeDSServerOperatorID;
	}
	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}
	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}
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
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getThreeDSRequestorURL() {
		return threeDSRequestorURL;
	}

	public void setThreeDSRequestorURL(String threeDSRequestorURL) {
		this.threeDSRequestorURL = threeDSRequestorURL;
	}
	
	public String getThreeDSRequestorID() {
		return threeDSRequestorID;
	}

	public void setThreeDSRequestorID(String threeDSRequestorID) {
		this.threeDSRequestorID = threeDSRequestorID;
	}

	public String getP_isSerialNumPresent() {
		return p_isSerialNumPresent;
	}

	public void setP_isSerialNumPresent(String p_isSerialNumPresent) {
		this.p_isSerialNumPresent = p_isSerialNumPresent;
	}
	
	

		
}
