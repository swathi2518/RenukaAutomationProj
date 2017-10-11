package PageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utility.Library;
import DataProvider.ReadExcelData;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class demovtigerPageObjects {

	public WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	Library help = new Library();

	ReadExcelData readexcel = new ReadExcelData("./TestData/Locators.xlsx");

	public demovtigerPageObjects(WebDriver driver, ExtentTest test) {

		this.driver = driver;
		this.test = test;

	}

	public void OpenVtiger() throws IOException {

		System.out.println(help.readConfigFile("WebURL", "frameworkConfig"));
		driver.get(help.readConfigFile("WebURL", "frameworkConfig"));

	}

	public void EnterValidLoginDetails() throws IOException,
			InterruptedException {

		help.Clear(help.readObjectLocator("Name"));

		Thread.sleep(10000);

		help.type(help.readObjectLocator("Name"), readexcel.getStringData("LoginData", 1, 0));

		help.Clear(help.readObjectLocator("pwd"));

		help.type(help.readObjectLocator("pwd"), readexcel.getStringData("LoginData", 1, 1));
		
	
		help.Buttonclick(help.readObjectLocator("Signin"));

	}

	/*public void EnterInValidLoginDetails() throws IOException,
			InterruptedException {

		String UsernameL = help.readobjectPath("Name", "Elements");

		String pwdL = help.readobjectPath("pwd", "Elements");

		String Errormessage = help.readobjectPath("Errorvalidate", "Elements");

		String ButtonL = help.readobjectPath("Signin", "Elements");

		Thread.sleep(5000);

		help.Clear(UsernameL);

		Thread.sleep(1000);

		help.type(UsernameL, readexcel.getStringData("LoginData", 2, 0));

		help.Clear(pwdL);

		help.type(pwdL, readexcel.getStringData("LoginData", 2, 1));

		help.Buttonclick(ButtonL);

		System.out
				.println(driver.findElement(By.xpath(Errormessage)).getText());

		String Errormsg = driver.findElement(By.xpath(Errormessage)).getText();

		Assert.assertEquals(Errormsg, "Invalid credentials");

	}

	public void EnterBlankLoginDetails() throws IOException,
			InterruptedException {
		// System.out.println(readexcel.getStringData("Locators", 1, 2));
		// System.out.println(help.readobjectPath("Name", "Elements"));

		String UsernameL = help.readobjectPath("Name", "Elements");

		String pwdL = help.readobjectPath("pwd", "Elements");

		String ButtonL = help.readobjectPath("Signin", "Elements");

		String Errormessage = help.readobjectPath("Errorvalidate", "Elements");

		help.Clear(UsernameL);

		Thread.sleep(1000);

		help.Clear(pwdL);

		help.Buttonclick(ButtonL);

		System.out	.println(driver.findElement(By.xpath(Errormessage)).getText());

		String Errormsg = driver.findElement(By.xpath(Errormessage)).getText();

		Assert.assertEquals(Errormsg, "Please enter valid username");

	}

	public void VerifyTitle() throws InterruptedException {
		Thread.sleep(5000);

		System.out.println(driver.getTitle());

		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Dashboard"),
				"Verify title doesnt match");

	}

	public void Signout() throws IOException, InterruptedException {
		driver.findElement(
				By.xpath(help.readobjectPath("UserButton", "Elements")))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(help.readobjectPath("Logout", "Elements")))
				.click();

	}
*/
}
