package pages.login;

import br.com.renatolop3s.sjf.core.BasePage;
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

    public LoginPage enterUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return this;
    }

    public InventoryPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
        return new InventoryPage();
    }

    public String getErrorMessage() {
        return waitForElementToBeVisible(errorMessage).getText();
    }
}
