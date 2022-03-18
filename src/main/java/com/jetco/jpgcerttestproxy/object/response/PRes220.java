package com.jetco.jpgcerttestproxy.object.response;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetco.jpgcerttestproxy.object.subfield.CardRangeData;
import com.jetco.jpgcerttestproxy.threeDSenum.ErrorResponse;
import com.jetco.jpgcerttestproxy.util.StringUtil;
import com.jetco.jpgcerttestproxy.util.ThreeD20Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PRes220 extends PRes {

	static final Logger log = LoggerFactory.getLogger(PRes220.class);

	public Map<String, String> validateMsg() {

		Map<String, String> erro = new HashMap<String, String>();
		// Message Version
		if (StringUtils.isEmpty(messageVersion)) {
			log.error("PRes v2.2.0: Message Version Number missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageVersion");
			return erro;
		}

		if (!ThreeD20Utils.isValidMessageVersion220(messageVersion)) {
			log.error("PRes v2.2.0: Invalid Message Version Number");
			erro.put(ErrorResponse.ErrorCode102.getErrorCode(), "messageVersion");
			return erro;
		}

		// Message Type
		if (StringUtils.isEmpty(messageType)) {
			log.error("PRes v2.2.0: Message Type missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageType");
			return erro;
		}

		if (!messageType.equals(ThreeD20Utils.MSG_TYPE_PRES)) {
			log.error("PRes v2.2.0: Incorrect Message Type for PRes Object");
			erro.put(ErrorResponse.ErrorCode101.getErrorCode(), "messageType");
			return erro;
		}

		// 3DS Server Transaction ID
		if (StringUtils.isEmpty(threeDSServerTransID)) {
			log.error("PRes v2.2.0: 3DS Server Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(threeDSServerTransID)) {
			log.error("PRes v2.2.0: Invalid 3DS Server Transaction ID [" + threeDSServerTransID + "]");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "threeDSServerTransID");
			return erro;
		}

		// DS End Protocol Version
		if (StringUtils.isEmpty(dsEndProtocolVersion)) {
			log.error("PRes v2.2.0: DS End Protocol Version is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsEndProtocolVersion");
			return erro;
		}

		if (dsEndProtocolVersion.length() > 8) {
			log.error("PRes v2.2.0: Invalid length in DS End Protocol Version [" + dsEndProtocolVersion + "]");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsEndProtocolVersion");
			return erro;
		}

		// DS Start Protocol Version
		if (StringUtils.isEmpty(dsStartProtocolVersion)) {
			log.error("PRes v2.2.0: DS Start Protocol Version is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsStartProtocolVersion");
			return erro;
		}

		if (dsStartProtocolVersion.length() > 8) {
			log.error("PRes v2.2.0: Invalid length in DS Start Protocol Version [" + dsStartProtocolVersion + "]");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsStartProtocolVersion");
			return erro;
		}

		// DS Transaction ID
		if (StringUtils.isEmpty(dsTransID)) {
			log.error("PRes v2.2.0: DS Transaction ID is missing");
			erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "dsTransID");
			return erro;
		}

		if (!StringUtil.isValidUUID(dsTransID)) {
			log.error("PRes v2.2.0:  Invalid DS Transaction ID [" + dsTransID + "]");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsTransID");
			return erro;
		}

		// Serial Number
		if (serialNum != null) {
			if (serialNum.length() <= 0 || serialNum.length() > 20) {
				log.error("PRes v2.2.0: Invalid length in Serial Number [" + serialNum + "].");
				erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "serialNum");
				return erro;
			}

			if (!StringUtils.isAlphanumeric(serialNum)) {
				log.error("PRes v2.2.0: Invalid length in Serial Number [" + serialNum + "].");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "serialNum");
				return erro;
			}
		}

		// Card Range Data
		if (cardRangeData != null && cardRangeData.size() == 0) {
			log.error("PRes v2.2.0: Invalid cardRangeDataList.");
			erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "cardRangeDataList");
			return erro;
		}

		if (cardRangeData != null && cardRangeData.size() > 0) {
			for (CardRangeData cardRange : cardRangeData) {

				// 3DS Method URL
				String threeDSMethodURL = cardRange.getThreeDSMethodURL();
				if (threeDSMethodURL != null && (threeDSMethodURL.length() <= 0 || threeDSMethodURL.length() > 256)) {
					log.error("PRes v2.2.0: Invalid length in 3DS Method URL [" + threeDSMethodURL + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSMethodURL");
					return erro;
				}

				if (threeDSMethodURL != null) {
					try {
						new URL(threeDSMethodURL);
					} catch (MalformedURLException mue) {
						log.error("PRes v2.2.0: Invalid 3DS Method URL [" + threeDSMethodURL + "].");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "threeDSMethodURL");
						return erro;
					}
				}

				// ACS End Protocol Version
				String acsEndProtocolVersion = cardRange.getAcsEndProtocolVersion();
				if (StringUtils.isEmpty(acsEndProtocolVersion)) {
					log.error("PRes v2.2.0: ACS End Protocol Version (in Card Range Data) is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsEndProtocolVersion");
					return erro;
				}

				if (acsEndProtocolVersion.length() < 5 && acsEndProtocolVersion.length() > 8) {
					log.error(
							"PRes v2.2.0: Invalid length in ACS End Protocol Version [" + acsEndProtocolVersion + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsEndProtocolVersion");
					return erro;
				}

				if (!acsEndProtocolVersion.matches("^\\d\\.\\d(\\.\\d)*$")) {
					log.error("PRes v2.2.0: Invalid ACS End Protocol Version [" + acsEndProtocolVersion + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsEndProtocolVersion");
					return erro;
				}

				if (!acsEndProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_200)
						&& !acsEndProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_210)
						&& !acsEndProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_220)) {
					log.error("PRes v2.2.0: Invalid ACS End Protocol Version [" + acsEndProtocolVersion + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsEndProtocolVersion");
					return erro;
				}

				// ACS Start Protocol Version
				String acsStartProtocolVersion = cardRange.getAcsStartProtocolVersion();
				if (StringUtils.isEmpty(acsStartProtocolVersion)) {
					log.error("PRes v2.2.0: ACS Start Protocol Version (in Card Range Data) is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "acsStartProtocolVersion");
					return erro;
				}

				if (acsStartProtocolVersion.length() < 5 && acsStartProtocolVersion.length() > 8) {
					log.error("PRes v2.2.0: Invalid length in ACS Start Protocol Version [" + acsStartProtocolVersion
							+ "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsStartProtocolVersion");
					return erro;
				}

				if (!acsStartProtocolVersion.matches("^\\d\\.\\d(\\.\\d)*$")) {
					log.error("PRes v2.2.0: Invalid ACS Start Protocol Version [" + acsStartProtocolVersion + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsStartProtocolVersion");
					return erro;
				}

				if (!acsStartProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_200)
						&& !acsStartProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_210)
						&& !acsStartProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_220)) {
					log.error("PRes v2.2.0: Invalid ACS Start Protocol Version [" + acsStartProtocolVersion + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "acsStartProtocolVersion");
					return erro;
				}

				// Action Indicator
				String actionInd = cardRange.getActionInd();
				if (!StringUtils.isEmpty(actionInd) && !ThreeD20Utils.isValidActionIndicator(actionInd)) {
					log.error("PRes v2.2.0: Invalid Action Indicator (in Card Range Data) [" + actionInd + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "actionInd");
					return erro;
				}

				// DS End Protocol Version
				String dsEndProtocolVersion = cardRange.getDsEndProtocolVersion();
				if (!StringUtils.isEmpty(dsEndProtocolVersion)) {
					if (dsEndProtocolVersion.length() < 5 && dsEndProtocolVersion.length() > 8) {
						log.error("PRes v2.2.0: Invalid length in DS End Protocol Version [" + dsEndProtocolVersion
								+ "].");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsEndProtocolVersion");
						return erro;
					}

					if (!dsEndProtocolVersion.matches("^\\d\\.\\d(\\.\\d)*$")) {
						log.error("PRes v2.2.0: Invalid DS End Protocol Version [" + dsEndProtocolVersion + "].");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsEndProtocolVersion");
						return erro;
					}

					if (!dsEndProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_200)
							&& !dsEndProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_210)
							&& !dsEndProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_220)) {
						log.error("PRes v2.2.0: Invalid DS End Protocol Version [" + dsEndProtocolVersion + "].");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsEndProtocolVersion");
						return erro;
					}
				}

				// DS Start Protocol Version
				String dsStartProtocolVersion = cardRange.getDsStartProtocolVersion();
				if (dsStartProtocolVersion != null) {
					if (dsStartProtocolVersion.length() < 5 && dsStartProtocolVersion.length() > 8) {
						log.error("PRes v2.2.0: Invalid length in DS Start Protocol Version [" + dsStartProtocolVersion
								+ "].");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsStartProtocolVersion");
						return erro;
					}

					if (!dsStartProtocolVersion.matches("^\\d\\.\\d(\\.\\d)*$")) {
						log.error("PRes v2.2.0: Invalid DS Start Protocol Version [" + dsStartProtocolVersion + "].");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsStartProtocolVersion");
						return erro;
					}

					if (!dsStartProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_200)
							&& !dsStartProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_210)
							&& !dsStartProtocolVersion.equals(ThreeD20Utils.PROTOCOL_VERSION_220)) {
						log.error("PRes v2.2.0: Invalid DS Start Protocol Version [" + dsStartProtocolVersion + "].");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "dsStartProtocolVersion");
						return erro;
					}
				}
				// End Card Range
				String endRange = cardRange.getEndRange();
				if (StringUtils.isEmpty(endRange)) {
					log.error("PRes v2.2.0: End Card Range (in Card Range Data) is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "endRange");
					return erro;
				}

				if (endRange.length() < 13 || endRange.length() > 19) {
					log.error("PRes v2.2.0: Invalid length in End Card Range [" + endRange + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "endRange");
					return erro;
				}

				if (!StringUtils.isNumeric(endRange)) {
					log.error("PRes v2.2.0: Invalid End Card Range [" + endRange + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "endRange");
					return erro;
				}

				// Start Card Range
				String startRange = cardRange.getStartRange();
				if (StringUtils.isEmpty(startRange)) {
					log.error("PRes v2.2.0: Start Card Range (in Card Range Data) is missing");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "startRange");
					return erro;
				}

				if (startRange.length() < 13 || startRange.length() > 19) {
					log.error("PRes v2.2.0: Invalid length in Start Card Range [" + startRange + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "startRange");
					return erro;
				}

				if (!StringUtils.isNumeric(startRange)) {
					log.error("PRes v2.2.0: Invalid Start Card Range [" + startRange + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "startRange");
					return erro;
				}

				BigInteger startBig = new BigInteger(startRange);
				BigInteger endBig = new BigInteger(endRange);

				if (startBig.compareTo(endBig) == 1) {
					log.error("PRes v2.2.0: Start Card Range [" + startRange + "] is greater then End Card Range ["
							+ endRange + "].");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
					return erro;
				}

				// ACS Information Indicator
				if (cardRange.getAcsInfoInd() != null) {
					if (cardRange.getAcsInfoInd() instanceof String) {
						log.error("PRes v2.2.0: Invalid ACS Informcation Indicator (in Card Range Data) ["
								+ cardRange.getAcsInfoInd() + "]");
						erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "cardRangeData.acsInfoInd");
						return erro;
					}
					List<String> acsInfoIndList = (List<String>) cardRange.getAcsInfoInd();
					if (acsInfoIndList != null && acsInfoIndList.size() > 0) {
						for (String acsInfoInd : acsInfoIndList) {

							if (StringUtils.isEmpty(acsInfoInd)) {
								log.error("PRes v2.2.0: Invalid ACS Informcation Indicator (in Card Range Data) ["
										+ acsInfoInd + "]");
								erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "cardRangeData.acsInfoInd");
								return erro;
							}

							if (acsInfoInd.length() != 2) {
								log.error(
										"PRes v2.2.0: Invalid length in ACS Informcation Indicator (in Card Range Data) ["
												+ acsInfoInd + "]");
								erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "cardRangeData.acsInfoInd");
								return erro;
							}

							if (!ThreeD20Utils.isValidACSInfoIndicator(acsInfoInd)) {
								log.error("PRes v2.2.0: Invalid ACS Informcation Indicator (in Card Range Data) ["
										+ acsInfoInd + "].");
								erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "cardRangeData.acsInfoInd");
								return erro;
							}
						}
					}
				}
			}
		}

		// Message extension
		if (messageExtension != null) {
			if (messageExtension.length > 10) {
				log.error("PRes v2.2.0: Invalid array size in Message Extension");
				erro.put(ErrorResponse.ErrorCode201.getErrorCode(), "messageExtension");
				return erro;
			}
			for (int i = 0; i < messageExtension.length; i++) {
				if (messageExtension[i].getName().length() > 64) {
					log.error("PRes v2.2.0: Invalid length on name in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension.name");
					return erro;
				}
				if (messageExtension[i].getId().length() > 64) {
					log.error("PRes v2.2.0: Invalid length on id in Message Extension");
					erro.put(ErrorResponse.ErrorCode203.getErrorCode(), "messageExtension.id");
					return erro;
				}
				if (messageExtension[i].getCriticalityIndicator() != null
						&& messageExtension[i].getCriticalityIndicator() == true) {
					log.error("PRes v2.2.0: Unrecognized message extension");
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
					log.error("PRes v2.2.0: Invalid length on id in Message Extension");
					erro.put(ErrorResponse.ErrorCode201.getErrorCode(), ErrorResponse.ErrorCode203.getErrorDesc());
					return erro;
				}
			}
		}
		return null;
	}
}
