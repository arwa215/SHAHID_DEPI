package com.sample;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ShahidWatchlistTest {
    private static final Logger logger = LoggerFactory.getLogger(ShahidWatchlistTest.class);

    // Define some test data (mocked content)
    private static final String MOVIE_1 = "Action Movie 1";
    private static final String MOVIE_2 = "Comedy Show 1";
    private static final String MOVIE_3 = "Drama Series 1";

    private WatchlistService watchlistService = new WatchlistService();

    @Test
    public void testAddingAndRemovingVideosFromWatchlist() {
        // Adding videos to the watchlist
        boolean addedMovie1 = watchlistService.addToWatchlist(MOVIE_1);
        boolean addedMovie2 = watchlistService.addToWatchlist(MOVIE_2);

        // Assert that the videos are added to the watchlist
        Assert.assertTrue(addedMovie1, "Failed to add Movie 1 to watchlist.");
        Assert.assertTrue(addedMovie2, "Failed to add Movie 2 to watchlist.");
        logger.info("Added movies to watchlist: {}, {}", MOVIE_1, MOVIE_2);

        // Check the watchlist content after addition
        List<String> watchlist = watchlistService.getWatchlist();
        Assert.assertTrue(watchlist.contains(MOVIE_1), "Watchlist does not contain Movie 1.");
        Assert.assertTrue(watchlist.contains(MOVIE_2), "Watchlist does not contain Movie 2.");

        // Removing a video from the watchlist
        boolean removedMovie1 = watchlistService.removeFromWatchlist(MOVIE_1);

        // Assert that Movie 1 is removed successfully
        Assert.assertTrue(removedMovie1, "Failed to remove Movie 1 from watchlist.");

        // Check the watchlist content after removal
        watchlist = watchlistService.getWatchlist();
        Assert.assertFalse(watchlist.contains(MOVIE_1), "Watchlist still contains Movie 1 after removal.");
        Assert.assertTrue(watchlist.contains(MOVIE_2), "Watchlist should still contain Movie 2.");

        // Log results
        logger.info("Watchlist after removal of Movie 1: {}", watchlist);
    }

    // Mocked WatchlistService to simulate adding and removing videos from the watchlist
    private static class WatchlistService {
        private List<String> watchlist = new java.util.ArrayList<>();

        // Simulate adding a movie to the watchlist
        public boolean addToWatchlist(String movie) {
            return watchlist.add(movie);
        }

        // Simulate removing a movie from the watchlist
        public boolean removeFromWatchlist(String movie) {
            return watchlist.remove(movie);
        }

        // Simulate fetching the user's current watchlist
        public List<String> getWatchlist() {
            return watchlist;
        }
    }
}

