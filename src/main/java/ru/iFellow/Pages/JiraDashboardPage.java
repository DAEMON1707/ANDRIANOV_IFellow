/**
 * Форма "System Dashboard".
 */

package ru.iFellow.Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class JiraDashboardPage {
    private final SelenideElement aProfile = $x("//a[@id='browse_link']");
    private final SelenideElement aViewAllProjects = $x("//a[@id='project_view_all_link_lnk']");
    private final SelenideElement inputLogin = $x("//input[@id='login-form-username']");
    private final SelenideElement inputPassword = $x("//input[@id='login-form-password']");
    private final SelenideElement buttonLogin = $x("//input[@id='login']");

    public void openWebSite (String url) {
        Selenide.open(url);
    }

    /**
     * Заполнение поля "Имя пользователя".
     * @param login имя пользователя.
     */
    public void setLogin(String login) {
        inputLogin.setValue(login);
    }

    /**
     * Заполнение поля "Имя пользователя".
     * @param password пароль.
     */
    public void setPassword(String password) {
        inputPassword.setValue(password);
    }

    /**
     * Нажатие на кнопку "Войти".
     */
    public void clickButtonLogin() {
        buttonLogin.click();
    }

    /**
     * Нажатие на кнопку "Просмотр всех проектов" в разделе меню "Проекты".
     * @return экземпляр класса jiraBrowseProjectsPage
     */
    public JiraBrowseProjectsPage clickButtonMenuProjectsViewAllProjects() {
        aProfile.click();
        aViewAllProjects.click();
        return new JiraBrowseProjectsPage();
    }
}
