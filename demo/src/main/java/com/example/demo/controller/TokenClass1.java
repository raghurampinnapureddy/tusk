package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class TokenClass1 {

	public static void main(String[] args) {

		String accessToken = getAccesstoken();
		System.out.println("Bearer Token:" + accessToken);
		if (accessToken != null) {
			String tokenUrl1 = "https://mingle-integ02-ionapi.mingle.awsdev.infor.com/INTEGRATION_AX1/IONSERVICES/api/ion/messaging/service/v2/message"; // pu+ot
			String message;

			JSONObject json = new JSONObject();
			json.put("documentName", "Sync.IDOCQuantityMessage");
			json.put("messageId", "msg#1234581");
			json.put("fromLogicalId", "lid://infor.ims.ionserviceforln");
			json.put("toLogicalId", "lid://default");

			JSONObject item = new JSONObject();
			item.put("value",
					"PF8tRkZNRVNfLVI+DQogIDxJRE9DIEJFR0lOPSIxIj4NCiAgICA8RURJX0RDNDAgU0VHTUVOVD0iMSI+DQogICAgICA8VEFCTkFNPkVESV9EQzQwPC9UQUJOQU0+DQogICAgICA8TUFORFQ+MTAwPC9NQU5EVD4NCiAgICAgIDxET0NOVU0+NDIyODU3NTc2MTAzNTIzNTgxNjwvRE9DTlVNPg0KICAgICAgPERPQ1JFTCAvPg0KICAgICAgPFNUQVRVUyAvPg0KICAgICAgPERJUkVDVD4yPC9ESVJFQ1Q+DQogICAgICA8T1VUTU9EIC8+DQogICAgICA8RVhQUlNTIC8+DQogICAgICA8VEVTVCAvPg0KICAgICAgPElET0NUWVA+L0ZGTUVTL1I8L0lET0NUWVA+DQogICAgICA8Q0lNVFlQIC8+DQogICAgICA8TUVTVFlQPi9GRk1FUy9NRVNTQUdFPC9NRVNUWVA+DQogICAgICA8TUVTQ09EIC8+DQogICAgICA8TUVTRkNUIC8+DQogICAgICA8U1REIC8+DQogICAgICA8U1REVlJTIC8+DQogICAgICA8U1RETUVTIC8+DQogICAgICA8U05EUE9SPkZPUkNBTUZGPC9TTkRQT1I+DQogICAgICA8U05EUFJUPkxTPC9TTkRQUlQ+DQogICAgICA8U05EUEZDIC8+DQogICAgICA8U05EUFJOPkZPUkNBTUZGPC9TTkRQUk4+DQogICAgICA8U05EU0FEIC8+DQogICAgICA8U05ETEFEIC8+DQogICAgICA8UkNWUE9SPlNBUEZPRDwvUkNWUE9SPg0KICAgICAgPFJDVlBSVD5MUzwvUkNWUFJUPg0KICAgICAgPFJDVlBGQyAvPg0KICAgICAgPFJDVlBSTj5GT0RDTE5UMTAwPC9SQ1ZQUk4+DQogICAgICA8UkNWU0FEIC8+DQogICAgICA8UkNWTEFEIC8+DQogICAgICA8Q1JFREFUIC8+DQogICAgICA8Q1JFVElNIC8+DQogICAgICA8UkVGSU5UIC8+DQogICAgICA8UkVGR1JQIC8+DQogICAgICA8UkVGTUVTIC8+DQogICAgICA8QVJDS0VZIC8+DQogICAgICA8U0VSSUFMPi9GRk1FUy9NU0c1MDIwMDAzMjU2PC9TRVJJQUw+DQogICAgPC9FRElfREM0MD4NCiAgICA8Xy1GRk1FU18tU1JVRUNLIFNFR01FTlQ9IjEiPg0KICAgICAgPFNBUlQ+T1BFTkQ8L1NBUlQ+DQogICAgICA8TUFORFQ+MTAwPC9NQU5EVD4NCiAgICAgIDxCVUtSUz45MDEwPC9CVUtSUz4NCiAgICAgIDxXRVJLUz45MTMwPC9XRVJLUz4NCiAgICAgIDxTWVNJRCAvPg0KICAgICAgPFJVRUNLPjAwOTA1MDE4NDg8L1JVRUNLPg0KICAgICAgPEFVRk5SPjFfMTI0MDAwMTk0PC9BVUZOUj4NCiAgICAgIDxWT1JOUj4xMDwvVk9STlI+DQogICAgICA8QVNQTFQ+MDwvQVNQTFQ+DQogICAgICA8QVJCUEw+NjU1NDA8L0FSQlBMPg0KICAgICAgPERBVFVNPjIzLjAzLjIwMTg8L0RBVFVNPg0KICAgICAgPEVaRUlUPjA0NDcwMjwvRVpFSVQ+DQogICAgICA8U0NISVQ+MjAxOC0wMy0yMzwvU0NISVQ+DQogICAgICA8U0NISUsgLz4NCiAgICAgIDxJTlROUj4zQThEODA5MzJFNTUxMUU4QzBCREY4QkMwQTEyMTVBMzwvSU5UTlI+DQogICAgICA8QVVTVEE+OTA8L0FVU1RBPg0KICAgICAgPFBFUk5SIC8+DQogICAgICA8TFNUQVIgLz4NCiAgICAgIDxEQVVFUj4xMDA8L0RBVUVSPg0KICAgICAgPFpGU0wxIC8+DQogICAgICA8TE1OR0EgLz4NCiAgICAgIDxYTU5HQSAvPg0KICAgICAgPFJNTkdBIC8+DQogICAgICA8TUVJTkggLz4NCiAgICAgIDxNQVROUiAvPg0KICAgICAgPEtPUklEIC8+DQogICAgICA8S09SVFkgLz4NCiAgICAgIDxLT1JEVCAvPg0KICAgICAgPEtPUlpUIC8+DQogICAgICA8S09SU0EgLz4NCiAgICA8L18tRkZNRVNfLVNSVUVDSz4NCiAgPC9JRE9DPg0KPC9fLUZGTUVTXy1SPg==");
			item.put("encoding", "BASE64");
			item.put("characterSet", "UTF-8");

			json.put("document", item);

			message = json.toString();

			System.out.println("message: " + message);
			BufferedReader reader1 = null;

			try {

				HttpsURLConnection connection1 = null;
				URL url1 = new URL(tokenUrl1);
				connection1 = (HttpsURLConnection) url1.openConnection();
				connection1.setRequestMethod("POST");
				connection1.setDoOutput(true);
				connection1.setRequestProperty("Authorization", "Bearer " + accessToken);
				connection1.setRequestProperty("Content-Type", "application/json");
				connection1.setRequestProperty("Accept", "application/json");

				PrintStream os1 = new PrintStream(connection1.getOutputStream());
				os1.print(json.toString());
				os1.close();
				reader1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
				String line1 = null;
				StringWriter out1 = new StringWriter(
						connection1.getContentLength() > 0 ? connection1.getContentLength() : 2048);
				while ((line1 = reader1.readLine()) != null) {
					out1.append(line1);
				}
				System.out.println("StringWriter: " + out1.toString());
				JSONObject object1 = new JSONObject(out1.toString());
				System.out.println(object1.toString());

			} catch (Exception e) {
				System.out.println("Error : " + e.getMessage());
			}

		}

	}

	public static String getAccesstoken() {

		String clientId = "INTEGRATION_AX1~nkbusYz_bHyaBjd8rNIbWP0YKOT_q0vKCGUNzXdb4-c"; // ci
		String clientSecret = "Zwv9Db2I9_TewAlnYgVD3TyX6zanUW5PkgiYDRe1Z8og4GfN50Jmq6jL0EVPw4JNeGKUWCnK82s7XbvGhH3oAw"; // cs
		String tokenUrl = "https://mingle-integ02-sso.mingle.awsdev.infor.com:443/INTEGRATION_AX1/as/token.oauth2"; // pu+ot
		String userName = "INTEGRATION_AX1#1nXi85x6ScOiDuKqIfLuQc91ciGplAcpe36r782IULvaLfkDXsLHaCYWWSFeSz4UCoObDQP48yfw3ODIisMb2Q"; // saak
		String password = "p3BcpW9PZmJ4TttL39WQ-s_qzWTaWAWx8LJjFNcckN5sGrO8DZwQvUF3jzrc7C_1YLgkfHtA-0km-Y8uxXT-bA"; // sask
		String accessToken = null;
		String refreshToken = null;
		String accessTokenFromRefreshToken = null;

		String auth = clientId + ":" + clientSecret;
		String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
		System.out.println("authentication*******" + authentication);
		String content = "grant_type=password&username=" + userName + "&password=" + password;
		BufferedReader reader = null;
		HttpsURLConnection connection = null;

		try {
			URL url = new URL(tokenUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authentication);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept", "application/json");
			PrintStream os = new PrintStream(connection.getOutputStream());
			os.print(content);
			os.close();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(
					connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			System.out.println("StringWriter: " + out.toString());
			JSONObject object = new JSONObject(out.toString());
			System.out.println(object.toString());
			accessToken = object.getString("access_token");
			refreshToken = object.getString("refresh_token");
			System.out.println("Access Token - " + accessToken);
			System.out.println("Refresh Token - " + refreshToken);
			accessTokenFromRefreshToken = getAccessTokenUsingRefreshToken(clientId, clientSecret, tokenUrl,
					refreshToken);
			System.out.println("accessTokenFromRefreshToken - " + accessTokenFromRefreshToken);

		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}

		return accessTokenFromRefreshToken;

	}

	public static String getAccessTokenUsingRefreshToken(String clientId, String clientSecret, String tokenURL,
			String RefreshToken) {
		try {
			Map<String, Object> params = new LinkedHashMap<>();
			params.put("grant_type", "refresh_token");
			params.put("client_id", clientId);
			params.put("client_secret", clientSecret);
			params.put("refresh_token", RefreshToken);
			params.put("always_issue_new_refresh_token",true);
			params.put("unset_refresh_token_after_use", false);

			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0) {
					postData.append('&');
				}
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			URL url = new URL(tokenURL);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.getOutputStream().write(postDataBytes);

			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				buffer.append(line);
			}
			
			JSONObject json = new JSONObject(buffer.toString());
			System.out.println("buffer:"+buffer.toString());
			String accessToken = json.getString("access_token");
			//String refreshToken =json.getString("refresh_token");
			return accessToken;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
