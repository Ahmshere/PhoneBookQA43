package restassured;

import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthenticationRequestModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegistrationTests implements TestHelper {

    @Test
    public void registrationPositive(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 7,5,3))
                .password(PasswordStringGenerator.generateRandomPassword());
        String token =
                given().body(requestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().assertThat().statusCode(200)
                .extract().path("token");
        System.out.println(token);
    }

    @Test
    public void registrationNegative(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username("EmailGenerator.")
                .password(PasswordStringGenerator.generateRandomPassword());
       Response response =  given().body(requestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().assertThat().statusCode(400).extract().response();
        System.out.println(response.getBody().asString());
        String message = response.jsonPath().getString("message.username");
        Assert.assertTrue(message.contains("must be a well-formed"));
        System.out.println("Time : "+response.getTime());
        System.out.println("Headers : "+response.getHeaders());
        System.out.println("Status line : "+response.getStatusLine());

    }

}
