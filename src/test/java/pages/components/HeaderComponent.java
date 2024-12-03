package pages.components;

import br.com.renatolop3s.sjf.core.BasePage;
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

    public SidebarMenuComponent openSidebarMenu() {
        menuButton.click();
        return new SidebarMenuComponent();
    }

    public CartPage goToCart() {
        shoppingCartLink.click();
        return new CartPage();
    }

    public int getItemCountInCart() {
        return Integer.parseInt(waitForElementToBeVisible(shoppingCartBadge).getText());
    }

    public boolean isCartEmpty() {
        return waitForElementToDisappear(shoppingCartBadge);
    }
}
