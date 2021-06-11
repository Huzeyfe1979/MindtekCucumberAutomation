package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITest {

    public static void main(String[] args) {

        /*
        Do GET call.
        get employee with employee id 100
        /api/employees/100
         */

        /*
        Get Call:
        URL + Headers
        URL = BaseURL+Endpoint
        Given BaseURL
        And Headers
        When Endpoint
         */

        Response response = given().baseUri("https://devenv-apihr-arslan.herokuapp.com") // BaseURL
                .and().accept(ContentType.JSON) // Header  (Accept)
                .when().get("/api/employees/100"); // call type and endpoint

        response.then().log().all(); // log.all means print all

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        System.out.println(response.body().as(HashMap.class));

        Map<String,Object> responseData = response.body().as(HashMap.class);

        System.out.println(responseData.get("employeeId"));

        /*
        Post Employee
        Request: BaseURL + Endpoint + Headers + Json Body
        Given BaseURL
        And Accept -> application json
        And Content Type -> application json
        When Json body
        And send post call

        Response:
        statusCode -> 201
         */

        Response postResponse = given().baseUri("https://devenv-apihr-arslan.herokuapp.com")
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body("{\"employee ID\" : \"266\",\n" +
                                "\"firstName\" : \"Huzeyfe\",\n" +
                                "\"lastName\" : \"Sutcu\",\n" +
                                "\"department name\" : \"IT\"}")

                .and().post("/api/employees");

        System.out.println(postResponse.statusCode());

        /*
        Delete call:
        Given BaseURL
        when delete call

        Response:
        Status Code -> 204 No Content
         */

        Response deleteResponse = given().baseUri("https://devenv-apihr-arslan.herokuapp.com")
                .when().delete("/api/employees/100");

        System.out.println(deleteResponse.statusCode());



    }

}
