package com.jetco.jpgcerttestproxy.object.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.object.subfield.AcsRenderingType;
import com.jetco.jpgcerttestproxy.object.subfield.MessageExtension;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class RReq {

	protected String threeDSServerTransID; // 3DS Server Transaction ID
	protected String acsTransID; // ACS Transaction ID
	protected AcsRenderingType acsRenderingType; // ACS Rendering Type (For App-Base)
	protected String authenticationType; // Authentication Type
	protected String authenticationValue; // Authentication Value
	protected String challengeCancel; // Challenge Cancelation Indicator (Conditional)
	protected String dsTransID; // DS Transaction ID
	protected String eci; // Electronic Commerce Indicator (ECI)
	protected String interactionCounter; // Interaction Counter
	protected String messageCategory; // Message Category
	protected String messageType; // Message Type
	protected String messageVersion; // Message Version
	protected String transStatus; // Transaction Status
	protected String transStatusReason; // Transaction Status Reason
	protected String sdkTransID; // SDK transaction ID
	
	protected MessageExtension[] messageExtension;
	
	public abstract Map<String, String> validateMsg(PArq pArq, ARes aRes); 
	
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
	public AcsRenderingType getAcsRenderingType() {
		return acsRenderingType;
	}
	public void setAcsRenderingType(AcsRenderingType acsRenderingType) {
		this.acsRenderingType = acsRenderingType;
	}
	public String getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}
	public String getAuthenticationValue() {
		return authenticationValue;
	}
	public void setAuthenticationValue(String authenticationValue) {
		this.authenticationValue = authenticationValue;
	}
	public String getChallengeCancel() {
		return challengeCancel;
	}
	public void setChallengeCancel(String challengeCancel) {
		this.challengeCancel = challengeCancel;
	}
	public String getDsTransID() {
		return dsTransID;
	}
	public void setDsTransID(String dsTransID) {
		this.dsTransID = dsTransID;
	}
	public String getEci() {
		return eci;
	}
	public void setEci(String eci) {
		this.eci = eci;
	}
	public String getInteractionCounter() {
		return interactionCounter;
	}
	public void setInteractionCounter(String interactionCounter) {
		this.interactionCounter = interactionCounter;
	}
	public String getMessageCategory() {
		return messageCategory;
	}
	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
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
	public String getTransStatusReason() {
		return transStatusReason;
	}
	public void setTransStatusReason(String transStatusReason) {
		this.transStatusReason = transStatusReason;
	}
	public String getSdkTransID() {
		return sdkTransID;
	}
	public void setSdkTransID(String sdkTransID) {
		this.sdkTransID = sdkTransID;
	}
	
	public MessageExtension[] getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(MessageExtension[] messageExtension) {
		this.messageExtension = messageExtension;
	}
	
	

}
