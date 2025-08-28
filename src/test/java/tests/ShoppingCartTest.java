package tests;

import br.com.renatolop3s.sjf.core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.inventory.InventoryPage;
import pages.login.LoginPage;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Shopping Cart")
public class ShoppingCartTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void login() {
        String username = cfg().getProperty("username");
        String password = cfg().getProperty("password");
        inventoryPage = new LoginPage()
                .open(cfg().baseUrl())
                .loginAs(username, password);
    }

    @Test
    @Description("Add an item to the shopping cart")
    public void shouldAddItemToShoppingCart() {
        inventoryPage.addNthItemToCart(2);

        assertThat(inventoryPage.header().getItemCountInCart())
                .isEqualTo(1);
    }

    @Test
    @Description("Add an item to the shopping cart")
    public void shouldAddMultipleItemsToShoppingCart() {
        inventoryPage.addMultipleItemsToCart(5);

        assertThat(inventoryPage.header().getItemCountInCart())
                .isEqualTo(5);
    }

    @Test
    @Description("Remove an item from the shopping cart")
    public void shouldRemoveItemFromShoppingCart() {
        inventoryPage
                .addNthItemToCart(0)
                .sleep(250)
                .removeNthItemToCart(0);

        assertThat(inventoryPage.header().isCartEmpty())
                .isTrue();
    }
}
