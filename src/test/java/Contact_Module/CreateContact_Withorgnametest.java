package Contact_Module;

import java.io.IOException; 
import java.util.Set;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import Utility.ExcelFileUtility;
import Utility.JavaUtiliity;
import Utility.PropertyFileUtility;
import Utility.WebdriverUtility;
import vtiger_crm.Contact_InformationPompage;
import vtiger_crm.Contact_PomPage;
import vtiger_crm.Creating_New_ContactPompage;
import vtiger_crm.Creating_New_OrganizationPomPage;
import vtiger_crm.HomepagePomPage;
import vtiger_crm.LoginpagePompage;
import vtiger_crm.Organizations_PomPage;
import vtiger_crm.organization_InformationPomPage;

public class CreateContact_Withorgnametest {
	@Test
	public void CreateContact_With_orgnametest() throws InterruptedException, EncryptedDocumentException, IOException {

		// fetch property file data using PropertyFileUtility
		PropertyFileUtility p = new PropertyFileUtility();

		String url = p.fetchdatafrompropfile("url");
		String Browser = p.fetchdatafrompropfile("browser");
		String username = p.fetchdatafrompropfile("username");
		String password = p.fetchdatafrompropfile("password");
		String timeouts = p.fetchdatafrompropfile("timeouts");

		JavaUtiliity jutil = new JavaUtiliity();
		int randam = jutil.randomint();

		
		// Get The Test Data From Excel File Using ExcelFileUtility
		ExcelFileUtility exc = new ExcelFileUtility();
		String orgname = exc.fetchDataFromExcelFile("Contact", 7, 4)+randam;
		String lastname = exc.fetchDataFromExcelFile("Contact", 7, 3)+randam;

				WebDriver driver = null;
		// lanch The Browser
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (Browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		WebdriverUtility wutil = new WebdriverUtility();
		// Maximize the Browser
		// driver.manage().window().maximize();
		wutil.Maximize_Browser(driver);

		// Implicity wait
		// long Timeouts = Long.parseLong(timeouts);
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
		wutil.WaitForElement_implictly(driver, timeouts);

		// Navigate To The Application
		// driver.get(url);
		wutil.NavigateToApplication(driver, url);

		LoginpagePompage log = new LoginpagePompage(driver);
		// Identify User NAme TF And Pass Data In Tf
		// driver.findElement(By.name("user_name")).sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		// driver.findElement(By.name("user_password")).sendKeys(password);
		// Identify Login Button And Click On It
		// driver.findElement(By.id("submitButton")).click();
		log.loginPage(username, password);

		// Identify The Home Page
		if (driver.getCurrentUrl().contains("Home")) {
			System.out.println("Home Page Should Be Displayed");
		} else {
			System.out.println("Login Test Fail");
		}

		HomepagePomPage hom = new HomepagePomPage(driver);

		// Identify Organization Tab And Click On It
		// driver.findElement(By.linkText("Organizations")).click();
		hom.getOrganizationstab();

		// Create Object For Organizationpage
		Organizations_PomPage org = new Organizations_PomPage(driver);

		// Identify Plus Icon And click
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();
		org.getPlusicon();

		// Create object for organization information page
		Creating_New_OrganizationPomPage cno = new Creating_New_OrganizationPomPage(driver);

		// Identify Organization Tf and pass The data
		// driver.findElement(By.name("accountname")).sendKeys(orgname);
		cno.getOrganization_name(orgname);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		cno.getSavebtn();

		// Identify Headerinfo And Validate
		organization_InformationPomPage orginfo=new organization_InformationPomPage(driver);
		
		String Headinfo = orginfo.getOrginfoheader();
		if (Headinfo.contains("Organization Information")) {
			System.out.println("Organization Information Header Successfully Displayed");
		} else {
			System.out.println("Organization Information Header not Displayed");

		}

		// Identify Contacts Tab And Click On It
		// driver.findElement(By.linkText("Contacts")).click();
		hom.getContactstab();

		// Identify Plus Icon And click
		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		Contact_PomPage contact = new Contact_PomPage(driver);
		contact.getContact_plusicon();

		// identify And Pass The Data in Last Name Text Field
		// driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname+randam);
		Creating_New_ContactPompage newcon = new Creating_New_ContactPompage(driver);
		newcon.getLastnametxt(lastname);

		// identify And Click Organization name pluse icon
		// driver.findElement(By.xpath("//img[@alt='Select']")).click();
		newcon.getOrganization_name_plusicon();

		// Handle Child Window Popup
		Set<String> Allwindow = wutil.FetchAllwindowid(driver);

		String parentwindows = wutil.Fetchwindowid(driver);

//		for (String all : parentwindow) {
//			driver.switchTo().window(all);
//			if (driver.getCurrentUrl().contains("module=Accounts&action=Popup")) {
//				System.out.println("controle in child page");
//
//				driver.findElement(By.xpath("//a[text()='jspiders']")).click();
//				break;
//			} else {
//				System.out.println("controle in parent page");
//
//			}
//		}
		Thread.sleep(5000);
		wutil.SwitchChildwindow_URL(driver, "module=Accounts&action=Popup");

		newcon.getSearchbox(orgname);
		Thread.sleep(5000);
		newcon.getSearchbtn();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();

//		driver.switchTo().window(parentwindow);
		wutil.SwitchToParentWindow(driver, parentwindows);

		// Identify Save Button And Click On It
		//driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		newcon.getSavebtn();
		
		// Identify Header And Validate
		Contact_InformationPompage cinfo=new Contact_InformationPompage(driver);
		String info = cinfo.getContacinfotheader();

		if (info.contains("Contact Information")) {
			System.out.println("Contact Information Header displayed");
		} else {
			System.out.println("Contact Information Header not displayed");
		}

	
		// Identify Contacts Tab And Click On It
		//driver.findElement(By.linkText("Contacts")).click();
		hom.getContactstab();
		
		// Identify Delete button And Click On It
		driver.findElement(By.xpath("//a[text()='" + lastname +"']/../..//a[text()='del']")).click();

		wutil.handleAlertClickonOK(driver);

		Thread.sleep(2000);

		hom.getOrganizationstab();

		driver.findElement(By.xpath(
				"//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();

		// Handle Pop ANd Click On Ok Button
		// driver.switchTo().alert().accept();
		wutil.handleAlertClickonOK(driver);

		Thread.sleep(2000);

		// click on Admin button
		WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
		// Actions act = new Actions(driver);
		// act.moveToElement(Adminicon).perform();
		wutil.MouserOverToElement(driver, Adminicon);

		// click On Sign Out
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		hom.getSignout();
		
		System.out.println("SignOut Successfully");
		// driver.quit();
		wutil.QuitTheBrowser(driver);

	}
}
