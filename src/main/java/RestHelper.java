import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestHelper {

    private static final String HOST = "https://block.io/";

    public Response sendGetRequest(String url, Map<String, Object> params) {
        return spec()
                .params(params)
                .get(url)
                .then()
                .log().all()
                .extract().response();
    }

    protected RequestSpecification spec() {
        return RestAssured.given()
                .baseUri(HOST)
                .contentType(ContentType.JSON)
                .log().all();
    }

}