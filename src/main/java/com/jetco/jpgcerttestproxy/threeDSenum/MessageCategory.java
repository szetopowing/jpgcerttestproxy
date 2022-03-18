package com.jetco.jpgcerttestproxy.threeDSenum;

public enum MessageCategory {

	PA("01"), NPA("02");

	public final String value;

	MessageCategory(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
