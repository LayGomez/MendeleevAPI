package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

public class EndpointTest {

    private static final String BASE_URL = "http://localhost:8080";  // Verifica que este sea el puerto correcto

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
                .contentType(ContentType.JSON)  // Asegúrate de enviar el tipo de contenido correcto
                .accept(ContentType.JSON)       // Aceptar JSON como respuesta
                .body(newGroupJson)             // El cuerpo de la solicitud
                .when()
                .post("/elementGroups")
                .then()
                .statusCode(201)                // Verifica que el código de estado sea 201 (Creado)
                .body("groupNumber", equalTo(1)) // Verifica el valor de groupNumber
                .body("name", equalTo("reactive Nonmetals")) // Verifica el nombre
                .body("description", equalTo("Reactive nonmetals tend to gain electrons.")); // Verifica la descripción
    }
}
