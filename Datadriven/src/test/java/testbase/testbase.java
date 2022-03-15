package testbase;

import java.io.IOException;

import javax.security.auth.login.LoginContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Extentmanager.Extentreporting;
import Runner.Datautil;
import keywords.Applicationkeywords;

public class testbase {
	
	public Applicationkeywords app;
	public int i;
	public ExtentReports report;
	public ExtentTest test;
    
@BeforeTest(alwaysRun = true)
public void beforetest(ITestContext con) throws NumberFormatException, IOException, ParseException {
	
	String datafilepath= con.getCurrentXmlTest().getParameter("datafilepath");
	String dataflag= con.getCurrentXmlTest().getParameter("dataflag");
	String iteration= con.getCurrentXmlTest().getParameter("iteration");
	Datautil d=new Datautil();
	JSONObject data=(JSONObject) d.gettestdata(datafilepath, dataflag, Integer.parseInt(iteration));
	con.setAttribute("data", data);
	String runmode= (String)data.get("runmode");
	
	System.out.println("before test");
	app=new Applicationkeywords();
	con.setAttribute("key", app);
	report= Extentreporting.getreport();
	test=report.createTest(con.getCurrentXmlTest().getName());
	app.getreport(test);
	app.log("Starting test-" +con.getCurrentXmlTest().getName());
	con.setAttribute("rep", report);
	con.setAttribute("test", test);
	
	if(runmode.equals("N"))
	{
		test.log(Status.SKIP, "Skipping as datmode:N");
		throw new SkipException("Skipping as datmode:N");
	}
	
	app.login();
	app.waitforpagetoload();
	
	
	
	
	
}

@BeforeMethod
public void beforemethod(ITestContext con) {
	test= (ExtentTest) con.getAttribute("test");
	app= (Applicationkeywords)con.getAttribute("key");
	report= (ExtentReports)con.getAttribute("rep");
	System.out.println("before method");
	JSONObject data= (JSONObject)con.getAttribute("data");
	
	String stockname=(String)data.get("stockname");
	String date=(String)data.get("date");
	String quantity=(String)data.get("quantity");
	String price=(String)data.get("price");
	con.setAttribute("stockname", stockname);
	con.setAttribute("date", date);
	con.setAttribute("quantity", quantity);
	con.setAttribute("price", price);
	
	
	
	String flag=(String)con.getAttribute("critical");
	if(flag!=null && flag.equalsIgnoreCase("Y"))
	{
		test.log(Status.SKIP, "Critical error in previous test");
		throw new SkipException("Critical error in previous test");
		
	}
	
	
	
	
	
}

@AfterTest(alwaysRun = true)
public void flush(ITestContext con) {
	
    System.out.println("aftertest");   
	app=(Applicationkeywords)con.getAttribute("key");
	app.quit();
    report.flush();
       //app.assertall();
}



	



}


