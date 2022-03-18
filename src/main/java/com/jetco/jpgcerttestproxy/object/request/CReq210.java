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

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CReq210 extends CReq {

	static final Logger log = LoggerFactory.getLogger(CReq210.class);
	
	@Override
	public CReq mapCReqFromARes(ARes aRes) {		
		CReq210 cReq210 = (CReq210) super.mapCReqFromARes(aRes);
		return cReq210;		
	}
	
	@Override
	public CReq mapCReqFromPGcq(PGcq pGcq) {
		CReq210 cReq210 = (CReq210) super.mapCReqFromPGcq(pGcq);
		return cReq210;	
	}

	@Override
	public Map<String, String> validateMsg() {

		log.info("In cReq210.validateMsg!!");
	
		Map<String, String> erro = new HashMap<String, String>();
	
		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("CReq v2.1.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}
	
		if (!ThreeD20Utils.isValidMessageVersion210(messageVersion)) {
			log.error("CReq v2.1.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}
	
		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("CReq v2.1.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}
	
		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_CREQ)) {
			log.error("CReq v2.1.0: Incorrect Message Type for CRes Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");
			return erro;
		}
	
		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("CReq v2.1.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}
	
		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("CReq v2.1.0: Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}
	
		// ACS Transaction ID
		if (StringUtils.isEmpty(acsTransID)) {
			log.error("CReq v2.1.0: ACS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsTransID");
			return erro;
		}
	
		if (!StringUtil.isValidUUID(acsTransID)) {
			log.error("CRes v2.1.0:  Invalid ACS Transaction ID [" + acsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsTransID");
			return erro;
		}
	
		// Challenge Window Size
		if (StringUtils.isEmpty(challengeWindowSize)) {
			log.error("CReq v2.1.0: Challenge Window Size is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "challengeWindowSize");
			return erro;
		}
	
		if (challengeWindowSize.length() != 2) {
			log.error("CReq v2.1.0: Invalid length in Challenge Window Size is missing");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeWindowSize");
			return erro;
		}
	
		if (!challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_250X400)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_390X400)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_500X600)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_600X400)
				&& !challengeWindowSize.equals(ThreeD20Utils.CHALLENGE_WINDOW_SIZE_FULL_SCREEN)) {
	
			log.error("CReq v2.1.0: Invalid Challenge Window Size [" + challengeWindowSize + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeWindowSize");
		}
	
		return null;
	}

}
