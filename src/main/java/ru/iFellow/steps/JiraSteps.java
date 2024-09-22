/**
 * Шаги тестирования.
 */

package ru.iFellow.steps;

import com.codeborne.selenide.Selenide;
import ru.iFellow.pages.*;

public class JiraSteps {

    private final JiraDashboardPage jiraDashboardPage = new JiraDashboardPage();
    private final JiraMenu jiraMenu = new JiraMenu();
    private final JiraProjectPage jiraProjectPage = new JiraProjectPage();

    /**
     * Авторизация.
     * @param login логин
     * @param password пароль
     */
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
    public void stepOpenFormProject(String project) {
        JiraBrowseProjectsPage jiraBrowseProjectsPage = jiraDashboardPage.clickButtonMenuProjectsViewAllProjects();
        jiraBrowseProjectsPage.inputFieldProjectFilterText(project);
        JiraBrowseProjectsPage.clickSearchProject(project);
    }

    /**
     * Открывает форму "Задачи" на боковой панели.
     */
    public void stepOpenFormTasks() {
        JiraProjectPage jiraProjectPage = new JiraProjectPage();
        jiraProjectPage.clickButtonSidebarTasks();
    }

    /**
     * Создает задачу и возвращает количество задач с учетом созданной задачи.
     * @param summary тема задачи
     * @return количество задач
     */
    public Integer stepCreateQuickTask(String summary) {
        JiraCreateTaskPage jiraCreateTaskPage = jiraMenu.clickButtonCreateTask();
        jiraCreateTaskPage.setFieldSummary(summary);
        jiraCreateTaskPage.clickButtonCreate();
        Selenide.refresh();
        return jiraProjectPage.getCountTasks();
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
    public void stepOpenTask(String summaryTask) {
        jiraMenu.openSearchTask(summaryTask);
    }

    /**
     * Смена статуса задачи.
     * @param status статус (В РАБОТЕ / РЕШЕННЫЕ / ПЕРЕОТКРЫТ / ГОТОВО)
     */
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
}
