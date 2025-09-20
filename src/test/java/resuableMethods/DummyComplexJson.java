package resuableMethods;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DummyComplexJson {
    /*Json used in this Section with Queries to solve

1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
  */

    @Test
    public void test() {
        JsonPath js1 = JsonResponse.rawJson(Payload.dynamicPayload());
        int count = js1.get("courses.size()");
        System.out.println(count);
        String title = js1.get("courses[1].title");
        System.out.println(title);
        String website = js1.get("dashboard.website");
        System.out.println(website);
        int sum = 0;
        for (int i = 0; i < count; i++) {
            int price = js1.getInt("courses["+i+"].price");
            int copies = js1.getInt("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            sum = sum + amount;

        }
        System.out.println(sum);
        int purchaseAmount =js1.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);

    }
}
