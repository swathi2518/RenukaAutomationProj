package TestScenarios;

import java.net.MalformedURLException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class ParallelTestA extends WebBaseClass{
	
	public static RemoteWebDriver driver;
	public static String appURL = "http://www.google.com";
	
	@BeforeClass
	@Parameters({ "browser","RemoteURL" })
	public void setUp(String browser,String RemoteURL) throws MalformedURLException {
		System.out.println("*******************");
		driver = getDriver(browser,RemoteURL);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testGooglePageTitleInFirefox() {
		test=rep.startTest("Testing Google");
		driver.navigate().to(appURL);
		test.log(LogStatus.INFO, "Opened the URL");
		String strPageTitle = driver.getTitle();
		test.log(LogStatus.INFO, "Get the title");
		Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
		test.log(LogStatus.PASS, "Google test Passed");
	}

	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			System.out.println("Closing browser");
			driver.quit();
		}
	}
	


}
