package br.com.renatolop3s.sjf.core;

import br.com.renatolop3s.sjf.driver.DriverManager;
import br.com.renatolop3s.sjf.driver.TargetFactory;
import br.com.renatolop3s.sjf.listener.AllureEnvironmentListener;
import br.com.renatolop3s.sjf.listener.ReportPortalTestListener;
import br.com.renatolop3s.sjf.listener.TestListener;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;

@Listeners({
        TestListener.class,
        AllureEnvironmentListener.class,
        ExtentITestListenerClassAdapter.class,
        ReportPortalTestListener.class
})
public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        WebDriver driver = new TargetFactory().createWebDriverInstance(browser);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(cfg().url());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
