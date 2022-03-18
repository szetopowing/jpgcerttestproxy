package com.jetco.jpgcerttestproxy.threeDSenum;

public enum MessageType {
	
	AReq("AReq"),
	ARes("ARes"),
	CReq("CReq"),
	CRes("CRes"),
	PReq("PReq"),
	PRes("PRes"),
	RReq("RReq"),
	RRes("RRes"),
	Erro("Erro");

	public final String value;

	MessageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}
