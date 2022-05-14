package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PReq220 extends PReq {

	static final Logger log = LoggerFactory.getLogger(PReq220.class);

	public Map<String, String> validateMsg() {
		
		log.info("In pReq220.validateMsg!!");
		Map<String, String> erro = new HashMap<String, String>();
		erro = super.validateMsg();
		return erro;
		
	}
}
