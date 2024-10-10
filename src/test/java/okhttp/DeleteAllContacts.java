
package okhttp;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContacts implements TestHelper {

    @Test
    public  void clearContacts() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_ALL_CONTACTS)
                .addHeader("Authorization", PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        System.out.println("Code: "+response.code());
        ContactResponseModel contactResponseModel =
                GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println("Message"+contactResponseModel.getMessage());
        //  Assert.assertEquals(contactResponseModel.getMessage(),"All contacts was deleted!");
        Assert.assertTrue(contactResponseModel.getMessage().contains("All contacts"));
    }

}

