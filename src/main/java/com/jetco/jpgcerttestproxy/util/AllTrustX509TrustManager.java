package com.jetco.jpgcerttestproxy.util;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class AllTrustX509TrustManager implements X509TrustManager {
    /**
     * An empty implementation of this method
     *
     * @param chain the peer certificate chain
     * @param authType the authentication type based on the client certificate
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }


    /**
     * An empty implementation of this method
     *
     * @param chain the peer certificate chain
     * @param authType the key exchange algorithm used
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }


    /**
     * Always return empty
     *
     * @return an empty array
     */
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
