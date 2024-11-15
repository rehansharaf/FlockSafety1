package com.flocksafety.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;


public class LoginPage extends TestBase{
	
	 WebDriver driver;
	 Action action;
	
	 By username = By.id("username");
	 By password = By.id("password");
	 By loginBtn = By.id("Login");
	
	public LoginPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);
		
	}
	
	
	public void login(String email, String pass) {
		
		action.type(username, email);
		action.type(password, pass);
		action.click(loginBtn);
		
		
	}
	
	
	public void verifyLoginPage() throws InterruptedException {
		
		//action.waitforElementVisible(driver.findElement(loginBtn));
		action.waitForElement(loginBtn,"Login");
		
	}
	

	
}
