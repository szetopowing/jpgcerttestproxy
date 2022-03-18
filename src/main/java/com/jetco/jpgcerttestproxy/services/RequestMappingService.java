package com.jetco.jpgcerttestproxy.services;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.controller.PArController;
import com.jetco.jpgcerttestproxy.object.CreditPaymentInfo;
import com.jetco.jpgcerttestproxy.object.request.AReq;
import com.jetco.jpgcerttestproxy.object.request.AReq210;
import com.jetco.jpgcerttestproxy.object.request.CReq;
import com.jetco.jpgcerttestproxy.object.request.CReq210;
import com.jetco.jpgcerttestproxy.object.request.PArq;
import com.jetco.jpgcerttestproxy.object.request.PGcq;
import com.jetco.jpgcerttestproxy.object.request.PPrq;
import com.jetco.jpgcerttestproxy.object.request.PReq210;
import com.jetco.jpgcerttestproxy.object.request.RReq;
import com.jetco.jpgcerttestproxy.object.request.RReq210;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.object.response.ARes210;
import com.jetco.jpgcerttestproxy.object.response.CRes210;
import com.jetco.jpgcerttestproxy.object.response.PArs;
import com.jetco.jpgcerttestproxy.object.response.PGcs;
import com.jetco.jpgcerttestproxy.object.response.PPrs;
import com.jetco.jpgcerttestproxy.object.response.PRes;
import com.jetco.jpgcerttestproxy.object.response.PRes210;
import com.jetco.jpgcerttestproxy.object.response.RRes;
import com.jetco.jpgcerttestproxy.object.response.RRes210;
import com.jetco.jpgcerttestproxy.services.inf.RequestMappingServiceInf;
import com.jetco.jpgcerttestproxy.threeDSenum.DeviceChannel;

@Service
public class RequestMappingService implements RequestMappingServiceInf {

	static final Logger log = LoggerFactory.getLogger(RequestMappingService.class);
	
	@Value("${testingEnv}")
	private String testingEnv;

	@Value("${atomWork.threeDSServerRefNumber}")
	private String atomWorkThreeDSServerRefNumber;
	
	@Value("${visa.threeDSServerRefNumber}")
	private String visaThreeDSServerRefNumber;
/*
	public AReq210 mapPArq(PArq pArq) {
		
		AReq aReq = new AReq(); 
		AReq210 aReq210 = (AReq210) aReq.mapPArq(pArq);
		
		return aReq210;
	}
	*/

	/*
	public CReq210 mapPGcq(PGcq pGcq) {

		CReq210 cReq210 = new CReq210();

		cReq210.setMessageType("CReq");
		cReq210.setMessageVersion(pGcq.getMessageVersion());
		cReq210.setThreeDSServerTransID(pGcq.getThreeDSServerTransID());
		cReq210.setAcsTransID(pGcq.getAcsTransID());
		cReq210.setChallengeWindowSize(pGcq.getChallengeWindowSize());

		// String cReq210JsonString;

		// cReq210JsonString = mapper.writeValueAsString(cReq210);

		// String threeDSSessionData = pGcq.getThreeDSServerTransID() + ";" +
		// pGcq.getThreeDSServerTransID() + ";" + messageID;

		// cReq.setThreeDSSessionData(threeDSSessionData);
		// cReq.setCreq(java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(cReq210JsonString.getBytes()));

		return cReq210;

	}
	*/
	
	/*
	public CReq210 mapCReq210(ARes aRes210) {
		
		CReq210 cReq210 = new CReq210();

		cReq210.setMessageType("CReq");
		cReq210.setMessageVersion(aRes210.getMessageVersion());
		cReq210.setThreeDSServerTransID(aRes210.getThreeDSServerTransID());
		cReq210.setAcsTransID(aRes210.getAcsTransID());
		cReq210.setChallengeWindowSize("05"); //full Screen
		
		return cReq210;
	}
	*/
/*
	public PReq210 mapPPrq(PPrq pPrq, String serialNum) {

		PReq210 pReq210 = new PReq210();
		pReq210.setMessageType("PReq");
		pReq210.setMessageVersion(pPrq.getMessageVersion());
		pReq210.setThreeDSServerRefNumber("3DS_LOA_SER_PPFU_020100_00008");
		pReq210.setThreeDSServerOperatorID("1jpeeLAWgGFgS1Ri9tX9"); // ??
		
		if (!StringUtils.isEmpty(serialNum)) {
			pReq210.setSerialNum(serialNum);
		}

		pReq210.setThreeDSServerTransID(pPrq.getThreeDSServerTransID());
		return pReq210;

	}
	*/
/*
	public PArs mapPArs(ARes210 aRes210, String deviceChannel) {
		PArs pArs = new PArs();
		pArs.setAcsChallengeMandated(aRes210.getAcsChallengeMandated());
		// pArs.setAcsDecConInd(aRes210.getac);
		pArs.setAcsOperatorID(aRes210.getAcsOperatorID());
		pArs.setAcsReferenceNumber(aRes210.getAcsReferenceNumber());
		pArs.setAcsRenderingType(aRes210.getAcsRenderingType());
		if (deviceChannel.equals(DeviceChannel.APP.getValue())){
			pArs.setAcsSignedContent(aRes210.getAcsSignedContent());
		}
		pArs.setAcsTransID(aRes210.getAcsTransID());
		pArs.setAcsURL(aRes210.getAcsURL());
		pArs.setAuthenticationType(aRes210.getAuthenticationType());
		pArs.setAuthenticationValue(aRes210.getAuthenticationValue());
		pArs.setBroadInfo(aRes210.getBroadInfo());
		pArs.setCardholderInfo(aRes210.getCardholderInfo());
		pArs.setDsReferenceNumber(aRes210.getDsReferenceNumber());
		pArs.setDsTransID(aRes210.getDsTransID());
		pArs.setEci(aRes210.getEci());
		pArs.setMessageExtension(aRes210.getMessageExtension());
		pArs.setMessageType("pArs");
		pArs.setMessageVersion(aRes210.getMessageVersion());
		// pArs.setP_messageVersion(aRes210.get);
		pArs.setSdkTransID(aRes210.getSdkTransID());
		pArs.setTransStatus(aRes210.getTransStatus());
		pArs.setTransStatusReason(aRes210.getTransStatus());
		// pArs.setWhiteListStatus(whiteListStatus);
		// pArs.setWhiteListStatusSource(whiteListStatusSource);

		return pArs;
	}
	*/
/*
	public PGcs mapPGcs(CRes210 cRes210) { // CRes210 -> PGcs

		PGcs pGcs = new PGcs();
		pGcs.setHtmlCreq("");
		pGcs.setMessageType("pGcs");
		pGcs.setMessageVersion(cRes210.getMessageVersion());
		return pGcs;
	}
*/
	
	public PPrs mapPPrs(PRes pRes) { // PRes210 -> PPrs
		PPrs pPrs = new PPrs();

		pPrs.setMessageType("pPrs");
		pPrs.setMessageVersion(pRes.getMessageVersion());
		pPrs.setP_messageVersion("1.0.5");
		pPrs.setP_completed("True");

		return pPrs;

	}
/*
	public RRes mapRRes(RReq rReq) {

		RRes rRes = new RRes();

		rRes210.setAcsTransID(rReq.getAcsTransID());
		rRes210.setDsTransID(rReq.getDsTransID());
		// rRes210.setMessageID(rReq210.getMessageID());
		rRes210.setMessageType("RRes");
		rRes210.setMessageVersion(rReq.getMessageVersion());
		rRes210.setThreeDSServerTransID(rReq.getThreeDSServerTransID());
		rRes210.setResultsStatus("01");
		if (rReq.getChallengeCancel() != null
				&& (rReq.getChallengeCancel().equals("02") || rReq.getChallengeCancel().equals("05"))) {
			rRes210.setResultsStatus("02");
		}

		return rRes210;

	}
*/
}
