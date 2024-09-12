package helpers;

import io.qameta.allure.Step;
import models.Contact;
import org.testng.annotations.BeforeMethod;

public class ContactGeneratorPrecondition {

    private Contact contact;

    public Contact getContact() {
        return contact;
    }
    @Step("Creating a new contact entity.")
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

}
