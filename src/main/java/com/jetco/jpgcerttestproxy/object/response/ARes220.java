package com.jetco.jpgcerttestproxy.object.response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.object.request.AReq;
import com.jetco.jpgcerttestproxy.object.request.AReq220;
import com.jetco.jpgcerttestproxy.threeDSenum.CardType;
import com.jetco.jpgcerttestproxy.threeDSenum.DeviceChannel;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

public class ARes220 extends ARes {

	static final Logger log = LoggerFactory.getLogger(ARes220.class);

	private String acsDecConInd;
	private String whiteListStatus;
	private String whiteListStatusSource;
	
	@Override
	public PArs mapPArs(String deviceChannel) {
		
		PArs pArs = super.mapPArs(deviceChannel);
		pArs.setAcsDecConInd(acsDecConInd);
		pArs.setWhiteListStatus(whiteListStatus);
		pArs.setWhiteListStatusSource(whiteListStatusSource);

		return pArs;
	}


	public Map<String, String> validateMsg(AReq aReq) {

		log.info("In ARes220.validateMsg!!");
		
		AReq220 aReq220 = (AReq220) aReq;

		Map<String, String> erro = new HashMap<String, String>();

		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("ARes v2.2.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion220(messageVersion)) {
			log.error("ARes v2.2.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("ARes v2.2.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_ARES)) {
			log.error("ARes v2.2.0: Incorrect Message Type for ARes Object");
			//erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");   //merge to production -- start
			erro.put(ErrorResponse.ErrorCode101.getErrorCode(), "messageType");     //merge to production -- end
			return erro;
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("ARes v2.2.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("ARes v2.2.0: Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		//if (aReq != null) {
		    if (!threeDSServerTransID.equals(aReq.getThreeDSServerTransID())) {      // merge to production -- flow 301 --- should equal to areq.getThreeDSServerTransID
		    	log.error("aRes.threeDSServerTransID is not consistent with aReq.threeDSServerTransID");
				erro.put(ErrorResponse.ErrorCode301.getErrorCode(), "threeDSServerTransID");
				return erro;
	 		}																		 // merge to production -- flow 301 --- should equal to areq.getThreeDSServerTransID
			if (aReq220.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE)
					&& StringUtils.isEmpty(sdkTransID)) {
				log.error("ARes v2.2.0: SDK transaction ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkTransID");
				return erro;
			}
			//merge to production -- start
			if ( aReq220.getSdkTransID()  != null) {
				if (!sdkTransID.equals( aReq220.getSdkTransID())) { 
					log.error("aRes.sdkTransID is not consistent with aReq.sdkTransID");
					erro.put(ErrorResponse.ErrorCode301.getErrorCode(), "sdkTransID");
					return erro;
				} 
			}
			//merge to production -- end
		//}

		// ACS Reference Number
		if (StringUtils.isEmpty(acsReferenceNumber)) {
			log.error("ARes v2.2.0: ACS Reference Number is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsReferenceNumber");
			return erro;
		}

		if (acsReferenceNumber.length() > 32) {
			log.error("ARes v2.2.0: Invalid length in ACS Reference Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsReferenceNumber");
			return erro;
		}

		// ACS Transaction ID
		if (StringUtils.isEmpty(acsTransID)) {
			log.error("ARes v2.2.0: ACS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(acsTransID)) {
			log.error("ARes v2.2.0:  Invalid ACS Transaction ID [" + acsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsTransID");
			return erro;
		}

		// DS Reference Number
		if (StringUtils.isEmpty(dsReferenceNumber)) {
			log.error("ARes v2.2.0: DS Reference Number is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsReferenceNumber");
			return erro;
		}

		if (dsReferenceNumber.length() > 32) {
			log.error("ARes v2.2.0: Invalid length in DS Reference Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsReferenceNumber");
			return erro;
		}

		// DS Transaction ID
		if (StringUtils.isEmpty(dsTransID)) {
			log.error("ARes v2.2.0: DS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(dsTransID)) {
			log.error("ARes v2.2.0:  Invalid DS Transaction ID [" + dsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsTransID");
			return erro;
		}

		// Transaction Status
		if (StringUtils.isEmpty(transStatus)) {
			log.error("ARes v2.2.0: Transaction Status is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatus");
			return erro;
		}

		if (!ThreeD20Utils.isValidTransStatus(transStatus, messageType, ThreeD20Utils.THREED_MSG_VERSION_220)) {
			log.error("ARes v2.2.0: Invalid Transaction Status [" + transStatus + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatus");
			return erro;
		}

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_INFORMATION)) {
			if (!StringUtils.isEmpty(aReq220.getThreeDSRequestorChallengeInd())) {
				if (!(aReq220.getThreeDSRequestorChallengeInd().equals("05")
						|| aReq220.getThreeDSRequestorChallengeInd().equals("06")
						|| aReq220.getThreeDSRequestorChallengeInd().equals("07"))) {
					log.error("ARes v2.2.0: Invalid Transaction Status [" + transStatus + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatus");
					return erro;
				}
			}

			if (aReq220.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_3RI)) {
				log.error("ARes v2.2.0: Invalid Transaction Status [" + transStatus + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatus");
				return erro;
			}
		}

		if (acsSignedContent == null || StringUtils.isEmpty(String.valueOf(acsSignedContent))) { // merge to production
																									// -- start
			if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)
					&& aReq220.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE)) {
				log.error("ARes v2.1.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsSignedContent");
				return erro;
			}

		} else {
			log.info("acsSignedContent 2.2 length =" + String.valueOf(acsSignedContent).length());
			if (!StringUtil.isValidJWS(String.valueOf(acsSignedContent))) {
				log.error("ARes v2.2.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsSignedContent");
				return erro;
			} else {
				if (String.valueOf(acsSignedContent).length() > 3441
						&& String.valueOf(acsSignedContent).length() != 5642) {
					log.error("ARes v2.2.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsSignedContent");
					return erro;
				}
				if (String.valueOf(acsSignedContent).contains("+") || String.valueOf(acsSignedContent).contains("/")) {
					log.error("ARes v2.2.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsSignedContent");
					return erro;
				}
			}
		} // merge to production -- end

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)) {
			//if (aReq != null) {
				if (!StringUtils.isEmpty(aReq220.getThreeDSRequestorChallengeInd())
						&& aReq220.getThreeDSRequestorChallengeInd().equals("06")) {
					log.error("ARes v2.2.0: Invalid Transaction Status [" + transStatus + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatus");
					return erro;
				}

				if (aReq220.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE) && acsSignedContent == null) {
					log.error("ARes v2.2.0: Invalid ACS Signed Content");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsSignedContent");
					return erro;
				}

				if (aReq220.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE) && acsSignedContent != null
						&& ((String) acsSignedContent).trim().isEmpty()) {
					log.error("ARes v2.2.0: Invalid ACS Signed Content");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsSignedContent");
					return erro;
				}

				if (aReq220.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE) && acsRenderingType == null) {
					log.error("ARes v2.2.0: Invalid ACS Rendering Type");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsRenderingType");
					return erro;
				}

				if (aReq220.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE) && acsURL == null) {
					log.error("ARes v2.2.0: Invalid ACS URL");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsURL");
					return erro;
				}
			//}
		}

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_DECOUPLED_CHALLENGE)) {
			// ACS Challenge Mandated Indicator
			if (StringUtils.isEmpty(acsChallengeMandated)) {
				log.error("ARes v2.2.0: ACS Challenge Mandated Indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsChallengeMandated");
				return erro;
			}

			//if (aReq != null) {
				// Authentication Type
				if (aReq220.getMessageCategory()  != null
						&& aReq220.getMessageCategory().equals(ThreeD20Utils.MESSAGE_CATEGORY_PA)
						&& StringUtils.isEmpty(authenticationType)) {
					log.error("ARes v2.2.0: Authentication Type is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "authenticationType");
					return erro;
				}
			//}

			if (StringUtils.isEmpty(authenticationType)) {
				log.error("ARes v2.2.0: Invalid Authentication Type");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "authenticationType");
				return erro;
			} else {
				if (authenticationType.length() != 2) {
					log.error("ARes v2.2.0: Invalid length in Authentication Type");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
					return erro;
				}

				if (!StringUtils.isNumeric(authenticationType)) {
					log.error("ARes v2.2.0: Invalid Authentication Type [" + authenticationType + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
					return erro;
				}

				if (!ThreeD20Utils.isValidAuthType(authenticationType)) {
					log.error("ARes v2.2.0: Invalid Authentication Type [" + authenticationType + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
					return erro;
				}
			}
		}

		// ACS Challenge Mandated Indicator
		if (acsChallengeMandated != null) {
			if (!StringUtils.isEmpty(acsChallengeMandated) && !acsChallengeMandated.equals(ThreeD20Utils.YES_IND)
					&& !acsChallengeMandated.equals(ThreeD20Utils.NO_IND)) {
				log.error("ARes v2.2.0: Invalid  ACS Challenge Mandated Indicator [" + acsChallengeMandated + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsChallengeMandated");
				return erro;
			}
			if (acsChallengeMandated.trim().equals("")) {
				log.error("ARes v2.1.0: ACS Challenge Mandated Indicator is empty");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsChallengeMandated");
				return erro;
			}
		}

		/*
		 * if (!transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE) && --merge to
		 * production --- start
		 * !transStatus.equals(ThreeD20Utils.TRANS_STATUS_DECOUPLED_CHALLENGE)) { if
		 * (acsChallengeMandated != null) { log.
		 * error("ARes v2.2.0: ACS Challenge Mandated Indicator shall not be presented if transaction status is not C or D ["
		 * + acsChallengeMandated + "]"); throw new
		 * Invalid3DMsgException("acsChallengeMandated"); } }
		 */

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)) {
			if (StringUtils.isEmpty(acsChallengeMandated)) {
				log.error(
						"ARes v2.2.0: ACS Challenge Mandated Indicator shall be presented if transaction status is C");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsChallengeMandated");
				return erro;
			}
		} // --merge to production ---- end

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_SUCCESS)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_ATTEMPT)) {

			// Authentication Value
			if (StringUtils.isEmpty(authenticationValue)) {
				log.error("ARes v2.2.0: Authentication Value	is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "authenticationValue");
				return erro;
			}

			if (authenticationValue.length() > 28) {
				log.error("ARes v2.2.0: Invalid length in Authentication Value");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationValue");
				return erro;
			}
		}

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_DENIED)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_NOT_PERFORM)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_REJECT)) {

			// Transaction Status Reason
			if (StringUtils.isEmpty(transStatusReason)) {
				log.error("ARes v2.2.0: Transaction Status Reason is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (transStatusReason.length() != 2) {
				log.error("ARes v2.2.0: Invalid length in Transaction Status Reason");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (!StringUtils.isNumeric(transStatusReason)) {
				log.error("ARes v2.2.0: Invalid Transaction Status Reason [" + transStatusReason + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (!ThreeD20Utils.isValidTransStatusReason(transStatusReason, messageVersion)) {
				log.error("ARes v2.2.0: Invalid Transaction Status Reason [" + transStatusReason + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}
		}

		// ACS URL
		if (acsURL != null) {
			if (acsURL.length() == 0) { // merge to production - start
				log.error("ARes v2.1.0: empty ACS URL");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsURL");
				return erro;
			}
			// if (acsURL.length() <= 0 || acsURL.length() > 2048) {
			if (acsURL.length() > 2048) { // merge to production - end
				log.error("ARes v2.2.0: Invalid length in ACS URL");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsURL");
				return erro;
			}

			if (!StringUtil.isValidHTTPURL(acsURL)) {
				log.error("ARes v2.2.0: Invalid ACS URL [" + acsURL + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsURL");
				return erro;
			}
		}

		// ACS Operator ID (Optional)
		if (acsOperatorID != null) {
			if (acsOperatorID.length() <= 0 || acsOperatorID.length() > 32) {
				log.error("ARes v2.2.0: Invalid length in ACS Operator ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsOperatorID");
				return erro;
			}
		}

		// Electronic Commerce Indicator (ECI)
		if (eci != null) {
			if (eci.length() != 2) {
				log.error("ARes v2.2.0: Invalid length in Electronic Commerce Indicator (ECI)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}

			if (!StringUtils.isNumeric(eci)) {
				log.error("ARes v2.2.0: Electronic Commerce Indicator (ECI) [" + eci + "] is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}

			if (!ThreeD20Utils.isValidEci(CardType.TYPE_VISA.getValue(), eci)) {
				log.error("ARes v2.2.0: Invalid Electronic Commerce Indicator (ECI) [" + eci + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}
		}

		// Cardholder Information Text
		if (acsDecConInd != null && acsDecConInd.equals(ThreeD20Utils.YES_IND)) {
			// if (aReq.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_3RI) &&
			// StringUtils.isEmpty(cardholderInfo)) {
			if (StringUtils.isEmpty(cardholderInfo)) {
				log.error(
						"ARes v2.2.0: Cardholder Information Text is missing when ACS Decoupled Confirmation Indicator = Y");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "cardholderInfo");
				return erro;
			}
		}
		if (cardholderInfo != null) {
			if (cardholderInfo.length() <= 0 || cardholderInfo.length() > 128) {
				log.error("ARes v2.2.0: Invalid length in Cardholder Information Text");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "cardholderInfo");
				return erro;
			}
		}

		// Whitelist Status
		if (whiteListStatus != null) {
			if (StringUtils.isEmpty(whiteListStatus)) { // merge to production -- start
				log.error("ARes v2.2.0: White list status is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "whiteListStatus");
				return erro;
			}

			if (StringUtils.isEmpty(whiteListStatusSource)) {
				log.error("ARes v2.2.0: whiteListStatusSource is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "whiteListStatus");
				return erro;
			} // merge to production -- end

			if (whiteListStatus.length() != 1) {
				log.error("ARes v2.2.0: Invalid length in Whitelist Status");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatus(whiteListStatus, messageVersion)) {
				log.error("ARes v2.2.0: Invalid Whitelist Status [" + whiteListStatus + "], messageVersion ["
						+ messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}
		}

		// Whitelist Status Sources
		if (whiteListStatusSource != null) {
			if (whiteListStatusSource.length() != 2) {
				log.error("ARes v2.2.0: Invalid length in Whitelist Status Sources");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatusSource(whiteListStatusSource, messageVersion)) {
				log.error("ARes v2.2.0: Invalid Whitelist Status Source [" + whiteListStatusSource
						+ "], messageVersion [" + messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}
		}

		// ACS Rendering Type
		if (acsRenderingType != null) {
			// ACS Interface
			String acsInterface = acsRenderingType.getAcsInterface();
			if (StringUtils.isEmpty(acsInterface)) {
				log.error("ARes v2.2.0: ACS Interface in ACS Rendering Type is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsRenderingType.acsInterface");
				return erro;
			}

			if (acsInterface.length() != 2) {
				log.error("ARes v2.2.0: Invalid length in ACS Interface in ACS Rendering Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsRenderingType.acsInterface");
				return erro;
			}

			if (!acsInterface.equals(ThreeD20Utils.ACS_INTERFACE_NATIVE_UI)
					&& !acsInterface.equals(ThreeD20Utils.ACS_INTERFACE_HTML_UI)) {
				log.error("ARes v2.2.0:  Invalid ACS Interface in ACS Rendering Type [" + acsInterface + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsRenderingType.acsInterface");
				return erro;
			}

			// ACS UI Template
			String acsUiTemplate = acsRenderingType.getAcsUiTemplate();
			if (StringUtils.isEmpty(acsUiTemplate)) {
				log.error("ARes v2.2.0: ACS UI Template in ACS Rendering Type is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsRenderingType.acsUiTemplate");
				return erro;
			}

			if (acsUiTemplate.length() != 2) {
				log.error("ARes v2.2.0: Invalid length in ACS UI Template in ACS Rendering Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsRenderingType.acsUiTemplate");
				return erro;
			}

			if (!acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_TEXT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_SINGLE_SELECT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_MULTI_SELECT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_OOB)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_HTML_OTHER)) {
				log.error("ARes v2.2.0:  Invalid ACS UI Template in ACS Rendering Type [" + acsUiTemplate + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsRenderingType.acsUiTemplate");
				return erro;
			}
		}

		// ACS Decoupled Confirmation Indicator
		if (acsDecConInd != null) {
			if (acsDecConInd.length() != 1) {
				log.error("ARes v2.2.0: Invalid length in ACS Decoupled Confirmation Indicator");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsDecConInd");
				return erro;
			}

			if (!acsDecConInd.equals(ThreeD20Utils.YES_IND) && !acsDecConInd.equals(ThreeD20Utils.NO_IND)) {
				log.error("ARes v2.2.0: Invalid ACS Decoupled Confirmation Indicator [" + acsDecConInd + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsDecConInd");
				return erro;
			}
			//merge to production -- start 
			if (transStatus.equals("D") && acsDecConInd.equals("N")) {
				log.error("ARes v2.2.0: Invalid ACS Decoupled Confirmation Indicator [" + acsDecConInd + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsDecConInd");
				return erro;
			}
							
			if (aReq220.getThreeDSRequestorDecReqInd().equals("N") && acsDecConInd.equals("Y")) {
				log.error("ARes v2.2.0: Invalid ACS Decoupled Confirmation Indicator [" + acsDecConInd + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsDecConInd");
				return erro;
			}
			//merge to production -- end
		}

		// Message extension
		if (messageExtension != null) {
			if (messageExtension.length > 10) {
				log.error("ARes v2.2.0: Invalid array size in Message Extension");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
				return erro;
			}
			for (int i = 0; i < messageExtension.length; i++) {
				if (messageExtension[i].getName().length() > 64) {
					log.error("ARes v2.2.0: Invalid length on name in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension.name");
					return erro;
				}
				if (messageExtension[i].getId().length() > 64) {
					log.error("ARes v2.2.0: Invalid length on id in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension.id");
					return erro;					
				}
				if (messageExtension[i].getCriticalityIndicator() != null
						&& messageExtension[i].getCriticalityIndicator() == true) {
					log.error("ARes v2.2.0: Unrecognized message extension");					
					erro.put(ErrorResponse.ErrorCode202.getErrorCode(), "messageExtension.criticalityIndicator");
					return erro;
				}
				ObjectMapper m = new ObjectMapper();
				Map<String, Object> dataMapObject = m.convertValue(messageExtension[i].getData(), Map.class);
				int count = 0;
				for (Map.Entry<String, Object> entry : dataMapObject.entrySet()) {
					count += ((String) entry.getValue()).length();
				}
				if (count > 8059) {
					log.error("ARes v2.2.0: Invalid length on id in Message Extension");					
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension.data");
					return erro;
				}
			}
		}
		return null;
	}

	public String getAcsDecConInd() {
		return acsDecConInd;
	}

	public void setAcsDecConInd(String acsDecConInd) {
		this.acsDecConInd = acsDecConInd;
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

	@Override
	public String toString() {
		return "ARes220 [acsDecConInd=" + acsDecConInd + ", whiteListStatus=" + whiteListStatus
				+ ", whiteListStatusSource=" + whiteListStatusSource + ", threeDSServerTransID=" + threeDSServerTransID
				+ ", acsChallengeMandated=" + acsChallengeMandated + ", acsOperatorID=" + acsOperatorID
				+ ", acsReferenceNumber=" + acsReferenceNumber + ", acsRenderingType=" + acsRenderingType
				+ ", acsSignedContent=" + acsSignedContent + ", acsTransID=" + acsTransID + ", acsURL=" + acsURL
				+ ", authenticationType=" + authenticationType + ", authenticationValue=" + authenticationValue
				+ ", broadInfo=" + broadInfo + ", cardholderInfo=" + cardholderInfo + ", dsReferenceNumber="
				+ dsReferenceNumber + ", dsTransID=" + dsTransID + ", eci=" + eci + ", messageExtension="
				+ Arrays.toString(messageExtension) + ", messageType=" + messageType + ", messageVersion="
				+ messageVersion + ", sdkTransID=" + sdkTransID + ", transStatus=" + transStatus
				+ ", transStatusReason=" + transStatusReason + "]";
	}

}
