package vtiger_crm;

import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_PomPage {

	//Declaration
	@FindBy(xpath = "//a[text()='Contacts' and @class='hdrLink']")
	private WebElement contacttext;
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement Contact_plusicon;
	
		
	//Initilization
	public Contact_PomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	//Utilization

	public String getContacttext() {
		return contacttext.getText();
	}

	public void getContact_plusicon() {
		 Contact_plusicon.click();
	}
	
	
	
}
