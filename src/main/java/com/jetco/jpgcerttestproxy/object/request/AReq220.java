package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
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
public class AReq220 extends AReq{

	private String payTokenSource;
	private String threeDSRequestorDecMaxTime;
	private String threeDSRequestorDecReqInd;
	private String whiteListStatus;
	private String whiteListStatusSource;
	
	static final Logger log = LoggerFactory.getLogger(AReq210.class);
	
	@Override
	public AReq mapPArq(PArq pArq, String testingEnv, String _threeDSServerRefNumber, String visaThreeDSServerRefNumber) {		
		AReq220 aReq220 = new AReq220();
		aReq220 = (AReq220) super.mapPArq(pArq, testingEnv, _threeDSServerRefNumber, visaThreeDSServerRefNumber);
		aReq220.setPayTokenSource(pArq.getPayTokenSource());
		aReq220.setThreeDSRequestorDecMaxTime(pArq.getThreeDSRequestorDecMaxTime());
		aReq220.setWhiteListStatus(pArq.getWhiteListStatus());
		aReq220.setWhiteListStatusSource(pArq.getWhiteListStatusSource());
		aReq220.setThreeDSRequestorDecReqInd(pArq.getThreeDSRequestorDecReqInd());
		return aReq220;		
	}

	public Map<String, String> validateMsg() {

		Map<String, String> erro = new HashMap<String, String>();

		erro = super.validateMsg();
		
		if (erro != null) {
			return erro;
		}
		
		// Whitelist Status
		if (whiteListStatus != null) {
			if (StringUtils.isEmpty(whiteListStatus)) {
				log.error("AReq v2.2.0: White list status is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "whiteListStatusSource");
				return erro;
			}

			if (whiteListStatus.length() != 1) {
				log.error("AReq v2.2.0: Invalid length in Whitelist Status");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatus(whiteListStatus, messageVersion)) {
				log.error("AReq v2.2.0: Invalid Whitelist Status [" + whiteListStatus + "], messageVersion ["
						+ messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}
		}

		// Whitelist Status Sources
		if (whiteListStatusSource != null) {
			if (whiteListStatusSource.length() != 2) {
				log.error("AReq v2.2.0: Invalid length in Whitelist Status Sources");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatusSource(whiteListStatusSource, messageVersion)) {
				log.error("AReq v2.2.0: Invalid Whitelist Status Source [" + whiteListStatusSource
						+ "], messageVersion [" + messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}
		}

		// 3DS requestor decoupled max time  
		if (threeDSRequestorDecReqInd != null && threeDSRequestorDecReqInd.equals("Y")) {
			if (StringUtils.isEmpty(threeDSRequestorDecMaxTime)) {
				log.error("AReq v2.2.0: 3DS Requestor Decoupled Maximum time is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorDecMaxTime");
				return erro;
			}
		}
		
		return null;
	}

}
