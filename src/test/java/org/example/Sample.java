package org.example;

import org.junit.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Sample {
    //http://api.zippopotam.us/us/90210 GET call
    @Test
    public void verifyStatusCode(){
        given().
                baseUri("http://api.zippopotam.us/").
        when().
                get("us/90210").
        then().
                assertThat().statusCode(200);
    }

    @Test
    public void testMe(){
        Assert.assertEquals(1,1);
    }
}
