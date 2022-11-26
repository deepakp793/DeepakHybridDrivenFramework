package testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashboardPage;

public class DashboradTest extends TestBase {
	
	
	@Test
	public void verifyWidgetCountAndText() {
		DashboardPage dashboardPage = new DashboardPage();
		
		System.out.println("Step-Verify total 9 widget's are disply on Dashboard page");
		int widgetCount=dashboardPage.getWidgetSize();
		Assert.assertEquals(widgetCount, 9,"Expected widgets are 9 but in actual it is "+widgetCount);
		
		System.out.println("Step-Get list of all widgets");
		List <String> widgetText=dashboardPage.getWidgetText();
		System.out.println(widgetText);
		
		System.out.println("Step-Verify widget displayed on Dashboard Page");
		List<String> expectedWidget= new ArrayList<String>(Arrays.asList("Buzz Latest Posts","Quick Access", "My Actions",
				"Latest Documents", "Latest News", "Employees on Leave Today",
				"Time At Work", "Headcount by Location", "COVID-19 Report"));		
		Assert.assertEquals(widgetText, expectedWidget);
	}
	
	@Test
	public void verifyProfileAboutContent() {
		DashboardPage dashboardPage = new DashboardPage();

		System.out.println("Step-Verify User profile is displayed");
		assertTrue(dashboardPage.isProfileDisplayed(),"Profile is not displayed");
		
		System.out.println("STEP - Mouse hover on Profile and Click on Settings");
		List<String> expectedProfileSettingOptions = new ArrayList<String>(Arrays.asList("Change Password", "About"));
		List<String> profileSettingOptions = dashboardPage.getProfileOptionsText();
		
		System.out.println("VERIFY - Available setting options");
		Assert.assertEquals(profileSettingOptions, expectedProfileSettingOptions);
		
		System.out.println("STEP-Click on about link");
		dashboardPage.clickOnPrfileAbt();
		
		SoftAssert softAssert = new SoftAssert();
		
		System.out.println("VERIFY-Comapny Name");
		String companyName= "OrangeHRM (Pvt) Ltd(Parallel Demo)";
		softAssert.assertEquals(dashboardPage.getCompanyName(),companyName);
		
		System.out.println("VERIFY-Version");
		String version="OrangeHRM 7.7.178472";
		softAssert.assertEquals(dashboardPage.getVerions(), version);
		
		System.out.println("VERIFY - Employee");
		String employees = "98 (103 more allowed)";
		softAssert.assertEquals(dashboardPage.getEmployees(), employees);
		
		System.out.println("VERIFY - Users");
		String users = "83 (417 more allowed)";
		softAssert.assertEquals(dashboardPage.users(), users);
		
		System.out.println("VERIFY- Renewal On");
		String renewalOn="Fri, 30 Dec 2022";
		softAssert.assertEquals(dashboardPage.getRenewalOn(), renewalOn);
		
		System.out.println("STEP-Click on Ok button");
		dashboardPage.clickOnAboutPopupBtn("Ok");
		
		softAssert.assertAll();
	}

}
