package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ThomasBuyerTest {
    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "http://www.thomas-bayer.com/sqlrest";
    }

    @Test
    public void checkStatusCode() {
        Response response = RestAssured.when()
                .get("/PRODUCT/49")
                .andReturn();
        System.out.println(response.getStatusLine());
        System.out.println("Response Body is =>  " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void createNewProduct() {

        Map<String, Object> productDetails = new HashMap<String, Object>();
        productDetails.put("ID", "50");
        productDetails.put("NAME", "Oreo");
        productDetails.put("PRICE", "222");


        Response response =  given()
                .contentType("application/json")
                .body(productDetails)
                .when()
                .post(baseURI + "/PRODUCT");
        String body = response.getBody().asString();
        System.out.println(body);
    }
}
