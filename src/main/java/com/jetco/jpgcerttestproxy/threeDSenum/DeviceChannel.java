package com.jetco.jpgcerttestproxy.threeDSenum;

public enum DeviceChannel {
	
	APP("01"),
	BRW("02"),
	threeRI("03");	
	
	public final String value;
	
	DeviceChannel(String value) {
		this.value = value;		
	}

	public String getValue() {
		return value;
	}
	
}
