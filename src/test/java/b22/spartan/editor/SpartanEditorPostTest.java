package b22.spartan.editor;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.SpartanNewBase;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import utilities.SpartanUtil;
import java.util.HashMap;
import java.util.Map;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;

@Disabled
@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {

    @DisplayName("Editor should be able to POST")
    @Test
    public void postSpartanAsEditor(){
        // create one spartan using util
        Map<String, Object> bodyMap = SpartanUtil.getRandomSpartanMap();

        given().auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
        .when().post("/spartans")
        .then().statusCode(201);

    }

    @DisplayName("Editor should be able to POST")
    @Test
    public void SpartanAsEditor(){
        // create one spartan using util
        Map<String, Object> bodyMap = SpartanUtil.getRandomSpartanMap();


        given().auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when().post("/spartans")
                .then().statusCode(201);


        int id = lastResponse().path("data.id");

        Ensure.that("status code", validatableResponse -> validatableResponse.statusCode(201));
        Ensure.that("content type",validatableResponse -> validatableResponse.contentType(ContentType.JSON));
        Ensure.that("id",validatableResponse -> validatableResponse.body("data.id",notNullValue()));
        Ensure.that("success", validatableResponse -> validatableResponse.body("success",is("A Spartan is Born!")));
        Ensure.that("name", validatableResponse -> validatableResponse.body("data.name",is(bodyMap.get("name"))));
        Ensure.that("gender", validatableResponse -> validatableResponse.body("data.gender",is(bodyMap.get("gender"))));
        Ensure.that("phone", validatableResponse -> validatableResponse.body("data.phone",is(bodyMap.get("phone"))));
        Ensure.that("check location heaader ends with newly generated id ", validatableResponse -> validatableResponse.header("Location", endsWith(String.valueOf(id))));

    }


    @ParameterizedTest(name = "New Spartan {index} - name: {0}")
    @CsvFileSource(resources = "/SpartanData.csv", numLinesToSkip = 1)
    public void postSpartanWithCsv(String name, String gender,long phone){

        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("name", name);
        mapData.put("gender", gender);
        mapData.put("phone", phone);
    }

}
