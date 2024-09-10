package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class GetIdPrecondition implements TestHelper {

    String id;

    @BeforeTest
    public void createNewContactGetIdPrecondition() throws IOException {
        Contact contact = new Contact(NameAndLastNameGenerator.generateName()
                , NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3),
                AddressGEnerator.generateAddress(),
                "Description");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel = GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println("RESPONSE: " + contactResponseModel.getMessage() + contact.toString());
        id = IdExtractor.getId(contactResponseModel.getMessage());
    }

    public String getId() {
        return id;
    }

}
