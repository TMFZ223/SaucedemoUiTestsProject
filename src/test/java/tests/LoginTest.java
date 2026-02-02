package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(testName = "Позитивный тест на логин")
    public void positiveLoginTest() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.checkDisplayingTitle(), "Заголовок не появился");
        assertEquals(productsPage.checkTitleText(), "Products");
    }

    @DataProvider
    public Object[][] negativeTestLoginData() {
        return new Object[][]{
                {UserFactory.withUnknownUser(), "Epic sadface: Username and password do not match any user in this service"},
                {UserFactory.withLockedOutUser(), "Epic sadface: Sorry, this user has been locked out."},
                {UserFactory.withEmptyUserName(), "Epic sadface: Username is required"},
                {UserFactory.withEmptyPassword(), "Epic sadface: Password is required"}};
    }

    @Test(dataProvider = "negativeTestLoginData", testName = "Негативный тест на логин")
    public void negativeLoginTest(User user, String expectedErrorMessage) {
        loginPage.open();
        loginPage.login(user);
        assertTrue(loginPage.checkDisplayingError(), "Ошибка не появилась");
        assertEquals(loginPage.checkErrorMessage(), expectedErrorMessage);
    }
}