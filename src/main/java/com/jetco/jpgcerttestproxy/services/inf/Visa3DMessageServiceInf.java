package com.jetco.jpgcerttestproxy.services.inf;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

public interface Visa3DMessageServiceInf {

	public ResponseEntity<Object> sendAReq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion);
	
	public ResponseEntity<Object> sendPReq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion);
	
}
