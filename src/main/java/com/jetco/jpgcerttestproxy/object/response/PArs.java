package com.jetco.jpgcerttestproxy.object.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.subfield.AcsRenderingType;
import com.jetco.jpgcerttestproxy.object.subfield.BroadInfo;
import com.jetco.jpgcerttestproxy.object.subfield.MessageExtension;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PArs {

	private String p_messageVersion;
	private String messageType;
	private String messageVersion;
	private String transStatus;
	private String dsReferenceNumber;
	private String acsReferenceNumber;
	private String acsTransID;
	private String dsTransID;
	private String authenticationValue;
	private AcsRenderingType acsRenderingType;
	private String acsOperatorID;
	private String acsSignedContent;
	private String acsURL;
	private String authenticationType;
	private String acsChallengeMandated;
	private String eci;
	private MessageExtension[] messageExtension;
	private String sdkTransID;
	private String transStatusReason;
	private String cardholderInfo;
	private BroadInfo broadInfo;
	private String acsDecConInd;
	private String whiteListStatus;
	private String whiteListStatusSource;
	
	public String getP_messageVersion() {
		return p_messageVersion;
	}
	public void setP_messageVersion(String p_messageVersion) {
		this.p_messageVersion = p_messageVersion;
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
	public String getDsReferenceNumber() {
		return dsReferenceNumber;
	}
	public void setDsReferenceNumber(String dsReferenceNumber) {
		this.dsReferenceNumber = dsReferenceNumber;
	}
	public String getAcsReferenceNumber() {
		return acsReferenceNumber;
	}
	public void setAcsReferenceNumber(String acsReferenceNumber) {
		this.acsReferenceNumber = acsReferenceNumber;
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
	public String getAuthenticationValue() {
		return authenticationValue;
	}
	public void setAuthenticationValue(String authenticationValue) {
		this.authenticationValue = authenticationValue;
	}
	public AcsRenderingType getAcsRenderingType() {
		return acsRenderingType;
	}
	public void setAcsRenderingType(AcsRenderingType acsRenderingType) {
		this.acsRenderingType = acsRenderingType;
	}
	public String getAcsOperatorID() {
		return acsOperatorID;
	}
	public void setAcsOperatorID(String acsOperatorID) {
		this.acsOperatorID = acsOperatorID;
	}
	public String getAcsSignedContent() {
		return acsSignedContent;
	}
	public void setAcsSignedContent(String acsSignedContent) {
		this.acsSignedContent = acsSignedContent;
	}
	public String getAcsURL() {
		return acsURL;
	}
	public void setAcsURL(String acsURL) {
		this.acsURL = acsURL;
	}
	public String getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}
	public String getAcsChallengeMandated() {
		return acsChallengeMandated;
	}
	public void setAcsChallengeMandated(String acsChallengeMandated) {
		this.acsChallengeMandated = acsChallengeMandated;
	}
	public String getEci() {
		return eci;
	}
	public void setEci(String eci) {
		this.eci = eci;
	}
	public MessageExtension[] getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(MessageExtension[] messageExtension) {
		this.messageExtension = messageExtension;
	}
	public String getSdkTransID() {
		return sdkTransID;
	}
	public void setSdkTransID(String sdkTransID) {
		this.sdkTransID = sdkTransID;
	}
	public String getTransStatusReason() {
		return transStatusReason;
	}
	public void setTransStatusReason(String transStatusReason) {
		this.transStatusReason = transStatusReason;
	}
	public String getCardholderInfo() {
		return cardholderInfo;
	}
	public void setCardholderInfo(String cardholderInfo) {
		this.cardholderInfo = cardholderInfo;
	}
	public BroadInfo getBroadInfo() {
		return broadInfo;
	}
	public void setBroadInfo(BroadInfo broadInfo) {
		this.broadInfo = broadInfo;
	}
	public String getAcsDecConInd() {
		return acsDecConInd;
	}
	public void setAcsDecConInd(String acsDecConInd) {
		this.acsDecConInd = acsDecConInd;
	}
	public String getWhiteListStatus() {
		return whiteListStatus;
	}
	public void setWhiteListStatus(String whiteListStatus) {
		this.whiteListStatus = whiteListStatus;
	}
	public String getWhiteListStatusSource() {
		return whiteListStatusSource;
	}
	public void setWhiteListStatusSource(String whiteListStatusSource) {
		this.whiteListStatusSource = whiteListStatusSource;
	}
	
			
}
