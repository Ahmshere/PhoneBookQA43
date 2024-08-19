package web;

import config.BaseTest;
import enums.TopMenuItem;
import helpers.*;
import interfaces.TestHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

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

    @Test
    public void  loginWithoutPasswordPositive(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage
                .fillEmailField(EmailGenerator.generateEmail(10,7,3))
                .clickByLoginButtonAlert();
        String expectedAlertText = "Wrong email or password";
        boolean res = AlertHandler.handleAlert(alert, expectedAlertText);
        Assert.assertTrue(res);

    }

    @Test
    public void loginOfAnExistingUserAddContact(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage.fillEmailField(PropertiesReaderXML.getProperties(MY_USER, XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD,XML_DATA_FILE))
                .clickByLoginButton();
        AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGEnerator.generateAddress(),
                "Test");
        System.out.println("Contact "+contact.toString());
        addPage.fillContactFormAndSave(contact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        System.out.println("Contacts size: "+ contactsPage.getContactListSize());
        Assert.assertTrue(contactsPage.getDataFromContactList(contact));


    }





}

