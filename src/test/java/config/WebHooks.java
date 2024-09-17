package config;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.awt.*;

public class WebHooks {

    private void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 4000;
        Configuration.browserPosition = "0x0";

        // Устанавливаем максимальное разрешение экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Configuration.browserSize = screenSize.width + "x" + screenSize.height;
    }

    public static void refresh() {
        Selenide.refresh();
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
