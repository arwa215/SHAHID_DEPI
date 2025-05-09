import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Initiate_Shahid {

    public static WebDriver driver;

    @BeforeMethod
    public static void Open_Shahid() {
        {
            driver = new EdgeDriver();  // Now initializes the class-level 'driver'
            driver.manage().window().maximize();
            driver.get("https://shahid.mbc.net/en?msockid=1ea2289851e965cc03cb3d7b50f964b7");
        }
    }
@AfterMethod
        public static void Close_Browser(){
                driver.quit();
        }

        }




