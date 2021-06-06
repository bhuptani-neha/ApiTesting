package org.example;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class Module3HomeTask1 {

    @Test
    public void verifyPetStoreAPIGetCall() {
            Response response =
                    given().
                            baseUri("https://petstore.swagger.io/v2/").
                            when().
                            get("pet/12345").
                            then().
                            assertThat().statusCode(200).
                            assertThat().contentType("application/json").
                            body("category.name",equalTo("dog")).
                            body("name",equalTo("snoopieTestAPI")).
                            body("status",equalTo("pending")).
                            extract().response();
            //System.out.println("Response: "+response.asString());

    }

    @Test
    public void verifyJsonPlaceHolderAPIGetCall() {
        Response response =
                given().
                        baseUri("https://jsonplaceholder.typicode.com/").
                        when().
                        get("users").
                        then().
                        assertThat().statusCode(200).
                        assertThat().contentType("application/json").
                        body("name",hasItem("Ervin Howell")).
                        body("size()", greaterThan(3)).
                        extract().response();
        //System.out.println("Response: "+response.asString());

    }
}
