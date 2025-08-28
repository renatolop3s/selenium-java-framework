package pages.login;

import br.com.renatolop3s.sjf.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.inventory.InventoryPage;

public class LoginPage extends BasePage<LoginPage> {

    @FindBy(css = "[data-test=username]")
    private WebElement usernameInput;

    @FindBy(css = "[data-test=password]")
    private WebElement passwordInput;

    @FindBy(css = "[data-test=login-button]")
    private WebElement loginButton;

    @FindBy(css = "[data-test=error]")
    private WebElement errorMessage;

    public boolean isAt() {
        return loginButton.isDisplayed();
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Click on login button")
    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Login as user: {username}")
    public InventoryPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
        return new InventoryPage();
    }

    @Step("Get login error message")
    public String getErrorMessage() {
        return waitForElementToBeVisible(errorMessage).getText();
    }
}
