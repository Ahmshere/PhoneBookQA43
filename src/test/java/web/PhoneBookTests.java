package web;

import config.BaseTest;
import enums.TopMenuItem;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTests extends BaseTest {

    @Test
    public void successfulLogin(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickByLoginButton();
        // TASK 2
      boolean result = ContactsPage
                .isElementPersist(getDriver().findElement(By.xpath("//button[contains(text(),'Sign')]")));
        Assert.assertTrue(result);
    }
}
