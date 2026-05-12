package vtiger_crm;

import java.awt.print.PageFormat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_InformationPompage {
	
	//Declaration
	@FindBy(xpath = "//span[contains(text(),' Contact Information')]")
	private WebElement contacinfotheader;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement verifylastnametxt;
	
	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']//a")
	private WebElement verifyOrgnametxt;

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement verifystartdatetext;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement verifyEndatetxt;

	//Initilize
	public Contact_InformationPompage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	//Utilize
	public String getContacinfotheader() {
		return contacinfotheader.getText();
	}

	public String getVerifylastnametxt() {
		return verifylastnametxt.getText();
	}

	public String getVerifyOrgnametxt() {
		return verifyOrgnametxt.getText();
	}

	public String getVerifystartdatetext() {
		return verifystartdatetext.getText();
	}

	public String getVerifyEndatetxt() {
		return verifyEndatetxt.getText();
	}
	

	
}
