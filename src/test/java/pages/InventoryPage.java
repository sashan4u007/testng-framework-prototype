package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.google.common.collect.Comparators;

import utils.CustomException;

public class InventoryPage extends CommonPage {
	
	private WebElement title() {
		return getElement("css", ".title");
	}
	
	private WebElement filterDropdown() {
		return getElement("css", ".product_sort_container");
	}
	
	private WebElement selectedFilterName() {
		return getElement("css", ".active_option");
	}
	
	private WebElement inventoryList() {
		return getElement("css", ".inventory_list");
	}
	
	private List<WebElement> inventoryItems() {
		return getElements("css", "inventory_item");
	}
	
	private List<WebElement> inventoryItemsName() {
		return getElements("css", ".inventory_item_name");
	}
	
	private List<WebElement> inventoryItemsDescription() {
		return getElements("css", ".inventory_item_desc");
	}
	
	private List<WebElement> inventoryItemPrice() {
		return getElements("css", ".inventory_item_price");
	}
	
	private List<WebElement> addToCartButton() {
		return getElements("css", ".btn_inventory");
	}
	
	public void verifyInventoryPage() {
		assertTrue(title().isDisplayed());
		assertEquals(getText(title()), "Products");
		assertTrue(filterDropdown().isDisplayed());
		assertTrue(inventoryList().isDisplayed());
	}
	
	public boolean isPageOpen() {
		try {
			verifyHeader();
			verifyInventoryPage();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getSelectedFilterValue() {
		return selectedFilterName().getText();
	}
	
	public void selectFilter(String filterName) {
		scrollElementIntoView(filterDropdown());
		selectValueFromDropdown(filterDropdown(), filterName, "ByVisibleText");
		waitForTextToBePresentInElement(selectedFilterName(), filterName);
		scrollElementIntoView(filterDropdown());
	}
	
	public List<String> getItemNames() {
		List<String> names = new ArrayList<String>();
		for(WebElement element : inventoryItemsName()) {
			scrollElementIntoView(element);
			names.add(element.getText());
		}
		return names;
	}
	
	public List<String> getItemPrices() {
		List<String> prices = new ArrayList<String>() ;
		for(WebElement element: inventoryItemPrice()) {
			scrollElementIntoView(element);
			prices.add(element.getText());
		}
		return prices;
	}
}
