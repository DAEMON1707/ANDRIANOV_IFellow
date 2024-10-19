package iFellow.hooks;

import iFellow.api.specifications.Specifications;
import iFellow.properties.Props;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    Specifications specifications = new Specifications();

    @Before
    public void initApiSpec() {
        RestAssured.requestSpecification = specifications.baseRequestSpec(Props.props.url());
    }
}