package Runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class Runnerhelper {
	
	TestNG testng;
	List<XmlSuite> suites;
	XmlSuite suite;
	List<XmlTest> tests;
	XmlTest test;
	Map<String,String> params;
	List<XmlClass> classes;
	//List<XmlInclude> methods;
	XmlClass cls;



public Runnerhelper(int threadpoolsize)
{

	testng=new TestNG();
	suites=new ArrayList<XmlSuite>();
	tests=new ArrayList<XmlTest>();
	testng.setSuiteThreadPoolSize(threadpoolsize);
	testng.setXmlSuites(suites);
	
	
}

public void createsuite(String name,boolean parallel)
{
   
	suite= new XmlSuite();
	suite.setName(name);
	
	
	if(parallel)
		suite.setParallel(ParallelMode.TESTS);
	
	suites.add(suite);
	
}

public void createtest(String name)
{

	test=new XmlTest(suite);
	test.setName(name);
	//test.addParameter(param, value);
	tests.add(test);
	classes= new ArrayList<XmlClass>();
	params= new HashMap<String, String>();
	test.setXmlClasses(classes);
	test.setParameters(params);
	
	
}

public void createclass(String name,List<String> methods)
{
    cls= new XmlClass(name);
    List<XmlInclude> clsmethods= new ArrayList<XmlInclude>();
    int priority=1;
    
    for(String methodname: methods)
    {
    	XmlInclude method= new XmlInclude(methodname, priority);
    	clsmethods.add(method);
    	priority++;
    	
    }
    	
    cls.setIncludedMethods(clsmethods);
    
    
    classes.add(cls);   
}

/*public void createmethod(String name, int rank) {
	
	methods.add(new XmlInclude(name,rank));
	//cls.setIncludedMethods(methods);
	
}*/

public void addparameter(String name,String value) {
	
	params.put(name, value);
	
}

public void run()
{
testng.run();	
}



}