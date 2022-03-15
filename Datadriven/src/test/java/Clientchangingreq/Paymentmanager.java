package Clientchangingreq;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Paymentmanager {
	
	@Parameters({"action"})
	@Test
	public void makepayment(String type,ITestContext context) {
		if(type.equalsIgnoreCase("instantpay")) {
		System.out.println("making payment");
			
		}
		else
			System.out.println("payment will be made at hotel");
		
		context.setAttribute("bookingid", "abc123");
	}
	
	@Parameters({"action"})
	@Test
	public void applydiscount(String type) {
		if(type.equalsIgnoreCase("instantpay"))
		System.out.println("applying discount");
		else
			System.out.println("No discount for payment at hotel");
	}
	
	

}
