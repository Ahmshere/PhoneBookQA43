package web;

import config.BaseTest;
import enums.TopMenuItem;
import helpers.AlertHandler;
import helpers.EmailGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTests extends BaseTest {

    @Test
    public void successfulLogin() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickByLoginButton();
        // TASK 2
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

