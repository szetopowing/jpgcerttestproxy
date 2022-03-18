package com.jetco.jpgcerttestproxy.object.response;

import java.util.Map;

import com.jetco.jpgcerttestproxy.object.request.AReq;
import com.jetco.jpgcerttestproxy.object.request.AReq210;
import com.jetco.jpgcerttestproxy.object.subfield.AcsRenderingType;
import com.jetco.jpgcerttestproxy.object.subfield.BroadInfo;
import com.jetco.jpgcerttestproxy.object.subfield.MessageExtension;
import com.jetco.jpgcerttestproxy.threeDSenum.DeviceChannel;

public abstract class ARes {
	
	protected String threeDSServerTransID;
	protected String acsChallengeMandated;
	protected String acsOperatorID;
	protected String acsReferenceNumber;
	protected AcsRenderingType acsRenderingType;
	protected String acsSignedContent;
	protected String acsTransID;
	protected String acsURL;
	protected String authenticationType;
	protected String authenticationValue;
	protected BroadInfo broadInfo;
	protected String cardholderInfo;
	protected String dsReferenceNumber;
	protected String dsTransID;
	protected String eci;
	protected MessageExtension[] messageExtension;
	protected String messageType;
	protected String messageVersion;
	protected String sdkTransID;
	protected String transStatus;
	protected String transStatusReason;
	
	public abstract Map<String, String> validateMsg(AReq aReq);
	
	public PArs mapPArs(String deviceChannel) {
		PArs pArs = new PArs();
		pArs.setAcsChallengeMandated(this.getAcsChallengeMandated());	;
		pArs.setAcsOperatorID(this.getAcsOperatorID());
		pArs.setAcsReferenceNumber(this.getAcsReferenceNumber());
		pArs.setAcsRenderingType(this.getAcsRenderingType());
		if (deviceChannel.equals(DeviceChannel.APP.getValue())){
			pArs.setAcsSignedContent(this.getAcsSignedContent());
		}
		pArs.setAcsTransID(this.getAcsTransID());
		if (deviceChannel.equals(DeviceChannel.BRW.getValue())){
			pArs.setAcsURL(this.getAcsURL());
		}
		pArs.setAuthenticationType(this.getAuthenticationType());
		pArs.setAuthenticationValue(this.getAuthenticationValue());
		pArs.setBroadInfo(this.getBroadInfo());
		pArs.setCardholderInfo(this.getCardholderInfo());
		pArs.setDsReferenceNumber(this.getDsReferenceNumber());
		pArs.setDsTransID(this.getDsTransID());
		pArs.setEci(this.getEci());
		pArs.setMessageExtension(this.getMessageExtension());
		pArs.setMessageType("pArs");
		pArs.setMessageVersion(this.getMessageVersion());
		// pArs.setP_messageVersion(this.get);
		pArs.setSdkTransID(this.getSdkTransID());
		pArs.setTransStatus(this.getTransStatus());
		pArs.setTransStatusReason(this.getTransStatusReason());
		// pArs.setWhiteListStatus(whiteListStatus);
		// pArs.setWhiteListStatusSource(whiteListStatusSource);

		return pArs;
	}
		
	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}
	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}
	public String getAcsChallengeMandated() {
		return acsChallengeMandated;
	}
	public void setAcsChallengeMandated(String acsChallengeMandated) {
		this.acsChallengeMandated = acsChallengeMandated;
	}
	public String getAcsOperatorID() {
		return acsOperatorID;
	}
	public void setAcsOperatorID(String acsOperatorID) {
		this.acsOperatorID = acsOperatorID;
	}
	public String getAcsReferenceNumber() {
		return acsReferenceNumber;
	}
	public void setAcsReferenceNumber(String acsReferenceNumber) {
		this.acsReferenceNumber = acsReferenceNumber;
	}
	public AcsRenderingType getAcsRenderingType() {
		return acsRenderingType;
	}
	public void setAcsRenderingType(AcsRenderingType acsRenderingType) {
		this.acsRenderingType = acsRenderingType;
	}
	public String getAcsSignedContent() {
		return acsSignedContent;
	}
	public void setAcsSignedContent(String acsSignedContent) {
		this.acsSignedContent = acsSignedContent;
	}
	public String getAcsTransID() {
		return acsTransID;
	}
	public void setAcsTransID(String acsTransID) {
		this.acsTransID = acsTransID;
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
	public String getAuthenticationValue() {
		return authenticationValue;
	}
	public void setAuthenticationValue(String authenticationValue) {
		this.authenticationValue = authenticationValue;
	}
	public BroadInfo getBroadInfo() {
		return broadInfo;
	}
	public void setBroadInfo(BroadInfo broadInfo) {
		this.broadInfo = broadInfo;
	}
	public String getCardholderInfo() {
		return cardholderInfo;
	}
	public void setCardholderInfo(String cardholderInfo) {
		this.cardholderInfo = cardholderInfo;
	}
	public String getDsReferenceNumber() {
		return dsReferenceNumber;
	}
	public void setDsReferenceNumber(String dsReferenceNumber) {
		this.dsReferenceNumber = dsReferenceNumber;
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
	public MessageExtension[] getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(MessageExtension[] messageExtension) {
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
	public String getSdkTransID() {
		return sdkTransID;
	}
	public void setSdkTransID(String sdkTransID) {
		this.sdkTransID = sdkTransID;
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
	
}
