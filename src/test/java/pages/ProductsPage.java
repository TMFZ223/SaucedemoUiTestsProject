package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String add_to_cart = "//*[text()='%s']//ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private static final String remove = "//*[text()='%s']//ancestor::div[@class='inventory_item']//child::button[text()='Remove']";
    private final By cartCounter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавить в корзину товары {products}")
    public void addToCart(String... products) {
        for (String product : products) {
            By addToCart = By.xpath(add_to_cart.formatted(product));
            findElement(addToCart).click();
        }
    }

    @Step("Удалить из корзины товары {products}")
    public void removeFromCart(String... products) {
        for (String product : products) {
            By removeFromCart = By.xpath(remove.formatted(product));
            findElement(removeFromCart).click();
        }
    }

    @Step("Убедиться в наличии счётчика корзины")
    public boolean checkVisibilityCartCounter() {
        return findElement(cartCounter).isDisplayed();
    }

    @Step("Проверить состояние счётчика корзины")
    public String checkCartCounter() {
        return findElement(cartCounter).getText();
    }

    @Step("Перейти в корзину")
    public void swichToCart() {
        findElement(cartLink).click();
    }

    @Step("Проверить текст ссылки корзины")
    public String checkCartText() {
        return findElement(cartLink).getText();
    }
}