package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;//logging package
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public  static WebDriver driver;
	
	public Logger logger;// declaring the variable for logging

	public ResourceBundle rb;

	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String br) {
		rb = ResourceBundle.getBundle("config");// Load config.properties file
		
		logger = LogManager.getLogger(this.getClass());// this.getClass-captures the current test case which is
														// executing

		ChromeOptions options = new ChromeOptions();//Both these statements are for removing "chrome is controlled by automated software"
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--remote-allow-origins=*");// Both these statements
		
		Map<String, Object> prefs = new HashMap<String, Object>();//these statements are only to remove the pop up to Save and Never
		prefs.put("credentials_enable_service", false);//these statements are only to remove the pop up to Save and Never
		prefs.put("profile.password_manager_enabled", false);//these statements are only to remove the pop up to Save and Never
		options.setExperimentalOption("prefs", prefs);//these statements are only to remove the pop up to Save and Never
																								
		if (br.equals("chrome")) {
			driver = new ChromeDriver(options);
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(rb.getString("appURL"));
		// driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();

	}

	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}

	public String randomeString() {// For random alphabets
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomeNumber() {// for random Numbers and also OTP-4 digits
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}

	public String randomAlphaNumeric() {// for random telephone numbers
		String Str = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		return (Str + num);
		// return (str+"@"+num);
	}

	public String captureScreen(String tname) throws IOException  {

		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		// Date dt = new Date();
		// String timestamp = df.format(dt);
		// all the above 3 statements have been combined into 1 statement the below one

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}

}
