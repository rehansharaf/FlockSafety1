package com.flocksafety.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;

public class AccountPage extends TestBase {

	WebDriver driver;
	Action action;
	
	//public String acctId;
	
	 By headingReleatedlist = By.xpath("//h2[contains(text(),'Related List')]");
	 By spanOpportunities = By.xpath("//span[contains(@class,'slds-truncate')][@title='Opportunities']");
	
	
	
	
	
	public AccountPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);
		
	}
	
	
	public void openExistingAccountRecord(String object, String acctId) throws InterruptedException {
		//driver.navigate().to("https://flocksafety--uat.sandbox.lightning.force.com/lightning/r/Account/"+acctId+"/view");
		action.gotoRecordURL(env.get("Env"), object, acctId);
		action.scrollByVisibilityOfElement(headingReleatedlist);
		action.waitUntilElementVisible(spanOpportunities);
	}
	
	
	
	
	
	
	
	
}
