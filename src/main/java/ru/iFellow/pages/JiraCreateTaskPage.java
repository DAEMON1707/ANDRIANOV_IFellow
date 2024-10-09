/**
 * Форма "Создание задачи".
 */

package ru.iFellow.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraCreateTaskPage {
    private final SelenideElement buttonAssignToMeTrigger = $x("//button[@id='assign-to-me-trigger']").as("Кнопка \"Назначить меня\"");
    private final SelenideElement inputButtonCreate = $x("//input[@id='create-issue-submit']").as("Кнопка \"Создать\"");
    private final SelenideElement inputFieldSummary = $x("//input[@id='summary']").as("Поле \"Тема\"");
    private final SelenideElement selectFixVersions = $x("//select[@id='fixVersions']").as("Поле \"Исправить в версиях\"");
    private final SelenideElement selectVersions = $x("//select[@id='versions']").as("Поле \"Затронутые версии\"");
    private final SelenideElement textareaDescription = $x("//textarea[@id='description']").as("Поле \"Описание\"");
    private final SelenideElement textareaEnvironment = $x("//textarea[@id='environment']").as("Поле \"Окружение\"");
    private final SelenideElement textareaLabels = $x("//div[@id='labels-multi-select']/textarea").as("Поле \"Метки\"");

    /**
     * Выбор элемента списка.
     * @param select список.
     * @param value значение.
     * @return список с выбранным значением.
     */
    private SelenideElement selectVersion (SelenideElement select, String value) {
        return select.$x(".//option[contains(text(), '" + value + "')]").as(select + " с выбранным значением \"" + value + "\"");
    }

    /**
     * Нажатие на кнопку "Назначить меня".
     */
    public void clickButtonAssignToMeTrigger() {
        buttonAssignToMeTrigger.click();
    }

    /**
     * Нажатие на кнопку "Создать".
     */
    public void clickButtonCreate() {
        inputButtonCreate.click();
    }

    /**
     * Заполнение поля "Тема".
     * @param summary тема.
     */
    public void setFieldSummary(String summary) {
        inputFieldSummary.setValue(summary);
    }

    /**
     * Заполнение поля "Описание".
     * @param description описание.
     */
    public void setDescription(String description) {
        textareaDescription.setValue(description);
    }

    /**
     * Заполнение поля "Окружение".
     * @param environment описание.
     */
    public void setEnvironment(String environment) {
        textareaEnvironment.setValue(environment);
    }

    /**
     * Выбор значения из списка "Исправить в версиях".
     * @param fixVersions версия
     */
    public void selectFixVersions(String fixVersions) {
        selectVersion(selectFixVersions, fixVersions).click();
    }

    /**
     * Выбор значения из списка "Затронутые версии".
     * @param versions версия
     */
    public void selectVersions(String versions) {
        selectVersion(selectVersions, versions).click();
    }

    /**
     * Выбор метки.
     * @param label наименование метки
     */
    public void setLabels(String label) {
        textareaLabels.setValue(label).pressEnter();
    }
}
