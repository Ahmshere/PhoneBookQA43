package web;

import config.BaseTest;
import enums.ContactField;
import enums.TopMenuItem;
import helpers.*;
import interfaces.TestHelper;

import models.Contact;
import org.openqa.selenium.Alert;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class PhoneBookTests extends BaseTest implements TestHelper {

    @Test
    public void successfulLogin() {
        MainPage mainPage = new MainPage(getDriver());
        BasePage basePage = new BasePage(getDriver());

        LoginPage loginPage = basePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReaderXML.getProperties(MY_USER, XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD, XML_DATA_FILE))
                .clickByLoginButton();
        ContactsPage cp = new ContactsPage(getDriver());
        boolean result = cp.isSignButtonPersist();
        Assert.assertTrue(result);
    }

    @Test
    public void registrationWithoutPassword() {
        MainPage mainPage = new MainPage(getDriver());
        BasePage basePage = new BasePage(getDriver());
        LoginPage loginPage = basePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage
                .fillEmailField(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 10, 7, 3))
                .clickByRegistrationButton();
        String expectedTextAlert = "Wrong";
        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedTextAlert);
        Assert.assertTrue(isAlertHandled);
    }

    @Test
    public void loginWithoutPasswordPositive() {
        MainPage mainPage = new MainPage(getDriver());
        BasePage basePage = new BasePage(getDriver());
        LoginPage loginPage = basePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage
                .fillEmailField(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 10, 7, 3))
                .clickByLoginButtonAlert();
        String expectedAlertText = "Wrong email or password";
        boolean res = AlertHandler.handleAlert(alert, expectedAlertText);
        Assert.assertTrue(res);

    }

    @Test
    public void loginOfAnExistingUserAddContact() {
        MainPage mainPage = new MainPage(getDriver());
        BasePage basePage = new BasePage(getDriver());
        LoginPage loginPage = basePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage.fillEmailField(PropertiesReaderXML.getProperties(MY_USER, XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD, XML_DATA_FILE))
                .clickByLoginButton();
        AddPage addPage = basePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3),
                AddressGEnerator.generateAddress(),
                "Test");
        System.out.println("Contact " + contact.toString());
        addPage.fillContactFormAndSave(contact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        System.out.println("Contacts size: " + contactsPage.getContactListSize());
        TakeScreen.takeScreenShot(getDriver(), "loginOfAnExistingUserAddContact");
        Assert.assertTrue(contactsPage.getDataFromContactList(contact));
    }

    @Test
    public void loginOfAnExistingUserAddAndEditContact() {
        MainPage mainPage = new MainPage(getDriver());
        BasePage basePage = new BasePage(getDriver());
        LoginPage loginPage = basePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage.fillEmailField(PropertiesReaderXML.getProperties(MY_USER, XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD, XML_DATA_FILE))
                .clickByLoginButton();
        AddPage addPage = basePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3),
                AddressGEnerator.generateAddress(),
                "Test");
        System.out.println("CONTACT: " + contact.toString());
        addPage.fillContactFormAndSave(contact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        String myNewValue = contactsPage
                .findOpenContactAndChangeFieldValue(
                        contact, ContactField.EMAIL, EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3));
        contactsPage.clickSaveButton(myNewValue);
        System.out.println("NEW VALUE: " + myNewValue);
        // Assert....

    }

    @Test
    public void createAndDeleteContactUsingSerialization() throws IOException, ClassNotFoundException {
        MainPage mainPage = new MainPage(getDriver());
        BasePage basePage = new BasePage(getDriver());
        LoginPage loginPage = basePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage.fillEmailField(PropertiesReaderXML.getProperties(MY_USER, XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD, XML_DATA_FILE))
                .clickByLoginButton();
        AddPage addPage = basePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3),
                AddressGEnerator.generateAddress(),
                "Test");
        System.out.println("CONTACT: " + contact.toString());
        addPage.fillContactFormAndSave(contact);
        Contact.serializationContact(contact, "initContact.dat");
        Contact deserealizedContact = Contact.deserializationContact("initContact.dat");
        // Task Delete



    }


}

