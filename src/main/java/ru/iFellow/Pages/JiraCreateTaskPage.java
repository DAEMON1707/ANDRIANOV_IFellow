/**
 * Форма "Создание задачи".
 */

package ru.iFellow.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraCreateTaskPage {
    private final SelenideElement buttonAssignToMeTrigger = $x("//button[@id='assign-to-me-trigger']");
    private final SelenideElement inputButtonCreate = $x("//input[@id='create-issue-submit']");
    private final SelenideElement inputFieldSummary = $x("//input[@id='summary']");
    private final SelenideElement selectFixVersions = $x("//select[@id='fixVersions']");
    private final SelenideElement selectVersions = $x("//select[@id='versions']");
    private final SelenideElement textareaDescription = $x("//textarea[@id='description']");
    private final SelenideElement textareaEnvironment = $x("//textarea[@id='environment']");
    private final SelenideElement textareaLabels = $x("//div[@id='labels-multi-select']/textarea");

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
        selectFixVersions.$x(".//option[contains(text(), '" + fixVersions + "')]").click();
    }

    /**
     * Выбор значения из списка "Затронутые версии".
     * @param versions версия
     */
    public void selectVersions(String versions) {
        selectVersions.$x(".//option[contains(text(), '" + versions + "')]").click();
    }

    /**
     * Выбор метки.
     * @param label наименование метки
     */
    public void setLabels(String label) {
        textareaLabels.$x("./textarea").setValue(label).pressEnter();
    }
}
