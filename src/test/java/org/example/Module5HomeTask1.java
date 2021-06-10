package org.example;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class Module5HomeTask1 {


    //Find the event names which are in English(En) language from below and Verify the list of names
    @Test
    public void verifyListOfEventsInEnglish(){
        given().baseUri("https://events.epam.com/").
                basePath("api/v2/").
        when().
                get("events").
                then().
                assertThat().statusCode(200).
                assertThat().contentType("application/json").
                body("events.findAll{it.language = \"En\"}.title",contains("Check-in app guide"));
    }
}
