package com.flocksafety.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;


public class UserPersonaPage extends TestBase{
	WebDriver driver;
	Action action;
	DataProvider dataprovider;
	
	By userIframe = By.xpath("//iframe[contains(@name,'vfFrameId')]");
	By usrDetail = By.xpath("//a[@title='User Detail']/..");
	By inputUserDetailLogin = By.xpath("(//input[@title='Login'])[1]");
	
	
	
	
	public UserPersonaPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);
	}

	
	public void gotoSummaryPage(String object, String profileName) {
		
		//driver.navigate().to("https://flocksafety--uat.sandbox.lightning.force.com/lightning/r/User/"+id+"/view");
		//driver.navigate().to("https://flocksafety--uat.sandbox.lightning.force.com/lightning/r/User/"+userId+"/view");
		action.gotoRecordURL(env.get("Env"), object, profile.get(profileName));
	}
	
	public void switchToUser() throws InterruptedException {
		action.click(usrDetail);
		action.waitUntilElementVisible(userIframe);
		action.switchToFrameByLocator(userIframe);
		Thread.sleep(3000);
		action.waitUntilElementVisible(inputUserDetailLogin);
		action.JSClick(inputUserDetailLogin);
		Thread.sleep(5000);
	}














}
