package org.example;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.*;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Task1FindCountResources {
    public static ExtractableResponse response;
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @DataProvider(name="EndPoints")
    Object[][] EndPointData(){
        return new Object[][] {
                {"posts"},
                {"comments"},
                {"albums"},
                {"photos"},
                {"todos"},
                {"users"}
        };
    }

    @Test(dataProvider = "EndPoints")
    public void CountNumberOfPosts(String path){
        int  len = when().
                get(path).
        then().
                assertThat().statusCode(200).
                extract().body().path("id").toString().split(",").length;

        int len1 = when().
                get("posts").
                then().
                assertThat().statusCode(200).
                extract().body().path("id").toString().split(",").length;
    }
    @Test(dataProvider = "EndPoints")
    public void GetResourceTest(String path){
        given().
                baseUri("https://jsonplaceholder.typicode.com/").
        when().
                get(path+"/2").
        then().
                assertThat().statusCode(200).
                body("id",equalTo(2));
    }

    @Test
    public void PostResourceTest(){
        given().
                baseUri("https://jsonplaceholder.typicode.com/").
                when().
                post("posts/2").
                then().
                assertThat().statusCode(200).
                body("id",equalTo(2));
    }


    @Test(dataProvider = "EndPoints")
    public void CountNumberOfPosts1(String path){
        int  len = when().
                get(path).
                then().
                assertThat().statusCode(200).
                extract().body().path("id").toString().split(",").length;

        int len1 = when().
                get("posts").
                then().
                assertThat().statusCode(200).
                extract().body().path("id").toString().split(",").length;

    }

    @Test(dataProvider = "EndPoints")
    public void CountNumberOfPosts2(String path){
        int  len = when().
                get(path).
                then().
                assertThat().statusCode(200).
                extract().body().path("id").toString().split(",").length;

        int len1 = when().
                get("posts").
                then().
                assertThat().statusCode(200).
                extract().body().path("id").toString().split(",").length;

    }


}
