package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listenerimplimentation implements ITestListener {
//class Listenerimplimentation <i>ITestListener
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "    -started");
		test = report.createTest(methodname);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "    -passed");
		test.log(Status.PASS,methodname+"----passed-----");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "    -skipped");
		test.log(Status.SKIP,methodname+"---skipped----");
		test.log(Status.INFO,result.getThrowable());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "    failed");
		test.log(Status.FAIL,methodname+"----failed-----");
		test.log(Status.INFO, result.getThrowable());

		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		String screenshot = methodname + "-" + jutil.toGetSytemDateAndTime();
		//driver and screen shot name we have to give 
		try {
		String	path = wutil.toTakeScreenshot(BaseClass.sDriver, screenshot);
		//listeners
		//sdrive =driver ;  here static driver calle here using baseclass name
		test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("-----suite excuition started");
		ExtentSparkReporter htmlreporter = new ExtentSparkReporter(
				"./ExtentReports/Reports" + new JavaUtility().toGetSytemDateAndTime() + ".html");
		htmlreporter.config().setDocumentTitle("vtiger excuition reports");
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setReportName("VTIGER EXCUITION REPORTS");
		//
		report = new ExtentReports();
		report.attachReporter(htmlreporter);
		report.setSystemInfo("Base Url", "http://localhost:8888/");
		report.setSystemInfo("base Browser", "Chrome");
		report.setSystemInfo("Username", "admin");
		report.setSystemInfo("password", "password");
		report.setSystemInfo("Reportername", "vijetha");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("-----suite excuition Finished");
		report.flush();
	}

}
