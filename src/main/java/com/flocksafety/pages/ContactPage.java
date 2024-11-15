package com.flocksafety.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;

public class ContactPage extends TestBase {

	WebDriver driver;
	Action action;
	
	//By inputPrimaryContact		= By.xpath("//input[contains(@class,'slds-combobox')]");
	//By btnAddRow				= By.xpath("//span[text()='Add Row']/..");
	By btnAddRow				= By.xpath("//div[contains(@class,'addNewRows')]//button");
	
	By btnContact				= By.xpath("(//button[contains(@class, 'slds-button trigger')])[1]");
	By inputContactName1		= By.xpath("(//input[contains(@class, 'uiInputTextForAutocomplete')])[1]");
	By optionContactName1		= By.xpath("//div[contains(@title,'Woods')]/..");
	By btnContactType1			= By.xpath("(//button[contains(@class, 'slds-button trigger')])[2]");
	By inputContactType1		= By.xpath("(//a[contains(@class, 'select')])[1]");
	By optionContactType1		= By.xpath("//a[contains(@role, 'option')][text()='Primary Contact']");
	
	By btnContact2				= By.xpath("(//button[contains(@class, 'slds-button trigger')])[3]");
	By btnContactType2			= By.xpath("(//button[contains(@class, 'slds-button trigger')])[4]");
	By optionContactType2		= By.xpath("//a[contains(@role, 'option')][text()='Billing Contact']");
	
	By btnContact3				= By.xpath("(//button[contains(@class, 'slds-button trigger')])[5]");
	By btnContactType3			= By.xpath("(//button[contains(@class, 'slds-button trigger')])[6]");
	By optionContactType3		= By.xpath("//a[contains(@role, 'option')][text()='Contract Signer']");
	
	By btnContact4				= By.xpath("(//button[contains(@class, 'slds-button trigger')])[7]");
	By btnContactType4			= By.xpath("(//button[contains(@class, 'slds-button trigger')])[8]");
	By optionContactType4		= By.xpath("//a[contains(@role, 'option')][text()='Economic Buyer']");
	
	By btnContact5				= By.xpath("(//button[contains(@class, 'slds-button trigger')])[9]");
	By btnContactType5			= By.xpath("(//button[contains(@class, 'slds-button trigger')])[10]");
	By optionContactType5		= By.xpath("//a[contains(@role, 'option')][text()='Accounts Payable']");
	
	By btnSave					= By.xpath("(//span[text()='Save'])[last()]/..");
	By btnEditContactRoles		= By.xpath("//a[contains(@title,'Edit Contact Roles')]");
	
	
	public ContactPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);
	}
	
	
	
	public void addContactRows() throws Exception {
		Thread.sleep(2000);
		action.click(btnAddRow);
		
	}
	
	public void addContactRoles(String name, By btnCont, By inputContName, By optionContName, By btnContType, By inputContType, By optionType) throws Exception {
		action.click(btnCont);
		action.type(inputContName, name);
		Thread.sleep(2000);
		action.click(optionContName);
		Thread.sleep(2000);
		action.click(btnContType);
		action.click(inputContType);
		action.click(optionType);
		Thread.sleep(2000);
		
	}

	public void addMultipleContactRows() throws Exception {
		addContactRoles("Darryl Woods",btnContact,inputContactName1,optionContactName1,btnContactType1,inputContactType1,optionContactType1);
		addContactRoles("Darryl Woods",btnContact2,inputContactName1,optionContactName1,btnContactType2,inputContactType1,optionContactType2);
		addContactRoles("Darryl Woods",btnContact3,inputContactName1,optionContactName1,btnContactType3,inputContactType1,optionContactType3);
		addContactRoles("Darryl Woods",btnContact4,inputContactName1,optionContactName1,btnContactType4,inputContactType1,optionContactType4);
		addContactRoles("Darryl Woods",btnContact5,inputContactName1,optionContactName1,btnContactType5,inputContactType1,optionContactType5);
		Thread.sleep(2000);
		action.click(btnSave);
		action.elementPresent(btnEditContactRoles);
		
	}
	


}
