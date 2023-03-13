package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {
	
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void test_loginDDt(String email, String password, String exp) {
		logger.info("***Stateing TC_003_LoginDDT***");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(password);
		lp.clickLoginBtn();

		MyAccountPage macc = new MyAccountPage(driver);
		boolean isOnMyAccountPage = macc.isMyAccountPageExists();

		if (exp.equals("Valid")) {// Data is Valid
			if (isOnMyAccountPage) {
				macc.clickLogout();
				Assert.assertTrue(true);// Test is Passed
			} else {
				Assert.assertTrue(false);// Test is Failed
				// Assert.fail();
			}
		}
		if (exp.equals("Invalid")) {// Data is Invalid
			if (isOnMyAccountPage) {
				macc.clickLogout();
				Assert.assertTrue(false);// Test is Failed

			} else {
				Assert.assertTrue(true);// Test is Passed
			}
		}
		} catch(Exception e) {
				Assert.fail();
				}
			
			logger.info("*** Finished TC_003_LoginDDT***");
		
	}	
	
}
