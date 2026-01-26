package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameInput = By.xpath("//input[@data-test='username']");
    private final By passwordInput = By.xpath("//input[@data-test='password']");
    private final By loginButton = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean checkDisplayingError() {
        return driver.findElement(error).isDisplayed();
    }

    public String checkErrorMessage() {
        return driver.findElement(error).getText();
    }
}
