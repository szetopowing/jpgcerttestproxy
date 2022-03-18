package com.jetco.jpgcerttestproxy.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.factory.ThreeDSMsgFactory;
import com.jetco.jpgcerttestproxy.object.request.AReq;
import com.jetco.jpgcerttestproxy.object.request.AReq210;
import com.jetco.jpgcerttestproxy.object.request.CReq210;
import com.jetco.jpgcerttestproxy.object.request.PReq;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.object.response.ARes210;
import com.jetco.jpgcerttestproxy.object.response.CRes210;
import com.jetco.jpgcerttestproxy.object.response.ErrorMsg;
import com.jetco.jpgcerttestproxy.object.response.PArs;
import com.jetco.jpgcerttestproxy.object.response.PRes;
import com.jetco.jpgcerttestproxy.services.inf.RequestMappingServiceInf;
import com.jetco.jpgcerttestproxy.services.inf.Visa3DMessageServiceInf;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;

@Service
public class Visa3DMessageService implements Visa3DMessageServiceInf {

	static final Logger log = LoggerFactory.getLogger(Visa3DMessageService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ThreeDSMsgFactory threeDSMsgFactory;

	public ResponseEntity<Object> sendAReq(JSONObject jSONObject, String dsUrl, String testCaseResultId,
			String messageVersion) {

		try {

			AReq aReq = threeDSMsgFactory.createAReqFromMsgVersion(jSONObject, messageVersion);
			aReq.setThreeDSServerTransID(UUID.randomUUID().toString());
			
			log.info("AReq Txn id =" + aReq.getThreeDSServerTransID());

			aReq.setThreeDSServerTransID(UUID.randomUUID().toString());
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap = aReq.validateMsg();

			if (errorMap == null) { // no error for AReq210

				// ARes210 aRes210 = restTemplate.postForObject(dsUrl, aReq, ARes210.class);
				ARes aRes = threeDSMsgFactory.createAResFromMsgVersion(dsUrl, aReq, messageVersion);

				if (aRes == null) {
					ErrorMsg errorMsg = new ErrorMsg(aReq.getThreeDSServerTransID(),
							ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
							ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(),
							aReq.getMessageType());
					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}
				log.info("ARes210 =" + aRes.toString());

				if (aRes.getMessageType().equals("Erro")) { // ARes returns Erro
					ErrorMsg errorMsg = new ErrorMsg();
					errorMsg.setThreeDSServerTransID(aRes.getThreeDSServerTransID());
					errorMsg.setAcsTransID(aRes.getAcsTransID());
					errorMsg.setDsTransID(aRes.getDsTransID());
					// errorMsg.setErrorCode(aRes210.get);
					// errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
					// errorMsg.setErrorDetail(entry.getValue());
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(aRes.getMessageVersion());
					errorMsg.setSdkTransID(aRes.getSdkTransID());
					errorMsg.setErrorComponent("S");

					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}

				errorMap = aRes.validateMsg(aReq);

				if (errorMap != null) { // has error for ARes
					ErrorMsg errorMsg = new ErrorMsg();
					if (!StringUtils.isEmpty(aRes.getThreeDSServerTransID())) {
						errorMsg.setThreeDSServerTransID(aRes.getThreeDSServerTransID());
					} else {
						errorMsg.setThreeDSServerTransID(aReq.getThreeDSServerTransID());
					}
					errorMsg.setDsTransID(aRes.getDsTransID());
					Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
					errorMsg.setErrorCode(entry.getKey());
					errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
					errorMsg.setErrorDetail(entry.getValue());
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(aReq.getMessageVersion());
					// if (aRes210.getSdkTransID() != null) {
					errorMsg.setSdkTransID(aRes.getSdkTransID());
					// }
					errorMsg.setErrorComponent("S");
					errorMsg.setErrorMessageType("ARes");

					// restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					String reply = restTemplate.postForObject(dsUrl, errorMsg, String.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);

				}

//				PArs pArs = aRes.mapPArs(aReq.getDeviceChannel());
				return new ResponseEntity<Object>(aRes, HttpStatus.OK);

				// return new ResponseEntity<Object>(pArs, HttpStatus.OK);
			} else { // has error for AReq210
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setThreeDSServerTransID(aReq.getThreeDSServerTransID());
				// errorMsg.setAcsTransID(aReq210.getac);
				errorMsg.setDsTransID(aReq.getDsTransID());
				Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
				errorMsg.setErrorCode(entry.getKey());
				errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
				errorMsg.setErrorDetail(entry.getValue());
				errorMsg.setMessageType("Erro");
				errorMsg.setMessageVersion(aReq.getMessageVersion());
				errorMsg.setSdkTransID(aReq.getSdkTransID());
				errorMsg.setErrorComponent("S");

				restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
				return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);

			}
			/*
			 * } catch (JsonMappingException e) { log.error(" has error :" + e.toString());
			 * ErrorMsg errorMsg = new ErrorMsg(aReq.getThreeDSServerTransID(),
			 * ErrorResponse.ErrorCode101.getErrorCode(), "S",
			 * ErrorResponse.ErrorCode101.getErrorDesc(),
			 * ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(),
			 * aReq.getMessageType()); restTemplate.postForObject(dsUrl, errorMsg,
			 * ErrorMsg.class); return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
			 * } catch (JsonProcessingException e) { ErrorMsg errorMsg = new
			 * ErrorMsg(aReq.getThreeDSServerTransID(),
			 * ErrorResponse.ErrorCode101.getErrorCode(), "S",
			 * ErrorResponse.ErrorCode101.getErrorDesc(),
			 * ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(),
			 * aReq.getMessageType()); restTemplate.postForObject(dsUrl, errorMsg,
			 * ErrorMsg.class); return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
			 * } catch (RestClientException e) { ErrorMsg errorMsg = new
			 * ErrorMsg(aReq.getThreeDSServerTransID(),
			 * ErrorResponse.ErrorCode101.getErrorCode(), "S",
			 * ErrorResponse.ErrorCode101.getErrorDesc(),
			 * ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(),
			 * aReq.getMessageType()); restTemplate.postForObject(dsUrl, errorMsg,
			 * ErrorMsg.class); return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
			 */
		} catch (Exception e) {
			return null;
		}
	}

	public ResponseEntity<Object> sendPReq(JSONObject jSONObject, String dsUrl, String testCaseResultId,
			String messageVersion) {

		try {

			PReq pReq = threeDSMsgFactory.createPReqFromMsgVersion(jSONObject, messageVersion);

			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap = pReq.validateMsg();

			if (errorMap == null) { // no error for PReq210
				
				PRes pRes = threeDSMsgFactory.createPResFromMsgVersion(dsUrl, pReq, messageVersion);

				if (pRes == null) {
					ErrorMsg errorMsg = new ErrorMsg(pReq.getThreeDSServerTransID(),
							ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
							ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pReq.getMessageVersion(),
							pReq.getMessageType());
					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}
				log.info("PRes =" + pRes.toString());

				if (pRes.getMessageType().equals("Erro")) { // PRes returns Erro
					ErrorMsg errorMsg = new ErrorMsg();
					errorMsg.setThreeDSServerTransID(pRes.getThreeDSServerTransID());					
					errorMsg.setDsTransID(pRes.getDsTransID());					
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(pRes.getMessageVersion());					
					errorMsg.setErrorComponent("S");

					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}

				errorMap = pRes.validateMsg();

				if (errorMap != null) { // has error for PRes
					ErrorMsg errorMsg = new ErrorMsg();
					if (!StringUtils.isEmpty(pRes.getThreeDSServerTransID())) {
						errorMsg.setThreeDSServerTransID(pRes.getThreeDSServerTransID());
					} else {
						errorMsg.setThreeDSServerTransID(pReq.getThreeDSServerTransID());
					}
					errorMsg.setDsTransID(pRes.getDsTransID());
					Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
					errorMsg.setErrorCode(entry.getKey());
					errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
					errorMsg.setErrorDetail(entry.getValue());
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(pReq.getMessageVersion());
					errorMsg.setErrorComponent("S");
					errorMsg.setErrorMessageType("ARes");
					
					restTemplate.postForObject(dsUrl, errorMsg, String.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);

				}

				return new ResponseEntity<Object>(pRes, HttpStatus.OK);

			} else { // has error for PReq210
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setThreeDSServerTransID(pReq.getThreeDSServerTransID());			
				Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
				errorMsg.setErrorCode(entry.getKey());
				errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
				errorMsg.setErrorDetail(entry.getValue());
				errorMsg.setMessageType("Erro");
				errorMsg.setMessageVersion(pReq.getMessageVersion());				
				errorMsg.setErrorComponent("S");

				restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
				return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);

			}

		} catch (Exception e) {
			return null;
		}
	}

}
