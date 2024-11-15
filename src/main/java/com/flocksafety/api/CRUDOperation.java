package com.flocksafety.api;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.flocksafety.base.TestBase;


public class CRUDOperation extends TestBase{

	
	private static String REST_ENDPOINT = "/services/data" ;
	private static String API_VERSION = "/v61.0" ;
	private static String baseUri;
	private static Header oauthHeader;
	private static Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
	private static String leadId ;
	
			
	
	SOQLqueryBuilder sqlBuild;
	
	
	public CRUDOperation() {
		sqlBuild = new SOQLqueryBuilder();
	}


	/* ***************************************************Main Function***************************************************************/	

	
	public void SFapi(String object, String apiFunction) throws JSONException {

		String USERNAME     =  "newyorktominnesota@playful-otter-1o15gi.com";
		String PASSWORD     = "Capricon25!";
		String LOGINURL     = "https://playful-otter-1o15gi-dev-ed.my.salesforce.com/";
		String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
		String CLIENTID     =  "3MVG9IHf89I1t8hrVI8QIKnHu15HouplzQcUPYAbcC6zmLG0LpiVzX.plp.iwG6zRjl4mBXrtkixoXRMPRr1x";
		String CLIENTSECRET = "0267A32C328F43830019EC480ECBCAFB1C2F1D3DC2FA51196B065036BB5C3320";

		
		HttpClient httpclient = HttpClientBuilder.create().build();

		// Assemble the login request URL
		String loginURL = LOGINURL +
				GRANTSERVICE +
				"&client_id=" + CLIENTID +
				"&client_secret=" + CLIENTSECRET +
				"&username=" + USERNAME +
				"&password=" + PASSWORD;

		// Login requests must be POSTs
		HttpPost httpPost = new HttpPost(loginURL);
		HttpResponse response = null;

		try {
			// Execute the login POST request
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException cpException) {
			cpException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		// verify response is HTTP OK
		final int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			System.out.println("Error authenticating to Force.com: "+statusCode);
			// Error is in EntityUtils.toString(response.getEntity())
			return;
		}

		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		JSONObject jsonObject = null;
		String loginAccessToken = null;
		String loginInstanceUrl = null;

		try {
			jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
			loginAccessToken = jsonObject.getString("access_token");
			loginInstanceUrl = jsonObject.getString("instance_url");
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}

		baseUri = loginInstanceUrl + REST_ENDPOINT + API_VERSION ;
		oauthHeader = new BasicHeader("Authorization", "OAuth " + loginAccessToken) ;
		
		System.out.println("\n" + response.getStatusLine());
		System.out.println("Successful login");
		System.out.println("access token/session ID: "+loginAccessToken);
		
		switch (object) {
		
			case "Lead":
				if 		(apiFunction.equalsIgnoreCase("createLead")) 	{ createRecord("Lead"); }
				else if (apiFunction.equalsIgnoreCase("getLead")) 		{ getRecordFor("Lead","00QKe00000159wmMAA"); }
				else if (apiFunction.equalsIgnoreCase("updateLead")) 	{ updateRecord("Lead","00QKe00000159wmMAA"); }
				else if (apiFunction.equalsIgnoreCase("deleteLead")) 	{ deleteRecord("Lead","00QKe0000015BGZMA2"); }
				break;
			
			case "Opportunity":
				if 		(apiFunction.equalsIgnoreCase("createOpportunity")) { createRecord("Opportunity"); }
				else if (apiFunction.equalsIgnoreCase("getOpportunity"))	{ getRecordFor("Opportunity","0064P00000jOjM7QAK"); }
				else if (apiFunction.equalsIgnoreCase("updateOpportunity"))	{ updateRecord("Opportunity","TestOppSales1728500214"); }
				else if (apiFunction.equalsIgnoreCase("deleteOpportunity"))	{ updateRecord("Opportunity","TestOppSales1728500214"); }
				break;
		}
		
		// release connection
		httpPost.releaseConnection();
	}





	/* *****************************************************Get Function using API ******************************************************/	



	
	// Query Leads using REST HttpGet
	public  void getRecordFor(String object, String Id) {

		//leadId = leadID;

		System.out.println("\n_______________ Record QUERY _______________");
		try {

			//Set up the HTTP objects needed to make the request.
			HttpClient httpClient = HttpClientBuilder.create().build();

			//String uri = baseUri + "/query?q=Select+Id+,+FirstName+,+LastName+,+Company+From+Lead+WHERE+Id+=+'"+leadID+"'";
			String uri = baseUri + sqlBuild.getQueryFor(object, Id);

			//System.out.println("Query URL: " + uri);
			HttpGet httpGet = new HttpGet(uri);
			System.out.println("oauthHeader2: " + oauthHeader+ "\n");
			httpGet.addHeader(oauthHeader);
			httpGet.addHeader(prettyPrintHeader);

			// Make the request.
			HttpResponse response = httpClient.execute(httpGet);

			// Process the result
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				String response_string = EntityUtils.toString(response.getEntity());
				try {
					JSONObject json = new JSONObject(response_string);
					System.out.println("JSON result of Query:\n" + json.toString(1));
				} catch (JSONException je) {
					je.printStackTrace();
				}
			} else {
				System.out.println("Query was unsuccessful. Status code returned is " + statusCode);
				System.out.println("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
				System.exit(-1);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}


	/* *************************************Create functions using API ******************************************************************/		

	
	// Create Leads using REST HttpPost
	
	public  void createRecord(String object) {

		System.out.println("\n_______________ Record INSERT _______________");

		String uri = baseUri + "/sobjects/"+object+"/";
		String objectId = object+"sfdcId";

		try {

			
			sqlBuild.postQueryFor(object);

			System.out.println("JSON for "+object+" record to be inserted:\n" + sqlBuild.apiObject.toString(1));

			
			//Construct the objects needed for the request
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(uri);
			httpPost.addHeader(oauthHeader);
			httpPost.addHeader(prettyPrintHeader);

			// The message we are going to post
			StringEntity body = new StringEntity(sqlBuild.apiObject.toString(1));
			body.setContentType("application/json");
			httpPost.setEntity(body);

			//Make the request
			HttpResponse response = httpClient.execute(httpPost);


			//Process the results
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 201) {
				System.out.println("Status code returned is " + statusCode);
				String response_string = EntityUtils.toString(response.getEntity());
				JSONObject json = new JSONObject(response_string);

				// Store the retrieved lead id to use when we update the lead.
				sfdc.put(objectId, json.getString("id"));
				System.out.println("New "+object+" id from response: "+sfdc.get(objectId));
				
			} else {
				System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
			}
		} catch (JSONException e) {
			System.out.println("Issue creating JSON or processing results");
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}


	/* *************************************Update functions using API ******************************************************************/

	// Update Leads using REST HttpPatch. We have to create the HTTPPatch, as it does not exist in the standard library
	// Since the PATCH method was only recently standardized and is not yet implemented in Apache HttpClient
	
	public void updateRecord(String object, String Id) {
		
		System.out.println("\n_______________ Record UPDATE _______________");

		//Notice, the id for the record to update is part of the URI, not part of the JSON
		String uri = baseUri + "/sobjects/"+object+"/" + Id;
		try {
			
			//Create the JSON object containing the updated record and Id of  we are updating.
			JSONObject jsonObject = new JSONObject();
			
			jsonObject = sqlBuild.updateQueryFor(object);
			System.out.println("JSON for update of "+object+" record:\n" + jsonObject.toString(1));

			//Set up the objects necessary to make the request.
			HttpClient httpClient = HttpClientBuilder.create().build();

			HttpPatch httpPatch = new HttpPatch(uri);
			httpPatch.addHeader(oauthHeader);
			httpPatch.addHeader(prettyPrintHeader);
			StringEntity body = new StringEntity(jsonObject.toString(1));
			body.setContentType("application/json");
			httpPatch.setEntity(body);

			//Make the request
			HttpResponse response = httpClient.execute(httpPatch);

			//Process the response
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 204) {
				System.out.println("Status code returned is " + statusCode);
				System.out.println("Updated the "+object+" successfully.");
			} else {
				System.out.println(object +"update NOT successfully. Status code is " + statusCode);
			}
		} catch (JSONException e) {
			System.out.println("Issue creating JSON or processing results");
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}


	/* *************************************Delete functions using API ******************************************************************/

	// Update Leads using REST HttpDelete (We have to create the HTTPDelete, as it does not exist in the standard library.)
	
	public static void deleteRecord(String object, String Id) throws JSONException {

		System.out.println("\n_______________ Record DELETE _______________");

		//Notice, the id for the record to update is part of the URI, not part of the JSON
		String uri = baseUri + "/sobjects/"+object+"/" + Id;
		
		try {
			//Set up the objects necessary to make the request.
			HttpClient httpClient = HttpClientBuilder.create().build();

			HttpDelete httpDelete = new HttpDelete(uri);
			httpDelete.addHeader(oauthHeader);
			httpDelete.addHeader(prettyPrintHeader);

			//Make the request
			HttpResponse response = httpClient.execute(httpDelete);

			//Process the response
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 204) {
				System.out.println("Status code returned is " + statusCode);
				System.out.println("Deleted "+object+" successfully.");
			} else {
				System.out.println(object+" delete NOT successful. Status code is " + statusCode);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}


	/* *************************************Patch functions using API ******************************************************************/

	// Extend the Apache HttpPost method to implement an HttpPatch
	private static class HttpPatch extends HttpPost {
		public HttpPatch(String uri) {
			super(uri);
		}

		public String getMethod() {
			return "PATCH";
		}
	}



}



