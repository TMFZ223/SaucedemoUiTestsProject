package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String add_to_cart = "//*[text()='%s']//ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private static final String remove = "//*[text()='%s']//ancestor::div[@class='inventory_item']//child::button[text()='Remove']";
    private final By title = By.cssSelector("[data-test='title']");
    private final By cartCounter = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkDisplayingTitle() {
        return driver.findElement(title).isDisplayed();
    }

    public String checkTitleText() {
        return driver.findElement(title).getText();
    }

    // Метод для добавления одного или более товаров в корзину
    public void addToCart(String... products) {
        for (String product : products) {
            By addToCart = By.xpath(add_to_cart.formatted(product));
            driver.findElement(addToCart).click();
        }
    }

    // Метод для удаления одного или более товаров из корзины
    public void removeFromCart(String... products) {
        for (String product : products) {
            By removeFromCart = By.xpath(remove.formatted(product));
            driver.findElement(removeFromCart).click();
        }
    }

    public boolean checkVisibilityCartCounter() {
        return driver.findElement(cartCounter).isDisplayed();
    }

    public String checkCartCounter() {
        return driver.findElement(cartCounter).getText();
    }

    public String checkCartText() {
        return driver.findElement(cartLink).getText();
    }
}