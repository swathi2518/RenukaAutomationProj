package TestScenarios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import utility.Library;
import utility.Log;
import DataProvider.ReadExcelData;
import Factory.BrowserFactory;
import PageObjects.demovtigerPageObjects;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class WebBaseClass {
	


		
		protected static WebDriver driver;
		protected ExtentReports extent;
		protected static ExtentTest test;		
		
		public ExtentReports rep = utility.ExtentManager.getInstance();
		
		public  Library help;
	
		demovtigerPageObjects PO;
		
		public static ReadExcelData exceldata;

		@Parameters("browserName")
		@BeforeClass
		public void startbrowser(String browserName) throws IOException {
			System.out.println("Browser name is " + browserName);
		
			BrowserFactory factory=new BrowserFactory();
			driver=factory.getBrowser(browserName);
           
           PO=new demovtigerPageObjects(driver,test);
           
           exceldata=new ReadExcelData("./TestData/Locators.xlsx") ;
           
           help=new Library();
           
   
		}



			@AfterMethod
			public void endTestCase(ITestResult result) {
			
				if (result.getStatus() == ITestResult.FAILURE)
				{
					try {
						Thread.sleep(4000);
						test.log(LogStatus.INFO, test.addScreenCapture(utility.Library.captureScreenshotwithpath(driver)));
						String tc_name = result.getName();
					
						//System.out.println("The test case name is " + tc_name);
						test.log(LogStatus.FAIL, "Validation Failed Taken Screenshot"+tc_name);
						//test.log(LogStatus.INFO, test.addScreenCapture(Library.captureScreenshotwithpath(driver)));
					} catch (Exception e) {
						System.out.println("Exception while taking screen shot"+e.getMessage());
					}
			
				}
				rep.endTest(test);
				rep.flush();
			}
			
			/*public static RemoteWebDriver getDriver(String browser) throws MalformedURLException {
				return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getBrowserCapabilities(browser));
			}*/
			
			
			public static RemoteWebDriver getDriver(String browser,String RemoteURL) throws MalformedURLException {
				return new RemoteWebDriver(new URL(RemoteURL), getBrowserCapabilities(browser));
			}
			
			private static DesiredCapabilities getBrowserCapabilities(String browserType) {
				switch (browserType) {
				case "firefox":
					System.out.println("Opening firefox driver");
					return DesiredCapabilities.firefox();
				case "chrome":
					System.out.println("Opening chrome driver");
					return DesiredCapabilities.chrome();
				case "IE":
					System.out.println("Opening IE driver");
					return DesiredCapabilities.internetExplorer();
				default:
					System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
					return DesiredCapabilities.firefox();
				}
			}
			
			
			

			
			@AfterSuite
			public void tearDown() throws Throwable {
			
				String file=utility.ExtentManager.ReportName;
				System.out.println(file);
			
				
				String filepath="file:///D:/Swathi_WS/Ta3s_Automation_Fw/AdvanceReports/";
			//rep.close();
		//	driver.get(reportPath);
		//	Thread.sleep(5000);
		//	driver.close();
			//driver.get(file);
				
				System.out.println(filepath+file);
				
				driver.get(filepath+file);
				
			Thread.sleep(10000);
		//	driver.quit();
		   // System.out.println("Report is generated >>>> Browser is closed >>>>"+reportPath);
			
			
					
					
			}
			
			
			

	}


