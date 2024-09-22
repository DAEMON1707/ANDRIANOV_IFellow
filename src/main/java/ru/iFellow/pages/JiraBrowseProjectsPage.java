/**
 * Форма "Просмотр проектов".
 */

package ru.iFellow.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraBrowseProjectsPage {
    private final SelenideElement inputProjectFilterText = $x("//input[@id='project-filter-text']");

    /**
     * Ввод значения в фильт по проектам.
     * @param text text
     */
    public void inputFieldProjectFilterText(String text) {
        inputProjectFilterText.setValue(text);
    }

    /**
     * Нажатие на имя проекта в таблице проектов.
     * @param project имя проекта.
     */
    public static JiraProjectPage clickSearchProject(String project) {
        $x("//tbody//a[contains(@title,'" + project + "')]").click();
        return new JiraProjectPage();
    }
}
