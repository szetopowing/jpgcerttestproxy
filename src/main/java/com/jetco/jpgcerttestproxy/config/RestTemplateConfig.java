package com.jetco.jpgcerttestproxy.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.jetco.jpgcerttestproxy.object.Test;
import com.jetco.jpgcerttestproxy.object.response.PRes210;
import com.jetco.jpgcerttestproxy.util.AllTrustX509TrustManager;

import java.security.cert.CertificateException;
//import org.apache.http.conn.ssl.TrustStrategy;
//import org.apache.http.ssl.TrustStrategy;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class RestTemplateConfig {
	
	static final Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);
	
	@Value("${atomWork.p12File}")
	private String atomWorkP12File;

	@Value("${visa.p12File}")
	private String visaP12File;
	
	@Value("${master.p12File}")
	private String masterP12File;
	
	@Value("${testingEnv}")
	private String testingEnv;
	
	@Value("${atomwork.certPwd}")
	private String atomworkCertPwd;

	@Value("${visa.certPwd}")
	private String visaCertPwd;
	
	@Value("${master.certPwd}")
	private String masterCertPwd;
	
	@Bean
	public RestTemplate getRestTemplateForHttps() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

		HttpHost proxy = new HttpHost("172.5.2.110", 8080, "http");
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		
		String p12FileName = "";
		String certPwd = "";
		
	    RestTemplate restTemplate;
		try {			
			if (testingEnv.equals("ATOMWORK")) {
				p12FileName = atomWorkP12File;
				certPwd  = atomworkCertPwd;
			} else if (testingEnv.equals("VISA")) {
				p12FileName = visaP12File;
				certPwd  = visaCertPwd;
			} else if (testingEnv.equals("MASTER")) {
				p12FileName = masterP12File;
				certPwd  = masterCertPwd;
			}
			SSLContext sslContext = createSslFor3DS20(p12FileName, certPwd);
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			CloseableHttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner).setSSLSocketFactory(csf).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setReadTimeout(10 * 1000);
			requestFactory.setConnectTimeout(10 * 1000);
			requestFactory.setHttpClient(httpClient);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			restTemplate = new RestTemplate(requestFactory);
			log.info("restTemplate create successfull");
			 return restTemplate;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("cannot create restTemplate" + e.toString());
		}

	    return null;
	}
	
	private static SSLContext createSslFor3DS20(String p12FileName, String certPwd) throws Exception {

		// Make Connection to DS
		KeyStore clientKeyStore = null;
		KeyStore caKeyStore = null;

		try {

			RestTemplateConfig instance = new RestTemplateConfig();
			File file = instance.getFile(p12FileName);

			System.out.println("In createSslFor3DS20(), get client keystore ....");
			clientKeyStore = KeyStore.getInstance("PKCS12");
			clientKeyStore.load(new FileInputStream(file), certPwd.toCharArray());

			// caKeyStore = KeyStore.getInstance("jks");
			// caKeyStore.load(new FileInputStream(caKeyPath),
			// clientKeyPassword.toCharArray());

		} catch (Exception e) {
			System.out.println("Error occurred when creating instance for pkcs12 keystore: ");
		}

		SSLContext context = null;

		try {
			System.out.println("In createSslFor3DS20(), setup trustManager ....");
			context = SSLContext.getInstance("TLSv1.2");

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientKeyStore, certPwd.toCharArray());

			if (caKeyStore == null) {
				TrustManager[] tm = new TrustManager[] { new AllTrustX509TrustManager() };
				context.init(kmf.getKeyManagers(), tm, null);

			} else {
				TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				tmf.init(caKeyStore);

				context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
			}

		} catch (Exception nsae) {
			System.out.println("Error when getting instance for SSL context: " + nsae);
		}

		return context;
	}
	
	private File getFile(String fileName) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);

		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}
	}

}
