package Base;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestBase {
    protected Map<String, Object> headers = new HashMap<String, Object>();

    public RequestBase(){
        headers.put("Content-Type", "application/json");
    }

    public Response executeGetMethod(String path){
        Response results =
                given()
                    .headers(headers)
                    .log().all()
                .when()
                    .get(path)
                .then()
                    .log().all()
                    .extract()
                    .response();
        return results;
    }

    public Response executePostMethod(String path, String jsonBody){
        Response results =
                given()
                        .headers(headers)
                        .body(jsonBody)
                        .log().all()
                        .when()
                        .post(path)
                        .then()
                        .log().all()
                        .extract()
                        .response();
        return results;
    }

    public Response executePutMethod(String path, String jsonBody){
        Response results =
                given()
                        .headers(headers)
                        .body(jsonBody)
                        .log().all()
                        .when()
                        .put(path)
                        .then()
                        .log().all()
                        .extract()
                        .response();
        return results;
    }

    public Response executeDeleteMethod(String path){
        Response results =
                given()
                        .headers(headers)
                        .log().all()
                        .when()
                        .delete(path)
                        .then()
                        .log().all()
                        .extract()
                        .response();
        return results;
    }

}
