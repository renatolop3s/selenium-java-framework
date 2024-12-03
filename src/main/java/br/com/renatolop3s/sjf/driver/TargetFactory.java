package br.com.renatolop3s.sjf.driver;

import br.com.renatolop3s.sjf.enums.Target;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;

@Slf4j
public class TargetFactory {

    public WebDriver createWebDriverInstance(String browser) {
        Target target = Target.valueOf(cfg().target().toUpperCase());
        String browserToUse = (browser != null) ? browser : cfg().browser();
        return switch (target) {
            case LOCAL -> DriverFactory.valueOf(browserToUse.toUpperCase()).createLocalDriver();
            case GRID -> DriverFactory.valueOf(browserToUse.toUpperCase()).createRemoteDriver();
            case CLOUD -> throw new IllegalArgumentException("Cloud Execution Not Implemented Yet!");
        };
    }
}
