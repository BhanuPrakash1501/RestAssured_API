package resuableMethods;

import io.restassured.path.json.JsonPath;

public class JsonResponse {
    public static JsonPath rawJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
}
