package com.jetco.jpgcerttestproxy.factory;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.exception.Invalid3DMsgVersionException;
import com.jetco.jpgcerttestproxy.object.request.AReq;
import com.jetco.jpgcerttestproxy.object.request.AReq210;
import com.jetco.jpgcerttestproxy.object.request.AReq220;
import com.jetco.jpgcerttestproxy.object.request.CReq;
import com.jetco.jpgcerttestproxy.object.request.CReq210;
import com.jetco.jpgcerttestproxy.object.request.CReq220;
import com.jetco.jpgcerttestproxy.object.request.PReq;
import com.jetco.jpgcerttestproxy.object.request.PReq210;
import com.jetco.jpgcerttestproxy.object.request.PReq220;
import com.jetco.jpgcerttestproxy.object.request.RReq;
import com.jetco.jpgcerttestproxy.object.request.RReq210;
import com.jetco.jpgcerttestproxy.object.request.RReq220;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.object.response.ARes210;
import com.jetco.jpgcerttestproxy.object.response.ARes220;
import com.jetco.jpgcerttestproxy.object.response.CRes;
import com.jetco.jpgcerttestproxy.object.response.CRes210;
import com.jetco.jpgcerttestproxy.object.response.CRes220;
import com.jetco.jpgcerttestproxy.object.response.PRes;
import com.jetco.jpgcerttestproxy.object.response.PRes210;
import com.jetco.jpgcerttestproxy.object.response.PRes220;
import com.jetco.jpgcerttestproxy.object.response.RRes;
import com.jetco.jpgcerttestproxy.object.response.RRes210;
import com.jetco.jpgcerttestproxy.object.response.RRes220;

@Service
public class ThreeDSMsgFactory {

	static final Logger log = LoggerFactory.getLogger(ThreeDSMsgFactory.class);

	@Autowired
	private RestTemplate restTemplate;

	public AReq createAReqFromMsgVersion(JSONObject jSONObject, String messageVersion) {

		ObjectMapper objectMapper = new ObjectMapper();

		switch (messageVersion) {
		case "2.1.0":
			try {
				if (jSONObject == null) {
					return new AReq210();
				} else {
					return objectMapper.readValue(jSONObject.toString(), AReq210.class);
				}
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		case "2.2.0":
			try {
				if (jSONObject == null) {
					return new AReq220();
				} else {
					return objectMapper.readValue(jSONObject.toString(), AReq220.class);
				}
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new AReq220();
		}
		return null;
	}

	public ARes createAResFromMsgVersion(String dsUrl, AReq aReq, String messageVersion) {

		switch (messageVersion) {
		case "2.1.0":
			ARes210 aRes210 = restTemplate.postForObject(dsUrl, aReq, ARes210.class);
			return aRes210;

		case "2.2.0":

			ARes220 aRes220 = restTemplate.postForObject(dsUrl, aReq, ARes220.class);
			return aRes220;

		}
		return null;
	}

	public CReq createCReqFromMsgVersion(String messageVersion) {

		switch (messageVersion) {
		case "2.1.0":
			return new CReq210();

		case "2.2.0":
			return new CReq220();

		}
		return null;

	}

	public CRes createCResFromMsgVersion(JSONObject jSONObject, String messageVersion) {

		ObjectMapper objectMapper = new ObjectMapper();

		switch (messageVersion) {
		case "2.1.0":
			try {
				if (jSONObject == null) {
					return new CRes210();
				} else {
					return objectMapper.readValue(jSONObject.toString(), CRes210.class);
				}
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		case "2.2.0":
			try {
				if (jSONObject == null) {
					return new CRes220();
				} else {
					return objectMapper.readValue(jSONObject.toString(), CRes220.class);
				}
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public PReq createPReqFromMsgVersion(JSONObject jSONObject, String messageVersion) {

		ObjectMapper objectMapper = new ObjectMapper();

		switch (messageVersion) {
		case "2.1.0":
			try {
				if (jSONObject == null) {
					return new PReq210();
				} else {
					return objectMapper.readValue(jSONObject.toString(), PReq210.class);
				}
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		case "2.2.0":
			try {
				if (jSONObject == null) {
					return new PReq220();
				} else {
					return objectMapper.readValue(jSONObject.toString(), PReq220.class);
				}
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public PRes createPResFromMsgVersion(String dsUrl, PReq pReq, String messageVersion) {

		switch (messageVersion) {
		case "2.1.0":
			PRes210 pRes210 = restTemplate.postForObject(dsUrl, pReq, PRes210.class);
			return pRes210;

		case "2.2.0":
			PRes220 pRes220 = restTemplate.postForObject(dsUrl, pReq, PRes220.class);
			return pRes220;

		}
		return null;
	}

	public RReq createRReqFromMsgVersion(JSONObject jSONObject, String messageVersion)
			throws Invalid3DMsgVersionException {

		ObjectMapper objectMapper = new ObjectMapper();

		switch (messageVersion) {
		case "2.1.0":
			try {
				if (jSONObject == null) {
					return new RReq210();
				} else {
					return objectMapper.readValue(jSONObject.toString(), RReq210.class);
				}
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		case "2.2.0":
			try {
				if (jSONObject == null) {
					return new RReq220();
				} else {
					return objectMapper.readValue(jSONObject.toString(), RReq220.class);
				}
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		default:
			throw new Invalid3DMsgVersionException("invalid message version");

		}
		// return null;
	}

	public RRes createRResFromMsgVersion(String messageVersion) {

		switch (messageVersion) {
		case "2.1.0":
			return new RRes210();

		case "2.2.0":
			return new RRes220();

		}
		return null;

	}

}
