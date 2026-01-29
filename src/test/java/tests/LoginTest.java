package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(testName = "Позитивный тест на логин")
    public void positiveLoginTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.checkDisplayingTitle(), "Заголовок не появился");
        assertEquals(productsPage.checkTitleText(), "Products");
    }

    @DataProvider
    public Object[][] negativeTestLoginData() {
        return new Object[][]{
                {"standarduser", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}};
    }

    @Test(dataProvider = "negativeTestLoginData", testName = "Негативный тест на логин")
    public void negativeLoginTest(String userName, String password, String expectedErrorMessage) {
        loginPage.open();
        loginPage.login(userName, password);
        assertTrue(loginPage.checkDisplayingError(), "Ошибка не появилась");
        assertEquals(loginPage.checkErrorMessage(), expectedErrorMessage);
    }
}