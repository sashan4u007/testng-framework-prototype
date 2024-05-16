package tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;

public class LoginTests extends Setup {
	
	LoginPage lp;
	InventoryPage ip;
	
	@BeforeClass
	public void isLoginPageOpened() {
		lp = new LoginPage();
		lp.verifyLoginPage();
	}
	
	@AfterMethod
	public void openLoginPage() {
		try {
			if(lp.isPageOpen()) {
				
			} else {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(ip.isPageOpen())
			ip.logout();
	}
		
	@Test(priority=1, threadPoolSize = 2)
	public void loginToDemo() throws IOException {
		lp = new LoginPage();
		lp.login("standard_user", "secret_sauce");
		ip = new InventoryPage();
		ip.verifyInventoryPage();
	}
	
	@Test(dataProvider = "Invalid Login Credentials", priority = 2, threadPoolSize = 2)
	public void loginWithInvalidTestData(String uname, String pwd) {
		lp = new LoginPage();
		lp.login(uname, pwd);
		lp.verifyLoginErrorMessage();
		lp.closeErrorMessage();		
	}
	
	@DataProvider(name = "Invalid Login Credentials")
	public String[][] invlidLoginCredentials() {
		return new String[][] 
		    	{
		            { "Guru99", "India" },
		            { "Krishna", "UK" },
		            { "Bhupesh", "USA" }
		        };
	}
}
