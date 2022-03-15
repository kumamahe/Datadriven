package testcase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import testbase.testbase;

public class Stockmanagement extends testbase{
	
	@Test
	public void addstock(ITestContext con) {
		
		String stockname= "Tata Motors";
		String date= "01-01-2018";
		int addstock=10;
		int stockprice=150;
		
	    //waitforpagetoload();
		//driver.findElement(By.id("addStock")).click();
		app.log("Adding stock-" +stockname);
		app.click("addStock_id");
		//driver.findElement(By.id("addstockname")).sendKeys("Tata");
		app.type("addstockname_id", "stockname");
		app.waitforajaxinaddstock();
		app.pressenter("addstockname_id");
		//driver.findElement(By.xpath("//div[text()='"+stockname+"']")).click();
		app.click("stockPurchaseDate_id");
		//driver.findElement(By.id("stockPurchaseDate")).click();
		app.selectdate(date);
		
		//driver.findElement(By.id("addstockqty")).sendKeys("10");
		app.type("addstockqty_id", "addstock");
		//driver.findElement(By.id("addstockprice")).sendKeys("150");
		app.type("addstockprice_id", "stockprice");
		
		//driver.findElement(By.id("addStockButton")).click();
		app.click("addStockButton_id");
		app.waitforpagetoload();
		con.setAttribute("quantity", addstock);
		
		//String todelete="Tata Steel";
		
	}
	
	@Parameters ({"action"})
	@Test
	public void modifystock(ITestContext con,String action) {
		
		String stockname= (String)con.getAttribute("stockname");
		String date= (String)con.getAttribute("date");
		String quantity=(String)con.getAttribute("quantity");
		String price=(String)con.getAttribute("price");
		
		app.log("modify stock");
		//System.out.println("modifystock");
        //List<WebElement> stocks= driver.findElements(By.xpath("//table[@id='stock']/tbody/tr/td[2]"));
	    app.waitforpagetoload();
		int quantity2= app.getstockquantity(stockname);
	    con.setAttribute("quantity", quantity2);
		app.selectstock(stockname);
		app.selectbuysell(stockname,action);
		app.click("buySellCalendar_id");
		//driver.findElement(By.xpath("//table[@id='stock']/tbody/tr["+(i+1)+"]/td[1]/input[@type='radio']")).click();
		
		//driver.findElement(By.xpath("//tr[@class='active_tr']//input[@name='Buy / Sell']")).click();
		//driver.findElement(By.id("buySellCalendar")).click();
		//app.click("buySellStockButton_id");
		
		app.selectdate(date);
		//driver.findElement(By.id("buysellqty")).sendKeys(buy);
		app.type("buysellqty_id", quantity);
		//driver.findElement(By.id("buysellprice")).sendKeys(buyprice);
		app.type("buysellprice_id", price);
		//driver.findElement(By.id("buySellStockButton")).click();
		app.click("buySellStockButton_id");
		//waitforpagetoload();
		//driver.findElement(By.xpath("//table[@id='stock']/tbody/tr["+(i+1)+"]/td[1]/input[@type='radio']")).click();
		//transactionhistory(buy,buyprice);			
		//break;
				
				
				
	}
		
	
	@Parameters ({"action"})
	@Test
	public void verifyquantity(ITestContext con,String action) {
		String stockname= (String)con.getAttribute("stockname");
		String date= (String)con.getAttribute("date");
		String quantity=(String)con.getAttribute("quantity");
		String price=(String)con.getAttribute("price");
		app.log("verifyquantity");
		app.waitforpagetoload();
        int currentquantity=app.getstockquantity(stockname);
        int beforequantity= (Integer) con.getAttribute("quantity");
        int modifiedquantity=0;
        
        if(action.equalsIgnoreCase("buy")) {
        modifiedquantity= currentquantity-beforequantity;
        }else if (action.equalsIgnoreCase("sell")) {
        modifiedquantity= beforequantity-currentquantity;
		}
        
        
        if(!(modifiedquantity==Integer.parseInt(quantity)))
        {
        	app.log("Current quantity in UI is:"+currentquantity+", Before quantity is in UI:"+beforequantity+",Modified quantity is:"+modifiedquantity);
        	app.reportfail("Stock added("+quantity+") is not reflecting correctly", false);
        	
        }
        
        app.log("Quanity is reflected as expected");
        con.setAttribute("quantity", quantity);
        
	}
	
	@Parameters({"action"})
	@Test
	public void verifytransactionhistory(String action,ITestContext con) {
		
		app.log("verifytransactionhistory");
		String stockname= (String)con.getAttribute("stockname");
		String date= (String)con.getAttribute("date");
		String quantity=(String)con.getAttribute("quantity");
		String price=(String)con.getAttribute("price");
		
		app.selectstock(stockname);
		app.selecttransactionhistory(stockname);
		app.waitforpagetoload();
		int modifystock=(Integer)con.getAttribute("quantity");
		app.verifytransactionhistory(Integer.parseInt(price), modifystock,action);
		app.click("closebutton_xpath");
		
			

	}

}
