package br.com.renatolop3s.sjf.dsl;

import br.com.renatolop3s.sjf.driver.DriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WebDriverDsl<T extends WebDriverDsl<T>> {

    protected WebDriverWait wait;

    protected WebDriverDsl() {
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
    }

    @SneakyThrows
    public T sleep(long millis) {
        Thread.sleep(millis);
        return (T) this;
    }

    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(visibilityOf(element));
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(visibilityOfElementLocated(locator));
    }

    protected boolean waitForElementToDisappear(WebElement element) {
        return wait.until(invisibilityOf(element));
    }
}
