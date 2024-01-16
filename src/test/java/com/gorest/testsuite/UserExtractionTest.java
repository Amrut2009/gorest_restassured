package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> listOfId = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of id is : " + listOfId);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> listOfNames = response.extract().path("Names");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Names are : " + listOfNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  name  is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> inactiveNames = response.extract().path("findAll { it.status == 'inactive' }.name");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Names of objects with status 'inactive': " + inactiveNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> activeNames = response.extract().path("findAll { it.status == 'active' }.name");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Names of gender with status 'active': " + activeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> femaleNames = response.extract().path("findAll { it.gender == 'female' }.name");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Names of objects with gender 'female': " + femaleNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> inactiveemails = response.extract().path("findAll { it.status == 'inactive' }.email");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("all email with status 'inactive': " + inactiveemails);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> maleIds = response.extract().path("findAll { it.gender == 'male' }.id");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("IDs of objects with gender 'male': " + maleIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<String> statusValues = response.extract().path("status");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("All status values: " + statusValues);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Lal Dwivedi
    @Test
    public void test0010() {
        String email = response.extract().path("find{it.email == 'embranthiri_akshat@pfeffer.test'}.name");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Email for the object with name 'Lal Dwivedi': " + email);
        System.out.println("------------------End of Test---------------------------");
    }


    //11. Get gender of id = 5914189
    @Test
    public void test0011() {
        String gender = response.extract().path("find { it.id == 5914189 }.gender");

        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Gender for the object with id 5914189: " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    }
