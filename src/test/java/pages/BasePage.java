package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class BasePage {
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    protected final By title = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean checkDisplayingTitle() {
        return driver.findElement(title).isDisplayed();
    }

    public String checkTitleText() {
        return driver.findElement(title).getText();
    }
}
