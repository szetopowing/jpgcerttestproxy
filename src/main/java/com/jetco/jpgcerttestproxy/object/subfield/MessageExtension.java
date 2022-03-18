package com.jetco.jpgcerttestproxy.object.subfield;

public class MessageExtension {

	private Boolean criticalityIndicator;		
	private Object data;
	private String id;
	private String name;
	
	public Boolean getCriticalityIndicator() {
		return criticalityIndicator;
	}
	public void setCriticalityIndicator(Boolean criticalityIndicator) {
		this.criticalityIndicator = criticalityIndicator;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
