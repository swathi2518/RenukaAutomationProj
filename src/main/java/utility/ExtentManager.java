package utility;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	
	private ExtentManager(){}

	static String ReportPath="./AdvanceReports/";
	public static Date d= new Date();
	public static String fileName=ReportPath+d.toString().replace(":", "_").replace(" ", "_")+".html";
	public static String ReportName=d.toString().replace(":", "_").replace(" ", "_")+".html";
	
	public static ExtentReports getInstance() {
		if (extent == null) {
			
			extent = new ExtentReports(fileName, true, DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53").addSystemInfo(
					"Environment", "PROD");
		}
		return extent;
	}
}		                               
