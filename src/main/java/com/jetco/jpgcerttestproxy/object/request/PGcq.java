package com.jetco.jpgcerttestproxy.object.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PGcq {
	
	private String messageType;
	private String messageVersion;
	private String threeDSServerTransID;
	private String acsTransID;
	private String threeDSSessionData;
	private String challengeWindowSize;
	
	
}
