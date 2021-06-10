package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class Module5HomeTask2 {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://api.openweathermap.org/";
        RestAssured.basePath = "data/2.5/";
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                build().
                header("Content-type", "application/json");
        RestAssured.requestSpecification = requestSpecification;
    }

    @Test
    public void getLatLongAndUseToGetWeatherData(){
        //Get Latitude and Longitude for Hyderabad City
        Response res = when().
                get("weather?q=hyderabad&appid=78f2117d4bf50e40eb9558ddec0f9596").
                then().
                assertThat().statusCode(200).
                extract().response();
        System.out.println("Response  - "+res.asString());
        float longitude = res.body().path("coord.lon");
        float latitude = res.body().path("coord.lat");
        System.out.println("Longitude -- "+longitude + " latitude -- " +latitude);

        //Use Latitude and Longitude to get weather Data and verify
        Response response = when().
                get("weather?lat="+latitude+"&lon="+longitude+"&appid=78f2117d4bf50e40eb9558ddec0f9596").
                then().
                assertThat().statusCode(200).
                assertThat().contentType("application/json").
                body("name",equalTo("Hyderabad")).
                body("sys.country",equalTo("IN")).
                body("main.temp_min",equalTo("301.38")).
                body("main.temp",greaterThan(0)).
                extract().response();
    System.out.println("Response- "+response.asString());


    }

}
