package pages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import utils.WebElementUtils;

public abstract class CommonPage extends WebElementUtils {
	
	private WebElement burgerMenu() {
		return getElement("id", "react-burger-menu-btn");
	}

	private WebElement header() {
		return getElement("css", ".app_logo");
	}
	
	private WebElement cart() {
		return getElement("css", ".shopping_cart_link");
	}
	
	private WebElement menuByName(String name) {
		String locator;
		switch (name.toLowerCase()) {
		case "all items": {
			locator = "inventory_sidebar_link";
			break;
		}
		case "about": {
			locator = "about_sidebar_link";
			break;
		}
		case "logout": {
			locator = "logout_sidebar_link";
			break;
		}
		
		case "reset app state": {
			locator = "reset_sidebar_link";
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + name+". Available menus are 'all items', 'about', 'logout' and 'reset app state'");
		}
		return getElement("id", locator);
	}
	
	private String headerText = "Swag Labs";
	
	public void verifyHeader() {
		WebElement header = header();
		assertTrue(header.isDisplayed());
		assertTrue(header.getText().equals(headerText));
	}
	
	public void verifyPage() {
		verifyHeader();
		assertTrue(burgerMenu().isDisplayed());
		assertTrue(cart().isDisplayed());
	}
	
	public void logout() {
		burgerMenu().click();
		try {
			Thread.sleep(Duration.ofSeconds(2));
		} catch (Exception e) {
			// TODO: handle exception
		}
		menuByName("logout").click();
	}
	
	abstract boolean isPageOpen();
}