package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class DashboardPage extends PredefinedActions {
	
	@FindBy(xpath="//div[contains(@class,'oxd dashboard-widget-shell') and not(contains(@class,'ng-hide'))]//div[@class='widget-header']//span//following-sibling::span")
	private List <WebElement> widgetElementList;
	
	public DashboardPage(){
		PageFactory.initElements(driver, this);
	}
	
	public int getWidgetSize() {
		return widgetElementList.size();
	}
	
	public List<String> getWidgetText() {
		List<String> widgetText=new ArrayList();
		for(WebElement e : widgetElementList) {
			widgetText.add(e.getText());
		}
		return widgetText;
	}
}
