package web;

import config.BaseTest;
import enums.TopMenuItem;
import helpers.AlertHandler;
import helpers.EmailGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTests extends BaseTest implements TestHelper{

    @Test
    public void successfulLogin() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReaderXML.getProperties(MY_USER, XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD,XML_DATA_FILE))
                .clickByLoginButton();
        ContactsPage cp = new ContactsPage(getDriver());
        boolean result = cp.isSignButtonPersist();
        Assert.assertTrue(result);
    }

    @Test
    public void registrationWithoutPassword(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage
                .fillEmailField(EmailGenerator.generateEmail(10,7,3))
                .clickByRegistrationButton();
        String expectedTextAlert = "Wrong";
        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedTextAlert);
        Assert.assertTrue(isAlertHandled);
    }

}

