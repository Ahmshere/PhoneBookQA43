package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactTest extends  GetIdPrecondition implements TestHelper {

    @Test
    public void deleteContactById() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CONTACT+getId())
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void deleteContactByIdUnauthorizedNegative() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CONTACT+getId())
                .addHeader(AUTHORIZATION_HEADER, "222")
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
        System.out.println(errorModel.getError());
        System.out.println(errorModel.getMessage());
        Assert.assertEquals(errorModel.getStatus(), 401);
    }

    @Test
    public void deleteContactByIdContactNotFound() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CONTACT+"1")
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .delete().build();
        Response response = CLIENT.newCall(request).execute();
        System.out.println(response.body().string());
       // Assert.assertEquals(response.code(), 404);
    }



}
