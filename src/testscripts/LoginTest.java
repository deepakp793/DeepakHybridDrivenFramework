package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.PredefinedActions;
import pages.LoginPage;
import utility.ExcelOperations;
public class LoginTest {
	
	
	@Test(dataProvider="SignInDataFromExcel")
	public void tc3_TDD(String URL, String username, String password, boolean succesfulLogin) {
		System.out.println("Step-Launch Chrome Browser and Hit URL");
		PredefinedActions.start("https://dpatil-trials77.orangehrmlive.com");
		
		System.out.println("Step-Enter username and password");
		LoginPage loginPage = new LoginPage();
		loginPage.login(username, password);
		
		if(succesfulLogin) {
			System.out.println("Step-Verify home page is displayed");
			String expectedTitle="Employee Management";
			String actualTitle= loginPage.getPageTitle();
			Assert.assertEquals(expectedTitle, actualTitle, "Expected title was " + expectedTitle + " but actual title was " + actualTitle);
			
			System.out.println("Step-Clean UP");
			PredefinedActions.closeBrowser();
		}else{
			System.out.println("Step-Verify Retry Login page loaded by Title");
			String actualTitle=loginPage.getPageTitle();
			String expectedTitle = "OrangeHRM";
			Assert.assertEquals(actualTitle, expectedTitle, "Expected title was "+expectedTitle+" but actual title was "+actualTitle);
			
			System.out.println("Step-Verify Retry Login page loaded by URL");
			String endURLString="retryLogin";
			String currentURL= loginPage.getPageURL();
			Assert.assertTrue(currentURL.endsWith(endURLString), "Not Retry Login page as page URL is different");
			
			System.out.println("Step-Clean UP");
			PredefinedActions.closeBrowser();
		}
	}
	
	@DataProvider(name="SignInDataFromExcel")
	public Object[][] getLoginDataByReadExcel() throws IOException{
		Object[][]data;
		String filePath=".//testdata/Login Data.xlsx";
		
		try {
			data=ExcelOperations.readExcelData(filePath, "LoginDataSheet");
		} catch (IOException e) {
			data=ExcelOperations.readExcelData(".//testdata/Login Data.xlsx", "LoginDataSheet");
		}
		return data;
	}
	
	@DataProvider(name="SignIn")
	public Object [][] getLoginData(){
		Object [][] loginData= new Object[2][4];
		
		loginData[0][0]="https://harshaug2022-trials76.orangehrmlive.com";
		loginData[0][1]="Admin";
		loginData[0][2]="RKh@6Eoa9H";
		loginData[0][3]=true;
		
		loginData[1][0]="https://harshaug2022-trials76.orangehrmlive.com";
		loginData[1][1]="Admin";
		loginData[1][2]="RKh@6Eoa9invalid";
		loginData[1][3]=false;
		
		return loginData;
	}	
	
	@Test(enabled = false)
	public void tc2_negativeLogin() {
		
		System.out.println("Step-Launch Chrome Browser and Hit URL");
		PredefinedActions.start("https://dpatil-trials77.orangehrmlive.com/");
		
		System.out.println("Step-Enter invalid login credential");
		LoginPage loginPage = new LoginPage();
		loginPage.login("Admin", "RKh@6Eoa9invalid");
		
		System.out.println("Step-Verify Retry Login page loaded by Title");
		String actualTitle=loginPage.getPageTitle();
		String expectedTitle = "OrangeHRM";
		Assert.assertEquals(actualTitle, expectedTitle, "Expected title was "+expectedTitle+" but actual title was "+actualTitle);
		
		System.out.println("Step-Verify Retry Login page by URL");
		String endURLString="retryLogin";
		String currentURL= loginPage.getPageURL();
		Assert.assertTrue(currentURL.endsWith(endURLString), "Not Retry Login page as page URL is different");
		
		System.out.println("Step- Clean UP");
		PredefinedActions.closeBrowser();
	}
	
	@Test(enabled = false)
	public void tc1_Login() {
		System.out.println("Step-Launch Chrome Browser and Hit URL");
		PredefinedActions.start("https://dpatil-trials77.orangehrmlive.com/");
		
		System.out.println("Step-Enter valid login credential");
		LoginPage loginPage = new LoginPage();
		loginPage.login("Admin", "RKh@6Eoa9H");
		
		System.out.println("Step-Verify home page is displayed");
		String expectedTitle="Employee Management";
		String actualTitle= loginPage.getPageTitle();
		
		Assert.assertEquals(expectedTitle, actualTitle, "Expected title was " + expectedTitle + " but actual title was " + actualTitle);
		
		System.out.println("Step-Clean UP");
		PredefinedActions.closeBrowser();
	}
}
