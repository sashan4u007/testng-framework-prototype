package tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import utils.CommonUtils;
import utils.CustomException;

public class InventoryListTest extends Setup {

	LoginPage lp;
	InventoryPage ip;
	
	@BeforeClass
	public void isLoginPageOpened() {
		lp = new LoginPage();
		lp.verifyLoginPage();
		lp.login("standard_user", "secret_sauce");
		ip = new InventoryPage();
		ip.verifyInventoryPage();
	}
	
	@Test(priority = 1, threadPoolSize = 2)
	public void verifyPageNameIsDisplay() {
		ip.verifyInventoryPage();
	}
	
	@Test(dataProvider = "Filter", priority = 2, threadPoolSize = 2)
	public void verifyFilter(String filterName, String order) {
		ip.selectFilter(filterName);
		List<String> items = new ArrayList<String>();
		if(filterName.toLowerCase().contains("price"))
			items = ip.getItemPrices();
		else if ( filterName.toLowerCase().contains("name"))
			items = ip.getItemNames();
		else {
			throw new IllegalArgumentException(filterName+ " is not available in filter list");
		}
		commonUtils.verifyListOrder(items, order);
		
	}
	
	@DataProvider(name = "Filter")
	public String[][] getFilterName() {
		String[][] filterNames = {
				{"Name (A to Z)", "acsending"}, 
				{"Name (Z to A)", "descending"},
				{"Price (low to high)", "acsending"}, 
				{"Price (high to low)", "descending"},
				};
		return filterNames;
	}
	
	@AfterMethod
	public void openLoginPage() {
		try {
			if(lp.isPageOpen()) {
				lp.login("standard_user", "secret_sauce");
				ip.verifyInventoryPage();
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
