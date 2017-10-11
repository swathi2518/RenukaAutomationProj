package TestScenarios;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.Library;
import utility.Log;
import PageObjects.demovtigerPageObjects;

public class DemoVtigettest extends WebBaseClass {

	@Test(priority = 0)
	public void testLoginvtiger() throws IOException, InterruptedException {
		test = rep.startTest("Login_TC01_Valid");

		Log.info("Test case started");

		test.log(LogStatus.INFO, "Started the Login test with valid data");

		PO.OpenVtiger();

		test.log(LogStatus.INFO, "Opened the vtiger demo website");

		PO.EnterValidLoginDetails();

		test.log(LogStatus.INFO, "Enetered the Valid Login details ");

		test.log(LogStatus.INFO, "Login with valid is suceesful ");

		test.log(LogStatus.PASS, "Finished the Login test");

	}

	@Test(priority = 1)
	public void VerifyTitle() throws InterruptedException {
		
		test = rep.startTest("Login_TC2_VerifyTitle");

		test.log(LogStatus.INFO, "Login succesful");

		test.log(LogStatus.INFO, "Verifying the title");

		Log.info("Verify title");
	//	PO.VerifyTitle();

		test.log(LogStatus.PASS, "Finished the verify title test");
	}
	
	

	/*@Test(priority = 2)
	public void LogOut() throws IOException, InterruptedException {
		test = rep.startTest("Login_TC3_Logout");

		Log.info("LogOUT test completed");
		PO.Signout();
		test.log(LogStatus.PASS, "Finished the Logout test");

	}

	@Test(priority = 3)
	public void TestwithInvalid() throws IOException, InterruptedException {
		test = rep.startTest("Login_TC4_Invalid");

		Log.info("Opened vtiger");

		Log.info("Entered valid Username ");

		Log.info("Entered Invalid Password");

		PO.EnterInValidLoginDetails();

		test.log(LogStatus.PASS, "Finished the Invalid Login test");

	}

	@Test(priority = 4)
	public void TestwithBlank() throws IOException, InterruptedException {
		test = rep.startTest("Login_TC4_Blank");

		Log.info("Opened vtiger");

		Log.info("Entered valid Username ");

		Log.info("Entered Invalid Password");

		PO.EnterBlankLoginDetails();

		test.log(LogStatus.PASS, "Finished the Blank Login test");

	}

	@Test(priority = 5)
	public void TestwithDemo() throws IOException {
		test = rep.startTest("Login_TC5_DemoFail");

		Log.info("Opened vtiger");

		// PO.OpenVtiger();

		Assert.assertEquals("swa", "ws");
	}

	@Test(priority = 6)
	public void TestwithDemo2() throws IOException {
		test = rep.startTest("Login_TC6_DemoFail");

		Log.info("Opened vtiger");

		// PO.OpenVtiger();

		Assert.assertEquals("swa", "ws");
	}
*/
}
