package utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.given;

public class SpartanNewBase {



    public static RequestSpecification spec;
    public static RequestSpecification adminSpec;
    public static RequestSpecification userSpec;
    public static RequestSpecification editorSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.92.248.102";
        port = 7000;
        basePath ="/api";

        adminSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        userSpec =given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("user", "user")
                .log().all();
        editorSpec =given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("editor", "editor")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);  //logging with response specficiation

    }

    public static ResponseSpecification responseSpec (int statusCode){
       return expect().statusCode(statusCode)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }


    @AfterAll
    public static void close(){
        //reset the info we set above ,method comes from restassured
        reset();
    }

}
