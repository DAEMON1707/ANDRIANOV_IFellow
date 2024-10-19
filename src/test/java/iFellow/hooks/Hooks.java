package iFellow.hooks;

import iFellow.api.specifications.Specifications;
import io.restassured.RestAssured;

import static iFellow.properties.Props.props;

public class Hooks {

    Specifications specifications = new Specifications();

    public void initApiRickAndMorty() {
        RestAssured.requestSpecification = specifications.baseRequestSpec(props.urlRickAndMorty());
    }

    public void initApiReqres() {
        RestAssured.requestSpecification = specifications.baseRequestSpec(props.urlReqres());
    }
}