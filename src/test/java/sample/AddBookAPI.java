package sample;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resuableMethods.Payload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class AddBookAPI {

    @Test(dataProvider = "Books")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String res = given().header("Content-Type", "application/json").

                body(Payload.addBook(isbn, aisle)).

                when().

                post("/Library/Addbook.php").

                then().log().all().assertThat().statusCode(200).

                extract().response().asString();
    }

    @DataProvider(name = "Books")
    public Object[][] addBooks() {
        Object data[][] = {
                {"ojfwtyy", "93683"},
                {"cwetzee", "42583"},
                {"okmmfet", "5373"}
        };
        return data;
    }

    @Test
    public void bookApi() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String res = given().header("Content-Type", "application/json").

                body(new String(Files.readAllBytes(Paths.get("D:\\bhanu_API\\RestAssured\\Api notes\\addbookApi.json")))).

                when().

                post("/Library/Addbook.php").

                then().log().all().assertThat().statusCode(200).

                extract().response().asString();
    }
}
