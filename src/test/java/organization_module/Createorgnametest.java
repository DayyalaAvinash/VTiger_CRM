package organization_module;

import java.io.IOException; 
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

public class Createorgnametest {

	@Test
	public void Createorgname_test() throws InterruptedException, IOException {

		// fetch property file data using utility
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
		String orgname = exc.fetchDataFromExcelFile("Contact", 1, 3)+randam;
		

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
		wutil.Maximize_Browser(driver);

		// Implicity wait
		// long Timeouts = Long.parseLong(timeouts);
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
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
		//	log.getLoginbtn().click();
		log.loginPage(username, password);
		
		// Identify The Home Page
		if (driver.getCurrentUrl().contains("Home")) {
			System.out.println("Home Page Should Be Displayed");
		} else {
			System.out.println("Login Test Fail");
		}

		//Create object for Homepom page
		HomepagePomPage hom = new HomepagePomPage(driver);

		// Identify Organization Tab And Click On It
	
		hom.getOrganizationstab();

		// Create Object For Organizationpage
		Organizations_PomPage org = new Organizations_PomPage(driver);

		// Identify Plus Icon And click
		
		org.getPlusicon();

		// Create object for organization information page
		Creating_New_OrganizationPomPage cno = new Creating_New_OrganizationPomPage(driver);

		
		cno.getOrganization_name(orgname);
		
		cno.getSavebtn();

		//Identify And Validate Information text
		organization_InformationPomPage orginfo=new organization_InformationPomPage(driver);
		
		
		 String Headinfo = orginfo.getOrginfoheader();
		if (Headinfo.contains("Organization Information")) {
			System.out.println("Header Successfully Displayed");
		} else {
			System.out.println("Header not Displayed");

		}

		// Identify Organization Tab And Click On It
		// driver.findElement(By.linkText("Organizations")).click();
		hom.getOrganizationstab();

		// Identify Delete button And Click On It
		driver.findElement(By.xpath(
				"//a[text()='" + orgname +"' and @title='Organizations']/../../descendant::a[text()='del']")).click();

		// Handle Pop ANd Click On Ok Button
		//driver.switchTo().alert().accept();
		wutil.handleAlertClickonOK(driver);
		
		Thread.sleep(2000);

		// click on Admin button
		// WebElement Adminicon =
		// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
//		Actions act = new Actions(driver);
//		act.moveToElement(Adminicon).perform();
		wutil.MouserOverToElement(driver, Adminicon);
		
		// click On Sign Out
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		hom.getSignout();
		wutil.QuitTheBrowser(driver);
		
	}
}
