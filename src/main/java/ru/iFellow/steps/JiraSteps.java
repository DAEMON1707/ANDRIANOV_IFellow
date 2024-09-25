/**
 * Шаги тестирования.
 */

package ru.iFellow.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import org.junit.jupiter.api.Assertions;
import ru.iFellow.config.ConfigProvider;
import ru.iFellow.pages.*;

public class JiraSteps {

    private final JiraCreateTaskPage jiraCreateTaskPage = new JiraCreateTaskPage();
    private final JiraDashboardPage jiraDashboardPage = new JiraDashboardPage();
    private final JiraMenu jiraMenu = new JiraMenu();
    private final JiraProjectPage jiraProjectPage = new JiraProjectPage();

    /**
     * Авторизация.
     * @param login логин
     * @param password пароль
     */
    @Когда("Пользователь авторизуется в Jira по адресу {string} под логином {string} и паролем {string}")
    public void stepAuthorization(String url, String login, String password) {
        jiraDashboardPage.openWebSite(url);
        jiraDashboardPage.setLogin(login);
        jiraDashboardPage.setPassword(password);
        jiraDashboardPage.clickButtonLogin();
        Assertions.assertEquals(login, jiraMenu.getUserLogin());
    }

    /**
     * Открытие проекта.
     * @param project имя проекта
     */
    @Когда("Пользователь открывает проект с именем {string}")
    public void stepOpenFormProject(String project) {
        JiraBrowseProjectsPage jiraBrowseProjectsPage = jiraDashboardPage.clickButtonMenuProjectsViewAllProjects();
        jiraBrowseProjectsPage.inputFieldProjectFilterText(project);
        JiraBrowseProjectsPage.clickSearchProject(project);
        Assertions.assertEquals(project, jiraProjectPage.getNameProject());
    }

    /**
     * Открывает форму "Задачи" на боковой панели.
     */
    @Когда("Пользователь открывает форму \"Задачи\" на боковой панели")
    public void stepOpenFormTasks() {
        JiraProjectPage jiraProjectPage = new JiraProjectPage();
        jiraProjectPage.clickButtonSidebarTasks();
        Assertions.assertEquals("Открытые задачи", jiraProjectPage.getHeadingPage());
    }

    /**
     * Создает задачу и возвращает количество задач с учетом созданной задачи.
     * @param summary тема задачи
     * @return количество задач
     */
    @Когда("Пользователь создает задачу {string}")
    public void stepCreateQuickTask(String summary) {
        Integer countTasks = jiraProjectPage.getCountTasks();
        jiraMenu.clickButtonCreateTask();
        jiraCreateTaskPage.setFieldSummary(summary);
        jiraCreateTaskPage.clickButtonCreate();
        Selenide.refresh();
        Assertions.assertEquals(countTasks + 1, jiraProjectPage.getCountTasks());
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
    @Когда("Пользователь создает задачу с параметрами: Тема {string}, Описание {string}, Исправить в версиях {string}, Окружение {string}, Метка {string}, Затронуты версии {string}")
    public void stepCreateFullTask(String summary, String description, String fixVersions, String environment, String label, String versions) {
        jiraMenu.clickButtonCreateTask();
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
     * @param taskSummary тема задачи
     */
    @Когда("Пользователь открывает задачу с темой {string}, статусом {string} и Исправить в версиях {string}")
    public void stepOpenTask(String taskSummary, String taskStatus, String taskFixVersion) {
        jiraMenu.openSearchTask(taskSummary);
        Assertions.assertAll("stepOpenTask",
                () -> Assertions.assertEquals(taskSummary, jiraProjectPage.getTaskSummary()),
                () -> Assertions.assertEquals(taskStatus, jiraProjectPage.getTaskStatus()),
                () -> Assertions.assertEquals(taskFixVersion, jiraProjectPage.getTaskFixVersions())
        );
    }

    /**
     * Смена статуса задачи.
     * @param status статус (В РАБОТЕ / РЕШЕННЫЕ / ПЕРЕОТКРЫТ / ГОТОВО)
     */
    @Когда("Пользователь меняет статус задачи на {string}")
    public void stepChangeStatus(String status) {
        switch(status) {
            case "В РАБОТЕ":
                jiraProjectPage.clickButtonCommandBar("В работе");
                Assertions.assertEquals("В РАБОТЕ", jiraProjectPage.getTaskStatus("В РАБОТЕ"));
                break;
            case "РЕШЕННЫЕ":
                jiraProjectPage.clickButtonCommandBar("Бизнес-процесс");
                jiraProjectPage.clickButtonCommandBar("Исполнено");
                jiraProjectPage.clickButtonSubmit();
                Assertions.assertEquals("РЕШЕННЫЕ", jiraProjectPage.getTaskStatus("РЕШЕННЫЕ"));
                break;
            case "ПЕРЕОТКРЫТ":
                jiraProjectPage.clickButtonCommandBar("Бизнес-процесс");
                jiraProjectPage.clickButtonCommandBar("Переоткрыт");
                jiraProjectPage.clickButtonSubmit();
                Assertions.assertEquals("ПЕРЕОТКРЫТ", jiraProjectPage.getTaskStatus("ПЕРЕОТКРЫТ"));
                break;
            case "ГОТОВО":
                jiraProjectPage.clickButtonCommandBar("Бизнес-процесс");
                jiraProjectPage.clickButtonCommandBar("Выполнено");
                Assertions.assertEquals("ГОТОВО", jiraProjectPage.getTaskStatus("ГОТОВО"));
                break;
            default:
                return;
        }
    }
}
