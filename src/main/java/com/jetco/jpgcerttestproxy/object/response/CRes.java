package com.jetco.jpgcerttestproxy.object.response;

import java.util.Map;

public abstract class CRes {

	protected String threeDSServerTransID; // 3DS Server Transaction ID
	protected String acsTransID; // ACS Transaction ID
	protected String messageType; // Message Type
	protected String messageVersion; // Message Version Number
	protected String transStatus; // Transaction Status
	protected String sdkTransID; // SDK Transaction ID
	protected String challengeCompletionInd; // Challenge Completion Indicator
	protected String acsUiType; // ACS UI Type
	protected String challengeInfoHeader; // Challenge Information Header
	protected String challengeInfoLabel; // Challenge Information Label
	protected String challengeInfoText; // Challenge Information Text
	protected String submitAuthenticationLabel; // Submit Authentication Label
	protected Map<String, String> challengeSelectInfo; // Challenge Selection Information
	
	public abstract Map<String, String> validateMsg();
	
	
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
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getSdkTransID() {
		return sdkTransID;
	}
	public void setSdkTransID(String sdkTransID) {
		this.sdkTransID = sdkTransID;
	}
	public String getChallengeCompletionInd() {
		return challengeCompletionInd;
	}
	public void setChallengeCompletionInd(String challengeCompletionInd) {
		this.challengeCompletionInd = challengeCompletionInd;
	}
	public String getAcsUiType() {
		return acsUiType;
	}
	public void setAcsUiType(String acsUiType) {
		this.acsUiType = acsUiType;
	}
	public String getChallengeInfoHeader() {
		return challengeInfoHeader;
	}
	public void setChallengeInfoHeader(String challengeInfoHeader) {
		this.challengeInfoHeader = challengeInfoHeader;
	}
	public String getChallengeInfoLabel() {
		return challengeInfoLabel;
	}
	public void setChallengeInfoLabel(String challengeInfoLabel) {
		this.challengeInfoLabel = challengeInfoLabel;
	}
	public String getChallengeInfoText() {
		return challengeInfoText;
	}
	public void setChallengeInfoText(String challengeInfoText) {
		this.challengeInfoText = challengeInfoText;
	}
	public String getSubmitAuthenticationLabel() {
		return submitAuthenticationLabel;
	}
	public void setSubmitAuthenticationLabel(String submitAuthenticationLabel) {
		this.submitAuthenticationLabel = submitAuthenticationLabel;
	}
	public Map<String, String> getChallengeSelectInfo() {
		return challengeSelectInfo;
	}
	public void setChallengeSelectInfo(Map<String, String> challengeSelectInfo) {
		this.challengeSelectInfo = challengeSelectInfo;
	}
	
	

}
