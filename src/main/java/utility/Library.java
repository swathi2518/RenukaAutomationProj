package utility;

import io.appium.java_client.android.AndroidDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestScenarios.WebBaseClass;
import DataProvider.ReadExcelData;

public class Library extends WebBaseClass{
	

	 public static String destDir;
	 public static DateFormat dateFormat;
	 public static String scrPath;
	ArrayList<String> ObjectList;
	ReadExcelData readexcel = new ReadExcelData("./TestData/Locators.xlsx");
	

	
	public void type(WebElement ele, String value) throws IOException {
		try {
				ele.sendKeys(value);
			}
		 catch (Exception e) {
			System.out.println("Typing failed on the key " + ele);
		}
	}
	
	
	public void Clear(WebElement Element) throws IOException {
		try {			
			Element.clear();
		} catch (Exception e) {
			
			System.out.println("clearing failed on the key " + Element);
		}
	}
	
	
	
	public void Buttonclick(WebElement ele) throws IOException {
		try {
			if (ele.isEnabled()) {
				ele.click();
			}
		} catch (Exception e) {
			
			System.out.println("Click failed on the key " + ele);
		}
	}
	
		
	public void selectByVisibleText(String key, String value) throws IOException{
		Select select= new Select(driver.findElement(By.xpath(key)));
		select.selectByVisibleText(value);
	}
	
	public void selectByValue(String key, String value) throws IOException{
		Select select= new Select(driver.findElement(By.xpath(key)));
		select.selectByValue(value);
	}
	
	public String getText(String key){
		try{
			return driver.findElement(By.xpath(key)).getText();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Get text failed on the key "+ key);
		}
		return null;
	}
	
		
	public void keyTab() throws AWTException, InterruptedException{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
	}
	
	public void waitForLoader(int timeOutInSeconds){
		Wait<WebDriver> wait= new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='_viewRoot:status.start']/img")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='_viewRoot:status.start']/img")));
	}
	public void HandleAlert(){
		Alert alt=null;
		try{
		alt=driver.switchTo().alert();
		}catch(Exception e){
			alt.accept();
		}
		
	}
	 public static WebElement isElementPresnt(WebDriver driver,String xpath,int time)
		{
		 

		WebElement ele = null;
		 
		for(int i=0;i<time;i++)
		{
		try{
		ele=driver.findElement(By.xpath(xpath));
		break;
		}
		catch(Exception e)
		{
		try 
		{
		Thread.sleep(1000);
		} catch (InterruptedException e1) 
		{
		System.out.println("Waiting for element to appear on DOM");
		}
		}
		 
		 
		}
		return ele;
		 
		}
		
	 
	 //****************CL-02*****************
	 
	 public static String getDay(String mydate)
		{
			   Date mydate1;
			try {
				SimpleDateFormat format = new SimpleDateFormat();
				   mydate1 = new Date(mydate);
				   return new SimpleDateFormat("EE").format(mydate1);
			} catch (Exception e) {
				System.out.println("Exception while coverting to day"+e.getMessage());
				return e.getMessage();
			}
	}
	 
	 //********************CL_03***************
	public static void captureScreenshot(WebDriver ldriver)
	{
		
			TakesScreenshot ts=(TakesScreenshot)ldriver;
			File screen_src=ts.getScreenshotAs(OutputType.FILE);
			String path="./Screenshots/"+System.currentTimeMillis()+".png";
			File destination=new File(path);
			try
			{
			FileUtils.copyFile(screen_src,destination);
			System.out.println("Screenshot Taken");
			}
		catch (Exception e) {
			System.out.println("Exception while taking screen shot"+e.getMessage());
			}
		
	}
	public static void captureScreenshotwithTC(WebDriver ldriver, String tc_name)
	{
		
			TakesScreenshot ts=(TakesScreenshot)ldriver;
			File screen_src=ts.getScreenshotAs(OutputType.FILE);
			String path="./Screenshots/"+tc_name+System.currentTimeMillis()+".png";
			File destination=new File(path);
			try
			{
			FileUtils.copyFile(screen_src,destination);
			System.out.println("Screenshot Taken");
			}
		catch (Exception e) {
			System.out.println("Exception while taking screen shot"+e.getMessage());
			//return e.getMessage();
		}
		}
	public static String captureScreenshotwithpath(WebDriver ldriver)
	{
		
			TakesScreenshot ts=(TakesScreenshot)ldriver;
			File screen_src=ts.getScreenshotAs(OutputType.FILE);
			String path="D:/Swathi_WS/Ta3s_Automation_Fw/Screenshots"+System.currentTimeMillis()+".png";			
			File destination=new File(path);
			try
			{
			FileUtils.copyFile(screen_src,destination);
			System.out.println("Screenshot Taken");
			//return dest;
		}
		catch (Exception e) {
			System.out.println("Exception while taking screen shot"+e.getMessage());
			//return e.getMessage();
		}
			return path;
	}
	
	
	
	public static String OCR(String ImagePath)
	{
		 String result = null;
		  File imageFile = new File(ImagePath);
		  
		 
	      ITesseract instance = new Tesseract();  
	      try {
	         instance.setDatapath("C://Program Files (x86)//Tesseract-OCR//tessdata");
	          result =instance.doOCR(imageFile);
	     
	      } catch (TesseractException e) {
	          System.err.println(e.getMessage());
	      }
		return result;
	  }
	
	
	public static String  takeScreenShotfortoast(AndroidDriver driver) {
		  // Set folder name to store screenshots.
		  destDir = "D://Swathi_WS//Ta3s_Automation_Fw//Screenshots";
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // Set date format to set It as screenshot file name.
		  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // Create folder under project with name "screenshots" provided to destDir.
		  new File(destDir).mkdirs();
		  // Set file name using current date time.
		  String destFile = dateFormat.format(new Date()) + ".png";

		  try {
		   // Copy paste file at destination folder location
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		   scrPath = destDir+ "//" + destFile;
		   System.out.println("File created");
		  
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  
		  return scrPath;
		 
		 }

	
	
	
	
	public void waitForElementClickable(int timeOutInSeconds,WebElement  webElement) {
		Wait<WebDriver> wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	public void waitForElementVisibility(int timeOutInSeconds,WebElement webElement) {
		Wait<WebDriver> wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	public void waitForPageLoad(){
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
	}

	
	public static WebElement highLightElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; boarder:2px solid red;');",element);
		
		try{
			Thread.sleep(500);
		}catch (InterruptedException e){
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style', 'boarder: solid 2px white');", element);
		return element;
	}
		
	
	public void ExplicitWaitElementClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.elementToBeClickable(ele)); 
						
	}
	
	
	public void ExplicitWaitElementVisible(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void ExplicitWaitElementSelect(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.elementToBeSelected(ele)); 
						
	}
	
	public  String readConfigFile(String key,String filename) throws IOException {
		Properties p = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+
				"//Configuration//"+filename+".properties");
		p.load(fs);
		return (String) p.get(key);
		
	}

	
	
	public WebElement  ReadObject(String VariableName,String SheetName)
	{
		
	     ObjectList=new ArrayList<String>();
		
		if(ObjectList.get(0).equalsIgnoreCase(VariableName))
		{
		//	System.out.println("inloopt" );
			String identifier =ObjectList.get(1);
		//	System.out.println(identifier);
			String locator =ObjectList.get(2);
			//System.out.println(locator);
			if (identifier.equalsIgnoreCase("xpath")) {
				return driver.findElement(By.xpath(locator));
			}
			else if (identifier.equalsIgnoreCase("id")) {
				
				return  driver.findElement(By.xpath(locator));
			}
			else if (identifier.equalsIgnoreCase("name")) {
				return  driver.findElement(By.xpath(locator));
			} else if (identifier.equalsIgnoreCase("cssSelector")) {
				return driver.findElement(By.xpath(locator));
			}
		}
		
		return null;
		//System.out.println("Sorry");
	
	

}
	
	

	 public WebElement readObjectLocator(String key) throws IOException {
			
			Object[][] data = readexcel.Getdata("Elements");
			for (int row = 0; row < data.length; row++) {
				if (data[row][0].equals(key)) {
					String identifier = (String) data[row][1];
					String locator = (String) data[row][2];
					if (identifier.equalsIgnoreCase("xpath")) {
						return driver.findElement(By.xpath(locator));
					} else if (identifier.equalsIgnoreCase("id")) {
						return driver.findElement(By.id(locator));
					} else if (identifier.equalsIgnoreCase("name")) {
						return driver.findElement(By.name(locator));
					} else if (identifier.equalsIgnoreCase("cssSelector")) {
						return driver.findElement(By.cssSelector(locator));
					}
				}
			}
			return null;

		}
	 
	 
	
	
	public List<WebElement> ReadObjects(String VariableName,String SheetName)
	{
		ArrayList<String>	 ObjectList=new ArrayList<String>();
		
		ObjectList=	exceldata.ReturnExcelRowandColDataAsArray(SheetName);
		
		if(ObjectList.get(0).equalsIgnoreCase(VariableName))
		{
			
			String identifier = ObjectList.get(1);
			String locator =ObjectList.get(2);;
			if (identifier.equalsIgnoreCase("xpath")) {
				return driver.findElements(By.xpath(locator));
			} else if (identifier.equalsIgnoreCase("id")) {
				return driver.findElements(By.id(locator));
			} else if (identifier.equalsIgnoreCase("name")) {
				return driver.findElements(By.name(locator));
			} else if (identifier.equalsIgnoreCase("cssSelector")) {
				return driver.findElements(By.cssSelector(locator));
			}
		}
	
	return null;

}
	

	public static  String readobjectPath(String key,String SheetName) throws IOException {
		
		
		//System.out.println(key);
	//	System.out.println(exceldata.getRowCount(SheetName));
		
		
		
		for(int i=0;i<exceldata.getRowCount(SheetName);i++)
		{
			ArrayList<String> al=new ArrayList<String>();
			
			al=exceldata.ReturnExcelRowAsArray(SheetName, i);
			
			if(al.get(0).equalsIgnoreCase(key))
			{
				return al.get(2);
			}
			
		}
		return null;
		
	}
				
			
	public static boolean isTestExecutable(String testName,String SheetName) throws IOException{
		
		if(readobjectPath(testName,SheetName).equalsIgnoreCase("Y"))
		{
			return true;	
			
		}
		
		return false;
	}
			
			
	public void HandleWebTable() throws InterruptedException
	{
		
		
		WebDriver driver=new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.get("https://online.actitime.com/floreat/");
		
		driver.findElement(By.id("username")).sendKeys("swathijkm");
		
		driver.findElement(By.name("pwd")).sendKeys("XybYrEra");
		
		driver.findElement(By.id("loginButton")).click();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//span[@class='dashedLink'][@onclick='openAddNewTasksPopup();']")).click();
		
		driver.findElement(By.xpath("//div[@id='createTasksPopup_customerSelector']")).click();
		
		driver.findElement(By.xpath(".//*[contains(text(),'Architects Bureau')]")).click();
		
		driver.findElement(By.xpath("//div[@id='createTasksPopup_projectSelector']")).click();
		
		driver.findElement(By.xpath(".//*[contains(text(),'- ALL ACTIVE PROJECTS -')]")).click();
		
		//Creating the loop and entering the new customer details one by one
		
		int NewTaskCounts=driver.findElements(By.xpath(".//*[@id='createTasksPopup_createTasksTableContainer']/table/tbody/tr")).size();
		
		for(int i=1;i<=NewTaskCounts;i++)
		{
			
	      String SelectDate="09/15/2017";
	      
	      Date d=new Date(SelectDate);
	      
	      SimpleDateFormat formatD=new SimpleDateFormat("MMM/dd/yyyy");
	      
	      String date=formatD.format(d);
	      
	      System.out.println(date);
	      
	      String[] split=date.split("/");
	      
	      System.out.println(split[0]+","+split[1]+","+split[2]);
	      
	      String year=split[2];
	      
	      System.out.println(year);
	      
         String month=split[0];
	      
	      System.out.println(month);
	      
	      String Date=split[1];
	      
	      System.out.println(Date);
	      
	      
	      String xpath1=".//*[@id='createTasksPopup_createTasksTableContainer']/table/tbody/tr[";
	      
	      String xpath2="]";
	      
	      String EnterTaskNameXpath=xpath1+i+xpath2+"/td[1]/input";
	      
	      driver.findElement(By.xpath(EnterTaskNameXpath)).sendKeys("task1");
	      
	      String Notneededxpath=xpath1+i+xpath2+" /td[3]/input";
	      
	      driver.findElement(By.xpath(Notneededxpath)).sendKeys("12hrs");
	      
	      String deadlinexpath=xpath1+i+xpath2+"/td[4]";
	      
	      driver.findElement(By.xpath(deadlinexpath)).click();
	      
	      if(i>=1)
	      {
	    	  driver.findElements(By.xpath("//div[@class='x-date-picker x-unselectable atap-base-date-picker atap-date-picker']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button")).get(i-1).click();
	    	     	  
	      }
	      
	      //Clicking on the month
	      System.out.println("Clicking on the month"+month);
	      driver.findElement(By.linkText(month)).click();
	      
	      System.out.println("Clicking on the month"+year);
	      driver.findElement(By.linkText(year)).click();
	      
	      if(i>=1)
	      {
	    	  driver.findElements(By.xpath("//button[@class='x-date-mp-ok']")).get(i-1).click();
	    	  
	      }
	      
	      System.out.println("Clicking on the date"+Date);
	      driver.findElement(By.linkText(Date)).click();
	      
	      String BilableXpath=xpath1+i+xpath2+"/td[1]/following-sibling::td[4]";
	      
	      driver.findElement(By.xpath(BilableXpath)).click();
	      
	      if(i==1)
	      {
	    	  driver.findElements(By.xpath("//div[@class='x-layer x-menu at-dropdown-list-btn-menu billingTypeSelectorMenu addNewTasks']/ul/li")).get(1).click();
	    	  
	      }
	      
	      if(i==2)
	      {
	    	  driver.findElements(By.xpath("//div[@class='x-layer x-menu at-dropdown-list-btn-menu billingTypeSelectorMenu addNewTasks']/ul/li")).get(12).click();
	    	  
	      }
	      
	      if(i==3)
	      {
	    	  driver.findElements(By.xpath("//div[@class='x-layer x-menu at-dropdown-list-btn-menu billingTypeSelectorMenu addNewTasks']/ul/li")).get(22).click();
	    	  
	      }
	          
		}
		}
		
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
