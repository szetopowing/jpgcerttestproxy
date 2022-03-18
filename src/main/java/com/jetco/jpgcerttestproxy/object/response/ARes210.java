package com.jetco.jpgcerttestproxy.object.response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.object.request.AReq;
import com.jetco.jpgcerttestproxy.object.request.AReq210;
import com.jetco.jpgcerttestproxy.object.request.AReq220;
import com.jetco.jpgcerttestproxy.threeDSenum.CardType;
import com.jetco.jpgcerttestproxy.threeDSenum.DeviceChannel;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ARes210 extends ARes {

	static final Logger log = LoggerFactory.getLogger(ARes210.class);
	
	@Override
	public PArs mapPArs(String deviceChannel) {
						
		PArs pArs = super.mapPArs(deviceChannel);
		return pArs;
	}

	@Override
	public Map<String, String> validateMsg(AReq aReq) {
		
		log.info("In ARes210.validateMsg!!");

		AReq210 aReq210 = (AReq210) aReq;
		Map<String, String> erro = new HashMap<String, String>();

		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("ARes v2.1.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion210(messageVersion)) {
			log.error("ARes v2.1.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("ARes v2.1.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_ARES)) {
			log.error("ARes v2.1.0: Incorrect Message Type for ARes Object");
			erro.put(ErrorResponse.ErrorCode101.getErrorCode(), "messageType");
			return erro;
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("ARes v2.1.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("ARes v2.1.0: Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		//if (aReq != null) {
			if (!threeDSServerTransID.equals(aReq210.getThreeDSServerTransID())) { 
				log.error("aRes.threeDSServerTransID is not consistent with aReq.threeDSServerTransID");
				erro.put(ErrorResponse.ErrorCode301.getErrorCode(), "threeDSServerTransID");
				return erro;
			}
			
			if (aReq210.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE)
					&& StringUtils.isEmpty(sdkTransID)) {
				log.error("ARes v2.1.0: SDK transaction ID is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkTransID");
				return erro;
			}

			//merge to production -- start
			if ( aReq210.getSdkTransID()  != null) {
				if (!sdkTransID.equals( aReq210.getSdkTransID())) { 
					log.error("aRes.sdkTransID is not consistent with aReq.sdkTransID");
					erro.put(ErrorResponse.ErrorCode301.getErrorCode(), "sdkTransID");
					return erro;
				} 
			}
			//merge to production -- end
			
			//merge to production -- start
			//if (aReq_deviceChannel.equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE)
			//		&& StringUtils.isEmpty(sdkTransID)) {
			//	log.error("ARes v2.1.0: SDK transaction ID is missing");
			//	erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "sdkTransID");
			//	return erro;
			//}
			//merge to production -- start
		//}

		// ACS Reference Number
		if (StringUtils.isEmpty(acsReferenceNumber)) {
			log.error("ARes v2.1.0: ACS Reference Number is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsReferenceNumber");
			return erro;
		}

		if (acsReferenceNumber.length() > 32) {
			log.error("ARes v2.1.0: Invalid length in ACS Reference Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsReferenceNumber");
			return erro;
		}

		// ACS Transaction ID
		if (StringUtils.isEmpty(acsTransID)) {
			log.error("ARes v2.1.0: ACS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(acsTransID)) {
			log.error("ARes v2.1.0:  Invalid ACS Transaction ID [" + acsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsTransID");
			return erro;
		}

		// DS Reference Number
		if (StringUtils.isEmpty(dsReferenceNumber)) {
			log.error("ARes v2.1.0: DS Reference Number is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsReferenceNumber");
			return erro;
		}

		if (dsReferenceNumber.length() > 32) {
			log.error("ARes v2.1.0: Invalid length in DS Reference Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsReferenceNumber");
			return erro;
		}

		// DS Transaction ID
		if (StringUtils.isEmpty(dsTransID)) {
			log.error("ARes v2.1.0: DS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(dsTransID)) {
			log.error("ARes v2.1.0:  Invalid DS Transaction ID [" + dsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsTransID");
			return erro;
		}

		// Transaction Status
		if (StringUtils.isEmpty(transStatus)) {
			log.error("ARes v2.1.0: Transaction Status is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatus");
			return erro;
		}

		if (!ThreeD20Utils.isValidTransStatus(transStatus, messageType, ThreeD20Utils.THREED_MSG_VERSION_210)) {
			log.error("ARes v2.1.0: Invalid Transaction Status [" + transStatus + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatus");
			return erro;
		}

		if (acsSignedContent == null || StringUtils.isEmpty(String.valueOf(acsSignedContent))) { // merge to production
																									// -- start
			if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)
					&&  aReq210.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE)) {
				log.error("ARes v2.1.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsSignedContent");
				return erro;
			}

		} else {
			log.info("acsSignedContent 2.1 length =" + String.valueOf(acsSignedContent).length());
			if (!StringUtil.isValidJWS(String.valueOf(acsSignedContent))) {
				log.error("ARes v2.1.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsSignedContent");
				return erro;
			} else {
				if (String.valueOf(acsSignedContent).length() > 3441
						&& String.valueOf(acsSignedContent).length() != 5642) {
					log.error("ARes v2.1.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsSignedContent");
					return erro;
				}
				if (String.valueOf(acsSignedContent).contains("+") || String.valueOf(acsSignedContent).contains("/")) {
					log.error("ARes v2.1.0: Invalid acsSignedContent [" + String.valueOf(acsSignedContent) + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsSignedContent");
					return erro;
				}
			}
		} // merge to production -- end

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)) {
			if (aReq210.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE) && acsSignedContent == null) {
				log.error("ARes v2.1.0: Invalid ACS Signed Content");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsSignedContent");
				return erro;
			}

			if (aReq210.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE) && acsSignedContent != null
					&& ((String) acsSignedContent).trim().isEmpty()) {
				log.error("ARes v2.1.0: Invalid ACS Signed Content");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsSignedContent");
				return erro;
			}

			if (aReq210.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_APP_BASE) && acsRenderingType == null) {
				log.error("ARes v2.1.0: Invalid ACS Rendering Type");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsRenderingType");
				return erro;
			}

			if (aReq210.getDeviceChannel().equals(ThreeD20Utils.DEVICE_CHANNEL_BROWSER_BASE) && acsURL == null) {
				log.error("ARes v2.1.0: Invalid ACS URL");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsURL");
				return erro;
			}
		}

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_DECOUPLED_CHALLENGE)) {
			// ACS Challenge Mandated Indicator
			if (StringUtils.isEmpty(acsChallengeMandated)) {
				log.error("ARes v2.1.0: ACS Challenge Mandated Indicator is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsChallengeMandated");
				return erro;
			}

			//if (aReq != null) {
				// Authentication Type
				if (aReq210.getMessageCategory() != null
						&& aReq210.getMessageCategory().equals(ThreeD20Utils.MESSAGE_CATEGORY_PA)
						&& StringUtils.isEmpty(authenticationType)) {
					log.error("ARes v2.1.0: Authentication Type is missing");
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
					log.error("ARes v2.1.0: Invalid length in Authentication Type");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
					return erro;
				}

				if (!StringUtils.isNumeric(authenticationType)) {
					log.error("ARes v2.1.0: Invalid Authentication Type [" + authenticationType + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
					return erro;
				}

				if (!ThreeD20Utils.isValidAuthType(authenticationType)) {
					log.error("ARes v2.1.0: Invalid Authentication Type [" + authenticationType + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
					return erro;
				}
			}
		}

		// ACS Challenge Mandated Indicator
		if (acsChallengeMandated != null) {
			if (!StringUtils.isEmpty(acsChallengeMandated) && !acsChallengeMandated.equals(ThreeD20Utils.YES_IND)
					&& !acsChallengeMandated.equals(ThreeD20Utils.NO_IND)) {
				log.error("ARes v2.1.0: Invalid  ACS Challenge Mandated Indicator [" + acsChallengeMandated + "]");
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
		 * if (!transStatus.equals(ThreeD20Utils.TRANS_STATUS_CHALLENGE)) { --merge to
		 * production -- start if (acsChallengeMandated != null) { log.
		 * error("ARes v2.1.0: ACS Challenge Mandated Indicator shall not be presented if transaction status is not C ["
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
		} // ----merge to production -- end

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_SUCCESS)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_ATTEMPT)) {

			// Authentication Value
			if (StringUtils.isEmpty(authenticationValue)) {
				log.error("ARes v2.1.0: Authentication Value	is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "authenticationValue");
				return erro;
			}

			if (authenticationValue.length() > 28) {
				log.error("ARes v2.1.0: Invalid length in Authentication Value");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationValue");
				return erro;
			}
		}

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_DENIED)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_NOT_PERFORM)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_REJECT)) {

			// Transaction Status Reason
			if (StringUtils.isEmpty(transStatusReason)) {
				log.error("ARes v2.1.0: Transaction Status Reason is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (transStatusReason.length() != 2) {
				log.error("ARes v2.1.0: Invalid length in Transaction Status Reason");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (!StringUtils.isNumeric(transStatusReason)) {
				log.error("ARes v2.1.0: Invalid Transaction Status Reason [" + transStatusReason + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (!ThreeD20Utils.isValidTransStatusReason(transStatusReason, messageVersion)) {
				log.error("ARes v2.1.0: Invalid Transaction Status Reason [" + transStatusReason + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}
		}

		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_INFORMATION)) {
			if (!StringUtils.isEmpty(aReq210.getThreeDSRequestorChallengeInd())) {
				if (!(aReq210.getThreeDSRequestorChallengeInd().equals("05")
						|| aReq210.getThreeDSRequestorChallengeInd().equals("06")
						|| aReq210.getThreeDSRequestorChallengeInd().equals("07"))) {
					log.error("ARes v2.1.0: Invalid Transaction Status [" + transStatus + "]");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
					return erro;
				}
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
				log.error("ARes v2.1.0: Invalid length in ACS URL");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsURL");
				return erro;
			}

			if (!StringUtil.isValidHTTPURL(acsURL)) {
				log.error("ARes v2.1.0: Invalid ACS URL [" + acsURL + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsURL");
				return erro;
			}
		}

		// ACS Operator ID (Optional)
		if (acsOperatorID != null) {
			if (acsOperatorID.length() <= 0 || acsOperatorID.length() > 32) {
				log.error("ARes v2.1.0: Invalid length in ACS Operator ID");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsOperatorID");
				return erro;
			}
		}

		// Electronic Commerce Indicator (ECI)
		if (eci != null) {
			if (eci.length() != 2) {
				log.error("ARes v2.1.0: Invalid length in Electronic Commerce Indicator (ECI)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}

			if (!StringUtils.isNumeric(eci)) {
				log.error("ARes v2.1.0: Electronic Commerce Indicator (ECI) [" + eci + "] is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}

			if (!ThreeD20Utils.isValidEci(CardType.TYPE_VISA.getValue(), eci)) {
				log.error("ARes v2.1.0: Invalid Electronic Commerce Indicator (ECI) [" + eci + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}
		}

		// Cardholder Information Text
		if (cardholderInfo != null) {
			if (cardholderInfo.length() <= 0 || cardholderInfo.length() > 128) {
				log.error("ARes v2.1.0: Invalid length in Cardholder Information Text");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "cardholderInfo");
				return erro;
			}
		}

		// Whitelist Status
		/*
		 * if (whiteListStatus != null) {
		 * 
		 * if (StringUtils.isEmpty(whiteListStatus)) { //merge to production -- start
		 * log.error("ARes v2.2.0: White list status is missing");
		 * erro.put(ErrorResponse.ErrorCode201.getErrorCode(),
		 * ErrorResponse.ErrorCode201.getErrorDesc()); }
		 * 
		 * 
		 * if (StringUtils.isEmpty(whiteListStatusSource)) {
		 * log.error("ARes v2.2.0: White list status is missing");
		 * erro.put(ErrorResponse.ErrorCode201.getErrorCode(),
		 * ErrorResponse.ErrorCode201.getErrorDesc()); } //merge to production -- end
		 * 
		 * if (whiteListStatus.length() != 1) {
		 * log.error("ARes v2.1.0: Invalid length in Whitelist Status");
		 * erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
		 * ErrorResponse.ErrorCode203.getErrorDesc()); }
		 * 
		 * if (!ThreeD20Utils.isValidWhiteListStatus(whiteListStatus, messageVersion)) {
		 * log.error("ARes v2.1.0: Invalid Whitelist Status [" + whiteListStatus +
		 * "], messageVersion [" + messageVersion + "].");
		 * erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
		 * ErrorResponse.ErrorCode203.getErrorDesc()); } }
		 */

		// Whitelist Status Sources
		/*
		 * if (whiteListStatusSource != null) { if (whiteListStatusSource.length() != 2)
		 * { log.error("ARes v2.1.0: Invalid length in Whitelist Status Sources");
		 * erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
		 * ErrorResponse.ErrorCode203.getErrorDesc()); }
		 * 
		 * if (!ThreeD20Utils.isValidWhiteListStatusSource(whiteListStatusSource,
		 * messageVersion)) { log.error("ARes v2.1.0: Invalid Whitelist Status Source ["
		 * + whiteListStatusSource + "], messageVersion [" + messageVersion + "].");
		 * erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
		 * ErrorResponse.ErrorCode203.getErrorDesc()); } }
		 */

		// ACS Rendering Type
		if (acsRenderingType != null) {
			// ACS Interface
			String acsInterface = acsRenderingType.getAcsInterface();
			if (StringUtils.isEmpty(acsInterface)) {
				log.error("ARes v2.1.0: ACS Interface in ACS Rendering Type is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsInterface");
				return erro;
			}

			if (acsInterface.length() != 2) {
				log.error("ARes v2.1.0: Invalid length in ACS Interface in ACS Rendering Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsInterface");
				return erro;
			}

			if (!acsInterface.equals(ThreeD20Utils.ACS_INTERFACE_NATIVE_UI)
					&& !acsInterface.equals(ThreeD20Utils.ACS_INTERFACE_HTML_UI)) {
				log.error("ARes v2.1.0:  Invalid ACS Interface in ACS Rendering Type [" + acsInterface + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsInterface");
				return erro;
			}

			// ACS UI Template
			String acsUiTemplate = acsRenderingType.getAcsUiTemplate();
			if (StringUtils.isEmpty(acsUiTemplate)) {
				log.error("ARes v2.1.0: ACS UI Template in ACS Rendering Type is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsUiTemplate");
				return erro;
			}

			if (acsUiTemplate.length() != 2) {
				log.error("ARes v2.1.0: Invalid length in ACS UI Template in ACS Rendering Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsUiTemplate");
				return erro;
			}

			if (!acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_TEXT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_SINGLE_SELECT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_MULTI_SELECT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_OOB)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_HTML_OTHER)) {
				log.error("ARes v2.1.0:  Invalid ACS UI Template in ACS Rendering Type [" + acsUiTemplate + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsUiTemplate");
				return erro;
			}
		}

		// ACS Decoupled Confirmation Indicator
		/*
		 * if (acsDecConInd != null) { if (acsDecConInd.length() != 1) { log.
		 * error("ARes v2.1.0: Invalid length in ACS Decoupled Confirmation Indicator");
		 * erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
		 * ErrorResponse.ErrorCode203.getErrorDesc()); }
		 * 
		 * if (!acsDecConInd.equals(ThreeD20Utils.YES_IND) &&
		 * !acsDecConInd.equals(ThreeD20Utils.NO_IND)) {
		 * log.error("ARes v2.1.0: Invalid ACS Decoupled Confirmation Indicator [" +
		 * acsDecConInd + "]."); erro.put(ErrorResponse.ErrorCode203.getErrorCode(),
		 * ErrorResponse.ErrorCode203.getErrorDesc()); } }
		 */

		// Message extension
		if (messageExtension != null) {
			if (messageExtension.length > 10) {
				log.error("ARes v2.2.0: Invalid array size in Message Extension");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
				return erro;
			}
			for (int i = 0; i < messageExtension.length; i++) {
				if (messageExtension[i].getName().length() > 64) {
					log.error("ARes v2.1.0: Invalid length on name in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
					return erro;
				}
				if (messageExtension[i].getId().length() > 64) {
					log.error("ARes v2.1.0: Invalid length on id in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
					return erro;
				}
				if (messageExtension[i].getCriticalityIndicator() != null
						&& messageExtension[i].getCriticalityIndicator() == true) {
					log.error("ARes v2.1.0: Unrecognized message extension");
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
					log.error("ARes v2.1.0: Invalid length on id in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
					return erro;
				}
			}
		}
		return null;

	}

	@Override
	public String toString() {
		return "ARes210 [threeDSServerTransID=" + threeDSServerTransID + ", acsChallengeMandated="
				+ acsChallengeMandated + ", acsOperatorID=" + acsOperatorID + ", acsReferenceNumber="
				+ acsReferenceNumber + ", acsRenderingType=" + acsRenderingType + ", acsSignedContent="
				+ acsSignedContent + ", acsTransID=" + acsTransID + ", acsURL=" + acsURL + ", authenticationType="
				+ authenticationType + ", authenticationValue=" + authenticationValue + ", broadInfo=" + broadInfo
				+ ", cardholderInfo=" + cardholderInfo + ", dsReferenceNumber=" + dsReferenceNumber + ", dsTransID="
				+ dsTransID + ", eci=" + eci + ", messageExtension=" + Arrays.toString(messageExtension)
				+ ", messageType=" + messageType + ", messageVersion=" + messageVersion + ", sdkTransID=" + sdkTransID
				+ ", transStatus=" + transStatus + ", transStatusReason=" + transStatusReason + "]";
	}
	
	

}
