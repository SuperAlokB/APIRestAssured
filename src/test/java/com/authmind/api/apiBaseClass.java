package com.authmind.api;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bsh.Console;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class apiBaseClass {
	
	 public static void main(String args[]) {

		 try {
			 JSONObject jsonObject = new JSONObject();
             //OkHttpClient client = new OkHttpClient().newBuilder().build();
             
             OkHttpClient client = new OkHttpClient.Builder().followRedirects(false).build();
	        
	       // String jsonObject = "{\"email\":\"apiautomation@authmind.com\"}";
	        try {
	            jsonObject.put("email", "apiautomation@authmind.com");
	          
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        
	        MediaType JSON = MediaType.parse("application/json;");
	        // put your json here
	        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
	       
	        Request request = new Request.Builder()
                    .url("https://qatest.authmind.com/api/verify")
                    .post(body)
                    .build();
	        Response response = null;
	        try {
	        	System.out.print("**** Request *****\n :" + request.toString());
	        	
	        	response = client.newCall(request).execute();
	        	String resStr = response.body().string();
	        	
	        	 if(resStr.contains("403")) {
	 	    		System.out.println("**********Not Able to Log in**************  \n " + resStr);
	 	    		 
	 	    	 }
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        
	       
	        /* mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"email\":\"apiautomation@authmind.com\"}");
			
			{
		    "email": "apiautomation@authmind.com"
		    
								
			request = new Request.Builder()
			 .url("https://qatest.authmind.com/api/verify")
			 .post(body)
			 .build();		
			
			System.out.print(request.toString());
			
			Response response = client.newCall(request).execute();
				// assertThat(response.code(), equalTo(200));
		    	String jsonData = response.body().string();			
		    	
		    	if(jsonData.contains("403")) {
		    		System.out.println("**********Not Able to Log in**************  \n " + jsonData);
		    		 
		    	 }else {
		    	
		    	 json = new JSONObject(jsonData);			
			     System.out.println(json.get("token"));
			     mediaType = MediaType.parse("text/plain");
			     newBody = RequestBody.create(mediaType, "{\"password\":\"AuthMind@2022\"}");
		         request = new Request.Builder()
			    		  .url("https://qatest.authmind.com/api/login")
			    		  .method("POST", newBody)
			    		  .addHeader("Authorization", "Bearer " + json.get("token"))
			    		  //.addHeader("Authorization", "Bearer 54e260e2d9aa4b979d067326a25357da")
			    		  .addHeader("Content-Type", "text/plain")
			    		  .build();
			    		response = client.newCall(request).execute();     
			    		
			    		 String  newloginResponse = response.body().string();	
			    		 System.out.println("Response from login :" + newloginResponse );
			    		 
			    		 
			    		// JSONObject jsonObject = new JSONObject(newloginResponse);
			    		// json = new JSONObject(newloginResponse);		
			      	    // System.out.println(json.get("token"));
				         //System.out.println(json.get("email"));
			     
		    	 }
		    	 */
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
	
}
