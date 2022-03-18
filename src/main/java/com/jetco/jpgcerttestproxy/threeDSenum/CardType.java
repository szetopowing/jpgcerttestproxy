package com.jetco.jpgcerttestproxy.threeDSenum;

public enum CardType {
	
	TYPE_VISA(0),
	TYPE_MASTER(1);
	
	public final Integer value;
	
	CardType(Integer value) {
		this.value = value;		
	}

	public Integer getValue() {
		return value;
	}

}
