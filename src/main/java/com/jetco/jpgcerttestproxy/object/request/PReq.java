package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public abstract class PReq {
	
	static final Logger log = LoggerFactory.getLogger(PReq.class);

	protected String threeDSRequestorID;
	protected String threeDSRequestorURL;
	protected String p_isSerialNumPresent;
	protected String threeDSServerRefNumber;
	protected String threeDSServerOperatorID;
	protected String threeDSServerTransID;
	protected String messageType;
	protected String messageVersion;
	protected String serialNum;
	
	//public abstract Map<String, String> validateMsg();
	
	public Map<String, String> validateMsg() {
		
		log.info("In pReq validateMsg!!");

		Map<String, String> erro = new HashMap<String, String>();

		// 3DS Server Reference Number
		if (StringUtils.isEmpty(threeDSServerRefNumber)) {
			log.error("PReq : 3DS Server Reference Number is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerRefNumber");
			return erro;
		}

		if (threeDSServerRefNumber.length() > 32) {
			log.error("PReq : Invalid length in 3DS Server Reference Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerRefNumber");
			return erro;
		}

		// 3DS Server Operator ID (Optional field)
		if (!StringUtils.isEmpty(threeDSServerOperatorID)) {
			if (threeDSServerOperatorID.length() > 32) {
				log.error("PReq : Invalid length in 3DS Server Operator ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerOperatorID");
				return erro;
			}
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("PReq : 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("PReq : Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("PReq : Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion210(messageVersion)) {
			log.error("PReq : Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("PReq : Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_PREQ)) {
			log.error("PReq : Incorrect Message Type for PReq Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");
			return erro;
		}

		// Serial Number
		if (!StringUtils.isEmpty(serialNum)) {
			if (serialNum.length() > 20) {
				log.error("PReq : Invalid length in Serial Number");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "serialNum");
				return erro;
			}

			//if (!StringUtils.isAlphaNumeric(serialNum)) {
			if (!StringUtils.isAlphanumeric(serialNum)) {
				log.error("PReq : Invalid length in Serial Number");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "serialNum");
				return erro;
			}
		}

		return null;
	}

}
