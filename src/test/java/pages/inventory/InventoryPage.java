package pages.inventory;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SharedComponentsPage;

import java.util.List;

public class InventoryPage extends SharedComponentsPage<InventoryPage> {

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(css = ".inventory_list .inventory_item")
    private List<WebElement> items;

    public boolean isAt() {
        return title.isDisplayed();
    }

    @Step("Add item #{index} to cart")
    public InventoryPage addNthItemToCart(int index) {
        String addToCartButtonLocator = "[id^=add-to-cart]";
        items.get(index).findElement(By.cssSelector(addToCartButtonLocator)).click();
        return this;
    }

    @Step("Add {count} items to cart")
    public InventoryPage addMultipleItemsToCart(int count) {
        for (int i = 1; i <= count; i++) {
            addNthItemToCart(i);
        }
        return this;
    }

    @Step("Remove item #{index} from cart")
    public InventoryPage removeNthItemToCart(int index) {
        String removeButtonLocator = "[id^=remove]";
        items.get(index).findElement(By.cssSelector(removeButtonLocator)).click();
        return this;
    }
}
