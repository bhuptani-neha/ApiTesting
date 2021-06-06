package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class albumsAPITest {

    private int Id;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void GetAPIResourcePosts(){
        Response response =
                when().
                        get("albums/2").
                        then().
                        assertThat().statusCode(200).
                        body("id",equalTo(2)).
                        extract().response();
        System.out.println(response.asString());
    }

    @Test
    public void PostAPIResourcePosts(){
        //Create Json Object
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("userId","1");
        jsonRequest.put("title","TestEpamAPI");

        Response response  = given().
                header("Content-type", "application/json").
                body(jsonRequest.toJSONString()).
                when().
                post("albums").
                then().
                assertThat().statusCode(201).
                extract().response();
        Id = response.body().path("id");
        System.out.println(response.body().asString());
        System.out.println(""+response.body().path("id"));
    }

    @Test
    public void PutAPIResourcePosts(){
        //Create Json Object
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("id","501");
        jsonRequest.put("userId","1");
        jsonRequest.put("title","TestEpamAPI123");

        Response response  = given().
                header("Content-type", "application/json").
                body(jsonRequest.toJSONString()).
                when().
                put("albums").
                then().
                assertThat().statusCode(200).
                extract().response();
        //Id = response.body().path("id");
        System.out.println(response.body().asString());
        System.out.println(""+response.body().path("id"));
    }

    @Test
    public void DeleteAPIResourcePosts(){

        Response response  = given().
                header("Content-type", "application/json").
                when().
                delete("albums/2").
                then().
                assertThat().statusCode(200).
                extract().response();
        //Id = response.body().path("id");
        System.out.println(response.body().asString());
        //System.out.println(""+response.body().path("id"));
    }


}
