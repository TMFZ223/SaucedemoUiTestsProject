package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {
    private Random random;
    private String[] products = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};

    @Test(testName = "Добавление случайного товара в корзину")
    public void addRandProductTest() {
        random = new Random();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String randomProduct = products[random.nextInt(products.length)];
        productsPage.addToCart(randomProduct);
        assertTrue(productsPage.checkVisibilityCartCounter(), "Счётчик корзины не появился на страницы");
        assertEquals(productsPage.checkCartCounter(), "1");
    }

    @Test(testName = "Добавление двух товаров в корзину")
    public void addToCartTwoProductsTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(products[2], products[5]);
        assertTrue(productsPage.checkVisibilityCartCounter(), "Счётчик корзины не появился на страницы");
        assertEquals(productsPage.checkCartCounter(), "2");
    }
}
