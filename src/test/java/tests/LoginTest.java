package tests;

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

    @Test(testName = "Негативный тест на логин – ввод неизвестного значения имени пользователя")
    public void negativeLoginTestWithUnknownUsername() {
        loginPage.open();
        loginPage.login("standarduser", "secret_sauce");
        assertTrue(loginPage.checkDisplayingError(), "Ошибка не появилась");
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(loginPage.checkErrorMessage(), expectedErrorMessage);
    }

    @Test(testName = "Негативный тест на логин – ввод заблокированного значения имени пользователя")
    public void negativeLoginTestWithLockedOutUsername() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.checkDisplayingError(), "Ошибка не появилась");
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        assertEquals(loginPage.checkErrorMessage(), expectedErrorMessage);
    }

    @Test(testName = "Негативный тест на логин – ввод пустого значения имени пользователя")
    public void negativeLoginTestWithEmptyUsername() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertTrue(loginPage.checkDisplayingError(), "Ошибка не появилась");
        String expectedErrorMessage = "Epic sadface: Username is required";
        assertEquals(loginPage.checkErrorMessage(), expectedErrorMessage);
    }

    @Test(testName = "Негативный тест на логин – ввод пустого значения пароля")
    public void negativeLoginTestWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertTrue(loginPage.checkDisplayingError(), "Ошибка не появилась");
        String expectedErrorMessage = "Epic sadface: Password is required";
        assertEquals(loginPage.checkErrorMessage(), expectedErrorMessage);
    }
}
