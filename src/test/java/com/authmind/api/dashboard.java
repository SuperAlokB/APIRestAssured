package com.authmind.api;

import java.util.ArrayList;

import org.testng.Assert;

import bsh.Console;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static io.restassured.RestAssured.*;

public class dashboard {
	
	
	//For login there are two api calls -
	//1.Verify / 2.Login
	
	final static String BaseUrl = "https://qatest.authmind.com/";
	final static String loginUrl =  BaseUrl + "api/verify";
	   
	 public static void main(String args[]) {

	    // getResponseBody();
	     //getResponseStatus();
	     
	 RestAssured.baseURI = "https://qatest.authmind.com/api/verify";
	 RequestSpecification httpRequest = RestAssured.given().queryParam("mediaType", "{\"email\":\"apiautomation@authmind.com\"}");
	
	 Response response = httpRequest.post("token");
 	// Retrieve the body of the Response
 	 ResponseBody body = response.getBody();

	 	// To check for sub string presence get the Response body as a String.
	 	// Do a String.contains
	 	String bodyAsString = body.asString();
	 	Assert.assertEquals(bodyAsString.contains("token") /*Expected value*/, true /*Actual Value*/, "Response body contains token");

	 }
	   
	   public static void getResponseBody(){
	      String token =  given().queryParam("email","alok@authmind.com").when().get(loginUrl).getContentType();
	      System.out.print(token);
	    //   given().queryParam("email","alok@authmind.com").when().then().log().body();

	 // given().queryParam("email","alok@authmind.com")
	 //            .when().get(BaseUrl + "api/login").then().log().body();
	   }

		}


