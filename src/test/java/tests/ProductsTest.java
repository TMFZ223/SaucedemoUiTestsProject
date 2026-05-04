package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import user.UserFactory;

import java.util.Random;

@Feature("Работа со страницей товаров")
public class ProductsTest extends BaseTest {
    private Random random = new Random();

    @Test(description = "Добавление случайного товара в корзину")
    public void addRandProductTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        String randomProduct = expectedProducts.get(random.nextInt(expectedProducts.size()));
        productsPage.addToCart(randomProduct);
        soft.assertTrue(productsPage.checkVisibilityCartCounter(), "Счётчик корзины не появился на страницы");
        soft.assertEquals(productsPage.checkCartCounter(), "1");
    }

    @Test(description = "Добавление двух товаров в корзину")
    public void addToCartTwoProductsTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.addToCart(expectedProducts.get(2), expectedProducts.get(5));
        soft.assertTrue(productsPage.checkVisibilityCartCounter(), "Счётчик корзины не появился на страницы");
        soft.assertEquals(productsPage.checkCartCounter(), "2");
    }

    @Test(description = "Добавление и удаление случайного товара из корзины на странице продуктов")
    public void addAndDeleteRandProductInProductPageTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        String randomProduct = expectedProducts.get(random.nextInt(expectedProducts.size()));
        productsPage.addToCart(randomProduct);
        soft.assertEquals(productsPage.checkCartText(), "1");
        productsPage.removeFromCart(randomProduct);
        soft.assertEquals(productsPage.checkCartText(), "");
    }

    @Test(description = "Добавление и удаление двух товаров из корзины на странице продуктов")
    public void addAndRemoveFromCartTwoProductsInProductsPageTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.addToCart(expectedProducts.get(1), expectedProducts.get(3));
        soft.assertEquals(productsPage.checkCartText(), "2");
        productsPage.removeFromCart(expectedProducts.get(1), expectedProducts.get(3));
        soft.assertEquals(productsPage.checkCartText(), "");
    }
}