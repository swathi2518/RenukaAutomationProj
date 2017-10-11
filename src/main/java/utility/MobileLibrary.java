package utility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class MobileLibrary {
	
	AndroidDriver driver;
	
	public MobileLibrary(AndroidDriver driver)
	{
		this.driver=driver;
	}
	

	public void enterTextboxMobile(WebElement ele,String value)
	{
		
		ele.sendKeys(value);
		
	}
	
	
	
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

	
	public void Swipe(double startheight,double Endheight,int time)
	{
		

		Dimension size = driver.manage().window().getSize();
		
		int startx=(int)(size.width/2);
		
		int starty = (int) (size.height * startheight);
		  //Find endy point which is at top side of screen.
		int endy = (int) (size.height * startheight);
		
		driver.swipe(startx, starty, startx, endy, time);
		
	}
	
	
	public void waitforsec(int time) throws InterruptedException
	{
		
		Thread.sleep(time);
	}
	
	public void  GoingBack()
	{
		
		driver.pressKeyCode(4);
		
	}
	
	public void  PressEnter()
	{
		
		//driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER );
		
		//((AppiumDriver) driver).sendKeyEvent(AndroidKeyCode.ENTER);
		//driver.sendKeyEvent(AndroidKeyCode.ENTER)
		//driver.pressKeyCode(66);
		
		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.ENTER);
	}
		
	/*
	public void searchbutton()
	{
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH );
		//driver.pressKeyCode(84);
	}
	*/
	
	/*public void justEnter()
	{
		
		driver.sendKeyEvent(AndroidKeyCode.ENTER);
	}
	
	
*/
	public void SwipeUntilElementisVisible(WebElement ele) throws InterruptedException
	{
				
		Dimension size = driver.manage().window().getSize();
		
		int startx=size.width/2;
		int starty=size.height*1/8;
		int Endy=size.height*1/2;
		
		for (int i = 1; i <= 10; i++) {
			if (ele.isDisplayed())
			{
				System.out.println("is displayed");
				ele.click();
				break;
				
			}
			
			else
			{
				System.out.println("is not displayed");
				Thread.sleep(3000);
				driver.swipe(startx, starty, startx, Endy, 500);
				
			}
		
		}
	}	
		
		public void swipeVertical() throws InterruptedException
		{
			int screenwidth=driver.manage().window().getSize().width;
			
			int screenheight=driver.manage().window().getSize().height;

			Thread.sleep(5000);
			
			driver.swipe(screenwidth/2, screenheight*9/10, screenwidth/2, screenheight*5/10, 1000);
					
			
		}
		
		public void HideKeyBoard()
		{
			try
			{
				driver.hideKeyboard();
				
			}
			catch(Exception e)
			{
			
				System.out.println("keyboard is not existing");
			}
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
	
}
