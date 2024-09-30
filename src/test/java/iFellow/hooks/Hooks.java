package iFellow.hooks;

import iFellow.api.specifications.Specifications;
import iFellow.properties.Props;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class Hooks {

    Specifications specifications = new Specifications();

    @BeforeEach
    public void initApiSpec() {
        RestAssured.requestSpecification = specifications.baseRequestSpec(Props.props.url());
    }
}
