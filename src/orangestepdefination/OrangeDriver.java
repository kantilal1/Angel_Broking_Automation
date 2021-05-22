package orange.crm.orangestepdefination;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import orange.crm.Listeners.ExtentReportListener;
import orange.crm.Utility.BrowserUtility;
import orange.crm.Utility.EzxcelHandlerDemo;
import orange.crm.Utility.OrangeCRMPropertiesFileReader;
import orange.crm.Utility.PropertiesFileReader;

public class OrangeDriver extends ExtentReportListener{
    final static Logger logger = Logger.getLogger(OrangeDriver.class);
	
	PropertiesFileReader obj = new PropertiesFileReader();
	OrangeCRMPropertiesFileReader crmProperties = new OrangeCRMPropertiesFileReader();
    Properties crmproperties = crmProperties.getProperty();
	  public static String sheetName;
	    public static String filePath;
	    public static WebDriver webDriver;
	    public static String run;
	    public static String testcaseId;
	    public static String scenarioNames;
	

	    @Before(value = "@OrangeFunctionality")
	    public void before(Scenario scenario) throws Exception {
	        String scenarioName = scenario.getName();
	        System.out.println("scenarioName===>"  +scenarioName);
	        scenarioNames = "Sachin";
	        System.out.println("scenarioName===>"  +scenarioName);
	        Map<String, String> TestDataInMap = EzxcelHandlerDemo.getTestDataInMap(".\\ORANGE_CRM\\Orange\\DriverFile\\TestSet.xlsx",
	                "TestSet", scenarioName);
	        String str = TestDataInMap.get("Run");
	        System.out.println("scenarioNames===>"  +scenarioNames);
	        if (str.contains("No")) {
	            logger.info("SCENARIO SKIPPED ####" + scenarioName);
	            throw new SkipException(scenario.getName());
	        }
	        setAppData(scenarioName);
	        logger.info("SCENARIO STARTED ####" + scenarioName);
	    }

	    public static Map<String, String> appTestData = new HashMap<String, String>();

	    private void setAppData(String scenarioName) throws Exception {
	        Properties properties = crmProperties.getProperty();
	        appTestData = EzxcelHandlerDemo.getTestDataInMap(".\\ORANGE_CRM\\Orange\\DriverFile\\ApplicationData.xlsx", "AppData",
	                scenarioName);
	        logger.info("AppTestData:" + appTestData);

	    }
	    
	 @When("Configure my data sheet path {string} and spread sheet name {string}")
	    public void configureMyDataSheet(String filePath, String sheetName) {
		 System.out.println("Hello first method");
	        this.filePath = filePath;
	        this.sheetName = sheetName;

	    }
	 
	 @Given("Launch browser and enter orange crm url for ScenarioId {string}")
	    public void launchWebApplication(String tcId) throws Throwable {
	        System.out.println("Test casesID ::::::::" +tcId);

	        ExtentTest logInfo = null;
	        try {
	            logInfo = getNode(tcId, ".\\ORANGE_CRM\\Orange\\DriverFile\\TestSet.xlsx", "open_browser_with_URL");
	            Properties properties = obj.getProperty();
	            System.out.println("hello new logic");

	            webDriver = BrowserUtility.launchBrowser(webDriver, properties.getProperty("browser.name"),
	            		crmproperties.getProperty("OrangeCRMUrl"));
	            // logInfo = object.logsHandlerWeb(stepDescription, "Open Serav Web application", driver);
	            System.out.println("hello new logic 2");

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

}
