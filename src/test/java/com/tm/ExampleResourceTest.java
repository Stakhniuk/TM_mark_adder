package com.tm;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ExampleResourceTest {

    private static String BASE_URL = "https://quarkus.io/";

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/")
                .then()
                .statusCode(200);
//                .body(is(""));
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        Resource proxy = client.target(BASE_URL).proxy(Resource.class);
        ResteasyWebTarget target = client.target("https://quarkus.io/");
    }
}