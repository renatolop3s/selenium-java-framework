package pages.cart;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SharedComponentsPage;
import pages.checkout.CheckoutStepOnePage;

public class CartPage extends SharedComponentsPage<CartPage> {

    @FindBy(css = "[data-test=checkout]")
    private WebElement checkoutButton;

    @Step("Click on checkout button")
    public CheckoutStepOnePage clickOnCheckoutButton() {
        checkoutButton.click();
        return new CheckoutStepOnePage();
    }
}
