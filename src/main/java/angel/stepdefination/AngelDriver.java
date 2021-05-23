package angel.stepdefination;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;

import angel.Listeners.ExtentReportListener;
import angel.Utility.BrowserUtility;
import angel.Utility.EzxcelHandlerDemo;
import angel.Utility.PropertiesFileReader;
import angel.Utility.WiniumHandler;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AngelDriver extends ExtentReportListener{
    final static Logger logger = Logger.getLogger(AngelDriver.class);

    WiniumHandler object = new WiniumHandler();
	PropertiesFileReader crmProperties = new PropertiesFileReader();
    Properties crmproperties = crmProperties.getProperty();
    
	    public static String sheetName;
	    public static String filePath;
	    public static WebDriver webDriver;
	    public static String run;
	    public static String testcaseId;
	    public static String scenarioNames;
	    public static String stepDescription;
	    public static String textValue;
	    
	    SoftAssert soft = new SoftAssert();

	    
	  //  @Before(value="@abc, @xyz")
	    @Before()
	    public void before(Scenario scenario) throws Exception {
	        String scenarioName = scenario.getName();	       
	        scenarioNames = scenarioName;
	        System.out.println("scenarioNames===>"  +scenarioName);
	        Map<String, String> TestDataInMap = EzxcelHandlerDemo.getTestDataInMap(".\\Angel_Broking\\Angel\\DriverFile\\TestSet.xlsx",
	                "TestSet", scenarioName);
	        String str = TestDataInMap.get("Run");
	     
	        if (str.contains("No")) {
	            logger.info("SCENARIO SKIPPED ####" + scenarioName);
	            throw new SkipException(scenario.getName());
	        }
	        setAppData(scenarioName);
	        logger.info("SCENARIO STARTED ####" + scenarioName);
	    }

	    public static Map<String, String> appTestData = new HashMap<String, String>();

	    private void setAppData(String scenarioName) throws Exception {
	        appTestData = EzxcelHandlerDemo.getTestDataInMap(".\\Angel_Broking\\Angel\\DriverFile\\ApplicationData.xlsx", "AppData",
	                scenarioName);
	        logger.info("AppTestData:" + appTestData);

	    }
	    
	 @When("Configure my data sheet path {string} and spread sheet name {string}")
	    public void configureMyDataSheet(String filePath, String sheetName) {
	        this.filePath = filePath;
	        this.sheetName = sheetName;

	    }
	 
	 @Given("Launch browser and enter orange crm url for ScenarioId {string}")
	    public void launchWebApplication(String tcId) throws Throwable {
	  	        ExtentTest logInfo = null;
	        try {
	            logInfo = getNode(tcId, ".\\Angel_Broking\\Angel\\DriverFile\\TestSet.xlsx", "open_browser_with_URL");
	            webDriver = BrowserUtility.launchBrowser(webDriver, crmproperties.getProperty("browserName"),
	            		crmproperties.getProperty("AngelBrokingUrl"));
	            logInfo.pass("Opened IE browser and entered url");
	            logInfo.addScreenCaptureFromPath(captureScreenShot(webDriver));
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }

	    }
	 
	   public ExtentTest getNode(String rowId, String testsetPath, String nodeText) throws Exception, ClassNotFoundException {
	        ExtentTest logInfo;
	        Map<String, String> TestDataInMap = EzxcelHandlerDemo.getTestDataInMap(testsetPath, "TestSet", rowId);
	        run = TestDataInMap.get("Run");
	        testcaseId = TestDataInMap.get("TestCaseId");
	        String TestCaseDescription = TestDataInMap.get("TestCaseDescription");
	        test = extent.createTest((Class<? extends IGherkinFormatterModel>) Feature.class, testcaseId);
	        test = test.createNode((Class<? extends IGherkinFormatterModel>) Scenario.class, TestCaseDescription);
	        logInfo = test.createNode(new GherkinKeyword("Given"), nodeText);
	        return logInfo;
	    }
	   @Then("Close the Angel Web Application")
	    public void exitWebDriver() throws Exception {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Close the Application", webDriver);
	            webDriver.quit();
	        } catch (AssertionError | Exception e) {
	            testStepHandleNoDriver("FAIL", logInfo, e);
	        }

	    }
	   @And("The Below Step Description is {string}")
	    public void logger(String stepDescription) {
	        this.stepDescription = stepDescription;
	        System.out.println(stepDescription);
	    }
	   
	   @Then("Enter value in {string} and {string} web")
	    public void enterValueWeb(String locator, String locator1) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Enter value", webDriver);
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            elt.sendKeys(crmproperties.getProperty("username"));
	            WebElement elt1 = object.locatorsfetch(locator1, webDriver, filePath, sheetName);
	            elt1.sendKeys(crmproperties.getProperty("password"));
	            
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   @Then("Click on element {string}")
	    public void clickOnElement(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Click On", webDriver);
	            
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.elementToBeClickable(elt));
	            elt1.click();
	            Thread.sleep(5000);
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   
	   
	   @Then("Click on specific element {string} {string}")
	    public void clickOnSpecificElement(String locator, String value) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Click On", webDriver);
	           int i= Integer.parseInt(value);
	            List<WebElement> elt = object.locatorsfetchList(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.elementToBeClickable(elt.get(i)));
	            elt1.click();
	            Thread.sleep(5000);
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   
	   @Then("JSClick on element {string}")
	    public void jsClickOnElement(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Click On", webDriver);
	            
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            JavascriptExecutor executor = (JavascriptExecutor)webDriver;
	            executor.executeScript("arguments[0].click();", elt);
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   
	   @Then("Mouse hover on element {string}")
	    public void mouseHoverOnElement(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Click On", webDriver);            
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            Actions actions = new Actions(webDriver);
	        	actions.moveToElement(elt).perform();
	        	Thread.sleep(5000);
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   

	   @Then("Get Text from element {string} and {string}")
	    public void getTextWebelement(String locator, String value) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Get Text", webDriver);
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.visibilityOf(elt));
	           // System.out.println("Get Text===>" +elt1.getText());
	            soft.assertEquals(elt1.getText(), appTestData.get(value), "The user is not redirect to the "+value+" Page ");
	            soft.assertAll();
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   
	   @Then("Wait for element")
	    public void waitToElment() throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Wait", webDriver);
	            Thread.sleep(7000);
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   
	   @Then("Get Text from element {string}")
	    public void getTextWebelement(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Get Text", webDriver);
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.visibilityOf(elt));
	         //   System.out.println("Get Text===>" +elt1.getAttribute("value"));
	            String text =elt1.getAttribute("value");
	            soft.assertEquals(text, BrowserUtility.getDate(), "The "+text+" is not matched");
	            soft.assertAll();
	   
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }

	   @Then("Get Text from list of element {string} {string}")
	    public void getTextWebelements(String locator, String value) throws MalformedURLException {
	        ExtentTest logInfo = null;
	       int i= Integer.parseInt(value);
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Get Text", webDriver);
	            List<WebElement> elt = object.locatorsfetchList(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.visibilityOf(elt.get(i)));
	          //  System.out.println("Get Text===>" +elt1.getAttribute("innerHTML"));
	            textValue =elt1.getAttribute("innerHTML");
	   
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }

	   @Then("Switch to parent window")
	    public void switchToWindow() throws MalformedURLException {
	        ExtentTest logInfo = null;

	        try {
	            String parentwindow = webDriver.getWindowHandle();
	            webDriver.switchTo().window(parentwindow);
	            System.out.println("Window switched to parent");
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   
	   @Then("Switch to child window {string}")
	    public void switchToChildWindow(String value) throws MalformedURLException {
	        ExtentTest logInfo = null;
               int i =Integer.parseInt(value);
	        try {
	            ArrayList<String> childWindow = new ArrayList<String>(webDriver.getWindowHandles());	            
	            webDriver.switchTo().window((childWindow.get(i)));
	            System.out.println("Window switched to childWindow");
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }


	   @Then("Verify that the element is Present {string}")
	    public void isElementPresent(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Get Text", webDriver);
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            System.out.println("Verifying1");
	            WebElement elt1 = wait.until(ExpectedConditions.elementToBeClickable(elt));
	            System.out.println("Get Text===>" +elt1.getText());
	            soft.assertTrue(elt1.isDisplayed(), "The "+elt1.getText()+" element is not displayed");
	            soft.assertAll();
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   
	   @Then("Pass the value in field {string}")
	    public void passTheValue(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Pass value", webDriver);
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.elementToBeClickable(elt));
	            elt1.sendKeys(textValue);
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
    
	
	   
}
