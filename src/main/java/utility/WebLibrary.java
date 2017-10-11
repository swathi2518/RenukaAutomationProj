package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;






import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import TestScenarios.WebBaseClass;

public class WebLibrary {
	
	WebDriver driver;
	
	public class BrowserFactory extends WebBaseClass {
		//static WebDriver driver;
		 Library help;
			public WebDriver getBrowser(String browserName) throws IOException{
				
				if(driver==null){
					if(browserName.equalsIgnoreCase("Firefox")){
						ProfilesIni init=new ProfilesIni();
						FirefoxProfile profile=init.getProfile("default");
						driver=new FirefoxDriver(profile);
					}
					if(browserName.equalsIgnoreCase("chrome")){
						System.setProperty("webdriver.chrome.driver",help. readConfigFile( "ChromeDriverPath","frameworkConfig"));
						driver=new ChromeDriver();
					}
					if(browserName.equalsIgnoreCase("IE")){
						DesiredCapabilities cap=new DesiredCapabilities();
						cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
						System.setProperty("webdriver.ie.driver",help. readConfigFile( "IEDriverPath","frameworkConfig"));
						driver=new InternetExplorerDriver(cap);
					}
				}
				return driver;
			}
	
	public void OpenURL(WebDriver driver,String URL)
	{
		
		driver.get(URL);
		
	}
	
	
	public void DisableChromeNotifications()
	
	{
        // Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();
             
               // Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

               // Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

               // Set the experimental option
		options.setExperimentalOption("prefs", prefs);

               // pass the options object in Chrome driver
		
	}
	
	
	
	public void SelectDropdownbyvalue(WebElement ele,String value)
	{
		 Select dropdown=new Select(ele);
		 
		 dropdown.selectByValue(value);
	
	}
	
	
	public void SelectDropdownbyIndex(WebElement ele,int value)
	{
		 Select dropdown=new Select(ele);
		 
		 dropdown.selectByIndex(value);
	
	}
	

	public void SelectDropdownbyVisibletext(WebElement ele,String value)
	{
		 Select dropdown=new Select(ele);
		 
		 dropdown.selectByVisibleText(value);
	
	}
	
	
	
	public void SelectBootStrapDropdown(List<WebElement> dropdownList,String value)
	{

		for(WebElement ele:dropdownList)
		{
			
			if(ele.getText().equalsIgnoreCase("HTML"))
			{
				ele.click();
				
			}
			
			
		}
		
	}
		
		
		public void DragandDrop(WebElement drag,WebElement drop)
		{
			Actions act=new Actions(driver);

			// find element which we need to drag
			WebElement dragp=driver.findElement(By.xpath(".//*[@id='draggable']"));

			// find element which we need to drop
			WebElement dropp=driver.findElement(By.xpath(".//*[@id='droppable']"));

			// this will drag element to destination
			act.dragAndDrop(dragp, dropp).build().perform();
		}

	public void uploadfileUsingRobot(String filepath) throws AWTException
	{
		 StringSelection ss = new StringSelection(filepath);
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		 
		    Robot r = new Robot();
		    
		    
		    // Calling key press event to press Enter Key from keyboard to place cursor in window textbox
		    r.keyPress(KeyEvent.VK_ENTER);
		    //Releaseing the Enter Key
		    r.keyRelease(KeyEvent.VK_ENTER);
		  
		    /*
		     * Now we are going to trigger CTRL+V action so first we will press CTRL and then V and finally will
		     * release these key.
		     */
		    r.keyPress(KeyEvent.VK_CONTROL);    
		    r.keyPress(KeyEvent.VK_V);
		    
		    r.keyRelease(KeyEvent.VK_V);    
		    r.delay(1000);
		    r.keyRelease(KeyEvent.VK_CONTROL);
		    
		    // After pasting path now we are going to click on Open and that can be 
		    //triggered by pressing enter key from Keyboard.
		    r.keyPress(KeyEvent.VK_ENTER);
		    r.keyRelease(KeyEvent.VK_ENTER);
		    //Release open 
		    
		 }

		
	
public void WorkingwithAutocomplete(WebElement element) throws InterruptedException
		{
	 
	// Create object on Actions class
	Actions builder=new Actions(driver);
	 
	// find the element which we want to Select from auto suggestion
	WebElement ele=element;
	 
	// use Mouse hover action for that element
	builder.moveToElement(ele).build().perform();
	 
	// Give wait for 2 seconds 
	Thread.sleep(2000);
	 
	// finally click on that element
	builder.click(ele).build().perform();
	}
	 

public Boolean  VerifyErrorMessage(WebElement ele,String ExpecErrorMsg)
{
	String Actualmessage=ele.getText();
	
	if(Actualmessage.equalsIgnoreCase(ExpecErrorMsg))
	{
		return true;
		
	}
	else
		
		return false;
}
		
	


public void HandleMultiplewindows(WebDriver driver)
{
	
	String parent=driver.getWindowHandle();
	 
	// This will return the number of windows opened by Webdriver and will return Set of St//rings
	Set<String>s1=driver.getWindowHandles();
	 
	// Now we will iterate using Iterator
	Iterator<String> I1= s1.iterator();
	 
	while(I1.hasNext())
	{
	 
	   String child_window=I1.next();
	 
	// Here we will compare if parent window is not equal to child window then we            will close
	 
	if(!parent.equals(child_window))
	{
	driver.switchTo().window(child_window);
	 
	//System.out.println(driver.switchTo().window(child_window).getTitle());
	 
	driver.close();
	}
	 
	}
}
	
	
	
	public void SwitchtoFramewithindex(int indexno )
	{
		try
		{

		driver.switchTo().frame(indexno);
		}
		
		catch(Exception e)
		{
			System.out.println("Unable to switch to frame with indexno" +indexno);
		}
	
	
	}
	
	
	
	public void SwitchtoFramewithname(String Fname)
	{
		try
		{

		driver.switchTo().frame(Fname);
		}
		
		catch(Exception e)
		{
			System.out.println("Unable to switch to frame with name" +Fname);
		}
	
	
	}
	
	public void SwitchtoFramewithElement(WebElement ele)
	{
		try
		{

		driver.switchTo().frame(ele );
		}
		
		catch(Exception e)
		{
			System.out.println("Unable to switch to frame with element" +ele);
		}
	
	}
	




public boolean isAlertPresent(WebDriver ldriver){
	
	try{
		
		 ldriver.switchTo().alert();
		 return true;
	}
	catch(NoAlertPresentException ex){

		return false;
	
	}
 }
	
	
	
	
	public void Action_ToolTip(WebElement ToolTipElement)
	{
		
		Actions builder=new Actions(driver);
		 
		// find the tooltip xpath
		 WebElement username_tooltip=ToolTipElement;
		 
		// Mouse hover to that text message
		builder.moveToElement(username_tooltip).perform();
		 
		// Extract text from tooltip
		 String tooltip_msg=username_tooltip.getText();
		 
		// Print the tooltip message just for our refrences
		 System.out.println("Tooltip/ Help message is "+tooltip_msg);
	}
	
	
	
	public void HandleProxy()
	{
		// Create proxy class object
		  Proxy p=new Proxy();
		 
		 
		  // Set HTTP Port to 7777
		  p.setHttpProxy("localhost:7777");
		 
		  // Create desired Capability object
		  DesiredCapabilities cap=new DesiredCapabilities();
		 
		 
		  // Pass proxy object p
		  cap.setCapability(CapabilityType.PROXY, p);
		 
		
		
	}

	
	public void WorkingwithdefaultProfileSettings()
	{
		
		 // Create object of ProfilesIni class
		 
        ProfilesIni init=new ProfilesIni();



        // Get the default session  

        FirefoxProfile profile=init.getProfile("default");


	}
	
	public FirefoxProfile  HandlesUntrustedCertificates()
	{
		FirefoxProfile profile=new FirefoxProfile();
		 
		// This will set the true value
		profile.setAcceptUntrustedCertificates(true);
		
		return profile;
		
		
	}
	
	public void ScrollintoView(WebElement elementtoview)
	{
		
		 
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
	
		je.executeScript("arguments[0].scrollIntoView(true);",elementtoview);
		 
	}
	
	
	

		public boolean RerunFailedTestcases(ITestResult result,int  maxretryCount) {
			// TODO Auto-generated method stub
			int minretryCount=0;
			
			if(minretryCount<=maxretryCount)
			{
				
				System.out.println("Following test is failing===="+result.getName());
				System.out.println("Retrying the test Count is=== "+ (minretryCount+1));
				
				minretryCount++;
				return true;
			}
			return false;
		}
		
		
		
		
	
	
	

public void verifyLinkActive(String linkUrl)
{
    try 
    {
       URL url = new URL(linkUrl);
       
       HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
       
       httpURLConnect.setConnectTimeout(3000);
       
       httpURLConnect.connect();
       
       if(httpURLConnect.getResponseCode()==200)
       {
           System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
        }
      if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
       {
           System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
        }
    } catch (Exception e) {
       
    }
} 

	}
	
	
	
	public void handletable()
	{
		 driver=new FirefoxDriver();
		
		 driver.manage().window().maximize();
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		String companyname="Island Trading";

		String Contact="Helen Bennett";
		
		int i=2;
		
		String xpath1="//table[@id='customers']/tbody/tr[";
		
		String xpath2="]/td[1]";
		
		while(iseleemntpresent(xpath1+i+xpath2))
		{
			
			String company=driver.findElement(By.xpath(xpath1+i+xpath2)).getText();
			
			if(company.equalsIgnoreCase(companyname))
			{
				System.out.println("Company name found");
				
				String Con=driver.findElement(By.xpath(xpath1+i+xpath2.replace("/td[1]", "/td[2]"))).getText();
				
				if(Con.equalsIgnoreCase(Contact))
				{
					System.out.println("Contact  found");
				}
				
			}
			i++;
			
		}
		
		
		
	}
	
	public boolean iseleemntpresent(String xpath)
	{
		int count=driver.findElements(By.xpath(xpath)).size();
		
		if(count==0)
			return false;
		else
			return true;
		
	}
	
	
	public void Select_The_CheckBox_from_List(WebElement element, String valueToSelect) {
		List<WebElement> allOptions = element.findElements(By.tagName("input"));
		for (WebElement option : allOptions) {
			   System.out.println("Option value "+option.getText());
			        if (valueToSelect.equals(option.getText())) {
			            option.click();
			            break;
			        }
			    }
	}

public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}

private boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
public void SelectAutocomepleteOPtionwithtext(String valutotSelect)
{
	try
	{
	WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
	
	//stem.out.println(autoOptions.
	
//WebDriver wait=	wait.until(ExpectedConditions.visibilityOf(autoOptions));

	List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
	
	System.out.println(optionsToSelect.size());
	for(WebElement option : optionsToSelect){
		System.out.println(option.getText());
        if(option.getText().equals(valutotSelect)) {
        	
        	System.out.println("Trying to select: "+valutotSelect);
            option.click();
            break;
        }
    }
	
} catch (NoSuchElementException e) {
	System.out.println(e.getStackTrace());
}
catch (Exception e) {
	System.out.println(e.getStackTrace());
}
}
public static FirefoxProfile firefoxProfile() throws Exception {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.download.folderList",2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
		firefoxProfile.setPreference("browser.download.dir","downloadPath");
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		

		return firefoxProfile;
	}
	
public int getNumberOfEntries() {
		
		String entriesTxt = driver.findElement(By.id("example_info")).getText().trim();
		String[] aEntriesText = entriesTxt.split(" ");
		String totalEntriesText = aEntriesText[aEntriesText.length-2];
		return Integer.parseInt(totalEntriesText);
	}

public int getRecordsCountInCSV(String downloadPath, String csvFileName) {
		int lineNumberCount = 0;
		try {
			if (!csvFileName.isEmpty() || csvFileName != null) {
				String filePath =	downloadPath + System.getProperty("file.separator") + csvFileName;
				System.out.println(filePath);
				File file = new File(filePath);
				if (file.exists()) {
					System.out.println("File found :" +csvFileName);
					FileReader fr = new FileReader(file);
					LineNumberReader linenumberreader = new LineNumberReader(fr);
					while (linenumberreader.readLine() != null) {
						lineNumberCount++;
					}
					//To remove the header
					lineNumberCount=lineNumberCount-1;
					System.out.println("Total number of lines found in csv : " + (lineNumberCount));
					linenumberreader.close();
				} else {
					System.out.println("File does not exists");
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return lineNumberCount;
	}
/*public void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode()!= 200)
				//invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/

	
}




	
	
	
	
	
	
	





















	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	


