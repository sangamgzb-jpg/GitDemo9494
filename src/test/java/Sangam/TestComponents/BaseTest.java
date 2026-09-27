package Sangam.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Sangam.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException 
	
	{
		
		Properties pro= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Sangam\\Resources\\GlobalData.properties");
		pro.load(fis);
		
		
		String browserName=System.getProperty("browser")!=null? System.getProperty("browser"):pro.getProperty("browser");
		//String browserName=pro.getProperty("browser");
		
		
		if (browserName.equalsIgnoreCase("chrome")) {
		    WebDriverManager.chromedriver().setup();
		    ChromeOptions options = new ChromeOptions();
		   
		    driver = new ChromeDriver(options);
		
		
		}     else if (browserName.equalsIgnoreCase("firefox")) {
		        WebDriverManager.firefoxdriver().setup();
		        FirefoxOptions options = new FirefoxOptions();
		        
		        driver = new FirefoxDriver(options);

		    } else if (browserName.equalsIgnoreCase("edge")) {
		        WebDriverManager.edgedriver().setup();
		        EdgeOptions options = new EdgeOptions();
		        //options.addArguments("--headless=new");
		        
		        driver = new EdgeDriver(options);
		        
		    } else {
		        throw new IllegalArgumentException("Unsupported browser: " + browserName);
		
		    }
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException 
	{
		
		//read jason to string
		String jsonContent = FileUtils.readFileToString(
		        new File(FilePath), StandardCharsets.UTF_8);
		
		
		
		//string to hashmap----Jackson Databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String >>>(){});
		return data;
		
	}
	
	
	public String getScreenShots(String testCaseName,WebDriver driver) throws IOException 
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(System.getProperty("user.dir")+"//reports//"+ testCaseName+".png");
		FileUtils.copyFile(src, dst);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName+".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException 
	{
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() 
	{
		driver.close();
	}
	
	
	
	
}
