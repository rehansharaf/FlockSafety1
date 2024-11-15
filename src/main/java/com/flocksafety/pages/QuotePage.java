package com.flocksafety.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;

	public class QuotePage extends TestBase{


	WebDriver driver;
	Action action;
	
	
	//Quote Page
	By headingNewQuote 				= By.xpath("//h2[text()='New Quote']");
	By checkboxPrimary 				= By.xpath("(//span[text()='Primary'])[last()]/../..//input");
	By btnSave 						= By.xpath("//button[contains(@class,'button_brand')]//span[text()='Save']");
	By btnNewQuote 					= By.xpath("(//button[@name='Opportunity.SR_New_Quote'])[1]");
	By quoteRelatedlist 			= By.xpath("//th[@data-label='Quote Number']//a[contains(@href,'lightning')]");
	By btnEditLines 				= By.xpath("//li[contains(@data-target-selection-name,'EditLines')]//button");
	By quoteIframe					= By.xpath("//div[@class='iframe-parent slds-template_iframe slds-card']/iframe[contains(@name,'vfFrameId')]");
	
	//Edit Quote ShadowDOM elements
	By shadowrootDOM1				= By.tagName("sb-page-container");
	By shadowrootDOM2 				= By.cssSelector("#content > sb-line-editor");
	By shadowrootDOM3 				= By.cssSelector("#actions > sb-custom-action:nth-child(3)");
	By btnMain 						= By.cssSelector("#mainButton");
	
	//Add Products
	By shadowrootDOM1_Product 		= By.tagName("sbPageContainer");
	By shadowrootDOM2_Product 		= By.cssSelector("#content > sb-product-lookup");
	By shadowrootDOM3_Product 		= By.cssSelector("#lookupLayout");
	By shadowrootDOM4_Product		= By.cssSelector("#tableRow");
	By btnArrow 					= By.cssSelector("#row");
	
	//LPR Products Drop down option
	By dropdownLPRProduct 			= By.cssSelector("#field");
	
	//Check box Flock Safety Falcon
	By selection					= By.cssSelector("#selection");
	By checkboxTableCell			= By.cssSelector("#g > div > sb-table-cell-select");
	By checkboxTable				= By.cssSelector("#checkbox");
	
	//Select Button
	By btnSelect					= By.cssSelector("#plSelect");
	
	//wait till Opportunity page appears
	By navOptty					= By.xpath("//a[contains(@href,'Opportunity') and @data-navigation='enable']");
	By linkShowAll				= By.xpath("//a[contains(text(),'Show All')]");
	By labelOpportunity			= By.xpath("//records-entity-label[text()='Opportunity']");
	By tabRelated				= By.xpath("//a[@data-label='Related']");
	
	
	//**************Select Check Box****************
	
	By listRowId				= By.cssSelector("sb-table-row[id='row']");
	By optionCell				= By.cssSelector("#g > div > sb-option-cell.innerTd.primary.first.firstInnerTd.initial");
	By selectMe					= By.cssSelector("#me");
	By checkboxContainer		= By.cssSelector("#checkboxContainer");
	
	
	//**************RequiredElement****************
	By selectList				= By.cssSelector("#list");
	By optionFirstInnerId		= By.cssSelector("#g > div > sb-table-cell.innerTd.primary.first.firstInnerTd");
	By spanMe					= By.cssSelector("span[id='me']");
	
	
	
	
	public QuotePage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);
	
	}
	
	
	public void addProducts() throws InterruptedException {
		action.launchUrl("https://flocksafety--uat.sandbox.lightning.force.com/lightning/r/SBQQ__Quote__c/aADOy00000156gvOAA/view");
		
		action.elementPresent(btnEditLines);
		action.click(btnEditLines);
		
		//Crawling through ShadoDOM elements
		int cond = 0;
		while(cond == 0) {
			try {driver.switchTo().frame(driver.findElement(quoteIframe));break;}
			catch(Exception e) {Thread.sleep(1000);}
		}
		
		cond = 0;
		WebElement root1 = null;
		while(cond == 0) {
			try {root1 = driver.findElement(shadowrootDOM1); break;}
			catch(Exception e) {Thread.sleep(1000);}
		}
		
		
		SearchContext shadow_root1 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root1);
		
		Thread.sleep(3000);
		WebElement root2 = shadow_root1.findElement(shadowrootDOM2);
		SearchContext shadow_root2 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root2);
		
		WebElement root3 = shadow_root2.findElement(shadowrootDOM3);
		SearchContext shadow_root3 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root3);
		
		shadow_root3.findElement(btnMain).click();
		
		//Click Flock Safety LPR Products Dropdown option
		SearchContext ele = getRequiredElement("Flock Safety LPR Products","sb-table-cell.innerTd.primary.first.firstInnerTd");
		ele.findElement(dropdownLPRProduct).click();
		
		//Click Checkbox Flock Safety Falcon
		ele = getRequiredElement("Flock Safety Falcon","tableRow");
		WebElement root8 = ele.findElement(selection);
		SearchContext shadow_root8 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root8);
		
		WebElement root9 = shadow_root8.findElement(checkboxTableCell);
		SearchContext shadow_root9 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root9);
		shadow_root9.findElement(checkboxTable).click();
		
		//Click Select Button
		WebElement root = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector(\"#sbPageContainer\")."
		+ "shadowRoot.querySelector(\"#content > sb-product-lookup\")");  
		SearchContext shadow_root = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root);
		shadow_root.findElement(btnSelect).click();
		
		//Click Check box Professional Services - Standard Implementation Fee
		selectCheckBox("Professional Services - Standard Implementation Fee");
		cond = 0;
		while(cond == 0) {
		
			try {
			root = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector(\"#sbPageContainer\")."
			+ "shadowRoot.querySelector(\"#content > sb-line-editor\").shadowRoot.querySelector(\"#actions > sb-custom-action:nth-child(15)\")."
			+ "shadowRoot.querySelector(\"#mainButton\")");
			
			root.click();break;}
			catch(Exception e) {Thread.sleep(1000);}
		}
		
		driver.switchTo().defaultContent();
		cond = 0;
		while(cond == 0) {
			try {
				//action.explicitWaitElementClickable(linkShowAll);
				action.explicitWaitElementClickable(tabRelated);
			break;}
			catch(StaleElementReferenceException ste) {Thread.sleep(2000);}
		}
		
		action.JSClick(navOptty);
		//action.explicitWaitElementClickable(linkShowAll);
		//action.elementPresent(labelOpportunity);
		//action.scrollByVisibilityOfElement(By.xpath("//h2[contains(@id,'rlql-headerLabel')]"));
		
	}
	
		
	public SearchContext getRequiredElement(String reqText, String needElement) throws InterruptedException {
		
		
		driver.switchTo().defaultContent();
		int cond = 0;
		while(cond == 0) {
			try {driver.switchTo().frame(driver.findElement(quoteIframe));break;}
			catch(Exception e) {Thread.sleep(1000);}
		}
		
		cond = 0;
		WebElement root1 = null;
		while(cond == 0) {
			try {root1 = driver.findElement(shadowrootDOM1); break;}
			catch(Exception e) {Thread.sleep(1000);}
		}
		
		SearchContext shadow_root1 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root1);
		
		WebElement root2 = shadow_root1.findElement(shadowrootDOM2_Product);
		SearchContext shadow_root2 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root2);
		
		WebElement root3 = shadow_root2.findElement(shadowrootDOM3_Product);
		SearchContext shadow_root3 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root3);
		
		WebElement table_element = shadow_root3.findElement(selectList);
		List<WebElement> tr_collection=table_element.findElements(shadowrootDOM4_Product);
		
		int row_num, index;
		row_num=1;
		
		while(row_num<=tr_collection.size()){
		    index = row_num - 1;
		    WebElement root4 = tr_collection.get(index);
			SearchContext shadow_root4 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root4);
			
			WebElement root5 = shadow_root4.findElement(btnArrow);
			SearchContext shadow_root5 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root5);
			
			WebElement root6 = shadow_root5.findElement(optionFirstInnerId);
			SearchContext shadow_root6 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root6);
			
			WebElement root7 = shadow_root6.findElement(dropdownLPRProduct);
			SearchContext shadow_root7 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root7);
			
			String capturedText = shadow_root7.findElement(spanMe).getText();
			capturedText = capturedText.trim();
			capturedText = capturedText.replaceAll("^[^A-Za-z0-9]|[^A-Za-z0-9]$", "");
			capturedText = capturedText.trim();
			
			if(capturedText.equals(reqText)) {
				if(needElement.equals("sb-table-cell.innerTd.primary.first.firstInnerTd"))
					return shadow_root6;
				else if(needElement.equals("tableRow"))
					return shadow_root4;
				}
		
		         row_num++;
		     }
		    
		
		return null;
		
	}
	
	
	public void selectCheckBox(String reqText) throws InterruptedException {
		
		driver.switchTo().defaultContent();
		int cond = 0;
		while(cond == 0) {
			try {driver.switchTo().frame(driver.findElement(quoteIframe));break;}
			catch(Exception e) {Thread.sleep(1000);}
		}
	
		cond = 0;
		while(cond == 0) {
			try {
			WebElement root = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector(\"#sbPageContainer\").shadowRoot.querySelector(\"#content > sb-product-config\")."
			+ "shadowRoot.querySelector(\"#bundles\").shadowRoot.querySelector(\"#features\").shadowRoot.querySelector(\"sb-product-feature-list\")."
			+ "shadowRoot.querySelector(\"#featureList > sb-product-feature:nth-child(1)\").shadowRoot.querySelector(\"#ot\")");
			break;}
			catch(Exception e) {Thread.sleep(1000);}
		}
		
		WebElement root = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector(\"#sbPageContainer\").shadowRoot.querySelector(\"#content > sb-product-config\")."
		+ "shadowRoot.querySelector(\"#bundles\").shadowRoot.querySelector(\"#features\").shadowRoot.querySelector(\"sb-product-feature-list\")."
		+ "shadowRoot.querySelector(\"#featureList > sb-product-feature:nth-child(1)\").shadowRoot.querySelector(\"#ot\")");
		
		SearchContext shadow_root1 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root);
		
		List<WebElement> tr_collection = shadow_root1.findElements(listRowId);
		//System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
		
		int row_num, index;
		row_num=1;
		while(row_num<=tr_collection.size()){
			index = row_num - 1;
			WebElement root4 = tr_collection.get(index);
			SearchContext shadow_root4 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root4);
			
			WebElement root5 = shadow_root4.findElement(btnArrow);
			SearchContext shadow_root5 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root5);
			
			WebElement root6 = shadow_root5.findElement(optionCell);
			SearchContext shadow_root6 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root6);
			
			WebElement root7 = shadow_root6.findElement(dropdownLPRProduct);
			SearchContext shadow_root7 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root7);
			
			String capturedText = shadow_root7.findElement(selectMe).getText();
			capturedText = capturedText.trim();
			capturedText = capturedText.replaceAll("^[^A-Za-z0-9]|[^A-Za-z0-9]$", "");
			capturedText = capturedText.trim();
			
			if(capturedText.equals(reqText)) {
				root5 = shadow_root4.findElement(selection);
				shadow_root5 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root5);
				
				root6 = shadow_root5.findElement(checkboxTableCell);
				shadow_root6 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root6);
				
				root7 = shadow_root6.findElement(checkboxTable);
				shadow_root7 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root7);
				
				Thread.sleep(2000);
				shadow_root7.findElement(checkboxContainer).click();
				
				WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector(\"#sbPageContainer\")."
				+ "shadowRoot.querySelector(\"#content > sb-product-config\").shadowRoot.querySelector(\"#pcSave\")");
				ele.click();
				
				break;
			}
			
			row_num++;
		}
			
	}
	
	
	
		
	
	
	public void createNewQuote() throws InterruptedException {
		action.elementPresent(headingNewQuote);
		action.click(checkboxPrimary);
		action.click(btnSave);
		action.elementPresent(btnNewQuote);
		Thread.sleep(2000);
	}
	
	public void editQuote(String oppttyId) throws InterruptedException {
	action.launchUrl("https://flocksafety--uat.sandbox.lightning.force.com/lightning/r/Opportunity/"+oppttyId+"/related/SBQQ__Quotes2__r/view");
	action.click(quoteRelatedlist);
	action.elementPresent(btnEditLines);
	action.click(btnEditLines);
	
	//Crawling through ShadoDOM elements
	int cond = 0;
	while(cond == 0) {
	
	try {driver.switchTo().frame(0);break;}
	catch(Exception e) {Thread.sleep(1000);}
	}
	
	cond = 0;
	WebElement root1 = null;
	while(cond == 0) {
	try {root1 = driver.findElement(shadowrootDOM1); break;}
	catch(Exception e) {Thread.sleep(1000);}
	}
	
	SearchContext shadow_root1 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root1);
	
	Thread.sleep(3000);
	WebElement root2 = shadow_root1.findElement(shadowrootDOM2);
	SearchContext shadow_root2 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root2);
	
	WebElement root3 = shadow_root2.findElement(shadowrootDOM3);
	SearchContext shadow_root3 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", root3);
	
	shadow_root3.findElement(btnMain).click();
	
	}
	
		
	
} 
