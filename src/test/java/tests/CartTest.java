package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import user.UserFactory;

import java.util.Random;

import static enums.PageTitles.Cart;

@Feature("Работа с корзиной товаров")
public class CartTest extends BaseTest {
    private Random random = new Random();

    @Test(description = "Переход в корзину")
    public void testGoCart() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.swichToCart();
        soft.assertTrue(cartPage.checkDisplayingTitle());
        soft.assertEquals(cartPage.checkTitleText(), Cart.getDisplayName());
        soft.assertAll();
    }

    @Test(description = "Добавление и просмотр товара в корзине")
    public void addToCartAndDisplayProductTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        String randomProduct = expectedProducts.get(random.nextInt(expectedProducts.size()));
        productsPage.addToCart(randomProduct);
        productsPage.swichToCart();
        soft.assertEquals(cartPage.getProductNames().size(), 1);
        soft.assertTrue(cartPage.getProductNames().contains(randomProduct));
        soft.assertAll();
    }

    @Test(description = "Добавление и просмотр двух товаров в корзине")
    public void addAndDisplayTwoProductsTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.addToCart(expectedProducts.get(2), expectedProducts.get(5));
        productsPage.swichToCart();
        soft.assertEquals(cartPage.getProductNames().size(), 2);
        soft.assertTrue(cartPage.getProductNames().contains(expectedProducts.get(2)) && cartPage.getProductNames().contains(expectedProducts.get(5)));
        soft.assertAll();
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
        soft.assertEquals(cartPage.getProductNames().size(), 2);
        soft.assertTrue(cartPage.getProductNames().contains(expectedProducts.get(0)) && cartPage.getProductNames().contains(expectedProducts.get(3)));
        soft.assertAll();
    }
}
