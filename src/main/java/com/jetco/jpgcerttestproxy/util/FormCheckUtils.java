package com.jetco.jpgcerttestproxy.util;

public class FormCheckUtils {
	
	 /**
     * Test and see if the input string is a valid IPv4 address
     *
     * @param str  The string to be tested
     * @return  whether the input string is a valid IPv4 address
     */
    public static boolean isValidIPAddress(String str) {
        if (str == null) 
            return false;

        if (!str.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$"))
            return false;

        String[] parts = str.split("\\.");
        for (int i = 0; i < parts.length; ++i) {
            int value = Integer.parseInt(parts[i]);
            if ((value < 0)  || (value > 255)) 
                return false;
        }

        return true;
    }

    /**
     * Test and see if the input string is a valid IPv6 address only
     *
     * @param str  The string to be tested
     * @return  whether the input string is a valid IPv6 address only
     */
    public static boolean isValidIPv6AddressOnly(String str) {
    	if (str == null) 
    		return false;

 		if (!str.matches("([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}")) 
 		 	return false;
 		else 
 			return true;
    }
    
    /**
     * Test and see if the input string is a valid IPv4 and IPv6 address
     *
     * @param str  The string to be tested
     * @return  whether the input string is a valid IPv4 and IPv6 address
     */
    public static boolean isValidIPv4AndIPv6Address(String str) {
    	if (str == null) 
    		return false;

 		return (isValidIPAddress(str) || isValidIPv6AddressOnly(str)); 
    }

}
