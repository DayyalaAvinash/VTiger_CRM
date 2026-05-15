package LoginModule;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTest {

	@Test
	public void Login() {
		System.out.println("Login");
		Reporter.log("logged in");
		
	}
}
