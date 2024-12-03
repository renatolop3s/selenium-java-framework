package br.com.renatolop3s.sjf.listener;

import com.epam.reportportal.service.ReportPortal;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;

public class ReportPortalTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // Set the description dynamically for the test run
        String description = "Dynamic description: Automated test execution";
        ReportPortal.emitLog(description, "INFO", Calendar.getInstance().getTime());

        // Add attributes dynamically
        Map<String, String> attributes = new HashMap<>();
        attributes.put("Target", cfg().target());
        attributes.put("Browser", cfg().browser());
        attributes.put("Environment", cfg().env());

        attributes.forEach((key, value) ->
                ReportPortal.emitLog("Attribute: " + key + "=" + value, "INFO", Calendar.getInstance().getTime())
        );
    }
}
