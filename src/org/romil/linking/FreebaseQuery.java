package org.romil.linking;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FreebaseQuery {
	public static void main(String[] args) {
		try {
			System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
			System.setProperty("http.proxyPort", "8080");
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			JSONParser parser = new JSONParser();
			GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
			//url.put("query", "Cee Lo Green");
			url.put("query", "samsung s4");
			//url.put("filter", "(all type:/music/artist created:\"The Lady Killer\")");
			url.put("limit", "10");
			url.put("indent", "true");
			url.put("key", "AIzaSyBrGg4rdw3iBS-Zx8I9R6E8oDM-H7UKYvk");
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
			JSONArray results = (JSONArray)response.get("result");
			for (Object result : results) {
				System.out.println(result);
				System.out.println(JsonPath.read(result,"$.name").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}