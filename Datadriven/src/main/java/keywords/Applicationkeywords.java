package keywords;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;


public class Applicationkeywords extends Validationkeywords{
	
	//public Properties prop;
	
	public Applicationkeywords() {
		
		prop= new Properties(); 
		envprop=new Properties();
		FileInputStream fis,fis2=null;
		
		String path= System.getProperty("user.dir")+ "//src//main//resources//prop.properties";
		try {
			fis= new FileInputStream(path);
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String envpath= System.getProperty("user.dir")+"//src//main//resources//"+prop.getProperty("flag")+".properties";
	//	System.out.println(envpath);
		try {
			fis2= new FileInputStream(envpath);
			envprop.load(fis2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sa=new SoftAssert();
		
		
				
	}
	
	
	
	public void login() {
	    
		//System.out.println("login");
		log("Logging in");
		//app.reportfail("summa",false);
		//Applicationkeywords bap= (Applicationkeywords)con.getAttribute("key");
		//app.openbrowser("chrome");
		openbrowser("browser");
		navigate("url");
		//waitforpagetoload();
		//driver.findElement(By.linkText("Sign In")).click();
		click("sigin_linktext");
		type("username_id","username");
		type("password_id","password");
		click("submit_id");
		
		
}
	
public void selectdate(String x) {
		
		Date datetobeselected=null;
		Date d=new Date();
		System.out.println(d);
		SimpleDateFormat date= new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(date);
		try {
			datetobeselected= date.parse(x);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(datetobeselected);
		
		String inui= driver.findElement(By.xpath("//div[@class='dpTitleText']")).getText();
		System.out.println(inui);
		String day= new SimpleDateFormat("d").format(datetobeselected);
		String month= new SimpleDateFormat("MMMM").format(datetobeselected);
		String year= new SimpleDateFormat("yyyy").format(datetobeselected);
		
		String toui= month+" "+year;
		
		System.out.println(toui);
		
		while(!inui.equals(toui))
		{
			if(d.compareTo(datetobeselected)==1)
			{
				driver.findElement(By.xpath("//button[text()='<']")).click();
			}
			else if(d.compareTo(datetobeselected)==-1)
			{
				driver.findElement(By.xpath("//button[text()='>']")).click();
			}
			
			inui= driver.findElement(By.xpath("//div[@class='dpTitleText']")).getText();
		}
		
		driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
		
		
		
		
		
		
	}

public void selectstock(String companyname)
{

	int rNum=getrownumberfromcelldata("selectstock_xpath", companyname);
	if(rNum==-1)
	{
	   reportfail("Stock "+companyname+ "is not displayed in table", true);	
	}
	driver.findElement(By.xpath(prop.getProperty("selectstock_xpath")+"/tr["+rNum+"]/td")).click();
	
}

public void selectbuysell(String companyname,String action)
{

	int rNum=getrownumberfromcelldata("selectstock_xpath", companyname);
	driver.findElement(By.xpath(prop.getProperty("selectstock_xpath")+"/tr["+rNum+"]/td[3]//input")).click();
	waitforpagetoload();	
	WebElement dropdown= findwebelement("equityaction_id");
	Select sp= new Select(dropdown);
	sp.selectByVisibleText(action);
	
	
}

public int getstockquantity(String companyname)

{

	int rNum=getrownumberfromcelldata("selectstock_xpath", companyname);
	String quantity=driver.findElement(By.xpath(prop.getProperty("selectstock_xpath")+"/tr["+rNum+"]/td[4]/span")).getText();
	int stockquantity=Integer.parseInt(quantity);
	return stockquantity;
	

}

public void selecttransactionhistory(String companyname)
{

	int rNum=getrownumberfromcelldata("selectstock_xpath", companyname);
	if(rNum==-1)
	{
		//log("Stock not displayed");
		reportfail("Stock "+companyname+" is not displayed", true);
		
	}
	
	driver.findElement(By.xpath(prop.getProperty("selectstock_xpath")+"/tr["+rNum+"]/td[3]//input[3]")).click();
}
	
public void verifytransactionhistory(int stockprice, int stockquantity, String action)
{

	String price= driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]")).getText();
	int thprice=Integer.parseInt(price);
	String quantity= driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr/td[3]")).getText();
	int thquantity=Integer.parseInt(quantity);
	if(!(thprice==stockprice))
		{
			reportfail("Stock price("+thprice+") in transaction history doesnot match with stock price("+stockprice+") entered", false);
		}
	else
	{
		log("Stock price("+thprice+") in transaction history matches with stock price("+stockprice+") entered");
	}
	
	if(action.equalsIgnoreCase("buy"))
	{if(!(thquantity==stockquantity))
	{
		reportfail("Stock quantity("+thquantity+") in transaction history doesnot match with stock quantity("+stockquantity+") entered", false);
		
	} else
	{
		
		log("Stock quantity("+thquantity+") in transaction history matches with stock quantity("+stockquantity+") entered");
		
	}}
	else if(action.equalsIgnoreCase("sell"))
	{
		if(!(thquantity==(-stockquantity)))
		{
			reportfail("Stock quantity("+thquantity+") in transaction history doesnot match with stock quantity("+stockquantity+") entered", false);
			
		} else
		{
			
			log("Stock quantity("+thquantity+") in transaction history matches with stock quantity("+stockquantity+") entered");
			
		}
	}
}
	
	
	
	
	
	
	

	public void verifystockadded() {
		
		
	}
}

