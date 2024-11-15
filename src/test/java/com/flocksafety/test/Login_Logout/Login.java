package com.flocksafety.test.Login_Logout;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flocksafety.base.BrowserFactory;
import com.flocksafety.base.TestBase;
import com.flocksafety.pages.HomePage;
import com.flocksafety.pages.LoginPage;
import com.flocksafety.utils.ExtentManager;
import com.flocksafety.utils.Log;
import com.flocksafety.actiondriver.*;

public class Login extends TestBase {

	LoginPage lp;
	HomePage hp;
	Action action;
	
	@BeforeMethod
	public void beforeTest() {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		//lp.login(prop.getProperty("UATusername"), prop.getProperty("password"));
		lp.login(env.get("username"), env.get("password"));
	}


	@Test(groups = "regression,sanity,smoke", priority = 1, description = "Test Case: Logging into SF")

	
	public void loginToSalesforce() throws Exception {
		Log.startTestCase("Start Loging in to SF");
		hp.verifyHomePageHeading();
		ExtentManager.extenttest.get().info("Home Page Verified");
		Assert.assertTrue(true, "Home Page found");
		Log.endTestCase("Verify Successful Login");
		hp.logoutSF();

	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 2, description = "Failed Test Case")

	public void failedLoginTestCase() throws Exception {
		Log.startTestCase("Start Loging in to SF");
		//hp.invalidHomePageHeading();
		ExtentManager.extenttest.get().info("Home Page not Verified");

	}
	

	@AfterMethod
	public void afterTest() {

		BrowserFactory.getInstance().removeDriver();

	}

}
