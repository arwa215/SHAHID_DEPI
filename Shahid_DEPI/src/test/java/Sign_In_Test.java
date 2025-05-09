import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Sign_In_Test extends Initiate_Shahid{

@Test
    public void log_With_Valid_Credentials() throws InterruptedException {
        new Sign_In_Page(driver).Press_On_sign().Enter_Valid_Email().Enter_Password().Press_On_Title().Press_On_Sign_In_button().Press_On_Title_In_Login();


    Assert.assertEquals(

            driver.findElements(By.xpath("//h1[normalize-space()='Hello! Choose who is watching now']")).isEmpty(),
            false,
            "No EditText elements found");

    }
    @Test
    public void log_With_Invalid_Credentials() throws InterruptedException {
        new Sign_In_Page(driver).Press_On_sign().Enter_Invalid_Email().Press_On_Title();



        Assert.assertEquals(

                driver.findElements(By.xpath("//div[normalize-space()='Please enter a valid email address.']")).isEmpty(),
                false,
                "No EditText elements found");

    }
    @Test
    public void log_With_Invalid_Password() throws InterruptedException {
        new Sign_In_Page(driver).Press_On_sign().Enter_Valid_Email().Enter_Invalid_password().Press_On_Title().Press_On_Sign_In_button();

Thread.sleep(3000);
        Assert.assertEquals(
                driver.findElements(By.xpath("//span[contains(text(),'WEB - You have entered an invalid username or pass')]")).isEmpty(),
                false,
                "No EditText elements found");

    }
    @Test
    public void Password_Is_Encrypted() throws InterruptedException {
        new Sign_In_Page(driver).Press_On_sign().Enter_Valid_Email().Enter_Invalid_password();

        Assert.assertEquals(driver.findElement(By.id("emailMobileSignIn.password"))
                .getAttribute("type"),
                "password",
                "Password field should be of type 'password'"
        );

    }



}
