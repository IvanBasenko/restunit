package rofl;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
public class Tests {

    private static final String TEST_URL = "http://www.omdbapi.com?apikey=5be277c0";

    @Test
    public void test1() {
        given()
                .param("s", "Rambo")
                .when()
                .get(TEST_URL)
                .then()
                .statusCode(200)
                .body("Search.findAll{it.Type == 'movie'}.size()", equalTo(5));
    }
    @Test
    public void test2() {
        Response firstResponse = given().param("t", "Ready Player One").get(TEST_URL);
        String firstResponseBody = firstResponse.asString();

        String imdbID = firstResponse.path("imdbID");

        Response secondResponse = given().param("i", imdbID).get(TEST_URL);
        String secondResponseBody = secondResponse.asString();

        assertEquals(firstResponseBody, secondResponseBody);
    }

}

