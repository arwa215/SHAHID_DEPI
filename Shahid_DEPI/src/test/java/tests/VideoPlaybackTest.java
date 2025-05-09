package tests;

import Pages.VideoPlayerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class VideoPlaybackTest {
    private WebDriver driver;
    private VideoPlayerPage videoPlayerPage;

    // Test Data
    private final String VIDEO_TITLE = "ASHGHAL SHAQQA GEDDAN";
    private final String VIDEO_URL = "https://shahid.mbc.net/en/player/episodes/Ashghal-Shaqqa-Geddan-season-2-episode-1/id-49922944791249";
    private final String SERIES_URL = "https://shahid.mbc.net/en/series/Ashghal-Shaqqa-Geddan"; // Corrected series URL

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Add these arguments for better stability
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Reduced implicit wait
        driver.manage().window().maximize();

        videoPlayerPage = new VideoPlayerPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void TC01_VideoPlaybackControl() {
        System.out.println("Loading video page...");
        driver.get(VIDEO_URL);
        System.out.println("Video page loaded.");

        videoPlayerPage.waitForVideoToLoad();

        // Handle potential ad
        videoPlayerPage.waitForAdToFinish(30);

        // 1.1 Play a video
        videoPlayerPage.clickPlay();
        Assert.assertTrue(videoPlayerPage.isVideoPlaying(), "Playback should start after clicking Play");

        // 1.2 Click pause, then play
        videoPlayerPage.clickPause();
        Assert.assertTrue(videoPlayerPage.isVideoPaused(), "Video should pause when Pause button clicked");

        videoPlayerPage.clickPlay();
        Assert.assertTrue(videoPlayerPage.isVideoPlaying(), "Video should resume playback after clicking Play again");

        // 1.3 Use rewind and fast-forward buttons
        double initialTime = videoPlayerPage.getCurrentPlaybackTime();

        videoPlayerPage.clickRewind();
        videoPlayerPage.waitForPlaybackTimeChange(initialTime, false);
        Assert.assertTrue(videoPlayerPage.getCurrentPlaybackTime() < initialTime,
                "Video should rewind to an earlier position");

        initialTime = videoPlayerPage.getCurrentPlaybackTime();
        videoPlayerPage.clickForward();
        videoPlayerPage.waitForPlaybackTimeChange(initialTime, true);
        Assert.assertTrue(videoPlayerPage.getCurrentPlaybackTime() > initialTime,
                "Video should fast-forward to a later position");
    }

    @Test
    public void TC02_VideoQualities() {
        driver.get(VIDEO_URL);
        videoPlayerPage.waitForVideoToLoad();
        videoPlayerPage.waitForAdToFinish(30);

        videoPlayerPage.clickPlay();

        String[] qualities = {"360p", "720p", "1080p"};

        for (String quality : qualities) {
            videoPlayerPage.openQualitySettings();
            videoPlayerPage.selectQuality(quality);
            videoPlayerPage.waitForQualityChange(quality);

            Assert.assertTrue(videoPlayerPage.getCurrentQuality().contains(quality),
                    "Video quality should change to " + quality);
        }
    }

    @Test
    public void TC03_ResumePlayback() {
        // First session - play video and stop at specific timestamp
        driver.get(VIDEO_URL);
        videoPlayerPage.waitForVideoToLoad();
        videoPlayerPage.waitForAdToFinish(30);

        videoPlayerPage.clickPlay();
        videoPlayerPage.waitForPlayback(10);

        double playbackPosition = videoPlayerPage.getCurrentPlaybackTime();
        Assert.assertTrue(playbackPosition > 0, "Video should have played for some time");

        // Store playback position (simulating cookie/local storage)
        String playbackKey = "playbackPosition_" + VIDEO_URL.hashCode();
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('" + playbackKey + "', '" + playbackPosition + "');");

        // Second session - reopen browser and check resume
        tearDown();
        setUp();

        driver.get(VIDEO_URL);
        videoPlayerPage.waitForVideoToLoad();
        videoPlayerPage.waitForAdToFinish(30);

        // Simulate retrieving stored position
        double storedPosition = Double.parseDouble(
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("return localStorage.getItem('" + playbackKey + "');").toString());

        videoPlayerPage.clickPlay();
        videoPlayerPage.waitForPlayback(2);

        double currentPlaybackPosition = videoPlayerPage.getCurrentPlaybackTime();
        Assert.assertTrue(Math.abs(currentPlaybackPosition - storedPosition) <= 5,
                "Video should resume from approximately the last watched position");
    }

    @Test
    public void TC04_Subtitles() {
        driver.get(VIDEO_URL);
        videoPlayerPage.waitForVideoToLoad();
        videoPlayerPage.waitForAdToFinish(30);

        videoPlayerPage.clickPlay();

        // Enable subtitles
        videoPlayerPage.toggleSubtitles();
        Assert.assertTrue(videoPlayerPage.areSubtitlesEnabled(), "Subtitles should be enabled after toggling");

        // Disable subtitles
        videoPlayerPage.toggleSubtitles();
        Assert.assertFalse(videoPlayerPage.areSubtitlesEnabled(), "Subtitles should be disabled after toggling again");
    }

    @Test
    public void TC05_PlaybackControlsOnContentPage() {
        driver.get(SERIES_URL);
        videoPlayerPage.waitForPageToLoad();

        Assert.assertTrue(videoPlayerPage.isElementVisible(videoPlayerPage.playButton),
                "Play button should be visible");
        Assert.assertTrue(videoPlayerPage.isElementVisible(videoPlayerPage.nextEpisodeButton),
                "Next Episode button should be visible");

        videoPlayerPage.clickPlay();
        videoPlayerPage.waitForVideoToLoad();
        Assert.assertTrue(videoPlayerPage.isVideoPlaying(), "Video should start playing");

        // Add verification for next episode navigation if needed
    }
}