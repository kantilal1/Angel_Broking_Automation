package angel.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.WiniumDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener {

    public static ExtentHtmlReporter report = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;
    protected static String reportPath = "Reports/ExecutedOn_" + getCurrentDate() + "_Time_" + getCurrentTime();

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("HH-mm-ss").format(new Date());
    }

    public static ExtentReports setUp() {
        String reportLocation = reportPath + "/ExtentReport.html";
        report = new ExtentHtmlReporter(reportLocation);
        report.config().setDocumentTitle("Automation Test Report");
        report.config().setReportName("Automation Test Report");
        report.config().setTheme(Theme.DARK);
        System.out.println("Extent Report location initialized . . .");
        report.start();

        extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Application", "CRM");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        System.out.println("System Info. set in Extent Report");
        return extent;
    }

    public static void testStepHandle(String teststatus, WebDriver driver, ExtentTest extenttest, Throwable throwable) {
        switch (teststatus) {
        case "FAIL":
            extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
            extenttest.error(throwable.fillInStackTrace());


            try {
                extenttest.addScreenCaptureFromPath(captureScreenShot(driver));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (driver != null) {
                driver.quit();
            }
            break;

        case "PASS":
            extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
            break;

        default:
            break;
        }
    }

    public static void testStepHandleNoDriver(String teststatus, ExtentTest extenttest, Throwable throwable) {
        switch (teststatus) {
        case "FAIL":
            extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
            extenttest.error(throwable.fillInStackTrace());

            break;

        case "PASS":
            extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
            break;

        default:
            break;
        }
    }


    public static String captureScreenShot(WebDriver driver) throws IOException {

        TakesScreenshot screen = (TakesScreenshot) driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String screenshotPath = "./screenshots/SNAPSHOT_" + getCurrentDate() + "_Time_" + getCurrentTime() + ".png";
        String dest = reportPath + screenshotPath;

        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return screenshotPath;

    }

    private static String getcurrentdateandtime() {
        String str = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
            Date date = new Date();
            str = dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        } catch (Exception e) {
        }
        return str;
    }
    
    
    /* public static void testStepHandleWinium(String teststatus, WiniumDriver winiumdriver, ExtentTest extenttest, Throwable throwable) {
    switch (teststatus) {
    case "FAIL":
        extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
        extenttest.error(throwable.fillInStackTrace());

        try {
            extenttest.addScreenCaptureFromPath(captureScreenShot(winiumdriver));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (winiumdriver != null) {
            winiumdriver.quit();
        }
        break;

    case "PASS":
        extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
        break;

    default:
        break;
    }
}

*/   
    /*   public static String captureScreenShotWinium(WiniumDriver winiumdriver) throws IOException {
    TakesScreenshot screen = (TakesScreenshot) winiumdriver;
    File src = screen.getScreenshotAs(OutputType.FILE);
    String screenshotPath = "./screenshots/SNAPSHOT_" + getCurrentDate() + "_Time_" + getCurrentTime() + ".png";
    String dest = reportPath + screenshotPath;

    File target = new File(dest);
    FileUtils.copyFile(src, target);
    return screenshotPath;
}*/



}