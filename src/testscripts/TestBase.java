package testscripts;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;
import pages.LoginPage;
import utility.PropertyFileOperations;

public class TestBase {
	
	@BeforeMethod
	void startUp() throws IOException {
		PropertyFileOperations propFile = new PropertyFileOperations(".//config//config.properties");
		
		String URL=propFile.getPropValue("URL");
		PredefinedActions.start(URL);
		
		LoginPage login = new LoginPage();
		login.login(propFile.getPropValue("Username"), propFile.getPropValue("Password"));
	}
	
	@AfterMethod
	void wrapUp(ITestResult result) {
		int status=result.getStatus();
		if(ITestResult.FAILURE==status) {
			PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
		}
		
		PredefinedActions.closeBrowser();
	}

}

