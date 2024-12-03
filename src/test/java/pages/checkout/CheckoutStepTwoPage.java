package pages.checkout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SharedComponentsPage;

public class CheckoutStepTwoPage extends SharedComponentsPage<CheckoutStepTwoPage> {

    @FindBy(css = "[data-test=finish]")
    private WebElement finishButton;

    public CheckoutCompletePage clickOnFinishButton() {
        finishButton.click();
        return new CheckoutCompletePage();
    }
}
