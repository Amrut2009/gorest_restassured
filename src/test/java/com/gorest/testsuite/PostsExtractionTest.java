package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);

    }

    //
//1. Extract the title
    @Test
    public void test001() {
        List<String> listOftitle = response.extract().path("data.title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Extract  title is : " + listOftitle);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        int totalRecords = response.extract().path("data.size()");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Total number of records: " + totalRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String bodyOf15thRecord = response.extract().path("[14].body");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Body of the 15th record: " + bodyOf15thRecord);
        System.out.println("------------------End of Test---------------------------");
    }


//4. Extract the user_id of all the records
@Test
public void test004() {
    List<String> listOfuser_Id = response.extract().path("user_id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of user_id is : " + listOfuser_Id);
    System.out.println("------------------End of Test---------------------------");
}

//5. Extract the title of all the records
@Test
public void test005() {
    List<String> listOftitle = response.extract().path("title");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of title is : " + listOftitle);
    System.out.println("------------------End of Test---------------------------");
}
//6. Extract the title of all records whose user_id = 5914200
@Test
public void test006() {
    List<String> titlesForUserId = response.extract().path("findAll { it.user_id == 5914200 }.title");

    System.out.println("------------------Starting Test---------------------------");
    System.out.println("Titles of records with user_id 5914200: " + titlesForUserId);
    System.out.println("------------------End of Test---------------------------");
}
//7. Extract the body of all records whose id = 93957
@Test
public void test007() {
    List<String> titlesForUserId = response.extract().path("findAll { it.user_id == 93957 }.body");

    System.out.println("------------------Starting Test---------------------------");
    System.out.println("body of records with user_id 93957: " + titlesForUserId);
    System.out.println("------------------End of Test---------------------------");
}

}