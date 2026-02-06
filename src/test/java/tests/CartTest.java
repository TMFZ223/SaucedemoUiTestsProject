package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import user.UserFactory;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

@Feature("Работа с корзиной товаров")
public class CartTest extends BaseTest {
    private Random random = new Random();
    private final List<String> expectedProducts = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");

    @Test(description = "Переход в корзину")
    public void testGoCart() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.swichToCart();
        assertTrue(cartPage.checkDisplayingTitle());
        assertEquals(cartPage.checkTitleText(), "Your Cart");
    }

    @Test(description = "Добавление и просмотр товара в корзине")
    public void addToCartAndDisplayProductTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        String randomProduct = expectedProducts.get(random.nextInt(expectedProducts.size()));
        productsPage.addToCart(randomProduct);
        productsPage.swichToCart();
        assertEquals(cartPage.getProductNames().size(), 1);
        assertTrue(cartPage.getProductNames().contains(randomProduct));
    }

    @Test(description = "Добавление и просмотр двух товаров в корзине")
    public void addAndDisplayTwoProductsTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.addToCart(expectedProducts.get(2), expectedProducts.get(5));
        productsPage.swichToCart();
        assertEquals(cartPage.getProductNames().size(), 2);
        assertTrue(cartPage.getProductNames().contains(expectedProducts.get(2)) && cartPage.getProductNames().contains(expectedProducts.get(5)));
    }

    @Test(description = "Продолжение покупок после добавления товара в корзину")
    public void continueShoppingAfterAddProductToCartTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.addToCart(expectedProducts.get(0));
        productsPage.swichToCart();
        cartPage.clickContinueShopping();
        productsPage.addToCart(expectedProducts.get(3));
        productsPage.swichToCart();
        assertEquals(cartPage.getProductNames().size(), 2);
        assertTrue(cartPage.getProductNames().contains(expectedProducts.get(0)) && cartPage.getProductNames().contains(expectedProducts.get(3)));
    }
}
