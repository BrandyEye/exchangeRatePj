package com.guisi.exchangeRate.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class ExchangeRateHandler {

	public static String dailyForeignExchangeRates_API() {
		String rsJson ="";
		HttpURLConnection con = null;
		BufferedReader br = null;
		InputStreamReader is = null;

		try {
			// openData的URL
			String DailyForeignExchangeRates_Url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";

			URL url = new URL(DailyForeignExchangeRates_Url);

			con = (HttpURLConnection) url.openConnection();

			// GET方法
			con.setRequestMethod("GET");
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			con.setRequestProperty("Accept", "application/json");

			con.connect();

			int rsCode = con.getResponseCode();

			if(rsCode == 200) {
				// 讀取數據
				is = new InputStreamReader(con.getInputStream());
				br = new BufferedReader(is);
				String inputLine;
				StringBuilder rs = new StringBuilder();

				while ((inputLine = br.readLine()) != null) {
					rs.append(inputLine);
				}

				rsJson = rs.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(is);
		}
		return rsJson;

	}
}
