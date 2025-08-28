package br.com.renatolop3s.sjf.listeners;

import br.com.renatolop3s.sjf.utils.WebDriverUtils;
import com.epam.reportportal.service.ReportPortal;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result, "SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result, "FAILURE");
    }

    private void captureScreenshot(ITestResult result, String status) {
        byte[] screenshot = WebDriverUtils.captureScreenshot();

        if (screenshot.length > 0) {
            String testName = result.getName();
            String directoryPath = "target/screenshots";
            String filePath = directoryPath + "/" + testName + "_" + status + ".png";

            try {
                // Ensure the directory exists
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    boolean created = directory.mkdirs();
                    if (created) {
                        log.info("Created directory for screenshots: {}", directoryPath);
                    } else {
                        log.warn("Failed to create directory for screenshots: {}", directoryPath);
                    }
                }

                // Save the screenshot locally
                File screenshotFile = new File(filePath);
                try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
                    fos.write(screenshot);
                    log.info("Screenshot saved: {}", filePath);
                }

                // Attach screenshot to ReportPortal
                ReportPortal.emitLog(
                        testName + " - Screenshot on " + status,
                        "INFO",
                        new Date(),
                        screenshotFile
                );
            } catch (Exception e) {
                log.error("Error saving screenshot for test: {}", testName, e);
            }
        } else {
            log.warn("No screenshot captured for test: {}", result.getName());
        }
    }
}
