package com.jetco.jpgcerttestproxy.object.response;

import java.util.Map;

import com.jetco.jpgcerttestproxy.object.request.RReq;

public abstract class RRes {
	
	protected String threeDSServerTransID;
	protected String acsTransID;
	protected String dsTransID;
	protected String messageExtension;
	protected String messageType;
	protected String messageVersion;
	protected String resultsStatus;
	
	public abstract  Map<String, String> validateMsg();
	
	public RRes mapRRes(RReq rReq) {

		this.setAcsTransID(rReq.getAcsTransID());
		this.setDsTransID(rReq.getDsTransID());
		// rRes210.setMessageID(rReq210.getMessageID());
		this.setMessageType("RRes");
		this.setMessageVersion(rReq.getMessageVersion());
		this.setThreeDSServerTransID(rReq.getThreeDSServerTransID());
		this.setResultsStatus("01");
		if (rReq.getChallengeCancel() != null
				&& (rReq.getChallengeCancel().equals("02") || rReq.getChallengeCancel().equals("05"))) {
			this.setResultsStatus("02");
		}

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
	public String getDsTransID() {
		return dsTransID;
	}
	public void setDsTransID(String dsTransID) {
		this.dsTransID = dsTransID;
	}
	public String getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(String messageExtension) {
		this.messageExtension = messageExtension;
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
	public String getResultsStatus() {
		return resultsStatus;
	}
	public void setResultsStatus(String resultsStatus) {
		this.resultsStatus = resultsStatus;
	}
	
}
