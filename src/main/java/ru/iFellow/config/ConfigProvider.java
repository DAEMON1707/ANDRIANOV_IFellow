package ru.iFellow.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.load("test.conf");
    }

    String URL = readConfig().getString( "url");
    String USER_LOGIN = readConfig().getString("user.login");
    String USER_PASSWORD = readConfig().getString("user.password");
    String TEST_PROJECT = readConfig().getString("test.project");
    String TEST_SUMMARY = readConfig().getString("test.summary");
    String TEST_TASK_SUMMARY = readConfig().getString("test.task.summary");
    String TEST_TASK_STATUS = readConfig().getString("test.task.status");
    String TEST_TASK_FIXVERSIONS = readConfig().getString("test.task.fixVersions");
}

