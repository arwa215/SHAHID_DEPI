import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Registration_Test extends Initiate_Shahid {
    @Test
    public void Register_With_Valid_Credentials(){
        new Sign_In_Page(driver).Press_On_sign();
        new Registration_Page(driver).Press_On_Ok_Button().Press_On_Sign_Up_Button().Enter_Email_In_Registration().Press_On_Continue_Button().Enter_Password_In_Registration()
                .Press_On_Check_Box_1().Press_On_Check_Box_2().Press_On_Create_Account_Button().Press_On_Create_Profile_Title();
        Assert.assertEquals(

                driver.findElements(By.cssSelector("div[class='text-center text-[16px] font-black leading-[1.25] text-white md:font-bold md:vw-text-[24]']")).isEmpty(),
                false,
                "No EditText elements found");

    }

}
