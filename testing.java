package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

public class TC_001 extends BaseClass {
	
	@Test(priority=1, groups = { "Sanity", "Regression" })
	void doStuff1()  
	{
		try {
		
			logger.info("***** Started TC_001 *****");
			
			String pg = pr.getProperty("pagetitle");   
		    String ch = pr.getProperty("cartheader"); 
			String x1 = pr.getProperty("item1");      
			
			Assert.assertEquals(driver.getTitle(), pg);
			
			ProductSearch psObj = new ProductSearch(driver);  
			psObj.enterProductName(x1);
			psObj.clickOnSearch();
			
			System.out.println("Total search result found: "+ psObj.totalSearchResult());
			
			String ttt = psObj.getitemTitlebyName(x1);   
			System.out.println("Search item title: " + ttt);
		
			psObj.addToCartByName(ttt);
			logger.info("***** Added item to cart *****");
			
			ShoppingCart ccc = new ShoppingCart(driver);   
			ccc.clickOnCart();
		
			Assert.assertEquals(ccc.getShoppingCartHeader(), ch);
			Assert.assertTrue(ccc.isCartItemExist(ttt));
			
			logger.info("***** Verified cart item *****");
			logger.info("***** Ended TC_001 *****");
			
		}
		catch (AssertionError abc) {   
    			logger.error("Assertion Failed :- " + abc);
    			Assert.fail();
		}
		catch(Exception eee) {   
			logger.error("Testcase Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
	}
 
}
