package pages;

import io.qameta.allure.Step;
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

    @Step("Кликнуть на элемент продолжения покупок")
    public void clickContinueShopping() {
        findElement(continueShopping).click();
    }
@Step("Получить список добавленных товаров в корзину")
    public ArrayList<String> getProductNames() {
        List<WebElement> allProducts = driver.findElements(cartProduct);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }
}
