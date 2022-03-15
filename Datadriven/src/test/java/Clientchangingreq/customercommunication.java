package Clientchangingreq;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class customercommunication {

	
	@Test
	public void checkemail(ITestContext context) {
		
		System.out.println("checking email");
		System.out.println("Booking id from Payment module----"+ context.getAttribute("bookingid"));
		
	}
	
}
