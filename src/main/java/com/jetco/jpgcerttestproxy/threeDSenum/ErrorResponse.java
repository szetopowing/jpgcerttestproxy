package com.jetco.jpgcerttestproxy.threeDSenum;

public enum ErrorResponse {
	
	ErrorCode101("101", "Message Received Invalid"),
	ErrorCode102("102", "Message Version Number Not Supported"),
	ErrorCode103("103", "Sent Messages Limit Exceeded"),	
	ErrorCode201("201", "Required Data Element Missing"),
	ErrorCode202("202", "Critical Message Extension Not Recognised"),
	ErrorCode203("203", "Format of one or more Data Elements is Invalid according to the Specification"),
	ErrorCode204("204", "Duplicate Data Element"),
	ErrorCode301("301", "Transaction ID Not Recognised"),
	ErrorCode302("302", "Data Decryption Failure"),
	ErrorCode303("303", "Access Denied, Invalid Endpoint"),
	ErrorCode304("304", "ISO Code Invalid"),
	ErrorCode305("305", "Transaction data not valid"),
	ErrorCode306("306", "Merchant Category Code (MCC) Not Valid for Payment System"),
	ErrorCode307("307", "Serial Number not Valid"),
	ErrorCode402("402", "Transaction Timed Out"),
	ErrorCode403("403", "Transient System Failure"),
	ErrorCode404("404", "Permanent System Failure"),
	ErrorCode405("405", "System Connection Failure");

	public final String errorCode;
    public final String errorDesc;

    
	ErrorResponse(String errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public String getErrorDesc() {
		return errorDesc;
	}
	
	public static String getErrorDescByCode(String errorCode) {
		for (ErrorResponse b:ErrorResponse.values()) {
			if (b.errorCode.equalsIgnoreCase(errorCode)) {
                return b.errorDesc;
            }
        }
        return null;
	}
	
	public static void main (String [] args) {
		System.out.println(getErrorDescByCode("405"));
	}

}

