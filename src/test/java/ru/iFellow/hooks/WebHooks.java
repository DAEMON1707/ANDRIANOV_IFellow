package ru.iFellow.hooks;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static ru.iFellow.properties.Props.props;

public class WebHooks {

    private static void setUp() {
        ChromeOptions options = new ChromeOptions();

        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 4000;

        // Разворачиваем окно браузера на весь экран
        options.addArguments("--start-maximized");

        //Проверяем переменную "driversChromeDriverPath", если она есть, то используем ее, иначе используем путь по умолчанию.
        if (props.driversChromeDriverPath() != null) {
            System.setProperty("webdriver.chrome.driver", props.driversChromeDriverPath());
        }

        ChromeDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
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
