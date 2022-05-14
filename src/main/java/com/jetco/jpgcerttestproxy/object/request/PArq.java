package com.jetco.jpgcerttestproxy.object.request;

import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.object.subfield.AcctInfo;
import com.jetco.jpgcerttestproxy.object.subfield.DeviceRenderOptions;
import com.jetco.jpgcerttestproxy.object.subfield.HomePhone;
import com.jetco.jpgcerttestproxy.object.subfield.MerchantRiskIndicator;
import com.jetco.jpgcerttestproxy.object.subfield.MobilePhone;
import com.jetco.jpgcerttestproxy.object.subfield.SDKEphemPubKey;
import com.jetco.jpgcerttestproxy.object.subfield.ThreeDSRequestorAuthenticationInfo;
import com.jetco.jpgcerttestproxy.object.subfield.ThreeDSRequestorPriorAuthenticationInfo;
import com.jetco.jpgcerttestproxy.object.subfield.WorkPhone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PArq {
	
	@JsonIgnore
	private String testCaseResultId;
	private String acctNumber;
	private String cardExpiryDate;
	private String deviceChannel;
	private String messageCategory;
	private String messageType;
	private String messageVersion;
	private String p_messageVersion;
	private String threeDSRequestorID;
	private String threeDSRequestorName;
	private String threeDSRequestorURL;
	private String acquirerBIN;
	private String acquirerMerchantID;	
	private String addrMatch;
	private String billAddrCity;
	private String billAddrCountry;
	private String billAddrLine1;
	private String billAddrLine2;
	private String billAddrLine3;
	private String billAddrPostCode;
	private String billAddrState;
	private String browserAcceptHeader;
	private String browserColorDepth;
	private String browserIP;
	private String browserJavaEnabled;
	private String browserLanguage;
	private String browserScreenHeight;
	private String browserScreenWidth;
	private String browserTZ;
	private String browserUserAgent;
	private String cardholderName;
	private DeviceRenderOptions deviceRenderOptions;
	private String email;
	private HomePhone homePhone;
	private String mcc;
	private String merchantCountryCode;
	private String merchantName;
	private MobilePhone mobilePhone;
	private String purchaseAmount;
	private String purchaseCurrency;
	private String purchaseDate;
	private String purchaseExponent;
	private String recurringExpiry;
	private String recurringFrequency;
	private String sdkAppID;
	private String sdkEncData;
	private SDKEphemPubKey sdkEphemPubKey;
	private String sdkReferenceNumber;
	private String sdkTransID;
	private String shipAddrCity;
	private String shipAddrCountry;
	private String shipAddrLine1;
	private String shipAddrLine2;
	private String shipAddrLine3;
	private String shipAddrPostCode;
	private String shipAddrState;
	private String transType;
	private WorkPhone workPhone;
	private String acctID;
	private AcctInfo acctInfo;
	private String acctType;
	private MerchantRiskIndicator merchantRiskIndicator;
	private String messageExtension;
	private String payTokenInd;
	private String purchaseInstalData;
	private ThreeDSRequestorAuthenticationInfo threeDSRequestorAuthenticationInfo;	
	private String threeDSRequestorChallengeInd;
	private String threeDSRequestorAuthenticationInd;
	private String threeRIInd;
	private ThreeDSRequestorPriorAuthenticationInfo threeDSRequestorPriorAuthenticationInfo;
	private String threeDSServerRefNumber;
	private String threeDSServerOperatorID;
	private String threeDSServerTransID;
	private String threeDSServerURL;
	private String broadInfo;
	private String notificationURL;
	private String threeDSCompInd;
	private String sdkMaxTimeout;
	private String acsURL;
	private String threeDSReqAuthMethodInd;
	private String threeDSRequestorDecMaxTime;
	private String threeDSRequestorDecReqInd;
	private Boolean browserJavascriptEnabled;
	private String payTokenSource;
	private String whiteListStatus;
	private String whiteListStatusSource;
	
	@Cacheable(value="pArq", key="#test-case-result-id")  
	public PArq getPArq (JSONObject jSONObject){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			PArq pArq = objectMapper.readValue(jSONObject.toString(), PArq.class);
			return pArq;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
		
}
	
	