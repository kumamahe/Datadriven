package Extentmanager;

import java.io.File;
import java.io.UTFDataFormatException;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Utf8;

public class Extentreporting {
	public static ExtentReports reports;
	public static String path;
	
	public static ExtentReports getreport() {
		if(reports==null) {
		
		reports= new ExtentReports();
		Date d= new Date();
		path= System.getProperty("user.dir")+"//report//"+d.toString().replaceAll(":","-")+"//screenshots";
		File f=new File(path);
		f.mkdirs();
		ExtentSparkReporter rep= new ExtentSparkReporter(System.getProperty("user.dir")+"//report//"+d.toString().replaceAll(":","-"));
		rep.config().setReportName("Testing");
		rep.config().setDocumentTitle("learndatadrivenframework");
		rep.config().setEncoding("utf-8");
		rep.config().setTheme(Theme.DARK);
		
		reports.attachReporter(rep);
		
	}
	
		return reports;
	}

	
}
