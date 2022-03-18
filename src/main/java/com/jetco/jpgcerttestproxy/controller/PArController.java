package com.jetco.jpgcerttestproxy.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.jetco.jpgcerttestproxy.object.response.ErrorMsg;
import com.jetco.jpgcerttestproxy.services.CReqService;
import com.jetco.jpgcerttestproxy.services.inf.AtomWork3DMessageServiceInf;
import com.jetco.jpgcerttestproxy.services.inf.RequestMappingServiceInf;
import com.jetco.jpgcerttestproxy.services.inf.Visa3DMessageServiceInf;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;

//@RestController
@Controller
public class PArController {

	static final Logger log = LoggerFactory.getLogger(PArController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	RequestMappingServiceInf requestMappingServiceInf;

	@Autowired
	AtomWork3DMessageServiceInf atomWork3DMessageServiceInf;
	
	@Autowired
	Visa3DMessageServiceInf visa3DMessageServiceInf;
	
	@Autowired
	CReqService cReqService;

	@Value("${areqUrl}")
	private String areqUrl;

	@Value("${atomWork.dsUrl}")
	private String atomWorkDsUrl;
	
	@Value("${visa.dsUrl}")
	private String visaDsUrl;
	
	@Value("${master.dsUrl}")
	private String masterDsUrl;
	
	
	@Value("${testingEnv}")
	private String testingEnv;

	@RequestMapping(value = "/PayPage/receivePArqServlet", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> receivePArq(@RequestHeader(value = "User-Agent") String userAgent,
			@RequestHeader(value = "test-case-result-id", required = false) String testCaseResultId, @RequestBody(required = false) Object object,
			HttpServletRequest request) throws Exception {
		
		String msgType = null;
		String msgVersion = null;

		try {
			log.info("In receivePArq!!");
			log.info("testCardResultId =" + testCaseResultId);

			String dsUrl = null;
			
			if (testingEnv.equals("ATOMWORK")) {
				dsUrl = atomWorkDsUrl;
			} else if (testingEnv.equals("VISA")) {
				dsUrl = visaDsUrl;
			} else if (testingEnv.equals("MASTER")) {
				dsUrl = masterDsUrl;
			}
			
			String jsonString = new Gson().toJson(object, LinkedHashMap.class);
			JSONObject jSONObject = new JSONObject(jsonString);

			msgType = jSONObject.getString("messageType");
			msgVersion = jSONObject.getString("messageVersion");
			
			if (StringUtils.isEmpty(msgType)) {
				throw new JSONException("Invalid Message Type");
			}

			if (msgType.equals("pArq")) {
				return atomWork3DMessageServiceInf.validatePArq(jSONObject, dsUrl, testCaseResultId, msgVersion);
			}

			if (msgType.equals("pGcq")) {
				return atomWork3DMessageServiceInf.validatePGcq(jSONObject, dsUrl, testCaseResultId);
			}

			if (msgType.equals("pPrq")) {
				return atomWork3DMessageServiceInf.validatePPrq(jSONObject, dsUrl, testCaseResultId, msgVersion);
			}

			if (msgType.equals("RReq")) {
				return atomWork3DMessageServiceInf.validateRReq(jSONObject, dsUrl, testCaseResultId, msgVersion);
			}

			return null;
		} catch (JSONException e) {
			ErrorMsg errorMsg = new ErrorMsg();

			if (msgVersion == null) {
				errorMsg.setErrorCode(ErrorResponse.ErrorCode201.getErrorCode());
				errorMsg.setErrorDescription(ErrorResponse.ErrorCode201.getErrorDesc());
				errorMsg.setErrorMessageType(msgType);
			} else {
				errorMsg.setErrorCode(ErrorResponse.ErrorCode101.getErrorCode());
				errorMsg.setErrorDescription(ErrorResponse.ErrorCode101.getErrorDesc());
				errorMsg.setErrorMessageType(msgType);
			}
			errorMsg.setErrorDetail("Invalid Message Type");
			errorMsg.setMessageType("Erro");
			// errorMsg.setMessageVersion(pArq.getMessageVersion());
			errorMsg.setErrorComponent("S");

			restTemplate.postForObject(atomWorkDsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/PayPage/receiveCRes", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> receiveCRes(HttpServletRequest request, @RequestParam String cres) throws Exception {

		try {
			log.info("In receiveCRes!!");
			byte[] decodeCres = Base64.getUrlDecoder().decode(cres.getBytes());
			String cResStr = new String(decodeCres);
			log.info("Decoded CRes [" + cResStr + "].");
			JSONObject jSONObject = new JSONObject(cResStr);

			String msgType = jSONObject.getString("messageType");
			String msgVersion = jSONObject.getString("messageVersion");

			if (StringUtils.isEmpty(msgType)) {
				throw new JSONException("Invalid Message Type");
			}

			return atomWork3DMessageServiceInf.validateCRes(jSONObject, visaDsUrl, msgVersion);

		} catch (JSONException e) {
			ErrorMsg errorMsg = new ErrorMsg();

			errorMsg.setErrorCode(ErrorResponse.ErrorCode101.getErrorCode());
			errorMsg.setErrorDescription(ErrorResponse.ErrorCode101.getErrorDesc());
			errorMsg.setErrorDetail("Invalid Message Type");
			errorMsg.setMessageType("Erro");
			// errorMsg.setMessageVersion(pArq.getMessageVersion());
			errorMsg.setErrorComponent("S");

			restTemplate.postForObject(visaDsUrl, errorMsg, ErrorMsg.class);
			return new ResponseEntity<Object>(errorMsg, HttpStatus.OK);
		}

	}
	
	@GetMapping(value = "/PayPage/visaAReqPage")
	public String visaArqPage(Model model) {
		try {
			Resource resource1 = new ClassPathResource("visaAReq.json");
			InputStream inputStream1 = resource1.getInputStream();
			String result1 = new BufferedReader(new InputStreamReader(inputStream1))
					   .lines().collect(Collectors.joining("\n"));
			model.addAttribute("result1", result1);
			log.info("result1 =" + result1);
			
			Resource resource2 = new ClassPathResource("visaAReq220.json");
			InputStream inputStream2 = resource2.getInputStream();
			String result2 = new BufferedReader(new InputStreamReader(inputStream2))
					   .lines().collect(Collectors.joining("\n"));
			model.addAttribute("result2", result2);
			log.info("result2 =" + result2);
			
			Resource resource3 = new ClassPathResource("pReq220.json");
			InputStream inputStream3 = resource3.getInputStream();
			String pReq220 = new BufferedReader(new InputStreamReader(inputStream3))
					   .lines().collect(Collectors.joining("\n"));
			model.addAttribute("pReq220", pReq220);
			log.info("pReq220 =" + pReq220);
			
			return "visaArqPage";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
			
		}				
	}
	
	@PostMapping(value = "/PayPage/sendVisaAReq210")
	public String sendVisaAReq210(@RequestParam(name = "aReq210", required = false) String aReq210, Model model) {
		try {
			log.info("aReq210 =" + aReq210.toString());
			JSONObject jSONObject = new JSONObject(aReq210);

			ResponseEntity<Object> res = visa3DMessageServiceInf.sendAReq(jSONObject, visaDsUrl, null, "2.1.0");

			cReqService.processCReqService(model, res);

			return "visaCreqPage";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error!", e);
			return null;

		}
	}
	
	@PostMapping(value = "/PayPage/sendVisaAReq220")
	public  String sendVisaAReq220(@RequestParam(name ="aReq220", required=false) String aReq220, Model model) {
		try {
			log.info("aReq220 =" + aReq220.toString());
			JSONObject jSONObject = new JSONObject(aReq220);			
			ResponseEntity<Object> res = visa3DMessageServiceInf.sendAReq(jSONObject, visaDsUrl, null, "2.2.0");
			
			cReqService.processCReqService(model, res);

			return "visaCreqPage";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error!", e);
			return null;
			
		}				
	}
	
	@PostMapping(value = "/PayPage/sendVisaPReq220")
	public  ResponseEntity<Object> sendVisaPReq220(@RequestParam(name ="pReq220", required=false) String pReq220) {
		try {
			log.info("pReq220 =" + pReq220.toString());
			JSONObject jSONObject = new JSONObject(pReq220);			
			return visa3DMessageServiceInf.sendPReq(jSONObject, visaDsUrl, null, "2.2.0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error!", e);
			return null;
			
		}				
	}
	
	
}
