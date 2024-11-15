package com.flocksafety.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.flocksafety.utils.ExtentManager;

public class TestBase {

	public WebDriver driver;
	public static Properties prop;
	
	
	public static Map<String, String> mobileApp = new HashMap<String, String>();
	public static Map<String, String> system = new HashMap<String, String>();


	public static String testEnv = "UAT"; 	
	public static Map<String, String> rndNum = new HashMap<String,String>();
	public static Map<String, String> env = new HashMap<String, String>();
	public static Map<String, String> sfdc = new HashMap<String, String>();
	public static Map<String, String> url = new HashMap<String, String>();
	public static Map<String, String> profile = new HashMap<String, String>();
	public static SoftAssert softAssertion = new SoftAssert();
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static List<Object> list = new ArrayList<Object>();
	public static Set<Object> set = new HashSet<Object>();


	
	

	@BeforeSuite
	public void loadConfig() throws IOException {

		FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"\\screenshots"));
		//FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"\\test-output"));
		File screeshot_file = new File(System.getProperty("user.dir")+"\\screenshots\\.gitkeep");
		screeshot_file.createNewFile();
		
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/configuration/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}

	}

	public void browserIntialization() {

		String browser = prop.getProperty("browser");
		BrowserFactory.getInstance().setDriver(browser);
		driver = BrowserFactory.getInstance().getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicit_wait_timeout"))));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("UATurl"));
		
	}



	@AfterSuite
	public void afterSuite() {
		ExtentManager.endReport();
	}

}
