package com.jetco.jpgcerttestproxy.object;

public class CreditPaymentInfo {

	private long txnID;
	private String invoiceNumber;
	private String merchantID;
	private double amount;
	private int currencyCode;
	private String locale;
	private String returnURL;
	private int txnType;
	private boolean isFromMobile;

	private String initVect;
	private String sessionKey;
	private String merchPubKey;

	private String cardType;
	private String pan;
	private String expiryDate;

	private String eci;

	private int mpiVersion;

	// System transaction private key name
	private String systemTxnPvtKeyName;
	private boolean keyStageEnabled;

	// Drop these browser-related fields when not required
	private transient String accept;
	private transient String userAgent;

	// Some 3D-specific fields
	private String acqBin;
	private String xid;
	private String cavv;

	// Some 0400-specific fields
	private String terminalID;
	private String issuerName;
	private String orgTxnID;
	private String orgSysTraceNum;
	private String traceIDInfo;

	// Some CVV2-specific fields
	private String cvv;

	// Some MOTO-specific fields
	private int motoIndicator;

	// Some MasterPass-specific fields
	private String wid;

	// Some shipping info fields
	private boolean reqShippingInfo;

	// Some fields for P2M
	private boolean isP2MTxn;
	private String mobileNumber; // mobile number or p2p id token
	private String paymentType;
	private String merchantCategory;

	// Some fields for P2M SVF
	private String issuerBank;
	private double merchantBankBilling;
	private double jetcoBilling;
	private double issuerBilling;
	private int billingTierStart;
	private int billingTierEnd;

	// Some fields for new Paypage Version
	private int paypageVersion;
	private String displayLanguage;

	// Private variables not to be accessed by the other codes
	//private transient MerchantInfo merchInfo;
	//private transient CurrencyInfo currencyInfo;
	//private transient BankInfo bankInfo;

	// Fields for P2M MDR
	private boolean isP2MMDREnabled;
	private Double lessDiscount;
	private Double netAmount;

	// Fields for Consumer Present QR
	private int customerIdType;
	private String consumerQR; // qr code or bar code
	private boolean genTraceNum;
	private int payMethod;

	// Field for partia Refund
	private String partialRefundInd;

	// Field for browser information (3DS 2.0)
	private String browserLanguage;
	private boolean browserJavaEnabled;
	private boolean browserJavascriptEnabled;
	private String browserColorDepth;
	private String browserScreenHeight;
	private String browserScreenWidth;
	private String merchantRequestURL;
	private String custIP;
	private String browserTZ;
	private String programProtocol;
	private String dsTxnId;
	private String messageVersion;
	
	public long getTxnID() {
		return txnID;
	}
	public void setTxnID(long txnID) {
		this.txnID = txnID;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getReturnURL() {
		return returnURL;
	}
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
	public int getTxnType() {
		return txnType;
	}
	public void setTxnType(int txnType) {
		this.txnType = txnType;
	}
	public boolean isFromMobile() {
		return isFromMobile;
	}
	public void setFromMobile(boolean isFromMobile) {
		this.isFromMobile = isFromMobile;
	}
	public String getInitVect() {
		return initVect;
	}
	public void setInitVect(String initVect) {
		this.initVect = initVect;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getMerchPubKey() {
		return merchPubKey;
	}
	public void setMerchPubKey(String merchPubKey) {
		this.merchPubKey = merchPubKey;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getEci() {
		return eci;
	}
	public void setEci(String eci) {
		this.eci = eci;
	}
	public int getMpiVersion() {
		return mpiVersion;
	}
	public void setMpiVersion(int mpiVersion) {
		this.mpiVersion = mpiVersion;
	}
	public String getSystemTxnPvtKeyName() {
		return systemTxnPvtKeyName;
	}
	public void setSystemTxnPvtKeyName(String systemTxnPvtKeyName) {
		this.systemTxnPvtKeyName = systemTxnPvtKeyName;
	}
	public boolean isKeyStageEnabled() {
		return keyStageEnabled;
	}
	public void setKeyStageEnabled(boolean keyStageEnabled) {
		this.keyStageEnabled = keyStageEnabled;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getAcqBin() {
		return acqBin;
	}
	public void setAcqBin(String acqBin) {
		this.acqBin = acqBin;
	}
	public String getXid() {
		return xid;
	}
	public void setXid(String xid) {
		this.xid = xid;
	}
	public String getCavv() {
		return cavv;
	}
	public void setCavv(String cavv) {
		this.cavv = cavv;
	}
	public String getTerminalID() {
		return terminalID;
	}
	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public String getOrgTxnID() {
		return orgTxnID;
	}
	public void setOrgTxnID(String orgTxnID) {
		this.orgTxnID = orgTxnID;
	}
	public String getOrgSysTraceNum() {
		return orgSysTraceNum;
	}
	public void setOrgSysTraceNum(String orgSysTraceNum) {
		this.orgSysTraceNum = orgSysTraceNum;
	}
	public String getTraceIDInfo() {
		return traceIDInfo;
	}
	public void setTraceIDInfo(String traceIDInfo) {
		this.traceIDInfo = traceIDInfo;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public int getMotoIndicator() {
		return motoIndicator;
	}
	public void setMotoIndicator(int motoIndicator) {
		this.motoIndicator = motoIndicator;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public boolean isReqShippingInfo() {
		return reqShippingInfo;
	}
	public void setReqShippingInfo(boolean reqShippingInfo) {
		this.reqShippingInfo = reqShippingInfo;
	}
	public boolean isP2MTxn() {
		return isP2MTxn;
	}
	public void setP2MTxn(boolean isP2MTxn) {
		this.isP2MTxn = isP2MTxn;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getMerchantCategory() {
		return merchantCategory;
	}
	public void setMerchantCategory(String merchantCategory) {
		this.merchantCategory = merchantCategory;
	}
	public String getIssuerBank() {
		return issuerBank;
	}
	public void setIssuerBank(String issuerBank) {
		this.issuerBank = issuerBank;
	}
	public double getMerchantBankBilling() {
		return merchantBankBilling;
	}
	public void setMerchantBankBilling(double merchantBankBilling) {
		this.merchantBankBilling = merchantBankBilling;
	}
	public double getJetcoBilling() {
		return jetcoBilling;
	}
	public void setJetcoBilling(double jetcoBilling) {
		this.jetcoBilling = jetcoBilling;
	}
	public double getIssuerBilling() {
		return issuerBilling;
	}
	public void setIssuerBilling(double issuerBilling) {
		this.issuerBilling = issuerBilling;
	}
	public int getBillingTierStart() {
		return billingTierStart;
	}
	public void setBillingTierStart(int billingTierStart) {
		this.billingTierStart = billingTierStart;
	}
	public int getBillingTierEnd() {
		return billingTierEnd;
	}
	public void setBillingTierEnd(int billingTierEnd) {
		this.billingTierEnd = billingTierEnd;
	}
	public int getPaypageVersion() {
		return paypageVersion;
	}
	public void setPaypageVersion(int paypageVersion) {
		this.paypageVersion = paypageVersion;
	}
	public String getDisplayLanguage() {
		return displayLanguage;
	}
	public void setDisplayLanguage(String displayLanguage) {
		this.displayLanguage = displayLanguage;
	}
	public boolean isP2MMDREnabled() {
		return isP2MMDREnabled;
	}
	public void setP2MMDREnabled(boolean isP2MMDREnabled) {
		this.isP2MMDREnabled = isP2MMDREnabled;
	}
	public Double getLessDiscount() {
		return lessDiscount;
	}
	public void setLessDiscount(Double lessDiscount) {
		this.lessDiscount = lessDiscount;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	public int getCustomerIdType() {
		return customerIdType;
	}
	public void setCustomerIdType(int customerIdType) {
		this.customerIdType = customerIdType;
	}
	public String getConsumerQR() {
		return consumerQR;
	}
	public void setConsumerQR(String consumerQR) {
		this.consumerQR = consumerQR;
	}
	public boolean isGenTraceNum() {
		return genTraceNum;
	}
	public void setGenTraceNum(boolean genTraceNum) {
		this.genTraceNum = genTraceNum;
	}
	public int getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}
	public String getPartialRefundInd() {
		return partialRefundInd;
	}
	public void setPartialRefundInd(String partialRefundInd) {
		this.partialRefundInd = partialRefundInd;
	}
	public String getBrowserLanguage() {
		return browserLanguage;
	}
	public void setBrowserLanguage(String browserLanguage) {
		this.browserLanguage = browserLanguage;
	}
	public boolean isBrowserJavaEnabled() {
		return browserJavaEnabled;
	}
	public void setBrowserJavaEnabled(boolean browserJavaEnabled) {
		this.browserJavaEnabled = browserJavaEnabled;
	}
	public boolean isBrowserJavascriptEnabled() {
		return browserJavascriptEnabled;
	}
	public void setBrowserJavascriptEnabled(boolean browserJavascriptEnabled) {
		this.browserJavascriptEnabled = browserJavascriptEnabled;
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
	public String getMerchantRequestURL() {
		return merchantRequestURL;
	}
	public void setMerchantRequestURL(String merchantRequestURL) {
		this.merchantRequestURL = merchantRequestURL;
	}
	public String getCustIP() {
		return custIP;
	}
	public void setCustIP(String custIP) {
		this.custIP = custIP;
	}
	public String getBrowserTZ() {
		return browserTZ;
	}
	public void setBrowserTZ(String browserTZ) {
		this.browserTZ = browserTZ;
	}
	public String getProgramProtocol() {
		return programProtocol;
	}
	public void setProgramProtocol(String programProtocol) {
		this.programProtocol = programProtocol;
	}
	public String getDsTxnId() {
		return dsTxnId;
	}
	public void setDsTxnId(String dsTxnId) {
		this.dsTxnId = dsTxnId;
	}
	public String getMessageVersion() {
		return messageVersion;
	}
	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}
	
	

}
