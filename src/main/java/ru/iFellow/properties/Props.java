package ru.iFellow.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/test.properties"})


public interface Props extends Config{
    Props props = ConfigFactory.create(Props.class);

    @Config.Key("url")
    String url();

    @Config.Key("user.login")
    String userLogin();

    @Config.Key("user.password")
    String userPassword();

    @Config.Key("test.summary")
    String testSummary();

    @Config.Key("test.project")
    String testProject();

    @Config.Key("test.task.summary")
    String testTaskSummary();

    @Config.Key("test.task.status")
    String testTaskStatus();

    @Config.Key("test.task.fixVersions")
    String testTaskFixVersions();
}