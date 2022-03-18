package com.jetco.jpgcerttestproxy.threeDSenum;

public enum Eci {
	
	ECI_SECURE_MASTER("02"),
	ECI_NON_PARTICIPATE_OR_AMPT_MASTER("01"),
	ECI_NON_AUTH_MASTER("00"),	
	
	/*
	 *  VISA Cert Test : 3DSS-210-101
	 *  Add "0" to pass validation
	 */
	ECI_SECURE_VISA("05"),
    ECI_NON_PARTICIPATE_OR_AMPT_VISA("06"),
    ECI_NON_AUTH_VISA("07");
	
	public final String value;
	
	Eci(String value) {
		this.value = value;		
	}

	public String getValue() {
		return value;
	}

}
