import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class F02 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod

    public void setup(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://shahid.mbc.net/en");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


    @Test
     public void testOpenAccountSettings() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/en/hub/login']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailMobileSignIn.userName"))).sendKeys("01211629204");
        driver.findElement(By.id("emailMobileSignIn.password")).sendKeys("M5tb.Qzzq&5iRiM");
        Thread.sleep(6000);
        driver.findElement(By.cssSelector("button[type='submit'].btn-nsf-primary")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[alt='elshamyy611']"))).click();
        WebElement profilePic = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='elshamyy611']")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='desktop-menu']/h3[9]/div/div")).click();
        driver.findElement(By.xpath("//*[@id='desktop-menu']/h3[9]/div/div/div/div/div[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='page-transition']/div/div/div/div[2]/div[2]/ul/li[1]/span[2]")).click();
        Thread.sleep(4000);
        WebDriver.Navigation navigate = driver.navigate();
        navigate.back();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='page-transition']/div/div/div/div[2]/div[2]/ul/li[2]/span[2]")).click();
        Thread.sleep(4000);
        navigate.back();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='page-transition']/div/div/div/div[2]/div[2]/ul/li[6]/span[2]")).click();
        Thread.sleep(4000);
        navigate.back();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='page-transition']/div/div/div/div[2]/div[3]")).click();

        Thread.sleep(4000);
        driver.quit();




       // wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/en/account-settings']"))).click();



    }

}

