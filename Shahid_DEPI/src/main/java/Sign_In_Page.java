import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utilities.Utility;
import org.openqa.selenium.WebElement;

public class Sign_In_Page {
    public WebDriver driver;
    private final By sign = By.xpath("//a[normalize-space()='Sign in']");
    private final By Email = By.xpath("//input[@id='emailMobileSignIn.userName']");
    public final By Password = By.xpath("//input[@id='emailMobileSignIn.password']");
    private final By Sign_In_button = By.xpath("//button[@type='submit']");
    private final By Title = By.xpath("//span[normalize-space()='Sign in to Shahid']");
    private final By Title_In_Login = By.xpath("//h1[normalize-space()='Hello! Choose who is watching now']");


    public Sign_In_Page(WebDriver driver) {
        this.driver = driver;
    }

    public Sign_In_Page Press_On_sign() {
        Utility.Click_On_Element(driver, sign);
        return this;
    }

    public Sign_In_Page Enter_Valid_Email() {
        Utility.Send_Data(driver, Email, "ahmeddelnagdi@gmail.com");
        return this;
    }

    public Sign_In_Page Enter_Password() {
        Utility.Send_Data(driver, Password, "omar123O");
        return this;
    }

    public Sign_In_Page Press_On_Title() throws InterruptedException {
        Utility.Click_On_Element(driver, Title);
        return this;
    }

    public Sign_In_Page Press_On_Sign_In_button() throws InterruptedException {
        Thread.sleep(6000);
        Utility.Click_On_Element(driver, Sign_In_button);
        return this;
    }

    public Sign_In_Page Press_On_Title_In_Login() throws InterruptedException {
        Thread.sleep(6000);
        Utility.Click_On_Element(driver, Title_In_Login);
        return this;
    }

    public Sign_In_Page Enter_Invalid_Email() {
        Utility.Send_Data(driver, Email, "ahmeddelnagdi@gmai");
        return this;

    }

    public Sign_In_Page Enter_Invalid_password() {
        Utility.Send_Data(driver, Password, "omar123wO");
        return this;
    }

    public Sign_In_Page Check_Encryption() {
        WebElement passwordField = driver.findElement(By.id("emailMobileSignIn.password"));
        boolean isPasswordMasked = passwordField.getAttribute("type").equals("password");

        return this;

    }



}


