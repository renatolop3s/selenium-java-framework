package pages.checkout;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SharedComponentsPage;
import pages.inventory.InventoryPage;

public class CheckoutCompletePage extends SharedComponentsPage<CheckoutCompletePage> {

    @FindBy(css = "[data-test=back-to-products]")
    private WebElement backHomeButton;

    @FindBy(className = "complete-header")
    private WebElement completeMessageHeader;

    @FindBy(className = "complete-text")
    private WebElement completeMessageText;

    @Step("Click on back to home button")
    public InventoryPage clickOnBackHomeButton() {
        backHomeButton.click();
        return new InventoryPage();
    }

    public String getCompleteMessageHeader() {
        return completeMessageHeader.getText();
    }

    public String getCompleteMessageText() {
        return completeMessageText.getText();
    }
}
