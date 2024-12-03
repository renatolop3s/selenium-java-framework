package br.com.renatolop3s.sjf.util;

import br.com.renatolop3s.sjf.driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Slf4j
public class WebDriverUtils {

    private WebDriverUtils() {

    }

    /**
     * Captures a screenshot as a byte array.
     *
     * @return Screenshot as byte array if the driver is initialized; otherwise, an empty byte array.
     */
    public static byte[] captureScreenshot() {
        try {
            if (DriverManager.getDriver() != null) {
                return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            } else {
                log.warn("WebDriver is null. Unable to capture screenshot.");
                return new byte[0];
            }
        } catch (Exception e) {
            log.error("Error capturing screenshot: {}", e.getMessage());
            return new byte[0];
        }
    }
}
