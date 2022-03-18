package com.jetco.jpgcerttestproxy.object.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.subfield.CardRangeData;
import com.jetco.jpgcerttestproxy.object.subfield.MessageExtension;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class PRes {

	protected String threeDSServerTransID; // 3DS Server Transaction ID
	protected List<CardRangeData> cardRangeData; // Card Range Data (Conditional)
	protected String dsEndProtocolVersion; // DS End Protocol Version
	protected String dsStartProtocolVersion; // DS Start Protocol Version
	protected String dsTransID; // dsTransID
	protected String messageType; // Message Type
	protected String messageVersion; // Message Version Number
	protected String serialNum; // Serial Number (Optional)
	protected MessageExtension[] messageExtension;
	
	public abstract Map<String, String> validateMsg();
	
	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}
	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}
	public List<CardRangeData> getCardRangeData() {
		return cardRangeData;
	}
	public void setCardRangeData(List<CardRangeData> cardRangeData) {
		this.cardRangeData = cardRangeData;
	}
	public String getDsEndProtocolVersion() {
		return dsEndProtocolVersion;
	}
	public void setDsEndProtocolVersion(String dsEndProtocolVersion) {
		this.dsEndProtocolVersion = dsEndProtocolVersion;
	}
	public String getDsStartProtocolVersion() {
		return dsStartProtocolVersion;
	}
	public void setDsStartProtocolVersion(String dsStartProtocolVersion) {
		this.dsStartProtocolVersion = dsStartProtocolVersion;
	}
	public String getDsTransID() {
		return dsTransID;
	}
	public void setDsTransID(String dsTransID) {
		this.dsTransID = dsTransID;
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
	public MessageExtension[] getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(MessageExtension[] messageExtension) {
		this.messageExtension = messageExtension;
	}

	
}
