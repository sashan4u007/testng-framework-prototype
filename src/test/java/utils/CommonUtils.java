package utils;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {
	public void verifyListOrder(List<String> items, String order) {
		List<Object> actual;
		if (items.get(0).startsWith("$")) {
			actual = items.stream().map(element -> Float.parseFloat(element.replace("$", "")))
					.collect(Collectors.toList());
		} else {
			actual = Arrays.asList(items);
		}
		List<Object> expectedList = new ArrayList<Object>();

		if (order.equals("acsending")) {
			expectedList = actual.stream().sorted().toList();
		} else if (order.equals("descending")) {
			expectedList = actual.stream().sorted(Collections.reverseOrder()).toList();
		} else {
			throw new IllegalArgumentException(order + " is not valid option to check the list order");
		}
		assertEquals(actual.size(), expectedList.size());
		assertEquals(actual, expectedList);
	}
}
