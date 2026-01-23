import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
//import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;

public class AndroidApp {

    private AndroidDriver driver;

    String userName = System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY");
    String grid_url = "mobile-hub.lambdatest.com";

    @Test
    @Parameters({"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {

        try {
            // -------- Appium Options (W3C compliant) --------


            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("w3c", true);
            ltOptions.put("build", "Java TestNG");
            ltOptions.put("name", platform + " " + device + " " + version);
            ltOptions.put("deviceName", device);
            ltOptions.put("platformVersion", version);
            ltOptions.put("platformName", platform);
            ltOptions.put("app", "lt://APP1016054801767535388174642");
            ltOptions.put("autoGrantPermissions", true);
            ltOptions.put("isRealMobile", true);
            capabilities.setCapability("lt:options", ltOptions);


            String hub = "https://" + userName + ":" + accessKey + "@"
                    + grid_url + "/wd/hub";

            driver = new AndroidDriver(new URL(hub), capabilities);

            // -------- App Actions --------
            driver.findElement(By.id("com.lambdatest.proverbial:id/color")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("com.lambdatest.proverbial:id/color")).click();

            driver.findElement(By.id("com.lambdatest.proverbial:id/Text")).click();
            driver.findElement(By.id("com.lambdatest.proverbial:id/toast")).click();

            driver.findElement(By.id("com.lambdatest.proverbial:id/notification")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("com.lambdatest.proverbial:id/geoLocation")).click();
            Thread.sleep(5000);

            driver.findElement(AppiumBy.accessibilityId("Home")).click();

            driver.findElement(By.id("com.lambdatest.proverbial:id/speedTest")).click();
            Thread.sleep(5000);

            driver.findElement(AppiumBy.accessibilityId("Home")).click();

            driver.findElement(AppiumBy.accessibilityId("Browser")).click();

            driver.findElement(By.id("com.lambdatest.proverbial:id/url"))
                    .sendKeys("https://www.lambdatest.com");

            driver.findElement(By.id("com.lambdatest.proverbial:id/find")).click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
