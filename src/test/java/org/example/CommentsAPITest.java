package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class CommentsAPITest {
    private int Id;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void GetAPIResourcePosts(){
        Response response =
                when().
                        get("comments/2").
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
        jsonRequest.put("name","TestEpamAPI");
        jsonRequest.put("body","testing testing testing");
        jsonRequest.put("email","abc@xyz.com");

        Response response  = given().
                header("Content-type", "application/json").
                body(jsonRequest.toJSONString()).
                when().
                post("comments").
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
        jsonRequest.put("email","test@test.com");
        jsonRequest.put("body","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");

        Response response  = given().
                header("Content-type", "application/json").
                body(jsonRequest.toJSONString()).
                when().
                put("comments/101").
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
                delete("comments/2").
                then().
                assertThat().statusCode(200).
                extract().response();
        //Id = response.body().path("id");
        System.out.println(response.body().asString());
        //System.out.println(""+response.body().path("id"));
    }

}
