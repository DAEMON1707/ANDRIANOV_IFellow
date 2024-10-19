/**
 * Шаги тестирования.
 */

package ru.iFellow.steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.iFellow.pages.*;

public class JiraSteps {

    private final JiraDashboardPage jiraDashboardPage = new JiraDashboardPage();
    private final JiraMenu jiraMenu = new JiraMenu();
    private final JiraProjectPage jiraProjectPage = new JiraProjectPage();
    private final JiraSearchPage jiraSearchPage = new JiraSearchPage();

    /**
     * Авторизация.
     * @param login логин
     * @param password пароль
     */
    @Step("Авторизация под логином [{login}]")
    public void stepAuthorization(String url, String login, String password) {
        jiraDashboardPage.openWebSite(url);
        jiraDashboardPage.setLogin(login);
        jiraDashboardPage.setPassword(password);
        jiraDashboardPage.clickButtonLogin();
    }

    /**
     * Открытие проекта.
     * @param project имя проекта
     */
    @Step("Открытие проекта [{project}]")
    public void stepOpenFormProject(String project) {
        JiraBrowseProjectsPage jiraBrowseProjectsPage = jiraDashboardPage.clickButtonMenuProjectsViewAllProjects();
        jiraBrowseProjectsPage.inputFieldProjectFilterText(project);
        jiraBrowseProjectsPage.clickSearchProject(project);
    }

    /**
     * Открывает форму "Задачи" на боковой панели.
     */
    @Step("Открытие формы \"Задачи\"")
    public void stepOpenFormTasks() {
        JiraProjectPage jiraProjectPage = new JiraProjectPage();
        jiraProjectPage.clickButtonSidebarTasks();
    }

    /**
     * Создает задачу и возвращает количество задач с учетом созданной задачи.
     * @param summary тема задачи
     */
    @Step("Быстрое создание задачи с темой [{summary}]")
    public void stepCreateQuickTask(String summary) {
        JiraCreateTaskPage jiraCreateTaskPage = jiraMenu.clickButtonCreateTask();
        jiraCreateTaskPage.setFieldSummary(summary);
        jiraCreateTaskPage.clickButtonCreate();
        Selenide.refresh();
    }

    /**
     * Создает задачу с заполнением полей.
     * @param summary тема
     * @param description описание
     * @param fixVersions исправить в версиях
     * @param environment окружение
     * @param label метка
     * @param versions затронуты версии
     */
    @Step("Создание задачи с темой [{summary}]")
    public void stepCreateFullTask(String summary, String description, String fixVersions, String environment, String label, String versions) {
        JiraCreateTaskPage jiraCreateTaskPage = jiraMenu.clickButtonCreateTask();
        jiraCreateTaskPage.setFieldSummary(summary);
        jiraCreateTaskPage.setDescription(description);
        jiraCreateTaskPage.selectFixVersions(fixVersions);
        jiraCreateTaskPage.setLabels(label);
        jiraCreateTaskPage.setEnvironment(environment);
        jiraCreateTaskPage.selectVersions(versions);
        jiraCreateTaskPage.clickButtonAssignToMeTrigger();
        jiraCreateTaskPage.clickButtonCreate();
        Selenide.refresh();
    }

    /**
     * Открывает задачу.
     * @param summaryTask тема задачи
     */
    @Step("Открытие задачи [{summaryTask}]")
    public void stepOpenTask(String summaryTask) {
        jiraMenu.searchTask(summaryTask);
        jiraSearchPage.sortingTask("Cоздано");
        jiraSearchPage.clickLinkOpenTask();
    }

    /**
     * Смена статуса задачи.
     * @param status статус (В РАБОТЕ / РЕШЕННЫЕ / ПЕРЕОТКРЫТ / ГОТОВО)
     */
    @Step("Смена статуса на [{status}]")
    public void stepChangeStatus(String status) {
        switch(status) {
            case "В РАБОТЕ":
                jiraProjectPage.clickButtonCommandBar("В работе");
                break;
            case "РЕШЕННЫЕ":
                jiraProjectPage.clickButtonCommandBar("Бизнес-процесс");
                jiraProjectPage.clickButtonCommandBar("Исполнено");
                jiraProjectPage.clickButtonSubmit();
                break;
            case "ПЕРЕОТКРЫТ":
                jiraProjectPage.clickButtonCommandBar("Бизнес-процесс");
                jiraProjectPage.clickButtonCommandBar("Переоткрыт");
                jiraProjectPage.clickButtonSubmit();
                break;
            case "ГОТОВО":
                jiraProjectPage.clickButtonCommandBar("Бизнес-процесс");
                jiraProjectPage.clickButtonCommandBar("Выполнено");
                break;
            default:
                return;
        }
    }

    /**
     * Проверка авторизации.
     * @param expectedLogin ожидаемый логин авторизованного агента
     */
    @Step("Проверка авторизации")
    public void stepCheckAuthorization(String expectedLogin) {
        Assertions.assertEquals(expectedLogin, jiraMenu.getUserLogin());
    }

    /**
     * Проверка открытия проекта.
     * @param expectedProject ожидаемый открытый проект
     */
    @Step("Проверка открытия проекта")
    public void stepCheckOpenFormProject(String expectedProject) {
        Assertions.assertEquals(expectedProject, jiraProjectPage.getNameProject());
    }

    /**
     * Проверка открытия задачи
     */
    @Step("Проверка открытия формы \"Задачи\"")
    public void stepCheckOpenFormTasks() {
        Assertions.assertEquals("Открытые задачи", jiraProjectPage.getHeadingPage()); //Проверка заголовка страницы
    }

    /**
     * Проверка количества задач
     * @param summary тема задачи
     */
    @Step("Проверка количества задач")
    public void stepCheckCountingQuantityTasks(String summary) {
        Integer countTasks = jiraProjectPage.getCountTasks();
        stepCreateQuickTask(summary);
        Integer newCountTasks = jiraProjectPage.getCountTasks();
        Assertions.assertEquals( countTasks + 1, newCountTasks);
    }

    /**
     * Проверка открытой задачи
     * @param expectedSummary ожидаемая тема задачи
     * @param expectedStatus ожидаемый статус задачи
     * @param expectedFixVersions ожидаемое значение в поле "Исправить в версиях"
     */
    @Step("Проверка открытой задачи")
    public void checkStepOpenTask(String expectedSummary, String expectedStatus, String expectedFixVersions) {
        Assertions.assertAll("Проверка полей задачи",
                () -> Assertions.assertEquals(expectedSummary, jiraProjectPage.getTaskSummary()),
                () -> Assertions.assertEquals(expectedStatus, jiraProjectPage.getTaskStatus()),
                () -> Assertions.assertEquals(expectedFixVersions, jiraProjectPage.getTaskFixVersions())
        );
    }

    /**
     * Проверка смены статуса
     * @param expectedStatus ожидаемый статус
     */
    @Step("Проверка смены статуса")
    public void checkStepChangeStatus(String expectedStatus) {
        Assertions.assertEquals(expectedStatus, jiraProjectPage.getTaskStatus(expectedStatus));
    }
}
