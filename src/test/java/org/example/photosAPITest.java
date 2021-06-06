package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class photosAPITest {

    private int Id;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void GetAPIResourcePosts(){
        Response response =
                when().
                        get("photos/2").
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
        jsonRequest.put("albumId","1");
        jsonRequest.put("title","TestEpamAPI");
        jsonRequest.put("url","testing testing testing");
        jsonRequest.put("thumbnailUrl","abc@xyz.com");

        Response response  = given().
                header("Content-type", "application/json").
                body(jsonRequest.toJSONString()).
                when().
                post("photos").
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
        jsonRequest.put("albumId","1");
        jsonRequest.put("title","testing 1234 testing 1234");
        jsonRequest.put("url","https://via.placeholder.com/600/771796");

        Response response  = given().
                header("Content-type", "application/json").
                body(jsonRequest.toJSONString()).
                when().
                put("photos").
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
                delete("photos/501").
                then().
                assertThat().statusCode(200).
                extract().response();
        //Id = response.body().path("id");
        System.out.println(response.body().asString());
        //System.out.println(""+response.body().path("id"));
    }

}
