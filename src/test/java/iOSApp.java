import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class iOSApp {

    String userName = System.getenv("LT_USERNAME") != null ? System.getenv("LT_USERNAME") : "username";
    String accessKey = System.getenv("LT_ACCESS_KEY") != null ? System.getenv("LT_ACCESS_KEY") : "accessKey";
    String appId = System.getenv("LT_APP_ID") != null ? System.getenv("LT_APP_ID") : "lt://proverbial-ios";
    String gridUrl = System.getenv("LT_GRID_URL") != null ? System.getenv("LT_GRID_URL") : "mobile-hub.lambdatest.com";

    AppiumDriver driver;

    @Test
    @Parameters({ "device", "version", "platform" })
    public void iOSApp1(String device, String version, String platform) {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("w3c", true);
            ltOptions.put("build", "Java TestNG");
            ltOptions.put("name", platform + " " + device + " " + version);
            ltOptions.put("deviceName", device);
            ltOptions.put("platformVersion", version);
            ltOptions.put("platformName", platform);
            ltOptions.put("app", "lt://APP1016018631760361477812757");
            ltOptions.put("autoGrantPermissions", true);
            ltOptions.put("isRealMobile", true);
            capabilities.setCapability("lt:options", ltOptions);


            String hub = "https://" + userName + ":" + accessKey + "@" + gridUrl + "/wd/hub";
            driver = new IOSDriver(new URL(hub), capabilities);  // Use IOSDriver for iOS

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Use AppiumBy for all locators
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("color"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("toast"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("notification"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("geoLocation"))).click();
            driver.navigate().back();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("speedTest"))).click();
            driver.navigate().back();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Browser"))).click();

            WebElement url = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("url")));
            url.sendKeys("https://www.lambdatest.com");
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("find"))).click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) driver.quit();
        }
    }
}
