import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration_Page {
    public WebDriver driver;
    private final By Sign_Up_Button = By.cssSelector("div[class='m-auto block w-fit cursor-pointer text-[12px] font-medium text-primary md:inline md:vw-text-[14] md:vw-ml-[2] md:ltr:ml-[4px] md:rtl:mr-[4px]']");
    private final By Ok_Button = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    private final By Email_Field = By.xpath("//input[@id='userName']");
    private final By Continue_Button = By.xpath("//button[@type='submit']");
    private final By Password = By.xpath("//input[@id='password']");
    private final By Check_Box_1 = By.cssSelector("label[for='promotionsNews'] span:nth-child(2)");
    private final By Check_Box_2 = By.cssSelector("label[for='termsConditions'] span:nth-child(2)");
    private final By Create_Account_Button = By.xpath("//button[@type='submit']");
    private final By Create_Profile_Title = By.cssSelector("div[class='text-center text-[16px] font-black leading-[1.25] text-white md:font-bold md:vw-text-[24]']");
























    public Registration_Page(WebDriver driver){
        this.driver=driver;


    }

    public Registration_Page Press_On_Sign_Up_Button(){
        Utility.Click_On_Element(driver, Sign_Up_Button);
        return this;
    }

    public Registration_Page Press_On_Ok_Button(){
        Utility.Click_On_Element(driver, Ok_Button);
        return this;
    }

    public Registration_Page Enter_Email_In_Registration(){
        Utility.Send_Data(driver,Email_Field,"omarradwaa@gmail.com");
        return this;
    }

    public Registration_Page Press_On_Continue_Button(){
        Utility.Click_On_Element(driver, Continue_Button);
        return this;
    }
    public Registration_Page Enter_Password_In_Registration(){
        Utility.Send_Data(driver,Password,"omar123O");
        return this;

    }
    public Registration_Page Press_On_Check_Box_1(){
        Utility.Click_On_Element(driver, Check_Box_1);
        return this;
    }

    public Registration_Page Press_On_Check_Box_2(){
        Utility.Click_On_Element(driver, Check_Box_2);
        return this;}


    public Registration_Page Press_On_Create_Account_Button(){
        Utility.Click_On_Element(driver, Create_Account_Button);
        return this;}


    public Registration_Page Press_On_Create_Profile_Title(){
        Utility.Click_On_Element(driver, Create_Profile_Title);
        return this;}

}
