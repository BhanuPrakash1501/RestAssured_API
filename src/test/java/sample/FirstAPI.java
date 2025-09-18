package sample;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import resuableMethods.JsonResponse;
import resuableMethods.Payload;

import static io.restassured.RestAssured.given;

public class FirstAPI {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParams("key", "qaclick123").header("content-Type", "application/json")
                .body(Payload.addPlacePayload())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = JsonResponse.rawJson(response);
        System.out.println(js1.getString("scope"));
        String placeId = js1.getString("place_id");
        System.out.println(placeId);
        String update_Address = "Madhapur";

        String updateResponse = given().log().all().queryParams("key", "qaclick123").queryParams("place_id", placeId)
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + update_Address + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").
                when().put("/maps/api/place/update/json")
                .then().log().all().statusCode(200).extract().response().asString();

        JsonPath js2 = JsonResponse.rawJson(updateResponse);
        String message = js2.getString("msg");
        System.out.println(message);

        String res = given().log().all().queryParams("key", "qaclick123").queryParams("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().response().asString();
        JsonPath js3 = JsonResponse.rawJson(res);
        String actual_address = js3.getString("address");
        Assert.assertEquals(actual_address, update_Address);

    }
}
