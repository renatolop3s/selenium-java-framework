package br.com.renatolop3s.sjf.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;

@Slf4j
public class TargetFactory {

    public WebDriver createWebDriverInstance(String browser) {
        String browserToUse = (browser != null) ? browser.toUpperCase() : cfg().browser().toUpperCase();
        return switch (cfg().target()) {
            case LOCAL -> DriverFactory.valueOf(browserToUse).createLocalDriver();
            case REMOTE -> DriverFactory.valueOf(browserToUse).createRemoteDriver();
            case TESTCONTAINERS -> DriverFactory.valueOf(browserToUse).createTestcontainersDriver();
            case CLOUD -> throw new IllegalArgumentException("Cloud Execution Not Implemented Yet!");
        };
    }
}
