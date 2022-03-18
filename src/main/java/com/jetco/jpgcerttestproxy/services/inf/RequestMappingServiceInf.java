package com.jetco.jpgcerttestproxy.services.inf;


import com.jetco.jpgcerttestproxy.object.request.PPrq;
import com.jetco.jpgcerttestproxy.object.request.PReq210;
import com.jetco.jpgcerttestproxy.object.request.RReq;
import com.jetco.jpgcerttestproxy.object.request.RReq210;
import com.jetco.jpgcerttestproxy.object.response.CRes210;
import com.jetco.jpgcerttestproxy.object.response.PGcs;
import com.jetco.jpgcerttestproxy.object.response.PPrs;
import com.jetco.jpgcerttestproxy.object.response.PRes;
import com.jetco.jpgcerttestproxy.object.response.PRes210;
import com.jetco.jpgcerttestproxy.object.response.RRes;
import com.jetco.jpgcerttestproxy.object.response.RRes210;

public interface RequestMappingServiceInf {
	
	//public AReq210 mapPArq(PArq pArq, AReq aReq); //pArq -> AReq
	
	//public CReq210 mapPGcq(PGcq pGcq); //pGcq -> CReq210
	
	//public CReq210 mapCReq210(ARes aRes210); //aRes210 -> CReq
	
	//public PReq210 mapPPrq(PPrq pPrq, String serialNum); //pPrq -> PReq210
	
	//public PArs mapPArs(ARes210 aRes210, String deviceChannel); //ARes210 -> PArs 
	
	//public PGcs mapPGcs(CRes210 cres210); //CRes210 -> PGcs
	
	public PPrs mapPPrs(PRes pRes); //PRes210 -> PPrs
	
	//public RRes mapRRes(RRes rRes, RReq rReq); //RReq210 -> RRes210

}
