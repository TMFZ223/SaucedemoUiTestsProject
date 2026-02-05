package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.id("login-button");
    private final By error = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу {this.BASE_URL}")
    public void open() {
        driver.get(BASE_URL);
    }

    public void login(User user) {
        enterUsername(user.getUserName());
        enterPassword(user.getPassword());
        clickLoginButton();
    }

    @Step("Ввести в поле username значение {username}")
    public void enterUsername(String username) {
        findElement(usernameInput).sendKeys(username);
    }

    @Step("Ввести в поле password значение {password}")
    public void enterPassword(String password) {
        findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать на кнопку входа")
    public void clickLoginButton() {
        findElement(loginButton).click();
    }

    @Step("Проверить видимость сообщения об ошибке входа")
    public boolean checkDisplayingError() {
        return findElement(error).isDisplayed();
    }

    @Step("Проверить текст сообщения об ошибке входа")
    public String checkErrorMessage() {
        return findElement(error).getText();
    }
}
