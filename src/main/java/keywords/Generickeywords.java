package keywords;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Extentmanager.Extentreporting;

public class Generickeywords {
	public WebDriver driver;
	public Properties prop;
	public Properties envprop;
	public ExtentTest test;
	public SoftAssert sa;
	
	public void openbrowser(String browserkey) {
		
		String grid="N";
		String browser= (String) prop.getProperty(browserkey);
		log("Opening browser");
		if(grid.equalsIgnoreCase("y"))
		{
			DesiredCapabilities cap= new DesiredCapabilities();
			ChromeOptions opt=new ChromeOptions();
			if((browser).equalsIgnoreCase("chrome"))
			{
				cap.setBrowserName("Chrome");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
				opt.addArguments("--start-maximized");
				opt.addArguments("--ignore-certificate-errors");
				opt.addArguments("--disable-notifications");
				opt.setCapability("platformName", "Windows 10");
				opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
			}
			
			try {
				
				driver=new RemoteWebDriver(new URL("http://localhost:4444"),opt);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else {
		if((browser).equalsIgnoreCase("chrome"))
		{
			ChromeOptions opt=new ChromeOptions();
			opt.addArguments("--start-maximized");
			opt.addArguments("--ignore-certificate-errors");
			opt.addArguments("--disable-notifications");
			opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver=new ChromeDriver(opt);
			
		}
		else if(browser.toUpperCase().equals("IE")) {
			driver=new InternetExplorerDriver();
			}
		else if(browser.toUpperCase().equals("FIREFOX")) {
			ProfilesIni allprof=new ProfilesIni();
			FirefoxProfile profile=	allprof.getProfile("Oct21");
			profile.setPreference("dom.webnotifications.enabled", false);
			FirefoxOptions options= new FirefoxOptions();
			options.setProfile(profile);
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver=new FirefoxDriver(options);
		}}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
	}

	public void navigate(String urlkey) {
		
		String url=(String) envprop.getProperty(urlkey);
		log("Navigating to url" +url);
		System.out.println(urlkey+"------"+url);
		driver.get(url);
		
	}
	
	public void click(String locatorkey) {
		
		log("Clicking on element" +locatorkey);
		findwebelement(locatorkey).click();
		
	}
	
	public void type(String locatorkey,String datakey) {
		
		String data;
		if((datakey.equalsIgnoreCase("username")) ||  (datakey.equalsIgnoreCase("password")))
		{
			data=(String)envprop.getProperty(datakey);
		}
		/*else if(datakey.equals()||datakey.equalsIgnoreCase("price"))
		{
			data=datakey;
		}
		else {
		data=(String)prop.getProperty(datakey);
		}*/
		else
		{
			data=datakey;
		}
		
		log("Typing data- " +data);
		findwebelement(locatorkey).sendKeys(data);
		
	}
	
	public WebElement findwebelement(String locatorkey) {
		
		//String locator= (String) prop.get(locatorkey);
		//System.out.println(locator);
		
		if(!iselementpresent(locatorkey))
		{
			System.out.println("element not present-" +locatorkey);
		}
		
		if(!iselementvisible(locatorkey))
		{
			System.out.println("element not visible-" +locatorkey);
		}
		
		
		WebElement element= driver.findElement(locatorkey(locatorkey));
		return element;
	}
	
	public boolean iselementpresent(String locatorkey) {
		
		try {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(locatorkey(locatorkey)));
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}


public boolean iselementvisible(String locatorkey) {
	
	try {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locatorkey(locatorkey)));
	}
	catch(Exception e)
	{
		return false;
	}
	
	return true;
}

public By locatorkey(String locatorkey) {
	
	By by=null; 
	String locator=(String)prop.getProperty(locatorkey);
	if(locatorkey.endsWith("_id"))
	{
	   by= By.id(locator);
	}
	else if(locatorkey.endsWith("_xpath"))
	{
		by= By.xpath(locator);
	}
	else if(locatorkey.endsWith("_css"))
	{
		by= By.cssSelector(locator);
	}
	else if(locatorkey.endsWith("_linktext"))
	{
		by= By.linkText(locator);
	}
	
	return by;
	
	
	
	
}

public void getreport(ExtentTest test)
{
   this.test=test;	
}

public void log(String msg) {
	
	test.log(Status.INFO, msg);
	
}

public void reportfail(String msg,boolean flag)
{
    test.log(Status.FAIL, msg);
	sa.fail(msg);
	if(flag)
	{
		takescreenshot();
		assertall();
	}
	
}

public void assertall()
{
Reporter.getCurrentTestResult().getTestContext().setAttribute("critical", "Y");
takescreenshot();
sa.assertAll();	
}

public void takescreenshot() {
	
	Date d=new Date();
	String screenshot= d.toString().replace(":", "_").replace(" ", "_")+".png";
	File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(srcfile, new File(Extentreporting.path+"//"+screenshot));
		test.log(Status.INFO, "Screenshot->" +test.addScreenCaptureFromPath(Extentreporting.path+"//"+screenshot));
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void clear(String locatorkey)
{
   log("Clearing data in field");
   findwebelement(locatorkey).clear();
	

}

public void waitforpagetoload()
{
	JavascriptExecutor js= (JavascriptExecutor)driver;
	
	for(int i=0;i<10;i++)
	{
	String state= (String) js.executeScript("return document.readyState");
	if(state.equalsIgnoreCase("complete"))
		break;
	else
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("waiting for page to load");
		}
	    
	i++;
	}

  int i=0;
  Boolean jquery=false;
  while(i!=10) {
 try {
	  jquery= (Boolean) js.executeScript("return window.jQuery !=undefined && jquery.active == 0");}
 catch(Exception e){
	 System.out.println("waiting for jquery to load");
 }
  if(jquery)
  {
	  break;
  } else
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  i++;


}
}

public void selectvaluefromdropdown(String locatorkey, String data)
{
    log("Checking"+data+" in dropdown");
	WebElement dropdown= findwebelement(locatorkey);
    Select sp= new Select(dropdown);
    sp.selectByVisibleText(data);
    //waitforpagetoload();
    
	
}

public void acceptalert()
{
	driver.switchTo().alert().accept();	
}

public void pressenter(String locatorkey)
{
 
	findwebelement(locatorkey).sendKeys(Keys.ENTER);
	
}

public int getrownumberfromcelldata(String locatorkey,String data)
{
	WebElement table=findwebelement(locatorkey);
	List<WebElement> rows= table.findElements(By.tagName("tr"));
	System.out.println(rows.size());
	for(int rNum=0;rNum<rows.size();rNum++)
	{
		WebElement row= rows.get(rNum);
		List<WebElement> cells= row.findElements(By.tagName("td"));
		System.out.println(cells.size());
		for(int cNum=0;cNum<cells.size();cNum++)
		{
			WebElement cell=cells.get(cNum);
			String name=cell.getText();
			System.out.println(name);
			if(name.startsWith((data))) {
			System.out.println(name+"------"+rNum);
			return (rNum+1);
			}
		
		}
		
		
	}
	
	return -1;

}

public List<WebElement> findelements(String locatorkey)
{

	List<WebElement> list= driver.findElements(locatorkey(locatorkey));
	return list;

}

public void waitpls()
{
   try {
	Thread.sleep(5000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
}

public void waitforajaxinaddstock() {
	
	try{WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ajax_listOfOptions > div")));}
	catch(Exception e)
	{
		reportfail("Ajax component didnot load", true);
	}
	//driver.findElements(By.cssSelector("#ajax_listOfOptions > div"));
	
}

public void quit()
{
driver.quit();	
}


}
