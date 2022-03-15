package keywords;

import java.net.StandardProtocolFamily;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Validationkeywords extends Generickeywords{
	
	public void verifyvalueindropdown(String locatorkey, String data)
	{
		log("Verifying value in dropdown");
		WebElement dropdown= findwebelement(locatorkey); 
		Select dp= new Select(dropdown);
		String actual= dp.getFirstSelectedOption().getText();
		//System.out.println(actual);
		if(!actual.equalsIgnoreCase(data))
			reportfail("Value not displayed in dropdown-critical error", true);
		
		log("Data '"+data+"' displayed in dropdown");
		
		//Assert.assertTrue(expected.equalsIgnoreCase(actual), "Created Portfolio not reflected in page");
		
	}
	

	public void verifyvaluenotindropdown(String locatorkey, String data)
	{
		log("Verifying value not in dropdown");
		WebElement dropdown= findwebelement(locatorkey); 
		Select dp= new Select(dropdown);
		try {
		dp.selectByVisibleText(data);
		reportfail("Value displayed in dropdown-critical error", true);
		}catch(Exception e)
		{
			log("Data '"+data+"' not displayed in dropdown");
		}
		//System.out.println(actual);
		
		//Assert.assertTrue(expected.equalsIgnoreCase(actual), "Created Portfolio not reflected in page");
		
	}
	
	
	public void validatetitle() {
		
		
	}
	
	public void validatetext(){ 
	
	}
	
}

