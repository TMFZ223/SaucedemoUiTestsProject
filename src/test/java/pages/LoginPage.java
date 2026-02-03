package pages;

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

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(User user) {
        driver.findElement(usernameInput).sendKeys(user.getUserName());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

    public boolean checkDisplayingError() {
        return driver.findElement(error).isDisplayed();
    }

    public String checkErrorMessage() {
        return driver.findElement(error).getText();
    }
}
