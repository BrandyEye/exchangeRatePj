package com.guisi.exchangeRate.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.guisi.exchangeRate.entities.ExchangeRate;


public class ExchangeRateHandler {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 連線至opendata取得匯率資料 
	 * @return json匯率資料
	 */
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
	
	
	public static String dailyForeignExchangeRates_API_sp() {
		String apiUrl = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
		String searchUrl = new StringBuffer(apiUrl).toString();
		RestTemplate rses  = new RestTemplate();
		ResponseEntity<String> resp = rses.getForEntity(searchUrl, String.class);
//		List<CmsActBatchRemitBo> ansList = Arrays.asList(resp);
		String str  = resp.getBody();
		List<ExchangeRate> exChangeList = new ArrayList<>();
//		JsonObject jsonObject = JsonParser.parseString(str).getAsJsonObject();
		JsonArray jsonArrayObject = JsonParser.parseString(str).getAsJsonArray();
		jsonArrayObject.asList().forEach((obj)->{
			JsonObject jb = obj.getAsJsonObject();
			ExchangeRate rate = new ExchangeRate();
            BigDecimal USD_NTD = jb.get("USD/NTD").getAsBigDecimal();
            BigDecimal USD_RMB = jb.get("USD/RMB").getAsBigDecimal();
            BigDecimal RMB_NTD = jb.get("RMB/NTD").getAsBigDecimal();
            String date = jb.get("Date").getAsString();
            rate.setDate(date);
            rate.setRMB_NTD(RMB_NTD);
            rate.setUSD_NTD(USD_NTD);
            rate.setUSD_RMB(USD_RMB);
            exChangeList.add(rate);

		});
        System.out.println(exChangeList);
		return apiUrl;
	
	}
	
	public static void main(String[] args) {
		dailyForeignExchangeRates_API_sp();
	}
}
