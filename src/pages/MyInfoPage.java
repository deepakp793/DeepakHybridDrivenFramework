package pages;

import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class MyInfoPage extends PredefinedActions {
	private static MyInfoPage myInfoPage;
	
	private String MenuPage ="//a[contains(text(),'%s')]";
	
	private MyInfoPage() {
		PageFactory.initElements(driver, this);
	}
	
	public enum MyInfoMenu{
		PERSONALDETAILS("Personal Details"),
		JOB("Job"),
		SALARY("Salary");
		
		public String value;
		private MyInfoMenu(String value){
			this.value=value;
		}		
	}
	
	public static MyInfoPage getObject() {
		if(myInfoPage==null)
			myInfoPage=new MyInfoPage();
		return myInfoPage;
	}
	
	public void goToMenu(MyInfoMenu myInfoMenu) {
		String menuText = myInfoMenu.value;
		String locatorValue=String.format(MenuPage, menuText);
		clickOnElement(getElement("xpath", locatorValue, true),true);
	}
	
}
