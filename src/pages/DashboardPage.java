package pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;
import pages.DashboardPage.Menu;

public class DashboardPage extends PredefinedActions {
	
	private static DashboardPage dashboardPage;
	
	@FindBy(xpath="//div[contains(@class,'oxd dashboard-widget-shell') and not(contains(@class,'ng-hide'))]//div[@class='widget-header']//span//following-sibling::span")
	private List <WebElement> widgetElementList;
	
	@FindBy(css=".image-container img")
	private WebElement profileElement;
	
	@FindBy(css=".picture i")
	private WebElement setting;
	
	@FindBy(css=".picture .sub-menu-item")
	private List <WebElement> profileOptions;
	
	@FindBy(css="a#aboutDisplayLink")
	private WebElement profileAbt;
	
	@FindBy(css="#companyInfo p")
	private List <WebElement> aboutInfo;
	
	@FindBy(css="#companyInfo>div>div:nth-child(1)>p")
	private WebElement aboutContentFirstP;
	
	private String abtButtonLocator="//a[text()='%s']";
	private String menuLocator="//a[contains(text(),'%s')]";
	
	private void expandProfileByClickOnSetting() {
		mouseHover(profileElement);
		clickOnElement(setting, false);
	}
	
	private DashboardPage(){
		PageFactory.initElements(driver, this);
	}
	
	public static DashboardPage getObject() {
		if(dashboardPage==null)
			dashboardPage = new DashboardPage();
		return dashboardPage;
	}
	
	public int getWidgetSize() {
		return widgetElementList.size();
	}
	
	public List<String> getWidgetText() {
		return getListOfElementText(widgetElementList);
	}
	
	public boolean isProfileDisplayed() {
		return isElementDisplayed(profileElement);
	}
	
	public List<String> getProfileOptionsText() {
		expandProfileByClickOnSetting();
		return getListOfElementText(profileOptions);
	}
	
	public void clickOnPrfileAbt() {
		if(!isElementDisplayed(profileAbt))
			mouseHover(setting);
		clickOnElement(profileAbt, false);
	}
	
	
	
	public Map<String, String> getAboutInfoText(){
		boolean flag=waitForVisibilityOfElement(aboutContentFirstP);
		if(!flag) {
			throw new NoSuchElementException("About content not being loaded");
		}
		
		List<String> detailsList = getListOfElementText(aboutInfo);
		Map<String,String> aboutDetailMap= new LinkedHashMap<String,String>();
		for(String str : detailsList) {
			String[] arr=str.split(":");
			aboutDetailMap.put(arr[0].trim(), arr[1].trim());
		}
		return aboutDetailMap;
	}
	
	public String getCompanyName() {
		return getAboutInfoText().get("Company Name");
	}
	
	public String getVerions() {
		return getAboutInfoText().get("Version");
	}
	
	public String getEmployees() {
		return getAboutInfoText().get("Employees");
	}
	
	public String users() {
		return getAboutInfoText().get("Users");
	}
	
	public String getRenewalOn() {
		return getAboutInfoText().get("Renewal on");
	}
	
	public void clickOnAboutPopupBtn(String btnName){
		String locatorValue= String.format(abtButtonLocator, btnName);
		WebElement e = getElement("xpath", locatorValue, false);
		clickOnElement(e, false);
	}
	
	public enum Menu{
		EMPLOYEELIST("Employee List"),
		MYINFO("My Info"),
		DIRECTORY("Directory");
		
		public String menuItem;
		
		private Menu(String menuTitle) {
			this.menuItem = menuTitle;
		}
	}
	
	enum Browser{
		CHROME,
		FF,
		SAFARI;
	}
	
	
	public void gotoMenu(Menu menuName) {
		String menuText = menuName.menuItem;
		String locatorValue = String.format(menuLocator, menuText);
		clickOnElement(getElement("xpath", locatorValue, true),false);
	}
	
}
