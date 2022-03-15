package testcase;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import com.aventstack.extentreports.Status;

import keywords.Applicationkeywords;
import testbase.testbase;

public class Session extends testbase{
	
	
	public void login(ITestContext con)
	{
	
	//ap= new Applicationkeywords();
	//app.openbrowser("chrome");
	//app.navigate("https://app.usertesting.com/users/sign_in");
    	
	System.out.println("login");
	app.log("Logging in");
	//app.reportfail("summa",false);
	//Applicationkeywords bap= (Applicationkeywords)con.getAttribute("key");
	//app.openbrowser("chrome");
	app.openbrowser("browser");
	app.navigate("url");
	//driver.findElement(By.linkText("Sign In")).click();
	app.click("sigin_linktext");
	app.type("username_id","username");
	app.type("password_id","password");
	app.click("submit_id");
	
	//app.navigate("https://app.usertesting.com/users/sign_in");
	//i=100;
	
	
	}
	
	
	public void logout() {
		
		//app.navigate("https://www.youtube.com");
		System.out.println("logout");
		app.log("Logging out");
		System.out.println(i);
	}

}
