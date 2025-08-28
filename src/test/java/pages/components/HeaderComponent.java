package pages.components;

import br.com.renatolop3s.sjf.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.cart.CartPage;

public class HeaderComponent extends BasePage<HeaderComponent> {

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    @Step("Open sidebar menu")
    public SidebarMenuComponent openSidebarMenu() {
        menuButton.click();
        return new SidebarMenuComponent();
    }

    @Step("Navigate to shopping cart")
    public CartPage goToCart() {
        shoppingCartLink.click();
        return new CartPage();
    }

    @Step("Get item count in cart")
    public int getItemCountInCart() {
        return Integer.parseInt(waitForElementToBeVisible(shoppingCartBadge).getText());
    }

    @Step("Check if cart is empty")
    public boolean isCartEmpty() {
        return waitForElementToDisappear(shoppingCartBadge);
    }
}
