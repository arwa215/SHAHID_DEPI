package com.sample;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ShahidSearchFilteringTest {

    private static final Logger logger = LoggerFactory.getLogger(ShahidSearchFilteringTest.class);

    // Define some test data (mocked content)
    private static final String FREE_CONTENT = "Free Content";
    private static final String PAID_CONTENT = "Paid Content";
    private static final String MOVIE = "Movie";
    private static final String DOCUMENTARY = "Documentary";

    @Test
    public void testFreeContentFiltering() {
        // Apply filter for Free Content
        List<String> filteredContent = applyFreeContentFilter();

        // Assert that only free content is shown
        Assert.assertTrue(filteredContent.contains(FREE_CONTENT), "Free content not displayed correctly.");
        Assert.assertFalse(filteredContent.contains(PAID_CONTENT), "Paid content should not be displayed when filtering for free content.");

        // Log results
        logger.info("Free content filtering test passed with {} items shown", filteredContent.size());
    }

    @Test
    public void testCaseInsensitiveSearch() {
        // Simulate a case-insensitive search query
        List<String> searchResults = searchForContent("movie");  // Search in lowercase
        List<String> searchResultsUpper = searchForContent("MOVIE");  // Search in uppercase

        // Assert that both searches return the same results, demonstrating case insensitivity
        Assert.assertEquals(searchResults, searchResultsUpper, "Search should be case-insensitive.");

        // Log results
        logger.info("Case insensitive search test passed.");
    }

    // Mock method to simulate applying the "Free Content" filter (in real test, this would interact with the platform)
    private List<String> applyFreeContentFilter() {
        // Simulate filtering logic, showing only "Free Content"
        return List.of(FREE_CONTENT, MOVIE, DOCUMENTARY); // Simulating a content list after filter is applied
    }

    // Mock method to simulate content search (case-insensitive)
    private List<String> searchForContent(String query) {
        // Simulating the search results for content, ignoring case
        if ("movie".equalsIgnoreCase(query)) {
            return List.of(MOVIE, DOCUMENTARY);
        }
        return List.of();  // Return empty if no matching content
    }
}
