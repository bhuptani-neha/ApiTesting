package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class Module5HomeTask2 {
    static String APIkey = "";

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://api.openweathermap.org/";
        RestAssured.basePath = "data/2.5/";
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                build().
                header("Content-type", "application/json");
        RestAssured.requestSpecification = requestSpecification;
        try {
            File f = new File("src/test/resources/APIKEY.txt");
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            byte[] keyBytes = new byte[(int) f.length()];
            dis.readFully(keyBytes);
            String temp = new String(keyBytes);
            System.out.println(""+temp);
            APIkey = temp.trim();
            dis.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void getLatLongAndUseToGetWeatherData(){
        //Get Latitude and Longitude for Hyderabad City
        Response res = when().
                get("weather?q=hyderabad&appid="+APIkey).
                then().
                assertThat().statusCode(200).
                extract().response();
        System.out.println("Response  - "+res.asString());
        float longitude = res.body().path("coord.lon");
        float latitude = res.body().path("coord.lat");
        System.out.println("Longitude -- "+longitude + " latitude -- " +latitude);

        //Use Latitude and Longitude to get weather Data and verify
        Response response = when().
                get("weather?lat="+latitude+"&lon="+longitude+"&appid="+APIkey).
                then().
                assertThat().statusCode(200).
                assertThat().contentType("application/json").
                body("name",equalTo("Hyderabad")).
                body("sys.country",equalTo("IN")).
                body("main.temp_min",greaterThan(Float.parseFloat(""+0))).
                body("main.temp",greaterThan(Float.parseFloat(""+0))).
                extract().response();
                System.out.println("Response- "+response.asString());
    }

}
