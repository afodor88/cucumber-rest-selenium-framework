package stepDefinitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;



public class ReqresSteps {
    private static final String BASE_URL = "https://reqres.in";

    private static String token;
    private static Response response;
    private static String jsonString;

    @When("user tries to register with username {string} and password {string}")
    public void user_tries_to_register_with_username_and_password(String username, String password) {

        RequestSpecification request = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        JsonObject requestParams = new JsonObject();
        requestParams.add("email", username);
        requestParams.add("password", password);

        jsonString = requestParams.toString();
        request.body(jsonString);
        System.out.println(jsonString);

        response = request.post("/api/register");


    }
    @Then("API call is successful with the status code {int}")
    public void api_call_is_successful_with_the_status_code(int statusCode) {

        Assert.assertEquals(statusCode, response.getStatusCode());

    }

    @And("a token is received")
    public void a_token_is_received() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        token = jsonPathEvaluator.get("token");
        System.out.println(token);

        Assert.assertNotNull(token);
    }

    @Then("API call has failed with the status code {int} and error message {string}")
    public void api_call_has_failed_with_the_status_code_and_error_message(int statusCode, String errorMessage) {
        Assert.assertEquals(statusCode, response.getStatusCode());

        JsonPath jsonPathEvaluator = response.jsonPath();
        String errorMsg = jsonPathEvaluator.get("error");
        System.out.println(errorMsg);

        Assert.assertEquals(errorMsg, errorMessage);
    }


}
