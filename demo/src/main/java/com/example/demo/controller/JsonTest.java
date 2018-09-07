package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		
		String tokenUrl = "https://mingle-integ02-ionapi.mingle.awsdev.infor.com/INTEGRATION_AX1/IONSERVICES/api/ion/messaging/service/v2/message"; // pu+ot
		String message;

		

		JSONObject json = new JSONObject();
		json.put("documentName", "Sync.IDOCQuantityMessage");
		json.put("messageId", "msg#1234569");
		json.put("fromLogicalId", "lid://infor.ims.ionserviceforln");
		json.put("toLogicalId", "lid://default");
		
		JSONObject item = new JSONObject();
		item.put("value", "PF8tRkZNRVNfLVI+DQogICAgPElET0MgQkVHSU49IjEiPg0KICAgICAgICA8RURJX0RDNDAgU0VHTUVOVD0iMSI+DQogICAgICAgICAgICA8VEFCTkFNPkVESV9EQzQwPC9UQUJOQU0+DQogICAgICAgICAgICA8TUFORFQ+MTAwPC9NQU5EVD4NCiAgICAgICAgICAgIDxET0NOVU0+LTc2ODY1MzMxNjI2NzA4NzgyMzU8L0RPQ05VTT4NCiAgICAgICAgICAgIDxET0NSRUwvPg0KICAgICAgICAgICAgPFNUQVRVUy8+DQogICAgICAgICAgICA8RElSRUNUPjI8L0RJUkVDVD4NCiAgICAgICAgICAgIDxPVVRNT0QvPg0KICAgICAgICAgICAgPEVYUFJTUy8+DQogICAgICAgICAgICA8VEVTVC8+DQogICAgICAgICAgICA8SURPQ1RZUD4vRkZNRVMvUjwvSURPQ1RZUD4NCiAgICAgICAgICAgIDxDSU1UWVAvPg0KICAgICAgICAgICAgPE1FU1RZUD4vRkZNRVMvTUVTU0FHRTwvTUVTVFlQPg0KICAgICAgICAgICAgPE1FU0NPRC8+DQogICAgICAgICAgICA8TUVTRkNULz4NCiAgICAgICAgICAgIDxTVEQvPg0KICAgICAgICAgICAgPFNURFZSUy8+DQogICAgICAgICAgICA8U1RETUVTLz4NCiAgICAgICAgICAgIDxTTkRQT1I+Rk9SQ0FNRkY8L1NORFBPUj4NCiAgICAgICAgICAgIDxTTkRQUlQ+TFM8L1NORFBSVD4NCiAgICAgICAgICAgIDxTTkRQRkMvPg0KICAgICAgICAgICAgPFNORFBSTj5GT1JDQU1GRjwvU05EUFJOPg0KICAgICAgICAgICAgPFNORFNBRC8+DQogICAgICAgICAgICA8U05ETEFELz4NCiAgICAgICAgICAgIDxSQ1ZQT1I+U0FQRk9EPC9SQ1ZQT1I+DQogICAgICAgICAgICA8UkNWUFJUPkxTPC9SQ1ZQUlQ+DQogICAgICAgICAgICA8UkNWUEZDLz4NCiAgICAgICAgICAgIDxSQ1ZQUk4+Rk9EQ0xOVDEwMDwvUkNWUFJOPg0KICAgICAgICAgICAgPFJDVlNBRC8+DQogICAgICAgICAgICA8UkNWTEFELz4NCiAgICAgICAgICAgIDxDUkVEQVQvPg0KICAgICAgICAgICAgPENSRVRJTS8+DQogICAgICAgICAgICA8UkVGSU5ULz4NCiAgICAgICAgICAgIDxSRUZHUlAvPg0KICAgICAgICAgICAgPFJFRk1FUy8+DQogICAgICAgICAgICA8QVJDS0VZLz4NCiAgICAgICAgICAgIDxTRVJJQUw+MDAxMDwvU0VSSUFMPg0KICAgICAgICA8L0VESV9EQzQwPg0KICAgICAgICA8Xy1GRk1FU18tU1JVRUNLIFNFR01FTlQ9IjEiPg0KICAgICAgICAgICAgPFNBUlQ+UVRZTUc8L1NBUlQ+DQogICAgICAgICAgICA8TUFORFQ+QVVESTwvTUFORFQ+DQogICAgICAgICAgICA8QlVLUlM+S09NUEFTUzwvQlVLUlM+DQogICAgICAgICAgICA8V0VSS1M+V0VSS18wMjwvV0VSS1M+DQogICAgICAgICAgICA8U1lTSUQ+aWQxPC9TWVNJRD4NCiAgICAgICAgICAgIDxSVUVDSz5jb25maXJtYXRpb25OdW1iZXI8L1JVRUNLPg0KICAgICAgICAgICAgPEFVRk5SPjFfUFJPMDAwMjA0PC9BVUZOUj4NCiAgICAgICAgICAgIDxWT1JOUj4xMDwvVk9STlI+DQogICAgICAgICAgICA8QVNQTFQ+MCc8L0FTUExUPg0KICAgICAgICAgICAgPEFSQlBMPndwbDwvQVJCUEw+DQogICAgICAgICAgICA8REFUVU0+MDEuMDEuMTk3MDwvREFUVU0+DQogICAgICAgICAgICA8RVpFSVQ+MDUzMDAwPC9FWkVJVD4NCiAgICAgICAgICAgIDxTQ0hJVD4xOTcwLTAxLTAxPC9TQ0hJVD4NCiAgICAgICAgICAgIDxTQ0hJSz5GPC9TQ0hJSz4NCiAgICAgICAgICAgIDxJTlROUj4xMjM0PC9JTlROUj4NCiAgICAgICAgICAgIDxBR1JVTj5zY3JhcENvZGU8L0FHUlVOPg0KICAgICAgICAgICAgPE5HUlVOPnJld29ya0NvZGU8L05HUlVOPg0KICAgICAgICAgICAgPEFVU1RBLz4NCiAgICAgICAgICAgIDxQRVJOUj5wZXJzb25uZWxOdW1iZXI8L1BFUk5SPg0KICAgICAgICAgICAgPExTVEFSLz4NCiAgICAgICAgICAgIDxEQVVFUi8+DQogICAgICAgICAgICA8WkZTTDEvPg0KICAgICAgICAgICAgPExNTkdBPjUuNTwvTE1OR0E+DQogICAgICAgICAgICA8WE1OR0E+Ni41PC9YTU5HQT4NCiAgICAgICAgICAgIDxSTU5HQT43LjU8L1JNTkdBPg0KICAgICAgICAgICAgPE1FSU5IPjU8L01FSU5IPg0KICAgICAgICAgICAgPE1BVE5SPm1hdGVyaWFsTnVtYmVyPC9NQVROUj4NCiAgICAgICAgICAgIDxLT1JJRC8+DQogICAgICAgICAgICA8S09SVFkvPg0KICAgICAgICAgICAgPEtPUkRULz4NCiAgICAgICAgICAgIDxLT1JaVC8+DQogICAgICAgICAgICA8S09SU0EvPg0KICAgICAgICA8L18tRkZNRVNfLVNSVUVDSz4NCiAgICA8L0lET0M+DQo8L18tRkZNRVNfLVI+");
		item.put("encoding", "BASE64");
		item.put("characterSet", "UTF-8");
		
		json.put("document", item);

		message = json.toString();

		System.out.println("message: " + message);
		BufferedReader reader = null;
		

		try {

			HttpsURLConnection connection1 = null;
			URL url1 = new URL(tokenUrl);
			connection1 = (HttpsURLConnection) url1.openConnection();
			connection1.setRequestMethod("POST");
			connection1.setDoOutput(true);
			connection1.setRequestProperty("Authorization", "Bearer IRmgRsPERllLOKQARxiLmzWNdkWY");
			connection1.setRequestProperty("Content-Type", "application/json");
			connection1.setRequestProperty("Accept", "application/json");
		
			PrintStream os1 = new PrintStream(connection1.getOutputStream());
			os1.print(json.toString());
			os1.close();
			reader = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(
					connection1.getContentLength() > 0 ? connection1.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			System.out.println("StringWriter: " + out.toString());
			JSONObject object = new JSONObject(out.toString());
			System.out.println(object.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
