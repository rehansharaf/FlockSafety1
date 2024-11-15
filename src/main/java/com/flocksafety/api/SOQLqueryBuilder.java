package com.flocksafety.api;

import org.json.JSONException;
import org.json.JSONObject;


public class SOQLqueryBuilder {

	private  String read, post;
	public JSONObject apiObject= new JSONObject();	

	public SOQLqueryBuilder() {
		
	}
	
	public String getQueryFor(String queryFor, String sfdcId)   {

		if (queryFor.equalsIgnoreCase("Lead")) {
			//read = "/query?q=Select+Id+,+FirstName+,+LastName+,+Company+From+Lead+WHERE+Id+=+'"+sfdcId+"'";
			read = "/query?q=Select+Id+,+Name+From+"+queryFor+"+WHERE+Id+=+'"+sfdcId+"'";
		}
		else if (queryFor.equalsIgnoreCase("Opportunity")) {
			//read = "/query?q=Select+Id+,+Name+From+"+queryFor+"+WHERE+Name+=+'"+sfdcId+"'";
			read = "/query?q=Select+Id+,+Name+From+"+queryFor+"+WHERE+Id+=+'"+sfdcId+"'";
		}
		else if (queryFor.equalsIgnoreCase("Case")) {
			read = "";
		}
		else if (queryFor.equalsIgnoreCase("Opportunity")) {
			read = "";
		}
		else if (queryFor.equalsIgnoreCase("Contact")) {
			read = "";
		}

		System.out.println(read);
		return read;
	}


	
	public void postQueryFor(String queryFor) throws JSONException   {
		
		if (queryFor.equalsIgnoreCase("Lead")) {
			
			apiObject.put("FirstName", "Adamupdate");
			apiObject.put("LastName", "Noriupdate");
			apiObject.put("Company", "FullTest.com");
			
		}
		else if (queryFor.equalsIgnoreCase("Account")) {
			
		}
		else if (queryFor.equalsIgnoreCase("Case")) {
			
		}
		else if (queryFor.equalsIgnoreCase("Opportunity")) {
			
		}
		else if (queryFor.equalsIgnoreCase("Contact")) {
			
		}
		
	}

	
	public  JSONObject updateQueryFor(String queryFor) throws JSONException   {
		
		if (queryFor.equalsIgnoreCase("Lead")) {
			
			apiObject.put("FirstName", "Adam_Update");
			apiObject.put("LastName", "Nori_Update");
			apiObject.put("Company", "FullTest_Update.com");
		}
		else if (queryFor.equalsIgnoreCase("Account")) {
			
		}
		else if (queryFor.equalsIgnoreCase("Case")) {
			
		}
		else if (queryFor.equalsIgnoreCase("Opportunity")) {
			
		}
		else if (queryFor.equalsIgnoreCase("Contact")) {
			
		}

		return apiObject;
	}




}
