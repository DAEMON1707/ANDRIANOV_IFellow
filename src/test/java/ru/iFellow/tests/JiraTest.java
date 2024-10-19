/**
 * Базовый класс для выполнения тестов.
 */

package ru.iFellow.tests;

import ru.iFellow.hooks.WebHooks;
import io.qameta.allure.*;
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
    @Severity(SeverityLevel.BLOCKER)
    @Step("Авторизация")
    public void checkStepAuthorization() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        jiraSteps.stepCheckAuthorization(props.userLogin());
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Открытие проекта")
    @Description("Проверка открытия проекта")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Открытие проекта")
    public void checkStepOpenProject() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        jiraSteps.stepCheckAuthorization(props.userLogin());

        jiraSteps.stepOpenFormProject(props.testProject());
        jiraSteps.stepCheckOpenFormProject(props.testProject());
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Количество задач")
    @Description("Проверка корректности общего числа задач на странице")
    @Severity(SeverityLevel.NORMAL)
    @Step("Количество задач")
    public void checkStepCountingQuantityTasks() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        jiraSteps.stepCheckAuthorization(props.userLogin());

        jiraSteps.stepOpenFormProject(props.testProject());
        jiraSteps.stepCheckOpenFormProject(props.testProject());

        jiraSteps.stepOpenFormTasks();
        jiraSteps.stepCheckOpenFormTasks();
        jiraSteps.stepCheckCountingQuantityTasks(props.testSummary());
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Открытие задачи")
    @Description("Проверка открытия задачи")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Открытие задачи")
    public void checkStepOpenTask() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        jiraSteps.stepCheckAuthorization(props.userLogin());

        jiraSteps.stepOpenFormProject(props.testProject());
        jiraSteps.stepCheckOpenFormProject(props.testProject());

        jiraSteps.stepOpenFormTasks();
        jiraSteps.stepCheckOpenFormTasks();

        jiraSteps.stepOpenTask(props.testTaskSummary());
        jiraSteps.checkStepOpenTask(props.testTaskSummary(), props.testTaskStatus(), props.testTaskFixVersions());
    }

    @Test
    @Owner("Dmitry Andrianov")
    @DisplayName("Создание задачи")
    @Description("Проверка что задача создается с теми параметрами, что были указаны при создании")
    @Severity(SeverityLevel.NORMAL)
    @Step("Создание задачи")
    public void checkStepCreateFullTask() {
        jiraSteps.stepAuthorization(props.url(), props.userLogin(), props.userPassword());
        jiraSteps.stepCheckAuthorization(props.userLogin());

        jiraSteps.stepCreateFullTask(props.testSummary(), props.testDescription(), props.testFixVersions(), props.testEnvironment(), props.testLabel(), props.testVersions());
        jiraSteps.stepOpenTask(props.testSummary());
        jiraSteps.checkStepOpenTask(props.testSummary(), "СДЕЛАТЬ", props.testFixVersions());

        jiraSteps.stepChangeStatus("В РАБОТЕ");
        jiraSteps.checkStepChangeStatus("В РАБОТЕ");

        jiraSteps.stepChangeStatus("РЕШЕННЫЕ");
        jiraSteps.checkStepChangeStatus("РЕШЕННЫЕ");

        jiraSteps.stepChangeStatus("ПЕРЕОТКРЫТ");
        jiraSteps.checkStepChangeStatus("ПЕРЕОТКРЫТ");

        jiraSteps.stepChangeStatus("ГОТОВО");
        jiraSteps.checkStepChangeStatus("ГОТОВО");
    }
}