package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends CommonPage {
	
	private WebElement usernameInput() {
		return getElement("id", "user-name");
	}
	
	private WebElement passwordInput() {
		return getElement("id", "password");
	}
	
	private WebElement loginButton() {
		return getElement("id", "login-button");
	}
	
	private WebElement errorMessage() {
		return getElement("css", "[data-test='error']");
	}
	
	private WebElement errorMessageCloseButton() {
		return getElement("css", "[class='error-button']");
	}
	
	public void login(String uname, String pwd) {
		usernameInput().clear();
		usernameInput().sendKeys(uname);
		passwordInput().clear();
		passwordInput().sendKeys(pwd);
		loginButton().click();
	}
	
	public void verifyLoginErrorMessage() {
		assertTrue(errorMessage().isDisplayed());
		assertTrue(errorMessageCloseButton().isDisplayed());
		assertTrue(errorMessage().getText().equals("Epic sadface: Username and password do not match any user in this service"));
	}
	
	public void closeErrorMessage() {
		errorMessageCloseButton().click();
	}

	public void verifyLoginPage() {
		assertTrue(usernameInput().isDisplayed());
		assertTrue(passwordInput().isDisplayed());
		assertTrue(loginButton().isDisplayed());
	}
	
	public boolean isPageOpen() {
		try {
			verifyLoginPage();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
