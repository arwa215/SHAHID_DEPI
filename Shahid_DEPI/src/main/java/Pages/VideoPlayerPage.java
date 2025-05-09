package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VideoPlayerPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // CSS Selectors
    public By playButton = By.xpath("//*[@id=\"controls\"]/div[2]/div[3]/div/button");
    public By pauseButton = By.xpath("//*[@id=\"controls\"]/div[2]/div[3]/div/button");
    private By rewindButton = By.xpath("//*[@id=\"controls\"]/div[2]/div[4]/div/button");
    private By forwardButton = By.xpath("//*[@id=\"controls\"]/div[2]/div[5]/div/button");
    private By qualityButton = By.xpath("//*[@id=\"controls\"]/div[2]/div[13]/button");
    private By qualityOption360p = By.xpath(".//*[@id=\"qualityContainer\"]/div[3]/div/span");
    private By qualityOption720p = By.xpath("//*[@id=\"qualityContainer\"]/div[2]/div/span");
    private By qualityOption1080p = By.xpath("//*[@id=\"qualityContainer\"]/div[1]/div/span");
    private By subtitlesButton = By.xpath("//*[@id=\"controls\"]/div[2]/div[12]/button");
    public By nextEpisodeButton = By.xpath("//*[@id=\"controls\"]/div[2]/div[11]/div/button");
    private By videoPlayer = By.xpath("//*[@id=\"bitmovin-element\"]");
    private By adOverlay = By.xpath("/html/body/div[1]/div[2]/div/div"); // Example ad overlay selector

    public VideoPlayerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
    }

    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
    }

    public void waitForVideoToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(videoPlayer));
        wait.until(webDriver -> {
            return (Boolean) ((org.openqa.selenium.JavascriptExecutor) webDriver)
                    .executeScript("return arguments[0].readyState > 0",
                            driver.findElement(videoPlayer));
        });
    }

    public void waitForAdToFinish(int maxSeconds) {
        try {
            WebDriverWait adWait = new WebDriverWait(driver, Duration.ofSeconds(maxSeconds));
            adWait.until(ExpectedConditions.invisibilityOfElementLocated(adOverlay));
        } catch (Exception e) {
            System.out.println("No ad detected or ad timeout reached");
        }
    }

    public void clickPlay() {
        wait.until(ExpectedConditions.elementToBeClickable(playButton)).click();
    }

    public void clickPause() {
        wait.until(ExpectedConditions.elementToBeClickable(pauseButton)).click();
    }

    public void clickRewind() {
        wait.until(ExpectedConditions.elementToBeClickable(rewindButton)).click();
    }

    public void clickForward() {
        wait.until(ExpectedConditions.elementToBeClickable(forwardButton)).click();
    }

    public void openQualitySettings() {
        wait.until(ExpectedConditions.elementToBeClickable(qualityButton)).click();
    }

    public void selectQuality(String quality) {
        switch (quality) {
            case "360p":
                wait.until(ExpectedConditions.elementToBeClickable(qualityOption360p)).click();
                break;
            case "720p":
                wait.until(ExpectedConditions.elementToBeClickable(qualityOption720p)).click();
                break;
            case "1080p":
                wait.until(ExpectedConditions.elementToBeClickable(qualityOption1080p)).click();
                break;
            default:
                throw new IllegalArgumentException("Unsupported quality: " + quality);
        }
    }

    public void toggleSubtitles() {
        wait.until(ExpectedConditions.elementToBeClickable(subtitlesButton)).click();
    }

    public void clickNextEpisode() {
        wait.until(ExpectedConditions.elementToBeClickable(nextEpisodeButton)).click();
    }

    public boolean isVideoPlaying() {
        return (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "return arguments[0].paused === false && arguments[0].currentTime > 0",
                driver.findElement(videoPlayer));
    }

    public boolean isVideoPaused() {
        return (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "return arguments[0].paused === true",
                driver.findElement(videoPlayer));
    }

    public double getCurrentPlaybackTime() {
        return (Double) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "return arguments[0].currentTime",
                driver.findElement(videoPlayer));
    }

    public String getCurrentQuality() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(qualityButton)).getText();
    }

    public boolean areSubtitlesEnabled() {
        WebElement subtitlesBtn = driver.findElement(subtitlesButton);
        return subtitlesBtn.getAttribute("class").contains("active");
    }

    public void waitForPlaybackTimeChange(double initialTime, boolean shouldIncrease) {
        wait.until(webDriver -> {
            double currentTime = getCurrentPlaybackTime();
            return shouldIncrease ? currentTime > initialTime : currentTime < initialTime;
        });
    }

    public void waitForQualityChange(String quality) {
        wait.until(webDriver -> getCurrentQuality().contains(quality));
    }

    public void waitForPlayback(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}