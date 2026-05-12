package BaseClassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ListenerUtility.UtilityObjectClass;
import Utility.DataBaseUtility;
import Utility.PropertyFileUtility;
import Utility.WebdriverUtility;
import vtiger_crm.HomepagePomPage;
import vtiger_crm.LoginpagePompage;

public class BaseClass {

	public DataBaseUtility dbutil=new DataBaseUtility();
	public PropertyFileUtility putil=new PropertyFileUtility();
	public WebdriverUtility wutil=new WebdriverUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	@BeforeSuite
	public void ConnectWith_BD() throws SQLException {
		
		dbutil.getconnectwith_DB();
		Reporter.log("Connected With DataBase",true);
	}
	
	@BeforeTest
	public void ConfigParallelExe() {
		Reporter.log("Configured Parallel Execution",true);
	}
	
	//@Parameters("Browser")
	@BeforeClass
	public void LaunchTheBrowser() throws IOException {
		Reporter.log("Launching Browser",true);
		// launch The Browser
		 
		String Browser = putil.fetchdatafrompropfile("browser");
				if (Browser.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (Browser.equals("edge")) {
					driver = new EdgeDriver();
				} else if (Browser.equals("firefox")) {
					driver = new FirefoxDriver();
				} else {
					driver = new ChromeDriver();
				}
				UtilityObjectClass.setDriver(driver);
				sdriver=driver;
	}
	
	@BeforeMethod
	public void login() throws IOException {
		//Fetch data From Property File
		
		String url = putil.fetchdatafrompropfile("url");
		String Browser = putil.fetchdatafrompropfile("browser");
		String username = putil.fetchdatafrompropfile("username");
		String password = putil.fetchdatafrompropfile("password");
		String timeouts = putil.fetchdatafrompropfile("timeouts");
		
		//Maximize the Browser
		wutil.Maximize_Browser(driver);
		
		//Implicity Wait
		wutil.WaitForElement_implictly(driver, timeouts);
		
		//Navigate To The Application
		wutil.NavigateToApplication(driver, url);
		
		LoginpagePompage login= new LoginpagePompage(driver);
		
		login.loginPage(username, password);
		
		Reporter.log("Logged Into Application",true);
		
	}
	
	@AfterMethod
	public void Logout() {
		
		HomepagePomPage hom=new HomepagePomPage(driver);
		wutil.MouserOverToElement(driver, hom.getAdminicon());
		hom.getSignout();
		Reporter.log("Logged Out  the Application",true);
	}
	
	@AfterClass
	public void quitThebrowser() {
		wutil.QuitTheBrowser(driver);
		Reporter.log("Quiting The Browser",true);
	}
	
	@AfterTest
	public void CloseConfigParallelExe() {
		Reporter.log("Close Configured Parallel Execution",true);
	}
	
	@AfterSuite
	public void Disconneted_DB() throws SQLException {
		dbutil.disconnectwith_DB();
		Reporter.log("Disconnected With DataBase",true);
	}
}
