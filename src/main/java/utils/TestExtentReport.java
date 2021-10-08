package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestExtentReport {

    public static ExtentReports extent;
    public static ExtentSparkReporter spark;

    static {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(Paths.get(System.getProperty("user.dir"), "reports", "TestExtentReport.html").toString());
        extent.attachReporter(spark);
        spark.config().setTimelineEnabled(false);
        spark.config().setTheme(Theme.STANDARD);
    }

    public static void init() {
        spark.config().setDocumentTitle(TestProperties.EXTENT_REPORT_TITLE);
        spark.config().setReportName(TestProperties.EXTENT_REPORT_NAME);
        extent.setSystemInfo("OS Name", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("runOnChromeEmulator", TestProperties.RUN_ON_CHROME_EMULATOR);
        if (TestProperties.RUN_ON_CHROME_EMULATOR.equalsIgnoreCase("TRUE")) {
            extent.setSystemInfo("deviceName", TestProperties.DEVICE_NAME);
            extent.setSystemInfo("deviceWidth", TestProperties.DEVICE_WIDTH);
            extent.setSystemInfo("deviceHeight", TestProperties.DEVICE_HEIGHT);
            extent.setSystemInfo("devicePixelRatio", TestProperties.DEVICE_PIXEL_RATIO);
        }
        extent.setSystemInfo("environmentName", TestProperties.ENVIRONMENT_NAME);
        extent.setSystemInfo("parallelThreadCount", TestProperties.PARALLEL_THREAD_COUNT);
        if (!TestProperties.RUN_ON_CHROME_EMULATOR.equalsIgnoreCase("TRUE")) {
            extent.setSystemInfo("browserName", TestProperties.WEB_BROWSER_NAME);
            extent.setSystemInfo("browserWidth", TestProperties.WEB_BROWSER_WIDTH);
            extent.setSystemInfo("browserHeight", TestProperties.WEB_BROWSER_HEIGHT);
        }
    }

    public static Map<URI, ExtentTest> testFeature = new HashMap<>();
    public static ThreadLocal<ExtentTest> testScenario = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> testStep = new ThreadLocal<>();

}
