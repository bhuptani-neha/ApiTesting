package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Module4HomeTask {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://dummy.restapiexample.com/";
        RestAssured.basePath = "api/v1/";
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                build().
                header("Content-type", "application/json");
        RestAssured.requestSpecification = requestSpecification;
    }

    @Test
    public void getAllEmployees(){

        int len = when().
                get("employees").
                then().
                assertThat().statusCode(200).
                extract().body().path("data.id").toString().split(",").length;
        System.out.println("Total Number of Employees --- "+len);

    }

    @Test
    public void employeePost(){
        Map<String,Object> Employee = new HashMap<>();
        Employee.put("employee_name","Epam Test1");
        Employee.put("employee_salary",55000);
        Employee.put("employee_age",30);

        String EmplyoeeId = given().
                body(Employee).
                when().
                post("create").
                then().
                extract().body().
                path("data.id").toString();
        System.out.println("EId -- "+EmplyoeeId);
    }


    //Get Method is for existing data as API is not storing post data
    @Test
    public void employeeGet(){
         when().
                 get("employee/1").
                 then().
                 assertThat().statusCode(200).
                 assertThat().contentType("application/json").
                 body("data.employee_name",equalTo("Tiger Nixon")).
                 body("data.employee_salary",equalTo(320800)).
                 body("data.employee_age",equalTo(61));
    }


    //Put method is for existing data as Rest API is not storing Post Data
    @Test
    public void employeePut(){
        Map<String,Object> Employee = new HashMap<>();
        Employee.put("id","1");
        Employee.put("employee_name","Epam Test1");
        Employee.put("employee_salary",55000);
        Employee.put("employee_age",40);

        String age = given().
                body(Employee).
                when().
                put("create/1").
                then().
                extract().body().
                path("data.employee_age").toString();
        System.out.println("Updated Age -- "+age);
    }

    //Delete method is for existing data as Rest API is not storing Post Data
    @Test
    public void employeeDelete(){

        String SucessMessage = when().
                delete("create/1").
                then().
                extract().body().
                path("message").toString();
        System.out.println("SucessMessage -- "+SucessMessage);
    }

}
