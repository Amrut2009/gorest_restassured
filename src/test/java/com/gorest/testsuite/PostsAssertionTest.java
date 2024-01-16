package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .when()
                .get("/posts")
                .then().statusCode(200);

    }

//1. Verify the if the total record is 25
@Test
public void test001() {
    List<Integer> total = response.extract().path("id");
    Assert.assertEquals(total.size(), 25);
}
// 2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
@Test
public void test002() {
    response.body("[2].title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));
}

// 3. Check the single user_id in the Array list (5914249)
@Test
public void test003 () {
    response.body("user_id", hasItem(5914065));
}

//4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
@Test
public void test004 () {
    response.body("id", hasItems(" 93941,","93920,","93916,"));
}

//5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
//    Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
//    vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
//    tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
//    acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
@Test
public void test005 () {
    response.body("find { it.userId ==  5914181 }.body", equalTo("Sed uxor adimpleo. Nostrum amiculum via. Consectetur agnitio ustilo. Dolores adfero qui. Adultus dicta necessitatibus. Dignissimos cena dolore. Thymbra verbum quaerat. Adstringo solitudo defigo. Capitulus valeo tonsor. Amissio attonbitus tabula. Non adopto utroque. Ascit capillus ver."));
}

}

