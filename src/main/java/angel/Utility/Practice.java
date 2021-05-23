package angel.Utility;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;

public class Practice {

	public static void main(String[] args) {
        
		  Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy");  
		    String strDate= formatter.format(date);  
		    System.out.println(strDate); 

	}
	
}
	 /*  @Then("Dynamic element {string}")
	    public void dynamicElement(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Click On", webDriver);
	            WebElement elt = webDriver.findElement(By.xpath("//div[contains(text(),'1')]"));
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.visibilityOf(elt));
	            elt1.click();
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    
	

}
*/