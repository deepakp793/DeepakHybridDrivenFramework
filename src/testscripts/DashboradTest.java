package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

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

}
