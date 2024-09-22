package tests; /**
 * Базовый класс для выполнения тестов.
 */

import hooks.WebHooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.iFellow.pages.*;
import ru.iFellow.steps.JiraSteps;
import ru.iFellow.config.ConfigProvider;

public class JiraTest extends WebHooks implements ConfigProvider {

    private final JiraMenu jiraMenu = new JiraMenu();
    private final JiraProjectPage jiraProjectPage = new JiraProjectPage();
    private final JiraSteps jiraSteps = new JiraSteps();

    @Test
    public void checkStepAuthorization() {
        jiraSteps.stepAuthorization(URL, USER_LOGIN, USER_PASSWORD);
        Assertions.assertEquals(USER_LOGIN, jiraMenu.getUserLogin());
    }

    @Test
    public void checkStepOpenProject() {
        jiraSteps.stepAuthorization(URL, USER_LOGIN, USER_PASSWORD);
        Assertions.assertEquals(USER_LOGIN, jiraMenu.getUserLogin());

        jiraSteps.stepOpenFormProject(TEST_PROJECT);
        Assertions.assertEquals(TEST_PROJECT, jiraProjectPage.getNameProject());
    }

    @Test
    public void checkStepCountingQuantityTasks() {
        jiraSteps.stepAuthorization(URL, USER_LOGIN, USER_PASSWORD);
        Assertions.assertEquals(USER_LOGIN, jiraMenu.getUserLogin());

        jiraSteps.stepOpenFormProject(TEST_PROJECT);
        Assertions.assertEquals(TEST_PROJECT, jiraProjectPage.getNameProject());

        jiraSteps.stepOpenFormTasks();
        Assertions.assertEquals("Открытые задачи", jiraProjectPage.getHeadingPage());
        Assertions.assertEquals(jiraProjectPage.getCountTasks() + 1,jiraSteps.stepCreateQuickTask(TEST_SUMMARY));
    }

    @Test
    public void checkStepOpenTask() {
        jiraSteps.stepAuthorization(URL, USER_LOGIN, USER_PASSWORD);
        Assertions.assertEquals(USER_LOGIN, jiraMenu.getUserLogin());

        jiraSteps.stepOpenFormProject(TEST_PROJECT);
        Assertions.assertEquals(TEST_PROJECT, jiraProjectPage.getNameProject());

        jiraSteps.stepOpenFormTasks();
        Assertions.assertAll("stepOpenFromTasks",
                () -> Assertions.assertEquals("Открытые задачи", jiraProjectPage.getHeadingPage()),
                () -> Assertions.assertEquals(jiraProjectPage.getCountTasks() + 1,jiraSteps.stepCreateQuickTask(TEST_SUMMARY))
        );

        jiraSteps.stepOpenTask(TEST_TASK_SUMMARY);
        Assertions.assertAll("stepOpenTask",
                () -> Assertions.assertEquals(TEST_TASK_SUMMARY, jiraProjectPage.getTaskSummary()),
                () -> Assertions.assertEquals(TEST_TASK_STATUS, jiraProjectPage.getTaskStatus()),
                () -> Assertions.assertEquals(TEST_TASK_FIXVERSIONS, jiraProjectPage.getTaskFixVersions())
        );
    }

    @Test
    public void checkStepCreateFullTask() {
        jiraSteps.stepAuthorization(URL, USER_LOGIN, USER_PASSWORD);
        Assertions.assertEquals(USER_LOGIN, jiraMenu.getUserLogin());

        jiraSteps.stepCreateFullTask(TEST_SUMMARY, "Описание", "Version 2.0", "Окружение","Метка", "Version 1.0");
        jiraSteps.stepOpenTask(TEST_SUMMARY);
        Assertions.assertAll("checkCreateTask",
                () -> Assertions.assertEquals(TEST_SUMMARY, jiraProjectPage.getTaskSummary()),
                () -> Assertions.assertEquals("СДЕЛАТЬ", jiraProjectPage.getTaskStatus()),
                () -> Assertions.assertEquals("Version 2.0", jiraProjectPage.getTaskFixVersions())
        );

        jiraSteps.stepChangeStatus("В РАБОТЕ");
        Assertions.assertEquals("В РАБОТЕ", jiraProjectPage.getTaskStatus("В РАБОТЕ"));

        jiraSteps.stepChangeStatus("РЕШЕННЫЕ");
        Assertions.assertEquals("РЕШЕННЫЕ", jiraProjectPage.getTaskStatus("РЕШЕННЫЕ"));

        jiraSteps.stepChangeStatus("ПЕРЕОТКРЫТ");
        Assertions.assertEquals("ПЕРЕОТКРЫТ", jiraProjectPage.getTaskStatus("ПЕРЕОТКРЫТ"));

        jiraSteps.stepChangeStatus("ГОТОВО");
        Assertions.assertEquals("ГОТОВО", jiraProjectPage.getTaskStatus("ГОТОВО"));
    }
}