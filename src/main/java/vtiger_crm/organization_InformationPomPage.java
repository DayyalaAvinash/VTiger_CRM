package vtiger_crm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class organization_InformationPomPage {

	//Declaration
	@FindBy(xpath = "//span[contains(text(),'Organization Information')]")
	private WebElement orginfoheader;
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement verifyOrgnametxt;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement verifyPhnotxt;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement verifyIndustry_DDtxt;
	
	@FindBy(id = "dtlview_Type")
	private WebElement verifyType_DDtxt;

	
	//Initilize
	public organization_InformationPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	//Utilize
	public String getOrginfoheader() {
		return orginfoheader.getText();
	}


	public String getVerifyOrgnametxt() {
		return verifyOrgnametxt.getText();
	}


	public String getVerifyPhnotxt() {
		return verifyPhnotxt.getText();
	}


	public String getVerifyIndustry_DDtxt() {
		return verifyIndustry_DDtxt.getText();
	}


	public String getVerifyType_DDtxt() {
		return verifyType_DDtxt.getText();
	}
	
	
	
}
