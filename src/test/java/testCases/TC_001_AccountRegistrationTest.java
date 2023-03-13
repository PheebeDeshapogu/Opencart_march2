package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression", "Master"})
	 public void test_account_Registration() throws InterruptedException {
		//logger.debug("application logs......");
		logger.info("******Starting TC_001_AccountRegistrationTest******");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			logger.info("Clicked on MyAccount link");
			hp.clickRegister();
			logger.info("Clicked on Register link");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("Providing Customer Data");
			regpage.setFirstName(randomeString());
			
			// For UpperCase:-regpage.setFirstName(randomeString().toUpperCase());
			regpage.setlastname(randomeString());
			
			
            regpage.setemail(randomeString() + "@gmail.com");// wpr@gmail.com
			
			regpage.setPassword(randomAlphaNumeric());
			

			regpage.setPrivacyPolicy();
			logger.info("Clicked on Privacy Policy");
			
			regpage.continueBtn();
			logger.info("Clicked on Continue");
			
			logger.info("Validating MyAccountHeaderExists");
			regpage.isMyAccountHeaderExists();
			
			boolean targetHeader = regpage.isMyAccountHeaderExists();
			Assert.assertEquals(targetHeader, true);

			
			

		} catch (Exception e) {
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("******Finished TC_001_AccountRegistrationTest******");
	}
}
