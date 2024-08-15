package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHandler {
    private WebDriver driver;

    public AlertHandler(WebDriver driver) {
        this.driver = driver;
    }

    public static boolean handleAlert(Alert alert, String expectedText){
        if(alert != null){
            String alertText = alert.getText();
            alert.accept();
            return alertText.contains(expectedText);
        }else {
            System.out.println("There is no alert...");
            return false;}
    }
    public Alert getAlertIfPresent(){
        try{
            WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.alertIsPresent());
        }catch (TimeoutException exception){
            System.out.println("There is no alert...");
            return null;
        }
    }


}
