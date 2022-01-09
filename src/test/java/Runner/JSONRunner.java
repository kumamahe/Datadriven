package Runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRunner {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		Datautil d= new Datautil();
		Map<String,String> clsmethods= d.getclassmethods();
		List<String> methodsincls= new ArrayList<String>();
		String path= System.getProperty("user.dir")+"//src//test//resources//testconfig.json";
		FileReader read= new FileReader(path);
		JSONParser parser= new JSONParser();
		JSONObject json=(JSONObject) parser.parse(read);
		
		String threadpoolsize=(String)json.get("parallelsuites");
		Runnerhelper testng=new Runnerhelper(Integer.parseInt(threadpoolsize));
		
		JSONArray suites= (JSONArray)json.get("testsuites");
		for(int sID=0;sID<suites.size();sID++)
		{
			JSONObject suite=(JSONObject) suites.get(sID);
			String runmode=(String)suite.get("runmode");
			if(runmode.equals("Y"))
			{
				String suitename= (String)suite.get("name");			
				String testdatapath= (String)suite.get("testdatajsonfile");
				String testdatafullpath= System.getProperty("user.dir")+"//src//test//resources//"+testdatapath;
				//String suitename= (String)suite.get("testdataxlsfile");
				String suitepath= (String)suite.get("suitefilename");
				String paralleltest= (String)suite.get("paralleltests");
				boolean ptest=false;
				if(paralleltest.equals("Y"))
					ptest=true;
				
				testng.createsuite(suitename, ptest);
				
				String suitefilepath=System.getProperty("user.dir")+"//src//test//resources//"+suitepath;
				FileReader suiteread= new FileReader(suitefilepath);
				JSONParser suiteparse= new JSONParser();
				JSONObject suitejson=(JSONObject) suiteparse.parse(suiteread);
				JSONArray tests=(JSONArray)suitejson.get("testcases");
				for(int tID=0;tID<tests.size();tID++)
				{
					JSONObject tc=(JSONObject) tests.get(tID);
					//tc.get("name");
					JSONArray parameter=(JSONArray)tc.get("parameternames");
					JSONArray testcases= (JSONArray)tc.get("executions");
					
					for(int tcID=0;tcID<testcases.size();tcID++)
					{
						JSONObject testcase= (JSONObject)testcases.get(tcID);
						String tcrunmode=(String)testcase.get("runmode");
						boolean tcrun=false;
						if(tcrunmode.equals("Y"))
							tcrun=true;
						
						if(tcrun)
						{
							String testcasename=(String) testcase.get("executionname");
							String dataflag=(String) testcase.get("dataflag");
							int iteration=d.getestdatasets(testdatafullpath, dataflag);
							for(int iterate=0;iterate<iteration;iterate++)
							{
							testng.createtest(testcasename+"--"+iterate);	
							JSONArray parametervalues=(JSONArray) testcase.get("parametervalues");
							for(int paID=0;paID<parametervalues.size();paID++)
							{
								String para=(String) parameter.get(paID);
								String paravalue=(String) parametervalues.get(paID);
								testng.addparameter(para, paravalue);
							}
							JSONArray methods=(JSONArray) testcase.get("methods");
							
							testng.addparameter("datafilepath", testdatafullpath);
							testng.addparameter("dataflag", dataflag);
							testng.addparameter("iteration", String.valueOf(iterate));
							
							for(int methodsID=0;methodsID<methods.size();methodsID++)
							{
								String methodscls=(String)methods.get(methodsID);
								String clsformethod=clsmethods.get(methodscls);
								System.out.println(clsformethod+"------"+methodscls);
								if((methodsID==methods.size()-1)|| !(clsmethods.get(methods.get(methodsID+1))).equals(clsformethod))
								{
								    
									methodsincls.add(methodscls);
									testng.createclass(clsformethod, methodsincls);
									methodsincls=new ArrayList<String>();
									
								
								}
								else
								{
									methodsincls.add(methodscls);
									
								}
								
								
							}
						}
							
						}
						
					}
					
					
				}
				
				

			}
			
		}
		
	testng.run();
		
	}

}
