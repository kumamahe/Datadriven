package Runner;

import java.util.ArrayList;
import java.util.List;

public class runusingfun {
	
	public static void main(String[] args) {
		
		Runnerhelper rn=new Runnerhelper(1);
		rn.createsuite("Suite",false);
		rn.createtest("BookingTest-Apply discount-Instant pay");
		List<String> methods= new ArrayList<String>();
		methods.add("searchhotel");
		methods.add("selectroom");
		methods.add("enterguestinfo");
		
		rn.createclass("Clientchangingreq.Bookingsearch",methods);
		//rn.createmethod("searchhotel",1);
		//rn.createmethod("selectroom",2);
		//rn.createmethod("enterguestinfo",3);
		//rn.testng.run();
		//rn.createclass("Clientchangingreq.customercommunication");
		rn.testng.run();
		
	}

}
