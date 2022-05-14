package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AReq210 extends AReq{

	static final Logger log = LoggerFactory.getLogger(AReq210.class);
	
	@Override
	public AReq mapPArq(PArq pArq, String testingEnv, String _threeDSServerRefNumber, String visaThreeDSServerRefNumber) {		
		AReq210 aReq210 = (AReq210) super.mapPArq(pArq, testingEnv, _threeDSServerRefNumber, visaThreeDSServerRefNumber);
		return aReq210;		
	}

	@Override
	public Map<String, String> validateMsg() {
		
		log.info("In AReq210.validateMsg!!");
		Map<String, String> erro = new HashMap<String, String>();
		erro = super.validateMsg();
		return erro;
		
	}

}