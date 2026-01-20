import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FirstTest {
    private WebDriver driver;

    @Test
    public void exampleTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Открываю страницу https://saucedemo.com/
        driver.get("https://saucedemo.com/");
        // Открываю окно на весь экран
        driver.manage().window().maximize();
        // Проверяю заголовок открывшейся страницы
        assertEquals(driver.getTitle(), "Swag Labs");
        // Закрываю браузер
        driver.quit();
    }
}
