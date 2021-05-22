package orange.crm.Utility;

import java.net.MalformedURLException;
import java.net.URL;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility {

	public static WebDriver launchBrowser(WebDriver driver, String browserName, String testURL) throws InterruptedException, MalformedURLException {
        if (browserName.equals("Chrome")) { 
        	System.out.println("Launching browser for automation and Browser name is===>" +browserName);
        	WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(testURL);
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        } else if (browserName.equals("IE")) {
        	System.out.println("Launching browser for automation and Browser name is===>" +browserName);
        	WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.get(testURL);
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        } else if (browserName.equals("Firefox")) {
        	System.out.println("Launching browser for automation and Browser name is===>" +browserName);
        	WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(testURL);
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

}
