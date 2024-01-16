package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .when()
                .get("/users")
                .then().statusCode(200);

    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(), 20);
    }
        // 2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
        @Test
        public void test002 () {
            response.body("find{it.id ==  5914143}.name", equalTo("Himadri Banerjee"));
        }

        // 3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
        @Test
        public void test003 () {
            response.body("name", hasItem(" Bilwa Embranthiri"));
        }

        //4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
        @Test
        public void test004 () {
            response.body("name", hasItems("Bhadrak Singh", "Kailash Pillai", "Chandrabhan Adiga"));
        }

//5. Verify the email of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
@Test
public void test005 () {
response.body("find { it.userId == 5914185 }.email", equalTo("tandon_iv_aanandinii@prosacco.example"));
}

// 6. Verify the status is “Active” of user name is “Amaresh Rana”
@Test
public void test006 () {
response.body("data.find { it.userName == 'Amaresh Rana' }.status", equalTo("Active"));
}
//  7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
@Test
public void test007 () {
    response.body("data.find { it.userName == 'Dhanalakshmi Pothuvaal' }.gender", equalTo("male"));
}

    }