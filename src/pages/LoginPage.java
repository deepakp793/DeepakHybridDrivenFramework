package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class LoginPage extends PredefinedActions {
	
	@FindBy(id="txtUsername")
	private WebElement usernameElement;
	
	@FindBy(id="txtPassword")
	private WebElement passwordElement;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(css = "div.organization-logo.shadow>img")
	private WebElement logo;
	
	@FindBy(css = "#txtUsername-error")
	private WebElement usernameErrorElement;
	
	@FindBy(css = "#txtPassword-error")
	private WebElement pwdErrorElement;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickOnLoginButton();		
	}
	
	public void enterUsername(String username) {
		//driver.findElement(By.id("txtUsername")).sendKeys(username);
		
		WebElement e = getElement("id", "txtUsername", false);
		setText(e, username);
	}
	
	public void enterPassword(String password) {
		//driver.findElement(By.id("txtPassword")).sendKeys(password);
		setText(passwordElement, password);
	}
	
	public void clickOnLoginButton() {
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		clickOnElement(submitBtn, false);
	}
	
	public boolean isUsernameErrorMessageDisplayed() {
		//return driver.findElement(By.cssSelector("#txtUsername-error")).isDisplayed();
		return isElementDisplayed(usernameElement);
	}
	
	public boolean isPasswordErrorMessageDisplayed() {
		//return driver.findElement(By.cssSelector("#txtPassword-error")).isDisplayed();
		return isElementDisplayed(passwordElement);
	}
	
	public boolean isLogoDisplayed() {
		//return driver.findElement(By.cssSelector("div.organization-logo.shadow>img")).isDisplayed();
		return isElementDisplayed(logo);
	}
}
