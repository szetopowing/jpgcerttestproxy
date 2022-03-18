package com.jetco.jpgcerttestproxy.services.inf;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import com.jetco.jpgcerttestproxy.exception.Invalid3DMsgVersionException;

public interface AtomWork3DMessageServiceInf {
	
	public ResponseEntity<Object> validatePArq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion);
	
	public ResponseEntity<Object> validatePGcq(JSONObject jSONObject, String dsUrl, String testCaseResultId);
	
    public ResponseEntity<Object> validatePPrq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion);
    
    public ResponseEntity<Object> validateRReq(JSONObject jSONObject, String dsUrl, String testCaseResultId, String messageVersion);
    
    public ResponseEntity<Object> validateCRes(JSONObject jSONObject, String dsUrl, String messageVersion);
   
}
