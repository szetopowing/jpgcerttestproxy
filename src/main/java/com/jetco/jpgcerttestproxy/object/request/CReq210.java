package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.object.response.ARes;

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
		erro = super.validateMsg();
		
		return erro;
		
	}

}
