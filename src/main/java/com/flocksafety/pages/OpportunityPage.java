package com.flocksafety.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;

public class OpportunityPage extends TestBase {



	WebDriver 		driver;
	Action 			action;
	ContactPage 	contactpage;
	QuotePage 		quotepage;
	
	
	
	//create Opportunity
	 By spanOpportunities 		= By.xpath("//span[contains(@class,'slds-truncate')][@title='Opportunities']");
	 By btnNewOpportunity 		= By.xpath("//button[@name='New']");
	 By radioBtnNewOpptyRecType = By.xpath("(//span[contains(@class,'slds-form-element__label')][text()='Commercial']/../..//span)[1]");
	 By btnNext 				= By.xpath("(//span[contains(@class,'bBody')])[last()]/..");
	 By inputOpttyName 			= By.xpath("//label[text()='Opportunity Name']/..//input");
	 By comboboxOpttyType 		= By.xpath("//label[text()='Type']/..//lightning-base-combobox");
	 By optionOpttyNewBusiness 	= By.xpath("//lightning-base-combobox-item[contains(@class,'listbox__option')]//span[text()='New Business']");
	 By comboboxOpttyStage 		= By.xpath("//label[text()='Stage']/..//lightning-base-combobox");
	 By optionStageAsDiscovery 	= By.xpath("//lightning-base-combobox-item[contains(@class,'listbox__option')]//span[text()='1 - Discovery']");
	 By inputCloseDate 			= By.xpath("//label[text()='Close Date']/..//input");
	 By lblLeadSource 			= By.xpath("//label[text()='Lead Source']");
	 By comboboxLeadSource 		= By.xpath("//label[text()='Lead Source']/..//lightning-base-combobox");
	 By optionWebsite 			= By.xpath("//lightning-base-combobox-item[contains(@data-value,'Website')]");
	 By btnSave 				= By.xpath("//button[@name='SaveEdit']");
	 By btnNew					= By.xpath("//button[@name='New']");
	 By btnNewRelatedList		= By.xpath("//a[@title='New']");
	 
	 
	 By lblStage				= By.xpath("(//span[text()='Stage'])[last()]/../../..//lightning-formatted-text");
	
	 //Opportunity Stage2
	 By lblNeed					= By.xpath("//label[contains(text(),'Need')]");
	 By lblClosedLostDesc		= By.xpath("//span[text()='Closed Lost Description']");
	 By btnUpdateOppty			= By.xpath("//button[contains(text(),'Update')]/..");
	 By lblGetMet				= By.xpath("(//strong[text()='Gate met'])[1]");
	 By spanMarkasCurrentStage	= By.xpath("//span[contains(@class,'uiOutputText')][contains(text(),'Mark')]/..");
	
	 //Opportunity Stage 3
	 By lblContractFullyExecuted= By.xpath("(//span[contains(text(),'Contract Fully')])[1]");
	 By btnUpdateOpptty			= By.xpath("//button[contains(text(),'Update')]/..");
	 
	 //Opportunity Stage 4
	 By comboboxRFPRequired		= By.xpath("//label[text()='RFP Required?']/..//lightning-base-combobox");
	 By optionRFPRequired_Yes	= By.xpath("//lightning-base-combobox-item[contains(@class,'listbox__option')]//span[text()='Yes']");
	 By lblContractSigner		= By.xpath("(//span[text()='Contract Signer'])[last()]");
	 By comboboxKnownObjection	= By.xpath("//label[text()='Known Objection']/..//lightning-base-combobox");
	 By optionNone				= By.xpath("//lightning-base-combobox-item[contains(@class,'listbox__option')]//span[text()='None']");
	 
	 //Opportunity Stage 5
	 By linkContactOnOpptty		= By.xpath("//a[contains(@href,'ContactRoles')]");
	 By btnEditContactRoles		= By.xpath("//a[contains(@title,'Edit Contact Roles')]");
	 By btnContact				= By.xpath("(//button[contains(@class, 'slds-button trigger')])[1]");
	 
	 //Quote
	 By btnNewQuote				= By.xpath("(//button[@name='Opportunity.SR_New_Quote'])[1]");
	 
	 
	
	 
	 
	
	public OpportunityPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		action = 			new Action(driver);
		contactpage = 		new ContactPage(driver);
		quotepage=			new QuotePage(driver);
		
	}
	
	public void createCommercialOpportunity() throws InterruptedException {
		String OpportunityName = "TestOppSales" + rndNum.get("randomId");
		
		action.click(spanOpportunities);
		action.click(btnNewOpportunity);
		action.click(radioBtnNewOpptyRecType);
		action.click(btnNext);
		action.type(inputOpttyName,OpportunityName);
		action.click(comboboxOpttyType);
		action.click(optionOpttyNewBusiness);
		action.click(comboboxOpttyStage);
		action.click(optionStageAsDiscovery);
		action.type(inputCloseDate, "12/30/2024");
		action.scrollByVisibilityOfElement(lblLeadSource);
		action.click(comboboxLeadSource);
		action.click(optionWebsite);
		action.click(btnSave);
		action.waitUntilElementVisible(btnNew);
		Thread.sleep(5000);
		System.out.println("Test Opportunity created Successfully: "+OpportunityName);
	
	}

	public void pickOpportunityFromRelatedList(String objectName) throws InterruptedException {
		Thread.sleep(5000);
		//driver.navigate().to("https://flocksafety--"+env+".sandbox.lightning.force.com/lightning/o/"+objectName+"/list?filterName=__Recent");
		action.gotoRecordUsingRelatedListURL(env.get("Env"),objectName);
		action.waitUntilElementVisible(btnNewRelatedList);
		action.clickRecordinRelatedList();
		action.waitUntilElementVisible(lblStage);
		action.saveURL("Opportunity");
	}

	public void validateOppStage1() throws Exception {
		
	}
	
	public void movingToOppStage2() throws Exception {
		Thread.sleep(2000);
		action.TextArea("Audience", "Testing");
		Thread.sleep(2000);
		action.TextArea("Need", "Testing");
		Thread.sleep(2000);
		action.scrollByVisibilityOfElement(lblClosedLostDesc);
		action.click(btnUpdateOppty);
		action.elementPresent(btnUpdateOppty);
		driver.navigate().to(url.get("Opportunity"));
		action.waitUntilElementVisible(spanMarkasCurrentStage);
		action.JSClick(spanMarkasCurrentStage);
		System.out.println("Successfully moved to Stage 2");
	}
	
	public void movingToOppStage3() throws Exception {
		Thread.sleep(2000);
		action.elementPresent(btnUpdateOppty);
		action.TextArea("Budgets", "Testing");
		action.TextArea("Landscape", "Testing");
		action.TextArea("Risks", "Testing");
		action.scrollByVisibilityOfElement(lblContractFullyExecuted);
		action.click(btnUpdateOpptty);
		action.elementPresent(btnUpdateOppty);
		action.waitUntilElementVisible(lblGetMet);
		driver.navigate().to(url.get("Opportunity"));
		action.waitUntilElementVisible(spanMarkasCurrentStage);
		action.JSClick(spanMarkasCurrentStage);
		System.out.println("Successfully moved to Stage 3");
	}
	
	public void movingToOppStage4() throws Exception {
		Thread.sleep(2000);
		action.scrollByPosition(20);
		Thread.sleep(2000);
		action.click(comboboxRFPRequired);
		Thread.sleep(2000);
		action.click(optionRFPRequired_Yes);
		action.JSClick(optionRFPRequired_Yes);
		action.TextArea("Legal", "Testing");
		action.TextArea("Signature", "Testing");
		action.scrollByVisibilityOfElement(lblContractSigner);
		action.click(comboboxKnownObjection);
		action.click(optionNone);
		action.click(btnUpdateOpptty);
		action.elementPresent(btnUpdateOppty);
		action.waitUntilElementVisible(lblGetMet);
		driver.navigate().to(url.get("Opportunity"));
		action.waitUntilElementVisible(spanMarkasCurrentStage);
		action.JSClick(spanMarkasCurrentStage);
		System.out.println("Successfully moved to Stage 4");
		Thread.sleep(5000);
		
	}
	
	public void movingToOppStage5() throws Exception {
		Thread.sleep(5000);
		action.click(linkContactOnOpptty);
		action.click(btnEditContactRoles);
		action.scrollByVisibilityOfElement(btnContact);
		for (int i = 0; i < 5; i++) {contactpage.addContactRows();}
		contactpage.addMultipleContactRows();
		driver.navigate().to(url.get("Opportunity"));
		action.waitUntilElementVisible(spanMarkasCurrentStage);
		action.JSClick(spanMarkasCurrentStage);
		System.out.println("Successfully moved to Stage 5");
		Thread.sleep(5000);
		quotepage.createNewQuote();
		quotepage.editQuote(null);
	
	}
	
	
	
	
}
