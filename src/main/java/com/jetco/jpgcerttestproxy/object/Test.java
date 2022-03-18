package com.jetco.jpgcerttestproxy.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

import com.jetco.jpgcerttestproxy.util.AllTrustX509TrustManager;

public class Test {

	public static void main(String[] arg) {

		HttpHost proxy = new HttpHost("172.5.2.110", 8080, "http");
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

		try {

			StringEntity requestEntity = new StringEntity("{\"name\": \"Upendra\", \"job\": \"Programmer\"}",
					ContentType.APPLICATION_JSON);

			SSLContext sslcontext = createSslFor3DS20();

			// Creating SSLConnectionSocketFactory object
			SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(sslcontext,
					new NoopHostnameVerifier());

			// Creating HttpClientBuilder
			HttpClientBuilder clientbuilder = HttpClients.custom().setRoutePlanner(routePlanner);

			// Setting the SSLConnectionSocketFactory
			clientbuilder = clientbuilder.setSSLSocketFactory(sslConSocFactory);

			// Building the CloseableHttpClient
			CloseableHttpClient httpclient = clientbuilder.build();

			// Creating the HttpGet request
			HttpPost httppost = new HttpPost("https://xml-ds.3dstest.com/xmlsimulator/simulation/ds/authentication");
			httppost.setEntity(requestEntity);

			// Executing the request
			HttpResponse httpresponse = httpclient.execute(httppost);

			// printing the status line
			System.out.println("response static line = " + httpresponse.getStatusLine());

			// Retrieving the HttpEntity and displaying the no.of bytes read
			HttpEntity entity = httpresponse.getEntity();
			if (entity != null) {
				System.out.println("response" + EntityUtils.toByteArray(entity).length);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private static SSLContext createSslFor3DS20() throws Exception {

		// Make Connection to DS
		KeyStore clientKeyStore = null;
		KeyStore caKeyStore = null;

		try {

			Test instance = new Test();
			File file = instance.getFile("signed-awcsr.p12");

			System.out.println("In createSslFor3DS20(), get client keystore ....");
			clientKeyStore = KeyStore.getInstance("PKCS12");
			clientKeyStore.load(new FileInputStream(file), "123456".toCharArray());

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
			kmf.init(clientKeyStore, "123456".toCharArray());

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

}
