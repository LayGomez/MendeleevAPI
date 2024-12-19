package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class EndpointTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void addElementGroup() {
        String newGroupJson = "{"
                + "\"groupNumber\" : 1,"
                + "\"name\" : \"reactive Nonmetals\","
                + "\"description\" : \"Reactive nonmetals tend to gain electrons.\""
                + "}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(newGroupJson)
                .when()
                .post("/elementGroups")
                .then()
                .statusCode(201)
                .body("groupNumber", equalTo(1))
                .body("name", equalTo("reactive Nonmetals"))
                .body("description", equalTo("Reactive nonmetals tend to gain electrons."));
    }
}
