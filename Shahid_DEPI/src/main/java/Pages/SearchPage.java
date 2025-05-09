package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage extends BasePage {

    private By searchIcon = By.id("menu-search-header");
    private By searchInput = By.id("searchInput");
    private By searchResults = By.xpath("//*[@id='searchOverlay']/div[2]/div[2]/div/div/div[1]/div[1]/a/div[2]/img[@title]");
    private By noResultsMessage = By.xpath("//*[@id='searchOverlay']/div[2]/div[1]/div[1]");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void openSearch() {
        click(searchIcon);
    }

    public void searchForTitle(String title) {
        type(searchInput, title);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
        waitForResultsToLoad();
    }

    public boolean isResultFound() {
        return isElementVisible(searchResults);
    }

    public boolean isNoResultsMessageDisplayed() {
        return isElementVisible(noResultsMessage);
    }

    private void waitForResultsToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfAllElementsLocatedBy(searchResults),
                ExpectedConditions.presenceOfElementLocated(noResultsMessage)
        ));
    }
}