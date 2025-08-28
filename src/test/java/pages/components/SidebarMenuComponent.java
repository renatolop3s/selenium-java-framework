package pages.components;

import br.com.renatolop3s.sjf.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.login.LoginPage;

public class SidebarMenuComponent extends BasePage<SidebarMenuComponent> {

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutLink;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateLink;

    @Step("Logout from application")
    public LoginPage logout() {
        waitForElementToBeVisible(logoutLink).click();
        return new LoginPage();
    }
}
