package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups={"Sanity", "Master"})
	public void test_login() {
		try {
			logger.info("****Starting TC_002_LoginTest****");
			HomePage hp = new HomePage(driver);
			
			hp.clickMyaccount();
			logger.info("Clicked on MyAccount");
			
			hp.clickLogin();
			logger.info("Clicked on Login link");
			
			LoginPage lp = new LoginPage(driver);
			
			logger.info("Providing login details");
			lp.setEmailAddress(rb.getString("email"));// valid email get it from config.properties
			lp.setPassword(rb.getString("password"));// valid password get it from config.properties
			
			lp.clickLoginBtn();
			logger.info("Clicked on Login Button");
			
			MyAccountPage macc = new MyAccountPage(driver);

			boolean targetpage = macc.isMyAccountPageExists();
			Assert.assertEquals(targetpage, true, "Invalid Data");

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("***Finished TC_002_LoginTest***");
	}
}