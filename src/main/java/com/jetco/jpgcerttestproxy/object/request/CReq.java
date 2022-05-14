package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class CReq {
	
	static final Logger log = LoggerFactory.getLogger(CReq.class);
	
	protected String threeDSServerTransID;						// 3DS Server Transaction ID
	protected String acsTransID;									// ACS Transaction ID
	protected String messageType;									// Message Type
	protected String messageVersion;								// Message Version
	// Field required for Browser Mode CReq message
	protected String challengeWindowSize;							// Challenge Window Size
	protected String challengeDataEntry;							// Challenge Data Entry
	
	//public abstract Map<String, String> validateMsg();
	
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
	
	public Map<String, String> validateMsg() {

		log.info("In cReq validateMsg!!");
	
		Map<String, String> erro = new HashMap<String, String>();
	
		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("CReq : Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}
	
		if (!ThreeD20Utils.isValidMessageVersion210(messageVersion)) {
			log.error("CReq : Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}
	
		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("CReq : Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}
	
		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_CREQ)) {
			log.error("CReq : Incorrect Message Type for CRes Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");
			return erro;
		}
	
		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("CReq : 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}
	
		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("CReq : Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}
	
		// ACS Transaction ID
		if (StringUtils.isEmpty(acsTransID)) {
			log.error("CReq : ACS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsTransID");
			return erro;
		}
	
		if (!StringUtil.isValidUUID(acsTransID)) {
			log.error("CRes :  Invalid ACS Transaction ID [" + acsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsTransID");
			return erro;
		}
	
		// Challenge Window Size
		if (StringUtils.isEmpty(challengeWindowSize)) {
			log.error("CReq : Challenge Window Size is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "challengeWindowSize");
			return erro;
		}
	
		if (challengeWindowSize.length() != 2) {
			log.error("CReq : Invalid length in Challenge Window Size is missing");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeWindowSize");
			return erro;
		}
	
		if (!challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_250X400)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_390X400)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_500X600)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_600X400)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_FULL_SCREEN)) {
	
			log.error("CReq : Invalid Challenge Window Size [" + challengeWindowSize + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeWindowSize");
		}
	
		return null;
	}
	
			
}
