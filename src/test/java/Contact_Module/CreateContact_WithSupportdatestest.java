package Contact_Module;
 
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
import vtiger_crm.Contact_InformationPompage;
import vtiger_crm.Contact_PomPage;
import vtiger_crm.Creating_New_ContactPompage;
import vtiger_crm.HomepagePomPage;
import vtiger_crm.LoginpagePompage;

public class CreateContact_WithSupportdatestest {

	@Test
	public void CreateContact_WithSupportdates_test()throws InterruptedException, EncryptedDocumentException, IOException {

		// fetch property file data using PropertyFileUtility
		PropertyFileUtility p = new PropertyFileUtility();

		String url = p.fetchdatafrompropfile("url");
		String Browser = p.fetchdatafrompropfile("browser");
		String username = p.fetchdatafrompropfile("username");
		String password = p.fetchdatafrompropfile("password");
		String timeouts = p.fetchdatafrompropfile("timeouts");
		
		JavaUtiliity jutil=new JavaUtiliity();
		int randam = jutil.randomint();
		String startdate = jutil.fetchcurrentdate();
		String enddate = jutil.fetchdateaftersomedays();

		// Get The Test Data From Excel File Using ExcelFileUtility
		ExcelFileUtility exc = new ExcelFileUtility();
		String lastname = exc.fetchDataFromExcelFile("Contact", 4, 3)+randam;

		
//		String Browser="chrome";	
//		String lastname = "Tommy";
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
		
		LoginpagePompage log=new LoginpagePompage(driver);
		// Identify User NAme TF And Pass Data In Tf
		//driver.findElement(By.name("user_name")).sendKeys(username);
		// Identify Password Tf And Pass The data In Tf
		//driver.findElement(By.name("user_password")).sendKeys(password);
		// Identify Login Button And Click On It
		//driver.findElement(By.id("submitButton")).click();
		log.loginPage(username, password);

		// Identify The Home Page
		if (driver.getCurrentUrl().contains("Home")) {
			System.out.println("Home Page Should Be Displayed");
		} else {
			System.out.println("Login Test Fail");
		}

		// Identify Contacts Tab And Click On It
		//driver.findElement(By.linkText("Contacts")).click();
		HomepagePomPage hom=new HomepagePomPage(driver);
		hom.getContactstab();

		// Identify Plus Icon And click
		//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		Contact_PomPage contact=new Contact_PomPage(driver);
		contact.getContact_plusicon();
		
		// identify And Pass The Data in Last Name Text Field
		//driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname+randam);
		Creating_New_ContactPompage cno=new Creating_New_ContactPompage(driver);
		cno.getLastnametxt(lastname);
		
		// identify Calender And click On It
//		WebElement supportstartdate = cno.getStartdate();
//		supportstartdate.click();
//		supportstartdate.clear();
//		supportstartdate.sendKeys(startdate);
		cno.getStartdate(startdate);
		
//		WebElement supportenddate = cno.getEnddate();
//		supportenddate.click();
//		supportenddate.clear();
//		supportenddate.sendKeys(enddate);
		cno.getEnddate(enddate);
		
		// Identify Save Button And Click On It
		//driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		cno.getSavebtn();
		
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
		driver.findElement(By.xpath("//a[text()='" + lastname+ "']/../..//a[text()='del']")).click();

		// Handle Pop ANd Click On Ok Button
		//driver.switchTo().alert().accept();
		wutil.handleAlertClickonOK(driver);
		
		Thread.sleep(2000);

		// click on Admin button
		WebElement Adminicon = hom.getAdminicon();

		// move the cursor to Admin Button
		//Actions act = new Actions(driver);
		//act.moveToElement(Adminicon).perform();
		wutil.MouserOverToElement(driver, Adminicon);
		
		// click On Sign Out
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		hom.getSignout();
		
		System.out.println("SignOut Successfully");
		//driver.quit();
		wutil.QuitTheBrowser(driver);
	}
}
