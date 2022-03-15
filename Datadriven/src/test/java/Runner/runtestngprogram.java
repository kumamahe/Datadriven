package Runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class runtestngprogram {
	
	public static void main(String[] args) {
	
	TestNG testng=new TestNG();
	List<XmlSuite> suites= new ArrayList<XmlSuite>();
	XmlSuite suite= new XmlSuite();
	suite.setName("Suite");
	suites.add(suite);
	
	
	XmlTest test= new XmlTest(suite);
	test.setName("BookingTest-Apply discount-Instant pay");
	
	
	List<XmlClass> classes=new ArrayList<XmlClass>();
	XmlClass classa= new XmlClass("Clientchangingreq.Bookingsearch");
	
	List<XmlInclude> methods= new ArrayList<XmlInclude>();
	methods.add(new XmlInclude("searchhotel",1));
	methods.add(new XmlInclude ("selectroom",2));
	methods.add(new XmlInclude("enterguestinfo",3));
	classa.setIncludedMethods(methods);
	
	
	classes.add(classa);
	classes.add(new XmlClass("Clientchangingreq.Paymentmanager"));
	test.setXmlClasses(classes);
	
	Map<String,String> parameter=new HashMap<String,String>();
	parameter.put("action", "instantpay");
	
	test.setParameters(parameter);
	
	testng.setXmlSuites(suites);
	testng.run();
	
	
	
	}

}
