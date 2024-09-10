package helpers;

import io.qameta.allure.Step;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class ContactGeneratorPrecondition {

    private Contact contact;

    @Step("Creating a Contact Entity")
    @BeforeMethod
    public void createNewContact(){
        contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5,5,3),
                AddressGEnerator.generateAddress(),"descr"
        );
    }

    public Contact getContact() {
        return contact;
    }
}
