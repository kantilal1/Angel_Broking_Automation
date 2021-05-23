package angel.Utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import angel.stepdefination.AngelDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility {

	public static WebDriver launchBrowser(WebDriver driver, String browserName, String testURL) throws InterruptedException, MalformedURLException {
        if (browserName.equals("Chrome")) { 
        	System.out.println("Launching browser for automation and Browser name is===>" +browserName);
        	/*WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("Maximise");
            driver.get(testURL);
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
        	System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(testURL);
            Thread.sleep(5000);
            return driver;
        } else if (browserName.equals("IE")) {
        	System.out.println("Launching browser for automation and Browser name is===>" +browserName);
        /*	WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.get(testURL);
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
        	System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "accept");
            // capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability("ignoreZoomSetting", true);
            capabilities.setCapability("requireWindowFocus", true);// to move mouse manually
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.get(testURL);
            return driver;
        } else if (browserName.equals("Firefox")) {
        	System.out.println("Launching browser for automation and Browser name is===>" +browserName);
        	/*WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(testURL);
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
           
           System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
           driver = new FirefoxDriver();
           driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
           driver.manage().window().maximize();
           driver.get(testURL);
           Thread.sleep(4000);
            return driver;
        } else if (browserName.equals("i will test it on cloud")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WINDOWS);
            ChromeOptions option = new ChromeOptions();
            option.merge(capabilities);
            String HubUrl = "http://172.20.235.42:4444/wd/hub";
            driver = new RemoteWebDriver(new URL(HubUrl), option);

        }
        return null;
    }
	
    public static String getDate() throws MalformedURLException {
        String strDate = null;
        	 Date date = new Date();  
 		    SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy");  
 		     strDate= formatter.format(date);  
 		    System.out.println("strDate==>" +strDate); 

		return strDate;		
    }

}
