package TestScenarios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import DataProvider.ReadExcelData;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


import com.relevantcodes.extentreports.LogStatus;








import utility.Library;
import utility.MWHelper;


public class MobileBaseClass {

	DesiredCapabilities capabilities = new DesiredCapabilities();
	static AndroidDriver driver;
	public static final int DEFAULT_TIMEOUT = 60;
	public  MWHelper helpUtil=new MWHelper() ;

//	public ExtentReports rep = ExtentManager.GetExtent();
	public ExtentTest test;

	 public Library lib;
	
	 public static ReadExcelData exceldata;

	@BeforeSuite
	public void launchApp() throws IOException, InterruptedException {
		
		String deviceName = helpUtil.readConfigFile("DeviceName", "config");
		String serverAddress = helpUtil
				.readConfigFile("appiumServer", "config");
		String portNumber = helpUtil.readConfigFile("PORT", "config");

		
       // test=rep.startTest("BaseClass", "Tests begins");
        

		helpUtil.stopAppiumServer();
		test.log(LogStatus.INFO, "Stopping the Appium Server");
		Thread.sleep(3000);
		
		test.log(LogStatus.INFO, "Stating the Appium Server");
		
		helpUtil.startAppiumServer(serverAddress, portNumber);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	
		
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION",
				helpUtil.readConfigFile("M1Version", "Mobile"));
		capabilities.setCapability("deviceName",
				helpUtil.readConfigFile("M1Version", "Mobile"));
		capabilities.setCapability("platformName",
				helpUtil.readConfigFile("M1Platform", "Mobile"));
	/*	capabilities.setCapability("app",
				helpUtil.readConfigFile("WFilepath", "Mobile"));
		*/
		capabilities.setCapability("appPackage",
				helpUtil.readConfigFile("M1AppPackage", "Mobile"));
		capabilities.setCapability("appActivity",
				helpUtil.readConfigFile("M1AppActivity", "Mobile"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://"
					+ serverAddress + ":" + portNumber + "/wd/hub"),
					capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	
		
		 lib=new Library();
	}
	
	

	
	//@AfterSuite()
	public void EndAppAfterSuite() throws InterruptedException {

		Thread.sleep(5000);
	//	rep.endTest(test);	
	//	rep.flush();

		driver.quit();
	}
}
