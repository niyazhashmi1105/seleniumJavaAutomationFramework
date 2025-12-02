package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {

    private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

    public static WebDriver intializeLambdaTestSession(String browser, String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap();
        ltOptions.put("user", "hashmimdniyaz");
        ltOptions.put("accessKey", "LT_u8IvWqNP4kJxzEoKKGd3ed8WP10q7NZC3DQrUHC6RO19rJY");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "macOS Sonoma"); //ltOptions.put("platformName", "Windows 11");
        ltOptions.put("seCdp", true);
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-java");
        ltOptions.put("selenium_version", "4.38.0");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driverLocal.set(driver);

        return driverLocal.get();
    }

    public static void quitSession(){
        if(driverLocal.get()!= null){
            driverLocal.get().quit();
        }
    }
}
