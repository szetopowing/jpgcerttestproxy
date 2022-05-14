package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.services.RequestMappingService;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CReq220 extends CReq {
	
	static final Logger log = LoggerFactory.getLogger(RequestMappingService.class);
	
	@Override
	public CReq mapCReqFromARes(ARes aRes) {		
		CReq220 cReq220 = (CReq220) super.mapCReqFromARes(aRes);
		return cReq220;		
	}

	@Override
	public CReq mapCReqFromPGcq(PGcq pGcq) {
		CReq220 cReq210 = (CReq220) super.mapCReqFromPGcq(pGcq);
		return cReq210;	
	}
	
	@Override
	public Map<String, String> validateMsg() {

		log.info("In cReq220.validateMsg!!");
	
		Map<String, String> erro = new HashMap<String, String>();
		erro = super.validateMsg();
		
		return erro;
		
	}
}
