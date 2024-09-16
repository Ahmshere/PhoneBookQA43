package restassured;

import db.DatabaseConnection;
import db.DatabaseReader;
import helpers.*;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class UpdateContact implements TestHelper {

    @Test
    public void updateContactPositive(){
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5,5,3),
                AddressGEnerator.generateAddress(),"descr"
        );
        RestAssured.baseURI = BASE_URL+UPDATE_CONTACT;
        String message = given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .body(contact)
                .contentType(ContentType.JSON)
                .when().post().then().extract().path("message");
        String id = IdExtractor.getId(message);
        contact.setId(id);
        contact.setEmail("updatedemail@email.com");

       Response response = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .body(contact)
                .contentType(ContentType.JSON)
                .when().put()
                .then().assertThat()
                .extract().response();
        System.out.println("RESPONSE "+response.getBody().path("message"));
        Assert.assertEquals(response.getBody().path("message"), "Contact was update");
    }

    @Test
    public void updateContactLocal() throws SQLException {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5,5,3),
                AddressGEnerator.generateAddress(),"descr"
        );
        RestAssured.baseURI = BASE_URL+ADD_CONTACT;
        String response = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .body(contact).contentType(ContentType.JSON)
                .when().post().then().extract().path("message");
        String id = IdExtractor.getId(response);
        contact.setId(id);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.contactRecorder(contact);
        contact.setEmail("fghrtyert@mail.test");
        given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .body(contact).contentType(ContentType.JSON).when().put().then().statusCode(200);
            Contact changedContact = DatabaseReader.readContactFromDb(id);
            Assert.assertNotEquals(changedContact.getEmail(), contact.getEmail());
    }

}
