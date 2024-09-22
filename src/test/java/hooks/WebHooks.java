package hooks;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebHooks {

    private static void setUp() {
        ChromeOptions options = new ChromeOptions();

        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 4000;

        // Разворачиваем окно браузера на весь экран
        options.addArguments("--start-maximized");

        ChromeDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}
