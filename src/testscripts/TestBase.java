package testscripts;

import java.io.IOException;

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
	void wrapUp() {
		PredefinedActions.closeBrowser();
	}

}

