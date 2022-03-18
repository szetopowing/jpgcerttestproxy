package com.jetco.jpgcerttestproxy.threeDSenum;

public enum MessageVersion {
	
	V21("2.1.0"),
	v22("2.2.0");
	
	public final String value;
	
	MessageVersion(String value) {
		this.value = value;		
	}

	public String getValue() {
		return value;
	}

}
