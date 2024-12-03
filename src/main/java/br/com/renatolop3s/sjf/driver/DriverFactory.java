package br.com.renatolop3s.sjf.driver;

import br.com.renatolop3s.sjf.exception.BrowserNotSupportedException;
import br.com.renatolop3s.sjf.exception.HeadlessNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;
import static br.com.renatolop3s.sjf.driver.DriverArguments.*;
import static java.lang.String.format;

@Slf4j
public enum DriverFactory {

    CHROME {
        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(START_MAXIMIZED);
            options.addArguments(DISABLE_INFOBARS);
            options.addArguments(DISABLE_NOTIFICATIONS);
            options.addArguments(REMOTE_ALLOW_ORIGINS);
            if (cfg().headless()) {
                options.addArguments(CHROME_HEADLESS);
            }
            if ("grid".equalsIgnoreCase(cfg().target())) {
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    put("sessionTimeout", cfg().gridSessionTimeout());
                    put("enableVnc", cfg().gridEnableVnc());
                    put("enableVideo", cfg().gridEnableVideo());
                }});
            }
            return options;
        }

        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(getOptions());
        }

        @Override
        public WebDriver createRemoteDriver() {
            WebDriverManager.chromedriver().setup();
            return new RemoteWebDriver(getRemoteUrl(), getOptions());
        }
    },

    FIREFOX {
        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments(START_MAXIMIZED);
            if (cfg().headless()) {
                options.addArguments(HEADLESS);
            }
            if ("grid".equalsIgnoreCase(cfg().target())) {
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    put("sessionTimeout", cfg().gridSessionTimeout());
                    put("enableVnc", cfg().gridEnableVnc());
                    put("enableVideo", cfg().gridEnableVideo());
                }});
            }
            return options;
        }

        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(getOptions());
        }

        @Override
        public WebDriver createRemoteDriver() {
            WebDriverManager.firefoxdriver().setup();
            return new RemoteWebDriver(getRemoteUrl(), getOptions());
        }
    },

    EDGE {
        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();
            options.addArguments(START_MAXIMIZED);
            if (cfg().headless()) {
                options.addArguments(HEADLESS);
            }
            if ("grid".equalsIgnoreCase(cfg().target())) {
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    put("sessionTimeout", cfg().gridSessionTimeout());
                    put("enableVnc", cfg().gridEnableVnc());
                    put("enableVideo", cfg().gridEnableVideo());
                }});
            }
            return options;
        }

        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(getOptions());
        }

        @Override
        public WebDriver createRemoteDriver() {
            WebDriverManager.edgedriver().setup();
            return new RemoteWebDriver(getRemoteUrl(), getOptions());
        }
    },

    OPERA {
        @Override
        public AbstractDriverOptions<?> getOptions() {
            return null;
        }

        @Override
        public WebDriver createLocalDriver() {
            throw new BrowserNotSupportedException("Browser not supported: Opera. " +
                    "consider using an alternative browser, such as Chrome, Firefox, Edge, or Safari.");
        }

        @Override
        public WebDriver createRemoteDriver() {
            throw new BrowserNotSupportedException("Opera is not supported for execution on the current Selenium grid setup. " +
                    "Ensure the grid supports Opera or consider using an alternative browser, such as Chrome or Firefox, for compatibility.");
        }
    },

    SAFARI {
        @Override
        public SafariOptions getOptions() {
            // Safari does not support addArguments, but maximization can be managed by WebDriver directly.
            if (cfg().headless()) {
                throw new HeadlessNotSupportedException("Safari does not support headless mode.");
            }
            return new SafariOptions();
        }

        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.safaridriver().setup();
            return new SafariDriver(getOptions());
        }

        @Override
        public WebDriver createRemoteDriver() {
            throw new BrowserNotSupportedException("Safari is not supported for execution on the current Selenium grid setup. " +
                    "Ensure the grid supports Safari or consider alternative solutions such as a macOS environment with safaridriver.");
        }
    };

    public abstract AbstractDriverOptions<?> getOptions();
    public abstract WebDriver createLocalDriver();
    public abstract WebDriver createRemoteDriver();

    @SneakyThrows
    private static URL getRemoteUrl() {
        return URI.create(cfg().gridUrl()).toURL();
    }
}
