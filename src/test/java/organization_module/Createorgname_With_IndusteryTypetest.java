package organization_module;

import java.io.IOException; 
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
import vtiger_crm.Creating_New_OrganizationPomPage;
import vtiger_crm.HomepagePomPage;
import vtiger_crm.LoginpagePompage;
import vtiger_crm.Organizations_PomPage;
import vtiger_crm.organization_InformationPomPage;

public class Createorgname_With_IndusteryTypetest {

	@Test
	public void Createorgname_With_Industery_Typetest()throws InterruptedException, EncryptedDocumentException, IOException {

		// fetch property file data using PropertyFileUtility
		PropertyFileUtility p = new PropertyFileUtility();

		String url = p.fetchdatafrompropfile("url");
		String Browser = p.fetchdatafrompropfile("browser");
		String username = p.fetchdatafrompropfile("username");
		String password = p.fetchdatafrompropfile("password");
		String timeouts = p.fetchdatafrompropfile("timeouts");

		JavaUtiliity jutil=new JavaUtiliity();
		int randam = jutil.randomint();
		
		// Get The Test Data From Excel File Using ExcelFileUtility
		ExcelFileUtility exc = new ExcelFileUtility();
		String orgname = exc.fetchDataFromExcelFile("Organization", 4, 3)+randam;
		String Banking = exc.fetchDataFromExcelFile("Organization", 4, 4);
		String Analyst = exc.fetchDataFromExcelFile("Organization", 4, 5);

		
//		String Browser="chrome";	
//		String orgname = "Astra";
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

		WebdriverUtility wutil=new WebdriverUtility();
		// Maximize the Browser
		//driver.manage().window().maximize();
		wutil.Maximize_Browser(driver);
		
		// Implicity wait
		//long Timeouts = Long.parseLong(timeouts);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
		wutil.WaitForElement_implictly(driver, timeouts);
		
		// Navigate To The Application
		//driver.get(url);
		wutil.NavigateToApplication(driver, url);
		
		// Create objet for logingpage
		LoginpagePompage log = new LoginpagePompage(driver);

		// Identify User NAme TF And Pass Data In Tf
		// driver.findElement(By.name("user_name")).sendKeys(username);
		//log.getUsernametf().sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		// driver.findElement(By.name("user_password")).sendKeys(password);
		//log.getPasswordtf().sendKeys(password);
		// Identify Login Button And Click On It
		// driver.findElement(By.id("submitButton")).click();
		//log.getLoginbtn().click();
		log.loginPage(username, password);
		
		// Identify The Home Page
		if (driver.getCurrentUrl().contains("Home")) {
			System.out.println("Home Page Should Be Displayed");
		} else {
			System.out.println("Login Test Fail");
		}

		// Create Object For Homepage
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

		// click On Industary DropDown
		// WebElement industrydrop = driver.findElement(By.name("industry"));
		WebElement industrydrop = cno.getIndustrydrop();

		// Handle The Dropdown We Create opject For Select Class
//		Select se = new Select(industrydrop);
//
//		if (se.isMultiple()) {
//			System.out.println("It Is Multiple Dropdown");
//		} else {
//			System.out.println("It Is Single Dropdown");
//
//			se.selectByContainsVisibleText(Banking);
//		}
		wutil.SelectDDbyVisibletext(industrydrop, Banking);

		// click On Type DropDown
		// WebElement Typedrop = driver.findElement(By.name("accounttype"));
		WebElement Typedrop = cno.getTypedrop();

		// Handle The Dropdown We Create opject For Select Class
//		Select se1 = new Select(Typedrop);
//
//		if (se1.isMultiple()) {
//			System.out.println("It Is Multiple Dropdown");
//		} else {
//			System.out.println("It Is Single Dropdown");
//
//			se1.selectByContainsVisibleText(Analyst);
//		}
		wutil.SelectDDbyVisibletext(Typedrop, Analyst);

		// Identify Save Button And Click On It
		// driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		cno.getSavebtn();

		// Identify Verifyindustry And Validate
		organization_InformationPomPage orginfo=new organization_InformationPomPage(driver);
		String Verifyindustry = orginfo.getVerifyIndustry_DDtxt();
		if (Verifyindustry.contains(Banking)) {
			System.out.println("Organization Created With Industary");
		} else {
			System.out.println("Organization Test Fail");

			// Identify Verifytype And Validate
			String Verifytype = orginfo.getVerifyType_DDtxt();

			if (Verifytype.contains(Analyst)) {
				System.out.println("Organization Created With Type");
			} else {
				System.out.println("Organization Test Fail");
			}
		}

		// Identify Organization Tab And Click On It
		// driver.findElement(By.linkText("Organizations")).click();
		hom.getOrganizationstab();

		// Identify Delete button And Click On It
		driver.findElement(By.xpath("//a[text()='" + orgname+ "' and @title='Organizations']/../../descendant::a[text()='del']")).click();

		// Handle Pop ANd Click On Ok Button
		//driver.switchTo().alert().accept();
		wutil.handleAlertClickonOK(driver);
		
		Thread.sleep(2000);

		// click on Admin button
		// WebElement Adminicon =
		// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
		//Actions act = new Actions(driver);
		//act.moveToElement(Adminicon).perform();
		wutil.MouserOverToElement(driver, Adminicon);
		
		// click On Sign Out
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		hom.getSignout();
		
		wutil.QuitTheBrowser(driver);
		
	}
}
