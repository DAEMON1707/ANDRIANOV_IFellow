/**
 * Базовый класс для выполнения тестов.
 */

package ru.iFellow.tests;

import ru.iFellow.hooks.WebHooks;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.pages.*;
import ru.iFellow.steps.*;

import static ru.iFellow.properties.Props.props;

public class JiraTest extends WebHooks {

    private final JiraMenu jiraMenu = new JiraMenu();
    private final JiraProjectPage jiraProjectPage = new JiraProjectPage();
    private final JiraSteps jiraSteps = new JiraSteps();

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Авторизация")
    @Description("Проверка авторизации")
    @Step("Проверка авторизации")
    public void checkStepAuthorization() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        Assertions.assertEquals(props.userLogin(), jiraMenu.getUserLogin());
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Открытие проекта")
    @Description("Проверка открытия проекта")
    @Step("Проверка открытия проекта")
    public void checkStepOpenProject() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        Assertions.assertEquals(props.userLogin(), jiraMenu.getUserLogin());

        jiraSteps.stepOpenFormProject(props.testProject());
        Assertions.assertEquals(props.testProject(), jiraProjectPage.getNameProject());
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Количество задач")
    @Description("Проверка корректности общего числа задач на странице")
    @Step("Проверка корректности общего числа задач на странице")
    public void checkStepCountingQuantityTasks() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        Assertions.assertEquals(props.userLogin(), jiraMenu.getUserLogin());

        jiraSteps.stepOpenFormProject(props.testProject());
        Assertions.assertEquals(props.testProject(), jiraProjectPage.getNameProject());

        jiraSteps.stepOpenFormTasks();
        Assertions.assertEquals("Открытые задачи", jiraProjectPage.getHeadingPage());
        Assertions.assertEquals(jiraProjectPage.getCountTasks() + 1,jiraSteps.stepCreateQuickTask(props.testSummary()));
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Открытие задачи")
    @Description("Проверка открытия задачи")
    @Step("Проверка открытия задачи")
    public void checkStepOpenTask() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        Assertions.assertEquals(props.userLogin(), jiraMenu.getUserLogin());

        jiraSteps.stepOpenFormProject(props.testProject());
        Assertions.assertEquals(props.testProject(), jiraProjectPage.getNameProject());

        jiraSteps.stepOpenFormTasks();
        Assertions.assertAll("stepOpenFromTasks",
                () -> Assertions.assertEquals("Открытые задачи", jiraProjectPage.getHeadingPage()),
                () -> Assertions.assertEquals(jiraProjectPage.getCountTasks() + 1,jiraSteps.stepCreateQuickTask(props.testSummary()))
        );

        jiraSteps.stepOpenTask(props.testTaskSummary());
        System.out.println(props.testTaskStatus());
        Assertions.assertAll("stepOpenTask",
                () -> Assertions.assertEquals(props.testTaskSummary(), jiraProjectPage.getTaskSummary()),
                () -> Assertions.assertEquals(props.testTaskStatus(), jiraProjectPage.getTaskStatus()),
                () -> Assertions.assertEquals(props.testTaskFixVersions(), jiraProjectPage.getTaskFixVersions())
        );
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Создание задачи")
    @Description("Проверка что задача создается с теми параметрами, что были указаны при создании")
    @Step("Проверка что задача создается с теми параметрами, что были указаны при создании")
    public void checkStepCreateFullTask() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        Assertions.assertEquals(props.userLogin(), jiraMenu.getUserLogin());

        jiraSteps.stepCreateFullTask(props.testProject(), "Описание", "Version 2.0", "Окружение","Метка", "Version 1.0");
        jiraSteps.stepOpenTask(props.testProject());
        Assertions.assertAll("checkCreateTask",
                () -> Assertions.assertEquals(props.testProject(), jiraProjectPage.getTaskSummary()),
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