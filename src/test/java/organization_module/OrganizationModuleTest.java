package organization_module;

import java.io.IOException; 

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseClassUtility.BaseClass;
import ListenerUtility.UtilityObjectClass;
import Utility.ExcelFileUtility;
import Utility.JavaUtiliity;
import Utility.WebdriverUtility;
import vtiger_crm.Creating_New_OrganizationPomPage;
import vtiger_crm.HomepagePomPage;
import vtiger_crm.Organizations_PomPage;
import vtiger_crm.organization_InformationPomPage;
//@Listeners(ListenerUtility.Listeners.class)
public class OrganizationModuleTest extends BaseClass {

	@Test(groups = "smoke",retryAnalyzer = ListenerUtility.RetryAnalyser.class)
	public void Createorgname_With_Industery_Typetest()throws InterruptedException, EncryptedDocumentException, IOException {

		// fetch property file data using PropertyFileUtility
//		PropertyFileUtility p = new PropertyFileUtility();
//
//		String url = p.fetchdatafrompropfile("url");
//		String Browser = p.fetchdatafrompropfile("browser");
//		String username = p.fetchdatafrompropfile("username");
//		String password = p.fetchdatafrompropfile("password");
//		String timeouts = p.fetchdatafrompropfile("timeouts");

		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Random Integer");
		JavaUtiliity jutil=new JavaUtiliity();
		int randam = jutil.randomint();
		
		// Get The Test Data From Excel File Using ExcelFileUtility
		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Data From excel File");
		ExcelFileUtility exc = new ExcelFileUtility();
		String orgname = exc.fetchDataFromExcelFile("Organization", 4, 3)+randam;
		String Banking = exc.fetchDataFromExcelFile("Organization", 4, 4);
		String Analyst = exc.fetchDataFromExcelFile("Organization", 4, 5);

		
//		String Browser="chrome";	
//		String orgname = "Astra";
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

		//WebdriverUtility wutil=new WebdriverUtility();
		// Maximize the Browser
		//driver.manage().window().maximize();
		//wutil.Maximize_Browser(driver);
		
		// Implicity wait
		//long Timeouts = Long.parseLong(timeouts);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
		//wutil.WaitForElement_implictly(driver, timeouts);
		
		// Navigate To The Application
		//driver.get(url);
		//wutil.NavigateToApplication(driver, url);
		
		// Create objet for logingpage
		//LoginpagePompage log = new LoginpagePompage(driver);

		// Identify User NAme TF And Pass Data In Tf
		// driver.findElement(By.name("user_name")).sendKeys(username);
		//log.getUsernametf().sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		// driver.findElement(By.name("user_password")).sendKeys(password);
		//log.getPasswordtf().sendKeys(password);
		// Identify Login Button And Click On It
		// driver.findElement(By.id("submitButton")).click();
		//log.getLoginbtn().click();
		//log.loginPage(username, password);
		
		// Identify The Home Page
//		if (driver.getCurrentUrl().contains("Home")) {
//			Reporter.log("Home Page Should Be Displayed",true);
//		} else {
//			Reporter.log("Login Test Fail",true);
//		}
		UtilityObjectClass.getTest().log(Status.INFO, "Verify Home Page");
		SoftAssert softas=new SoftAssert();
		softas.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating Home Page");

		// Create Object For Homepage
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
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Plus Icon And Click On It In Organization Page");
		org.getPlusicon();

		// Create object for organization information page
		Creating_New_OrganizationPomPage cno = new Creating_New_OrganizationPomPage(driver);

		// Identify Organization Tf and pass The data
		// driver.findElement(By.name("accountname")).sendKeys(orgname);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization Name text Field And Pass The Data");
		cno.getOrganization_name(orgname);

		// click On Industary DropDown
		// WebElement industrydrop = driver.findElement(By.name("industry"));
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Industry Dropdown And Click On it");
		WebElement industrydrop = cno.getIndustrydrop();

		// Handle The Dropdown We Create opject For Select Class
//		Select se = new Select(industrydrop);
//
//		if (se.isMultiple()) {
//			Reporter.log("It Is Multiple Dropdown");
//		} else {
//			Reporter.log("It Is Single Dropdown");
//
//			se.selectByContainsVisibleText(Banking);
//		}
		UtilityObjectClass.getTest().log(Status.INFO,"Handling Industry Dropdown");
		wutil.SelectDDbyVisibletext(industrydrop, Banking);

		// click On Type DropDown
		// WebElement Typedrop = driver.findElement(By.name("accounttype"));
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Type Dropdown And Click On It");
		WebElement Typedrop = cno.getTypedrop();

		// Handle The Dropdown We Create opject For Select Class
//		Select se1 = new Select(Typedrop);
//
//		if (se1.isMultiple()) {
//			Reporter.log("It Is Multiple Dropdown");
//		} else {
//			Reporter.log("It Is Single Dropdown");
//
//			se1.selectByContainsVisibleText(Analyst);
//		}
		UtilityObjectClass.getTest().log(Status.INFO,"Handling Type Dropdown");
		wutil.SelectDDbyVisibletext(Typedrop, Analyst);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Save Button And Click On It");
		cno.getSavebtn();

		// Identify Verifyindustry And Validate
		UtilityObjectClass.getTest().log(Status.PASS,"Verify Organization Is Created With Banking In Industry Dropdown ");
		organization_InformationPomPage orginfo=new organization_InformationPomPage(driver);
		String Verifyindustry = orginfo.getVerifyIndustry_DDtxt();
//		if (Verifyindustry.contains(Banking)) {
//			Reporter.log("Organization Created With Industary",true);
//		} else {
//			Reporter.log("Organization Test Fail",true);
		Assert.assertTrue(Verifyindustry.contains("Banking"), "Organization Created With Industry");

			// Identify Verifytype And Validate
		UtilityObjectClass.getTest().log(Status.PASS,"Verify Organization Is Created With Analyst In Type Dropdown ");
			String Verifytype = orginfo.getVerifyType_DDtxt();
//
//			if (Verifytype.contains(Analyst)) {
//				Reporter.log("Organization Created With Type",true);
//			} else {
//				Reporter.log("Organization Test Fail",true);
//			}
		//}
		Assert.assertTrue(Verifytype.contains("Analyst"), "Organization Created With Analyst");

		// Identify Organization Tab And Click On It
		// driver.findElement(By.linkText("Organizations")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization Tab And Click On It");
		hom.getOrganizationstab();

		// Identify Delete button And Click On It
		UtilityObjectClass.getTest().log(Status.INFO,"Identify delete Button And Click On It In Organization Page");
		driver.findElement(By.xpath("//a[text()='" + orgname+ "' and @title='Organizations']/../../descendant::a[text()='del']")).click();
		Thread.sleep(2000);
		// Handle Pop ANd Click On Ok Button
		//driver.switchTo().alert().accept();
		UtilityObjectClass.getTest().log(Status.INFO,"Handle Alert Popup And Click On Ok Button");
		wutil.handleAlertClickonOK(driver);
		
		Thread.sleep(2000);

		// click on Admin button
		// WebElement Adminicon =
		// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
		//Actions act = new Actions(driver);
		//act.moveToElement(Adminicon).perform();
		//wutil.MouserOverToElement(driver, Adminicon);
		
		// click On Sign Out
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		//hom.getSignout();
		//wutil.QuitTheBrowser(driver);
		//Close The Excel File
		UtilityObjectClass.getTest().log(Status.INFO,"Close tthe Excel");
		exc.closeExcel();
		softas.assertAll();
		
	}
	
	@Test(groups = "regression",retryAnalyzer = ListenerUtility.RetryAnalyser.class)
	public void Createorgname_Withphonenumber_test() throws InterruptedException, IOException {

		// fetch property file data using PropertyFileUtility
//		PropertyFileUtility p = new PropertyFileUtility();
//
//		String url = p.fetchdatafrompropfile("url");
//		String Browser = p.fetchdatafrompropfile("browser");
//		String username = p.fetchdatafrompropfile("username");
//		String password = p.fetchdatafrompropfile("password");
//		String timeouts = p.fetchdatafrompropfile("timeouts");

		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Random Integer");
		JavaUtiliity jutil=new JavaUtiliity();
		int randam = jutil.randomint();
		
		// Get The Test Data From Excel File Using ExcelFileUtility
		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Data From Excel File");
		ExcelFileUtility exc = new ExcelFileUtility();
		String orgname = exc.fetchDataFromExcelFile("Organization", 1, 3)+randam;
		String phno = exc.fetchDataFromExcelFile("Organization", 8, 4);

		
//		String Browser="chrome";	
//		String orgname = "Astra";
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

		//WebdriverUtility wutil=new WebdriverUtility();
		// Maximize the Browser
		//driver.manage().window().maximize();
		//wutil.Maximize_Browser(driver);
		
		// Implicity wait
		//long Timeouts = Long.parseLong(timeouts);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
		//wutil.WaitForElement_implictly(driver, timeouts);
		
		// Navigate To The Application
		//driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		//wutil.NavigateToApplication(driver, url);
		
		// Create objet for logingpage
		//LoginpagePompage log = new LoginpagePompage(driver);

		// Identify User NAme TF And Pass Data In Tf
		// driver.findElement(By.name("user_name")).sendKeys(username);
		//log.getUsernametf().sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		// driver.findElement(By.name("user_password")).sendKeys(password);
		//log.getPasswordtf().sendKeys(password);
		// Identify Login Button And Click On It
		// driver.findElement(By.id("submitButton")).click();
		//log.getLoginbtn().click();
		//log.loginPage(username, password);

		// Identify The Home Page
//		if (driver.getCurrentUrl().contains("Home")) {
//			Reporter.log("Home Page Should Be Displayed",true);
//		} else {
//			Reporter.log("Login Test Fail",true);
//		}
		UtilityObjectClass.getTest().log(Status.INFO,"Verify Home Page");
		SoftAssert softas=new SoftAssert();
		softas.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating Home Page");

		// Create Object For Homepage
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
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Plus Icon And Click On It In Organization Page");
		org.getPlusicon();

		// Create object for organization information page
		Creating_New_OrganizationPomPage cno = new Creating_New_OrganizationPomPage(driver);

		// Identify Organization Tf and pass The data
		// driver.findElement(By.name("accountname")).sendKeys(orgname);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization Name TextField And Pass The Data");
		cno.getOrganization_name(orgname);

		// Identify And Pass The Date In Phone Number Test Field
		// driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phno);
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Phone Number TextField And Pass The Data");
		cno.getPhnotxt(phno);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Save Button And Click  On It");
		cno.getSavebtn();

		// Identify Headerinfo And Validate
		UtilityObjectClass.getTest().log(Status.PASS,"Verify Organization Information Header is Displayed");
		organization_InformationPomPage orginfo=new organization_InformationPomPage(driver);
		String infoheader = orginfo.getOrginfoheader();
//		if (infoheader.contains("Organization Information")) {
//			Reporter.log("Information Header Successfully Displayed",true);
//		} else {
//			Reporter.log("Information Header Not Displayed",true);
//
//		}
		Assert.assertTrue(infoheader.contains("Organization Information"), "Organization Information Header displayed");

		//Verify Phno Number 
		UtilityObjectClass.getTest().log(Status.PASS,"verify Phno Number Is Displayed");
		String phnotxt = orginfo.getVerifyPhnotxt();
//		if (phnotxt.contains("7841256930")) {
//			Reporter.log("Successfully Created With Phno Number",true);
//		} else {
//			Reporter.log("Create with Phno Test Fail",true);
//			
//		}
		Assert.assertTrue(phnotxt.contains("7841256930"), "Successfully Created With Phno Number");
		// Identify Organization Tab And Click On It
		// driver.findElement(By.linkText("Organizations")).click();
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization Tab And Click On It");
		hom.getOrganizationstab();

		// Identify Delete button And Click On It
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Delete Button And Click on It In Organization Page");
		driver.findElement(By.xpath("//a[text()='" + orgname+ "' and @title='Organizations']/../../descendant::a[text()='del']")).click();
		Thread.sleep(2000);
		// Handle Pop ANd Click On Ok Button
		//driver.switchTo().alert().accept();
		UtilityObjectClass.getTest().log(Status.INFO,"Handle Alert Popup And Click Ok Button");
		wutil.handleAlertClickonOK(driver);
		
		Thread.sleep(2000);

		// click on Admin button
		// WebElement Adminicon =
		// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		// WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
		//Actions act = new Actions(driver);
		//act.moveToElement(Adminicon).perform();
		//wutil.MouserOverToElement(driver, Adminicon);
		
		// click On Sign Out
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		//hom.getSignout();
		//Reporter.log("SignOut Successfully");
		//driver.close();
		//wutil.QuitTheBrowser(driver);
		//Close The Excel File
		UtilityObjectClass.getTest().log(Status.INFO,"Close The Excel");
		exc.closeExcel();
		softas.assertAll();
		
	}
	
	@Test(groups = "regression",retryAnalyzer = ListenerUtility.RetryAnalyser.class)
	public void Createorgname_test() throws InterruptedException, IOException {

		// fetch property file data using utility
//		PropertyFileUtility p = new PropertyFileUtility();
//
//		String url = p.fetchdatafrompropfile("url");
//		String Browser = p.fetchdatafrompropfile("browser");
//		String username = p.fetchdatafrompropfile("username");
//		String password = p.fetchdatafrompropfile("password");
//		
//		String timeouts = p.fetchdatafrompropfile("timeouts");
		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Random Integer");
		JavaUtiliity jutil = new JavaUtiliity();
		int randam = jutil.randomint();
		
		// Get The Test Data From Excel File Using ExcelFileUtility
		UtilityObjectClass.getTest().log(Status.INFO,"Fetching Data From Excel File");
		ExcelFileUtility exc = new ExcelFileUtility();
		String orgname = exc.fetchDataFromExcelFile("Contact", 1, 3)+randam;
		

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

		WebdriverUtility wutil = new WebdriverUtility();
		// Maximize the Browser
		//wutil.Maximize_Browser(driver);

		// Implicity wait
		// long Timeouts = Long.parseLong(timeouts);
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
		//wutil.WaitForElement_implictly(driver, timeouts);
		
		// Navigate To The Application
		//driver.get(url);
		//wutil.NavigateToApplication(driver, url);
		
		// Create objet for logingpage
		//LoginpagePompage log = new LoginpagePompage(driver);

		// Identify User NAme TF And Pass Data In Tf
		// driver.findElement(By.name("user_name")).sendKeys(username);
		//log.getUsernametf().sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		// driver.findElement(By.name("user_password")).sendKeys(password);
		//log.getPasswordtf().sendKeys(password);
		// Identify Login Button And Click On It
		// driver.findElement(By.id("submitButton")).click();
		//	log.getLoginbtn().click();
		//log.loginPage(username, password);
		
		// Identify The Home Page
//		if (driver.getCurrentUrl().contains("Home")) {
//			Reporter.log("Home Page Should Be Displayed",true);
//		} else {
//			Reporter.log("Login Test Fail",true);
//		}
		UtilityObjectClass.getTest().log(Status.INFO,"Verify Home Page Is Displayed");
		SoftAssert softas=new SoftAssert();
		softas.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating Home Page");

		//Create object for Homepom page
		HomepagePomPage hom = new HomepagePomPage(driver);

		// Identify Organization Tab And Click On It
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization Tab And Click On It");
		hom.getOrganizationstab();

		// Create Object For Organizationpage
		Organizations_PomPage org = new Organizations_PomPage(driver);

		// Identify Plus Icon And click
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Plus Icon And Click On It");
		org.getPlusicon();

		// Create object for organization information page
		Creating_New_OrganizationPomPage cno = new Creating_New_OrganizationPomPage(driver);

		UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization TextField And Pass the Data");
		cno.getOrganization_name(orgname);
		
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Save Button And Click on It");
		cno.getSavebtn();

		//Identify And Validate Information text
		organization_InformationPomPage orginfo=new organization_InformationPomPage(driver);
		
		UtilityObjectClass.getTest().log(Status.PASS,"Organization Information Header And Validate");
		 String Headinfo = orginfo.getOrginfoheader();
//		if (Headinfo.contains("Organization Information")) {
//			Reporter.log("Header Successfully Displayed",true);
//		} else {
//			Reporter.log("Header not Displayed",true);
//
//		}
		 Assert.assertTrue(Headinfo.contains("Organization Information"), "Organization Information Header displayed");

		// Identify Organization Tab And Click On It
		// driver.findElement(By.linkText("Organizations")).click();
		 UtilityObjectClass.getTest().log(Status.INFO,"Identify Organization tab And Click On It");
		hom.getOrganizationstab();

		// Identify Delete button And Click On It
		UtilityObjectClass.getTest().log(Status.INFO,"Identify Delete Button And Click on It In Organization Page");
		driver.findElement(By.xpath(
				"//a[text()='" + orgname +"' and @title='Organizations']/../../descendant::a[text()='del']")).click();
		Thread.sleep(2000);
		// Handle Pop ANd Click On Ok Button
		//driver.switchTo().alert().accept();
		UtilityObjectClass.getTest().log(Status.INFO,"Handle alert Popup And click Ok Button");
		wutil.handleAlertClickonOK(driver);
		
		Thread.sleep(2000);

		// click on Admin button
		// WebElement Adminicon =
		// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
//		Actions act = new Actions(driver);
//		act.moveToElement(Adminicon).perform();
		//wutil.MouserOverToElement(driver, Adminicon);
		
		// click On Sign Out
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		//hom.getSignout();
		//wutil.QuitTheBrowser(driver);
		//Close The Excel File
		exc.closeExcel();
		UtilityObjectClass.getTest().log(Status.INFO,"Close The Excel");
		softas.assertAll();
	}
}
