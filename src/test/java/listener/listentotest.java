package listener;

import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class listentotest implements ITestListener{

/*	
	public void onTestFailure(ITestResult result) {
		
		System.out.println("Test failed" +result.getName());
	}
	*/
   
	public void onTestFailure(ITestResult result)
	{
		System.out.println(" Test Failed-" +result.getName());
		System.out.println(result.getThrowable().getMessage());
		ExtentTest test= (ExtentTest)result.getTestContext().getAttribute("test");
		test.log(Status.FAIL, "from listener-" +result.getThrowable().getMessage());
}
	
	public void onTestSkipped(ITestResult result)
	{
		System.out.println(" Test Skipped-" +result.getName());
		//System.out.println(result.getThrowable().getMessage());
		ExtentTest test= (ExtentTest)result.getTestContext().getAttribute("test");
		test.log(Status.SKIP, "from listener- " );
	}
	
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("Test Passed" +result.getName());
		ExtentTest test= (ExtentTest)result.getTestContext().getAttribute("test");
		test.log(Status.PASS, "from listener- Test Success" +result.getName());
		
	}
}
