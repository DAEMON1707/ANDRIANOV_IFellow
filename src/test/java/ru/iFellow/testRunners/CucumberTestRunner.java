package ru.iFellow.testRunners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "ru/iFellow")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/ru/iFellow/feature")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@Test")
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")

public class CucumberTestRunner {

}

