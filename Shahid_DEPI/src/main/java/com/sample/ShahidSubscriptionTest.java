import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShahidSubscriptionTest {
    private static final Logger logger = LoggerFactory.getLogger(ShahidSubscriptionTest.class);
    private static final double VIP_SUBSCRIPTION_PRICE = 49.99;
    private static final String CURRENCY = "SAR";

    @Test
    public void testSuccessfulPaymentAndTracking() {
        String userEmail = "user@shahid.net";
        boolean isPaymentSuccessful = processPayment(userEmail, VIP_SUBSCRIPTION_PRICE);
        boolean isHistoryTracked = trackSubscription(userEmail, isPaymentSuccessful);

        Assert.assertTrue(isPaymentSuccessful, "Payment failed on Shahid platform");
        Assert.assertTrue(isHistoryTracked, "Subscription history not updated");
    }

    @Test
    public void testFailedPaymentHandling() {
        String userEmail = "invalid@shahid.net";
        boolean isPaymentSuccessful = processPayment(userEmail, VIP_SUBSCRIPTION_PRICE);
        boolean isHistoryTracked = trackSubscription(userEmail, isPaymentSuccessful);

        Assert.assertFalse(isPaymentSuccessful, "Payment should fail for invalid user");
        Assert.assertFalse(isHistoryTracked, "History should not track failed payments");
    }

    private boolean processPayment(String userEmail, double amount) {
        logger.info("Processing payment for: {}, Amount: {} {}", userEmail, amount, CURRENCY);
        return !userEmail.contains("invalid");
    }

    private boolean trackSubscription(String userEmail, boolean paymentSuccess) {
        if (paymentSuccess) {
            logger.info("Subscription for {} tracked in history", userEmail);
            return true;
        }
        return false;
    }
}