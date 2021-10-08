package utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class TestWebDriver {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void launchBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("CHROME")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver.set(new ChromeDriver());
        }
        if (browserName.equalsIgnoreCase("FIREFOX")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            driver.set(new FirefoxDriver());
        }
        size(Integer.parseInt(TestProperties.WEB_BROWSER_WIDTH), Integer.parseInt(TestProperties.WEB_BROWSER_HEIGHT));
    }

    public static WebDriver get() {
        return driver.get();
    }

    public static void remove() {
        driver.get().quit();
        driver.remove();
    }

    private static void size(int x, int y) {
        Dimension dimension = new Dimension(x, y);
        driver.get().manage().window().setSize(dimension);
    }

    public static String screenshot() {
        return ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BASE64);
    }

    public static void launchDevice(int x, int y, double pixelRatio) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        String userAgent = "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
        // setting device metrics
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", x);
        deviceMetrics.put("height", y);
        deviceMetrics.put("pixelRatio", pixelRatio);
        // setting device emulation
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", userAgent);
        // setting chrome options
        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver.set(new ChromeDriver(desiredCapabilities));
    }

}
