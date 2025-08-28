package br.com.renatolop3s.sjf.driver;

import br.com.renatolop3s.sjf.enums.Target;
import br.com.renatolop3s.sjf.exceptions.BrowserNotSupportedException;
import br.com.renatolop3s.sjf.exceptions.HeadlessNotSupportedException;
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
import java.util.Map;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;
import static br.com.renatolop3s.sjf.driver.DriverArguments.DISABLE_DEV_SHM_USAGE;
import static br.com.renatolop3s.sjf.driver.DriverArguments.DISABLE_EXTENSIONS;
import static br.com.renatolop3s.sjf.driver.DriverArguments.DISABLE_GPU;
import static br.com.renatolop3s.sjf.driver.DriverArguments.DISABLE_INFOBARS;
import static br.com.renatolop3s.sjf.driver.DriverArguments.DISABLE_NOTIFICATIONS;
import static br.com.renatolop3s.sjf.driver.DriverArguments.HEADLESS;
import static br.com.renatolop3s.sjf.driver.DriverArguments.HEADLESS_CHROMIUM;
import static br.com.renatolop3s.sjf.driver.DriverArguments.IGNORE_CERTIFICATE_ERRORS;
import static br.com.renatolop3s.sjf.driver.DriverArguments.NO_SANDBOX;
import static br.com.renatolop3s.sjf.driver.DriverArguments.REMOTE_ALLOW_ORIGINS;
import static br.com.renatolop3s.sjf.driver.DriverArguments.START_MAXIMIZED;

@Slf4j
public enum DriverFactory {

    CHROME {
        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    NO_SANDBOX,
                    DISABLE_GPU,
                    DISABLE_INFOBARS,
                    DISABLE_EXTENSIONS,
                    DISABLE_NOTIFICATIONS,
                    DISABLE_DEV_SHM_USAGE,
                    REMOTE_ALLOW_ORIGINS,
                    IGNORE_CERTIFICATE_ERRORS
            );
            if (cfg().headless()) {
                options.addArguments(HEADLESS_CHROMIUM);
            }
            if (cfg().target() == Target.LOCAL) {
                options.addArguments(START_MAXIMIZED);
            }
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
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

        @Override
        public WebDriver createTestcontainersDriver() {
            return null;
        }
    },

    FIREFOX {
        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            if (cfg().headless()) {
                options.addArguments(HEADLESS);
            }
            if (cfg().target() == Target.LOCAL) {
                options.addArguments(START_MAXIMIZED);
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

        @Override
        public WebDriver createTestcontainersDriver() {
            return null;
        }
    },

    EDGE {
        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();
            if (cfg().headless()) {
                options.addArguments(HEADLESS_CHROMIUM);
            }
            if (cfg().target() == Target.LOCAL) {
                options.addArguments(START_MAXIMIZED);
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

        @Override
        public WebDriver createTestcontainersDriver() {
            return null;
        }
    },

    OPERA {
        @Override
        public AbstractDriverOptions<?> getOptions() {
            return null;
        }

        @Override
        public WebDriver createLocalDriver() {
            throw new BrowserNotSupportedException(
                    """
                    Opera is not currently supported.
                    Consider using an alternative browser, such as Chrome, Edge, Firefox, or Safari.
                    """
            );
        }

        @Override
        public WebDriver createRemoteDriver() {
            throw new BrowserNotSupportedException(
                    """
                    Opera is not supported for execution on the current Selenium grid setup.
                    Ensure the grid supports Opera or consider using an alternative browser, such as Chrome or Firefox, for compatibility.
                    """
            );
        }

        @Override
        public WebDriver createTestcontainersDriver() {
            throw new BrowserNotSupportedException(
                    """
                    Opera is not supported for execution on the current Testcontainers setup.
                    Consider using an alternative browser, such as Chrome, Edge, or Firefox.
                    """
            );
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
            throw new BrowserNotSupportedException(
                    """
                    Safari is not supported for execution on the current Selenium grid setup.
                    Ensure the grid supports Safari or consider alternative solutions such as a macOS environment with safaridriver.
                    """
            );
        }

        @Override
        public WebDriver createTestcontainersDriver() {
            throw new BrowserNotSupportedException(
                    """
                    Safari is not supported for execution on the current Testcontainers setup.
                    Consider using an alternative browser, such as Chrome, Edge, or Firefox.
                    """
            );
        }
    };

    public abstract AbstractDriverOptions<?> getOptions();
    public abstract WebDriver createLocalDriver();
    public abstract WebDriver createRemoteDriver();
    public abstract WebDriver createTestcontainersDriver();

    @SneakyThrows
    private static URL getRemoteUrl() {
        return URI.create(cfg().remoteUrl()).toURL();
    }
}
