package com.jetco.jpgcerttestproxy.util;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.dao.DataAccessException;

import com.jetco.common.crypto.CryptoException;
import com.jetco.common.crypto.CryptoLib;
import com.jetco.common.util.StringUtils;
import com.jetco.pygw.beans.BankInfo;
import com.jetco.pygw.beans.MerchantInfo;
import com.jetco.pygw.credit.CreditPaymentUtils;
import com.jetco.pygw.credit.paypage.ECI;
import com.jetco.pygw.credit.paypage.threedmsg.Malformed3DMsgException;
import com.jetco.pygw.credit.paypage.threedmsg20.ThreeDMsg20;
import com.jetco.pygw.credit.paypage.threedmsg20.ThreeDResMsg20;
*/
import org.apache.commons.lang3.StringUtils;

import com.jetco.jpgcerttestproxy.threeDSenum.CardType;
import com.jetco.jpgcerttestproxy.threeDSenum.Eci;

/**
 * A class that is used as an utility for 3DS 2.0
 *
 * @author poppy_k
 * @version 1.0
 */
public class ThreeD20Utils {

	// Constant for determine for cardType
	public static final String CARD_TYPE_3D = "3D";
	public static final String CARD_TYPE_SC = "SC";
	public static final String CARD_TYPE_UPOP = "UP";
	
	// Constant for SSL protocol
    public static final String SSL_PROTOCOL_TLS10_DESC = "TLS";
    public static final String SSL_PROTOCOL_SSL_TLS2_DESC = "SSL_TLSv2";
    public static final String SSL_PROTOCOL_TLS12_DESC = "TLSv1.2";
	
	// Constant for Message Version
	public static final String THREED_MSG_VERSION_BOTH = "0.0.0";
	public static final String THREED_MSG_VERSION_102 = "1.0.2";
	public static final String THREED_MSG_VERSION_210 = "2.1.0";
	public static final String THREED_MSG_VERSION_220 = "2.2.0";
	

	public static final String PROTOCOL_VERSION_200 = "2.0.0";
	public static final String PROTOCOL_VERSION_210 = "2.1.0";
	public static final String PROTOCOL_VERSION_220 = "2.2.0";
	
	// Constant for Message Version Description
	public static final String PROTOCOL_VERSION_BOTH_DESC = "Support Both";
	public static final String PROTOCOL_VERSION_1_0_2_DESC = "3DS 1.0";
	public static final String PROTOCOL_VERSION_2_1_0_DESC = "EMV 3DS 2.0";
	
	public static final String MESSAGE_CATEGORY_01 = "01";
	public static final String MESSAGE_CATEGORY_02 = "02";
	
	// Constant for Program Protocol
	public static final String PROGRAM_PROTOCOL_SSL = "0";
	public static final String PROGRAM_PROTOCOL_3DS10 = "1";
	public static final String PROGRAM_PROTOCOL_3DS20 = "2";

	// Constant for 3DS 2.0 Message Type
	public static final String MSG_TYPE_ERROR = "Erro";
	public static final String MSG_TYPE_AREQ = "AReq";
	public static final String MSG_TYPE_ARES = "ARes";
	public static final String MSG_TYPE_CREQ = "CReq";
	public static final String MSG_TYPE_CRES = "CRes";
	public static final String MSG_TYPE_RREQ = "RReq";
	public static final String MSG_TYPE_RRES = "RRes";
	public static final String MSG_TYPE_PREQ = "PReq";
	public static final String MSG_TYPE_PRES = "PRes";

	// Constant for Transaction Status in 3DS 2.0 Message
	public static final String TRANS_STATUS_SUCCESS = "Y";
	public static final String TRANS_STATUS_DENIED = "N";
	public static final String TRANS_STATUS_NOT_PERFORM = "U";
	public static final String TRANS_STATUS_ATTEMPT = "A";
	public static final String TRANS_STATUS_CHALLENGE = "C";
	public static final String TRANS_STATUS_REJECT = "R";
	public static final String TRANS_STATUS_DECOUPLED_CHALLENGE = "D";
	public static final String TRANS_STATUS_INFORMATION = "I";
	
	// Constant for Transaction Status Description in 3DS 2.0 Message
	public static final String TRANS_STATUS_SUCCESS_MSG = "Authentication/ Account Verification Successful";
	public static final String TRANS_STATUS_DENIED_MSG = "Not Authenticated /Account Not Verified; Transaction denied";
	public static final String TRANS_STATUS_NOT_PERFORM_MSG = "Authentication/ Account Verification Could Not Be Performed; Technical or other problem, as indicated in ARes or RReq";
	public static final String TRANS_STATUS_ATTEMPT_MSG = "Attempts Processing Performed; Not Authenticated/Verified, but a proof of attempted authentication/verification is provided";
	public static final String TRANS_STATUS_CHALLENGE_MSG = "Challenge Required; Additional authentication is required using the CReq/CRes";
	public static final String TRANS_STATUS_REJECT_MSG = "Authentication/ Account Verification Rejected; Issuer is rejecting authentication/verification and request that authorisation not be attempted";
	public static final String TRANS_STATUS_DECOUPLED_CHALLENGE_MSG = "Challenge Required; Decoupled Authentication confirmed";
	public static final String TRANS_STATUS_INFORMATION_MSG = "Informational Only; 3DS Requestor challenge preference acknowledged";
	
	public static Hashtable transStatusDispTable;
	static {
		transStatusDispTable = new Hashtable();
		transStatusDispTable.put(TRANS_STATUS_SUCCESS, TRANS_STATUS_SUCCESS_MSG);
		transStatusDispTable.put(TRANS_STATUS_DENIED, TRANS_STATUS_DENIED_MSG);
		transStatusDispTable.put(TRANS_STATUS_NOT_PERFORM, TRANS_STATUS_NOT_PERFORM_MSG);
		transStatusDispTable.put(TRANS_STATUS_ATTEMPT, TRANS_STATUS_ATTEMPT_MSG);
		transStatusDispTable.put(TRANS_STATUS_CHALLENGE, TRANS_STATUS_CHALLENGE_MSG);
		transStatusDispTable.put(TRANS_STATUS_REJECT, TRANS_STATUS_REJECT_MSG);
		transStatusDispTable.put(TRANS_STATUS_DECOUPLED_CHALLENGE, TRANS_STATUS_DECOUPLED_CHALLENGE_MSG);
		transStatusDispTable.put(TRANS_STATUS_INFORMATION, TRANS_STATUS_INFORMATION_MSG);
	}

	// Constant Indicator
	public static final String YES_IND = "Y";
	public static final String NO_IND = "N";
	public static final String UNAVAILABLE_IND = "U";

	// Constant for Device Channel
	public static final String DEVICE_CHANNEL_APP_BASE = "01";
	public static final String DEVICE_CHANNEL_BROWSER_BASE = "02";
	public static final String DEVICE_CHANNEL_3RI = "03";

	// Constant for Device Channel Description
	public static final String DEVICE_CHANNEL_APP_BASE_DISP = "App-based (APP)";
	public static final String DEVICE_CHANNEL_BROWSER_BASE_DISP = "Browser (BRW)";

	// Constant for Merchant Category
	public static final String MESSAGE_CATEGORY_PA = "01";
	public static final String MESSAGE_CATEGORY_NPA = "02";

	// Constant for Merchant Category Description
	public static final String MESSAGE_CATEGORY_PA_DISP = "Payment Authentication (PA)";
	public static final String MESSAGE_CATEGORY_NPA_DISP = "NON-Payment Authentication (NPA)";

	// Constant for Result Status
	public static final String RESULT_STATUS_RECEIVED = "01";
	public static final String RESULT_STATUS_CHALLENGE_NOT_SEND = "02";
	public static final String RESULT_STATUS_ARES_NOT_SEND = "03";
	
	// Constant for Result Status Description
	public static final String RESULT_STATUS_RECEIVED_MSG = "Results Request Received for further Processing";
	public static final String RESULT_STATUS_CHALLENGE_NOT_SEND_MSG = "Challenge Request not sent to ACS by 3DS Requestor (3DS Server or 3DS Requestor opted out of the challenge)";
	public static final String RESULT_STATUS_ARES_NOT_SEND_MSG = "ARes challenge data not delivered to the 3DS Requestor due to technical error";
	
	// Constant for 3DS Requestor Authentication Indicator
	public static final String AHTH_IND_PAYMENT = "01";

	// Constant for Type of transaction being authenticated
	public static final String TRAN_TYPE_SERVICE_PURCHASE = "01";
	public static final String TRAN_TYPE_CHECK_ACCEPTANCE = "02";
	public static final String TRAN_TYPE_ACCOUNT_FUNDING = "10";
	public static final String TRAN_TYPE_QUASI_CASH_TXN = "11";
	public static final String TRAN_TYPE_PREPAID = "28";

	// Constant for 3DS Requestor Challenge Indicator
	public static final String CHALLENGE_IND_NO_PREFERENCE = "01";
	public static final String CHALLENGE_IND_NO_CHALLENGE_REQUEST = "02";
	public static final String CHALLENGE_IND_CHALLENGE_REQUEST = "03";
	public static final String CHALLENGE_IND_CHALLENGE_REQUEST_MANDATE = "04";
	public static final String CHALLENGE_IND_NO_CHALLENGE_REQUEST_RISK_PERFORM = "05";
	public static final String CHALLENGE_IND_NO_CHALLENGE_REQUEST_DATA_SHARE = "06";
	public static final String CHALLENGE_IND_NO_CHALLENGE_REQUEST_STRONG_AUTH = "07";
	public static final String CHALLENGE_IND_NO_CHALLENGE_REQUEST_WHITELIST_EXEMPTION = "08";
	public static final String CHALLENGE_IND_CHALLENGE_REQUEST_WHITELIST_PROMPT = "09";
	
	// Constant for Authentication Type
	public static final String AUTHENTICATON_TYPE_STATIC = "01";
	public static final String AUTHENTICATON_TYPE_DYNAMIC = "02";
	public static final String AUTHENTICATON_TYPE_OOB = "03";
	public static final String AUTHENTICATON_TYPE_DECOUPLED = "04";

	// Constant for Authentication Type Description
	public static final String AUTHENTICATON_TYPE_STATIC_DISP = "Static";
	public static final String AUTHENTICATON_TYPE_DYNAMIC_DISP = "Dynamic";
	public static final String AUTHENTICATON_TYPE_OOB_DISP = "OOB";
	public static final String AUTHENTICATON_TYPE_DECOUPLED_DISP = "Decoupled";

	// Constant for Error Component
	public static final String ERROR_COMPONENT_3DS_SDK = "C";
	public static final String ERROR_COMPONENT_3DS_SERVER = "S";
	public static final String ERROR_COMPONENT_DS = "D";
	public static final String ERROR_COMPONENT_ACS = "A";

	// Constant for Authentication Method
	public static final String AUTH_METHOD_STATIC = "01";
	public static final String AUTH_METHOD_SMS_OTP = "02";
	public static final String AUTH_METHOD_KEY = "03";
	public static final String AUTH_METHOD_APP_OTP = "04";
	public static final String AUTH_METHOD_OTP_OTHER = "05";
	public static final String AUTH_METHOD_KBA = "06";
	public static final String AUTH_METHOD_OOB_BIOMETRICS = "07";
	public static final String AUTH_METHOD_OOB_LOGIN = "08";
	public static final String AUTH_METHOD_OOB_OTHER = "09";
	public static final String AUTH_METHOD_OTHER = "10";

	// Constant for Authentication Method Description
	public static final String AUTH_METHOD_STATIC_DISP = "Static Passcode";
	public static final String AUTH_METHOD_SMS_OTP_DISP = "SMS OTP";
	public static final String AUTH_METHOD_KEY_DISP = "Key fob or EMV card reader OTP";
	public static final String AUTH_METHOD_APP_OTP_DISP = "App OTP";
	public static final String AUTH_METHOD_OTP_OTHER_DISP = "OTP Other";
	public static final String AUTH_METHOD_KBA_DISP = "KBA";
	public static final String AUTH_METHOD_OOB_BIOMETRICS_DISP = "OOB Biometrics";
	public static final String AUTH_METHOD_OOB_LOGIN_DISP = "OOB Login";
	public static final String AUTH_METHOD_OOB_OTHER_DISP = "OOB Other";
	public static final String AUTH_METHOD_OTHER_DISP = "Other";
	
	// Constant for Authentication Type
	public static final String AUTH_TPYE_STATIC = "01";
	public static final String AUTH_TPYE_DYNAMIC = "02";
	public static final String AUTH_TPYE_OOB = "03";
	public static final String AUTH_TPYE_DECOUPLE = "04";

	// Constant for Challenge Window Size
	public static final String CHALLENGE_WINDOW_SIZE_250X400 = "01";
	public static final String CHALLENGE_WINDOW_SIZE_390X400 = "02";
	public static final String CHALLENGE_WINDOW_SIZE_500X600 = "03";
	public static final String CHALLENGE_WINDOW_SIZE_600X400 = "04";
	public static final String CHALLENGE_WINDOW_SIZE_FULL_SCREEN = "05";

	// Constant for ACS Interface
	public static final String ACS_INTERFACE_NATIVE_UI = "01";
	public static final String ACS_INTERFACE_HTML_UI = "02";
	
	// Constant for ACS UI Template
	public static final String ACS_UI_TEMPLATE_TEXT = "01";
	public static final String ACS_UI_TEMPLATE_SINGLE_SELECT = "02";
	public static final String ACS_UI_TEMPLATE_MULTI_SELECT = "03";
	public static final String ACS_UI_TEMPLATE_OOB = "04";
	public static final String ACS_UI_TEMPLATE_HTML_OTHER = "05";
			
	// Constant for Challenge Cancelation Indicator
	public static final String CHALLENGE_CANCELATION_IND_CARDHOLDER_CANCEL = "01";
//	public static final String CHALLENGE_CANCELATION_IND_3DS_REQUESTOR_CANCEL = "02";
	public static final String CHALLENGE_CANCELATION_IND_DECOUPLE_TIMEOUT = "03";
	public static final String CHALLENGE_CANCELATION_IND_ACS_OTHER_TIMEOUT = "04";
	public static final String CHALLENGE_CANCELATION_IND_ACS_FIRST_CREQ_TIMEOUT = "05";
	public static final String CHALLENGE_CANCELATION_IND_TXN_ERROR = "06";
	public static final String CHALLENGE_CANCELATION_IND_UNKNOWN = "07";
	public static final String CHALLENGE_CANCELATION_IND_SDK_TXN_TIMEOUT = "08";
	
	// Constant for Transaction Status Reason
	public static Hashtable transStatusReasonTable;
	public static final String TRANS_STATUS_REASON_01 = "01";
	public static final String TRANS_STATUS_REASON_02 = "02";
	public static final String TRANS_STATUS_REASON_03 = "03";
	public static final String TRANS_STATUS_REASON_04 = "04";
	public static final String TRANS_STATUS_REASON_05 = "05";
	public static final String TRANS_STATUS_REASON_06 = "06";
	public static final String TRANS_STATUS_REASON_07 = "07";
	public static final String TRANS_STATUS_REASON_08 = "08";
	public static final String TRANS_STATUS_REASON_09 = "09";
	public static final String TRANS_STATUS_REASON_10 = "10";
	public static final String TRANS_STATUS_REASON_11 = "11";
	public static final String TRANS_STATUS_REASON_12 = "12";
	public static final String TRANS_STATUS_REASON_13 = "13";
	public static final String TRANS_STATUS_REASON_14 = "14";
	public static final String TRANS_STATUS_REASON_15 = "15";
	public static final String TRANS_STATUS_REASON_16 = "16";
	public static final String TRANS_STATUS_REASON_17 = "17";
	public static final String TRANS_STATUS_REASON_18 = "18";
	public static final String TRANS_STATUS_REASON_19 = "19";
	public static final String TRANS_STATUS_REASON_20 = "20";
	public static final String TRANS_STATUS_REASON_21 = "21";
	public static final String TRANS_STATUS_REASON_22 = "22";
	public static final String TRANS_STATUS_REASON_23 = "23";
	public static final String TRANS_STATUS_REASON_24 = "24";
	public static final String TRANS_STATUS_REASON_25 = "25";
	public static final String TRANS_STATUS_REASON_26 = "26";
	
	public static final String TRANS_STATUS_REASON_CARD_AUTH_FAIL_01 = "Card authentication failed";
	public static final String TRANS_STATUS_REASON_UNKNOWN_DEVICE_02 = "Unknown Device";
	public static final String TRANS_STATUS_REASON_UNSUPPORTED_DEVICE_03 = "Unsupported Device";
	public static final String TRANS_STATUS_REASON_EXCEED_LIMIT_04 = "Exceeds authentication frequency limit";
	public static final String TRANS_STATUS_REASON_EXPIRED_CARD_05 = "Expired card";
	public static final String TRANS_STATUS_REASON_INVALID_CARD_NO_06 = "Invalid card number";
	public static final String TRANS_STATUS_REASON_INVALID_TXN_07 = "Invalid transaction";
	public static final String TRANS_STATUS_REASON_NO_CARD_RECORD_08 = "No Card record";
	public static final String TRANS_STATUS_REASON_SECURITY_FAIL_09 = "Security failure";
	public static final String TRANS_STATUS_REASON_STOLEN_CARD_10 = "Stolen card";
	public static final String TRANS_STATUS_REASON_SUSPECTED_FRAUD_11 = "Suspected fraud";
	public static final String TRANS_STATUS_REASON_TXN_NOT_PERMIT_12 = "Transaction not permitted to cardholder";
	public static final String TRANS_STATUS_REASON_CARD_NOT_ENROLL_13 = "Cardholder not enrolled in service";
	public static final String TRANS_STATUS_REASON_TIME_OUT_IN_ACS_14 = "Transaction timed out at the ACS";
	public static final String TRANS_STATUS_REASON_LOW_CONFIDENCE_15 = "Low confidence";
	public static final String TRANS_STATUS_REASON_MEDIUM_CONFIDENCE_16 = "Medium confidence";
	public static final String TRANS_STATUS_REASON_HIGH_CONFIDENCE_17 = "High confidence";
	public static final String TRANS_STATUS_REASON_VERY_HIGH_CONFIDENCE_18 = "Very High confidence";
	public static final String TRANS_STATUS_REASON_EXCESS_ACS_MAX_CHALLENGE_19 = "Exceeds ACS maximum challenges";
	public static final String TRANS_STATUS_REASON_NP_NOT_SUPPORT_20 = "Non-Payment transaction not supported";
	public static final String TRANS_STATUS_REASON_3RI_NOT_SUPPORT_21 = "3RI transaction not supported";
	public static final String TRANS_STATUS_REASON_3RI_NOT_SUPPORT_22 = "ACS technical issue";
	public static final String TRANS_STATUS_REASON_3RI_NOT_SUPPORT_23 = "Decoupled Authentication required by ACS but not requested by 3DS Requestor";
	public static final String TRANS_STATUS_REASON_3RI_NOT_SUPPORT_24 = "3DS Requestor Decoupled Max Expiry Time exceeded";
	public static final String TRANS_STATUS_REASON_3RI_NOT_SUPPORT_25 = "Decoupled Authentication was provided insufficient time to authenticate cardholder. ACS will not make attempt";
	public static final String TRANS_STATUS_REASON_3RI_NOT_SUPPORT_26 = "Authentication attempted but not performed by the cardholder";

	static {
		transStatusReasonTable = new Hashtable();
		transStatusReasonTable.put(TRANS_STATUS_REASON_01, TRANS_STATUS_REASON_CARD_AUTH_FAIL_01);
		transStatusReasonTable.put(TRANS_STATUS_REASON_02, TRANS_STATUS_REASON_UNKNOWN_DEVICE_02);
		transStatusReasonTable.put(TRANS_STATUS_REASON_03, TRANS_STATUS_REASON_UNSUPPORTED_DEVICE_03);
		transStatusReasonTable.put(TRANS_STATUS_REASON_04, TRANS_STATUS_REASON_EXCEED_LIMIT_04);
		transStatusReasonTable.put(TRANS_STATUS_REASON_05, TRANS_STATUS_REASON_EXPIRED_CARD_05);
		transStatusReasonTable.put(TRANS_STATUS_REASON_06, TRANS_STATUS_REASON_INVALID_CARD_NO_06);
		transStatusReasonTable.put(TRANS_STATUS_REASON_07, TRANS_STATUS_REASON_INVALID_TXN_07);
		transStatusReasonTable.put(TRANS_STATUS_REASON_08, TRANS_STATUS_REASON_NO_CARD_RECORD_08);
		transStatusReasonTable.put(TRANS_STATUS_REASON_09, TRANS_STATUS_REASON_SECURITY_FAIL_09);
		transStatusReasonTable.put(TRANS_STATUS_REASON_10, TRANS_STATUS_REASON_STOLEN_CARD_10);
		transStatusReasonTable.put(TRANS_STATUS_REASON_11, TRANS_STATUS_REASON_SUSPECTED_FRAUD_11);
		transStatusReasonTable.put(TRANS_STATUS_REASON_12, TRANS_STATUS_REASON_TXN_NOT_PERMIT_12);
		transStatusReasonTable.put(TRANS_STATUS_REASON_13, TRANS_STATUS_REASON_CARD_NOT_ENROLL_13);
		transStatusReasonTable.put(TRANS_STATUS_REASON_14, TRANS_STATUS_REASON_TIME_OUT_IN_ACS_14);
		transStatusReasonTable.put(TRANS_STATUS_REASON_15, TRANS_STATUS_REASON_LOW_CONFIDENCE_15);
		transStatusReasonTable.put(TRANS_STATUS_REASON_16, TRANS_STATUS_REASON_MEDIUM_CONFIDENCE_16);
		transStatusReasonTable.put(TRANS_STATUS_REASON_17, TRANS_STATUS_REASON_HIGH_CONFIDENCE_17);
		transStatusReasonTable.put(TRANS_STATUS_REASON_18, TRANS_STATUS_REASON_VERY_HIGH_CONFIDENCE_18);
		transStatusReasonTable.put(TRANS_STATUS_REASON_19, TRANS_STATUS_REASON_EXCESS_ACS_MAX_CHALLENGE_19);
		transStatusReasonTable.put(TRANS_STATUS_REASON_20, TRANS_STATUS_REASON_NP_NOT_SUPPORT_20);
		transStatusReasonTable.put(TRANS_STATUS_REASON_21, TRANS_STATUS_REASON_3RI_NOT_SUPPORT_21);
		transStatusReasonTable.put(TRANS_STATUS_REASON_22, TRANS_STATUS_REASON_3RI_NOT_SUPPORT_22);
		transStatusReasonTable.put(TRANS_STATUS_REASON_23, TRANS_STATUS_REASON_3RI_NOT_SUPPORT_23);
		transStatusReasonTable.put(TRANS_STATUS_REASON_24, TRANS_STATUS_REASON_3RI_NOT_SUPPORT_24);
		transStatusReasonTable.put(TRANS_STATUS_REASON_25, TRANS_STATUS_REASON_3RI_NOT_SUPPORT_25);
		transStatusReasonTable.put(TRANS_STATUS_REASON_26, TRANS_STATUS_REASON_3RI_NOT_SUPPORT_26);
	}

	// Constant for Action Indicator
	public static final String ACTION_INDICATOR_ADD = "A";
	public static final String ACTION_INDICATOR_DELETE = "D";
	public static final String ACTION_INDICATOR_MODIFY = "M";
	
	// Constants for 3DS Requestor Authentication Indicator 	
	public static final String AUTHENTICATION_IND_PAYMENT = "01";
	public static final String AUTHENTICATION_IND_RECURRING = "02";
	public static final String AUTHENTICATION_IND_INSTALMENT = "03";
	public static final String AUTHENTICATION_IND_ADD_CARD = "04";
	public static final String AUTHENTICATION_IND_MAINTAIN_CARD = "05";
	public static final String AUTHENTICATION_IND_VERIFICATION = "06";

	// Constants for Whitelist Status Indicator 	
	public static final String WHITE_LIST_STATUS_YES = "Y";
	public static final String WHITE_LIST_STATUS_NO = "N";
	public static final String WHITE_LIST_STATUS_NOT_ELIGIBLE = "E";
	public static final String WHITE_LIST_STATUS_PENDING = "P";
	public static final String WHITE_LIST_STATUS_REJECT = "R";
	public static final String WHITE_LIST_STATUS_UNKNOWN = "U";

	// Constants for Whitelist Status Source Indicator 	
	public static final String WHITE_LIST_STATUS_SOURCE_3DS_SERVER = "01";
	public static final String WHITE_LIST_STATUS_SOURCE_DS = "02";
	public static final String WHITE_LIST_STATUS_SOURCE_ACS = "03";
	
	// Constants for ACS Information Indicator
	public static final String ACS_INFO_IND_AUTH_AVAIL = "01";
	public static final String ACS_INFO_IND_ATTEMP = "02";
	public static final String ACS_INFO_IND_DECOUPLE = "03";
	public static final String ACS_INFO_IND_WHITELIST = "04";

	// Constant for 3DS Requestor Authentication Method
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_NO_OCCURRED = "01";
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_3DS_REQUESTOR = "02";
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FEDERATED_ID = "03";
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_ISSUER_CREDENTIAL = "04";
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_3RD_PARTY = "05";
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FIDO_AUTHENTICATOR = "06";
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FIDO_AUTHENTICATOR_DATA_SIGNED = "07";
	public static final String THREEDS_REQUESTOR_AUTHENTICATION_METHOD_SRC_ASSURANCE_DATA = "08";
	
	// Constant for 3RI Indicator
	public static final String THREERI_IND_RECCURRING = "01";
	public static final String THREERI_IND_INSTALMENT_TXN = "02";
	public static final String THREERI_IND_ADD_CARD = "03";
	public static final String THREERI_IND_MAINTAIN_CARD_INFO = "04";
	public static final String THREERI_IND_ACCOUNT_VERIFY = "05";
	public static final String THREERI_IND_SPLIT_DELAY_SHIPMENT = "06";
	public static final String THREERI_IND_TOPUP = "07";
	public static final String THREERI_IND_MAIL_ORDER = "08";
	public static final String THREERI_IND_TELEPHONE_ORDER = "09";
	public static final String THREERI_IND_WHITELIST_STATUS_CHECK = "10";
	public static final String THREERI_IND_OTHER_PAYMENT = "11";
	public static final String THREERI_IND_INVALID_12 = "12";
	
	// Constant for DS Reserve 
	public static final int CONSTANT_FOR_DS_RESERVE_DATA_80 = 80;
	public static final int CONSTANT_FOR_DS_RESERVE_DATA_99 = 99;
	
  
	
	

	public static boolean isValidThreeDVersion(String msgVersion) {
		if (StringUtils.isEmpty(msgVersion)) {
			return false;
		}

		if (!msgVersion.equals(THREED_MSG_VERSION_102) && !msgVersion.equals(THREED_MSG_VERSION_210) && !msgVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isValidProgramProtocol(String msgVersion) {
		if (StringUtils.isEmpty(msgVersion)) {
			return false;
		}

		if (!msgVersion.equals(PROGRAM_PROTOCOL_3DS10) && !msgVersion.equals(PROGRAM_PROTOCOL_3DS20)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String generateThreeDSServerTransID(String txnId) {

		// Generate specific UUID byte
		byte[] txnIdByte = txnId.getBytes();
		UUID txnIdUUID = UUID.nameUUIDFromBytes(txnIdByte);

		return txnIdUUID.toString();
	}
	
	public static boolean isValidTransStatus(String transStatus, String msgType) {
		return isValidTransStatus(transStatus, msgType, null);
	}

	public static boolean isValidTransStatus(String transStatus, String msgType, String version) {
		if (StringUtils.isEmpty(transStatus)) {
			return false;
		}

		if (transStatus.length() != 1) {
			return false;
		}

		if (msgType.equals(ThreeD20Utils.MSG_TYPE_ARES)) {
			
			if (version == null){
				return false;
			}
			
			if (!version.equals(THREED_MSG_VERSION_210) && !version.equals(THREED_MSG_VERSION_220)){
				return false;
			}
			
			if (version.equals(THREED_MSG_VERSION_210)){
				if (!transStatus.equals(TRANS_STATUS_SUCCESS)
						&& !transStatus.equals(TRANS_STATUS_DENIED)
						&& !transStatus.equals(TRANS_STATUS_NOT_PERFORM)
						&& !transStatus.equals(TRANS_STATUS_ATTEMPT)
						&& !transStatus.equals(TRANS_STATUS_CHALLENGE)
						&& !transStatus.equals(TRANS_STATUS_REJECT)){				
					return false;
				}  	
			}
			
			if (version.equals(THREED_MSG_VERSION_220)){			
				if (!transStatus.equals(TRANS_STATUS_SUCCESS)
						&& !transStatus.equals(TRANS_STATUS_DENIED)
						&& !transStatus.equals(TRANS_STATUS_NOT_PERFORM)
						&& !transStatus.equals(TRANS_STATUS_ATTEMPT)
						&& !transStatus.equals(TRANS_STATUS_CHALLENGE)
						&& !transStatus.equals(TRANS_STATUS_REJECT)
						&& !transStatus.equals(TRANS_STATUS_DECOUPLED_CHALLENGE)
						&& !transStatus.equals(TRANS_STATUS_INFORMATION)) {
					return false;
				}
			}
			
		} else if (msgType.equals(ThreeD20Utils.MSG_TYPE_RREQ)){
			if (!transStatus.equals(TRANS_STATUS_SUCCESS)
					&& !transStatus.equals(TRANS_STATUS_DENIED)
					&& !transStatus.equals(TRANS_STATUS_NOT_PERFORM)
					&& !transStatus.equals(TRANS_STATUS_ATTEMPT)
					&& !transStatus.equals(TRANS_STATUS_REJECT)) {
				return false;
			}
		} else if (msgType.equals(ThreeD20Utils.MSG_TYPE_CRES)) {
			if (!transStatus.equals(TRANS_STATUS_SUCCESS)
					&& !transStatus.equals(TRANS_STATUS_DENIED)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidMessageVersion210(String messageVersion) {
		if (StringUtils.isEmpty(messageVersion)) {
			return false;
		}

		if (messageVersion.length() < 5 && messageVersion.length() > 8) {
			return false;
		}

		if (!messageVersion.matches("^\\d\\.\\d(\\.\\d)*$")) {
			return false;
		}
		
		if (!messageVersion.equals(THREED_MSG_VERSION_210)) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidMessageVersion220(String messageVersion) {
		if (StringUtils.isEmpty(messageVersion)) {
			return false;
		}

		if (messageVersion.length() < 5 && messageVersion.length() > 8) {
			return false;
		}

		if (!messageVersion.matches("^\\d\\.\\d(\\.\\d)*$")) {
			return false;
		}
		
		if (!messageVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		}
		return true;
	}
	
	public static String getMsgTypeFromMsg(String msg) {
	    String msgType = null;
	    
		Pattern msgTypeExp = Pattern.compile("\"messageType\"\\s*:\\s*\"(\\w+)\"");
        Matcher m = msgTypeExp.matcher(msg);
        while (m.find()) {
           msgType = m.group(1);
        }
        return msgType;
	}
	
	public static String getMsgVersionFromMsg(String msg) {
	    String msgVersion = null;
	    
//	    Pattern msgVersionExp = Pattern.compile("\"messageVersion\"\\s*:\\s*\"(\\d.\\d.\\d)\"");	 
	    Pattern msgVersionExp = Pattern.compile("\"messageVersion\"\\s*:\\s*\"(.*?)\"");
        Matcher m = msgVersionExp.matcher(msg);

        while (m.find()) {
        	msgVersion = m.group(1);
        }
        return msgVersion;
	}
	

	public static String get3DSTxnIDFromMsg(String msg) {
	    String threeDSTxnIDFieldValue = null;
	    String threeDSTxnID = null;

	    // 1. Check for threeDSServerTransID exist or not
	    Pattern threeDSServerTxnIDExp = Pattern.compile("\"threeDSServerTransID\"\\s*:\\s*\"(.*?)\"");		
	    Matcher m = threeDSServerTxnIDExp.matcher(msg);
	    while (m.find()) {
	    	threeDSTxnIDFieldValue = m.group(1);
        }
	    
	    if (threeDSTxnIDFieldValue != null) {
	    	// 2. threeDSServerTransID exist, check for valid data
	    	Pattern	threeDSServerTxnIDValidDataExp = Pattern.compile("\"threeDSServerTransID\"\\s*:\\s*\"([0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12})\"");

	    	Matcher m2 = threeDSServerTxnIDValidDataExp.matcher(msg);
	 	    while (m2.find()) {
	         	threeDSTxnID = m2.group(1);
	        }
	 	    
	 	    if (threeDSTxnID == null) {
	 	    	// 3. threeDSServerTransID is invalid, return value
	 	    	return threeDSTxnIDFieldValue;
	 	    } else {
	 	    	return threeDSTxnID;
	 	    }
	    }
	    
        return threeDSTxnID;
	}
	
	public static String getACSTxnIDFromMsg(String msg) {
	    String acsTxnID = null;
	    
	    Pattern acsTxnIDExp = Pattern.compile("\"acsTransID\"\\s*:\\s*\"([0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12})\"");
        Matcher m = acsTxnIDExp.matcher(msg);
        while (m.find()) {
        	acsTxnID = m.group(1);
        }
        return acsTxnID;
	}
	
	public static String getDSTxnIDFromMsg(String msg) {
	    String dsTxnID = null;
	    
	    Pattern dsTxnIDExp = Pattern.compile("\"dsTransID\"\\s*:\\s*\"([0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12})\"");
        Matcher m = dsTxnIDExp.matcher(msg);
        while (m.find()) {
        	dsTxnID = m.group(1);
        }
        return dsTxnID;
	}
	
	public static String getSDKTxnIDFromMsg(String msg) {
	    String sdkTxnID = null;
	    
	    Pattern sdkTxnIDExp = Pattern.compile("\"sdkTransID\"\\s*:\\s*\"([0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12})\"");
        Matcher m = sdkTxnIDExp.matcher(msg);
        while (m.find()) {
        	sdkTxnID = m.group(1);
        }
        return sdkTxnID;
	}
	
	public static String getCardRangeData(String msg) {
	    String dsTxnID = null;
	    
	    Pattern dsTxnIDExp = Pattern.compile("\"cardRangeData\"\\s*:\\s*(\\[\\{.*\\}\\])"); //array
        Matcher m = dsTxnIDExp.matcher(msg);
        while (m.find()) {
        	dsTxnID = m.group(1);
        }
        return dsTxnID;
	}

	
	
	
	
	
	
	public static boolean isValidActionIndicator(String actionIndicator) {
		if (StringUtils.isEmpty(actionIndicator)) {
			return false;
		}

		if (actionIndicator.length() != 1) {
			return false;
		}

		if (!actionIndicator.equals(ACTION_INDICATOR_ADD)
				&& !actionIndicator.equals(ACTION_INDICATOR_DELETE)
				&& !actionIndicator.equals(ACTION_INDICATOR_MODIFY)) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidACSInfoIndicator(String acsInfoInd) {
		
		if (!StringUtils.isNumeric(acsInfoInd)) {
			return false;
		}
		
		if (!acsInfoInd.equals(ACS_INFO_IND_AUTH_AVAIL)
				&& !acsInfoInd.equals(ACS_INFO_IND_ATTEMP)
				&& !acsInfoInd.equals(ACS_INFO_IND_DECOUPLE)
				&& !acsInfoInd.equals(ACS_INFO_IND_WHITELIST)
				&& !(Integer.parseInt(acsInfoInd) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(acsInfoInd) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
			
			return false;
		}
		return true;
	}

	public static boolean isValidWhiteListStatus(String whiteListStatus, String messageVersion) {
		if (StringUtils.isEmpty(whiteListStatus) || StringUtils.isEmpty(messageVersion)) {
			return false;
		}

		if (!messageVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		}

		if (whiteListStatus.length() != 1) {
			return false;
		}
		
		if (!whiteListStatus.equals(WHITE_LIST_STATUS_YES)
				&& !whiteListStatus.equals(WHITE_LIST_STATUS_NO)
				&& !whiteListStatus.equals(WHITE_LIST_STATUS_NOT_ELIGIBLE)
				&& !whiteListStatus.equals(WHITE_LIST_STATUS_PENDING)
				&& !whiteListStatus.equals(WHITE_LIST_STATUS_REJECT)
				&& !whiteListStatus.equals(WHITE_LIST_STATUS_UNKNOWN)) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidWhiteListStatusSource(String whiteListStatusSource, String messageVersion) {
		if (StringUtils.isEmpty(whiteListStatusSource) || StringUtils.isEmpty(messageVersion)) {
			return false;
		}
		
		if (!StringUtils.isNumeric(whiteListStatusSource)){          //merge to production -- start
			return false;
		}															 //merge to production -- end

		if (!messageVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		}
		
		if (whiteListStatusSource.length() != 2) {
			return false;
		}
		
		if (!whiteListStatusSource.equals(WHITE_LIST_STATUS_SOURCE_3DS_SERVER)
				&& !whiteListStatusSource.equals(WHITE_LIST_STATUS_SOURCE_DS)
				&& !whiteListStatusSource.equals(WHITE_LIST_STATUS_SOURCE_ACS)
				&& !(Integer.parseInt(whiteListStatusSource) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(whiteListStatusSource) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidACSUiType(String acsUiType) {
		if (StringUtils.isEmpty(acsUiType)) {
			return false;
		}

		if (acsUiType.length() != 2) {
			return false;
		}
		
		if (!acsUiType.equals(ACS_UI_TEMPLATE_TEXT)
				&& !acsUiType.equals(ACS_UI_TEMPLATE_SINGLE_SELECT)
				&& !acsUiType.equals(ACS_UI_TEMPLATE_MULTI_SELECT)
				&& !acsUiType.equals(ACS_UI_TEMPLATE_OOB)
				&& !acsUiType.equals(ACS_UI_TEMPLATE_HTML_OTHER)
				&& !(Integer.parseInt(acsUiType) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(acsUiType) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {	
			return false;
		}
		return true;
	}
	
	public static boolean isValidChallengeCancelationInd(String challengeCancel, String messageVersion) {
		if (StringUtils.isEmpty(challengeCancel) || StringUtils.isEmpty(messageVersion)) {
			return false;
		}

		if (challengeCancel.length() != 2) {
			return false;
		}
		
		if (!messageVersion.equals(THREED_MSG_VERSION_210) && 
				!messageVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		}
		
		if (messageVersion.equals(THREED_MSG_VERSION_210)) {
			if (!challengeCancel.equals(CHALLENGE_CANCELATION_IND_CARDHOLDER_CANCEL)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_ACS_OTHER_TIMEOUT)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_ACS_FIRST_CREQ_TIMEOUT)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_TXN_ERROR)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_UNKNOWN)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_SDK_TXN_TIMEOUT)
					//&& !(Integer.parseInt(challengeCancel) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(challengeCancel) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) { //merge to production ?-- start -- need to clarify
					){ //merge to production ?-- end -- need to clarify
				return false;
			}
		} else if (messageVersion.equals(THREED_MSG_VERSION_220)) {
			if (!challengeCancel.equals(CHALLENGE_CANCELATION_IND_CARDHOLDER_CANCEL)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_DECOUPLE_TIMEOUT)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_ACS_OTHER_TIMEOUT)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_ACS_FIRST_CREQ_TIMEOUT)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_TXN_ERROR)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_UNKNOWN)
					&& !challengeCancel.equals(CHALLENGE_CANCELATION_IND_SDK_TXN_TIMEOUT)
					//&& !(Integer.parseInt(challengeCancel) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(challengeCancel) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {   //merge to production ?-- start -- need to clarify
					){ //merge to production ?-- end -- need to clarify
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidAuthType(String authenticationType) {
		if (StringUtils.isEmpty(authenticationType)) {
			return false;
		}

		if (authenticationType.length() != 2) {
			return false;
		}
		
		if (!authenticationType.equals(AUTH_TPYE_STATIC)
				&& !authenticationType.equals(AUTH_TPYE_DYNAMIC)
				&& !authenticationType.equals(AUTH_TPYE_OOB)
				&& !authenticationType.equals(AUTH_TPYE_DECOUPLE)
				&& !(Integer.parseInt(authenticationType) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(authenticationType) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {	
			return false;
		}
		return true;
	}
	
	public static boolean isValidMessageCategory(String messageCategory) {
		if (StringUtils.isEmpty(messageCategory)) {
			return false;
		}

		if (messageCategory.length() != 2) {
			return false;
		}
		
		if (!messageCategory.equals(MESSAGE_CATEGORY_PA)
				&& !messageCategory.equals(MESSAGE_CATEGORY_NPA)) {
			return false;
		}
		return true;
	}
	
	
	public static boolean isValidTransStatusReason(String transStatusReason, String messageVersion) {
		if (StringUtils.isEmpty(transStatusReason) || StringUtils.isEmpty(messageVersion)) {
			return false;
		}

		if (transStatusReason.length() != 2) {
			return false;
		}
		
		if (!messageVersion.equals(THREED_MSG_VERSION_210) && 
				!messageVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		}
		
		if (messageVersion.equals(THREED_MSG_VERSION_210)) {
			if (!transStatusReason.equals(TRANS_STATUS_REASON_01)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_02)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_03)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_04)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_05)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_06)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_07)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_08)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_09)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_10)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_11)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_12)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_13)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_14)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_15)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_16)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_17)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_18)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_19)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_20)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_21)
					&& !(Integer.parseInt(transStatusReason) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(transStatusReason) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
				return false;
			}
		} else 	if (messageVersion.equals(THREED_MSG_VERSION_220)) {
			if (!transStatusReason.equals(TRANS_STATUS_REASON_01)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_02)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_03)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_04)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_05)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_06)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_07)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_08)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_09)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_10)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_11)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_12)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_13)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_14)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_15)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_16)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_17)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_18)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_19)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_20)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_21)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_22)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_23)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_24)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_25)
					&& !transStatusReason.equals(TRANS_STATUS_REASON_26)
					&& !(Integer.parseInt(transStatusReason) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(transStatusReason) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
				return false;
			}
		} 
		return true;
	}
	
	public static boolean isValid3DSRequestorAuthenticationMethod(String threeDSReqAuthMethod, String messageVersion) {
		if (StringUtils.isEmpty(threeDSReqAuthMethod) || StringUtils.isEmpty(messageVersion)) {
			return false;
		}
		
		if (!messageVersion.equals(THREED_MSG_VERSION_210) && 
				!messageVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		}

		if (threeDSReqAuthMethod.length() != 2) {
			return false;
		}
		
		if (messageVersion.equals(THREED_MSG_VERSION_210)) {
			if (!threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_NO_OCCURRED)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_3DS_REQUESTOR)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FEDERATED_ID)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_ISSUER_CREDENTIAL)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_3RD_PARTY)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FIDO_AUTHENTICATOR)
					&& !(Integer.parseInt(threeDSReqAuthMethod) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(threeDSReqAuthMethod) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
				return false;
			}
		} else if (messageVersion.equals(THREED_MSG_VERSION_220)) {
			if (!threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_NO_OCCURRED)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_3DS_REQUESTOR)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FEDERATED_ID)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_ISSUER_CREDENTIAL)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_3RD_PARTY)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FIDO_AUTHENTICATOR)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_LOGIN_FIDO_AUTHENTICATOR_DATA_SIGNED)
					&& !threeDSReqAuthMethod.equals(THREEDS_REQUESTOR_AUTHENTICATION_METHOD_SRC_ASSURANCE_DATA)
					&& !(Integer.parseInt(threeDSReqAuthMethod) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(threeDSReqAuthMethod) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidthreeRIInd(String threeRIInd, String messageVersion) {     //merge to production -- start
		if (StringUtils.isEmpty(threeRIInd) || StringUtils.isEmpty(messageVersion)) {
			return false;
		}
		
		if (!messageVersion.equals(THREED_MSG_VERSION_210) && 
				!messageVersion.equals(THREED_MSG_VERSION_220)) {
			return false;
		}

		if (threeRIInd.length() != 2) {
			return false;
		}
		
		if (messageVersion.equals(THREED_MSG_VERSION_210)) {
			if (!threeRIInd.equals(THREERI_IND_RECCURRING)
					&& !threeRIInd.equals(THREERI_IND_INSTALMENT_TXN)
					&& !threeRIInd.equals(THREERI_IND_ADD_CARD)
					&& !threeRIInd.equals(THREERI_IND_MAINTAIN_CARD_INFO)
					&& !threeRIInd.equals(THREERI_IND_ACCOUNT_VERIFY)
					&& !(Integer.parseInt(threeRIInd) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(threeRIInd) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
				return false;
			}
		} else if (messageVersion.equals(THREED_MSG_VERSION_220)) {
			if (!threeRIInd.equals(THREERI_IND_RECCURRING)
					&& !threeRIInd.equals(THREERI_IND_INSTALMENT_TXN)
					&& !threeRIInd.equals(THREERI_IND_ADD_CARD)
					&& !threeRIInd.equals(THREERI_IND_MAINTAIN_CARD_INFO)
					&& !threeRIInd.equals(THREERI_IND_ACCOUNT_VERIFY)
					&& !threeRIInd.equals(THREERI_IND_SPLIT_DELAY_SHIPMENT)
					&& !threeRIInd.equals(THREERI_IND_TOPUP)
					&& !threeRIInd.equals(THREERI_IND_MAIL_ORDER)
					&& !threeRIInd.equals(THREERI_IND_TELEPHONE_ORDER)
					&& !threeRIInd.equals(THREERI_IND_WHITELIST_STATUS_CHECK)
					&& !threeRIInd.equals(THREERI_IND_OTHER_PAYMENT)
					&& !threeRIInd.equals(THREERI_IND_INVALID_12)   //merge to production ? not match with EMV spec
					&& !(Integer.parseInt(threeRIInd) >= CONSTANT_FOR_DS_RESERVE_DATA_80 && Integer.parseInt(threeRIInd) <= CONSTANT_FOR_DS_RESERVE_DATA_99)) {
				return false;
			}
		}
		return true;
	}                                                                                 //merge to production -- end

	public static boolean isValidEci(int cardType, String eci) {
		
		if (StringUtils.isEmpty(eci) || !StringUtils.isNumeric(eci)) {
			return false;
		}
		
		//do not merge to production -- start 
		if (eci.equals("00")  || eci.equals("99")){
			return true;
		}
		//do not merge to production -- end
		
		if (cardType != CardType.TYPE_VISA.getValue() && cardType != CardType.TYPE_MASTER.getValue()) {
			return false;
		}
		
		/*
		 * VISA Cert Test : 3DSS-210-101
		 * fix string cannot compare
		 */
		if (cardType == CardType.TYPE_VISA.getValue()) {
			if (eci.equals(Eci.ECI_SECURE_VISA.getValue()) || eci.equals(Eci.ECI_NON_PARTICIPATE_OR_AMPT_VISA.getValue()) || eci.equals(Eci.ECI_NON_AUTH_VISA.getValue())) {
				return true;
			}
		} else if (cardType == CardType.TYPE_MASTER.getValue()) {
			if (eci == Eci.ECI_SECURE_MASTER.getValue() || eci == Eci.ECI_NON_PARTICIPATE_OR_AMPT_MASTER.getValue() || eci == Eci.ECI_NON_AUTH_MASTER.getValue()) {
				return true;
			}
		}
				
		return false;
	}
	
	public static List getSupportSslProtocolList() throws Exception {
		List sslProtocolList = new ArrayList();
		
		sslProtocolList.add(SSL_PROTOCOL_TLS10_DESC);
		sslProtocolList.add(SSL_PROTOCOL_SSL_TLS2_DESC);
		sslProtocolList.add(SSL_PROTOCOL_TLS12_DESC);
		
		Collections.sort(sslProtocolList);
		return sslProtocolList;
	}
	
	public static boolean isValueSSLProtocol(String sslProtocol) throws Exception {
		
		if (StringUtils.isEmpty(sslProtocol)) {
			return false;
		}
		
		if (!sslProtocol.equals(SSL_PROTOCOL_TLS10_DESC) && 
			!sslProtocol.equals(SSL_PROTOCOL_SSL_TLS2_DESC) &&
			!sslProtocol.equals(SSL_PROTOCOL_TLS12_DESC)) {
			return false;
		}
		
		return true;
	}
	
	public static String getMessageVersionDesc(String msgVersion) {
		String msgVersionDesc = null;
		
		if (StringUtils.isEmpty(msgVersion)) {
			return null;
		} 
		
		if (msgVersion.equals(THREED_MSG_VERSION_BOTH)) {
			msgVersionDesc = PROTOCOL_VERSION_BOTH_DESC;
			
		} else if (msgVersion.equals(THREED_MSG_VERSION_102)) {
			msgVersionDesc = PROTOCOL_VERSION_1_0_2_DESC;
			
		} else if (msgVersion.equals(THREED_MSG_VERSION_210)) {
			msgVersionDesc = PROTOCOL_VERSION_2_1_0_DESC;
			
		} else {
			msgVersionDesc = msgVersion;
		}

		return msgVersionDesc;
	}
	
}
