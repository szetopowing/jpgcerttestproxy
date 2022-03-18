package com.jetco.jpgcerttestproxy.object.response;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.threeDSenum.MessageType;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CRes210 extends CRes {

	static final Logger log = LoggerFactory.getLogger(CRes210.class);
	
	@Override
	public Map<String, String> validateMsg() {

		

		Map<String, String> erro = new HashMap<String, String>();

		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("CRes v2.1.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion210(messageVersion)) {
			log.error("CRes v2.1.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode102.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("CRes v2.1.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_CRES)) {
			log.error("CRes v2.1.0: Incorrect Message Type for CRes Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");
			return erro;
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("CRes v2.1.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("CRes v2.1.0: Invalid 3DS Server Transaction ID");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		// ACS Transaction ID
		if (StringUtils.isEmpty(acsTransID)) {
			log.error("CRes v2.1.0: ACS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(acsTransID)) {
			log.error("CRes v2.1.0:  Invalid ACS Transaction ID");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsTransID");
			return erro;
		}

		// Transaction Status
		if (!StringUtils.isEmpty(transStatus)) {
			if (!ThreeD20Utils.isValidTransStatus(transStatus, messageType)) {
				log.error("CRes v2.1.0: Invalid Transaction Status [" + transStatus + "]");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatus");
				return erro;
			}
		}

		// SDK Transaction ID
		if (!StringUtils.isEmpty(sdkTransID)) {
			if (!StringUtil.isValidUUID(sdkTransID)) {
				log.error("CRes v2.1.0: Invalid SDK Transaction ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "sdkTransID");
				return erro;
			}
		}

		// Challenge Completion Indicator
		if (challengeCompletionInd != null) {
			if (challengeCompletionInd.length() != 1) {
				log.error("CRes v2.1.0: Invalid length in Challenge Completion Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeCompletionInd");
				return erro;
			}

			if (!challengeCompletionInd.equals(ThreeD20Utils.YES_IND)
					&& !challengeCompletionInd.equals(ThreeD20Utils.NO_IND)) {
				log.error("CRes v2.1.0: Invalid Challenge Completion Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeCompletionInd");
				return erro;
			}
		}

		// ACS UI Type
		if (acsUiType != null) {
			if (!ThreeD20Utils.isValidACSUiType(acsUiType)) {
				log.error("CRes v2.1.0: Invalid ACS UI Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsUiType");
				return erro;
			}
		}

		// Challenge Information Header
		if (challengeInfoHeader != null) {
			if (challengeInfoHeader.length() <= 0 || challengeInfoHeader.length() > 45) {
				log.error("CRes v2.1.0: Invalid length in Challenge Information Header");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeInfoHeader");
				return erro;
			}
		}

		// Challenge Information Label
		if (challengeInfoLabel != null) {
			if (challengeInfoLabel.length() <= 0 || challengeInfoLabel.length() > 45) {
				log.error("CRes v2.1.0: Invalid length in Challenge Information Label");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeInfoLabel");
				return erro;
			}
		}

		// Challenge Information Text
		if (challengeInfoText != null) {
			if (challengeInfoText.length() <= 0 || challengeInfoText.length() > 350) {
				log.error("CRes v2.1.0: Invalid length in Challenge Information Text");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeInfoText");
				return erro;
			}
		}

		// Submit Authentication Label
		if (submitAuthenticationLabel != null) {
			if (submitAuthenticationLabel.length() <= 0 || submitAuthenticationLabel.length() > 45) {
				log.error("CRes v2.1.0: Invalid length in Submit Authentication Label");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "submitAuthenticationLabel");
				return erro;
			}
		} else {
			if ((acsUiType != null && (acsUiType.equals(ThreeD20Utils.ACS_UI_TEMPLATE_TEXT)
					|| acsUiType.equals(ThreeD20Utils.ACS_UI_TEMPLATE_SINGLE_SELECT)
					|| acsUiType.equals(ThreeD20Utils.ACS_UI_TEMPLATE_MULTI_SELECT)))) {
				log.error("CRes v2.1.0: Empty Submit Authentication Label");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsUiType");
				return erro;
			}
		}

		return null;
	}

	
}
