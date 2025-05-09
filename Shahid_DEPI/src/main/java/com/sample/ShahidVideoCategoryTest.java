package com.sample;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ShahidVideoCategoryTest {
    private static final Logger logger = LoggerFactory.getLogger(ShahidVideoCategoryTest.class);

    // Define categories
    private static final String ACTION_CATEGORY = "Action";
    private static final String COMEDY_CATEGORY = "Comedy";
    private static final String DRAMA_CATEGORY = "Drama";

    @Test
    public void testBrowsingVideosByCategory() {
        // Test category selection and content display for different categories.

        // Test for Action Category
        List<String> actionVideos = browseVideosByCategory(ACTION_CATEGORY);
        Assert.assertTrue(actionVideos.size() > 0, "No videos found in Action category");
        logger.info("Action category contains {} videos", actionVideos.size());

        // Test for Comedy Category
        List<String> comedyVideos = browseVideosByCategory(COMEDY_CATEGORY);
        Assert.assertTrue(comedyVideos.size() > 0, "No videos found in Comedy category");
        logger.info("Comedy category contains {} videos", comedyVideos.size());

        // Test for Drama Category
        List<String> dramaVideos = browseVideosByCategory(DRAMA_CATEGORY);
        Assert.assertTrue(dramaVideos.size() > 0, "No videos found in Drama category");
        logger.info("Drama category contains {} videos", dramaVideos.size());
    }

    // Mock method to simulate browsing videos by category (in real tests, this would query a database or API)
    private List<String> browseVideosByCategory(String category) {
        // Simulating categories with a simple list of videos (in real test, this would be fetched from a service).
        if (category.equals(ACTION_CATEGORY)) {
            return List.of("Action Movie 1", "Action Movie 2", "Action Movie 3");
        } else if (category.equals(COMEDY_CATEGORY)) {
            return List.of("Comedy Show 1", "Comedy Show 2");
        } else if (category.equals(DRAMA_CATEGORY)) {
            return List.of("Drama Series 1", "Drama Series 2", "Drama Series 3", "Drama Series 4");
        }
        return List.of();
    }
}

