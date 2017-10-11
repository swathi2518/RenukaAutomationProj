package BaseUtilClasses;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;




public class MobileBaseClassGRID {

	DesiredCapabilities capabilities = new DesiredCapabilities();

	static AndroidDriver driver;
	String FirstDeviceName = "E6YHUST8VODQYDQG";

	String SecondDeviceName = "ZY2224VQQ8";

	@Parameters({ "port", "Device_ID", "version" })
	@BeforeMethod
	public void setupCapabilities(String port, String Device_ID, String version)
			throws Exception {
		String deviceId = Device_ID;

		if (Device_ID.equalsIgnoreCase(FirstDeviceName)) {

			capabilities.setCapability("automationName", "Appium");

			capabilities.setCapability("platformName", "Android");

			capabilities.setCapability("platformVersion",version);

			capabilities.setCapability("--no-Reset", "true");

			capabilities.setCapability("deviceName", FirstDeviceName);
			capabilities.setCapability("newCommandTimeout", "45000");

			capabilities.setCapability("app",
					"/Users/sumukha/Documents/apk/app-production-debug.apk");

			capabilities.setCapability("appPackage", "com.curbside.nCurbside");
			capabilities.setCapability("appActivity",
					"com.curbside.nCurbside.app.help.SplashScreenActivity");

			driver = new AndroidDriver(new URL("http://127.0.0.1:" + port
					+ "/wd/hub"), capabilities);



		}

		if (Device_ID.equalsIgnoreCase(SecondDeviceName)) {
			capabilities.setCapability("automationName", "Appium");

			capabilities.setCapability("platformName", "Android");

			capabilities.setCapability("platformVersion", "6.0.1");

			capabilities.setCapability("--no-Reset", "true");

			capabilities.setCapability("deviceName", SecondDeviceName);
			capabilities.setCapability("newCommandTimeout", "45000");

			capabilities.setCapability("app",
					"/Users/sumukha/Documents/apk/app-production-debug.apk");

			capabilities.setCapability("appPackage", "com.curbside.nCurbside");
			capabilities.setCapability("appActivity",
					"com.curbside.nCurbside.app.help.SplashScreenActivity");

			driver = new AndroidDriver(new URL("http://127.0.0.1:" + port
					+ "/wd/hub"), capabilities);

		}

		
	}

	@AfterSuite()
	public void EndAppAfterSuite() throws InterruptedException {

		Thread.sleep(5000);
		driver.removeApp("com.curbside.nCurbside");
		/*Report.endTest(extentT);
		Report.flush();*/

		driver.quit();
	}
}
