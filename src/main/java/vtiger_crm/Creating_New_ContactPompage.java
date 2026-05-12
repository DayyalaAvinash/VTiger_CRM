package vtiger_crm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Creating_New_ContactPompage {

	//Declaration
	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement Creatingncontactheadertxt;
	
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastnametxt;
	
	@FindBy(xpath = "//img[contains(@onclick,'return')]")
	private WebElement organization_name_plusicon;
	
	@FindBy(xpath = "//input[@name='support_start_date']")
	private WebElement startdate;
	
	@FindBy(xpath = "//input[@name='support_end_date']")
	private WebElement enddate;
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchbox;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchbtn;

	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	
	//Initilization
	public Creating_New_ContactPompage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	//Utilization
	public String getCreatingnewcontact() {
		return Creatingncontactheadertxt.getText();
	}

	public void getLastnametxt(String lastname) {
		 lastnametxt.sendKeys(lastname);;
	}

	public void getOrganization_name_plusicon() {
		 organization_name_plusicon.click();;
	}

	public void getStartdate(String Startdate) {
		 startdate.clear();
		 startdate.sendKeys(Startdate);
		
	}

	public void getEnddate(String Enddate) {
		 enddate.clear();
		 enddate.sendKeys(Enddate);
	}
	

	public void getSearchbox(String orgname) {
		 searchbox.sendKeys(orgname);
	}

	public void getSearchbtn() {
		 searchbtn.click();
	}


	public void getSavebtn() {
		 savebtn.click();
	}
	
	
}
