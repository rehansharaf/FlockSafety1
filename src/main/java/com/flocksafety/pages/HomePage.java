package com.flocksafety.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.flocksafety.actiondriver.Action;

public class HomePage {
	
	WebDriver driver;
	Action action = new Action();
	
	By homePageHeading = By.xpath("//span[contains(text(),'Sandbox')]");
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyHomePageHeading() {
		
		action.waitUntilElementVisible(driver, driver.findElement(homePageHeading));
		//return driver.findElement(homePageHeading).getText();
		
	}

}
