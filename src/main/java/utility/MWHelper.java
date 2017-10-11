package utility;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class MWHelper {
	
	
	public void startAppiumServer(String server, String port) throws IOException {

		// prop = loadPropertyFile();

		CommandLine command = new CommandLine(readConfigFile("WnodePath","config"));
		command.addArgument(readConfigFile("WappiumPath","config"), false);
		String appiumServer =readConfigFile("appiumServer","config");
		command.addArgument("--address", true);
		command.addArgument(server);
		command.addArgument("--port", true);
		command.addArgument(port);
		command.addArgument("--session-override", true);
		command.addArgument("--no-reset");
		command.addArgument("--log-level", false);
		command.addArgument("error");
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.setWatchdog(watchdog);
		String url = null;
		try {
			System.out.println("Command to be executed : " + command.toString());
			executor.execute(command, resultHandler);
			// AppiumServiceBuilder builder = new AppiumServiceBuilder();
			// builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");
			url = appiumServer + ":" + port;
			Thread.sleep(5000);
			System.out.println("Appium Server Started on URL : " + url);
		} catch (IOException e) {
			System.out.println("Unable to start appium on " + url);
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Unable to start appium on " + url);
			e.printStackTrace();
		}
	}

	public void stopAppiumServer() {
		// Add different arguments In command line which requires to stop appium
		// server.
		Reporter.log("Stopping Appium server", true);
		try {
			Thread.sleep(2000);

			CommandLine command = new CommandLine(
					"c:\\windows\\system32\\cmd.exe /c c:\\windows\\system32\\TASKKILL.exe /F /IM node.exe");
			// Execute command line arguments to stop appium server.
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);

			executor.execute(command, resultHandler);

			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Appium server stopped successfully", true);
	}
	
	public  String readConfigFile(String key,String filename) throws IOException {
		Properties p = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+
				"//Configuration//"+filename+".properties");
		p.load(fs);
		
		return (String) p.get(key);
	}


	public static void captureScreenshotforMobile(AndroidDriver driver,String filepath)
	{
		
			TakesScreenshot ts=(TakesScreenshot)driver;
			File screen_src=ts.getScreenshotAs(OutputType.FILE);
		//	String path="./Screenshots/"+System.currentTimeMillis()+".png";
			File destination=new File(filepath);
			try
			{
			FileUtils.copyFile(screen_src,destination);
			System.out.println("Screenshot Taken");
			}
		catch (Exception e) {
			System.out.println("Exception while taking screen shot"+e.getMessage());
			}
		
	}
	
	
	public static void captureScreenshotforWeb(WebDriver driver,String filepath)
	{
		
			TakesScreenshot ts=(TakesScreenshot)driver;
			File screen_src=ts.getScreenshotAs(OutputType.FILE);
		//	String path="./Screenshots/"+System.currentTimeMillis()+".png";
			File destination=new File(filepath);
			try
			{
			FileUtils.copyFile(screen_src,destination);
			System.out.println("Screenshot Taken");
			}
		catch (Exception e) {
			System.out.println("Exception while taking screen shot"+e.getMessage());
			}
		
	}
	
	
	
	public void enterTextboxMobile(WebElement ele,String value)
	{
		
		ele.sendKeys(value);
		
	}
	
	public void enterTextboxWeb(WebElement ele,String value)
	{
		
		ele.sendKeys(value);
		
	}
	
	public void ClickonElement(WebElement ele)
	{
		
		ele.click();
	}
	
	
	
	
	public String GetElementText(WebElement ele)
	{
		return ele.getText();
		
	}
	
	
	public List<WebElement> GetElementTextaluesv(List<WebElement> eles)
	{
		
		List<WebElement>value=eles;
		
		return value;
		
	}
	
	public void ImplicitWaitW(WebDriver driver, int time)
	{
		
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	
	
	public void ImplicitWaitM(AndroidDriver driver, int time)
	{
		
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	
	
	public String  JSGetDomain(WebDriver driver)
	{
		JavascriptExecutor javascript = (JavascriptExecutor) driver; 
		
		return (String)javascript.executeScript("return document.domain");
		
	}
	
	
	
	
	
	
	
	

}
