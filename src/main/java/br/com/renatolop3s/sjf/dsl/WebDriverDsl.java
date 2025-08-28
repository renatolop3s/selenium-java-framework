package br.com.renatolop3s.sjf.dsl;

import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static br.com.renatolop3s.sjf.driver.DriverManager.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@SuppressWarnings("unchecked")
public class WebDriverDsl<T extends WebDriverDsl<T>> {

    protected WebDriverWait wait;

    protected WebDriverDsl() {
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
    }

    public T open(String url) {
        getDriver().get(url);
        return (T) this;
    }

    public T refresh() {
        getDriver().navigate().refresh();
        return (T) this;
    }

    @SneakyThrows
    public T sleep(long millis) {
        Thread.sleep(millis);
        return (T) this;
    }

    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(visibilityOf(element));
    }

    protected boolean waitForElementToDisappear(WebElement element) {
        return wait.until(invisibilityOf(element));
    }
}
