package tests;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.service.ExtentTestManager;

import utils.CommonUtils;
import utils.WebDriverUtils;

public class Setup {
	CommonUtils commonUtils = new CommonUtils();

	@BeforeTest
	public void launchBrowser() {
		WebDriverUtils.launchBrowser();
	}

	@AfterTest
	public void quitBrowserSession() {
		WebDriverUtils.quitBrowser();
	}

	@AfterMethod
	public synchronized void afterTest(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			try {
				String testName = result.getMethod().getMethodName();
				ExtentTestManager.getTest(result)
						.addScreenCaptureFromPath(WebDriverUtils.captureScreenshot(testName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
