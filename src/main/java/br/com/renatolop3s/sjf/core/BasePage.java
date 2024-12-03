package br.com.renatolop3s.sjf.core;

import br.com.renatolop3s.sjf.dsl.WebDriverDsl;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static br.com.renatolop3s.sjf.driver.DriverManager.getDriver;

public abstract class BasePage<T extends BasePage<T>> extends WebDriverDsl<T> {

    protected BasePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
    }

    @SuppressWarnings("unchecked")
    public T open(String url) {
        getDriver().get(url);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T refresh() {
        getDriver().navigate().refresh();
        return (T) this;
    }
}
