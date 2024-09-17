/**
 * Панель "Меню". Общая для всех страниц.
 */
package ru.iFellow.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraMenu {
    private static final SelenideElement aButtonCreateTask = $x("//a[@id='create_link']");
    private static final SelenideElement inputSearch = $x("//input[@id='quickSearchInput']");
    private static final SelenideElement imgProfile = $x("//img[starts-with(@alt,'Пользовательский профиль для')]");

    /**
     * Нажатие на кнопку "Создать".
     * @return экземпляр класса JiraCreateTaskPage
     */
    public static JiraCreateTaskPage clickButtonCreateTask() {
        aButtonCreateTask.click();
        return new JiraCreateTaskPage();
    }

    /**
     * Получение логина пользователя из атрибута "Alt" элемента "ImgProfile".
     * @return login
     */
    public static String getUserLogin() {
        String alt = imgProfile.getAttribute("alt");
        assert alt != null;
        return alt.substring(alt.indexOf("для") + 4);
    }

    public static void openSearchTask(String search) {
        inputSearch.setValue(search);
        $x("//li[contains(@original-title, '" + search + "')]").click();
    }
}
