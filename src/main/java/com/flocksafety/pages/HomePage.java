package com.flocksafety.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;





public class HomePage extends TestBase {
	
	WebDriver driver;
	Action action;
	
	By homePageHeading = By.xpath("//span[contains(text(),'Sandbox')]");
	By btnUsrProfile = By.xpath("//button[contains(@class,'userProfile')]");
	By optionLogout = By.xpath("//a[contains(text(),'Log Out')]");
	By wronghomePageHeading = By.xpath("//span[contains(text(),'testing')]");
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);

	}

	public void verifyHomePageHeading() throws InterruptedException {
		
		action.waitUntilElementVisible(homePageHeading);
		String text = driver.findElement(homePageHeading).getText();
		softAssertion.assertEquals(text.contains("Sandbox"), "Sandbox");
		
	}

	public void invalidHomePageHeading() throws InterruptedException {
		
		action.click(wronghomePageHeading);
		
	}

	
	
	
	public void logoutSF() throws Exception {
		
		action.waitUntilElementVisible(btnUsrProfile);
		action.clickElement(btnUsrProfile);
		Thread.sleep(3000);
		action.clickElement(optionLogout);
	}
	
	public void movetoPersonaPage() throws Exception {
	
	}
	
	
	
}
