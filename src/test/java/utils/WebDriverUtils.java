package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	public static WebDriver driver;
	
	public static void launchBrowser() {
		String browser = Config.browser;
		boolean headless = Config.headless;
		if( browser.equalsIgnoreCase("chrome")) {
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--no-sandbox");
			if(headless) {
				co.addArguments("--headless");
			}
			driver = new ChromeDriver(co);
		}
		else if ( browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("No valid option");
			System.exit(0);
		}
		driver.get(Config.url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));
	}
	
	public static String captureScreenshot(String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("Report/extent/screenshot/"+name+System.currentTimeMillis()+".png");
		FileUtils.copyFile(src, dest);
		return dest.getAbsolutePath().toString();
	}
	
	public static void quitBrowser() {
		driver.quit();
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
}
