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
public class PPrq {

	private String messageType;
	private String p_messageVersion;
	private String messageVersion;
	private String threeDSRequestorID;
	private String threeDSServerTransID;
	private String threeDSRequestorURL;
	private String messageExtension;
	private String p_isSerialNumPresent;
	
	
	
}
