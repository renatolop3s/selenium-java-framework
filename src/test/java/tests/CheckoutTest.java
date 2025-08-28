package tests;

import br.com.renatolop3s.sjf.core.BaseTest;
import data.factory.CustomerFactory;
import data.model.Customer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.checkout.CheckoutCompletePage;
import pages.checkout.CheckoutStepOnePage;
import pages.inventory.InventoryPage;
import pages.login.LoginPage;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;
import static br.com.renatolop3s.sjf.utils.MessageReader.getMessage;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Checkout")
public class CheckoutTest extends BaseTest {

    private Customer customer;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void login() {
        customer = CustomerFactory.create();
        inventoryPage = new LoginPage()
                .open(cfg().baseUrl())
                .loginAs(customer.getUsername(), customer.getPassword());
    }

    @Test
    @Description("Checkout with valid information")
    public void shouldCompleteCheckoutSuccessfully() {
        CheckoutCompletePage checkoutCompletePage = inventoryPage
                .addNthItemToCart(0)
                .header()
                .goToCart()
                .clickOnCheckoutButton()
                .checkoutAs(customer)
                .clickOnFinishButton();

        assertThat(checkoutCompletePage.getCompleteMessageHeader())
                .isEqualTo(getMessage("checkout.success.messageHeader"));

        assertThat(checkoutCompletePage.getCompleteMessageText())
                .isEqualTo(getMessage("checkout.success.messageText"));
    }

    @Test
    @Description("Checkout and get back to home page")
    public void shouldGoToInventoryPageAfterCompleteCheckoutSuccessfully() {
        inventoryPage = inventoryPage
                .addNthItemToCart(1)
                .header()
                .goToCart()
                .clickOnCheckoutButton()
                .checkoutAs(customer)
                .clickOnFinishButton()
                .clickOnBackHomeButton();

        assertThat(inventoryPage.isAt()).isTrue();
    }

    @Test
    @Description("Checkout without 'First Name' information")
    public void shouldShowErrorWithInvalidCheckoutInformation_FirstName() {
        CheckoutStepOnePage checkoutStepOnePage = inventoryPage
                .addNthItemToCart(2)
                .header()
                .goToCart()
                .clickOnCheckoutButton()
                .clickOnContinueButton();

        assertThat(checkoutStepOnePage.getErrorMessage())
                .isEqualTo(getMessage("checkout.error.firstNameRequired"));
    }

    @Test
    @Description("Checkout without 'Last Name' information")
    public void shouldShowErrorWithInvalidCheckoutInformation_LastName() {
        CheckoutStepOnePage checkoutStepOnePage = inventoryPage
                .addNthItemToCart(3)
                .header()
                .goToCart()
                .clickOnCheckoutButton()
                .enterFirstName(customer.getFirstName())
                .clickOnContinueButton();

        assertThat(checkoutStepOnePage.getErrorMessage())
                .isEqualTo(getMessage("checkout.error.lastNameRequired"));
    }

    @Test
    @Description("Checkout without 'Zip/Postal Code' information")
    public void shouldShowErrorWithInvalidCheckoutInformation_ZipCode() {
        CheckoutStepOnePage checkoutStepOnePage = inventoryPage
                .addNthItemToCart(4)
                .header()
                .goToCart()
                .clickOnCheckoutButton()
                .enterFirstName(customer.getFirstName())
                .enterLastName(customer.getLastName())
                .clickOnContinueButton();

        assertThat(checkoutStepOnePage.getErrorMessage())
                .isEqualTo(getMessage("checkout.error.postalCodeRequired"));
    }
}
