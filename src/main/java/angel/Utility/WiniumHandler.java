/*
 * Creation : 6 Jun 2020
 */
package angel.Utility;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import angel.Listeners.ExtentReportListener;

public class WiniumHandler extends ExtentReportListener {
    public static WiniumDriver Winumdriver;
    public static Logger logger = Logger.getLogger(WiniumHandler.class);

    public WebElement winiumLocators(String row, WiniumDriver Winumdriver, String filePath, String sheetName) throws Exception {
        String rowid = row.replaceAll("\\.", "");

        Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(filePath, sheetName, rowid);

        String locatorValue = TestDataInMap.get("locatorValue");
        String locatorType = TestDataInMap.get("locatorType");

        logger.info("Searching desktop locator in sheet " + filePath + "//" + sheetName);
        logger.info("Get desktop element " + locatorType + " locator for " + locatorValue);
        switch (locatorType.toLowerCase()) {

        case "xpath":

            return Winumdriver.findElementByXPath(locatorValue);
        case "id":

            return Winumdriver.findElementById(locatorValue);
        case "name":

            return Winumdriver.findElementByName(locatorValue);
        case "cssSelector":

            return Winumdriver.findElementByCssSelector(locatorValue);
        case "linkText":

            return Winumdriver.findElementByLinkText(locatorValue);
        case "partialLinkText":

            return Winumdriver.findElementByPartialLinkText(locatorValue);
        case "className":

            return Winumdriver.findElementByClassName(locatorValue);
        default:
            throw new Exception(locatorType + " desktop locator Not Found for " + locatorValue);

        }
    }

    public List<WebElement> listOfwiniumLocators(String row, WiniumDriver Winumdriver, String filePath, String sheetName) throws Exception {
        String rowid = row.replaceAll("\\.", "");

        Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(filePath, sheetName, rowid);

        String locatorValue = TestDataInMap.get("locatorValue");

        String locatorType = TestDataInMap.get("locatorType");
        try {
            switch (locatorType.toLowerCase()) {

            case "xpath":

                return Winumdriver.findElementsByXPath(locatorValue);
            case "id":

                return Winumdriver.findElementsById(locatorValue);
            case "name":
                System.out.println("In Name:" + locatorValue);
                return Winumdriver.findElementsByName(locatorValue);
            case "cssSelector":

                return Winumdriver.findElementsByCssSelector(locatorValue);
            case "linkText":

                return Winumdriver.findElementsByLinkText(locatorValue);
            case "partialLinkText":

                return Winumdriver.findElementsByPartialLinkText(locatorValue);
            case "className":

                return Winumdriver.findElementsByClassName(locatorValue);
            default:
                throw new Exception(locatorType + " Locator Not Found for " + locatorValue);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ExtentTest logsHandler(String stepDescription, String methodName, WiniumDriver Winumdriver) throws Exception {
        ExtentTest logInfo = null;

        logInfo = test.createNode(new GherkinKeyword("And"), methodName);

        logInfo.pass(stepDescription);
        logInfo.addScreenCaptureFromPath(captureScreenShot(Winumdriver));

        return logInfo;

    }

    public ExtentTest logsHandlerWithoutDriver(String stepDescription, String methodName) throws Exception {
        ExtentTest logInfo = null;

        logInfo = test.createNode(new GherkinKeyword("And"), methodName);

        logInfo.pass(stepDescription);

        return logInfo;

    }

   
    public WebElement locatorsfetch(String row, WebDriver driver, String filePath, String sheetName) throws Exception {
        String rowid = row.replaceAll("\\.", "");

        Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(filePath, sheetName, rowid);

        String locatorValue = TestDataInMap.get("locatorValue");
        String locatorType = TestDataInMap.get("locatorType");
        logger.info("Searching web locator in sheet " + filePath + "//" + sheetName);
        logger.info("Get webelement " + locatorType + " locator for " + locatorValue);

        switch (locatorType.toLowerCase()) {

        case "xpath":

            return driver.findElement(By.xpath(locatorValue));
        case "id":

            return driver.findElement(By.id(locatorValue));
        case "name":

            return driver.findElement(By.name(locatorValue));
        case "cssselector":

            return driver.findElement(By.cssSelector(locatorValue));
        case "linkText":

            return driver.findElement(By.linkText(locatorValue));
        case "partialLinkText":

            return driver.findElement(By.partialLinkText(locatorValue));
        case "className":

            return driver.findElement(By.className(locatorValue));
        default:
            throw new Exception(locatorType + " web locator Not Found for " + locatorValue);

        }
    }

    public List<WebElement> locatorsfetchList(String row, WebDriver driver, String filePath, String sheetName) throws Exception {
        String rowid = row.replaceAll("\\.", "");

        // Properties properties = obj.getProperty();

        Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(filePath, sheetName, rowid);
        System.out.println("filePath" + filePath);
        System.out.println("sheetName" + sheetName);

        String locatorValue = TestDataInMap.get("locatorValue");

        String locatorType = TestDataInMap.get("locatorType");

        switch (locatorType.toLowerCase()) {

        case "xpath":

            return driver.findElements(By.xpath(locatorValue));
        case "id":

            return driver.findElements(By.id(locatorValue));
        case "name":

            return driver.findElements(By.name(locatorValue));
        case "cssselector":

            return driver.findElements(By.cssSelector(locatorValue));
        case "linkText":

            return driver.findElements(By.linkText(locatorValue));
        case "partialLinkText":

            return driver.findElements(By.partialLinkText(locatorValue));
        case "className":

            return driver.findElements(By.className(locatorValue));
        default:
            throw new Exception("Locator Not Found");

        }
    }

    public ExtentTest logsHandlerWeb(String stepDescription, String methodName, WebDriver driver) throws Exception {
        ExtentTest logInfo = null;

        logInfo = test.createNode(new GherkinKeyword("And"), methodName);

        logInfo.pass(stepDescription);
        logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

        return logInfo;

    }

    public String EmailConfigurationTo(String rowid, String filePath, String sheetName) throws Exception {

        Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(filePath, sheetName, rowid);

        String Email_To_Recipients = TestDataInMap.get("Email_To_Recipients");

        return Email_To_Recipients;
    }

    public String EmailConfigurationCC(String rowid, String filePath, String sheetName) throws Exception {

        Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(filePath, sheetName, rowid);

        String Email_CC_Recipients = TestDataInMap.get("Email_CC_Recipients");

        return Email_CC_Recipients;
    }

   /* public String STCAPI_AILocators(String rowid, String filePath, String sheetName) throws Exception {

        Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(filePath, sheetName, rowid);
        String systemDir = System.getProperty("user.dir");
        String imageName = TestDataInMap.get("locatorValue");
        String path = systemDir + "\\Winium-Projects\\STCAPI\\AIMap\\" + imageName;

        // String path = System.getProperty(projectlevelpath);
        System.out.println(path);

        return path;
    }*/

}
