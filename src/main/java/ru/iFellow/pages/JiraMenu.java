/**
 * Панель "Меню". Общая для всех страниц.
 */
package ru.iFellow.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraMenu {
    private final SelenideElement aButtonCreateTask = $x("//a[@id='create_link']");
    private final SelenideElement inputSearch = $x("//input[@id='quickSearchInput']");
    private final SelenideElement imgProfile = $x("//img[starts-with(@alt,'Пользовательский профиль для')]");

    /**
     * Определение динамического элемента отвечающего за определенную задачу в выпадающем списке поиска.
     * @param originalTitle наименование задачи
     * @return элемент интерфейса
     */
    private SelenideElement liOriginalTitle(String originalTitle){
        return $x("//li[contains(@original-title, '" + originalTitle + "')]").as("Задача \"" + originalTitle + "\" в выпадающем списке поиска");
    }

    /**
     * Нажатие на кнопку "Создать".
     * @return экземпляр класса JiraCreateTaskPage
     */
    public JiraCreateTaskPage clickButtonCreateTask() {
        aButtonCreateTask.click();
        return new JiraCreateTaskPage();
    }

    /**
     * Получение логина пользователя из атрибута "Alt" элемента "ImgProfile".
     * @return login
     */
    public String getUserLogin() {
        String alt = imgProfile.getAttribute("alt");
        assert alt != null;
        return alt.substring(alt.indexOf("для") + 4);
    }

    /**
     * Открывает страницу задачи через строку поиска.
     * @param search поисковой запрос
     */
    public void openSearchTask(String search) {
        inputSearch.setValue(search);
        liOriginalTitle(search).click();
    }
}
