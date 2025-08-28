package br.com.renatolop3s.sjf.core;

import br.com.renatolop3s.sjf.dsl.WebDriverDsl;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;
import static br.com.renatolop3s.sjf.driver.DriverManager.getDriver;

public abstract class BasePage<T extends BasePage<T>> extends WebDriverDsl<T> {

    protected BasePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), cfg().timeout()), this);
    }
}
