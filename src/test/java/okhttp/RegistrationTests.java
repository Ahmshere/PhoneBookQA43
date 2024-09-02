package okhttp;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.bouncycastle.cert.ocsp.Req;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements TestHelper {

    @Test
    public void registrationTestPositive() throws IOException {
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3))
                .password(PasswordStringGenerator.generateRandomPassword());
        System.out.println("New user " + requestModel.toString());
        RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        String result = response.body().string();

        System.out.println(result);
        if (response.isSuccessful()) {
            AuthenticationResponseModel responseModel =
                    GSON.fromJson(result, AuthenticationResponseModel.class);
            System.out.println("TOKEN : " + responseModel.getToken());
            Assert.assertTrue(response.isSuccessful());
        } else {
            ErrorModel errorModel = GSON.fromJson(result, ErrorModel.class);
            System.out.println(response.code());
        }
    }

    @Test
    public void wrongPasswordRegistration() throws IOException {
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3))
                .password("1");
        RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        if(response.isSuccessful()){
            System.out.println("Error...");
        }
        else {
            ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);

            Assert.assertEquals(response.code(), 400);
        }
    }
    @Test
    public void duplicateUserRegistration() throws IOException {
        // TASK
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3))
                .password(PasswordStringGenerator.generateRandomPassword());
        Response response = null;
        String result = null;
        for (int i=0; i<2; i++){
            RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);

            Request request = new Request.Builder()
                    .url(BASE_URL+REGISTRATION_PATH)
                    .post(requestBody)
                    .build();
            response = CLIENT.newCall(request).execute();
           result = response.body().string();
           System.out.println("USER_1: "+result);
        }
        if(response.isSuccessful()){
            AuthenticationResponseModel authenticationResponseModel = GSON.fromJson(result, AuthenticationResponseModel.class);
            System.out.println("token: "+authenticationResponseModel.getToken());
        }
        else {ErrorModel errorModel = GSON.fromJson(result, ErrorModel.class);
        Assert.assertEquals(response.code(), 409);
        }
    }





}
