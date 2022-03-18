package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.util.FormCheckUtils;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AReq220 extends AReq{

	private String payTokenSource;
	private String threeDSRequestorDecMaxTime;
	private String threeDSRequestorDecReqInd;
	private String whiteListStatus;
	private String whiteListStatusSource;
	
	static final Logger log = LoggerFactory.getLogger(AReq210.class);
	
	@Override
	public AReq mapPArq(PArq pArq, String testingEnv, String _threeDSServerRefNumber, String visaThreeDSServerRefNumber) {		
		AReq220 aReq220 = new AReq220();
		aReq220 = (AReq220) super.mapPArq(pArq, testingEnv, _threeDSServerRefNumber, visaThreeDSServerRefNumber);
		aReq220.setPayTokenSource(pArq.getPayTokenSource());
		aReq220.setThreeDSRequestorDecMaxTime(pArq.getThreeDSRequestorDecMaxTime());
		aReq220.setWhiteListStatus(pArq.getWhiteListStatus());
		aReq220.setWhiteListStatusSource(pArq.getWhiteListStatusSource());
		aReq220.setThreeDSRequestorDecReqInd(pArq.getThreeDSRequestorDecReqInd());
		return aReq220;		
	}

	public Map<String, String> validateMsg() {

		Map<String, String> erro = new HashMap<String, String>();

		if (StringUtils.isEmpty(messageVersion)) {
			log.error("AReq v2.2.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion220(messageVersion)) {
			log.error("AReq v2.2.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("AReq v2.2.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_AREQ)) {
			log.error("AReq v2.2.0: Incorrect Message Type for AReq Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");
			return erro;
		}

		// Device Channel
		if (StringUtils.isEmpty(deviceChannel)) {
			log.error("AReq v2.2.0: Device Channel is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "deviceChannel");
			return erro;
		}

		if (deviceChannel.length() != 2) {
			log.error("AReq v2.2.0: Invalid length in Device Channel ");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "deviceChannel");
			return erro;
		}

		if (!StringUtils.isNumeric(deviceChannel)) {
			log.error("AReq v2.2.0: Device Channel is not numeric");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "deviceChannel");
			return erro;
		}

		// 3DS Method Completion Indicator
		if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
			if (StringUtils.isEmpty(threeDSCompInd)) {
				log.error("AReq v2.2.0: 3DS Method Completion Indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSCompInd");
				return erro;
			}

			if (threeDSCompInd.length() != 1) {
				log.error("AReq v2.2.0: Invalid length in 3DS Method Completion Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSCompInd");
				return erro;
			}

			if (!StringUtils.isAlpha(threeDSCompInd)) {
				log.error("AReq v2.2.0: 3DS Method Completion Indicator is not an alphabet character");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSCompInd");
				return erro;
			}

			// Browser Accept Headers
			if (StringUtils.isEmpty(browserAcceptHeader)) {
				log.error("AReq v2.2.0: Browser Accept Headers is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserAcceptHeader");
				return erro;
			}

			if (browserAcceptHeader.length() > 2048) {
				log.error("AReq v2.2.0: Invalid length in Browser Accept Headers");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserAcceptHeader");
				return erro;
			}

			// Browser IP Address
//				if (StringUtils.isEmpty(browserIP)) {
//				   log.error("AReq v2.2.0: Browser IP Address is missing");
//				   throw new RequiredFieldMissing3DMsgException("browserIP");
//				}	

			if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
				if (!StringUtils.isEmpty(browserIP)) {
					if (browserIP.length() > 45) {
						log.error("AReq v2.2.0: Invalid length in Browser IP Address");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserIP");
						return erro;
					}

					if (!FormCheckUtils.isValidIPv4AndIPv6Address(browserIP)) {
						log.error("AReq v2.2.0: Invalid Browser IP Address [" + browserIP + "]");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserIP");
						return erro;
					}
				}
			}

			if (browserJavascriptEnabled) {
				if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
					if (browserJavaEnabled == null) {
						log.error("AReq v2.2.0: Browser Java Enabeld is missing");
						erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserJavaEnabled");
						return erro;
					}

					if (StringUtils.isEmpty(browserColorDepth)) {
						log.error("AReq v2.2.0: Browser Screen Color Depth is missing");
						erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserColorDepth");
						return erro;
					}

					if (StringUtils.isEmpty(browserScreenHeight)) {
						log.error("AReq v2.2.0: Browser Screen Height is missing");
						erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserScreenHeight");
						return erro;
					}

					if (StringUtils.isEmpty(browserScreenWidth)) {
						log.error("AReq v2.2.0: Browser Screen Width is missing");
						erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserScreenWidth");
						return erro;
					}

					if (StringUtils.isEmpty(browserTZ)) {
						log.error("AReq v2.2.0: Browser Time Zone is missing");
						erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserTZ");
						return erro;
					}
				}
			}

			if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE)) {
				// Browser Screen Color Depth
				if (!StringUtils.isEmpty(browserColorDepth)) {
					if (StringUtils.isEmpty(browserTZ) && browserColorDepth.length() > 2) {
						log.error("AReq v2.2.0: Invalid length in Browser Screen Color Depth");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserTZ");
						return erro;
					}

					if (!StringUtils.isNumeric(browserColorDepth)) {
						log.error("AReq v2.2.0: Browser Screen Color Depth is not numeric");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserColorDepth");
						return erro;
					}
				}

				// Browser Screen Height
				if (!StringUtils.isEmpty(browserScreenHeight)) {
					if (browserScreenHeight.length() > 6) {
						log.error("AReq v2.2.0: Invalid length in Browser Screen Height");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenHeight");
						return erro;
					}

					if (!StringUtils.isNumeric(browserScreenHeight)) {
						log.error("AReq v2.2.0: Browser Screen Height is not numeric");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenHeight");
						return erro;
					}
				}

				// Browser Screen Width
				if (!StringUtils.isEmpty(browserScreenWidth)) {
					if (browserScreenWidth.length() > 6) {
						log.error("AReq v2.2.0: Invalid length in Browser Screen Width");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenWidth");
						return erro;
					}

					if (!StringUtils.isNumeric(browserScreenWidth)) {
						log.error("AReq v2.2.0: Browser Screen Width is not numeric");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserScreenWidth");
						return erro;
					}
				}

				// Browser Time Zone
				if (!StringUtils.isEmpty(browserTZ) && browserTZ.length() > 5) {
					log.error("AReq v2.2.0: Invalid length in Browser Time Zone");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserTZ");
					return erro;
				}

				// Browser User-Agent
				if (StringUtils.isEmpty(browserUserAgent)) {
					log.error("AReq v2.2.0: Browser User-Agent is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserUserAgent");
					return erro;
				}

				if (browserUserAgent.length() > 2048) {
					log.error("AReq v2.2.0: Invalid length in Browser User-Agent");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserUserAgent");
					return erro;
				}

				// Browser Language
				if (StringUtils.isEmpty(browserLanguage)) {
					log.error("AReq v2.2.0: Browser Language is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "browserLanguage");
					return erro;
				}

				if (browserLanguage.length() > 8) {
					log.error("AReq v2.2.0: Invalid length in Browser Language");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "browserLanguage");
					return erro;
				}
			}
		}

		// Message Category
		if (StringUtils.isEmpty(messageCategory)) {
			log.error("AReq v2.2.0: Message Category is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageCategory");
			return erro;
		}

		if (messageCategory.length() != 2) {
			log.error("AReq v2.2.0: Invalid length in Message Category");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		if (!StringUtils.isNumeric(messageCategory)) {
			log.error("AReq v2.2.0: Message Category is not numeric");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageCategory(messageCategory)) {
			log.error("AReq v2.1.0: Invalid Message Category [" + messageCategory + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		// Transaction Type being authenticated
		if (messageCategory.equals(ThreeD20Utils.MESSAGE_CATEGORY_PA)) {
			if (StringUtils.isEmpty(transType)) {
				log.error("AReq v2.2.0: Transaction Type being authenticated is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transType");
				return erro;
			}

			if (transType.length() != 2) {
				log.error("AReq v2.2.0: Invalid length in Transaction Type being authenticated");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transType");
				return erro;
			}

			if (!StringUtils.isNumeric(transType)) {
				log.error("AReq v2.2.0: Transaction Type being authenticated is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transType");
				return erro;
			}
		}

		// 3DS Server URL (ACS Return URL)
		if (StringUtils.isEmpty(threeDSServerURL)) {
			log.error("AReq v2.2.0: 3DS Server URL (ACS Return URL) is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerURL");
			return erro;
		}

		if (!StringUtil.isValidHTTPURL(threeDSServerURL)) {
			log.error("AReq v2.2.0: Invalid 3DS Server URL (ACS Return URL) [" + threeDSServerURL + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerURL");
			return erro;
		}

		// 3DS Server Reference Number
		if (StringUtils.isEmpty(threeDSServerRefNumber)) {
			log.error("AReq v2.2.0: 3DS Server Reference Number is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerRefNumber");
			return erro;
		}

		if (threeDSServerRefNumber.length() > 32) {
			log.error("AReq v2.2.0: Invalid length in 3DS Server Reference Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerRefNumber");
			return erro;
		}

		// 3DS Requestor Authentication Indicator
		if (threeDSRequestorAuthenticationInd != null) {
			if (StringUtils.isEmpty(threeDSRequestorAuthenticationInd)) {
				log.error("AReq v2.2.0: 3DS Requestor Authentication Indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorAuthenticationInd");
				return erro;
			}

			if (threeDSRequestorAuthenticationInd.length() != 2) {
				log.error("AReq v2.2.0: Invalid length in 3DS Requestor Authentication Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorAuthenticationInd");
				return erro;
			}

			if (!StringUtils.isNumeric(threeDSRequestorAuthenticationInd)) {
				log.error("AReq v2.2.0: 3DS Requestor Authentication Indicator is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorAuthenticationInd");
				return erro;
			}
		}

		// 3DS Requestor Challenge Indicator (Optional field)
		if (!StringUtils.isEmpty(threeDSRequestorChallengeInd)) {
			if (threeDSRequestorChallengeInd.length() != 2) {
				log.error("AReq v2.2.0: Invalid length in 3DS Requestor Challenge Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorChallengeInd");
				return erro;
			}

			if (!StringUtils.isNumeric(threeDSRequestorChallengeInd)) {
				log.error("AReq v2.2.0: 3DS Requestor Challenge Indicator is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorChallengeInd");
				return erro;
			}
		}

		// 3DS Server Operator ID (Optional field)
		if (!StringUtils.isEmpty(threeDSServerOperatorID)) {
			if (threeDSServerOperatorID.length() > 32) {
				log.error("AReq v2.2.0: Invalid length in 3DS Server Operator ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerOperatorID");
				return erro;
			}
		}

		// 3DS Requestor ID (Assigned by DS)
		if (StringUtils.isEmpty(threeDSRequestorID)) {
			log.error("AReq v2.2.0: 3DS Requestor ID (Assigned by DS) is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorID");
			return erro;
		}

		if (threeDSRequestorID.length() > 35) {
			log.error("AReq v2.2.0: Invalid length in 3DS Requestor ID (Assigned by DS)");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorID");
			return erro;
		}

		// Acquirer Bin
		if (acquirerBIN != null) {
			if (StringUtils.isEmpty(acquirerBIN)) {
				log.error("AReq v2.2.0: Acquirer Bin is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acquirerBIN");
				return erro;
			}

			if (acquirerBIN.length() > 11) {
				log.error("AReq v2.2.0: Invalid length in Acquirer Bin");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acquirerBIN");
				return erro;
			}
		}

		// Acquirer Merchant ID
		if (acquirerMerchantID != null) {
			if (StringUtils.isEmpty(acquirerMerchantID)) {
				log.error("AReq v2.2.0: Acquirer Merchant ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acquirerMerchantID");
				return erro;
			}

			if (acquirerMerchantID.length() > 35) {
				log.error("AReq v2.2.0: Invalid length in Acquirer Merchant ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acquirerMerchantID");
				return erro;
			}
		}

		// 3DS Requestor Name (Assigned by DS)
		if (StringUtils.isEmpty(threeDSRequestorName)) {
			log.error("AReq v2.2.0: 3DS Requestor Name (Assigned by DS) is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorName");
			return erro;
		}

		if (threeDSRequestorName.length() > 40) {
			log.error("AReq v2.2.0: Invalid length in 3DS Requestor Name (Assigned by DS)");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorName");
			return erro;
		}

		// 3DS Requestor URL
		if (StringUtils.isEmpty(threeDSRequestorURL)) {
			log.error("AReq v2.2.0: 3DS Requestor URL is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorURL");
			return erro;
		}

		if (threeDSRequestorURL.length() > 2048) {
			log.error("AReq v2.2.0: Invalid length in 3DS Requestor URL");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorURL");
			return erro;
		}

		if (!StringUtil.isValidHTTPURL(threeDSRequestorURL)) {
			log.error("AReq v2.2.0: Invalid 3DS Requestor URL [" + threeDSRequestorURL + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSRequestorURL");
			return erro;
		}

		// Merchant Category Code
		if (mcc != null) {
			if (StringUtils.isEmpty(mcc)) {
				log.error("AReq v2.2.0: Merchant Category Code is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "mcc");
				return erro;
			}

			if (mcc.length() != 4) {
				log.error("AReq v2.2.0: Invalid length in Merchant Category Code");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "mcc");
				return erro;
			}
		}

		// Merchant Country Code
		if (merchantCountryCode != null) {
			if (StringUtils.isEmpty(merchantCountryCode)) {
				log.error("AReq v2.2.0: Merchant Country Code is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "merchantCountryCode");
				return erro;
			}

			if (merchantCountryCode.length() != 3) {
				log.error("AReq v2.2.0: Invalid length in Merchant Country Code");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "merchantCountryCode");
				return erro;
			}

			if (!StringUtils.isNumeric(merchantCountryCode)) {
				log.error("AReq v2.2.0: Merchant Country Code is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "merchantCountryCode");
				return erro;
			}
		}

		// Merchant Name
		if (merchantName != null) {
			if (StringUtils.isEmpty(merchantName)) {
				log.error("AReq v2.2.0: Merchant Name is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "merchantName");
				return erro;
			}

			if (merchantName.length() > 40) {
				log.error("AReq v2.2.0: Invalid length in Merchant Name");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "merchantName");
				return erro;
			}
		}

		// Cardholder Account Number
		if (StringUtils.isEmpty(acctNumber)) {
			log.error("AReq v2.2.0: Cardholder Account Number is missing");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acctNumber");
			return erro;
		}

		if (acctNumber.length() < 13 || acctNumber.length() > 19) {
			log.error("AReq v2.2.0: Invalid length in Cardholder Account Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acctNumber");
			return erro;
		}

		if (!StringUtils.isNumeric(acctNumber)) {
			log.error("AReq v2.2.0: Cardholder Account Number is not numeric");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acctNumber");
			return erro;
		}

		// Card/Token Expiry Date (not required on UL?)
//	        if (StringUtils.isEmpty(cardExpiryDate)) {
//	  		   log.error("AReq v2.2.0: Card/Token Expiry Date is missing");
//	  		   throw new RequiredFieldMissing3DMsgException("cardExpiryDate");
//	  		}	
//	  		
//	  		if (cardExpiryDate.length() != 4) {
//	  		   log.error("AReq v2.2.0: Invalid length in Card/Token Expiry Date");
//	  		   throw new Invalid3DMsgException("cardExpiryDate");
//	  		}
//	 	 		
//	  		if (!StringUtils.isDateOfFormat(cardExpiryDate, "yyMM")) {
//	   		   log.error("AReq v2.2.0: Invalid Card/Token Expiry Date");
//	  		   throw new Invalid3DMsgException("cardExpiryDate");
//	  		}

		// Purchase Amount
		if (purchaseAmount != null) {
			if (StringUtils.isEmpty(purchaseAmount)) {
				log.error("AReq v2.2.0: Purchase Amount is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseAmount");
				return erro;
			}

			if (purchaseAmount.length() > 48) {
				log.error("AReq v2.2.0: Invalid length in Purchase Amount");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseAmount");
				return erro;
			}

			if (!StringUtils.isNumeric(purchaseAmount)) {
				log.error("AReq v2.2.0: Purchase Amount is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseAmount");
				return erro;
			}
		}

		// Purchase Currency
		// CreditDOHandler doHandler = new CreditDOHandler();
		int expectedCurrencyExponent = -1;

		if (purchaseCurrency != null) {
			if (StringUtils.isEmpty(purchaseCurrency)) {
				log.error("AReq v2.2.0: Purchase Currency is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseCurrency");
				return erro;
			}

			if (purchaseCurrency.length() != 3) {
				log.error("AReq v2.2.0: Invalid length in Purchase Currency");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseCurrency");
				return erro;
			}

			if (!StringUtils.isNumeric(purchaseCurrency)) {
				log.error("AReq v2.2.0: Purchase Currency is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseCurrency");
				return erro;
			}

//		    	try { // check it during transaction?? if check it here we cannot pass the case
//		    		expectedCurrencyExponent = doHandler.checkCurrencyCode(Integer.parseInt(purchaseCurrency));
//		    	} catch (Exception e) {
//		    		log.error("AReq v2.2.0: Invalid Purchase Currency. " + e);
//		 			throw new Invalid3DMsgException("purchaseCurrency");
//				}
		}

		// Purchase Currency Exponent
		if (purchaseExponent != null) {
			if (StringUtils.isEmpty(purchaseExponent)) {
				log.error("AReq v2.2.0: Purchase Currency Exponent is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseExponent");
				return erro;
			}

			if (purchaseExponent.length() != 1) {
				log.error("AReq v2.2.0: Invalid length in Purchase CurrencyExponent");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseExponent");
				return erro;
			}

			if (!StringUtils.isNumeric(purchaseExponent)) {
				log.error("AReq v2.2.0: Purchase Currency Exponent is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseExponent");
				return erro;
			}

			if (expectedCurrencyExponent != -1 && Integer.parseInt(purchaseExponent) != expectedCurrencyExponent) {
				log.error("AReq v2.2.0: Invalid Purchase Currency Exponent [" + purchaseExponent + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "purchaseExponent");
				return erro;
			}
		}

		// Purchase Date & Time
		if (purchaseDate != null) {
			if (StringUtils.isEmpty(purchaseDate)) {
				log.error("AReq v2.2.0: Purchase Date & Time is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseDate");
				return erro;
			}

			if (purchaseDate.length() != 14) {
				log.error("AReq v2.2.0: Invalid length in Purchase Date & Time");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseDate");
				return erro;
			}

			if (!StringUtil.isDateOfFormat(purchaseDate, "yyyyMMddHHmmss")) {
				log.error("AReq v2.2.0: Invalid Purchase Date & Time [" + purchaseDate + "]");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "purchaseDate");
				return erro;
			}
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("AReq v2.2.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("AReq v2.2.0: Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		// EMV Payment Token Source
		/*
		 * if (payTokenInd != null && payTokenInd) { if
		 * (StringUtils.isEmpty(payTokenSource)) {
		 * log.error("AReq v2.2.0: EMV Payment Token Source is missing"); throw new
		 * RequiredFieldMissing3DMsgException("payTokenSource"); }
		 * 
		 * if (payTokenSource.length() != 2) {
		 * log.error("AReq v2.2.0: Invalid length in EMV Payment Token Source"); throw
		 * new Invalid3DMsgException("payTokenSource"); } }
		 */

		// Instalment Payment Data (Required only if 3DS Requestor Authentication
		// Indicator = 03)
		if (threeDSRequestorAuthenticationInd != null
				&& threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_INSTALMENT)) {
			if (purchaseInstalData != null && StringUtils.isEmpty(purchaseInstalData)) {
				log.error("AReq v2.2.0: Instalment Payment Data is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorAuthenticationInd");
				return erro;
			}
//  		} else { // do not need this field if it is not an instalment payment
//  			setPurchaseInstalData(null);
		}

		// Recurring Expiry (Required if 3DS Requestor Authentication Indicator = 02 or
		// 03.)
		if (threeDSRequestorAuthenticationInd != null
				&& (threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_RECURRING)
						|| threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_INSTALMENT))) {

			if (StringUtils.isEmpty(recurringExpiry)) {
				log.error("AReq v2.2.0: Recurring Expiry is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "recurringExpiry");
				return erro;
			}

			if (!StringUtil.isDateOfFormat(recurringExpiry, "yyyyMMdd")) {
				log.error("AReq v2.2.0: Invalid Recurring Expiry  [" + recurringExpiry + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "recurringExpiry");
				return erro;
			}
		}

		if (threeRIInd != null && (threeRIInd.equals(ThreeD20Utils.AUTHENTICATION_IND_RECURRING) // merge to production
																									// -- start
		)) {

			if (StringUtils.isEmpty(recurringFrequency)) {
				log.error("AReq v2.2.0: Recurring Frequency is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "recurringFrequency");
				return erro;
			}

			if (StringUtils.isEmpty(recurringExpiry)) {
				log.error("AReq v2.2.0: Recurring Expiry is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "recurringExpiry");
				return erro;
			}

			if (!StringUtil.isDateOfFormat(recurringExpiry, "yyyyMMdd")) {
				log.error("AReq v2.2.0: Invalid Recurring Expiry  [" + recurringExpiry + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "recurringExpiry");
				return erro;
			}
		}

		// merge to production -- end

		// Recurring Frequency (Required if 3DS Requestor Authentication Indicator = 02
		// or 03.)
		if (threeDSRequestorAuthenticationInd != null
				&& (threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_RECURRING)
						|| threeDSRequestorAuthenticationInd.equals(ThreeD20Utils.AUTHENTICATION_IND_INSTALMENT))) {

			if (StringUtils.isEmpty(recurringFrequency)) {
				log.error("AReq v2.2.0: Recurring Frequency is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "recurringFrequency");
				return erro;
			}
		}

		// SDK APP ID
		if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE)) {
			if (StringUtils.isEmpty(sdkAppID)) {
				log.error("AReq v2.2.0: SDK APP ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "recurringFrequency");
				return erro;
			}
			if (sdkEphemPubKey == null) {
				log.error("AReq v2.2.0: SDK Ephem Public Key is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkEphemPubKey");
				return erro;
			}
			if (StringUtils.isEmpty(sdkMaxTimeout)) {
				log.error("AReq v2.2.0: SDK Max Timeout is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkMaxTimeout");
				return erro;
			}
			if (StringUtils.isEmpty(sdkReferenceNumber)) {
				log.error("AReq v2.2.0: SDK Reference Number is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkReferenceNumber");
				return erro;
			}
			if (StringUtils.isEmpty(sdkTransID)) {
				log.error("AReq v2.2.0: SDK transaction ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkTransID");
				return erro;
			}
		}

		log.error("deviceChannel =" + deviceChannel + " threeRIInd =" + threeRIInd);

		if (deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_3RI)) { // merge to production -- start
			if (StringUtils.isEmpty(threeRIInd)) {
				log.error("AReq v2.1.0: ThreeRI indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeRIInd");
				return erro;
			} else {
				if (!ThreeD20Utils.isValidthreeRIInd(threeRIInd, messageVersion)) {
					log.error("AReq v2.2.0: Invalid threeRIInd [" + threeRIInd + "], messageVersion [" + messageVersion
							+ "].");
					return erro;
				}
				// if (threeRIInd.equals(ThreeD20Utils.AUTHENTICATION_IND_RECURRING) ||
				// threeRIInd.equals(ThreeD20Utils.AUTHENTICATION_IND_INSTALMENT)){
				// log.error("AReq v2.2.0: Recurring Frequency is missing");
				// throw new RequiredFieldMissing3DMsgException("recurringFrequency");
				// }
			}
		} // merge to production -- end

		// Whitelist Status
		if (whiteListStatus != null) {
			if (StringUtils.isEmpty(whiteListStatus)) {
				log.error("AReq v2.2.0: White list status is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "whiteListStatusSource");
				return erro;
			}

			if (whiteListStatus.length() != 1) {
				log.error("AReq v2.2.0: Invalid length in Whitelist Status");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatus(whiteListStatus, messageVersion)) {
				log.error("AReq v2.2.0: Invalid Whitelist Status [" + whiteListStatus + "], messageVersion ["
						+ messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}
		}

		// Whitelist Status Sources
		if (whiteListStatusSource != null) {
			if (whiteListStatusSource.length() != 2) {
				log.error("AReq v2.2.0: Invalid length in Whitelist Status Sources");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatusSource(whiteListStatusSource, messageVersion)) {
				log.error("AReq v2.2.0: Invalid Whitelist Status Source [" + whiteListStatusSource
						+ "], messageVersion [" + messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}
		}

		// 3DS Requestor Authentication Information
		if (threeDSRequestorAuthenticationInfo != null) {

			// 3DS Requestor Authentication Data
			String threeDSReqAuthData = threeDSRequestorAuthenticationInfo.getThreeDSReqAuthData();
			if (StringUtils.isEmpty(threeDSReqAuthData)) {
				log.error(
						"AReq v2.2.0: 3DS Requestor Authentication Data (In 3DS Requestor Authentication Information) is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthData");
				return erro;
			}

			if (threeDSReqAuthData.length() <= 0 || threeDSReqAuthData.length() > 2048) {
				log.error(
						"AReq v2.2.0: Invalid length in 3DS Requestor Authentication Data (In 3DS Requestor Authentication Information)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthData");
				return erro;
			}

			// 3DS Requestor Authentication Method
			String threeDSReqAuthMethod = threeDSRequestorAuthenticationInfo.getThreeDSReqAuthMethod();
			if (StringUtils.isEmpty(threeDSReqAuthMethod)) {
				log.error(
						"AReq v2.2.0: 3DS Requestor Authentication Method (In 3DS Requestor Authentication Information) is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthMethod");
				return erro;
			}

			if (threeDSReqAuthMethod.length() != 2) {
				log.error(
						"AReq v2.2.0: Invalid length in 3DS Requestor Authentication Method (In 3DS Requestor Authentication Information)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthMethod");
				return erro;
			}

			if (!ThreeD20Utils.isValid3DSRequestorAuthenticationMethod(threeDSReqAuthMethod, messageVersion)) {
				log.error(
						"AReq v2.2.0: Invalid 3DS Requestor Authentication Method (In 3DS Requestor Authentication Information) ["
								+ threeDSReqAuthMethod + "], messageVersion [" + messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthMethod");
				return erro;
			}

			// 3DS Requestor Authentication Timestamp
			String threeDSReqAuthTimestamp = threeDSRequestorAuthenticationInfo.getThreeDSReqAuthTimestamp();
			if (StringUtils.isEmpty(threeDSReqAuthTimestamp)) {
				log.error(
						"AReq v2.2.0: 3DS Requestor Authentication Timestamp (In 3DS Requestor Authentication Information) is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthTimestamp");
				return erro;
			}

			if (threeDSReqAuthTimestamp.length() != 12) {
				log.error(
						"AReq v2.2.0: Invalid length in 3DS Requestor Authentication Timestamp (In 3DS Requestor Authentication Information)");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthTimestamp");
				return erro;
			}

			if (!StringUtil.isDateOfFormat(threeDSReqAuthTimestamp, "yyyyMMddHHmm")) {
				log.error(
						"AReq v2.2.0: Invalid 3DS Requestor Authentication Timestamp (In 3DS Requestor Authentication Information) ["
								+ threeDSReqAuthTimestamp + "]");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(),
						"threeDSRequestorAuthenticationInfo.threeDSReqAuthTimestamp");
				return erro;
			}

		}

		// 3DS requestor decoupled max time
		if (threeDSRequestorDecReqInd != null && threeDSRequestorDecReqInd.equals("Y")) {
			if (StringUtils.isEmpty(threeDSRequestorDecMaxTime)) {
				log.error("AReq v2.2.0: 3DS Requestor Decoupled Maximum time is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSRequestorDecMaxTime");
				return erro;
			}
		}
		
		return null;
	}

	public String getPayTokenSource() {
		return payTokenSource;
	}

	public void setPayTokenSource(String payTokenSource) {
		this.payTokenSource = payTokenSource;
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

	

}