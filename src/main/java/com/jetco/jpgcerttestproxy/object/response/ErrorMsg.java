package com.jetco.jpgcerttestproxy.object.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMsg {
	
	private String threeDSServerTransID;
	private String acsTransID;
	private String dsTransID;
	private String errorCode;
	private String errorComponent;
	private String errorDescription;
	private String errorDetail;
	private String errorMessageType;
	private String messageType;
	private String messageVersion;
	private String sdkTransID;
	
	
	public ErrorMsg() {
		super();
	}

	public ErrorMsg(String threeDSServerTransID, String errorCode,
			String errorComponent, String errorDescription, String errorDetail,
			String messageType, String messageVersion, String errorMessageType) {
		super();
		this.threeDSServerTransID = threeDSServerTransID;
		this.errorCode = errorCode;
		this.errorComponent = errorComponent;
		this.errorDescription = errorDescription;
		this.errorDetail = errorDetail;
		this.messageType = messageType;
		this.messageVersion = messageVersion;
		this.errorMessageType = errorMessageType;

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
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorComponent() {
		return errorComponent;
	}
	public void setErrorComponent(String errorComponent) {
		this.errorComponent = errorComponent;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getErrorDetail() {
		return errorDetail;
	}
	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	public String getErrorMessageType() {
		return errorMessageType;
	}
	public void setErrorMessageType(String errorMessageType) {
		this.errorMessageType = errorMessageType;
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

	@Override
	public String toString() {
		return "ErrorMsg [threeDSServerTransID=" + threeDSServerTransID + ", acsTransID=" + acsTransID + ", dsTransID="
				+ dsTransID + ", errorCode=" + errorCode + ", errorComponent=" + errorComponent + ", errorDescription="
				+ errorDescription + ", errorDetail=" + errorDetail + ", errorMessageType=" + errorMessageType
				+ ", messageType=" + messageType + ", messageVersion=" + messageVersion + ", sdkTransID=" + sdkTransID
				+ "]";
	}
	
	
	
}
