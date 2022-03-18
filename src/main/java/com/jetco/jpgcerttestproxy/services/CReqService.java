package com.jetco.jpgcerttestproxy.services;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.jetco.jpgcerttestproxy.factory.ThreeDSMsgFactory;
import com.jetco.jpgcerttestproxy.object.request.CReq;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.object.response.ErrorMsg;

@Service
public class CReqService {

	static final Logger log = LoggerFactory.getLogger(CReqService.class);

	@Autowired
	private ThreeDSMsgFactory threeDSMsgFactory;
	
	public void processCReqService(Model model, ResponseEntity<Object> resEntity) {

		if (resEntity.getBody() instanceof ARes) {
			ARes aRes = (ARes) resEntity.getBody();			
			CReq cReq = threeDSMsgFactory.createCReqFromMsgVersion(aRes.getMessageVersion());

			
			//cReq210 = requestMappingServiceInf.mapCReq210(aRes);
			cReq = cReq.mapCReqFromARes(aRes);
			String acsUrl = aRes.getAcsURL();
			log.info("acsUrl = " + acsUrl);
			log.info("cReq =" + cReq.toString());

			//String cReqStr = new Gson().toJson(cReq210, CReq210.class);
			String cReqStr = new Gson().toJson(cReq, cReq.getClass());
			String base64CReqStr = Base64.getUrlEncoder().withoutPadding().encodeToString(cReqStr.getBytes());

			log.info("cReqStr =" + cReqStr);
			log.info("base64CReqStr =" + base64CReqStr);
			
			if(aRes.getTransStatus().equals("C")) {
				model.addAttribute("isChallenge", true);
				model.addAttribute("cReq", base64CReqStr);
				model.addAttribute("acsUrl", acsUrl);
			} else {
				model.addAttribute("isChallenge", false);
				String aResJson = new Gson().toJson(aRes, aRes.getClass());
				model.addAttribute("aResJson", aResJson);
			}


		} else if (resEntity.getBody() instanceof ErrorMsg) {

			ErrorMsg errMsg = (ErrorMsg) resEntity.getBody();
			String errMsgStr = new Gson().toJson(errMsg, ErrorMsg.class);
			model.addAttribute("errMsgStr", errMsgStr);

		}

	}

}
