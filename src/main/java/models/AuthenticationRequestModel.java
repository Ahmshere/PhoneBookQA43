package models;

import io.qameta.allure.Step;

// Lombok !!!!!
public class AuthenticationRequestModel {

    private  String username;
    private String password;

    public static AuthenticationRequestModel username(String username){
        return new AuthenticationRequestModel(username, null);
    }
    public AuthenticationRequestModel password(String password){
        this.password=password;
        return this;
    }
    @Step("Get user name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public AuthenticationRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
