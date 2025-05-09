package tests;

import Pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest {
    private WebDriver driver;
    private SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://shahid.mbc.net/");
        searchPage = new SearchPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testValidMovieSearch() {
        searchPage.openSearch();
        searchPage.searchForTitle("John Wick");
        Assert.assertTrue(searchPage.isResultFound(), "Movie not found in search results!");
    }

    @Test
    public void testValidSeriesSearch() {
        searchPage.openSearch();
        searchPage.searchForTitle("ASHGHAL SHAQQA GEDDAN");
        Assert.assertTrue(searchPage.isResultFound(), "TV series not found in search results!");
    }

    @Test
    public void testPartialKeywordSearch() {
        searchPage.openSearch();
        searchPage.searchForTitle("ASHGHAL");
        Assert.assertTrue(searchPage.isResultFound(), "Relevant results not found for partial keyword!");
    }

    @Test
    public void testSpecialCharactersSearch() {
        searchPage.openSearch();
        searchPage.searchForTitle("@#*&");
        Assert.assertTrue(searchPage.isNoResultsMessageDisplayed() || searchPage.isResultFound(), "System did not handle special characters correctly!");
    }

    @Test
    public void testUnavailableMovieSearch() {
        searchPage.openSearch();
        searchPage.searchForTitle("kjhfdxcb");
        Assert.assertTrue(searchPage.isNoResultsMessageDisplayed(), "No results message not displayed for unavailable title!");
    }

    @Test
    public void testCaseInsensitiveSearch() {
        searchPage.openSearch();
        searchPage.searchForTitle("ashGhAL ShAqqA GeDdan");
        Assert.assertTrue(searchPage.isResultFound(), "Case insensitive search failed!");
    }

    @Test
    public void testSearchWithExtraSpaces() {
        searchPage.openSearch();
        searchPage.searchForTitle("       ASHGHAL        SHAQQA   GEDDAN");
        Assert.assertTrue(searchPage.isResultFound(), "Search failed with extra spaces in title!");
    }

    @Test
    public void testDifferentLanguageSearch() {
        searchPage.openSearch();
        searchPage.searchForTitle("اشغال شقة جدا");
        Assert.assertTrue(searchPage.isResultFound(), "Different language search failed!");
    }
}