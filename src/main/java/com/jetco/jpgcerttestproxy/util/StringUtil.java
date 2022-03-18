package com.jetco.jpgcerttestproxy.util;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;

public class StringUtil {
	
	static final Logger log = LoggerFactory.getLogger(StringUtil.class);

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public static boolean isDateOfFormat(String dateStr, String datefmt) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(datefmt);
			df.parse(dateStr);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	public static <T> boolean isValidJsonObject(Class<T> targetClass, String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.readValue(jsonString, targetClass);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isValidHTTPURL(String urlStr) {
		try {
			URL url = new URL(urlStr);
			return (url.getProtocol().equalsIgnoreCase("http") || url.getProtocol().equalsIgnoreCase("https"));
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValidHTTPSURL(String urlStr) {
		try {
			URL url = new URL(urlStr);
			return url.getProtocol().equalsIgnoreCase("https");
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValidUUID(String uuid) {
		if (StringUtils.isEmpty(uuid))
			return false;

		if (uuid.length() != 36) {
			return false;
		}

		try {
			UUID.fromString(uuid);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	// public static boolean isBase64UrlDecode(String str) {
	public static boolean isValidJWS(String str) { // merge to production -- start

		try {
			JWSObject jwsObject = JWSObject.parse(str);
			return true;
		} catch (Exception e) {
			log.error("Cannot parse JWS " + str);
			return false;
		}
	}

}
