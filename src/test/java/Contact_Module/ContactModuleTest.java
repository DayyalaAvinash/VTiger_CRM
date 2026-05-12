package Contact_Module;

import java.io.IOException; 
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseClassUtility.BaseClass;
import ListenerUtility.UtilityObjectClass;
import Utility.ExcelFileUtility;
import Utility.JavaUtiliity;
import vtiger_crm.Contact_InformationPompage;
import vtiger_crm.Contact_PomPage;
import vtiger_crm.Creating_New_ContactPompage;
import vtiger_crm.Creating_New_OrganizationPomPage;
import vtiger_crm.HomepagePomPage;

import vtiger_crm.Organizations_PomPage;
import vtiger_crm.organization_InformationPomPage;
//@Listeners(ListenerUtility.Listeners.class)
public class ContactModuleTest extends BaseClass {

	
	@Test(groups = "smoke",retryAnalyzer = ListenerUtility.RetryAnalyser.class)
	public void CreateContact_Withlastname_test() throws InterruptedException, EncryptedDocumentException, IOException {
		// Reporter.log("Browser Setup",true);
		// fetch property file data using PropertyFileUtility
//		PropertyFileUtility p = new PropertyFileUtility();
//		String url = p.fetchdatafrompropfile("url");
//		String Browser = p.fetchdatafrompropfile("browser");
//		String username = p.fetchdatafrompropfile("username");
//		String password = p.fetchdatafrompropfile("password");
//		String timeouts = p.fetchdatafrompropfile("timeouts");
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching random Integer");
		JavaUtiliity jutil = new JavaUtiliity();
		int randam = jutil.randomint();

		// Get The Test Data From Excel File Using ExcelFileUtility
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching Data From Excel File");
		ExcelFileUtility exc = new ExcelFileUtility();
		String lastname = exc.fetchDataFromExcelFile("Contact", 1, 3) + randam;

//		String Browser="chrome";	
//		String lastname = "Tommy";
//		WebDriver driver = null;
//		// lanch The Browser
//		if (Browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (Browser.equals("edge")) {
//			driver = new EdgeDriver();
//		} else if (Browser.equals("Firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			driver = new ChromeDriver();
//		}
//		WebdriverUtility wutil=new WebdriverUtility();
//		// Maximize the Browser
//		//driver.manage().window().maximize();
//		wutil.Maximize_Browser(driver);
//		
//		// Implicity wait
//		//long Timeouts = Long.parseLong(timeouts);
//		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
//		wutil.WaitForElement_implictly(driver, timeouts);
//		
//		// Navigate To The Application
//		//driver.get(url);
//		wutil.NavigateToApplication(driver, url);
//		
//		// Create objet for logingpage
//		LoginpagePompage log = new LoginpagePompage(driver);
//
//		// Identify User NAme TF And Pass Data In Tf
//		// driver.findElement(By.name("user_name")).sendKeys(username);
//		//log.getUsernametf().sendKeys(username);
//		// Identify Password Tf And Pass The data In Tf
//		// driver.findElement(By.name("user_password")).sendKeys(password);
//		//log.getPasswordtf().sendKeys(password);
//		// Identify Login Button And Click On It
//		// driver.findElement(By.id("submitButton")).click();
//		//log.getLoginbtn().click();
//		log.loginPage(username, password);

		// Identify The Home Page
//		if (driver.getCurrentUrl().contains("Home")) {
//			Reporter.log("Home Page Should Be Displayed", true);
//		} else {
//			Reporter.log("Login Test Fail", true);
//		}
		UtilityObjectClass.getTest().log(Status.INFO, "Verified Home Page");
		SoftAssert softas=new SoftAssert();
		softas.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating Home Page");

		// Identify Contacts Tab And Click On It
		// driver.findElement(By.linkText("Contacts")).click();
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Contact Tab And Click On It");
		HomepagePomPage hom = new HomepagePomPage(driver);
		hom.getContactstab();

		// Identify Plus Icon And click
		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Pluse Icon In Contact Tab And Click On It");
		Contact_PomPage contact = new Contact_PomPage(driver);
		contact.getContact_plusicon();

		// identify And Pass The Data in Last Name Text Field
		// driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname+randam);
		UtilityObjectClass.getTest().log(Status.INFO, "Identify And Pass The Data In Last Name Text Field");
		Creating_New_ContactPompage cno = new Creating_New_ContactPompage(driver);
		cno.getLastnametxt(lastname);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		UtilityObjectClass.getTest().log(Status.INFO, "Identify The Save Button And Click On It");
		cno.getSavebtn();

		// Identify Header And Validate
		UtilityObjectClass.getTest().log(Status.PASS, "Identify And Validate The Contact Information Header");
		Contact_InformationPompage cinfo = new Contact_InformationPompage(driver);
		String info = cinfo.getContacinfotheader();

//		if (info.contains("Contact Information")) {
//			Reporter.log("Contact Information Header displayed", true);
//		} else {
//			Reporter.log("Contact Information Header not displayed", true);
//		}
		Assert.assertTrue(info.contains("Contact Information"), "Contact Information Header displayed");

		UtilityObjectClass.getTest().log(Status.PASS, "Identify And Validate The Contact Created With Last name");
		String lastnametxt = cinfo.getVerifylastnametxt();
//		if (lastnametxt.contains(lastname)) {
//			Reporter.log("Contact Created With Last name", true);
//		} else {
//			Reporter.log("Contact Not Created With Lastname", true);
//		}
		Assert.assertTrue(lastnametxt.contains(lastname), "Contact Created With Last name");

		// Identify Contacts Tab And Click On It
		// driver.findElement(By.linkText("Contacts")).click();
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Contact Tab And Click On It");
		hom.getContactstab();

		// Identify Delete button And Click On It
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Delete button And Click On It");
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		// Handle Pop ANd Click On Ok Button
		// driver.switchTo().alert().accept();
		UtilityObjectClass.getTest().log(Status.INFO, "Handle Pop And Click On Ok Button");
		wutil.handleAlertClickonOK(driver);

		Thread.sleep(2000);

		// click on Admin button
//		WebElement Adminicon = hom.getAdminicon();
//
//		// move the cursor to Admin Button
//		//Actions act = new Actions(driver);
//		//act.moveToElement(Adminicon).perform();
//		wutil.MouserOverToElement(driver, Adminicon);
//		
//		// click On Sign Out
//		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		hom.getSignout();
//		
//		Reporter.log("SignOut Successfully");
//		//driver.quit();
//		wutil.QuitTheBrowser(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "Close The Excel");
		exc.closeExcel();
		softas.assertAll();
		

	}

	@Test(groups = "regression",retryAnalyzer = ListenerUtility.RetryAnalyser.class)
	public void CreateContact_With_orgnametest() throws InterruptedException, EncryptedDocumentException, IOException {

		// fetch property file data using PropertyFileUtility
//		PropertyFileUtility p = new PropertyFileUtility();
//
//		String url = p.fetchdatafrompropfile("url");
//		String Browser = p.fetchdatafrompropfile("browser");
//		String username = p.fetchdatafrompropfile("username");
//		String password = p.fetchdatafrompropfile("password");
//		String timeouts = p.fetchdatafrompropfile("timeouts");

		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Random Integer");
		JavaUtiliity jutil = new JavaUtiliity();
		int randam = jutil.randomint();

		// Get The Test Data From Excel File Using ExcelFileUtility
		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Data From Excel");
		ExcelFileUtility exc = new ExcelFileUtility();
		String orgname = exc.fetchDataFromExcelFile("Contact", 7, 4) + randam;
		String lastname = exc.fetchDataFromExcelFile("Contact", 7, 3) + randam;

//		WebDriver driver = null;
//		// lanch The Browser
//		if (Browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (Browser.equals("edge")) {
//			driver = new EdgeDriver();
//		} else if (Browser.equals("Firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			driver = new ChromeDriver();
//		}

//		WebdriverUtility wutil = new WebdriverUtility();
//		// Maximize the Browser
//		// driver.manage().window().maximize();
//		wutil.Maximize_Browser(driver);
//
//		// Implicity wait
//		// long Timeouts = Long.parseLong(timeouts);
//		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
//		wutil.WaitForElement_implictly(driver, timeouts);
//
//		// Navigate To The Application
//		// driver.get(url);
//		wutil.NavigateToApplication(driver, url);
//
//		LoginpagePompage log = new LoginpagePompage(driver);
		// Identify User NAme TF And Pass Data In Tf
		// driver.findElement(By.name("user_name")).sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		// driver.findElement(By.name("user_password")).sendKeys(password);
		// Identify Login Button And Click On It
		// driver.findElement(By.id("submitButton")).click();
		// log.loginPage(username, password);

		// Identify The Home Page
//		if (driver.getCurrentUrl().contains("Home")) {
//			Reporter.log("Home Page Should Be Displayed", true);
//		} else {
//			Reporter.log("Login Test Fail", true);
//		}
		UtilityObjectClass.getTest().log(Status.INFO,"Verify The Home Page");
		SoftAssert softas=new SoftAssert();
		softas.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating Home Page");

		HomepagePomPage hom = new HomepagePomPage(driver);

		// Identify Organization Tab And Click On It
		// driver.findElement(By.linkText("Organizations")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization Tab And Click On It");
		hom.getOrganizationstab();

		// Create Object For Organizationpage
		Organizations_PomPage org = new Organizations_PomPage(driver);

		// Identify Plus Icon And click
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Plus Icon And Click On It in Organization Page");
		org.getPlusicon();

		// Create object for organization information page
		Creating_New_OrganizationPomPage cno = new Creating_New_OrganizationPomPage(driver);

		// Identify Organization Tf and pass The data
		// driver.findElement(By.name("accountname")).sendKeys(orgname);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify The Organization Text field And Pass The Data");
		cno.getOrganization_name(orgname);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Save Button And Click On It");
		cno.getSavebtn();

		// Identify Headerinfo And Validate
		organization_InformationPomPage orginfo = new organization_InformationPomPage(driver);
		UtilityObjectClass.getTest().log(Status.PASS,"Verify Organization Information Header");
		String Headinfo = orginfo.getOrginfoheader();
//		if (Headinfo.contains("Organization Information")) {
//			Reporter.log("Organization Information Header Successfully Displayed", true);
//		} else {
//			Reporter.log("Organization Information Header not Displayed", true);
//
//		}
		Assert.assertTrue(Headinfo.contains("Organization Information"), "Organization Information Header displayed");

		// Identify Contacts Tab And Click On It
		// driver.findElement(By.linkText("Contacts")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Contact Tab And Click On It");
		hom.getContactstab();

		// Identify Plus Icon And click
		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Plus Icon And Click On it In Contact Page");
		Contact_PomPage contact = new Contact_PomPage(driver);
		contact.getContact_plusicon();

		// identify And Pass The Data in Last Name Text Field
		// driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname+randam);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify LastName Text Field And Pass The Data");
		Creating_New_ContactPompage newcon = new Creating_New_ContactPompage(driver);
		newcon.getLastnametxt(lastname);

		// identify And Click Organization name pluse icon
		// driver.findElement(By.xpath("//img[@alt='Select']")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization Name Plus Icon And Click On It In Contact Page");
		newcon.getOrganization_name_plusicon();

		// Handle Child Window Popup
		UtilityObjectClass.getTest().log(Status.INFO,"Handled Child Window Popup");
		Set<String> Allwindow = wutil.FetchAllwindowid(driver);

		String parentwindows = wutil.Fetchwindowid(driver);

//		for (String all : parentwindow) {
//			driver.switchTo().window(all);
//			if (driver.getCurrentUrl().contains("module=Accounts&action=Popup")) {
//				Reporter.log("controle in child page");
//
//				driver.findElement(By.xpath("//a[text()='jspiders']")).click();
//				break;
//			} else {
//				Reporter.log("controle in parent page");
//
//			}
//		}
		Thread.sleep(5000);
		UtilityObjectClass.getTest().log(Status.INFO,"Verify Current Page URL In Child Window Popup");
		wutil.SwitchChildwindow_URL(driver, "module=Accounts&action=Popup");

		UtilityObjectClass.getTest().log(Status.INFO,"Identify Search Box And Pass The Data");
		newcon.getSearchbox(orgname);
		Thread.sleep(5000);
		
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Search Button And Click On It");
		newcon.getSearchbtn();
		Thread.sleep(5000);
		
		UtilityObjectClass.getTest().log(Status.INFO,"Switch Back to Child Window To Parent Window");
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
//		driver.switchTo().window(parentwindow);
		wutil.SwitchToParentWindow(driver, parentwindows);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify And Click On The Save Button In Contact Page");
		newcon.getSavebtn();

		// Identify Header And Validate
		UtilityObjectClass.getTest().log(Status.PASS,"Verify Contact Information Header And Validate");
		Contact_InformationPompage cinfo = new Contact_InformationPompage(driver);
		String info = cinfo.getContacinfotheader();

//		if (info.contains("Contact Information")) {
//			Reporter.log("Contact Information Header displayed");
//		} else {
//			Reporter.log("Contact Information Header not displayed");
//		}
		Assert.assertTrue(info.contains("Contact Information"), "Contact Information Header displayed");

		// Identify Contacts Tab And Click On It
		// driver.findElement(By.linkText("Contacts")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify And Click On Contact Tab");
		hom.getContactstab();

		// Identify Delete button And Click On It
		UtilityObjectClass.getTest().log(Status.INFO,"Identify And Click On Delete Button In Contact Page");
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		
		UtilityObjectClass.getTest().log(Status.INFO,"Handle Alert Popup And Click On Ok Button");
		wutil.handleAlertClickonOK(driver);

		Thread.sleep(2000);

		UtilityObjectClass.getTest().log(Status.INFO,"Identify And Click On Organization Tab");
		hom.getOrganizationstab();

		UtilityObjectClass.getTest().log(Status.INFO,"Identify And Click On Delet Button In Organization Page");
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();
		Thread.sleep(2000);
		// Handle Pop ANd Click On Ok Button
		// driver.switchTo().alert().accept();
		UtilityObjectClass.getTest().log(Status.INFO,"Handle Alert Popup And Click on Ok Button");
		wutil.handleAlertClickonOK(driver);

		Thread.sleep(2000);

		// click on Admin button
		// WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
		// Actions act = new Actions(driver);
		// act.moveToElement(Adminicon).perform();
		// wutil.MouserOverToElement(driver, Adminicon);

		// click On Sign Out
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// hom.getSignout();

		// Reporter.log("SignOut Successfully");
		// driver.quit();
		// wutil.QuitTheBrowser(driver);
		// Close The Excel File
		UtilityObjectClass.getTest().log(Status.INFO,"Close The Excel");
		exc.closeExcel();
		softas.assertAll();
	}

	@Test(groups = "regression",retryAnalyzer = ListenerUtility.RetryAnalyser.class)
	public void CreateContact_WithSupportdates_test()
			throws InterruptedException, EncryptedDocumentException, IOException {

		// fetch property file data using PropertyFileUtility
//		PropertyFileUtility p = new PropertyFileUtility();
//
//		String url = p.fetchdatafrompropfile("url");
//		String Browser = p.fetchdatafrompropfile("browser");
//		String username = p.fetchdatafrompropfile("username");
//		String password = p.fetchdatafrompropfile("password");
//		String timeouts = p.fetchdatafrompropfile("timeouts");

		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Random Integer");
		JavaUtiliity jutil = new JavaUtiliity();
		int randam = jutil.randomint();
		String startdate = jutil.fetchcurrentdate();
		String enddate = jutil.fetchdateaftersomedays();

		// Get The Test Data From Excel File Using ExcelFileUtility
		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Data From Excel File");
		ExcelFileUtility exc = new ExcelFileUtility();
		String lastname = exc.fetchDataFromExcelFile("Contact", 4, 3) + randam;

//		String Browser="chrome";	
//		String lastname = "Tommy";
//		WebDriver driver = null;
//		// lanch The Browser
//		if (Browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (Browser.equals("edge")) {
//			driver = new EdgeDriver();
//		} else if (Browser.equals("Firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			driver = new ChromeDriver();
//		}

		// WebdriverUtility wutil = new WebdriverUtility();
		// Maximize the Browser
		// driver.manage().window().maximize();
		// wutil.Maximize_Browser(driver);

		// Implicity wait
		// long Timeouts = Long.parseLong(timeouts);
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
		// wutil.WaitForElement_implictly(driver, timeouts);

		// Navigate To The Application
		// driver.get(url);
		// wutil.NavigateToApplication(driver, url);

		// LoginpagePompage log = new LoginpagePompage(driver);
		// Identify User NAme TF And Pass Data In Tf
		// driver.findElement(By.name("user_name")).sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		// driver.findElement(By.name("user_password")).sendKeys(password);
		// Identify Login Button And Click On It
		// driver.findElement(By.id("submitButton")).click();
		// log.loginPage(username, password);

		// Identify The Home Page
//		if (driver.getCurrentUrl().contains("Home")) {
//			Reporter.log("Home Page Should Be Displayed");
//		} else {
//			Reporter.log("Login Test Fail");
//		}
		UtilityObjectClass.getTest().log(Status.INFO,"Verify Home Page");
		SoftAssert softas=new SoftAssert();
		softas.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating Home Page");

		// Identify Contacts Tab And Click On It
		// driver.findElement(By.linkText("Contacts")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Contack Tab And Click On It");
		HomepagePomPage hom = new HomepagePomPage(driver);
		hom.getContactstab();

		// Identify Plus Icon And click
		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Plu Icon And Click On It In Contact Page");
		Contact_PomPage contact = new Contact_PomPage(driver);
		contact.getContact_plusicon();

		// identify And Pass The Data in Last Name Text Field
		// driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname+randam);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Last name Text Field And Pass The Data");
		Creating_New_ContactPompage cno = new Creating_New_ContactPompage(driver);
		cno.getLastnametxt(lastname);

		// identify Calender And click On It
//		WebElement supportstartdate = cno.getStartdate();
//		supportstartdate.click();
//		supportstartdate.clear();
//		supportstartdate.sendKeys(startdate);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Support Start Date text Field And Pass The Data");
		cno.getStartdate(startdate);

//		WebElement supportenddate = cno.getEnddate();
//		supportenddate.click();
//		supportenddate.clear();
//		supportenddate.sendKeys(enddate);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify The Support End Date Text Field And Pass The Data");
		cno.getEnddate(enddate);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify save Button And Click On It");
		cno.getSavebtn();

		// Identify Header And Validate
		UtilityObjectClass.getTest().log(Status.PASS,"Verify Contact Information Header And Validate It");
		Contact_InformationPompage cinfo = new Contact_InformationPompage(driver);
		String info = cinfo.getContacinfotheader();

//		if (info.contains("Contact Information")) {
//			Reporter.log("Contact Information Header displayed", true);
//		} else {
//			Reporter.log("Contact Information Header not displayed", true);
//		}
		Assert.assertTrue(info.contains("Contact Information"), "Contact Information Header displayed");

		// Identify Contacts Tab And Click On It
		// driver.findElement(By.linkText("Contacts")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Contact Tab And Click On It");
		hom.getContactstab();

		// Identify Delete button And Click On It
		UtilityObjectClass.getTest().log(Status.INFO,"Identify The Delete Button And Click on It In Contact Page");
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		// Handle Pop ANd Click On Ok Button
		// driver.switchTo().alert().accept();
		UtilityObjectClass.getTest().log(Status.INFO,"Handle Alert Popup And Click On Ok Button");
		wutil.handleAlertClickonOK(driver);

		Thread.sleep(2000);

		// click on Admin button
		// WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
		// Actions act = new Actions(driver);
		// act.moveToElement(Adminicon).perform();
		// wutil.MouserOverToElement(driver, Adminicon);

		// click On Sign Out
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// hom.getSignout();
		// Reporter.log("SignOut Successfully");
		// driver.quit();
		// wutil.QuitTheBrowser(driver);
		// Close The Excel File
		UtilityObjectClass.getTest().log(Status.INFO,"Close The Excel File");
		exc.closeExcel();
		softas.assertAll();
	}
}
