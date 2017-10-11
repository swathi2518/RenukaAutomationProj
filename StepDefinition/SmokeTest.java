package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.PendingException;

public class SmokeTest {
	
	
	WebDriver driver;
	@Given("^Open Chrome/Firefox$")
	public void Open_Chrome_Firefox() throws Throwable {
	    // Express the Regexp above with the code you wish you had
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Given("^nd start application$")
	public void nd_start_application() throws Throwable {
	  
		driver.get("http://www.facebook.com");
	}
/*
	@When("^I Entered Valid \"([^\"]*)\"$")
	public void I_Entered_Valid_UN(String uname) throws Throwable {
	   
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(uname);
	}

	@When("^I Entered Valid \"([^\"]*)\"$")
	public void valid_Password(String pwd) throws Throwable {
	  
		
		
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(pwd);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//label[@id='loginbutton']")).click();
		
	}*/
	
	
	
	
	
	@When("^user enters \"([^\"]*)\"and \"([^\"]*)\"$")
	public void user_enters_andpassword(String username, String password) throws Throwable {
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//label[@id='loginbutton']")).click();
}
	


	@Then("^User shoud be Able to Login Succesful$")
	public void User_shoud_be_Able_to_Login_Succesful() throws Throwable {
	  
	

		
		System.out.println("Login in Succesfully");
	}

	

}
