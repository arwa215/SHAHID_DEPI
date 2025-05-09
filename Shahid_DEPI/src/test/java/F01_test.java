import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class F01_test {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup(){

        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://shahid.mbc.net/en");
        wait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    @Test
    public void Account_settings() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/en/hub/login']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailMobileSignIn.userName"))).sendKeys("01211629204");
        driver.findElement(By.id("emailMobileSignIn.password")).sendKeys("M5tb.Qzzq&5iRiM");
        Thread.sleep(6000);
        driver.findElement(By.cssSelector("button[type='submit'].btn-nsf-primary")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[alt='elshamyy611']"))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/en/subscribe']"))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/en/subscribe']"))).click();
       //driver.findElement(By.cssSelector("a[href='/en/subscribe']")).click();
        Thread.sleep(3000);
        driver.get("https://shahid.mbc.net/en/hub/promo");
      //  driver.findElement(By.className("cursor-pointer")).click();
      //  driver.findElement(By.className("justify-center")).click();
        driver.findElement(By.className("bg-packageButtonBg")).click();
        Thread.sleep(5000);
       // driver.findElement(By.xpath("//*[@id=\"adyen-checkout-encryptedCardNumber-1746796417283\"]")).sendKeys("4525262628526");
      //  driver.findElement(By.xpath("//*[@id=\"adyen-checkout-encryptedCardNumber-1746796757323\"]")).sendKeys();
       // driver.findElement(By.id("adyen-checkout-encryptedExpiryDate-1746773282698")).sendKeys("0226");
        //driver.findElement(By.id("adyen-checkout-encryptedSecurityCode-1746773282699")).sendKeys("123");
        By applyButton = By.cssSelector("button.plan_applyButton__TCibp");
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(applyButton));
        Assert.assertTrue(buttonElement.isDisplayed(), "Apply button should be displayed");
        Thread.sleep(4000);
        // driver.quit();





















    }
}
