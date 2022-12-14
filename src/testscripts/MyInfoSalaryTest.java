package testscripts;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.DashboardPage.Menu;
import pages.MyInfoPage;
import pages.MyInfoPage.MyInfoMenu;
import pages.MyInfo_SalaryPage;

public class MyInfoSalaryTest extends TestBase {
	
	@Test
	public void verifyCTC() {
		
		DashboardPage dashboardPage = DashboardPage.getObject();
		
		dashboardPage.gotoMenu(Menu.MYINFO);
		MyInfoPage myInfoPage = MyInfoPage.getObject();
		myInfoPage.goToMenu(MyInfoMenu.SALARY);
		MyInfo_SalaryPage myInfo_SalaryPage = MyInfo_SalaryPage.getObject();
		String costToCompany = myInfo_SalaryPage.getCostToCompany();
		assertTrue(costToCompany.startsWith("$"),"Actual CTC displays "+costToCompany);
		costToCompany= costToCompany.replace("$","").replace(",", "");
		double ctc= Double.parseDouble(costToCompany);
		assertTrue(ctc>0,"CTC value was "+ctc);
		System.out.println(costToCompany);
	}
}
