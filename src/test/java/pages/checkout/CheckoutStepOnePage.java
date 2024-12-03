package pages.checkout;

import data.model.Customer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SharedComponentsPage;

public class CheckoutStepOnePage extends SharedComponentsPage<CheckoutStepOnePage> {

    @FindBy(css = "[data-test=firstName]")
    private WebElement firstNameInput;

    @FindBy(css = "[data-test=lastName]")
    private WebElement lastNameInput;

    @FindBy(css = "[data-test=postalCode]")
    private WebElement postalCodeInput;

    @FindBy(css = "[data-test=continue]")
    private WebElement continueButton;

    @FindBy(css = "[data-test=error]")
    private WebElement errorMessage;

    public CheckoutStepOnePage enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public CheckoutStepOnePage enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public CheckoutStepOnePage enterPostalCodeName(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
        return this;
    }

    public CheckoutStepOnePage clickOnContinueButton() {
        continueButton.click();
        return this;
    }

    public CheckoutStepTwoPage checkoutAs(Customer customer) {
        enterFirstName(customer.getFirstName());
        enterLastName(customer.getLastName());
        enterPostalCodeName(customer.getPostalCode());
        clickOnContinueButton();
        return new CheckoutStepTwoPage();
    }

    public String getErrorMessage() {
        return waitForElementToBeVisible(errorMessage).getText();
    }
}
