package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constant.ConstantValue;
import customExceptions.ElementNotEnabledException;

public class PredefinedActions {
	protected static WebDriver driver;
	static WebDriverWait wait;
	private static Actions action;

	public static void start(String URL) {
		System.setProperty(ConstantValue.CHROMEDRIVERKEY, ConstantValue.CHROMEDRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, ConstantValue.EXPLICITWAITTIME);
		action = new Actions(driver);
	}

	protected WebElement getElement(String locatorType, String locatorValue, Boolean isWaitRequired) {
		WebElement element = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				element = driver.findElement(By.id(locatorValue));
			break;

		case "xpath":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				element = driver.findElement(By.xpath(locatorValue));
			break;

		case "cssSelector":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			else
				element = driver.findElement(By.cssSelector(locatorValue));
			break;

		case "linktext":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				element = driver.findElement(By.linkText(locatorValue));
			break;

		case "partiallinkText":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			else
				element = driver.findElement(By.partialLinkText(locatorValue));
			break;

		case "name":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			} else
				element = driver.findElement(By.name(locatorValue));
			break;

		case "classname":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			} else
				element = driver.findElement(By.className(locatorValue));
			break;

		case "tagname":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			} else
				element = driver.findElement(By.tagName(locatorValue));
			break;
		}
		return element;
	}

	protected boolean waitForVisibilityOfElement(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	protected void setText(String locatorType, String locatorValue, String text, boolean isWaitRequired) {
		WebElement e = getElement(locatorValue, locatorType, isWaitRequired);
		if (e.isEnabled())
			e.sendKeys(text);
	}

	protected void setText(WebElement e, String text) {
		scrollToElement(e);
		if (e.isEnabled())
			e.sendKeys(text);
		else
			throw new ElementNotEnabledException(text + " can't be entered as ele,ent is not enabled");
	}

	protected void clickOnElement(WebElement e, boolean isWaitRequired) {
		scrollToElement(e);
		if (isWaitRequired) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
		}
		e.click();
	}

	protected void scrollToElement(WebElement e) {
		if (!e.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true)", e);
		}
	}

	protected boolean isElementDisplayed(WebElement e) {
		scrollToElement(e);
		return e.isDisplayed();
	}

	protected void mouseHover(WebElement e) {
		scrollToElement(e);
		action.moveToElement(e).build().perform();
		;
	}

	protected List<String> getListOfElementText(List<WebElement> list) {
		List<String> listOfElementText = new ArrayList<String>();
		for (WebElement e : list) {
			listOfElementText.add(e.getText());
		}
		return listOfElementText;
	}

	protected String getElementText(WebElement e, boolean isWaitRequired) {
		if (isWaitRequired)
			waitForVisibilityOfElement(e);

		String text = e.getText();
		if (text.equals(""))
			text = e.getAttribute("value");

		return text;
	}

	protected void clickUsingJS(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
	}

	protected void sendKeysUsingJS(WebElement ele, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + text + "'", ele);
	}

	protected void markCheckboxUsingJS(WebElement ele, boolean checkValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].checked="+checkValue+"", ele);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}

	public static void closeBrowser() {
		driver.close();
	}

	public static void takeScreenshot(String testCaseName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(ConstantValue.SCREENSHOTLOCATION + testCaseName + ConstantValue.SCREENSHOTEXTENSION));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
