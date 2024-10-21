package com.flocksafety.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flocksafety.base.BrowserFactory;
import com.flocksafety.base.TestBase;
import com.flocksafety.pages.HomePage;
import com.flocksafety.pages.LoginPage;
import com.flocksafety.utils.Log;
import com.flocksafety.actiondriver.*;

public class Login extends TestBase {

	LoginPage lp;
	HomePage hp;
	Action action;
	
	@BeforeMethod
	public void beforeTest() {

		browserIntialization();

	}

	@Test(groups = "regression,sanity,smoke", priority = 1, description = "Verify Successful Login Only")

	public void verifyLogin_Tc_001() {

		Log.startTestCase("Verify Successful Login Only");
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("UATusername"), prop.getProperty("password"));
		hp.verifyHomePageHeading();
		Assert.assertTrue(true, "Home Page found");
		Log.endTestCase("Verify Successful Login Only");

	}
	
	
	
	
//	(dataProvider = "credentials", dataProviderClass = DataProviders.class)

	@AfterMethod
	public void afterTest() {

		//driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();

	}

}
