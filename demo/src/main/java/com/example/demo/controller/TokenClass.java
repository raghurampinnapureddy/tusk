package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;


public class TokenClass {

	static String clientId = "INTEGRATION_AX1~nkbusYz_bHyaBjd8rNIbWP0YKOT_q0vKCGUNzXdb4-c"; // ci
	static String clientSecret = "Zwv9Db2I9_TewAlnYgVD3TyX6zanUW5PkgiYDRe1Z8og4GfN50Jmq6jL0EVPw4JNeGKUWCnK82s7XbvGhH3oAw"; // cs
	static String tokenUrl = "https://mingle-integ02-sso.mingle.awsdev.infor.com:443/INTEGRATION_AX1/as/token.oauth2"; // pu+ot
	static String userName = "INTEGRATION_AX1#1nXi85x6ScOiDuKqIfLuQc91ciGplAcpe36r782IULvaLfkDXsLHaCYWWSFeSz4UCoObDQP48yfw3ODIisMb2Q"; // saak
	static String password = "p3BcpW9PZmJ4TttL39WQ-s_qzWTaWAWx8LJjFNcckN5sGrO8DZwQvUF3jzrc7C_1YLgkfHtA-0km-Y8uxXT-bA"; // sask
              public static String accessToken;
              public static void main(String[] args) {
                             String auth = clientId + ":" + clientSecret;
                      String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
                      System.out.println("authentication*******"+authentication);
                      String content = "grant_type=password&username=" + userName + "&password=" + password;
                      BufferedReader reader = null;
                      HttpsURLConnection connection = null;

                      try {
                          URL url = new URL(tokenUrl);
                          connection = (HttpsURLConnection) url.openConnection();
                          connection.setRequestMethod("POST");
                          connection.setDoOutput(true);
                          connection.setRequestProperty("Authorization", "Basic "+authentication);
                          connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                          connection.setRequestProperty("Accept", "application/json");
                          PrintStream os = new PrintStream(connection.getOutputStream());
                          os.print(content);
                          os.close();
                          reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                          String line = null;
                          StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
                          while ((line = reader.readLine()) != null) {
                              out.append(line);
                          }
                          System.out.println("StringWriter: "+ out.toString());
                          JSONObject object = new JSONObject(out.toString());
                          System.out.println(object.toString());
                          accessToken = object.getString("access_token");
                          System.out.println("Access Token - "+accessToken);
                          
                          CloseableHttpClient client = HttpClients.createDefault();
                          HttpPost httpPost = new HttpPost("https://mingle-integ02-ionapi.mingle.awsdev.infor.com/INTEGRATION_AX1/IONSERVICES/api/ion/messaging/service/v2/message");
                          
                        JSONObject item = new JSONObject();
                  		item.put("value", "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxfLUZGTUVTXy1SPg0KICAgIDxJRE9DIEJFR0lOPSIxIj4NCiAgICAgICAgPEVESV9EQzQwIFNFR01FTlQ9IjEiPg0KICAgICAgICAgICAgPFRBQk5BTT5FRElfREM0MDwvVEFCTkFNPg0KICAgICAgICAgICAgPE1BTkRUPjEwMDwvTUFORFQ+DQogICAgICAgICAgICA8RE9DTlVNPi03Njg2NTMzMTYyNjcwODc4MjM1PC9ET0NOVU0+DQogICAgICAgICAgICA8RE9DUkVMLz4NCiAgICAgICAgICAgIDxTVEFUVVMvPg0KICAgICAgICAgICAgPERJUkVDVD4yPC9ESVJFQ1Q+DQogICAgICAgICAgICA8T1VUTU9ELz4NCiAgICAgICAgICAgIDxFWFBSU1MvPg0KICAgICAgICAgICAgPFRFU1QvPg0KICAgICAgICAgICAgPElET0NUWVA+L0ZGTUVTL1I8L0lET0NUWVA+DQogICAgICAgICAgICA8Q0lNVFlQLz4NCiAgICAgICAgICAgIDxNRVNUWVA+L0ZGTUVTL01FU1NBR0U8L01FU1RZUD4NCiAgICAgICAgICAgIDxNRVNDT0QvPg0KICAgICAgICAgICAgPE1FU0ZDVC8+DQogICAgICAgICAgICA8U1RELz4NCiAgICAgICAgICAgIDxTVERWUlMvPg0KICAgICAgICAgICAgPFNURE1FUy8+DQogICAgICAgICAgICA8U05EUE9SPkZPUkNBTUZGPC9TTkRQT1I+DQogICAgICAgICAgICA8U05EUFJUPkxTPC9TTkRQUlQ+DQogICAgICAgICAgICA8U05EUEZDLz4NCiAgICAgICAgICAgIDxTTkRQUk4+Rk9SQ0FNRkY8L1NORFBSTj4NCiAgICAgICAgICAgIDxTTkRTQUQvPg0KICAgICAgICAgICAgPFNORExBRC8+DQogICAgICAgICAgICA8UkNWUE9SPlNBUEZPRDwvUkNWUE9SPg0KICAgICAgICAgICAgPFJDVlBSVD5MUzwvUkNWUFJUPg0KICAgICAgICAgICAgPFJDVlBGQy8+DQogICAgICAgICAgICA8UkNWUFJOPkZPRENMTlQxMDA8L1JDVlBSTj4NCiAgICAgICAgICAgIDxSQ1ZTQUQvPg0KICAgICAgICAgICAgPFJDVkxBRC8+DQogICAgICAgICAgICA8Q1JFREFULz4NCiAgICAgICAgICAgIDxDUkVUSU0vPg0KICAgICAgICAgICAgPFJFRklOVC8+DQogICAgICAgICAgICA8UkVGR1JQLz4NCiAgICAgICAgICAgIDxSRUZNRVMvPg0KICAgICAgICAgICAgPEFSQ0tFWS8+DQogICAgICAgICAgICA8U0VSSUFMPjAwMTA8L1NFUklBTD4NCiAgICAgICAgPC9FRElfREM0MD4NCiAgICAgICAgPF8tRkZNRVNfLVNSVUVDSyBTRUdNRU5UPSIxIj4NCiAgICAgICAgICAgIDxTQVJUPlFUWU1HPC9TQVJUPg0KICAgICAgICAgICAgPE1BTkRUPkFVREk8L01BTkRUPg0KICAgICAgICAgICAgPEJVS1JTPktPTVBBU1M8L0JVS1JTPg0KICAgICAgICAgICAgPFdFUktTPldFUktfMDI8L1dFUktTPg0KICAgICAgICAgICAgPFNZU0lEPmlkMTwvU1lTSUQ+DQogICAgICAgICAgICA8UlVFQ0s+Y29uZmlybWF0aW9uTnVtYmVyPC9SVUVDSz4NCiAgICAgICAgICAgIDxBVUZOUj4xX1BSTzAwMDE5ODwvQVVGTlI+DQogICAgICAgICAgICA8Vk9STlI+MTA8L1ZPUk5SPg0KICAgICAgICAgICAgPEFTUExUPjAnPC9BU1BMVD4NCiAgICAgICAgICAgIDxBUkJQTD53cGw8L0FSQlBMPg0KICAgICAgICAgICAgPERBVFVNPjAxLjAxLjE5NzA8L0RBVFVNPg0KICAgICAgICAgICAgPEVaRUlUPjA1MzAwMDwvRVpFSVQ+DQogICAgICAgICAgICA8U0NISVQ+MTk3MC0wMS0wMTwvU0NISVQ+DQogICAgICAgICAgICA8U0NISUs+RjwvU0NISUs+DQogICAgICAgICAgICA8SU5UTlI+MTIzNDwvSU5UTlI+DQogICAgICAgICAgICA8QUdSVU4+c2NyYXBDb2RlPC9BR1JVTj4NCiAgICAgICAgICAgIDxOR1JVTj5yZXdvcmtDb2RlPC9OR1JVTj4NCiAgICAgICAgICAgIDxBVVNUQS8+DQogICAgICAgICAgICA8UEVSTlI+cGVyc29ubmVsTnVtYmVyPC9QRVJOUj4NCiAgICAgICAgICAgIDxMU1RBUi8+DQogICAgICAgICAgICA8REFVRVIvPg0KICAgICAgICAgICAgPFpGU0wxLz4NCiAgICAgICAgICAgIDxMTU5HQT41LjU8L0xNTkdBPg0KICAgICAgICAgICAgPFhNTkdBPjYuNTwvWE1OR0E+DQogICAgICAgICAgICA8Uk1OR0E+Ny41PC9STU5HQT4NCiAgICAgICAgICAgIDxNRUlOSD4xPC9NRUlOSD4NCiAgICAgICAgICAgIDxNQVROUj5tYXRlcmlhbE51bWJlcjwvTUFUTlI+DQogICAgICAgICAgICA8S09SSUQvPg0KICAgICAgICAgICAgPEtPUlRZLz4NCiAgICAgICAgICAgIDxLT1JEVC8+DQogICAgICAgICAgICA8S09SWlQvPg0KICAgICAgICAgICAgPEtPUlNBLz4NCiAgICAgICAgPC9fLUZGTUVTXy1TUlVFQ0s+DQogICAgPC9JRE9DPg0KPC9fLUZGTUVTXy1SPg==");
                  		item.put("encoding", "BASE64");
                  		item.put("characterSet", "UTF-8");		
                  		
                  		JSONObject json = new JSONObject();
                  		json.put("documentName", "Sync.IDOCQuantityMessage");
                  		json.put("messageId", "msg#1234655");
                  		json.put("fromLogicalId", "lid://infor.ims.ionserviceforln");
                  		json.put("toLogicalId", "lid://default");
                  		json.put("document", item);              		
                  		
                  		System.out.println(json.toString());
                       
                        
                          StringEntity entity = new StringEntity(json.toString());
                          httpPost.setEntity(entity);
                          httpPost.setHeader("Accept", "application/json");
                          httpPost.setHeader("Content-Type", "application/json");
                          httpPost.setHeader("Authorization", "Bearer "+accessToken);
                       
                          CloseableHttpResponse response = client.execute(httpPost);
                          System.out.println("response:"+response.getStatusLine().getStatusCode());
                          //assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
                          client.close();


                      } catch (Exception e) {
                          System.out.println("Error : " + e.getMessage());
                      }


              }

}

