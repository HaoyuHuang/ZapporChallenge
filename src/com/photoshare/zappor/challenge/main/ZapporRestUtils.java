package com.photoshare.zappor.challenge.main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ZapporRestUtils {

	private static final String URL = "http://api.zappos.com/";

	public static final String SEARCH_ACTION = "Search";

	private static final String API_KEY = "52ddafbe3ee659bad97fcce7c53592916a6bfd73";

	public static String request(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		StringBuilder builder = new StringBuilder();
		StringBuilder reqUrl = new StringBuilder();
		reqUrl.append(URL);
		reqUrl.append(url);

		reqUrl.append("&key=");
		reqUrl.append(API_KEY);
		try {
			Logger.getAnonymousLogger().log(Level.INFO, "request: " + reqUrl.toString());
			HttpGet httpGet = new HttpGet(reqUrl.toString());
			response = httpclient.execute(httpGet);
			Logger.getAnonymousLogger().log(Level.INFO,
					response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream input = new BufferedInputStream(entity.getContent());
				int temp;
				while ((temp = input.read()) != -1) {
					builder.append((char) temp);
				}
			}
			Logger.getAnonymousLogger().log(Level.INFO, builder.toString());
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();

	}

	public static JSONObject checkResponse(String response)
			throws RuntimeException {
		try {
			JSONObject jsonObject = new JSONObject(response);
			String status = jsonObject.optString("statusCode");
			if (!"200".equals(status)) {
				throw new RuntimeException(status);
			}
			return jsonObject;
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
