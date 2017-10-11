/**
 * 
 */
package Factory;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utility.Library;
import TestScenarios.WebBaseClass;

/**
 * @author SMuppidi
 *
 */
	public class BrowserFactory extends WebBaseClass {
			
			public WebDriver getBrowser(String browserName) throws IOException{
				
				if(driver==null){
					if(browserName.equalsIgnoreCase("Firefox")){
						ProfilesIni init=new ProfilesIni();
						FirefoxProfile profile=init.getProfile("default");
						driver=new FirefoxDriver(profile);
					}
					if(browserName.equalsIgnoreCase("chrome")){
						
						//System.out.println(help.readConfigFile("ChromeDriverPath","frameworkConfig"));
						System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
						driver=new ChromeDriver();
					}
					if(browserName.equalsIgnoreCase("IE")){
						DesiredCapabilities cap=new DesiredCapabilities();
						cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
						//System.setProperty("webdriver.ie.driver",help.readConfigFile( "IEDriverPath","frameworkConfig"));
						driver=new InternetExplorerDriver(cap);
					}
				}
				return driver;
			}
	
	/*public static void closeBrowser()
	{
		driver.quit();
	}*/
}

