package CommonPO;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath;

public class BaseTest {
    public WebDriver driver;

    public BaseTest() {
    }

    public ExtentHtmlReporter htmlReporter; //used for look and fill of report
    public ExtentReports extent = new ExtentReports(); // used to add data/logs in report
    public static ExtentTest test; //used for add/update test status in report

    @Parameters({"browserName", "url", "localDriverPath"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String browserName, @Optional("https://demo.nopcommerce.com/") String url, @Optional("C:\\Program Files\\chromedriver78.exe") String localDriverPath) throws IOException {
        FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "\\Report"));

        getLocalDriver(browserName, localDriverPath);

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Report\\Report.html");
        htmlReporter.config().setDocumentTitle("Automation Testing"); //Title of report
        htmlReporter.config().setReportName("Extent Report Understanding to Rajani"); //Name of report
        htmlReporter.config().setTheme(Theme.STANDARD); //set report theme
        String css = ".r-img {width: 50%;}"; //set width for failure image
        htmlReporter.config().setCSS(css);
        htmlReporter.config().setAutoCreateRelativePathMedia(true);

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("HostName", "NopCommerce");
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser Name", "Chrome");
        extent.setSystemInfo("Trainer", "Nishit");
        extent.setSystemInfo("Trainee", "Rajani");
        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    @Parameters({"url"})
    @BeforeMethod (alwaysRun = true)
    public void beforeMethod(@Optional("https://demo.nopcommerce.com/") String url){
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getName() + " Test Case Failed"); //Get name of test case
            test.log(Status.FAIL, "Failed Test Case Error: " + result.getThrowable()); //add error in report

            MediaEntityModelProvider screenshot = createScreenCaptureFromPath(getScreenshot(driver, result.getMethod().getMethodName())).build();
            test.log(Status.FAIL, "Screenshot", screenshot);

            // test.addScreenCaptureFromPath(ExtentReportDemo.getScreenshot(driver, result.getMethod().getMethodName())); // add failure image in report
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, result.getName() + " Test Case Skipped");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName() + " Test Case Passed");
        }
        Thread.sleep(2000);
    }

    @AfterClass(alwaysRun = true)
    public void terminateReport() {
        driver.quit();
        extent.flush();
    }

    private void getLocalDriver(String browserName, String driverPath) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            this.driver = new ChromeDriver();
            this.driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverPath);
            this.driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            this.driver = new InternetExplorerDriver();
        }
    }

    private static String getScreenshot(WebDriver driver, String screenShotName) throws IOException {
        String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "\\Report\\failureImages\\" + screenShotName + "_" + currentDate + ".png";
        File fileDestination = new File(destination);
        FileUtils.copyFile(source, fileDestination);
        System.out.println("Image Path: " + destination);
        return destination;
    }
}