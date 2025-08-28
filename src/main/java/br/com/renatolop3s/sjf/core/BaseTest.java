package br.com.renatolop3s.sjf.core;

import br.com.renatolop3s.sjf.driver.DriverManager;
import br.com.renatolop3s.sjf.driver.TargetFactory;
import br.com.renatolop3s.sjf.listeners.AllureEnvironmentListener;
import br.com.renatolop3s.sjf.listeners.ReportPortalTestListener;
import br.com.renatolop3s.sjf.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;

@Listeners({
        TestListener.class,
        AllureEnvironmentListener.class,
        ReportPortalTestListener.class
})
public class BaseTest {

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browser) {
        WebDriver driver = new TargetFactory().createWebDriverInstance(browser);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(cfg().baseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
