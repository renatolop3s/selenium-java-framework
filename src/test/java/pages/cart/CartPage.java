package pages.cart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SharedComponentsPage;
import pages.checkout.CheckoutStepOnePage;

public class CartPage extends SharedComponentsPage<CartPage> {

    @FindBy(css = "[data-test=checkout]")
    private WebElement checkoutButton;

    public CheckoutStepOnePage clickOnCheckoutButton() {
        checkoutButton.click();
        return new CheckoutStepOnePage();
    }
}
