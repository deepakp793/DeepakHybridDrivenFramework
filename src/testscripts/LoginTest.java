package testscripts;


import org.testng.Assert;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.LoginPage;

public class LoginTest {
	
	@Test
	public void tc1() {
		System.out.println("Step-Launch Chrome Browser and Hit URL");
		PredefinedActions.start("https://dpatil-trials77.orangehrmlive.com/");
		
		System.out.println("Step-Enter valid login credential");
		LoginPage loginPage = new LoginPage();
		loginPage.login("Admin", "RKh@6Eoa9H");
		
		System.out.println("Step-Verify home page is displayed");
		String expectedTitle="Employee Management";
		String actualTitle= loginPage.getTitle();
		
		Assert.assertEquals(expectedTitle, actualTitle, "Expected title was " + expectedTitle + " but actual title was " + actualTitle);
		
		PredefinedActions.closeBrowser();
	}

}
