package vtiger_crm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizations_PomPage {

	//Declaration
	@FindBy(xpath = "//a[text()='Organizations' and @class='hdrLink']")
	private WebElement Organizations_text;
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement plusicon;
	
	
	

	//Initilization
	public Organizations_PomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	
	
	public String getOrganizations_text() {
		return Organizations_text.getText();
	}

	public void getPlusicon() {
		 plusicon.click();;
	}
	

}
