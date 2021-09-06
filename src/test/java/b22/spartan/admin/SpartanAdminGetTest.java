package b22.spartan.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;

@Disabled
@SerenityTest
public class SpartanAdminGetTest {
    
    @BeforeAll
    public static void init(){
        baseURI = "http://44.195.19.167:7000";
    }

    @Test
    public void getAllSpartan(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200);

    }
    @Test
    public void getOneSpartan(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200);

        System.out.println("statusCode = " + lastResponse().statusCode());
        System.out.println("id = " + lastResponse().path("id"));
        System.out.println("name = " + lastResponse().jsonPath().getString("name"));

        //serenity way of assertion

        Ensure.that("Status code is 200",validatableResponse -> validatableResponse.statusCode(200));

        Ensure.that("Content-type is JSON", vRes -> vRes.contentType(ContentType.JSON));

        Ensure.that("Id is 15", vRes -> vRes.body("id", is(15)));
    }



}
