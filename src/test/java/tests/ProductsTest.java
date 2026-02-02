package tests;

import org.testng.annotations.Test;
import user.UserFactory;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {
    private Random random = new Random();
    private final List<String> expectedProducts = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");

    @Test(testName = "Добавление случайного товара в корзину")
    public void addRandProductTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
                String randomProduct = expectedProducts.get(random.nextInt(expectedProducts.size()));
        productsPage.addToCart(randomProduct);
        assertTrue(productsPage.checkVisibilityCartCounter(), "Счётчик корзины не появился на страницы");
        assertEquals(productsPage.checkCartCounter(), "1");
    }

    @Test(testName = "Добавление двух товаров в корзину")
    public void addToCartTwoProductsTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.addToCart(expectedProducts.get(2), expectedProducts.get(5));
        assertTrue(productsPage.checkVisibilityCartCounter(), "Счётчик корзины не появился на страницы");
        assertEquals(productsPage.checkCartCounter(), "2");
    }

    @Test(testName = "Добавление и удаление случайного товара из корзины на странице продуктов")
    public void addAndDeleteRandProductInProductPageTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        String randomProduct = expectedProducts.get(random.nextInt(expectedProducts.size()));
        productsPage.addToCart(randomProduct);
        assertEquals(productsPage.checkCartText(), "1");
        productsPage.removeFromCart(randomProduct);
        assertEquals(productsPage.checkCartText(), "");
    }

    @Test(testName = "Добавление и удаление двух товаров из корзины на странице продуктов")
    public void addAndRemoveFromCartTwoProductsInProductsPageTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.addToCart(expectedProducts.get(1), expectedProducts.get(3));
        assertEquals(productsPage.checkCartText(), "2");
        productsPage.removeFromCart(expectedProducts.get(1), expectedProducts.get(3));
        assertEquals(productsPage.checkCartText(), "");
    }
}