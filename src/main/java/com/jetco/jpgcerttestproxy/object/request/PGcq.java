package com.jetco.jpgcerttestproxy.object.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PGcq {
	
	private String messageType;
	private String messageVersion;
	private String threeDSServerTransID;
	private String acsTransID;
	private String threeDSSessionData;
	private String challengeWindowSize;
	
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
	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}
	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}
	public String getAcsTransID() {
		return acsTransID;
	}
	public void setAcsTransID(String acsTransID) {
		this.acsTransID = acsTransID;
	}
	public String getThreeDSSessionData() {
		return threeDSSessionData;
	}
	public void setThreeDSSessionData(String threeDSSessionData) {
		this.threeDSSessionData = threeDSSessionData;
	}
	public String getChallengeWindowSize() {
		return challengeWindowSize;
	}
	public void setChallengeWindowSize(String challengeWindowSize) {
		this.challengeWindowSize = challengeWindowSize;
	}
	
}
