package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.threeDSenum.MessageCategory;
import com.jetco.jpgcerttestproxy.util.FormCheckUtils;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class AReq {
	
	static final Logger log = LoggerFactory.getLogger(AReq.class);

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

	protected String broadInfo; // miss in 2.2
	protected String dsReferenceNumber; // miss in both 2.1 and 2.2
	protected String deviceInfo; // miss in both 2.1 and 2.2
	protected String dsTransID; // miss in both 2.1 and 2.2
	protected String dsURL; // miss in both 2.1 and 2.2
	protected String messageExtension; // miss in both 2.1 and 2.2

	@Value("${testingEnv}")
	private String testingEnv;

	@Value("${atomWork.threeDSServerRefNumber}")
	private String atomWorkThreeDSServerRefNumber;

	@Value("${visa.threeDSServerRefNumber}")
	private String visaThreeDSServerRefNumber;

	@Value("${master.threeDSServerRefNumber}")
	private String masterThreeDSServerRefNumber;

	// public abstract Map<String, String> validateMsg();

	public AReq mapPArq(PArq pArq, String testingEnv, String _threeDSServerRefNumber,
			String visaThreeDSServerRefNumber) {

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
		// this.setThreeDSServerOperatorID("SVR-V210-JOINT_ELECTRONIC_-66391");
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

	public Map<String, String> validateMsg() {

		//log.info("In AReq validateMsg!!");

		Map<String, String> erro = new HashMap<String, String>();

		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("AReq : Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion210(messageVersion)) {
			log.error("AReq : Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("AReq : Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_AREQ)) {
			log.error("AReq : Incorrect Message Type for AReq Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");
			return erro;
		}

		// Device Channel
		log.info("deviceChannel =" + deviceChannel);
		if (StringUtils.isEmpty(deviceChannel)) {
			log.error("AReq : Device Channel is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "deviceChannel");
			return erro;
		}

		if (deviceChannel.length() != 2) {
			log.error("AReq : Invalid length in Device Channel ");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "deviceChannel");
			return erro;
		}

		if (!StringUtils.isNumeric(deviceChannel)) {
			log.error("AReq : Device Channel is not numeric");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "deviceChannel");
			return erro;
		}

		// 3DS Method Completion Indicator
		if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
			if (StringUtils.isEmpty(threeDSCompInd)) {
				log.error("AReq : 3DS Method Completion Indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSCompInd");
				return erro;
			}

			if (threeDSCompInd.length() != 1) {
				log.error("AReq : Invalid length in 3DS Method Completion Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSCompInd");
				return erro;
			}

			if (!StringUtils.isAlpha(threeDSCompInd)) {
				log.error("AReq : 3DS Method Completion Indicator is not an alphabet character");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSCompInd");
				return erro;
			}

			log.info("browserAcceptHeader =" + browserAcceptHeader);
			// Browser Accept Headers
			if (StringUtils.isEmpty(browserAcceptHeader)) {
				log.error("AReq : Browser Accept Headers is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserAcceptHeader");
				return erro;
			}

			if (browserAcceptHeader.length() > 2048) {
				log.error("AReq : Invalid length in Browser Accept Headers");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserAcceptHeader");
				return erro;
			}

			// browserIP
			if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
				if (!StringUtils.isEmpty(browserIP)) {
					if (browserIP.length() > 45) {
						log.error("AReq : Invalid length in Browser IP Address");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserIP");
						return erro;
					}

					if (!FormCheckUtils.isValidIPv4AndIPv6Address(browserIP)) {
						log.error("AReq : Invalid Browser IP Address [" + browserIP + "]");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserIP");
						return erro;
					}
				}
			}

			if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
				if (browserJavaEnabled == null) {
					// browserJavaEnabled
					log.error("AReq : Browser Java Enabeld is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserJavaEnabled");
					return erro;
				}

				if (StringUtils.isEmpty(browserColorDepth)) {
					// browserColorDepth
					log.error("AReq : Browser Screen Color Depth is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserColorDepth");
					return erro;
				}

				if (StringUtils.isEmpty(browserScreenHeight)) {
					// browserScreenHeight
					log.error("AReq : Browser Screen Height is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserScreenHeight");
					return erro;
				}

				if (StringUtils.isEmpty(browserScreenWidth)) {
					// browserScreenWidth
					log.error("AReq : Browser Screen Width is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserScreenWidth");
					return erro;
				}

				if (StringUtils.isEmpty(browserTZ)) {
					// browserTZ
					log.error("AReq : Browser Time Zone is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserTZ");
					return erro;
				}
			}

			if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
				// Browser Screen Color Depth
				if (!StringUtils.isEmpty(browserColorDepth)) {
					if (StringUtils.isEmpty(browserTZ) && browserColorDepth.length() > 2) {
						log.error("AReq : Invalid length in Browser Screen Color Depth");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserColorDepth");
						return erro;
					}

					if (!StringUtils.isNumeric(browserColorDepth)) {
						log.error("AReq : Browser Screen Color Depth is not numeric");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserColorDepth");
						return erro;
					}
				}

				// Browser Screen Height
				if (!StringUtils.isEmpty(browserScreenHeight)) {
					if (browserScreenHeight.length() > 6) {
						log.error("AReq : Invalid length in Browser Screen Height");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenHeight");
						return erro;
					}

					if (!StringUtils.isNumeric(browserScreenHeight)) {
						log.error("AReq : Browser Screen Height is not numeric");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenHeight");
						return erro;
					}
				}

				// Browser Screen Width
				if (!StringUtils.isEmpty(browserScreenWidth)) {
					if (browserScreenWidth.length() > 6) {
						log.error("AReq : Invalid length in Browser Screen Width");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenWidth");
						return erro;
					}

					if (!StringUtils.isNumeric(browserScreenWidth)) {
						log.error("AReq : Browser Screen Width is not numeric");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenWidth");
						return erro;
					}
				}

				// Browser Time Zone
				if (!StringUtils.isEmpty(browserTZ) && browserTZ.length() > 5) {
					log.error("AReq : Invalid length in Browser Time Zone");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserTZ");
					return erro;
				}

				// Browser User-Agent
				if (StringUtils.isEmpty(browserUserAgent)) {
					log.error("AReq : Browser User-Agent is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserUserAgent");
					return erro;
				}

				if (browserUserAgent.length() > 2048) {
					log.error("AReq : Invalid length in Browser User-Agent");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserUserAgent");
					return erro;
				}

				// Browser Language
				if (StringUtils.isEmpty(browserLanguage)) {
					log.error("AReq : Browser Language is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserLanguage");
					return erro;
				}

				if (browserLanguage.length() > 8) {
					log.error("AReq : Invalid length in Browser Language");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserLanguage");
					return erro;
				}
			}
		}

		// Message Category
		if (StringUtils.isEmpty(messageCategory)) {
			log.error("AReq : Message Category is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageCategory");
			return erro;
		}

		if (messageCategory.length() != 2) {
			log.error("AReq : Invalid length in Message Category");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		if (!StringUtils.isNumeric(messageCategory)) {
			log.error("AReq : Message Category is not numeric");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageCategory(messageCategory)) {
			log.error("AReq : Invalid Message Category [" + messageCategory + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		// Transaction Type being authenticated
		if (messageCategory.equals(ThreeD20Utils.MESSAGE_CATEGORY_PA)) {
			if (StringUtils.isEmpty(transType)) {
				log.error("AReq : Transaction Type being authenticated is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transType");
				return erro;
			}

			if (transType.length() != 2) {
				log.error("AReq : Invalid length in Transaction Type being authenticated");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transType");
				return erro;
			}

			if (!StringUtils.isNumeric(transType)) {
				log.error("AReq : Transaction Type being authenticated is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transType");
				return erro;
			}
		}

		// 3DS Server URL (ACS Return URL)
		if (StringUtils.isEmpty(threeDSServerURL)) {
			log.error("AReq : 3DS Server URL (ACS Return URL) is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerURL");
			return erro;
		}

		if (!StringUtil.isValidHTTPURL(threeDSServerURL)) {
			log.error("AReq : Invalid 3DS Server URL (ACS Return URL) [" + threeDSServerURL + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerURL");
			return erro;
		}

		// 3DS Server Reference Number
		if (StringUtils.isEmpty(threeDSServerRefNumber)) {
			log.error("AReq : 3DS Server Reference Number is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerRefNumber");
			return erro;
		}

		if (threeDSServerRefNumber.length() > 32) {
			log.error("AReq : Invalid length in 3DS Server Reference Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerRefNumber");
			return erro;
		}

		// 3DS Requestor Authentication Indicator
		if (threeDSRequestorAuthenticationInd != null) {
			if (StringUtils.isEmpty(threeDSRequestorAuthenticationInd)) {
				log.error("AReq : 3DS Requestor Authentication Indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorAuthenticationInd");
				return erro;
			}

			if (threeDSRequestorAuthenticationInd.length() != 2) {
				log.error("AReq : Invalid length in 3DS Requestor Authentication Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorAuthenticationInd");
				return erro;
			}

			if (!StringUtils.isNumeric(threeDSRequestorAuthenticationInd)) {
				log.error("AReq : 3DS Requestor Authentication Indicator is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorAuthenticationInd");
				return erro;
			}
		}

		// 3DS Requestor Challenge Indicator (Optional field)
		if (!StringUtils.isEmpty(threeDSRequestorChallengeInd)) {
			if (threeDSRequestorChallengeInd.length() != 2) {
				log.error("AReq : Invalid length in 3DS Requestor Challenge Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorChallengeInd");
				return erro;
			}

			if (!StringUtils.isNumeric(threeDSRequestorChallengeInd)) {
				log.error("AReq : 3DS Requestor Challenge Indicator is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorChallengeInd");
				return erro;
			}
		}

		// 3DS Server Operator ID (Optional field)
		if (!StringUtils.isEmpty(threeDSServerOperatorID)) {
			if (threeDSServerOperatorID.length() > 32) {
				log.error("AReq : Invalid length in 3DS Server Operator ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerOperatorID");
				return erro;
			}
		}

		// 3DS Requestor ID (Assigned by DS)
		if (StringUtils.isEmpty(threeDSRequestorID)) {
			log.error("AReq : 3DS Requestor ID (Assigned by DS) is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorID");
			return erro;
		}

		if (threeDSRequestorID.length() > 35) {
			log.error("AReq : Invalid length in 3DS Requestor ID (Assigned by DS)");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorID");
			return erro;
		}

		// Acquirer Bin
		if (acquirerBIN != null) {
			if (StringUtils.isEmpty(acquirerBIN)) {
				log.error("AReq : Acquirer Bin is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acquirerBIN");
				return erro;
			}

			if (acquirerBIN.length() > 11) {
				log.error("AReq : Invalid length in Acquirer Bin");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acquirerBIN");
				return erro;
			}
		}

		// Acquirer Merchant ID
		if (acquirerMerchantID != null) {
			if (StringUtils.isEmpty(acquirerMerchantID)) {
				log.error("AReq : Acquirer Merchant ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acquirerMerchantID");
				return erro;
			}

			if (acquirerMerchantID.length() > 35) {
				log.error("AReq : Invalid length in Acquirer Merchant ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acquirerMerchantID");
				return erro;
			}
		}

		// 3DS Requestor Name (Assigned by DS)
		if (StringUtils.isEmpty(threeDSRequestorName)) {
			log.error("AReq : 3DS Requestor Name (Assigned by DS) is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorName");
			return erro;
		}

		if (threeDSRequestorName.length() > 40) {
			log.error("AReq : Invalid length in 3DS Requestor Name (Assigned by DS)");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorName");
			return erro;
		}

		// 3DS Requestor URL
		if (StringUtils.isEmpty(threeDSRequestorURL)) {
			log.error("AReq : 3DS Requestor URL is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorURL");
			return erro;
		}

		if (threeDSRequestorURL.length() > 2048) {
			log.error("AReq : Invalid length in 3DS Requestor URL");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorURL");
			return erro;
		}

		if (!StringUtil.isValidHTTPURL(threeDSRequestorURL)) {
			log.error("AReq : Invalid 3DS Requestor URL [" + threeDSRequestorURL + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorURL");
			return erro;
		}

		// Merchant Category Code
		if (mcc != null) {
			if (StringUtils.isEmpty(mcc)) {
				log.error("AReq : Merchant Category Code is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "mcc");
				return erro;
			}

			if (mcc.length() != 4) {
				log.error("AReq : Invalid length in Merchant Category Code");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "mcc");
				return erro;
			}
		}

		// Merchant Country Code
		if (merchantCountryCode != null) {
			if (StringUtils.isEmpty(merchantCountryCode)) {
				log.error("AReq : Merchant Country Code is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "merchantCountryCode");
				return erro;
			}

			if (merchantCountryCode.length() != 3) {
				log.error("AReq : Invalid length in Merchant Country Code");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "merchantCountryCode");
				return erro;
			}

			if (!StringUtils.isNumeric(merchantCountryCode)) {
				log.error("AReq : Merchant Country Code is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "merchantCountryCode");
				return erro;
			}
		}

		// Merchant Name
		if (merchantName != null) {
			if (StringUtils.isEmpty(merchantName)) {
				log.error("AReq : Merchant Name is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "merchantName");
				return erro;
			}

			if (merchantName.length() > 40) {
				log.error("AReq : Invalid length in Merchant Name");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "merchantName");
				return erro;
			}
		}

		// Cardholder Account Number
		if (StringUtils.isEmpty(acctNumber)) {
			log.error("AReq : Cardholder Account Number is missing");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acctNumber");
			return erro;
		}

		if (acctNumber.length() < 13 || acctNumber.length() > 19) {
			log.error("AReq : Invalid length in Cardholder Account Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acctNumber");
			return erro;
		}

		if (!StringUtils.isNumeric(acctNumber)) {
			log.error("AReq : Cardholder Account Number is not numeric");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acctNumber");
			return erro;
		}

		/*
		 * VISA Cert Test : 3DSS-210-101 fix missing mandate
		 */
		// Purchase Amount
		if (messageCategory.equals(MessageCategory.PA.getValue())
				|| (messageCategory.equals(MessageCategory.NPA.getValue())
						&& ((threeDSRequestorAuthenticationInd + "").equals("02")
								|| (threeDSRequestorAuthenticationInd + "").equals("03")))) {
			if (StringUtils.isEmpty(purchaseAmount)) {
				log.error("AReq : Purchase Amount is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseAmount");
				return erro;
			}
			if (StringUtils.isEmpty(purchaseCurrency)) {
				log.error("AReq : Purchase Currency is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseCurrency");
				return erro;
			}
			if (StringUtils.isEmpty(purchaseExponent)) {
				log.error("AReq : Purchase Currency Exponent is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseExponent");
				return erro;
			}
			if (StringUtils.isEmpty(purchaseDate)) {
				log.error("AReq : Purchase Date & Time is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseDate");
				return erro;
			}
		}

		if (!StringUtils.isEmpty(purchaseAmount)) {

			if (purchaseAmount.length() > 48) {
				log.error("AReq : Invalid length in Purchase Amount");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseAmount");
				return erro;
			}

			if (!StringUtils.isNumeric(purchaseAmount)) {
				log.error("AReq : Purchase Amount is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseAmount");
				return erro;
			}

		}

		/*
		 * VISA Cert Test : 3DSS-210-101 fix missing mandate
		 */
		// Purchase Currency
		// CreditDOHandler doHandler = new CreditDOHandler();
		int expectedCurrencyExponent = -1;
		if (!StringUtils.isEmpty(purchaseCurrency)) {
			if (purchaseCurrency.length() != 3) {
				log.error("AReq : Invalid length in Purchase Currency");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseCurrency");
				return erro;
			}

			if (!StringUtils.isNumeric(purchaseCurrency)) {
				log.error("AReq : Purchase Currency is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseCurrency");
				return erro;
			}
		}

		// try {
		// expectedCurrencyExponent =
		// doHandler.checkCurrencyCode(Integer.parseInt(purchaseCurrency));
		// } catch (Exception e) {
		// log.error("AReq v2.2.0: Invalid Purchase Currency. " + e);
		// erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
		// ErrorResponse.ErrorCode203.getErrorDesc());
		// return erro;
		// }

		/*
		 * VISA Cert Test : 3DSS-210-101 fix missing mandate
		 */
		// Purchase Currency Exponent
		if (!StringUtils.isEmpty(purchaseExponent)) {

			if (purchaseExponent.length() != 1) {
				log.error("AReq : Invalid length in Purchase CurrencyExponent");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseExponent");
				return erro;
			}

			if (!StringUtils.isNumeric(purchaseExponent)) {
				log.error("AReq : Purchase Currency Exponent is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseExponent");
				return erro;
			}
		}

		if (expectedCurrencyExponent != -1 && Integer.parseInt(purchaseExponent) != expectedCurrencyExponent) {
			log.error("AReq : Invalid Purchase Currency Exponent [" + purchaseExponent + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseExponent");
			return erro;
		}

		/*
		 * VISA Cert Test : 3DSS-210-101 fix missing mandate
		 */
		// Purchase Date & Time
		if (!StringUtils.isEmpty(purchaseDate)) {
			if (purchaseDate.length() != 14) {
				log.error("AReq : Invalid length in Purchase Date & Time");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseDate");
				return erro;
			}

			if (!StringUtil.isDateOfFormat(purchaseDate, "yyyyMMddHHmmss")) {
				log.error("AReq : Invalid Purchase Date & Time [" + purchaseDate + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseDate");
				return erro;
			}
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("AReq : 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("AReq : Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		// EMV Payment Token Source
		/*
		 * if (payTokenInd != null && payTokenInd) { if
		 * (StringUtils.isEmpty(payTokenSource)) {
		 * log.error("AReq : EMV Payment Token Source is missing"); throw new
		 * RequiredFieldMissing3DMsgException("payTokenInd"); }
		 * 
		 * if (payTokenSource.length() != 2) {
		 * log.error("AReq : Invalid length in EMV Payment Token Source"); throw
		 * new Invalid3DMsgException("payTokenInd"); } }
		 */

		// Instalment Payment Data (Required only if 3DS Requestor Authentication
		// Indicator = 03)
		if (threeDSRequestorAuthenticationInd != null
				&& threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_INSTALMENT)) {
			if (purchaseInstalData != null && StringUtils.isEmpty(purchaseInstalData)) {
				log.error("AReq : Instalment Payment Data is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseInstalData");
				return erro;
			}
		}

		// Recurring Expiry (Required if 3DS Requestor Authentication Indicator = 02 or
		// 03.)
		if (threeDSRequestorAuthenticationInd != null
				&& (threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_RECURRING)
						|| threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_INSTALMENT))) {

			if (StringUtils.isEmpty(recurringExpiry)) {
				log.error("AReq : Recurring Expiry is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "recurringExpiry");
				return erro;
			}

			if (!StringUtil.isDateOfFormat(recurringExpiry, "yyyyMMdd")) {
				log.error("AReq : Invalid Recurring Expiry  [" + recurringExpiry + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "recurringExpiry");
				return erro;
			}
		}

		// Recurring Frequency (Required if 3DS Requestor Authentication Indicator = 02
		// or 03.)
		if (threeDSRequestorAuthenticationInd != null
				&& (threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_RECURRING)
						|| threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_INSTALMENT))) {

			if (StringUtils.isEmpty(recurringFrequency)) {
				log.error("AReq : Recurring Frequency is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "recurringFrequency");
				return erro;
			}
		}

		// SDK APP ID
		if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE)) {
			if (StringUtils.isEmpty(sdkAppID)) {
				log.error("AReq : SDK APP ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkAppID");
				return erro;
			}
			if (sdkEphemPubKey == null) {
				log.error("AReq : SDK Ephem Public Key is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkAppID");
				return erro;
			}
			if (StringUtils.isEmpty(sdkMaxTimeout)) {
				log.error("AReq : SDK Max Timeout is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkAppID");
				return erro;
			}
			if (StringUtils.isEmpty(sdkReferenceNumber)) {
				log.error("AReq : SDK Reference Number is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkAppID");
				return erro;
			}
			if (StringUtils.isEmpty(sdkTransID)) {
				log.error("AReq : SDK transaction ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkAppID");
				return erro;
			}
		}

		if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_3RI)) { // merge to production -- start
			if (StringUtils.isEmpty(threeRIInd)) {
				log.error("AReq : ThreeRI indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeRIInd");
				return erro;
			} else {
				if (!ThreeD20Utils.isValidthreeRIInd(threeRIInd, messageVersion)) {
					log.error("AReq v2.2.0: Invalid threeRIInd [" + threeRIInd + "], messageVersion [" + messageVersion
							+ "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeRIInd");
					return erro;
				}
				// if (StringUtils.isEmpty(recurringFrequency)) {
				// log.error("AReq v2.2.0: Recurring Frequency is missing");
				// throw new RequiredFieldMissing3DMsgException("recurringFrequency");
				// }
			}
		} // merge to production -- end

		// Whitelist Status
		/*
		 * if (whiteListStatus != null) { if (StringUtils.isEmpty(whiteListStatus)) {
		 * log.error("AReq : White list status is missing"); throw new
		 * RequiredFieldMissing3DMsgException("whiteListStatusSource"); }
		 * 
		 * if (whiteListStatus.length() != 1) {
		 * log.error("AReq : Invalid length in Whitelist Status"); throw new
		 * Invalid3DMsgException("whiteListStatus"); }
		 * 
		 * if (!ThreeD20Utils.isValidWhiteListStatus(whiteListStatus, messageVersion)) {
		 * log.error("AReq : Invalid Whitelist Status [" + whiteListStatus +
		 * "], messageVersion [" + messageVersion + "]."); throw new
		 * Invalid3DMsgException("whiteListStatus"); } }
		 * 
		 * // Whitelist Status Sources if (whiteListStatusSource != null) { if
		 * (whiteListStatusSource.length() != 2) {
		 * log.error("AReq : Invalid length in Whitelist Status Sources"); throw
		 * new Invalid3DMsgException("whiteListStatusSource"); }
		 * 
		 * if (!ThreeD20Utils.isValidWhiteListStatusSource(whiteListStatusSource,
		 * messageVersion)) { log.error("AReq : Invalid Whitelist Status Source ["
		 * + whiteListStatusSource + "], messageVersion [" + messageVersion + "].");
		 * throw new Invalid3DMsgException("whiteListStatusSource"); } }
		 */
		// 3DS Requestor Authentication Information
		if (threeDSRequestorAuthenticationInfo != null) {

			// 3DS Requestor Authentication Data
			String threeDSReqAuthData = threeDSRequestorAuthenticationInfo.getThreeDSReqAuthData();
			if (StringUtils.isEmpty(threeDSReqAuthData)) {
				log.error(
						"AReq : 3DS Requestor Authentication Data (In 3DS Requestor Authentication Information) is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSReqAuthData");
				return erro;
			}

			if (threeDSReqAuthData.length() <= 0 || threeDSReqAuthData.length() > 2048) {
				log.error(
						"AReq : Invalid length in 3DS Requestor Authentication Data (In 3DS Requestor Authentication Information)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSReqAuthData");
				return erro;
			}

			// 3DS Requestor Authentication Method
			String threeDSReqAuthMethod = threeDSRequestorAuthenticationInfo.getThreeDSReqAuthMethod();
			if (StringUtils.isEmpty(threeDSReqAuthMethod)) {
				log.error(
						"AReq : 3DS Requestor Authentication Method (In 3DS Requestor Authentication Information) is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSReqAuthMethod");
				return erro;
			}

			if (threeDSReqAuthMethod.length() != 2) {
				log.error(
						"AReq : Invalid length in 3DS Requestor Authentication Method (In 3DS Requestor Authentication Information)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSReqAuthMethod");
				return erro;
			}

			if (!ThreeD20Utils.isValid3DSRequestorAuthenticationMethod(threeDSReqAuthMethod, messageVersion)) {
				log.error(
						"AReq : Invalid 3DS Requestor Authentication Method (In 3DS Requestor Authentication Information) ["
								+ threeDSReqAuthMethod + "], messageVersion [" + messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSReqAuthMethod");
				return erro;
			}

			// 3DS Requestor Authentication Timestamp
			String threeDSReqAuthTimestamp = threeDSRequestorAuthenticationInfo.getThreeDSReqAuthTimestamp();
			if (StringUtils.isEmpty(threeDSReqAuthTimestamp)) {
				log.error(
						"AReq : 3DS Requestor Authentication Timestamp (In 3DS Requestor Authentication Information) is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSReqAuthTimestamp");
				return erro;
			}

			if (threeDSReqAuthTimestamp.length() != 12) {
				log.error(
						"AReq : Invalid length in 3DS Requestor Authentication Timestamp (In 3DS Requestor Authentication Information)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSReqAuthTimestamp");
				return erro;
			}

			if (!StringUtil.isDateOfFormat(threeDSReqAuthTimestamp, "yyyyMMddHHmm")) {
				log.error(
						"AReq : Invalid 3DS Requestor Authentication Timestamp (In 3DS Requestor Authentication Information) ["
								+ threeDSReqAuthTimestamp + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSReqAuthTimestamp");
				return erro;
			}
		}

		return null;

	}

}
