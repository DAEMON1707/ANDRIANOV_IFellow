/**
 * Форма "Проект" открытая по определенному проекту.
 */

package ru.iFellow.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class JiraProjectPage {
    private final SelenideElement aNameProject = $x("//div[@class='aui-item project-title']//a").as("Наименование проекта");
    private final SelenideElement aSidebarTasks = $x("//a[@data-link-id='com.atlassian.jira.jira-projects-issue-navigator:sidebar-issue-navigator']").as("Пункт боковой панели \"Задачи\"");
    private final SelenideElement aTaskFixVersions = $x("//span[@id='fixVersions-field']/a").as("Параметр задачи \"Исправить в версиях\"");
    private final SelenideElement divCommandBar = $x("//div[@class='command-bar']").as("Панель команд");
    private final SelenideElement inputButtonSubmit = $x("//input[@id='issue-workflow-transition-submit']").as("Кнопка подтверждения смены статуса");
    private final SelenideElement h1TaskSummary = $x("//h1[@id='summary-val']").as("Тема задачи");
    private final SelenideElement spanCountTasks = $x("//span[contains(text(),' из ')]").as("Порядковый номер задачи из общего числа задач");
    private final SelenideElement spanHeadingPage = $x("//h1//span[@id='issues-subnavigation-title']").as("Заголовок страницы");
    private final SelenideElement spanTaskStatus = $x("//span[@id='status-val']/span").as("Статус задачи");

    /**
     * Получение кнопки из панели команд.
     * @param button кнопка, которую необходимо вернуть.
     * @return кнопка.
     */
    private SelenideElement getButtonCommandBar(String button) {
        return divCommandBar.$x(".//span[contains(text(), '" + button +"')]/..").as("Кнопка \"" + button + "\"");
    }


    /**
     * Получить наименование проекта.
     * @return имя проекта
     */
    public String getNameProject() {
        return aNameProject.getAttribute("title");
    }

    /**
     * Нажатие на пункт "Задачи" на боковой панели.
     */
    public void clickButtonSidebarTasks() {
        aSidebarTasks.click();
    }

    /**
     * Нажатие на кнопку подтверждения смены статуса.
     */
    public void clickButtonSubmit() {
        inputButtonSubmit.click();
    }

    /**
     * Получить общее число задач.
     * @return количество задач.
     */
    public Integer getCountTasks() {
        return Integer.parseInt(spanCountTasks.text().substring(spanCountTasks.text().indexOf("из") + 3));
    }

    /**
     * Получить заголовок страницы.
     * @return заголовок
     */
    public String getHeadingPage() {
        return spanHeadingPage.text();
    }

    /**
     * Получение темы задачи.
     * @return тема
     */
    public String getTaskSummary() {
        return h1TaskSummary.text();
    }

    /**
     * Получение статуса задачи с ожиданием изменения статуса. Костыль на скорую руку.
     * @param externalText ожидаемый статус
     * @return статус
     */
    public String getTaskStatus(String externalText) {
        return spanTaskStatus.shouldHave(text(externalText)).text();
    }
    /**
     * Получение статуса задачи.
     * @return статус
     */
    public String getTaskStatus() {
        return spanTaskStatus.text();
    }

    /**
     * Получение поля "Исправить в версиях" задачи.
     * @return версия
     */
    public String getTaskFixVersions() {
        return aTaskFixVersions.text();
    }

    /**
     * Нажатие на кнопку панели команд.
     * @param button наименование кнопки (В работе / Исполнено / Переоткрыт / Выполнено / Бизнес-процесс)
     */
    public void clickButtonCommandBar(String button) {
        getButtonCommandBar(button).click();
    }
}