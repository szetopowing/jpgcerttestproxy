package com.jetco.jpgcerttestproxy.object.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.object.subfield.AcsRenderingType;
import com.jetco.jpgcerttestproxy.object.subfield.MessageExtension;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
	
}
