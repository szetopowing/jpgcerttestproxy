package com.jetco.jpgcerttestproxy.object.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.object.response.ARes;
import com.jetco.jpgcerttestproxy.threeDSenum.CardType;
import com.jetco.jpgcerttestproxy.threeDSenum.DeviceChannel;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RReq210 extends RReq{

	static final Logger log = LoggerFactory.getLogger(RReq210.class);

	public Map<String, String> validateMsg(PArq pArq, ARes aRes) {
		
		log.info("In rReq210.validateMsg!!");

		Map<String, String> erro = new HashMap<String, String>();

		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("RReq v2.1.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion210(messageVersion)) {
			log.error("RReq v2.1.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("RReq v2.1.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_RREQ)) {
			log.error("RReq v2.1.0: Incorrect Message Type for RReq Object");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageType");
			return erro;
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("RReq v2.1.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("RReq v2.1.0: Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		// ACS Transaction ID
		if (StringUtils.isEmpty(acsTransID)) {
			log.error("RReq v2.1.0: ACS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(acsTransID)) {
			log.error("RReq v2.1.0:  Invalid ACS Transaction ID [" + acsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsTransID");
			return erro;
		}

		// ACS Rendering Type
		if (acsRenderingType != null) {
			// ACS Interface
			String acsInterface = acsRenderingType.getAcsInterface();
			if (StringUtils.isEmpty(acsInterface)) {
				log.error("RReq v2.1.0: ACS Interface in ACS Rendering Type is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsInterface");
				return erro;
			}

			if (acsInterface.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in ACS Interface in ACS Rendering Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsInterface");
				return erro;
			}

			if (!acsInterface.equals(ThreeD20Utils.ACS_INTERFACE_NATIVE_UI)
					&& !acsInterface.equals(ThreeD20Utils.ACS_INTERFACE_HTML_UI)) {
				log.error("RReq v2.1.0:  Invalid ACS Interface in ACS Rendering Type [" + acsInterface + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsInterface");
				return erro;
			}

			// ACS UI Template
			String acsUiTemplate = acsRenderingType.getAcsUiTemplate();
			if (StringUtils.isEmpty(acsUiTemplate)) {
				log.error("RReq v2.1.0: ACS UI Template in ACS Rendering Type is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsUiTemplate");
				return erro;
			}

			if (acsUiTemplate.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in ACS UI Template in ACS Rendering Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsUiTemplate");
				return erro;
			}

			if (!acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_TEXT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_SINGLE_SELECT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_MULTI_SELECT)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_OOB)
					&& !acsUiTemplate.equals(ThreeD20Utils.ACS_UI_TEMPLATE_HTML_OTHER)) {
				log.error("RReq v2.1.0:  Invalid ACS UI Template in ACS Rendering Type [" + acsUiTemplate + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsUiTemplate");
				return erro;
			}
		} else {
			/*
			 * VISA don't need PArq
			 * No need to merge to production
			 * if(pArq != null) {}
			 * 
			 */
			if(pArq != null) {
				if (pArq.getDeviceChannel().equals(DeviceChannel.APP.getValue())) {
					log.error("RReq v2.1.0: ACS Interface in ACS Rendering Type is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsRenderingType.acsInterface");
					return erro;
				}
			}
		}

		// Transaction Status
		if (StringUtils.isEmpty(transStatus)) {
			log.error("RReq v2.1.0: Transaction Status is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatus");
			return erro;
		}

		if (!ThreeD20Utils.isValidTransStatus(transStatus, messageType)) {
			log.error("RReq v2.1.0: Invalid Transaction Status [" + transStatus + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatus");
			return erro;
		}

		// Authentication Type (Only for transStatus = Y/N)
		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_SUCCESS)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_DENIED)) {

			if (StringUtils.isEmpty(authenticationType)) {
				log.error("RReq v2.1.0: Authentication Type is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatus");
				return erro;
			}
		}

		// Authentication Type
		if (authenticationType != null) {
			if (authenticationType.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in Authentication Type");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
				return erro;
			}

			if (!StringUtils.isNumeric(authenticationType)) {
				log.error("RReq v2.1.0: Invalid Authentication Type [" + authenticationType + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
				return erro;
			}

			if (!ThreeD20Utils.isValidAuthType(authenticationType)) {
				log.error("RReq v2.1.0: Invalid Authentication Type [" + authenticationType + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationType");
				return erro;
			}
		}

		// Transaction Status Reason
		if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_DENIED)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_NOT_PERFORM)
				|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_REJECT)) {

			// Transaction Status Reason
			if (StringUtils.isEmpty(transStatusReason)) {
				log.error("RReq v2.1.0: Transaction Status Reason is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (transStatusReason.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in Transaction Status Reason");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (!StringUtils.isNumeric(transStatusReason)) {
				log.error("RReq v2.1.0: Invalid Transaction Status Reason [" + transStatusReason + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}

			if (!ThreeD20Utils.isValidTransStatusReason(transStatusReason, messageVersion)) {
				log.error("RReq v2.1.0: Invalid Transaction Status Reason [" + transStatusReason + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "transStatusReason");
				return erro;
			}
		}

		// Message Category
		if (StringUtils.isEmpty(messageCategory)) {
			log.error("RReq v2.1.0: Message Category is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageCategory");
			return erro;
		}

		if (messageCategory.length() != 2) {
			log.error("RReq v2.1.0: Invalid length in Message Category");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		if (!StringUtils.isNumeric(messageCategory)) {
			log.error("RReq v2.1.0: Message Category is not numeric");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageCategory(messageCategory)) {
			log.error("RReq v2.1.0: Invalid Message Category [" + messageCategory + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageCategory");
			return erro;
		}

		// Interaction Counter
		if (!StringUtils.isEmpty(interactionCounter)) {
			if (interactionCounter.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in Interaction Counter");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "interactionCounter");
				return erro;
			}

			if (!StringUtils.isNumeric(interactionCounter)) {
				log.error("RReq v2.1.0: Interaction Counter is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "interactionCounter");
				return erro;
			}
		} else {
			log.error("RReq v2.1.0: Interaction Counter is not numeric");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "interactionCounter");
			return erro;
		}

		// Electronic Commerce Indicator (ECI)
		if (eci != null) {
			if (eci.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in Electronic Commerce Indicator (ECI)");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}

			if (!StringUtils.isNumeric(eci)) {
				log.error("RReq v2.1.0: Electronic Commerce Indicator (ECI) [" + eci + "] is not numeric");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}

			if (!ThreeD20Utils.isValidEci(CardType.TYPE_VISA.getValue(), eci)) {
				log.error("RReq v2.1.0: Invalid Electronic Commerce Indicator (ECI) [" + eci + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "eci");
				return erro;
			}
		}

		// DS Transaction ID
		if (StringUtils.isEmpty(dsTransID)) {
			log.error("RReq v2.1.0: DS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(dsTransID)) {
			log.error("RReq v2.1.0:  Invalid DS Transaction ID [" + dsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsTransID");
			return erro;
		}

		// Challenge Cancel
		if (!StringUtils.isEmpty(challengeCancel)) {
			if (challengeCancel.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in Challenge Cancel");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeCancel");
				return erro;
			}

			if (!StringUtils.isNumeric(challengeCancel)) {
				log.error("RReq v2.1.0:  Invalid Challenge Cancel [" + dsTransID + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeCancel");
				return erro;
			}

			if (!ThreeD20Utils.isValidChallengeCancelationInd(challengeCancel, messageVersion)) {
				log.error("RReq v2.1.0:  Invalid Challenge Cancel [" + challengeCancel + "]. MessageVersion ["
						+ messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeCancel");
				return erro;
			}
		}

		// TransStatusReason == 14 => challengeCancel Shoud be = 04 / 05
		if (!StringUtils.isEmpty(transStatusReason) && transStatusReason.equals(ThreeD20Utils.TRANS_STATUS_REASON_14)) {

			if (StringUtils.isEmpty(challengeCancel)) {
				log.error("RReq v2.1.0: Challenge Cancel is missing");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "challengeCancel");
				return erro;

			} else if (!challengeCancel.equals(ThreeD20Utils.CHALLENGE_CANCELATION_IND_ACS_OTHER_TIMEOUT)
					&& !challengeCancel.equals(ThreeD20Utils.CHALLENGE_CANCELATION_IND_ACS_FIRST_CREQ_TIMEOUT)) {
				log.error("RReq v2.1.0:  Invalid combination for transStatusReason [" + transStatusReason
						+ "] and Challenge Cancel [" + challengeCancel + "]");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "challengeCancel");
				return erro;
			}
		}

		if (!messageCategory.equals(ThreeD20Utils.MESSAGE_CATEGORY_02)) { // merge to production

			if (transStatus.equals(ThreeD20Utils.TRANS_STATUS_SUCCESS)
					|| transStatus.equals(ThreeD20Utils.TRANS_STATUS_ATTEMPT)) {

				if (StringUtils.isEmpty(authenticationValue)) {
					log.error("RReq v2.1.0: Authentication Value	is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "authenticationValue");
					return erro;
				}
			}

		}
		// Authentication Value
		if (!StringUtils.isEmpty(authenticationValue) && authenticationValue.length() > 28) {
			log.error("RReq v2.1.0: Invalid length in Authentication Value");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "authenticationValue");
			return erro;
		}

		// Whitelist Status
		/*
		if (whiteListStatus != null) {
			if (StringUtils.isEmpty(whiteListStatusSource)) {
				log.error("RReq v2.1.0: White list status is missing");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}

			if (whiteListStatus.length() != 1) {
				log.error("RReq v2.1.0: Invalid length in Whitelist Status");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatus(whiteListStatus, messageVersion)) {
				log.error("RReq v2.1.0: Invalid Whitelist Status [" + whiteListStatus + "], messageVersion ["
						+ messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatus");
				return erro;
			}
		}

		// Whitelist Status Sources
		if (whiteListStatusSource != null) {
			if (whiteListStatusSource.length() != 2) {
				log.error("RReq v2.1.0: Invalid length in Whitelist Status Sources");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}

			if (!ThreeD20Utils.isValidWhiteListStatusSource(whiteListStatusSource, messageVersion)) {
				log.error("RReq v2.1.0: Invalid Whitelist Status Source [" + whiteListStatusSource
						+ "], messageVersion [" + messageVersion + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "whiteListStatusSource");
				return erro;
			}
		}
		*/

		if (messageExtension != null) {
			if (messageExtension.length > 10) {
				log.error("RReq v2.1.0: Invalid array size in Message Extension");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
				return erro;
			}
			for (int i = 0; i < messageExtension.length; i++) {
				if (messageExtension[i].getName().length() > 64) {
					log.error("RReq v2.1.0: Invalid length on name in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
					return erro;
				}
				if (messageExtension[i].getId().length() > 64) {
					log.error("RReq v2.1.0: Invalid length on id in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
					return erro;
				}
				if (messageExtension[i].getCriticalityIndicator() != null
						&& messageExtension[i].getCriticalityIndicator() == true) {
					log.error("PRes v2.1.0: Unrecognized message extension");
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
					log.error("RReq v2.1.0: Invalid length on id in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension");
					return erro;
				}
			}
		}

		return null;

	}
	
}
