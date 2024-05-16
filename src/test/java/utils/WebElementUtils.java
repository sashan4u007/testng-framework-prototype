package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementUtils {
	public WebDriver driver = WebDriverUtils.driver;
	int time = 20;
	int interval = 1;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time), Duration.ofSeconds(interval));
	
	
	public WebElement getElement(String locatorType, String locatorStr) {
		By by = getBy(locatorType, locatorStr);
		waitForVisibilityOfElementLocated(by);
		return driver.findElement(by);
	}
	
	public List<WebElement> getElements(String locatorType, String locatorStr) {
		By by = getBy(locatorType, locatorStr);
		waitForVisibilityOfElementLocated(by);
		return driver.findElements(by);
	}
	
	private By getBy(String locatorType, String locatorStr) {
		By locator ;
		switch (locatorType) {
		case "id": {
			locator = By.id(locatorStr);
			break;
		}
		case "xpath": {
			locator = By.xpath(locatorStr);
			break;
		}
		case "className": {
			locator = By.className(locatorStr);
			break;
		}
		case "tagName": {
			locator = By.tagName(locatorStr);
			break;
		}
		case "partialLinkText": {
			locator = By.partialLinkText(locatorStr);
			break;
		}
		case "linkText" : {
			locator = By.linkText(locatorStr);
			break;
		}
		case "css" : {
			locator = By.cssSelector(locatorStr);
			break;
		}
		case "name": {
			locator = By.name(locatorStr);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + locatorType);
		}
		return locator;
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void waitForPageLoad() {
		wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete"));
	}
	
	public void waitForVisibilityOfElementLocated(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForVisibilityOf(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForInvisibilityOf(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForTextToBePresentInElement(WebElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public void selectValueFromDropdown(WebElement element, String option, String selectBy) {
		Select dropdown = new Select(element);
		if (selectBy.equals("ByVisibleText"))
			dropdown.selectByVisibleText(option);
		else if (selectBy.equals("ByValue"))
			dropdown.selectByValue(option);
		else if (selectBy.equals("ByIndex")) 
			dropdown.selectByIndex(Integer.parseInt(option));
		else
			throw new IllegalArgumentException(selectBy+" is invlid selection type from dropdown");
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public String getAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);		
	}
	
	public void sendKeys(WebElement element, String text) {
		element.sendKeys(text);
	}

	public void uploadFileByJS(WebElement element, CharSequence keys) {
		String jse = "arguments[0].type='file'";
		((JavascriptExecutor) driver).executeScript(jse, element); element.sendKeys(keys);
	}

	public void scrollElementIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void clickElement(WebElement element) {
		waitForVisibilityOf(element);
		scrollElementIntoView(element);
		waitForElementToBeClickable(element);
		element.click();
	}

	public void clickElementByJS(WebElement element) {
		waitForVisibilityOf(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
}
