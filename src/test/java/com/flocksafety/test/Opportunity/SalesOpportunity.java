package com.flocksafety.test.Opportunity;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flocksafety.actiondriver.Action;
import com.flocksafety.base.TestBase;
import com.flocksafety.pages.AccountPage;
import com.flocksafety.pages.ContactPage;
import com.flocksafety.pages.HomePage;
import com.flocksafety.pages.LoginPage;
import com.flocksafety.pages.OpportunityPage;
import com.flocksafety.pages.QuotePage;
import com.flocksafety.pages.UserPersonaPage;

public class SalesOpportunity extends TestBase{
	Action 			action;
	LoginPage 		lp;
	HomePage 		hp;
	UserPersonaPage userpersonapage;
	AccountPage 	accountpage;
	OpportunityPage opportunitypage;
	ContactPage		contactpage;
	QuotePage		quotepage;
	
	
	@BeforeMethod
	public void beforeTest() {

		browserIntialization();
		
		hp = 				new HomePage(driver);
		lp = 				new LoginPage(driver);
		action = 			new Action(driver);
		userpersonapage = 	new UserPersonaPage(driver);
		accountpage = 		new AccountPage(driver);
		opportunitypage = 	new OpportunityPage(driver);
		contactpage = 		new ContactPage(driver);
		quotepage = 		new QuotePage(driver);
		
		action.randomId();
		lp.login(prop.getProperty("UATusername"), prop.getProperty("password"));
	}

	
	@Test(groups = "regression,sanity,smoke", priority = 1, description = "Create Sales Opportunity")

	public void createSalesOpportunity() throws Exception {
		userpersonapage.gotoSummaryPage("User","Troy Aitken");
		userpersonapage.switchToUser();
		accountpage.openExistingAccountRecord("Account","001Oy00000RYTCvIAP");
		opportunitypage.createCommercialOpportunity();
		opportunitypage.pickOpportunityFromRelatedList("Opportunity");
		opportunitypage.movingToOppStage2();
		opportunitypage.movingToOppStage3();
		opportunitypage.movingToOppStage4();
		opportunitypage.movingToOppStage5();
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 2, description = "Create New Case")
	
	public void createCommercialOpportunity() throws Exception {
		userpersonapage.gotoSummaryPage("User","Troy Aitken");
		userpersonapage.switchToUser();
		quotepage.addProducts();
		//quotepage.selectFlockSafetyLPRProduct("Flock Safety LPR Products");
		
		
		
	}
	
	
	
	
	
	
	
	
}
