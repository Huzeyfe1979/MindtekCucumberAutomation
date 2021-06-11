package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APITest4 {

    public static void main(String[] args) {

        /*
        Authentication api call Request
        1. Endpoint
        2. Headers
        3. Body
         */

        Response response = given().baseUri("https://mindtek-restapi.herokuapp.com/")
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("{\n" +
                        "    \"password\": \"MindtekStudent\",\n" +
                        "    \"username\": \"Mindtek\"\n" +
                        "}")
                .when().post("authenticate");

        String token = response.jsonPath().getString("jwt");

        System.out.println(token);
    }
}