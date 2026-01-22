import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void positiveLoginTest() {
        WebElement loginInput = driver.findElement(By.xpath("//input[@data-test='username']"));
        loginInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@data-test='password']"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.cssSelector("input[id='login-button']"));
        loginButton.click();
        WebElement title = driver.findElement(By.cssSelector("[data-test='title']"));
        assertTrue(title.isDisplayed(), "Заголовок не появился");
        String expectedTitleText = "Productsino";
        assertEquals(title.getText(), expectedTitleText);
    }

    @Test
    public void negativeLoginTest() {
        WebElement loginInput = driver.findElement(By.xpath("//input[@data-test='username']"));
        loginInput.sendKeys("standarduser");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@data-test='password']"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.cssSelector("input[id='login-button']"));
        loginButton.click();
        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(error.isDisplayed(), "Ошибка не появилась");
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(error.getText(), expectedErrorMessage);
    }
}
