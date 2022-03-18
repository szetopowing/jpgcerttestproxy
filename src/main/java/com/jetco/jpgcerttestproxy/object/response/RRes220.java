package com.jetco.jpgcerttestproxy.object.response;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.request.RReq;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RRes220 extends RRes {
	
	static final Logger log = LoggerFactory.getLogger(RRes220.class);
	
	@Override
	public RRes mapRRes(RReq rReq) { 		
		RRes220 rRes220 = (RRes220) super.mapRRes(rReq);
		return rRes220;
	}

	public Map<String, String> validateMsg() {

		log.info("In rRes220.validateMsg!!");

		Map<String, String> erro = new HashMap<String, String>();

		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("RRes v2.1.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		/*
		 * no need to merge to production
		 * isValidMessageVersion210 -> isValidMessageVersion220
		 */
		if (!ThreeD20Utils.isValidMessageVersion220(messageVersion)) {
			log.error("RRes v2.1.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("RRes v2.1.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_RRES)) {
			log.error("RRes v2.1.0: Incorrect Message Type for RRes Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("RRes v2.1.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), ErrorResponse.ErrorCode201.getErrorDesc());
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("RRes v2.1.0: Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		// ACS Transaction ID
		if (StringUtils.isEmpty(acsTransID)) {
			log.error("RRes v2.1.0: ACS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), ErrorResponse.ErrorCode201.getErrorDesc());
			return erro;
		}

		if (!StringUtil.isValidUUID(acsTransID)) {
			log.error("RRes v2.1.0:  Invalid ACS Transaction ID [" + acsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		// DS Transaction ID
		if (StringUtils.isEmpty(dsTransID)) {
			log.error("RRes v2.1.0: DS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), ErrorResponse.ErrorCode201.getErrorDesc());
			return erro;
		}

		if (!StringUtil.isValidUUID(dsTransID)) {
			log.error("RRes v2.1.0:  Invalid DS Transaction ID [" + dsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		// Result Status
		if (StringUtils.isEmpty(resultsStatus)) {
			log.error("RRes v2.1.0: Result Status is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), ErrorResponse.ErrorCode201.getErrorDesc());
			return erro;
		}

		if (resultsStatus.length() != 2) {
			log.error("RRes v2.1.0: Invalid length in Result Status");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		if (!StringUtils.isNumeric(resultsStatus)) {
			log.error("RRes v2.1.0: Invalid Result Status [" + resultsStatus + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
			return erro;
		}

		return null;
	}

}
