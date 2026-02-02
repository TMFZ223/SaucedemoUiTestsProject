package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By cartProduct = By.cssSelector(".inventory_item_name");
    private final By continueShopping = By.cssSelector(DATA_TEST_PATTERN.formatted("continue-shopping"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueShopping() {
        driver.findElement(continueShopping).click();
    }

    public ArrayList<String> getProductNames() {
        List<WebElement> allProducts = driver.findElements(cartProduct);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }
}
