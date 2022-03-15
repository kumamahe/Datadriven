package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.netty.handler.ssl.JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory;
import keywords.Applicationkeywords;
import testbase.testbase;

public class portfoliomanagement extends testbase{
	
	@Test
	public void createportfolio(ITestContext con) {
		
		System.out.println("Createportfolio");
		app.log("Creating portfolio");
		//driver.findElement(By.id("createPortfolio")).click();
		app.click("createport_id");
		app.waitforpagetoload();
		//driver.findElement(By.id("create")).clear();
		app.clear("create_id");
		//driver.findElement(By.id("create")).sendKeys(expected);
		app.type("create_id", "Newportfolio");
		//driver.findElement(By.id("createPortfolioButton")).click();
		app.click("createPortfolioButton_id");
		app.waitforpagetoload();
		app.verifyvalueindropdown("portfolio_id", "Kavian1995");
		
		//Thread.sleep(3000);
		//app.reportfail("Summa inonu",false);
		//System.out.println(i);
		//app.reportfail("critical failure", false);
		//int i=100/0;
		//app.reportfail("Summa oru failure");
		//Applicationkeywords cap= (Applicationkeywords)con.getAttribute("key");
		//app.navigate("https://www.whizdomtraining.com/");
		//app.openbrowser("browser");
		//app.navigate("https://www.youtube.com");
		//app=new Applicationkeywords();	
		//app.login("browser", "url", "username", "password");      //,"https://money.rediff.com/index.html" , "kavian95@rediffmail.com", "Jones95#");
		//app.assertall();
		
	}
	
	@Test
	public void deleteportfolio() {
		
		System.out.println("Deleteportfolio");
		app.log("Deleting portfolio");
		app.selectvaluefromdropdown("portfolio_id", "Kavian1995");
		app.click("deletePortfolio_id");
		//driver.findElement(By.id("deletePortfolio")).click();
		
		//app.waitforpagetoload();
		app.acceptalert();
		app.waitforpagetoload();
		
		app.verifyvaluenotindropdown("portfolio_id","Kavian1995");
		
		
		//app.driver.switchTo().alert().accept();
		//driver.switchTo().alert().accept();
		
	}
	

}
