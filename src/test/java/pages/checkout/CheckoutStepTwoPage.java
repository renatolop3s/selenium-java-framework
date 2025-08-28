package pages.checkout;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SharedComponentsPage;

public class CheckoutStepTwoPage extends SharedComponentsPage<CheckoutStepTwoPage> {

    @FindBy(css = "[data-test=finish]")
    private WebElement finishButton;

    @Step("Click on finish button")
    public CheckoutCompletePage clickOnFinishButton() {
        finishButton.click();
        return new CheckoutCompletePage();
    }
}
