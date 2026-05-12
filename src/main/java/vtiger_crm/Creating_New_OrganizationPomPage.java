package vtiger_crm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Creating_New_OrganizationPomPage {

	//Declaration
	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement createorgheader;
	
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement organization_name;
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industrydrop;
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typedrop;
	
	@FindBy(xpath = "//input[@id='phone']")
	private WebElement phnotxt;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]") 
	private WebElement savebtn;
	

	//Initilization
	public Creating_New_OrganizationPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public String getCreateorgheader() {
		return createorgheader.getText();
	}
	
	
	public void getOrganization_name(String username) {
		 organization_name.sendKeys(username);
	}

	public WebElement getIndustrydrop() {
		return industrydrop;
	}


	public WebElement getTypedrop() {
		return typedrop;
	}
	
	public void getPhnotxt(String phno) {
		 phnotxt.sendKeys(phno);
	}

	public void getSavebtn() {
		 savebtn.click();
	}
	
}
