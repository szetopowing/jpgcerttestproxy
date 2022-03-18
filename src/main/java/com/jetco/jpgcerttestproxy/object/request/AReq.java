package com.jetco.jpgcerttestproxy.object.request;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.jetco.jpgcerttestproxy.object.subfield.AcctInfo;
import com.jetco.jpgcerttestproxy.object.subfield.DeviceRenderOptions;
import com.jetco.jpgcerttestproxy.object.subfield.HomePhone;
import com.jetco.jpgcerttestproxy.object.subfield.MerchantRiskIndicator;
import com.jetco.jpgcerttestproxy.object.subfield.MobilePhone;
import com.jetco.jpgcerttestproxy.object.subfield.SDKEphemPubKey;
import com.jetco.jpgcerttestproxy.object.subfield.ThreeDSRequestorAuthenticationInfo;
import com.jetco.jpgcerttestproxy.object.subfield.ThreeDSRequestorPriorAuthenticationInfo;
import com.jetco.jpgcerttestproxy.object.subfield.WorkPhone;

public abstract class AReq {
	
	protected String threeDSCompInd;  
	protected String threeDSRequestorID;
	protected String threeDSRequestorName;
	protected String threeDSRequestorURL; 
	protected String acquirerBIN; 
	protected String acquirerMerchantID;
	protected String cardExpiryDate;  
	protected String acctNumber;  
	protected String deviceChannel;   
	protected String browserAcceptHeader; 
	protected String browserIP;  
	protected String browserJavaEnabled;  
	protected String browserLanguage;    
	protected String browserColorDepth;  
	protected String browserScreenHeight;
	protected String browserScreenWidth; 
	protected String browserTZ;  
	protected String browserUserAgent;  
	protected String mcc;    
	protected String merchantCountryCode;
	protected String merchantName;       
	protected String messageCategory;    
	protected String messageType;    
	protected String messageVersion; 
	protected String notificationURL; 
	protected String purchaseAmount;  
	protected String purchaseCurrency;
	protected String purchaseExponent;
	protected String purchaseDate; 
	protected String transType; 
	protected String threeDSServerURL;  
	protected String threeDSServerTransID; 
	protected String threeDSServerRefNumber; 
	protected String threeDSRequestorAuthenticationInd; 
	protected String threeDSRequestorChallengeInd; 
	protected String threeDSServerOperatorID;  
	protected Boolean browserJavascriptEnabled;
	protected String purchaseInstalData;   
	protected String recurringExpiry;   
	protected String recurringFrequency; 
	protected String acctType;  
	protected String billAddrCity;  
	protected String billAddrCountry;      
	protected String billAddrLine1;    
	protected String billAddrLine2;    
	protected String billAddrLine3;    
	protected String billAddrPostCode; 
	protected String billAddrState;     
	protected String email;        
	protected HomePhone homePhone;    
	protected MobilePhone mobilePhone;   
	protected String cardholderName;    
	protected String shipAddrCity;     
	protected String shipAddrCountry;   
	protected String shipAddrLine1;    
	protected String shipAddrLine2;    
	protected String shipAddrLine3;    
	protected String shipAddrPostCode;      
	protected String shipAddrState;      
	protected WorkPhone workPhone;   
	protected String payTokenInd;   	
	protected String addrMatch;  
	protected AcctInfo acctInfo; 
	protected String acctID; 
	protected ThreeDSRequestorAuthenticationInfo threeDSRequestorAuthenticationInfo;     
	protected ThreeDSRequestorPriorAuthenticationInfo threeDSRequestorPriorAuthenticationInfo; 
	protected MerchantRiskIndicator merchantRiskIndicator;  
	protected DeviceRenderOptions deviceRenderOptions; 
	protected String sdkAppID;   
	protected Object sdkEncData;   
	protected SDKEphemPubKey sdkEphemPubKey;
	protected String sdkTransID;   
	protected String sdkReferenceNumber;
	protected String sdkMaxTimeout; 
	protected String threeRIInd; 

	protected String broadInfo; //miss in 2.2
	protected String dsReferenceNumber; //miss in both 2.1 and 2.2
	protected String deviceInfo; //miss in both 2.1 and 2.2
	protected String dsTransID; //miss in both 2.1 and 2.2
	protected String dsURL; //miss in both 2.1 and 2.2
	protected String messageExtension; //miss in both 2.1 and 2.2
	
	@Value("${testingEnv}")
	private String testingEnv;

	@Value("${atomWork.threeDSServerRefNumber}")
	private String atomWorkThreeDSServerRefNumber;
	
	@Value("${visa.threeDSServerRefNumber}")
	private String visaThreeDSServerRefNumber;
	
	@Value("${master.threeDSServerRefNumber}")
	private String masterThreeDSServerRefNumber;
	
    public abstract Map<String, String> validateMsg();
	
	public AReq mapPArq(PArq pArq, String testingEnv, String _threeDSServerRefNumber, String visaThreeDSServerRefNumber) {
		
		String threeDSServerRefNumber = "";
		
		if (testingEnv.equals("ATOMWORK") || testingEnv.equals("MASTER")) {
			threeDSServerRefNumber = _threeDSServerRefNumber;
		} else if (testingEnv.equals("VISA")) {
			threeDSServerRefNumber = visaThreeDSServerRefNumber;
		} 

		this.setAcctNumber(pArq.getAcctNumber());
		this.setCardExpiryDate(pArq.getCardExpiryDate());
		this.setDeviceChannel(pArq.getDeviceChannel());
		this.setMessageCategory(pArq.getMessageCategory());
		this.setMessageType("AReq");
		this.setMessageVersion(pArq.getMessageVersion());
		this.setAcctInfo(pArq.getAcctInfo());
		// p_messageVersion
		this.setThreeDSRequestorID(pArq.getThreeDSRequestorID());
		this.setThreeDSRequestorName(pArq.getThreeDSRequestorName());
		this.setThreeDSRequestorURL(pArq.getThreeDSRequestorURL());
		this.setAcquirerBIN(pArq.getAcquirerBIN());
		this.setAcquirerMerchantID(pArq.getAcquirerMerchantID());
		this.setAddrMatch(pArq.getAddrMatch());
		this.setBillAddrCity(pArq.getBillAddrCity());
		this.setBillAddrCountry(pArq.getBillAddrCountry());
		this.setBillAddrLine1(pArq.getBillAddrLine1());
		this.setBillAddrLine2(pArq.getBillAddrLine2());
		this.setBillAddrLine3(pArq.getBillAddrLine3());
		this.setBillAddrPostCode(pArq.getBillAddrPostCode());
		this.setBillAddrState(pArq.getBillAddrState());
		this.setBrowserAcceptHeader(pArq.getBrowserAcceptHeader());
		this.setBrowserColorDepth(pArq.getBrowserColorDepth());
		this.setBrowserIP(pArq.getBrowserIP());
		this.setBrowserJavaEnabled(pArq.getBrowserJavaEnabled());
		this.setBrowserLanguage(pArq.getBrowserLanguage());
		this.setBrowserScreenHeight(pArq.getBrowserScreenHeight());
		this.setBrowserScreenWidth(pArq.getBrowserScreenWidth());
		this.setBrowserTZ(pArq.getBrowserTZ());
		this.setBrowserUserAgent(pArq.getBrowserUserAgent());
		this.setCardholderName(pArq.getCardholderName());
		this.setDeviceRenderOptions(pArq.getDeviceRenderOptions());
		this.setEmail(pArq.getEmail());
		this.setHomePhone(pArq.getHomePhone());
		this.setMcc(pArq.getMcc());
		this.setMerchantCountryCode(pArq.getMerchantCountryCode());
		this.setMerchantName(pArq.getMerchantName());
		this.setMobilePhone(pArq.getMobilePhone());
		this.setPurchaseAmount(pArq.getPurchaseAmount());
		this.setPurchaseCurrency(pArq.getPurchaseCurrency());
		this.setPurchaseDate(pArq.getPurchaseDate());
		this.setPurchaseExponent(pArq.getPurchaseExponent());
		this.setRecurringExpiry(pArq.getRecurringExpiry());
		this.setRecurringFrequency(pArq.getRecurringFrequency());
		this.setSdkAppID(pArq.getSdkAppID());
		this.setSdkEncData(pArq.getSdkEncData());
		this.setSdkEphemPubKey(pArq.getSdkEphemPubKey());
		this.setSdkReferenceNumber(pArq.getSdkReferenceNumber());
		this.setSdkTransID(pArq.getSdkTransID());
		this.setShipAddrCity(pArq.getShipAddrCity());
		this.setShipAddrCountry(pArq.getShipAddrCountry());
		this.setShipAddrLine1(pArq.getShipAddrLine1());
		this.setShipAddrLine2(pArq.getShipAddrLine2());
		this.setShipAddrLine3(pArq.getShipAddrLine3());
		this.setShipAddrPostCode(pArq.getShipAddrPostCode());
		this.setShipAddrState(pArq.getShipAddrState());
		this.setTransType(pArq.getTransType());
		this.setWorkPhone(pArq.getWorkPhone());
		this.setAcctID(pArq.getAcctID());
		this.setAcctType(pArq.getAcctType());
		this.setMerchantRiskIndicator(pArq.getMerchantRiskIndicator());
		this.setMessageExtension(pArq.getMessageExtension());
		this.setPayTokenInd(pArq.getPayTokenInd());
		this.setPurchaseInstalData(pArq.getPurchaseInstalData());
		this.setThreeDSRequestorAuthenticationInd(pArq.getThreeDSRequestorAuthenticationInd());
		this.setThreeRIInd(pArq.getThreeRIInd());
		this.setThreeDSRequestorPriorAuthenticationInfo(pArq.getThreeDSRequestorPriorAuthenticationInfo());
		this.setThreeDSServerRefNumber(threeDSServerRefNumber);
		this.setThreeDSServerOperatorID(pArq.getThreeDSServerOperatorID()); // ??
		//this.setThreeDSServerOperatorID("SVR-V210-JOINT_ELECTRONIC_-66391");
		this.setThreeDSServerTransID(UUID.randomUUID().toString());
		this.setThreeDSServerURL("https://djpg.jtetbwkl.com.hk:4443/PayPage/receivePArqServlet");
		this.setBroadInfo(pArq.getBroadInfo());
		this.setNotificationURL(pArq.getNotificationURL());
		this.setThreeDSCompInd(pArq.getThreeDSCompInd());
		this.setSdkMaxTimeout(pArq.getSdkMaxTimeout());
		this.setThreeDSRequestorChallengeInd(pArq.getThreeDSRequestorChallengeInd());
		this.setThreeDSRequestorAuthenticationInfo(pArq.getThreeDSRequestorAuthenticationInfo());
		this.setBrowserJavascriptEnabled(pArq.getBrowserJavascriptEnabled());

		return this;
	}
	
	public String getThreeDSCompInd() {
		return threeDSCompInd;
	}
	public void setThreeDSCompInd(String threeDSCompInd) {
		this.threeDSCompInd = threeDSCompInd;
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
	public String getCardExpiryDate() {
		return cardExpiryDate;
	}
	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}
	public String getAcctNumber() {
		return acctNumber;
	}
	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	public String getDeviceChannel() {
		return deviceChannel;
	}
	public void setDeviceChannel(String deviceChannel) {
		this.deviceChannel = deviceChannel;
	}
	public String getBrowserAcceptHeader() {
		return browserAcceptHeader;
	}
	public void setBrowserAcceptHeader(String browserAcceptHeader) {
		this.browserAcceptHeader = browserAcceptHeader;
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
	public String getBrowserColorDepth() {
		return browserColorDepth;
	}
	public void setBrowserColorDepth(String browserColorDepth) {
		this.browserColorDepth = browserColorDepth;
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
	public String getNotificationURL() {
		return notificationURL;
	}
	public void setNotificationURL(String notificationURL) {
		this.notificationURL = notificationURL;
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
	public String getPurchaseExponent() {
		return purchaseExponent;
	}
	public void setPurchaseExponent(String purchaseExponent) {
		this.purchaseExponent = purchaseExponent;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getThreeDSServerURL() {
		return threeDSServerURL;
	}
	public void setThreeDSServerURL(String threeDSServerURL) {
		this.threeDSServerURL = threeDSServerURL;
	}
	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}
	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}
	public String getThreeDSServerRefNumber() {
		return threeDSServerRefNumber;
	}
	public void setThreeDSServerRefNumber(String threeDSServerRefNumber) {
		this.threeDSServerRefNumber = threeDSServerRefNumber;
	}
	public String getThreeDSRequestorAuthenticationInd() {
		return threeDSRequestorAuthenticationInd;
	}
	public void setThreeDSRequestorAuthenticationInd(String threeDSRequestorAuthenticationInd) {
		this.threeDSRequestorAuthenticationInd = threeDSRequestorAuthenticationInd;
	}
	public String getThreeDSRequestorChallengeInd() {
		return threeDSRequestorChallengeInd;
	}
	public void setThreeDSRequestorChallengeInd(String threeDSRequestorChallengeInd) {
		this.threeDSRequestorChallengeInd = threeDSRequestorChallengeInd;
	}
	public String getThreeDSServerOperatorID() {
		return threeDSServerOperatorID;
	}
	public void setThreeDSServerOperatorID(String threeDSServerOperatorID) {
		this.threeDSServerOperatorID = threeDSServerOperatorID;
	}
//	public Boolean getBrowserJavascriptEnabled() {
//		return browserJavascriptEnabled;
//	}
//	public void setBrowserJavascriptEnabled(Boolean browserJavascriptEnabled) {
//		this.browserJavascriptEnabled = browserJavascriptEnabled;
//	}
	public Boolean getBrowserJavascriptEnabled() {
		return browserJavascriptEnabled;
	}

	public void setBrowserJavascriptEnabled(Boolean browserJavascriptEnabled) {
		this.browserJavascriptEnabled = browserJavascriptEnabled;
	}
	
	public String getPurchaseInstalData() {
		return purchaseInstalData;
	}
	
	public void setPurchaseInstalData(String purchaseInstalData) {
		this.purchaseInstalData = purchaseInstalData;
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
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
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
	public MobilePhone getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(MobilePhone mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
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
	public WorkPhone getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(WorkPhone workPhone) {
		this.workPhone = workPhone;
	}
	public String getPayTokenInd() {
		return payTokenInd;
	}
	public void setPayTokenInd(String payTokenInd) {
		this.payTokenInd = payTokenInd;
	}
	public String getAddrMatch() {
		return addrMatch;
	}
	public void setAddrMatch(String addrMatch) {
		this.addrMatch = addrMatch;
	}
	public AcctInfo getAcctInfo() {
		return acctInfo;
	}
	public void setAcctInfo(AcctInfo acctInfo) {
		this.acctInfo = acctInfo;
	}
	public String getAcctID() {
		return acctID;
	}
	public void setAcctID(String acctID) {
		this.acctID = acctID;
	}
	public ThreeDSRequestorAuthenticationInfo getThreeDSRequestorAuthenticationInfo() {
		return threeDSRequestorAuthenticationInfo;
	}
	public void setThreeDSRequestorAuthenticationInfo(
			ThreeDSRequestorAuthenticationInfo threeDSRequestorAuthenticationInfo) {
		this.threeDSRequestorAuthenticationInfo = threeDSRequestorAuthenticationInfo;
	}
	public ThreeDSRequestorPriorAuthenticationInfo getThreeDSRequestorPriorAuthenticationInfo() {
		return threeDSRequestorPriorAuthenticationInfo;
	}
	public void setThreeDSRequestorPriorAuthenticationInfo(
			ThreeDSRequestorPriorAuthenticationInfo threeDSRequestorPriorAuthenticationInfo) {
		this.threeDSRequestorPriorAuthenticationInfo = threeDSRequestorPriorAuthenticationInfo;
	}
	public MerchantRiskIndicator getMerchantRiskIndicator() {
		return merchantRiskIndicator;
	}
	public void setMerchantRiskIndicator(MerchantRiskIndicator merchantRiskIndicator) {
		this.merchantRiskIndicator = merchantRiskIndicator;
	}
	public DeviceRenderOptions getDeviceRenderOptions() {
		return deviceRenderOptions;
	}
	public void setDeviceRenderOptions(DeviceRenderOptions deviceRenderOptions) {
		this.deviceRenderOptions = deviceRenderOptions;
	}
	public String getSdkAppID() {
		return sdkAppID;
	}
	public void setSdkAppID(String sdkAppID) {
		this.sdkAppID = sdkAppID;
	}
	public Object getSdkEncData() {
		return sdkEncData;
	}
	public void setSdkEncData(Object sdkEncData) {
		this.sdkEncData = sdkEncData;
	}
	public SDKEphemPubKey getSdkEphemPubKey() {
		return sdkEphemPubKey;
	}
	public void setSdkEphemPubKey(SDKEphemPubKey sdkEphemPubKey) {
		this.sdkEphemPubKey = sdkEphemPubKey;
	}
	public String getSdkTransID() {
		return sdkTransID;
	}
	public void setSdkTransID(String sdkTransID) {
		this.sdkTransID = sdkTransID;
	}
	public String getSdkReferenceNumber() {
		return sdkReferenceNumber;
	}
	public void setSdkReferenceNumber(String sdkReferenceNumber) {
		this.sdkReferenceNumber = sdkReferenceNumber;
	}
	public String getSdkMaxTimeout() {
		return sdkMaxTimeout;
	}
	public void setSdkMaxTimeout(String sdkMaxTimeout) {
		this.sdkMaxTimeout = sdkMaxTimeout;
	}
	public String getThreeRIInd() {
		return threeRIInd;
	}
	public void setThreeRIInd(String threeRIInd) {
		this.threeRIInd = threeRIInd;
	}
	public String getBroadInfo() {
		return broadInfo;
	}
	public void setBroadInfo(String broadInfo) {
		this.broadInfo = broadInfo;
	}
	public String getDsReferenceNumber() {
		return dsReferenceNumber;
	}
	public void setDsReferenceNumber(String dsReferenceNumber) {
		this.dsReferenceNumber = dsReferenceNumber;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getDsTransID() {
		return dsTransID;
	}
	public void setDsTransID(String dsTransID) {
		this.dsTransID = dsTransID;
	}
	public String getDsURL() {
		return dsURL;
	}
	public void setDsURL(String dsURL) {
		this.dsURL = dsURL;
	}
	public String getMessageExtension() {
		return messageExtension;
	}
	public void setMessageExtension(String messageExtension) {
		this.messageExtension = messageExtension;
	}

	@Override
	public String toString() {
		return "AReq [threeDSCompInd=" + threeDSCompInd + ", threeDSRequestorID=" + threeDSRequestorID
				+ ", threeDSRequestorName=" + threeDSRequestorName + ", threeDSRequestorURL=" + threeDSRequestorURL
				+ ", acquirerBIN=" + acquirerBIN + ", acquirerMerchantID=" + acquirerMerchantID + ", cardExpiryDate="
				+ cardExpiryDate + ", acctNumber=" + acctNumber + ", deviceChannel=" + deviceChannel
				+ ", browserAcceptHeader=" + browserAcceptHeader + ", browserIP=" + browserIP + ", browserJavaEnabled="
				+ browserJavaEnabled + ", browserLanguage=" + browserLanguage + ", browserColorDepth="
				+ browserColorDepth + ", browserScreenHeight=" + browserScreenHeight + ", browserScreenWidth="
				+ browserScreenWidth + ", browserTZ=" + browserTZ + ", browserUserAgent=" + browserUserAgent + ", mcc="
				+ mcc + ", merchantCountryCode=" + merchantCountryCode + ", merchantName=" + merchantName
				+ ", messageCategory=" + messageCategory + ", messageType=" + messageType + ", messageVersion="
				+ messageVersion + ", notificationURL=" + notificationURL + ", purchaseAmount=" + purchaseAmount
				+ ", purchaseCurrency=" + purchaseCurrency + ", purchaseExponent=" + purchaseExponent
				+ ", purchaseDate=" + purchaseDate + ", transType=" + transType + ", threeDSServerURL="
				+ threeDSServerURL + ", threeDSServerTransID=" + threeDSServerTransID + ", threeDSServerRefNumber="
				+ threeDSServerRefNumber + ", threeDSRequestorAuthenticationInd=" + threeDSRequestorAuthenticationInd
				+ ", threeDSRequestorChallengeInd=" + threeDSRequestorChallengeInd + ", threeDSServerOperatorID="
				+ threeDSServerOperatorID + ", browserJavascriptEnabled=" + browserJavascriptEnabled
				+ ", purchaseInstalData=" + purchaseInstalData + ", recurringExpiry=" + recurringExpiry
				+ ", recurringFrequency=" + recurringFrequency + ", acctType=" + acctType + ", billAddrCity="
				+ billAddrCity + ", billAddrCountry=" + billAddrCountry + ", billAddrLine1=" + billAddrLine1
				+ ", billAddrLine2=" + billAddrLine2 + ", billAddrLine3=" + billAddrLine3 + ", billAddrPostCode="
				+ billAddrPostCode + ", billAddrState=" + billAddrState + ", email=" + email + ", homePhone="
				+ homePhone + ", mobilePhone=" + mobilePhone + ", cardholderName=" + cardholderName + ", shipAddrCity="
				+ shipAddrCity + ", shipAddrCountry=" + shipAddrCountry + ", shipAddrLine1=" + shipAddrLine1
				+ ", shipAddrLine2=" + shipAddrLine2 + ", shipAddrLine3=" + shipAddrLine3 + ", shipAddrPostCode="
				+ shipAddrPostCode + ", shipAddrState=" + shipAddrState + ", workPhone=" + workPhone + ", payTokenInd="
				+ payTokenInd + ", addrMatch=" + addrMatch + ", acctInfo=" + acctInfo + ", acctID=" + acctID
				+ ", threeDSRequestorAuthenticationInfo=" + threeDSRequestorAuthenticationInfo
				+ ", threeDSRequestorPriorAuthenticationInfo=" + threeDSRequestorPriorAuthenticationInfo
				+ ", merchantRiskIndicator=" + merchantRiskIndicator + ", deviceRenderOptions=" + deviceRenderOptions
				+ ", sdkAppID=" + sdkAppID + ", sdkEncData=" + sdkEncData + ", sdkEphemPubKey=" + sdkEphemPubKey
				+ ", sdkTransID=" + sdkTransID + ", sdkReferenceNumber=" + sdkReferenceNumber + ", sdkMaxTimeout="
				+ sdkMaxTimeout + ", threeRIInd=" + threeRIInd + ", broadInfo=" + broadInfo + ", dsReferenceNumber="
				+ dsReferenceNumber + ", deviceInfo=" + deviceInfo + ", dsTransID=" + dsTransID + ", dsURL=" + dsURL
				+ ", messageExtension=" + messageExtension + ", testingEnv=" + testingEnv
				+ ", atomWorkThreeDSServerRefNumber=" + atomWorkThreeDSServerRefNumber + ", visaThreeDSServerRefNumber="
				+ visaThreeDSServerRefNumber + ", masterThreeDSServerRefNumber=" + masterThreeDSServerRefNumber + "]";
	}
	
}
