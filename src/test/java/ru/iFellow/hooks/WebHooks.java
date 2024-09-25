package ru.iFellow.hooks;

import com.codeborne.selenide.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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

    @Before
    public void init() {
        setUp();
    }

    @After
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}
