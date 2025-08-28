package tests;

import br.com.renatolop3s.sjf.core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.inventory.InventoryPage;
import pages.login.LoginPage;

import static br.com.renatolop3s.sjf.utils.MessageReader.getMessage;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Login")
public class LoginTest extends BaseTest {

    @Test
    @Description("Login with valid credentials")
    public void shouldLoginSuccessfullyWithValidCredentials() {
        InventoryPage inventoryPage = new LoginPage()
                .loginAs("standard_user", "secret_sauce");

        assertThat(inventoryPage.isAt())
                .isTrue();
    }

    @Test
    @Description("Login with invalid credentials")
    public void shouldShowErrorMessageWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage()
                .enterUsername("wrong_user")
                .enterPassword("wrong_password")
                .clickOnLoginButton();

        assertThat(loginPage.getErrorMessage())
                .isEqualTo(getMessage("login.error.invalidCredentials"));
    }

    @Test
    @Description("Login with a locked-out user")
    public void shouldShowErrorMessageForLockedOutUser() {
        LoginPage loginPage = new LoginPage()
                .enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .clickOnLoginButton();

        assertThat(loginPage.getErrorMessage())
                .isEqualTo(getMessage("login.error.userLockedOut"));
    }

    @Test
    @Description("Logout from the application")
    public void shouldLogoutAndReturnToLoginPage() {
        LoginPage loginPage = new LoginPage()
                .loginAs("standard_user", "secret_sauce")
                .openSidebarMenu()
                .logout();

        assertThat(loginPage.isAt())
                .isTrue();
    }

}
