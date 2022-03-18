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
	
	
	
	public String getTestCaseResultId() {
		return testCaseResultId;
	}
	public void setTestCaseResultId(String testCaseResultId) {
		this.testCaseResultId = testCaseResultId;
	}
	public String getAcctNumber() {
		return acctNumber;
	}
	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	public String getCardExpiryDate() {
		return cardExpiryDate;
	}
	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}
	public String getDeviceChannel() {
		return deviceChannel;
	}
	public void setDeviceChannel(String deviceChannel) {
		this.deviceChannel = deviceChannel;
	}
	public String getMessageCategory() {
		return messageCategory;
	}
	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageVersion() {
		return messageVersion;
	}
	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}
	public String getP_messageVersion() {
		return p_messageVersion;
	}
	public void setP_messageVersion(String p_messageVersion) {
		this.p_messageVersion = p_messageVersion;
	}
	public String getThreeDSRequestorID() {
		return threeDSRequestorID;
	}
	public void setThreeDSRequestorID(String threeDSRequestorID) {
		this.threeDSRequestorID = threeDSRequestorID;
	}
	public String getThreeDSRequestorName() {
		return threeDSRequestorName;
	}
	public void setThreeDSRequestorName(String threeDSRequestorName) {
		this.threeDSRequestorName = threeDSRequestorName;
	}
	public String getThreeDSRequestorURL() {
		return threeDSRequestorURL;
	}
	public void setThreeDSRequestorURL(String threeDSRequestorURL) {
		this.threeDSRequestorURL = threeDSRequestorURL;
	}
	public String getAcquirerBIN() {
		return acquirerBIN;
	}
	public void setAcquirerBIN(String acquirerBIN) {
		this.acquirerBIN = acquirerBIN;
	}
	public String getAcquirerMerchantID() {
		return acquirerMerchantID;
	}
	public void setAcquirerMerchantID(String acquirerMerchantID) {
		this.acquirerMerchantID = acquirerMerchantID;
	}
	public String getAddrMatch() {
		return addrMatch;
	}
	public void setAddrMatch(String addrMatch) {
		this.addrMatch = addrMatch;
	}
	public String getBillAddrCity() {
		return billAddrCity;
	}
	public void setBillAddrCity(String billAddrCity) {
		this.billAddrCity = billAddrCity;
	}
	public String getBillAddrCountry() {
		return billAddrCountry;
	}
	public void setBillAddrCountry(String billAddrCountry) {
		this.billAddrCountry = billAddrCountry;
	}
	public String getBillAddrLine1() {
		return billAddrLine1;
	}
	public void setBillAddrLine1(String billAddrLine1) {
		this.billAddrLine1 = billAddrLine1;
	}
	public String getBillAddrLine2() {
		return billAddrLine2;
	}
	public void setBillAddrLine2(String billAddrLine2) {
		this.billAddrLine2 = billAddrLine2;
	}
	public String getBillAddrLine3() {
		return billAddrLine3;
	}
	public void setBillAddrLine3(String billAddrLine3) {
		this.billAddrLine3 = billAddrLine3;
	}
	public String getBillAddrPostCode() {
		return billAddrPostCode;
	}
	public void setBillAddrPostCode(String billAddrPostCode) {
		this.billAddrPostCode = billAddrPostCode;
	}
	public String getBillAddrState() {
		return billAddrState;
	}
	public void setBillAddrState(String billAddrState) {
		this.billAddrState = billAddrState;
	}
	public String getBrowserAcceptHeader() {
		return browserAcceptHeader;
	}
	public void setBrowserAcceptHeader(String browserAcceptHeader) {
		this.browserAcceptHeader = browserAcceptHeader;
	}
	public String getBrowserColorDepth() {
		return browserColorDepth;
	}
	public void setBrowserColorDepth(String browserColorDepth) {
		this.browserColorDepth = browserColorDepth;
	}
	public String getBrowserIP() {
		return browserIP;
	}
	public void setBrowserIP(String browserIP) {
		this.browserIP = browserIP;
	}
	public String getBrowserJavaEnabled() {
		return browserJavaEnabled;
	}
	public void setBrowserJavaEnabled(String browserJavaEnabled) {
		this.browserJavaEnabled = browserJavaEnabled;
	}
	public String getBrowserLanguage() {
		return browserLanguage;
	}
	public void setBrowserLanguage(String browserLanguage) {
		this.browserLanguage = browserLanguage;
	}
	public String getBrowserScreenHeight() {
		return browserScreenHeight;
	}
	public void setBrowserScreenHeight(String browserScreenHeight) {
		this.browserScreenHeight = browserScreenHeight;
	}
	public String getBrowserScreenWidth() {
		return browserScreenWidth;
	}
	public void setBrowserScreenWidth(String browserScreenWidth) {
		this.browserScreenWidth = browserScreenWidth;
	}
	public String getBrowserTZ() {
		return browserTZ;
	}
	public void setBrowserTZ(String browserTZ) {
		this.browserTZ = browserTZ;
	}
	public String getBrowserUserAgent() {
		return browserUserAgent;
	}
	public void setBrowserUserAgent(String browserUserAgent) {
		this.browserUserAgent = browserUserAgent;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
	public DeviceRenderOptions getDeviceRenderOptions() {
		return deviceRenderOptions;
	}
	public void setDeviceRenderOptions(DeviceRenderOptions deviceRenderOptions) {
		this.deviceRenderOptions = deviceRenderOptions;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HomePhone getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(HomePhone homePhone) {
		this.homePhone = homePhone;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMerchantCountryCode() {
		return merchantCountryCode;
	}
	public void setMerchantCountryCode(String merchantCountryCode) {
		this.merchantCountryCode = merchantCountryCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public MobilePhone getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(MobilePhone mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public String getPurchaseCurrency() {
		return purchaseCurrency;
	}
	public void setPurchaseCurrency(String purchaseCurrency) {
		this.purchaseCurrency = purchaseCurrency;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseExponent() {
		return purchaseExponent;
	}
	public void setPurchaseExponent(String purchaseExponent) {
		this.purchaseExponent = purchaseExponent;
	}
	public String getRecurringExpiry() {
		return recurringExpiry;
	}
	public void setRecurringExpiry(String recurringExpiry) {
		this.recurringExpiry = recurringExpiry;
	}
	public String getRecurringFrequency() {
		return recurringFrequency;
	}
	public void setRecurringFrequency(String recurringFrequency) {
		this.recurringFrequency = recurringFrequency;
	}
	public String getSdkAppID() {
		return sdkAppID;
	}
	public void setSdkAppID(String sdkAppID) {
		this.sdkAppID = sdkAppID;
	}
	public String getSdkEncData() {
		return sdkEncData;
	}
	public void setSdkEncData(String sdkEncData) {
		this.sdkEncData = sdkEncData;
	}
	public SDKEphemPubKey getSdkEphemPubKey() {
		return sdkEphemPubKey;
	}
	public void setSdkEphemPubKey(SDKEphemPubKey sdkEphemPubKey) {
		this.sdkEphemPubKey = sdkEphemPubKey;
	}
	public String getSdkReferenceNumber() {
		return sdkReferenceNumber;
	}
	public void setSdkReferenceNumber(String sdkReferenceNumber) {
		this.sdkReferenceNumber = sdkReferenceNumber;
	}
	public String getSdkTransID() {
		return sdkTransID;
	}
	public void setSdkTransID(String sdkTransID) {
		this.sdkTransID = sdkTransID;
	}
	public String getShipAddrCity() {
		return shipAddrCity;
	}
	public void setShipAddrCity(String shipAddrCity) {
		this.shipAddrCity = shipAddrCity;
	}
	public String getShipAddrCountry() {
		return shipAddrCountry;
	}
	public void setShipAddrCountry(String shipAddrCountry) {
		this.shipAddrCountry = shipAddrCountry;
	}
	public String getShipAddrLine1() {
		return shipAddrLine1;
	}
	public void setShipAddrLine1(String shipAddrLine1) {
		this.shipAddrLine1 = shipAddrLine1;
	}
	public String getShipAddrLine2() {
		return shipAddrLine2;
	}
	public void setShipAddrLine2(String shipAddrLine2) {
		this.shipAddrLine2 = shipAddrLine2;
	}
	public String getShipAddrLine3() {
		return shipAddrLine3;
	}
	public void setShipAddrLine3(String shipAddrLine3) {
		this.shipAddrLine3 = shipAddrLine3;
	}
	public String getShipAddrPostCode() {
		return shipAddrPostCode;
	}
	public void setShipAddrPostCode(String shipAddrPostCode) {
		this.shipAddrPostCode = shipAddrPostCode;
	}
	public String getShipAddrState() {
		return shipAddrState;
	}
	public void setShipAddrState(String shipAddrState) {
		this.shipAddrState = shipAddrState;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public WorkPhone getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(WorkPhone workPhone) {
		this.workPhone = workPhone;
	}
	public String getAcctID() {
		return acctID;
	}
	public void setAcctID(String acctID) {
		this.acctID = acctID;
	}
	public AcctInfo getAcctInfo() {
		return acctInfo;
	}
	public void setAcctInfo(AcctInfo acctInfo) {
		this.acctInfo = acctInfo;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public MerchantRiskIndicator getMerchantRiskIndicator() {
		return merchantRiskIndicator;
	}
	public void setMerchantRiskIndicator(MerchantRiskIndicator merchantRiskIndicator) {
		this.merchantRiskIndicator = merchantRiskIndicator;
	}
	public String getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(String messageExtension) {
		this.messageExtension = messageExtension;
	}
	public String getPayTokenInd() {
		return payTokenInd;
	}
	public void setPayTokenInd(String payTokenInd) {
		this.payTokenInd = payTokenInd;
	}
	public String getPurchaseInstalData() {
		return purchaseInstalData;
	}
	public void setPurchaseInstalData(String purchaseInstalData) {
		this.purchaseInstalData = purchaseInstalData;
	}
	public ThreeDSRequestorAuthenticationInfo getThreeDSRequestorAuthenticationInfo() {
		return threeDSRequestorAuthenticationInfo;
	}
	public void setThreeDSRequestorAuthenticationInfo(
			ThreeDSRequestorAuthenticationInfo threeDSRequestorAuthenticationInfo) {
		this.threeDSRequestorAuthenticationInfo = threeDSRequestorAuthenticationInfo;
	}
	public String getThreeDSRequestorChallengeInd() {
		return threeDSRequestorChallengeInd;
	}
	public void setThreeDSRequestorChallengeInd(String threeDSRequestorChallengeInd) {
		this.threeDSRequestorChallengeInd = threeDSRequestorChallengeInd;
	}
	public String getThreeDSRequestorAuthenticationInd() {
		return threeDSRequestorAuthenticationInd;
	}
	public void setThreeDSRequestorAuthenticationInd(String threeDSRequestorAuthenticationInd) {
		this.threeDSRequestorAuthenticationInd = threeDSRequestorAuthenticationInd;
	}
	public String getThreeRIInd() {
		return threeRIInd;
	}
	public void setThreeRIInd(String threeRIInd) {
		this.threeRIInd = threeRIInd;
	}
	public ThreeDSRequestorPriorAuthenticationInfo getThreeDSRequestorPriorAuthenticationInfo() {
		return threeDSRequestorPriorAuthenticationInfo;
	}
	public void setThreeDSRequestorPriorAuthenticationInfo(ThreeDSRequestorPriorAuthenticationInfo threeDSRequestorPriorAuthenticationInfo) {
		this.threeDSRequestorPriorAuthenticationInfo = threeDSRequestorPriorAuthenticationInfo;
	}
	public String getThreeDSServerRefNumber() {
		return threeDSServerRefNumber;
	}
	public void setThreeDSServerRefNumber(String threeDSServerRefNumber) {
		this.threeDSServerRefNumber = threeDSServerRefNumber;
	}
	public String getThreeDSServerOperatorID() {
		return threeDSServerOperatorID;
	}
	public void setThreeDSServerOperatorID(String threeDSServerOperatorID) {
		this.threeDSServerOperatorID = threeDSServerOperatorID;
	}
	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}
	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}
	public String getThreeDSServerURL() {
		return threeDSServerURL;
	}
	public void setThreeDSServerURL(String threeDSServerURL) {
		this.threeDSServerURL = threeDSServerURL;
	}
	public String getBroadInfo() {
		return broadInfo;
	}
	public void setBroadInfo(String broadInfo) {
		this.broadInfo = broadInfo;
	}
	public String getNotificationURL() {
		return notificationURL;
	}
	public void setNotificationURL(String notificationURL) {
		this.notificationURL = notificationURL;
	}
	public String getThreeDSCompInd() {
		return threeDSCompInd;
	}
	public void setThreeDSCompInd(String threeDSCompInd) {
		this.threeDSCompInd = threeDSCompInd;
	}
	public String getSdkMaxTimeout() {
		return sdkMaxTimeout;
	}
	public void setSdkMaxTimeout(String sdkMaxTimeout) {
		this.sdkMaxTimeout = sdkMaxTimeout;
	}
	public String getAcsURL() {
		return acsURL;
	}
	public void setAcsURL(String acsURL) {
		this.acsURL = acsURL;
	}
	public String getThreeDSReqAuthMethodInd() {
		return threeDSReqAuthMethodInd;
	}
	public void setThreeDSReqAuthMethodInd(String threeDSReqAuthMethodInd) {
		this.threeDSReqAuthMethodInd = threeDSReqAuthMethodInd;
	}
	public String getThreeDSRequestorDecMaxTime() {
		return threeDSRequestorDecMaxTime;
	}
	public void setThreeDSRequestorDecMaxTime(String threeDSRequestorDecMaxTime) {
		this.threeDSRequestorDecMaxTime = threeDSRequestorDecMaxTime;
	}
	public String getThreeDSRequestorDecReqInd() {
		return threeDSRequestorDecReqInd;
	}
	public void setThreeDSRequestorDecReqInd(String threeDSRequestorDecReqInd) {
		this.threeDSRequestorDecReqInd = threeDSRequestorDecReqInd;
	}
	
	public Boolean getBrowserJavascriptEnabled() {
		return browserJavascriptEnabled;
	}
	public void setBrowserJavascriptEnabled(Boolean browserJavascriptEnabled) {
		this.browserJavascriptEnabled = browserJavascriptEnabled;
	}
	public String getPayTokenSource() {
		return payTokenSource;
	}
	public void setPayTokenSource(String payTokenSource) {
		this.payTokenSource = payTokenSource;
	}
	public String getWhiteListStatus() {
		return whiteListStatus;
	}
	public void setWhiteListStatus(String whiteListStatus) {
		this.whiteListStatus = whiteListStatus;
	}
	public String getWhiteListStatusSource() {
		return whiteListStatusSource;
	}
	public void setWhiteListStatusSource(String whiteListStatusSource) {
		this.whiteListStatusSource = whiteListStatusSource;
	}
	
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
	
	