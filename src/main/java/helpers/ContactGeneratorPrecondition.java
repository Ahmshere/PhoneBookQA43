package helpers;

import io.qameta.allure.Step;
import models.Contact;
import org.testng.annotations.BeforeMethod;

public class ContactGeneratorPrecondition {

    @Step("Creating a Contact Entity")
    @BeforeMethod
    public void createNewContact(){
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5,5,3),
                AddressGEnerator.generateAddress(),"descr"
        );
    }

}
