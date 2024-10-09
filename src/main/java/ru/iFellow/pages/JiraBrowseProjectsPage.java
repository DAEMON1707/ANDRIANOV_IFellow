/**
 * Форма "Просмотр проектов".
 */

package ru.iFellow.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraBrowseProjectsPage {
    private final SelenideElement inputProjectFilterText = $x("//input[@id='project-filter-text']").as("Поиск");
    private final SelenideElement tbodyProjectsList = $x("//tbody").as("Таблица");

    /**
     * Выбор проекта в таблице.
     * @param project проект.
     * @return таблица с выбранным проектом.
     */
    private SelenideElement selectProject(String project) {
        return tbodyProjectsList.$x(".//a[contains(@title,'" + project + "')]").as("Таблица с выбранным проектом \"" + project + "\"");
    }

    /**
     * Ввод значения в фильтр по проектам.
     * @param text text
     */
    public void inputFieldProjectFilterText(String text) {
        inputProjectFilterText.setValue(text);
    }

    /**
     * Нажатие на имя проекта в таблице проектов.
     * @param project имя проекта.
     */
    public void clickSearchProject(String project) {
        selectProject(project).click();
    }
}
