package utils;

import java.io.FileReader;
import java.nio.file.Paths;

public class TestProperties {

    static {
        initRunProperties();
        initEnvironmentProperties();
        initDeviceProperties();
    }

    // run properties
    public static String RUN_ON_CHROME_EMULATOR;
    public static String DEVICE_NAME;
    public static String ENVIRONMENT_NAME;
    public static String PARALLEL_THREAD_COUNT;
    public static String WEB_BROWSER_NAME;
    public static String WEB_BROWSER_WIDTH;
    public static String WEB_BROWSER_HEIGHT;
    public static String EXTENT_REPORT_TITLE;
    public static String EXTENT_REPORT_NAME;
    public static String CREATE_SCREENS_REPORT;

    // device properties
    public static String DEVICE_WIDTH;
    public static String DEVICE_HEIGHT;
    public static String DEVICE_PIXEL_RATIO;

    // environment properties
    public static String WEB_HOST_URL;
    public static String SERVICE_HOST_URL;

    // initialising general properties
    public static void initRunProperties() {
        String filePath = Paths.get(System.getProperty("user.dir"), "run.properties").toString();
        RUN_ON_CHROME_EMULATOR = getProperties(filePath).getProperty("runOnChromeEmulator");
        DEVICE_NAME = getProperties(filePath).getProperty("deviceName");
        ENVIRONMENT_NAME = getProperties(filePath).getProperty("environmentName");
        PARALLEL_THREAD_COUNT = getProperties(filePath).getProperty("parallelThreadCount");
        WEB_BROWSER_NAME = getProperties(filePath).getProperty("browserName");
        WEB_BROWSER_WIDTH = getProperties(filePath).getProperty("browserWidth");
        WEB_BROWSER_HEIGHT = getProperties(filePath).getProperty("browserHeight");
        EXTENT_REPORT_TITLE = getProperties(filePath).getProperty("reportTitle");
        EXTENT_REPORT_NAME = getProperties(filePath).getProperty("reportName");
        CREATE_SCREENS_REPORT = getProperties(filePath).getProperty("createScreensReport");
    }

    // initialising environment properties
    public static void initEnvironmentProperties() {
        String filePath = "";
        if (ENVIRONMENT_NAME.equalsIgnoreCase("INT")) {
            filePath = Paths.get(System.getProperty("user.dir"), "environments", "int.properties").toString();
        } else if (ENVIRONMENT_NAME.equalsIgnoreCase("UAT")) {
            filePath = Paths.get(System.getProperty("user.dir"), "environments", "uat.properties").toString();
        } else {
            filePath = Paths.get(System.getProperty("user.dir"), "environments", "qa.properties").toString();
        }
        WEB_HOST_URL = getProperties(filePath).getProperty("webHostURL");
        SERVICE_HOST_URL = getProperties(filePath).getProperty("serviceHostURL");
    }

    // initialising device properties
    public static void initDeviceProperties() {
        if (RUN_ON_CHROME_EMULATOR.equalsIgnoreCase("TRUE")) {
            switch (DEVICE_NAME.toUpperCase()) {
                case "IPADPRO":
                    setDeviceProperties("iPadPro");
                    break;
                case "IPHONEX":
                    setDeviceProperties("iPhoneX");
                    break;
                case "MOTOG4":
                    setDeviceProperties("motoG4");
                    break;
                case "PIXEL2XL":
                    setDeviceProperties("pixel2XL");
                    break;
            }
        }
    }

    // setting device properties
    private static void setDeviceProperties(String deviceName){
        String filePath = Paths.get(System.getProperty("user.dir"), "devices", deviceName + ".properties").toString();
        DEVICE_WIDTH = getProperties(filePath).getProperty("deviceWidth");
        DEVICE_HEIGHT = getProperties(filePath).getProperty("deviceHeight");
        DEVICE_PIXEL_RATIO = getProperties(filePath).getProperty("devicePixelRatio");
    }

    // getting properties object
    private static java.util.Properties getProperties(String filePath) {
        java.util.Properties properties = new java.util.Properties();
        try {
            properties.load(new FileReader(filePath));
        } catch (Exception e) {
            System.out.println("Not able to read properties file: " + filePath);
            System.out.println(e.toString());
        }
        return properties;
    }

}
