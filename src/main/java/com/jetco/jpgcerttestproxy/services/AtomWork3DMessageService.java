package com.jetco.jpgcerttestproxy.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.exception.Invalid3DMsgVersionException;
import com.jetco.jpgcerttestproxy.factory.ThreeDSMsgFactory;
import com.jetco.jpgcerttestproxy.object.request.AReq;
import com.jetco.jpgcerttestproxy.object.request.AReq210;
import com.jetco.jpgcerttestproxy.object.request.CReq;
import com.jetco.jpgcerttestproxy.object.request.CReq210;
import com.jetco.jpgcerttestproxy.object.request.PArq;
import com.jetco.jpgcerttestproxy.object.request.PArqCache;
import com.jetco.jpgcerttestproxy.object.request.PGcq;
import com.jetco.jpgcerttestproxy.object.request.PPrq;
import com.jetco.jpgcerttestproxy.object.request.PReq;
import com.jetco.jpgcerttestproxy.object.request.PReq210;
import com.jetco.jpgcerttestproxy.object.request.RReq;
import com.jetco.jpgcerttestproxy.object.request.RReq210;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.object.response.ARes210;
import com.jetco.jpgcerttestproxy.object.response.CRes;
import com.jetco.jpgcerttestproxy.object.response.CRes210;
import com.jetco.jpgcerttestproxy.object.response.ErrorMsg;
import com.jetco.jpgcerttestproxy.object.response.PArs;
import com.jetco.jpgcerttestproxy.object.response.PGcs;
import com.jetco.jpgcerttestproxy.object.response.PPrs;
import com.jetco.jpgcerttestproxy.object.response.PRes;
import com.jetco.jpgcerttestproxy.object.response.PRes210;
import com.jetco.jpgcerttestproxy.object.response.RRes;
import com.jetco.jpgcerttestproxy.object.response.RRes210;
import com.jetco.jpgcerttestproxy.services.inf.RequestMappingServiceInf;
import com.jetco.jpgcerttestproxy.services.inf.AtomWork3DMessageServiceInf;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.threeDSenum.MessageType;

@Service
public class AtomWork3DMessageService implements AtomWork3DMessageServiceInf {

	static final Logger log = LoggerFactory.getLogger(AtomWork3DMessageService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	RequestMappingServiceInf requestMappingServiceInf;
	
	@Autowired
	ThreeDSMsgFactory threeDSMsgFactory; 
	
	@Value("${testingEnv}")
	private String testingEnv;

	@Value("${atomWork.threeDSServerRefNumber}")
	private String atomWorkThreeDSServerRefNumber;
	
	@Value("${visa.threeDSServerRefNumber}")
	private String visaThreeDSServerRefNumber;
	
	@Value("${master.threeDSServerRefNumber}")
	private String masterDSServerRefNumber;
	
	Map<String, Object> cache3DMsg = new HashMap<String, Object>();
	Map<String, String> lastSerialNumMap = new HashMap<String, String>();

	public ResponseEntity<Object> validatePArq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion) {
		
		PArq pArq = new PArq();
		
		//ThreeDSMsgFactory threeDSMsgFactory = new ThreeDSMsgFactory();
		String threeDSServerRefNumber = "";
		
		if (testingEnv.equals("ATOMWORK")) {
			threeDSServerRefNumber = visaThreeDSServerRefNumber;
		} else if (testingEnv.equals("MASTER")) {
			threeDSServerRefNumber = masterDSServerRefNumber;
		}
		
		
		AReq aReq = threeDSMsgFactory.createAReqFromMsgVersion(null, messageVersion);

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			pArq = objectMapper.readValue(jSONObject.toString(), PArq.class);

			if (cache3DMsg.get(testCaseResultId + "_pArq") == null) {
				cache3DMsg.put(testCaseResultId + "_pArq", pArq);
			}
	
			aReq = aReq.mapPArq(pArq, testingEnv, threeDSServerRefNumber, visaThreeDSServerRefNumber);
			
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap = aReq.validateMsg();
			
			if (errorMap == null) { // no error for AReq210

				ARes aRes = threeDSMsgFactory.createAResFromMsgVersion(dsUrl, aReq, messageVersion);
								
				if (aRes == null) {
					ErrorMsg errorMsg = new ErrorMsg(aReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
							ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(), aReq.getMessageType());
					//restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);			
				}
				log.info("ARes210 =" + aRes.toString());

				if (aRes.getMessageType().equals("Erro")) { // ARes210 returns Erro
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

				if (errorMap != null) { // has error for ARes210
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
					//if (aRes210.getSdkTransID() != null) {
						errorMsg.setSdkTransID(aRes.getSdkTransID());
					//}
					errorMsg.setErrorComponent("S");
					errorMsg.setErrorMessageType("ARes");

					//restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					String reply = restTemplate.postForObject(dsUrl, errorMsg, String.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);

				}

				cache3DMsg.put(testCaseResultId + "_ARes", aRes);
				PArs pArs = aRes.mapPArs(aReq.getDeviceChannel());
				return new ResponseEntity<Object>(pArs, HttpStatus.OK);
			} else { // has error for AReq210
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setThreeDSServerTransID(aReq.getThreeDSServerTransID());
				// errorMsg.setAcsTransID(aReq210.getac);
				errorMsg.setDsTransID(aReq.getDsTransID());
				Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
				errorMsg.setErrorCode(entry.getKey());
				errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
				errorMsg.setErrorDetail(entry.getValue());
				errorMsg.setErrorMessageType("AReq");
				errorMsg.setMessageType("Erro");
				errorMsg.setMessageVersion(aReq.getMessageVersion());
				errorMsg.setSdkTransID(aReq.getSdkTransID());
				errorMsg.setErrorComponent("S");

				restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
				return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				
			}
		} catch (JsonMappingException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(aReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(), aReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);			
		} catch (JsonProcessingException e) {
			ErrorMsg errorMsg = new ErrorMsg(aReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(), aReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);			
		} catch (RestClientException e) {
			ErrorMsg errorMsg = new ErrorMsg(aReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(), aReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);			
		} catch (Exception e) {
			ErrorMsg errorMsg = new ErrorMsg(aReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", aReq.getMessageVersion(), aReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
			
		}
	}

	public ResponseEntity<Object> validatePGcq(JSONObject jSONObject, String dsUrl, String testCaseResultId) {
		
		PArq pArq = new PArq();
		PGcq pGcq = new PGcq();
		
		CReq cReq =null;

		try {
			
			pArq = (PArq) cache3DMsg.get(testCaseResultId + "_pArq");
			//log.info("pArq = " + pArq.toString());
						
			ObjectMapper objectMapper = new ObjectMapper();
			pGcq = objectMapper.readValue(jSONObject.toString(), PGcq.class);
			
			cReq = threeDSMsgFactory.createCReqFromMsgVersion(pGcq.getMessageVersion());
			//cReq210 = requestMappingServiceInf.mapPGcq(pGcq);
			cReq = cReq.mapCReqFromPGcq(pGcq);

			Map<String, String> errorMap = new HashMap<String, String>();
			//errorMap = cReq210.validateMsg(cReq210);
			
			//CRes210 cRes210 = restTemplate.postForObject(dsUrl, cReq, CRes210.class);
			//log.info("cRes210 =" + cRes210.toString());
			
			String line1 = "<form action=\'";
			String line2 = "\' method=\'post\'><input type=\'hidden\' name=\'creq\' value=\'";
			String line3 ="\' /><input type=\'hidden\' name=\'threeDSSessionData\'value=\'";
			String line4 = "\' /></form>";
			//String threeDSSessionData = ThreeD20Utils.formThreeDSSessionData(jsonDetails.getTxnID(), cReq210.getThreeDSServerTransID() , cReq.getMessageID());
			String threeDSSessionData = cReq.getThreeDSServerTransID();
			
			ObjectMapper mapper = new ObjectMapper();
			String cReqJsonString  = mapper.writeValueAsString(cReq);
			String encodedCReqJsonString = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(cReqJsonString.getBytes());

			// String threeDSSessionData = pGcq.getThreeDSServerTransID() + ";" +
			// pGcq.getThreeDSServerTransID() + ";" + messageID;

			// cReq.setThreeDSSessionData(threeDSSessionData);
			// cReq.setCreq(java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(cReq210JsonString.getBytes()));
			
			String htmlCreq = line1 + "https://xml-ds.3dstest.com/simulator/simulation/acs/challenge" + line2 + encodedCReqJsonString + line3 + threeDSSessionData + line4;
			
			PGcs pGcs = new PGcs();
			
			pGcs.setMessageType("pGcs");			
			pGcs.setMessageVersion(cReq.getMessageVersion());
			pGcs.setHtmlCreq(htmlCreq);
			
			
			return new ResponseEntity<Object>(pGcs, HttpStatus.OK);
			
		} catch (JsonMappingException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pGcq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pArq.getMessageVersion(), cReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pGcq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pArq.getMessageVersion(), cReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		} catch (RestClientException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pGcq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pArq.getMessageVersion(), cReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		}  catch (Exception e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pGcq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pArq.getMessageVersion(), cReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		}

	}

	public ResponseEntity<Object> validatePPrq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion) {
		
		PPrq pPrq = new PPrq();
		//PReq210 pReq210 = new PReq210();
		//PRes210 pRes210 = new PRes210();
		String serialNum = "";
		PReq pReq = threeDSMsgFactory.createPReqFromMsgVersion(jSONObject, messageVersion);
		pReq.setThreeDSServerRefNumber(atomWorkThreeDSServerRefNumber);
		pReq.setMessageType(MessageType.PReq.getValue());
		
		
		try {
						
			ObjectMapper objectMapper = new ObjectMapper();
			pPrq = objectMapper.readValue(jSONObject.toString(), PPrq.class);	
			if (cache3DMsg.isEmpty()) {
				cache3DMsg.put(testCaseResultId + "_pPrq", pPrq);
			}
			serialNum = (String) lastSerialNumMap.get(testCaseResultId + "_pRes210");
			if (!StringUtils.isEmpty(serialNum)) {
				pReq.setSerialNum(serialNum);
			}
		

			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap = pReq.validateMsg();

			if (errorMap == null) {

				//pRes210 = restTemplate.postForObject(dsUrl, pReq210, PRes210.class);
				PRes pRes = threeDSMsgFactory.createPResFromMsgVersion(dsUrl, pReq, messageVersion);
				lastSerialNumMap.put(testCaseResultId + "_pRes210", pRes.getSerialNum());
				
				log.info("pRes210 =" + pRes.toString());

				if (pRes.getMessageType().equals("Erro")) {
					ErrorMsg errorMsg = new ErrorMsg();
					errorMsg.setThreeDSServerTransID(pRes.getThreeDSServerTransID());
					errorMsg.setDsTransID(pRes.getDsTransID());
					// errorMsg.setErrorCode(aRes210.get);
					// errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
					// errorMsg.setErrorDetail(entry.getValue());
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(pRes.getMessageVersion());
					errorMsg.setErrorComponent("S");

					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}

				errorMap = pRes.validateMsg();
				
				//PArq pArq = (PArq) cache3DMsg.get(testCaseResultId + "_pPrq");
				
				if (errorMap != null) {
					ErrorMsg errorMsg = new ErrorMsg();
					if (!StringUtils.isEmpty(pRes.getThreeDSServerTransID())) {
						errorMsg.setThreeDSServerTransID(pRes.getThreeDSServerTransID());
					} else {
						errorMsg.setThreeDSServerTransID(pReq.getThreeDSServerTransID());
					}
					// errorMsg.setAcsTransID(aReq210.getac);
					errorMsg.setDsTransID(pRes.getDsTransID());
					Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
					errorMsg.setErrorCode(entry.getKey());
					errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
					errorMsg.setErrorDetail(entry.getValue());
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(pPrq.getMessageVersion());		
					errorMsg.setErrorComponent("S");
					errorMsg.setErrorMessageType("PRes");

					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}

				PPrs pPrs = requestMappingServiceInf.mapPPrs(pRes);
				return new ResponseEntity<Object>(pPrs, HttpStatus.OK);
			} else {
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setThreeDSServerTransID(pReq.getThreeDSServerTransID());
				//errorMsg.setAcsTransID(pReq210.getac);
				//errorMsg.setDsTransID(pReq210.getDsTransID());
				Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
				errorMsg.setErrorCode(entry.getKey());
				errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
				errorMsg.setErrorDetail(entry.getValue());
				errorMsg.setMessageType("Erro");
				errorMsg.setMessageVersion(pReq.getMessageVersion());
				//errorMsg.setSdkTransID(pReq210.getSdkTransID());
				errorMsg.setErrorComponent("S");
				errorMsg.setErrorMessageType("PReq");

				restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
				return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
			}
		} catch (JsonMappingException e) {			
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pPrq.getMessageVersion(), "pRes");
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);									
		} catch (JsonProcessingException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pPrq.getMessageVersion(), "pRes");
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		} catch (RestClientException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pPrq.getMessageVersion(), "pRes");
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		}  catch (Exception e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", pPrq.getMessageVersion(), "pRes");
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<Object> validateRReq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion) {
		
//		PArq pArq = new PArq();
		RReq rReq = null;
		PArq pArq = null;
		ARes aRes = null;
	
		try {
			
			pArq = (PArq) cache3DMsg.get(testCaseResultId + "_pArq");
			
			rReq = threeDSMsgFactory.createRReqFromMsgVersion(jSONObject, messageVersion);
			
			//aRes = threeDSMsgFactory.createAResFromMsgVersion(dsUrl, null, messageVersion);
			aRes = (ARes) cache3DMsg.get(testCaseResultId + "_ARes");
			
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap = rReq.validateMsg(pArq, aRes);

			if (errorMap == null) {
				
				RRes rRes = threeDSMsgFactory.createRResFromMsgVersion(messageVersion);
				//rRes =  requestMappingServiceInf.mapRRes(rRes, rReq);
				rRes = rRes.mapRRes(rReq);
				log.info("rRes =" + rRes.toString());

				if (rRes.getMessageType().equals("Erro")) {
					ErrorMsg errorMsg = new ErrorMsg();
					errorMsg.setThreeDSServerTransID(rRes.getThreeDSServerTransID());
					errorMsg.setDsTransID(rRes.getDsTransID());
					// errorMsg.setErrorCode(aRes210.get);
					// errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
					// errorMsg.setErrorDetail(entry.getValue());
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(rReq.getMessageVersion());
					errorMsg.setErrorComponent("S");

					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}

				errorMap = rRes.validateMsg();

				if (errorMap != null) {
					ErrorMsg errorMsg = new ErrorMsg();
					errorMsg.setThreeDSServerTransID(rRes.getThreeDSServerTransID());
					errorMsg.setAcsTransID(rRes.getAcsTransID());
					errorMsg.setDsTransID(rRes.getDsTransID());
					Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
					errorMsg.setErrorCode(entry.getKey());
					errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
					errorMsg.setErrorDetail(entry.getValue());
					errorMsg.setMessageType("Erro");
					errorMsg.setMessageVersion(rRes.getMessageVersion());	
					errorMsg.setErrorComponent("S");

					restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
					return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
				}
				
				return new ResponseEntity<Object>(rRes, HttpStatus.OK);
			} else {
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setThreeDSServerTransID(rReq.getThreeDSServerTransID());
				errorMsg.setAcsTransID(rReq.getAcsTransID());
				errorMsg.setDsTransID(rReq.getDsTransID());
				Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
				errorMsg.setErrorCode(entry.getKey());
				errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
				errorMsg.setErrorDetail(entry.getValue());
				errorMsg.setMessageType("Erro");
				errorMsg.setMessageVersion(rReq.getMessageVersion());
				errorMsg.setErrorComponent("S");
				errorMsg.setErrorMessageType("RReq");
				//errorMsg.setSdkTransID(pReq210.getSdkTransID());

				restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
				return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
			}
		} catch (RestClientException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(rReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", rReq.getMessageVersion(), rReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		} catch (Invalid3DMsgVersionException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(pArq.getThreeDSServerTransID(), ErrorResponse.ErrorCode203.getErrorCode(), "S", ErrorResponse.ErrorCode203.getErrorDesc(),
					ErrorResponse.ErrorCode203.getErrorDesc(), "Erro", pArq.getMessageVersion(), MessageType.RReq.getValue());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		} catch (Exception e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(rReq.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(), "S", ErrorResponse.ErrorCode101.getErrorDesc(),
					ErrorResponse.ErrorCode101.getErrorDesc(), "Erro", rReq.getMessageVersion(), rReq.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);			
		}
		
	}
	
	public ResponseEntity<Object> validateCRes(JSONObject jSONObject, String dsUrl, String messageVersion) {

		CRes cRes = threeDSMsgFactory.createCResFromMsgVersion(jSONObject, messageVersion);
		try {

			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap = cRes.validateMsg();

			if (errorMap == null) {

				return new ResponseEntity<Object>(cRes, HttpStatus.OK);

			} else {
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setThreeDSServerTransID(cRes.getThreeDSServerTransID());
				errorMsg.setAcsTransID(cRes.getAcsTransID());
//				errorMsg.setDsTransID(cRes.getDsTransID());
				Map.Entry<String, String> entry = errorMap.entrySet().iterator().next();
				errorMsg.setErrorCode(entry.getKey());
				errorMsg.setErrorDescription(ErrorResponse.getErrorDescByCode(entry.getKey()));
				errorMsg.setErrorDetail(entry.getValue());
				errorMsg.setMessageType("Erro");
				errorMsg.setMessageVersion(cRes.getMessageVersion());
				errorMsg.setErrorComponent("S");
				errorMsg.setErrorMessageType("CRes");
				// errorMsg.setSdkTransID(pReq210.getSdkTransID());

				restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
				return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
			}
		} catch (RestClientException e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(cRes.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(),
					"S", ErrorResponse.ErrorCode101.getErrorDesc(), ErrorResponse.ErrorCode101.getErrorDesc(), "Erro",
					cRes.getMessageVersion(), cRes.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		} catch (Exception e) {
			log.error(" has error :" + e.toString());
			ErrorMsg errorMsg = new ErrorMsg(cRes.getThreeDSServerTransID(), ErrorResponse.ErrorCode101.getErrorCode(),
					"S", ErrorResponse.ErrorCode101.getErrorDesc(), ErrorResponse.ErrorCode101.getErrorDesc(), "Erro",
					cRes.getMessageVersion(), cRes.getMessageType());
			restTemplate.postForObject(dsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		}

	}

}
