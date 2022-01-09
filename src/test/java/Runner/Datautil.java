package Runner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Datautil {
	
	
	public Map<String,String> getclassmethods() throws IOException, ParseException
	{
		Map<String,String> clsmethods= new HashMap<String, String>();
		String path= System.getProperty("user.dir")+"//src//test//resources//classmethods.json";
		FileReader read= new FileReader(path);
		JSONParser parser= new JSONParser();
		JSONObject json=(JSONObject) parser.parse(read);
		
		JSONArray noofcls= (JSONArray)json.get("classdetails");
		
		for(int clsID=0;clsID<noofcls.size();clsID++)
		{
			JSONObject cls= (JSONObject) noofcls.get(clsID);
			String classname=(String) cls.get("class");
			JSONArray methods= (JSONArray) cls.get("methods");
			
			for(int mID=0;mID<methods.size();mID++)
			{
				String method= (String)methods.get(mID);
				clsmethods.put(method, classname);
				
			}
			
			
		}
		
		return clsmethods;
        //System.out.println(clsmethods);
		
	}
	
	public int getestdatasets(String path, String dataflag) throws IOException, ParseException
	{
		
		JSONParser parser=new JSONParser();
		FileReader read= new FileReader(path);
		JSONObject datajson=(JSONObject) parser.parse(read);
		JSONArray test=(JSONArray) datajson.get("testdata");
		
		for(int tid=0;tid<test.size();tid++)
		{
			JSONObject datasets=(JSONObject) test.get(tid);
			String flag=(String)datasets.get("flag");
			
			if(flag.equalsIgnoreCase(dataflag))
			{
				JSONArray data= (JSONArray) datasets.get("data");
				return data.size();
				
			
				
			}
		}
		
		
		return -1;
	}
	
	public JSONObject gettestdata(String path, String dataflag,int iteration) throws IOException, ParseException
	{
		JSONParser parser=new JSONParser();
		FileReader read= new FileReader(path);
		JSONObject datajson=(JSONObject) parser.parse(read);
		JSONArray test=(JSONArray) datajson.get("testdata");
		
		for(int tid=0;tid<test.size();tid++)
		{
			JSONObject datasets=(JSONObject) test.get(tid);
			String flag=(String)datasets.get("flag");
			
			if(flag.equalsIgnoreCase(dataflag))
			{
				JSONArray data= (JSONArray) datasets.get("data");		
				JSONObject datano=(JSONObject) data.get(iteration);
				return datano;
				
				
		
	}}
return null;
}}
