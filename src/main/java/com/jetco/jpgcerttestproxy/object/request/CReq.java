package com.jetco.jpgcerttestproxy.object.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.response.ARes;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class CReq {
	
	protected String threeDSServerTransID;						// 3DS Server Transaction ID
	protected String acsTransID;									// ACS Transaction ID
	protected String messageType;									// Message Type
	protected String messageVersion;								// Message Version
	// Field required for Browser Mode CReq message
	protected String challengeWindowSize;							// Challenge Window Size
	protected String challengeDataEntry;							// Challenge Data Entry
	
	public abstract Map<String, String> validateMsg();
	
	public CReq mapCReqFromARes(ARes aRes) {
		
		this.messageType = "CReq"; 
		this.messageVersion = aRes.getMessageVersion();
		this.threeDSServerTransID = aRes.getThreeDSServerTransID();
		this.acsTransID = aRes.getAcsTransID();
		this.challengeWindowSize =  "05";
						
		return this;
	}
	
	public CReq mapCReqFromPGcq(PGcq pGcq) {
		
		this.messageType = "CReq"; 
		this.messageVersion = pGcq.getMessageVersion();
		this.threeDSServerTransID = pGcq.getThreeDSServerTransID();
		this.acsTransID = pGcq.getAcsTransID();
		this.challengeWindowSize = pGcq.getChallengeWindowSize();
					
		return this;
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
	public String getChallengeWindowSize() {
		return challengeWindowSize;
	}
	public void setChallengeWindowSize(String challengeWindowSize) {
		this.challengeWindowSize = challengeWindowSize;
	}
	public String getChallengeDataEntry() {
		return challengeDataEntry;
	}
	public void setChallengeDataEntry(String challengeDataEntry) {
		this.challengeDataEntry = challengeDataEntry;
	}
			
}
